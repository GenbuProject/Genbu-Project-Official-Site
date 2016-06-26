package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.zzms;

public final class GameEntity
  extends GamesDowngradeableSafeParcel
  implements Game
{
  public static final Parcelable.Creator<GameEntity> CREATOR = new GameEntityCreatorCompat();
  private final int mVersionCode;
  private final boolean zzDZ;
  private final String zzWQ;
  private final String zzZC;
  private final String zzaCa;
  private final String zzaCb;
  private final String zzaCc;
  private final Uri zzaCd;
  private final Uri zzaCe;
  private final Uri zzaCf;
  private final boolean zzaCg;
  private final boolean zzaCh;
  private final String zzaCi;
  private final int zzaCj;
  private final int zzaCk;
  private final int zzaCl;
  private final boolean zzaCm;
  private final boolean zzaCn;
  private final String zzaCo;
  private final String zzaCp;
  private final String zzaCq;
  private final boolean zzaCr;
  private final boolean zzaCs;
  private final String zzaCt;
  private final boolean zzaCu;
  private final String zzaxl;
  
  GameEntity(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, Uri paramUri1, Uri paramUri2, Uri paramUri3, boolean paramBoolean1, boolean paramBoolean2, String paramString7, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean3, boolean paramBoolean4, String paramString8, String paramString9, String paramString10, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7, String paramString11, boolean paramBoolean8)
  {
    this.mVersionCode = paramInt1;
    this.zzZC = paramString1;
    this.zzWQ = paramString2;
    this.zzaCa = paramString3;
    this.zzaCb = paramString4;
    this.zzaxl = paramString5;
    this.zzaCc = paramString6;
    this.zzaCd = paramUri1;
    this.zzaCo = paramString8;
    this.zzaCe = paramUri2;
    this.zzaCp = paramString9;
    this.zzaCf = paramUri3;
    this.zzaCq = paramString10;
    this.zzaCg = paramBoolean1;
    this.zzaCh = paramBoolean2;
    this.zzaCi = paramString7;
    this.zzaCj = paramInt2;
    this.zzaCk = paramInt3;
    this.zzaCl = paramInt4;
    this.zzaCm = paramBoolean3;
    this.zzaCn = paramBoolean4;
    this.zzDZ = paramBoolean5;
    this.zzaCr = paramBoolean6;
    this.zzaCs = paramBoolean7;
    this.zzaCt = paramString11;
    this.zzaCu = paramBoolean8;
  }
  
  public GameEntity(Game paramGame)
  {
    this.mVersionCode = 7;
    this.zzZC = paramGame.getApplicationId();
    this.zzaCa = paramGame.getPrimaryCategory();
    this.zzaCb = paramGame.getSecondaryCategory();
    this.zzaxl = paramGame.getDescription();
    this.zzaCc = paramGame.getDeveloperName();
    this.zzWQ = paramGame.getDisplayName();
    this.zzaCd = paramGame.getIconImageUri();
    this.zzaCo = paramGame.getIconImageUrl();
    this.zzaCe = paramGame.getHiResImageUri();
    this.zzaCp = paramGame.getHiResImageUrl();
    this.zzaCf = paramGame.getFeaturedImageUri();
    this.zzaCq = paramGame.getFeaturedImageUrl();
    this.zzaCg = paramGame.zzvx();
    this.zzaCh = paramGame.zzvz();
    this.zzaCi = paramGame.zzvA();
    this.zzaCj = paramGame.zzvB();
    this.zzaCk = paramGame.getAchievementTotalCount();
    this.zzaCl = paramGame.getLeaderboardCount();
    this.zzaCm = paramGame.isRealTimeMultiplayerEnabled();
    this.zzaCn = paramGame.isTurnBasedMultiplayerEnabled();
    this.zzDZ = paramGame.isMuted();
    this.zzaCr = paramGame.zzvy();
    this.zzaCs = paramGame.areSnapshotsEnabled();
    this.zzaCt = paramGame.getThemeColor();
    this.zzaCu = paramGame.hasGamepadSupport();
  }
  
  static int zza(Game paramGame)
  {
    return zzw.hashCode(new Object[] { paramGame.getApplicationId(), paramGame.getDisplayName(), paramGame.getPrimaryCategory(), paramGame.getSecondaryCategory(), paramGame.getDescription(), paramGame.getDeveloperName(), paramGame.getIconImageUri(), paramGame.getHiResImageUri(), paramGame.getFeaturedImageUri(), Boolean.valueOf(paramGame.zzvx()), Boolean.valueOf(paramGame.zzvz()), paramGame.zzvA(), Integer.valueOf(paramGame.zzvB()), Integer.valueOf(paramGame.getAchievementTotalCount()), Integer.valueOf(paramGame.getLeaderboardCount()), Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled()), Boolean.valueOf(paramGame.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(paramGame.isMuted()), Boolean.valueOf(paramGame.zzvy()), Boolean.valueOf(paramGame.areSnapshotsEnabled()), paramGame.getThemeColor(), Boolean.valueOf(paramGame.hasGamepadSupport()) });
  }
  
  static boolean zza(Game paramGame, Object paramObject)
  {
    boolean bool2 = true;
    if (!(paramObject instanceof Game)) {
      bool1 = false;
    }
    do
    {
      return bool1;
      bool1 = bool2;
    } while (paramGame == paramObject);
    paramObject = (Game)paramObject;
    boolean bool3;
    if ((zzw.equal(((Game)paramObject).getApplicationId(), paramGame.getApplicationId())) && (zzw.equal(((Game)paramObject).getDisplayName(), paramGame.getDisplayName())) && (zzw.equal(((Game)paramObject).getPrimaryCategory(), paramGame.getPrimaryCategory())) && (zzw.equal(((Game)paramObject).getSecondaryCategory(), paramGame.getSecondaryCategory())) && (zzw.equal(((Game)paramObject).getDescription(), paramGame.getDescription())) && (zzw.equal(((Game)paramObject).getDeveloperName(), paramGame.getDeveloperName())) && (zzw.equal(((Game)paramObject).getIconImageUri(), paramGame.getIconImageUri())) && (zzw.equal(((Game)paramObject).getHiResImageUri(), paramGame.getHiResImageUri())) && (zzw.equal(((Game)paramObject).getFeaturedImageUri(), paramGame.getFeaturedImageUri())) && (zzw.equal(Boolean.valueOf(((Game)paramObject).zzvx()), Boolean.valueOf(paramGame.zzvx()))) && (zzw.equal(Boolean.valueOf(((Game)paramObject).zzvz()), Boolean.valueOf(paramGame.zzvz()))) && (zzw.equal(((Game)paramObject).zzvA(), paramGame.zzvA())) && (zzw.equal(Integer.valueOf(((Game)paramObject).zzvB()), Integer.valueOf(paramGame.zzvB()))) && (zzw.equal(Integer.valueOf(((Game)paramObject).getAchievementTotalCount()), Integer.valueOf(paramGame.getAchievementTotalCount()))) && (zzw.equal(Integer.valueOf(((Game)paramObject).getLeaderboardCount()), Integer.valueOf(paramGame.getLeaderboardCount()))) && (zzw.equal(Boolean.valueOf(((Game)paramObject).isRealTimeMultiplayerEnabled()), Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled()))))
    {
      bool3 = ((Game)paramObject).isTurnBasedMultiplayerEnabled();
      if ((!paramGame.isTurnBasedMultiplayerEnabled()) || (!zzw.equal(Boolean.valueOf(((Game)paramObject).isMuted()), Boolean.valueOf(paramGame.isMuted()))) || (!zzw.equal(Boolean.valueOf(((Game)paramObject).zzvy()), Boolean.valueOf(paramGame.zzvy())))) {
        break label501;
      }
    }
    label501:
    for (boolean bool1 = true;; bool1 = false)
    {
      if ((zzw.equal(Boolean.valueOf(bool3), Boolean.valueOf(bool1))) && (zzw.equal(Boolean.valueOf(((Game)paramObject).areSnapshotsEnabled()), Boolean.valueOf(paramGame.areSnapshotsEnabled()))) && (zzw.equal(((Game)paramObject).getThemeColor(), paramGame.getThemeColor())))
      {
        bool1 = bool2;
        if (zzw.equal(Boolean.valueOf(((Game)paramObject).hasGamepadSupport()), Boolean.valueOf(paramGame.hasGamepadSupport()))) {
          break;
        }
      }
      return false;
    }
  }
  
  static String zzb(Game paramGame)
  {
    return zzw.zzy(paramGame).zzg("ApplicationId", paramGame.getApplicationId()).zzg("DisplayName", paramGame.getDisplayName()).zzg("PrimaryCategory", paramGame.getPrimaryCategory()).zzg("SecondaryCategory", paramGame.getSecondaryCategory()).zzg("Description", paramGame.getDescription()).zzg("DeveloperName", paramGame.getDeveloperName()).zzg("IconImageUri", paramGame.getIconImageUri()).zzg("IconImageUrl", paramGame.getIconImageUrl()).zzg("HiResImageUri", paramGame.getHiResImageUri()).zzg("HiResImageUrl", paramGame.getHiResImageUrl()).zzg("FeaturedImageUri", paramGame.getFeaturedImageUri()).zzg("FeaturedImageUrl", paramGame.getFeaturedImageUrl()).zzg("PlayEnabledGame", Boolean.valueOf(paramGame.zzvx())).zzg("InstanceInstalled", Boolean.valueOf(paramGame.zzvz())).zzg("InstancePackageName", paramGame.zzvA()).zzg("AchievementTotalCount", Integer.valueOf(paramGame.getAchievementTotalCount())).zzg("LeaderboardCount", Integer.valueOf(paramGame.getLeaderboardCount())).zzg("RealTimeMultiplayerEnabled", Boolean.valueOf(paramGame.isRealTimeMultiplayerEnabled())).zzg("TurnBasedMultiplayerEnabled", Boolean.valueOf(paramGame.isTurnBasedMultiplayerEnabled())).zzg("AreSnapshotsEnabled", Boolean.valueOf(paramGame.areSnapshotsEnabled())).zzg("ThemeColor", paramGame.getThemeColor()).zzg("HasGamepadSupport", Boolean.valueOf(paramGame.hasGamepadSupport())).toString();
  }
  
  public boolean areSnapshotsEnabled()
  {
    return this.zzaCs;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public Game freeze()
  {
    return this;
  }
  
  public int getAchievementTotalCount()
  {
    return this.zzaCk;
  }
  
  public String getApplicationId()
  {
    return this.zzZC;
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzaxl, paramCharArrayBuffer);
  }
  
  public String getDeveloperName()
  {
    return this.zzaCc;
  }
  
  public void getDeveloperName(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzaCc, paramCharArrayBuffer);
  }
  
  public String getDisplayName()
  {
    return this.zzWQ;
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzWQ, paramCharArrayBuffer);
  }
  
  public Uri getFeaturedImageUri()
  {
    return this.zzaCf;
  }
  
  public String getFeaturedImageUrl()
  {
    return this.zzaCq;
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
  
  public int getLeaderboardCount()
  {
    return this.zzaCl;
  }
  
  public String getPrimaryCategory()
  {
    return this.zzaCa;
  }
  
  public String getSecondaryCategory()
  {
    return this.zzaCb;
  }
  
  public String getThemeColor()
  {
    return this.zzaCt;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public boolean hasGamepadSupport()
  {
    return this.zzaCu;
  }
  
  public int hashCode()
  {
    return zza(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public boolean isMuted()
  {
    return this.zzDZ;
  }
  
  public boolean isRealTimeMultiplayerEnabled()
  {
    return this.zzaCm;
  }
  
  public boolean isTurnBasedMultiplayerEnabled()
  {
    return this.zzaCn;
  }
  
  public String toString()
  {
    return zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    Object localObject2 = null;
    if (!zzqC())
    {
      GameEntityCreator.zza(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.zzZC);
    paramParcel.writeString(this.zzWQ);
    paramParcel.writeString(this.zzaCa);
    paramParcel.writeString(this.zzaCb);
    paramParcel.writeString(this.zzaxl);
    paramParcel.writeString(this.zzaCc);
    Object localObject1;
    if (this.zzaCd == null)
    {
      localObject1 = null;
      paramParcel.writeString((String)localObject1);
      if (this.zzaCe != null) {
        break label189;
      }
      localObject1 = null;
      label93:
      paramParcel.writeString((String)localObject1);
      if (this.zzaCf != null) {
        break label201;
      }
      localObject1 = localObject2;
      label110:
      paramParcel.writeString((String)localObject1);
      if (!this.zzaCg) {
        break label213;
      }
      paramInt = 1;
      label125:
      paramParcel.writeInt(paramInt);
      if (!this.zzaCh) {
        break label218;
      }
    }
    label189:
    label201:
    label213:
    label218:
    for (paramInt = i;; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      paramParcel.writeString(this.zzaCi);
      paramParcel.writeInt(this.zzaCj);
      paramParcel.writeInt(this.zzaCk);
      paramParcel.writeInt(this.zzaCl);
      return;
      localObject1 = this.zzaCd.toString();
      break;
      localObject1 = this.zzaCe.toString();
      break label93;
      localObject1 = this.zzaCf.toString();
      break label110;
      paramInt = 0;
      break label125;
    }
  }
  
  public String zzvA()
  {
    return this.zzaCi;
  }
  
  public int zzvB()
  {
    return this.zzaCj;
  }
  
  public boolean zzvx()
  {
    return this.zzaCg;
  }
  
  public boolean zzvy()
  {
    return this.zzaCr;
  }
  
  public boolean zzvz()
  {
    return this.zzaCh;
  }
  
  static final class GameEntityCreatorCompat
    extends GameEntityCreator
  {
    public GameEntity zzea(Parcel paramParcel)
    {
      if ((GameEntity.zzc(GameEntity.zzvC())) || (GameEntity.zzdG(GameEntity.class.getCanonicalName()))) {
        return super.zzea(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      String str3 = paramParcel.readString();
      String str4 = paramParcel.readString();
      String str5 = paramParcel.readString();
      String str6 = paramParcel.readString();
      Object localObject1 = paramParcel.readString();
      Object localObject2;
      label90:
      Object localObject3;
      label104:
      boolean bool1;
      if (localObject1 == null)
      {
        localObject1 = null;
        localObject2 = paramParcel.readString();
        if (localObject2 != null) {
          break label188;
        }
        localObject2 = null;
        localObject3 = paramParcel.readString();
        if (localObject3 != null) {
          break label198;
        }
        localObject3 = null;
        if (paramParcel.readInt() <= 0) {
          break label208;
        }
        bool1 = true;
        label113:
        if (paramParcel.readInt() <= 0) {
          break label213;
        }
      }
      label188:
      label198:
      label208:
      label213:
      for (boolean bool2 = true;; bool2 = false)
      {
        return new GameEntity(7, str1, str2, str3, str4, str5, str6, (Uri)localObject1, (Uri)localObject2, (Uri)localObject3, bool1, bool2, paramParcel.readString(), paramParcel.readInt(), paramParcel.readInt(), paramParcel.readInt(), false, false, null, null, null, false, false, false, null, false);
        localObject1 = Uri.parse((String)localObject1);
        break;
        localObject2 = Uri.parse((String)localObject2);
        break label90;
        localObject3 = Uri.parse((String)localObject3);
        break label104;
        bool1 = false;
        break label113;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\GameEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */