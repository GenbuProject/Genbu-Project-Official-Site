package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.internal.zzx;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public class zzq
{
  private static final Lock zzYa = new ReentrantLock();
  private static zzq zzYb;
  private final Lock zzYc = new ReentrantLock();
  private final SharedPreferences zzYd;
  
  zzq(Context paramContext)
  {
    this.zzYd = paramContext.getSharedPreferences("com.google.android.gms.signin", 0);
  }
  
  public static zzq zzaf(Context paramContext)
  {
    zzx.zzz(paramContext);
    zzYa.lock();
    try
    {
      if (zzYb == null) {
        zzYb = new zzq(paramContext.getApplicationContext());
      }
      paramContext = zzYb;
      return paramContext;
    }
    finally
    {
      zzYa.unlock();
    }
  }
  
  private String zzs(String paramString1, String paramString2)
  {
    return paramString1 + ":" + paramString2;
  }
  
  void zza(GoogleSignInAccount paramGoogleSignInAccount, GoogleSignInOptions paramGoogleSignInOptions)
  {
    zzx.zzz(paramGoogleSignInAccount);
    zzx.zzz(paramGoogleSignInOptions);
    String str = paramGoogleSignInAccount.zzmL();
    zzr(zzs("googleSignInAccount", str), paramGoogleSignInAccount.zzmM());
    zzr(zzs("googleSignInOptions", str), paramGoogleSignInOptions.zzmI());
  }
  
  void zza(SignInAccount paramSignInAccount, SignInConfiguration paramSignInConfiguration)
  {
    zzx.zzz(paramSignInAccount);
    zzx.zzz(paramSignInConfiguration);
    String str = paramSignInAccount.getUserId();
    SignInAccount localSignInAccount = zzbP(str);
    if ((localSignInAccount != null) && (localSignInAccount.zzmV() != null)) {
      zzbU(localSignInAccount.zzmV().zzmL());
    }
    zzr(zzs("signInConfiguration", str), paramSignInConfiguration.zzmI());
    zzr(zzs("signInAccount", str), paramSignInAccount.zzmI());
    if (paramSignInAccount.zzmV() != null) {
      zza(paramSignInAccount.zzmV(), paramSignInConfiguration.zznm());
    }
  }
  
  public void zzb(GoogleSignInAccount paramGoogleSignInAccount, GoogleSignInOptions paramGoogleSignInOptions)
  {
    zzx.zzz(paramGoogleSignInAccount);
    zzx.zzz(paramGoogleSignInOptions);
    zzr("defaultGoogleSignInAccount", paramGoogleSignInAccount.zzmL());
    zza(paramGoogleSignInAccount, paramGoogleSignInOptions);
  }
  
  public void zzb(SignInAccount paramSignInAccount, SignInConfiguration paramSignInConfiguration)
  {
    zzx.zzz(paramSignInAccount);
    zzx.zzz(paramSignInConfiguration);
    zznq();
    zzr("defaultSignInAccount", paramSignInAccount.getUserId());
    if (paramSignInAccount.zzmV() != null) {
      zzr("defaultGoogleSignInAccount", paramSignInAccount.zzmV().zzmL());
    }
    zza(paramSignInAccount, paramSignInConfiguration);
  }
  
  SignInAccount zzbP(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      paramString = zzbS(zzs("signInAccount", paramString));
    } while (TextUtils.isEmpty(paramString));
    try
    {
      paramString = SignInAccount.zzbM(paramString);
      if (paramString.zzmV() != null)
      {
        GoogleSignInAccount localGoogleSignInAccount = zzbQ(paramString.zzmV().zzmL());
        if (localGoogleSignInAccount != null) {
          paramString.zza(localGoogleSignInAccount);
        }
      }
      return paramString;
    }
    catch (JSONException paramString) {}
    return null;
  }
  
  GoogleSignInAccount zzbQ(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      paramString = zzbS(zzs("googleSignInAccount", paramString));
    } while (paramString == null);
    try
    {
      paramString = GoogleSignInAccount.zzbH(paramString);
      return paramString;
    }
    catch (JSONException paramString) {}
    return null;
  }
  
  GoogleSignInOptions zzbR(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      paramString = zzbS(zzs("googleSignInOptions", paramString));
    } while (paramString == null);
    try
    {
      paramString = GoogleSignInOptions.zzbJ(paramString);
      return paramString;
    }
    catch (JSONException paramString) {}
    return null;
  }
  
  protected String zzbS(String paramString)
  {
    this.zzYc.lock();
    try
    {
      paramString = this.zzYd.getString(paramString, null);
      return paramString;
    }
    finally
    {
      this.zzYc.unlock();
    }
  }
  
  void zzbT(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    SignInAccount localSignInAccount;
    do
    {
      return;
      localSignInAccount = zzbP(paramString);
      zzbV(zzs("signInAccount", paramString));
      zzbV(zzs("signInConfiguration", paramString));
    } while ((localSignInAccount == null) || (localSignInAccount.zzmV() == null));
    zzbU(localSignInAccount.zzmV().zzmL());
  }
  
  void zzbU(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    zzbV(zzs("googleSignInAccount", paramString));
    zzbV(zzs("googleSignInOptions", paramString));
  }
  
  protected void zzbV(String paramString)
  {
    this.zzYc.lock();
    try
    {
      this.zzYd.edit().remove(paramString).apply();
      return;
    }
    finally
    {
      this.zzYc.unlock();
    }
  }
  
  public GoogleSignInAccount zzno()
  {
    return zzbQ(zzbS("defaultGoogleSignInAccount"));
  }
  
  public GoogleSignInOptions zznp()
  {
    return zzbR(zzbS("defaultGoogleSignInAccount"));
  }
  
  public void zznq()
  {
    String str = zzbS("defaultSignInAccount");
    zzbV("defaultSignInAccount");
    zznr();
    zzbT(str);
  }
  
  public void zznr()
  {
    String str = zzbS("defaultGoogleSignInAccount");
    zzbV("defaultGoogleSignInAccount");
    zzbU(str);
  }
  
  protected void zzr(String paramString1, String paramString2)
  {
    this.zzYc.lock();
    try
    {
      this.zzYd.edit().putString(paramString1, paramString2).apply();
      return;
    }
    finally
    {
      this.zzYc.unlock();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\signin\internal\zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */