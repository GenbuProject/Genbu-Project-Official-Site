package com.google.android.gms.games.stats;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class PlayerStatsBuffer
  extends AbstractDataBuffer<PlayerStats>
{
  public PlayerStatsBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  public PlayerStats zzgV(int paramInt)
  {
    return new PlayerStatsRef(this.zzahi, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\stats\PlayerStatsBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */