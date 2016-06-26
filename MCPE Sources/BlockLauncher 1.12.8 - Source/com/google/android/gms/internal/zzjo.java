package com.google.android.gms.internal;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.ads.internal.overlay.zzk;
import com.google.android.gms.common.internal.zzx;

@zzhb
public class zzjo
{
  private final Context mContext;
  private zzk zzFo;
  private final ViewGroup zzNx;
  private final zzjp zzpD;
  
  public zzjo(Context paramContext, ViewGroup paramViewGroup, zzjp paramzzjp)
  {
    this(paramContext, paramViewGroup, paramzzjp, null);
  }
  
  zzjo(Context paramContext, ViewGroup paramViewGroup, zzjp paramzzjp, zzk paramzzk)
  {
    this.mContext = paramContext;
    this.zzNx = paramViewGroup;
    this.zzpD = paramzzjp;
    this.zzFo = paramzzk;
  }
  
  public void onDestroy()
  {
    zzx.zzcD("onDestroy must be called from the UI thread.");
    if (this.zzFo != null) {
      this.zzFo.destroy();
    }
  }
  
  public void onPause()
  {
    zzx.zzcD("onPause must be called from the UI thread.");
    if (this.zzFo != null) {
      this.zzFo.pause();
    }
  }
  
  public void zza(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if (this.zzFo != null) {
      return;
    }
    zzbx.zza(this.zzpD.zzic().zzdA(), this.zzpD.zzib(), new String[] { "vpr" });
    zzbz localzzbz = zzbx.zzb(this.zzpD.zzic().zzdA());
    this.zzFo = new zzk(this.mContext, this.zzpD, paramInt5, this.zzpD.zzic().zzdA(), localzzbz);
    this.zzNx.addView(this.zzFo, 0, new ViewGroup.LayoutParams(-1, -1));
    this.zzFo.zzd(paramInt1, paramInt2, paramInt3, paramInt4);
    this.zzpD.zzhU().zzG(false);
  }
  
  public void zze(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    zzx.zzcD("The underlay may only be modified from the UI thread.");
    if (this.zzFo != null) {
      this.zzFo.zzd(paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }
  
  public zzk zzhM()
  {
    zzx.zzcD("getAdVideoUnderlay must be called from the UI thread.");
    return this.zzFo;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzjo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */