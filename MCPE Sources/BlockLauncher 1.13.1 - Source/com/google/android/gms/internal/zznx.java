package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class zznx
{
  private static final double zzazE = 1000.0D / TimeUnit.SECONDS.toNanos(1L);
  private static final double zzazF = 1000.0D / TimeUnit.SECONDS.toNanos(1L);
  private static final zznx zzazI = new zznx();
  private final Map<String, Map<String, zza>> zzazG;
  private final Map<String, zza> zzazH;
  
  private zznx()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(zznt.zzaxF.name, new zza(-90.0D, 90.0D, null));
    localHashMap.put(zznt.zzaxG.name, new zza(-180.0D, 180.0D, null));
    localHashMap.put(zznt.zzaxH.name, new zza(0.0D, 10000.0D, null));
    localHashMap.put(zznt.zzaxE.name, new zza(0.0D, 1000.0D, null));
    localHashMap.put(zznt.zzaxI.name, new zza(-100000.0D, 100000.0D, null));
    localHashMap.put(zznt.zzaxP.name, new zza(0.0D, 100.0D, null));
    localHashMap.put(zznt.zzaxx.name, new zza(0.0D, 100.0D, null));
    localHashMap.put(zznt.zzaxA.name, new zza(0.0D, 9.223372036854776E18D, null));
    localHashMap.put(zznt.zzaxM.name, new zza(0.0D, 10.0D, null));
    localHashMap.put(zznt.zzaxN.name, new zza(0.0D, 1000.0D, null));
    localHashMap.put(zznt.zzaxQ.name, new zza(0.0D, 200000.0D, null));
    this.zzazH = Collections.unmodifiableMap(localHashMap);
    localHashMap = new HashMap();
    localHashMap.put("com.google.step_count.delta", zzf(zznt.zzaxz.name, new zza(0.0D, zzazE, null)));
    localHashMap.put("com.google.calories.consumed", zzf(zznt.zzaxT.name, new zza(0.0D, zzazF, null)));
    localHashMap.put("com.google.calories.expended", zzf(zznt.zzaxT.name, new zza(0.0D, zzazF, null)));
    this.zzazG = Collections.unmodifiableMap(localHashMap);
  }
  
  private static <K, V> Map<K, V> zzf(K paramK, V paramV)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramK, paramV);
    return localHashMap;
  }
  
  public static zznx zzuG()
  {
    return zzazI;
  }
  
  public zza zzC(String paramString1, String paramString2)
  {
    paramString1 = (Map)this.zzazG.get(paramString1);
    if (paramString1 != null) {
      return (zza)paramString1.get(paramString2);
    }
    return null;
  }
  
  public zza zzdE(String paramString)
  {
    return (zza)this.zzazH.get(paramString);
  }
  
  public static class zza
  {
    private final double zzazJ;
    private final double zzazK;
    
    private zza(double paramDouble1, double paramDouble2)
    {
      this.zzazJ = paramDouble1;
      this.zzazK = paramDouble2;
    }
    
    public boolean zzh(double paramDouble)
    {
      return (paramDouble >= this.zzazJ) && (paramDouble <= this.zzazK);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zznx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */