package com.google.android.gms.games.internal.events;

import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class EventIncrementCache
{
  final Object zzaId = new Object();
  private Handler zzaIe;
  private boolean zzaIf;
  private HashMap<String, AtomicInteger> zzaIg;
  private int zzaIh;
  
  public EventIncrementCache(Looper paramLooper, int paramInt)
  {
    this.zzaIe = new Handler(paramLooper);
    this.zzaIg = new HashMap();
    this.zzaIh = paramInt;
  }
  
  private void zzxl()
  {
    synchronized (this.zzaId)
    {
      this.zzaIf = false;
      flush();
      return;
    }
  }
  
  public void flush()
  {
    synchronized (this.zzaId)
    {
      Iterator localIterator = this.zzaIg.entrySet().iterator();
      if (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        zzs((String)localEntry.getKey(), ((AtomicInteger)localEntry.getValue()).get());
      }
    }
    this.zzaIg.clear();
  }
  
  protected abstract void zzs(String paramString, int paramInt);
  
  public void zzw(String paramString, int paramInt)
  {
    synchronized (this.zzaId)
    {
      if (!this.zzaIf)
      {
        this.zzaIf = true;
        this.zzaIe.postDelayed(new Runnable()
        {
          public void run()
          {
            EventIncrementCache.zza(EventIncrementCache.this);
          }
        }, this.zzaIh);
      }
      AtomicInteger localAtomicInteger2 = (AtomicInteger)this.zzaIg.get(paramString);
      AtomicInteger localAtomicInteger1 = localAtomicInteger2;
      if (localAtomicInteger2 == null)
      {
        localAtomicInteger1 = new AtomicInteger();
        this.zzaIg.put(paramString, localAtomicInteger1);
      }
      localAtomicInteger1.addAndGet(paramInt);
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\internal\events\EventIncrementCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */