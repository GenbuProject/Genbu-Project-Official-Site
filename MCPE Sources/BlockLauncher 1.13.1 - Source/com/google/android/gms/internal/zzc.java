package com.google.android.gms.internal;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class zzc
  extends Thread
{
  private static final boolean DEBUG = zzs.DEBUG;
  private final BlockingQueue<zzk<?>> zzh;
  private final BlockingQueue<zzk<?>> zzi;
  private final zzb zzj;
  private final zzn zzk;
  private volatile boolean zzl = false;
  
  public zzc(BlockingQueue<zzk<?>> paramBlockingQueue1, BlockingQueue<zzk<?>> paramBlockingQueue2, zzb paramzzb, zzn paramzzn)
  {
    this.zzh = paramBlockingQueue1;
    this.zzi = paramBlockingQueue2;
    this.zzj = paramzzb;
    this.zzk = paramzzn;
  }
  
  public void quit()
  {
    this.zzl = true;
    interrupt();
  }
  
  public void run()
  {
    if (DEBUG) {
      zzs.zza("start new dispatcher", new Object[0]);
    }
    Process.setThreadPriority(10);
    this.zzj.zza();
    for (;;)
    {
      try
      {
        zzk localzzk = (zzk)this.zzh.take();
        localzzk.zzc("cache-queue-take");
        if (!localzzk.isCanceled()) {
          break label73;
        }
        localzzk.zzd("cache-discard-canceled");
        continue;
        if (!this.zzl) {
          continue;
        }
      }
      catch (InterruptedException localInterruptedException) {}
      return;
      label73:
      zzb.zza localzza = this.zzj.zza(localInterruptedException.zzh());
      if (localzza == null)
      {
        localInterruptedException.zzc("cache-miss");
        this.zzi.put(localInterruptedException);
      }
      else if (localzza.zzb())
      {
        localInterruptedException.zzc("cache-hit-expired");
        localInterruptedException.zza(localzza);
        this.zzi.put(localInterruptedException);
      }
      else
      {
        localInterruptedException.zzc("cache-hit");
        zzm localzzm = localInterruptedException.zza(new zzi(localzza.data, localzza.zzg));
        localInterruptedException.zzc("cache-hit-parsed");
        if (!localzza.zzc())
        {
          this.zzk.zza(localInterruptedException, localzzm);
        }
        else
        {
          localInterruptedException.zzc("cache-hit-refresh-needed");
          localInterruptedException.zza(localzza);
          localzzm.zzai = true;
          this.zzk.zza(localInterruptedException, localzzm, new Runnable()
          {
            public void run()
            {
              try
              {
                zzc.zza(zzc.this).put(localInterruptedException);
                return;
              }
              catch (InterruptedException localInterruptedException) {}
            }
          });
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */