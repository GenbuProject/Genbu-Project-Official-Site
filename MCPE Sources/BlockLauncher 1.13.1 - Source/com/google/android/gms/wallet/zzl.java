package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.identity.intents.model.UserAddress;

public class zzl
  implements Parcelable.Creator<MaskedWallet>
{
  static void zza(MaskedWallet paramMaskedWallet, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramMaskedWallet.getVersionCode());
    zzb.zza(paramParcel, 2, paramMaskedWallet.zzboo, false);
    zzb.zza(paramParcel, 3, paramMaskedWallet.zzbop, false);
    zzb.zza(paramParcel, 4, paramMaskedWallet.zzbou, false);
    zzb.zza(paramParcel, 5, paramMaskedWallet.zzbor, false);
    zzb.zza(paramParcel, 6, paramMaskedWallet.zzbos, paramInt, false);
    zzb.zza(paramParcel, 7, paramMaskedWallet.zzbot, paramInt, false);
    zzb.zza(paramParcel, 8, paramMaskedWallet.zzbpi, paramInt, false);
    zzb.zza(paramParcel, 9, paramMaskedWallet.zzbpj, paramInt, false);
    zzb.zza(paramParcel, 10, paramMaskedWallet.zzbov, paramInt, false);
    zzb.zza(paramParcel, 11, paramMaskedWallet.zzbow, paramInt, false);
    zzb.zza(paramParcel, 12, paramMaskedWallet.zzbox, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public MaskedWallet zzhz(Parcel paramParcel)
  {
    int j = zza.zzau(paramParcel);
    int i = 0;
    String str3 = null;
    String str2 = null;
    String[] arrayOfString = null;
    String str1 = null;
    Address localAddress2 = null;
    Address localAddress1 = null;
    LoyaltyWalletObject[] arrayOfLoyaltyWalletObject = null;
    OfferWalletObject[] arrayOfOfferWalletObject = null;
    UserAddress localUserAddress2 = null;
    UserAddress localUserAddress1 = null;
    InstrumentInfo[] arrayOfInstrumentInfo = null;
    while (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        i = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str3 = zza.zzp(paramParcel, k);
        break;
      case 3: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 4: 
        arrayOfString = zza.zzB(paramParcel, k);
        break;
      case 5: 
        str1 = zza.zzp(paramParcel, k);
        break;
      case 6: 
        localAddress2 = (Address)zza.zza(paramParcel, k, Address.CREATOR);
        break;
      case 7: 
        localAddress1 = (Address)zza.zza(paramParcel, k, Address.CREATOR);
        break;
      case 8: 
        arrayOfLoyaltyWalletObject = (LoyaltyWalletObject[])zza.zzb(paramParcel, k, LoyaltyWalletObject.CREATOR);
        break;
      case 9: 
        arrayOfOfferWalletObject = (OfferWalletObject[])zza.zzb(paramParcel, k, OfferWalletObject.CREATOR);
        break;
      case 10: 
        localUserAddress2 = (UserAddress)zza.zza(paramParcel, k, UserAddress.CREATOR);
        break;
      case 11: 
        localUserAddress1 = (UserAddress)zza.zza(paramParcel, k, UserAddress.CREATOR);
        break;
      case 12: 
        arrayOfInstrumentInfo = (InstrumentInfo[])zza.zzb(paramParcel, k, InstrumentInfo.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new MaskedWallet(i, str3, str2, arrayOfString, str1, localAddress2, localAddress1, arrayOfLoyaltyWalletObject, arrayOfOfferWalletObject, localUserAddress2, localUserAddress1, arrayOfInstrumentInfo);
  }
  
  public MaskedWallet[] zzkX(int paramInt)
  {
    return new MaskedWallet[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\zzl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */