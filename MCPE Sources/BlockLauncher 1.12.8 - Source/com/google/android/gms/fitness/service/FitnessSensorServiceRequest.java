package com.google.android.gms.fitness.service;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.zzk;
import com.google.android.gms.fitness.data.zzk.zza;
import java.util.concurrent.TimeUnit;

public class FitnessSensorServiceRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<FitnessSensorServiceRequest> CREATOR = new zza();
  public static final int UNSPECIFIED = -1;
  private final int mVersionCode;
  private final long zzaBR;
  private final long zzaBS;
  private final zzk zzaBh;
  private final DataSource zzavU;
  
  FitnessSensorServiceRequest(int paramInt, DataSource paramDataSource, IBinder paramIBinder, long paramLong1, long paramLong2)
  {
    this.mVersionCode = paramInt;
    this.zzavU = paramDataSource;
    this.zzaBh = zzk.zza.zzbt(paramIBinder);
    this.zzaBR = paramLong1;
    this.zzaBS = paramLong2;
  }
  
  private boolean zza(FitnessSensorServiceRequest paramFitnessSensorServiceRequest)
  {
    return (zzw.equal(this.zzavU, paramFitnessSensorServiceRequest.zzavU)) && (this.zzaBR == paramFitnessSensorServiceRequest.zzaBR) && (this.zzaBS == paramFitnessSensorServiceRequest.zzaBS);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof FitnessSensorServiceRequest)) && (zza((FitnessSensorServiceRequest)paramObject)));
  }
  
  public long getBatchInterval(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzaBS, TimeUnit.MICROSECONDS);
  }
  
  public DataSource getDataSource()
  {
    return this.zzavU;
  }
  
  public SensorEventDispatcher getDispatcher()
  {
    return new zzb(this.zzaBh);
  }
  
  public long getSamplingRate(TimeUnit paramTimeUnit)
  {
    if (this.zzaBR == -1L) {
      return -1L;
    }
    return paramTimeUnit.convert(this.zzaBR, TimeUnit.MICROSECONDS);
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzavU, Long.valueOf(this.zzaBR), Long.valueOf(this.zzaBS) });
  }
  
  public String toString()
  {
    return String.format("FitnessSensorServiceRequest{%s}", new Object[] { this.zzavU });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public long zzux()
  {
    return this.zzaBR;
  }
  
  IBinder zzvb()
  {
    return this.zzaBh.asBinder();
  }
  
  public long zzvs()
  {
    return this.zzaBS;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\service\FitnessSensorServiceRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */