package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.internal.GamesLog;

public final class MilestoneRef
  extends zzc
  implements Milestone
{
  MilestoneRef(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  private long zzxQ()
  {
    return getLong("initial_value");
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return MilestoneEntity.zza(this, paramObject);
  }
  
  public Milestone freeze()
  {
    return new MilestoneEntity(this);
  }
  
  public byte[] getCompletionRewardData()
  {
    return getByteArray("completion_reward_data");
  }
  
  public long getCurrentProgress()
  {
    long l2 = 0L;
    long l1;
    switch (getState())
    {
    default: 
      l1 = 0L;
      if (l1 < 0L)
      {
        GamesLog.zzA("MilestoneRef", "Current progress should never be negative");
        l1 = l2;
      }
      break;
    }
    for (;;)
    {
      l2 = l1;
      if (l1 > getTargetProgress())
      {
        GamesLog.zzA("MilestoneRef", "Current progress should never exceed target progress");
        l2 = getTargetProgress();
      }
      return l2;
      l1 = getTargetProgress();
      break;
      l1 = 0L;
      break;
      long l3 = getLong("current_value");
      l1 = l3;
      if (getLong("quest_state") == 6L) {
        break;
      }
      l1 = l3 - zzxQ();
      break;
    }
  }
  
  public String getEventId()
  {
    return getString("external_event_id");
  }
  
  public String getMilestoneId()
  {
    return getString("external_milestone_id");
  }
  
  public int getState()
  {
    return getInteger("milestone_state");
  }
  
  public long getTargetProgress()
  {
    return getLong("target_value");
  }
  
  public int hashCode()
  {
    return MilestoneEntity.zza(this);
  }
  
  public String toString()
  {
    return MilestoneEntity.zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((MilestoneEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\quest\MilestoneRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */