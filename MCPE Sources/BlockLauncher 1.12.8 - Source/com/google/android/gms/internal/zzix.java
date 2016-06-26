package com.google.android.gms.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.zzx;

@zzhb
public class zzix
{
  private Handler mHandler = null;
  private HandlerThread zzMG = null;
  private int zzMH = 0;
  private final Object zzpV = new Object();
  
  public Looper zzhC()
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzMH == 0)
        {
          if (this.zzMG == null)
          {
            zzin.v("Starting the looper thread.");
            this.zzMG = new HandlerThread("LooperProvider");
            this.zzMG.start();
            this.mHandler = new Handler(this.zzMG.getLooper());
            zzin.v("Looper thread started.");
            this.zzMH += 1;
            Looper localLooper = this.zzMG.getLooper();
            return localLooper;
          }
          zzin.v("Resuming the looper thread");
          this.zzpV.notifyAll();
        }
      }
      zzx.zzb(this.zzMG, "Invalid state: mHandlerThread should already been initialized.");
    }
  }
  
  public void zzhD()
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzMH > 0)
        {
          bool = true;
          zzx.zzb(bool, "Invalid state: release() called more times than expected.");
          int i = this.zzMH - 1;
          this.zzMH = i;
          if (i == 0) {
            this.mHandler.post(new Runnable()
            {
              public void run()
              {
                synchronized (zzix.zza(zzix.this))
                {
                  zzin.v("Suspending the looper thread");
                  for (;;)
                  {
                    int i = zzix.zzb(zzix.this);
                    if (i == 0) {
                      try
                      {
                        zzix.zza(zzix.this).wait();
                        zzin.v("Looper thread resumed");
                      }
                      catch (InterruptedException localInterruptedException)
                      {
                        zzin.v("Looper thread interrupted.");
                      }
                    }
                  }
                }
              }
            });
          }
          return;
        }
      }
      boolean bool = false;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */