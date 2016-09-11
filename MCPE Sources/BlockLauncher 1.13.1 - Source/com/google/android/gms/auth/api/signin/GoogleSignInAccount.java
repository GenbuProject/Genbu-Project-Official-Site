package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzmt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount
  implements SafeParcelable
{
  public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zzb();
  public static zzmq zzWO = zzmt.zzsc();
  private static Comparator<Scope> zzWV = new Comparator()
  {
    public int zza(Scope paramAnonymousScope1, Scope paramAnonymousScope2)
    {
      return paramAnonymousScope1.zzpb().compareTo(paramAnonymousScope2.zzpb());
    }
  };
  final int versionCode;
  List<Scope> zzVs;
  private String zzWP;
  private String zzWQ;
  private Uri zzWR;
  private String zzWS;
  private long zzWT;
  private String zzWU;
  private String zzWk;
  private String zzyv;
  
  GoogleSignInAccount(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, Uri paramUri, String paramString5, long paramLong, String paramString6, List<Scope> paramList)
  {
    this.versionCode = paramInt;
    this.zzyv = paramString1;
    this.zzWk = paramString2;
    this.zzWP = paramString3;
    this.zzWQ = paramString4;
    this.zzWR = paramUri;
    this.zzWS = paramString5;
    this.zzWT = paramLong;
    this.zzWU = paramString6;
    this.zzVs = paramList;
  }
  
  public static GoogleSignInAccount zza(@Nullable String paramString1, @Nullable String paramString2, @Nullable String paramString3, @Nullable String paramString4, @Nullable Uri paramUri, @Nullable Long paramLong, @NonNull String paramString5, @NonNull Set<Scope> paramSet)
  {
    Long localLong = paramLong;
    if (paramLong == null) {
      localLong = Long.valueOf(zzWO.currentTimeMillis() / 1000L);
    }
    return new GoogleSignInAccount(2, paramString1, paramString2, paramString3, paramString4, paramUri, null, localLong.longValue(), zzx.zzcM(paramString5), new ArrayList((Collection)zzx.zzz(paramSet)));
  }
  
  @Nullable
  public static GoogleSignInAccount zzbH(@Nullable String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    paramString = localJSONObject.optString("photoUrl", null);
    if (!TextUtils.isEmpty(paramString)) {}
    for (paramString = Uri.parse(paramString);; paramString = null)
    {
      long l = Long.parseLong(localJSONObject.getString("expirationTime"));
      HashSet localHashSet = new HashSet();
      JSONArray localJSONArray = localJSONObject.getJSONArray("grantedScopes");
      int j = localJSONArray.length();
      int i = 0;
      while (i < j)
      {
        localHashSet.add(new Scope(localJSONArray.getString(i)));
        i += 1;
      }
      return zza(localJSONObject.optString("id"), localJSONObject.optString("tokenId", null), localJSONObject.optString("email", null), localJSONObject.optString("displayName", null), paramString, Long.valueOf(l), localJSONObject.getString("obfuscatedIdentifier"), localHashSet).zzbI(localJSONObject.optString("serverAuthCode", null));
    }
  }
  
  private JSONObject zzmJ()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if (getId() != null) {
        localJSONObject.put("id", getId());
      }
      if (getIdToken() != null) {
        localJSONObject.put("tokenId", getIdToken());
      }
      if (getEmail() != null) {
        localJSONObject.put("email", getEmail());
      }
      if (getDisplayName() != null) {
        localJSONObject.put("displayName", getDisplayName());
      }
      if (getPhotoUrl() != null) {
        localJSONObject.put("photoUrl", getPhotoUrl().toString());
      }
      if (getServerAuthCode() != null) {
        localJSONObject.put("serverAuthCode", getServerAuthCode());
      }
      localJSONObject.put("expirationTime", this.zzWT);
      localJSONObject.put("obfuscatedIdentifier", zzmL());
      JSONArray localJSONArray = new JSONArray();
      Collections.sort(this.zzVs, zzWV);
      Iterator localIterator = this.zzVs.iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((Scope)localIterator.next()).zzpb());
      }
      localJSONException.put("grantedScopes", localJSONArray);
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
    return localJSONException;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof GoogleSignInAccount)) {
      return false;
    }
    return ((GoogleSignInAccount)paramObject).zzmI().equals(zzmI());
  }
  
  @Nullable
  public String getDisplayName()
  {
    return this.zzWQ;
  }
  
  @Nullable
  public String getEmail()
  {
    return this.zzWP;
  }
  
  @NonNull
  public Set<Scope> getGrantedScopes()
  {
    return new HashSet(this.zzVs);
  }
  
  @Nullable
  public String getId()
  {
    return this.zzyv;
  }
  
  @Nullable
  public String getIdToken()
  {
    return this.zzWk;
  }
  
  @Nullable
  public Uri getPhotoUrl()
  {
    return this.zzWR;
  }
  
  @Nullable
  public String getServerAuthCode()
  {
    return this.zzWS;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public boolean zzb()
  {
    return zzWO.currentTimeMillis() / 1000L >= this.zzWT - 300L;
  }
  
  public GoogleSignInAccount zzbI(String paramString)
  {
    this.zzWS = paramString;
    return this;
  }
  
  public String zzmI()
  {
    return zzmJ().toString();
  }
  
  public long zzmK()
  {
    return this.zzWT;
  }
  
  @NonNull
  public String zzmL()
  {
    return this.zzWU;
  }
  
  public String zzmM()
  {
    JSONObject localJSONObject = zzmJ();
    localJSONObject.remove("serverAuthCode");
    return localJSONObject.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\api\signin\GoogleSignInAccount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */