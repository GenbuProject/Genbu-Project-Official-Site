package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.auth.api.signin.zzd;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzlf.zza;
import com.google.android.gms.internal.zzlh;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@KeepName
public class SignInHubActivity
  extends FragmentActivity
{
  private zzq zzXP;
  private zzk zzXQ;
  private SignInConfiguration zzXR;
  private boolean zzXS;
  private String zzXT;
  private String zzXU;
  private boolean zzXV;
  private int zzXW;
  private Intent zzXX;
  
  private void zza(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Iterator localIterator = this.zzXQ.zznh().iterator();
    while ((localIterator.hasNext()) && (!((zzlf)localIterator.next()).zza(paramInt1, paramInt2, paramIntent, zzbO(this.zzXU)))) {}
    if (paramInt2 == 0) {
      finish();
    }
  }
  
  private void zza(int paramInt, Intent paramIntent)
  {
    if (paramIntent != null)
    {
      Object localObject = (SignInAccount)paramIntent.getParcelableExtra("signInAccount");
      if ((localObject != null) && (((SignInAccount)localObject).zzmV() != null))
      {
        localObject = ((SignInAccount)localObject).zzmV();
        this.zzXP.zzb((GoogleSignInAccount)localObject, this.zzXR.zznm());
        paramIntent.removeExtra("signInAccount");
        paramIntent.putExtra("googleSignInAccount", (Parcelable)localObject);
        this.zzXV = true;
        this.zzXW = paramInt;
        this.zzXX = paramIntent;
        zzd(paramInt, paramIntent);
        return;
      }
      if (paramIntent.hasExtra("errorCode"))
      {
        zzaS(paramIntent.getIntExtra("errorCode", 8));
        return;
      }
    }
    zzaS(8);
  }
  
  private void zzaR(int paramInt)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("errorCode", paramInt);
    setResult(0, localIntent);
    finish();
  }
  
  private void zzaS(int paramInt)
  {
    Status localStatus = new Status(paramInt);
    Intent localIntent = new Intent();
    localIntent.putExtra("googleSignInStatus", localStatus);
    setResult(0, localIntent);
    finish();
  }
  
  private void zzb(int paramInt, Intent paramIntent)
  {
    if (paramInt == -1)
    {
      localObject1 = (SignInAccount)paramIntent.getParcelableExtra("signInAccount");
      if (localObject1 != null)
      {
        this.zzXP.zzb((SignInAccount)localObject1, this.zzXR);
        localObject2 = paramIntent.getStringExtra("accessToken");
        if ((!TextUtils.isEmpty((CharSequence)localObject2)) && (!TextUtils.isEmpty(this.zzXU)))
        {
          localObject3 = new HashSet(Arrays.asList(TextUtils.split(this.zzXU, " ")));
          zzm.zzbN(((SignInAccount)localObject1).getUserId()).zza((Set)localObject3, new zzm.zza((String)localObject2, paramIntent.getLongExtra("accessTokenExpiresAtSecs", 0L)));
          paramIntent.removeExtra("accessTokenExpiresAtSecs");
        }
        setResult(-1, paramIntent);
        finish();
        return;
      }
      Log.w("AuthSignInClient", "[SignInHubActivity] SignInAccount is null.");
      zzaR(2);
      return;
    }
    if (paramIntent == null)
    {
      finish();
      return;
    }
    Object localObject1 = paramIntent.getStringExtra("email");
    Object localObject2 = zzd.zzbL(paramIntent.getStringExtra("idProvider"));
    if (localObject2 == null)
    {
      setResult(paramInt, paramIntent);
      finish();
      return;
    }
    this.zzXT = paramIntent.getStringExtra("pendingToken");
    Object localObject3 = this.zzXQ.zza((zzd)localObject2);
    if (localObject3 == null)
    {
      paramIntent = ((zzd)localObject2).zzae(this);
      Log.w("AuthSignInClient", paramIntent + " is not supported. Please check your configuration");
      zzaR(1);
      return;
    }
    paramInt = paramIntent.getIntExtra("idpAction", -1);
    if (paramInt == 0)
    {
      if (TextUtils.isEmpty((CharSequence)localObject1))
      {
        ((zzlf)localObject3).zza(zzbO(this.zzXU));
        return;
      }
      ((zzlf)localObject3).zza((String)localObject1, zzbO(this.zzXU));
      return;
    }
    if ((paramInt == 1) && (!TextUtils.isEmpty(this.zzXT)) && (!TextUtils.isEmpty((CharSequence)localObject1)))
    {
      ((zzlf)localObject3).zza((String)localObject1, this.zzXT, zzbO(this.zzXU));
      return;
    }
    Log.w("AuthSignInClient", "Internal error!");
    zzaR(2);
  }
  
  private zzlf.zza zzbO(final String paramString)
  {
    new zzlf.zza()
    {
      public void zzk(Intent paramAnonymousIntent)
      {
        if (paramAnonymousIntent != null)
        {
          if (!TextUtils.isEmpty(paramString)) {
            paramAnonymousIntent.putExtra("scopes", paramString);
          }
          SignInHubActivity.zza(SignInHubActivity.this, paramAnonymousIntent);
          return;
        }
        Log.w("AuthSignInClient", "Idp signin failed!");
        SignInHubActivity.zza(SignInHubActivity.this, 4);
      }
    };
  }
  
  private void zzc(int paramInt, Intent paramIntent)
  {
    if (paramInt == 0)
    {
      setResult(0, paramIntent);
      finish();
      return;
    }
    Intent localIntent = new Intent("com.google.android.gms.auth.VERIFY_ASSERTION");
    localIntent.putExtra("idpTokenType", IdpTokenType.zzXA);
    localIntent.putExtra("idpToken", paramIntent.getStringExtra("idpToken"));
    localIntent.putExtra("pendingToken", this.zzXT);
    localIntent.putExtra("idProvider", zzd.zzXi.zzmT());
    zzj(localIntent);
  }
  
  private void zzd(int paramInt, Intent paramIntent)
  {
    getSupportLoaderManager().initLoader(0, null, new zza(null));
  }
  
  private void zzj(Intent paramIntent)
  {
    paramIntent.setPackage("com.google.android.gms");
    paramIntent.putExtra("config", this.zzXR);
    if (this.zzXS) {}
    for (int i = 40962;; i = 40961) {
      try
      {
        startActivityForResult(paramIntent, i);
        return;
      }
      catch (ActivityNotFoundException paramIntent)
      {
        Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
        if (!this.zzXS) {
          break;
        }
        zzaS(8);
        return;
        zzaR(2);
      }
    }
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    return true;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    setResult(0);
    switch (paramInt1)
    {
    default: 
      zza(paramInt1, paramInt2, paramIntent);
      return;
    case 40962: 
      zza(paramInt2, paramIntent);
      return;
    case 40961: 
      zzb(paramInt2, paramIntent);
      return;
    }
    zzc(paramInt2, paramIntent);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.zzXP = zzq.zzaf(this);
    Intent localIntent = getIntent();
    this.zzXR = ((SignInConfiguration)localIntent.getParcelableExtra("config"));
    this.zzXS = "com.google.android.gms.auth.GOOGLE_SIGN_IN".equals(localIntent.getAction());
    this.zzXU = localIntent.getStringExtra("scopes");
    if (this.zzXR == null)
    {
      Log.e("AuthSignInClient", "Activity started with invalid configuration.");
      setResult(0);
      finish();
      return;
    }
    Object localObject = new LinkedList();
    HashMap localHashMap = new HashMap();
    zzi.zza(this.zzXR, (List)localObject, localHashMap);
    this.zzXQ = new zzk(this, (List)localObject, localHashMap);
    if (paramBundle == null) {
      if (this.zzXS)
      {
        localObject = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        paramBundle = null;
      }
    }
    for (;;)
    {
      if ((paramBundle != null) && (paramBundle.zzmU() == zzd.zzXi))
      {
        zzbO(this.zzXU);
        throw new NullPointerException();
        localObject = new Intent("com.google.android.gms.auth.LOGIN_PICKER");
        if ("com.google.android.gms.auth.RESOLVE_CREDENTIAL".equals(localIntent.getAction()))
        {
          ((Intent)localObject).fillIn(localIntent, 3);
          paramBundle = (SignInAccount)localIntent.getParcelableExtra("signInAccount");
          continue;
        }
        this.zzXP.zznq();
        if (0 != 0) {
          try
          {
            zzlh.zzag(this);
            paramBundle = null;
          }
          catch (IllegalStateException paramBundle)
          {
            paramBundle = null;
          }
        }
      }
      else
      {
        zzj((Intent)localObject);
        return;
        this.zzXT = paramBundle.getString("pendingToken");
        this.zzXV = paramBundle.getBoolean("signingInGoogleApiClients");
        if (!this.zzXV) {
          break;
        }
        this.zzXW = paramBundle.getInt("signInResultCode");
        this.zzXX = ((Intent)paramBundle.getParcelable("signInResultData"));
        zzd(this.zzXW, this.zzXX);
        return;
      }
      paramBundle = null;
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putString("pendingToken", this.zzXT);
    paramBundle.putBoolean("signingInGoogleApiClients", this.zzXV);
    if (this.zzXV)
    {
      paramBundle.putInt("signInResultCode", this.zzXW);
      paramBundle.putParcelable("signInResultData", this.zzXX);
    }
  }
  
  private class zza
    implements LoaderManager.LoaderCallbacks<Void>
  {
    private zza() {}
    
    public Loader<Void> onCreateLoader(int paramInt, Bundle paramBundle)
    {
      return new zzb(SignInHubActivity.this, GoogleApiClient.zzoV());
    }
    
    public void onLoaderReset(Loader<Void> paramLoader) {}
    
    public void zza(Loader<Void> paramLoader, Void paramVoid)
    {
      SignInHubActivity.this.setResult(SignInHubActivity.zza(SignInHubActivity.this), SignInHubActivity.zzb(SignInHubActivity.this));
      SignInHubActivity.this.finish();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\signin\internal\SignInHubActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */