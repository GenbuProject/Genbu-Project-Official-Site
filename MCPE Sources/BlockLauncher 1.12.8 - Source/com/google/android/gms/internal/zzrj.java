package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;

public class zzrj
  extends zzj<zzri>
{
  public zzrj(Context paramContext, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, zzf paramzzf)
  {
    super(paramContext, paramContext.getMainLooper(), 73, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  protected zzri zzdZ(IBinder paramIBinder)
  {
    return zzri.zza.zzdY(paramIBinder);
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.search.service.SEARCH_AUTH_START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.search.internal.ISearchAuthService";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzrj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */