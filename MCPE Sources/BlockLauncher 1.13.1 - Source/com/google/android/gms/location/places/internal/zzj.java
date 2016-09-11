package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.zzl;
import com.google.android.gms.location.places.zzl.zzd;
import com.google.android.gms.location.places.zzl.zzf;

public class zzj
  implements PlaceDetectionApi
{
  public PendingResult<PlaceLikelihoodBuffer> getCurrentPlace(GoogleApiClient paramGoogleApiClient, final PlaceFilter paramPlaceFilter)
  {
    paramGoogleApiClient.zza(new zzl.zzd(Places.zzaPO, paramGoogleApiClient)
    {
      protected void zza(zzk paramAnonymouszzk)
        throws RemoteException
      {
        paramAnonymouszzk.zza(new zzl(this, paramAnonymouszzk.getContext()), paramPlaceFilter);
      }
    });
  }
  
  public PendingResult<Status> reportDeviceAtPlace(GoogleApiClient paramGoogleApiClient, final PlaceReport paramPlaceReport)
  {
    paramGoogleApiClient.zzb(new zzl.zzf(Places.zzaPO, paramGoogleApiClient)
    {
      protected void zza(zzk paramAnonymouszzk)
        throws RemoteException
      {
        paramAnonymouszzk.zza(new zzl(this), paramPlaceReport);
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\internal\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */