package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zznw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class DataSet
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataSet> CREATOR = new zze();
  private final int mVersionCode;
  private final DataType zzavT;
  private final DataSource zzavU;
  private boolean zzawi = false;
  private final List<DataPoint> zzawp;
  private final List<DataSource> zzawq;
  
  DataSet(int paramInt, DataSource paramDataSource, DataType paramDataType, List<RawDataPoint> paramList, List<DataSource> paramList1, boolean paramBoolean)
  {
    this.mVersionCode = paramInt;
    this.zzavU = paramDataSource;
    this.zzavT = paramDataSource.getDataType();
    this.zzawi = paramBoolean;
    this.zzawp = new ArrayList(paramList.size());
    if (paramInt >= 2) {}
    for (;;)
    {
      this.zzawq = paramList1;
      paramDataSource = paramList.iterator();
      while (paramDataSource.hasNext())
      {
        paramDataType = (RawDataPoint)paramDataSource.next();
        this.zzawp.add(new DataPoint(this.zzawq, paramDataType));
      }
      paramList1 = Collections.singletonList(paramDataSource);
    }
  }
  
  public DataSet(DataSource paramDataSource)
  {
    this.mVersionCode = 3;
    this.zzavU = ((DataSource)zzx.zzz(paramDataSource));
    this.zzavT = paramDataSource.getDataType();
    this.zzawp = new ArrayList();
    this.zzawq = new ArrayList();
    this.zzawq.add(this.zzavU);
  }
  
  public DataSet(RawDataSet paramRawDataSet, List<DataSource> paramList)
  {
    this.mVersionCode = 3;
    this.zzavU = ((DataSource)zzb(paramList, paramRawDataSet.zzaxg));
    this.zzavT = this.zzavU.getDataType();
    this.zzawq = paramList;
    this.zzawi = paramRawDataSet.zzawi;
    paramRawDataSet = paramRawDataSet.zzaxj;
    this.zzawp = new ArrayList(paramRawDataSet.size());
    paramRawDataSet = paramRawDataSet.iterator();
    while (paramRawDataSet.hasNext())
    {
      paramList = (RawDataPoint)paramRawDataSet.next();
      this.zzawp.add(new DataPoint(this.zzawq, paramList));
    }
  }
  
  public static DataSet create(DataSource paramDataSource)
  {
    zzx.zzb(paramDataSource, "DataSource should be specified");
    return new DataSet(paramDataSource);
  }
  
  private boolean zza(DataSet paramDataSet)
  {
    return (zzw.equal(getDataType(), paramDataSet.getDataType())) && (zzw.equal(this.zzavU, paramDataSet.zzavU)) && (zzw.equal(this.zzawp, paramDataSet.zzawp)) && (this.zzawi == paramDataSet.zzawi);
  }
  
  private static <T> T zzb(List<T> paramList, int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < paramList.size())) {
      return (T)paramList.get(paramInt);
    }
    return null;
  }
  
  public void add(DataPoint paramDataPoint)
  {
    DataSource localDataSource = paramDataPoint.getDataSource();
    zzx.zzb(localDataSource.getStreamIdentifier().equals(this.zzavU.getStreamIdentifier()), "Conflicting data sources found %s vs %s", new Object[] { localDataSource, this.zzavU });
    paramDataPoint.zzui();
    zznw.zze(paramDataPoint);
    zzb(paramDataPoint);
  }
  
  public void addAll(Iterable<DataPoint> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      add((DataPoint)paramIterable.next());
    }
  }
  
  public DataPoint createDataPoint()
  {
    return DataPoint.create(this.zzavU);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof DataSet)) && (zza((DataSet)paramObject)));
  }
  
  public List<DataPoint> getDataPoints()
  {
    return Collections.unmodifiableList(this.zzawp);
  }
  
  public DataSource getDataSource()
  {
    return this.zzavU;
  }
  
  public DataType getDataType()
  {
    return this.zzavU.getDataType();
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzavU });
  }
  
  public boolean isEmpty()
  {
    return this.zzawp.isEmpty();
  }
  
  public String toString()
  {
    Object localObject = zzuk();
    String str = this.zzavU.toDebugString();
    if (this.zzawp.size() < 10) {}
    for (;;)
    {
      return String.format("DataSet{%s %s}", new Object[] { str, localObject });
      localObject = String.format("%d data points, first 5: %s", new Object[] { Integer.valueOf(this.zzawp.size()), ((List)localObject).subList(0, 5) });
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  public void zzb(DataPoint paramDataPoint)
  {
    this.zzawp.add(paramDataPoint);
    paramDataPoint = paramDataPoint.getOriginalDataSource();
    if ((paramDataPoint != null) && (!this.zzawq.contains(paramDataPoint))) {
      this.zzawq.add(paramDataPoint);
    }
  }
  
  public void zzb(Iterable<DataPoint> paramIterable)
  {
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      zzb((DataPoint)paramIterable.next());
    }
  }
  
  public boolean zzuc()
  {
    return this.zzawi;
  }
  
  List<RawDataPoint> zzuk()
  {
    return zzv(this.zzawq);
  }
  
  List<DataSource> zzul()
  {
    return this.zzawq;
  }
  
  List<RawDataPoint> zzv(List<DataSource> paramList)
  {
    ArrayList localArrayList = new ArrayList(this.zzawp.size());
    Iterator localIterator = this.zzawp.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(new RawDataPoint((DataPoint)localIterator.next(), paramList));
    }
    return localArrayList;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\DataSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */