package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.internal.constants.LeaderboardCollection;
import com.google.android.gms.games.internal.constants.TimeSpan;

public final class LeaderboardVariantEntity
  implements LeaderboardVariant
{
  private final String zzaJA;
  private final String zzaJB;
  private final String zzaJC;
  private final int zzaJr;
  private final int zzaJs;
  private final boolean zzaJt;
  private final long zzaJu;
  private final String zzaJv;
  private final long zzaJw;
  private final String zzaJx;
  private final String zzaJy;
  private final long zzaJz;
  
  public LeaderboardVariantEntity(LeaderboardVariant paramLeaderboardVariant)
  {
    this.zzaJr = paramLeaderboardVariant.getTimeSpan();
    this.zzaJs = paramLeaderboardVariant.getCollection();
    this.zzaJt = paramLeaderboardVariant.hasPlayerInfo();
    this.zzaJu = paramLeaderboardVariant.getRawPlayerScore();
    this.zzaJv = paramLeaderboardVariant.getDisplayPlayerScore();
    this.zzaJw = paramLeaderboardVariant.getPlayerRank();
    this.zzaJx = paramLeaderboardVariant.getDisplayPlayerRank();
    this.zzaJy = paramLeaderboardVariant.getPlayerScoreTag();
    this.zzaJz = paramLeaderboardVariant.getNumScores();
    this.zzaJA = paramLeaderboardVariant.zzxL();
    this.zzaJB = paramLeaderboardVariant.zzxM();
    this.zzaJC = paramLeaderboardVariant.zzxN();
  }
  
  static int zza(LeaderboardVariant paramLeaderboardVariant)
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(paramLeaderboardVariant.getTimeSpan()), Integer.valueOf(paramLeaderboardVariant.getCollection()), Boolean.valueOf(paramLeaderboardVariant.hasPlayerInfo()), Long.valueOf(paramLeaderboardVariant.getRawPlayerScore()), paramLeaderboardVariant.getDisplayPlayerScore(), Long.valueOf(paramLeaderboardVariant.getPlayerRank()), paramLeaderboardVariant.getDisplayPlayerRank(), Long.valueOf(paramLeaderboardVariant.getNumScores()), paramLeaderboardVariant.zzxL(), paramLeaderboardVariant.zzxN(), paramLeaderboardVariant.zzxM() });
  }
  
  static boolean zza(LeaderboardVariant paramLeaderboardVariant, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof LeaderboardVariant)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramLeaderboardVariant == paramObject);
      paramObject = (LeaderboardVariant)paramObject;
      if ((!zzw.equal(Integer.valueOf(((LeaderboardVariant)paramObject).getTimeSpan()), Integer.valueOf(paramLeaderboardVariant.getTimeSpan()))) || (!zzw.equal(Integer.valueOf(((LeaderboardVariant)paramObject).getCollection()), Integer.valueOf(paramLeaderboardVariant.getCollection()))) || (!zzw.equal(Boolean.valueOf(((LeaderboardVariant)paramObject).hasPlayerInfo()), Boolean.valueOf(paramLeaderboardVariant.hasPlayerInfo()))) || (!zzw.equal(Long.valueOf(((LeaderboardVariant)paramObject).getRawPlayerScore()), Long.valueOf(paramLeaderboardVariant.getRawPlayerScore()))) || (!zzw.equal(((LeaderboardVariant)paramObject).getDisplayPlayerScore(), paramLeaderboardVariant.getDisplayPlayerScore())) || (!zzw.equal(Long.valueOf(((LeaderboardVariant)paramObject).getPlayerRank()), Long.valueOf(paramLeaderboardVariant.getPlayerRank()))) || (!zzw.equal(((LeaderboardVariant)paramObject).getDisplayPlayerRank(), paramLeaderboardVariant.getDisplayPlayerRank())) || (!zzw.equal(Long.valueOf(((LeaderboardVariant)paramObject).getNumScores()), Long.valueOf(paramLeaderboardVariant.getNumScores()))) || (!zzw.equal(((LeaderboardVariant)paramObject).zzxL(), paramLeaderboardVariant.zzxL())) || (!zzw.equal(((LeaderboardVariant)paramObject).zzxN(), paramLeaderboardVariant.zzxN()))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((LeaderboardVariant)paramObject).zzxM(), paramLeaderboardVariant.zzxM()));
    return false;
  }
  
  static String zzb(LeaderboardVariant paramLeaderboardVariant)
  {
    zzw.zza localzza = zzw.zzy(paramLeaderboardVariant).zzg("TimeSpan", TimeSpan.zzgw(paramLeaderboardVariant.getTimeSpan())).zzg("Collection", LeaderboardCollection.zzgw(paramLeaderboardVariant.getCollection()));
    if (paramLeaderboardVariant.hasPlayerInfo())
    {
      localObject = Long.valueOf(paramLeaderboardVariant.getRawPlayerScore());
      localzza = localzza.zzg("RawPlayerScore", localObject);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label191;
      }
      localObject = paramLeaderboardVariant.getDisplayPlayerScore();
      label76:
      localzza = localzza.zzg("DisplayPlayerScore", localObject);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label197;
      }
      localObject = Long.valueOf(paramLeaderboardVariant.getPlayerRank());
      label103:
      localzza = localzza.zzg("PlayerRank", localObject);
      if (!paramLeaderboardVariant.hasPlayerInfo()) {
        break label203;
      }
    }
    label191:
    label197:
    label203:
    for (Object localObject = paramLeaderboardVariant.getDisplayPlayerRank();; localObject = "none")
    {
      return localzza.zzg("DisplayPlayerRank", localObject).zzg("NumScores", Long.valueOf(paramLeaderboardVariant.getNumScores())).zzg("TopPageNextToken", paramLeaderboardVariant.zzxL()).zzg("WindowPageNextToken", paramLeaderboardVariant.zzxN()).zzg("WindowPagePrevToken", paramLeaderboardVariant.zzxM()).toString();
      localObject = "none";
      break;
      localObject = "none";
      break label76;
      localObject = "none";
      break label103;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public int getCollection()
  {
    return this.zzaJs;
  }
  
  public String getDisplayPlayerRank()
  {
    return this.zzaJx;
  }
  
  public String getDisplayPlayerScore()
  {
    return this.zzaJv;
  }
  
  public long getNumScores()
  {
    return this.zzaJz;
  }
  
  public long getPlayerRank()
  {
    return this.zzaJw;
  }
  
  public String getPlayerScoreTag()
  {
    return this.zzaJy;
  }
  
  public long getRawPlayerScore()
  {
    return this.zzaJu;
  }
  
  public int getTimeSpan()
  {
    return this.zzaJr;
  }
  
  public boolean hasPlayerInfo()
  {
    return this.zzaJt;
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
  
  public String zzxL()
  {
    return this.zzaJA;
  }
  
  public String zzxM()
  {
    return this.zzaJB;
  }
  
  public String zzxN()
  {
    return this.zzaJC;
  }
  
  public LeaderboardVariant zzxO()
  {
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\leaderboard\LeaderboardVariantEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */