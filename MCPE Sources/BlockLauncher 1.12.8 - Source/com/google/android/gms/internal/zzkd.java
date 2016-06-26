package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.measurement.zze;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzkd
  extends zze<zzkd>
{
  private final Map<String, Object> zzxA = new HashMap();
  
  private String zzaW(String paramString)
  {
    zzx.zzcM(paramString);
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.startsWith("&")) {
        str = paramString.substring(1);
      }
    }
    zzx.zzh(str, "Name can not be empty or \"&\"");
    return str;
  }
  
  public void set(String paramString1, String paramString2)
  {
    paramString1 = zzaW(paramString1);
    this.zzxA.put(paramString1, paramString2);
  }
  
  public String toString()
  {
    return zzF(this.zzxA);
  }
  
  public void zza(zzkd paramzzkd)
  {
    zzx.zzz(paramzzkd);
    paramzzkd.zzxA.putAll(this.zzxA);
  }
  
  public Map<String, Object> zziR()
  {
    return Collections.unmodifiableMap(this.zzxA);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzkd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */