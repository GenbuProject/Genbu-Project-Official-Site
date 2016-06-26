package com.google.android.gms.ads.internal.overlay;

import android.os.Handler;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzir;

@zzhb
class zzu
  implements Runnable
{
  private boolean mCancelled = false;
  private zzk zzFo;
  
  zzu(zzk paramzzk)
  {
    this.zzFo = paramzzk;
  }
  
  public void cancel()
  {
    this.mCancelled = true;
    zzir.zzMc.removeCallbacks(this);
  }
  
  public void run()
  {
    if (!this.mCancelled)
    {
      this.zzFo.zzfF();
      zzfQ();
    }
  }
  
  public void zzfQ()
  {
    zzir.zzMc.postDelayed(this, 250L);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\overlay\zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */