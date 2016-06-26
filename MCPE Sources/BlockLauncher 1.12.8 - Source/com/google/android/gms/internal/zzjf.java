package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@zzhb
public class zzjf
{
  public static <A, B> zzjg<B> zza(final zzjg<A> paramzzjg, final zza<A, B> paramzza)
  {
    zzjd localzzjd = new zzjd();
    paramzzjg.zzb(new Runnable()
    {
      public void run()
      {
        try
        {
          this.zzNf.zzg(paramzza.zzf(paramzzjg.get()));
          return;
        }
        catch (ExecutionException localExecutionException)
        {
          this.zzNf.cancel(true);
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;) {}
        }
        catch (CancellationException localCancellationException)
        {
          for (;;) {}
        }
      }
    });
    return localzzjd;
  }
  
  public static <V> zzjg<List<V>> zzl(final List<zzjg<V>> paramList)
  {
    final zzjd localzzjd = new zzjd();
    final int i = paramList.size();
    AtomicInteger localAtomicInteger = new AtomicInteger(0);
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      ((zzjg)localIterator.next()).zzb(new Runnable()
      {
        public void run()
        {
          if (this.zzNi.incrementAndGet() >= i) {}
          try
          {
            localzzjd.zzg(zzjf.zzn(paramList));
            return;
          }
          catch (InterruptedException localInterruptedException)
          {
            zzin.zzd("Unable to convert list of futures to a future of list", localInterruptedException);
            return;
          }
          catch (ExecutionException localExecutionException)
          {
            for (;;) {}
          }
        }
      });
    }
    return localzzjd;
  }
  
  private static <V> List<V> zzm(List<zzjg<V>> paramList)
    throws ExecutionException, InterruptedException
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = ((zzjg)paramList.next()).get();
      if (localObject != null) {
        localArrayList.add(localObject);
      }
    }
    return localArrayList;
  }
  
  public static abstract interface zza<D, R>
  {
    public abstract R zzf(D paramD);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzjf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */