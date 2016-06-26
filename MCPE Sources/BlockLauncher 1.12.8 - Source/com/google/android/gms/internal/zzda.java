package com.google.android.gms.internal;

import java.util.Map;

@zzhb
public final class zzda
  implements zzdf
{
  private final zzdb zzyW;
  
  public zzda(zzdb paramzzdb)
  {
    this.zzyW = paramzzdb;
  }
  
  public void zza(zzjp paramzzjp, Map<String, String> paramMap)
  {
    paramzzjp = (String)paramMap.get("name");
    if (paramzzjp == null)
    {
      zzin.zzaK("App event with no name parameter.");
      return;
    }
    this.zzyW.onAppEvent(paramzzjp, (String)paramMap.get("info"));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzda.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */