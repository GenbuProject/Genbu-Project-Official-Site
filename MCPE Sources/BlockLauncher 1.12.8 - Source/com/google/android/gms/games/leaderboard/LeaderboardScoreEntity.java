package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.zzms;

public final class LeaderboardScoreEntity
  implements LeaderboardScore
{
  private final long zzaJe;
  private final String zzaJf;
  private final String zzaJg;
  private final long zzaJh;
  private final long zzaJi;
  private final String zzaJj;
  private final Uri zzaJk;
  private final Uri zzaJl;
  private final PlayerEntity zzaJm;
  private final String zzaJn;
  private final String zzaJo;
  private final String zzaJp;
  
  public LeaderboardScoreEntity(LeaderboardScore paramLeaderboardScore)
  {
    this.zzaJe = paramLeaderboardScore.getRank();
    this.zzaJf = ((String)zzx.zzz(paramLeaderboardScore.getDisplayRank()));
    this.zzaJg = ((String)zzx.zzz(paramLeaderboardScore.getDisplayScore()));
    this.zzaJh = paramLeaderboardScore.getRawScore();
    this.zzaJi = paramLeaderboardScore.getTimestampMillis();
    this.zzaJj = paramLeaderboardScore.getScoreHolderDisplayName();
    this.zzaJk = paramLeaderboardScore.getScoreHolderIconImageUri();
    this.zzaJl = paramLeaderboardScore.getScoreHolderHiResImageUri();
    Object localObject = paramLeaderboardScore.getScoreHolder();
    if (localObject == null) {}
    for (localObject = null;; localObject = (PlayerEntity)((Player)localObject).freeze())
    {
      this.zzaJm = ((PlayerEntity)localObject);
      this.zzaJn = paramLeaderboardScore.getScoreTag();
      this.zzaJo = paramLeaderboardScore.getScoreHolderIconImageUrl();
      this.zzaJp = paramLeaderboardScore.getScoreHolderHiResImageUrl();
      return;
    }
  }
  
  static int zza(LeaderboardScore paramLeaderboardScore)
  {
    return zzw.hashCode(new Object[] { Long.valueOf(paramLeaderboardScore.getRank()), paramLeaderboardScore.getDisplayRank(), Long.valueOf(paramLeaderboardScore.getRawScore()), paramLeaderboardScore.getDisplayScore(), Long.valueOf(paramLeaderboardScore.getTimestampMillis()), paramLeaderboardScore.getScoreHolderDisplayName(), paramLeaderboardScore.getScoreHolderIconImageUri(), paramLeaderboardScore.getScoreHolderHiResImageUri(), paramLeaderboardScore.getScoreHolder() });
  }
  
  static boolean zza(LeaderboardScore paramLeaderboardScore, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof LeaderboardScore)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramLeaderboardScore == paramObject);
      paramObject = (LeaderboardScore)paramObject;
      if ((!zzw.equal(Long.valueOf(((LeaderboardScore)paramObject).getRank()), Long.valueOf(paramLeaderboardScore.getRank()))) || (!zzw.equal(((LeaderboardScore)paramObject).getDisplayRank(), paramLeaderboardScore.getDisplayRank())) || (!zzw.equal(Long.valueOf(((LeaderboardScore)paramObject).getRawScore()), Long.valueOf(paramLeaderboardScore.getRawScore()))) || (!zzw.equal(((LeaderboardScore)paramObject).getDisplayScore(), paramLeaderboardScore.getDisplayScore())) || (!zzw.equal(Long.valueOf(((LeaderboardScore)paramObject).getTimestampMillis()), Long.valueOf(paramLeaderboardScore.getTimestampMillis()))) || (!zzw.equal(((LeaderboardScore)paramObject).getScoreHolderDisplayName(), paramLeaderboardScore.getScoreHolderDisplayName())) || (!zzw.equal(((LeaderboardScore)paramObject).getScoreHolderIconImageUri(), paramLeaderboardScore.getScoreHolderIconImageUri())) || (!zzw.equal(((LeaderboardScore)paramObject).getScoreHolderHiResImageUri(), paramLeaderboardScore.getScoreHolderHiResImageUri())) || (!zzw.equal(((LeaderboardScore)paramObject).getScoreHolder(), paramLeaderboardScore.getScoreHolder()))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((LeaderboardScore)paramObject).getScoreTag(), paramLeaderboardScore.getScoreTag()));
    return false;
  }
  
  static String zzb(LeaderboardScore paramLeaderboardScore)
  {
    zzw.zza localzza = zzw.zzy(paramLeaderboardScore).zzg("Rank", Long.valueOf(paramLeaderboardScore.getRank())).zzg("DisplayRank", paramLeaderboardScore.getDisplayRank()).zzg("Score", Long.valueOf(paramLeaderboardScore.getRawScore())).zzg("DisplayScore", paramLeaderboardScore.getDisplayScore()).zzg("Timestamp", Long.valueOf(paramLeaderboardScore.getTimestampMillis())).zzg("DisplayName", paramLeaderboardScore.getScoreHolderDisplayName()).zzg("IconImageUri", paramLeaderboardScore.getScoreHolderIconImageUri()).zzg("IconImageUrl", paramLeaderboardScore.getScoreHolderIconImageUrl()).zzg("HiResImageUri", paramLeaderboardScore.getScoreHolderHiResImageUri()).zzg("HiResImageUrl", paramLeaderboardScore.getScoreHolderHiResImageUrl());
    if (paramLeaderboardScore.getScoreHolder() == null) {}
    for (Object localObject = null;; localObject = paramLeaderboardScore.getScoreHolder()) {
      return localzza.zzg("Player", localObject).zzg("ScoreTag", paramLeaderboardScore.getScoreTag()).toString();
    }
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public String getDisplayRank()
  {
    return this.zzaJf;
  }
  
  public void getDisplayRank(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzaJf, paramCharArrayBuffer);
  }
  
  public String getDisplayScore()
  {
    return this.zzaJg;
  }
  
  public void getDisplayScore(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzaJg, paramCharArrayBuffer);
  }
  
  public long getRank()
  {
    return this.zzaJe;
  }
  
  public long getRawScore()
  {
    return this.zzaJh;
  }
  
  public Player getScoreHolder()
  {
    return this.zzaJm;
  }
  
  public String getScoreHolderDisplayName()
  {
    if (this.zzaJm == null) {
      return this.zzaJj;
    }
    return this.zzaJm.getDisplayName();
  }
  
  public void getScoreHolderDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (this.zzaJm == null)
    {
      zzms.zzb(this.zzaJj, paramCharArrayBuffer);
      return;
    }
    this.zzaJm.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getScoreHolderHiResImageUri()
  {
    if (this.zzaJm == null) {
      return this.zzaJl;
    }
    return this.zzaJm.getHiResImageUri();
  }
  
  public String getScoreHolderHiResImageUrl()
  {
    if (this.zzaJm == null) {
      return this.zzaJp;
    }
    return this.zzaJm.getHiResImageUrl();
  }
  
  public Uri getScoreHolderIconImageUri()
  {
    if (this.zzaJm == null) {
      return this.zzaJk;
    }
    return this.zzaJm.getIconImageUri();
  }
  
  public String getScoreHolderIconImageUrl()
  {
    if (this.zzaJm == null) {
      return this.zzaJo;
    }
    return this.zzaJm.getIconImageUrl();
  }
  
  public String getScoreTag()
  {
    return this.zzaJn;
  }
  
  public long getTimestampMillis()
  {
    return this.zzaJi;
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
  
  public LeaderboardScore zzxK()
  {
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\leaderboard\LeaderboardScoreEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */