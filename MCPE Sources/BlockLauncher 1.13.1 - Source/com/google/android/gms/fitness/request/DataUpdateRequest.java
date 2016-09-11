package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataUpdateRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataUpdateRequest> CREATOR = new zzm();
  private final int mVersionCode;
  private final long zzRD;
  private final zzow zzaAD;
  private final long zzavV;
  private final DataSet zzaxn;
  
  DataUpdateRequest(int paramInt, long paramLong1, long paramLong2, DataSet paramDataSet, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.zzRD = paramLong1;
    this.zzavV = paramLong2;
    this.zzaxn = paramDataSet;
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
  }
  
  public DataUpdateRequest(long paramLong1, long paramLong2, DataSet paramDataSet, IBinder paramIBinder)
  {
    this.mVersionCode = 1;
    this.zzRD = paramLong1;
    this.zzavV = paramLong2;
    this.zzaxn = paramDataSet;
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
  }
  
  private DataUpdateRequest(Builder paramBuilder)
  {
    this(Builder.zza(paramBuilder), Builder.zzb(paramBuilder), Builder.zzc(paramBuilder), null);
  }
  
  public DataUpdateRequest(DataUpdateRequest paramDataUpdateRequest, IBinder paramIBinder)
  {
    this(paramDataUpdateRequest.zzlO(), paramDataUpdateRequest.zzud(), paramDataUpdateRequest.getDataSet(), paramIBinder);
  }
  
  private boolean zzb(DataUpdateRequest paramDataUpdateRequest)
  {
    return (this.zzRD == paramDataUpdateRequest.zzRD) && (this.zzavV == paramDataUpdateRequest.zzavV) && (zzw.equal(this.zzaxn, paramDataUpdateRequest.zzaxn));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof DataUpdateRequest)) && (zzb((DataUpdateRequest)paramObject)));
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaAD == null) {
      return null;
    }
    return this.zzaAD.asBinder();
  }
  
  public DataSet getDataSet()
  {
    return this.zzaxn;
  }
  
  public long getEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzavV, TimeUnit.MILLISECONDS);
  }
  
  public long getStartTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzRD, TimeUnit.MILLISECONDS);
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Long.valueOf(this.zzRD), Long.valueOf(this.zzavV), this.zzaxn });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("startTimeMillis", Long.valueOf(this.zzRD)).zzg("endTimeMillis", Long.valueOf(this.zzavV)).zzg("dataSet", this.zzaxn).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzm.zza(this, paramParcel, paramInt);
  }
  
  public long zzlO()
  {
    return this.zzRD;
  }
  
  public long zzud()
  {
    return this.zzavV;
  }
  
  public static class Builder
  {
    private long zzRD;
    private long zzavV;
    private DataSet zzaxn;
    
    private void zzuV()
    {
      zzx.zza(this.zzRD, "Must set a non-zero value for startTimeMillis/startTime");
      zzx.zza(this.zzavV, "Must set a non-zero value for endTimeMillis/endTime");
      zzx.zzb(this.zzaxn, "Must set the data set");
      Iterator localIterator = this.zzaxn.getDataPoints().iterator();
      if (localIterator.hasNext())
      {
        DataPoint localDataPoint = (DataPoint)localIterator.next();
        long l1 = localDataPoint.getStartTime(TimeUnit.MILLISECONDS);
        long l2 = localDataPoint.getEndTime(TimeUnit.MILLISECONDS);
        int i;
        if ((l1 > l2) || ((l1 != 0L) && (l1 < this.zzRD)) || ((l1 != 0L) && (l1 > this.zzavV)) || (l2 > this.zzavV) || (l2 < this.zzRD))
        {
          i = 1;
          label144:
          if (i != 0) {
            break label205;
          }
        }
        label205:
        for (boolean bool = true;; bool = false)
        {
          zzx.zza(bool, "Data Point's startTimeMillis %d, endTimeMillis %d should lie between timeRange provided in the request. StartTimeMillis %d, EndTimeMillis: %d", new Object[] { Long.valueOf(l1), Long.valueOf(l2), Long.valueOf(this.zzRD), Long.valueOf(this.zzavV) });
          break;
          i = 0;
          break label144;
        }
      }
    }
    
    public DataUpdateRequest build()
    {
      zzuV();
      return new DataUpdateRequest(this, null);
    }
    
    public Builder setDataSet(DataSet paramDataSet)
    {
      zzx.zzb(paramDataSet, "Must set the data set");
      this.zzaxn = paramDataSet;
      return this;
    }
    
    public Builder setTimeInterval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      if (paramLong1 > 0L)
      {
        bool = true;
        zzx.zzb(bool, "Invalid start time :%d", new Object[] { Long.valueOf(paramLong1) });
        if (paramLong2 < paramLong1) {
          break label82;
        }
      }
      label82:
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Invalid end time :%d", new Object[] { Long.valueOf(paramLong2) });
        this.zzRD = paramTimeUnit.toMillis(paramLong1);
        this.zzavV = paramTimeUnit.toMillis(paramLong2);
        return this;
        bool = false;
        break;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\DataUpdateRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */