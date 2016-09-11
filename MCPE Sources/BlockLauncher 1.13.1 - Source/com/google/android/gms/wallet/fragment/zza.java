package com.google.android.gms.wallet.fragment;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public class zza
  implements Parcelable.Creator<WalletFragmentInitParams>
{
  static void zza(WalletFragmentInitParams paramWalletFragmentInitParams, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramWalletFragmentInitParams.mVersionCode);
    zzb.zza(paramParcel, 2, paramWalletFragmentInitParams.getAccountName(), false);
    zzb.zza(paramParcel, 3, paramWalletFragmentInitParams.getMaskedWalletRequest(), paramInt, false);
    zzb.zzc(paramParcel, 4, paramWalletFragmentInitParams.getMaskedWalletRequestCode());
    zzb.zza(paramParcel, 5, paramWalletFragmentInitParams.getMaskedWallet(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public WalletFragmentInitParams zzhL(Parcel paramParcel)
  {
    MaskedWallet localMaskedWallet = null;
    int k = com.google.android.gms.common.internal.safeparcel.zza.zzau(paramParcel);
    int j = 0;
    int i = -1;
    MaskedWalletRequest localMaskedWalletRequest = null;
    String str = null;
    while (paramParcel.dataPosition() < k)
    {
      int m = com.google.android.gms.common.internal.safeparcel.zza.zzat(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzca(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, m);
        break;
      case 1: 
        j = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 2: 
        str = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 3: 
        localMaskedWalletRequest = (MaskedWalletRequest)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, MaskedWalletRequest.CREATOR);
        break;
      case 4: 
        i = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 5: 
        localMaskedWallet = (MaskedWallet)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, MaskedWallet.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new WalletFragmentInitParams(j, str, localMaskedWalletRequest, i, localMaskedWallet);
  }
  
  public WalletFragmentInitParams[] zzlj(int paramInt)
  {
    return new WalletFragmentInitParams[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\fragment\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */