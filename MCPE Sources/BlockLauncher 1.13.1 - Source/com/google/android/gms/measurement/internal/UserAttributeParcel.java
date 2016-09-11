package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public class UserAttributeParcel
  implements SafeParcelable
{
  public static final zzah CREATOR = new zzah();
  public final String name;
  public final int versionCode;
  public final String zzaVW;
  public final long zzaZm;
  public final Long zzaZn;
  public final Float zzaZo;
  public final String zzamJ;
  
  UserAttributeParcel(int paramInt, String paramString1, long paramLong, Long paramLong1, Float paramFloat, String paramString2, String paramString3)
  {
    this.versionCode = paramInt;
    this.name = paramString1;
    this.zzaZm = paramLong;
    this.zzaZn = paramLong1;
    this.zzaZo = paramFloat;
    this.zzamJ = paramString2;
    this.zzaVW = paramString3;
  }
  
  UserAttributeParcel(zzai paramzzai)
  {
    this(paramzzai.mName, paramzzai.zzaZp, paramzzai.zzNc, paramzzai.zzaUa);
  }
  
  UserAttributeParcel(String paramString1, long paramLong, Object paramObject, String paramString2)
  {
    zzx.zzcM(paramString1);
    this.versionCode = 1;
    this.name = paramString1;
    this.zzaZm = paramLong;
    this.zzaVW = paramString2;
    if (paramObject == null)
    {
      this.zzaZn = null;
      this.zzaZo = null;
      this.zzamJ = null;
      return;
    }
    if ((paramObject instanceof Long))
    {
      this.zzaZn = ((Long)paramObject);
      this.zzaZo = null;
      this.zzamJ = null;
      return;
    }
    if ((paramObject instanceof Float))
    {
      this.zzaZn = null;
      this.zzaZo = ((Float)paramObject);
      this.zzamJ = null;
      return;
    }
    if ((paramObject instanceof String))
    {
      this.zzaZn = null;
      this.zzaZo = null;
      this.zzamJ = ((String)paramObject);
      return;
    }
    throw new IllegalArgumentException("User attribute given of un-supported type");
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Object getValue()
  {
    if (this.zzaZn != null) {
      return this.zzaZn;
    }
    if (this.zzaZo != null) {
      return this.zzaZo;
    }
    if (this.zzamJ != null) {
      return this.zzamJ;
    }
    return null;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzah.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\UserAttributeParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */