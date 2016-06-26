package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.consent.zza;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzc;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.auth.api.signin.internal.zzo;
import com.google.android.gms.auth.api.signin.zzg;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzkq;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zzkv;
import com.google.android.gms.internal.zzkw;
import com.google.android.gms.internal.zzkz;
import com.google.android.gms.internal.zzld;
import java.util.Collections;
import java.util.List;

public final class Auth
{
  public static final Api<AuthCredentialsOptions> CREDENTIALS_API;
  public static final CredentialsApi CredentialsApi;
  public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API;
  public static final GoogleSignInApi GoogleSignInApi = new zzc();
  public static final Api<zza> PROXY_API;
  public static final ProxyApi ProxyApi;
  private static final Api.zza<com.google.android.gms.auth.api.credentials.internal.zzf, AuthCredentialsOptions> zzVA;
  private static final Api.zza<zzks, Api.ApiOptions.NoOptions> zzVB;
  private static final Api.zza<zzkw, Api.ApiOptions.NoOptions> zzVC;
  private static final Api.zza<zzo, zzg> zzVD;
  private static final Api.zza<com.google.android.gms.auth.api.signin.internal.zzd, GoogleSignInOptions> zzVE;
  public static final Api<zzg> zzVF;
  public static final Api<Api.ApiOptions.NoOptions> zzVG;
  public static final Api<Api.ApiOptions.NoOptions> zzVH;
  public static final zzkq zzVI;
  public static final com.google.android.gms.auth.api.signin.zzf zzVJ;
  public static final zza zzVK = new zzkv();
  public static final Api.zzc<zzkz> zzVt = new Api.zzc();
  public static final Api.zzc<com.google.android.gms.auth.api.credentials.internal.zzf> zzVu = new Api.zzc();
  public static final Api.zzc<zzks> zzVv = new Api.zzc();
  public static final Api.zzc<zzo> zzVw = new Api.zzc();
  public static final Api.zzc<com.google.android.gms.auth.api.signin.internal.zzd> zzVx = new Api.zzc();
  public static final Api.zzc<zzkw> zzVy = new Api.zzc();
  private static final Api.zza<zzkz, zza> zzVz = new Api.zza()
  {
    public zzkz zza(Context paramAnonymousContext, Looper paramAnonymousLooper, com.google.android.gms.common.internal.zzf paramAnonymouszzf, Auth.zza paramAnonymouszza, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      return new zzkz(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzf, paramAnonymouszza, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
    }
  };
  
  static
  {
    zzVA = new Api.zza()
    {
      public com.google.android.gms.auth.api.credentials.internal.zzf zza(Context paramAnonymousContext, Looper paramAnonymousLooper, com.google.android.gms.common.internal.zzf paramAnonymouszzf, Auth.AuthCredentialsOptions paramAnonymousAuthCredentialsOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
      {
        return new com.google.android.gms.auth.api.credentials.internal.zzf(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzf, paramAnonymousAuthCredentialsOptions, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
      }
    };
    zzVB = new Api.zza()
    {
      public zzks zzc(Context paramAnonymousContext, Looper paramAnonymousLooper, com.google.android.gms.common.internal.zzf paramAnonymouszzf, Api.ApiOptions.NoOptions paramAnonymousNoOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
      {
        return new zzks(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzf, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
      }
    };
    zzVC = new Api.zza()
    {
      public zzkw zzd(Context paramAnonymousContext, Looper paramAnonymousLooper, com.google.android.gms.common.internal.zzf paramAnonymouszzf, Api.ApiOptions.NoOptions paramAnonymousNoOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
      {
        return new zzkw(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzf, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
      }
    };
    zzVD = new Api.zza()
    {
      public zzo zza(Context paramAnonymousContext, Looper paramAnonymousLooper, com.google.android.gms.common.internal.zzf paramAnonymouszzf, zzg paramAnonymouszzg, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
      {
        return new zzo(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzf, paramAnonymouszzg, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
      }
    };
    zzVE = new Api.zza()
    {
      public com.google.android.gms.auth.api.signin.internal.zzd zza(Context paramAnonymousContext, Looper paramAnonymousLooper, com.google.android.gms.common.internal.zzf paramAnonymouszzf, @Nullable GoogleSignInOptions paramAnonymousGoogleSignInOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
      {
        return new com.google.android.gms.auth.api.signin.internal.zzd(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzf, paramAnonymousGoogleSignInOptions, paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
      }
      
      public List<Scope> zza(@Nullable GoogleSignInOptions paramAnonymousGoogleSignInOptions)
      {
        if (paramAnonymousGoogleSignInOptions == null) {
          return Collections.emptyList();
        }
        return paramAnonymousGoogleSignInOptions.zzmN();
      }
    };
    PROXY_API = new Api("Auth.PROXY_API", zzVz, zzVt);
    CREDENTIALS_API = new Api("Auth.CREDENTIALS_API", zzVA, zzVu);
    zzVF = new Api("Auth.SIGN_IN_API", zzVD, zzVw);
    GOOGLE_SIGN_IN_API = new Api("Auth.GOOGLE_SIGN_IN_API", zzVE, zzVx);
    zzVG = new Api("Auth.ACCOUNT_STATUS_API", zzVB, zzVv);
    zzVH = new Api("Auth.CONSENT_API", zzVC, zzVy);
    ProxyApi = new zzld();
    CredentialsApi = new com.google.android.gms.auth.api.credentials.internal.zzd();
    zzVI = new zzkr();
    zzVJ = new zzn();
  }
  
  public static final class AuthCredentialsOptions
    implements Api.ApiOptions.Optional
  {
    private final String zzVL;
    private final PasswordSpecification zzVM;
    
    public Bundle zzml()
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("consumer_package", this.zzVL);
      localBundle.putParcelable("password_specification", this.zzVM);
      return localBundle;
    }
    
    public PasswordSpecification zzmr()
    {
      return this.zzVM;
    }
    
    public static class Builder
    {
      @NonNull
      private PasswordSpecification zzVM = PasswordSpecification.zzWl;
    }
  }
  
  public static final class zza
    implements Api.ApiOptions.Optional
  {
    private final Bundle zzVN;
    
    public Bundle zzms()
    {
      return new Bundle(this.zzVN);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\Auth.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */