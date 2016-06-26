package com.google.android.gms.drive;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.drive.internal.zzaa;
import com.google.android.gms.drive.internal.zzac;
import com.google.android.gms.drive.internal.zzs;
import com.google.android.gms.drive.internal.zzu;
import com.google.android.gms.drive.internal.zzx;

public final class Drive
{
  public static final Api<Api.ApiOptions.NoOptions> API;
  public static final DriveApi DriveApi;
  public static final DrivePreferencesApi DrivePreferencesApi = new zzaa();
  public static final Scope SCOPE_APPFOLDER;
  public static final Scope SCOPE_FILE;
  public static final Api.zzc<zzu> zzUI = new Api.zzc();
  public static final Scope zzaoE;
  public static final Scope zzaoF;
  public static final Api<zzb> zzaoG;
  public static final zzd zzaoH;
  public static final zzf zzaoI;
  
  static
  {
    SCOPE_FILE = new Scope("https://www.googleapis.com/auth/drive.file");
    SCOPE_APPFOLDER = new Scope("https://www.googleapis.com/auth/drive.appdata");
    zzaoE = new Scope("https://www.googleapis.com/auth/drive");
    zzaoF = new Scope("https://www.googleapis.com/auth/drive.apps");
    API = new Api("Drive.API", new zza()
    {
      protected Bundle zza(Api.ApiOptions.NoOptions paramAnonymousNoOptions)
      {
        return new Bundle();
      }
    }, zzUI);
    zzaoG = new Api("Drive.INTERNAL_API", new zza()
    {
      protected Bundle zza(Drive.zzb paramAnonymouszzb)
      {
        if (paramAnonymouszzb == null) {
          return new Bundle();
        }
        return paramAnonymouszzb.zzsw();
      }
    }, zzUI);
    DriveApi = new zzs();
    zzaoH = new zzx();
    zzaoI = new zzac();
  }
  
  public static abstract class zza<O extends Api.ApiOptions>
    extends Api.zza<zzu, O>
  {
    protected abstract Bundle zza(O paramO);
    
    public zzu zza(Context paramContext, Looper paramLooper, com.google.android.gms.common.internal.zzf paramzzf, O paramO, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      return new zzu(paramContext, paramLooper, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener, zza(paramO));
    }
  }
  
  public static class zzb
    implements Api.ApiOptions.Optional
  {
    private final Bundle zzUH;
    
    public Bundle zzsw()
    {
      return this.zzUH;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\Drive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */