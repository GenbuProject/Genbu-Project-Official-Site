package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.SessionDataSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SessionReadResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<SessionReadResult> CREATOR = new zzj();
  private final int mVersionCode;
  private final Status zzUX;
  private final List<Session> zzaAG;
  private final List<SessionDataSet> zzaBO;
  
  SessionReadResult(int paramInt, List<Session> paramList, List<SessionDataSet> paramList1, Status paramStatus)
  {
    this.mVersionCode = paramInt;
    this.zzaAG = paramList;
    this.zzaBO = Collections.unmodifiableList(paramList1);
    this.zzUX = paramStatus;
  }
  
  public SessionReadResult(List<Session> paramList, List<SessionDataSet> paramList1, Status paramStatus)
  {
    this.mVersionCode = 3;
    this.zzaAG = paramList;
    this.zzaBO = Collections.unmodifiableList(paramList1);
    this.zzUX = paramStatus;
  }
  
  public static SessionReadResult zzU(Status paramStatus)
  {
    return new SessionReadResult(new ArrayList(), new ArrayList(), paramStatus);
  }
  
  private boolean zzb(SessionReadResult paramSessionReadResult)
  {
    return (this.zzUX.equals(paramSessionReadResult.zzUX)) && (zzw.equal(this.zzaAG, paramSessionReadResult.zzaAG)) && (zzw.equal(this.zzaBO, paramSessionReadResult.zzaBO));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof SessionReadResult)) && (zzb((SessionReadResult)paramObject)));
  }
  
  public List<DataSet> getDataSet(Session paramSession)
  {
    zzx.zzb(this.zzaAG.contains(paramSession), "Attempting to read data for session %s which was not returned", new Object[] { paramSession });
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzaBO.iterator();
    while (localIterator.hasNext())
    {
      SessionDataSet localSessionDataSet = (SessionDataSet)localIterator.next();
      if (zzw.equal(paramSession, localSessionDataSet.getSession())) {
        localArrayList.add(localSessionDataSet.getDataSet());
      }
    }
    return localArrayList;
  }
  
  public List<DataSet> getDataSet(Session paramSession, DataType paramDataType)
  {
    zzx.zzb(this.zzaAG.contains(paramSession), "Attempting to read data for session %s which was not returned", new Object[] { paramSession });
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzaBO.iterator();
    while (localIterator.hasNext())
    {
      SessionDataSet localSessionDataSet = (SessionDataSet)localIterator.next();
      if ((zzw.equal(paramSession, localSessionDataSet.getSession())) && (paramDataType.equals(localSessionDataSet.getDataSet().getDataType()))) {
        localArrayList.add(localSessionDataSet.getDataSet());
      }
    }
    return localArrayList;
  }
  
  public List<Session> getSessions()
  {
    return this.zzaAG;
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
    return zzw.hashCode(new Object[] { this.zzUX, this.zzaAG, this.zzaBO });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("status", this.zzUX).zzg("sessions", this.zzaAG).zzg("sessionDataSets", this.zzaBO).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj.zza(this, paramParcel, paramInt);
  }
  
  public List<SessionDataSet> zzvp()
  {
    return this.zzaBO;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\result\SessionReadResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */