package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.constants.TurnBasedMatchTurnStatus;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

public final class LoadMatchesResponse
{
  private final InvitationBuffer zzaKe;
  private final TurnBasedMatchBuffer zzaKf;
  private final TurnBasedMatchBuffer zzaKg;
  private final TurnBasedMatchBuffer zzaKh;
  
  public LoadMatchesResponse(Bundle paramBundle)
  {
    DataHolder localDataHolder = zza(paramBundle, 0);
    if (localDataHolder != null)
    {
      this.zzaKe = new InvitationBuffer(localDataHolder);
      localDataHolder = zza(paramBundle, 1);
      if (localDataHolder == null) {
        break label101;
      }
      this.zzaKf = new TurnBasedMatchBuffer(localDataHolder);
      label48:
      localDataHolder = zza(paramBundle, 2);
      if (localDataHolder == null) {
        break label109;
      }
    }
    label101:
    label109:
    for (this.zzaKg = new TurnBasedMatchBuffer(localDataHolder);; this.zzaKg = null)
    {
      paramBundle = zza(paramBundle, 3);
      if (paramBundle == null) {
        break label117;
      }
      this.zzaKh = new TurnBasedMatchBuffer(paramBundle);
      return;
      this.zzaKe = null;
      break;
      this.zzaKf = null;
      break label48;
    }
    label117:
    this.zzaKh = null;
  }
  
  private static DataHolder zza(Bundle paramBundle, int paramInt)
  {
    String str = TurnBasedMatchTurnStatus.zzgw(paramInt);
    if (!paramBundle.containsKey(str)) {
      return null;
    }
    return (DataHolder)paramBundle.getParcelable(str);
  }
  
  @Deprecated
  public void close()
  {
    release();
  }
  
  public TurnBasedMatchBuffer getCompletedMatches()
  {
    return this.zzaKh;
  }
  
  public InvitationBuffer getInvitations()
  {
    return this.zzaKe;
  }
  
  public TurnBasedMatchBuffer getMyTurnMatches()
  {
    return this.zzaKf;
  }
  
  public TurnBasedMatchBuffer getTheirTurnMatches()
  {
    return this.zzaKg;
  }
  
  public boolean hasData()
  {
    if ((this.zzaKe != null) && (this.zzaKe.getCount() > 0)) {}
    while (((this.zzaKf != null) && (this.zzaKf.getCount() > 0)) || ((this.zzaKg != null) && (this.zzaKg.getCount() > 0)) || ((this.zzaKh != null) && (this.zzaKh.getCount() > 0))) {
      return true;
    }
    return false;
  }
  
  public void release()
  {
    if (this.zzaKe != null) {
      this.zzaKe.release();
    }
    if (this.zzaKf != null) {
      this.zzaKf.release();
    }
    if (this.zzaKg != null) {
      this.zzaKg.release();
    }
    if (this.zzaKh != null) {
      this.zzaKh.release();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\multiplayer\turnbased\LoadMatchesResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */