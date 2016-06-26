package com.google.android.gms.common.stats;

import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

public class zze
{
  private final long zzanN;
  private final int zzanO;
  private final SimpleArrayMap<String, Long> zzanP;
  
  public zze()
  {
    this.zzanN = 60000L;
    this.zzanO = 10;
    this.zzanP = new SimpleArrayMap(10);
  }
  
  public zze(int paramInt, long paramLong)
  {
    this.zzanN = paramLong;
    this.zzanO = paramInt;
    this.zzanP = new SimpleArrayMap();
  }
  
  private void zzb(long paramLong1, long paramLong2)
  {
    int i = this.zzanP.size() - 1;
    while (i >= 0)
    {
      if (paramLong2 - ((Long)this.zzanP.valueAt(i)).longValue() > paramLong1) {
        this.zzanP.removeAt(i);
      }
      i -= 1;
    }
  }
  
  public Long zzcS(String paramString)
  {
    long l2 = SystemClock.elapsedRealtime();
    long l1 = this.zzanN;
    try
    {
      while (this.zzanP.size() >= this.zzanO)
      {
        zzb(l1, l2);
        l1 /= 2L;
        Log.w("ConnectionTracker", "The max capacity " + this.zzanO + " is not enough. Current durationThreshold is: " + l1);
      }
      paramString = (Long)this.zzanP.put(paramString, Long.valueOf(l2));
    }
    finally {}
    return paramString;
  }
  
  public boolean zzcT(String paramString)
  {
    for (;;)
    {
      try
      {
        if (this.zzanP.remove(paramString) != null)
        {
          bool = true;
          return bool;
        }
      }
      finally {}
      boolean bool = false;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\stats\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */