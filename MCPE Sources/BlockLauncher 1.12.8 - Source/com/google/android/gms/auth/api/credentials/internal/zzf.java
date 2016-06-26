package com.google.android.gms.auth.api.credentials.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.Auth.AuthCredentialsOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public final class zzf
  extends com.google.android.gms.common.internal.zzj<zzj>
{
  @Nullable
  private final Auth.AuthCredentialsOptions zzWA;
  
  public zzf(Context paramContext, Looper paramLooper, com.google.android.gms.common.internal.zzf paramzzf, Auth.AuthCredentialsOptions paramAuthCredentialsOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 68, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzWA = paramAuthCredentialsOptions;
  }
  
  protected zzj zzav(IBinder paramIBinder)
  {
    return zzj.zza.zzax(paramIBinder);
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.auth.api.credentials.service.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
  }
  
  Auth.AuthCredentialsOptions zzmD()
  {
    return this.zzWA;
  }
  
  protected Bundle zzml()
  {
    if (this.zzWA == null) {
      return new Bundle();
    }
    return this.zzWA.zzml();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\credentials\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */