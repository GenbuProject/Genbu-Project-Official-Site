package com.google.android.gms.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzhb
public class zzjd<T>
  implements zzjg<T>
{
  private boolean zzCy = false;
  private T zzNc = null;
  private boolean zzNd = false;
  private final zzjh zzNe = new zzjh();
  private final Object zzpV = new Object();
  
  public boolean cancel(boolean paramBoolean)
  {
    if (!paramBoolean) {
      return false;
    }
    synchronized (this.zzpV)
    {
      if (this.zzNd) {
        return false;
      }
    }
    this.zzCy = true;
    this.zzNd = true;
    this.zzpV.notifyAll();
    this.zzNe.zzhK();
    return true;
  }
  
  public T get()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzNd;
      if (bool) {}
    }
    try
    {
      this.zzpV.wait();
      if (this.zzCy)
      {
        throw new CancellationException("CallbackFuture was cancelled.");
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
      Object localObject3 = this.zzNc;
      return (T)localObject3;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  public T get(long paramLong, TimeUnit paramTimeUnit)
    throws TimeoutException
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzNd;
      if (!bool) {}
      try
      {
        paramLong = paramTimeUnit.toMillis(paramLong);
        if (paramLong != 0L) {
          this.zzpV.wait(paramLong);
        }
      }
      catch (InterruptedException paramTimeUnit)
      {
        for (;;) {}
      }
      if (!this.zzNd) {
        throw new TimeoutException("CallbackFuture timed out.");
      }
    }
    if (this.zzCy) {
      throw new CancellationException("CallbackFuture was cancelled.");
    }
    paramTimeUnit = this.zzNc;
    return paramTimeUnit;
  }
  
  public boolean isCancelled()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzCy;
      return bool;
    }
  }
  
  public boolean isDone()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzNd;
      return bool;
    }
  }
  
  public void zzb(Runnable paramRunnable)
  {
    this.zzNe.zzb(paramRunnable);
  }
  
  public void zzc(Runnable paramRunnable)
  {
    this.zzNe.zzc(paramRunnable);
  }
  
  public void zzg(T paramT)
  {
    synchronized (this.zzpV)
    {
      if (this.zzCy) {
        return;
      }
      if (this.zzNd) {
        throw new IllegalStateException("Provided CallbackFuture with multiple values.");
      }
    }
    this.zzNd = true;
    this.zzNc = paramT;
    this.zzpV.notifyAll();
    this.zzNe.zzhK();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzjd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */