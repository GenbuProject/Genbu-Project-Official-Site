package com.google.android.gms.auth.api.signin;

import com.google.android.gms.auth.api.signin.internal.SignInConfiguration;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.internal.zzx;

public class zzg
  implements Api.ApiOptions.HasOptions
{
  private final SignInConfiguration zzXo;
  
  private zzg(SignInConfiguration paramSignInConfiguration)
  {
    this.zzXo = paramSignInConfiguration;
  }
  
  public SignInConfiguration zzmX()
  {
    return this.zzXo;
  }
  
  public static class zza
  {
    private final SignInConfiguration zzXo;
    
    public zza(String paramString)
    {
      zzx.zzcM(paramString);
      this.zzXo = new SignInConfiguration(paramString);
    }
    
    public zza zzi(GoogleSignInOptions paramGoogleSignInOptions)
    {
      zzx.zzz(paramGoogleSignInOptions);
      this.zzXo.zzj(paramGoogleSignInOptions);
      return this;
    }
    
    public zzg zzmY()
    {
      if ((this.zzXo.zznl() != null) || (this.zzXo.zznm() != null)) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zza(bool, "Must support either Facebook, Google or Email sign-in.");
        return new zzg(this.zzXo, null);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\signin\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */