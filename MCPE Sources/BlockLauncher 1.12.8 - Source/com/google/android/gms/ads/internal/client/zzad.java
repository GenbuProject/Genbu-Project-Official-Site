package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.zzf;
import com.google.android.gms.ads.internal.reward.client.zzi;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzhb;

@zzhb
public class zzad
{
  private static final Object zzqy = new Object();
  private static zzad zzuV;
  private zzy zzuW;
  private RewardedVideoAd zzuX;
  
  public static zzad zzdi()
  {
    synchronized (zzqy)
    {
      if (zzuV == null) {
        zzuV = new zzad();
      }
      zzad localzzad = zzuV;
      return localzzad;
    }
  }
  
  public RewardedVideoAd getRewardedVideoAdInstance(Context paramContext)
  {
    synchronized (zzqy)
    {
      if (this.zzuX != null)
      {
        paramContext = this.zzuX;
        return paramContext;
      }
      zzew localzzew = new zzew();
      this.zzuX = new zzi(paramContext, zzn.zzcX().zza(paramContext, localzzew));
      paramContext = this.zzuX;
      return paramContext;
    }
  }
  
  public void initialize(Context paramContext)
  {
    synchronized (zzqy)
    {
      if (this.zzuW != null) {
        return;
      }
      if (paramContext == null) {
        throw new IllegalArgumentException("Context cannot be null.");
      }
    }
    try
    {
      this.zzuW = zzn.zzcV().zzu(paramContext);
      this.zzuW.zza();
      return;
    }
    catch (RemoteException paramContext)
    {
      for (;;)
      {
        zzb.zzaK("Fail to initialize mobile ads setting manager");
      }
    }
  }
  
  public void setAppVolume(float paramFloat)
  {
    boolean bool2 = true;
    if ((0.0F <= paramFloat) && (paramFloat <= 1.0F))
    {
      bool1 = true;
      zzx.zzb(bool1, "The app volume must be a value between 0 and 1 inclusive.");
      if (this.zzuW == null) {
        break label53;
      }
    }
    label53:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzx.zza(bool1, "MobileAds.initialize() must be called prior to setting the app volume.");
      try
      {
        this.zzuW.setAppVolume(paramFloat);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        zzb.zzb("Unable to set app volume.", localRemoteException);
      }
      bool1 = false;
      break;
    }
  }
  
  public void zza(Context paramContext, String paramString, zzae paramzzae)
  {
    initialize(paramContext);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\client\zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */