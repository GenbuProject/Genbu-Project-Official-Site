package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.proxy.ProxyApi.ProxyResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zza;

abstract class zzlc
  extends zza.zza<ProxyApi.ProxyResult, zzkz>
{
  public zzlc(GoogleApiClient paramGoogleApiClient)
  {
    super(Auth.zzVt, paramGoogleApiClient);
  }
  
  protected abstract void zza(Context paramContext, zzlb paramzzlb)
    throws RemoteException;
  
  protected final void zza(zzkz paramzzkz)
    throws RemoteException
  {
    zza(paramzzkz.getContext(), (zzlb)paramzzkz.zzqJ());
  }
  
  protected ProxyApi.ProxyResult zzj(Status paramStatus)
  {
    return new zzle(paramStatus);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzlc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */