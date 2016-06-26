package com.google.android.gms.ads.internal.client;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.ClientApi;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzcj;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzfv;
import com.google.android.gms.internal.zzge;
import com.google.android.gms.internal.zzhb;

@zzhb
public class zzl
{
  public static String zzuq = null;
  private zzm zzup;
  
  public zzl()
  {
    ClientApi.retainReference();
    if (zzuq != null) {
      try
      {
        this.zzup = ((zzm)zzl.class.getClassLoader().loadClass(zzuq).newInstance());
        return;
      }
      catch (Exception localException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to instantiate ClientApi class.", localException);
        this.zzup = new zzai();
        return;
      }
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaK("No client jar implementation found.");
    this.zzup = new zzai();
  }
  
  public zzs createAdLoaderBuilder(Context paramContext, String paramString, zzew paramzzew, VersionInfoParcel paramVersionInfoParcel)
  {
    return this.zzup.createAdLoaderBuilder(paramContext, paramString, paramzzew, paramVersionInfoParcel);
  }
  
  @Nullable
  public zzfv createAdOverlay(Activity paramActivity)
  {
    return this.zzup.createAdOverlay(paramActivity);
  }
  
  public zzu createBannerAdManager(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzew paramzzew, VersionInfoParcel paramVersionInfoParcel)
  {
    return this.zzup.createBannerAdManager(paramContext, paramAdSizeParcel, paramString, paramzzew, paramVersionInfoParcel);
  }
  
  @Nullable
  public zzge createInAppPurchaseManager(Activity paramActivity)
  {
    return this.zzup.createInAppPurchaseManager(paramActivity);
  }
  
  public zzu createInterstitialAdManager(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzew paramzzew, VersionInfoParcel paramVersionInfoParcel)
  {
    return this.zzup.createInterstitialAdManager(paramContext, paramAdSizeParcel, paramString, paramzzew, paramVersionInfoParcel);
  }
  
  public zzcj createNativeAdViewDelegate(FrameLayout paramFrameLayout1, FrameLayout paramFrameLayout2)
  {
    return this.zzup.createNativeAdViewDelegate(paramFrameLayout1, paramFrameLayout2);
  }
  
  public com.google.android.gms.ads.internal.reward.client.zzb createRewardedVideoAd(Context paramContext, zzew paramzzew, VersionInfoParcel paramVersionInfoParcel)
  {
    return this.zzup.createRewardedVideoAd(paramContext, paramzzew, paramVersionInfoParcel);
  }
  
  public zzy getMobileAdsSettingsManager(Context paramContext)
  {
    return this.zzup.getMobileAdsSettingsManager(paramContext);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\client\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */