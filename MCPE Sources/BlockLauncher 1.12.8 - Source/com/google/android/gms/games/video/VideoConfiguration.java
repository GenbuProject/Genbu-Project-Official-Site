package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public final class VideoConfiguration
  implements SafeParcelable
{
  public static final Parcelable.Creator<VideoConfiguration> CREATOR = new VideoConfigurationCreator();
  private final int mVersionCode;
  private final int zzaLr;
  private final int zzaLs;
  private final String zzaLt;
  private final String zzaLu;
  
  public VideoConfiguration(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2)
  {
    this.mVersionCode = paramInt1;
    zzx.zzac(zzgZ(paramInt2));
    zzx.zzac(zzha(paramInt3));
    this.zzaLr = paramInt2;
    this.zzaLs = paramInt3;
    if (paramInt3 == 1)
    {
      this.zzaLu = paramString2;
      this.zzaLt = paramString1;
      return;
    }
    if (paramString2 == null)
    {
      bool1 = true;
      zzx.zzb(bool1, "Stream key should be null when not streaming");
      if (paramString1 != null) {
        break label102;
      }
    }
    label102:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzx.zzb(bool1, "Stream url should be null when not streaming");
      this.zzaLu = null;
      this.zzaLt = null;
      return;
      bool1 = false;
      break;
    }
  }
  
  public static boolean zzgZ(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public static boolean zzha(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getStreamUrl()
  {
    return this.zzaLt;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    VideoConfigurationCreator.zza(this, paramParcel, paramInt);
  }
  
  public int zzyd()
  {
    return this.zzaLr;
  }
  
  public int zzye()
  {
    return this.zzaLs;
  }
  
  public String zzyf()
  {
    return this.zzaLu;
  }
  
  public static final class Builder {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\video\VideoConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */