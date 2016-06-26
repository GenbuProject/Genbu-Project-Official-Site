package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class zzce
  extends zzak
{
  private static final String ID = zzad.zzcf.toString();
  private static final String zzbka = zzae.zzdV.toString();
  private static final String zzbkb = zzae.zzdW.toString();
  private static final String zzbkc = zzae.zzfO.toString();
  private static final String zzbkd = zzae.zzfI.toString();
  
  public zzce()
  {
    super(ID, new String[] { zzbka, zzbkb });
  }
  
  public boolean zzFW()
  {
    return true;
  }
  
  public zzag.zza zzP(Map<String, zzag.zza> paramMap)
  {
    Object localObject = (zzag.zza)paramMap.get(zzbka);
    zzag.zza localzza = (zzag.zza)paramMap.get(zzbkb);
    if ((localObject == null) || (localObject == zzdf.zzHF()) || (localzza == null) || (localzza == zzdf.zzHF())) {
      return zzdf.zzHF();
    }
    int i = 64;
    if (zzdf.zzk((zzag.zza)paramMap.get(zzbkc)).booleanValue()) {
      i = 66;
    }
    paramMap = (zzag.zza)paramMap.get(zzbkd);
    int j;
    if (paramMap != null)
    {
      paramMap = zzdf.zzi(paramMap);
      if (paramMap == zzdf.zzHA()) {
        return zzdf.zzHF();
      }
      int k = paramMap.intValue();
      j = k;
      if (k < 0) {
        return zzdf.zzHF();
      }
    }
    else
    {
      j = 1;
    }
    try
    {
      paramMap = zzdf.zzg((zzag.zza)localObject);
      localObject = zzdf.zzg(localzza);
      localzza = null;
      localObject = Pattern.compile((String)localObject, i).matcher(paramMap);
      paramMap = localzza;
      if (((Matcher)localObject).find())
      {
        paramMap = localzza;
        if (((Matcher)localObject).groupCount() >= j) {
          paramMap = ((Matcher)localObject).group(j);
        }
      }
      if (paramMap == null) {
        return zzdf.zzHF();
      }
      paramMap = zzdf.zzR(paramMap);
      return paramMap;
    }
    catch (PatternSyntaxException paramMap) {}
    return zzdf.zzHF();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzce.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */