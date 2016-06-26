package com.google.android.gms.ads.internal.reward.client;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.internal.client.zzh;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zzhb;

@zzhb
public class zzi
  implements RewardedVideoAd
{
  private final Context mContext;
  private final zzb zzKA;
  private RewardedVideoAdListener zzaX;
  private final Object zzpV = new Object();
  private String zzrG;
  
  public zzi(Context paramContext, zzb paramzzb)
  {
    this.zzKA = paramzzb;
    this.mContext = paramContext;
  }
  
  public void destroy()
  {
    synchronized (this.zzpV)
    {
      if (this.zzKA == null) {
        return;
      }
    }
    try
    {
      this.zzKA.destroy();
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward destroy to RewardedVideoAd", localRemoteException);
      }
    }
  }
  
  public RewardedVideoAdListener getRewardedVideoAdListener()
  {
    synchronized (this.zzpV)
    {
      RewardedVideoAdListener localRewardedVideoAdListener = this.zzaX;
      return localRewardedVideoAdListener;
    }
  }
  
  public String getUserId()
  {
    synchronized (this.zzpV)
    {
      String str = this.zzrG;
      return str;
    }
  }
  
  public boolean isLoaded()
  {
    boolean bool;
    synchronized (this.zzpV)
    {
      if (this.zzKA == null) {
        return false;
      }
    }
    return false;
  }
  
  public void loadAd(String paramString, AdRequest paramAdRequest)
  {
    synchronized (this.zzpV)
    {
      if (this.zzKA == null) {
        return;
      }
    }
    try
    {
      this.zzKA.zza(zzh.zzcO().zza(this.mContext, paramAdRequest.zzaE(), paramString));
      return;
      paramString = finally;
      throw paramString;
    }
    catch (RemoteException paramString)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward loadAd to RewardedVideoAd", paramString);
      }
    }
  }
  
  public void pause()
  {
    synchronized (this.zzpV)
    {
      if (this.zzKA == null) {
        return;
      }
    }
    try
    {
      this.zzKA.pause();
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward pause to RewardedVideoAd", localRemoteException);
      }
    }
  }
  
  public void resume()
  {
    synchronized (this.zzpV)
    {
      if (this.zzKA == null) {
        return;
      }
    }
    try
    {
      this.zzKA.resume();
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward resume to RewardedVideoAd", localRemoteException);
      }
    }
  }
  
  public void setRewardedVideoAdListener(RewardedVideoAdListener paramRewardedVideoAdListener)
  {
    synchronized (this.zzpV)
    {
      this.zzaX = paramRewardedVideoAdListener;
      zzb localzzb = this.zzKA;
      if (localzzb != null) {}
      try
      {
        this.zzKA.zza(new zzg(paramRewardedVideoAdListener));
        return;
      }
      catch (RemoteException paramRewardedVideoAdListener)
      {
        for (;;)
        {
          com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward setRewardedVideoAdListener to RewardedVideoAd", paramRewardedVideoAdListener);
        }
      }
    }
  }
  
  public void setUserId(String paramString)
  {
    synchronized (this.zzpV)
    {
      if (!TextUtils.isEmpty(this.zzrG))
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaK("A user id has already been set, ignoring.");
        return;
      }
      this.zzrG = paramString;
      zzb localzzb = this.zzKA;
      if (localzzb == null) {}
    }
    try
    {
      this.zzKA.setUserId(paramString);
      return;
      paramString = finally;
      throw paramString;
    }
    catch (RemoteException paramString)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward setUserId to RewardedVideoAd", paramString);
      }
    }
  }
  
  public void show()
  {
    synchronized (this.zzpV)
    {
      if (this.zzKA == null) {
        return;
      }
    }
    try
    {
      this.zzKA.show();
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not forward show to RewardedVideoAd", localRemoteException);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\reward\client\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */