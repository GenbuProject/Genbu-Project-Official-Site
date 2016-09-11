package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public final class VideoCapabilities
  implements SafeParcelable
{
  public static final Parcelable.Creator<VideoCapabilities> CREATOR = new VideoCapabilitiesCreator();
  private final int mVersionCode;
  private final boolean zzaLm;
  private final boolean zzaLn;
  private final boolean zzaLo;
  private final boolean[] zzaLp;
  private final boolean[] zzaLq;
  
  public VideoCapabilities(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean[] paramArrayOfBoolean1, boolean[] paramArrayOfBoolean2)
  {
    this.mVersionCode = paramInt;
    this.zzaLm = paramBoolean1;
    this.zzaLn = paramBoolean2;
    this.zzaLo = paramBoolean3;
    this.zzaLp = paramArrayOfBoolean1;
    this.zzaLq = paramArrayOfBoolean2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof VideoCapabilities)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (this == paramObject);
      paramObject = (VideoCapabilities)paramObject;
      if ((!zzw.equal(((VideoCapabilities)paramObject).zzyb(), zzyb())) || (!zzw.equal(((VideoCapabilities)paramObject).zzyc(), zzyc())) || (!zzw.equal(Boolean.valueOf(((VideoCapabilities)paramObject).zzxZ()), Boolean.valueOf(zzxZ()))) || (!zzw.equal(Boolean.valueOf(((VideoCapabilities)paramObject).zzxY()), Boolean.valueOf(zzxY())))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(Boolean.valueOf(((VideoCapabilities)paramObject).zzya()), Boolean.valueOf(zzya())));
    return false;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { zzyb(), zzyc(), Boolean.valueOf(zzxZ()), Boolean.valueOf(zzxY()), Boolean.valueOf(zzya()) });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("SupportedCaptureModes", zzyb()).zzg("SupportedQualityLevels", zzyc()).zzg("CameraSupported", Boolean.valueOf(zzxZ())).zzg("MicSupported", Boolean.valueOf(zzxY())).zzg("StorageWriteSupported", Boolean.valueOf(zzya())).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    VideoCapabilitiesCreator.zza(this, paramParcel, paramInt);
  }
  
  public boolean zzxY()
  {
    return this.zzaLn;
  }
  
  public boolean zzxZ()
  {
    return this.zzaLm;
  }
  
  public boolean zzya()
  {
    return this.zzaLo;
  }
  
  public boolean[] zzyb()
  {
    return this.zzaLp;
  }
  
  public boolean[] zzyc()
  {
    return this.zzaLq;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\video\VideoCapabilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */