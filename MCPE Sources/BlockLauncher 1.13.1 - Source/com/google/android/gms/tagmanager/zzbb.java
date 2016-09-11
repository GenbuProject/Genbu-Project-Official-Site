package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Locale;
import java.util.Map;

public class zzbb
  extends zzak
{
  private static final String ID = zzad.zzbM.toString();
  
  public zzbb()
  {
    super(ID, new String[0]);
  }
  
  public boolean zzFW()
  {
    return false;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    paramMap = Locale.getDefault();
    if (paramMap == null) {
      return zzdf.zzHF();
    }
    paramMap = paramMap.getLanguage();
    if (paramMap == null) {
      return zzdf.zzHF();
    }
    return zzdf.zzR(paramMap.toLowerCase());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzbb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */