package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;

@zzhb
public class zzgk
  implements InAppPurchaseResult
{
  private final zzgg zzGb;
  
  public zzgk(zzgg paramzzgg)
  {
    this.zzGb = paramzzgg;
  }
  
  public void finishPurchase()
  {
    try
    {
      this.zzGb.finishPurchase();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzd("Could not forward finishPurchase to InAppPurchaseResult", localRemoteException);
    }
  }
  
  public String getProductId()
  {
    try
    {
      String str = this.zzGb.getProductId();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzd("Could not forward getProductId to InAppPurchaseResult", localRemoteException);
    }
    return null;
  }
  
  public Intent getPurchaseData()
  {
    try
    {
      Intent localIntent = this.zzGb.getPurchaseData();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzd("Could not forward getPurchaseData to InAppPurchaseResult", localRemoteException);
    }
    return null;
  }
  
  public int getResultCode()
  {
    try
    {
      int i = this.zzGb.getResultCode();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzd("Could not forward getPurchaseData to InAppPurchaseResult", localRemoteException);
    }
    return 0;
  }
  
  public boolean isVerified()
  {
    try
    {
      boolean bool = this.zzGb.isVerified();
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzd("Could not forward isVerified to InAppPurchaseResult", localRemoteException);
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzgk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */