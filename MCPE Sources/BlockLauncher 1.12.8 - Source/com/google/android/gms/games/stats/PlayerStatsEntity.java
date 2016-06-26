package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public class PlayerStatsEntity
  implements SafeParcelable, PlayerStats
{
  public static final Parcelable.Creator<PlayerStatsEntity> CREATOR = new PlayerStatsEntityCreator();
  private final int mVersionCode;
  private final float zzaLd;
  private final float zzaLe;
  private final int zzaLf;
  private final int zzaLg;
  private final int zzaLh;
  private final float zzaLi;
  private final float zzaLj;
  private final Bundle zzaLk;
  private final float zzaLl;
  
  PlayerStatsEntity(int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, int paramInt3, int paramInt4, float paramFloat3, float paramFloat4, Bundle paramBundle, float paramFloat5)
  {
    this.mVersionCode = paramInt1;
    this.zzaLd = paramFloat1;
    this.zzaLe = paramFloat2;
    this.zzaLf = paramInt2;
    this.zzaLg = paramInt3;
    this.zzaLh = paramInt4;
    this.zzaLi = paramFloat3;
    this.zzaLj = paramFloat4;
    this.zzaLk = paramBundle;
    this.zzaLl = paramFloat5;
  }
  
  public PlayerStatsEntity(PlayerStats paramPlayerStats)
  {
    this.mVersionCode = 3;
    this.zzaLd = paramPlayerStats.getAverageSessionLength();
    this.zzaLe = paramPlayerStats.getChurnProbability();
    this.zzaLf = paramPlayerStats.getDaysSinceLastPlayed();
    this.zzaLg = paramPlayerStats.getNumberOfPurchases();
    this.zzaLh = paramPlayerStats.getNumberOfSessions();
    this.zzaLi = paramPlayerStats.getSessionPercentile();
    this.zzaLj = paramPlayerStats.getSpendPercentile();
    this.zzaLl = paramPlayerStats.getSpendProbability();
    this.zzaLk = paramPlayerStats.zzxV();
  }
  
  static int zza(PlayerStats paramPlayerStats)
  {
    return zzw.hashCode(new Object[] { Float.valueOf(paramPlayerStats.getAverageSessionLength()), Float.valueOf(paramPlayerStats.getChurnProbability()), Integer.valueOf(paramPlayerStats.getDaysSinceLastPlayed()), Integer.valueOf(paramPlayerStats.getNumberOfPurchases()), Integer.valueOf(paramPlayerStats.getNumberOfSessions()), Float.valueOf(paramPlayerStats.getSessionPercentile()), Float.valueOf(paramPlayerStats.getSpendPercentile()), Float.valueOf(paramPlayerStats.getSpendProbability()) });
  }
  
  static boolean zza(PlayerStats paramPlayerStats, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof PlayerStats)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramPlayerStats == paramObject);
      paramObject = (PlayerStats)paramObject;
      if ((!zzw.equal(Float.valueOf(((PlayerStats)paramObject).getAverageSessionLength()), Float.valueOf(paramPlayerStats.getAverageSessionLength()))) || (!zzw.equal(Float.valueOf(((PlayerStats)paramObject).getChurnProbability()), Float.valueOf(paramPlayerStats.getChurnProbability()))) || (!zzw.equal(Integer.valueOf(((PlayerStats)paramObject).getDaysSinceLastPlayed()), Integer.valueOf(paramPlayerStats.getDaysSinceLastPlayed()))) || (!zzw.equal(Integer.valueOf(((PlayerStats)paramObject).getNumberOfPurchases()), Integer.valueOf(paramPlayerStats.getNumberOfPurchases()))) || (!zzw.equal(Integer.valueOf(((PlayerStats)paramObject).getNumberOfSessions()), Integer.valueOf(paramPlayerStats.getNumberOfSessions()))) || (!zzw.equal(Float.valueOf(((PlayerStats)paramObject).getSessionPercentile()), Float.valueOf(paramPlayerStats.getSessionPercentile()))) || (!zzw.equal(Float.valueOf(((PlayerStats)paramObject).getSpendPercentile()), Float.valueOf(paramPlayerStats.getSpendPercentile())))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(Float.valueOf(((PlayerStats)paramObject).getSpendProbability()), Float.valueOf(paramPlayerStats.getSpendProbability())));
    return false;
  }
  
  static String zzb(PlayerStats paramPlayerStats)
  {
    return zzw.zzy(paramPlayerStats).zzg("AverageSessionLength", Float.valueOf(paramPlayerStats.getAverageSessionLength())).zzg("ChurnProbability", Float.valueOf(paramPlayerStats.getChurnProbability())).zzg("DaysSinceLastPlayed", Integer.valueOf(paramPlayerStats.getDaysSinceLastPlayed())).zzg("NumberOfPurchases", Integer.valueOf(paramPlayerStats.getNumberOfPurchases())).zzg("NumberOfSessions", Integer.valueOf(paramPlayerStats.getNumberOfSessions())).zzg("SessionPercentile", Float.valueOf(paramPlayerStats.getSessionPercentile())).zzg("SpendPercentile", Float.valueOf(paramPlayerStats.getSpendPercentile())).zzg("SpendProbability", Float.valueOf(paramPlayerStats.getSpendProbability())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public float getAverageSessionLength()
  {
    return this.zzaLd;
  }
  
  public float getChurnProbability()
  {
    return this.zzaLe;
  }
  
  public int getDaysSinceLastPlayed()
  {
    return this.zzaLf;
  }
  
  public int getNumberOfPurchases()
  {
    return this.zzaLg;
  }
  
  public int getNumberOfSessions()
  {
    return this.zzaLh;
  }
  
  public float getSessionPercentile()
  {
    return this.zzaLi;
  }
  
  public float getSpendPercentile()
  {
    return this.zzaLj;
  }
  
  public float getSpendProbability()
  {
    return this.zzaLl;
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
  
  public String toString()
  {
    return zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    PlayerStatsEntityCreator.zza(this, paramParcel, paramInt);
  }
  
  public Bundle zzxV()
  {
    return this.zzaLk;
  }
  
  public PlayerStats zzxW()
  {
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\stats\PlayerStatsEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */