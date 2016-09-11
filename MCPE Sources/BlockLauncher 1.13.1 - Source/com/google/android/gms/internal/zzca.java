package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@zzhb
public class zzca
{
  @Nullable
  private final zzcb zzpe;
  private final Map<String, zzbz> zzxy;
  
  public zzca(@Nullable zzcb paramzzcb)
  {
    this.zzpe = paramzzcb;
    this.zzxy = new HashMap();
  }
  
  public void zza(String paramString, zzbz paramzzbz)
  {
    this.zzxy.put(paramString, paramzzbz);
  }
  
  public void zza(String paramString1, String paramString2, long paramLong)
  {
    zzbx.zza(this.zzpe, (zzbz)this.zzxy.get(paramString2), paramLong, new String[] { paramString1 });
    this.zzxy.put(paramString1, zzbx.zza(this.zzpe, paramLong));
  }
  
  @Nullable
  public zzcb zzdA()
  {
    return this.zzpe;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzca.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */