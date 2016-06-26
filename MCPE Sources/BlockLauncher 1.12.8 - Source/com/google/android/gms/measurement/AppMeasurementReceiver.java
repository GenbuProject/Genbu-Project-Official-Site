package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzrp;
import com.google.android.gms.measurement.internal.zzaj;
import com.google.android.gms.measurement.internal.zzd;
import com.google.android.gms.measurement.internal.zzp;
import com.google.android.gms.measurement.internal.zzp.zza;
import com.google.android.gms.measurement.internal.zzw;

public final class AppMeasurementReceiver
  extends BroadcastReceiver
{
  static zzrp zzOM;
  static Boolean zzON;
  static final Object zzqy = new Object();
  
  public static boolean zzY(Context paramContext)
  {
    zzx.zzz(paramContext);
    if (zzON != null) {
      return zzON.booleanValue();
    }
    boolean bool = zzaj.zza(paramContext, AppMeasurementReceiver.class, false);
    zzON = Boolean.valueOf(bool);
    return bool;
  }
  
  @MainThread
  public void onReceive(Context paramContext, Intent arg2)
  {
    Object localObject = zzw.zzaT(paramContext);
    localzzp = ((zzw)localObject).zzAo();
    ??? = ???.getAction();
    if (((zzw)localObject).zzCp().zzkr()) {
      localzzp.zzCK().zzj("Device AppMeasurementReceiver got", ???);
    }
    for (;;)
    {
      boolean bool;
      if ("com.google.android.gms.measurement.UPLOAD".equals(???))
      {
        bool = AppMeasurementService.zzZ(paramContext);
        localObject = new Intent(paramContext, AppMeasurementService.class);
        ((Intent)localObject).setAction("com.google.android.gms.measurement.UPLOAD");
      }
      synchronized (zzqy)
      {
        paramContext.startService((Intent)localObject);
        if (!bool)
        {
          return;
          localzzp.zzCK().zzj("Local AppMeasurementReceiver got", ???);
          continue;
        }
        try
        {
          if (zzOM == null)
          {
            zzOM = new zzrp(paramContext, 1, "AppMeasurement WakeLock");
            zzOM.setReferenceCounted(false);
          }
          zzOM.acquire(1000L);
        }
        catch (SecurityException paramContext)
        {
          for (;;)
          {
            localzzp.zzCF().zzfg("AppMeasurementService at risk of not starting. For more reliable app measurements, add the WAKE_LOCK permission to your manifest.");
          }
        }
        return;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\AppMeasurementReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */