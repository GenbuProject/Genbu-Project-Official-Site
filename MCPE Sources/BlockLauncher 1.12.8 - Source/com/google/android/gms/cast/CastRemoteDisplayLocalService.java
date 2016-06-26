package com.google.android.gms.cast;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v7.media.MediaRouteSelector.Builder;
import android.support.v7.media.MediaRouter;
import android.support.v7.media.MediaRouter.Callback;
import android.support.v7.media.MediaRouter.RouteInfo;
import android.text.TextUtils;
import android.view.Display;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.id;
import com.google.android.gms.R.string;
import com.google.android.gms.cast.internal.zzl;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzx;
import java.util.concurrent.atomic.AtomicBoolean;

@TargetApi(19)
public abstract class CastRemoteDisplayLocalService
  extends Service
{
  private static final zzl zzaaf = new zzl("CastRemoteDisplayLocalService");
  private static final int zzaag = R.id.cast_notification_id;
  private static final Object zzaah = new Object();
  private static AtomicBoolean zzaai = new AtomicBoolean(false);
  private static CastRemoteDisplayLocalService zzaax;
  private Handler mHandler;
  private Notification mNotification;
  private String zzZC;
  private GoogleApiClient zzaaj;
  private CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzaak;
  private Callbacks zzaal;
  private zzb zzaam;
  private NotificationSettings zzaan;
  private boolean zzaao;
  private PendingIntent zzaap;
  private CastDevice zzaaq;
  private Display zzaar;
  private Context zzaas;
  private ServiceConnection zzaat;
  private MediaRouter zzaau;
  private boolean zzaav = false;
  private final MediaRouter.Callback zzaaw = new MediaRouter.Callback()
  {
    public void onRouteUnselected(MediaRouter paramAnonymousMediaRouter, MediaRouter.RouteInfo paramAnonymousRouteInfo)
    {
      CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, "onRouteUnselected");
      if (CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this) == null)
      {
        CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, "onRouteUnselected, no device was selected");
        return;
      }
      if (!CastDevice.getFromBundle(paramAnonymousRouteInfo.getExtras()).getDeviceId().equals(CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this).getDeviceId()))
      {
        CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, "onRouteUnselected, device does not match");
        return;
      }
      CastRemoteDisplayLocalService.stopService();
    }
  };
  private final IBinder zzaay = new zza(null);
  
  public static CastRemoteDisplayLocalService getInstance()
  {
    synchronized (zzaah)
    {
      CastRemoteDisplayLocalService localCastRemoteDisplayLocalService = zzaax;
      return localCastRemoteDisplayLocalService;
    }
  }
  
  protected static void setDebugEnabled()
  {
    zzaaf.zzY(true);
  }
  
  public static void startService(final Context paramContext, Class<? extends CastRemoteDisplayLocalService> paramClass, String paramString, final CastDevice paramCastDevice, final NotificationSettings paramNotificationSettings, final Callbacks paramCallbacks)
  {
    zzaaf.zzb("Starting Service", new Object[0]);
    synchronized (zzaah)
    {
      if (zzaax != null)
      {
        zzaaf.zzf("An existing service had not been stopped before starting one", new Object[0]);
        zzS(true);
      }
      zzb(paramContext, paramClass);
      zzx.zzb(paramContext, "activityContext is required.");
      zzx.zzb(paramClass, "serviceClass is required.");
      zzx.zzb(paramString, "applicationId is required.");
      zzx.zzb(paramCastDevice, "device is required.");
      zzx.zzb(paramNotificationSettings, "notificationSettings is required.");
      zzx.zzb(paramCallbacks, "callbacks is required.");
      if ((NotificationSettings.zzb(paramNotificationSettings) == null) && (NotificationSettings.zze(paramNotificationSettings) == null)) {
        throw new IllegalArgumentException("notificationSettings: Either the notification or the notificationPendingIntent must be provided");
      }
    }
    if (zzaai.getAndSet(true))
    {
      zzaaf.zzc("Service is already being started, startService has been called twice", new Object[0]);
      return;
    }
    paramClass = new Intent(paramContext, paramClass);
    paramContext.startService(paramClass);
    paramContext.bindService(paramClass, new ServiceConnection()
    {
      public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
      {
        paramAnonymousComponentName = ((CastRemoteDisplayLocalService.zza)paramAnonymousIBinder).zznM();
        if ((paramAnonymousComponentName == null) || (!CastRemoteDisplayLocalService.zza(paramAnonymousComponentName, this.zzZI, paramCastDevice, paramNotificationSettings, paramContext, this, paramCallbacks)))
        {
          CastRemoteDisplayLocalService.zznI().zzc("Connected but unable to get the service instance", new Object[0]);
          paramCallbacks.onRemoteDisplaySessionError(new Status(2200));
          CastRemoteDisplayLocalService.zznJ().set(false);
        }
        try
        {
          paramContext.unbindService(this);
          return;
        }
        catch (IllegalArgumentException paramAnonymousComponentName)
        {
          CastRemoteDisplayLocalService.zznI().zzb("No need to unbind service, already unbound", new Object[0]);
        }
      }
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        CastRemoteDisplayLocalService.zznI().zzb("onServiceDisconnected", new Object[0]);
        paramCallbacks.onRemoteDisplaySessionError(new Status(2201, "Service Disconnected"));
        CastRemoteDisplayLocalService.zznJ().set(false);
        try
        {
          paramContext.unbindService(this);
          return;
        }
        catch (IllegalArgumentException paramAnonymousComponentName)
        {
          CastRemoteDisplayLocalService.zznI().zzb("No need to unbind service, already unbound", new Object[0]);
        }
      }
    }, 64);
  }
  
  public static void stopService()
  {
    zzS(false);
  }
  
  private void zzQ(final boolean paramBoolean)
  {
    if (this.mHandler != null)
    {
      if (Looper.myLooper() != Looper.getMainLooper()) {
        this.mHandler.post(new Runnable()
        {
          public void run()
          {
            CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, paramBoolean);
          }
        });
      }
    }
    else {
      return;
    }
    zzR(paramBoolean);
  }
  
  private void zzR(boolean paramBoolean)
  {
    zzbe("Stopping Service");
    zzx.zzcD("stopServiceInstanceInternal must be called on the main thread");
    if ((!paramBoolean) && (this.zzaau != null))
    {
      zzbe("Setting default route");
      this.zzaau.selectRoute(this.zzaau.getDefaultRoute());
    }
    if (this.zzaam != null)
    {
      zzbe("Unregistering notification receiver");
      unregisterReceiver(this.zzaam);
    }
    zznF();
    zznG();
    zznB();
    if (this.zzaaj != null)
    {
      this.zzaaj.disconnect();
      this.zzaaj = null;
    }
    if ((this.zzaas != null) && (this.zzaat != null)) {}
    try
    {
      this.zzaas.unbindService(this.zzaat);
      this.zzaat = null;
      this.zzaas = null;
      this.zzZC = null;
      this.zzaaj = null;
      this.mNotification = null;
      this.zzaar = null;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        zzbe("No need to unbind service, already unbound");
      }
    }
  }
  
  private static void zzS(boolean paramBoolean)
  {
    zzaaf.zzb("Stopping Service", new Object[0]);
    zzaai.set(false);
    synchronized (zzaah)
    {
      if (zzaax == null)
      {
        zzaaf.zzc("Service is already being stopped", new Object[0]);
        return;
      }
      CastRemoteDisplayLocalService localCastRemoteDisplayLocalService = zzaax;
      zzaax = null;
      localCastRemoteDisplayLocalService.zzQ(paramBoolean);
      return;
    }
  }
  
  private Notification zzT(boolean paramBoolean)
  {
    zzbe("createDefaultNotification");
    int k = getApplicationInfo().labelRes;
    String str3 = NotificationSettings.zzc(this.zzaan);
    String str2 = NotificationSettings.zzd(this.zzaan);
    int j;
    int i;
    String str1;
    if (paramBoolean)
    {
      j = R.string.cast_notification_connected_message;
      i = R.drawable.cast_ic_notification_on;
      str1 = str3;
      if (TextUtils.isEmpty(str3)) {
        str1 = getString(k);
      }
      if (!TextUtils.isEmpty(str2)) {
        break label163;
      }
      str2 = getString(j, new Object[] { this.zzaaq.getFriendlyName() });
    }
    label163:
    for (;;)
    {
      return new NotificationCompat.Builder(this).setContentTitle(str1).setContentText(str2).setContentIntent(NotificationSettings.zze(this.zzaan)).setSmallIcon(i).setOngoing(true).addAction(17301560, getString(R.string.cast_notification_disconnect), zznH()).build();
      j = R.string.cast_notification_connecting_message;
      i = R.drawable.cast_ic_notification_connecting;
      break;
    }
  }
  
  private GoogleApiClient zza(CastDevice paramCastDevice)
  {
    paramCastDevice = new CastRemoteDisplay.CastRemoteDisplayOptions.Builder(paramCastDevice, this.zzaak);
    new GoogleApiClient.Builder(this, new GoogleApiClient.ConnectionCallbacks()new GoogleApiClient.OnConnectionFailedListener
    {
      public void onConnected(Bundle paramAnonymousBundle)
      {
        CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, "onConnected");
        CastRemoteDisplayLocalService.zzf(CastRemoteDisplayLocalService.this);
      }
      
      public void onConnectionSuspended(int paramAnonymousInt)
      {
        CastRemoteDisplayLocalService.zznI().zzf(String.format("[Instance: %s] ConnectionSuspended %d", new Object[] { this, Integer.valueOf(paramAnonymousInt) }), new Object[0]);
      }
    }, new GoogleApiClient.OnConnectionFailedListener()
    {
      public void onConnectionFailed(ConnectionResult paramAnonymousConnectionResult)
      {
        CastRemoteDisplayLocalService.zzb(CastRemoteDisplayLocalService.this, "Connection failed: " + paramAnonymousConnectionResult);
        CastRemoteDisplayLocalService.zzc(CastRemoteDisplayLocalService.this);
      }
    }).addApi(CastRemoteDisplay.API, paramCastDevice.build()).build();
  }
  
  private void zza(Display paramDisplay)
  {
    this.zzaar = paramDisplay;
    if (this.zzaao)
    {
      this.mNotification = zzT(true);
      startForeground(zzaag, this.mNotification);
    }
    if (this.zzaal != null)
    {
      this.zzaal.onRemoteDisplaySessionStarted(this);
      this.zzaal = null;
    }
    onCreatePresentation(this.zzaar);
  }
  
  private void zza(NotificationSettings paramNotificationSettings)
  {
    zzx.zzcD("updateNotificationSettingsInternal must be called on the main thread");
    if (this.zzaan == null) {
      throw new IllegalStateException("No current notification settings to update");
    }
    if (this.zzaao)
    {
      if (NotificationSettings.zzb(paramNotificationSettings) != null) {
        throw new IllegalStateException("Current mode is default notification, notification attribute must not be provided");
      }
      if (NotificationSettings.zze(paramNotificationSettings) != null) {
        NotificationSettings.zza(this.zzaan, NotificationSettings.zze(paramNotificationSettings));
      }
      if (!TextUtils.isEmpty(NotificationSettings.zzc(paramNotificationSettings))) {
        NotificationSettings.zza(this.zzaan, NotificationSettings.zzc(paramNotificationSettings));
      }
      if (!TextUtils.isEmpty(NotificationSettings.zzd(paramNotificationSettings))) {
        NotificationSettings.zzb(this.zzaan, NotificationSettings.zzd(paramNotificationSettings));
      }
      this.mNotification = zzT(true);
    }
    for (;;)
    {
      startForeground(zzaag, this.mNotification);
      return;
      zzx.zzb(NotificationSettings.zzb(paramNotificationSettings), "notification is required.");
      this.mNotification = NotificationSettings.zzb(paramNotificationSettings);
      NotificationSettings.zza(this.zzaan, this.mNotification);
    }
  }
  
  private boolean zza(String paramString, CastDevice paramCastDevice, NotificationSettings paramNotificationSettings, Context paramContext, ServiceConnection paramServiceConnection, Callbacks paramCallbacks)
  {
    zzbe("startRemoteDisplaySession");
    zzx.zzcD("Starting the Cast Remote Display must be done on the main thread");
    for (;;)
    {
      synchronized (zzaah)
      {
        if (zzaax != null)
        {
          zzaaf.zzf("An existing service had not been stopped before starting one", new Object[0]);
          return false;
        }
        zzaax = this;
        this.zzaal = paramCallbacks;
        this.zzZC = paramString;
        this.zzaaq = paramCastDevice;
        this.zzaas = paramContext;
        this.zzaat = paramServiceConnection;
        this.zzaau = MediaRouter.getInstance(getApplicationContext());
        paramString = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(this.zzZC)).build();
        zzbe("addMediaRouterCallback");
        this.zzaau.addCallback(paramString, this.zzaaw, 4);
        this.zzaak = new CastRemoteDisplay.CastRemoteDisplaySessionCallbacks()
        {
          public void onRemoteDisplayEnded(Status paramAnonymousStatus)
          {
            CastRemoteDisplayLocalService.zznI().zzb(String.format("Cast screen has ended: %d", new Object[] { Integer.valueOf(paramAnonymousStatus.getStatusCode()) }), new Object[0]);
            CastRemoteDisplayLocalService.zzU(false);
          }
        };
        this.mNotification = NotificationSettings.zzb(paramNotificationSettings);
        this.zzaam = new zzb(null);
        registerReceiver(this.zzaam, new IntentFilter("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT"));
        this.zzaan = new NotificationSettings(paramNotificationSettings, null);
        if (NotificationSettings.zzb(this.zzaan) == null)
        {
          this.zzaao = true;
          this.mNotification = zzT(false);
          startForeground(zzaag, this.mNotification);
          this.zzaaj = zza(paramCastDevice);
          this.zzaaj.connect();
          if (this.zzaal != null) {
            this.zzaal.onServiceCreated(this);
          }
          return true;
        }
      }
      this.zzaao = false;
      this.mNotification = NotificationSettings.zzb(this.zzaan);
    }
  }
  
  private static void zzb(Context paramContext, Class paramClass)
  {
    try
    {
      paramClass = new ComponentName(paramContext, paramClass);
      paramContext = paramContext.getPackageManager().getServiceInfo(paramClass, 128);
      if ((paramContext != null) && (paramContext.exported)) {
        throw new IllegalStateException("The service must not be exported, verify the manifest configuration");
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      throw new IllegalStateException("Service not found, did you forget to configure it in the manifest?");
    }
  }
  
  private void zzbe(String paramString)
  {
    zzaaf.zzb("[Instance: %s] %s", new Object[] { this, paramString });
  }
  
  private void zzbh(String paramString)
  {
    zzaaf.zzc("[Instance: %s] %s", new Object[] { this, paramString });
  }
  
  private void zznB()
  {
    if (this.zzaau != null)
    {
      zzx.zzcD("CastRemoteDisplayLocalService calls must be done on the main thread");
      zzbe("removeMediaRouterCallback");
      this.zzaau.removeCallback(this.zzaaw);
    }
  }
  
  private void zznC()
  {
    zzbe("startRemoteDisplay");
    if ((this.zzaaj == null) || (!this.zzaaj.isConnected()))
    {
      zzaaf.zzc("Unable to start the remote display as the API client is not ready", new Object[0]);
      return;
    }
    CastRemoteDisplay.CastRemoteDisplayApi.startRemoteDisplay(this.zzaaj, this.zzZC).setResultCallback(new ResultCallback()
    {
      public void zza(CastRemoteDisplay.CastRemoteDisplaySessionResult paramAnonymousCastRemoteDisplaySessionResult)
      {
        if (!paramAnonymousCastRemoteDisplaySessionResult.getStatus().isSuccess())
        {
          CastRemoteDisplayLocalService.zznI().zzc("Connection was not successful", new Object[0]);
          CastRemoteDisplayLocalService.zzc(CastRemoteDisplayLocalService.this);
          return;
        }
        CastRemoteDisplayLocalService.zznI().zzb("startRemoteDisplay successful", new Object[0]);
        synchronized (CastRemoteDisplayLocalService.zznK())
        {
          if (CastRemoteDisplayLocalService.zznL() == null)
          {
            CastRemoteDisplayLocalService.zznI().zzb("Remote Display started but session already cancelled", new Object[0]);
            CastRemoteDisplayLocalService.zzc(CastRemoteDisplayLocalService.this);
            return;
          }
        }
        paramAnonymousCastRemoteDisplaySessionResult = paramAnonymousCastRemoteDisplaySessionResult.getPresentationDisplay();
        if (paramAnonymousCastRemoteDisplaySessionResult != null) {
          CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, paramAnonymousCastRemoteDisplaySessionResult);
        }
        for (;;)
        {
          CastRemoteDisplayLocalService.zznJ().set(false);
          if ((CastRemoteDisplayLocalService.zzd(CastRemoteDisplayLocalService.this) == null) || (CastRemoteDisplayLocalService.zze(CastRemoteDisplayLocalService.this) == null)) {
            break;
          }
          try
          {
            CastRemoteDisplayLocalService.zzd(CastRemoteDisplayLocalService.this).unbindService(CastRemoteDisplayLocalService.zze(CastRemoteDisplayLocalService.this));
            CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, null);
            CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, null);
            return;
            CastRemoteDisplayLocalService.zznI().zzc("Cast Remote Display session created without display", new Object[0]);
          }
          catch (IllegalArgumentException paramAnonymousCastRemoteDisplaySessionResult)
          {
            for (;;)
            {
              CastRemoteDisplayLocalService.zznI().zzb("No need to unbind service, already unbound", new Object[0]);
            }
          }
        }
      }
    });
  }
  
  private void zznD()
  {
    zzbe("stopRemoteDisplay");
    if ((this.zzaaj == null) || (!this.zzaaj.isConnected()))
    {
      zzaaf.zzc("Unable to stop the remote display as the API client is not ready", new Object[0]);
      return;
    }
    CastRemoteDisplay.CastRemoteDisplayApi.stopRemoteDisplay(this.zzaaj).setResultCallback(new ResultCallback()
    {
      public void zza(CastRemoteDisplay.CastRemoteDisplaySessionResult paramAnonymousCastRemoteDisplaySessionResult)
      {
        if (!paramAnonymousCastRemoteDisplaySessionResult.getStatus().isSuccess()) {
          CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, "Unable to stop the remote display, result unsuccessful");
        }
        for (;;)
        {
          CastRemoteDisplayLocalService.zzb(CastRemoteDisplayLocalService.this, null);
          return;
          CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, "remote display stopped");
        }
      }
    });
  }
  
  private void zznE()
  {
    if (this.zzaal != null)
    {
      this.zzaal.onRemoteDisplaySessionError(new Status(2200));
      this.zzaal = null;
    }
    stopService();
  }
  
  private void zznF()
  {
    zzbe("stopRemoteDisplaySession");
    zznD();
    onDismissPresentation();
  }
  
  private void zznG()
  {
    zzbe("Stopping the remote display Service");
    stopForeground(true);
    stopSelf();
  }
  
  private PendingIntent zznH()
  {
    if (this.zzaap == null) {
      this.zzaap = PendingIntent.getBroadcast(this, 0, new Intent("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT"), 268435456);
    }
    return this.zzaap;
  }
  
  protected Display getDisplay()
  {
    return this.zzaar;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    zzbe("onBind");
    return this.zzaay;
  }
  
  public void onCreate()
  {
    zzbe("onCreate");
    super.onCreate();
    this.mHandler = new Handler(getMainLooper());
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, "onCreate after delay. The local service been started: " + CastRemoteDisplayLocalService.zzb(CastRemoteDisplayLocalService.this));
        if (!CastRemoteDisplayLocalService.zzb(CastRemoteDisplayLocalService.this))
        {
          CastRemoteDisplayLocalService.zzb(CastRemoteDisplayLocalService.this, "The local service has not been been started, stopping it");
          CastRemoteDisplayLocalService.this.stopSelf();
        }
      }
    }, 100L);
  }
  
  public abstract void onCreatePresentation(Display paramDisplay);
  
  public abstract void onDismissPresentation();
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    zzbe("onStartCommand");
    this.zzaav = true;
    return 2;
  }
  
  public void updateNotificationSettings(final NotificationSettings paramNotificationSettings)
  {
    zzx.zzb(paramNotificationSettings, "notificationSettings is required.");
    zzx.zzb(this.mHandler, "Service is not ready yet.");
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        CastRemoteDisplayLocalService.zza(CastRemoteDisplayLocalService.this, paramNotificationSettings);
      }
    });
  }
  
  public static abstract interface Callbacks
  {
    public abstract void onRemoteDisplaySessionError(Status paramStatus);
    
    public abstract void onRemoteDisplaySessionStarted(CastRemoteDisplayLocalService paramCastRemoteDisplayLocalService);
    
    public abstract void onServiceCreated(CastRemoteDisplayLocalService paramCastRemoteDisplayLocalService);
  }
  
  public static final class NotificationSettings
  {
    private Notification mNotification;
    private PendingIntent zzaaF;
    private String zzaaG;
    private String zzaaH;
    
    private NotificationSettings() {}
    
    private NotificationSettings(NotificationSettings paramNotificationSettings)
    {
      this.mNotification = paramNotificationSettings.mNotification;
      this.zzaaF = paramNotificationSettings.zzaaF;
      this.zzaaG = paramNotificationSettings.zzaaG;
      this.zzaaH = paramNotificationSettings.zzaaH;
    }
    
    public static final class Builder
    {
      private CastRemoteDisplayLocalService.NotificationSettings zzaaI = new CastRemoteDisplayLocalService.NotificationSettings(null);
      
      public CastRemoteDisplayLocalService.NotificationSettings build()
      {
        if (CastRemoteDisplayLocalService.NotificationSettings.zzb(this.zzaaI) != null)
        {
          if (!TextUtils.isEmpty(CastRemoteDisplayLocalService.NotificationSettings.zzc(this.zzaaI))) {
            throw new IllegalArgumentException("notificationTitle requires using the default notification");
          }
          if (!TextUtils.isEmpty(CastRemoteDisplayLocalService.NotificationSettings.zzd(this.zzaaI))) {
            throw new IllegalArgumentException("notificationText requires using the default notification");
          }
          if (CastRemoteDisplayLocalService.NotificationSettings.zze(this.zzaaI) != null) {
            throw new IllegalArgumentException("notificationPendingIntent requires using the default notification");
          }
        }
        else if ((TextUtils.isEmpty(CastRemoteDisplayLocalService.NotificationSettings.zzc(this.zzaaI))) && (TextUtils.isEmpty(CastRemoteDisplayLocalService.NotificationSettings.zzd(this.zzaaI))) && (CastRemoteDisplayLocalService.NotificationSettings.zze(this.zzaaI) == null))
        {
          throw new IllegalArgumentException("At least an argument must be provided");
        }
        return this.zzaaI;
      }
      
      public Builder setNotification(Notification paramNotification)
      {
        CastRemoteDisplayLocalService.NotificationSettings.zza(this.zzaaI, paramNotification);
        return this;
      }
      
      public Builder setNotificationPendingIntent(PendingIntent paramPendingIntent)
      {
        CastRemoteDisplayLocalService.NotificationSettings.zza(this.zzaaI, paramPendingIntent);
        return this;
      }
      
      public Builder setNotificationText(String paramString)
      {
        CastRemoteDisplayLocalService.NotificationSettings.zzb(this.zzaaI, paramString);
        return this;
      }
      
      public Builder setNotificationTitle(String paramString)
      {
        CastRemoteDisplayLocalService.NotificationSettings.zza(this.zzaaI, paramString);
        return this;
      }
    }
  }
  
  private class zza
    extends Binder
  {
    private zza() {}
    
    CastRemoteDisplayLocalService zznM()
    {
      return CastRemoteDisplayLocalService.this;
    }
  }
  
  private static final class zzb
    extends BroadcastReceiver
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT"))
      {
        CastRemoteDisplayLocalService.zznI().zzb("disconnecting", new Object[0]);
        CastRemoteDisplayLocalService.stopService();
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\CastRemoteDisplayLocalService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */