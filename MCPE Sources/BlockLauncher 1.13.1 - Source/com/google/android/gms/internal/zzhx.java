package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.zza.zza;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

@zzhb
public class zzhx
  extends zza.zza
{
  private zzhy zzKC;
  private zzhv zzKJ;
  private zzhw zzKK;
  
  public zzhx(zzhw paramzzhw)
  {
    this.zzKK = paramzzhw;
  }
  
  public void zza(zzd paramzzd, RewardItemParcel paramRewardItemParcel)
  {
    if (this.zzKK != null) {
      this.zzKK.zzc(paramRewardItemParcel);
    }
  }
  
  public void zza(zzhv paramzzhv)
  {
    this.zzKJ = paramzzhv;
  }
  
  public void zza(zzhy paramzzhy)
  {
    this.zzKC = paramzzhy;
  }
  
  public void zzb(zzd paramzzd, int paramInt)
  {
    if (this.zzKJ != null) {
      this.zzKJ.zzN(paramInt);
    }
  }
  
  public void zzc(zzd paramzzd, int paramInt)
  {
    if (this.zzKC != null) {
      this.zzKC.zza(zze.zzp(paramzzd).getClass().getName(), paramInt);
    }
  }
  
  public void zzg(zzd paramzzd)
  {
    if (this.zzKJ != null) {
      this.zzKJ.zzgN();
    }
  }
  
  public void zzh(zzd paramzzd)
  {
    if (this.zzKC != null) {
      this.zzKC.zzax(zze.zzp(paramzzd).getClass().getName());
    }
  }
  
  public void zzi(zzd paramzzd)
  {
    if (this.zzKK != null) {
      this.zzKK.onRewardedVideoAdOpened();
    }
  }
  
  public void zzj(zzd paramzzd)
  {
    if (this.zzKK != null) {
      this.zzKK.onRewardedVideoStarted();
    }
  }
  
  public void zzk(zzd paramzzd)
  {
    if (this.zzKK != null) {
      this.zzKK.onRewardedVideoAdClosed();
    }
  }
  
  public void zzl(zzd paramzzd)
  {
    if (this.zzKK != null) {
      this.zzKK.zzgM();
    }
  }
  
  public void zzm(zzd paramzzd)
  {
    if (this.zzKK != null) {
      this.zzKK.onRewardedVideoAdLeftApplication();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzhx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */