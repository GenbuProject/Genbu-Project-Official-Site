package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.internal.reward.client.zzg;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zzcg;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzgi;
import com.google.android.gms.internal.zzgm;
import com.google.android.gms.internal.zzhb;

@zzhb
public class zzac
{
  private final Context mContext;
  private String zzaW;
  private RewardedVideoAdListener zzaX;
  private final zzh zzoB;
  private String zzpS;
  private AdListener zztA;
  private zza zztz;
  private final zzew zzuJ = new zzew();
  private zzu zzuL;
  private String zzuM;
  private InAppPurchaseListener zzuO;
  private PlayStorePurchaseListener zzuP;
  private OnCustomRenderedAdLoadedListener zzuQ;
  private Correlator zzuR;
  private PublisherInterstitialAd zzuT;
  private boolean zzuU;
  private AppEventListener zzun;
  
  public zzac(Context paramContext)
  {
    this(paramContext, zzh.zzcO(), null);
  }
  
  public zzac(Context paramContext, PublisherInterstitialAd paramPublisherInterstitialAd)
  {
    this(paramContext, zzh.zzcO(), paramPublisherInterstitialAd);
  }
  
  public zzac(Context paramContext, zzh paramzzh, PublisherInterstitialAd paramPublisherInterstitialAd)
  {
    this.mContext = paramContext;
    this.zzoB = paramzzh;
    this.zzuT = paramPublisherInterstitialAd;
  }
  
  private void zzH(String paramString)
    throws RemoteException
  {
    if (this.zzpS == null) {
      zzI(paramString);
    }
    if (this.zzuU) {}
    for (paramString = AdSizeParcel.zzcP();; paramString = new AdSizeParcel())
    {
      this.zzuL = zzn.zzcT().zzb(this.mContext, paramString, this.zzpS, this.zzuJ);
      if (this.zztA != null) {
        this.zzuL.zza(new zzc(this.zztA));
      }
      if (this.zztz != null) {
        this.zzuL.zza(new zzb(this.zztz));
      }
      if (this.zzun != null) {
        this.zzuL.zza(new zzj(this.zzun));
      }
      if (this.zzuO != null) {
        this.zzuL.zza(new zzgi(this.zzuO));
      }
      if (this.zzuP != null) {
        this.zzuL.zza(new zzgm(this.zzuP), this.zzuM);
      }
      if (this.zzuQ != null) {
        this.zzuL.zza(new zzcg(this.zzuQ));
      }
      if (this.zzuR != null) {
        this.zzuL.zza(this.zzuR.zzaF());
      }
      if (this.zzaX != null) {
        this.zzuL.zza(new zzg(this.zzaX));
      }
      if (this.zzaW != null) {
        this.zzuL.setUserId(this.zzaW);
      }
      return;
    }
  }
  
  private void zzI(String paramString)
  {
    if (this.zzuL == null) {
      throw new IllegalStateException("The ad unit ID must be set on InterstitialAd before " + paramString + " is called.");
    }
  }
  
  public AdListener getAdListener()
  {
    return this.zztA;
  }
  
  public String getAdUnitId()
  {
    return this.zzpS;
  }
  
  public AppEventListener getAppEventListener()
  {
    return this.zzun;
  }
  
  public InAppPurchaseListener getInAppPurchaseListener()
  {
    return this.zzuO;
  }
  
  public String getMediationAdapterClassName()
  {
    try
    {
      if (this.zzuL != null)
      {
        String str = this.zzuL.getMediationAdapterClassName();
        return str;
      }
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to get the mediation adapter class name.", localRemoteException);
    }
    return null;
  }
  
  public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener()
  {
    return this.zzuQ;
  }
  
  public boolean isLoaded()
  {
    try
    {
      if (this.zzuL == null) {
        return false;
      }
      boolean bool = this.zzuL.isReady();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to check if ad is ready.", localRemoteException);
    }
    return false;
  }
  
  public boolean isLoading()
  {
    try
    {
      if (this.zzuL == null) {
        return false;
      }
      boolean bool = this.zzuL.isLoading();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to check if ad is loading.", localRemoteException);
    }
    return false;
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    try
    {
      this.zztA = paramAdListener;
      zzu localzzu;
      if (this.zzuL != null)
      {
        localzzu = this.zzuL;
        if (paramAdListener == null) {
          break label38;
        }
      }
      label38:
      for (paramAdListener = new zzc(paramAdListener);; paramAdListener = null)
      {
        localzzu.zza(paramAdListener);
        return;
      }
      return;
    }
    catch (RemoteException paramAdListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdListener.", paramAdListener);
    }
  }
  
  public void setAdUnitId(String paramString)
  {
    if (this.zzpS != null) {
      throw new IllegalStateException("The ad unit ID can only be set once on InterstitialAd.");
    }
    this.zzpS = paramString;
  }
  
  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    try
    {
      this.zzun = paramAppEventListener;
      zzu localzzu;
      if (this.zzuL != null)
      {
        localzzu = this.zzuL;
        if (paramAppEventListener == null) {
          break label38;
        }
      }
      label38:
      for (paramAppEventListener = new zzj(paramAppEventListener);; paramAppEventListener = null)
      {
        localzzu.zza(paramAppEventListener);
        return;
      }
      return;
    }
    catch (RemoteException paramAppEventListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AppEventListener.", paramAppEventListener);
    }
  }
  
  public void setCorrelator(Correlator paramCorrelator)
  {
    this.zzuR = paramCorrelator;
    try
    {
      if (this.zzuL != null)
      {
        zzu localzzu = this.zzuL;
        if (this.zzuR == null) {}
        for (paramCorrelator = null;; paramCorrelator = this.zzuR.zzaF())
        {
          localzzu.zza(paramCorrelator);
          return;
        }
      }
      return;
    }
    catch (RemoteException paramCorrelator)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set correlator.", paramCorrelator);
    }
  }
  
  public void setInAppPurchaseListener(InAppPurchaseListener paramInAppPurchaseListener)
  {
    if (this.zzuP != null) {
      throw new IllegalStateException("Play store purchase parameter has already been set.");
    }
    try
    {
      this.zzuO = paramInAppPurchaseListener;
      zzu localzzu;
      if (this.zzuL != null)
      {
        localzzu = this.zzuL;
        if (paramInAppPurchaseListener == null) {
          break label55;
        }
      }
      label55:
      for (paramInAppPurchaseListener = new zzgi(paramInAppPurchaseListener);; paramInAppPurchaseListener = null)
      {
        localzzu.zza(paramInAppPurchaseListener);
        return;
      }
      return;
    }
    catch (RemoteException paramInAppPurchaseListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the InAppPurchaseListener.", paramInAppPurchaseListener);
    }
  }
  
  public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener paramOnCustomRenderedAdLoadedListener)
  {
    try
    {
      this.zzuQ = paramOnCustomRenderedAdLoadedListener;
      zzu localzzu;
      if (this.zzuL != null)
      {
        localzzu = this.zzuL;
        if (paramOnCustomRenderedAdLoadedListener == null) {
          break label38;
        }
      }
      label38:
      for (paramOnCustomRenderedAdLoadedListener = new zzcg(paramOnCustomRenderedAdLoadedListener);; paramOnCustomRenderedAdLoadedListener = null)
      {
        localzzu.zza(paramOnCustomRenderedAdLoadedListener);
        return;
      }
      return;
    }
    catch (RemoteException paramOnCustomRenderedAdLoadedListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the OnCustomRenderedAdLoadedListener.", paramOnCustomRenderedAdLoadedListener);
    }
  }
  
  public void setPlayStorePurchaseParams(PlayStorePurchaseListener paramPlayStorePurchaseListener, String paramString)
  {
    if (this.zzuO != null) {
      throw new IllegalStateException("In app purchase parameter has already been set.");
    }
    try
    {
      this.zzuP = paramPlayStorePurchaseListener;
      this.zzuM = paramString;
      zzu localzzu;
      if (this.zzuL != null)
      {
        localzzu = this.zzuL;
        if (paramPlayStorePurchaseListener == null) {
          break label62;
        }
      }
      label62:
      for (paramPlayStorePurchaseListener = new zzgm(paramPlayStorePurchaseListener);; paramPlayStorePurchaseListener = null)
      {
        localzzu.zza(paramPlayStorePurchaseListener, paramString);
        return;
      }
      return;
    }
    catch (RemoteException paramPlayStorePurchaseListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the play store purchase parameter.", paramPlayStorePurchaseListener);
    }
  }
  
  public void setRewardedVideoAdListener(RewardedVideoAdListener paramRewardedVideoAdListener)
  {
    try
    {
      this.zzaX = paramRewardedVideoAdListener;
      zzu localzzu;
      if (this.zzuL != null)
      {
        localzzu = this.zzuL;
        if (paramRewardedVideoAdListener == null) {
          break label38;
        }
      }
      label38:
      for (paramRewardedVideoAdListener = new zzg(paramRewardedVideoAdListener);; paramRewardedVideoAdListener = null)
      {
        localzzu.zza(paramRewardedVideoAdListener);
        return;
      }
      return;
    }
    catch (RemoteException paramRewardedVideoAdListener)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdListener.", paramRewardedVideoAdListener);
    }
  }
  
  public void setUserId(String paramString)
  {
    try
    {
      this.zzaW = paramString;
      if (this.zzuL != null) {
        this.zzuL.setUserId(paramString);
      }
      return;
    }
    catch (RemoteException paramString)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdListener.", paramString);
    }
  }
  
  public void show()
  {
    try
    {
      zzI("show");
      this.zzuL.showInterstitial();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to show interstitial.", localRemoteException);
    }
  }
  
  public void zza(zza paramzza)
  {
    try
    {
      this.zztz = paramzza;
      zzu localzzu;
      if (this.zzuL != null)
      {
        localzzu = this.zzuL;
        if (paramzza == null) {
          break label38;
        }
      }
      label38:
      for (paramzza = new zzb(paramzza);; paramzza = null)
      {
        localzzu.zza(paramzza);
        return;
      }
      return;
    }
    catch (RemoteException paramzza)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the AdClickListener.", paramzza);
    }
  }
  
  public void zza(zzaa paramzzaa)
  {
    try
    {
      if (this.zzuL == null) {
        zzH("loadAd");
      }
      if (this.zzuL.zzb(this.zzoB.zza(this.mContext, paramzzaa))) {
        this.zzuJ.zzg(paramzzaa.zzdb());
      }
      return;
    }
    catch (RemoteException paramzzaa)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to load ad.", paramzzaa);
    }
  }
  
  public void zza(boolean paramBoolean)
  {
    this.zzuU = paramBoolean;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\client\zzac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */