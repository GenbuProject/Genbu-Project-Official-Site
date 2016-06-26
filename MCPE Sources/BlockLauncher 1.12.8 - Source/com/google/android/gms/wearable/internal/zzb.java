package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.internal.zzx;

final class zzb<T>
  extends zzi<Status>
{
  private T mListener;
  private zzq<T> zzbbi;
  private zza<T> zzbrA;
  
  private zzb(GoogleApiClient paramGoogleApiClient, T paramT, zzq<T> paramzzq, zza<T> paramzza)
  {
    super(paramGoogleApiClient);
    this.mListener = zzx.zzz(paramT);
    this.zzbbi = ((zzq)zzx.zzz(paramzzq));
    this.zzbrA = ((zza)zzx.zzz(paramzza));
  }
  
  static <T> PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, zza<T> paramzza, T paramT)
  {
    return paramGoogleApiClient.zza(new zzb(paramGoogleApiClient, paramT, paramGoogleApiClient.zzr(paramT), paramzza));
  }
  
  protected void zza(zzbp paramzzbp)
    throws RemoteException
  {
    this.zzbrA.zza(paramzzbp, this, this.mListener, this.zzbbi);
    this.mListener = null;
    this.zzbbi = null;
  }
  
  protected Status zzb(Status paramStatus)
  {
    this.mListener = null;
    this.zzbbi = null;
    return paramStatus;
  }
  
  static abstract interface zza<T>
  {
    public abstract void zza(zzbp paramzzbp, zza.zzb<Status> paramzzb, T paramT, zzq<T> paramzzq)
      throws RemoteException;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */