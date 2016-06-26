package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;

class zzad
  extends zzak
{
  private static final String ID = com.google.android.gms.internal.zzad.zzbZ.toString();
  private static final String zzbiQ = zzae.zzdV.toString();
  private static final String zzbiR = zzae.zzgu.toString();
  private static final String zzbiS = zzae.zzfQ.toString();
  private static final String zzbiT = zzae.zzgE.toString();
  
  public zzad()
  {
    super(ID, new String[] { zzbiQ });
  }
  
  public boolean zzFW()
  {
    return true;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    Object localObject = (zzag.zza)paramMap.get(zzbiQ);
    if ((localObject == null) || (localObject == zzdf.zzHF())) {
      return zzdf.zzHF();
    }
    String str2 = zzdf.zzg((zzag.zza)localObject);
    localObject = (zzag.zza)paramMap.get(zzbiS);
    String str1;
    if (localObject == null)
    {
      str1 = "text";
      localObject = (zzag.zza)paramMap.get(zzbiT);
      if (localObject != null) {
        break label148;
      }
      localObject = "base16";
      label75:
      paramMap = (zzag.zza)paramMap.get(zzbiR);
      if ((paramMap == null) || (!zzdf.zzk(paramMap).booleanValue())) {
        break label322;
      }
    }
    label148:
    label257:
    label296:
    label322:
    for (int i = 3;; i = 2)
    {
      for (;;)
      {
        try
        {
          if ("text".equals(str1))
          {
            paramMap = str2.getBytes();
            if (!"base16".equals(localObject)) {
              break label257;
            }
            paramMap = zzk.zzj(paramMap);
            return zzdf.zzR(paramMap);
            str1 = zzdf.zzg((zzag.zza)localObject);
            break;
            localObject = zzdf.zzg((zzag.zza)localObject);
            break label75;
          }
          if ("base16".equals(str1))
          {
            paramMap = zzk.zzfO(str2);
            continue;
          }
          if ("base64".equals(str1))
          {
            paramMap = Base64.decode(str2, i);
            continue;
          }
          if ("base64url".equals(str1))
          {
            paramMap = Base64.decode(str2, i | 0x8);
            continue;
          }
          zzbg.e("Encode: unknown input format: " + str1);
          paramMap = zzdf.zzHF();
          return paramMap;
        }
        catch (IllegalArgumentException paramMap)
        {
          zzbg.e("Encode: invalid input:");
          return zzdf.zzHF();
        }
        if ("base64".equals(localObject))
        {
          paramMap = Base64.encodeToString(paramMap, i);
        }
        else
        {
          if (!"base64url".equals(localObject)) {
            break label296;
          }
          paramMap = Base64.encodeToString(paramMap, i | 0x8);
        }
      }
      zzbg.e("Encode: unknown output format: " + (String)localObject);
      return zzdf.zzHF();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */