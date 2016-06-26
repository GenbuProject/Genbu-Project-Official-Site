package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class OnPinnedDownloadPreferencesResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnPinnedDownloadPreferencesResponse> CREATOR = new zzbf();
  final int mVersionCode;
  final ParcelableTransferPreferences zzasu;
  
  OnPinnedDownloadPreferencesResponse(int paramInt, ParcelableTransferPreferences paramParcelableTransferPreferences)
  {
    this.mVersionCode = paramInt;
    this.zzasu = paramParcelableTransferPreferences;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbf.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\OnPinnedDownloadPreferencesResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */