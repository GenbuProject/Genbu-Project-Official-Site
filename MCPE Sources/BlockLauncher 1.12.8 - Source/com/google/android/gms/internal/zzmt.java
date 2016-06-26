package com.google.android.gms.internal;

import android.os.SystemClock;

public final class zzmt
  implements zzmq
{
  private static zzmt zzaoa;
  
  public static zzmq zzsc()
  {
    try
    {
      if (zzaoa == null) {
        zzaoa = new zzmt();
      }
      zzmt localzzmt = zzaoa;
      return localzzmt;
    }
    finally {}
  }
  
  public long currentTimeMillis()
  {
    return System.currentTimeMillis();
  }
  
  public long elapsedRealtime()
  {
    return SystemClock.elapsedRealtime();
  }
  
  public long nanoTime()
  {
    return System.nanoTime();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzmt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */