package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.fitness.RecordingApi;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.data.Subscription.zza;
import com.google.android.gms.fitness.request.ListSubscriptionsRequest;
import com.google.android.gms.fitness.request.SubscribeRequest;
import com.google.android.gms.fitness.request.UnsubscribeRequest;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

public class zzpe
  implements RecordingApi
{
  private PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, final Subscription paramSubscription)
  {
    paramGoogleApiClient.zza(new zzod.zzc(paramGoogleApiClient)
    {
      protected void zza(zzod paramAnonymouszzod)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzoo)paramAnonymouszzod.zzqJ()).zza(new SubscribeRequest(paramSubscription, false, localzzph));
      }
    });
  }
  
  public PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zza(new zzod.zza(paramGoogleApiClient)
    {
      protected ListSubscriptionsResult zzM(Status paramAnonymousStatus)
      {
        return ListSubscriptionsResult.zzT(paramAnonymousStatus);
      }
      
      protected void zza(zzod paramAnonymouszzod)
        throws RemoteException
      {
        zzpe.zza localzza = new zzpe.zza(this, null);
        ((zzoo)paramAnonymouszzod.zzqJ()).zza(new ListSubscriptionsRequest(null, localzza));
      }
    });
  }
  
  public PendingResult<ListSubscriptionsResult> listSubscriptions(GoogleApiClient paramGoogleApiClient, final DataType paramDataType)
  {
    paramGoogleApiClient.zza(new zzod.zza(paramGoogleApiClient)
    {
      protected ListSubscriptionsResult zzM(Status paramAnonymousStatus)
      {
        return ListSubscriptionsResult.zzT(paramAnonymousStatus);
      }
      
      protected void zza(zzod paramAnonymouszzod)
        throws RemoteException
      {
        zzpe.zza localzza = new zzpe.zza(this, null);
        ((zzoo)paramAnonymouszzod.zzqJ()).zza(new ListSubscriptionsRequest(paramDataType, localzza));
      }
    });
  }
  
  public PendingResult<Status> subscribe(GoogleApiClient paramGoogleApiClient, DataSource paramDataSource)
  {
    return zza(paramGoogleApiClient, new Subscription.zza().zzb(paramDataSource).zzuz());
  }
  
  public PendingResult<Status> subscribe(GoogleApiClient paramGoogleApiClient, DataType paramDataType)
  {
    return zza(paramGoogleApiClient, new Subscription.zza().zzb(paramDataType).zzuz());
  }
  
  public PendingResult<Status> unsubscribe(GoogleApiClient paramGoogleApiClient, final DataSource paramDataSource)
  {
    paramGoogleApiClient.zzb(new zzod.zzc(paramGoogleApiClient)
    {
      protected void zza(zzod paramAnonymouszzod)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzoo)paramAnonymouszzod.zzqJ()).zza(new UnsubscribeRequest(null, paramDataSource, localzzph));
      }
    });
  }
  
  public PendingResult<Status> unsubscribe(GoogleApiClient paramGoogleApiClient, final DataType paramDataType)
  {
    paramGoogleApiClient.zzb(new zzod.zzc(paramGoogleApiClient)
    {
      protected void zza(zzod paramAnonymouszzod)
        throws RemoteException
      {
        zzph localzzph = new zzph(this);
        ((zzoo)paramAnonymouszzod.zzqJ()).zza(new UnsubscribeRequest(paramDataType, null, localzzph));
      }
    });
  }
  
  public PendingResult<Status> unsubscribe(GoogleApiClient paramGoogleApiClient, Subscription paramSubscription)
  {
    if (paramSubscription.getDataType() == null) {
      return unsubscribe(paramGoogleApiClient, paramSubscription.getDataSource());
    }
    return unsubscribe(paramGoogleApiClient, paramSubscription.getDataType());
  }
  
  private static class zza
    extends zzor.zza
  {
    private final zza.zzb<ListSubscriptionsResult> zzamC;
    
    private zza(zza.zzb<ListSubscriptionsResult> paramzzb)
    {
      this.zzamC = paramzzb;
    }
    
    public void zza(ListSubscriptionsResult paramListSubscriptionsResult)
    {
      this.zzamC.zzs(paramListSubscriptionsResult);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzpe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */