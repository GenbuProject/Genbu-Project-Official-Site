package com.google.android.gms.auth.api.credentials;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import java.util.Collections;
import java.util.List;

public class Credential
  implements SafeParcelable
{
  public static final Parcelable.Creator<Credential> CREATOR = new zza();
  public static final String EXTRA_KEY = "com.google.android.gms.credentials.Credential";
  @Nullable
  private final String mName;
  final int mVersionCode;
  @Nullable
  private final Uri zzVV;
  private final List<IdToken> zzVW;
  @Nullable
  private final String zzVX;
  @Nullable
  private final String zzVY;
  @Nullable
  private final String zzVZ;
  @Nullable
  private final String zzWa;
  private final String zzyv;
  
  Credential(int paramInt, String paramString1, String paramString2, Uri paramUri, List<IdToken> paramList, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    this.mVersionCode = paramInt;
    paramString1 = ((String)zzx.zzb(paramString1, "credential identifier cannot be null")).trim();
    zzx.zzh(paramString1, "credential identifier cannot be empty");
    this.zzyv = paramString1;
    paramString1 = paramString2;
    if (paramString2 != null)
    {
      paramString1 = paramString2;
      if (TextUtils.isEmpty(paramString2.trim())) {
        paramString1 = null;
      }
    }
    this.mName = paramString1;
    this.zzVV = paramUri;
    if (paramList == null) {}
    for (paramString1 = Collections.emptyList();; paramString1 = Collections.unmodifiableList(paramList))
    {
      this.zzVW = paramString1;
      this.zzVX = paramString3;
      if ((paramString3 == null) || (!paramString3.isEmpty())) {
        break;
      }
      throw new IllegalArgumentException("password cannot be empty");
    }
    if (!TextUtils.isEmpty(paramString4))
    {
      paramString1 = Uri.parse(paramString4).getScheme();
      if ((!"http".equalsIgnoreCase(paramString1)) && (!"https".equalsIgnoreCase(paramString1))) {
        break label208;
      }
    }
    label208:
    for (boolean bool = true;; bool = false)
    {
      zzx.zzac(bool);
      this.zzVY = paramString4;
      this.zzVZ = paramString5;
      this.zzWa = paramString6;
      if ((TextUtils.isEmpty(this.zzVX)) || (TextUtils.isEmpty(this.zzVY))) {
        break;
      }
      throw new IllegalStateException("password and accountType cannot both be set");
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof Credential)) {
        return false;
      }
      paramObject = (Credential)paramObject;
    } while ((TextUtils.equals(this.zzyv, ((Credential)paramObject).zzyv)) && (TextUtils.equals(this.mName, ((Credential)paramObject).mName)) && (zzw.equal(this.zzVV, ((Credential)paramObject).zzVV)) && (TextUtils.equals(this.zzVX, ((Credential)paramObject).zzVX)) && (TextUtils.equals(this.zzVY, ((Credential)paramObject).zzVY)) && (TextUtils.equals(this.zzVZ, ((Credential)paramObject).zzVZ)));
    return false;
  }
  
  @Nullable
  public String getAccountType()
  {
    return this.zzVY;
  }
  
  @Nullable
  public String getGeneratedPassword()
  {
    return this.zzVZ;
  }
  
  public String getId()
  {
    return this.zzyv;
  }
  
  public List<IdToken> getIdTokens()
  {
    return this.zzVW;
  }
  
  @Nullable
  public String getName()
  {
    return this.mName;
  }
  
  @Nullable
  public String getPassword()
  {
    return this.zzVX;
  }
  
  @Nullable
  public Uri getProfilePictureUri()
  {
    return this.zzVV;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzyv, this.mName, this.zzVV, this.zzVX, this.zzVY, this.zzVZ });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public String zzmx()
  {
    return this.zzWa;
  }
  
  public static class Builder
  {
    private String mName;
    private Uri zzVV;
    private List<IdToken> zzVW;
    private String zzVX;
    private String zzVY;
    private String zzVZ;
    private String zzWa;
    private final String zzyv;
    
    public Builder(Credential paramCredential)
    {
      this.zzyv = Credential.zza(paramCredential);
      this.mName = Credential.zzb(paramCredential);
      this.zzVV = Credential.zzc(paramCredential);
      this.zzVW = Credential.zzd(paramCredential);
      this.zzVX = Credential.zze(paramCredential);
      this.zzVY = Credential.zzf(paramCredential);
      this.zzVZ = Credential.zzg(paramCredential);
      this.zzWa = Credential.zzh(paramCredential);
    }
    
    public Builder(String paramString)
    {
      this.zzyv = paramString;
    }
    
    public Credential build()
    {
      return new Credential(3, this.zzyv, this.mName, this.zzVV, this.zzVW, this.zzVX, this.zzVY, this.zzVZ, this.zzWa);
    }
    
    public Builder setAccountType(String paramString)
    {
      this.zzVY = paramString;
      return this;
    }
    
    public Builder setName(String paramString)
    {
      this.mName = paramString;
      return this;
    }
    
    public Builder setPassword(String paramString)
    {
      this.zzVX = paramString;
      return this;
    }
    
    public Builder setProfilePictureUri(Uri paramUri)
    {
      this.zzVV = paramUri;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\credentials\Credential.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */