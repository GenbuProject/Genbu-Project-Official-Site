package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.common.internal.zzx;

@zzhb
public final class zzff
  implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener
{
  private final zzez zzCK;
  private NativeAdMapper zzCL;
  
  public zzff(zzez paramzzez)
  {
    this.zzCK = paramzzez;
  }
  
  public void onAdClicked(MediationBannerAdapter paramMediationBannerAdapter)
  {
    zzx.zzcD("onAdClicked must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdClicked.");
    try
    {
      this.zzCK.onAdClicked();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdClicked.", paramMediationBannerAdapter);
    }
  }
  
  public void onAdClicked(MediationInterstitialAdapter paramMediationInterstitialAdapter)
  {
    zzx.zzcD("onAdClicked must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdClicked.");
    try
    {
      this.zzCK.onAdClicked();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdClicked.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onAdClicked(MediationNativeAdapter paramMediationNativeAdapter)
  {
    zzx.zzcD("onAdClicked must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdClicked.");
    try
    {
      this.zzCK.onAdClicked();
      return;
    }
    catch (RemoteException paramMediationNativeAdapter)
    {
      zzb.zzd("Could not call onAdClicked.", paramMediationNativeAdapter);
    }
  }
  
  public void onAdClosed(MediationBannerAdapter paramMediationBannerAdapter)
  {
    zzx.zzcD("onAdClosed must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdClosed.");
    try
    {
      this.zzCK.onAdClosed();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdClosed.", paramMediationBannerAdapter);
    }
  }
  
  public void onAdClosed(MediationInterstitialAdapter paramMediationInterstitialAdapter)
  {
    zzx.zzcD("onAdClosed must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdClosed.");
    try
    {
      this.zzCK.onAdClosed();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdClosed.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onAdClosed(MediationNativeAdapter paramMediationNativeAdapter)
  {
    zzx.zzcD("onAdClosed must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdClosed.");
    try
    {
      this.zzCK.onAdClosed();
      return;
    }
    catch (RemoteException paramMediationNativeAdapter)
    {
      zzb.zzd("Could not call onAdClosed.", paramMediationNativeAdapter);
    }
  }
  
  public void onAdFailedToLoad(MediationBannerAdapter paramMediationBannerAdapter, int paramInt)
  {
    zzx.zzcD("onAdFailedToLoad must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdFailedToLoad with error. " + paramInt);
    try
    {
      this.zzCK.onAdFailedToLoad(paramInt);
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdFailedToLoad.", paramMediationBannerAdapter);
    }
  }
  
  public void onAdFailedToLoad(MediationInterstitialAdapter paramMediationInterstitialAdapter, int paramInt)
  {
    zzx.zzcD("onAdFailedToLoad must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdFailedToLoad with error " + paramInt + ".");
    try
    {
      this.zzCK.onAdFailedToLoad(paramInt);
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdFailedToLoad.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onAdFailedToLoad(MediationNativeAdapter paramMediationNativeAdapter, int paramInt)
  {
    zzx.zzcD("onAdFailedToLoad must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdFailedToLoad with error " + paramInt + ".");
    try
    {
      this.zzCK.onAdFailedToLoad(paramInt);
      return;
    }
    catch (RemoteException paramMediationNativeAdapter)
    {
      zzb.zzd("Could not call onAdFailedToLoad.", paramMediationNativeAdapter);
    }
  }
  
  public void onAdLeftApplication(MediationBannerAdapter paramMediationBannerAdapter)
  {
    zzx.zzcD("onAdLeftApplication must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdLeftApplication.");
    try
    {
      this.zzCK.onAdLeftApplication();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdLeftApplication.", paramMediationBannerAdapter);
    }
  }
  
  public void onAdLeftApplication(MediationInterstitialAdapter paramMediationInterstitialAdapter)
  {
    zzx.zzcD("onAdLeftApplication must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdLeftApplication.");
    try
    {
      this.zzCK.onAdLeftApplication();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdLeftApplication.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onAdLeftApplication(MediationNativeAdapter paramMediationNativeAdapter)
  {
    zzx.zzcD("onAdLeftApplication must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdLeftApplication.");
    try
    {
      this.zzCK.onAdLeftApplication();
      return;
    }
    catch (RemoteException paramMediationNativeAdapter)
    {
      zzb.zzd("Could not call onAdLeftApplication.", paramMediationNativeAdapter);
    }
  }
  
  public void onAdLoaded(MediationBannerAdapter paramMediationBannerAdapter)
  {
    zzx.zzcD("onAdLoaded must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdLoaded.");
    try
    {
      this.zzCK.onAdLoaded();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdLoaded.", paramMediationBannerAdapter);
    }
  }
  
  public void onAdLoaded(MediationInterstitialAdapter paramMediationInterstitialAdapter)
  {
    zzx.zzcD("onAdLoaded must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdLoaded.");
    try
    {
      this.zzCK.onAdLoaded();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdLoaded.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onAdLoaded(MediationNativeAdapter paramMediationNativeAdapter, NativeAdMapper paramNativeAdMapper)
  {
    zzx.zzcD("onAdLoaded must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdLoaded.");
    this.zzCL = paramNativeAdMapper;
    try
    {
      this.zzCK.onAdLoaded();
      return;
    }
    catch (RemoteException paramMediationNativeAdapter)
    {
      zzb.zzd("Could not call onAdLoaded.", paramMediationNativeAdapter);
    }
  }
  
  public void onAdOpened(MediationBannerAdapter paramMediationBannerAdapter)
  {
    zzx.zzcD("onAdOpened must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdOpened.");
    try
    {
      this.zzCK.onAdOpened();
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdOpened.", paramMediationBannerAdapter);
    }
  }
  
  public void onAdOpened(MediationInterstitialAdapter paramMediationInterstitialAdapter)
  {
    zzx.zzcD("onAdOpened must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdOpened.");
    try
    {
      this.zzCK.onAdOpened();
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdOpened.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onAdOpened(MediationNativeAdapter paramMediationNativeAdapter)
  {
    zzx.zzcD("onAdOpened must be called on the main UI thread.");
    zzb.zzaI("Adapter called onAdOpened.");
    try
    {
      this.zzCK.onAdOpened();
      return;
    }
    catch (RemoteException paramMediationNativeAdapter)
    {
      zzb.zzd("Could not call onAdOpened.", paramMediationNativeAdapter);
    }
  }
  
  public NativeAdMapper zzeJ()
  {
    return this.zzCL;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzff.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */