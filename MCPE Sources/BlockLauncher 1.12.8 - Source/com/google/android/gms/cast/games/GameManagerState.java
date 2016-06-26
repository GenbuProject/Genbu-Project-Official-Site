package com.google.android.gms.cast.games;

import java.util.Collection;
import java.util.List;
import org.json.JSONObject;

public abstract interface GameManagerState
{
  public abstract CharSequence getApplicationName();
  
  public abstract List<PlayerInfo> getConnectedControllablePlayers();
  
  public abstract List<PlayerInfo> getConnectedPlayers();
  
  public abstract List<PlayerInfo> getControllablePlayers();
  
  public abstract JSONObject getGameData();
  
  public abstract CharSequence getGameStatusText();
  
  public abstract int getGameplayState();
  
  public abstract Collection<String> getListOfChangedPlayers(GameManagerState paramGameManagerState);
  
  public abstract int getLobbyState();
  
  public abstract int getMaxPlayers();
  
  public abstract PlayerInfo getPlayer(String paramString);
  
  public abstract Collection<PlayerInfo> getPlayers();
  
  public abstract List<PlayerInfo> getPlayersInState(int paramInt);
  
  public abstract boolean hasGameDataChanged(GameManagerState paramGameManagerState);
  
  public abstract boolean hasGameStatusTextChanged(GameManagerState paramGameManagerState);
  
  public abstract boolean hasGameplayStateChanged(GameManagerState paramGameManagerState);
  
  public abstract boolean hasLobbyStateChanged(GameManagerState paramGameManagerState);
  
  public abstract boolean hasPlayerChanged(String paramString, GameManagerState paramGameManagerState);
  
  public abstract boolean hasPlayerDataChanged(String paramString, GameManagerState paramGameManagerState);
  
  public abstract boolean hasPlayerStateChanged(String paramString, GameManagerState paramGameManagerState);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\games\GameManagerState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */