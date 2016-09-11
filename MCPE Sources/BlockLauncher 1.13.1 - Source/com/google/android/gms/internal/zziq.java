package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.ads.internal.zzr;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@zzhb
public final class zziq
{
  private static final ExecutorService zzLU = Executors.newFixedThreadPool(10, zzaB("Default"));
  private static final ExecutorService zzLV = Executors.newFixedThreadPool(5, zzaB("Loader"));
  
  public static zzjg<Void> zza(int paramInt, Runnable paramRunnable)
  {
    if (paramInt == 1) {
      zza(zzLV, new Callable()
      {
        public Void zzdt()
        {
          this.zzLW.run();
          return null;
        }
      });
    }
    zza(zzLU, new Callable()
    {
      public Void zzdt()
      {
        this.zzLW.run();
        return null;
      }
    });
  }
  
  public static zzjg<Void> zza(Runnable paramRunnable)
  {
    return zza(0, paramRunnable);
  }
  
  public static <T> zzjg<T> zza(Callable<T> paramCallable)
  {
    return zza(zzLU, paramCallable);
  }
  
  public static <T> zzjg<T> zza(ExecutorService paramExecutorService, final Callable<T> paramCallable)
  {
    zzjd localzzjd = new zzjd();
    try
    {
      localzzjd.zzc(new Runnable()
      {
        public void run()
        {
          try
          {
            Process.setThreadPriority(10);
            this.zzLX.zzg(paramCallable.call());
            return;
          }
          catch (Exception localException)
          {
            zzr.zzbF().zzb(localException, true);
            this.zzLX.cancel(true);
          }
        }
      }
      {
        public void run()
        {
          if (this.zzLX.isCancelled()) {
            this.zzLZ.cancel(true);
          }
        }
      });
      return localzzjd;
    }
    catch (RejectedExecutionException paramExecutorService)
    {
      zzin.zzd("Thread execution is rejected.", paramExecutorService);
      localzzjd.cancel(true);
    }
    return localzzjd;
  }
  
  private static ThreadFactory zzaB(String paramString)
  {
    new ThreadFactory()
    {
      private final AtomicInteger zzMa = new AtomicInteger(1);
      
      public Thread newThread(Runnable paramAnonymousRunnable)
      {
        return new Thread(paramAnonymousRunnable, "AdWorker(" + this.zzMb + ") #" + this.zzMa.getAndIncrement());
      }
    };
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zziq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */