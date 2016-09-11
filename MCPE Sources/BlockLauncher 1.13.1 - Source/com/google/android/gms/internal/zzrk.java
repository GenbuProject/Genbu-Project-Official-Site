package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zza;
import com.google.android.gms.search.GoogleNowAuthState;
import com.google.android.gms.search.SearchAuth;
import com.google.android.gms.search.SearchAuthApi;
import com.google.android.gms.search.SearchAuthApi.GoogleNowAuthResult;

public class zzrk
  implements SearchAuthApi
{
  public PendingResult<Status> clearToken(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.zza(new zzb(paramGoogleApiClient, paramString));
  }
  
  public PendingResult<SearchAuthApi.GoogleNowAuthResult> getGoogleNowAuth(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return paramGoogleApiClient.zza(new zzc(paramGoogleApiClient, paramString));
  }
  
  static abstract class zza
    extends zzrh.zza
  {
    public void zza(Status paramStatus, GoogleNowAuthState paramGoogleNowAuthState)
    {
      throw new UnsupportedOperationException();
    }
    
    public void zzbj(Status paramStatus)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  static class zzb
    extends zza.zza<Status, zzrj>
  {
    private final String zzXI;
    private final String zzbgJ;
    private final boolean zzbgK = Log.isLoggable("SearchAuth", 3);
    
    protected zzb(GoogleApiClient paramGoogleApiClient, String paramString)
    {
      super(paramGoogleApiClient);
      this.zzXI = paramString;
      this.zzbgJ = paramGoogleApiClient.getContext().getPackageName();
    }
    
    protected void zza(zzrj paramzzrj)
      throws RemoteException
    {
      if (this.zzbgK) {
        Log.d("SearchAuth", "ClearTokenImpl started");
      }
      zzrk.zza local1 = new zzrk.zza()
      {
        public void zzbj(Status paramAnonymousStatus)
        {
          if (zzrk.zzb.zza(zzrk.zzb.this)) {
            Log.d("SearchAuth", "ClearTokenImpl success");
          }
          zzrk.zzb.this.zza(paramAnonymousStatus);
        }
      };
      ((zzri)paramzzrj.zzqJ()).zzb(local1, this.zzbgJ, this.zzXI);
    }
    
    protected Status zzb(Status paramStatus)
    {
      if (this.zzbgK) {
        Log.d("SearchAuth", "ClearTokenImpl received failure: " + paramStatus.getStatusMessage());
      }
      return paramStatus;
    }
  }
  
  static class zzc
    extends zza.zza<SearchAuthApi.GoogleNowAuthResult, zzrj>
  {
    private final String zzbgJ;
    private final boolean zzbgK = Log.isLoggable("SearchAuth", 3);
    private final String zzbgM;
    
    protected zzc(GoogleApiClient paramGoogleApiClient, String paramString)
    {
      super(paramGoogleApiClient);
      this.zzbgM = paramString;
      this.zzbgJ = paramGoogleApiClient.getContext().getPackageName();
    }
    
    protected void zza(zzrj paramzzrj)
      throws RemoteException
    {
      if (this.zzbgK) {
        Log.d("SearchAuth", "GetGoogleNowAuthImpl started");
      }
      zzrk.zza local1 = new zzrk.zza()
      {
        public void zza(Status paramAnonymousStatus, GoogleNowAuthState paramAnonymousGoogleNowAuthState)
        {
          if (zzrk.zzc.zza(zzrk.zzc.this)) {
            Log.d("SearchAuth", "GetGoogleNowAuthImpl success");
          }
          zzrk.zzc.this.zza(new zzrk.zzd(paramAnonymousStatus, paramAnonymousGoogleNowAuthState));
        }
      };
      ((zzri)paramzzrj.zzqJ()).zza(local1, this.zzbgJ, this.zzbgM);
    }
    
    protected SearchAuthApi.GoogleNowAuthResult zzbk(Status paramStatus)
    {
      if (this.zzbgK) {
        Log.d("SearchAuth", "GetGoogleNowAuthImpl received failure: " + paramStatus.getStatusMessage());
      }
      return new zzrk.zzd(paramStatus, null);
    }
  }
  
  static class zzd
    implements SearchAuthApi.GoogleNowAuthResult
  {
    private final Status zzUX;
    private final GoogleNowAuthState zzbgO;
    
    zzd(Status paramStatus, GoogleNowAuthState paramGoogleNowAuthState)
    {
      this.zzUX = paramStatus;
      this.zzbgO = paramGoogleNowAuthState;
    }
    
    public GoogleNowAuthState getGoogleNowAuthState()
    {
      return this.zzbgO;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzrk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */