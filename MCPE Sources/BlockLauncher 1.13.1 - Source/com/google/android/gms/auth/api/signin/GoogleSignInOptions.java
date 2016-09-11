package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions
  implements Api.ApiOptions.Optional, SafeParcelable
{
  public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zzc();
  public static final GoogleSignInOptions DEFAULT_SIGN_IN;
  private static Comparator<Scope> zzWV = new Comparator()
  {
    public int zza(Scope paramAnonymousScope1, Scope paramAnonymousScope2)
    {
      return paramAnonymousScope1.zzpb().compareTo(paramAnonymousScope2.zzpb());
    }
  };
  public static final Scope zzWW = new Scope("profile");
  public static final Scope zzWX = new Scope("email");
  public static final Scope zzWY = new Scope("openid");
  final int versionCode;
  private Account zzTI;
  private final ArrayList<Scope> zzWZ;
  private boolean zzXa;
  private final boolean zzXb;
  private final boolean zzXc;
  private String zzXd;
  private String zzXe;
  
  static
  {
    DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
  }
  
  GoogleSignInOptions(int paramInt, ArrayList<Scope> paramArrayList, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2)
  {
    this.versionCode = paramInt;
    this.zzWZ = paramArrayList;
    this.zzTI = paramAccount;
    this.zzXa = paramBoolean1;
    this.zzXb = paramBoolean2;
    this.zzXc = paramBoolean3;
    this.zzXd = paramString1;
    this.zzXe = paramString2;
  }
  
  private GoogleSignInOptions(Set<Scope> paramSet, Account paramAccount, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString1, String paramString2)
  {
    this(2, new ArrayList(paramSet), paramAccount, paramBoolean1, paramBoolean2, paramBoolean3, paramString1, paramString2);
  }
  
  @Nullable
  public static GoogleSignInOptions zzbJ(@Nullable String paramString)
    throws JSONException
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject(paramString);
    HashSet localHashSet = new HashSet();
    paramString = localJSONObject.getJSONArray("scopes");
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      localHashSet.add(new Scope(paramString.getString(i)));
      i += 1;
    }
    paramString = localJSONObject.optString("accountName", null);
    if (!TextUtils.isEmpty(paramString)) {}
    for (paramString = new Account(paramString, "com.google");; paramString = null) {
      return new GoogleSignInOptions(localHashSet, paramString, localJSONObject.getBoolean("idTokenRequested"), localJSONObject.getBoolean("serverAuthRequested"), localJSONObject.getBoolean("forceCodeForRefreshToken"), localJSONObject.optString("serverClientId", null), localJSONObject.optString("hostedDomain", null));
    }
  }
  
  private JSONObject zzmJ()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      JSONArray localJSONArray = new JSONArray();
      Collections.sort(this.zzWZ, zzWV);
      Iterator localIterator = this.zzWZ.iterator();
      while (localIterator.hasNext()) {
        localJSONArray.put(((Scope)localIterator.next()).zzpb());
      }
      localJSONException.put("scopes", localJSONArray);
    }
    catch (JSONException localJSONException)
    {
      throw new RuntimeException(localJSONException);
    }
    if (this.zzTI != null) {
      localJSONException.put("accountName", this.zzTI.name);
    }
    localJSONException.put("idTokenRequested", this.zzXa);
    localJSONException.put("forceCodeForRefreshToken", this.zzXc);
    localJSONException.put("serverAuthRequested", this.zzXb);
    if (!TextUtils.isEmpty(this.zzXd)) {
      localJSONException.put("serverClientId", this.zzXd);
    }
    if (!TextUtils.isEmpty(this.zzXe)) {
      localJSONException.put("hostedDomain", this.zzXe);
    }
    return localJSONException;
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
        paramObject = (GoogleSignInOptions)paramObject;
        if ((this.zzWZ.size() != ((GoogleSignInOptions)paramObject).zzmN().size()) || (!this.zzWZ.containsAll(((GoogleSignInOptions)paramObject).zzmN()))) {
          continue;
        }
        if (this.zzTI == null)
        {
          if (((GoogleSignInOptions)paramObject).getAccount() != null) {
            continue;
          }
          label56:
          if (!TextUtils.isEmpty(this.zzXd)) {
            break label128;
          }
          if (!TextUtils.isEmpty(((GoogleSignInOptions)paramObject).zzmR())) {
            continue;
          }
        }
        while ((this.zzXc == ((GoogleSignInOptions)paramObject).zzmQ()) && (this.zzXa == ((GoogleSignInOptions)paramObject).zzmO()) && (this.zzXb == ((GoogleSignInOptions)paramObject).zzmP()))
        {
          return true;
          if (!this.zzTI.equals(((GoogleSignInOptions)paramObject).getAccount())) {
            break;
          }
          break label56;
          label128:
          boolean bool = this.zzXd.equals(((GoogleSignInOptions)paramObject).zzmR());
          if (!bool) {
            break;
          }
        }
        return false;
      }
      catch (ClassCastException paramObject) {}
    }
  }
  
  public Account getAccount()
  {
    return this.zzTI;
  }
  
  public Scope[] getScopeArray()
  {
    return (Scope[])this.zzWZ.toArray(new Scope[this.zzWZ.size()]);
  }
  
  public int hashCode()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzWZ.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((Scope)localIterator.next()).zzpb());
    }
    Collections.sort(localArrayList);
    return new zze().zzp(localArrayList).zzp(this.zzTI).zzp(this.zzXd).zzP(this.zzXc).zzP(this.zzXa).zzP(this.zzXb).zzne();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public String zzmI()
  {
    return zzmJ().toString();
  }
  
  public ArrayList<Scope> zzmN()
  {
    return new ArrayList(this.zzWZ);
  }
  
  public boolean zzmO()
  {
    return this.zzXa;
  }
  
  public boolean zzmP()
  {
    return this.zzXb;
  }
  
  public boolean zzmQ()
  {
    return this.zzXc;
  }
  
  public String zzmR()
  {
    return this.zzXd;
  }
  
  public String zzmS()
  {
    return this.zzXe;
  }
  
  public static final class Builder
  {
    private Account zzTI;
    private boolean zzXa;
    private boolean zzXb;
    private boolean zzXc;
    private String zzXd;
    private String zzXe;
    private Set<Scope> zzXf = new HashSet();
    
    public Builder() {}
    
    public Builder(@NonNull GoogleSignInOptions paramGoogleSignInOptions)
    {
      zzx.zzz(paramGoogleSignInOptions);
      this.zzXf = new HashSet(GoogleSignInOptions.zzb(paramGoogleSignInOptions));
      this.zzXb = GoogleSignInOptions.zzc(paramGoogleSignInOptions);
      this.zzXc = GoogleSignInOptions.zzd(paramGoogleSignInOptions);
      this.zzXa = GoogleSignInOptions.zze(paramGoogleSignInOptions);
      this.zzXd = GoogleSignInOptions.zzf(paramGoogleSignInOptions);
      this.zzTI = GoogleSignInOptions.zzg(paramGoogleSignInOptions);
      this.zzXe = GoogleSignInOptions.zzh(paramGoogleSignInOptions);
    }
    
    private String zzbK(String paramString)
    {
      zzx.zzcM(paramString);
      if ((this.zzXd == null) || (this.zzXd.equals(paramString))) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "two different server client ids provided");
        return paramString;
      }
    }
    
    public GoogleSignInOptions build()
    {
      if ((this.zzXa) && ((this.zzTI == null) || (!this.zzXf.isEmpty()))) {
        requestId();
      }
      return new GoogleSignInOptions(this.zzXf, this.zzTI, this.zzXa, this.zzXb, this.zzXc, this.zzXd, this.zzXe, null);
    }
    
    public Builder requestEmail()
    {
      this.zzXf.add(GoogleSignInOptions.zzWX);
      return this;
    }
    
    public Builder requestId()
    {
      this.zzXf.add(GoogleSignInOptions.zzWY);
      return this;
    }
    
    public Builder requestIdToken(String paramString)
    {
      this.zzXa = true;
      this.zzXd = zzbK(paramString);
      return this;
    }
    
    public Builder requestProfile()
    {
      this.zzXf.add(GoogleSignInOptions.zzWW);
      return this;
    }
    
    public Builder requestScopes(Scope paramScope, Scope... paramVarArgs)
    {
      this.zzXf.add(paramScope);
      this.zzXf.addAll(Arrays.asList(paramVarArgs));
      return this;
    }
    
    public Builder requestServerAuthCode(String paramString)
    {
      return requestServerAuthCode(paramString, false);
    }
    
    public Builder requestServerAuthCode(String paramString, boolean paramBoolean)
    {
      this.zzXb = true;
      this.zzXd = zzbK(paramString);
      this.zzXc = paramBoolean;
      return this;
    }
    
    public Builder setAccountName(String paramString)
    {
      this.zzTI = new Account(zzx.zzcM(paramString), "com.google");
      return this;
    }
    
    public Builder setHostedDomain(String paramString)
    {
      this.zzXe = zzx.zzcM(paramString);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\api\signin\GoogleSignInOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */