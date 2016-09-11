package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public final class LocationRequest
  implements SafeParcelable
{
  public static final LocationRequestCreator CREATOR = new LocationRequestCreator();
  public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
  public static final int PRIORITY_HIGH_ACCURACY = 100;
  public static final int PRIORITY_LOW_POWER = 104;
  public static final int PRIORITY_NO_POWER = 105;
  int mPriority;
  private final int mVersionCode;
  boolean zzaBr;
  long zzaND;
  long zzaNY;
  long zzaNZ;
  int zzaOa;
  float zzaOb;
  long zzaOc;
  
  public LocationRequest()
  {
    this.mVersionCode = 1;
    this.mPriority = 102;
    this.zzaNY = 3600000L;
    this.zzaNZ = 600000L;
    this.zzaBr = false;
    this.zzaND = Long.MAX_VALUE;
    this.zzaOa = Integer.MAX_VALUE;
    this.zzaOb = 0.0F;
    this.zzaOc = 0L;
  }
  
  LocationRequest(int paramInt1, int paramInt2, long paramLong1, long paramLong2, boolean paramBoolean, long paramLong3, int paramInt3, float paramFloat, long paramLong4)
  {
    this.mVersionCode = paramInt1;
    this.mPriority = paramInt2;
    this.zzaNY = paramLong1;
    this.zzaNZ = paramLong2;
    this.zzaBr = paramBoolean;
    this.zzaND = paramLong3;
    this.zzaOa = paramInt3;
    this.zzaOb = paramFloat;
    this.zzaOc = paramLong4;
  }
  
  public static LocationRequest create()
  {
    return new LocationRequest();
  }
  
  private static void zzL(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("invalid interval: " + paramLong);
    }
  }
  
  private static void zzd(float paramFloat)
  {
    if (paramFloat < 0.0F) {
      throw new IllegalArgumentException("invalid displacement: " + paramFloat);
    }
  }
  
  private static void zzhs(int paramInt)
  {
    switch (paramInt)
    {
    case 101: 
    case 103: 
    default: 
      throw new IllegalArgumentException("invalid quality: " + paramInt);
    }
  }
  
  public static String zzht(int paramInt)
  {
    switch (paramInt)
    {
    case 101: 
    case 103: 
    default: 
      return "???";
    case 100: 
      return "PRIORITY_HIGH_ACCURACY";
    case 102: 
      return "PRIORITY_BALANCED_POWER_ACCURACY";
    case 104: 
      return "PRIORITY_LOW_POWER";
    }
    return "PRIORITY_NO_POWER";
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof LocationRequest)) {
        return false;
      }
      paramObject = (LocationRequest)paramObject;
    } while ((this.mPriority == ((LocationRequest)paramObject).mPriority) && (this.zzaNY == ((LocationRequest)paramObject).zzaNY) && (this.zzaNZ == ((LocationRequest)paramObject).zzaNZ) && (this.zzaBr == ((LocationRequest)paramObject).zzaBr) && (this.zzaND == ((LocationRequest)paramObject).zzaND) && (this.zzaOa == ((LocationRequest)paramObject).zzaOa) && (this.zzaOb == ((LocationRequest)paramObject).zzaOb));
    return false;
  }
  
  public long getExpirationTime()
  {
    return this.zzaND;
  }
  
  public long getFastestInterval()
  {
    return this.zzaNZ;
  }
  
  public long getInterval()
  {
    return this.zzaNY;
  }
  
  public long getMaxWaitTime()
  {
    long l2 = this.zzaOc;
    long l1 = l2;
    if (l2 < this.zzaNY) {
      l1 = this.zzaNY;
    }
    return l1;
  }
  
  public int getNumUpdates()
  {
    return this.zzaOa;
  }
  
  public int getPriority()
  {
    return this.mPriority;
  }
  
  public float getSmallestDisplacement()
  {
    return this.zzaOb;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.mPriority), Long.valueOf(this.zzaNY), Long.valueOf(this.zzaNZ), Boolean.valueOf(this.zzaBr), Long.valueOf(this.zzaND), Integer.valueOf(this.zzaOa), Float.valueOf(this.zzaOb) });
  }
  
  public LocationRequest setExpirationDuration(long paramLong)
  {
    long l = SystemClock.elapsedRealtime();
    if (paramLong > Long.MAX_VALUE - l) {}
    for (this.zzaND = Long.MAX_VALUE;; this.zzaND = (l + paramLong))
    {
      if (this.zzaND < 0L) {
        this.zzaND = 0L;
      }
      return this;
    }
  }
  
  public LocationRequest setExpirationTime(long paramLong)
  {
    this.zzaND = paramLong;
    if (this.zzaND < 0L) {
      this.zzaND = 0L;
    }
    return this;
  }
  
  public LocationRequest setFastestInterval(long paramLong)
  {
    zzL(paramLong);
    this.zzaBr = true;
    this.zzaNZ = paramLong;
    return this;
  }
  
  public LocationRequest setInterval(long paramLong)
  {
    zzL(paramLong);
    this.zzaNY = paramLong;
    if (!this.zzaBr) {
      this.zzaNZ = ((this.zzaNY / 6.0D));
    }
    return this;
  }
  
  public LocationRequest setMaxWaitTime(long paramLong)
  {
    zzL(paramLong);
    this.zzaOc = paramLong;
    return this;
  }
  
  public LocationRequest setNumUpdates(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("invalid numUpdates: " + paramInt);
    }
    this.zzaOa = paramInt;
    return this;
  }
  
  public LocationRequest setPriority(int paramInt)
  {
    zzhs(paramInt);
    this.mPriority = paramInt;
    return this;
  }
  
  public LocationRequest setSmallestDisplacement(float paramFloat)
  {
    zzd(paramFloat);
    this.zzaOb = paramFloat;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Request[").append(zzht(this.mPriority));
    if (this.mPriority != 105)
    {
      localStringBuilder.append(" requested=");
      localStringBuilder.append(this.zzaNY).append("ms");
    }
    localStringBuilder.append(" fastest=");
    localStringBuilder.append(this.zzaNZ).append("ms");
    if (this.zzaOc > this.zzaNY)
    {
      localStringBuilder.append(" maxWait=");
      localStringBuilder.append(this.zzaOc).append("ms");
    }
    if (this.zzaND != Long.MAX_VALUE)
    {
      long l1 = this.zzaND;
      long l2 = SystemClock.elapsedRealtime();
      localStringBuilder.append(" expireIn=");
      localStringBuilder.append(l1 - l2).append("ms");
    }
    if (this.zzaOa != Integer.MAX_VALUE) {
      localStringBuilder.append(" num=").append(this.zzaOa);
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    LocationRequestCreator.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\LocationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */