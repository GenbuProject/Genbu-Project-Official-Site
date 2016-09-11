package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public abstract interface PlayerStats
  extends Parcelable, Freezable<PlayerStats>
{
  public static final float UNSET_VALUE = -1.0F;
  
  public abstract float getAverageSessionLength();
  
  public abstract float getChurnProbability();
  
  public abstract int getDaysSinceLastPlayed();
  
  public abstract int getNumberOfPurchases();
  
  public abstract int getNumberOfSessions();
  
  public abstract float getSessionPercentile();
  
  public abstract float getSpendPercentile();
  
  public abstract float getSpendProbability();
  
  public abstract Bundle zzxV();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\stats\PlayerStats.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */