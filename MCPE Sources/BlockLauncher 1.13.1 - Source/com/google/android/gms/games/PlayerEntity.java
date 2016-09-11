package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.internal.player.MostRecentGameInfo;
import com.google.android.gms.games.internal.player.MostRecentGameInfoEntity;
import com.google.android.gms.internal.zzms;

public final class PlayerEntity
  extends GamesDowngradeableSafeParcel
  implements Player
{
  public static final Parcelable.Creator<PlayerEntity> CREATOR = new PlayerEntityCreatorCompat();
  private final String mName;
  private final int mVersionCode;
  private String zzWQ;
  private final long zzaCO;
  private final int zzaCP;
  private final long zzaCQ;
  private final MostRecentGameInfoEntity zzaCR;
  private final PlayerLevelInfo zzaCS;
  private final boolean zzaCT;
  private final boolean zzaCU;
  private final String zzaCV;
  private final Uri zzaCW;
  private final String zzaCX;
  private final Uri zzaCY;
  private final String zzaCZ;
  private final Uri zzaCd;
  private final Uri zzaCe;
  private final String zzaCo;
  private final String zzaCp;
  private String zzacX;
  private final String zzapg;
  
  PlayerEntity(int paramInt1, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2, long paramLong1, int paramInt2, long paramLong2, String paramString3, String paramString4, String paramString5, MostRecentGameInfoEntity paramMostRecentGameInfoEntity, PlayerLevelInfo paramPlayerLevelInfo, boolean paramBoolean1, boolean paramBoolean2, String paramString6, String paramString7, Uri paramUri3, String paramString8, Uri paramUri4, String paramString9)
  {
    this.mVersionCode = paramInt1;
    this.zzacX = paramString1;
    this.zzWQ = paramString2;
    this.zzaCd = paramUri1;
    this.zzaCo = paramString3;
    this.zzaCe = paramUri2;
    this.zzaCp = paramString4;
    this.zzaCO = paramLong1;
    this.zzaCP = paramInt2;
    this.zzaCQ = paramLong2;
    this.zzapg = paramString5;
    this.zzaCT = paramBoolean1;
    this.zzaCR = paramMostRecentGameInfoEntity;
    this.zzaCS = paramPlayerLevelInfo;
    this.zzaCU = paramBoolean2;
    this.zzaCV = paramString6;
    this.mName = paramString7;
    this.zzaCW = paramUri3;
    this.zzaCX = paramString8;
    this.zzaCY = paramUri4;
    this.zzaCZ = paramString9;
  }
  
  public PlayerEntity(Player paramPlayer)
  {
    this(paramPlayer, true);
  }
  
  public PlayerEntity(Player paramPlayer, boolean paramBoolean)
  {
    this.mVersionCode = 13;
    Object localObject1;
    if (paramBoolean)
    {
      localObject1 = paramPlayer.getPlayerId();
      this.zzacX = ((String)localObject1);
      this.zzWQ = paramPlayer.getDisplayName();
      this.zzaCd = paramPlayer.getIconImageUri();
      this.zzaCo = paramPlayer.getIconImageUrl();
      this.zzaCe = paramPlayer.getHiResImageUri();
      this.zzaCp = paramPlayer.getHiResImageUrl();
      this.zzaCO = paramPlayer.getRetrievedTimestamp();
      this.zzaCP = paramPlayer.zzvG();
      this.zzaCQ = paramPlayer.getLastPlayedWithTimestamp();
      this.zzapg = paramPlayer.getTitle();
      this.zzaCT = paramPlayer.zzvH();
      localObject1 = paramPlayer.zzvI();
      if (localObject1 != null) {
        break label267;
      }
      localObject1 = localObject2;
      label143:
      this.zzaCR = ((MostRecentGameInfoEntity)localObject1);
      this.zzaCS = paramPlayer.getLevelInfo();
      this.zzaCU = paramPlayer.zzvF();
      this.zzaCV = paramPlayer.zzvE();
      this.mName = paramPlayer.getName();
      this.zzaCW = paramPlayer.getBannerImageLandscapeUri();
      this.zzaCX = paramPlayer.getBannerImageLandscapeUrl();
      this.zzaCY = paramPlayer.getBannerImagePortraitUri();
      this.zzaCZ = paramPlayer.getBannerImagePortraitUrl();
      if (paramBoolean) {
        zzb.zzv(this.zzacX);
      }
      zzb.zzv(this.zzWQ);
      if (this.zzaCO <= 0L) {
        break label279;
      }
    }
    label267:
    label279:
    for (paramBoolean = true;; paramBoolean = false)
    {
      zzb.zzab(paramBoolean);
      return;
      localObject1 = null;
      break;
      localObject1 = new MostRecentGameInfoEntity((MostRecentGameInfo)localObject1);
      break label143;
    }
  }
  
  static boolean zza(Player paramPlayer, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Player)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramPlayer == paramObject);
      paramObject = (Player)paramObject;
      if ((!zzw.equal(((Player)paramObject).getPlayerId(), paramPlayer.getPlayerId())) || (!zzw.equal(((Player)paramObject).getDisplayName(), paramPlayer.getDisplayName())) || (!zzw.equal(Boolean.valueOf(((Player)paramObject).zzvF()), Boolean.valueOf(paramPlayer.zzvF()))) || (!zzw.equal(((Player)paramObject).getIconImageUri(), paramPlayer.getIconImageUri())) || (!zzw.equal(((Player)paramObject).getHiResImageUri(), paramPlayer.getHiResImageUri())) || (!zzw.equal(Long.valueOf(((Player)paramObject).getRetrievedTimestamp()), Long.valueOf(paramPlayer.getRetrievedTimestamp()))) || (!zzw.equal(((Player)paramObject).getTitle(), paramPlayer.getTitle())) || (!zzw.equal(((Player)paramObject).getLevelInfo(), paramPlayer.getLevelInfo())) || (!zzw.equal(((Player)paramObject).zzvE(), paramPlayer.zzvE())) || (!zzw.equal(((Player)paramObject).getName(), paramPlayer.getName())) || (!zzw.equal(((Player)paramObject).getBannerImageLandscapeUri(), paramPlayer.getBannerImageLandscapeUri()))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((Player)paramObject).getBannerImagePortraitUri(), paramPlayer.getBannerImagePortraitUri()));
    return false;
  }
  
  static int zzb(Player paramPlayer)
  {
    return zzw.hashCode(new Object[] { paramPlayer.getPlayerId(), paramPlayer.getDisplayName(), Boolean.valueOf(paramPlayer.zzvF()), paramPlayer.getIconImageUri(), paramPlayer.getHiResImageUri(), Long.valueOf(paramPlayer.getRetrievedTimestamp()), paramPlayer.getTitle(), paramPlayer.getLevelInfo(), paramPlayer.zzvE(), paramPlayer.getName(), paramPlayer.getBannerImageLandscapeUri(), paramPlayer.getBannerImagePortraitUri() });
  }
  
  static String zzc(Player paramPlayer)
  {
    return zzw.zzy(paramPlayer).zzg("PlayerId", paramPlayer.getPlayerId()).zzg("DisplayName", paramPlayer.getDisplayName()).zzg("HasDebugAccess", Boolean.valueOf(paramPlayer.zzvF())).zzg("IconImageUri", paramPlayer.getIconImageUri()).zzg("IconImageUrl", paramPlayer.getIconImageUrl()).zzg("HiResImageUri", paramPlayer.getHiResImageUri()).zzg("HiResImageUrl", paramPlayer.getHiResImageUrl()).zzg("RetrievedTimestamp", Long.valueOf(paramPlayer.getRetrievedTimestamp())).zzg("Title", paramPlayer.getTitle()).zzg("LevelInfo", paramPlayer.getLevelInfo()).zzg("GamerTag", paramPlayer.zzvE()).zzg("Name", paramPlayer.getName()).zzg("BannerImageLandscapeUri", paramPlayer.getBannerImageLandscapeUri()).zzg("BannerImageLandscapeUrl", paramPlayer.getBannerImageLandscapeUrl()).zzg("BannerImagePortraitUri", paramPlayer.getBannerImagePortraitUri()).zzg("BannerImagePortraitUrl", paramPlayer.getBannerImagePortraitUrl()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public Player freeze()
  {
    return this;
  }
  
  public Uri getBannerImageLandscapeUri()
  {
    return this.zzaCW;
  }
  
  public String getBannerImageLandscapeUrl()
  {
    return this.zzaCX;
  }
  
  public Uri getBannerImagePortraitUri()
  {
    return this.zzaCY;
  }
  
  public String getBannerImagePortraitUrl()
  {
    return this.zzaCZ;
  }
  
  public String getDisplayName()
  {
    return this.zzWQ;
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzWQ, paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    return this.zzaCe;
  }
  
  public String getHiResImageUrl()
  {
    return this.zzaCp;
  }
  
  public Uri getIconImageUri()
  {
    return this.zzaCd;
  }
  
  public String getIconImageUrl()
  {
    return this.zzaCo;
  }
  
  public long getLastPlayedWithTimestamp()
  {
    return this.zzaCQ;
  }
  
  public PlayerLevelInfo getLevelInfo()
  {
    return this.zzaCS;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getPlayerId()
  {
    return this.zzacX;
  }
  
  public long getRetrievedTimestamp()
  {
    return this.zzaCO;
  }
  
  public String getTitle()
  {
    return this.zzapg;
  }
  
  public void getTitle(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzapg, paramCharArrayBuffer);
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
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
    return zzb(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return zzc(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    Object localObject2 = null;
    if (!zzqC())
    {
      PlayerEntityCreator.zza(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.zzacX);
    paramParcel.writeString(this.zzWQ);
    if (this.zzaCd == null)
    {
      localObject1 = null;
      paramParcel.writeString((String)localObject1);
      if (this.zzaCe != null) {
        break label82;
      }
    }
    label82:
    for (Object localObject1 = localObject2;; localObject1 = this.zzaCe.toString())
    {
      paramParcel.writeString((String)localObject1);
      paramParcel.writeLong(this.zzaCO);
      return;
      localObject1 = this.zzaCd.toString();
      break;
    }
  }
  
  public String zzvE()
  {
    return this.zzaCV;
  }
  
  public boolean zzvF()
  {
    return this.zzaCU;
  }
  
  public int zzvG()
  {
    return this.zzaCP;
  }
  
  public boolean zzvH()
  {
    return this.zzaCT;
  }
  
  public MostRecentGameInfo zzvI()
  {
    return this.zzaCR;
  }
  
  static final class PlayerEntityCreatorCompat
    extends PlayerEntityCreator
  {
    public PlayerEntity zzeb(Parcel paramParcel)
    {
      if ((PlayerEntity.zzc(PlayerEntity.zzvC())) || (PlayerEntity.zzdG(PlayerEntity.class.getCanonicalName()))) {
        return super.zzeb(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      Object localObject1 = paramParcel.readString();
      Object localObject2 = paramParcel.readString();
      if (localObject1 == null)
      {
        localObject1 = null;
        if (localObject2 != null) {
          break label111;
        }
      }
      label111:
      for (localObject2 = null;; localObject2 = Uri.parse((String)localObject2))
      {
        return new PlayerEntity(13, str1, str2, (Uri)localObject1, (Uri)localObject2, paramParcel.readLong(), -1, -1L, null, null, null, null, null, true, false, paramParcel.readString(), paramParcel.readString(), null, null, null, null);
        localObject1 = Uri.parse((String)localObject1);
        break;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\PlayerEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */