package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.zzpq;
import com.google.android.gms.measurement.zzg;

public class zzk
  extends zzd
{
  private final zzpq zzQX = new zzpq();
  
  zzk(zzf paramzzf)
  {
    super(paramzzf);
  }
  
  public void zziE()
  {
    Object localObject = zziI();
    String str = ((zzan)localObject).zzlg();
    if (str != null) {
      this.zzQX.setAppName(str);
    }
    localObject = ((zzan)localObject).zzli();
    if (localObject != null) {
      this.zzQX.setAppVersion((String)localObject);
    }
  }
  
  protected void zziJ()
  {
    zzjo().zzAH().zza(this.zzQX);
    zziE();
  }
  
  public zzpq zzjS()
  {
    zzjv();
    return this.zzQX;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\internal\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */