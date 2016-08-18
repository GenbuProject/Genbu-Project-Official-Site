package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class MediaBrowserCompat
{
  public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
  public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
  private static final String TAG = "MediaBrowserCompat";
  private final MediaBrowserImpl mImpl;
  
  public MediaBrowserCompat(Context paramContext, ComponentName paramComponentName, ConnectionCallback paramConnectionCallback, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 23)
    {
      this.mImpl = new MediaBrowserImplApi23(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
      return;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      this.mImpl = new MediaBrowserImplApi21(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
      return;
    }
    this.mImpl = new MediaBrowserServiceImplBase(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
  }
  
  public void connect()
  {
    this.mImpl.connect();
  }
  
  public void disconnect()
  {
    this.mImpl.disconnect();
  }
  
  @Nullable
  public Bundle getExtras()
  {
    return this.mImpl.getExtras();
  }
  
  public void getItem(@NonNull String paramString, @NonNull ItemCallback paramItemCallback)
  {
    this.mImpl.getItem(paramString, paramItemCallback);
  }
  
  @NonNull
  public String getRoot()
  {
    return this.mImpl.getRoot();
  }
  
  @NonNull
  public ComponentName getServiceComponent()
  {
    return this.mImpl.getServiceComponent();
  }
  
  @NonNull
  public MediaSessionCompat.Token getSessionToken()
  {
    return this.mImpl.getSessionToken();
  }
  
  public boolean isConnected()
  {
    return this.mImpl.isConnected();
  }
  
  public void subscribe(@NonNull String paramString, @NonNull Bundle paramBundle, @NonNull SubscriptionCallback paramSubscriptionCallback)
  {
    if (paramBundle == null) {
      throw new IllegalArgumentException("options are null");
    }
    this.mImpl.subscribe(paramString, paramBundle, paramSubscriptionCallback);
  }
  
  public void subscribe(@NonNull String paramString, @NonNull SubscriptionCallback paramSubscriptionCallback)
  {
    this.mImpl.subscribe(paramString, null, paramSubscriptionCallback);
  }
  
  public void unsubscribe(@NonNull String paramString)
  {
    this.mImpl.unsubscribe(paramString, null);
  }
  
  public void unsubscribe(@NonNull String paramString, @NonNull Bundle paramBundle)
  {
    if (paramBundle == null) {
      throw new IllegalArgumentException("options are null");
    }
    this.mImpl.unsubscribe(paramString, paramBundle);
  }
  
  private static class CallbackHandler
    extends Handler
  {
    private final MediaBrowserCompat.MediaBrowserServiceCallbackImpl mCallbackImpl;
    private WeakReference<Messenger> mCallbacksMessengerRef;
    
    CallbackHandler(MediaBrowserCompat.MediaBrowserServiceCallbackImpl paramMediaBrowserServiceCallbackImpl)
    {
      this.mCallbackImpl = paramMediaBrowserServiceCallbackImpl;
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (this.mCallbacksMessengerRef == null) {
        return;
      }
      Bundle localBundle = paramMessage.getData();
      localBundle.setClassLoader(MediaSessionCompat.class.getClassLoader());
      switch (paramMessage.what)
      {
      default: 
        Log.w("MediaBrowserCompat", "Unhandled message: " + paramMessage + "\n  Client version: " + 1 + "\n  Service version: " + paramMessage.arg1);
        return;
      case 1: 
        this.mCallbackImpl.onServiceConnected((Messenger)this.mCallbacksMessengerRef.get(), localBundle.getString("data_media_item_id"), (MediaSessionCompat.Token)localBundle.getParcelable("data_media_session_token"), localBundle.getBundle("data_root_hints"));
        return;
      case 2: 
        this.mCallbackImpl.onConnectionFailed((Messenger)this.mCallbacksMessengerRef.get());
        return;
      }
      this.mCallbackImpl.onLoadChildren((Messenger)this.mCallbacksMessengerRef.get(), localBundle.getString("data_media_item_id"), localBundle.getParcelableArrayList("data_media_item_list"), localBundle.getBundle("data_options"));
    }
    
    void setCallbacksMessenger(Messenger paramMessenger)
    {
      this.mCallbacksMessengerRef = new WeakReference(paramMessenger);
    }
  }
  
  public static class ConnectionCallback
  {
    private ConnectionCallbackInternal mConnectionCallbackInternal;
    final Object mConnectionCallbackObj;
    
    public ConnectionCallback()
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21(null));
        return;
      }
      this.mConnectionCallbackObj = null;
    }
    
    public void onConnected() {}
    
    public void onConnectionFailed() {}
    
    public void onConnectionSuspended() {}
    
    void setInternalConnectionCallback(ConnectionCallbackInternal paramConnectionCallbackInternal)
    {
      this.mConnectionCallbackInternal = paramConnectionCallbackInternal;
    }
    
    static abstract interface ConnectionCallbackInternal
    {
      public abstract void onConnected();
      
      public abstract void onConnectionFailed();
      
      public abstract void onConnectionSuspended();
    }
    
    private class StubApi21
      implements MediaBrowserCompatApi21.ConnectionCallback
    {
      private StubApi21() {}
      
      public void onConnected()
      {
        if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
          MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnected();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnected();
      }
      
      public void onConnectionFailed()
      {
        if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
          MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnectionFailed();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnectionFailed();
      }
      
      public void onConnectionSuspended()
      {
        if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
          MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnectionSuspended();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnectionSuspended();
      }
    }
  }
  
  public static abstract class ItemCallback
  {
    final Object mItemCallbackObj;
    
    public ItemCallback()
    {
      if (Build.VERSION.SDK_INT >= 23)
      {
        this.mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23(null));
        return;
      }
      this.mItemCallbackObj = null;
    }
    
    public void onError(@NonNull String paramString) {}
    
    public void onItemLoaded(MediaBrowserCompat.MediaItem paramMediaItem) {}
    
    private class StubApi23
      implements MediaBrowserCompatApi23.ItemCallback
    {
      private StubApi23() {}
      
      public void onError(@NonNull String paramString)
      {
        MediaBrowserCompat.ItemCallback.this.onError(paramString);
      }
      
      public void onItemLoaded(Parcel paramParcel)
      {
        paramParcel.setDataPosition(0);
        MediaBrowserCompat.MediaItem localMediaItem = (MediaBrowserCompat.MediaItem)MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(paramParcel);
        paramParcel.recycle();
        MediaBrowserCompat.ItemCallback.this.onItemLoaded(localMediaItem);
      }
    }
  }
  
  private static class ItemReceiver
    extends ResultReceiver
  {
    private final MediaBrowserCompat.ItemCallback mCallback;
    private final String mMediaId;
    
    ItemReceiver(String paramString, MediaBrowserCompat.ItemCallback paramItemCallback, Handler paramHandler)
    {
      super();
      this.mMediaId = paramString;
      this.mCallback = paramItemCallback;
    }
    
    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      paramBundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
      if ((paramInt != 0) || (paramBundle == null) || (!paramBundle.containsKey("media_item")))
      {
        this.mCallback.onError(this.mMediaId);
        return;
      }
      paramBundle = paramBundle.getParcelable("media_item");
      if ((paramBundle instanceof MediaBrowserCompat.MediaItem))
      {
        this.mCallback.onItemLoaded((MediaBrowserCompat.MediaItem)paramBundle);
        return;
      }
      this.mCallback.onError(this.mMediaId);
    }
  }
  
  static abstract interface MediaBrowserImpl
  {
    public abstract void connect();
    
    public abstract void disconnect();
    
    @Nullable
    public abstract Bundle getExtras();
    
    public abstract void getItem(@NonNull String paramString, @NonNull MediaBrowserCompat.ItemCallback paramItemCallback);
    
    @NonNull
    public abstract String getRoot();
    
    public abstract ComponentName getServiceComponent();
    
    @NonNull
    public abstract MediaSessionCompat.Token getSessionToken();
    
    public abstract boolean isConnected();
    
    public abstract void subscribe(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback);
    
    public abstract void unsubscribe(@NonNull String paramString, Bundle paramBundle);
  }
  
  static class MediaBrowserImplApi21
    implements MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl, MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
  {
    private static final boolean DBG = false;
    protected Object mBrowserObj;
    private Messenger mCallbacksMessenger;
    private final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
    private MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
    private final ComponentName mServiceComponent;
    private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();
    
    public MediaBrowserImplApi21(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      this.mServiceComponent = paramComponentName;
      paramConnectionCallback.setInternalConnectionCallback(this);
      this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(paramContext, paramComponentName, paramConnectionCallback.mConnectionCallbackObj, paramBundle);
    }
    
    public void connect()
    {
      MediaBrowserCompatApi21.connect(this.mBrowserObj);
    }
    
    public void disconnect()
    {
      MediaBrowserCompatApi21.disconnect(this.mBrowserObj);
    }
    
    @Nullable
    public Bundle getExtras()
    {
      return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
    }
    
    public void getItem(@NonNull final String paramString, @NonNull final MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("mediaId is empty.");
      }
      if (paramItemCallback == null) {
        throw new IllegalArgumentException("cb is null.");
      }
      if (!MediaBrowserCompatApi21.isConnected(this.mBrowserObj))
      {
        Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            paramItemCallback.onError(paramString);
          }
        });
        return;
      }
      if (this.mServiceBinderWrapper == null)
      {
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            paramItemCallback.onItemLoaded(null);
          }
        });
        return;
      }
      MediaBrowserCompat.ItemReceiver localItemReceiver = new MediaBrowserCompat.ItemReceiver(paramString, paramItemCallback, this.mHandler);
      try
      {
        this.mServiceBinderWrapper.getMediaItem(paramString, localItemReceiver);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.i("MediaBrowserCompat", "Remote error getting media item: " + paramString);
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            paramItemCallback.onError(paramString);
          }
        });
      }
    }
    
    @NonNull
    public String getRoot()
    {
      return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
    }
    
    public ComponentName getServiceComponent()
    {
      return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
    }
    
    @NonNull
    public MediaSessionCompat.Token getSessionToken()
    {
      return MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
    }
    
    public boolean isConnected()
    {
      return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
    }
    
    public void onConnected()
    {
      Object localObject = MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
      if (localObject == null) {}
      do
      {
        return;
        localObject = BundleCompat.getBinder((Bundle)localObject, "extra_messenger");
      } while (localObject == null);
      this.mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper((IBinder)localObject);
      this.mCallbacksMessenger = new Messenger(this.mHandler);
      this.mHandler.setCallbacksMessenger(this.mCallbacksMessenger);
      try
      {
        this.mServiceBinderWrapper.registerCallbackMessenger(this.mCallbacksMessenger);
        onServiceConnected(this.mCallbacksMessenger, null, null, null);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
        }
      }
    }
    
    public void onConnectionFailed() {}
    
    public void onConnectionFailed(Messenger paramMessenger) {}
    
    public void onConnectionSuspended()
    {
      this.mServiceBinderWrapper = null;
      this.mCallbacksMessenger = null;
    }
    
    public void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, @NonNull Bundle paramBundle)
    {
      if (this.mCallbacksMessenger != paramMessenger) {}
      do
      {
        return;
        paramMessenger = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(paramString);
      } while (paramMessenger == null);
      paramMessenger.getCallback(paramBundle).onChildrenLoaded(paramString, paramList, paramBundle);
    }
    
    public void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
    {
      paramMessenger = this.mSubscriptions.entrySet().iterator();
      if (paramMessenger.hasNext())
      {
        paramToken = (Map.Entry)paramMessenger.next();
        paramString = (String)paramToken.getKey();
        paramBundle = (MediaBrowserCompat.Subscription)paramToken.getValue();
        paramToken = paramBundle.getOptionsList();
        paramBundle = paramBundle.getCallbacks();
        int i = 0;
        label69:
        if (i < paramToken.size())
        {
          if (paramToken.get(i) != null) {
            break label123;
          }
          MediaBrowserCompatApi21.subscribe(this.mBrowserObj, paramString, MediaBrowserCompat.SubscriptionCallbackApi21.access$1800((MediaBrowserCompat.SubscriptionCallbackApi21)paramBundle.get(i)));
        }
        for (;;)
        {
          i += 1;
          break label69;
          break;
          try
          {
            label123:
            this.mServiceBinderWrapper.addSubscription(paramString, (Bundle)paramToken.get(i), this.mCallbacksMessenger);
          }
          catch (RemoteException localRemoteException)
          {
            Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + paramString);
          }
        }
      }
    }
    
    public void subscribe(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      MediaBrowserCompat.SubscriptionCallbackApi21 localSubscriptionCallbackApi21 = new MediaBrowserCompat.SubscriptionCallbackApi21(paramSubscriptionCallback, paramBundle);
      MediaBrowserCompat.Subscription localSubscription = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(paramString);
      paramSubscriptionCallback = localSubscription;
      if (localSubscription == null)
      {
        paramSubscriptionCallback = new MediaBrowserCompat.Subscription();
        this.mSubscriptions.put(paramString, paramSubscriptionCallback);
      }
      paramSubscriptionCallback.setCallbackForOptions(localSubscriptionCallbackApi21, paramBundle);
      if (MediaBrowserCompatApi21.isConnected(this.mBrowserObj))
      {
        if ((paramBundle == null) || (this.mServiceBinderWrapper == null)) {
          MediaBrowserCompatApi21.subscribe(this.mBrowserObj, paramString, MediaBrowserCompat.SubscriptionCallbackApi21.access$1800(localSubscriptionCallbackApi21));
        }
      }
      else {
        return;
      }
      try
      {
        this.mServiceBinderWrapper.addSubscription(paramString, paramBundle, this.mCallbacksMessenger);
        return;
      }
      catch (RemoteException paramBundle)
      {
        Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + paramString);
      }
    }
    
    public void unsubscribe(@NonNull String paramString, Bundle paramBundle)
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("parentId is empty.");
      }
      MediaBrowserCompat.Subscription localSubscription = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(paramString);
      if ((localSubscription != null) && (localSubscription.remove(paramBundle)))
      {
        if ((paramBundle != null) && (this.mServiceBinderWrapper != null)) {
          break label96;
        }
        if ((this.mServiceBinderWrapper != null) || (localSubscription.isEmpty())) {
          MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, paramString);
        }
      }
      for (;;)
      {
        if ((localSubscription != null) && (localSubscription.isEmpty())) {
          this.mSubscriptions.remove(paramString);
        }
        return;
        label96:
        if (this.mServiceBinderWrapper == null) {
          try
          {
            this.mServiceBinderWrapper.removeSubscription(paramString, paramBundle, this.mCallbacksMessenger);
          }
          catch (RemoteException paramBundle)
          {
            Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + paramString);
          }
        }
      }
    }
  }
  
  static class MediaBrowserImplApi23
    extends MediaBrowserCompat.MediaBrowserImplApi21
  {
    public MediaBrowserImplApi23(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      super(paramComponentName, paramConnectionCallback, paramBundle);
    }
    
    public void getItem(@NonNull String paramString, @NonNull MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      MediaBrowserCompatApi23.getItem(this.mBrowserObj, paramString, paramItemCallback.mItemCallbackObj);
    }
  }
  
  static abstract interface MediaBrowserServiceCallbackImpl
  {
    public abstract void onConnectionFailed(Messenger paramMessenger);
    
    public abstract void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle);
    
    public abstract void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle);
  }
  
  static class MediaBrowserServiceImplBase
    implements MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl
  {
    private static final int CONNECT_STATE_CONNECTED = 2;
    private static final int CONNECT_STATE_CONNECTING = 1;
    private static final int CONNECT_STATE_DISCONNECTED = 0;
    private static final int CONNECT_STATE_SUSPENDED = 3;
    private static final boolean DBG = false;
    private final MediaBrowserCompat.ConnectionCallback mCallback;
    private Messenger mCallbacksMessenger;
    private final Context mContext;
    private Bundle mExtras;
    private final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
    private MediaSessionCompat.Token mMediaSessionToken;
    private final Bundle mRootHints;
    private String mRootId;
    private MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
    private final ComponentName mServiceComponent;
    private MediaServiceConnection mServiceConnection;
    private int mState = 0;
    private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();
    
    public MediaBrowserServiceImplBase(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      if (paramContext == null) {
        throw new IllegalArgumentException("context must not be null");
      }
      if (paramComponentName == null) {
        throw new IllegalArgumentException("service component must not be null");
      }
      if (paramConnectionCallback == null) {
        throw new IllegalArgumentException("connection callback must not be null");
      }
      this.mContext = paramContext;
      this.mServiceComponent = paramComponentName;
      this.mCallback = paramConnectionCallback;
      this.mRootHints = paramBundle;
    }
    
    private void forceCloseConnection()
    {
      if (this.mServiceConnection != null) {
        this.mContext.unbindService(this.mServiceConnection);
      }
      this.mState = 0;
      this.mServiceConnection = null;
      this.mServiceBinderWrapper = null;
      this.mCallbacksMessenger = null;
      this.mRootId = null;
      this.mMediaSessionToken = null;
    }
    
    private static String getStateLabel(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return "UNKNOWN/" + paramInt;
      case 0: 
        return "CONNECT_STATE_DISCONNECTED";
      case 1: 
        return "CONNECT_STATE_CONNECTING";
      case 2: 
        return "CONNECT_STATE_CONNECTED";
      }
      return "CONNECT_STATE_SUSPENDED";
    }
    
    private boolean isCurrent(Messenger paramMessenger, String paramString)
    {
      if (this.mCallbacksMessenger != paramMessenger)
      {
        if (this.mState != 0) {
          Log.i("MediaBrowserCompat", paramString + " for " + this.mServiceComponent + " with mCallbacksMessenger=" + this.mCallbacksMessenger + " this=" + this);
        }
        return false;
      }
      return true;
    }
    
    public void connect()
    {
      if (this.mState != 0) {
        throw new IllegalStateException("connect() called while not disconnected (state=" + getStateLabel(this.mState) + ")");
      }
      if (this.mServiceBinderWrapper != null) {
        throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + this.mServiceBinderWrapper);
      }
      if (this.mCallbacksMessenger != null) {
        throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + this.mCallbacksMessenger);
      }
      this.mState = 1;
      Intent localIntent = new Intent("android.media.browse.MediaBrowserService");
      localIntent.setComponent(this.mServiceComponent);
      final MediaServiceConnection localMediaServiceConnection = new MediaServiceConnection(null);
      this.mServiceConnection = localMediaServiceConnection;
      int i = 0;
      try
      {
        boolean bool = this.mContext.bindService(localIntent, this.mServiceConnection, 1);
        i = bool;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.e("MediaBrowserCompat", "Failed binding to service " + this.mServiceComponent);
        }
      }
      if (i == 0) {
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            if (localMediaServiceConnection == MediaBrowserCompat.MediaBrowserServiceImplBase.this.mServiceConnection)
            {
              MediaBrowserCompat.MediaBrowserServiceImplBase.this.forceCloseConnection();
              MediaBrowserCompat.MediaBrowserServiceImplBase.this.mCallback.onConnectionFailed();
            }
          }
        });
      }
    }
    
    public void disconnect()
    {
      if (this.mCallbacksMessenger != null) {}
      try
      {
        this.mServiceBinderWrapper.disconnect(this.mCallbacksMessenger);
        forceCloseConnection();
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          Log.w("MediaBrowserCompat", "RemoteException during connect for " + this.mServiceComponent);
        }
      }
    }
    
    void dump()
    {
      Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
      Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.mServiceComponent);
      Log.d("MediaBrowserCompat", "  mCallback=" + this.mCallback);
      Log.d("MediaBrowserCompat", "  mRootHints=" + this.mRootHints);
      Log.d("MediaBrowserCompat", "  mState=" + getStateLabel(this.mState));
      Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.mServiceConnection);
      Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.mServiceBinderWrapper);
      Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.mCallbacksMessenger);
      Log.d("MediaBrowserCompat", "  mRootId=" + this.mRootId);
      Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.mMediaSessionToken);
    }
    
    @Nullable
    public Bundle getExtras()
    {
      if (!isConnected()) {
        throw new IllegalStateException("getExtras() called while not connected (state=" + getStateLabel(this.mState) + ")");
      }
      return this.mExtras;
    }
    
    public void getItem(@NonNull final String paramString, @NonNull final MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("mediaId is empty.");
      }
      if (paramItemCallback == null) {
        throw new IllegalArgumentException("cb is null.");
      }
      if (this.mState != 2)
      {
        Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            paramItemCallback.onError(paramString);
          }
        });
        return;
      }
      MediaBrowserCompat.ItemReceiver localItemReceiver = new MediaBrowserCompat.ItemReceiver(paramString, paramItemCallback, this.mHandler);
      try
      {
        this.mServiceBinderWrapper.getMediaItem(paramString, localItemReceiver);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        Log.i("MediaBrowserCompat", "Remote error getting media item.");
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            paramItemCallback.onError(paramString);
          }
        });
      }
    }
    
    @NonNull
    public String getRoot()
    {
      if (!isConnected()) {
        throw new IllegalStateException("getRoot() called while not connected(state=" + getStateLabel(this.mState) + ")");
      }
      return this.mRootId;
    }
    
    @NonNull
    public ComponentName getServiceComponent()
    {
      if (!isConnected()) {
        throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.mState + ")");
      }
      return this.mServiceComponent;
    }
    
    @NonNull
    public MediaSessionCompat.Token getSessionToken()
    {
      if (!isConnected()) {
        throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.mState + ")");
      }
      return this.mMediaSessionToken;
    }
    
    public boolean isConnected()
    {
      return this.mState == 2;
    }
    
    public void onConnectionFailed(Messenger paramMessenger)
    {
      Log.e("MediaBrowserCompat", "onConnectFailed for " + this.mServiceComponent);
      if (!isCurrent(paramMessenger, "onConnectFailed")) {
        return;
      }
      if (this.mState != 1)
      {
        Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
        return;
      }
      forceCloseConnection();
      this.mCallback.onConnectionFailed();
    }
    
    public void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle)
    {
      if (!isCurrent(paramMessenger, "onLoadChildren")) {}
      do
      {
        do
        {
          return;
          paramMessenger = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(paramString);
        } while (paramMessenger == null);
        paramMessenger = paramMessenger.getCallback(paramBundle);
      } while (paramMessenger == null);
      if (paramBundle == null)
      {
        paramMessenger.onChildrenLoaded(paramString, paramList);
        return;
      }
      paramMessenger.onChildrenLoaded(paramString, paramList, paramBundle);
    }
    
    public void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
    {
      if (!isCurrent(paramMessenger, "onConnect")) {}
      for (;;)
      {
        return;
        if (this.mState != 1)
        {
          Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
          return;
        }
        this.mRootId = paramString;
        this.mMediaSessionToken = paramToken;
        this.mExtras = paramBundle;
        this.mState = 2;
        this.mCallback.onConnected();
        try
        {
          paramMessenger = this.mSubscriptions.entrySet().iterator();
          while (paramMessenger.hasNext())
          {
            paramToken = (Map.Entry)paramMessenger.next();
            paramString = (String)paramToken.getKey();
            paramToken = ((MediaBrowserCompat.Subscription)paramToken.getValue()).getOptionsList().iterator();
            while (paramToken.hasNext())
            {
              paramBundle = (Bundle)paramToken.next();
              this.mServiceBinderWrapper.addSubscription(paramString, paramBundle, this.mCallbacksMessenger);
            }
          }
          return;
        }
        catch (RemoteException paramMessenger)
        {
          Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
        }
      }
    }
    
    public void subscribe(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("parentId is empty.");
      }
      if (paramSubscriptionCallback == null) {
        throw new IllegalArgumentException("callback is null");
      }
      MediaBrowserCompat.Subscription localSubscription2 = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(paramString);
      MediaBrowserCompat.Subscription localSubscription1 = localSubscription2;
      if (localSubscription2 == null)
      {
        localSubscription1 = new MediaBrowserCompat.Subscription();
        this.mSubscriptions.put(paramString, localSubscription1);
      }
      localSubscription1.setCallbackForOptions(paramSubscriptionCallback, paramBundle);
      if (this.mState == 2) {}
      try
      {
        this.mServiceBinderWrapper.addSubscription(paramString, paramBundle, this.mCallbacksMessenger);
        return;
      }
      catch (RemoteException paramBundle)
      {
        Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + paramString);
      }
    }
    
    public void unsubscribe(@NonNull String paramString, Bundle paramBundle)
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("parentId is empty.");
      }
      MediaBrowserCompat.Subscription localSubscription = (MediaBrowserCompat.Subscription)this.mSubscriptions.get(paramString);
      if ((localSubscription != null) && (localSubscription.remove(paramBundle)) && (this.mState == 2)) {}
      try
      {
        this.mServiceBinderWrapper.removeSubscription(paramString, paramBundle, this.mCallbacksMessenger);
        if ((localSubscription != null) && (localSubscription.isEmpty())) {
          this.mSubscriptions.remove(paramString);
        }
        return;
      }
      catch (RemoteException paramBundle)
      {
        for (;;)
        {
          Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + paramString);
        }
      }
    }
    
    private class MediaServiceConnection
      implements ServiceConnection
    {
      private MediaServiceConnection() {}
      
      private boolean isCurrent(String paramString)
      {
        if (MediaBrowserCompat.MediaBrowserServiceImplBase.this.mServiceConnection != this)
        {
          if (MediaBrowserCompat.MediaBrowserServiceImplBase.this.mState != 0) {
            Log.i("MediaBrowserCompat", paramString + " for " + MediaBrowserCompat.MediaBrowserServiceImplBase.this.mServiceComponent + " with mServiceConnection=" + MediaBrowserCompat.MediaBrowserServiceImplBase.this.mServiceConnection + " this=" + this);
          }
          return false;
        }
        return true;
      }
      
      private void postOrRun(Runnable paramRunnable)
      {
        if (Thread.currentThread() == MediaBrowserCompat.MediaBrowserServiceImplBase.this.mHandler.getLooper().getThread())
        {
          paramRunnable.run();
          return;
        }
        MediaBrowserCompat.MediaBrowserServiceImplBase.this.mHandler.post(paramRunnable);
      }
      
      public void onServiceConnected(final ComponentName paramComponentName, final IBinder paramIBinder)
      {
        postOrRun(new Runnable()
        {
          public void run()
          {
            if (!MediaBrowserCompat.MediaBrowserServiceImplBase.MediaServiceConnection.this.isCurrent("onServiceConnected")) {
              return;
            }
            MediaBrowserCompat.MediaBrowserServiceImplBase.access$1102(MediaBrowserCompat.MediaBrowserServiceImplBase.this, new MediaBrowserCompat.ServiceBinderWrapper(paramIBinder));
            MediaBrowserCompat.MediaBrowserServiceImplBase.access$1202(MediaBrowserCompat.MediaBrowserServiceImplBase.this, new Messenger(MediaBrowserCompat.MediaBrowserServiceImplBase.this.mHandler));
            MediaBrowserCompat.MediaBrowserServiceImplBase.this.mHandler.setCallbacksMessenger(MediaBrowserCompat.MediaBrowserServiceImplBase.this.mCallbacksMessenger);
            MediaBrowserCompat.MediaBrowserServiceImplBase.access$1402(MediaBrowserCompat.MediaBrowserServiceImplBase.this, 1);
            try
            {
              MediaBrowserCompat.MediaBrowserServiceImplBase.this.mServiceBinderWrapper.connect(MediaBrowserCompat.MediaBrowserServiceImplBase.this.mContext, MediaBrowserCompat.MediaBrowserServiceImplBase.this.mRootHints, MediaBrowserCompat.MediaBrowserServiceImplBase.this.mCallbacksMessenger);
              return;
            }
            catch (RemoteException localRemoteException)
            {
              Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserCompat.MediaBrowserServiceImplBase.this.mServiceComponent);
            }
          }
        });
      }
      
      public void onServiceDisconnected(final ComponentName paramComponentName)
      {
        postOrRun(new Runnable()
        {
          public void run()
          {
            if (!MediaBrowserCompat.MediaBrowserServiceImplBase.MediaServiceConnection.this.isCurrent("onServiceDisconnected")) {
              return;
            }
            MediaBrowserCompat.MediaBrowserServiceImplBase.access$1102(MediaBrowserCompat.MediaBrowserServiceImplBase.this, null);
            MediaBrowserCompat.MediaBrowserServiceImplBase.access$1202(MediaBrowserCompat.MediaBrowserServiceImplBase.this, null);
            MediaBrowserCompat.MediaBrowserServiceImplBase.this.mHandler.setCallbacksMessenger(null);
            MediaBrowserCompat.MediaBrowserServiceImplBase.access$1402(MediaBrowserCompat.MediaBrowserServiceImplBase.this, 3);
            MediaBrowserCompat.MediaBrowserServiceImplBase.this.mCallback.onConnectionSuspended();
          }
        });
      }
    }
  }
  
  public static class MediaItem
    implements Parcelable
  {
    public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator()
    {
      public MediaBrowserCompat.MediaItem createFromParcel(Parcel paramAnonymousParcel)
      {
        return new MediaBrowserCompat.MediaItem(paramAnonymousParcel, null);
      }
      
      public MediaBrowserCompat.MediaItem[] newArray(int paramAnonymousInt)
      {
        return new MediaBrowserCompat.MediaItem[paramAnonymousInt];
      }
    };
    public static final int FLAG_BROWSABLE = 1;
    public static final int FLAG_PLAYABLE = 2;
    private final MediaDescriptionCompat mDescription;
    private final int mFlags;
    
    private MediaItem(Parcel paramParcel)
    {
      this.mFlags = paramParcel.readInt();
      this.mDescription = ((MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(paramParcel));
    }
    
    public MediaItem(@NonNull MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
    {
      if (paramMediaDescriptionCompat == null) {
        throw new IllegalArgumentException("description cannot be null");
      }
      if (TextUtils.isEmpty(paramMediaDescriptionCompat.getMediaId())) {
        throw new IllegalArgumentException("description must have a non-empty media id");
      }
      this.mFlags = paramInt;
      this.mDescription = paramMediaDescriptionCompat;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    @NonNull
    public MediaDescriptionCompat getDescription()
    {
      return this.mDescription;
    }
    
    public int getFlags()
    {
      return this.mFlags;
    }
    
    @NonNull
    public String getMediaId()
    {
      return this.mDescription.getMediaId();
    }
    
    public boolean isBrowsable()
    {
      return (this.mFlags & 0x1) != 0;
    }
    
    public boolean isPlayable()
    {
      return (this.mFlags & 0x2) != 0;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("MediaItem{");
      localStringBuilder.append("mFlags=").append(this.mFlags);
      localStringBuilder.append(", mDescription=").append(this.mDescription);
      localStringBuilder.append('}');
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(this.mFlags);
      this.mDescription.writeToParcel(paramParcel, paramInt);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public static @interface Flags {}
  }
  
  private static class ServiceBinderWrapper
  {
    private Messenger mMessenger;
    
    public ServiceBinderWrapper(IBinder paramIBinder)
    {
      this.mMessenger = new Messenger(paramIBinder);
    }
    
    private void sendRequest(int paramInt, Bundle paramBundle, Messenger paramMessenger)
      throws RemoteException
    {
      Message localMessage = Message.obtain();
      localMessage.what = paramInt;
      localMessage.arg1 = 1;
      localMessage.setData(paramBundle);
      localMessage.replyTo = paramMessenger;
      this.mMessenger.send(localMessage);
    }
    
    void addSubscription(String paramString, Bundle paramBundle, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      localBundle.putBundle("data_options", paramBundle);
      sendRequest(3, localBundle, paramMessenger);
    }
    
    void connect(Context paramContext, Bundle paramBundle, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_package_name", paramContext.getPackageName());
      localBundle.putBundle("data_root_hints", paramBundle);
      sendRequest(1, localBundle, paramMessenger);
    }
    
    void disconnect(Messenger paramMessenger)
      throws RemoteException
    {
      sendRequest(2, null, paramMessenger);
    }
    
    void getMediaItem(String paramString, ResultReceiver paramResultReceiver)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      localBundle.putParcelable("data_result_receiver", paramResultReceiver);
      sendRequest(5, localBundle, null);
    }
    
    void registerCallbackMessenger(Messenger paramMessenger)
      throws RemoteException
    {
      sendRequest(6, null, paramMessenger);
    }
    
    void removeSubscription(String paramString, Bundle paramBundle, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      localBundle.putBundle("data_options", paramBundle);
      sendRequest(4, localBundle, paramMessenger);
    }
  }
  
  private static class Subscription
  {
    private final List<MediaBrowserCompat.SubscriptionCallback> mCallbacks = new ArrayList();
    private final List<Bundle> mOptionsList = new ArrayList();
    
    public MediaBrowserCompat.SubscriptionCallback getCallback(Bundle paramBundle)
    {
      int i = 0;
      while (i < this.mOptionsList.size())
      {
        if (MediaBrowserCompatUtils.areSameOptions((Bundle)this.mOptionsList.get(i), paramBundle)) {
          return (MediaBrowserCompat.SubscriptionCallback)this.mCallbacks.get(i);
        }
        i += 1;
      }
      return null;
    }
    
    public List<MediaBrowserCompat.SubscriptionCallback> getCallbacks()
    {
      return this.mCallbacks;
    }
    
    public List<Bundle> getOptionsList()
    {
      return this.mOptionsList;
    }
    
    public boolean isEmpty()
    {
      return this.mCallbacks.isEmpty();
    }
    
    public boolean remove(Bundle paramBundle)
    {
      int i = 0;
      while (i < this.mOptionsList.size())
      {
        if (MediaBrowserCompatUtils.areSameOptions((Bundle)this.mOptionsList.get(i), paramBundle))
        {
          this.mCallbacks.remove(i);
          this.mOptionsList.remove(i);
          return true;
        }
        i += 1;
      }
      return false;
    }
    
    public void setCallbackForOptions(MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback, Bundle paramBundle)
    {
      int i = 0;
      while (i < this.mOptionsList.size())
      {
        if (MediaBrowserCompatUtils.areSameOptions((Bundle)this.mOptionsList.get(i), paramBundle))
        {
          this.mCallbacks.set(i, paramSubscriptionCallback);
          return;
        }
        i += 1;
      }
      this.mCallbacks.add(paramSubscriptionCallback);
      this.mOptionsList.add(paramBundle);
    }
  }
  
  public static abstract class SubscriptionCallback
  {
    public void onChildrenLoaded(@NonNull String paramString, List<MediaBrowserCompat.MediaItem> paramList) {}
    
    public void onChildrenLoaded(@NonNull String paramString, List<MediaBrowserCompat.MediaItem> paramList, @NonNull Bundle paramBundle) {}
    
    public void onError(@NonNull String paramString) {}
    
    public void onError(@NonNull String paramString, @NonNull Bundle paramBundle) {}
  }
  
  static class SubscriptionCallbackApi21
    extends MediaBrowserCompat.SubscriptionCallback
  {
    private Bundle mOptions;
    MediaBrowserCompat.SubscriptionCallback mSubscriptionCallback;
    private final Object mSubscriptionCallbackObj;
    
    public SubscriptionCallbackApi21(MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback, Bundle paramBundle)
    {
      this.mSubscriptionCallback = paramSubscriptionCallback;
      this.mOptions = paramBundle;
      this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21(null));
    }
    
    public void onChildrenLoaded(@NonNull String paramString, List<MediaBrowserCompat.MediaItem> paramList)
    {
      this.mSubscriptionCallback.onChildrenLoaded(paramString, paramList);
    }
    
    public void onChildrenLoaded(@NonNull String paramString, List<MediaBrowserCompat.MediaItem> paramList, @NonNull Bundle paramBundle)
    {
      this.mSubscriptionCallback.onChildrenLoaded(paramString, paramList, paramBundle);
    }
    
    public void onError(@NonNull String paramString)
    {
      this.mSubscriptionCallback.onError(paramString);
    }
    
    public void onError(@NonNull String paramString, @NonNull Bundle paramBundle)
    {
      this.mSubscriptionCallback.onError(paramString, paramBundle);
    }
    
    private class StubApi21
      implements MediaBrowserCompatApi21.SubscriptionCallback
    {
      private StubApi21() {}
      
      public void onChildrenLoaded(@NonNull String paramString, List<Parcel> paramList)
      {
        Object localObject = null;
        if (paramList != null)
        {
          ArrayList localArrayList = new ArrayList();
          paramList = paramList.iterator();
          for (;;)
          {
            localObject = localArrayList;
            if (!paramList.hasNext()) {
              break;
            }
            localObject = (Parcel)paramList.next();
            ((Parcel)localObject).setDataPosition(0);
            localArrayList.add(MediaBrowserCompat.MediaItem.CREATOR.createFromParcel((Parcel)localObject));
            ((Parcel)localObject).recycle();
          }
        }
        if (MediaBrowserCompat.SubscriptionCallbackApi21.this.mOptions != null)
        {
          MediaBrowserCompat.SubscriptionCallbackApi21.this.onChildrenLoaded(paramString, MediaBrowserCompatUtils.applyOptions((List)localObject, MediaBrowserCompat.SubscriptionCallbackApi21.this.mOptions), MediaBrowserCompat.SubscriptionCallbackApi21.this.mOptions);
          return;
        }
        MediaBrowserCompat.SubscriptionCallbackApi21.this.onChildrenLoaded(paramString, (List)localObject);
      }
      
      public void onError(@NonNull String paramString)
      {
        if (MediaBrowserCompat.SubscriptionCallbackApi21.this.mOptions != null)
        {
          MediaBrowserCompat.SubscriptionCallbackApi21.this.onError(paramString, MediaBrowserCompat.SubscriptionCallbackApi21.this.mOptions);
          return;
        }
        MediaBrowserCompat.SubscriptionCallbackApi21.this.onError(paramString);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\android\support\v4\media\MediaBrowserCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */