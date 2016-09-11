package com.google.android.gms.games;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;

public final class PlayerLevelInfo
  implements SafeParcelable
{
  public static final Parcelable.Creator<PlayerLevelInfo> CREATOR = new PlayerLevelInfoCreator();
  private final int mVersionCode;
  private final long zzaDd;
  private final long zzaDe;
  private final PlayerLevel zzaDf;
  private final PlayerLevel zzaDg;
  
  PlayerLevelInfo(int paramInt, long paramLong1, long paramLong2, PlayerLevel paramPlayerLevel1, PlayerLevel paramPlayerLevel2)
  {
    if (paramLong1 != -1L) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzab(bool);
      zzx.zzz(paramPlayerLevel1);
      zzx.zzz(paramPlayerLevel2);
      this.mVersionCode = paramInt;
      this.zzaDd = paramLong1;
      this.zzaDe = paramLong2;
      this.zzaDf = paramPlayerLevel1;
      this.zzaDg = paramPlayerLevel2;
      return;
    }
  }
  
  public PlayerLevelInfo(long paramLong1, long paramLong2, PlayerLevel paramPlayerLevel1, PlayerLevel paramPlayerLevel2)
  {
    this(1, paramLong1, paramLong2, paramPlayerLevel1, paramPlayerLevel2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof PlayerLevelInfo)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == this);
      paramObject = (PlayerLevelInfo)paramObject;
      if ((!zzw.equal(Long.valueOf(this.zzaDd), Long.valueOf(((PlayerLevelInfo)paramObject).zzaDd))) || (!zzw.equal(Long.valueOf(this.zzaDe), Long.valueOf(((PlayerLevelInfo)paramObject).zzaDe))) || (!zzw.equal(this.zzaDf, ((PlayerLevelInfo)paramObject).zzaDf))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(this.zzaDg, ((PlayerLevelInfo)paramObject).zzaDg));
    return false;
  }
  
  public PlayerLevel getCurrentLevel()
  {
    return this.zzaDf;
  }
  
  public long getCurrentXpTotal()
  {
    return this.zzaDd;
  }
  
  public long getLastLevelUpTimestamp()
  {
    return this.zzaDe;
  }
  
  public PlayerLevel getNextLevel()
  {
    return this.zzaDg;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Long.valueOf(this.zzaDd), Long.valueOf(this.zzaDe), this.zzaDf, this.zzaDg });
  }
  
  public boolean isMaxLevel()
  {
    return this.zzaDf.equals(this.zzaDg);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    PlayerLevelInfoCreator.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\PlayerLevelInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */