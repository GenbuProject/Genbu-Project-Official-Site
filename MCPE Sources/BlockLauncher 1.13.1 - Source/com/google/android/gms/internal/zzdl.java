package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zze;
import java.util.Map;

@zzhb
public class zzdl
  implements zzdf
{
  static final Map<String, Integer> zzzC = zzmr.zza("resize", Integer.valueOf(1), "playVideo", Integer.valueOf(2), "storePicture", Integer.valueOf(3), "createCalendarEvent", Integer.valueOf(4), "setOrientationProperties", Integer.valueOf(5), "closeResizedAd", Integer.valueOf(6));
  private final zze zzzA;
  private final zzfn zzzB;
  
  public zzdl(zze paramzze, zzfn paramzzfn)
  {
    this.zzzA = paramzze;
    this.zzzB = paramzzfn;
  }
  
  public void zza(zzjp paramzzjp, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("a");
    int i = ((Integer)zzzC.get(str)).intValue();
    if ((i != 5) && (this.zzzA != null) && (!this.zzzA.zzbh()))
    {
      this.zzzA.zzq(null);
      return;
    }
    switch (i)
    {
    case 2: 
    default: 
      zzin.zzaJ("Unknown MRAID command called.");
      return;
    case 1: 
      this.zzzB.zzi(paramMap);
      return;
    case 4: 
      new zzfm(paramzzjp, paramMap).execute();
      return;
    case 3: 
      new zzfp(paramzzjp, paramMap).execute();
      return;
    case 5: 
      new zzfo(paramzzjp, paramMap).execute();
      return;
    }
    this.zzzB.zzp(true);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzdl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */