package com.google.android.gms.games.quest;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public final class MilestoneEntity
  implements SafeParcelable, Milestone
{
  public static final Parcelable.Creator<MilestoneEntity> CREATOR = new MilestoneEntityCreator();
  private final int mState;
  private final int mVersionCode;
  private final String zzaDU;
  private final String zzaFd;
  private final long zzaKt;
  private final long zzaKu;
  private final byte[] zzaKv;
  
  MilestoneEntity(int paramInt1, String paramString1, long paramLong1, long paramLong2, byte[] paramArrayOfByte, int paramInt2, String paramString2)
  {
    this.mVersionCode = paramInt1;
    this.zzaFd = paramString1;
    this.zzaKt = paramLong1;
    this.zzaKu = paramLong2;
    this.zzaKv = paramArrayOfByte;
    this.mState = paramInt2;
    this.zzaDU = paramString2;
  }
  
  public MilestoneEntity(Milestone paramMilestone)
  {
    this.mVersionCode = 4;
    this.zzaFd = paramMilestone.getMilestoneId();
    this.zzaKt = paramMilestone.getCurrentProgress();
    this.zzaKu = paramMilestone.getTargetProgress();
    this.mState = paramMilestone.getState();
    this.zzaDU = paramMilestone.getEventId();
    paramMilestone = paramMilestone.getCompletionRewardData();
    if (paramMilestone == null)
    {
      this.zzaKv = null;
      return;
    }
    this.zzaKv = new byte[paramMilestone.length];
    System.arraycopy(paramMilestone, 0, this.zzaKv, 0, paramMilestone.length);
  }
  
  static int zza(Milestone paramMilestone)
  {
    return zzw.hashCode(new Object[] { paramMilestone.getMilestoneId(), Long.valueOf(paramMilestone.getCurrentProgress()), Long.valueOf(paramMilestone.getTargetProgress()), Integer.valueOf(paramMilestone.getState()), paramMilestone.getEventId() });
  }
  
  static boolean zza(Milestone paramMilestone, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Milestone)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramMilestone == paramObject);
      paramObject = (Milestone)paramObject;
      if ((!zzw.equal(((Milestone)paramObject).getMilestoneId(), paramMilestone.getMilestoneId())) || (!zzw.equal(Long.valueOf(((Milestone)paramObject).getCurrentProgress()), Long.valueOf(paramMilestone.getCurrentProgress()))) || (!zzw.equal(Long.valueOf(((Milestone)paramObject).getTargetProgress()), Long.valueOf(paramMilestone.getTargetProgress()))) || (!zzw.equal(Integer.valueOf(((Milestone)paramObject).getState()), Integer.valueOf(paramMilestone.getState())))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((Milestone)paramObject).getEventId(), paramMilestone.getEventId()));
    return false;
  }
  
  static String zzb(Milestone paramMilestone)
  {
    return zzw.zzy(paramMilestone).zzg("MilestoneId", paramMilestone.getMilestoneId()).zzg("CurrentProgress", Long.valueOf(paramMilestone.getCurrentProgress())).zzg("TargetProgress", Long.valueOf(paramMilestone.getTargetProgress())).zzg("State", Integer.valueOf(paramMilestone.getState())).zzg("CompletionRewardData", paramMilestone.getCompletionRewardData()).zzg("EventId", paramMilestone.getEventId()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public Milestone freeze()
  {
    return this;
  }
  
  public byte[] getCompletionRewardData()
  {
    return this.zzaKv;
  }
  
  public long getCurrentProgress()
  {
    return this.zzaKt;
  }
  
  public String getEventId()
  {
    return this.zzaDU;
  }
  
  public String getMilestoneId()
  {
    return this.zzaFd;
  }
  
  public int getState()
  {
    return this.mState;
  }
  
  public long getTargetProgress()
  {
    return this.zzaKu;
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
    MilestoneEntityCreator.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\quest\MilestoneEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */