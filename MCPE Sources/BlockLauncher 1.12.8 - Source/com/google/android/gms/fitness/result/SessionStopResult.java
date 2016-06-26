package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.fitness.data.Session;
import java.util.Collections;
import java.util.List;

public class SessionStopResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<SessionStopResult> CREATOR = new zzk();
  private final int mVersionCode;
  private final Status zzUX;
  private final List<Session> zzaAG;
  
  SessionStopResult(int paramInt, Status paramStatus, List<Session> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzUX = paramStatus;
    this.zzaAG = Collections.unmodifiableList(paramList);
  }
  
  public SessionStopResult(Status paramStatus, List<Session> paramList)
  {
    this.mVersionCode = 3;
    this.zzUX = paramStatus;
    this.zzaAG = Collections.unmodifiableList(paramList);
  }
  
  public static SessionStopResult zzV(Status paramStatus)
  {
    return new SessionStopResult(paramStatus, Collections.emptyList());
  }
  
  private boolean zzb(SessionStopResult paramSessionStopResult)
  {
    return (this.zzUX.equals(paramSessionStopResult.zzUX)) && (zzw.equal(this.zzaAG, paramSessionStopResult.zzaAG));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof SessionStopResult)) && (zzb((SessionStopResult)paramObject)));
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
    return zzw.hashCode(new Object[] { this.zzUX, this.zzaAG });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("status", this.zzUX).zzg("sessions", this.zzaAG).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzk.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\result\SessionStopResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */