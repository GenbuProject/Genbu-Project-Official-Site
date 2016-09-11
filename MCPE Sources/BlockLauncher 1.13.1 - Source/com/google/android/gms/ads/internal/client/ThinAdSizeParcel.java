package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.internal.zzhb;

@zzhb
public class ThinAdSizeParcel
  extends AdSizeParcel
{
  public ThinAdSizeParcel(AdSizeParcel paramAdSizeParcel)
  {
    super(paramAdSizeParcel.versionCode, paramAdSizeParcel.zzuh, paramAdSizeParcel.height, paramAdSizeParcel.heightPixels, paramAdSizeParcel.zzui, paramAdSizeParcel.width, paramAdSizeParcel.widthPixels, paramAdSizeParcel.zzuj, paramAdSizeParcel.zzuk, paramAdSizeParcel.zzul, paramAdSizeParcel.zzum);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzb.zzav(paramParcel);
    zzb.zzc(paramParcel, 1, this.versionCode);
    zzb.zza(paramParcel, 2, this.zzuh, false);
    zzb.zzc(paramParcel, 3, this.height);
    zzb.zzc(paramParcel, 6, this.width);
    zzb.zzI(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\client\ThinAdSizeParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */