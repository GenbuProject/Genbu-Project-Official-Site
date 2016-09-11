package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzpk.zza;

public class FlagProviderImpl
  extends zzpk.zza
{
  private boolean zzqA = false;
  private SharedPreferences zzvx;
  
  public boolean getBooleanFlagValue(String paramString, boolean paramBoolean, int paramInt)
  {
    return zza.zza.zza(this.zzvx, paramString, Boolean.valueOf(paramBoolean)).booleanValue();
  }
  
  public int getIntFlagValue(String paramString, int paramInt1, int paramInt2)
  {
    return zza.zzb.zza(this.zzvx, paramString, Integer.valueOf(paramInt1)).intValue();
  }
  
  public long getLongFlagValue(String paramString, long paramLong, int paramInt)
  {
    return zza.zzc.zza(this.zzvx, paramString, Long.valueOf(paramLong)).longValue();
  }
  
  public String getStringFlagValue(String paramString1, String paramString2, int paramInt)
  {
    return zza.zzd.zza(this.zzvx, paramString1, paramString2);
  }
  
  public void init(zzd paramzzd)
  {
    paramzzd = (Context)zze.zzp(paramzzd);
    if (this.zzqA) {
      return;
    }
    try
    {
      this.zzvx = zzb.zzw(paramzzd.createPackageContext("com.google.android.gms", 0));
      this.zzqA = true;
      return;
    }
    catch (PackageManager.NameNotFoundException paramzzd) {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\flags\impl\FlagProviderImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */