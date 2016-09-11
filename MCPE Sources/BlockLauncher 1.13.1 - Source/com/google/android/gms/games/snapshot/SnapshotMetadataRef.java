package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class SnapshotMetadataRef
  extends zzc
  implements SnapshotMetadata
{
  private final Game zzaJc;
  private final Player zzaLc;
  
  public SnapshotMetadataRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
    this.zzaJc = new GameRef(paramDataHolder, paramInt);
    this.zzaLc = new PlayerRef(paramDataHolder, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return SnapshotMetadataEntity.zza(this, paramObject);
  }
  
  public SnapshotMetadata freeze()
  {
    return new SnapshotMetadataEntity(this);
  }
  
  public float getCoverImageAspectRatio()
  {
    float f1 = getFloat("cover_icon_image_height");
    float f2 = getFloat("cover_icon_image_width");
    if (f1 == 0.0F) {
      return 0.0F;
    }
    return f2 / f1;
  }
  
  public Uri getCoverImageUri()
  {
    return zzcA("cover_icon_image_uri");
  }
  
  public String getCoverImageUrl()
  {
    return getString("cover_icon_image_url");
  }
  
  public String getDescription()
  {
    return getString("description");
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    zza("description", paramCharArrayBuffer);
  }
  
  public String getDeviceName()
  {
    return getString("device_name");
  }
  
  public Game getGame()
  {
    return this.zzaJc;
  }
  
  public long getLastModifiedTimestamp()
  {
    return getLong("last_modified_timestamp");
  }
  
  public Player getOwner()
  {
    return this.zzaLc;
  }
  
  public long getPlayedTime()
  {
    return getLong("duration");
  }
  
  public long getProgressValue()
  {
    return getLong("progress_value");
  }
  
  public String getSnapshotId()
  {
    return getString("external_snapshot_id");
  }
  
  public String getTitle()
  {
    return getString("title");
  }
  
  public String getUniqueName()
  {
    return getString("unique_name");
  }
  
  public boolean hasChangePending()
  {
    return getInteger("pending_change_count") > 0;
  }
  
  public int hashCode()
  {
    return SnapshotMetadataEntity.zza(this);
  }
  
  public String toString()
  {
    return SnapshotMetadataEntity.zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((SnapshotMetadataEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\snapshot\SnapshotMetadataRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */