package com.google.android.gms.location.places.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlacesOptions;
import com.google.android.gms.location.places.PlacesOptions.Builder;
import com.google.android.gms.location.places.zzl;
import java.util.Locale;

public class zzk
  extends zzj<zzf>
{
  private final PlacesParams zzaQq;
  private final Locale zzaQr = Locale.getDefault();
  
  public zzk(Context paramContext, Looper paramLooper, com.google.android.gms.common.internal.zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, PlacesOptions paramPlacesOptions)
  {
    super(paramContext, paramLooper, 67, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    paramContext = null;
    if (paramzzf.getAccount() != null) {
      paramContext = paramzzf.getAccount().name;
    }
    this.zzaQq = new PlacesParams(paramString, this.zzaQr, paramContext, paramPlacesOptions.zzaPU, paramPlacesOptions.zzaPV);
  }
  
  public void zza(zzl paramzzl, PlaceFilter paramPlaceFilter)
    throws RemoteException
  {
    PlaceFilter localPlaceFilter = paramPlaceFilter;
    if (paramPlaceFilter == null) {
      localPlaceFilter = PlaceFilter.zzzd();
    }
    ((zzf)zzqJ()).zza(localPlaceFilter, this.zzaQq, paramzzl);
  }
  
  public void zza(zzl paramzzl, PlaceReport paramPlaceReport)
    throws RemoteException
  {
    zzx.zzz(paramPlaceReport);
    ((zzf)zzqJ()).zza(paramPlaceReport, this.zzaQq, paramzzl);
  }
  
  protected zzf zzcq(IBinder paramIBinder)
  {
    return zzf.zza.zzcm(paramIBinder);
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.location.places.PlaceDetectionApi";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.location.places.internal.IGooglePlaceDetectionService";
  }
  
  public static class zza
    extends Api.zza<zzk, PlacesOptions>
  {
    private final String zzaQs;
    
    public zza(String paramString)
    {
      this.zzaQs = paramString;
    }
    
    public zzk zzb(Context paramContext, Looper paramLooper, com.google.android.gms.common.internal.zzf paramzzf, PlacesOptions paramPlacesOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      String str;
      if (this.zzaQs != null)
      {
        str = this.zzaQs;
        if (paramPlacesOptions != null) {
          break label58;
        }
        paramPlacesOptions = new PlacesOptions.Builder().build();
      }
      label58:
      for (;;)
      {
        return new zzk(paramContext, paramLooper, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener, str, paramPlacesOptions);
        str = paramContext.getPackageName();
        break;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\internal\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */