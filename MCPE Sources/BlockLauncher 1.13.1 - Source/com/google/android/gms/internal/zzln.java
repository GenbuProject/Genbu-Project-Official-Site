package com.google.android.gms.internal;

import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.cast.internal.zzf;
import com.google.android.gms.common.internal.zzw;
import org.json.JSONObject;

public final class zzln
  implements PlayerInfo
{
  private final int zzabo;
  private final String zzacX;
  private final JSONObject zzadn;
  private final boolean zzado;
  
  public zzln(String paramString, int paramInt, JSONObject paramJSONObject, boolean paramBoolean)
  {
    this.zzacX = paramString;
    this.zzabo = paramInt;
    this.zzadn = paramJSONObject;
    this.zzado = paramBoolean;
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof PlayerInfo))) {}
    do
    {
      return false;
      paramObject = (PlayerInfo)paramObject;
    } while ((this.zzado != ((PlayerInfo)paramObject).isControllable()) || (this.zzabo != ((PlayerInfo)paramObject).getPlayerState()) || (!zzf.zza(this.zzacX, ((PlayerInfo)paramObject).getPlayerId())) || (!zznb.zze(this.zzadn, ((PlayerInfo)paramObject).getPlayerData())));
    return true;
  }
  
  public JSONObject getPlayerData()
  {
    return this.zzadn;
  }
  
  public String getPlayerId()
  {
    return this.zzacX;
  }
  
  public int getPlayerState()
  {
    return this.zzabo;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzacX, Integer.valueOf(this.zzabo), this.zzadn, Boolean.valueOf(this.zzado) });
  }
  
  public boolean isConnected()
  {
    switch (this.zzabo)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public boolean isControllable()
  {
    return this.zzado;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzln.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */