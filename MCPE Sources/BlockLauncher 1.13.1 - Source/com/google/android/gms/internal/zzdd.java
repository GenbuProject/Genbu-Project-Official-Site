package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzr;
import java.util.Map;

@zzhb
public final class zzdd
  implements zzdf
{
  private void zzb(zzjp paramzzjp, Map<String, String> paramMap)
  {
    String str2 = (String)paramMap.get("label");
    String str1 = (String)paramMap.get("start_label");
    paramMap = (String)paramMap.get("timestamp");
    if (TextUtils.isEmpty(str2))
    {
      zzin.zzaK("No label given for CSI tick.");
      return;
    }
    if (TextUtils.isEmpty(paramMap))
    {
      zzin.zzaK("No timestamp given for CSI tick.");
      return;
    }
    try
    {
      long l = zzc(Long.parseLong(paramMap));
      paramMap = str1;
      if (TextUtils.isEmpty(str1)) {
        paramMap = "native:view_load";
      }
      paramzzjp.zzic().zza(str2, paramMap, l);
      return;
    }
    catch (NumberFormatException paramzzjp)
    {
      zzin.zzd("Malformed timestamp for CSI tick.", paramzzjp);
    }
  }
  
  private long zzc(long paramLong)
  {
    return paramLong - zzr.zzbG().currentTimeMillis() + zzr.zzbG().elapsedRealtime();
  }
  
  private void zzc(zzjp paramzzjp, Map<String, String> paramMap)
  {
    paramMap = (String)paramMap.get("value");
    if (TextUtils.isEmpty(paramMap))
    {
      zzin.zzaK("No value given for CSI experiment.");
      return;
    }
    paramzzjp = paramzzjp.zzic().zzdA();
    if (paramzzjp == null)
    {
      zzin.zzaK("No ticker for WebView, dropping experiment ID.");
      return;
    }
    paramzzjp.zzc("e", paramMap);
  }
  
  private void zzd(zzjp paramzzjp, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("name");
    paramMap = (String)paramMap.get("value");
    if (TextUtils.isEmpty(paramMap))
    {
      zzin.zzaK("No value given for CSI extra.");
      return;
    }
    if (TextUtils.isEmpty(str))
    {
      zzin.zzaK("No name given for CSI extra.");
      return;
    }
    paramzzjp = paramzzjp.zzic().zzdA();
    if (paramzzjp == null)
    {
      zzin.zzaK("No ticker for WebView, dropping extra parameter.");
      return;
    }
    paramzzjp.zzc(str, paramMap);
  }
  
  public void zza(zzjp paramzzjp, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("action");
    if ("tick".equals(str)) {
      zzb(paramzzjp, paramMap);
    }
    do
    {
      return;
      if ("experiment".equals(str))
      {
        zzc(paramzzjp, paramMap);
        return;
      }
    } while (!"extra".equals(str));
    zzd(paramzzjp, paramMap);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzdd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */