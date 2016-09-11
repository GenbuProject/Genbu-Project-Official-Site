package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class zzap
  extends zzak
{
  private static final String ID = zzad.zzcb.toString();
  private static final String zzbiQ = zzae.zzdV.toString();
  private static final String zzbiS = zzae.zzfQ.toString();
  private static final String zzbiW = zzae.zzdL.toString();
  
  public zzap()
  {
    super(ID, new String[] { zzbiQ });
  }
  
  private byte[] zzg(String paramString, byte[] paramArrayOfByte)
    throws NoSuchAlgorithmException
  {
    paramString = MessageDigest.getInstance(paramString);
    paramString.update(paramArrayOfByte);
    return paramString.digest();
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
    String str = zzdf.zzg((zzag.zza)localObject);
    localObject = (zzag.zza)paramMap.get(zzbiW);
    if (localObject == null)
    {
      localObject = "MD5";
      paramMap = (zzag.zza)paramMap.get(zzbiS);
      if (paramMap != null) {
        break label110;
      }
      paramMap = "text";
      label73:
      if (!"text".equals(paramMap)) {
        break label118;
      }
      paramMap = str.getBytes();
    }
    for (;;)
    {
      try
      {
        paramMap = zzdf.zzR(zzk.zzj(zzg((String)localObject, paramMap)));
        return paramMap;
      }
      catch (NoSuchAlgorithmException paramMap)
      {
        label110:
        label118:
        zzbg.e("Hash: unknown algorithm: " + (String)localObject);
      }
      localObject = zzdf.zzg((zzag.zza)localObject);
      break;
      paramMap = zzdf.zzg(paramMap);
      break label73;
      if ("base16".equals(paramMap))
      {
        paramMap = zzk.zzfO(str);
      }
      else
      {
        zzbg.e("Hash: unknown input format: " + paramMap);
        return zzdf.zzHF();
      }
    }
    return zzdf.zzHF();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */