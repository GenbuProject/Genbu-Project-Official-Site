package com.google.android.gms.ads.internal.reward.client;

import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zzhb;

@zzhb
public class zzg
  extends zzd.zza
{
  private final RewardedVideoAdListener zzaX;
  
  public zzg(RewardedVideoAdListener paramRewardedVideoAdListener)
  {
    this.zzaX = paramRewardedVideoAdListener;
  }
  
  public void onRewardedVideoAdClosed()
  {
    if (this.zzaX != null) {
      this.zzaX.onRewardedVideoAdClosed();
    }
  }
  
  public void onRewardedVideoAdFailedToLoad(int paramInt)
  {
    if (this.zzaX != null) {
      this.zzaX.onRewardedVideoAdFailedToLoad(paramInt);
    }
  }
  
  public void onRewardedVideoAdLeftApplication()
  {
    if (this.zzaX != null) {
      this.zzaX.onRewardedVideoAdLeftApplication();
    }
  }
  
  public void onRewardedVideoAdLoaded()
  {
    if (this.zzaX != null) {
      this.zzaX.onRewardedVideoAdLoaded();
    }
  }
  
  public void onRewardedVideoAdOpened()
  {
    if (this.zzaX != null) {
      this.zzaX.onRewardedVideoAdOpened();
    }
  }
  
  public void onRewardedVideoStarted()
  {
    if (this.zzaX != null) {
      this.zzaX.onRewardedVideoStarted();
    }
  }
  
  public void zza(zza paramzza)
  {
    if (this.zzaX != null) {
      this.zzaX.onRewarded(new zze(paramzza));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\reward\client\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */