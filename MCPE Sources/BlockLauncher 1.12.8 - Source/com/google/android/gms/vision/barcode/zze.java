package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze
  implements Parcelable.Creator<Barcode.ContactInfo>
{
  static void zza(Barcode.ContactInfo paramContactInfo, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramContactInfo.versionCode);
    zzb.zza(paramParcel, 2, paramContactInfo.name, paramInt, false);
    zzb.zza(paramParcel, 3, paramContactInfo.organization, false);
    zzb.zza(paramParcel, 4, paramContactInfo.title, false);
    zzb.zza(paramParcel, 5, paramContactInfo.phones, paramInt, false);
    zzb.zza(paramParcel, 6, paramContactInfo.emails, paramInt, false);
    zzb.zza(paramParcel, 7, paramContactInfo.urls, false);
    zzb.zza(paramParcel, 8, paramContactInfo.addresses, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public Barcode.ContactInfo zzha(Parcel paramParcel)
  {
    Barcode.Address[] arrayOfAddress = null;
    int j = zza.zzau(paramParcel);
    int i = 0;
    String[] arrayOfString = null;
    Barcode.Email[] arrayOfEmail = null;
    Barcode.Phone[] arrayOfPhone = null;
    String str1 = null;
    String str2 = null;
    Barcode.PersonName localPersonName = null;
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
        localPersonName = (Barcode.PersonName)zza.zza(paramParcel, k, Barcode.PersonName.CREATOR);
        break;
      case 3: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 4: 
        str1 = zza.zzp(paramParcel, k);
        break;
      case 5: 
        arrayOfPhone = (Barcode.Phone[])zza.zzb(paramParcel, k, Barcode.Phone.CREATOR);
        break;
      case 6: 
        arrayOfEmail = (Barcode.Email[])zza.zzb(paramParcel, k, Barcode.Email.CREATOR);
        break;
      case 7: 
        arrayOfString = zza.zzB(paramParcel, k);
        break;
      case 8: 
        arrayOfAddress = (Barcode.Address[])zza.zzb(paramParcel, k, Barcode.Address.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new Barcode.ContactInfo(i, localPersonName, str2, str1, arrayOfPhone, arrayOfEmail, arrayOfString, arrayOfAddress);
  }
  
  public Barcode.ContactInfo[] zzkx(int paramInt)
  {
    return new Barcode.ContactInfo[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\vision\barcode\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */