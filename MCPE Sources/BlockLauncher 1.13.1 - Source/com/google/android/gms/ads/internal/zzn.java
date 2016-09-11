package com.google.android.gms.ads.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zzy.zza;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzin;

@zzhb
public class zzn
  extends zzy.zza
{
  private static final Object zzqy = new Object();
  private static zzn zzqz;
  private final Context mContext;
  private final Object zzpV = new Object();
  private boolean zzqA;
  private float zzqB = -1.0F;
  
  zzn(Context paramContext)
  {
    this.mContext = paramContext;
    this.zzqA = false;
  }
  
  public static zzn zzbs()
  {
    synchronized (zzqy)
    {
      zzn localzzn = zzqz;
      return localzzn;
    }
  }
  
  public static zzn zzr(Context paramContext)
  {
    synchronized (zzqy)
    {
      if (zzqz == null) {
        zzqz = new zzn(paramContext.getApplicationContext());
      }
      paramContext = zzqz;
      return paramContext;
    }
  }
  
  public void setAppVolume(float paramFloat)
  {
    synchronized (this.zzpV)
    {
      this.zzqB = paramFloat;
      return;
    }
  }
  
  public void zza()
  {
    synchronized (zzqy)
    {
      if (this.zzqA)
      {
        zzin.zzaK("Mobile ads is initialized already.");
        return;
      }
      this.zzqA = true;
      return;
    }
  }
  
  public float zzbt()
  {
    synchronized (this.zzpV)
    {
      float f = this.zzqB;
      return f;
    }
  }
  
  public boolean zzbu()
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzqB >= 0.0F)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */