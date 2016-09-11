package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzx;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzh
{
  private final String zzPO;
  private final long zzQD;
  private final String zzQE;
  private final boolean zzQF;
  private long zzQG;
  private final Map<String, String> zzxA;
  
  public zzh(long paramLong1, String paramString1, String paramString2, boolean paramBoolean, long paramLong2, Map<String, String> paramMap)
  {
    zzx.zzcM(paramString1);
    zzx.zzcM(paramString2);
    this.zzQD = paramLong1;
    this.zzPO = paramString1;
    this.zzQE = paramString2;
    this.zzQF = paramBoolean;
    this.zzQG = paramLong2;
    if (paramMap != null)
    {
      this.zzxA = new HashMap(paramMap);
      return;
    }
    this.zzxA = Collections.emptyMap();
  }
  
  public String getClientId()
  {
    return this.zzPO;
  }
  
  public long zzjD()
  {
    return this.zzQD;
  }
  
  public String zzjE()
  {
    return this.zzQE;
  }
  
  public boolean zzjF()
  {
    return this.zzQF;
  }
  
  public long zzjG()
  {
    return this.zzQG;
  }
  
  public Map<String, String> zzn()
  {
    return this.zzxA;
  }
  
  public void zzn(long paramLong)
  {
    this.zzQG = paramLong;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */