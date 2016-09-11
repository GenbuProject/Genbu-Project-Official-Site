package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzp.zza;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzq.zza;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzw.zza;
import com.google.android.gms.ads.internal.reward.client.zza;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.reward.client.zzd.zza;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzr;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzhb
class zzdw
{
  private final List<zza> zzpH = new LinkedList();
  
  void zza(final zzdx paramzzdx)
  {
    Handler localHandler = zzir.zzMc;
    Iterator localIterator = this.zzpH.iterator();
    while (localIterator.hasNext()) {
      localHandler.post(new Runnable()
      {
        public void run()
        {
          try
          {
            this.zzAo.zzb(paramzzdx);
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzin.zzd("Could not propagate interstitial ad event.", localRemoteException);
          }
        }
      });
    }
  }
  
  void zzc(zzk paramzzk)
  {
    paramzzk.zza(new zzq.zza()
    {
      public void onAdClosed()
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzpK != null) {
              paramAnonymous2zzdx.zzpK.onAdClosed();
            }
            zzr.zzbN().zzee();
          }
        });
      }
      
      public void onAdFailedToLoad(final int paramAnonymousInt)
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzpK != null) {
              paramAnonymous2zzdx.zzpK.onAdFailedToLoad(paramAnonymousInt);
            }
          }
        });
        zzin.v("Pooled interstitial failed to load.");
      }
      
      public void onAdLeftApplication()
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzpK != null) {
              paramAnonymous2zzdx.zzpK.onAdLeftApplication();
            }
          }
        });
      }
      
      public void onAdLoaded()
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzpK != null) {
              paramAnonymous2zzdx.zzpK.onAdLoaded();
            }
          }
        });
        zzin.v("Pooled interstitial loaded.");
      }
      
      public void onAdOpened()
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzpK != null) {
              paramAnonymous2zzdx.zzpK.onAdOpened();
            }
          }
        });
      }
    });
    paramzzk.zza(new zzw.zza()
    {
      public void onAppEvent(final String paramAnonymousString1, final String paramAnonymousString2)
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzAq != null) {
              paramAnonymous2zzdx.zzAq.onAppEvent(paramAnonymousString1, paramAnonymousString2);
            }
          }
        });
      }
    });
    paramzzk.zza(new zzgd.zza()
    {
      public void zza(final zzgc paramAnonymouszzgc)
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzAr != null) {
              paramAnonymous2zzdx.zzAr.zza(paramAnonymouszzgc);
            }
          }
        });
      }
    });
    paramzzk.zza(new zzcf.zza()
    {
      public void zza(final zzce paramAnonymouszzce)
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzAs != null) {
              paramAnonymous2zzdx.zzAs.zza(paramAnonymouszzce);
            }
          }
        });
      }
    });
    paramzzk.zza(new zzp.zza()
    {
      public void onAdClicked()
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzAt != null) {
              paramAnonymous2zzdx.zzAt.onAdClicked();
            }
          }
        });
      }
    });
    paramzzk.zza(new zzd.zza()
    {
      public void onRewardedVideoAdClosed()
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzAu != null) {
              paramAnonymous2zzdx.zzAu.onRewardedVideoAdClosed();
            }
          }
        });
      }
      
      public void onRewardedVideoAdFailedToLoad(final int paramAnonymousInt)
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzAu != null) {
              paramAnonymous2zzdx.zzAu.onRewardedVideoAdFailedToLoad(paramAnonymousInt);
            }
          }
        });
      }
      
      public void onRewardedVideoAdLeftApplication()
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzAu != null) {
              paramAnonymous2zzdx.zzAu.onRewardedVideoAdLeftApplication();
            }
          }
        });
      }
      
      public void onRewardedVideoAdLoaded()
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzAu != null) {
              paramAnonymous2zzdx.zzAu.onRewardedVideoAdLoaded();
            }
          }
        });
      }
      
      public void onRewardedVideoAdOpened()
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzAu != null) {
              paramAnonymous2zzdx.zzAu.onRewardedVideoAdOpened();
            }
          }
        });
      }
      
      public void onRewardedVideoStarted()
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzAu != null) {
              paramAnonymous2zzdx.zzAu.onRewardedVideoStarted();
            }
          }
        });
      }
      
      public void zza(final zza paramAnonymouszza)
        throws RemoteException
      {
        zzdw.zza(zzdw.this).add(new zzdw.zza()
        {
          public void zzb(zzdx paramAnonymous2zzdx)
            throws RemoteException
          {
            if (paramAnonymous2zzdx.zzAu != null) {
              paramAnonymous2zzdx.zzAu.zza(paramAnonymouszza);
            }
          }
        });
      }
    });
  }
  
  static abstract interface zza
  {
    public abstract void zzb(zzdx paramzzdx)
      throws RemoteException;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzdw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */