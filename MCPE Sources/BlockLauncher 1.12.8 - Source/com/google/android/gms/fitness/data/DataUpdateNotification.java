package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.concurrent.TimeUnit;

public class DataUpdateNotification
  implements SafeParcelable
{
  public static final String ACTION = "com.google.android.gms.fitness.DATA_UPDATE_NOTIFICATION";
  public static final Parcelable.Creator<DataUpdateNotification> CREATOR = new zzh();
  public static final String EXTRA_DATA_UPDATE_NOTIFICATION = "vnd.google.fitness.data_udpate_notification";
  public static final int OPERATION_DELETE = 2;
  public static final int OPERATION_INSERT = 1;
  public static final int OPERATION_UPDATE = 3;
  final int mVersionCode;
  private final DataType zzavT;
  private final DataSource zzavU;
  private final long zzawF;
  private final long zzawG;
  private final int zzawH;
  
  DataUpdateNotification(int paramInt1, long paramLong1, long paramLong2, int paramInt2, DataSource paramDataSource, DataType paramDataType)
  {
    this.mVersionCode = paramInt1;
    this.zzawF = paramLong1;
    this.zzawG = paramLong2;
    this.zzawH = paramInt2;
    this.zzavU = paramDataSource;
    this.zzavT = paramDataType;
  }
  
  public static DataUpdateNotification getDataUpdateNotification(Intent paramIntent)
  {
    return (DataUpdateNotification)zzc.zza(paramIntent, "vnd.google.fitness.data_udpate_notification", CREATOR);
  }
  
  private boolean zza(DataUpdateNotification paramDataUpdateNotification)
  {
    return (this.zzawF == paramDataUpdateNotification.zzawF) && (this.zzawG == paramDataUpdateNotification.zzawG) && (this.zzawH == paramDataUpdateNotification.zzawH) && (zzw.equal(this.zzavU, paramDataUpdateNotification.zzavU)) && (zzw.equal(this.zzavT, paramDataUpdateNotification.zzavT));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof DataUpdateNotification)) && (zza((DataUpdateNotification)paramObject)));
  }
  
  public DataSource getDataSource()
  {
    return this.zzavU;
  }
  
  public DataType getDataType()
  {
    return this.zzavT;
  }
  
  public int getOperationType()
  {
    return this.zzawH;
  }
  
  public long getUpdateEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzawG, TimeUnit.NANOSECONDS);
  }
  
  public long getUpdateStartTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzawF, TimeUnit.NANOSECONDS);
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Long.valueOf(this.zzawF), Long.valueOf(this.zzawG), Integer.valueOf(this.zzawH), this.zzavU, this.zzavT });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("updateStartTimeNanos", Long.valueOf(this.zzawF)).zzg("updateEndTimeNanos", Long.valueOf(this.zzawG)).zzg("operationType", Integer.valueOf(this.zzawH)).zzg("dataSource", this.zzavU).zzg("dataType", this.zzavT).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
  
  public long zzup()
  {
    return this.zzawF;
  }
  
  public long zzuq()
  {
    return this.zzawG;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\DataUpdateNotification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */