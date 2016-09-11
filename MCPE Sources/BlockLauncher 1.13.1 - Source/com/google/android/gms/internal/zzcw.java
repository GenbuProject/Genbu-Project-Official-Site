package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;

@zzhb
public class zzcw
  extends zzcr.zza
{
  private final NativeAppInstallAd.OnAppInstallAdLoadedListener zzyS;
  
  public zzcw(NativeAppInstallAd.OnAppInstallAdLoadedListener paramOnAppInstallAdLoadedListener)
  {
    this.zzyS = paramOnAppInstallAdLoadedListener;
  }
  
  public void zza(zzcl paramzzcl)
  {
    this.zzyS.onAppInstallAdLoaded(zzb(paramzzcl));
  }
  
  zzcm zzb(zzcl paramzzcl)
  {
    return new zzcm(paramzzcl);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzcw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */