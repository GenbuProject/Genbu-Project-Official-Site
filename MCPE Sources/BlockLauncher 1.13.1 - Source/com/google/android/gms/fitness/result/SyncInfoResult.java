package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public class SyncInfoResult
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<SyncInfoResult> CREATOR = new zzl();
  private final int mVersionCode;
  private final Status zzUX;
  private final long zzaez;
  
  SyncInfoResult(int paramInt, Status paramStatus, long paramLong)
  {
    this.mVersionCode = paramInt;
    this.zzUX = paramStatus;
    this.zzaez = paramLong;
  }
  
  private boolean zzb(SyncInfoResult paramSyncInfoResult)
  {
    return (this.zzUX.equals(paramSyncInfoResult.zzUX)) && (zzw.equal(Long.valueOf(this.zzaez), Long.valueOf(paramSyncInfoResult.zzaez)));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof SyncInfoResult)) && (zzb((SyncInfoResult)paramObject)));
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
    return zzw.hashCode(new Object[] { this.zzUX, Long.valueOf(this.zzaez) });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("status", this.zzUX).zzg("timestamp", Long.valueOf(this.zzaez)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzl.zza(this, paramParcel, paramInt);
  }
  
  public long zzvq()
  {
    return this.zzaez;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\result\SyncInfoResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */