package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzns;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class DataPoint
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataPoint> CREATOR = new zzd();
  private final int mVersionCode;
  private final DataSource zzavU;
  private long zzawj;
  private long zzawk;
  private final Value[] zzawl;
  private DataSource zzawm;
  private long zzawn;
  private long zzawo;
  
  DataPoint(int paramInt, DataSource paramDataSource1, long paramLong1, long paramLong2, Value[] paramArrayOfValue, DataSource paramDataSource2, long paramLong3, long paramLong4)
  {
    this.mVersionCode = paramInt;
    this.zzavU = paramDataSource1;
    this.zzawm = paramDataSource2;
    this.zzawj = paramLong1;
    this.zzawk = paramLong2;
    this.zzawl = paramArrayOfValue;
    this.zzawn = paramLong3;
    this.zzawo = paramLong4;
  }
  
  private DataPoint(DataSource paramDataSource)
  {
    this.mVersionCode = 4;
    this.zzavU = ((DataSource)zzx.zzb(paramDataSource, "Data source cannot be null"));
    paramDataSource = paramDataSource.getDataType().getFields();
    this.zzawl = new Value[paramDataSource.size()];
    paramDataSource = paramDataSource.iterator();
    int i = 0;
    while (paramDataSource.hasNext())
    {
      Field localField = (Field)paramDataSource.next();
      this.zzawl[i] = new Value(localField.getFormat());
      i += 1;
    }
  }
  
  public DataPoint(DataSource paramDataSource1, DataSource paramDataSource2, RawDataPoint paramRawDataPoint)
  {
    this(4, paramDataSource1, zza(Long.valueOf(paramRawDataPoint.zzawj), 0L), zza(Long.valueOf(paramRawDataPoint.zzawk), 0L), paramRawDataPoint.zzawl, paramDataSource2, zza(Long.valueOf(paramRawDataPoint.zzawn), 0L), zza(Long.valueOf(paramRawDataPoint.zzawo), 0L));
  }
  
  DataPoint(List<DataSource> paramList, RawDataPoint paramRawDataPoint)
  {
    this(zza(paramList, paramRawDataPoint.zzaxg), zza(paramList, paramRawDataPoint.zzaxh), paramRawDataPoint);
  }
  
  public static DataPoint create(DataSource paramDataSource)
  {
    return new DataPoint(paramDataSource);
  }
  
  public static DataPoint extract(Intent paramIntent)
  {
    if (paramIntent == null) {
      return null;
    }
    return (DataPoint)zzc.zza(paramIntent, "com.google.android.gms.fitness.EXTRA_DATA_POINT", CREATOR);
  }
  
  private static long zza(Long paramLong, long paramLong1)
  {
    if (paramLong != null) {
      paramLong1 = paramLong.longValue();
    }
    return paramLong1;
  }
  
  private static DataSource zza(List<DataSource> paramList, int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < paramList.size())) {
      return (DataSource)paramList.get(paramInt);
    }
    return null;
  }
  
  private boolean zza(DataPoint paramDataPoint)
  {
    return (zzw.equal(this.zzavU, paramDataPoint.zzavU)) && (this.zzawj == paramDataPoint.zzawj) && (this.zzawk == paramDataPoint.zzawk) && (Arrays.equals(this.zzawl, paramDataPoint.zzawl)) && (zzw.equal(getOriginalDataSource(), paramDataPoint.getOriginalDataSource()));
  }
  
  private void zzeO(int paramInt)
  {
    List localList = getDataType().getFields();
    int i = localList.size();
    if (paramInt == i) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "Attempting to insert %s values, but needed %s: %s", new Object[] { Integer.valueOf(paramInt), Integer.valueOf(i), localList });
      return;
    }
  }
  
  private boolean zzue()
  {
    return getDataType() == DataType.TYPE_LOCATION_SAMPLE;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataPoint)) && (zza((DataPoint)paramObject)));
  }
  
  public DataSource getDataSource()
  {
    return this.zzavU;
  }
  
  public DataType getDataType()
  {
    return this.zzavU.getDataType();
  }
  
  public long getEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzawj, TimeUnit.NANOSECONDS);
  }
  
  public DataSource getOriginalDataSource()
  {
    if (this.zzawm != null) {
      return this.zzawm;
    }
    return this.zzavU;
  }
  
  public long getStartTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzawk, TimeUnit.NANOSECONDS);
  }
  
  public long getTimestamp(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzawj, TimeUnit.NANOSECONDS);
  }
  
  public long getTimestampNanos()
  {
    return this.zzawj;
  }
  
  public Value getValue(Field paramField)
  {
    int i = getDataType().indexOf(paramField);
    return this.zzawl[i];
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzavU, Long.valueOf(this.zzawj), Long.valueOf(this.zzawk) });
  }
  
  public DataPoint setFloatValues(float... paramVarArgs)
  {
    zzeO(paramVarArgs.length);
    int i = 0;
    while (i < paramVarArgs.length)
    {
      this.zzawl[i].setFloat(paramVarArgs[i]);
      i += 1;
    }
    return this;
  }
  
  public DataPoint setIntValues(int... paramVarArgs)
  {
    zzeO(paramVarArgs.length);
    int i = 0;
    while (i < paramVarArgs.length)
    {
      this.zzawl[i].setInt(paramVarArgs[i]);
      i += 1;
    }
    return this;
  }
  
  public DataPoint setTimeInterval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
  {
    this.zzawk = paramTimeUnit.toNanos(paramLong1);
    this.zzawj = paramTimeUnit.toNanos(paramLong2);
    return this;
  }
  
  public DataPoint setTimestamp(long paramLong, TimeUnit paramTimeUnit)
  {
    this.zzawj = paramTimeUnit.toNanos(paramLong);
    if ((zzue()) && (zzns.zza(paramTimeUnit)))
    {
      Log.w("Fitness", "Storing location at more than millisecond granularity is not supported. Extra precision is lost as the value is converted to milliseconds.");
      this.zzawj = zzns.zza(this.zzawj, TimeUnit.NANOSECONDS, TimeUnit.MILLISECONDS);
    }
    return this;
  }
  
  public String toString()
  {
    String str2 = Arrays.toString(this.zzawl);
    long l1 = this.zzawk;
    long l2 = this.zzawj;
    long l3 = this.zzawn;
    long l4 = this.zzawo;
    String str3 = this.zzavU.toDebugString();
    if (this.zzawm != null) {}
    for (String str1 = this.zzawm.toDebugString();; str1 = "N/A") {
      return String.format("DataPoint{%s@[%s, %s,raw=%s,insert=%s](%s %s)}", new Object[] { str2, Long.valueOf(l1), Long.valueOf(l2), Long.valueOf(l3), Long.valueOf(l4), str3, str1 });
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
  
  public Value[] zzuf()
  {
    return this.zzawl;
  }
  
  public long zzug()
  {
    return this.zzawn;
  }
  
  public long zzuh()
  {
    return this.zzawo;
  }
  
  public void zzui()
  {
    DataSource localDataSource = getDataSource();
    zzx.zzb(getDataType().getName().equals(localDataSource.getDataType().getName()), "Conflicting data types found %s vs %s", new Object[] { getDataType(), getDataType() });
    if (this.zzawj > 0L)
    {
      bool = true;
      zzx.zzb(bool, "Data point does not have the timestamp set: %s", new Object[] { this });
      if (this.zzawk > this.zzawj) {
        break label107;
      }
    }
    label107:
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "Data point with start time greater than end time found: %s", new Object[] { this });
      return;
      bool = false;
      break;
    }
  }
  
  public long zzuj()
  {
    return this.zzawk;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\DataPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */