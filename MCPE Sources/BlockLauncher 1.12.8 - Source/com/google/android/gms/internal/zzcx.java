package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;

@zzhb
public class zzcx
  extends zzcs.zza
{
  private final NativeContentAd.OnContentAdLoadedListener zzyT;
  
  public zzcx(NativeContentAd.OnContentAdLoadedListener paramOnContentAdLoadedListener)
  {
    this.zzyT = paramOnContentAdLoadedListener;
  }
  
  public void zza(zzcn paramzzcn)
  {
    this.zzyT.onContentAdLoaded(zzb(paramzzcn));
  }
  
  zzco zzb(zzcn paramzzcn)
  {
    return new zzco(paramzzcn);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzcx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */