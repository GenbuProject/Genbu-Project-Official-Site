package com.google.android.gms.measurement;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzc
{
  private final zzf zzaUi;
  private boolean zzaUj;
  private long zzaUk;
  private long zzaUl;
  private long zzaUm;
  private long zzaUn;
  private long zzaUo;
  private boolean zzaUp;
  private final Map<Class<? extends zze>, zze> zzaUq;
  private final List<zzi> zzaUr;
  private final zzmq zzqW;
  
  zzc(zzc paramzzc)
  {
    this.zzaUi = paramzzc.zzaUi;
    this.zzqW = paramzzc.zzqW;
    this.zzaUk = paramzzc.zzaUk;
    this.zzaUl = paramzzc.zzaUl;
    this.zzaUm = paramzzc.zzaUm;
    this.zzaUn = paramzzc.zzaUn;
    this.zzaUo = paramzzc.zzaUo;
    this.zzaUr = new ArrayList(paramzzc.zzaUr);
    this.zzaUq = new HashMap(paramzzc.zzaUq.size());
    paramzzc = paramzzc.zzaUq.entrySet().iterator();
    while (paramzzc.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramzzc.next();
      zze localzze = zzg((Class)localEntry.getKey());
      ((zze)localEntry.getValue()).zza(localzze);
      this.zzaUq.put(localEntry.getKey(), localzze);
    }
  }
  
  zzc(zzf paramzzf, zzmq paramzzmq)
  {
    zzx.zzz(paramzzf);
    zzx.zzz(paramzzmq);
    this.zzaUi = paramzzf;
    this.zzqW = paramzzmq;
    this.zzaUn = 1800000L;
    this.zzaUo = 3024000000L;
    this.zzaUq = new HashMap();
    this.zzaUr = new ArrayList();
  }
  
  private static <T extends zze> T zzg(Class<T> paramClass)
  {
    try
    {
      paramClass = (zze)paramClass.newInstance();
      return paramClass;
    }
    catch (InstantiationException paramClass)
    {
      throw new IllegalArgumentException("dataType doesn't have default constructor", paramClass);
    }
    catch (IllegalAccessException paramClass)
    {
      throw new IllegalArgumentException("dataType default constructor is not accessible", paramClass);
    }
  }
  
  void zzAA()
  {
    this.zzaUm = this.zzqW.elapsedRealtime();
    if (this.zzaUl != 0L) {}
    for (this.zzaUk = this.zzaUl;; this.zzaUk = this.zzqW.currentTimeMillis())
    {
      this.zzaUj = true;
      return;
    }
  }
  
  zzf zzAB()
  {
    return this.zzaUi;
  }
  
  zzg zzAC()
  {
    return this.zzaUi.zzAC();
  }
  
  boolean zzAD()
  {
    return this.zzaUp;
  }
  
  void zzAE()
  {
    this.zzaUp = true;
  }
  
  public zzc zzAu()
  {
    return new zzc(this);
  }
  
  public Collection<zze> zzAv()
  {
    return this.zzaUq.values();
  }
  
  public List<zzi> zzAw()
  {
    return this.zzaUr;
  }
  
  public long zzAx()
  {
    return this.zzaUk;
  }
  
  public void zzAy()
  {
    zzAC().zze(this);
  }
  
  public boolean zzAz()
  {
    return this.zzaUj;
  }
  
  public void zzM(long paramLong)
  {
    this.zzaUl = paramLong;
  }
  
  public void zzb(zze paramzze)
  {
    zzx.zzz(paramzze);
    Class localClass = paramzze.getClass();
    if (localClass.getSuperclass() != zze.class) {
      throw new IllegalArgumentException();
    }
    paramzze.zza(zzf(localClass));
  }
  
  public <T extends zze> T zze(Class<T> paramClass)
  {
    return (zze)this.zzaUq.get(paramClass);
  }
  
  public <T extends zze> T zzf(Class<T> paramClass)
  {
    zze localzze2 = (zze)this.zzaUq.get(paramClass);
    zze localzze1 = localzze2;
    if (localzze2 == null)
    {
      localzze1 = zzg(paramClass);
      this.zzaUq.put(paramClass, localzze1);
    }
    return localzze1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */