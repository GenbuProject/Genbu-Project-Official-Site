package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.zzr;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zzhb
public final class zzhg
{
  private int mOrientation = -1;
  private final AdRequestInfoParcel zzCu;
  private List<String> zzGK;
  private boolean zzJA = true;
  private int zzJB = 0;
  private String zzJC = "";
  private boolean zzJD = false;
  private RewardItemParcel zzJE;
  private List<String> zzJF;
  private List<String> zzJG;
  private boolean zzJH = false;
  private String zzJn;
  private String zzJo;
  private List<String> zzJp;
  private String zzJq;
  private String zzJr;
  private List<String> zzJs;
  private long zzJt = -1L;
  private boolean zzJu = false;
  private final long zzJv = -1L;
  private long zzJw = -1L;
  private boolean zzJx = false;
  private boolean zzJy = false;
  private boolean zzJz = false;
  private boolean zzuS = false;
  private String zzxY;
  
  public zzhg(AdRequestInfoParcel paramAdRequestInfoParcel)
  {
    this.zzCu = paramAdRequestInfoParcel;
  }
  
  private void zzA(Map<String, List<String>> paramMap)
  {
    paramMap = zzd(paramMap, "X-Afma-Fluid");
    if ((paramMap != null) && (paramMap.equals("height"))) {
      this.zzJD = true;
    }
  }
  
  private void zzB(Map<String, List<String>> paramMap)
  {
    this.zzuS = "native_express".equals(zzd(paramMap, "X-Afma-Ad-Format"));
  }
  
  private void zzC(Map<String, List<String>> paramMap)
  {
    this.zzJE = RewardItemParcel.zzay(zzd(paramMap, "X-Afma-Rewards"));
  }
  
  private void zzD(Map<String, List<String>> paramMap)
  {
    if (this.zzJF != null) {
      return;
    }
    this.zzJF = zzf(paramMap, "X-Afma-Reward-Video-Start-Urls");
  }
  
  private void zzE(Map<String, List<String>> paramMap)
  {
    if (this.zzJG != null) {
      return;
    }
    this.zzJG = zzf(paramMap, "X-Afma-Reward-Video-Complete-Urls");
  }
  
  private void zzF(Map<String, List<String>> paramMap)
  {
    this.zzJH |= zzg(paramMap, "X-Afma-Use-Displayed-Impression");
  }
  
  static String zzd(Map<String, List<String>> paramMap, String paramString)
  {
    paramMap = (List)paramMap.get(paramString);
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      return (String)paramMap.get(0);
    }
    return null;
  }
  
  static long zze(Map<String, List<String>> paramMap, String paramString)
  {
    paramMap = (List)paramMap.get(paramString);
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramMap = (String)paramMap.get(0);
      try
      {
        float f = Float.parseFloat(paramMap);
        return (f * 1000.0F);
      }
      catch (NumberFormatException localNumberFormatException)
      {
        zzin.zzaK("Could not parse float from " + paramString + " header: " + paramMap);
      }
    }
    return -1L;
  }
  
  static List<String> zzf(Map<String, List<String>> paramMap, String paramString)
  {
    paramMap = (List)paramMap.get(paramString);
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramMap = (String)paramMap.get(0);
      if (paramMap != null) {
        return Arrays.asList(paramMap.trim().split("\\s+"));
      }
    }
    return null;
  }
  
  private boolean zzg(Map<String, List<String>> paramMap, String paramString)
  {
    paramMap = (List)paramMap.get(paramString);
    return (paramMap != null) && (!paramMap.isEmpty()) && (Boolean.valueOf((String)paramMap.get(0)).booleanValue());
  }
  
  private void zzk(Map<String, List<String>> paramMap)
  {
    this.zzJn = zzd(paramMap, "X-Afma-Ad-Size");
  }
  
  private void zzl(Map<String, List<String>> paramMap)
  {
    paramMap = zzf(paramMap, "X-Afma-Click-Tracking-Urls");
    if (paramMap != null) {
      this.zzJp = paramMap;
    }
  }
  
  private void zzm(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Debug-Dialog");
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      this.zzJq = ((String)paramMap.get(0));
    }
  }
  
  private void zzn(Map<String, List<String>> paramMap)
  {
    paramMap = zzf(paramMap, "X-Afma-Tracking-Urls");
    if (paramMap != null) {
      this.zzJs = paramMap;
    }
  }
  
  private void zzo(Map<String, List<String>> paramMap)
  {
    long l = zze(paramMap, "X-Afma-Interstitial-Timeout");
    if (l != -1L) {
      this.zzJt = l;
    }
  }
  
  private void zzp(Map<String, List<String>> paramMap)
  {
    this.zzJr = zzd(paramMap, "X-Afma-ActiveView");
  }
  
  private void zzq(Map<String, List<String>> paramMap)
  {
    this.zzJy = "native".equals(zzd(paramMap, "X-Afma-Ad-Format"));
  }
  
  private void zzr(Map<String, List<String>> paramMap)
  {
    this.zzJx |= zzg(paramMap, "X-Afma-Custom-Rendering-Allowed");
  }
  
  private void zzs(Map<String, List<String>> paramMap)
  {
    this.zzJu |= zzg(paramMap, "X-Afma-Mediation");
  }
  
  private void zzt(Map<String, List<String>> paramMap)
  {
    paramMap = zzf(paramMap, "X-Afma-Manual-Tracking-Urls");
    if (paramMap != null) {
      this.zzGK = paramMap;
    }
  }
  
  private void zzu(Map<String, List<String>> paramMap)
  {
    long l = zze(paramMap, "X-Afma-Refresh-Rate");
    if (l != -1L) {
      this.zzJw = l;
    }
  }
  
  private void zzv(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Orientation");
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      paramMap = (String)paramMap.get(0);
      if (!"portrait".equalsIgnoreCase(paramMap)) {
        break label58;
      }
      this.mOrientation = zzr.zzbE().zzhw();
    }
    label58:
    while (!"landscape".equalsIgnoreCase(paramMap)) {
      return;
    }
    this.mOrientation = zzr.zzbE().zzhv();
  }
  
  private void zzw(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Use-HTTPS");
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      this.zzJz = Boolean.valueOf((String)paramMap.get(0)).booleanValue();
    }
  }
  
  private void zzx(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Content-Url-Opted-Out");
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      this.zzJA = Boolean.valueOf((String)paramMap.get(0)).booleanValue();
    }
  }
  
  private void zzy(Map<String, List<String>> paramMap)
  {
    paramMap = zzf(paramMap, "X-Afma-OAuth-Token-Status");
    this.zzJB = 0;
    if (paramMap == null) {}
    String str;
    do
    {
      return;
      while (!paramMap.hasNext()) {
        paramMap = paramMap.iterator();
      }
      str = (String)paramMap.next();
      if ("Clear".equalsIgnoreCase(str))
      {
        this.zzJB = 1;
        return;
      }
    } while (!"No-Op".equalsIgnoreCase(str));
    this.zzJB = 0;
  }
  
  private void zzz(Map<String, List<String>> paramMap)
  {
    paramMap = (List)paramMap.get("X-Afma-Gws-Query-Id");
    if ((paramMap != null) && (!paramMap.isEmpty())) {
      this.zzJC = ((String)paramMap.get(0));
    }
  }
  
  public void zzb(String paramString1, Map<String, List<String>> paramMap, String paramString2)
  {
    this.zzJo = paramString1;
    this.zzxY = paramString2;
    zzj(paramMap);
  }
  
  public AdResponseParcel zzj(long paramLong)
  {
    return new AdResponseParcel(this.zzCu, this.zzJo, this.zzxY, this.zzJp, this.zzJs, this.zzJt, this.zzJu, -1L, this.zzGK, this.zzJw, this.mOrientation, this.zzJn, paramLong, this.zzJq, this.zzJr, this.zzJx, this.zzJy, this.zzJz, this.zzJA, false, this.zzJB, this.zzJC, this.zzJD, this.zzuS, this.zzJE, this.zzJF, this.zzJG, this.zzJH);
  }
  
  public void zzj(Map<String, List<String>> paramMap)
  {
    zzk(paramMap);
    zzl(paramMap);
    zzm(paramMap);
    zzn(paramMap);
    zzo(paramMap);
    zzs(paramMap);
    zzt(paramMap);
    zzu(paramMap);
    zzv(paramMap);
    zzp(paramMap);
    zzw(paramMap);
    zzr(paramMap);
    zzq(paramMap);
    zzx(paramMap);
    zzy(paramMap);
    zzz(paramMap);
    zzA(paramMap);
    zzB(paramMap);
    zzC(paramMap);
    zzD(paramMap);
    zzE(paramMap);
    zzF(paramMap);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzhg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */