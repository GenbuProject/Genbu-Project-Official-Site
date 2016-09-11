package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag.zza;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class zzdj
{
  private static zzbw<zzag.zza> zza(zzbw<zzag.zza> paramzzbw)
  {
    try
    {
      zzbw localzzbw = new zzbw(zzdf.zzR(zzgA(zzdf.zzg((zzag.zza)paramzzbw.getObject()))), paramzzbw.zzGP());
      return localzzbw;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      zzbg.zzb("Escape URI: unsupported encoding", localUnsupportedEncodingException);
    }
    return paramzzbw;
  }
  
  private static zzbw<zzag.zza> zza(zzbw<zzag.zza> paramzzbw, int paramInt)
  {
    if (!zzn((zzag.zza)paramzzbw.getObject()))
    {
      zzbg.e("Escaping can only be applied to strings.");
      return paramzzbw;
    }
    switch (paramInt)
    {
    default: 
      zzbg.e("Unsupported Value Escaping: " + paramInt);
      return paramzzbw;
    }
    return zza(paramzzbw);
  }
  
  static zzbw<zzag.zza> zza(zzbw<zzag.zza> paramzzbw, int... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramzzbw = zza(paramzzbw, paramVarArgs[i]);
      i += 1;
    }
    return paramzzbw;
  }
  
  static String zzgA(String paramString)
    throws UnsupportedEncodingException
  {
    return URLEncoder.encode(paramString, "UTF-8").replaceAll("\\+", "%20");
  }
  
  private static boolean zzn(zzag.zza paramzza)
  {
    return zzdf.zzl(paramzza) instanceof String;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\tagmanager\zzdj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */