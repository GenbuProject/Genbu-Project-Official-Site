package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationStatusCodes;
import java.util.List;

public class zzl
  extends zzb
{
  private final zzk zzaOM = new zzk(paramContext, this.zzaOt);
  
  public zzl(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString)
  {
    this(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramString, zzf.zzat(paramContext));
  }
  
  public zzl(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, zzf paramzzf)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramString, paramzzf);
  }
  
  public void disconnect()
  {
    synchronized (this.zzaOM)
    {
      boolean bool = isConnected();
      if (bool) {}
      try
      {
        this.zzaOM.removeAllListeners();
        this.zzaOM.zzyP();
        super.disconnect();
        return;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", localException);
        }
      }
    }
  }
  
  public Location getLastLocation()
  {
    return this.zzaOM.getLastLocation();
  }
  
  public void zza(long paramLong, PendingIntent paramPendingIntent)
    throws RemoteException
  {
    zzqI();
    zzx.zzz(paramPendingIntent);
    if (paramLong >= 0L) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "detectionIntervalMillis must be >= 0");
      ((zzi)zzqJ()).zza(paramLong, true, paramPendingIntent);
      return;
    }
  }
  
  public void zza(PendingIntent paramPendingIntent)
    throws RemoteException
  {
    zzqI();
    zzx.zzz(paramPendingIntent);
    ((zzi)zzqJ()).zza(paramPendingIntent);
  }
  
  public void zza(PendingIntent paramPendingIntent, zza.zzb<Status> paramzzb)
    throws RemoteException
  {
    zzqI();
    zzx.zzb(paramPendingIntent, "PendingIntent must be specified.");
    zzx.zzb(paramzzb, "ResultHolder not provided.");
    paramzzb = new zzb(paramzzb);
    ((zzi)zzqJ()).zza(paramPendingIntent, paramzzb, getContext().getPackageName());
  }
  
  public void zza(PendingIntent paramPendingIntent, zzg paramzzg)
    throws RemoteException
  {
    this.zzaOM.zza(paramPendingIntent, paramzzg);
  }
  
  public void zza(GeofencingRequest paramGeofencingRequest, PendingIntent paramPendingIntent, zza.zzb<Status> paramzzb)
    throws RemoteException
  {
    zzqI();
    zzx.zzb(paramGeofencingRequest, "geofencingRequest can't be null.");
    zzx.zzb(paramPendingIntent, "PendingIntent must be specified.");
    zzx.zzb(paramzzb, "ResultHolder not provided.");
    paramzzb = new zza(paramzzb);
    ((zzi)zzqJ()).zza(paramGeofencingRequest, paramPendingIntent, paramzzb);
  }
  
  public void zza(LocationCallback paramLocationCallback, zzg paramzzg)
    throws RemoteException
  {
    this.zzaOM.zza(paramLocationCallback, paramzzg);
  }
  
  public void zza(LocationListener paramLocationListener, zzg paramzzg)
    throws RemoteException
  {
    this.zzaOM.zza(paramLocationListener, paramzzg);
  }
  
  public void zza(LocationRequest paramLocationRequest, PendingIntent paramPendingIntent, zzg paramzzg)
    throws RemoteException
  {
    this.zzaOM.zza(paramLocationRequest, paramPendingIntent, paramzzg);
  }
  
  public void zza(LocationRequest paramLocationRequest, LocationListener paramLocationListener, Looper paramLooper, zzg paramzzg)
    throws RemoteException
  {
    synchronized (this.zzaOM)
    {
      this.zzaOM.zza(paramLocationRequest, paramLocationListener, paramLooper, paramzzg);
      return;
    }
  }
  
  public void zza(LocationSettingsRequest paramLocationSettingsRequest, zza.zzb<LocationSettingsResult> paramzzb, String paramString)
    throws RemoteException
  {
    boolean bool2 = true;
    zzqI();
    if (paramLocationSettingsRequest != null)
    {
      bool1 = true;
      zzx.zzb(bool1, "locationSettingsRequest can't be null nor empty.");
      if (paramzzb == null) {
        break label67;
      }
    }
    label67:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzx.zzb(bool1, "listener can't be null.");
      paramzzb = new zzc(paramzzb);
      ((zzi)zzqJ()).zza(paramLocationSettingsRequest, paramzzb, paramString);
      return;
      bool1 = false;
      break;
    }
  }
  
  public void zza(LocationRequestInternal paramLocationRequestInternal, LocationCallback paramLocationCallback, Looper paramLooper, zzg paramzzg)
    throws RemoteException
  {
    synchronized (this.zzaOM)
    {
      this.zzaOM.zza(paramLocationRequestInternal, paramLocationCallback, paramLooper, paramzzg);
      return;
    }
  }
  
  public void zza(zzg paramzzg)
    throws RemoteException
  {
    this.zzaOM.zza(paramzzg);
  }
  
  public void zza(List<String> paramList, zza.zzb<Status> paramzzb)
    throws RemoteException
  {
    zzqI();
    if ((paramList != null) && (paramList.size() > 0)) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "geofenceRequestIds can't be null nor empty.");
      zzx.zzb(paramzzb, "ResultHolder not provided.");
      paramList = (String[])paramList.toArray(new String[0]);
      paramzzb = new zzb(paramzzb);
      ((zzi)zzqJ()).zza(paramList, paramzzb, getContext().getPackageName());
      return;
    }
  }
  
  public void zzam(boolean paramBoolean)
    throws RemoteException
  {
    this.zzaOM.zzam(paramBoolean);
  }
  
  public void zzc(Location paramLocation)
    throws RemoteException
  {
    this.zzaOM.zzc(paramLocation);
  }
  
  public LocationAvailability zzyO()
  {
    return this.zzaOM.zzyO();
  }
  
  private static final class zza
    extends zzh.zza
  {
    private zza.zzb<Status> zzaON;
    
    public zza(zza.zzb<Status> paramzzb)
    {
      this.zzaON = paramzzb;
    }
    
    public void zza(int paramInt, PendingIntent paramPendingIntent)
    {
      Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByPendingIntentResult");
    }
    
    public void zza(int paramInt, String[] paramArrayOfString)
    {
      if (this.zzaON == null)
      {
        Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
        return;
      }
      paramArrayOfString = LocationStatusCodes.zzhz(LocationStatusCodes.zzhy(paramInt));
      this.zzaON.zzs(paramArrayOfString);
      this.zzaON = null;
    }
    
    public void zzb(int paramInt, String[] paramArrayOfString)
    {
      Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByRequestIdsResult");
    }
  }
  
  private static final class zzb
    extends zzh.zza
  {
    private zza.zzb<Status> zzaON;
    
    public zzb(zza.zzb<Status> paramzzb)
    {
      this.zzaON = paramzzb;
    }
    
    private void zzhC(int paramInt)
    {
      if (this.zzaON == null)
      {
        Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times");
        return;
      }
      Status localStatus = LocationStatusCodes.zzhz(LocationStatusCodes.zzhy(paramInt));
      this.zzaON.zzs(localStatus);
      this.zzaON = null;
    }
    
    public void zza(int paramInt, PendingIntent paramPendingIntent)
    {
      zzhC(paramInt);
    }
    
    public void zza(int paramInt, String[] paramArrayOfString)
    {
      Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult");
    }
    
    public void zzb(int paramInt, String[] paramArrayOfString)
    {
      zzhC(paramInt);
    }
  }
  
  private static final class zzc
    extends zzj.zza
  {
    private zza.zzb<LocationSettingsResult> zzaON;
    
    public zzc(zza.zzb<LocationSettingsResult> paramzzb)
    {
      if (paramzzb != null) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "listener can't be null.");
        this.zzaON = paramzzb;
        return;
      }
    }
    
    public void zza(LocationSettingsResult paramLocationSettingsResult)
      throws RemoteException
    {
      this.zzaON.zzs(paramLocationSettingsResult);
      this.zzaON = null;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\internal\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */