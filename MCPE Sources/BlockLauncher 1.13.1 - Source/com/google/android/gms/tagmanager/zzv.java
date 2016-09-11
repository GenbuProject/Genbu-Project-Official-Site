package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzv
  extends zzak
{
  private static final String ID = zzad.zzbz.toString();
  private static final String NAME = zzae.zzgo.toString();
  private static final String zzbiA = zzae.zzeY.toString();
  private final DataLayer zzbhN;
  
  public zzv(DataLayer paramDataLayer)
  {
    super(ID, new String[] { NAME });
    this.zzbhN = paramDataLayer;
  }
  
  public boolean zzFW()
  {
    return false;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    Object localObject = this.zzbhN.get(zzdf.zzg((zzag.zza)paramMap.get(NAME)));
    if (localObject == null)
    {
      paramMap = (zzag.zza)paramMap.get(zzbiA);
      if (paramMap != null) {
        return paramMap;
      }
      return zzdf.zzHF();
    }
    return zzdf.zzR(localObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */