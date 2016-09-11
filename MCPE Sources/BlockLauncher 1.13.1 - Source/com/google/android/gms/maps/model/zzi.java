package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzi
  implements Parcelable.Creator<PolylineOptions>
{
  static void zza(PolylineOptions paramPolylineOptions, Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, paramPolylineOptions.getVersionCode());
    zzb.zzc(paramParcel, 2, paramPolylineOptions.getPoints(), false);
    zzb.zza(paramParcel, 3, paramPolylineOptions.getWidth());
    zzb.zzc(paramParcel, 4, paramPolylineOptions.getColor());
    zzb.zza(paramParcel, 5, paramPolylineOptions.getZIndex());
    zzb.zza(paramParcel, 6, paramPolylineOptions.isVisible());
    zzb.zza(paramParcel, 7, paramPolylineOptions.isGeodesic());
    zzb.zza(paramParcel, 8, paramPolylineOptions.isClickable());
    zzb.zzI(paramParcel, paramInt);
  }
  
  public PolylineOptions zzfD(Parcel paramParcel)
  {
    float f1 = 0.0F;
    boolean bool1 = false;
    int k = zza.zzau(paramParcel);
    ArrayList localArrayList = null;
    boolean bool2 = false;
    boolean bool3 = false;
    int i = 0;
    float f2 = 0.0F;
    int j = 0;
    while (paramParcel.dataPosition() < k)
    {
      int m = zza.zzat(paramParcel);
      switch (zza.zzca(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        j = zza.zzg(paramParcel, m);
        break;
      case 2: 
        localArrayList = zza.zzc(paramParcel, m, LatLng.CREATOR);
        break;
      case 3: 
        f2 = zza.zzl(paramParcel, m);
        break;
      case 4: 
        i = zza.zzg(paramParcel, m);
        break;
      case 5: 
        f1 = zza.zzl(paramParcel, m);
        break;
      case 6: 
        bool3 = zza.zzc(paramParcel, m);
        break;
      case 7: 
        bool2 = zza.zzc(paramParcel, m);
        break;
      case 8: 
        bool1 = zza.zzc(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != k) {
      throw new zza.zza("Overread allowed size end=" + k, paramParcel);
    }
    return new PolylineOptions(j, localArrayList, f2, i, f1, bool3, bool2, bool1);
  }
  
  public PolylineOptions[] zzir(int paramInt)
  {
    return new PolylineOptions[paramInt];
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\maps\model\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */