package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

public class StringListResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<StringListResponse> CREATOR = new zzbw();
  private final int mVersionCode;
  private final List<String> zzasD;
  
  StringListResponse(int paramInt, List<String> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzasD = paramList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzbw.zza(this, paramParcel, paramInt);
  }
  
  public List<String> zztx()
  {
    return this.zzasD;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\StringListResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */