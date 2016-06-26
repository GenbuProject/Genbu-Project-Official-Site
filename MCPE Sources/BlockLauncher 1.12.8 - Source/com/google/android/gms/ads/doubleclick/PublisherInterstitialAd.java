package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.internal.client.zzac;

public final class PublisherInterstitialAd
{
  private final zzac zzoL = new zzac(paramContext, this);
  
  public PublisherInterstitialAd(Context paramContext) {}
  
  public AdListener getAdListener()
  {
    return this.zzoL.getAdListener();
  }
  
  public String getAdUnitId()
  {
    return this.zzoL.getAdUnitId();
  }
  
  public AppEventListener getAppEventListener()
  {
    return this.zzoL.getAppEventListener();
  }
  
  public String getMediationAdapterClassName()
  {
    return this.zzoL.getMediationAdapterClassName();
  }
  
  public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener()
  {
    return this.zzoL.getOnCustomRenderedAdLoadedListener();
  }
  
  public boolean isLoaded()
  {
    return this.zzoL.isLoaded();
  }
  
  public boolean isLoading()
  {
    return this.zzoL.isLoading();
  }
  
  @RequiresPermission("android.permission.INTERNET")
  public void loadAd(PublisherAdRequest paramPublisherAdRequest)
  {
    this.zzoL.zza(paramPublisherAdRequest.zzaE());
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    this.zzoL.setAdListener(paramAdListener);
  }
  
  public void setAdUnitId(String paramString)
  {
    this.zzoL.setAdUnitId(paramString);
  }
  
  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    this.zzoL.setAppEventListener(paramAppEventListener);
  }
  
  public void setCorrelator(Correlator paramCorrelator)
  {
    this.zzoL.setCorrelator(paramCorrelator);
  }
  
  public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener paramOnCustomRenderedAdLoadedListener)
  {
    this.zzoL.setOnCustomRenderedAdLoadedListener(paramOnCustomRenderedAdLoadedListener);
  }
  
  public void show()
  {
    this.zzoL.show();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\doubleclick\PublisherInterstitialAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */