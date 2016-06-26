package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.zzq;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzj.zzf;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzrn;
import com.google.android.gms.internal.zzro;

public class zzh
  extends zzj<zze>
  implements zzrn
{
  private final zzf zzahz;
  private Integer zzale;
  private final Bundle zzbgU;
  private final boolean zzbhi;
  
  public zzh(Context paramContext, Looper paramLooper, boolean paramBoolean, zzf paramzzf, Bundle paramBundle, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 44, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzbhi = paramBoolean;
    this.zzahz = paramzzf;
    this.zzbgU = paramBundle;
    this.zzale = paramzzf.zzqz();
  }
  
  public zzh(Context paramContext, Looper paramLooper, boolean paramBoolean, zzf paramzzf, zzro paramzzro, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this(paramContext, paramLooper, paramBoolean, paramzzf, zza(paramzzf), paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  private ResolveAccountRequest zzFN()
  {
    Account localAccount = this.zzahz.zzqq();
    GoogleSignInAccount localGoogleSignInAccount = null;
    if ("<<default account>>".equals(localAccount.name)) {
      localGoogleSignInAccount = zzq.zzaf(getContext()).zzno();
    }
    return new ResolveAccountRequest(localAccount, this.zzale.intValue(), localGoogleSignInAccount);
  }
  
  public static Bundle zza(zzf paramzzf)
  {
    zzro localzzro = paramzzf.zzqy();
    Integer localInteger = paramzzf.zzqz();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", paramzzf.getAccount());
    if (localInteger != null) {
      localBundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", localInteger.intValue());
    }
    if (localzzro != null)
    {
      localBundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", localzzro.zzFH());
      localBundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", localzzro.zzmO());
      localBundle.putString("com.google.android.gms.signin.internal.serverClientId", localzzro.zzmR());
      localBundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
      localBundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", localzzro.zzmQ());
      localBundle.putString("com.google.android.gms.signin.internal.hostedDomain", localzzro.zzmS());
      localBundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", localzzro.zzFI());
    }
    return localBundle;
  }
  
  public void connect()
  {
    zza(new zzj.zzf(this));
  }
  
  public void zzFG()
  {
    try
    {
      ((zze)zzqJ()).zzka(this.zzale.intValue());
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
    }
  }
  
  public void zza(zzp paramzzp, boolean paramBoolean)
  {
    try
    {
      ((zze)zzqJ()).zza(paramzzp, this.zzale.intValue(), paramBoolean);
      return;
    }
    catch (RemoteException paramzzp)
    {
      Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
    }
  }
  
  public void zza(zzd paramzzd)
  {
    zzx.zzb(paramzzd, "Expecting a valid ISignInCallbacks");
    try
    {
      ResolveAccountRequest localResolveAccountRequest = zzFN();
      ((zze)zzqJ()).zza(new SignInRequest(localResolveAccountRequest), paramzzd);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
      try
      {
        paramzzd.zzb(new SignInResponse(8));
        return;
      }
      catch (RemoteException paramzzd)
      {
        Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", localRemoteException);
      }
    }
  }
  
  protected zze zzec(IBinder paramIBinder)
  {
    return zze.zza.zzeb(paramIBinder);
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.signin.service.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.signin.internal.ISignInService";
  }
  
  public boolean zzmE()
  {
    return this.zzbhi;
  }
  
  protected Bundle zzml()
  {
    String str = this.zzahz.zzqv();
    if (!getContext().getPackageName().equals(str)) {
      this.zzbgU.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzahz.zzqv());
    }
    return this.zzbgU;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\signin\internal\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */