package com.google.android.gms.internal;

import java.util.Map;

@zzhb
public class zzdi
  implements zzdf
{
  private final zzdj zzzy;
  
  public zzdi(zzdj paramzzdj)
  {
    this.zzzy = paramzzdj;
  }
  
  public void zza(zzjp paramzzjp, Map<String, String> paramMap)
  {
    boolean bool1 = "1".equals(paramMap.get("transparentBackground"));
    boolean bool2 = "1".equals(paramMap.get("blur"));
    try
    {
      if (paramMap.get("blurRadius") != null)
      {
        f = Float.parseFloat((String)paramMap.get("blurRadius"));
        this.zzzy.zzd(bool1);
        this.zzzy.zza(bool2, f);
        return;
      }
    }
    catch (NumberFormatException paramzzjp)
    {
      for (;;)
      {
        zzin.zzb("Fail to parse float", paramzzjp);
        float f = 0.0F;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzdi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */