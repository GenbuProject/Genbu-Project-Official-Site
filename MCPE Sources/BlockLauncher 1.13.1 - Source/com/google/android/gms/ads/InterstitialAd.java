package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.client.zzac;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public final class InterstitialAd
{
  private final zzac zzoL;
  
  public InterstitialAd(Context paramContext)
  {
    this.zzoL = new zzac(paramContext);
  }
  
  public AdListener getAdListener()
  {
    return this.zzoL.getAdListener();
  }
  
  public String getAdUnitId()
  {
    return this.zzoL.getAdUnitId();
  }
  
  public InAppPurchaseListener getInAppPurchaseListener()
  {
    return this.zzoL.getInAppPurchaseListener();
  }
  
  public String getMediationAdapterClassName()
  {
    return this.zzoL.getMediationAdapterClassName();
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
  public void loadAd(AdRequest paramAdRequest)
  {
    this.zzoL.zza(paramAdRequest.zzaE());
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    this.zzoL.setAdListener(paramAdListener);
    if ((paramAdListener != null) && ((paramAdListener instanceof zza))) {
      this.zzoL.zza((zza)paramAdListener);
    }
    while (paramAdListener != null) {
      return;
    }
    this.zzoL.zza(null);
  }
  
  public void setAdUnitId(String paramString)
  {
    this.zzoL.setAdUnitId(paramString);
  }
  
  public void setInAppPurchaseListener(InAppPurchaseListener paramInAppPurchaseListener)
  {
    this.zzoL.setInAppPurchaseListener(paramInAppPurchaseListener);
  }
  
  public void setPlayStorePurchaseParams(PlayStorePurchaseListener paramPlayStorePurchaseListener, String paramString)
  {
    this.zzoL.setPlayStorePurchaseParams(paramPlayStorePurchaseListener, paramString);
  }
  
  public void setRewardedVideoAdListener(RewardedVideoAdListener paramRewardedVideoAdListener)
  {
    this.zzoL.setRewardedVideoAdListener(paramRewardedVideoAdListener);
  }
  
  public void show()
  {
    this.zzoL.show();
  }
  
  public void zza(boolean paramBoolean)
  {
    this.zzoL.zza(paramBoolean);
  }
  
  public void zzm(String paramString)
  {
    this.zzoL.setUserId(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\InterstitialAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */