package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.cast.internal.zzf;
import com.google.android.gms.common.internal.zzw;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public final class zzlm
  implements GameManagerState
{
  private final String zzada;
  private final int zzadb;
  private final int zzadg;
  private final int zzadh;
  private final JSONObject zzadj;
  private final String zzadk;
  private final Map<String, PlayerInfo> zzadm;
  
  public zzlm(int paramInt1, int paramInt2, String paramString1, JSONObject paramJSONObject, Collection<PlayerInfo> paramCollection, String paramString2, int paramInt3)
  {
    this.zzadh = paramInt1;
    this.zzadg = paramInt2;
    this.zzadk = paramString1;
    this.zzadj = paramJSONObject;
    this.zzada = paramString2;
    this.zzadb = paramInt3;
    this.zzadm = new HashMap(paramCollection.size());
    paramString1 = paramCollection.iterator();
    while (paramString1.hasNext())
    {
      paramJSONObject = (PlayerInfo)paramString1.next();
      this.zzadm.put(paramJSONObject.getPlayerId(), paramJSONObject);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if ((paramObject == null) || (!(paramObject instanceof GameManagerState))) {}
    do
    {
      return false;
      paramObject = (GameManagerState)paramObject;
    } while (getPlayers().size() != ((GameManagerState)paramObject).getPlayers().size());
    Iterator localIterator1 = getPlayers().iterator();
    label248:
    label249:
    for (;;)
    {
      int i;
      if (localIterator1.hasNext())
      {
        PlayerInfo localPlayerInfo1 = (PlayerInfo)localIterator1.next();
        Iterator localIterator2 = ((GameManagerState)paramObject).getPlayers().iterator();
        i = 0;
        if (localIterator2.hasNext())
        {
          PlayerInfo localPlayerInfo2 = (PlayerInfo)localIterator2.next();
          if (!zzf.zza(localPlayerInfo1.getPlayerId(), localPlayerInfo2.getPlayerId())) {
            break label248;
          }
          if (!zzf.zza(localPlayerInfo1, localPlayerInfo2)) {
            break;
          }
          i = 1;
        }
      }
      for (;;)
      {
        break;
        if (i != 0) {
          break label249;
        }
        return false;
        if ((this.zzadh == ((GameManagerState)paramObject).getLobbyState()) && (this.zzadg == ((GameManagerState)paramObject).getGameplayState()) && (this.zzadb == ((GameManagerState)paramObject).getMaxPlayers()) && (zzf.zza(this.zzada, ((GameManagerState)paramObject).getApplicationName())) && (zzf.zza(this.zzadk, ((GameManagerState)paramObject).getGameStatusText())) && (zznb.zze(this.zzadj, ((GameManagerState)paramObject).getGameData()))) {}
        for (;;)
        {
          return bool;
          bool = false;
        }
      }
    }
  }
  
  public CharSequence getApplicationName()
  {
    return this.zzada;
  }
  
  public List<PlayerInfo> getConnectedControllablePlayers()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getPlayers().iterator();
    while (localIterator.hasNext())
    {
      PlayerInfo localPlayerInfo = (PlayerInfo)localIterator.next();
      if ((localPlayerInfo.isConnected()) && (localPlayerInfo.isControllable())) {
        localArrayList.add(localPlayerInfo);
      }
    }
    return localArrayList;
  }
  
  public List<PlayerInfo> getConnectedPlayers()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getPlayers().iterator();
    while (localIterator.hasNext())
    {
      PlayerInfo localPlayerInfo = (PlayerInfo)localIterator.next();
      if (localPlayerInfo.isConnected()) {
        localArrayList.add(localPlayerInfo);
      }
    }
    return localArrayList;
  }
  
  public List<PlayerInfo> getControllablePlayers()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getPlayers().iterator();
    while (localIterator.hasNext())
    {
      PlayerInfo localPlayerInfo = (PlayerInfo)localIterator.next();
      if (localPlayerInfo.isControllable()) {
        localArrayList.add(localPlayerInfo);
      }
    }
    return localArrayList;
  }
  
  public JSONObject getGameData()
  {
    return this.zzadj;
  }
  
  public CharSequence getGameStatusText()
  {
    return this.zzadk;
  }
  
  public int getGameplayState()
  {
    return this.zzadg;
  }
  
  public Collection<String> getListOfChangedPlayers(GameManagerState paramGameManagerState)
  {
    HashSet localHashSet = new HashSet();
    Object localObject = getPlayers().iterator();
    while (((Iterator)localObject).hasNext())
    {
      PlayerInfo localPlayerInfo1 = (PlayerInfo)((Iterator)localObject).next();
      PlayerInfo localPlayerInfo2 = paramGameManagerState.getPlayer(localPlayerInfo1.getPlayerId());
      if ((localPlayerInfo2 == null) || (!localPlayerInfo1.equals(localPlayerInfo2))) {
        localHashSet.add(localPlayerInfo1.getPlayerId());
      }
    }
    paramGameManagerState = paramGameManagerState.getPlayers().iterator();
    while (paramGameManagerState.hasNext())
    {
      localObject = (PlayerInfo)paramGameManagerState.next();
      if (getPlayer(((PlayerInfo)localObject).getPlayerId()) == null) {
        localHashSet.add(((PlayerInfo)localObject).getPlayerId());
      }
    }
    return localHashSet;
  }
  
  public int getLobbyState()
  {
    return this.zzadh;
  }
  
  public int getMaxPlayers()
  {
    return this.zzadb;
  }
  
  public PlayerInfo getPlayer(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return (PlayerInfo)this.zzadm.get(paramString);
  }
  
  public Collection<PlayerInfo> getPlayers()
  {
    return Collections.unmodifiableCollection(this.zzadm.values());
  }
  
  public List<PlayerInfo> getPlayersInState(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = getPlayers().iterator();
    while (localIterator.hasNext())
    {
      PlayerInfo localPlayerInfo = (PlayerInfo)localIterator.next();
      if (localPlayerInfo.getPlayerState() == paramInt) {
        localArrayList.add(localPlayerInfo);
      }
    }
    return localArrayList;
  }
  
  public boolean hasGameDataChanged(GameManagerState paramGameManagerState)
  {
    return !zznb.zze(this.zzadj, paramGameManagerState.getGameData());
  }
  
  public boolean hasGameStatusTextChanged(GameManagerState paramGameManagerState)
  {
    return !zzf.zza(this.zzadk, paramGameManagerState.getGameStatusText());
  }
  
  public boolean hasGameplayStateChanged(GameManagerState paramGameManagerState)
  {
    return this.zzadg != paramGameManagerState.getGameplayState();
  }
  
  public boolean hasLobbyStateChanged(GameManagerState paramGameManagerState)
  {
    return this.zzadh != paramGameManagerState.getLobbyState();
  }
  
  public boolean hasPlayerChanged(String paramString, GameManagerState paramGameManagerState)
  {
    return !zzf.zza(getPlayer(paramString), paramGameManagerState.getPlayer(paramString));
  }
  
  public boolean hasPlayerDataChanged(String paramString, GameManagerState paramGameManagerState)
  {
    boolean bool2 = true;
    PlayerInfo localPlayerInfo = getPlayer(paramString);
    paramString = paramGameManagerState.getPlayer(paramString);
    boolean bool1;
    if ((localPlayerInfo == null) && (paramString == null)) {
      bool1 = false;
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (localPlayerInfo == null);
        bool1 = bool2;
      } while (paramString == null);
      bool1 = bool2;
    } while (!zznb.zze(localPlayerInfo.getPlayerData(), paramString.getPlayerData()));
    return false;
  }
  
  public boolean hasPlayerStateChanged(String paramString, GameManagerState paramGameManagerState)
  {
    boolean bool2 = true;
    PlayerInfo localPlayerInfo = getPlayer(paramString);
    paramString = paramGameManagerState.getPlayer(paramString);
    boolean bool1;
    if ((localPlayerInfo == null) && (paramString == null)) {
      bool1 = false;
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (localPlayerInfo == null);
        bool1 = bool2;
      } while (paramString == null);
      bool1 = bool2;
    } while (localPlayerInfo.getPlayerState() != paramString.getPlayerState());
    return false;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.zzadh), Integer.valueOf(this.zzadg), this.zzadm, this.zzadk, this.zzadj, this.zzada, Integer.valueOf(this.zzadb) });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzlm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */