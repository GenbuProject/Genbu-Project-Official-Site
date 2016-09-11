package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.fitness.SensorsApi;
import com.google.android.gms.fitness.data.zzk;
import com.google.android.gms.fitness.data.zzl;
import com.google.android.gms.fitness.data.zzl.zza;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRegistrationRequest;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.SensorUnregistrationRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;

public class zzpf
  implements SensorsApi
{
  private PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, final zzk paramzzk, final PendingIntent paramPendingIntent, final zza paramzza)
  {
    paramGoogleApiClient.zzb(new zzoe.zzc(paramGoogleApiClient)
    {
      protected void zza(zzoe paramAnonymouszzoe)
        throws RemoteException
      {
        zzpf.zzc localzzc = new zzpf.zzc(this, paramzza, null);
        ((zzop)paramAnonymouszzoe.zzqJ()).zza(new SensorUnregistrationRequest(paramzzk, paramPendingIntent, localzzc));
      }
      
      protected Status zzb(Status paramAnonymousStatus)
      {
        return paramAnonymousStatus;
      }
    });
  }
  
  private PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, final SensorRequest paramSensorRequest, final zzk paramzzk, final PendingIntent paramPendingIntent)
  {
    paramGoogleApiClient.zza(new zzoe.zzc(paramGoogleApiClient)
    {
      protected void zza(zzoe paramAnonymouszzoe)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzop)paramAnonymouszzoe.zzqJ()).zza(new SensorRegistrationRequest(paramSensorRequest, paramzzk, paramPendingIntent, localzzph));
      }
      
      protected Status zzb(Status paramAnonymousStatus)
      {
        return paramAnonymousStatus;
      }
    });
  }
  
  public PendingResult<Status> add(GoogleApiClient paramGoogleApiClient, SensorRequest paramSensorRequest, PendingIntent paramPendingIntent)
  {
    return zza(paramGoogleApiClient, paramSensorRequest, null, paramPendingIntent);
  }
  
  public PendingResult<Status> add(GoogleApiClient paramGoogleApiClient, SensorRequest paramSensorRequest, OnDataPointListener paramOnDataPointListener)
  {
    return zza(paramGoogleApiClient, paramSensorRequest, zzl.zza.zzuu().zza(paramOnDataPointListener), null);
  }
  
  public PendingResult<DataSourcesResult> findDataSources(GoogleApiClient paramGoogleApiClient, final DataSourcesRequest paramDataSourcesRequest)
  {
    paramGoogleApiClient.zza(new zzoe.zza(paramGoogleApiClient)
    {
      protected DataSourcesResult zzN(Status paramAnonymousStatus)
      {
        return DataSourcesResult.zzR(paramAnonymousStatus);
      }
      
      protected void zza(zzoe paramAnonymouszzoe)
        throws RemoteException
      {
        zzpf.zzb localzzb = new zzpf.zzb(this, null);
        ((zzop)paramAnonymouszzoe.zzqJ()).zza(new DataSourcesRequest(paramDataSourcesRequest, localzzb));
      }
    });
  }
  
  public PendingResult<Status> remove(GoogleApiClient paramGoogleApiClient, PendingIntent paramPendingIntent)
  {
    return zza(paramGoogleApiClient, null, paramPendingIntent, null);
  }
  
  public PendingResult<Status> remove(GoogleApiClient paramGoogleApiClient, final OnDataPointListener paramOnDataPointListener)
  {
    zzl localzzl = zzl.zza.zzuu().zzb(paramOnDataPointListener);
    if (localzzl == null) {
      return PendingResults.zza(Status.zzagC, paramGoogleApiClient);
    }
    zza(paramGoogleApiClient, localzzl, null, new zza()
    {
      public void zzuI()
      {
        zzl.zza.zzuu().zzc(paramOnDataPointListener);
      }
    });
  }
  
  private static abstract interface zza
  {
    public abstract void zzuI();
  }
  
  private static class zzb
    extends zzoi.zza
  {
    private final zza.zzb<DataSourcesResult> zzamC;
    
    private zzb(zza.zzb<DataSourcesResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void zza(DataSourcesResult paramDataSourcesResult)
    {
      this.zzamC.zzs(paramDataSourcesResult);
    }
  }
  
  private static class zzc
    extends zzow.zza
  {
    private final zzpf.zza zzaAq;
    private final zza.zzb<Status> zzamC;
    
    private zzc(zza.zzb<Status> paramzzb, zzpf.zza paramzza)
    {
      this.zzamC = paramzzb;
      this.zzaAq = paramzza;
    }
    
    public void zzp(Status paramStatus)
    {
      if ((this.zzaAq != null) && (paramStatus.isSuccess())) {
        this.zzaAq.zzuI();
      }
      this.zzamC.zzs(paramStatus);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzpf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */