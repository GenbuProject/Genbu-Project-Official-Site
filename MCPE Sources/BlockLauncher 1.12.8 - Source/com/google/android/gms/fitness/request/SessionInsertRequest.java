package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzns;
import com.google.android.gms.internal.zznw;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SessionInsertRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SessionInsertRequest> CREATOR = new zzx();
  private final int mVersionCode;
  private final zzow zzaAD;
  private final List<DataPoint> zzaBs;
  private final Session zzavX;
  private final List<DataSet> zzawg;
  
  SessionInsertRequest(int paramInt, Session paramSession, List<DataSet> paramList, List<DataPoint> paramList1, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.zzavX = paramSession;
    this.zzawg = Collections.unmodifiableList(paramList);
    this.zzaBs = Collections.unmodifiableList(paramList1);
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
  }
  
  public SessionInsertRequest(Session paramSession, List<DataSet> paramList, List<DataPoint> paramList1, zzow paramzzow)
  {
    this.mVersionCode = 3;
    this.zzavX = paramSession;
    this.zzawg = Collections.unmodifiableList(paramList);
    this.zzaBs = Collections.unmodifiableList(paramList1);
    this.zzaAD = paramzzow;
  }
  
  private SessionInsertRequest(Builder paramBuilder)
  {
    this(Builder.zza(paramBuilder), Builder.zzb(paramBuilder), Builder.zzc(paramBuilder), null);
  }
  
  public SessionInsertRequest(SessionInsertRequest paramSessionInsertRequest, zzow paramzzow)
  {
    this(paramSessionInsertRequest.zzavX, paramSessionInsertRequest.zzawg, paramSessionInsertRequest.zzaBs, paramzzow);
  }
  
  private boolean zzb(SessionInsertRequest paramSessionInsertRequest)
  {
    return (zzw.equal(this.zzavX, paramSessionInsertRequest.zzavX)) && (zzw.equal(this.zzawg, paramSessionInsertRequest.zzawg)) && (zzw.equal(this.zzaBs, paramSessionInsertRequest.zzaBs));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof SessionInsertRequest)) && (zzb((SessionInsertRequest)paramObject)));
  }
  
  public List<DataPoint> getAggregateDataPoints()
  {
    return this.zzaBs;
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaAD == null) {
      return null;
    }
    return this.zzaAD.asBinder();
  }
  
  public List<DataSet> getDataSets()
  {
    return this.zzawg;
  }
  
  public Session getSession()
  {
    return this.zzavX;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzavX, this.zzawg, this.zzaBs });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("session", this.zzavX).zzg("dataSets", this.zzawg).zzg("aggregateDataPoints", this.zzaBs).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzx.zza(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private List<DataPoint> zzaBs = new ArrayList();
    private List<DataSource> zzaBt = new ArrayList();
    private Session zzavX;
    private List<DataSet> zzawg = new ArrayList();
    
    private void zzf(DataPoint paramDataPoint)
    {
      zzh(paramDataPoint);
      zzg(paramDataPoint);
    }
    
    private void zzg(DataPoint paramDataPoint)
    {
      long l3 = this.zzavX.getStartTime(TimeUnit.NANOSECONDS);
      long l4 = this.zzavX.getEndTime(TimeUnit.NANOSECONDS);
      long l5 = paramDataPoint.getStartTime(TimeUnit.NANOSECONDS);
      long l2 = paramDataPoint.getEndTime(TimeUnit.NANOSECONDS);
      TimeUnit localTimeUnit;
      long l1;
      if ((l5 != 0L) && (l2 != 0L))
      {
        localTimeUnit = TimeUnit.MILLISECONDS;
        l1 = l2;
        if (l2 > l4) {
          l1 = zzns.zza(l2, TimeUnit.NANOSECONDS, localTimeUnit);
        }
        if ((l5 < l3) || (l1 > l4)) {
          break label196;
        }
      }
      label196:
      for (boolean bool = true;; bool = false)
      {
        com.google.android.gms.common.internal.zzx.zza(bool, "Data point %s has start and end times outside session interval [%d, %d]", new Object[] { paramDataPoint, Long.valueOf(l3), Long.valueOf(l4) });
        if (l1 != paramDataPoint.getEndTime(TimeUnit.NANOSECONDS))
        {
          Log.w("Fitness", String.format("Data point end time [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", new Object[] { Long.valueOf(paramDataPoint.getEndTime(TimeUnit.NANOSECONDS)), Long.valueOf(l1), localTimeUnit }));
          paramDataPoint.setTimeInterval(l5, l1, TimeUnit.NANOSECONDS);
        }
        return;
      }
    }
    
    private void zzh(DataPoint paramDataPoint)
    {
      long l3 = this.zzavX.getStartTime(TimeUnit.NANOSECONDS);
      long l4 = this.zzavX.getEndTime(TimeUnit.NANOSECONDS);
      long l2 = paramDataPoint.getTimestamp(TimeUnit.NANOSECONDS);
      TimeUnit localTimeUnit;
      long l1;
      if (l2 != 0L)
      {
        localTimeUnit = TimeUnit.MILLISECONDS;
        if (l2 >= l3)
        {
          l1 = l2;
          if (l2 <= l4) {}
        }
        else
        {
          l1 = zzns.zza(l2, TimeUnit.NANOSECONDS, localTimeUnit);
        }
        if ((l1 < l3) || (l1 > l4)) {
          break label185;
        }
      }
      label185:
      for (boolean bool = true;; bool = false)
      {
        com.google.android.gms.common.internal.zzx.zza(bool, "Data point %s has time stamp outside session interval [%d, %d]", new Object[] { paramDataPoint, Long.valueOf(l3), Long.valueOf(l4) });
        if (paramDataPoint.getTimestamp(TimeUnit.NANOSECONDS) != l1)
        {
          Log.w("Fitness", String.format("Data point timestamp [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", new Object[] { Long.valueOf(paramDataPoint.getTimestamp(TimeUnit.NANOSECONDS)), Long.valueOf(l1), localTimeUnit }));
          paramDataPoint.setTimestamp(l1, TimeUnit.NANOSECONDS);
        }
        return;
      }
    }
    
    private void zzvd()
    {
      Iterator localIterator1 = this.zzawg.iterator();
      while (localIterator1.hasNext())
      {
        Iterator localIterator2 = ((DataSet)localIterator1.next()).getDataPoints().iterator();
        while (localIterator2.hasNext()) {
          zzf((DataPoint)localIterator2.next());
        }
      }
      localIterator1 = this.zzaBs.iterator();
      while (localIterator1.hasNext()) {
        zzf((DataPoint)localIterator1.next());
      }
    }
    
    public Builder addAggregateDataPoint(DataPoint paramDataPoint)
    {
      DataSource localDataSource;
      if (paramDataPoint != null)
      {
        bool = true;
        com.google.android.gms.common.internal.zzx.zzb(bool, "Must specify a valid aggregate data point.");
        localDataSource = paramDataPoint.getDataSource();
        if (this.zzaBt.contains(localDataSource)) {
          break label79;
        }
      }
      label79:
      for (boolean bool = true;; bool = false)
      {
        com.google.android.gms.common.internal.zzx.zza(bool, "Data set/Aggregate data point for this data source %s is already added.", new Object[] { localDataSource });
        zznw.zze(paramDataPoint);
        this.zzaBt.add(localDataSource);
        this.zzaBs.add(paramDataPoint);
        return this;
        bool = false;
        break;
      }
    }
    
    public Builder addDataSet(DataSet paramDataSet)
    {
      boolean bool2 = true;
      DataSource localDataSource;
      if (paramDataSet != null)
      {
        bool1 = true;
        com.google.android.gms.common.internal.zzx.zzb(bool1, "Must specify a valid data set.");
        localDataSource = paramDataSet.getDataSource();
        if (this.zzaBt.contains(localDataSource)) {
          break label101;
        }
        bool1 = true;
        label36:
        com.google.android.gms.common.internal.zzx.zza(bool1, "Data set for this data source %s is already added.", new Object[] { localDataSource });
        if (paramDataSet.getDataPoints().isEmpty()) {
          break label106;
        }
      }
      label101:
      label106:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        com.google.android.gms.common.internal.zzx.zzb(bool1, "No data points specified in the input data set.");
        this.zzaBt.add(localDataSource);
        this.zzawg.add(paramDataSet);
        return this;
        bool1 = false;
        break;
        bool1 = false;
        break label36;
      }
    }
    
    public SessionInsertRequest build()
    {
      boolean bool2 = true;
      if (this.zzavX != null)
      {
        bool1 = true;
        com.google.android.gms.common.internal.zzx.zza(bool1, "Must specify a valid session.");
        if (this.zzavX.getEndTime(TimeUnit.MILLISECONDS) == 0L) {
          break label59;
        }
      }
      label59:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        com.google.android.gms.common.internal.zzx.zza(bool1, "Must specify a valid end time, cannot insert a continuing session.");
        zzvd();
        return new SessionInsertRequest(this, null);
        bool1 = false;
        break;
      }
    }
    
    public Builder setSession(Session paramSession)
    {
      this.zzavX = paramSession;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\SessionInsertRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */