package com.google.android.gms.nearby.sharing.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.nearby.sharing.SharedContent;
import java.util.List;

public final class ProvideContentRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<ProvideContentRequest> CREATOR = new zzf();
  final int versionCode;
  public IBinder zzbdk;
  public zzb zzbdl;
  @Deprecated
  public List<SharedContent> zzbdm;
  public long zzbdn;
  public zzc zzbdo;
  
  ProvideContentRequest()
  {
    this.versionCode = 1;
  }
  
  ProvideContentRequest(int paramInt, IBinder paramIBinder1, IBinder paramIBinder2, List<SharedContent> paramList, long paramLong, IBinder paramIBinder3)
  {
    this.versionCode = paramInt;
    this.zzbdk = paramIBinder1;
    this.zzbdl = zzb.zza.zzdG(paramIBinder2);
    this.zzbdm = paramList;
    this.zzbdn = paramLong;
    this.zzbdo = zzc.zza.zzdH(paramIBinder3);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzf.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzED()
  {
    return this.zzbdo.asBinder();
  }
  
  IBinder zzEP()
  {
    if (this.zzbdl == null) {
      return null;
    }
    return this.zzbdl.asBinder();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\sharing\internal\ProvideContentRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */