package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomPropertyKey
  implements SafeParcelable
{
  public static final Parcelable.Creator<CustomPropertyKey> CREATOR = new zzc();
  public static final int PRIVATE = 1;
  public static final int PUBLIC = 0;
  private static final Pattern zzasJ = Pattern.compile("[\\w.!@$%^&*()/-]+");
  final int mVersionCode;
  final int mVisibility;
  final String zzvs;
  
  CustomPropertyKey(int paramInt1, String paramString, int paramInt2)
  {
    zzx.zzb(paramString, "key");
    zzx.zzb(zzasJ.matcher(paramString).matches(), "key name characters must be alphanumeric or one of .!@$%^&*()-_/");
    boolean bool1 = bool2;
    if (paramInt2 != 0) {
      if (paramInt2 != 1) {
        break label69;
      }
    }
    label69:
    for (bool1 = bool2;; bool1 = false)
    {
      zzx.zzb(bool1, "visibility must be either PUBLIC or PRIVATE");
      this.mVersionCode = paramInt1;
      this.zzvs = paramString;
      this.mVisibility = paramInt2;
      return;
    }
  }
  
  public CustomPropertyKey(String paramString, int paramInt)
  {
    this(1, paramString, paramInt);
  }
  
  public static CustomPropertyKey fromJson(JSONObject paramJSONObject)
    throws JSONException
  {
    return new CustomPropertyKey(paramJSONObject.getString("key"), paramJSONObject.getInt("visibility"));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == null) {}
    do
    {
      return false;
      if (paramObject == this) {
        return true;
      }
    } while (!(paramObject instanceof CustomPropertyKey));
    paramObject = (CustomPropertyKey)paramObject;
    if ((((CustomPropertyKey)paramObject).getKey().equals(this.zzvs)) && (((CustomPropertyKey)paramObject).getVisibility() == this.mVisibility)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public String getKey()
  {
    return this.zzvs;
  }
  
  public int getVisibility()
  {
    return this.mVisibility;
  }
  
  public int hashCode()
  {
    return (this.zzvs + this.mVisibility).hashCode();
  }
  
  public JSONObject toJson()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("key", getKey());
    localJSONObject.put("visibility", getVisibility());
    return localJSONObject;
  }
  
  public String toString()
  {
    return "CustomPropertyKey(" + this.zzvs + "," + this.mVisibility + ")";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\metadata\CustomPropertyKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */