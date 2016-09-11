package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.auth.api.signin.zzg;
import com.google.android.gms.auth.api.signin.zzg.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import java.util.Iterator;
import java.util.Set;

public class zzd
  extends zzj<zzh>
{
  private final GoogleSignInOptions zzXx;
  
  public zzd(Context paramContext, Looper paramLooper, zzf paramzzf, GoogleSignInOptions paramGoogleSignInOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 91, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    if (paramGoogleSignInOptions != null) {}
    for (;;)
    {
      paramContext = paramGoogleSignInOptions;
      if (paramzzf.zzqt().isEmpty()) {
        break label103;
      }
      paramContext = new GoogleSignInOptions.Builder(paramGoogleSignInOptions);
      paramLooper = paramzzf.zzqt().iterator();
      while (paramLooper.hasNext()) {
        paramContext.requestScopes((Scope)paramLooper.next(), new Scope[0]);
      }
      paramGoogleSignInOptions = new GoogleSignInOptions.Builder().build();
    }
    paramContext = paramContext.build();
    label103:
    this.zzXx = paramContext;
  }
  
  protected zzh zzaB(IBinder paramIBinder)
  {
    return zzh.zza.zzaD(paramIBinder);
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.auth.api.signin.service.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.auth.api.signin.internal.ISignInService";
  }
  
  public boolean zznb()
  {
    return true;
  }
  
  public Intent zznc()
  {
    SignInConfiguration localSignInConfiguration = new zzg.zza(getContext().getPackageName()).zzi(this.zzXx).zzmY().zzmX();
    Intent localIntent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
    localIntent.setClass(getContext(), SignInHubActivity.class);
    localIntent.putExtra("config", localSignInConfiguration);
    return localIntent;
  }
  
  public GoogleSignInOptions zznd()
  {
    return this.zzXx;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\api\signin\internal\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */