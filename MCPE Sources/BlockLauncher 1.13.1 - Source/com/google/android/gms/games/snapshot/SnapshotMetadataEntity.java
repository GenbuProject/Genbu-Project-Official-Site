package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.zzms;

public final class SnapshotMetadataEntity
  implements SafeParcelable, SnapshotMetadata
{
  public static final Parcelable.Creator<SnapshotMetadataEntity> CREATOR = new SnapshotMetadataEntityCreator();
  private final int mVersionCode;
  private final String zzaEp;
  private final GameEntity zzaJE;
  private final Uri zzaKQ;
  private final PlayerEntity zzaKT;
  private final String zzaKU;
  private final long zzaKV;
  private final long zzaKW;
  private final float zzaKX;
  private final String zzaKY;
  private final boolean zzaKZ;
  private final long zzaLa;
  private final String zzaLb;
  private final String zzapg;
  private final String zzaxl;
  
  SnapshotMetadataEntity(int paramInt, GameEntity paramGameEntity, PlayerEntity paramPlayerEntity, String paramString1, Uri paramUri, String paramString2, String paramString3, String paramString4, long paramLong1, long paramLong2, float paramFloat, String paramString5, boolean paramBoolean, long paramLong3, String paramString6)
  {
    this.mVersionCode = paramInt;
    this.zzaJE = paramGameEntity;
    this.zzaKT = paramPlayerEntity;
    this.zzaEp = paramString1;
    this.zzaKQ = paramUri;
    this.zzaKU = paramString2;
    this.zzaKX = paramFloat;
    this.zzapg = paramString3;
    this.zzaxl = paramString4;
    this.zzaKV = paramLong1;
    this.zzaKW = paramLong2;
    this.zzaKY = paramString5;
    this.zzaKZ = paramBoolean;
    this.zzaLa = paramLong3;
    this.zzaLb = paramString6;
  }
  
  public SnapshotMetadataEntity(SnapshotMetadata paramSnapshotMetadata)
  {
    this.mVersionCode = 6;
    this.zzaJE = new GameEntity(paramSnapshotMetadata.getGame());
    this.zzaKT = new PlayerEntity(paramSnapshotMetadata.getOwner());
    this.zzaEp = paramSnapshotMetadata.getSnapshotId();
    this.zzaKQ = paramSnapshotMetadata.getCoverImageUri();
    this.zzaKU = paramSnapshotMetadata.getCoverImageUrl();
    this.zzaKX = paramSnapshotMetadata.getCoverImageAspectRatio();
    this.zzapg = paramSnapshotMetadata.getTitle();
    this.zzaxl = paramSnapshotMetadata.getDescription();
    this.zzaKV = paramSnapshotMetadata.getLastModifiedTimestamp();
    this.zzaKW = paramSnapshotMetadata.getPlayedTime();
    this.zzaKY = paramSnapshotMetadata.getUniqueName();
    this.zzaKZ = paramSnapshotMetadata.hasChangePending();
    this.zzaLa = paramSnapshotMetadata.getProgressValue();
    this.zzaLb = paramSnapshotMetadata.getDeviceName();
  }
  
  static int zza(SnapshotMetadata paramSnapshotMetadata)
  {
    return zzw.hashCode(new Object[] { paramSnapshotMetadata.getGame(), paramSnapshotMetadata.getOwner(), paramSnapshotMetadata.getSnapshotId(), paramSnapshotMetadata.getCoverImageUri(), Float.valueOf(paramSnapshotMetadata.getCoverImageAspectRatio()), paramSnapshotMetadata.getTitle(), paramSnapshotMetadata.getDescription(), Long.valueOf(paramSnapshotMetadata.getLastModifiedTimestamp()), Long.valueOf(paramSnapshotMetadata.getPlayedTime()), paramSnapshotMetadata.getUniqueName(), Boolean.valueOf(paramSnapshotMetadata.hasChangePending()), Long.valueOf(paramSnapshotMetadata.getProgressValue()), paramSnapshotMetadata.getDeviceName() });
  }
  
  static boolean zza(SnapshotMetadata paramSnapshotMetadata, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof SnapshotMetadata)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramSnapshotMetadata == paramObject);
      paramObject = (SnapshotMetadata)paramObject;
      if ((!zzw.equal(((SnapshotMetadata)paramObject).getGame(), paramSnapshotMetadata.getGame())) || (!zzw.equal(((SnapshotMetadata)paramObject).getOwner(), paramSnapshotMetadata.getOwner())) || (!zzw.equal(((SnapshotMetadata)paramObject).getSnapshotId(), paramSnapshotMetadata.getSnapshotId())) || (!zzw.equal(((SnapshotMetadata)paramObject).getCoverImageUri(), paramSnapshotMetadata.getCoverImageUri())) || (!zzw.equal(Float.valueOf(((SnapshotMetadata)paramObject).getCoverImageAspectRatio()), Float.valueOf(paramSnapshotMetadata.getCoverImageAspectRatio()))) || (!zzw.equal(((SnapshotMetadata)paramObject).getTitle(), paramSnapshotMetadata.getTitle())) || (!zzw.equal(((SnapshotMetadata)paramObject).getDescription(), paramSnapshotMetadata.getDescription())) || (!zzw.equal(Long.valueOf(((SnapshotMetadata)paramObject).getLastModifiedTimestamp()), Long.valueOf(paramSnapshotMetadata.getLastModifiedTimestamp()))) || (!zzw.equal(Long.valueOf(((SnapshotMetadata)paramObject).getPlayedTime()), Long.valueOf(paramSnapshotMetadata.getPlayedTime()))) || (!zzw.equal(((SnapshotMetadata)paramObject).getUniqueName(), paramSnapshotMetadata.getUniqueName())) || (!zzw.equal(Boolean.valueOf(((SnapshotMetadata)paramObject).hasChangePending()), Boolean.valueOf(paramSnapshotMetadata.hasChangePending()))) || (!zzw.equal(Long.valueOf(((SnapshotMetadata)paramObject).getProgressValue()), Long.valueOf(paramSnapshotMetadata.getProgressValue())))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((SnapshotMetadata)paramObject).getDeviceName(), paramSnapshotMetadata.getDeviceName()));
    return false;
  }
  
  static String zzb(SnapshotMetadata paramSnapshotMetadata)
  {
    return zzw.zzy(paramSnapshotMetadata).zzg("Game", paramSnapshotMetadata.getGame()).zzg("Owner", paramSnapshotMetadata.getOwner()).zzg("SnapshotId", paramSnapshotMetadata.getSnapshotId()).zzg("CoverImageUri", paramSnapshotMetadata.getCoverImageUri()).zzg("CoverImageUrl", paramSnapshotMetadata.getCoverImageUrl()).zzg("CoverImageAspectRatio", Float.valueOf(paramSnapshotMetadata.getCoverImageAspectRatio())).zzg("Description", paramSnapshotMetadata.getDescription()).zzg("LastModifiedTimestamp", Long.valueOf(paramSnapshotMetadata.getLastModifiedTimestamp())).zzg("PlayedTime", Long.valueOf(paramSnapshotMetadata.getPlayedTime())).zzg("UniqueName", paramSnapshotMetadata.getUniqueName()).zzg("ChangePending", Boolean.valueOf(paramSnapshotMetadata.hasChangePending())).zzg("ProgressValue", Long.valueOf(paramSnapshotMetadata.getProgressValue())).zzg("DeviceName", paramSnapshotMetadata.getDeviceName()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public SnapshotMetadata freeze()
  {
    return this;
  }
  
  public float getCoverImageAspectRatio()
  {
    return this.zzaKX;
  }
  
  public Uri getCoverImageUri()
  {
    return this.zzaKQ;
  }
  
  public String getCoverImageUrl()
  {
    return this.zzaKU;
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzaxl, paramCharArrayBuffer);
  }
  
  public String getDeviceName()
  {
    return this.zzaLb;
  }
  
  public Game getGame()
  {
    return this.zzaJE;
  }
  
  public long getLastModifiedTimestamp()
  {
    return this.zzaKV;
  }
  
  public Player getOwner()
  {
    return this.zzaKT;
  }
  
  public long getPlayedTime()
  {
    return this.zzaKW;
  }
  
  public long getProgressValue()
  {
    return this.zzaLa;
  }
  
  public String getSnapshotId()
  {
    return this.zzaEp;
  }
  
  public String getTitle()
  {
    return this.zzapg;
  }
  
  public String getUniqueName()
  {
    return this.zzaKY;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public boolean hasChangePending()
  {
    return this.zzaKZ;
  }
  
  public int hashCode()
  {
    return zza(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    SnapshotMetadataEntityCreator.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\snapshot\SnapshotMetadataEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */