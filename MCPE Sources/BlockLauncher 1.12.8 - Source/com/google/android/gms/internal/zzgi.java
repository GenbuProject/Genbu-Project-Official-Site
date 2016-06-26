package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchaseListener;

@zzhb
public final class zzgi
  extends zzgd.zza
{
  private final InAppPurchaseListener zzuO;
  
  public zzgi(InAppPurchaseListener paramInAppPurchaseListener)
  {
    this.zzuO = paramInAppPurchaseListener;
  }
  
  public void zza(zzgc paramzzgc)
  {
    this.zzuO.onInAppPurchaseRequested(new zzgl(paramzzgc));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzgi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */