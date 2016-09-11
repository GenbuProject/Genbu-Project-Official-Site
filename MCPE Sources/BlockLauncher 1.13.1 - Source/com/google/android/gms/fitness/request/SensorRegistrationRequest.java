package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.zzk;
import com.google.android.gms.fitness.data.zzk.zza;
import com.google.android.gms.internal.zzow;
import com.google.android.gms.internal.zzow.zza;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SensorRegistrationRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<SensorRegistrationRequest> CREATOR = new zzv();
  private final PendingIntent mPendingIntent;
  private final int mVersionCode;
  private final zzow zzaAD;
  private zzk zzaBh;
  int zzaBi;
  int zzaBj;
  private final long zzaBk;
  private final long zzaBl;
  private final List<LocationRequest> zzaBm;
  private final long zzaBn;
  private final List<Object> zzaBo;
  private DataType zzavT;
  private DataSource zzavU;
  private final long zzaxo;
  private final int zzaxp;
  
  SensorRegistrationRequest(int paramInt1, DataSource paramDataSource, DataType paramDataType, IBinder paramIBinder1, int paramInt2, int paramInt3, long paramLong1, long paramLong2, PendingIntent paramPendingIntent, long paramLong3, int paramInt4, List<LocationRequest> paramList, long paramLong4, IBinder paramIBinder2)
  {
    this.mVersionCode = paramInt1;
    this.zzavU = paramDataSource;
    this.zzavT = paramDataType;
    if (paramIBinder1 == null) {}
    for (paramDataSource = null;; paramDataSource = zzk.zza.zzbt(paramIBinder1))
    {
      this.zzaBh = paramDataSource;
      long l = paramLong1;
      if (paramLong1 == 0L) {
        l = paramInt2;
      }
      this.zzaxo = l;
      this.zzaBl = paramLong3;
      paramLong1 = paramLong2;
      if (paramLong2 == 0L) {
        paramLong1 = paramInt3;
      }
      this.zzaBk = paramLong1;
      this.zzaBm = paramList;
      this.mPendingIntent = paramPendingIntent;
      this.zzaxp = paramInt4;
      this.zzaBo = Collections.emptyList();
      this.zzaBn = paramLong4;
      this.zzaAD = zzow.zza.zzbR(paramIBinder2);
      return;
    }
  }
  
  public SensorRegistrationRequest(DataSource paramDataSource, DataType paramDataType, zzk paramzzk, PendingIntent paramPendingIntent, long paramLong1, long paramLong2, long paramLong3, int paramInt, List<LocationRequest> paramList, List<Object> paramList1, long paramLong4, zzow paramzzow)
  {
    this.mVersionCode = 6;
    this.zzavU = paramDataSource;
    this.zzavT = paramDataType;
    this.zzaBh = paramzzk;
    this.mPendingIntent = paramPendingIntent;
    this.zzaxo = paramLong1;
    this.zzaBl = paramLong2;
    this.zzaBk = paramLong3;
    this.zzaxp = paramInt;
    this.zzaBm = paramList;
    this.zzaBo = paramList1;
    this.zzaBn = paramLong4;
    this.zzaAD = paramzzow;
  }
  
  public SensorRegistrationRequest(SensorRequest paramSensorRequest, zzk paramzzk, PendingIntent paramPendingIntent, zzow paramzzow)
  {
    this(paramSensorRequest.getDataSource(), paramSensorRequest.getDataType(), paramzzk, paramPendingIntent, paramSensorRequest.getSamplingRate(TimeUnit.MICROSECONDS), paramSensorRequest.getFastestRate(TimeUnit.MICROSECONDS), paramSensorRequest.getMaxDeliveryLatency(TimeUnit.MICROSECONDS), paramSensorRequest.getAccuracyMode(), null, Collections.emptyList(), paramSensorRequest.zzvc(), paramzzow);
  }
  
  private boolean zzb(SensorRegistrationRequest paramSensorRegistrationRequest)
  {
    return (zzw.equal(this.zzavU, paramSensorRegistrationRequest.zzavU)) && (zzw.equal(this.zzavT, paramSensorRegistrationRequest.zzavT)) && (this.zzaxo == paramSensorRegistrationRequest.zzaxo) && (this.zzaBl == paramSensorRegistrationRequest.zzaBl) && (this.zzaBk == paramSensorRegistrationRequest.zzaBk) && (this.zzaxp == paramSensorRegistrationRequest.zzaxp) && (zzw.equal(this.zzaBm, paramSensorRegistrationRequest.zzaBm));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof SensorRegistrationRequest)) && (zzb((SensorRegistrationRequest)paramObject)));
  }
  
  public int getAccuracyMode()
  {
    return this.zzaxp;
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaAD == null) {
      return null;
    }
    return this.zzaAD.asBinder();
  }
  
  public DataSource getDataSource()
  {
    return this.zzavU;
  }
  
  public DataType getDataType()
  {
    return this.zzavT;
  }
  
  public PendingIntent getIntent()
  {
    return this.mPendingIntent;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzavU, this.zzavT, this.zzaBh, Long.valueOf(this.zzaxo), Long.valueOf(this.zzaBl), Long.valueOf(this.zzaBk), Integer.valueOf(this.zzaxp), this.zzaBm });
  }
  
  public String toString()
  {
    return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", new Object[] { this.zzavT, this.zzavU, Long.valueOf(this.zzaxo), Long.valueOf(this.zzaBl), Long.valueOf(this.zzaBk) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzv.zza(this, paramParcel, paramInt);
  }
  
  public long zzuX()
  {
    return this.zzaBl;
  }
  
  public long zzuY()
  {
    return this.zzaBk;
  }
  
  public List<LocationRequest> zzuZ()
  {
    return this.zzaBm;
  }
  
  public long zzux()
  {
    return this.zzaxo;
  }
  
  public long zzva()
  {
    return this.zzaBn;
  }
  
  IBinder zzvb()
  {
    if (this.zzaBh == null) {
      return null;
    }
    return this.zzaBh.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\SensorRegistrationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */