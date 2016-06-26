package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.Status;

public final class zzc
  implements CredentialRequestResult
{
  private final Status zzUX;
  private final Credential zzWu;
  
  public zzc(Status paramStatus, Credential paramCredential)
  {
    this.zzUX = paramStatus;
    this.zzWu = paramCredential;
  }
  
  public static zzc zzh(Status paramStatus)
  {
    return new zzc(paramStatus, null);
  }
  
  public Credential getCredential()
  {
    return this.zzWu;
  }
  
  public Status getStatus()
  {
    return this.zzUX;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\credentials\internal\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */