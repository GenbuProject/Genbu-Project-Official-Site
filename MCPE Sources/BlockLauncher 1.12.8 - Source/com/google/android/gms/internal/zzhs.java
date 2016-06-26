package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.client.zzb.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;

@zzhb
public class zzhs
  extends zzb.zza
{
  private final Context mContext;
  private final zzht zzKu;
  private final VersionInfoParcel zzpT;
  private final Object zzpV;
  
  public zzhs(Context paramContext, com.google.android.gms.ads.internal.zzd paramzzd, zzex paramzzex, VersionInfoParcel paramVersionInfoParcel)
  {
    this.mContext = paramContext;
    this.zzpT = paramVersionInfoParcel;
    this.zzKu = new zzht(paramContext, paramzzd, AdSizeParcel.zzcP(), paramzzex, paramVersionInfoParcel);
    this.zzpV = new Object();
  }
  
  public void destroy()
  {
    synchronized (this.zzpV)
    {
      this.zzKu.destroy();
      return;
    }
  }
  
  public boolean isLoaded()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzKu.isLoaded();
      return bool;
    }
  }
  
  public void pause()
  {
    synchronized (this.zzpV)
    {
      this.zzKu.pause();
      return;
    }
  }
  
  public void resume()
  {
    synchronized (this.zzpV)
    {
      this.zzKu.resume();
      return;
    }
  }
  
  public void setUserId(String paramString)
  {
    synchronized (this.zzpV)
    {
      this.zzKu.setUserId(paramString);
      return;
    }
  }
  
  public void show()
  {
    synchronized (this.zzpV)
    {
      this.zzKu.zzgL();
      return;
    }
  }
  
  public void zza(RewardedVideoAdRequestParcel paramRewardedVideoAdRequestParcel)
  {
    synchronized (this.zzpV)
    {
      this.zzKu.zza(paramRewardedVideoAdRequestParcel);
      return;
    }
  }
  
  public void zza(com.google.android.gms.ads.internal.reward.client.zzd paramzzd)
  {
    synchronized (this.zzpV)
    {
      this.zzKu.zza(paramzzd);
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzhs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */