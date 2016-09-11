package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzaw
  extends zzak
{
  private static final String ID = zzad.zzcc.toString();
  private static final String zzbhD = zzae.zzex.toString();
  private final Context context;
  
  public zzaw(Context paramContext)
  {
    super(ID, new String[0]);
    this.context = paramContext;
  }
  
  public boolean zzFW()
  {
    return true;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    if ((zzag.zza)paramMap.get(zzbhD) != null) {}
    for (paramMap = zzdf.zzg((zzag.zza)paramMap.get(zzbhD));; paramMap = null)
    {
      paramMap = zzax.zzm(this.context, paramMap);
      if (paramMap == null) {
        break;
      }
      return zzdf.zzR(paramMap);
    }
    return zzdf.zzHF();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzaw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */