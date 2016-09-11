package com.google.android.gms.internal;

import com.google.android.gms.cast.internal.zzf;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzlo
{
  private final int zzabo;
  private final String zzacX;
  private final JSONObject zzadn;
  
  public zzlo(String paramString, int paramInt, JSONObject paramJSONObject)
  {
    this.zzacX = paramString;
    this.zzabo = paramInt;
    this.zzadn = paramJSONObject;
  }
  
  public zzlo(JSONObject paramJSONObject)
    throws JSONException
  {
    this(paramJSONObject.optString("playerId"), paramJSONObject.optInt("playerState"), paramJSONObject.optJSONObject("playerData"));
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof zzlo))) {}
    do
    {
      return false;
      paramObject = (zzlo)paramObject;
    } while ((this.zzabo != ((zzlo)paramObject).getPlayerState()) || (!zzf.zza(this.zzacX, ((zzlo)paramObject).getPlayerId())) || (!zznb.zze(this.zzadn, ((zzlo)paramObject).getPlayerData())));
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
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzlo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */