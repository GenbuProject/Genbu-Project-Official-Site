package com.google.android.gms.drive.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveFileRange;
import java.util.Collections;
import java.util.List;

public class OnDownloadProgressResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<OnDownloadProgressResponse> CREATOR = new zzay();
  private static final List<DriveFileRange> zzash = ;
  final int mVersionCode;
  final int zzBc;
  final long zzasi;
  final long zzasj;
  final List<DriveFileRange> zzask;
  
  OnDownloadProgressResponse(int paramInt1, long paramLong1, long paramLong2, int paramInt2, List<DriveFileRange> paramList)
  {
    this.mVersionCode = paramInt1;
    this.zzasi = paramLong1;
    this.zzasj = paramLong2;
    this.zzBc = paramInt2;
    this.zzask = paramList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzay.zza(this, paramParcel, paramInt);
  }
  
  public long zztq()
  {
    return this.zzasi;
  }
  
  public long zztr()
  {
    return this.zzasj;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\OnDownloadProgressResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */