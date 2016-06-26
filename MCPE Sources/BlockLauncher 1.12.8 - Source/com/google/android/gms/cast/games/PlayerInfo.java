package com.google.android.gms.cast.games;

import org.json.JSONObject;

public abstract interface PlayerInfo
{
  public abstract JSONObject getPlayerData();
  
  public abstract String getPlayerId();
  
  public abstract int getPlayerState();
  
  public abstract boolean isConnected();
  
  public abstract boolean isControllable();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\games\PlayerInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */