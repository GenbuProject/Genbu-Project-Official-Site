package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.zzae;

public final class zzc
{
  public static String zzT(int paramInt)
  {
    return zzb("&cd", paramInt);
  }
  
  public static String zzU(int paramInt)
  {
    return zzb("cd", paramInt);
  }
  
  public static String zzV(int paramInt)
  {
    return zzb("&cm", paramInt);
  }
  
  public static String zzW(int paramInt)
  {
    return zzb("cm", paramInt);
  }
  
  public static String zzX(int paramInt)
  {
    return zzb("&pr", paramInt);
  }
  
  public static String zzY(int paramInt)
  {
    return zzb("pr", paramInt);
  }
  
  public static String zzZ(int paramInt)
  {
    return zzb("&promo", paramInt);
  }
  
  public static String zzaa(int paramInt)
  {
    return zzb("promo", paramInt);
  }
  
  public static String zzab(int paramInt)
  {
    return zzb("pi", paramInt);
  }
  
  public static String zzac(int paramInt)
  {
    return zzb("&il", paramInt);
  }
  
  public static String zzad(int paramInt)
  {
    return zzb("il", paramInt);
  }
  
  public static String zzae(int paramInt)
  {
    return zzb("cd", paramInt);
  }
  
  public static String zzaf(int paramInt)
  {
    return zzb("cm", paramInt);
  }
  
  private static String zzb(String paramString, int paramInt)
  {
    if (paramInt < 1)
    {
      zzae.zzf("index out of range for prefix", paramString);
      return "";
    }
    return paramString + paramInt;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */