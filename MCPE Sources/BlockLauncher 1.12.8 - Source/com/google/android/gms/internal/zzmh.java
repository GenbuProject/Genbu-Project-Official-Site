package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;

public final class zzmh
  implements zzmg
{
  public PendingResult<Status> zzf(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zzb(new zzmi.zza(paramGoogleApiClient)
    {
      protected void zza(zzmj paramAnonymouszzmj)
        throws RemoteException
      {
        ((zzml)paramAnonymouszzmj.zzqJ()).zza(new zzmh.zza(this));
      }
    });
  }
  
  private static class zza
    extends zzme
  {
    private final zza.zzb<Status> zzamC;
    
    public zza(zza.zzb<Status> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void zzcb(int paramInt)
      throws RemoteException
    {
      this.zzamC.zzs(new Status(paramInt));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzmh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */