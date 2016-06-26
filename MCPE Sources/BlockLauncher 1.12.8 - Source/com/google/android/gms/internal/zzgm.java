package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;

@zzhb
public final class zzgm
  extends zzgh.zza
{
  private final PlayStorePurchaseListener zzuP;
  
  public zzgm(PlayStorePurchaseListener paramPlayStorePurchaseListener)
  {
    this.zzuP = paramPlayStorePurchaseListener;
  }
  
  public boolean isValidPurchase(String paramString)
  {
    return this.zzuP.isValidPurchase(paramString);
  }
  
  public void zza(zzgg paramzzgg)
  {
    this.zzuP.onInAppPurchaseFinished(new zzgk(paramzzgg));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzgm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */