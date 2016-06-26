package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.zzms;
import java.util.ArrayList;

public final class LeaderboardEntity
  implements Leaderboard
{
  private final String zzWQ;
  private final Uri zzaCd;
  private final String zzaCo;
  private final String zzaIZ;
  private final int zzaJa;
  private final ArrayList<LeaderboardVariantEntity> zzaJb;
  private final Game zzaJc;
  
  public LeaderboardEntity(Leaderboard paramLeaderboard)
  {
    this.zzaIZ = paramLeaderboard.getLeaderboardId();
    this.zzWQ = paramLeaderboard.getDisplayName();
    this.zzaCd = paramLeaderboard.getIconImageUri();
    this.zzaCo = paramLeaderboard.getIconImageUrl();
    this.zzaJa = paramLeaderboard.getScoreOrder();
    Object localObject = paramLeaderboard.getGame();
    if (localObject == null) {}
    for (localObject = null;; localObject = new GameEntity((Game)localObject))
    {
      this.zzaJc = ((Game)localObject);
      paramLeaderboard = paramLeaderboard.getVariants();
      int j = paramLeaderboard.size();
      this.zzaJb = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        this.zzaJb.add((LeaderboardVariantEntity)((LeaderboardVariant)paramLeaderboard.get(i)).freeze());
        i += 1;
      }
    }
  }
  
  static int zza(Leaderboard paramLeaderboard)
  {
    return zzw.hashCode(new Object[] { paramLeaderboard.getLeaderboardId(), paramLeaderboard.getDisplayName(), paramLeaderboard.getIconImageUri(), Integer.valueOf(paramLeaderboard.getScoreOrder()), paramLeaderboard.getVariants() });
  }
  
  static boolean zza(Leaderboard paramLeaderboard, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Leaderboard)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramLeaderboard == paramObject);
      paramObject = (Leaderboard)paramObject;
      if ((!zzw.equal(((Leaderboard)paramObject).getLeaderboardId(), paramLeaderboard.getLeaderboardId())) || (!zzw.equal(((Leaderboard)paramObject).getDisplayName(), paramLeaderboard.getDisplayName())) || (!zzw.equal(((Leaderboard)paramObject).getIconImageUri(), paramLeaderboard.getIconImageUri())) || (!zzw.equal(Integer.valueOf(((Leaderboard)paramObject).getScoreOrder()), Integer.valueOf(paramLeaderboard.getScoreOrder())))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((Leaderboard)paramObject).getVariants(), paramLeaderboard.getVariants()));
    return false;
  }
  
  static String zzb(Leaderboard paramLeaderboard)
  {
    return zzw.zzy(paramLeaderboard).zzg("LeaderboardId", paramLeaderboard.getLeaderboardId()).zzg("DisplayName", paramLeaderboard.getDisplayName()).zzg("IconImageUri", paramLeaderboard.getIconImageUri()).zzg("IconImageUrl", paramLeaderboard.getIconImageUrl()).zzg("ScoreOrder", Integer.valueOf(paramLeaderboard.getScoreOrder())).zzg("Variants", paramLeaderboard.getVariants()).toString();
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public String getDisplayName()
  {
    return this.zzWQ;
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzWQ, paramCharArrayBuffer);
  }
  
  public Game getGame()
  {
    return this.zzaJc;
  }
  
  public Uri getIconImageUri()
  {
    return this.zzaCd;
  }
  
  public String getIconImageUrl()
  {
    return this.zzaCo;
  }
  
  public String getLeaderboardId()
  {
    return this.zzaIZ;
  }
  
  public int getScoreOrder()
  {
    return this.zzaJa;
  }
  
  public ArrayList<LeaderboardVariant> getVariants()
  {
    return new ArrayList(this.zzaJb);
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
  
  public Leaderboard zzxI()
  {
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\leaderboard\LeaderboardEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */