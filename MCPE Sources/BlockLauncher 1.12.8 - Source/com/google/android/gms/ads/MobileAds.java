package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.internal.client.zzad;
import com.google.android.gms.ads.internal.client.zzae;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class MobileAds
{
  public static RewardedVideoAd getRewardedVideoAdInstance(Context paramContext)
  {
    return zzad.zzdi().getRewardedVideoAdInstance(paramContext);
  }
  
  public static void initialize(Context paramContext)
  {
    zzad.zzdi().initialize(paramContext);
  }
  
  @Deprecated
  @RequiresPermission("android.permission.INTERNET")
  public static void initialize(Context paramContext, String paramString)
  {
    initialize(paramContext, paramString, null);
  }
  
  @Deprecated
  @RequiresPermission("android.permission.INTERNET")
  public static void initialize(Context paramContext, String paramString, Settings paramSettings)
  {
    zzad localzzad = zzad.zzdi();
    if (paramSettings == null) {}
    for (paramSettings = null;; paramSettings = paramSettings.zzaG())
    {
      localzzad.zza(paramContext, paramString, paramSettings);
      return;
    }
  }
  
  public static void setAppVolume(float paramFloat)
  {
    zzad.zzdi().setAppVolume(paramFloat);
  }
  
  public static final class Settings
  {
    private final zzae zzoM = new zzae();
    
    @Deprecated
    public String getTrackingId()
    {
      return this.zzoM.getTrackingId();
    }
    
    @Deprecated
    public boolean isGoogleAnalyticsEnabled()
    {
      return this.zzoM.isGoogleAnalyticsEnabled();
    }
    
    @Deprecated
    public Settings setGoogleAnalyticsEnabled(boolean paramBoolean)
    {
      this.zzoM.zzm(paramBoolean);
      return this;
    }
    
    @Deprecated
    public Settings setTrackingId(String paramString)
    {
      this.zzoM.zzJ(paramString);
      return this;
    }
    
    zzae zzaG()
    {
      return this.zzoM;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\MobileAds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */