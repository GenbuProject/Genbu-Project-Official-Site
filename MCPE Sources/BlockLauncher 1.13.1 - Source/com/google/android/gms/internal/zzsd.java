package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.IsReadyToPayRequest.zza;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.Payments;
import com.google.android.gms.wallet.Wallet.zza;
import com.google.android.gms.wallet.Wallet.zzb;

@SuppressLint({"MissingRemoteException"})
public class zzsd
  implements Payments
{
  public void changeMaskedWallet(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2, final int paramInt)
  {
    paramGoogleApiClient.zza(new Wallet.zzb(paramGoogleApiClient)
    {
      protected void zza(zzse paramAnonymouszzse)
      {
        paramAnonymouszzse.zzf(paramString1, paramString2, paramInt);
        zza(Status.zzagC);
      }
    });
  }
  
  public void checkForPreAuthorization(GoogleApiClient paramGoogleApiClient, final int paramInt)
  {
    paramGoogleApiClient.zza(new Wallet.zzb(paramGoogleApiClient)
    {
      protected void zza(zzse paramAnonymouszzse)
      {
        paramAnonymouszzse.zzln(paramInt);
        zza(Status.zzagC);
      }
    });
  }
  
  public void isNewUser(GoogleApiClient paramGoogleApiClient, final int paramInt)
  {
    paramGoogleApiClient.zza(new Wallet.zzb(paramGoogleApiClient)
    {
      protected void zza(zzse paramAnonymouszzse)
      {
        paramAnonymouszzse.zzlo(paramInt);
        zza(Status.zzagC);
      }
    });
  }
  
  public PendingResult<BooleanResult> isReadyToPay(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zza(new Wallet.zza(paramGoogleApiClient)
    {
      protected BooleanResult zzA(Status paramAnonymousStatus)
      {
        return new BooleanResult(paramAnonymousStatus, false);
      }
      
      protected void zza(zzse paramAnonymouszzse)
      {
        paramAnonymouszzse.zza(IsReadyToPayRequest.zzIj().zzIk(), this);
      }
    });
  }
  
  public void loadFullWallet(GoogleApiClient paramGoogleApiClient, final FullWalletRequest paramFullWalletRequest, final int paramInt)
  {
    paramGoogleApiClient.zza(new Wallet.zzb(paramGoogleApiClient)
    {
      protected void zza(zzse paramAnonymouszzse)
      {
        paramAnonymouszzse.zza(paramFullWalletRequest, paramInt);
        zza(Status.zzagC);
      }
    });
  }
  
  public void loadMaskedWallet(GoogleApiClient paramGoogleApiClient, final MaskedWalletRequest paramMaskedWalletRequest, final int paramInt)
  {
    paramGoogleApiClient.zza(new Wallet.zzb(paramGoogleApiClient)
    {
      protected void zza(zzse paramAnonymouszzse)
      {
        paramAnonymouszzse.zza(paramMaskedWalletRequest, paramInt);
        zza(Status.zzagC);
      }
    });
  }
  
  public void notifyTransactionStatus(GoogleApiClient paramGoogleApiClient, final NotifyTransactionStatusRequest paramNotifyTransactionStatusRequest)
  {
    paramGoogleApiClient.zza(new Wallet.zzb(paramGoogleApiClient)
    {
      protected void zza(zzse paramAnonymouszzse)
      {
        paramAnonymouszzse.zza(paramNotifyTransactionStatusRequest);
        zza(Status.zzagC);
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzsd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */