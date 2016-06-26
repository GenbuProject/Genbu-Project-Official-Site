package com.google.android.gms.auth.api.credentials.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zza.zza;

abstract class zze<R extends Result>
  extends zza.zza<R, zzf>
{
  zze(GoogleApiClient paramGoogleApiClient)
  {
    super(Auth.zzVu, paramGoogleApiClient);
  }
  
  protected abstract void zza(Context paramContext, zzj paramzzj)
    throws DeadObjectException, RemoteException;
  
  protected final void zza(zzf paramzzf)
    throws DeadObjectException, RemoteException
  {
    zza(paramzzf.getContext(), (zzj)paramzzf.zzqJ());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\credentials\internal\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */