package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.ads.internal.zzr;

@zzhb
public class zzdp
  extends zzim
{
  final zzjp zzpD;
  final zzdr zzzJ;
  private final String zzzK;
  
  zzdp(zzjp paramzzjp, zzdr paramzzdr, String paramString)
  {
    this.zzpD = paramzzjp;
    this.zzzJ = paramzzdr;
    this.zzzK = paramString;
    zzr.zzbR().zza(this);
  }
  
  public void onStop()
  {
    this.zzzJ.abort();
  }
  
  public void zzbr()
  {
    try
    {
      this.zzzJ.zzU(this.zzzK);
      return;
    }
    finally
    {
      zzir.zzMc.post(new Runnable()
      {
        public void run()
        {
          zzr.zzbR().zzb(zzdp.this);
        }
      });
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzdp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */