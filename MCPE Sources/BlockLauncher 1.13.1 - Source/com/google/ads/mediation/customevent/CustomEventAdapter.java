package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.internal.zzin;

@KeepName
public final class CustomEventAdapter
  implements MediationBannerAdapter<CustomEventExtras, CustomEventServerParameters>, MediationInterstitialAdapter<CustomEventExtras, CustomEventServerParameters>
{
  private View zzbk;
  CustomEventBanner zzbl;
  CustomEventInterstitial zzbm;
  
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
  
  public void destroy()
  {
    if (this.zzbl != null) {
      this.zzbl.destroy();
    }
    if (this.zzbm != null) {
      this.zzbm.destroy();
    }
  }
  
  public Class<CustomEventExtras> getAdditionalParametersType()
  {
    return CustomEventExtras.class;
  }
  
  public View getBannerView()
  {
    return this.zzbk;
  }
  
  public Class<CustomEventServerParameters> getServerParametersType()
  {
    return CustomEventServerParameters.class;
  }
  
  public void requestBannerAd(MediationBannerListener paramMediationBannerListener, Activity paramActivity, CustomEventServerParameters paramCustomEventServerParameters, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, CustomEventExtras paramCustomEventExtras)
  {
    this.zzbl = ((CustomEventBanner)zzj(paramCustomEventServerParameters.className));
    if (this.zzbl == null)
    {
      paramMediationBannerListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
      return;
    }
    if (paramCustomEventExtras == null) {}
    for (paramCustomEventExtras = null;; paramCustomEventExtras = paramCustomEventExtras.getExtra(paramCustomEventServerParameters.label))
    {
      this.zzbl.requestBannerAd(new zza(this, paramMediationBannerListener), paramActivity, paramCustomEventServerParameters.label, paramCustomEventServerParameters.parameter, paramAdSize, paramMediationAdRequest, paramCustomEventExtras);
      return;
    }
  }
  
  public void requestInterstitialAd(MediationInterstitialListener paramMediationInterstitialListener, Activity paramActivity, CustomEventServerParameters paramCustomEventServerParameters, MediationAdRequest paramMediationAdRequest, CustomEventExtras paramCustomEventExtras)
  {
    this.zzbm = ((CustomEventInterstitial)zzj(paramCustomEventServerParameters.className));
    if (this.zzbm == null)
    {
      paramMediationInterstitialListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
      return;
    }
    if (paramCustomEventExtras == null) {}
    for (paramCustomEventExtras = null;; paramCustomEventExtras = paramCustomEventExtras.getExtra(paramCustomEventServerParameters.label))
    {
      this.zzbm.requestInterstitialAd(zza(paramMediationInterstitialListener), paramActivity, paramCustomEventServerParameters.label, paramCustomEventServerParameters.parameter, paramMediationAdRequest, paramCustomEventExtras);
      return;
    }
  }
  
  public void showInterstitial()
  {
    this.zzbm.showInterstitial();
  }
  
  zzb zza(MediationInterstitialListener paramMediationInterstitialListener)
  {
    return new zzb(this, paramMediationInterstitialListener);
  }
  
  static final class zza
    implements CustomEventBannerListener
  {
    private final CustomEventAdapter zzbn;
    private final MediationBannerListener zzbo;
    
    public zza(CustomEventAdapter paramCustomEventAdapter, MediationBannerListener paramMediationBannerListener)
    {
      this.zzbn = paramCustomEventAdapter;
      this.zzbo = paramMediationBannerListener;
    }
    
    public void onClick()
    {
      zzin.zzaI("Custom event adapter called onFailedToReceiveAd.");
      this.zzbo.onClick(this.zzbn);
    }
    
    public void onDismissScreen()
    {
      zzin.zzaI("Custom event adapter called onFailedToReceiveAd.");
      this.zzbo.onDismissScreen(this.zzbn);
    }
    
    public void onFailedToReceiveAd()
    {
      zzin.zzaI("Custom event adapter called onFailedToReceiveAd.");
      this.zzbo.onFailedToReceiveAd(this.zzbn, AdRequest.ErrorCode.NO_FILL);
    }
    
    public void onLeaveApplication()
    {
      zzin.zzaI("Custom event adapter called onFailedToReceiveAd.");
      this.zzbo.onLeaveApplication(this.zzbn);
    }
    
    public void onPresentScreen()
    {
      zzin.zzaI("Custom event adapter called onFailedToReceiveAd.");
      this.zzbo.onPresentScreen(this.zzbn);
    }
    
    public void onReceivedAd(View paramView)
    {
      zzin.zzaI("Custom event adapter called onReceivedAd.");
      CustomEventAdapter.zza(this.zzbn, paramView);
      this.zzbo.onReceivedAd(this.zzbn);
    }
  }
  
  class zzb
    implements CustomEventInterstitialListener
  {
    private final CustomEventAdapter zzbn;
    private final MediationInterstitialListener zzbp;
    
    public zzb(CustomEventAdapter paramCustomEventAdapter, MediationInterstitialListener paramMediationInterstitialListener)
    {
      this.zzbn = paramCustomEventAdapter;
      this.zzbp = paramMediationInterstitialListener;
    }
    
    public void onDismissScreen()
    {
      zzin.zzaI("Custom event adapter called onDismissScreen.");
      this.zzbp.onDismissScreen(this.zzbn);
    }
    
    public void onFailedToReceiveAd()
    {
      zzin.zzaI("Custom event adapter called onFailedToReceiveAd.");
      this.zzbp.onFailedToReceiveAd(this.zzbn, AdRequest.ErrorCode.NO_FILL);
    }
    
    public void onLeaveApplication()
    {
      zzin.zzaI("Custom event adapter called onLeaveApplication.");
      this.zzbp.onLeaveApplication(this.zzbn);
    }
    
    public void onPresentScreen()
    {
      zzin.zzaI("Custom event adapter called onPresentScreen.");
      this.zzbp.onPresentScreen(this.zzbn);
    }
    
    public void onReceivedAd()
    {
      zzin.zzaI("Custom event adapter called onReceivedAd.");
      this.zzbp.onReceivedAd(CustomEventAdapter.this);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\ads\mediation\customevent\CustomEventAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */