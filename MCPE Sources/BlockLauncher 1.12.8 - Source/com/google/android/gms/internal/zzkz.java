package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.Auth.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import java.util.Set;

public final class zzkz
  extends zzj<zzlb>
{
  private final Bundle zzVN;
  
  public zzkz(Context paramContext, Looper paramLooper, zzf paramzzf, Auth.zza paramzza, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 16, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    if (paramzza == null) {}
    for (paramContext = new Bundle();; paramContext = paramzza.zzms())
    {
      this.zzVN = paramContext;
      return;
    }
  }
  
  protected zzlb zzay(IBinder paramIBinder)
  {
    return zzlb.zza.zzaA(paramIBinder);
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.auth.service.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.auth.api.internal.IAuthService";
  }
  
  public boolean zzmE()
  {
    zzf localzzf = zzqH();
    return (!TextUtils.isEmpty(localzzf.getAccountName())) && (!localzzf.zzb(Auth.PROXY_API).isEmpty());
  }
  
  protected Bundle zzml()
  {
    return this.zzVN;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzkz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */