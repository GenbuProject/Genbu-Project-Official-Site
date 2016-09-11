package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzr;
import java.util.Map;

@zzhb
public class zzfo
{
  private final boolean zzDp;
  private final String zzDq;
  private final zzjp zzpD;
  
  public zzfo(zzjp paramzzjp, Map<String, String> paramMap)
  {
    this.zzpD = paramzzjp;
    this.zzDq = ((String)paramMap.get("forceOrientation"));
    if (paramMap.containsKey("allowOrientationChange"))
    {
      this.zzDp = Boolean.parseBoolean((String)paramMap.get("allowOrientationChange"));
      return;
    }
    this.zzDp = true;
  }
  
  public void execute()
  {
    if (this.zzpD == null)
    {
      zzin.zzaK("AdWebView is null");
      return;
    }
    int i;
    if ("portrait".equalsIgnoreCase(this.zzDq)) {
      i = zzr.zzbE().zzhw();
    }
    for (;;)
    {
      this.zzpD.setRequestedOrientation(i);
      return;
      if ("landscape".equalsIgnoreCase(this.zzDq)) {
        i = zzr.zzbE().zzhv();
      } else if (this.zzDp) {
        i = -1;
      } else {
        i = zzr.zzbE().zzhx();
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */