package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzx;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class zznk
  implements ThreadFactory
{
  private final int mPriority;
  private final String zzaoq;
  private final AtomicInteger zzaor = new AtomicInteger();
  private final ThreadFactory zzaos = Executors.defaultThreadFactory();
  
  public zznk(String paramString)
  {
    this(paramString, 0);
  }
  
  public zznk(String paramString, int paramInt)
  {
    this.zzaoq = ((String)zzx.zzb(paramString, "Name must not be null"));
    this.mPriority = paramInt;
  }
  
  public Thread newThread(Runnable paramRunnable)
  {
    paramRunnable = this.zzaos.newThread(new zznl(paramRunnable, this.mPriority));
    paramRunnable.setName(this.zzaoq + "[" + this.zzaor.getAndIncrement() + "]");
    return paramRunnable;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zznk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */