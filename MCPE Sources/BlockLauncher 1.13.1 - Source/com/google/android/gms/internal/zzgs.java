package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.zzr;

@zzhb
public class zzgs
  extends zzgn
  implements zzjq.zza
{
  zzgs(Context paramContext, zzif.zza paramzza, zzjp paramzzjp, zzgr.zza paramzza1)
  {
    super(paramContext, paramzza, paramzzjp, paramzza1);
  }
  
  protected void zzgb()
  {
    if (this.zzGe.errorCode != -2) {
      return;
    }
    this.zzpD.zzhU().zza(this);
    zzgi();
    zzin.zzaI("Loading HTML in WebView.");
    this.zzpD.loadDataWithBaseURL(zzr.zzbC().zzaC(this.zzGe.zzEF), this.zzGe.body, "text/html", "UTF-8", null);
  }
  
  protected void zzgi() {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzgs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */