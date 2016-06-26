package com.google.android.gms.games.quest;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.zzms;
import java.util.ArrayList;
import java.util.List;

public final class QuestEntity
  implements SafeParcelable, Quest
{
  public static final Parcelable.Creator<QuestEntity> CREATOR = new QuestEntityCreator();
  private final String mName;
  private final int mState;
  private final int mVersionCode;
  private final long zzaDt;
  private final GameEntity zzaJE;
  private final long zzaKA;
  private final Uri zzaKB;
  private final String zzaKC;
  private final long zzaKD;
  private final long zzaKE;
  private final ArrayList<MilestoneEntity> zzaKF;
  private final String zzaKw;
  private final long zzaKx;
  private final Uri zzaKy;
  private final String zzaKz;
  private final int zzabB;
  private final String zzaxl;
  
  QuestEntity(int paramInt1, GameEntity paramGameEntity, String paramString1, long paramLong1, Uri paramUri1, String paramString2, String paramString3, long paramLong2, long paramLong3, Uri paramUri2, String paramString4, String paramString5, long paramLong4, long paramLong5, int paramInt2, int paramInt3, ArrayList<MilestoneEntity> paramArrayList)
  {
    this.mVersionCode = paramInt1;
    this.zzaJE = paramGameEntity;
    this.zzaKw = paramString1;
    this.zzaKx = paramLong1;
    this.zzaKy = paramUri1;
    this.zzaKz = paramString2;
    this.zzaxl = paramString3;
    this.zzaKA = paramLong2;
    this.zzaDt = paramLong3;
    this.zzaKB = paramUri2;
    this.zzaKC = paramString4;
    this.mName = paramString5;
    this.zzaKD = paramLong4;
    this.zzaKE = paramLong5;
    this.mState = paramInt2;
    this.zzabB = paramInt3;
    this.zzaKF = paramArrayList;
  }
  
  public QuestEntity(Quest paramQuest)
  {
    this.mVersionCode = 2;
    this.zzaJE = new GameEntity(paramQuest.getGame());
    this.zzaKw = paramQuest.getQuestId();
    this.zzaKx = paramQuest.getAcceptedTimestamp();
    this.zzaxl = paramQuest.getDescription();
    this.zzaKy = paramQuest.getBannerImageUri();
    this.zzaKz = paramQuest.getBannerImageUrl();
    this.zzaKA = paramQuest.getEndTimestamp();
    this.zzaKB = paramQuest.getIconImageUri();
    this.zzaKC = paramQuest.getIconImageUrl();
    this.zzaDt = paramQuest.getLastUpdatedTimestamp();
    this.mName = paramQuest.getName();
    this.zzaKD = paramQuest.zzxS();
    this.zzaKE = paramQuest.getStartTimestamp();
    this.mState = paramQuest.getState();
    this.zzabB = paramQuest.getType();
    paramQuest = paramQuest.zzxR();
    int j = paramQuest.size();
    this.zzaKF = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      this.zzaKF.add((MilestoneEntity)((Milestone)paramQuest.get(i)).freeze());
      i += 1;
    }
  }
  
  static int zza(Quest paramQuest)
  {
    return zzw.hashCode(new Object[] { paramQuest.getGame(), paramQuest.getQuestId(), Long.valueOf(paramQuest.getAcceptedTimestamp()), paramQuest.getBannerImageUri(), paramQuest.getDescription(), Long.valueOf(paramQuest.getEndTimestamp()), paramQuest.getIconImageUri(), Long.valueOf(paramQuest.getLastUpdatedTimestamp()), paramQuest.zzxR(), paramQuest.getName(), Long.valueOf(paramQuest.zzxS()), Long.valueOf(paramQuest.getStartTimestamp()), Integer.valueOf(paramQuest.getState()) });
  }
  
  static boolean zza(Quest paramQuest, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Quest)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramQuest == paramObject);
      paramObject = (Quest)paramObject;
      if ((!zzw.equal(((Quest)paramObject).getGame(), paramQuest.getGame())) || (!zzw.equal(((Quest)paramObject).getQuestId(), paramQuest.getQuestId())) || (!zzw.equal(Long.valueOf(((Quest)paramObject).getAcceptedTimestamp()), Long.valueOf(paramQuest.getAcceptedTimestamp()))) || (!zzw.equal(((Quest)paramObject).getBannerImageUri(), paramQuest.getBannerImageUri())) || (!zzw.equal(((Quest)paramObject).getDescription(), paramQuest.getDescription())) || (!zzw.equal(Long.valueOf(((Quest)paramObject).getEndTimestamp()), Long.valueOf(paramQuest.getEndTimestamp()))) || (!zzw.equal(((Quest)paramObject).getIconImageUri(), paramQuest.getIconImageUri())) || (!zzw.equal(Long.valueOf(((Quest)paramObject).getLastUpdatedTimestamp()), Long.valueOf(paramQuest.getLastUpdatedTimestamp()))) || (!zzw.equal(((Quest)paramObject).zzxR(), paramQuest.zzxR())) || (!zzw.equal(((Quest)paramObject).getName(), paramQuest.getName())) || (!zzw.equal(Long.valueOf(((Quest)paramObject).zzxS()), Long.valueOf(paramQuest.zzxS()))) || (!zzw.equal(Long.valueOf(((Quest)paramObject).getStartTimestamp()), Long.valueOf(paramQuest.getStartTimestamp())))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(Integer.valueOf(((Quest)paramObject).getState()), Integer.valueOf(paramQuest.getState())));
    return false;
  }
  
  static String zzb(Quest paramQuest)
  {
    return zzw.zzy(paramQuest).zzg("Game", paramQuest.getGame()).zzg("QuestId", paramQuest.getQuestId()).zzg("AcceptedTimestamp", Long.valueOf(paramQuest.getAcceptedTimestamp())).zzg("BannerImageUri", paramQuest.getBannerImageUri()).zzg("BannerImageUrl", paramQuest.getBannerImageUrl()).zzg("Description", paramQuest.getDescription()).zzg("EndTimestamp", Long.valueOf(paramQuest.getEndTimestamp())).zzg("IconImageUri", paramQuest.getIconImageUri()).zzg("IconImageUrl", paramQuest.getIconImageUrl()).zzg("LastUpdatedTimestamp", Long.valueOf(paramQuest.getLastUpdatedTimestamp())).zzg("Milestones", paramQuest.zzxR()).zzg("Name", paramQuest.getName()).zzg("NotifyTimestamp", Long.valueOf(paramQuest.zzxS())).zzg("StartTimestamp", Long.valueOf(paramQuest.getStartTimestamp())).zzg("State", Integer.valueOf(paramQuest.getState())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public Quest freeze()
  {
    return this;
  }
  
  public long getAcceptedTimestamp()
  {
    return this.zzaKx;
  }
  
  public Uri getBannerImageUri()
  {
    return this.zzaKy;
  }
  
  public String getBannerImageUrl()
  {
    return this.zzaKz;
  }
  
  public Milestone getCurrentMilestone()
  {
    return (Milestone)zzxR().get(0);
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzaxl, paramCharArrayBuffer);
  }
  
  public long getEndTimestamp()
  {
    return this.zzaKA;
  }
  
  public Game getGame()
  {
    return this.zzaJE;
  }
  
  public Uri getIconImageUri()
  {
    return this.zzaKB;
  }
  
  public String getIconImageUrl()
  {
    return this.zzaKC;
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
  
  public String getQuestId()
  {
    return this.zzaKw;
  }
  
  public long getStartTimestamp()
  {
    return this.zzaKE;
  }
  
  public int getState()
  {
    return this.mState;
  }
  
  public int getType()
  {
    return this.zzabB;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zza(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public boolean isEndingSoon()
  {
    return this.zzaKD <= System.currentTimeMillis() + 1800000L;
  }
  
  public String toString()
  {
    return zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    QuestEntityCreator.zza(this, paramParcel, paramInt);
  }
  
  public List<Milestone> zzxR()
  {
    return new ArrayList(this.zzaKF);
  }
  
  public long zzxS()
  {
    return this.zzaKD;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\quest\QuestEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */