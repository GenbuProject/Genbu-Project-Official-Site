package com.google.android.gms.fitness.request;

import android.os.SystemClock;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.concurrent.TimeUnit;

public class SensorRequest
{
  public static final int ACCURACY_MODE_DEFAULT = 2;
  public static final int ACCURACY_MODE_HIGH = 3;
  public static final int ACCURACY_MODE_LOW = 1;
  private final long zzaBk;
  private final long zzaBl;
  private final LocationRequest zzaBp;
  private final long zzaBq;
  private final DataType zzavT;
  private final DataSource zzavU;
  private final long zzaxo;
  private final int zzaxp;
  
  private SensorRequest(DataSource paramDataSource, LocationRequest paramLocationRequest)
  {
    this.zzaBp = paramLocationRequest;
    this.zzaxo = TimeUnit.MILLISECONDS.toMicros(paramLocationRequest.getInterval());
    this.zzaBl = TimeUnit.MILLISECONDS.toMicros(paramLocationRequest.getFastestInterval());
    this.zzaBk = this.zzaxo;
    this.zzavT = paramDataSource.getDataType();
    this.zzaxp = zza(paramLocationRequest);
    this.zzavU = paramDataSource;
    long l = paramLocationRequest.getExpirationTime();
    if (l == Long.MAX_VALUE)
    {
      this.zzaBq = Long.MAX_VALUE;
      return;
    }
    this.zzaBq = TimeUnit.MILLISECONDS.toMicros(l - SystemClock.elapsedRealtime());
  }
  
  private SensorRequest(Builder paramBuilder)
  {
    this.zzavU = Builder.zza(paramBuilder);
    this.zzavT = Builder.zzb(paramBuilder);
    this.zzaxo = Builder.zzc(paramBuilder);
    this.zzaBl = Builder.zzd(paramBuilder);
    this.zzaBk = Builder.zze(paramBuilder);
    this.zzaxp = Builder.zzf(paramBuilder);
    this.zzaBp = null;
    this.zzaBq = Builder.zzg(paramBuilder);
  }
  
  public static SensorRequest fromLocationRequest(DataSource paramDataSource, LocationRequest paramLocationRequest)
  {
    return new SensorRequest(paramDataSource, paramLocationRequest);
  }
  
  private static int zza(LocationRequest paramLocationRequest)
  {
    switch (paramLocationRequest.getPriority())
    {
    default: 
      return 2;
    case 100: 
      return 3;
    }
    return 1;
  }
  
  private boolean zza(SensorRequest paramSensorRequest)
  {
    return (zzw.equal(this.zzavU, paramSensorRequest.zzavU)) && (zzw.equal(this.zzavT, paramSensorRequest.zzavT)) && (this.zzaxo == paramSensorRequest.zzaxo) && (this.zzaBl == paramSensorRequest.zzaBl) && (this.zzaBk == paramSensorRequest.zzaBk) && (this.zzaxp == paramSensorRequest.zzaxp) && (zzw.equal(this.zzaBp, paramSensorRequest.zzaBp)) && (this.zzaBq == paramSensorRequest.zzaBq);
  }
  
  public static int zzfB(int paramInt)
  {
    int i = paramInt;
    switch (paramInt)
    {
    case 2: 
    default: 
      i = 2;
    }
    return i;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof SensorRequest)) && (zza((SensorRequest)paramObject)));
  }
  
  public int getAccuracyMode()
  {
    return this.zzaxp;
  }
  
  public DataSource getDataSource()
  {
    return this.zzavU;
  }
  
  public DataType getDataType()
  {
    return this.zzavT;
  }
  
  public long getFastestRate(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzaBl, TimeUnit.MICROSECONDS);
  }
  
  public long getMaxDeliveryLatency(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzaBk, TimeUnit.MICROSECONDS);
  }
  
  public long getSamplingRate(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzaxo, TimeUnit.MICROSECONDS);
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzavU, this.zzavT, Long.valueOf(this.zzaxo), Long.valueOf(this.zzaBl), Long.valueOf(this.zzaBk), Integer.valueOf(this.zzaxp), this.zzaBp, Long.valueOf(this.zzaBq) });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("dataSource", this.zzavU).zzg("dataType", this.zzavT).zzg("samplingRateMicros", Long.valueOf(this.zzaxo)).zzg("deliveryLatencyMicros", Long.valueOf(this.zzaBk)).zzg("timeOutMicros", Long.valueOf(this.zzaBq)).toString();
  }
  
  public long zzvc()
  {
    return this.zzaBq;
  }
  
  public static class Builder
  {
    private long zzaBk = 0L;
    private long zzaBl = 0L;
    private long zzaBq = Long.MAX_VALUE;
    private boolean zzaBr = false;
    private DataType zzavT;
    private DataSource zzavU;
    private long zzaxo = -1L;
    private int zzaxp = 2;
    
    public SensorRequest build()
    {
      boolean bool2 = false;
      if ((this.zzavU != null) || (this.zzavT != null)) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        zzx.zza(bool1, "Must call setDataSource() or setDataType()");
        if ((this.zzavT != null) && (this.zzavU != null))
        {
          bool1 = bool2;
          if (!this.zzavT.equals(this.zzavU.getDataType())) {}
        }
        else
        {
          bool1 = true;
        }
        zzx.zza(bool1, "Specified data type is incompatible with specified data source");
        return new SensorRequest(this, null);
      }
    }
    
    public Builder setAccuracyMode(int paramInt)
    {
      this.zzaxp = SensorRequest.zzfB(paramInt);
      return this;
    }
    
    public Builder setDataSource(DataSource paramDataSource)
    {
      this.zzavU = paramDataSource;
      return this;
    }
    
    public Builder setDataType(DataType paramDataType)
    {
      this.zzavT = paramDataType;
      return this;
    }
    
    public Builder setFastestRate(int paramInt, TimeUnit paramTimeUnit)
    {
      if (paramInt >= 0) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Cannot use a negative interval");
        this.zzaBr = true;
        this.zzaBl = paramTimeUnit.toMicros(paramInt);
        return this;
      }
    }
    
    public Builder setMaxDeliveryLatency(int paramInt, TimeUnit paramTimeUnit)
    {
      if (paramInt >= 0) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Cannot use a negative delivery interval");
        this.zzaBk = paramTimeUnit.toMicros(paramInt);
        return this;
      }
    }
    
    public Builder setSamplingRate(long paramLong, TimeUnit paramTimeUnit)
    {
      if (paramLong >= 0L) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Cannot use a negative sampling interval");
        this.zzaxo = paramTimeUnit.toMicros(paramLong);
        if (!this.zzaBr) {
          this.zzaBl = (this.zzaxo / 2L);
        }
        return this;
      }
    }
    
    public Builder setTimeout(long paramLong, TimeUnit paramTimeUnit)
    {
      boolean bool2 = true;
      if (paramLong > 0L)
      {
        bool1 = true;
        zzx.zzb(bool1, "Invalid time out value specified: %d", new Object[] { Long.valueOf(paramLong) });
        if (paramTimeUnit == null) {
          break label62;
        }
      }
      label62:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        zzx.zzb(bool1, "Invalid time unit specified");
        this.zzaBq = paramTimeUnit.toMicros(paramLong);
        return this;
        bool1 = false;
        break;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\SensorRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */