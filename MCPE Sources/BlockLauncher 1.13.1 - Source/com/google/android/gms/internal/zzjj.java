package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@zzhb
public class zzjj<T>
  implements zzji<T>
{
  protected int zzBc = 0;
  protected final BlockingQueue<zzjj<T>.zza> zzNq = new LinkedBlockingQueue();
  protected T zzNr;
  private final Object zzpV = new Object();
  
  public int getStatus()
  {
    return this.zzBc;
  }
  
  public void reject()
  {
    synchronized (this.zzpV)
    {
      if (this.zzBc != 0) {
        throw new UnsupportedOperationException();
      }
    }
    this.zzBc = -1;
    Iterator localIterator = this.zzNq.iterator();
    while (localIterator.hasNext()) {
      ((zza)localIterator.next()).zzNt.run();
    }
    this.zzNq.clear();
  }
  
  public void zza(zzji.zzc<T> paramzzc, zzji.zza paramzza)
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzBc == 1)
        {
          paramzzc.zze(this.zzNr);
          return;
        }
        if (this.zzBc == -1) {
          paramzza.run();
        }
      }
      if (this.zzBc == 0) {
        this.zzNq.add(new zza(paramzzc, paramzza));
      }
    }
  }
  
  public void zzh(T paramT)
  {
    synchronized (this.zzpV)
    {
      if (this.zzBc != 0) {
        throw new UnsupportedOperationException();
      }
    }
    this.zzNr = paramT;
    this.zzBc = 1;
    Iterator localIterator = this.zzNq.iterator();
    while (localIterator.hasNext()) {
      ((zza)localIterator.next()).zzNs.zze(paramT);
    }
    this.zzNq.clear();
  }
  
  class zza
  {
    public final zzji.zzc<T> zzNs;
    public final zzji.zza zzNt;
    
    public zza(zzji.zza paramzza)
    {
      this.zzNs = paramzza;
      zzji.zza localzza;
      this.zzNt = localzza;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzjj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */