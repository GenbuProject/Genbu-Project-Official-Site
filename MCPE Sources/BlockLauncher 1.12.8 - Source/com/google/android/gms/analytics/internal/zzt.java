package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.measurement.zzg;

abstract class zzt
{
  private static volatile Handler zzRC;
  private final zzf zzQj;
  private volatile long zzRD;
  private boolean zzRE;
  private final Runnable zzx;
  
  zzt(zzf paramzzf)
  {
    zzx.zzz(paramzzf);
    this.zzQj = paramzzf;
    this.zzx = new Runnable()
    {
      public void run()
      {
        if (Looper.myLooper() == Looper.getMainLooper()) {
          zzt.zza(zzt.this).zzjo().zzf(this);
        }
        boolean bool;
        do
        {
          return;
          bool = zzt.this.zzbw();
          zzt.zza(zzt.this, 0L);
        } while ((!bool) || (zzt.zzb(zzt.this)));
        zzt.this.run();
      }
    };
  }
  
  private Handler getHandler()
  {
    if (zzRC != null) {
      return zzRC;
    }
    try
    {
      if (zzRC == null) {
        zzRC = new Handler(this.zzQj.getContext().getMainLooper());
      }
      Handler localHandler = zzRC;
      return localHandler;
    }
    finally {}
  }
  
  public void cancel()
  {
    this.zzRD = 0L;
    getHandler().removeCallbacks(this.zzx);
  }
  
  public abstract void run();
  
  public boolean zzbw()
  {
    return this.zzRD != 0L;
  }
  
  public long zzkY()
  {
    if (this.zzRD == 0L) {
      return 0L;
    }
    return Math.abs(this.zzQj.zzjl().currentTimeMillis() - this.zzRD);
  }
  
  public void zzt(long paramLong)
  {
    cancel();
    if (paramLong >= 0L)
    {
      this.zzRD = this.zzQj.zzjl().currentTimeMillis();
      if (!getHandler().postDelayed(this.zzx, paramLong)) {
        this.zzQj.zzjm().zze("Failed to schedule delayed post. time", Long.valueOf(paramLong));
      }
    }
  }
  
  public void zzu(long paramLong)
  {
    long l = 0L;
    if (!zzbw()) {
      return;
    }
    if (paramLong < 0L)
    {
      cancel();
      return;
    }
    paramLong -= Math.abs(this.zzQj.zzjl().currentTimeMillis() - this.zzRD);
    if (paramLong < 0L) {
      paramLong = l;
    }
    for (;;)
    {
      getHandler().removeCallbacks(this.zzx);
      if (getHandler().postDelayed(this.zzx, paramLong)) {
        break;
      }
      this.zzQj.zzjm().zze("Failed to adjust delayed post. time", Long.valueOf(paramLong));
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\analytics\internal\zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */