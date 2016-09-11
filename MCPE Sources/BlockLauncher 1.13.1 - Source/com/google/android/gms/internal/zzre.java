package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.AttestationData;
import com.google.android.gms.safetynet.SafeBrowsingData;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetApi.AttestationResult;
import com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult;
import java.util.List;

public class zzre
  implements SafetyNetApi
{
  public PendingResult<SafetyNetApi.AttestationResult> attest(GoogleApiClient paramGoogleApiClient, final byte[] paramArrayOfByte)
  {
    paramGoogleApiClient.zza(new zzb(paramGoogleApiClient)
    {
      protected void zza(zzrf paramAnonymouszzrf)
        throws RemoteException
      {
        paramAnonymouszzrf.zza(this.zzbgC, paramArrayOfByte);
      }
    });
  }
  
  public PendingResult<SafetyNetApi.SafeBrowsingResult> lookupUri(GoogleApiClient paramGoogleApiClient, final List<Integer> paramList, final String paramString)
  {
    if (paramList == null) {
      throw new IllegalArgumentException("Null threatTypes in lookupUri");
    }
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("Null or empty uri in lookupUri");
    }
    paramGoogleApiClient.zza(new zzc(paramGoogleApiClient)
    {
      protected void zza(zzrf paramAnonymouszzrf)
        throws RemoteException
      {
        paramAnonymouszzrf.zza(this.zzbgC, paramList, 1, paramString);
      }
    });
  }
  
  static class zza
    implements SafetyNetApi.AttestationResult
  {
    private final Status zzUX;
    private final AttestationData zzbgB;
    
    public zza(Status paramStatus, AttestationData paramAttestationData)
    {
      this.zzUX = paramStatus;
      this.zzbgB = paramAttestationData;
    }
    
    public String getJwsResult()
    {
      if (this.zzbgB == null) {
        return null;
      }
      return this.zzbgB.getJwsResult();
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  static abstract class zzb
    extends zzrb<SafetyNetApi.AttestationResult>
  {
    protected zzrc zzbgC = new zzra()
    {
      public void zza(Status paramAnonymousStatus, AttestationData paramAnonymousAttestationData)
      {
        zzre.zzb.this.zza(new zzre.zza(paramAnonymousStatus, paramAnonymousAttestationData));
      }
    };
    
    public zzb(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    protected SafetyNetApi.AttestationResult zzbh(Status paramStatus)
    {
      return new zzre.zza(paramStatus, null);
    }
  }
  
  static abstract class zzc
    extends zzrb<SafetyNetApi.SafeBrowsingResult>
  {
    protected zzrc zzbgC = new zzra()
    {
      public void zza(Status paramAnonymousStatus, SafeBrowsingData paramAnonymousSafeBrowsingData)
      {
        zzre.zzc.this.zza(new zzre.zzd(paramAnonymousStatus, paramAnonymousSafeBrowsingData));
      }
    };
    
    public zzc(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    protected SafetyNetApi.SafeBrowsingResult zzbi(Status paramStatus)
    {
      return new zzre.zzd(paramStatus, null);
    }
  }
  
  static class zzd
    implements SafetyNetApi.SafeBrowsingResult
  {
    private Status zzUX;
    private final SafeBrowsingData zzbgF;
    private String zzbgv;
    
    public zzd(Status paramStatus, SafeBrowsingData paramSafeBrowsingData)
    {
      this.zzUX = paramStatus;
      this.zzbgF = paramSafeBrowsingData;
      this.zzbgv = null;
      if (this.zzbgF != null) {
        this.zzbgv = this.zzbgF.getMetadata();
      }
      while (!this.zzUX.isSuccess()) {
        return;
      }
      this.zzUX = new Status(8);
    }
    
    public String getMetadata()
    {
      return this.zzbgv;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzre.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */