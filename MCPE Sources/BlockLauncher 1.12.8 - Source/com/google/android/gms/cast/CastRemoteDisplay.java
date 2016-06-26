package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import android.view.Display;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzlr;

public final class CastRemoteDisplay
{
  public static final Api<CastRemoteDisplayOptions> API = new Api("CastRemoteDisplay.API", zzUJ, zzUI);
  public static final CastRemoteDisplayApi CastRemoteDisplayApi = new zzlq(zzUI);
  private static final Api.zzc<zzlr> zzUI = new Api.zzc();
  private static final Api.zza<zzlr, CastRemoteDisplayOptions> zzUJ = new Api.zza()
  {
    public zzlr zza(Context paramAnonymousContext, Looper paramAnonymousLooper, zzf paramAnonymouszzf, CastRemoteDisplay.CastRemoteDisplayOptions paramAnonymousCastRemoteDisplayOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new zzlr(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzf, paramAnonymousCastRemoteDisplayOptions.zzZL, paramAnonymousCastRemoteDisplayOptions.zzaad, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
    }
  };
  
  public static final class CastRemoteDisplayOptions
    implements Api.ApiOptions.HasOptions
  {
    final CastDevice zzZL;
    final CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzaad;
    
    private CastRemoteDisplayOptions(Builder paramBuilder)
    {
      this.zzZL = paramBuilder.zzZO;
      this.zzaad = paramBuilder.zzaae;
    }
    
    public static final class Builder
    {
      CastDevice zzZO;
      CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzaae;
      
      public Builder(CastDevice paramCastDevice, CastRemoteDisplay.CastRemoteDisplaySessionCallbacks paramCastRemoteDisplaySessionCallbacks)
      {
        zzx.zzb(paramCastDevice, "CastDevice parameter cannot be null");
        this.zzZO = paramCastDevice;
        this.zzaae = paramCastRemoteDisplaySessionCallbacks;
      }
      
      public CastRemoteDisplay.CastRemoteDisplayOptions build()
      {
        return new CastRemoteDisplay.CastRemoteDisplayOptions(this, null);
      }
    }
  }
  
  public static abstract interface CastRemoteDisplaySessionCallbacks
  {
    public abstract void onRemoteDisplayEnded(Status paramStatus);
  }
  
  public static abstract interface CastRemoteDisplaySessionResult
    extends Result
  {
    public abstract Display getPresentationDisplay();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\CastRemoteDisplay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */