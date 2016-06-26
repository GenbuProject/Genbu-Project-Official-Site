package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzcg;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzgi;
import com.google.android.gms.internal.zzgm;
import com.google.android.gms.internal.zzhb;
import java.util.concurrent.atomic.AtomicBoolean;

@zzhb
public class zzab
{
  private final zzh zzoB;
  private boolean zzpE;
  private String zzpS;
  private AdListener zztA;
  private zza zztz;
  private final zzew zzuJ = new zzew();
  private final AtomicBoolean zzuK;
  private zzu zzuL;
  private String zzuM;
  private ViewGroup zzuN;
  private InAppPurchaseListener zzuO;
  private PlayStorePurchaseListener zzuP;
  private OnCustomRenderedAdLoadedListener zzuQ;
  private Correlator zzuR;
  private boolean zzuS;
  private AppEventListener zzun;
  private AdSize[] zzuo;
  
  public zzab(ViewGroup paramViewGroup)
  {
    this(paramViewGroup, null, false, zzh.zzcO(), false);
  }
  
  public zzab(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean)
  {
    this(paramViewGroup, paramAttributeSet, paramBoolean, zzh.zzcO(), false);
  }
  
  zzab(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean1, zzh paramzzh, zzu paramzzu, boolean paramBoolean2)
  {
    this.zzuN = paramViewGroup;
    this.zzoB = paramzzh;
    this.zzuL = paramzzu;
    this.zzuK = new AtomicBoolean(false);
    this.zzuS = paramBoolean2;
    if (paramAttributeSet != null) {
      paramzzh = paramViewGroup.getContext();
    }
    try
    {
      paramAttributeSet = new zzk(paramzzh, paramAttributeSet);
      this.zzuo = paramAttributeSet.zzj(paramBoolean1);
      this.zzpS = paramAttributeSet.getAdUnitId();
      if (paramViewGroup.isInEditMode()) {
        zzn.zzcS().zza(paramViewGroup, zza(paramzzh, this.zzuo[0], this.zzuS), "Ads by Google");
      }
      return;
    }
    catch (IllegalArgumentException paramAttributeSet)
    {
      zzn.zzcS().zza(paramViewGroup, new AdSizeParcel(paramzzh, AdSize.BANNER), paramAttributeSet.getMessage(), paramAttributeSet.getMessage());
    }
  }
  
  zzab(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean1, zzh paramzzh, boolean paramBoolean2)
  {
    this(paramViewGroup, paramAttributeSet, paramBoolean1, paramzzh, null, paramBoolean2);
  }
  
  public zzab(ViewGroup paramViewGroup, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramViewGroup, paramAttributeSet, paramBoolean1, zzh.zzcO(), paramBoolean2);
  }
  
  public zzab(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    this(paramViewGroup, null, false, zzh.zzcO(), paramBoolean);
  }
  
  private static AdSizeParcel zza(Context paramContext, AdSize paramAdSize, boolean paramBoolean)
  {
    paramContext = new AdSizeParcel(paramContext, paramAdSize);
    paramContext.zzi(paramBoolean);
    return paramContext;
  }
  
  private static AdSizeParcel zza(Context paramContext, AdSize[] paramArrayOfAdSize, boolean paramBoolean)
  {
    paramContext = new AdSizeParcel(paramContext, paramArrayOfAdSize);
    paramContext.zzi(paramBoolean);
    return paramContext;
  }
  
  private void zzdf()
  {
    try
    {
      zzd localzzd = this.zzuL.zzaM();
      if (localzzd == null) {
        return;
      }
      this.zzuN.addView((View)com.google.android.gms.dynamic.zze.zzp(localzzd));
      return;
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to get an ad frame.", localRemoteException);
    }
  }
  
  public void destroy()
  {
    try
    {
      if (this.zzuL != null) {
        this.zzuL.destroy();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to destroy AdView.", localRemoteException);
    }
  }
  
  public AdListener getAdListener()
  {
    return this.zztA;
  }
  
  public AdSize getAdSize()
  {
    try
    {
      if (this.zzuL != null)
      {
        Object localObject = this.zzuL.zzaN();
        if (localObject != null)
        {
          localObject = ((AdSizeParcel)localObject).zzcQ();
          return (AdSize)localObject;
        }
      }
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to get the current AdSize.", localRemoteException);
      if (this.zzuo != null) {
        return this.zzuo[0];
      }
    }
    return null;
  }
  
  public AdSize[] getAdSizes()
  {
    return this.zzuo;
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
  
  public boolean isLoading()
  {
    try
    {
      if (this.zzuL != null)
      {
        boolean bool = this.zzuL.isLoading();
        return bool;
      }
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to check if ad is loading.", localRemoteException);
    }
    return false;
  }
  
  public void pause()
  {
    try
    {
      if (this.zzuL != null) {
        this.zzuL.pause();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to call pause.", localRemoteException);
    }
  }
  
  public void recordManualImpression()
  {
    if (this.zzuK.getAndSet(true)) {}
    for (;;)
    {
      return;
      try
      {
        if (this.zzuL != null)
        {
          this.zzuL.zzaP();
          return;
        }
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to record impression.", localRemoteException);
      }
    }
  }
  
  public void resume()
  {
    try
    {
      if (this.zzuL != null) {
        this.zzuL.resume();
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to call resume.", localRemoteException);
    }
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
  
  public void setAdSizes(AdSize... paramVarArgs)
  {
    if (this.zzuo != null) {
      throw new IllegalStateException("The ad size can only be set once on AdView.");
    }
    zza(paramVarArgs);
  }
  
  public void setAdUnitId(String paramString)
  {
    if (this.zzpS != null) {
      throw new IllegalStateException("The ad unit ID can only be set once on AdView.");
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
          break label56;
        }
      }
      label56:
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
  
  public void setManualImpressionsEnabled(boolean paramBoolean)
  {
    this.zzpE = paramBoolean;
    try
    {
      if (this.zzuL != null) {
        this.zzuL.setManualImpressionsEnabled(this.zzpE);
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set manual impressions.", localRemoteException);
    }
  }
  
  public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener paramOnCustomRenderedAdLoadedListener)
  {
    this.zzuQ = paramOnCustomRenderedAdLoadedListener;
    try
    {
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
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the onCustomRenderedAdLoadedListener.", paramOnCustomRenderedAdLoadedListener);
    }
  }
  
  public void setPlayStorePurchaseParams(PlayStorePurchaseListener paramPlayStorePurchaseListener, String paramString)
  {
    if (this.zzuO != null) {
      throw new IllegalStateException("InAppPurchaseListener has already been set.");
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
        zzdg();
      }
      if (this.zzuL.zzb(this.zzoB.zza(this.zzuN.getContext(), paramzzaa))) {
        this.zzuJ.zzg(paramzzaa.zzdb());
      }
      return;
    }
    catch (RemoteException paramzzaa)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to load ad.", paramzzaa);
    }
  }
  
  public void zza(AdSize... paramVarArgs)
  {
    this.zzuo = paramVarArgs;
    try
    {
      if (this.zzuL != null) {
        this.zzuL.zza(zza(this.zzuN.getContext(), this.zzuo, this.zzuS));
      }
      this.zzuN.requestLayout();
      return;
    }
    catch (RemoteException paramVarArgs)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Failed to set the ad size.", paramVarArgs);
      }
    }
  }
  
  void zzdg()
    throws RemoteException
  {
    if (((this.zzuo == null) || (this.zzpS == null)) && (this.zzuL == null)) {
      throw new IllegalStateException("The ad size and ad unit ID must be set before loadAd is called.");
    }
    this.zzuL = zzdh();
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
    this.zzuL.setManualImpressionsEnabled(this.zzpE);
    zzdf();
  }
  
  protected zzu zzdh()
    throws RemoteException
  {
    Context localContext = this.zzuN.getContext();
    return zzn.zzcT().zza(localContext, zza(localContext, this.zzuo, this.zzuS), this.zzpS, this.zzuJ);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\client\zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */