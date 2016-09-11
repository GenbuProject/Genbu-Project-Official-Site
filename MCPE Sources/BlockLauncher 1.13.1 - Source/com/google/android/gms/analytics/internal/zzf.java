package com.google.android.gms.analytics.internal;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzmt;

public class zzf
{
  private static zzf zzQn;
  private final Context mContext;
  private final zzu zzQA;
  private final Context zzQo;
  private final zzr zzQp;
  private final zzaf zzQq;
  private final com.google.android.gms.measurement.zzg zzQr;
  private final zzb zzQs;
  private final zzv zzQt;
  private final zzan zzQu;
  private final zzai zzQv;
  private final GoogleAnalytics zzQw;
  private final zzn zzQx;
  private final zza zzQy;
  private final zzk zzQz;
  private final zzmq zzqW;
  
  protected zzf(zzg paramzzg)
  {
    Object localObject1 = paramzzg.getApplicationContext();
    zzx.zzb(localObject1, "Application context can't be null");
    zzx.zzb(localObject1 instanceof Application, "getApplicationContext didn't return the application");
    Object localObject2 = paramzzg.zzjx();
    zzx.zzz(localObject2);
    this.mContext = ((Context)localObject1);
    this.zzQo = ((Context)localObject2);
    this.zzqW = paramzzg.zzh(this);
    this.zzQp = paramzzg.zzg(this);
    localObject2 = paramzzg.zzf(this);
    ((zzaf)localObject2).zza();
    this.zzQq = ((zzaf)localObject2);
    if (zzjn().zzkr()) {
      zzjm().zzbf("Google Analytics " + zze.VERSION + " is starting up.");
    }
    for (;;)
    {
      localObject2 = paramzzg.zzq(this);
      ((zzai)localObject2).zza();
      this.zzQv = ((zzai)localObject2);
      localObject2 = paramzzg.zze(this);
      ((zzan)localObject2).zza();
      this.zzQu = ((zzan)localObject2);
      localObject2 = paramzzg.zzl(this);
      zzn localzzn = paramzzg.zzd(this);
      zza localzza = paramzzg.zzc(this);
      zzk localzzk = paramzzg.zzb(this);
      zzu localzzu = paramzzg.zza(this);
      localObject1 = paramzzg.zzab((Context)localObject1);
      ((com.google.android.gms.measurement.zzg)localObject1).zza(zzjw());
      this.zzQr = ((com.google.android.gms.measurement.zzg)localObject1);
      localObject1 = paramzzg.zzi(this);
      localzzn.zza();
      this.zzQx = localzzn;
      localzza.zza();
      this.zzQy = localzza;
      localzzk.zza();
      this.zzQz = localzzk;
      localzzu.zza();
      this.zzQA = localzzu;
      paramzzg = paramzzg.zzp(this);
      paramzzg.zza();
      this.zzQt = paramzzg;
      ((zzb)localObject2).zza();
      this.zzQs = ((zzb)localObject2);
      if (zzjn().zzkr()) {
        zzjm().zzb("Device AnalyticsService version", zze.VERSION);
      }
      ((GoogleAnalytics)localObject1).zza();
      this.zzQw = ((GoogleAnalytics)localObject1);
      ((zzb)localObject2).start();
      return;
      zzjm().zzbf("Google Analytics " + zze.VERSION + " is starting up. " + "To enable debug logging on a device run:\n" + "  adb shell setprop log.tag.GAv4 DEBUG\n" + "  adb logcat -s GAv4");
    }
  }
  
  private void zza(zzd paramzzd)
  {
    zzx.zzb(paramzzd, "Analytics service not created/initialized");
    zzx.zzb(paramzzd.isInitialized(), "Analytics service not initialized");
  }
  
  public static zzf zzaa(Context paramContext)
  {
    zzx.zzz(paramContext);
    if (zzQn == null) {}
    try
    {
      if (zzQn == null)
      {
        zzmq localzzmq = zzmt.zzsc();
        long l1 = localzzmq.elapsedRealtime();
        paramContext = new zzf(new zzg(paramContext.getApplicationContext()));
        zzQn = paramContext;
        GoogleAnalytics.zziF();
        l1 = localzzmq.elapsedRealtime() - l1;
        long l2 = ((Long)zzy.zzSz.get()).longValue();
        if (l1 > l2) {
          paramContext.zzjm().zzc("Slow initialization (ms)", Long.valueOf(l1), Long.valueOf(l2));
        }
      }
      return zzQn;
    }
    finally {}
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  public zzb zziH()
  {
    zza(this.zzQs);
    return this.zzQs;
  }
  
  public zzan zziI()
  {
    zza(this.zzQu);
    return this.zzQu;
  }
  
  public zzai zzjA()
  {
    if ((this.zzQv == null) || (!this.zzQv.isInitialized())) {
      return null;
    }
    return this.zzQv;
  }
  
  public zza zzjB()
  {
    zza(this.zzQy);
    return this.zzQy;
  }
  
  public zzn zzjC()
  {
    zza(this.zzQx);
    return this.zzQx;
  }
  
  public void zzjk() {}
  
  public zzmq zzjl()
  {
    return this.zzqW;
  }
  
  public zzaf zzjm()
  {
    zza(this.zzQq);
    return this.zzQq;
  }
  
  public zzr zzjn()
  {
    return this.zzQp;
  }
  
  public com.google.android.gms.measurement.zzg zzjo()
  {
    zzx.zzz(this.zzQr);
    return this.zzQr;
  }
  
  public zzv zzjp()
  {
    zza(this.zzQt);
    return this.zzQt;
  }
  
  public zzai zzjq()
  {
    zza(this.zzQv);
    return this.zzQv;
  }
  
  public zzk zzjt()
  {
    zza(this.zzQz);
    return this.zzQz;
  }
  
  public zzu zzju()
  {
    return this.zzQA;
  }
  
  protected Thread.UncaughtExceptionHandler zzjw()
  {
    new Thread.UncaughtExceptionHandler()
    {
      public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
      {
        paramAnonymousThread = zzf.this.zzjy();
        if (paramAnonymousThread != null) {
          paramAnonymousThread.zze("Job execution failed", paramAnonymousThrowable);
        }
      }
    };
  }
  
  public Context zzjx()
  {
    return this.zzQo;
  }
  
  public zzaf zzjy()
  {
    return this.zzQq;
  }
  
  public GoogleAnalytics zzjz()
  {
    zzx.zzz(this.zzQw);
    zzx.zzb(this.zzQw.isInitialized(), "Analytics instance not initialized");
    return this.zzQw;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */