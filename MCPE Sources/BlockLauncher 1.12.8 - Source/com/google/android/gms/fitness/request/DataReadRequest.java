package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;
import com.google.android.gms.internal.zzoh;
import com.google.android.gms.internal.zzoh.zza;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DataReadRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataReadRequest> CREATOR = new zzf();
  public static final int NO_LIMIT = 0;
  private final int mVersionCode;
  private final long zzRD;
  private final List<DataSource> zzaAF;
  private final List<DataType> zzaAK;
  private final List<DataSource> zzaAL;
  private final long zzaAM;
  private final DataSource zzaAN;
  private final int zzaAO;
  private final boolean zzaAP;
  private final boolean zzaAQ;
  private final zzoh zzaAR;
  private final List<Device> zzaAS;
  private final long zzavV;
  private final List<DataType> zzawe;
  private final int zzawh;
  
  DataReadRequest(int paramInt1, List<DataType> paramList1, List<DataSource> paramList2, long paramLong1, long paramLong2, List<DataType> paramList3, List<DataSource> paramList4, int paramInt2, long paramLong3, DataSource paramDataSource, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, IBinder paramIBinder, List<Device> paramList)
  {
    this.mVersionCode = paramInt1;
    this.zzawe = paramList1;
    this.zzaAF = paramList2;
    this.zzRD = paramLong1;
    this.zzavV = paramLong2;
    this.zzaAK = paramList3;
    this.zzaAL = paramList4;
    this.zzawh = paramInt2;
    this.zzaAM = paramLong3;
    this.zzaAN = paramDataSource;
    this.zzaAO = paramInt3;
    this.zzaAP = paramBoolean1;
    this.zzaAQ = paramBoolean2;
    if (paramIBinder == null) {}
    for (paramList1 = null;; paramList1 = zzoh.zza.zzbC(paramIBinder))
    {
      this.zzaAR = paramList1;
      paramList1 = paramList;
      if (paramList == null) {
        paramList1 = Collections.emptyList();
      }
      this.zzaAS = paramList1;
      return;
    }
  }
  
  private DataReadRequest(Builder paramBuilder)
  {
    this(Builder.zza(paramBuilder), Builder.zzb(paramBuilder), Builder.zzc(paramBuilder), Builder.zzd(paramBuilder), Builder.zze(paramBuilder), Builder.zzf(paramBuilder), Builder.zzg(paramBuilder), Builder.zzh(paramBuilder), Builder.zzi(paramBuilder), Builder.zzj(paramBuilder), Builder.zzk(paramBuilder), Builder.zzl(paramBuilder), null, Builder.zzm(paramBuilder));
  }
  
  public DataReadRequest(DataReadRequest paramDataReadRequest, zzoh paramzzoh)
  {
    this(paramDataReadRequest.zzawe, paramDataReadRequest.zzaAF, paramDataReadRequest.zzRD, paramDataReadRequest.zzavV, paramDataReadRequest.zzaAK, paramDataReadRequest.zzaAL, paramDataReadRequest.zzawh, paramDataReadRequest.zzaAM, paramDataReadRequest.zzaAN, paramDataReadRequest.zzaAO, paramDataReadRequest.zzaAP, paramDataReadRequest.zzaAQ, paramzzoh, paramDataReadRequest.zzaAS);
  }
  
  public DataReadRequest(List<DataType> paramList1, List<DataSource> paramList2, long paramLong1, long paramLong2, List<DataType> paramList3, List<DataSource> paramList4, int paramInt1, long paramLong3, DataSource paramDataSource, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, zzoh paramzzoh, List<Device> paramList) {}
  
  private boolean zzb(DataReadRequest paramDataReadRequest)
  {
    return (this.zzawe.equals(paramDataReadRequest.zzawe)) && (this.zzaAF.equals(paramDataReadRequest.zzaAF)) && (this.zzRD == paramDataReadRequest.zzRD) && (this.zzavV == paramDataReadRequest.zzavV) && (this.zzawh == paramDataReadRequest.zzawh) && (this.zzaAL.equals(paramDataReadRequest.zzaAL)) && (this.zzaAK.equals(paramDataReadRequest.zzaAK)) && (zzw.equal(this.zzaAN, paramDataReadRequest.zzaAN)) && (this.zzaAM == paramDataReadRequest.zzaAM) && (this.zzaAQ == paramDataReadRequest.zzaAQ);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataReadRequest)) && (zzb((DataReadRequest)paramObject)));
  }
  
  public DataSource getActivityDataSource()
  {
    return this.zzaAN;
  }
  
  public List<DataSource> getAggregatedDataSources()
  {
    return this.zzaAL;
  }
  
  public List<DataType> getAggregatedDataTypes()
  {
    return this.zzaAK;
  }
  
  public long getBucketDuration(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzaAM, TimeUnit.MILLISECONDS);
  }
  
  public int getBucketType()
  {
    return this.zzawh;
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaAR == null) {
      return null;
    }
    return this.zzaAR.asBinder();
  }
  
  public List<DataSource> getDataSources()
  {
    return this.zzaAF;
  }
  
  public List<DataType> getDataTypes()
  {
    return this.zzawe;
  }
  
  public long getEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzavV, TimeUnit.MILLISECONDS);
  }
  
  public int getLimit()
  {
    return this.zzaAO;
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
    return zzw.hashCode(new Object[] { Integer.valueOf(this.zzawh), Long.valueOf(this.zzRD), Long.valueOf(this.zzavV) });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DataReadRequest{");
    Iterator localIterator;
    if (!this.zzawe.isEmpty())
    {
      localIterator = this.zzawe.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append(((DataType)localIterator.next()).zzuo()).append(" ");
      }
    }
    if (!this.zzaAF.isEmpty())
    {
      localIterator = this.zzaAF.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append(((DataSource)localIterator.next()).toDebugString()).append(" ");
      }
    }
    if (this.zzawh != 0)
    {
      localStringBuilder.append("bucket by ").append(Bucket.zzeM(this.zzawh));
      if (this.zzaAM > 0L) {
        localStringBuilder.append(" >").append(this.zzaAM).append("ms");
      }
      localStringBuilder.append(": ");
    }
    if (!this.zzaAK.isEmpty())
    {
      localIterator = this.zzaAK.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append(((DataType)localIterator.next()).zzuo()).append(" ");
      }
    }
    if (!this.zzaAL.isEmpty())
    {
      localIterator = this.zzaAL.iterator();
      while (localIterator.hasNext()) {
        localStringBuilder.append(((DataSource)localIterator.next()).toDebugString()).append(" ");
      }
    }
    localStringBuilder.append(String.format("(%tF %tT - %tF %tT)", new Object[] { Long.valueOf(this.zzRD), Long.valueOf(this.zzRD), Long.valueOf(this.zzavV), Long.valueOf(this.zzavV) }));
    if (this.zzaAN != null) {
      localStringBuilder.append("activities: ").append(this.zzaAN.toDebugString());
    }
    if (this.zzaAQ) {
      localStringBuilder.append(" +server");
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzf.zza(this, paramParcel, paramInt);
  }
  
  public long zzlO()
  {
    return this.zzRD;
  }
  
  public boolean zzuP()
  {
    return this.zzaAQ;
  }
  
  public boolean zzuQ()
  {
    return this.zzaAP;
  }
  
  public long zzuR()
  {
    return this.zzaAM;
  }
  
  public List<Device> zzuS()
  {
    return this.zzaAS;
  }
  
  public long zzud()
  {
    return this.zzavV;
  }
  
  public static class Builder
  {
    private long zzRD;
    private List<DataSource> zzaAF = new ArrayList();
    private List<DataType> zzaAK = new ArrayList();
    private List<DataSource> zzaAL = new ArrayList();
    private long zzaAM = 0L;
    private DataSource zzaAN;
    private int zzaAO = 0;
    private boolean zzaAP = false;
    private boolean zzaAQ = false;
    private List<Device> zzaAS = new ArrayList();
    private long zzavV;
    private List<DataType> zzawe = new ArrayList();
    private int zzawh = 0;
    
    public Builder aggregate(DataSource paramDataSource, DataType paramDataType)
    {
      zzx.zzb(paramDataSource, "Attempting to add a null data source");
      if (!this.zzaAF.contains(paramDataSource)) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zza(bool, "Cannot add the same data source for aggregated and detailed");
        DataType localDataType = paramDataSource.getDataType();
        zzx.zzb(DataType.AGGREGATE_INPUT_TYPES.contains(localDataType), "Unsupported input data type specified for aggregation: %s", new Object[] { localDataType });
        zzx.zzb(DataType.getAggregatesForInput(localDataType).contains(paramDataType), "Invalid output aggregate data type specified: %s -> %s", new Object[] { localDataType, paramDataType });
        if (!this.zzaAL.contains(paramDataSource)) {
          this.zzaAL.add(paramDataSource);
        }
        return this;
      }
    }
    
    public Builder aggregate(DataType paramDataType1, DataType paramDataType2)
    {
      zzx.zzb(paramDataType1, "Attempting to use a null data type");
      if (!this.zzawe.contains(paramDataType1)) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zza(bool, "Cannot add the same data type as aggregated and detailed");
        zzx.zzb(DataType.AGGREGATE_INPUT_TYPES.contains(paramDataType1), "Unsupported input data type specified for aggregation: %s", new Object[] { paramDataType1 });
        zzx.zzb(DataType.getAggregatesForInput(paramDataType1).contains(paramDataType2), "Invalid output aggregate data type specified: %s -> %s", new Object[] { paramDataType1, paramDataType2 });
        if (!this.zzaAK.contains(paramDataType1)) {
          this.zzaAK.add(paramDataType1);
        }
        return this;
      }
    }
    
    public Builder bucketByActivitySegment(int paramInt, TimeUnit paramTimeUnit)
    {
      if (this.zzawh == 0)
      {
        bool = true;
        zzx.zzb(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.zzawh) });
        if (paramInt <= 0) {
          break label74;
        }
      }
      label74:
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        this.zzawh = 4;
        this.zzaAM = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
      }
    }
    
    public Builder bucketByActivitySegment(int paramInt, TimeUnit paramTimeUnit, DataSource paramDataSource)
    {
      if (this.zzawh == 0)
      {
        bool = true;
        zzx.zzb(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.zzawh) });
        if (paramInt <= 0) {
          break label121;
        }
        bool = true;
        label38:
        zzx.zzb(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        if (paramDataSource == null) {
          break label127;
        }
      }
      label121:
      label127:
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Invalid activity data source specified");
        zzx.zzb(paramDataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", new Object[] { paramDataSource });
        this.zzaAN = paramDataSource;
        this.zzawh = 4;
        this.zzaAM = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
        bool = false;
        break label38;
      }
    }
    
    public Builder bucketByActivityType(int paramInt, TimeUnit paramTimeUnit)
    {
      if (this.zzawh == 0)
      {
        bool = true;
        zzx.zzb(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.zzawh) });
        if (paramInt <= 0) {
          break label74;
        }
      }
      label74:
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        this.zzawh = 3;
        this.zzaAM = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
      }
    }
    
    public Builder bucketByActivityType(int paramInt, TimeUnit paramTimeUnit, DataSource paramDataSource)
    {
      if (this.zzawh == 0)
      {
        bool = true;
        zzx.zzb(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.zzawh) });
        if (paramInt <= 0) {
          break label121;
        }
        bool = true;
        label38:
        zzx.zzb(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        if (paramDataSource == null) {
          break label127;
        }
      }
      label121:
      label127:
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Invalid activity data source specified");
        zzx.zzb(paramDataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", new Object[] { paramDataSource });
        this.zzaAN = paramDataSource;
        this.zzawh = 3;
        this.zzaAM = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
        bool = false;
        break label38;
      }
    }
    
    public Builder bucketBySession(int paramInt, TimeUnit paramTimeUnit)
    {
      if (this.zzawh == 0)
      {
        bool = true;
        zzx.zzb(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.zzawh) });
        if (paramInt <= 0) {
          break label74;
        }
      }
      label74:
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        this.zzawh = 2;
        this.zzaAM = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
      }
    }
    
    public Builder bucketByTime(int paramInt, TimeUnit paramTimeUnit)
    {
      if (this.zzawh == 0)
      {
        bool = true;
        zzx.zzb(bool, "Bucketing strategy already set to %s", new Object[] { Integer.valueOf(this.zzawh) });
        if (paramInt <= 0) {
          break label74;
        }
      }
      label74:
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Must specify a valid minimum duration for an activity segment: %d", new Object[] { Integer.valueOf(paramInt) });
        this.zzawh = 1;
        this.zzaAM = paramTimeUnit.toMillis(paramInt);
        return this;
        bool = false;
        break;
      }
    }
    
    public DataReadRequest build()
    {
      boolean bool2 = true;
      label69:
      label112:
      int i;
      if ((!this.zzaAF.isEmpty()) || (!this.zzawe.isEmpty()) || (!this.zzaAL.isEmpty()) || (!this.zzaAK.isEmpty()))
      {
        bool1 = true;
        zzx.zza(bool1, "Must add at least one data source (aggregated or detailed)");
        if (this.zzRD <= 0L) {
          break label205;
        }
        bool1 = true;
        zzx.zza(bool1, "Invalid start time: %s", new Object[] { Long.valueOf(this.zzRD) });
        if ((this.zzavV <= 0L) || (this.zzavV <= this.zzRD)) {
          break label210;
        }
        bool1 = true;
        zzx.zza(bool1, "Invalid end time: %s", new Object[] { Long.valueOf(this.zzavV) });
        if ((!this.zzaAL.isEmpty()) || (!this.zzaAK.isEmpty())) {
          break label215;
        }
        i = 1;
        label158:
        if (i != 0)
        {
          bool1 = bool2;
          if (this.zzawh == 0) {}
        }
        else
        {
          if ((i != 0) || (this.zzawh == 0)) {
            break label220;
          }
        }
      }
      label205:
      label210:
      label215:
      label220:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        zzx.zza(bool1, "Must specify a valid bucketing strategy while requesting aggregation");
        return new DataReadRequest(this, null);
        bool1 = false;
        break;
        bool1 = false;
        break label69;
        bool1 = false;
        break label112;
        i = 0;
        break label158;
      }
    }
    
    public Builder enableServerQueries()
    {
      this.zzaAQ = true;
      return this;
    }
    
    public Builder read(DataSource paramDataSource)
    {
      zzx.zzb(paramDataSource, "Attempting to add a null data source");
      if (!this.zzaAL.contains(paramDataSource)) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Cannot add the same data source as aggregated and detailed");
        if (!this.zzaAF.contains(paramDataSource)) {
          this.zzaAF.add(paramDataSource);
        }
        return this;
      }
    }
    
    public Builder read(DataType paramDataType)
    {
      zzx.zzb(paramDataType, "Attempting to use a null data type");
      if (!this.zzaAK.contains(paramDataType)) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zza(bool, "Cannot add the same data type as aggregated and detailed");
        if (!this.zzawe.contains(paramDataType)) {
          this.zzawe.add(paramDataType);
        }
        return this;
      }
    }
    
    public Builder setLimit(int paramInt)
    {
      if (paramInt > 0) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Invalid limit %d is specified", new Object[] { Integer.valueOf(paramInt) });
        this.zzaAO = paramInt;
        return this;
      }
    }
    
    public Builder setTimeRange(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      this.zzRD = paramTimeUnit.toMillis(paramLong1);
      this.zzavV = paramTimeUnit.toMillis(paramLong2);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\DataReadRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */