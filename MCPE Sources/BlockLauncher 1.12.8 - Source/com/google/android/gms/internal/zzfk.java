package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;

@zzhb
public final class zzfk<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters>
  implements MediationBannerListener, MediationInterstitialListener
{
  private final zzez zzCK;
  
  public zzfk(zzez paramzzez)
  {
    this.zzCK = paramzzez;
  }
  
  public void onClick(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzaI("Adapter called onClick.");
    if (!zzn.zzcS().zzhJ())
    {
      zzb.zzaK("onClick must be called on the main UI thread.");
      zza.zzMS.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzfk.zza(zzfk.this).onAdClicked();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdClicked.", localRemoteException);
          }
        }
      });
      return;
    }
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
  
  public void onDismissScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzaI("Adapter called onDismissScreen.");
    if (!zzn.zzcS().zzhJ())
    {
      zzb.zzaK("onDismissScreen must be called on the main UI thread.");
      zza.zzMS.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzfk.zza(zzfk.this).onAdClosed();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdClosed.", localRemoteException);
          }
        }
      });
      return;
    }
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
  
  public void onDismissScreen(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzaI("Adapter called onDismissScreen.");
    if (!zzn.zzcS().zzhJ())
    {
      zzb.zzaK("onDismissScreen must be called on the main UI thread.");
      zza.zzMS.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzfk.zza(zzfk.this).onAdClosed();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdClosed.", localRemoteException);
          }
        }
      });
      return;
    }
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
  
  public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter, final AdRequest.ErrorCode paramErrorCode)
  {
    zzb.zzaI("Adapter called onFailedToReceiveAd with error. " + paramErrorCode);
    if (!zzn.zzcS().zzhJ())
    {
      zzb.zzaK("onFailedToReceiveAd must be called on the main UI thread.");
      zza.zzMS.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzfk.zza(zzfk.this).onAdFailedToLoad(zzfl.zza(paramErrorCode));
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdFailedToLoad.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.zzCK.onAdFailedToLoad(zzfl.zza(paramErrorCode));
      return;
    }
    catch (RemoteException paramMediationBannerAdapter)
    {
      zzb.zzd("Could not call onAdFailedToLoad.", paramMediationBannerAdapter);
    }
  }
  
  public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter, final AdRequest.ErrorCode paramErrorCode)
  {
    zzb.zzaI("Adapter called onFailedToReceiveAd with error " + paramErrorCode + ".");
    if (!zzn.zzcS().zzhJ())
    {
      zzb.zzaK("onFailedToReceiveAd must be called on the main UI thread.");
      zza.zzMS.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzfk.zza(zzfk.this).onAdFailedToLoad(zzfl.zza(paramErrorCode));
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdFailedToLoad.", localRemoteException);
          }
        }
      });
      return;
    }
    try
    {
      this.zzCK.onAdFailedToLoad(zzfl.zza(paramErrorCode));
      return;
    }
    catch (RemoteException paramMediationInterstitialAdapter)
    {
      zzb.zzd("Could not call onAdFailedToLoad.", paramMediationInterstitialAdapter);
    }
  }
  
  public void onLeaveApplication(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzaI("Adapter called onLeaveApplication.");
    if (!zzn.zzcS().zzhJ())
    {
      zzb.zzaK("onLeaveApplication must be called on the main UI thread.");
      zza.zzMS.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzfk.zza(zzfk.this).onAdLeftApplication();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdLeftApplication.", localRemoteException);
          }
        }
      });
      return;
    }
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
  
  public void onLeaveApplication(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzaI("Adapter called onLeaveApplication.");
    if (!zzn.zzcS().zzhJ())
    {
      zzb.zzaK("onLeaveApplication must be called on the main UI thread.");
      zza.zzMS.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzfk.zza(zzfk.this).onAdLeftApplication();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdLeftApplication.", localRemoteException);
          }
        }
      });
      return;
    }
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
  
  public void onPresentScreen(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzaI("Adapter called onPresentScreen.");
    if (!zzn.zzcS().zzhJ())
    {
      zzb.zzaK("onPresentScreen must be called on the main UI thread.");
      zza.zzMS.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzfk.zza(zzfk.this).onAdOpened();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdOpened.", localRemoteException);
          }
        }
      });
      return;
    }
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
  
  public void onPresentScreen(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzaI("Adapter called onPresentScreen.");
    if (!zzn.zzcS().zzhJ())
    {
      zzb.zzaK("onPresentScreen must be called on the main UI thread.");
      zza.zzMS.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzfk.zza(zzfk.this).onAdOpened();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdOpened.", localRemoteException);
          }
        }
      });
      return;
    }
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
  
  public void onReceivedAd(MediationBannerAdapter<?, ?> paramMediationBannerAdapter)
  {
    zzb.zzaI("Adapter called onReceivedAd.");
    if (!zzn.zzcS().zzhJ())
    {
      zzb.zzaK("onReceivedAd must be called on the main UI thread.");
      zza.zzMS.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzfk.zza(zzfk.this).onAdLoaded();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdLoaded.", localRemoteException);
          }
        }
      });
      return;
    }
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
  
  public void onReceivedAd(MediationInterstitialAdapter<?, ?> paramMediationInterstitialAdapter)
  {
    zzb.zzaI("Adapter called onReceivedAd.");
    if (!zzn.zzcS().zzhJ())
    {
      zzb.zzaK("onReceivedAd must be called on the main UI thread.");
      zza.zzMS.post(new Runnable()
      {
        public void run()
        {
          try
          {
            zzfk.zza(zzfk.this).onAdLoaded();
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzb.zzd("Could not call onAdLoaded.", localRemoteException);
          }
        }
      });
      return;
    }
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
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzfk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */