package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.fitness.FitnessActivities;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bucket
  implements SafeParcelable
{
  public static final Parcelable.Creator<Bucket> CREATOR = new zzc();
  public static final int TYPE_ACTIVITY_SEGMENT = 4;
  public static final int TYPE_ACTIVITY_TYPE = 3;
  public static final int TYPE_SESSION = 2;
  public static final int TYPE_TIME = 1;
  private final int mVersionCode;
  private final long zzRD;
  private final long zzavV;
  private final Session zzavX;
  private final int zzawf;
  private final List<DataSet> zzawg;
  private final int zzawh;
  private boolean zzawi = false;
  
  Bucket(int paramInt1, long paramLong1, long paramLong2, Session paramSession, int paramInt2, List<DataSet> paramList, int paramInt3, boolean paramBoolean)
  {
    this.mVersionCode = paramInt1;
    this.zzRD = paramLong1;
    this.zzavV = paramLong2;
    this.zzavX = paramSession;
    this.zzawf = paramInt2;
    this.zzawg = paramList;
    this.zzawh = paramInt3;
    this.zzawi = paramBoolean;
  }
  
  public Bucket(RawBucket paramRawBucket, List<DataSource> paramList)
  {
    this(2, paramRawBucket.zzRD, paramRawBucket.zzavV, paramRawBucket.zzavX, paramRawBucket.zzaxf, zza(paramRawBucket.zzawg, paramList), paramRawBucket.zzawh, paramRawBucket.zzawi);
  }
  
  private static List<DataSet> zza(Collection<RawDataSet> paramCollection, List<DataSource> paramList)
  {
    ArrayList localArrayList = new ArrayList(paramCollection.size());
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext()) {
      localArrayList.add(new DataSet((RawDataSet)paramCollection.next(), paramList));
    }
    return localArrayList;
  }
  
  private boolean zza(Bucket paramBucket)
  {
    return (this.zzRD == paramBucket.zzRD) && (this.zzavV == paramBucket.zzavV) && (this.zzawf == paramBucket.zzawf) && (zzw.equal(this.zzawg, paramBucket.zzawg)) && (this.zzawh == paramBucket.zzawh) && (this.zzawi == paramBucket.zzawi);
  }
  
  public static String zzeM(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "bug";
    case 1: 
      return "time";
    case 3: 
      return "type";
    case 4: 
      return "segment";
    case 2: 
      return "session";
    }
    return "unknown";
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof Bucket)) && (zza((Bucket)paramObject)));
  }
  
  public String getActivity()
  {
    return FitnessActivities.getName(this.zzawf);
  }
  
  public int getBucketType()
  {
    return this.zzawh;
  }
  
  public DataSet getDataSet(DataType paramDataType)
  {
    Iterator localIterator = this.zzawg.iterator();
    while (localIterator.hasNext())
    {
      DataSet localDataSet = (DataSet)localIterator.next();
      if (localDataSet.getDataType().equals(paramDataType)) {
        return localDataSet;
      }
    }
    return null;
  }
  
  public List<DataSet> getDataSets()
  {
    return this.zzawg;
  }
  
  public long getEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzavV, TimeUnit.MILLISECONDS);
  }
  
  public Session getSession()
  {
    return this.zzavX;
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
    return zzw.hashCode(new Object[] { Long.valueOf(this.zzRD), Long.valueOf(this.zzavV), Integer.valueOf(this.zzawf), Integer.valueOf(this.zzawh) });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("startTime", Long.valueOf(this.zzRD)).zzg("endTime", Long.valueOf(this.zzavV)).zzg("activity", Integer.valueOf(this.zzawf)).zzg("dataSets", this.zzawg).zzg("bucketType", zzeM(this.zzawh)).zzg("serverHasMoreData", Boolean.valueOf(this.zzawi)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public boolean zzb(Bucket paramBucket)
  {
    return (this.zzRD == paramBucket.zzRD) && (this.zzavV == paramBucket.zzavV) && (this.zzawf == paramBucket.zzawf) && (this.zzawh == paramBucket.zzawh);
  }
  
  public long zzlO()
  {
    return this.zzRD;
  }
  
  public int zzub()
  {
    return this.zzawf;
  }
  
  public boolean zzuc()
  {
    if (this.zzawi) {
      return true;
    }
    Iterator localIterator = this.zzawg.iterator();
    while (localIterator.hasNext()) {
      if (((DataSet)localIterator.next()).zzuc()) {
        return true;
      }
    }
    return false;
  }
  
  public long zzud()
  {
    return this.zzavV;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\Bucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */