package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class LeaderboardScoreRef
  extends zzc
  implements LeaderboardScore
{
  private final PlayerRef zzaJq;
  
  LeaderboardScoreRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
    this.zzaJq = new PlayerRef(paramDataHolder, paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    return LeaderboardScoreEntity.zza(this, paramObject);
  }
  
  public String getDisplayRank()
  {
    return getString("display_rank");
  }
  
  public void getDisplayRank(CharArrayBuffer paramCharArrayBuffer)
  {
    zza("display_rank", paramCharArrayBuffer);
  }
  
  public String getDisplayScore()
  {
    return getString("display_score");
  }
  
  public void getDisplayScore(CharArrayBuffer paramCharArrayBuffer)
  {
    zza("display_score", paramCharArrayBuffer);
  }
  
  public long getRank()
  {
    return getLong("rank");
  }
  
  public long getRawScore()
  {
    return getLong("raw_score");
  }
  
  public Player getScoreHolder()
  {
    if (zzcB("external_player_id")) {
      return null;
    }
    return this.zzaJq;
  }
  
  public String getScoreHolderDisplayName()
  {
    if (zzcB("external_player_id")) {
      return getString("default_display_name");
    }
    return this.zzaJq.getDisplayName();
  }
  
  public void getScoreHolderDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (zzcB("external_player_id"))
    {
      zza("default_display_name", paramCharArrayBuffer);
      return;
    }
    this.zzaJq.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getScoreHolderHiResImageUri()
  {
    if (zzcB("external_player_id")) {
      return null;
    }
    return this.zzaJq.getHiResImageUri();
  }
  
  public String getScoreHolderHiResImageUrl()
  {
    if (zzcB("external_player_id")) {
      return null;
    }
    return this.zzaJq.getHiResImageUrl();
  }
  
  public Uri getScoreHolderIconImageUri()
  {
    if (zzcB("external_player_id")) {
      return zzcA("default_display_image_uri");
    }
    return this.zzaJq.getIconImageUri();
  }
  
  public String getScoreHolderIconImageUrl()
  {
    if (zzcB("external_player_id")) {
      return getString("default_display_image_url");
    }
    return this.zzaJq.getIconImageUrl();
  }
  
  public String getScoreTag()
  {
    return getString("score_tag");
  }
  
  public long getTimestampMillis()
  {
    return getLong("achieved_timestamp");
  }
  
  public int hashCode()
  {
    return LeaderboardScoreEntity.zza(this);
  }
  
  public String toString()
  {
    return LeaderboardScoreEntity.zzb(this);
  }
  
  public LeaderboardScore zzxK()
  {
    return new LeaderboardScoreEntity(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\leaderboard\LeaderboardScoreRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */