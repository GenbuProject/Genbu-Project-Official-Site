package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.analytics.internal.zzb;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzr;
import com.google.android.gms.analytics.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzrp;

public final class AnalyticsService
  extends Service
{
  private static Boolean zzOO;
  private final Handler mHandler = new Handler();
  
  public static boolean zzZ(Context paramContext)
  {
    zzx.zzz(paramContext);
    if (zzOO != null) {
      return zzOO.booleanValue();
    }
    boolean bool = zzam.zza(paramContext, AnalyticsService.class);
    zzOO = Boolean.valueOf(bool);
    return bool;
  }
  
  private void zziz()
  {
    try
    {
      synchronized (AnalyticsReceiver.zzqy)
      {
        zzrp localzzrp = AnalyticsReceiver.zzOM;
        if ((localzzrp != null) && (localzzrp.isHeld())) {
          localzzrp.release();
        }
        return;
      }
      return;
    }
    catch (SecurityException localSecurityException) {}
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public void onCreate()
  {
    super.onCreate();
    zzf localzzf = zzf.zzaa(this);
    zzaf localzzaf = localzzf.zzjm();
    if (localzzf.zzjn().zzkr())
    {
      localzzaf.zzbd("Device AnalyticsService is starting up");
      return;
    }
    localzzaf.zzbd("Local AnalyticsService is starting up");
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public void onDestroy()
  {
    zzf localzzf = zzf.zzaa(this);
    zzaf localzzaf = localzzf.zzjm();
    if (localzzf.zzjn().zzkr()) {
      localzzaf.zzbd("Device AnalyticsService is shutting down");
    }
    for (;;)
    {
      super.onDestroy();
      return;
      localzzaf.zzbd("Local AnalyticsService is shutting down");
    }
  }
  
  @RequiresPermission(allOf={"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
  public int onStartCommand(Intent paramIntent, int paramInt1, final int paramInt2)
  {
    zziz();
    final zzf localzzf = zzf.zzaa(this);
    final zzaf localzzaf = localzzf.zzjm();
    paramIntent = paramIntent.getAction();
    if (localzzf.zzjn().zzkr()) {
      localzzaf.zza("Device AnalyticsService called. startId, action", Integer.valueOf(paramInt2), paramIntent);
    }
    for (;;)
    {
      if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(paramIntent)) {
        localzzf.zziH().zza(new zzw()
        {
          public void zzc(Throwable paramAnonymousThrowable)
          {
            AnalyticsService.zza(AnalyticsService.this).post(new Runnable()
            {
              public void run()
              {
                if (AnalyticsService.this.stopSelfResult(AnalyticsService.1.this.zzOP))
                {
                  if (AnalyticsService.1.this.zzOQ.zzjn().zzkr()) {
                    AnalyticsService.1.this.zzOR.zzbd("Device AnalyticsService processed last dispatch request");
                  }
                }
                else {
                  return;
                }
                AnalyticsService.1.this.zzOR.zzbd("Local AnalyticsService processed last dispatch request");
              }
            });
          }
        });
      }
      return 2;
      localzzaf.zza("Local AnalyticsService called. startId, action", Integer.valueOf(paramInt2), paramIntent);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\analytics\AnalyticsService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */