package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;

abstract class zzf
{
  private static volatile Handler zzRC;
  private volatile long zzRD;
  private final zzw zzaTV;
  private boolean zzaVI;
  private final Runnable zzx;
  
  zzf(zzw paramzzw)
  {
    zzx.zzz(paramzzw);
    this.zzaTV = paramzzw;
    this.zzaVI = true;
    this.zzx = new Runnable()
    {
      public void run()
      {
        if (Looper.myLooper() == Looper.getMainLooper()) {
          zzf.zza(zzf.this).zzCn().zzg(this);
        }
        boolean bool;
        do
        {
          return;
          bool = zzf.this.zzbw();
          zzf.zza(zzf.this, 0L);
        } while ((!bool) || (!zzf.zzb(zzf.this)));
        zzf.this.run();
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
        zzRC = new Handler(this.zzaTV.getContext().getMainLooper());
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
  
  public void zzt(long paramLong)
  {
    cancel();
    if (paramLong >= 0L)
    {
      this.zzRD = this.zzaTV.zzjl().currentTimeMillis();
      if (!getHandler().postDelayed(this.zzx, paramLong)) {
        this.zzaTV.zzAo().zzCE().zzj("Failed to schedule delayed post. time", Long.valueOf(paramLong));
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */