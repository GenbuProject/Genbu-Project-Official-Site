package com.google.android.gms.location.places;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.internal.zzh.zza;

public class zzf
  extends zzh.zza
{
  private final zzb zzaPw;
  private final zza zzaPx;
  
  public zzf(zza paramzza)
  {
    this.zzaPw = null;
    this.zzaPx = paramzza;
  }
  
  public zzf(zzb paramzzb)
  {
    this.zzaPw = paramzzb;
    this.zzaPx = null;
  }
  
  public void zza(PlacePhotoMetadataResult paramPlacePhotoMetadataResult)
    throws RemoteException
  {
    this.zzaPw.zza(paramPlacePhotoMetadataResult);
  }
  
  public void zza(PlacePhotoResult paramPlacePhotoResult)
    throws RemoteException
  {
    this.zzaPx.zza(paramPlacePhotoResult);
  }
  
  public static abstract class zza<A extends Api.zzb>
    extends zzl.zzb<PlacePhotoResult, A>
  {
    public zza(Api.zzc<A> paramzzc, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected PlacePhotoResult zzaS(Status paramStatus)
    {
      return new PlacePhotoResult(paramStatus, null);
    }
  }
  
  public static abstract class zzb<A extends Api.zzb>
    extends zzl.zzb<PlacePhotoMetadataResult, A>
  {
    public zzb(Api.zzc<A> paramzzc, GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
    
    protected PlacePhotoMetadataResult zzaT(Status paramStatus)
    {
      return new PlacePhotoMetadataResult(paramStatus, null);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */