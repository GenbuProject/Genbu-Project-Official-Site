package com.google.android.gms.ads.internal.reward.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzhb;

@zzhb
public class zzf
  extends zzg<zzc>
{
  public zzf()
  {
    super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
  }
  
  private zzb zzb(Context paramContext, zzew paramzzew)
  {
    try
    {
      zzd localzzd = zze.zzC(paramContext);
      paramContext = zzb.zza.zzaa(((zzc)zzaB(paramContext)).zza(localzzd, paramzzew, 8487000));
      return paramContext;
    }
    catch (zzg.zza paramContext)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not get remote RewardedVideoAd.", paramContext);
      return null;
    }
    catch (RemoteException paramContext)
    {
      for (;;) {}
    }
  }
  
  public zzb zza(Context paramContext, zzew paramzzew)
  {
    Object localObject;
    if (zzn.zzcS().zzU(paramContext))
    {
      zzb localzzb = zzb(paramContext, paramzzew);
      localObject = localzzb;
      if (localzzb != null) {}
    }
    else
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaI("Using RewardedVideoAd from the client jar.");
      localObject = new VersionInfoParcel(8487000, 8487000, true);
      localObject = zzn.zzcU().createRewardedVideoAd(paramContext, paramzzew, (VersionInfoParcel)localObject);
    }
    return (zzb)localObject;
  }
  
  protected zzc zzad(IBinder paramIBinder)
  {
    return zzc.zza.zzab(paramIBinder);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\reward\client\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */