package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import java.util.List;

public class TokenData
  implements SafeParcelable
{
  public static final zze CREATOR = new zze();
  final int mVersionCode;
  private final String zzVo;
  private final Long zzVp;
  private final boolean zzVq;
  private final boolean zzVr;
  private final List<String> zzVs;
  
  TokenData(int paramInt, String paramString, Long paramLong, boolean paramBoolean1, boolean paramBoolean2, List<String> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzVo = zzx.zzcM(paramString);
    this.zzVp = paramLong;
    this.zzVq = paramBoolean1;
    this.zzVr = paramBoolean2;
    this.zzVs = paramList;
  }
  
  @Nullable
  public static TokenData zzc(Bundle paramBundle, String paramString)
  {
    paramBundle.setClassLoader(TokenData.class.getClassLoader());
    paramBundle = paramBundle.getBundle(paramString);
    if (paramBundle == null) {
      return null;
    }
    paramBundle.setClassLoader(TokenData.class.getClassLoader());
    return (TokenData)paramBundle.getParcelable("TokenData");
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof TokenData)) {}
    do
    {
      return false;
      paramObject = (TokenData)paramObject;
    } while ((!TextUtils.equals(this.zzVo, ((TokenData)paramObject).zzVo)) || (!zzw.equal(this.zzVp, ((TokenData)paramObject).zzVp)) || (this.zzVq != ((TokenData)paramObject).zzVq) || (this.zzVr != ((TokenData)paramObject).zzVr) || (!zzw.equal(this.zzVs, ((TokenData)paramObject).zzVs)));
    return true;
  }
  
  public String getToken()
  {
    return this.zzVo;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzVo, this.zzVp, Boolean.valueOf(this.zzVq), Boolean.valueOf(this.zzVr), this.zzVs });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  @Nullable
  public Long zzmn()
  {
    return this.zzVp;
  }
  
  public boolean zzmo()
  {
    return this.zzVq;
  }
  
  public boolean zzmp()
  {
    return this.zzVr;
  }
  
  @Nullable
  public List<String> zzmq()
  {
    return this.zzVs;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\TokenData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */