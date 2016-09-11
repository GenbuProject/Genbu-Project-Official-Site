package com.google.android.gms.search;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzrj;
import com.google.android.gms.internal.zzrk;

public class SearchAuth
{
  public static final Api<Api.ApiOptions.NoOptions> API = new Api("SearchAuth.API", zzbgI, zzUI);
  public static final SearchAuthApi SearchAuthApi = new zzrk();
  public static final Api.zzc<zzrj> zzUI;
  private static final Api.zza<zzrj, Api.ApiOptions.NoOptions> zzbgI = new Api.zza()
  {
    public zzrj zzt(Context paramAnonymousContext, Looper paramAnonymousLooper, zzf paramAnonymouszzf, Api.ApiOptions.NoOptions paramAnonymousNoOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new zzrj(paramAnonymousContext, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener, paramAnonymouszzf);
    }
  };
  
  static
  {
    zzUI = new Api.zzc();
  }
  
  public static class StatusCodes
  {
    public static final int AUTH_DISABLED = 10000;
    public static final int AUTH_THROTTLED = 10001;
    public static final int DEVELOPER_ERROR = 10;
    public static final int INTERNAL_ERROR = 8;
    public static final int SUCCESS = 0;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\search\SearchAuth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */