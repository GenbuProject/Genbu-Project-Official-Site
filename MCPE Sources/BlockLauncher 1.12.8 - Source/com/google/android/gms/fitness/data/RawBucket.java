package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@KeepName
public final class RawBucket
  implements SafeParcelable
{
  public static final Parcelable.Creator<RawBucket> CREATOR = new zzn();
  final int mVersionCode;
  public final long zzRD;
  public final long zzavV;
  public final Session zzavX;
  public final List<RawDataSet> zzawg;
  public final int zzawh;
  public final boolean zzawi;
  public final int zzaxf;
  
  public RawBucket(int paramInt1, long paramLong1, long paramLong2, Session paramSession, int paramInt2, List<RawDataSet> paramList, int paramInt3, boolean paramBoolean)
  {
    this.mVersionCode = paramInt1;
    this.zzRD = paramLong1;
    this.zzavV = paramLong2;
    this.zzavX = paramSession;
    this.zzaxf = paramInt2;
    this.zzawg = paramList;
    this.zzawh = paramInt3;
    this.zzawi = paramBoolean;
  }
  
  public RawBucket(Bucket paramBucket, List<DataSource> paramList, List<DataType> paramList1)
  {
    this.mVersionCode = 2;
    this.zzRD = paramBucket.getStartTime(TimeUnit.MILLISECONDS);
    this.zzavV = paramBucket.getEndTime(TimeUnit.MILLISECONDS);
    this.zzavX = paramBucket.getSession();
    this.zzaxf = paramBucket.zzub();
    this.zzawh = paramBucket.getBucketType();
    this.zzawi = paramBucket.zzuc();
    paramBucket = paramBucket.getDataSets();
    this.zzawg = new ArrayList(paramBucket.size());
    paramBucket = paramBucket.iterator();
    while (paramBucket.hasNext())
    {
      DataSet localDataSet = (DataSet)paramBucket.next();
      this.zzawg.add(new RawDataSet(localDataSet, paramList, paramList1));
    }
  }
  
  private boolean zza(RawBucket paramRawBucket)
  {
    return (this.zzRD == paramRawBucket.zzRD) && (this.zzavV == paramRawBucket.zzavV) && (this.zzaxf == paramRawBucket.zzaxf) && (zzw.equal(this.zzawg, paramRawBucket.zzawg)) && (this.zzawh == paramRawBucket.zzawh) && (this.zzawi == paramRawBucket.zzawi);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof RawBucket)) && (zza((RawBucket)paramObject)));
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Long.valueOf(this.zzRD), Long.valueOf(this.zzavV), Integer.valueOf(this.zzawh) });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("startTime", Long.valueOf(this.zzRD)).zzg("endTime", Long.valueOf(this.zzavV)).zzg("activity", Integer.valueOf(this.zzaxf)).zzg("dataSets", this.zzawg).zzg("bucketType", Integer.valueOf(this.zzawh)).zzg("serverHasMoreData", Boolean.valueOf(this.zzawi)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzn.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\RawBucket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */