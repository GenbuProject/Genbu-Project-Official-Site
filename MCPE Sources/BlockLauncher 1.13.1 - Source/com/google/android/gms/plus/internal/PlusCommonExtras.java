package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

@KeepName
public class PlusCommonExtras
  implements SafeParcelable
{
  public static final zzf CREATOR = new zzf();
  private final int mVersionCode;
  private String zzbeo;
  private String zzbep;
  
  public PlusCommonExtras()
  {
    this.mVersionCode = 1;
    this.zzbeo = "";
    this.zzbep = "";
  }
  
  PlusCommonExtras(int paramInt, String paramString1, String paramString2)
  {
    this.mVersionCode = paramInt;
    this.zzbeo = paramString1;
    this.zzbep = paramString2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof PlusCommonExtras)) {}
    do
    {
      return false;
      paramObject = (PlusCommonExtras)paramObject;
    } while ((this.mVersionCode != ((PlusCommonExtras)paramObject).mVersionCode) || (!zzw.equal(this.zzbeo, ((PlusCommonExtras)paramObject).zzbeo)) || (!zzw.equal(this.zzbep, ((PlusCommonExtras)paramObject).zzbep)));
    return true;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.mVersionCode), this.zzbeo, this.zzbep });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("versionCode", Integer.valueOf(this.mVersionCode)).zzg("Gpsrc", this.zzbeo).zzg("ClientCallingPackage", this.zzbep).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzf.zza(this, paramParcel, paramInt);
  }
  
  public String zzFb()
  {
    return this.zzbeo;
  }
  
  public String zzFc()
  {
    return this.zzbep;
  }
  
  public void zzJ(Bundle paramBundle)
  {
    paramBundle.putByteArray("android.gms.plus.internal.PlusCommonExtras.extraPlusCommon", zzc.zza(this));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\plus\internal\PlusCommonExtras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */