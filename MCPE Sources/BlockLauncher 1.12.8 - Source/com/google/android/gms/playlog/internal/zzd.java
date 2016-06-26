package com.google.android.gms.playlog.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.zzqu.zza;

public class zzd
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  private final zzqu.zza zzbdJ;
  private boolean zzbdK;
  private zzf zzbdy;
  
  public zzd(zzqu.zza paramzza)
  {
    this.zzbdJ = paramzza;
    this.zzbdy = null;
    this.zzbdK = true;
  }
  
  public void onConnected(Bundle paramBundle)
  {
    this.zzbdy.zzau(false);
    if ((this.zzbdK) && (this.zzbdJ != null)) {
      this.zzbdJ.zzES();
    }
    this.zzbdK = false;
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    this.zzbdy.zzau(true);
    if ((this.zzbdK) && (this.zzbdJ != null))
    {
      if (!paramConnectionResult.hasResolution()) {
        break label48;
      }
      this.zzbdJ.zzc(paramConnectionResult.getResolution());
    }
    for (;;)
    {
      this.zzbdK = false;
      return;
      label48:
      this.zzbdJ.zzET();
    }
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    this.zzbdy.zzau(true);
  }
  
  public void zza(zzf paramzzf)
  {
    this.zzbdy = paramzzf;
  }
  
  public void zzat(boolean paramBoolean)
  {
    this.zzbdK = paramBoolean;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\playlog\internal\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */