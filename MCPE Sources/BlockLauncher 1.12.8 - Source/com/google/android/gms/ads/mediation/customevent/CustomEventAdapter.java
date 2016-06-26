package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.internal.zzin;

@KeepName
public final class CustomEventAdapter
  implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter
{
  CustomEventBanner zzOq;
  CustomEventInterstitial zzOr;
  CustomEventNative zzOs;
  private View zzbk;
  
  private void zza(View paramView)
  {
    this.zzbk = paramView;
  }
  
  private static <T> T zzj(String paramString)
  {
    try
    {
      Object localObject = Class.forName(paramString).newInstance();
      return (T)localObject;
    }
    catch (Throwable localThrowable)
    {
      zzin.zzaK("Could not instantiate custom event adapter: " + paramString + ". " + localThrowable.getMessage());
    }
    return null;
  }
  
  public View getBannerView()
  {
    return this.zzbk;
  }
  
  public void onDestroy()
  {
    if (this.zzOq != null) {
      this.zzOq.onDestroy();
    }
    if (this.zzOr != null) {
      this.zzOr.onDestroy();
    }
    if (this.zzOs != null) {
      this.zzOs.onDestroy();
    }
  }
  
  public void onPause()
  {
    if (this.zzOq != null) {
      this.zzOq.onPause();
    }
    if (this.zzOr != null) {
      this.zzOr.onPause();
    }
    if (this.zzOs != null) {
      this.zzOs.onPause();
    }
  }
  
  public void onResume()
  {
    if (this.zzOq != null) {
      this.zzOq.onResume();
    }
    if (this.zzOr != null) {
      this.zzOr.onResume();
    }
    if (this.zzOs != null) {
      this.zzOs.onResume();
    }
  }
  
  public void requestBannerAd(Context paramContext, MediationBannerListener paramMediationBannerListener, Bundle paramBundle1, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, Bundle paramBundle2)
  {
    this.zzOq = ((CustomEventBanner)zzj(paramBundle1.getString("class_name")));
    if (this.zzOq == null)
    {
      paramMediationBannerListener.onAdFailedToLoad(this, 0);
      return;
    }
    if (paramBundle2 == null) {}
    for (paramBundle2 = null;; paramBundle2 = paramBundle2.getBundle(paramBundle1.getString("class_name")))
    {
      this.zzOq.requestBannerAd(paramContext, new zza(this, paramMediationBannerListener), paramBundle1.getString("parameter"), paramAdSize, paramMediationAdRequest, paramBundle2);
      return;
    }
  }
  
  public void requestInterstitialAd(Context paramContext, MediationInterstitialListener paramMediationInterstitialListener, Bundle paramBundle1, MediationAdRequest paramMediationAdRequest, Bundle paramBundle2)
  {
    this.zzOr = ((CustomEventInterstitial)zzj(paramBundle1.getString("class_name")));
    if (this.zzOr == null)
    {
      paramMediationInterstitialListener.onAdFailedToLoad(this, 0);
      return;
    }
    if (paramBundle2 == null) {}
    for (paramBundle2 = null;; paramBundle2 = paramBundle2.getBundle(paramBundle1.getString("class_name")))
    {
      this.zzOr.requestInterstitialAd(paramContext, zza(paramMediationInterstitialListener), paramBundle1.getString("parameter"), paramMediationAdRequest, paramBundle2);
      return;
    }
  }
  
  public void requestNativeAd(Context paramContext, MediationNativeListener paramMediationNativeListener, Bundle paramBundle1, NativeMediationAdRequest paramNativeMediationAdRequest, Bundle paramBundle2)
  {
    this.zzOs = ((CustomEventNative)zzj(paramBundle1.getString("class_name")));
    if (this.zzOs == null)
    {
      paramMediationNativeListener.onAdFailedToLoad(this, 0);
      return;
    }
    if (paramBundle2 == null) {}
    for (paramBundle2 = null;; paramBundle2 = paramBundle2.getBundle(paramBundle1.getString("class_name")))
    {
      this.zzOs.requestNativeAd(paramContext, new zzc(this, paramMediationNativeListener), paramBundle1.getString("parameter"), paramNativeMediationAdRequest, paramBundle2);
      return;
    }
  }
  
  public void showInterstitial()
  {
    this.zzOr.showInterstitial();
  }
  
  zzb zza(MediationInterstitialListener paramMediationInterstitialListener)
  {
    return new zzb(this, paramMediationInterstitialListener);
  }
  
  static final class zza
    implements CustomEventBannerListener
  {
    private final CustomEventAdapter zzOt;
    private final MediationBannerListener zzbc;
    
    public zza(CustomEventAdapter paramCustomEventAdapter, MediationBannerListener paramMediationBannerListener)
    {
      this.zzOt = paramCustomEventAdapter;
      this.zzbc = paramMediationBannerListener;
    }
    
    public void onAdClicked()
    {
      zzin.zzaI("Custom event adapter called onAdClicked.");
      this.zzbc.onAdClicked(this.zzOt);
    }
    
    public void onAdClosed()
    {
      zzin.zzaI("Custom event adapter called onAdClosed.");
      this.zzbc.onAdClosed(this.zzOt);
    }
    
    public void onAdFailedToLoad(int paramInt)
    {
      zzin.zzaI("Custom event adapter called onAdFailedToLoad.");
      this.zzbc.onAdFailedToLoad(this.zzOt, paramInt);
    }
    
    public void onAdLeftApplication()
    {
      zzin.zzaI("Custom event adapter called onAdLeftApplication.");
      this.zzbc.onAdLeftApplication(this.zzOt);
    }
    
    public void onAdLoaded(View paramView)
    {
      zzin.zzaI("Custom event adapter called onAdLoaded.");
      CustomEventAdapter.zza(this.zzOt, paramView);
      this.zzbc.onAdLoaded(this.zzOt);
    }
    
    public void onAdOpened()
    {
      zzin.zzaI("Custom event adapter called onAdOpened.");
      this.zzbc.onAdOpened(this.zzOt);
    }
  }
  
  class zzb
    implements CustomEventInterstitialListener
  {
    private final CustomEventAdapter zzOt;
    private final MediationInterstitialListener zzbd;
    
    public zzb(CustomEventAdapter paramCustomEventAdapter, MediationInterstitialListener paramMediationInterstitialListener)
    {
      this.zzOt = paramCustomEventAdapter;
      this.zzbd = paramMediationInterstitialListener;
    }
    
    public void onAdClicked()
    {
      zzin.zzaI("Custom event adapter called onAdClicked.");
      this.zzbd.onAdClicked(this.zzOt);
    }
    
    public void onAdClosed()
    {
      zzin.zzaI("Custom event adapter called onAdClosed.");
      this.zzbd.onAdClosed(this.zzOt);
    }
    
    public void onAdFailedToLoad(int paramInt)
    {
      zzin.zzaI("Custom event adapter called onFailedToReceiveAd.");
      this.zzbd.onAdFailedToLoad(this.zzOt, paramInt);
    }
    
    public void onAdLeftApplication()
    {
      zzin.zzaI("Custom event adapter called onAdLeftApplication.");
      this.zzbd.onAdLeftApplication(this.zzOt);
    }
    
    public void onAdLoaded()
    {
      zzin.zzaI("Custom event adapter called onReceivedAd.");
      this.zzbd.onAdLoaded(CustomEventAdapter.this);
    }
    
    public void onAdOpened()
    {
      zzin.zzaI("Custom event adapter called onAdOpened.");
      this.zzbd.onAdOpened(this.zzOt);
    }
  }
  
  static class zzc
    implements CustomEventNativeListener
  {
    private final CustomEventAdapter zzOt;
    private final MediationNativeListener zzbe;
    
    public zzc(CustomEventAdapter paramCustomEventAdapter, MediationNativeListener paramMediationNativeListener)
    {
      this.zzOt = paramCustomEventAdapter;
      this.zzbe = paramMediationNativeListener;
    }
    
    public void onAdClicked()
    {
      zzin.zzaI("Custom event adapter called onAdClicked.");
      this.zzbe.onAdClicked(this.zzOt);
    }
    
    public void onAdClosed()
    {
      zzin.zzaI("Custom event adapter called onAdClosed.");
      this.zzbe.onAdClosed(this.zzOt);
    }
    
    public void onAdFailedToLoad(int paramInt)
    {
      zzin.zzaI("Custom event adapter called onAdFailedToLoad.");
      this.zzbe.onAdFailedToLoad(this.zzOt, paramInt);
    }
    
    public void onAdLeftApplication()
    {
      zzin.zzaI("Custom event adapter called onAdLeftApplication.");
      this.zzbe.onAdLeftApplication(this.zzOt);
    }
    
    public void onAdLoaded(NativeAdMapper paramNativeAdMapper)
    {
      zzin.zzaI("Custom event adapter called onAdLoaded.");
      this.zzbe.onAdLoaded(this.zzOt, paramNativeAdMapper);
    }
    
    public void onAdOpened()
    {
      zzin.zzaI("Custom event adapter called onAdOpened.");
      this.zzbe.onAdOpened(this.zzOt);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\mediation\customevent\CustomEventAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */