package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.internal.zznr;
import java.util.Collections;
import java.util.List;

public class BleDevice
  implements SafeParcelable
{
  public static final Parcelable.Creator<BleDevice> CREATOR = new zzb();
  private final String mName;
  private final int mVersionCode;
  private final String zzawc;
  private final List<String> zzawd;
  private final List<DataType> zzawe;
  
  BleDevice(int paramInt, String paramString1, String paramString2, List<String> paramList, List<DataType> paramList1)
  {
    this.mVersionCode = paramInt;
    this.zzawc = paramString1;
    this.mName = paramString2;
    this.zzawd = Collections.unmodifiableList(paramList);
    this.zzawe = Collections.unmodifiableList(paramList1);
  }
  
  private boolean zza(BleDevice paramBleDevice)
  {
    return (this.mName.equals(paramBleDevice.mName)) && (this.zzawc.equals(paramBleDevice.zzawc)) && (zznr.zza(paramBleDevice.zzawd, this.zzawd)) && (zznr.zza(this.zzawe, paramBleDevice.zzawe));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof BleDevice)) && (zza((BleDevice)paramObject)));
  }
  
  public String getAddress()
  {
    return this.zzawc;
  }
  
  public List<DataType> getDataTypes()
  {
    return this.zzawe;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public List<String> getSupportedProfiles()
  {
    return this.zzawd;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.mName, this.zzawc, this.zzawd, this.zzawe });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("name", this.mName).zzg("address", this.zzawc).zzg("dataTypes", this.zzawe).zzg("supportedProfiles", this.zzawd).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\data\BleDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */