package com.google.android.gms.plus;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.zza.zza;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zznh;
import com.google.android.gms.internal.zzqv;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzqx;
import com.google.android.gms.internal.zzqy;
import com.google.android.gms.internal.zzqz;
import com.google.android.gms.plus.internal.PlusCommonExtras;
import com.google.android.gms.plus.internal.PlusSession;
import com.google.android.gms.plus.internal.zze;
import java.util.HashSet;
import java.util.Set;

public final class Plus
{
  public static final Api<PlusOptions> API;
  @Deprecated
  public static final Account AccountApi = new zzqv();
  public static final Moments MomentsApi;
  public static final People PeopleApi;
  public static final Scope SCOPE_PLUS_LOGIN;
  public static final Scope SCOPE_PLUS_PROFILE;
  public static final Api.zzc<zze> zzUI = new Api.zzc();
  static final Api.zza<zze, PlusOptions> zzUJ = new Api.zza()
  {
    public int getPriority()
    {
      return 2;
    }
    
    public zze zza(Context paramAnonymousContext, Looper paramAnonymousLooper, zzf paramAnonymouszzf, Plus.PlusOptions paramAnonymousPlusOptions, GoogleApiClient.ConnectionCallbacks paramAnonymousConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramAnonymousOnConnectionFailedListener)
    {
      Object localObject = paramAnonymousPlusOptions;
      if (paramAnonymousPlusOptions == null) {
        localObject = new Plus.PlusOptions(null);
      }
      paramAnonymousPlusOptions = paramAnonymouszzf.zzqq().name;
      String[] arrayOfString = zznh.zzc(paramAnonymouszzf.zzqt());
      localObject = (String[])((Plus.PlusOptions)localObject).zzbdZ.toArray(new String[0]);
      String str1 = paramAnonymousContext.getPackageName();
      String str2 = paramAnonymousContext.getPackageName();
      PlusCommonExtras localPlusCommonExtras = new PlusCommonExtras();
      return new zze(paramAnonymousContext, paramAnonymousLooper, paramAnonymouszzf, new PlusSession(paramAnonymousPlusOptions, arrayOfString, (String[])localObject, new String[0], str1, str2, null, localPlusCommonExtras), paramAnonymousConnectionCallbacks, paramAnonymousOnConnectionFailedListener);
    }
  };
  public static final zzb zzbdW = new zzqx();
  public static final zza zzbdX = new zzqw();
  
  static
  {
    API = new Api("Plus.API", zzUJ, zzUI);
    SCOPE_PLUS_LOGIN = new Scope("https://www.googleapis.com/auth/plus.login");
    SCOPE_PLUS_PROFILE = new Scope("https://www.googleapis.com/auth/plus.me");
    MomentsApi = new zzqy();
    PeopleApi = new zzqz();
  }
  
  public static zze zzf(GoogleApiClient paramGoogleApiClient, boolean paramBoolean)
  {
    if (paramGoogleApiClient != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "GoogleApiClient parameter is required.");
      zzx.zza(paramGoogleApiClient.isConnected(), "GoogleApiClient must be connected.");
      zzx.zza(paramGoogleApiClient.zza(API), "GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
      bool = paramGoogleApiClient.hasConnectedApi(API);
      if ((!paramBoolean) || (bool)) {
        break;
      }
      throw new IllegalStateException("GoogleApiClient has an optional Plus.API and is not connected to Plus. Use GoogleApiClient.hasConnectedApi(Plus.API) to guard this call.");
    }
    if (bool) {
      return (zze)paramGoogleApiClient.zza(zzUI);
    }
    return null;
  }
  
  public static final class PlusOptions
    implements Api.ApiOptions.Optional
  {
    final String zzbdY;
    final Set<String> zzbdZ;
    
    private PlusOptions()
    {
      this.zzbdY = null;
      this.zzbdZ = new HashSet();
    }
    
    private PlusOptions(Builder paramBuilder)
    {
      this.zzbdY = paramBuilder.zzbdY;
      this.zzbdZ = paramBuilder.zzbdZ;
    }
    
    public static Builder builder()
    {
      return new Builder();
    }
    
    public static final class Builder
    {
      String zzbdY;
      final Set<String> zzbdZ = new HashSet();
      
      public Builder addActivityTypes(String... paramVarArgs)
      {
        zzx.zzb(paramVarArgs, "activityTypes may not be null.");
        int i = 0;
        while (i < paramVarArgs.length)
        {
          this.zzbdZ.add(paramVarArgs[i]);
          i += 1;
        }
        return this;
      }
      
      public Plus.PlusOptions build()
      {
        return new Plus.PlusOptions(this, null);
      }
      
      public Builder setServerClientId(String paramString)
      {
        this.zzbdY = paramString;
        return this;
      }
    }
  }
  
  public static abstract class zza<R extends Result>
    extends zza.zza<R, zze>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\plus\Plus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */