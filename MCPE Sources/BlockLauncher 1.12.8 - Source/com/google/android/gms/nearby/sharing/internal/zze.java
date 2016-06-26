package com.google.android.gms.nearby.sharing.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;

class zze
  extends zzj<zzd>
{
  public zze(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, zzf paramzzf)
  {
    super(paramContext, paramLooper, 49, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  protected zzd zzdJ(IBinder paramIBinder)
  {
    return zzd.zza.zzdI(paramIBinder);
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.nearby.sharing.service.NearbySharingService.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.nearby.sharing.internal.INearbySharingService";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\sharing\internal\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */