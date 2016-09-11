package com.google.android.gms.ads.internal.client;

import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.client.zzb.zza;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;

public class zzal
  extends zzb.zza
{
  private zzd zzvb;
  
  public void destroy()
    throws RemoteException
  {}
  
  public boolean isLoaded()
    throws RemoteException
  {
    return false;
  }
  
  public void pause()
    throws RemoteException
  {}
  
  public void resume()
    throws RemoteException
  {}
  
  public void setUserId(String paramString)
    throws RemoteException
  {}
  
  public void show()
    throws RemoteException
  {}
  
  public void zza(RewardedVideoAdRequestParcel paramRewardedVideoAdRequestParcel)
    throws RemoteException
  {
    zzb.e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
    zza.zzMS.post(new Runnable()
    {
      public void run()
      {
        if (zzal.zza(zzal.this) != null) {}
        try
        {
          zzal.zza(zzal.this).onRewardedVideoAdFailedToLoad(1);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          zzb.zzd("Could not notify onRewardedVideoAdFailedToLoad event.", localRemoteException);
        }
      }
    });
  }
  
  public void zza(zzd paramzzd)
    throws RemoteException
  {
    this.zzvb = paramzzd;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\client\zzal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */