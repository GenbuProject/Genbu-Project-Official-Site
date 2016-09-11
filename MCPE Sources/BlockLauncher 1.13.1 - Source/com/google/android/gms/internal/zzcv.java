package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

@zzhb
public class zzcv
  extends zzg<zzck>
{
  public zzcv()
  {
    super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
  }
  
  private zzcj zzb(Context paramContext, FrameLayout paramFrameLayout1, FrameLayout paramFrameLayout2)
  {
    try
    {
      zzd localzzd = zze.zzC(paramContext);
      paramFrameLayout1 = zze.zzC(paramFrameLayout1);
      paramFrameLayout2 = zze.zzC(paramFrameLayout2);
      paramContext = zzcj.zza.zzu(((zzck)zzaB(paramContext)).zza(localzzd, paramFrameLayout1, paramFrameLayout2, 8487000));
      return paramContext;
    }
    catch (zzg.zza paramContext)
    {
      zzb.zzd("Could not create remote NativeAdViewDelegate.", paramContext);
      return null;
    }
    catch (RemoteException paramContext)
    {
      for (;;) {}
    }
  }
  
  protected zzck zzD(IBinder paramIBinder)
  {
    return zzck.zza.zzv(paramIBinder);
  }
  
  public zzcj zza(Context paramContext, FrameLayout paramFrameLayout1, FrameLayout paramFrameLayout2)
  {
    if (zzn.zzcS().zzU(paramContext))
    {
      zzcj localzzcj = zzb(paramContext, paramFrameLayout1, paramFrameLayout2);
      paramContext = localzzcj;
      if (localzzcj != null) {}
    }
    else
    {
      zzb.zzaI("Using NativeAdViewDelegate from the client jar.");
      paramContext = zzn.zzcU().createNativeAdViewDelegate(paramFrameLayout1, paramFrameLayout2);
    }
    return paramContext;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzcv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */