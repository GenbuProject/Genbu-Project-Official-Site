package com.google.android.gms.analytics.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.analytics.AnalyticsReceiver;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;

public class zzv
  extends zzd
{
  private boolean zzRG;
  private boolean zzRH;
  private AlarmManager zzRI = (AlarmManager)getContext().getSystemService("alarm");
  
  protected zzv(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  private PendingIntent zzld()
  {
    Intent localIntent = new Intent(getContext(), AnalyticsReceiver.class);
    localIntent.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
    return PendingIntent.getBroadcast(getContext(), 0, localIntent, 0);
  }
  
  public void cancel()
  {
    zzjv();
    this.zzRH = false;
    this.zzRI.cancel(zzld());
  }
  
  public boolean zzbw()
  {
    return this.zzRH;
  }
  
  protected void zziJ()
  {
    try
    {
      this.zzRI.cancel(zzld());
      if (zzjn().zzkA() > 0L)
      {
        ActivityInfo localActivityInfo = getContext().getPackageManager().getReceiverInfo(new ComponentName(getContext(), AnalyticsReceiver.class), 2);
        if ((localActivityInfo != null) && (localActivityInfo.enabled))
        {
          zzbd("Receiver registered. Using alarm for local dispatch.");
          this.zzRG = true;
        }
      }
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
  }
  
  public boolean zzlb()
  {
    return this.zzRG;
  }
  
  public void zzlc()
  {
    zzjv();
    zzx.zza(zzlb(), "Receiver not registered");
    long l1 = zzjn().zzkA();
    if (l1 > 0L)
    {
      cancel();
      long l2 = zzjl().elapsedRealtime();
      this.zzRH = true;
      this.zzRI.setInexactRepeating(2, l2 + l1, 0L, zzld());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\analytics\internal\zzv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */