package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public final class IdpTokenType
  implements SafeParcelable
{
  public static final Parcelable.Creator<IdpTokenType> CREATOR = new zzj();
  public static final IdpTokenType zzXA = new IdpTokenType("accessToken");
  public static final IdpTokenType zzXB = new IdpTokenType("idToken");
  final int versionCode;
  private final String zzXC;
  
  IdpTokenType(int paramInt, String paramString)
  {
    this.versionCode = paramInt;
    this.zzXC = zzx.zzcM(paramString);
  }
  
  private IdpTokenType(String paramString)
  {
    this(1, paramString);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramObject != null) {}
    try
    {
      boolean bool3 = this.zzXC.equals(((IdpTokenType)paramObject).zzng());
      bool1 = bool2;
      if (bool3) {
        bool1 = true;
      }
      return bool1;
    }
    catch (ClassCastException paramObject) {}
    return false;
  }
  
  public int hashCode()
  {
    return this.zzXC.hashCode();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj.zza(this, paramParcel, paramInt);
  }
  
  public String zzng()
  {
    return this.zzXC;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\api\signin\internal\IdpTokenType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */