package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAdOptions;

public abstract interface NativeMediationAdRequest
  extends MediationAdRequest
{
  public abstract NativeAdOptions getNativeAdOptions();
  
  public abstract boolean isAppInstallAdRequested();
  
  public abstract boolean isContentAdRequested();
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\mediation\NativeMediationAdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */