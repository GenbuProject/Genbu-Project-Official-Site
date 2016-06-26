package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.client.AdSizeParcel;

@zzhb
public class zzgp
  extends zzgn
{
  private zzgo zzGs;
  
  zzgp(Context paramContext, zzif.zza paramzza, zzjp paramzzjp, zzgr.zza paramzza1)
  {
    super(paramContext, paramzza, paramzzjp, paramzza1);
  }
  
  protected void zzgb()
  {
    Object localObject = this.zzpD.zzaN();
    int j;
    if (((AdSizeParcel)localObject).zzui)
    {
      localObject = this.mContext.getResources().getDisplayMetrics();
      j = ((DisplayMetrics)localObject).widthPixels;
    }
    for (int i = ((DisplayMetrics)localObject).heightPixels;; i = ((AdSizeParcel)localObject).heightPixels)
    {
      this.zzGs = new zzgo(this, this.zzpD, j, i);
      this.zzpD.zzhU().zza(this);
      this.zzGs.zza(this.zzGe);
      return;
      j = ((AdSizeParcel)localObject).widthPixels;
    }
  }
  
  protected int zzgc()
  {
    if (this.zzGs.zzgg())
    {
      zzin.zzaI("Ad-Network indicated no fill with passback URL.");
      return 3;
    }
    if (!this.zzGs.zzgh()) {
      return 2;
    }
    return -2;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzgp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */