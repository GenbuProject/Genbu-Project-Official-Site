package android.support.v4.media;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class MediaBrowserServiceCompat
  extends Service
{
  private static final boolean DBG = false;
  public static final String KEY_MEDIA_ITEM = "media_item";
  private static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
  public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
  private static final String TAG = "MediaBrowserServiceCompat";
  private final ArrayMap<IBinder, ConnectionRecord> mConnections = new ArrayMap();
  private final ServiceHandler mHandler = new ServiceHandler(null);
  private MediaBrowserServiceImpl mImpl;
  MediaSessionCompat.Token mSession;
  
  private void addSubscription(String paramString, ConnectionRecord paramConnectionRecord, Bundle paramBundle)
  {
    Object localObject2 = (List)paramConnectionRecord.subscriptions.get(paramString);
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = new ArrayList();
    }
    localObject2 = ((List)localObject1).iterator();
    while (((Iterator)localObject2).hasNext()) {
      if (MediaBrowserCompatUtils.areSameOptions(paramBundle, (Bundle)((Iterator)localObject2).next())) {
        return;
      }
    }
    ((List)localObject1).add(paramBundle);
    paramConnectionRecord.subscriptions.put(paramString, localObject1);
    performLoadChildren(paramString, paramConnectionRecord, paramBundle);
  }
  
  private List<MediaBrowserCompat.MediaItem> applyOptions(List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
  {
    int i = paramBundle.getInt("android.media.browse.extra.PAGE", -1);
    int m = paramBundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
    if ((i == -1) && (m == -1)) {
      return paramList;
    }
    int k = m * (i - 1);
    int j = k + m;
    if ((i < 1) || (m < 1) || (k >= paramList.size())) {
      return Collections.emptyList();
    }
    i = j;
    if (j > paramList.size()) {
      i = paramList.size();
    }
    return paramList.subList(k, i);
  }
  
  private boolean isValidPackage(String paramString, int paramInt)
  {
    if (paramString == null) {}
    for (;;)
    {
      return false;
      String[] arrayOfString = getPackageManager().getPackagesForUid(paramInt);
      int i = arrayOfString.length;
      paramInt = 0;
      while (paramInt < i)
      {
        if (arrayOfString[paramInt].equals(paramString)) {
          return true;
        }
        paramInt += 1;
      }
    }
  }
  
  private void notifyChildrenChangedInternal(final String paramString, final Bundle paramBundle)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
    }
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        Iterator localIterator = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();
        for (;;)
        {
          if (!localIterator.hasNext()) {
            return;
          }
          Object localObject1 = (IBinder)localIterator.next();
          localObject1 = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localObject1);
          Object localObject2 = (List)((MediaBrowserServiceCompat.ConnectionRecord)localObject1).subscriptions.get(paramString);
          if (localObject2 != null)
          {
            localObject2 = ((List)localObject2).iterator();
            if (((Iterator)localObject2).hasNext())
            {
              Bundle localBundle = (Bundle)((Iterator)localObject2).next();
              if (!MediaBrowserCompatUtils.hasDuplicatedItems(paramBundle, localBundle)) {
                break;
              }
              MediaBrowserServiceCompat.this.performLoadChildren(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject1, localBundle);
            }
          }
        }
      }
    });
  }
  
  private void performLoadChildren(final String paramString, final ConnectionRecord paramConnectionRecord, final Bundle paramBundle)
  {
    Result local3 = new Result(paramString)
    {
      void onResultSent(List<MediaBrowserCompat.MediaItem> paramAnonymousList, int paramAnonymousInt)
      {
        if (MediaBrowserServiceCompat.this.mConnections.get(paramConnectionRecord.callbacks.asBinder()) != paramConnectionRecord) {
          return;
        }
        if ((paramAnonymousInt & 0x1) != 0) {
          paramAnonymousList = MediaBrowserCompatUtils.applyOptions(paramAnonymousList, paramBundle);
        }
        for (;;)
        {
          try
          {
            paramConnectionRecord.callbacks.onLoadChildren(paramString, paramAnonymousList, paramBundle);
            return;
          }
          catch (RemoteException paramAnonymousList)
          {
            Log.w("MediaBrowserServiceCompat", "Calling onLoadChildren() failed for id=" + paramString + " package=" + paramConnectionRecord.pkg);
            return;
          }
        }
      }
    };
    if (paramBundle == null) {
      onLoadChildren(paramString, local3);
    }
    while (!local3.isDone())
    {
      throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + paramConnectionRecord.pkg + " id=" + paramString);
      onLoadChildren(paramString, local3, paramBundle);
    }
  }
  
  private void performLoadItem(String paramString, final ResultReceiver paramResultReceiver)
  {
    paramResultReceiver = new Result(paramString)
    {
      void onResultSent(MediaBrowserCompat.MediaItem paramAnonymousMediaItem, int paramAnonymousInt)
      {
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("media_item", paramAnonymousMediaItem);
        paramResultReceiver.send(0, localBundle);
      }
    };
    onLoadItem(paramString, paramResultReceiver);
    if (!paramResultReceiver.isDone()) {
      throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + paramString);
    }
  }
  
  private boolean removeSubscription(String paramString, ConnectionRecord paramConnectionRecord, Bundle paramBundle)
  {
    boolean bool2 = false;
    boolean bool3 = false;
    List localList = (List)paramConnectionRecord.subscriptions.get(paramString);
    if (localList != null)
    {
      Iterator localIterator = localList.iterator();
      Bundle localBundle;
      do
      {
        bool1 = bool3;
        if (!localIterator.hasNext()) {
          break;
        }
        localBundle = (Bundle)localIterator.next();
      } while (!MediaBrowserCompatUtils.areSameOptions(paramBundle, localBundle));
      boolean bool1 = true;
      localList.remove(localBundle);
      bool2 = bool1;
      if (localList.size() == 0)
      {
        paramConnectionRecord.subscriptions.remove(paramString);
        bool2 = bool1;
      }
    }
    return bool2;
  }
  
  public void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  @Nullable
  public MediaSessionCompat.Token getSessionToken()
  {
    return this.mSession;
  }
  
  public void notifyChildrenChanged(@NonNull String paramString)
  {
    notifyChildrenChangedInternal(paramString, null);
  }
  
  public void notifyChildrenChanged(@NonNull String paramString, @NonNull Bundle paramBundle)
  {
    if (paramBundle == null) {
      throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
    }
    notifyChildrenChangedInternal(paramString, paramBundle);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return this.mImpl.onBind(paramIntent);
  }
  
  public void onCreate()
  {
    super.onCreate();
    if (Build.VERSION.SDK_INT >= 23) {
      this.mImpl = new MediaBrowserServiceImplApi23();
    }
    for (;;)
    {
      this.mImpl.onCreate();
      return;
      if (Build.VERSION.SDK_INT >= 21) {
        this.mImpl = new MediaBrowserServiceImplApi21();
      } else {
        this.mImpl = new MediaBrowserServiceImplBase();
      }
    }
  }
  
  @Nullable
  public abstract BrowserRoot onGetRoot(@NonNull String paramString, int paramInt, @Nullable Bundle paramBundle);
  
  public abstract void onLoadChildren(@NonNull String paramString, @NonNull Result<List<MediaBrowserCompat.MediaItem>> paramResult);
  
  public void onLoadChildren(@NonNull String paramString, @NonNull Result<List<MediaBrowserCompat.MediaItem>> paramResult, @NonNull Bundle paramBundle)
  {
    paramResult.setFlags(1);
    onLoadChildren(paramString, paramResult);
  }
  
  public void onLoadItem(String paramString, Result<MediaBrowserCompat.MediaItem> paramResult)
  {
    paramResult.sendResult(null);
  }
  
  public void setSessionToken(final MediaSessionCompat.Token paramToken)
  {
    if (paramToken == null) {
      throw new IllegalArgumentException("Session token may not be null.");
    }
    if (this.mSession != null) {
      throw new IllegalStateException("The session token has already been set.");
    }
    this.mSession = paramToken;
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        Iterator localIterator = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();
        while (localIterator.hasNext())
        {
          IBinder localIBinder = (IBinder)localIterator.next();
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localIBinder);
          try
          {
            localConnectionRecord.callbacks.onConnect(localConnectionRecord.root.getRootId(), paramToken, localConnectionRecord.root.getExtras());
          }
          catch (RemoteException localRemoteException)
          {
            Log.w("MediaBrowserServiceCompat", "Connection for " + localConnectionRecord.pkg + " is no longer valid.");
            MediaBrowserServiceCompat.this.mConnections.remove(localIBinder);
          }
        }
      }
    });
  }
  
  public static final class BrowserRoot
  {
    public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
    public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
    public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
    private final Bundle mExtras;
    private final String mRootId;
    
    public BrowserRoot(@NonNull String paramString, @Nullable Bundle paramBundle)
    {
      if (paramString == null) {
        throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
      }
      this.mRootId = paramString;
      this.mExtras = paramBundle;
    }
    
    public Bundle getExtras()
    {
      return this.mExtras;
    }
    
    public String getRootId()
    {
      return this.mRootId;
    }
  }
  
  private class ConnectionRecord
  {
    MediaBrowserServiceCompat.ServiceCallbacks callbacks;
    String pkg;
    MediaBrowserServiceCompat.BrowserRoot root;
    Bundle rootHints;
    HashMap<String, List<Bundle>> subscriptions = new HashMap();
    
    private ConnectionRecord() {}
  }
  
  static abstract interface MediaBrowserServiceImpl
  {
    public abstract IBinder onBind(Intent paramIntent);
    
    public abstract void onCreate();
  }
  
  class MediaBrowserServiceImplApi21
    implements MediaBrowserServiceCompat.MediaBrowserServiceImpl
  {
    private Object mServiceObj;
    
    MediaBrowserServiceImplApi21() {}
    
    public IBinder onBind(Intent paramIntent)
    {
      return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, paramIntent);
    }
    
    public void onCreate()
    {
      this.mServiceObj = MediaBrowserServiceCompatApi21.createService();
      MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj, new MediaBrowserServiceCompat.ServiceImplApi21(MediaBrowserServiceCompat.this));
    }
  }
  
  class MediaBrowserServiceImplApi23
    implements MediaBrowserServiceCompat.MediaBrowserServiceImpl
  {
    private Object mServiceObj;
    
    MediaBrowserServiceImplApi23() {}
    
    public IBinder onBind(Intent paramIntent)
    {
      return MediaBrowserServiceCompatApi23.onBind(this.mServiceObj, paramIntent);
    }
    
    public void onCreate()
    {
      this.mServiceObj = MediaBrowserServiceCompatApi23.createService();
      MediaBrowserServiceCompatApi23.onCreate(this.mServiceObj, new MediaBrowserServiceCompat.ServiceImplApi23(MediaBrowserServiceCompat.this, null));
    }
  }
  
  class MediaBrowserServiceImplBase
    implements MediaBrowserServiceCompat.MediaBrowserServiceImpl
  {
    private Messenger mMessenger;
    
    MediaBrowserServiceImplBase() {}
    
    public IBinder onBind(Intent paramIntent)
    {
      if ("android.media.browse.MediaBrowserService".equals(paramIntent.getAction())) {
        return this.mMessenger.getBinder();
      }
      return null;
    }
    
    public void onCreate()
    {
      this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
    }
  }
  
  public static class Result<T>
  {
    private Object mDebug;
    private boolean mDetachCalled;
    private int mFlags;
    private boolean mSendResultCalled;
    
    Result(Object paramObject)
    {
      this.mDebug = paramObject;
    }
    
    public void detach()
    {
      if (this.mDetachCalled) {
        throw new IllegalStateException("detach() called when detach() had already been called for: " + this.mDebug);
      }
      if (this.mSendResultCalled) {
        throw new IllegalStateException("detach() called when sendResult() had already been called for: " + this.mDebug);
      }
      this.mDetachCalled = true;
    }
    
    boolean isDone()
    {
      return (this.mDetachCalled) || (this.mSendResultCalled);
    }
    
    void onResultSent(T paramT, int paramInt) {}
    
    public void sendResult(T paramT)
    {
      if (this.mSendResultCalled) {
        throw new IllegalStateException("sendResult() called twice for: " + this.mDebug);
      }
      this.mSendResultCalled = true;
      onResultSent(paramT, this.mFlags);
    }
    
    void setFlags(int paramInt)
    {
      this.mFlags = paramInt;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  private static @interface ResultFlags {}
  
  private static abstract interface ServiceCallbacks
  {
    public abstract IBinder asBinder();
    
    public abstract void onConnect(String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
      throws RemoteException;
    
    public abstract void onConnectFailed()
      throws RemoteException;
    
    public abstract void onLoadChildren(String paramString, List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
      throws RemoteException;
  }
  
  private class ServiceCallbacksApi21
    implements MediaBrowserServiceCompat.ServiceCallbacks
  {
    final MediaBrowserServiceCompatApi21.ServiceCallbacks mCallbacks;
    Messenger mMessenger;
    
    ServiceCallbacksApi21(MediaBrowserServiceCompatApi21.ServiceCallbacks paramServiceCallbacks)
    {
      this.mCallbacks = paramServiceCallbacks;
    }
    
    public IBinder asBinder()
    {
      return this.mCallbacks.asBinder();
    }
    
    public void onConnect(String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
      throws RemoteException
    {
      Bundle localBundle = paramBundle;
      if (paramBundle == null) {
        localBundle = new Bundle();
      }
      this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
      BundleCompat.putBinder(localBundle, "extra_messenger", this.mMessenger.getBinder());
      localBundle.putInt("extra_service_version", 1);
      this.mCallbacks.onConnect(paramString, paramToken.getToken(), localBundle);
    }
    
    public void onConnectFailed()
      throws RemoteException
    {
      this.mCallbacks.onConnectFailed();
    }
    
    public void onLoadChildren(String paramString, List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
      throws RemoteException
    {
      paramBundle = null;
      if (paramList != null)
      {
        ArrayList localArrayList = new ArrayList();
        paramList = paramList.iterator();
        for (;;)
        {
          paramBundle = localArrayList;
          if (!paramList.hasNext()) {
            break;
          }
          paramBundle = (MediaBrowserCompat.MediaItem)paramList.next();
          Parcel localParcel = Parcel.obtain();
          paramBundle.writeToParcel(localParcel, 0);
          localArrayList.add(localParcel);
        }
      }
      this.mCallbacks.onLoadChildren(paramString, paramBundle);
    }
  }
  
  private class ServiceCallbacksCompat
    implements MediaBrowserServiceCompat.ServiceCallbacks
  {
    final Messenger mCallbacks;
    
    ServiceCallbacksCompat(Messenger paramMessenger)
    {
      this.mCallbacks = paramMessenger;
    }
    
    private void sendRequest(int paramInt, Bundle paramBundle)
      throws RemoteException
    {
      Message localMessage = Message.obtain();
      localMessage.what = paramInt;
      localMessage.arg1 = 1;
      localMessage.setData(paramBundle);
      this.mCallbacks.send(localMessage);
    }
    
    public IBinder asBinder()
    {
      return this.mCallbacks.getBinder();
    }
    
    public void onConnect(String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
      throws RemoteException
    {
      Bundle localBundle = paramBundle;
      if (paramBundle == null) {
        localBundle = new Bundle();
      }
      localBundle.putInt("extra_service_version", 1);
      paramBundle = new Bundle();
      paramBundle.putString("data_media_item_id", paramString);
      paramBundle.putParcelable("data_media_session_token", paramToken);
      paramBundle.putBundle("data_root_hints", localBundle);
      sendRequest(1, paramBundle);
    }
    
    public void onConnectFailed()
      throws RemoteException
    {
      sendRequest(2, null);
    }
    
    public void onLoadChildren(String paramString, List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      localBundle.putBundle("data_options", paramBundle);
      if (paramList != null) {
        if (!(paramList instanceof ArrayList)) {
          break label57;
        }
      }
      label57:
      for (paramString = (ArrayList)paramList;; paramString = new ArrayList(paramList))
      {
        localBundle.putParcelableArrayList("data_media_item_list", paramString);
        sendRequest(3, localBundle);
        return;
      }
    }
  }
  
  private final class ServiceHandler
    extends Handler
  {
    private final MediaBrowserServiceCompat.ServiceImpl mServiceImpl = new MediaBrowserServiceCompat.ServiceImpl(MediaBrowserServiceCompat.this, null);
    
    private ServiceHandler() {}
    
    public MediaBrowserServiceCompat.ServiceImpl getServiceImpl()
    {
      return this.mServiceImpl;
    }
    
    public void handleMessage(Message paramMessage)
    {
      Bundle localBundle = paramMessage.getData();
      switch (paramMessage.what)
      {
      default: 
        Log.w("MediaBrowserServiceCompat", "Unhandled message: " + paramMessage + "\n  Service version: " + 1 + "\n  Client version: " + paramMessage.arg1);
        return;
      case 1: 
        this.mServiceImpl.connect(localBundle.getString("data_package_name"), localBundle.getInt("data_calling_uid"), localBundle.getBundle("data_root_hints"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
        return;
      case 2: 
        this.mServiceImpl.disconnect(new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
        return;
      case 3: 
        this.mServiceImpl.addSubscription(localBundle.getString("data_media_item_id"), localBundle.getBundle("data_options"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
        return;
      case 4: 
        this.mServiceImpl.removeSubscription(localBundle.getString("data_media_item_id"), localBundle.getBundle("data_options"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
        return;
      case 5: 
        this.mServiceImpl.getMediaItem(localBundle.getString("data_media_item_id"), (ResultReceiver)localBundle.getParcelable("data_result_receiver"));
        return;
      }
      this.mServiceImpl.registerCallbacks(new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
    }
    
    public void postOrRun(Runnable paramRunnable)
    {
      if (Thread.currentThread() == getLooper().getThread())
      {
        paramRunnable.run();
        return;
      }
      post(paramRunnable);
    }
    
    public boolean sendMessageAtTime(Message paramMessage, long paramLong)
    {
      Bundle localBundle = paramMessage.getData();
      localBundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
      localBundle.putInt("data_calling_uid", Binder.getCallingUid());
      return super.sendMessageAtTime(paramMessage, paramLong);
    }
  }
  
  private class ServiceImpl
  {
    private ServiceImpl() {}
    
    public void addSubscription(final String paramString, final Bundle paramBundle, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          Object localObject = paramServiceCallbacks.asBinder();
          localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localObject);
          if (localObject == null)
          {
            Log.w("MediaBrowserServiceCompat", "addSubscription for callback that isn't registered id=" + paramString);
            return;
          }
          MediaBrowserServiceCompat.this.addSubscription(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramBundle);
        }
      });
    }
    
    public void connect(final String paramString, final int paramInt, final Bundle paramBundle, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if (!MediaBrowserServiceCompat.this.isValidPackage(paramString, paramInt)) {
        throw new IllegalArgumentException("Package/uid mismatch: uid=" + paramInt + " package=" + paramString);
      }
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          MediaBrowserServiceCompat.this.mConnections.remove(localIBinder);
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = new MediaBrowserServiceCompat.ConnectionRecord(MediaBrowserServiceCompat.this, null);
          localConnectionRecord.pkg = paramString;
          localConnectionRecord.rootHints = paramBundle;
          localConnectionRecord.callbacks = paramServiceCallbacks;
          localConnectionRecord.root = MediaBrowserServiceCompat.this.onGetRoot(paramString, paramInt, paramBundle);
          if (localConnectionRecord.root == null) {
            Log.i("MediaBrowserServiceCompat", "No root for client " + paramString + " from service " + getClass().getName());
          }
          for (;;)
          {
            try
            {
              paramServiceCallbacks.onConnectFailed();
              return;
            }
            catch (RemoteException localRemoteException1)
            {
              Log.w("MediaBrowserServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + paramString);
              return;
            }
            try
            {
              MediaBrowserServiceCompat.this.mConnections.put(localRemoteException1, localConnectionRecord);
              if (MediaBrowserServiceCompat.this.mSession != null)
              {
                paramServiceCallbacks.onConnect(localConnectionRecord.root.getRootId(), MediaBrowserServiceCompat.this.mSession, localConnectionRecord.root.getExtras());
                return;
              }
            }
            catch (RemoteException localRemoteException2)
            {
              Log.w("MediaBrowserServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + paramString);
              MediaBrowserServiceCompat.this.mConnections.remove(localRemoteException1);
            }
          }
        }
      });
    }
    
    public void disconnect(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          if ((MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.remove(localIBinder) != null) {}
        }
      });
    }
    
    public void getMediaItem(final String paramString, final ResultReceiver paramResultReceiver)
    {
      if ((TextUtils.isEmpty(paramString)) || (paramResultReceiver == null)) {
        return;
      }
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          MediaBrowserServiceCompat.this.performLoadItem(paramString, paramResultReceiver);
        }
      });
    }
    
    public void registerCallbacks(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          MediaBrowserServiceCompat.this.mConnections.remove(localIBinder);
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = new MediaBrowserServiceCompat.ConnectionRecord(MediaBrowserServiceCompat.this, null);
          localConnectionRecord.callbacks = paramServiceCallbacks;
          MediaBrowserServiceCompat.this.mConnections.put(localIBinder, localConnectionRecord);
        }
      });
    }
    
    public void removeSubscription(final String paramString, final Bundle paramBundle, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          Object localObject = paramServiceCallbacks.asBinder();
          localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localObject);
          if (localObject == null) {
            Log.w("MediaBrowserServiceCompat", "removeSubscription for callback that isn't registered id=" + paramString);
          }
          while (MediaBrowserServiceCompat.this.removeSubscription(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramBundle)) {
            return;
          }
          Log.w("MediaBrowserServiceCompat", "removeSubscription called for " + paramString + " which is not subscribed");
        }
      });
    }
  }
  
  private class ServiceImplApi21
    implements MediaBrowserServiceCompatApi21.ServiceImplApi21
  {
    final MediaBrowserServiceCompat.ServiceImpl mServiceImpl = MediaBrowserServiceCompat.this.mHandler.getServiceImpl();
    
    ServiceImplApi21() {}
    
    public void addSubscription(String paramString, MediaBrowserServiceCompatApi21.ServiceCallbacks paramServiceCallbacks)
    {
      this.mServiceImpl.addSubscription(paramString, null, new MediaBrowserServiceCompat.ServiceCallbacksApi21(MediaBrowserServiceCompat.this, paramServiceCallbacks));
    }
    
    public void connect(String paramString, Bundle paramBundle, MediaBrowserServiceCompatApi21.ServiceCallbacks paramServiceCallbacks)
    {
      this.mServiceImpl.connect(paramString, Binder.getCallingUid(), paramBundle, new MediaBrowserServiceCompat.ServiceCallbacksApi21(MediaBrowserServiceCompat.this, paramServiceCallbacks));
    }
    
    public void disconnect(MediaBrowserServiceCompatApi21.ServiceCallbacks paramServiceCallbacks)
    {
      this.mServiceImpl.disconnect(new MediaBrowserServiceCompat.ServiceCallbacksApi21(MediaBrowserServiceCompat.this, paramServiceCallbacks));
    }
    
    public void removeSubscription(String paramString, MediaBrowserServiceCompatApi21.ServiceCallbacks paramServiceCallbacks)
    {
      this.mServiceImpl.removeSubscription(paramString, null, new MediaBrowserServiceCompat.ServiceCallbacksApi21(MediaBrowserServiceCompat.this, paramServiceCallbacks));
    }
  }
  
  private class ServiceImplApi23
    extends MediaBrowserServiceCompat.ServiceImplApi21
    implements MediaBrowserServiceCompatApi23.ServiceImplApi23
  {
    private ServiceImplApi23()
    {
      super();
    }
    
    public void getMediaItem(String paramString, final MediaBrowserServiceCompatApi23.ItemCallback paramItemCallback)
    {
      paramItemCallback = new ResultReceiver(MediaBrowserServiceCompat.this.mHandler)
      {
        protected void onReceiveResult(int paramAnonymousInt, Bundle paramAnonymousBundle)
        {
          MediaBrowserCompat.MediaItem localMediaItem = (MediaBrowserCompat.MediaItem)paramAnonymousBundle.getParcelable("media_item");
          Parcel localParcel = null;
          if (localMediaItem != null)
          {
            localParcel = Parcel.obtain();
            localMediaItem.writeToParcel(localParcel, 0);
          }
          paramItemCallback.onItemLoaded(paramAnonymousInt, paramAnonymousBundle, localParcel);
        }
      };
      this.mServiceImpl.getMediaItem(paramString, paramItemCallback);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\android\support\v4\media\MediaBrowserServiceCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */