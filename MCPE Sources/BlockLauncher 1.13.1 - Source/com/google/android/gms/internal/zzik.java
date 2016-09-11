package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzr;

@zzhb
public class zzik
{
  private int zzLJ;
  private int zzLK;
  private final String zzLh;
  private final Object zzpV = new Object();
  private final zzih zzqV;
  
  zzik(zzih paramzzih, String paramString)
  {
    this.zzqV = paramzzih;
    this.zzLh = paramString;
  }
  
  public zzik(String paramString)
  {
    this(zzr.zzbF(), paramString);
  }
  
  public Bundle toBundle()
  {
    synchronized (this.zzpV)
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("pmnli", this.zzLJ);
      localBundle.putInt("pmnll", this.zzLK);
      return localBundle;
    }
  }
  
  public void zzg(int paramInt1, int paramInt2)
  {
    synchronized (this.zzpV)
    {
      this.zzLJ = paramInt1;
      this.zzLK = paramInt2;
      this.zzqV.zza(this.zzLh, this);
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzik.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */