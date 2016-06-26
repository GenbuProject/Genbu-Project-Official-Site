package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.EmailSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import org.json.JSONException;
import org.json.JSONObject;

public final class SignInConfiguration
  implements SafeParcelable
{
  public static final Parcelable.Creator<SignInConfiguration> CREATOR = new zzp();
  final int versionCode;
  private final String zzXL;
  private EmailSignInOptions zzXM;
  private GoogleSignInOptions zzXN;
  private String zzXO;
  private String zzXd;
  
  SignInConfiguration(int paramInt, String paramString1, String paramString2, EmailSignInOptions paramEmailSignInOptions, GoogleSignInOptions paramGoogleSignInOptions, String paramString3)
  {
    this.versionCode = paramInt;
    this.zzXL = zzx.zzcM(paramString1);
    this.zzXd = paramString2;
    this.zzXM = paramEmailSignInOptions;
    this.zzXN = paramGoogleSignInOptions;
    this.zzXO = paramString3;
  }
  
  public SignInConfiguration(String paramString)
  {
    this(2, paramString, null, null, null, null);
  }
  
  private JSONObject zzmJ()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("consumerPackageName", this.zzXL);
      if (!TextUtils.isEmpty(this.zzXd)) {
        localJSONObject.put("serverClientId", this.zzXd);
      }
      if (this.zzXM != null) {
        localJSONObject.put("emailSignInOptions", this.zzXM.zzmI());
      }
      if (this.zzXN != null) {
        localJSONObject.put("googleSignInOptions", this.zzXN.zzmI());
      }
      if (!TextUtils.isEmpty(this.zzXO)) {
        localJSONObject.put("apiKey", this.zzXO);
      }
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    for (;;)
    {
      return false;
      try
      {
        paramObject = (SignInConfiguration)paramObject;
        if (this.zzXL.equals(((SignInConfiguration)paramObject).zznk()))
        {
          if (TextUtils.isEmpty(this.zzXd))
          {
            if (!TextUtils.isEmpty(((SignInConfiguration)paramObject).zzmR())) {
              continue;
            }
            label45:
            if (!TextUtils.isEmpty(this.zzXO)) {
              break label113;
            }
            if (!TextUtils.isEmpty(((SignInConfiguration)paramObject).zznn())) {
              continue;
            }
            label65:
            if (this.zzXM != null) {
              break label130;
            }
            if (((SignInConfiguration)paramObject).zznl() != null) {
              continue;
            }
          }
          for (;;)
          {
            if (this.zzXN != null) {
              break label147;
            }
            if (((SignInConfiguration)paramObject).zznm() != null) {
              break;
            }
            break label169;
            if (!this.zzXd.equals(((SignInConfiguration)paramObject).zzmR())) {
              break;
            }
            break label45;
            label113:
            if (!this.zzXO.equals(((SignInConfiguration)paramObject).zznn())) {
              break;
            }
            break label65;
            label130:
            if (!this.zzXM.equals(((SignInConfiguration)paramObject).zznl())) {
              break;
            }
          }
          label147:
          boolean bool = this.zzXN.equals(((SignInConfiguration)paramObject).zznm());
          if (!bool) {}
        }
      }
      catch (ClassCastException paramObject)
      {
        return false;
      }
    }
    label169:
    return true;
  }
  
  public int hashCode()
  {
    return new zze().zzp(this.zzXL).zzp(this.zzXd).zzp(this.zzXO).zzp(this.zzXM).zzp(this.zzXN).zzne();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzp.zza(this, paramParcel, paramInt);
  }
  
  public SignInConfiguration zzj(GoogleSignInOptions paramGoogleSignInOptions)
  {
    this.zzXN = ((GoogleSignInOptions)zzx.zzb(paramGoogleSignInOptions, "GoogleSignInOptions cannot be null."));
    return this;
  }
  
  public String zzmI()
  {
    return zzmJ().toString();
  }
  
  public String zzmR()
  {
    return this.zzXd;
  }
  
  public String zznk()
  {
    return this.zzXL;
  }
  
  public EmailSignInOptions zznl()
  {
    return this.zzXM;
  }
  
  public GoogleSignInOptions zznm()
  {
    return this.zzXN;
  }
  
  public String zznn()
  {
    return this.zzXO;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\signin\internal\SignInConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */