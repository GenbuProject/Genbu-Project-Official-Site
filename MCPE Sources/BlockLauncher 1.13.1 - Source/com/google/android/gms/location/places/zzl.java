package com.google.android.gms.location.places;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zza;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzng;
import com.google.android.gms.location.places.internal.zzi.zza;
import com.google.android.gms.location.places.personalized.zzd;

public class zzl
  extends zzi.zza
{
  private static final String TAG = zzl.class.getSimpleName();
  private final Context mContext;
  private final zzd zzaPP;
  private final zza zzaPQ;
  private final zze zzaPR;
  private final zzf zzaPS;
  private final zzc zzaPT;
  
  public zzl(zza paramzza)
  {
    this.zzaPP = null;
    this.zzaPQ = paramzza;
    this.zzaPR = null;
    this.zzaPS = null;
    this.zzaPT = null;
    this.mContext = null;
  }
  
  public zzl(zzc paramzzc, Context paramContext)
  {
    this.zzaPP = null;
    this.zzaPQ = null;
    this.zzaPR = null;
    this.zzaPS = null;
    this.zzaPT = paramzzc;
    this.mContext = paramContext.getApplicationContext();
  }
  
  public zzl(zzd paramzzd, Context paramContext)
  {
    this.zzaPP = paramzzd;
    this.zzaPQ = null;
    this.zzaPR = null;
    this.zzaPS = null;
    this.zzaPT = null;
    this.mContext = paramContext.getApplicationContext();
  }
  
  public zzl(zzf paramzzf)
  {
    this.zzaPP = null;
    this.zzaPQ = null;
    this.zzaPR = null;
    this.zzaPS = paramzzf;
    this.zzaPT = null;
    this.mContext = null;
  }
  
  public void zzaU(Status paramStatus)
    throws RemoteException
  {
    this.zzaPS.zza(paramStatus);
  }
  
  public void zzac(DataHolder paramDataHolder)
    throws RemoteException
  {
    if (this.zzaPP != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "placeEstimator cannot be null");
      if (paramDataHolder != null) {
        break;
      }
      if (Log.isLoggable(TAG, 6)) {
        Log.e(TAG, "onPlaceEstimated received null DataHolder: " + zzng.zzso());
      }
      this.zzaPP.zzw(Status.zzagE);
      return;
    }
    Bundle localBundle = paramDataHolder.zzpZ();
    if (localBundle == null) {}
    for (int i = 100;; i = PlaceLikelihoodBuffer.zzH(localBundle))
    {
      paramDataHolder = new PlaceLikelihoodBuffer(paramDataHolder, i, this.mContext);
      this.zzaPP.zza(paramDataHolder);
      return;
    }
  }
  
  public void zzad(DataHolder paramDataHolder)
    throws RemoteException
  {
    if (paramDataHolder == null)
    {
      if (Log.isLoggable(TAG, 6)) {
        Log.e(TAG, "onAutocompletePrediction received null DataHolder: " + zzng.zzso());
      }
      this.zzaPQ.zzw(Status.zzagE);
      return;
    }
    this.zzaPQ.zza(new AutocompletePredictionBuffer(paramDataHolder));
  }
  
  public void zzae(DataHolder paramDataHolder)
    throws RemoteException
  {
    if (paramDataHolder == null)
    {
      if (Log.isLoggable(TAG, 6)) {
        Log.e(TAG, "onPlaceUserDataFetched received null DataHolder: " + zzng.zzso());
      }
      this.zzaPR.zzw(Status.zzagE);
      return;
    }
    this.zzaPR.zza(new zzd(paramDataHolder));
  }
  
  public void zzaf(DataHolder paramDataHolder)
    throws RemoteException
  {
    paramDataHolder = new PlaceBuffer(paramDataHolder, this.mContext);
    this.zzaPT.zza(paramDataHolder);
  }
  
  public static abstract class zza<A extends Api.zzb>
    extends zzl.zzb<AutocompletePredictionBuffer, A>
  {
    public zza(Api.zzc<A> paramzzc, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected AutocompletePredictionBuffer zzaV(Status paramStatus)
    {
      return new AutocompletePredictionBuffer(DataHolder.zzbI(paramStatus.getStatusCode()));
    }
  }
  
  public static abstract class zzb<R extends Result, A extends Api.zzb>
    extends zza.zza<R, A>
  {
    public zzb(Api.zzc<A> paramzzc, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
  
  public static abstract class zzc<A extends Api.zzb>
    extends zzl.zzb<PlaceBuffer, A>
  {
    public zzc(Api.zzc<A> paramzzc, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected PlaceBuffer zzaW(Status paramStatus)
    {
      return new PlaceBuffer(DataHolder.zzbI(paramStatus.getStatusCode()), null);
    }
  }
  
  public static abstract class zzd<A extends Api.zzb>
    extends zzl.zzb<PlaceLikelihoodBuffer, A>
  {
    public zzd(Api.zzc<A> paramzzc, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected PlaceLikelihoodBuffer zzaX(Status paramStatus)
    {
      return new PlaceLikelihoodBuffer(DataHolder.zzbI(paramStatus.getStatusCode()), 100, null);
    }
  }
  
  public static abstract class zze<A extends Api.zzb>
    extends zzl.zzb<zzd, A>
  {
    protected zzd zzaY(Status paramStatus)
    {
      return zzd.zzaZ(paramStatus);
    }
  }
  
  public static abstract class zzf<A extends Api.zzb>
    extends zzl.zzb<Status, A>
  {
    public zzf(Api.zzc<A> paramzzc, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected Status zzb(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */