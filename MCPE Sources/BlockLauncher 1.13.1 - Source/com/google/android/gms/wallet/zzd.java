package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd
  implements Parcelable.Creator<CreateWalletObjectsRequest>
{
  static void zza(CreateWalletObjectsRequest paramCreateWalletObjectsRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramCreateWalletObjectsRequest.getVersionCode());
    zzb.zza(paramParcel, 2, paramCreateWalletObjectsRequest.zzbol, paramInt, false);
    zzb.zza(paramParcel, 3, paramCreateWalletObjectsRequest.zzbom, paramInt, false);
    zzb.zza(paramParcel, 4, paramCreateWalletObjectsRequest.zzbon, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public CreateWalletObjectsRequest zzhr(Parcel paramParcel)
  {
    GiftCardWalletObject localGiftCardWalletObject = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    Object localObject2 = null;
    Object localObject1 = null;
    if (paramParcel.dataPosition() < j)
    {
      int k = zza.zzat(paramParcel);
      Object localObject3;
      switch (zza.zzca(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
      }
      for (;;)
      {
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localObject3;
        break;
        i = zza.zzg(paramParcel, k);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = (LoyaltyWalletObject)zza.zza(paramParcel, k, LoyaltyWalletObject.CREATOR);
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = (OfferWalletObject)zza.zza(paramParcel, k, OfferWalletObject.CREATOR);
        localObject2 = localObject1;
        localObject1 = localObject3;
        continue;
        localGiftCardWalletObject = (GiftCardWalletObject)zza.zza(paramParcel, k, GiftCardWalletObject.CREATOR);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new CreateWalletObjectsRequest(i, (LoyaltyWalletObject)localObject1, (OfferWalletObject)localObject2, localGiftCardWalletObject);
  }
  
  public CreateWalletObjectsRequest[] zzkP(int paramInt)
  {
    return new CreateWalletObjectsRequest[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */