package com.google.android.gms.auth.api.signin.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzmt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class zzm
{
  private static final WeakHashMap<String, zzm> zzXE = new WeakHashMap();
  private static final Lock zzXF = new ReentrantLock();
  private final Lock zzXG = new ReentrantLock();
  private final Map<String, zza> zzXH;
  
  private zzm(Map<String, zza> paramMap)
  {
    this.zzXH = paramMap;
  }
  
  public static zzm zzbN(String paramString)
  {
    zzx.zzcM(paramString);
    zzXF.lock();
    try
    {
      zzm localzzm2 = (zzm)zzXE.get(paramString);
      zzm localzzm1 = localzzm2;
      if (localzzm2 == null)
      {
        localzzm1 = new zzm(new zzb(20));
        zzXE.put(paramString, localzzm1);
      }
      return localzzm1;
    }
    finally
    {
      zzXF.unlock();
    }
  }
  
  public boolean zza(Set<String> paramSet, zza paramzza)
  {
    zzx.zzz(paramSet);
    zzx.zzz(paramzza);
    if ((paramSet.size() == 0) || (paramzza.zzb())) {
      return false;
    }
    paramSet = new ArrayList(paramSet);
    Collections.sort(paramSet);
    this.zzXG.lock();
    try
    {
      paramSet = TextUtils.join(" ", paramSet);
      this.zzXH.put(paramSet, paramzza);
      return true;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  public static class zza
  {
    private final String zzXI;
    private final long zzXJ;
    private final zzmq zzqW;
    
    public zza(String paramString, long paramLong)
    {
      this(paramString, paramLong, zzmt.zzsc());
    }
    
    private zza(String paramString, long paramLong, zzmq paramzzmq)
    {
      this.zzXI = zzx.zzcM(paramString);
      if (paramLong > 0L) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzac(bool);
        this.zzXJ = paramLong;
        this.zzqW = ((zzmq)zzx.zzz(paramzzmq));
        return;
      }
    }
    
    public boolean zzb()
    {
      return this.zzqW.currentTimeMillis() / 1000L >= this.zzXJ - 300L;
    }
  }
  
  static class zzb<K, V>
    extends LinkedHashMap<K, V>
  {
    private final int zzav;
    
    public zzb(int paramInt)
    {
      this.zzav = paramInt;
    }
    
    protected boolean removeEldestEntry(Map.Entry<K, V> paramEntry)
    {
      return size() > this.zzav;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\api\signin\internal\zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */