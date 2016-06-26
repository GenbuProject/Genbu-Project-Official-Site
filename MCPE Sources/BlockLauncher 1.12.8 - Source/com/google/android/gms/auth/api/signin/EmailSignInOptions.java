package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Patterns;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class EmailSignInOptions
  implements SafeParcelable
{
  public static final Parcelable.Creator<EmailSignInOptions> CREATOR = new zza();
  final int versionCode;
  private final Uri zzWL;
  private String zzWM;
  private Uri zzWN;
  
  EmailSignInOptions(int paramInt, Uri paramUri1, String paramString, Uri paramUri2)
  {
    zzx.zzb(paramUri1, "Server widget url cannot be null in order to use email/password sign in.");
    zzx.zzh(paramUri1.toString(), "Server widget url cannot be null in order to use email/password sign in.");
    zzx.zzb(Patterns.WEB_URL.matcher(paramUri1.toString()).matches(), "Invalid server widget url");
    this.versionCode = paramInt;
    this.zzWL = paramUri1;
    this.zzWM = paramString;
    this.zzWN = paramUri2;
  }
  
  private JSONObject zzmJ()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("serverWidgetUrl", this.zzWL.toString());
      if (!TextUtils.isEmpty(this.zzWM)) {
        localJSONObject.put("modeQueryName", this.zzWM);
      }
      if (this.zzWN != null) {
        localJSONObject.put("tosUrl", this.zzWN.toString());
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
        paramObject = (EmailSignInOptions)paramObject;
        if (this.zzWL.equals(((EmailSignInOptions)paramObject).zzmF())) {
          if (this.zzWN == null)
          {
            if (((EmailSignInOptions)paramObject).zzmG() != null) {}
          }
          else
          {
            for (;;)
            {
              if (!TextUtils.isEmpty(this.zzWM)) {
                break label79;
              }
              if (!TextUtils.isEmpty(((EmailSignInOptions)paramObject).zzmH())) {
                break;
              }
              break label101;
              if (!this.zzWN.equals(((EmailSignInOptions)paramObject).zzmG())) {
                break;
              }
            }
            label79:
            boolean bool = this.zzWM.equals(((EmailSignInOptions)paramObject).zzmH());
            if (!bool) {}
          }
        }
      }
      catch (ClassCastException paramObject)
      {
        return false;
      }
    }
    label101:
    return true;
  }
  
  public int hashCode()
  {
    return new zze().zzp(this.zzWL).zzp(this.zzWN).zzp(this.zzWM).zzne();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public Uri zzmF()
  {
    return this.zzWL;
  }
  
  public Uri zzmG()
  {
    return this.zzWN;
  }
  
  public String zzmH()
  {
    return this.zzWM;
  }
  
  public String zzmI()
  {
    return zzmJ().toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\signin\EmailSignInOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */