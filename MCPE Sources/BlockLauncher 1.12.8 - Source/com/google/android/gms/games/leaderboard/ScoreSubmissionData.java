package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.games.internal.constants.TimeSpan;
import java.util.HashMap;

public final class ScoreSubmissionData
{
  private static final String[] zzaIX = { "leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag" };
  private String zzaIZ;
  private HashMap<Integer, Result> zzaJD;
  private String zzacX;
  private int zzade;
  
  public ScoreSubmissionData(DataHolder paramDataHolder)
  {
    this.zzade = paramDataHolder.getStatusCode();
    this.zzaJD = new HashMap();
    int j = paramDataHolder.getCount();
    if (j == 3) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzac(bool);
      int i = 0;
      while (i < j)
      {
        int k = paramDataHolder.zzbH(i);
        if (i == 0)
        {
          this.zzaIZ = paramDataHolder.zzd("leaderboardId", i, k);
          this.zzacX = paramDataHolder.zzd("playerId", i, k);
        }
        if (paramDataHolder.zze("hasResult", i, k)) {
          zza(new Result(paramDataHolder.zzb("rawScore", i, k), paramDataHolder.zzd("formattedScore", i, k), paramDataHolder.zzd("scoreTag", i, k), paramDataHolder.zze("newBest", i, k)), paramDataHolder.zzc("timeSpan", i, k));
        }
        i += 1;
      }
    }
  }
  
  private void zza(Result paramResult, int paramInt)
  {
    this.zzaJD.put(Integer.valueOf(paramInt), paramResult);
  }
  
  public String getLeaderboardId()
  {
    return this.zzaIZ;
  }
  
  public String getPlayerId()
  {
    return this.zzacX;
  }
  
  public Result getScoreResult(int paramInt)
  {
    return (Result)this.zzaJD.get(Integer.valueOf(paramInt));
  }
  
  public String toString()
  {
    zzw.zza localzza = zzw.zzy(this).zzg("PlayerId", this.zzacX).zzg("StatusCode", Integer.valueOf(this.zzade));
    int i = 0;
    if (i < 3)
    {
      Object localObject = (Result)this.zzaJD.get(Integer.valueOf(i));
      localzza.zzg("TimesSpan", TimeSpan.zzgw(i));
      if (localObject == null) {}
      for (localObject = "null";; localObject = ((Result)localObject).toString())
      {
        localzza.zzg("Result", localObject);
        i += 1;
        break;
      }
    }
    return localzza.toString();
  }
  
  public static final class Result
  {
    public final String formattedScore;
    public final boolean newBest;
    public final long rawScore;
    public final String scoreTag;
    
    public Result(long paramLong, String paramString1, String paramString2, boolean paramBoolean)
    {
      this.rawScore = paramLong;
      this.formattedScore = paramString1;
      this.scoreTag = paramString2;
      this.newBest = paramBoolean;
    }
    
    public String toString()
    {
      return zzw.zzy(this).zzg("RawScore", Long.valueOf(this.rawScore)).zzg("FormattedScore", this.formattedScore).zzg("ScoreTag", this.scoreTag).zzg("NewBest", Boolean.valueOf(this.newBest)).toString();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\leaderboard\ScoreSubmissionData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */