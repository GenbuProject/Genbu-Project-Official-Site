package com.google.android.gms.nearby.sharing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc
  implements Parcelable.Creator<SharedContent>
{
  static void zza(SharedContent paramSharedContent, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramSharedContent.getVersionCode());
    zzb.zza(paramParcel, 3, paramSharedContent.getUri(), false);
    zzb.zza(paramParcel, 8, paramSharedContent.zzEL(), paramInt, false);
    zzb.zza(paramParcel, 9, paramSharedContent.zzEM(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public SharedContent zzgs(Parcel paramParcel)
  {
    LocalContent[] arrayOfLocalContent = null;
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
      case 2: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
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
        localObject3 = zza.zzp(paramParcel, k);
        localObject1 = localObject2;
        localObject2 = localObject3;
        continue;
        localObject3 = (ViewableItem[])zza.zzb(paramParcel, k, ViewableItem.CREATOR);
        localObject2 = localObject1;
        localObject1 = localObject3;
        continue;
        arrayOfLocalContent = (LocalContent[])zza.zzb(paramParcel, k, LocalContent.CREATOR);
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new SharedContent(i, (String)localObject1, (ViewableItem[])localObject2, arrayOfLocalContent);
  }
  
  public SharedContent[] zzjy(int paramInt)
  {
    return new SharedContent[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\sharing\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */