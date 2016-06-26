package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.zzms;

public final class AchievementEntity
  implements SafeParcelable, Achievement
{
  public static final Parcelable.Creator<AchievementEntity> CREATOR = new AchievementEntityCreator();
  private final String mName;
  private final int mState;
  private final int mVersionCode;
  private final String zzaDj;
  private final Uri zzaDk;
  private final String zzaDl;
  private final Uri zzaDm;
  private final String zzaDn;
  private final int zzaDo;
  private final String zzaDp;
  private final PlayerEntity zzaDq;
  private final int zzaDr;
  private final String zzaDs;
  private final long zzaDt;
  private final long zzaDu;
  private final int zzabB;
  private final String zzaxl;
  
  AchievementEntity(int paramInt1, String paramString1, int paramInt2, String paramString2, String paramString3, Uri paramUri1, String paramString4, Uri paramUri2, String paramString5, int paramInt3, String paramString6, PlayerEntity paramPlayerEntity, int paramInt4, int paramInt5, String paramString7, long paramLong1, long paramLong2)
  {
    this.mVersionCode = paramInt1;
    this.zzaDj = paramString1;
    this.zzabB = paramInt2;
    this.mName = paramString2;
    this.zzaxl = paramString3;
    this.zzaDk = paramUri1;
    this.zzaDl = paramString4;
    this.zzaDm = paramUri2;
    this.zzaDn = paramString5;
    this.zzaDo = paramInt3;
    this.zzaDp = paramString6;
    this.zzaDq = paramPlayerEntity;
    this.mState = paramInt4;
    this.zzaDr = paramInt5;
    this.zzaDs = paramString7;
    this.zzaDt = paramLong1;
    this.zzaDu = paramLong2;
  }
  
  public AchievementEntity(Achievement paramAchievement)
  {
    this.mVersionCode = 1;
    this.zzaDj = paramAchievement.getAchievementId();
    this.zzabB = paramAchievement.getType();
    this.mName = paramAchievement.getName();
    this.zzaxl = paramAchievement.getDescription();
    this.zzaDk = paramAchievement.getUnlockedImageUri();
    this.zzaDl = paramAchievement.getUnlockedImageUrl();
    this.zzaDm = paramAchievement.getRevealedImageUri();
    this.zzaDn = paramAchievement.getRevealedImageUrl();
    this.zzaDq = ((PlayerEntity)paramAchievement.getPlayer().freeze());
    this.mState = paramAchievement.getState();
    this.zzaDt = paramAchievement.getLastUpdatedTimestamp();
    this.zzaDu = paramAchievement.getXpValue();
    if (paramAchievement.getType() == 1)
    {
      this.zzaDo = paramAchievement.getTotalSteps();
      this.zzaDp = paramAchievement.getFormattedTotalSteps();
      this.zzaDr = paramAchievement.getCurrentSteps();
    }
    for (this.zzaDs = paramAchievement.getFormattedCurrentSteps();; this.zzaDs = null)
    {
      zzb.zzv(this.zzaDj);
      zzb.zzv(this.zzaxl);
      return;
      this.zzaDo = 0;
      this.zzaDp = null;
      this.zzaDr = 0;
    }
  }
  
  static int zza(Achievement paramAchievement)
  {
    int j;
    int i;
    if (paramAchievement.getType() == 1)
    {
      j = paramAchievement.getCurrentSteps();
      i = paramAchievement.getTotalSteps();
    }
    for (;;)
    {
      return zzw.hashCode(new Object[] { paramAchievement.getAchievementId(), paramAchievement.getName(), Integer.valueOf(paramAchievement.getType()), paramAchievement.getDescription(), Long.valueOf(paramAchievement.getXpValue()), Integer.valueOf(paramAchievement.getState()), Long.valueOf(paramAchievement.getLastUpdatedTimestamp()), paramAchievement.getPlayer(), Integer.valueOf(j), Integer.valueOf(i) });
      i = 0;
      j = 0;
    }
  }
  
  static boolean zza(Achievement paramAchievement, Object paramObject)
  {
    boolean bool3 = true;
    boolean bool2;
    if (!(paramObject instanceof Achievement)) {
      bool2 = false;
    }
    do
    {
      return bool2;
      bool2 = bool3;
    } while (paramAchievement == paramObject);
    paramObject = (Achievement)paramObject;
    boolean bool1;
    if (paramAchievement.getType() == 1)
    {
      bool2 = zzw.equal(Integer.valueOf(((Achievement)paramObject).getCurrentSteps()), Integer.valueOf(paramAchievement.getCurrentSteps()));
      bool1 = zzw.equal(Integer.valueOf(((Achievement)paramObject).getTotalSteps()), Integer.valueOf(paramAchievement.getTotalSteps()));
    }
    for (;;)
    {
      if ((zzw.equal(((Achievement)paramObject).getAchievementId(), paramAchievement.getAchievementId())) && (zzw.equal(((Achievement)paramObject).getName(), paramAchievement.getName())) && (zzw.equal(Integer.valueOf(((Achievement)paramObject).getType()), Integer.valueOf(paramAchievement.getType()))) && (zzw.equal(((Achievement)paramObject).getDescription(), paramAchievement.getDescription())) && (zzw.equal(Long.valueOf(((Achievement)paramObject).getXpValue()), Long.valueOf(paramAchievement.getXpValue()))) && (zzw.equal(Integer.valueOf(((Achievement)paramObject).getState()), Integer.valueOf(paramAchievement.getState()))) && (zzw.equal(Long.valueOf(((Achievement)paramObject).getLastUpdatedTimestamp()), Long.valueOf(paramAchievement.getLastUpdatedTimestamp()))) && (zzw.equal(((Achievement)paramObject).getPlayer(), paramAchievement.getPlayer())) && (bool2))
      {
        bool2 = bool3;
        if (bool1) {
          break;
        }
      }
      return false;
      bool1 = true;
      bool2 = true;
    }
  }
  
  static String zzb(Achievement paramAchievement)
  {
    zzw.zza localzza = zzw.zzy(paramAchievement).zzg("Id", paramAchievement.getAchievementId()).zzg("Type", Integer.valueOf(paramAchievement.getType())).zzg("Name", paramAchievement.getName()).zzg("Description", paramAchievement.getDescription()).zzg("Player", paramAchievement.getPlayer()).zzg("State", Integer.valueOf(paramAchievement.getState()));
    if (paramAchievement.getType() == 1)
    {
      localzza.zzg("CurrentSteps", Integer.valueOf(paramAchievement.getCurrentSteps()));
      localzza.zzg("TotalSteps", Integer.valueOf(paramAchievement.getTotalSteps()));
    }
    return localzza.toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public Achievement freeze()
  {
    return this;
  }
  
  public String getAchievementId()
  {
    return this.zzaDj;
  }
  
  public int getCurrentSteps()
  {
    boolean bool = true;
    if (getType() == 1) {}
    for (;;)
    {
      zzb.zzab(bool);
      return zzvM();
      bool = false;
    }
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzaxl, paramCharArrayBuffer);
  }
  
  public String getFormattedCurrentSteps()
  {
    boolean bool = true;
    if (getType() == 1) {}
    for (;;)
    {
      zzb.zzab(bool);
      return zzvN();
      bool = false;
    }
  }
  
  public void getFormattedCurrentSteps(CharArrayBuffer paramCharArrayBuffer)
  {
    boolean bool = true;
    if (getType() == 1) {}
    for (;;)
    {
      zzb.zzab(bool);
      zzms.zzb(this.zzaDs, paramCharArrayBuffer);
      return;
      bool = false;
    }
  }
  
  public String getFormattedTotalSteps()
  {
    boolean bool = true;
    if (getType() == 1) {}
    for (;;)
    {
      zzb.zzab(bool);
      return zzvL();
      bool = false;
    }
  }
  
  public void getFormattedTotalSteps(CharArrayBuffer paramCharArrayBuffer)
  {
    boolean bool = true;
    if (getType() == 1) {}
    for (;;)
    {
      zzb.zzab(bool);
      zzms.zzb(this.zzaDp, paramCharArrayBuffer);
      return;
      bool = false;
    }
  }
  
  public long getLastUpdatedTimestamp()
  {
    return this.zzaDt;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public void getName(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.mName, paramCharArrayBuffer);
  }
  
  public Player getPlayer()
  {
    return this.zzaDq;
  }
  
  public Uri getRevealedImageUri()
  {
    return this.zzaDm;
  }
  
  public String getRevealedImageUrl()
  {
    return this.zzaDn;
  }
  
  public int getState()
  {
    return this.mState;
  }
  
  public int getTotalSteps()
  {
    boolean bool = true;
    if (getType() == 1) {}
    for (;;)
    {
      zzb.zzab(bool);
      return zzvK();
      bool = false;
    }
  }
  
  public int getType()
  {
    return this.zzabB;
  }
  
  public Uri getUnlockedImageUri()
  {
    return this.zzaDk;
  }
  
  public String getUnlockedImageUrl()
  {
    return this.zzaDl;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public long getXpValue()
  {
    return this.zzaDu;
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
    AchievementEntityCreator.zza(this, paramParcel, paramInt);
  }
  
  public int zzvK()
  {
    return this.zzaDo;
  }
  
  public String zzvL()
  {
    return this.zzaDp;
  }
  
  public int zzvM()
  {
    return this.zzaDr;
  }
  
  public String zzvN()
  {
    return this.zzaDs;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\achievement\AchievementEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */