package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoRef;
import com.google.android.gms.games.internal.player.PlayerColumnNames;

public final class PlayerRef
  extends zzc
  implements Player
{
  private final PlayerLevelInfo zzaCS;
  private final PlayerColumnNames zzaDh;
  private final MostRecentGameInfoRef zzaDi;
  
  public PlayerRef(DataHolder paramDataHolder, int paramInt)
  {
    this(paramDataHolder, paramInt, null);
  }
  
  public PlayerRef(DataHolder paramDataHolder, int paramInt, String paramString)
  {
    super(paramDataHolder, paramInt);
    this.zzaDh = new PlayerColumnNames(paramString);
    this.zzaDi = new MostRecentGameInfoRef(paramDataHolder, paramInt, this.zzaDh);
    int i;
    if (zzvJ())
    {
      paramInt = getInteger(this.zzaDh.zzaIC);
      i = getInteger(this.zzaDh.zzaIF);
      paramString = new PlayerLevel(paramInt, getLong(this.zzaDh.zzaID), getLong(this.zzaDh.zzaIE));
      if (paramInt == i) {
        break label178;
      }
    }
    label178:
    for (paramDataHolder = new PlayerLevel(i, getLong(this.zzaDh.zzaIE), getLong(this.zzaDh.zzaIG));; paramDataHolder = paramString)
    {
      this.zzaCS = new PlayerLevelInfo(getLong(this.zzaDh.zzaIB), getLong(this.zzaDh.zzaIH), paramString, paramDataHolder);
      return;
      this.zzaCS = null;
      return;
    }
  }
  
  private boolean zzvJ()
  {
    if (zzcB(this.zzaDh.zzaIB)) {}
    while (getLong(this.zzaDh.zzaIB) == -1L) {
      return false;
    }
    return true;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return PlayerEntity.zza(this, paramObject);
  }
  
  public Player freeze()
  {
    return new PlayerEntity(this);
  }
  
  public Uri getBannerImageLandscapeUri()
  {
    return zzcA(this.zzaDh.zzaIS);
  }
  
  public String getBannerImageLandscapeUrl()
  {
    return getString(this.zzaDh.zzaIT);
  }
  
  public Uri getBannerImagePortraitUri()
  {
    return zzcA(this.zzaDh.zzaIU);
  }
  
  public String getBannerImagePortraitUrl()
  {
    return getString(this.zzaDh.zzaIV);
  }
  
  public String getDisplayName()
  {
    return getString(this.zzaDh.zzaIt);
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    zza(this.zzaDh.zzaIt, paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    return zzcA(this.zzaDh.zzaIw);
  }
  
  public String getHiResImageUrl()
  {
    return getString(this.zzaDh.zzaIx);
  }
  
  public Uri getIconImageUri()
  {
    return zzcA(this.zzaDh.zzaIu);
  }
  
  public String getIconImageUrl()
  {
    return getString(this.zzaDh.zzaIv);
  }
  
  public long getLastPlayedWithTimestamp()
  {
    if ((!zzcz(this.zzaDh.zzaIA)) || (zzcB(this.zzaDh.zzaIA))) {
      return -1L;
    }
    return getLong(this.zzaDh.zzaIA);
  }
  
  public PlayerLevelInfo getLevelInfo()
  {
    return this.zzaCS;
  }
  
  public String getName()
  {
    return getString(this.zzaDh.name);
  }
  
  public String getPlayerId()
  {
    return getString(this.zzaDh.zzaIs);
  }
  
  public long getRetrievedTimestamp()
  {
    return getLong(this.zzaDh.zzaIy);
  }
  
  public String getTitle()
  {
    return getString(this.zzaDh.title);
  }
  
  public void getTitle(CharArrayBuffer paramCharArrayBuffer)
  {
    zza(this.zzaDh.title, paramCharArrayBuffer);
  }
  
  public boolean hasHiResImage()
  {
    return getHiResImageUri() != null;
  }
  
  public boolean hasIconImage()
  {
    return getIconImageUri() != null;
  }
  
  public int hashCode()
  {
    return PlayerEntity.zzb(this);
  }
  
  public String toString()
  {
    return PlayerEntity.zzc(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((PlayerEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
  
  public String zzvE()
  {
    return getString(this.zzaDh.zzaIR);
  }
  
  public boolean zzvF()
  {
    return getBoolean(this.zzaDh.zzaIQ);
  }
  
  public int zzvG()
  {
    return getInteger(this.zzaDh.zzaIz);
  }
  
  public boolean zzvH()
  {
    return getBoolean(this.zzaDh.zzaIJ);
  }
  
  public MostRecentGameInfo zzvI()
  {
    if (zzcB(this.zzaDh.zzaIK)) {
      return null;
    }
    return this.zzaDi;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\PlayerRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */