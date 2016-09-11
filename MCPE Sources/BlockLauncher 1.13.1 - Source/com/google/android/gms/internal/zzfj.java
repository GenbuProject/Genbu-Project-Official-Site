package com.google.android.gms.internal;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@zzhb
public final class zzfj<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters>
  extends zzey.zza
{
  private final MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> zzCO;
  private final NETWORK_EXTRAS zzCP;
  
  public zzfj(MediationAdapter<NETWORK_EXTRAS, SERVER_PARAMETERS> paramMediationAdapter, NETWORK_EXTRAS paramNETWORK_EXTRAS)
  {
    this.zzCO = paramMediationAdapter;
    this.zzCP = paramNETWORK_EXTRAS;
  }
  
  private SERVER_PARAMETERS zzb(String paramString1, int paramInt, String paramString2)
    throws RemoteException
  {
    if (paramString1 != null) {
      try
      {
        localObject = new JSONObject(paramString1);
        paramString2 = new HashMap(((JSONObject)localObject).length());
        Iterator localIterator = ((JSONObject)localObject).keys();
        for (;;)
        {
          paramString1 = paramString2;
          if (!localIterator.hasNext()) {
            break;
          }
          paramString1 = (String)localIterator.next();
          paramString2.put(paramString1, ((JSONObject)localObject).getString(paramString1));
        }
        paramString1 = new HashMap(0);
      }
      catch (Throwable paramString1)
      {
        zzb.zzd("Could not get MediationServerParameters.", paramString1);
        throw new RemoteException();
      }
    }
    Object localObject = this.zzCO.getServerParametersType();
    paramString2 = null;
    if (localObject != null)
    {
      paramString2 = (MediationServerParameters)((Class)localObject).newInstance();
      paramString2.load(paramString1);
    }
    return paramString2;
  }
  
  public void destroy()
    throws RemoteException
  {
    try
    {
      this.zzCO.destroy();
      return;
    }
    catch (Throwable localThrowable)
    {
      zzb.zzd("Could not destroy adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public Bundle getInterstitialAdapterInfo()
  {
    return new Bundle();
  }
  
  public zzd getView()
    throws RemoteException
  {
    if (!(this.zzCO instanceof MediationBannerAdapter))
    {
      zzb.zzaK("MediationAdapter is not a MediationBannerAdapter: " + this.zzCO.getClass().getCanonicalName());
      throw new RemoteException();
    }
    try
    {
      zzd localzzd = zze.zzC(((MediationBannerAdapter)this.zzCO).getBannerView());
      return localzzd;
    }
    catch (Throwable localThrowable)
    {
      zzb.zzd("Could not get banner view from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public boolean isInitialized()
  {
    return true;
  }
  
  public void pause()
    throws RemoteException
  {
    throw new RemoteException();
  }
  
  public void resume()
    throws RemoteException
  {
    throw new RemoteException();
  }
  
  public void showInterstitial()
    throws RemoteException
  {
    if (!(this.zzCO instanceof MediationInterstitialAdapter))
    {
      zzb.zzaK("MediationAdapter is not a MediationInterstitialAdapter: " + this.zzCO.getClass().getCanonicalName());
      throw new RemoteException();
    }
    zzb.zzaI("Showing interstitial from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.zzCO).showInterstitial();
      return;
    }
    catch (Throwable localThrowable)
    {
      zzb.zzd("Could not show interstitial from adapter.", localThrowable);
      throw new RemoteException();
    }
  }
  
  public void showVideo() {}
  
  public void zza(AdRequestParcel paramAdRequestParcel, String paramString1, String paramString2) {}
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString1, zza paramzza, String paramString2)
    throws RemoteException
  {}
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString, zzez paramzzez)
    throws RemoteException
  {
    zza(paramzzd, paramAdRequestParcel, paramString, null, paramzzez);
  }
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString1, String paramString2, zzez paramzzez)
    throws RemoteException
  {
    if (!(this.zzCO instanceof MediationInterstitialAdapter))
    {
      zzb.zzaK("MediationAdapter is not a MediationInterstitialAdapter: " + this.zzCO.getClass().getCanonicalName());
      throw new RemoteException();
    }
    zzb.zzaI("Requesting interstitial ad from adapter.");
    try
    {
      ((MediationInterstitialAdapter)this.zzCO).requestInterstitialAd(new zzfk(paramzzez), (Activity)zze.zzp(paramzzd), zzb(paramString1, paramAdRequestParcel.zztG, paramString2), zzfl.zzj(paramAdRequestParcel), this.zzCP);
      return;
    }
    catch (Throwable paramzzd)
    {
      zzb.zzd("Could not request interstitial ad from adapter.", paramzzd);
      throw new RemoteException();
    }
  }
  
  public void zza(zzd paramzzd, AdRequestParcel paramAdRequestParcel, String paramString1, String paramString2, zzez paramzzez, NativeAdOptionsParcel paramNativeAdOptionsParcel, List<String> paramList) {}
  
  public void zza(zzd paramzzd, AdSizeParcel paramAdSizeParcel, AdRequestParcel paramAdRequestParcel, String paramString, zzez paramzzez)
    throws RemoteException
  {
    zza(paramzzd, paramAdSizeParcel, paramAdRequestParcel, paramString, null, paramzzez);
  }
  
  public void zza(zzd paramzzd, AdSizeParcel paramAdSizeParcel, AdRequestParcel paramAdRequestParcel, String paramString1, String paramString2, zzez paramzzez)
    throws RemoteException
  {
    if (!(this.zzCO instanceof MediationBannerAdapter))
    {
      zzb.zzaK("MediationAdapter is not a MediationBannerAdapter: " + this.zzCO.getClass().getCanonicalName());
      throw new RemoteException();
    }
    zzb.zzaI("Requesting banner ad from adapter.");
    try
    {
      ((MediationBannerAdapter)this.zzCO).requestBannerAd(new zzfk(paramzzez), (Activity)zze.zzp(paramzzd), zzb(paramString1, paramAdRequestParcel.zztG, paramString2), zzfl.zzb(paramAdSizeParcel), zzfl.zzj(paramAdRequestParcel), this.zzCP);
      return;
    }
    catch (Throwable paramzzd)
    {
      zzb.zzd("Could not request banner ad from adapter.", paramzzd);
      throw new RemoteException();
    }
  }
  
  public void zzb(AdRequestParcel paramAdRequestParcel, String paramString) {}
  
  public zzfb zzeF()
  {
    return null;
  }
  
  public zzfc zzeG()
  {
    return null;
  }
  
  public Bundle zzeH()
  {
    return new Bundle();
  }
  
  public Bundle zzeI()
  {
    return new Bundle();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzfj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */