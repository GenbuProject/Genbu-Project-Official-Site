package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import org.json.JSONException;
import org.json.JSONObject;

public class SignInAccount
  implements SafeParcelable
{
  public static final Parcelable.Creator<SignInAccount> CREATOR = new zze();
  final int versionCode;
  private String zzWP;
  private String zzWQ;
  private Uri zzWR;
  private String zzWk;
  private String zzXj;
  private GoogleSignInAccount zzXm;
  private String zzXn;
  private String zzrG;
  
  SignInAccount(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, Uri paramUri, GoogleSignInAccount paramGoogleSignInAccount, String paramString5, String paramString6)
  {
    this.versionCode = paramInt;
    this.zzWP = zzx.zzh(paramString3, "Email cannot be empty.");
    this.zzWQ = paramString4;
    this.zzWR = paramUri;
    this.zzXj = paramString1;
    this.zzWk = paramString2;
    this.zzXm = paramGoogleSignInAccount;
    this.zzrG = zzx.zzcM(paramString5);
    this.zzXn = paramString6;
  }
  
  public static SignInAccount zza(zzd paramzzd, String paramString1, String paramString2, String paramString3, Uri paramUri, String paramString4, String paramString5)
  {
    String str = null;
    if (paramzzd != null) {
      str = paramzzd.zzmT();
    }
    return new SignInAccount(2, str, paramString1, paramString2, paramString3, paramUri, null, paramString4, paramString5);
  }
  
  public static SignInAccount zzbM(String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    paramString = localJSONObject.optString("photoUrl", null);
    if (!TextUtils.isEmpty(paramString)) {}
    for (paramString = Uri.parse(paramString);; paramString = null) {
      return zza(zzd.zzbL(localJSONObject.optString("providerId", null)), localJSONObject.optString("tokenId", null), localJSONObject.getString("email"), localJSONObject.optString("displayName", null), paramString, localJSONObject.getString("localId"), localJSONObject.optString("refreshToken")).zza(GoogleSignInAccount.zzbH(localJSONObject.optString("googleSignInAccount")));
    }
  }
  
  private JSONObject zzmJ()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("email", getEmail());
      if (!TextUtils.isEmpty(this.zzWQ)) {
        localJSONObject.put("displayName", this.zzWQ);
      }
      if (this.zzWR != null) {
        localJSONObject.put("photoUrl", this.zzWR.toString());
      }
      if (!TextUtils.isEmpty(this.zzXj)) {
        localJSONObject.put("providerId", this.zzXj);
      }
      if (!TextUtils.isEmpty(this.zzWk)) {
        localJSONObject.put("tokenId", this.zzWk);
      }
      if (this.zzXm != null) {
        localJSONObject.put("googleSignInAccount", this.zzXm.zzmI());
      }
      if (!TextUtils.isEmpty(this.zzXn)) {
        localJSONObject.put("refreshToken", this.zzXn);
      }
      localJSONObject.put("localId", getUserId());
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
  
  public String getDisplayName()
  {
    return this.zzWQ;
  }
  
  public String getEmail()
  {
    return this.zzWP;
  }
  
  public String getIdToken()
  {
    return this.zzWk;
  }
  
  public Uri getPhotoUrl()
  {
    return this.zzWR;
  }
  
  public String getUserId()
  {
    return this.zzrG;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  public SignInAccount zza(GoogleSignInAccount paramGoogleSignInAccount)
  {
    this.zzXm = paramGoogleSignInAccount;
    return this;
  }
  
  public String zzmI()
  {
    return zzmJ().toString();
  }
  
  String zzmT()
  {
    return this.zzXj;
  }
  
  public zzd zzmU()
  {
    return zzd.zzbL(this.zzXj);
  }
  
  public GoogleSignInAccount zzmV()
  {
    return this.zzXm;
  }
  
  public String zzmW()
  {
    return this.zzXn;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\api\signin\SignInAccount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */