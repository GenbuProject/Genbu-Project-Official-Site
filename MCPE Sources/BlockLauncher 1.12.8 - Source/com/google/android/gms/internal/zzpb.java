package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.fitness.ConfigApi;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.request.DataTypeReadRequest;
import com.google.android.gms.fitness.request.DisableFitRequest;
import com.google.android.gms.fitness.result.DataTypeResult;

public class zzpb
  implements ConfigApi
{
  public PendingResult<DataTypeResult> createCustomDataType(GoogleApiClient paramGoogleApiClient, final DataTypeCreateRequest paramDataTypeCreateRequest)
  {
    paramGoogleApiClient.zzb(new zzoa.zza(paramGoogleApiClient)
    {
      protected DataTypeResult zzJ(Status paramAnonymousStatus)
      {
        return DataTypeResult.zzS(paramAnonymousStatus);
      }
      
      protected void zza(zzoa paramAnonymouszzoa)
        throws RemoteException
      {
        zzpb.zza localzza = new zzpb.zza(this, null);
        ((zzol)paramAnonymouszzoa.zzqJ()).zza(new DataTypeCreateRequest(paramDataTypeCreateRequest, localzza));
      }
    });
  }
  
  public PendingResult<Status> disableFit(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zzb(new zzoa.zzc(paramGoogleApiClient)
    {
      protected void zza(zzoa paramAnonymouszzoa)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzol)paramAnonymouszzoa.zzqJ()).zza(new DisableFitRequest(localzzph));
      }
    });
  }
  
  public PendingResult<DataTypeResult> readDataType(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.zza(new zzoa.zza(paramGoogleApiClient)
    {
      protected DataTypeResult zzJ(Status paramAnonymousStatus)
      {
        return DataTypeResult.zzS(paramAnonymousStatus);
      }
      
      protected void zza(zzoa paramAnonymouszzoa)
        throws RemoteException
      {
        zzpb.zza localzza = new zzpb.zza(this, null);
        ((zzol)paramAnonymouszzoa.zzqJ()).zza(new DataTypeReadRequest(paramString, localzza));
      }
    });
  }
  
  private static class zza
    extends zzoj.zza
  {
    private final zza.zzb<DataTypeResult> zzamC;
    
    private zza(zza.zzb<DataTypeResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void zza(DataTypeResult paramDataTypeResult)
    {
      this.zzamC.zzs(paramDataTypeResult);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzpb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */