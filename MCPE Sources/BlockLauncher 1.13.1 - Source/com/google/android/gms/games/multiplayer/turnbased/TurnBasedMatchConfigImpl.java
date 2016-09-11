package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import java.util.ArrayList;

public final class TurnBasedMatchConfigImpl
  extends TurnBasedMatchConfig
{
  private final int zzaJJ;
  private final Bundle zzaJZ;
  private final String[] zzaKa;
  private final int zzaKi;
  
  TurnBasedMatchConfigImpl(TurnBasedMatchConfig.Builder paramBuilder)
  {
    this.zzaJJ = paramBuilder.zzaJJ;
    this.zzaKi = paramBuilder.zzaKi;
    this.zzaJZ = paramBuilder.zzaJZ;
    int i = paramBuilder.zzaJY.size();
    this.zzaKa = ((String[])paramBuilder.zzaJY.toArray(new String[i]));
  }
  
  public Bundle getAutoMatchCriteria()
  {
    return this.zzaJZ;
  }
  
  public String[] getInvitedPlayerIds()
  {
    return this.zzaKa;
  }
  
  public int getVariant()
  {
    return this.zzaJJ;
  }
  
  public int zzxP()
  {
    return this.zzaKi;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\multiplayer\turnbased\TurnBasedMatchConfigImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */