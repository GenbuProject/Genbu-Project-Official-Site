package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.location.internal.ParcelableGeofence;

public abstract interface Geofence
{
  public static final int GEOFENCE_TRANSITION_DWELL = 4;
  public static final int GEOFENCE_TRANSITION_ENTER = 1;
  public static final int GEOFENCE_TRANSITION_EXIT = 2;
  public static final long NEVER_EXPIRE = -1L;
  
  public abstract String getRequestId();
  
  public static final class Builder
  {
    private String zzEY = null;
    private int zzaNC = 0;
    private long zzaND = Long.MIN_VALUE;
    private short zzaNE = -1;
    private double zzaNF;
    private double zzaNG;
    private float zzaNH;
    private int zzaNI = 0;
    private int zzaNJ = -1;
    
    public Geofence build()
    {
      if (this.zzEY == null) {
        throw new IllegalArgumentException("Request ID not set.");
      }
      if (this.zzaNC == 0) {
        throw new IllegalArgumentException("Transitions types not set.");
      }
      if (((this.zzaNC & 0x4) != 0) && (this.zzaNJ < 0)) {
        throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
      }
      if (this.zzaND == Long.MIN_VALUE) {
        throw new IllegalArgumentException("Expiration not set.");
      }
      if (this.zzaNE == -1) {
        throw new IllegalArgumentException("Geofence region not set.");
      }
      if (this.zzaNI < 0) {
        throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
      }
      return new ParcelableGeofence(this.zzEY, this.zzaNC, (short)1, this.zzaNF, this.zzaNG, this.zzaNH, this.zzaND, this.zzaNI, this.zzaNJ);
    }
    
    public Builder setCircularRegion(double paramDouble1, double paramDouble2, float paramFloat)
    {
      this.zzaNE = 1;
      this.zzaNF = paramDouble1;
      this.zzaNG = paramDouble2;
      this.zzaNH = paramFloat;
      return this;
    }
    
    public Builder setExpirationDuration(long paramLong)
    {
      if (paramLong < 0L)
      {
        this.zzaND = -1L;
        return this;
      }
      this.zzaND = (SystemClock.elapsedRealtime() + paramLong);
      return this;
    }
    
    public Builder setLoiteringDelay(int paramInt)
    {
      this.zzaNJ = paramInt;
      return this;
    }
    
    public Builder setNotificationResponsiveness(int paramInt)
    {
      this.zzaNI = paramInt;
      return this;
    }
    
    public Builder setRequestId(String paramString)
    {
      this.zzEY = paramString;
      return this;
    }
    
    public Builder setTransitionTypes(int paramInt)
    {
      this.zzaNC = paramInt;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\Geofence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */