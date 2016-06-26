package com.google.android.gms.games.snapshot;

import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.gms.common.data.BitmapTeleporter;

public abstract class SnapshotMetadataChange
{
  public static final SnapshotMetadataChange EMPTY_CHANGE = new SnapshotMetadataChangeEntity();
  
  public abstract Bitmap getCoverImage();
  
  public abstract String getDescription();
  
  public abstract Long getPlayedTimeMillis();
  
  public abstract Long getProgressValue();
  
  public abstract BitmapTeleporter zzxU();
  
  public static final class Builder
  {
    private Long zzaKN;
    private Long zzaKO;
    private BitmapTeleporter zzaKP;
    private Uri zzaKQ;
    private String zzaxl;
    
    public SnapshotMetadataChange build()
    {
      return new SnapshotMetadataChangeEntity(this.zzaxl, this.zzaKN, this.zzaKP, this.zzaKQ, this.zzaKO);
    }
    
    public Builder fromMetadata(SnapshotMetadata paramSnapshotMetadata)
    {
      this.zzaxl = paramSnapshotMetadata.getDescription();
      this.zzaKN = Long.valueOf(paramSnapshotMetadata.getPlayedTime());
      this.zzaKO = Long.valueOf(paramSnapshotMetadata.getProgressValue());
      if (this.zzaKN.longValue() == -1L) {
        this.zzaKN = null;
      }
      this.zzaKQ = paramSnapshotMetadata.getCoverImageUri();
      if (this.zzaKQ != null) {
        this.zzaKP = null;
      }
      return this;
    }
    
    public Builder setCoverImage(Bitmap paramBitmap)
    {
      this.zzaKP = new BitmapTeleporter(paramBitmap);
      this.zzaKQ = null;
      return this;
    }
    
    public Builder setDescription(String paramString)
    {
      this.zzaxl = paramString;
      return this;
    }
    
    public Builder setPlayedTimeMillis(long paramLong)
    {
      this.zzaKN = Long.valueOf(paramLong);
      return this;
    }
    
    public Builder setProgressValue(long paramLong)
    {
      this.zzaKO = Long.valueOf(paramLong);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\snapshot\SnapshotMetadataChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */