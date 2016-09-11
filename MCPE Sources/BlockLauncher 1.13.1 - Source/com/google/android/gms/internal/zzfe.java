package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

@zzhb
public final class zzfe
  extends zzey.zza
{
  private final MediationAdapter zzCI;
  private zzff zzCJ;
  
  public zzfe(MediationAdapter paramMediationAdapter)
  {
    this.zzCI = paramMediationAdapter;
  }
  
  private Bundle zza(String paramString1, int paramInt, String paramString2)
    throws RemoteException
  {
    com.google.android.gms.ads.internal.util.client.zzb.zzaK("Server parameters: " + paramString1);
    Bundle localBundle;
    try
    {
      localBundle = new Bundle();
      if (paramString1 != null)
      {
        paramString1 = new JSONObject(paramString1);
        localBundle = new Bundle();
        Iterator localIterator = paramString1.keys();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localBundle.putString(str, paramString1.getString(str));
        }
      }
      if (!(this.zzCI instanceof AdMobAdapter)) {
        break label138;
      }
    }
    catch (Throwable paramString1)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not get Server Parameters Bundle.", paramString1);
      throw new RemoteException();
    }
    localBundle.putString("adJson", paramString2);
    localBundle.putInt("tagForChildDirectedTreatment", paramInt);
    label138:
    return localBundle;
  }
  
  public void destroy()
    throws RemoteException
  {
    try
    {
      this.zzCI.onDestroy();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not destroy adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public Bundle getInterstitialAdapterInfo()
  {
    if (!(this.zzCI instanceof zzka))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaK("MediationAdapter is not a v2 MediationInterstitialAdapter: " + this.zzCI.getClass().getCanonicalName());
      return new Bundle();
    }
    return ((zzka)this.zzCI).getInterstitialAdapterInfo();
  }
  
  public zzd getView()
    throws RemoteException
  {
    if (!(this.zzCI instanceof MediationBannerAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaK("MediationAdapter is not a MediationBannerAdapter: " + this.zzCI.getClass().getCanonicalName());
      throw new RemoteException();
    }
    try
    {
      zzd localzzd = zze.zzC(((MediationBannerAdapter)this.zzCI).getBannerView());
      return localzzd;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not get banner view from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public boolean isInitialized()
    throws RemoteException
  {
    if (!(this.zzCI instanceof MediationRewardedVideoAdAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaK("MediationAdapter is not a MediationRewardedVideoAdAdapter: " + this.zzCI.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaI("Check if adapter is initialized.");
    try
    {
      boolean bool = ((MediationRewardedVideoAdAdapter)this.zzCI).isInitialized();
      return bool;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not check if adapter is initialized.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void pause()
    throws RemoteException
  {
    try
    {
      this.zzCI.onPause();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not pause adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void resume()
    throws RemoteException
  {
    try
    {
      this.zzCI.onResume();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not resume adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void showInterstitial()
    throws RemoteException
  {
    if (!(this.zzCI instanceof MediationInterstitialAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaK("MediationAdapter is not a MediationInterstitialAdapter: " + this.zzCI.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaI("Showing interstitial from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.zzCI).showInterstitial();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not show interstitial from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void showVideo()
    throws RemoteException
  {
    if (!(this.zzCI instanceof MediationRewardedVideoAdAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaK("MediationAdapter is not a MediationRewardedVideoAdAdapter: " + this.zzCI.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaI("Show rewarded video ad from adapter.");
    try
    {
      ((MediationRewardedVideoAdAdapter)this.zzCI).showVideo();
      return;
    }
    catch (Throwable localThrowable)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not show rewarded video ad from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void zza(AdRequestParcel paramAdRequestParcel, String paramString1, String paramString2)
    throws RemoteException
  {
    if (!(this.zzCI instanceof MediationRewardedVideoAdAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaK("MediationAdapter is not a MediationRewardedVideoAdAdapter: " + this.zzCI.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaI("Requesting rewarded video ad from adapter.");
    for (;;)
    {
      try
      {
        MediationRewardedVideoAdAdapter localMediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter)this.zzCI;
        if (paramAdRequestParcel.zztE == null) {
          break label213;
        }
        localObject1 = new HashSet(paramAdRequestParcel.zztE);
        Object localObject2;
        if (paramAdRequestParcel.zztC == -1L)
        {
          localObject2 = null;
          localObject2 = new zzfd((Date)localObject2, paramAdRequestParcel.zztD, (Set)localObject1, paramAdRequestParcel.zztK, paramAdRequestParcel.zztF, paramAdRequestParcel.zztG, paramAdRequestParcel.zztR);
          if (paramAdRequestParcel.zztM != null)
          {
            localObject1 = paramAdRequestParcel.zztM.getBundle(localMediationRewardedVideoAdAdapter.getClass().getName());
            localMediationRewardedVideoAdAdapter.loadAd((MediationAdRequest)localObject2, zza(paramString1, paramAdRequestParcel.zztG, paramString2), (Bundle)localObject1);
          }
        }
        else
        {
          localObject2 = new Date(paramAdRequestParcel.zztC);
          continue;
        }
        localObject1 = null;
      }
      catch (Throwable paramAdRequestParcel)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not load rewarded video ad from adapter.", paramAdRequestParcel);
        throw new RemoteException();
      }
      continue;
      label213:
      Object localObject1 = null;
    }
  }
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString1, com.google.android.gms.ads.internal.reward.mediation.client.zza paramzza, String paramString2)
    throws RemoteException
  {
    if (!(this.zzCI instanceof MediationRewardedVideoAdAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaK("MediationAdapter is not a MediationRewardedVideoAdAdapter: " + this.zzCI.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaI("Initialize rewarded video adapter.");
    for (;;)
    {
      try
      {
        MediationRewardedVideoAdAdapter localMediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter)this.zzCI;
        if (paramAdRequestParcel.zztE == null) {
          break label232;
        }
        localObject1 = new HashSet(paramAdRequestParcel.zztE);
        Object localObject2;
        if (paramAdRequestParcel.zztC == -1L)
        {
          localObject2 = null;
          localObject2 = new zzfd((Date)localObject2, paramAdRequestParcel.zztD, (Set)localObject1, paramAdRequestParcel.zztK, paramAdRequestParcel.zztF, paramAdRequestParcel.zztG, paramAdRequestParcel.zztR);
          if (paramAdRequestParcel.zztM != null)
          {
            localObject1 = paramAdRequestParcel.zztM.getBundle(localMediationRewardedVideoAdAdapter.getClass().getName());
            localMediationRewardedVideoAdAdapter.initialize((Context)zze.zzp(paramzzd), (MediationAdRequest)localObject2, paramString1, new com.google.android.gms.ads.internal.reward.mediation.client.zzb(paramzza), zza(paramString2, paramAdRequestParcel.zztG, null), (Bundle)localObject1);
          }
        }
        else
        {
          localObject2 = new Date(paramAdRequestParcel.zztC);
          continue;
        }
        localObject1 = null;
      }
      catch (Throwable paramzzd)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not initialize rewarded video adapter.", paramzzd);
        throw new RemoteException();
      }
      continue;
      label232:
      Object localObject1 = null;
    }
  }
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString, zzez paramzzez)
    throws RemoteException
  {
    zza(paramzzd, paramAdRequestParcel, paramString, null, paramzzez);
  }
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString1, String paramString2, zzez paramzzez)
    throws RemoteException
  {
    if (!(this.zzCI instanceof MediationInterstitialAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaK("MediationAdapter is not a MediationInterstitialAdapter: " + this.zzCI.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaI("Requesting interstitial ad from adapter.");
    for (;;)
    {
      try
      {
        MediationInterstitialAdapter localMediationInterstitialAdapter = (MediationInterstitialAdapter)this.zzCI;
        if (paramAdRequestParcel.zztE == null) {
          break label232;
        }
        localObject1 = new HashSet(paramAdRequestParcel.zztE);
        Object localObject2;
        if (paramAdRequestParcel.zztC == -1L)
        {
          localObject2 = null;
          localObject2 = new zzfd((Date)localObject2, paramAdRequestParcel.zztD, (Set)localObject1, paramAdRequestParcel.zztK, paramAdRequestParcel.zztF, paramAdRequestParcel.zztG, paramAdRequestParcel.zztR);
          if (paramAdRequestParcel.zztM != null)
          {
            localObject1 = paramAdRequestParcel.zztM.getBundle(localMediationInterstitialAdapter.getClass().getName());
            localMediationInterstitialAdapter.requestInterstitialAd((Context)zze.zzp(paramzzd), new zzff(paramzzez), zza(paramString1, paramAdRequestParcel.zztG, paramString2), (MediationAdRequest)localObject2, (Bundle)localObject1);
          }
        }
        else
        {
          localObject2 = new Date(paramAdRequestParcel.zztC);
          continue;
        }
        localObject1 = null;
      }
      catch (Throwable paramzzd)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not request interstitial ad from adapter.", paramzzd);
        throw new RemoteException();
      }
      continue;
      label232:
      Object localObject1 = null;
    }
  }
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString1, String paramString2, zzez paramzzez, NativeAdOptionsParcel paramNativeAdOptionsParcel, List<String> paramList)
    throws RemoteException
  {
    if (!(this.zzCI instanceof MediationNativeAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaK("MediationAdapter is not a MediationNativeAdapter: " + this.zzCI.getClass().getCanonicalName());
      throw new RemoteException();
    }
    for (;;)
    {
      try
      {
        MediationNativeAdapter localMediationNativeAdapter = (MediationNativeAdapter)this.zzCI;
        if (paramAdRequestParcel.zztE == null) {
          break label239;
        }
        localHashSet = new HashSet(paramAdRequestParcel.zztE);
        Date localDate;
        if (paramAdRequestParcel.zztC == -1L)
        {
          localDate = null;
          paramList = new zzfi(localDate, paramAdRequestParcel.zztD, localHashSet, paramAdRequestParcel.zztK, paramAdRequestParcel.zztF, paramAdRequestParcel.zztG, paramNativeAdOptionsParcel, paramList, paramAdRequestParcel.zztR);
          if (paramAdRequestParcel.zztM != null)
          {
            paramNativeAdOptionsParcel = paramAdRequestParcel.zztM.getBundle(localMediationNativeAdapter.getClass().getName());
            this.zzCJ = new zzff(paramzzez);
            localMediationNativeAdapter.requestNativeAd((Context)zze.zzp(paramzzd), this.zzCJ, zza(paramString1, paramAdRequestParcel.zztG, paramString2), paramList, paramNativeAdOptionsParcel);
          }
        }
        else
        {
          localDate = new Date(paramAdRequestParcel.zztC);
          continue;
        }
        paramNativeAdOptionsParcel = null;
      }
      catch (Throwable paramzzd)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not request native ad from adapter.", paramzzd);
        throw new RemoteException();
      }
      continue;
      label239:
      HashSet localHashSet = null;
    }
  }
  
  public void zza(zzd paramzzd, AdSizeParcel paramAdSizeParcel, AdRequestParcel paramAdRequestParcel, String paramString, zzez paramzzez)
    throws RemoteException
  {
    zza(paramzzd, paramAdSizeParcel, paramAdRequestParcel, paramString, null, paramzzez);
  }
  
  public void zza(zzd paramzzd, AdSizeParcel paramAdSizeParcel, AdRequestParcel paramAdRequestParcel, String paramString1, String paramString2, zzez paramzzez)
    throws RemoteException
  {
    if (!(this.zzCI instanceof MediationBannerAdapter))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaK("MediationAdapter is not a MediationBannerAdapter: " + this.zzCI.getClass().getCanonicalName());
      throw new RemoteException();
    }
    com.google.android.gms.ads.internal.util.client.zzb.zzaI("Requesting banner ad from adapter.");
    for (;;)
    {
      try
      {
        MediationBannerAdapter localMediationBannerAdapter = (MediationBannerAdapter)this.zzCI;
        if (paramAdRequestParcel.zztE == null) {
          break label248;
        }
        localObject1 = new HashSet(paramAdRequestParcel.zztE);
        Object localObject2;
        if (paramAdRequestParcel.zztC == -1L)
        {
          localObject2 = null;
          localObject2 = new zzfd((Date)localObject2, paramAdRequestParcel.zztD, (Set)localObject1, paramAdRequestParcel.zztK, paramAdRequestParcel.zztF, paramAdRequestParcel.zztG, paramAdRequestParcel.zztR);
          if (paramAdRequestParcel.zztM != null)
          {
            localObject1 = paramAdRequestParcel.zztM.getBundle(localMediationBannerAdapter.getClass().getName());
            localMediationBannerAdapter.requestBannerAd((Context)zze.zzp(paramzzd), new zzff(paramzzez), zza(paramString1, paramAdRequestParcel.zztG, paramString2), com.google.android.gms.ads.zza.zza(paramAdSizeParcel.width, paramAdSizeParcel.height, paramAdSizeParcel.zzuh), (MediationAdRequest)localObject2, (Bundle)localObject1);
          }
        }
        else
        {
          localObject2 = new Date(paramAdRequestParcel.zztC);
          continue;
        }
        localObject1 = null;
      }
      catch (Throwable paramzzd)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not request banner ad from adapter.", paramzzd);
        throw new RemoteException();
      }
      continue;
      label248:
      Object localObject1 = null;
    }
  }
  
  public void zzb(AdRequestParcel paramAdRequestParcel, String paramString)
    throws RemoteException
  {
    zza(paramAdRequestParcel, paramString, null);
  }
  
  public zzfb zzeF()
  {
    NativeAdMapper localNativeAdMapper = this.zzCJ.zzeJ();
    if ((localNativeAdMapper instanceof NativeAppInstallAdMapper)) {
      return new zzfg((NativeAppInstallAdMapper)localNativeAdMapper);
    }
    return null;
  }
  
  public zzfc zzeG()
  {
    NativeAdMapper localNativeAdMapper = this.zzCJ.zzeJ();
    if ((localNativeAdMapper instanceof NativeContentAdMapper)) {
      return new zzfh((NativeContentAdMapper)localNativeAdMapper);
    }
    return null;
  }
  
  public Bundle zzeH()
  {
    if (!(this.zzCI instanceof zzjz))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaK("MediationAdapter is not a v2 MediationBannerAdapter: " + this.zzCI.getClass().getCanonicalName());
      return new Bundle();
    }
    return ((zzjz)this.zzCI).zzeH();
  }
  
  public Bundle zzeI()
  {
    return new Bundle();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzfe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */