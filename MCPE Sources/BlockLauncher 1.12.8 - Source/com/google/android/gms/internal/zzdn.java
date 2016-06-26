package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import java.util.Map;

public class zzdn
  implements zzdf
{
  private final zza zzzH;
  
  public zzdn(zza paramzza)
  {
    this.zzzH = paramzza;
  }
  
  public static void zza(zzjp paramzzjp, zza paramzza)
  {
    paramzzjp.zzhU().zza("/reward", new zzdn(paramzza));
  }
  
  private void zze(Map<String, String> paramMap)
  {
    try
    {
      int i = Integer.parseInt((String)paramMap.get("amount"));
      paramMap = (String)paramMap.get("type");
      if (!TextUtils.isEmpty(paramMap))
      {
        paramMap = new RewardItemParcel(paramMap, i);
        this.zzzH.zzb(paramMap);
        return;
      }
    }
    catch (NumberFormatException paramMap)
    {
      for (;;)
      {
        zzin.zzd("Unable to parse reward amount.", paramMap);
        paramMap = null;
      }
    }
  }
  
  private void zzf(Map<String, String> paramMap)
  {
    this.zzzH.zzbq();
  }
  
  public void zza(zzjp paramzzjp, Map<String, String> paramMap)
  {
    paramzzjp = (String)paramMap.get("action");
    if ("grant".equals(paramzzjp)) {
      zze(paramMap);
    }
    while (!"video_start".equals(paramzzjp)) {
      return;
    }
    zzf(paramMap);
  }
  
  public static abstract interface zza
  {
    public abstract void zzb(RewardItemParcel paramRewardItemParcel);
    
    public abstract void zzbq();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzdn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */