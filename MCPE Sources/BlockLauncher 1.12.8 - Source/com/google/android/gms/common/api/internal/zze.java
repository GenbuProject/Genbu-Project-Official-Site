package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.data.DataHolder;

public abstract class zze<L>
  implements zzq.zzb<L>
{
  private final DataHolder zzahi;
  
  protected zze(DataHolder paramDataHolder)
  {
    this.zzahi = paramDataHolder;
  }
  
  protected abstract void zza(L paramL, DataHolder paramDataHolder);
  
  public void zzpr()
  {
    if (this.zzahi != null) {
      this.zzahi.close();
    }
  }
  
  public final void zzt(L paramL)
  {
    zza(paramL, this.zzahi);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\api\internal\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */