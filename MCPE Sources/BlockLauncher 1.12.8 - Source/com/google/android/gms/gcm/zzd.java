package com.google.android.gms.gcm;

import android.os.Bundle;

public class zzd
{
  public static final zzd zzaMc = new zzd(0, 30, 3600);
  public static final zzd zzaMd = new zzd(1, 30, 3600);
  private final int zzaMe;
  private final int zzaMf;
  private final int zzaMg;
  
  private zzd(int paramInt1, int paramInt2, int paramInt3)
  {
    this.zzaMe = paramInt1;
    this.zzaMf = paramInt2;
    this.zzaMg = paramInt3;
  }
  
  public Bundle zzF(Bundle paramBundle)
  {
    paramBundle.putInt("retry_policy", this.zzaMe);
    paramBundle.putInt("initial_backoff_seconds", this.zzaMf);
    paramBundle.putInt("maximum_backoff_seconds", this.zzaMg);
    return paramBundle;
  }
  
  public int zzym()
  {
    return this.zzaMe;
  }
  
  public int zzyn()
  {
    return this.zzaMf;
  }
  
  public int zzyo()
  {
    return this.zzaMg;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\gcm\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */