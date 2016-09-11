package com.google.android.gms.games.video;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;

public final class VideoRef
  extends zzc
  implements Video
{
  VideoRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getDuration()
  {
    return getInteger("duration");
  }
  
  public long getFileSize()
  {
    return getLong("filesize");
  }
  
  public String getPackageName()
  {
    return getString("package");
  }
  
  public long getStartTime()
  {
    return getLong("start_time");
  }
  
  public String toString()
  {
    return VideoEntity.zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((VideoEntity)zzyg()).writeToParcel(paramParcel, paramInt);
  }
  
  public String zzxX()
  {
    return getString("filepath");
  }
  
  public Video zzyg()
  {
    return new VideoEntity(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\video\VideoRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */