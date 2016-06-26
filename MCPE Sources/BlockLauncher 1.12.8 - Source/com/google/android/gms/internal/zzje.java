package com.google.android.gms.internal;

import java.util.concurrent.TimeUnit;

@zzhb
public class zzje<T>
  implements zzjg<T>
{
  private final T zzNc;
  private final zzjh zzNe;
  
  public zzje(T paramT)
  {
    this.zzNc = paramT;
    this.zzNe = new zzjh();
    this.zzNe.zzhK();
  }
  
  public boolean cancel(boolean paramBoolean)
  {
    return false;
  }
  
  public T get()
  {
    return (T)this.zzNc;
  }
  
  public T get(long paramLong, TimeUnit paramTimeUnit)
  {
    return (T)this.zzNc;
  }
  
  public boolean isCancelled()
  {
    return false;
  }
  
  public boolean isDone()
  {
    return true;
  }
  
  public void zzb(Runnable paramRunnable)
  {
    this.zzNe.zzb(paramRunnable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzje.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */