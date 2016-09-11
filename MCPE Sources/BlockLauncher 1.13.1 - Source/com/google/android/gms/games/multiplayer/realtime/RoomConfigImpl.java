package com.google.android.gms.games.multiplayer.realtime;

import android.os.Bundle;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;

public final class RoomConfigImpl
  extends RoomConfig
{
  private final String zzUO;
  private final int zzaJJ;
  private final RoomUpdateListener zzaJU;
  private final RoomStatusUpdateListener zzaJV;
  private final RealTimeMessageReceivedListener zzaJW;
  private final Bundle zzaJZ;
  private final String[] zzaKa;
  
  RoomConfigImpl(RoomConfig.Builder paramBuilder)
  {
    this.zzaJU = paramBuilder.zzaJU;
    this.zzaJV = paramBuilder.zzaJV;
    this.zzaJW = paramBuilder.zzaJW;
    this.zzUO = paramBuilder.zzaJX;
    this.zzaJJ = paramBuilder.zzaJJ;
    this.zzaJZ = paramBuilder.zzaJZ;
    int i = paramBuilder.zzaJY.size();
    this.zzaKa = ((String[])paramBuilder.zzaJY.toArray(new String[i]));
    zzx.zzb(this.zzaJW, "Must specify a message listener");
  }
  
  public Bundle getAutoMatchCriteria()
  {
    return this.zzaJZ;
  }
  
  public String getInvitationId()
  {
    return this.zzUO;
  }
  
  public String[] getInvitedPlayerIds()
  {
    return this.zzaKa;
  }
  
  public RealTimeMessageReceivedListener getMessageReceivedListener()
  {
    return this.zzaJW;
  }
  
  public RoomStatusUpdateListener getRoomStatusUpdateListener()
  {
    return this.zzaJV;
  }
  
  public RoomUpdateListener getRoomUpdateListener()
  {
    return this.zzaJU;
  }
  
  public int getVariant()
  {
    return this.zzaJJ;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\multiplayer\realtime\RoomConfigImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */