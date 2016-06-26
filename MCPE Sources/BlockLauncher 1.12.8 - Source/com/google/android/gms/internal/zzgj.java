package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;

@zzhb
public final class zzgj
  extends zzg<zzgf>
{
  private static final zzgj zzGa = new zzgj();
  
  private zzgj()
  {
    super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
  }
  
  @Nullable
  public static zzge createInAppPurchaseManager(Activity paramActivity)
  {
    try
    {
      zzge localzzge1;
      if (!zzb(paramActivity))
      {
        zzge localzzge2 = zzGa.zzd(paramActivity);
        localzzge1 = localzzge2;
        if (localzzge2 != null) {}
      }
      else
      {
        zzin.zzaI("Using AdOverlay from the client jar.");
        localzzge1 = zzn.zzcU().createInAppPurchaseManager(paramActivity);
      }
      return localzzge1;
    }
    catch (zza paramActivity)
    {
      zzin.zzaK(paramActivity.getMessage());
    }
    return null;
  }
  
  private static boolean zzb(Activity paramActivity)
    throws zzgj.zza
  {
    paramActivity = paramActivity.getIntent();
    if (!paramActivity.hasExtra("com.google.android.gms.ads.internal.purchase.useClientJar")) {
      throw new zza("InAppPurchaseManager requires the useClientJar flag in intent extras.");
    }
    return paramActivity.getBooleanExtra("com.google.android.gms.ads.internal.purchase.useClientJar", false);
  }
  
  private zzge zzd(Activity paramActivity)
  {
    try
    {
      zzd localzzd = zze.zzC(paramActivity);
      paramActivity = zzge.zza.zzQ(((zzgf)zzaB(paramActivity)).zzf(localzzd));
      return paramActivity;
    }
    catch (RemoteException paramActivity)
    {
      zzin.zzd("Could not create remote InAppPurchaseManager.", paramActivity);
      return null;
    }
    catch (zzg.zza paramActivity)
    {
      zzin.zzd("Could not create remote InAppPurchaseManager.", paramActivity);
    }
    return null;
  }
  
  protected zzgf zzU(IBinder paramIBinder)
  {
    return zzgf.zza.zzR(paramIBinder);
  }
  
  private static final class zza
    extends Exception
  {
    public zza(String paramString)
    {
      super();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzgj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */