package com.google.android.gms.drive.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;

public class zzbu
  extends zzd
{
  private final zza.zzb<Status> zzamC;
  
  public zzbu(zza.zzb<Status> paramzzb)
  {
    this.zzamC = paramzzb;
  }
  
  public void onError(Status paramStatus)
    throws RemoteException
  {
    this.zzamC.zzs(paramStatus);
  }
  
  public void onSuccess()
    throws RemoteException
  {
    this.zzamC.zzs(Status.zzagC);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzbu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */