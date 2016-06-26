package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource.Builder;
import com.google.android.gms.fitness.data.DataType;

public class DailyTotalResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<DailyTotalResult> CREATOR = new zzb();
  private final int mVersionCode;
  private final Status zzUX;
  private final DataSet zzaxn;
  
  DailyTotalResult(int paramInt, Status paramStatus, DataSet paramDataSet)
  {
    this.mVersionCode = paramInt;
    this.zzUX = paramStatus;
    this.zzaxn = paramDataSet;
  }
  
  public DailyTotalResult(DataSet paramDataSet, Status paramStatus)
  {
    this.mVersionCode = 1;
    this.zzUX = paramStatus;
    this.zzaxn = paramDataSet;
  }
  
  public static DailyTotalResult zza(Status paramStatus, DataType paramDataType)
  {
    return new DailyTotalResult(DataSet.create(new DataSource.Builder().setDataType(paramDataType).setType(1).build()), paramStatus);
  }
  
  private boolean zzb(DailyTotalResult paramDailyTotalResult)
  {
    return (this.zzUX.equals(paramDailyTotalResult.zzUX)) && (zzw.equal(this.zzaxn, paramDailyTotalResult.zzaxn));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DailyTotalResult)) && (zzb((DailyTotalResult)paramObject)));
  }
  
  public Status getStatus()
  {
    return this.zzUX;
  }
  
  @Nullable
  public DataSet getTotal()
  {
    return this.zzaxn;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzUX, this.zzaxn });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("status", this.zzUX).zzg("dataPoint", this.zzaxn).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\result\DailyTotalResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */