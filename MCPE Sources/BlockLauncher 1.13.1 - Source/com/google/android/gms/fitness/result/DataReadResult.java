package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataSource.Builder;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import com.google.android.gms.fitness.request.DataReadRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DataReadResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<DataReadResult> CREATOR = new zzc();
  private final int mVersionCode;
  private final Status zzUX;
  private final List<Bucket> zzaBF;
  private int zzaBG;
  private final List<DataType> zzaBH;
  private final List<DataSet> zzawg;
  private final List<DataSource> zzawq;
  
  DataReadResult(int paramInt1, List<RawDataSet> paramList, Status paramStatus, List<RawBucket> paramList1, int paramInt2, List<DataSource> paramList2, List<DataType> paramList3)
  {
    this.mVersionCode = paramInt1;
    this.zzUX = paramStatus;
    this.zzaBG = paramInt2;
    this.zzawq = paramList2;
    this.zzaBH = paramList3;
    this.zzawg = new ArrayList(paramList.size());
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      paramStatus = (RawDataSet)paramList.next();
      this.zzawg.add(new DataSet(paramStatus, paramList2));
    }
    this.zzaBF = new ArrayList(paramList1.size());
    paramList = paramList1.iterator();
    while (paramList.hasNext())
    {
      paramStatus = (RawBucket)paramList.next();
      this.zzaBF.add(new Bucket(paramStatus, paramList2));
    }
  }
  
  public DataReadResult(List<DataSet> paramList, List<Bucket> paramList1, Status paramStatus)
  {
    this.mVersionCode = 5;
    this.zzawg = paramList;
    this.zzUX = paramStatus;
    this.zzaBF = paramList1;
    this.zzaBG = 1;
    this.zzawq = new ArrayList();
    this.zzaBH = new ArrayList();
  }
  
  public static DataReadResult zza(Status paramStatus, DataReadRequest paramDataReadRequest)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = paramDataReadRequest.getDataSources().iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(DataSet.create((DataSource)((Iterator)localObject).next()));
    }
    paramDataReadRequest = paramDataReadRequest.getDataTypes().iterator();
    while (paramDataReadRequest.hasNext())
    {
      localObject = (DataType)paramDataReadRequest.next();
      localArrayList.add(DataSet.create(new DataSource.Builder().setDataType((DataType)localObject).setType(1).setName("Default").build()));
    }
    return new DataReadResult(localArrayList, Collections.emptyList(), paramStatus);
  }
  
  private void zza(Bucket paramBucket, List<Bucket> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      paramList = (Bucket)localIterator.next();
      if (paramList.zzb(paramBucket))
      {
        paramBucket = paramBucket.getDataSets().iterator();
        while (paramBucket.hasNext()) {
          zza((DataSet)paramBucket.next(), paramList.getDataSets());
        }
      }
    }
    this.zzaBF.add(paramBucket);
  }
  
  private void zza(DataSet paramDataSet, List<DataSet> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      DataSet localDataSet = (DataSet)localIterator.next();
      if (localDataSet.getDataSource().equals(paramDataSet.getDataSource()))
      {
        localDataSet.zzb(paramDataSet.getDataPoints());
        return;
      }
    }
    paramList.add(paramDataSet);
  }
  
  private boolean zzc(DataReadResult paramDataReadResult)
  {
    return (this.zzUX.equals(paramDataReadResult.zzUX)) && (zzw.equal(this.zzawg, paramDataReadResult.zzawg)) && (zzw.equal(this.zzaBF, paramDataReadResult.zzaBF));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataReadResult)) && (zzc((DataReadResult)paramObject)));
  }
  
  public List<Bucket> getBuckets()
  {
    return this.zzaBF;
  }
  
  public DataSet getDataSet(DataSource paramDataSource)
  {
    Iterator localIterator = this.zzawg.iterator();
    while (localIterator.hasNext())
    {
      DataSet localDataSet = (DataSet)localIterator.next();
      if (paramDataSource.equals(localDataSet.getDataSource())) {
        return localDataSet;
      }
    }
    return DataSet.create(paramDataSource);
  }
  
  public DataSet getDataSet(DataType paramDataType)
  {
    Iterator localIterator = this.zzawg.iterator();
    while (localIterator.hasNext())
    {
      DataSet localDataSet = (DataSet)localIterator.next();
      if (paramDataType.equals(localDataSet.getDataType())) {
        return localDataSet;
      }
    }
    return DataSet.create(new DataSource.Builder().setDataType(paramDataType).setType(1).build());
  }
  
  public List<DataSet> getDataSets()
  {
    return this.zzawg;
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
    return zzw.hashCode(new Object[] { this.zzUX, this.zzawg, this.zzaBF });
  }
  
  public String toString()
  {
    zzw.zza localzza = zzw.zzy(this).zzg("status", this.zzUX);
    if (this.zzawg.size() > 5)
    {
      localObject = this.zzawg.size() + " data sets";
      localzza = localzza.zzg("dataSets", localObject);
      if (this.zzaBF.size() <= 5) {
        break label123;
      }
    }
    label123:
    for (Object localObject = this.zzaBF.size() + " buckets";; localObject = this.zzaBF)
    {
      return localzza.zzg("buckets", localObject).toString();
      localObject = this.zzawg;
      break;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public void zzb(DataReadResult paramDataReadResult)
  {
    Iterator localIterator = paramDataReadResult.getDataSets().iterator();
    while (localIterator.hasNext()) {
      zza((DataSet)localIterator.next(), this.zzawg);
    }
    paramDataReadResult = paramDataReadResult.getBuckets().iterator();
    while (paramDataReadResult.hasNext()) {
      zza((Bucket)paramDataReadResult.next(), this.zzaBF);
    }
  }
  
  List<DataSource> zzul()
  {
    return this.zzawq;
  }
  
  public int zzvj()
  {
    return this.zzaBG;
  }
  
  List<RawBucket> zzvk()
  {
    ArrayList localArrayList = new ArrayList(this.zzaBF.size());
    Iterator localIterator = this.zzaBF.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(new RawBucket((Bucket)localIterator.next(), this.zzawq, this.zzaBH));
    }
    return localArrayList;
  }
  
  List<RawDataSet> zzvl()
  {
    ArrayList localArrayList = new ArrayList(this.zzawg.size());
    Iterator localIterator = this.zzawg.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(new RawDataSet((DataSet)localIterator.next(), this.zzawq, this.zzaBH));
    }
    return localArrayList;
  }
  
  List<DataType> zzvm()
  {
    return this.zzaBH;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\result\DataReadResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */