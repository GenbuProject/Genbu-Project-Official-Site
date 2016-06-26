package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzf
  implements Releasable, Result
{
  protected final Status zzUX;
  protected final DataHolder zzahi;
  
  protected zzf(DataHolder paramDataHolder, Status paramStatus)
  {
    this.zzUX = paramStatus;
    this.zzahi = paramDataHolder;
  }
  
  public Status getStatus()
  {
    return this.zzUX;
  }
  
  public void release()
  {
    if (this.zzahi != null) {
      this.zzahi.close();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\api\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */