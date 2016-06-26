package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;
import com.google.android.gms.internal.zzhb;

@zzhb
public class zzaf
  extends zzg<zzz>
{
  public zzaf()
  {
    super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
  }
  
  private zzy zzv(Context paramContext)
  {
    try
    {
      zzd localzzd = zze.zzC(paramContext);
      paramContext = zzy.zza.zzo(((zzz)zzaB(paramContext)).zza(localzzd, 8487000));
      return paramContext;
    }
    catch (RemoteException paramContext)
    {
      zzb.zzd("Could not get remote MobileAdsSettingManager.", paramContext);
      return null;
    }
    catch (zzg.zza paramContext)
    {
      zzb.zzd("Could not get remote MobileAdsSettingManager.", paramContext);
    }
    return null;
  }
  
  protected zzz zzq(IBinder paramIBinder)
  {
    return zzz.zza.zzp(paramIBinder);
  }
  
  public zzy zzu(Context paramContext)
  {
    zzy localzzy1;
    if (zzn.zzcS().zzU(paramContext))
    {
      zzy localzzy2 = zzv(paramContext);
      localzzy1 = localzzy2;
      if (localzzy2 != null) {}
    }
    else
    {
      zzb.zzaI("Using MobileAdsSettingManager from the client jar.");
      new VersionInfoParcel(8487000, 8487000, true);
      localzzy1 = zzn.zzcU().getMobileAdsSettingsManager(paramContext);
    }
    return localzzy1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\client\zzaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */