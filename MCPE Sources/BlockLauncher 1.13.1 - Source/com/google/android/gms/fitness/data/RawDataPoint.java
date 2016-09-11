package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@KeepName
public final class RawDataPoint
  implements SafeParcelable
{
  public static final Parcelable.Creator<RawDataPoint> CREATOR = new zzo();
  final int mVersionCode;
  public final long zzawj;
  public final long zzawk;
  public final Value[] zzawl;
  public final long zzawn;
  public final long zzawo;
  public final int zzaxg;
  public final int zzaxh;
  
  public RawDataPoint(int paramInt1, long paramLong1, long paramLong2, Value[] paramArrayOfValue, int paramInt2, int paramInt3, long paramLong3, long paramLong4)
  {
    this.mVersionCode = paramInt1;
    this.zzawj = paramLong1;
    this.zzawk = paramLong2;
    this.zzaxg = paramInt2;
    this.zzaxh = paramInt3;
    this.zzawn = paramLong3;
    this.zzawo = paramLong4;
    this.zzawl = paramArrayOfValue;
  }
  
  RawDataPoint(DataPoint paramDataPoint, List<DataSource> paramList)
  {
    this.mVersionCode = 4;
    this.zzawj = paramDataPoint.getTimestamp(TimeUnit.NANOSECONDS);
    this.zzawk = paramDataPoint.getStartTime(TimeUnit.NANOSECONDS);
    this.zzawl = paramDataPoint.zzuf();
    this.zzaxg = zzt.zza(paramDataPoint.getDataSource(), paramList);
    this.zzaxh = zzt.zza(paramDataPoint.getOriginalDataSource(), paramList);
    this.zzawn = paramDataPoint.zzug();
    this.zzawo = paramDataPoint.zzuh();
  }
  
  private boolean zza(RawDataPoint paramRawDataPoint)
  {
    return (this.zzawj == paramRawDataPoint.zzawj) && (this.zzawk == paramRawDataPoint.zzawk) && (Arrays.equals(this.zzawl, paramRawDataPoint.zzawl)) && (this.zzaxg == paramRawDataPoint.zzaxg) && (this.zzaxh == paramRawDataPoint.zzaxh) && (this.zzawn == paramRawDataPoint.zzawn);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof RawDataPoint)) && (zza((RawDataPoint)paramObject)));
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Long.valueOf(this.zzawj), Long.valueOf(this.zzawk) });
  }
  
  public String toString()
  {
    return String.format("RawDataPoint{%s@[%s, %s](%d,%d)}", new Object[] { Arrays.toString(this.zzawl), Long.valueOf(this.zzawk), Long.valueOf(this.zzawj), Integer.valueOf(this.zzaxg), Integer.valueOf(this.zzaxh) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzo.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\data\RawDataPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */