package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.fitness.data.DataType;

public class DataTypeResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<DataTypeResult> CREATOR = new zzg();
  private final int mVersionCode;
  private final Status zzUX;
  private final DataType zzavT;
  
  DataTypeResult(int paramInt, Status paramStatus, DataType paramDataType)
  {
    this.mVersionCode = paramInt;
    this.zzUX = paramStatus;
    this.zzavT = paramDataType;
  }
  
  public DataTypeResult(Status paramStatus, DataType paramDataType)
  {
    this.mVersionCode = 2;
    this.zzUX = paramStatus;
    this.zzavT = paramDataType;
  }
  
  public static DataTypeResult zzS(Status paramStatus)
  {
    return new DataTypeResult(paramStatus, null);
  }
  
  private boolean zzb(DataTypeResult paramDataTypeResult)
  {
    return (this.zzUX.equals(paramDataTypeResult.zzUX)) && (zzw.equal(this.zzavT, paramDataTypeResult.zzavT));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataTypeResult)) && (zzb((DataTypeResult)paramObject)));
  }
  
  public DataType getDataType()
  {
    return this.zzavT;
  }
  
  public Status getStatus()
  {
    return this.zzUX;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzUX, this.zzavT });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("status", this.zzUX).zzg("dataType", this.zzavT).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\result\DataTypeResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */