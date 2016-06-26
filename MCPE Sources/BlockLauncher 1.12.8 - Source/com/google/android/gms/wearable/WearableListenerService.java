package com.google.android.gms.wearable;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.internal.AmsEntityUpdateParcelable;
import com.google.android.gms.wearable.internal.AncsNotificationParcelable;
import com.google.android.gms.wearable.internal.CapabilityInfoParcelable;
import com.google.android.gms.wearable.internal.ChannelEventParcelable;
import com.google.android.gms.wearable.internal.MessageEventParcelable;
import com.google.android.gms.wearable.internal.NodeParcelable;
import com.google.android.gms.wearable.internal.zzaw.zza;
import java.util.List;

public abstract class WearableListenerService
  extends Service
  implements CapabilityApi.CapabilityListener, ChannelApi.ChannelListener, DataApi.DataListener, MessageApi.MessageListener, NodeApi.NodeListener, NodeApi.zza
{
  public static final String BIND_LISTENER_INTENT_ACTION = "com.google.android.gms.wearable.BIND_LISTENER";
  private boolean zzQl;
  private String zzTJ;
  private IBinder zzakD;
  private Handler zzbro;
  private final Object zzbrp = new Object();
  
  public final IBinder onBind(Intent paramIntent)
  {
    if ("com.google.android.gms.wearable.BIND_LISTENER".equals(paramIntent.getAction())) {
      return this.zzakD;
    }
    return null;
  }
  
  public void onCapabilityChanged(CapabilityInfo paramCapabilityInfo) {}
  
  public void onChannelClosed(Channel paramChannel, int paramInt1, int paramInt2) {}
  
  public void onChannelOpened(Channel paramChannel) {}
  
  public void onConnectedNodes(List<Node> paramList) {}
  
  public void onCreate()
  {
    super.onCreate();
    if (Log.isLoggable("WearableLS", 3)) {
      Log.d("WearableLS", "onCreate: " + new ComponentName(getPackageName(), getClass().getName()).flattenToShortString());
    }
    this.zzTJ = getPackageName();
    HandlerThread localHandlerThread = new HandlerThread("WearableListenerService");
    localHandlerThread.start();
    this.zzbro = new Handler(localHandlerThread.getLooper());
    this.zzakD = new zza(null);
  }
  
  public void onDataChanged(DataEventBuffer paramDataEventBuffer) {}
  
  public void onDestroy()
  {
    if (Log.isLoggable("WearableLS", 3)) {
      Log.d("WearableLS", "onDestroy: " + new ComponentName(getPackageName(), getClass().getName()).flattenToShortString());
    }
    synchronized (this.zzbrp)
    {
      this.zzQl = true;
      if (this.zzbro == null) {
        throw new IllegalStateException("onDestroy: mServiceHandler not set, did you override onCreate() but forget to call super.onCreate()?");
      }
    }
    this.zzbro.getLooper().quit();
    super.onDestroy();
  }
  
  public void onInputClosed(Channel paramChannel, int paramInt1, int paramInt2) {}
  
  public void onMessageReceived(MessageEvent paramMessageEvent) {}
  
  public void onOutputClosed(Channel paramChannel, int paramInt1, int paramInt2) {}
  
  public void onPeerConnected(Node paramNode) {}
  
  public void onPeerDisconnected(Node paramNode) {}
  
  private final class zza
    extends zzaw.zza
  {
    private volatile int zzakz = -1;
    
    private zza() {}
    
    private void zzIx()
      throws SecurityException
    {
      int i = Binder.getCallingUid();
      if (i == this.zzakz) {
        return;
      }
      if (GooglePlayServicesUtil.zzf(WearableListenerService.this, i))
      {
        this.zzakz = i;
        return;
      }
      throw new SecurityException("Caller is not GooglePlayServices");
    }
    
    private boolean zza(Runnable paramRunnable, String paramString, Object paramObject)
    {
      if (!(WearableListenerService.this instanceof zzj)) {
        return false;
      }
      return zzb(paramRunnable, paramString, paramObject);
    }
    
    private boolean zzb(Runnable paramRunnable, String arg2, Object paramObject)
    {
      if (Log.isLoggable("WearableLS", 3)) {
        Log.d("WearableLS", String.format("%s: %s %s", new Object[] { ???, WearableListenerService.zza(WearableListenerService.this), paramObject }));
      }
      zzIx();
      synchronized (WearableListenerService.zzb(WearableListenerService.this))
      {
        if (WearableListenerService.zzc(WearableListenerService.this)) {
          return false;
        }
        WearableListenerService.zzd(WearableListenerService.this).post(paramRunnable);
        return true;
      }
    }
    
    public void onConnectedNodes(final List<NodeParcelable> paramList)
    {
      zzb(new Runnable()
      {
        public void run()
        {
          WearableListenerService.this.onConnectedNodes(paramList);
        }
      }, "onConnectedNodes", paramList);
    }
    
    public void zza(final AmsEntityUpdateParcelable paramAmsEntityUpdateParcelable)
    {
      zza(new Runnable()
      {
        public void run()
        {
          ((zzj)WearableListenerService.this).zza(paramAmsEntityUpdateParcelable);
        }
      }, "onEntityUpdate", paramAmsEntityUpdateParcelable);
    }
    
    public void zza(final AncsNotificationParcelable paramAncsNotificationParcelable)
    {
      zza(new Runnable()
      {
        public void run()
        {
          ((zzj)WearableListenerService.this).zza(paramAncsNotificationParcelable);
        }
      }, "onNotificationReceived", paramAncsNotificationParcelable);
    }
    
    public void zza(final CapabilityInfoParcelable paramCapabilityInfoParcelable)
    {
      zzb(new Runnable()
      {
        public void run()
        {
          WearableListenerService.this.onCapabilityChanged(paramCapabilityInfoParcelable);
        }
      }, "onConnectedCapabilityChanged", paramCapabilityInfoParcelable);
    }
    
    public void zza(final ChannelEventParcelable paramChannelEventParcelable)
    {
      zzb(new Runnable()
      {
        public void run()
        {
          paramChannelEventParcelable.zza(WearableListenerService.this);
        }
      }, "onChannelEvent", paramChannelEventParcelable);
    }
    
    public void zza(final MessageEventParcelable paramMessageEventParcelable)
    {
      zzb(new Runnable()
      {
        public void run()
        {
          WearableListenerService.this.onMessageReceived(paramMessageEventParcelable);
        }
      }, "onMessageReceived", paramMessageEventParcelable);
    }
    
    public void zza(final NodeParcelable paramNodeParcelable)
    {
      zzb(new Runnable()
      {
        public void run()
        {
          WearableListenerService.this.onPeerConnected(paramNodeParcelable);
        }
      }, "onPeerConnected", paramNodeParcelable);
    }
    
    public void zzag(final DataHolder paramDataHolder)
    {
      Runnable local1 = new Runnable()
      {
        public void run()
        {
          DataEventBuffer localDataEventBuffer = new DataEventBuffer(paramDataHolder);
          try
          {
            WearableListenerService.this.onDataChanged(localDataEventBuffer);
            return;
          }
          finally
          {
            localDataEventBuffer.release();
          }
        }
      };
      try
      {
        boolean bool = zzb(local1, "onDataItemChanged", paramDataHolder);
        if (!bool) {}
        return;
      }
      finally
      {
        paramDataHolder.close();
      }
    }
    
    public void zzb(final NodeParcelable paramNodeParcelable)
    {
      zzb(new Runnable()
      {
        public void run()
        {
          WearableListenerService.this.onPeerDisconnected(paramNodeParcelable);
        }
      }, "onPeerDisconnected", paramNodeParcelable);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\WearableListenerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */