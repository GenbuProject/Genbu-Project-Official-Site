package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class zzl
{
  private AtomicInteger zzY = new AtomicInteger();
  private final Map<String, Queue<zzk<?>>> zzZ = new HashMap();
  private final Set<zzk<?>> zzaa = new HashSet();
  private final PriorityBlockingQueue<zzk<?>> zzab = new PriorityBlockingQueue();
  private final PriorityBlockingQueue<zzk<?>> zzac = new PriorityBlockingQueue();
  private zzg[] zzad;
  private zzc zzae;
  private List<zza> zzaf = new ArrayList();
  private final zzb zzj;
  private final zzn zzk;
  private final zzf zzz;
  
  public zzl(zzb paramzzb, zzf paramzzf)
  {
    this(paramzzb, paramzzf, 4);
  }
  
  public zzl(zzb paramzzb, zzf paramzzf, int paramInt)
  {
    this(paramzzb, paramzzf, paramInt, new zze(new Handler(Looper.getMainLooper())));
  }
  
  public zzl(zzb paramzzb, zzf paramzzf, int paramInt, zzn paramzzn)
  {
    this.zzj = paramzzb;
    this.zzz = paramzzf;
    this.zzad = new zzg[paramInt];
    this.zzk = paramzzn;
  }
  
  public int getSequenceNumber()
  {
    return this.zzY.incrementAndGet();
  }
  
  public void start()
  {
    stop();
    this.zzae = new zzc(this.zzab, this.zzac, this.zzj, this.zzk);
    this.zzae.start();
    int i = 0;
    while (i < this.zzad.length)
    {
      zzg localzzg = new zzg(this.zzac, this.zzz, this.zzj, this.zzk);
      this.zzad[i] = localzzg;
      localzzg.start();
      i += 1;
    }
  }
  
  public void stop()
  {
    if (this.zzae != null) {
      this.zzae.quit();
    }
    int i = 0;
    while (i < this.zzad.length)
    {
      if (this.zzad[i] != null) {
        this.zzad[i].quit();
      }
      i += 1;
    }
  }
  
  public <T> zzk<T> zze(zzk<T> paramzzk)
  {
    paramzzk.zza(this);
    synchronized (this.zzaa)
    {
      this.zzaa.add(paramzzk);
      paramzzk.zza(getSequenceNumber());
      paramzzk.zzc("add-to-queue");
      if (!paramzzk.zzr())
      {
        this.zzac.add(paramzzk);
        return paramzzk;
      }
    }
    for (;;)
    {
      String str;
      synchronized (this.zzZ)
      {
        str = paramzzk.zzh();
        if (this.zzZ.containsKey(str))
        {
          Queue localQueue = (Queue)this.zzZ.get(str);
          ??? = localQueue;
          if (localQueue == null) {
            ??? = new LinkedList();
          }
          ((Queue)???).add(paramzzk);
          this.zzZ.put(str, ???);
          if (zzs.DEBUG) {
            zzs.zza("Request for cacheKey=%s is in flight, putting on hold.", new Object[] { str });
          }
          return paramzzk;
        }
      }
      this.zzZ.put(str, null);
      this.zzab.add(paramzzk);
    }
  }
  
  <T> void zzf(zzk<T> paramzzk)
  {
    Object localObject2;
    synchronized (this.zzaa)
    {
      this.zzaa.remove(paramzzk);
      synchronized (this.zzaf)
      {
        localObject2 = this.zzaf.iterator();
        if (((Iterator)localObject2).hasNext()) {
          ((zza)((Iterator)localObject2).next()).zzg(paramzzk);
        }
      }
    }
    if (paramzzk.zzr()) {
      synchronized (this.zzZ)
      {
        paramzzk = paramzzk.zzh();
        localObject2 = (Queue)this.zzZ.remove(paramzzk);
        if (localObject2 != null)
        {
          if (zzs.DEBUG) {
            zzs.zza("Releasing %d waiting requests for cacheKey=%s.", new Object[] { Integer.valueOf(((Queue)localObject2).size()), paramzzk });
          }
          this.zzab.addAll((Collection)localObject2);
        }
        return;
      }
    }
  }
  
  public static abstract interface zza<T>
  {
    public abstract void zzg(zzk<T> paramzzk);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */