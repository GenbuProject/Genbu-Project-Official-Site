package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.List;

@KeepName
public final class RawDataSet
  implements SafeParcelable
{
  public static final Parcelable.Creator<RawDataSet> CREATOR = new zzp();
  final int mVersionCode;
  public final boolean zzawi;
  public final int zzaxg;
  public final int zzaxi;
  public final List<RawDataPoint> zzaxj;
  
  public RawDataSet(int paramInt1, int paramInt2, int paramInt3, List<RawDataPoint> paramList, boolean paramBoolean)
  {
    this.mVersionCode = paramInt1;
    this.zzaxg = paramInt2;
    this.zzaxi = paramInt3;
    this.zzaxj = paramList;
    this.zzawi = paramBoolean;
  }
  
  public RawDataSet(DataSet paramDataSet, List<DataSource> paramList, List<DataType> paramList1)
  {
    this.mVersionCode = 3;
    this.zzaxj = paramDataSet.zzv(paramList);
    this.zzawi = paramDataSet.zzuc();
    this.zzaxg = zzt.zza(paramDataSet.getDataSource(), paramList);
    this.zzaxi = zzt.zza(paramDataSet.getDataType(), paramList1);
  }
  
  private boolean zza(RawDataSet paramRawDataSet)
  {
    return (this.zzaxg == paramRawDataSet.zzaxg) && (this.zzawi == paramRawDataSet.zzawi) && (zzw.equal(this.zzaxj, paramRawDataSet.zzaxj));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof RawDataSet)) && (zza((RawDataSet)paramObject)));
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.zzaxg) });
  }
  
  public String toString()
  {
    return String.format("RawDataSet{%s@[%s]}", new Object[] { Integer.valueOf(this.zzaxg), this.zzaxj });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzp.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\RawDataSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */