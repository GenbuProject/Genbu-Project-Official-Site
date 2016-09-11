package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public final class SnapshotMetadataChangeEntity
  extends SnapshotMetadataChange
  implements SafeParcelable
{
  public static final SnapshotMetadataChangeCreator CREATOR = new SnapshotMetadataChangeCreator();
  private final int mVersionCode;
  private final Long zzaKO;
  private final Uri zzaKQ;
  private final Long zzaKR;
  private BitmapTeleporter zzaKS;
  private final String zzaxl;
  
  SnapshotMetadataChangeEntity()
  {
    this(5, null, null, null, null, null);
  }
  
  SnapshotMetadataChangeEntity(int paramInt, String paramString, Long paramLong1, BitmapTeleporter paramBitmapTeleporter, Uri paramUri, Long paramLong2)
  {
    this.mVersionCode = paramInt;
    this.zzaxl = paramString;
    this.zzaKR = paramLong1;
    this.zzaKS = paramBitmapTeleporter;
    this.zzaKQ = paramUri;
    this.zzaKO = paramLong2;
    if (this.zzaKS != null) {
      if (this.zzaKQ == null) {
        zzx.zza(bool1, "Cannot set both a URI and an image");
      }
    }
    while (this.zzaKQ == null) {
      for (;;)
      {
        return;
        bool1 = false;
      }
    }
    if (this.zzaKS == null) {}
    for (bool1 = bool2;; bool1 = false)
    {
      zzx.zza(bool1, "Cannot set both a URI and an image");
      return;
    }
  }
  
  SnapshotMetadataChangeEntity(String paramString, Long paramLong1, BitmapTeleporter paramBitmapTeleporter, Uri paramUri, Long paramLong2)
  {
    this(5, paramString, paramLong1, paramBitmapTeleporter, paramUri, paramLong2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Bitmap getCoverImage()
  {
    if (this.zzaKS == null) {
      return null;
    }
    return this.zzaKS.zzqa();
  }
  
  public Uri getCoverImageUri()
  {
    return this.zzaKQ;
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public Long getPlayedTimeMillis()
  {
    return this.zzaKR;
  }
  
  public Long getProgressValue()
  {
    return this.zzaKO;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    SnapshotMetadataChangeCreator.zza(this, paramParcel, paramInt);
  }
  
  public BitmapTeleporter zzxU()
  {
    return this.zzaKS;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\snapshot\SnapshotMetadataChangeEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */