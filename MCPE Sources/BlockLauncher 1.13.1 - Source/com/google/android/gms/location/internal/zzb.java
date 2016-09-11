package com.google.android.gms.location.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;

public class zzb
  extends zzj<zzi>
{
  private final String zzaOs;
  protected final zzp<zzi> zzaOt = new zzp()
  {
    public void zzqI()
    {
      zzb.zza(zzb.this);
    }
    
    public zzi zzyM()
      throws DeadObjectException
    {
      return (zzi)zzb.this.zzqJ();
    }
  };
  
  public zzb(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, zzf paramzzf)
  {
    super(paramContext, paramLooper, 23, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzaOs = paramString;
  }
  
  protected zzi zzcg(IBinder paramIBinder)
  {
    return zzi.zza.zzcj(paramIBinder);
  }
  
  protected String zzgu()
  {
    return "com.google.android.location.internal.GoogleLocationManagerService.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
  }
  
  protected Bundle zzml()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("client_name", this.zzaOs);
    return localBundle;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */