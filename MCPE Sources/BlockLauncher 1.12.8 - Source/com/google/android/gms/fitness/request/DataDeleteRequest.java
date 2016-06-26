package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataDeleteRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataDeleteRequest> CREATOR = new zzd();
  private final int mVersionCode;
  private final long zzRD;
  private final zzow zzaAD;
  private final List<DataSource> zzaAF;
  private final List<Session> zzaAG;
  private final boolean zzaAH;
  private final boolean zzaAI;
  private final long zzavV;
  private final List<DataType> zzawe;
  
  DataDeleteRequest(int paramInt, long paramLong1, long paramLong2, List<DataSource> paramList, List<DataType> paramList1, List<Session> paramList2, boolean paramBoolean1, boolean paramBoolean2, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.zzRD = paramLong1;
    this.zzavV = paramLong2;
    this.zzaAF = Collections.unmodifiableList(paramList);
    this.zzawe = Collections.unmodifiableList(paramList1);
    this.zzaAG = paramList2;
    this.zzaAH = paramBoolean1;
    this.zzaAI = paramBoolean2;
    this.zzaAD = zzow.zza.zzbR(paramIBinder);
  }
  
  public DataDeleteRequest(long paramLong1, long paramLong2, List<DataSource> paramList, List<DataType> paramList1, List<Session> paramList2, boolean paramBoolean1, boolean paramBoolean2, zzow paramzzow)
  {
    this.mVersionCode = 3;
    this.zzRD = paramLong1;
    this.zzavV = paramLong2;
    this.zzaAF = Collections.unmodifiableList(paramList);
    this.zzawe = Collections.unmodifiableList(paramList1);
    this.zzaAG = paramList2;
    this.zzaAH = paramBoolean1;
    this.zzaAI = paramBoolean2;
    this.zzaAD = paramzzow;
  }
  
  private DataDeleteRequest(Builder paramBuilder)
  {
    this(Builder.zza(paramBuilder), Builder.zzb(paramBuilder), Builder.zzc(paramBuilder), Builder.zzd(paramBuilder), Builder.zze(paramBuilder), Builder.zzf(paramBuilder), Builder.zzg(paramBuilder), null);
  }
  
  public DataDeleteRequest(DataDeleteRequest paramDataDeleteRequest, zzow paramzzow)
  {
    this(paramDataDeleteRequest.zzRD, paramDataDeleteRequest.zzavV, paramDataDeleteRequest.zzaAF, paramDataDeleteRequest.zzawe, paramDataDeleteRequest.zzaAG, paramDataDeleteRequest.zzaAH, paramDataDeleteRequest.zzaAI, paramzzow);
  }
  
  private boolean zzb(DataDeleteRequest paramDataDeleteRequest)
  {
    return (this.zzRD == paramDataDeleteRequest.zzRD) && (this.zzavV == paramDataDeleteRequest.zzavV) && (zzw.equal(this.zzaAF, paramDataDeleteRequest.zzaAF)) && (zzw.equal(this.zzawe, paramDataDeleteRequest.zzawe)) && (zzw.equal(this.zzaAG, paramDataDeleteRequest.zzaAG)) && (this.zzaAH == paramDataDeleteRequest.zzaAH) && (this.zzaAI == paramDataDeleteRequest.zzaAI);
  }
  
  public boolean deleteAllData()
  {
    return this.zzaAH;
  }
  
  public boolean deleteAllSessions()
  {
    return this.zzaAI;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof DataDeleteRequest)) && (zzb((DataDeleteRequest)paramObject)));
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaAD == null) {
      return null;
    }
    return this.zzaAD.asBinder();
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
  
  public List<Session> getSessions()
  {
    return this.zzaAG;
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
    return zzw.hashCode(new Object[] { Long.valueOf(this.zzRD), Long.valueOf(this.zzavV) });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("startTimeMillis", Long.valueOf(this.zzRD)).zzg("endTimeMillis", Long.valueOf(this.zzavV)).zzg("dataSources", this.zzaAF).zzg("dateTypes", this.zzawe).zzg("sessions", this.zzaAG).zzg("deleteAllData", Boolean.valueOf(this.zzaAH)).zzg("deleteAllSessions", Boolean.valueOf(this.zzaAI)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
  
  public long zzlO()
  {
    return this.zzRD;
  }
  
  public boolean zzuL()
  {
    return this.zzaAH;
  }
  
  public boolean zzuM()
  {
    return this.zzaAI;
  }
  
  public long zzud()
  {
    return this.zzavV;
  }
  
  public static class Builder
  {
    private long zzRD;
    private List<DataSource> zzaAF = new ArrayList();
    private List<Session> zzaAG = new ArrayList();
    private boolean zzaAH = false;
    private boolean zzaAI = false;
    private long zzavV;
    private List<DataType> zzawe = new ArrayList();
    
    private void zzuN()
    {
      if (this.zzaAG.isEmpty()) {
        return;
      }
      Iterator localIterator = this.zzaAG.iterator();
      label23:
      Session localSession;
      if (localIterator.hasNext())
      {
        localSession = (Session)localIterator.next();
        if ((localSession.getStartTime(TimeUnit.MILLISECONDS) < this.zzRD) || (localSession.getEndTime(TimeUnit.MILLISECONDS) > this.zzavV)) {
          break label111;
        }
      }
      label111:
      for (boolean bool = true;; bool = false)
      {
        zzx.zza(bool, "Session %s is outside the time interval [%d, %d]", new Object[] { localSession, Long.valueOf(this.zzRD), Long.valueOf(this.zzavV) });
        break label23;
        break;
      }
    }
    
    public Builder addDataSource(DataSource paramDataSource)
    {
      boolean bool2 = true;
      if (!this.zzaAH)
      {
        bool1 = true;
        zzx.zzb(bool1, "All data is already marked for deletion.  addDataSource() cannot be combined with deleteAllData()");
        if (paramDataSource == null) {
          break label60;
        }
      }
      label60:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        zzx.zzb(bool1, "Must specify a valid data source");
        if (!this.zzaAF.contains(paramDataSource)) {
          this.zzaAF.add(paramDataSource);
        }
        return this;
        bool1 = false;
        break;
      }
    }
    
    public Builder addDataType(DataType paramDataType)
    {
      boolean bool2 = true;
      if (!this.zzaAH)
      {
        bool1 = true;
        zzx.zzb(bool1, "All data is already marked for deletion.  addDataType() cannot be combined with deleteAllData()");
        if (paramDataType == null) {
          break label60;
        }
      }
      label60:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        zzx.zzb(bool1, "Must specify a valid data type");
        if (!this.zzawe.contains(paramDataType)) {
          this.zzawe.add(paramDataType);
        }
        return this;
        bool1 = false;
        break;
      }
    }
    
    public Builder addSession(Session paramSession)
    {
      boolean bool2 = true;
      if (!this.zzaAI)
      {
        bool1 = true;
        zzx.zzb(bool1, "All sessions already marked for deletion.  addSession() cannot be combined with deleteAllSessions()");
        if (paramSession == null) {
          break label67;
        }
        bool1 = true;
        label23:
        zzx.zzb(bool1, "Must specify a valid session");
        if (paramSession.getEndTime(TimeUnit.MILLISECONDS) <= 0L) {
          break label72;
        }
      }
      label67:
      label72:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        zzx.zzb(bool1, "Cannot delete an ongoing session. Please stop the session prior to deleting it");
        this.zzaAG.add(paramSession);
        return this;
        bool1 = false;
        break;
        bool1 = false;
        break label23;
      }
    }
    
    public DataDeleteRequest build()
    {
      boolean bool2 = false;
      boolean bool1;
      int i;
      if ((this.zzRD > 0L) && (this.zzavV > this.zzRD))
      {
        bool1 = true;
        zzx.zza(bool1, "Must specify a valid time interval");
        if ((!this.zzaAH) && (this.zzaAF.isEmpty()) && (this.zzawe.isEmpty())) {
          break label124;
        }
        i = 1;
        label65:
        if ((!this.zzaAI) && (this.zzaAG.isEmpty())) {
          break label129;
        }
      }
      label124:
      label129:
      for (int j = 1;; j = 0)
      {
        if (i == 0)
        {
          bool1 = bool2;
          if (j == 0) {}
        }
        else
        {
          bool1 = true;
        }
        zzx.zza(bool1, "No data or session marked for deletion");
        zzuN();
        return new DataDeleteRequest(this, null);
        bool1 = false;
        break;
        i = 0;
        break label65;
      }
    }
    
    public Builder deleteAllData()
    {
      zzx.zzb(this.zzawe.isEmpty(), "Specific data type already added for deletion. deleteAllData() will delete all data types and cannot be combined with addDataType()");
      zzx.zzb(this.zzaAF.isEmpty(), "Specific data source already added for deletion. deleteAllData() will delete all data sources and cannot be combined with addDataSource()");
      this.zzaAH = true;
      return this;
    }
    
    public Builder deleteAllSessions()
    {
      zzx.zzb(this.zzaAG.isEmpty(), "Specific session already added for deletion. deleteAllData() will delete all sessions and cannot be combined with addSession()");
      this.zzaAI = true;
      return this;
    }
    
    public Builder setTimeInterval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      if (paramLong1 > 0L)
      {
        bool = true;
        zzx.zzb(bool, "Invalid start time :%d", new Object[] { Long.valueOf(paramLong1) });
        if (paramLong2 <= paramLong1) {
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\DataDeleteRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */