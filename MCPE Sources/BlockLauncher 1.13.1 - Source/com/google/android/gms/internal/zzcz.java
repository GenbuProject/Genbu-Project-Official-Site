package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;

@zzhb
public class zzcz
  extends zzcu.zza
{
  private final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzyV;
  
  public zzcz(NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener paramOnCustomTemplateAdLoadedListener)
  {
    this.zzyV = paramOnCustomTemplateAdLoadedListener;
  }
  
  public void zza(zzcp paramzzcp)
  {
    this.zzyV.onCustomTemplateAdLoaded(new zzcq(paramzzcp));
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzcz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */