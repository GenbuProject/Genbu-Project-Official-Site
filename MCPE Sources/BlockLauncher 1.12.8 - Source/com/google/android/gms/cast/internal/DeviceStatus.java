package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class DeviceStatus
  implements SafeParcelable
{
  public static final Parcelable.Creator<DeviceStatus> CREATOR = new zzg();
  private final int mVersionCode;
  private double zzabs;
  private boolean zzabt;
  private int zzadG;
  private int zzadH;
  private ApplicationMetadata zzadR;
  
  public DeviceStatus()
  {
    this(3, NaN.0D, false, -1, null, -1);
  }
  
  DeviceStatus(int paramInt1, double paramDouble, boolean paramBoolean, int paramInt2, ApplicationMetadata paramApplicationMetadata, int paramInt3)
  {
    this.mVersionCode = paramInt1;
    this.zzabs = paramDouble;
    this.zzabt = paramBoolean;
    this.zzadG = paramInt2;
    this.zzadR = paramApplicationMetadata;
    this.zzadH = paramInt3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof DeviceStatus)) {
        return false;
      }
      paramObject = (DeviceStatus)paramObject;
    } while ((this.zzabs == ((DeviceStatus)paramObject).zzabs) && (this.zzabt == ((DeviceStatus)paramObject).zzabt) && (this.zzadG == ((DeviceStatus)paramObject).zzadG) && (zzf.zza(this.zzadR, ((DeviceStatus)paramObject).zzadR)) && (this.zzadH == ((DeviceStatus)paramObject).zzadH));
    return false;
  }
  
  public ApplicationMetadata getApplicationMetadata()
  {
    return this.zzadR;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Double.valueOf(this.zzabs), Boolean.valueOf(this.zzabt), Integer.valueOf(this.zzadG), this.zzadR, Integer.valueOf(this.zzadH) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
  
  public double zzok()
  {
    return this.zzabs;
  }
  
  public int zzol()
  {
    return this.zzadG;
  }
  
  public int zzom()
  {
    return this.zzadH;
  }
  
  public boolean zzot()
  {
    return this.zzabt;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\internal\DeviceStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */