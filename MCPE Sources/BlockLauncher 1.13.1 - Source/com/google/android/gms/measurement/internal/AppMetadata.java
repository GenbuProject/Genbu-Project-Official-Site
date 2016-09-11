package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public class AppMetadata
  implements SafeParcelable
{
  public static final zzb CREATOR = new zzb();
  public final String packageName;
  public final int versionCode;
  public final String zzaMV;
  public final String zzaVt;
  public final String zzaVu;
  public final long zzaVv;
  public final long zzaVw;
  public final String zzaVx;
  public final boolean zzaVy;
  public final boolean zzaVz;
  
  AppMetadata(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, long paramLong1, long paramLong2, String paramString5, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.versionCode = paramInt;
    this.packageName = paramString1;
    this.zzaVt = paramString2;
    this.zzaMV = paramString3;
    this.zzaVu = paramString4;
    this.zzaVv = paramLong1;
    this.zzaVw = paramLong2;
    this.zzaVx = paramString5;
    if (paramInt >= 3) {}
    for (this.zzaVy = paramBoolean1;; this.zzaVy = true)
    {
      this.zzaVz = paramBoolean2;
      return;
    }
  }
  
  AppMetadata(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong1, long paramLong2, String paramString5, boolean paramBoolean1, boolean paramBoolean2)
  {
    zzx.zzcM(paramString1);
    this.versionCode = 4;
    this.packageName = paramString1;
    paramString1 = paramString2;
    if (TextUtils.isEmpty(paramString2)) {
      paramString1 = null;
    }
    this.zzaVt = paramString1;
    this.zzaMV = paramString3;
    this.zzaVu = paramString4;
    this.zzaVv = paramLong1;
    this.zzaVw = paramLong2;
    this.zzaVx = paramString5;
    this.zzaVy = paramBoolean1;
    this.zzaVz = paramBoolean2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\AppMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */