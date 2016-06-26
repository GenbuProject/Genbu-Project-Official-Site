package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzhb;

@zzhb
public final class zzd
  extends zzg<zzt>
{
  private static final zzd zztB = new zzd();
  
  private zzd()
  {
    super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
  }
  
  public static zzs zza(Context paramContext, String paramString, zzew paramzzew)
  {
    Object localObject;
    if (zzn.zzcS().zzU(paramContext))
    {
      zzs localzzs = zztB.zzb(paramContext, paramString, paramzzew);
      localObject = localzzs;
      if (localzzs != null) {}
    }
    else
    {
      zzb.zzaI("Using AdLoader from the client jar.");
      localObject = new VersionInfoParcel(8487000, 8487000, true);
      localObject = zzn.zzcU().createAdLoaderBuilder(paramContext, paramString, paramzzew, (VersionInfoParcel)localObject);
    }
    return (zzs)localObject;
  }
  
  private zzs zzb(Context paramContext, String paramString, zzew paramzzew)
  {
    try
    {
      com.google.android.gms.dynamic.zzd localzzd = zze.zzC(paramContext);
      paramContext = zzs.zza.zzi(((zzt)zzaB(paramContext)).zza(localzzd, paramString, paramzzew, 8487000));
      return paramContext;
    }
    catch (RemoteException paramContext)
    {
      zzb.zzd("Could not create remote builder for AdLoader.", paramContext);
      return null;
    }
    catch (zzg.zza paramContext)
    {
      for (;;)
      {
        zzb.zzd("Could not create remote builder for AdLoader.", paramContext);
      }
    }
  }
  
  protected zzt zzc(IBinder paramIBinder)
  {
    return zzt.zza.zzj(paramIBinder);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\client\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */