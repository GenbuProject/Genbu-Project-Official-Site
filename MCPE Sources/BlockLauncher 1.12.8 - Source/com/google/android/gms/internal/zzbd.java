package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzhb
public class zzbd
{
  private final Object zzpV = new Object();
  private int zzsW;
  private List<zzbc> zzsX = new LinkedList();
  
  public boolean zza(zzbc paramzzbc)
  {
    synchronized (this.zzpV)
    {
      return this.zzsX.contains(paramzzbc);
    }
  }
  
  public boolean zzb(zzbc paramzzbc)
  {
    synchronized (this.zzpV)
    {
      Iterator localIterator = this.zzsX.iterator();
      while (localIterator.hasNext())
      {
        zzbc localzzbc = (zzbc)localIterator.next();
        if ((paramzzbc != localzzbc) && (localzzbc.zzcy().equals(paramzzbc.zzcy())))
        {
          localIterator.remove();
          return true;
        }
      }
      return false;
    }
  }
  
  public void zzc(zzbc paramzzbc)
  {
    synchronized (this.zzpV)
    {
      if (this.zzsX.size() >= 10)
      {
        zzin.zzaI("Queue is full, current size = " + this.zzsX.size());
        this.zzsX.remove(0);
      }
      int i = this.zzsW;
      this.zzsW = (i + 1);
      paramzzbc.zzh(i);
      this.zzsX.add(paramzzbc);
      return;
    }
  }
  
  public zzbc zzcF()
  {
    Object localObject1 = null;
    label146:
    label149:
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzsX.size() == 0)
        {
          zzin.zzaI("Queue empty");
          return null;
        }
        if (this.zzsX.size() >= 2)
        {
          int i = Integer.MIN_VALUE;
          Iterator localIterator = this.zzsX.iterator();
          if (localIterator.hasNext())
          {
            zzbc localzzbc2 = (zzbc)localIterator.next();
            int j = localzzbc2.getScore();
            if (j <= i) {
              break label146;
            }
            localObject1 = localzzbc2;
            i = j;
            break label149;
          }
          this.zzsX.remove(localObject1);
          return (zzbc)localObject1;
        }
      }
      zzbc localzzbc1 = (zzbc)this.zzsX.get(0);
      localzzbc1.zzcA();
      return localzzbc1;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzbd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */