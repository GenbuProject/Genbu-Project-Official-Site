package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;

final class zzp
  extends zze.zza
{
  private final zza.zzb<Status> zzbcS;
  
  private zzp(zza.zzb<Status> paramzzb)
  {
    this.zzbcS = paramzzb;
  }
  
  static zzp zzn(zza.zzb<Status> paramzzb)
  {
    return new zzp(paramzzb);
  }
  
  public void zzbb(Status paramStatus)
    throws RemoteException
  {
    this.zzbcS.zzs(paramStatus);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\messages\internal\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */