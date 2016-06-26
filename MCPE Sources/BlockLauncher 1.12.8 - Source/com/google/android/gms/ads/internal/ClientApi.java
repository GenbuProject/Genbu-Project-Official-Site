package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.client.zzs;
import com.google.android.gms.ads.internal.client.zzu;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.purchase.zze;
import com.google.android.gms.ads.internal.reward.client.zzb;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzcj;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzfv;
import com.google.android.gms.internal.zzge;
import com.google.android.gms.internal.zzhs;

public class ClientApi
  implements zzm
{
  public static void retainReference()
  {
    com.google.android.gms.ads.internal.client.zzl.zzuq = ClientApi.class.getName();
  }
  
  public zzs createAdLoaderBuilder(Context paramContext, String paramString, zzew paramzzew, VersionInfoParcel paramVersionInfoParcel)
  {
    return new zzj(paramContext, paramString, paramzzew, paramVersionInfoParcel, zzd.zzbg());
  }
  
  public zzfv createAdOverlay(Activity paramActivity)
  {
    return new com.google.android.gms.ads.internal.overlay.zzd(paramActivity);
  }
  
  public zzu createBannerAdManager(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzew paramzzew, VersionInfoParcel paramVersionInfoParcel)
  {
    return new zzf(paramContext, paramAdSizeParcel, paramString, paramzzew, paramVersionInfoParcel, zzd.zzbg());
  }
  
  public zzge createInAppPurchaseManager(Activity paramActivity)
  {
    return new zze(paramActivity);
  }
  
  public zzu createInterstitialAdManager(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzew paramzzew, VersionInfoParcel paramVersionInfoParcel)
  {
    if (((Boolean)zzbt.zzwE.get()).booleanValue()) {
      return new zzeb(paramContext, paramString, paramzzew, paramVersionInfoParcel, zzd.zzbg());
    }
    return new zzk(paramContext, paramAdSizeParcel, paramString, paramzzew, paramVersionInfoParcel, zzd.zzbg());
  }
  
  public zzcj createNativeAdViewDelegate(FrameLayout paramFrameLayout1, FrameLayout paramFrameLayout2)
  {
    return new com.google.android.gms.ads.internal.formats.zzk(paramFrameLayout1, paramFrameLayout2);
  }
  
  public zzb createRewardedVideoAd(Context paramContext, zzew paramzzew, VersionInfoParcel paramVersionInfoParcel)
  {
    return new zzhs(paramContext, zzd.zzbg(), paramzzew, paramVersionInfoParcel);
  }
  
  public zzy getMobileAdsSettingsManager(Context paramContext)
  {
    return zzn.zzr(paramContext);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\ClientApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */