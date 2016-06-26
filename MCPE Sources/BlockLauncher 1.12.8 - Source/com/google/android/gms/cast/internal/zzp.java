package com.google.android.gms.cast.internal;

import android.os.SystemClock;

public final class zzp
{
  private static final zzl zzaaf = new zzl("RequestTracker");
  public static final Object zzaeB = new Object();
  private long zzacY;
  private zzo zzaeA;
  private long zzaey;
  private long zzaez;
  
  public zzp(long paramLong)
  {
    this.zzaey = paramLong;
    this.zzacY = -1L;
    this.zzaez = 0L;
  }
  
  private void zzoz()
  {
    this.zzacY = -1L;
    this.zzaeA = null;
    this.zzaez = 0L;
  }
  
  public void clear()
  {
    synchronized (zzaeB)
    {
      if (this.zzacY != -1L) {
        zzoz();
      }
      return;
    }
  }
  
  public boolean zzB(long paramLong)
  {
    for (;;)
    {
      synchronized (zzaeB)
      {
        if ((this.zzacY != -1L) && (this.zzacY == paramLong))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public void zza(long paramLong, zzo paramzzo)
  {
    synchronized (zzaeB)
    {
      zzo localzzo = this.zzaeA;
      long l = this.zzacY;
      this.zzacY = paramLong;
      this.zzaeA = paramzzo;
      this.zzaez = SystemClock.elapsedRealtime();
      if (localzzo != null) {
        localzzo.zzy(l);
      }
      return;
    }
  }
  
  public boolean zzc(long paramLong, int paramInt)
  {
    return zzc(paramLong, paramInt, null);
  }
  
  public boolean zzc(long paramLong, int paramInt, Object paramObject)
  {
    boolean bool = true;
    zzo localzzo = null;
    for (;;)
    {
      synchronized (zzaeB)
      {
        if ((this.zzacY != -1L) && (this.zzacY == paramLong))
        {
          zzaaf.zzb("request %d completed", new Object[] { Long.valueOf(this.zzacY) });
          localzzo = this.zzaeA;
          zzoz();
          if (localzzo != null) {
            localzzo.zza(paramLong, paramInt, paramObject);
          }
          return bool;
        }
      }
      bool = false;
    }
  }
  
  public boolean zzd(long paramLong, int paramInt)
  {
    boolean bool = true;
    long l = 0L;
    for (;;)
    {
      synchronized (zzaeB)
      {
        if ((this.zzacY != -1L) && (paramLong - this.zzaez >= this.zzaey))
        {
          zzaaf.zzb("request %d timed out", new Object[] { Long.valueOf(this.zzacY) });
          paramLong = this.zzacY;
          zzo localzzo = this.zzaeA;
          zzoz();
          if (localzzo != null) {
            localzzo.zza(paramLong, paramInt, null);
          }
          return bool;
        }
      }
      bool = false;
      Object localObject2 = null;
      paramLong = l;
    }
  }
  
  public boolean zzoA()
  {
    for (;;)
    {
      synchronized (zzaeB)
      {
        if (this.zzacY != -1L)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\internal\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */