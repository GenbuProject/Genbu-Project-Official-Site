package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchase;

@zzhb
public class zzgl
  implements InAppPurchase
{
  private final zzgc zzFL;
  
  public zzgl(zzgc paramzzgc)
  {
    this.zzFL = paramzzgc;
  }
  
  public String getProductId()
  {
    try
    {
      String str = this.zzFL.getProductId();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzd("Could not forward getProductId to InAppPurchase", localRemoteException);
    }
    return null;
  }
  
  public void recordPlayBillingResolution(int paramInt)
  {
    try
    {
      this.zzFL.recordPlayBillingResolution(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzd("Could not forward recordPlayBillingResolution to InAppPurchase", localRemoteException);
    }
  }
  
  public void recordResolution(int paramInt)
  {
    try
    {
      this.zzFL.recordResolution(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzd("Could not forward recordResolution to InAppPurchase", localRemoteException);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzgl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */