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
import com.google.android.gms.internal.zzou;
import com.google.android.gms.internal.zzou.zza;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SessionReadRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SessionReadRequest> CREATOR = new zzy();
  private final int mVersionCode;
  private final String zzLq;
  private final long zzRD;
  private final List<DataSource> zzaAF;
  private final boolean zzaAQ;
  private final String zzaBu;
  private boolean zzaBv;
  private final List<String> zzaBw;
  private final zzou zzaBx;
  private final long zzavV;
  private final List<DataType> zzawe;
  
  SessionReadRequest(int paramInt, String paramString1, String paramString2, long paramLong1, long paramLong2, List<DataType> paramList, List<DataSource> paramList1, boolean paramBoolean1, boolean paramBoolean2, List<String> paramList2, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.zzaBu = paramString1;
    this.zzLq = paramString2;
    this.zzRD = paramLong1;
    this.zzavV = paramLong2;
    this.zzawe = paramList;
    this.zzaAF = paramList1;
    this.zzaBv = paramBoolean1;
    this.zzaAQ = paramBoolean2;
    this.zzaBw = paramList2;
    this.zzaBx = zzou.zza.zzbP(paramIBinder);
  }
  
  private SessionReadRequest(Builder paramBuilder)
  {
    this(Builder.zza(paramBuilder), Builder.zzb(paramBuilder), Builder.zzc(paramBuilder), Builder.zzd(paramBuilder), Builder.zze(paramBuilder), Builder.zzf(paramBuilder), Builder.zzg(paramBuilder), Builder.zzh(paramBuilder), Builder.zzi(paramBuilder), null);
  }
  
  public SessionReadRequest(SessionReadRequest paramSessionReadRequest, zzou paramzzou)
  {
    this(paramSessionReadRequest.zzaBu, paramSessionReadRequest.zzLq, paramSessionReadRequest.zzRD, paramSessionReadRequest.zzavV, paramSessionReadRequest.zzawe, paramSessionReadRequest.zzaAF, paramSessionReadRequest.zzaBv, paramSessionReadRequest.zzaAQ, paramSessionReadRequest.zzaBw, paramzzou);
  }
  
  public SessionReadRequest(String paramString1, String paramString2, long paramLong1, long paramLong2, List<DataType> paramList, List<DataSource> paramList1, boolean paramBoolean1, boolean paramBoolean2, List<String> paramList2, zzou paramzzou) {}
  
  private boolean zzb(SessionReadRequest paramSessionReadRequest)
  {
    return (zzw.equal(this.zzaBu, paramSessionReadRequest.zzaBu)) && (this.zzLq.equals(paramSessionReadRequest.zzLq)) && (this.zzRD == paramSessionReadRequest.zzRD) && (this.zzavV == paramSessionReadRequest.zzavV) && (zzw.equal(this.zzawe, paramSessionReadRequest.zzawe)) && (zzw.equal(this.zzaAF, paramSessionReadRequest.zzaAF)) && (this.zzaBv == paramSessionReadRequest.zzaBv) && (this.zzaBw.equals(paramSessionReadRequest.zzaBw)) && (this.zzaAQ == paramSessionReadRequest.zzaAQ);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof SessionReadRequest)) && (zzb((SessionReadRequest)paramObject)));
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaBx == null) {
      return null;
    }
    return this.zzaBx.asBinder();
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
  
  public List<String> getExcludedPackages()
  {
    return this.zzaBw;
  }
  
  public String getSessionId()
  {
    return this.zzLq;
  }
  
  public String getSessionName()
  {
    return this.zzaBu;
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
    return zzw.hashCode(new Object[] { this.zzaBu, this.zzLq, Long.valueOf(this.zzRD), Long.valueOf(this.zzavV) });
  }
  
  public boolean includeSessionsFromAllApps()
  {
    return this.zzaBv;
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("sessionName", this.zzaBu).zzg("sessionId", this.zzLq).zzg("startTimeMillis", Long.valueOf(this.zzRD)).zzg("endTimeMillis", Long.valueOf(this.zzavV)).zzg("dataTypes", this.zzawe).zzg("dataSources", this.zzaAF).zzg("sessionsFromAllApps", Boolean.valueOf(this.zzaBv)).zzg("excludedPackages", this.zzaBw).zzg("useServer", Boolean.valueOf(this.zzaAQ)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzy.zza(this, paramParcel, paramInt);
  }
  
  public long zzlO()
  {
    return this.zzRD;
  }
  
  public boolean zzuP()
  {
    return this.zzaAQ;
  }
  
  public long zzud()
  {
    return this.zzavV;
  }
  
  public boolean zzve()
  {
    return this.zzaBv;
  }
  
  public static class Builder
  {
    private String zzLq;
    private long zzRD = 0L;
    private List<DataSource> zzaAF = new ArrayList();
    private boolean zzaAQ = false;
    private String zzaBu;
    private boolean zzaBv = false;
    private List<String> zzaBw = new ArrayList();
    private long zzavV = 0L;
    private List<DataType> zzawe = new ArrayList();
    
    public SessionReadRequest build()
    {
      if (this.zzRD > 0L)
      {
        bool = true;
        zzx.zzb(bool, "Invalid start time: %s", new Object[] { Long.valueOf(this.zzRD) });
        if ((this.zzavV <= 0L) || (this.zzavV <= this.zzRD)) {
          break label89;
        }
      }
      label89:
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Invalid end time: %s", new Object[] { Long.valueOf(this.zzavV) });
        return new SessionReadRequest(this, null);
        bool = false;
        break;
      }
    }
    
    public Builder enableServerQueries()
    {
      this.zzaAQ = true;
      return this;
    }
    
    public Builder excludePackage(String paramString)
    {
      zzx.zzb(paramString, "Attempting to use a null package name");
      if (!this.zzaBw.contains(paramString)) {
        this.zzaBw.add(paramString);
      }
      return this;
    }
    
    public Builder read(DataSource paramDataSource)
    {
      zzx.zzb(paramDataSource, "Attempting to add a null data source");
      if (!this.zzaAF.contains(paramDataSource)) {
        this.zzaAF.add(paramDataSource);
      }
      return this;
    }
    
    public Builder read(DataType paramDataType)
    {
      zzx.zzb(paramDataType, "Attempting to use a null data type");
      if (!this.zzawe.contains(paramDataType)) {
        this.zzawe.add(paramDataType);
      }
      return this;
    }
    
    public Builder readSessionsFromAllApps()
    {
      this.zzaBv = true;
      return this;
    }
    
    public Builder setSessionId(String paramString)
    {
      this.zzLq = paramString;
      return this;
    }
    
    public Builder setSessionName(String paramString)
    {
      this.zzaBu = paramString;
      return this;
    }
    
    public Builder setTimeInterval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      this.zzRD = paramTimeUnit.toMillis(paramLong1);
      this.zzavV = paramTimeUnit.toMillis(paramLong2);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\request\SessionReadRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */