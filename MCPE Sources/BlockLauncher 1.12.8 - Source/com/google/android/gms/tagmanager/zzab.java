package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzab
  extends zzak
{
  private static final String ID = zzad.zzbC.toString();
  
  public zzab()
  {
    super(ID, new String[0]);
  }
  
  public boolean zzFW()
  {
    return true;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    String str2 = Build.MANUFACTURER;
    String str1 = Build.MODEL;
    paramMap = str1;
    if (!str1.startsWith(str2))
    {
      paramMap = str1;
      if (!str2.equals("unknown")) {
        paramMap = str2 + " " + str1;
      }
    }
    return zzdf.zzR(paramMap);
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */