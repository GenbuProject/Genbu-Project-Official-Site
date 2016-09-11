package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.internal.zzmq;

public class zzad
  extends zzz
{
  private Handler mHandler;
  private long zzaZa;
  private final Runnable zzaZb = new Runnable()
  {
    @MainThread
    public void run()
    {
      zzad.this.zzCn().zzg(new Runnable()
      {
        public void run()
        {
          zzad.this.zzDx();
        }
      });
    }
  };
  private final zzf zzaZc = new zzf(this.zzaTV)
  {
    @WorkerThread
    public void run()
    {
      zzad.zza(zzad.this);
    }
  };
  private final zzf zzaZd = new zzf(this.zzaTV)
  {
    @WorkerThread
    public void run()
    {
      zzad.zzb(zzad.this);
    }
  };
  
  zzad(zzw paramzzw)
  {
    super(paramzzw);
  }
  
  private void zzDv()
  {
    try
    {
      if (this.mHandler == null) {
        this.mHandler = new Handler(Looper.getMainLooper());
      }
      return;
    }
    finally {}
  }
  
  @WorkerThread
  private void zzDy()
  {
    zzjk();
    long l = zzjl().elapsedRealtime();
    zzAo().zzCK().zzj("Session started, time", Long.valueOf(l));
    zzCo().zzaXu.set(false);
    zzCf().zze("auto", "_s", new Bundle());
  }
  
  @WorkerThread
  private void zzDz()
  {
    zzjk();
    long l1 = zzjl().elapsedRealtime();
    if (this.zzaZa == 0L) {
      this.zzaZa = (l1 - 3600000L);
    }
    long l2 = zzCo().zzaXw.get() + (l1 - this.zzaZa);
    zzCo().zzaXw.set(l2);
    zzAo().zzCK().zzj("Recording user engagement, ms", Long.valueOf(l2));
    Bundle localBundle = new Bundle();
    localBundle.putLong("_et", l2);
    zzCf().zze("auto", "_e", localBundle);
    zzCo().zzaXw.set(0L);
    this.zzaZa = l1;
    this.zzaZd.zzt(Math.max(0L, 3600000L - zzCo().zzaXw.get()));
  }
  
  @WorkerThread
  private void zzae(long paramLong)
  {
    zzjk();
    zzDv();
    this.zzaZc.cancel();
    this.zzaZd.cancel();
    zzAo().zzCK().zzj("Activity resumed, time", Long.valueOf(paramLong));
    this.zzaZa = paramLong;
    if (zzjl().currentTimeMillis() - zzCo().zzaXt.get() > zzCo().zzaXv.get())
    {
      zzCo().zzaXu.set(true);
      zzCo().zzaXw.set(0L);
    }
    if (zzCo().zzaXu.get())
    {
      this.zzaZc.zzt(Math.max(0L, zzCo().zzaXs.get() - zzCo().zzaXw.get()));
      return;
    }
    this.zzaZd.zzt(Math.max(0L, 3600000L - zzCo().zzaXw.get()));
  }
  
  @WorkerThread
  private void zzaf(long paramLong)
  {
    zzjk();
    zzDv();
    this.zzaZc.cancel();
    this.zzaZd.cancel();
    zzAo().zzCK().zzj("Activity paused, time", Long.valueOf(paramLong));
    if (this.zzaZa != 0L) {
      zzCo().zzaXw.set(zzCo().zzaXw.get() + (paramLong - this.zzaZa));
    }
    zzCo().zzaXv.set(zzjl().currentTimeMillis());
    try
    {
      if (!zzCo().zzaXu.get()) {
        this.mHandler.postDelayed(this.zzaZb, 1000L);
      }
      return;
    }
    finally {}
  }
  
  @MainThread
  protected void zzDu()
  {
    try
    {
      zzDv();
      this.mHandler.removeCallbacks(this.zzaZb);
      final long l = zzjl().elapsedRealtime();
      zzCn().zzg(new Runnable()
      {
        public void run()
        {
          zzad.zza(zzad.this, l);
        }
      });
      return;
    }
    finally {}
  }
  
  @MainThread
  protected void zzDw()
  {
    final long l = zzjl().elapsedRealtime();
    zzCn().zzg(new Runnable()
    {
      public void run()
      {
        zzad.zzb(zzad.this, l);
      }
    });
  }
  
  @WorkerThread
  public void zzDx()
  {
    zzjk();
    zzAo().zzCJ().zzfg("Application backgrounded. Logging engagement");
    long l = zzCo().zzaXw.get();
    if (l > 0L)
    {
      Bundle localBundle = new Bundle();
      localBundle.putLong("_et", l);
      zzCf().zze("auto", "_e", localBundle);
      zzCo().zzaXw.set(0L);
      return;
    }
    zzAo().zzCF().zzj("Not logging non-positive engagement time", Long.valueOf(l));
  }
  
  protected void zziJ() {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */