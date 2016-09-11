package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.signin.internal.zzg;
import com.google.android.gms.signin.internal.zzh;

public final class zzrl
{
  public static final Api<zzro> API = new Api("SignIn.API", zzUJ, zzUI);
  public static final Api.zzc<zzh> zzUI = new Api.zzc();
  public static final Api.zza<zzh, zzro> zzUJ;
  public static final Scope zzWW;
  public static final Scope zzWX;
  public static final Api<zza> zzaoG = new Api("SignIn.INTERNAL_API", zzbgS, zzavN);
  public static final Api.zzc<zzh> zzavN = new Api.zzc();
  static final Api.zza<zzh, zza> zzbgS;
  public static final zzrm zzbgT = new zzg();
  
  static
  {
    zzUJ = new Api.zza()
    {
      public zzh zza(Context paramAnonymousContext, Looper paramAnonymousLooper, zzf paramAnonymouszzf, zzro paramAnonymouszzro, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
      {
        if (paramAnonymouszzro == null) {
          paramAnonymouszzro = zzro.zzbgV;
        }
        for (;;)
        {
          return new zzh(paramAnonymousContext, paramAnonymousLooper, true, paramAnonymouszzf, paramAnonymouszzro, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
        }
      }
    };
    zzbgS = new Api.zza()
    {
      public zzh zza(Context paramAnonymousContext, Looper paramAnonymousLooper, zzf paramAnonymouszzf, zzrl.zza paramAnonymouszza, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
      {
        return new zzh(paramAnonymousContext, paramAnonymousLooper, false, paramAnonymouszzf, paramAnonymouszza.zzFF(), paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
      }
    };
    zzWW = new Scope("profile");
    zzWX = new Scope("email");
  }
  
  public static class zza
    implements Api.ApiOptions.HasOptions
  {
    private final Bundle zzbgU;
    
    public Bundle zzFF()
    {
      return this.zzbgU;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzrl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */