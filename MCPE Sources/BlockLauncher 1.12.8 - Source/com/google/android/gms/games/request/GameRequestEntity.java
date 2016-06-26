package com.google.android.gms.games.request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GameRequestEntity
  implements SafeParcelable, GameRequest
{
  public static final Parcelable.Creator<GameRequestEntity> CREATOR = new GameRequestEntityCreator();
  private final int mVersionCode;
  private final int zzBc;
  private final String zzEY;
  private final GameEntity zzaJE;
  private final long zzaJF;
  private final PlayerEntity zzaKG;
  private final ArrayList<PlayerEntity> zzaKH;
  private final long zzaKI;
  private final Bundle zzaKJ;
  private final byte[] zzaKm;
  private final int zzabB;
  
  GameRequestEntity(int paramInt1, GameEntity paramGameEntity, PlayerEntity paramPlayerEntity, byte[] paramArrayOfByte, String paramString, ArrayList<PlayerEntity> paramArrayList, int paramInt2, long paramLong1, long paramLong2, Bundle paramBundle, int paramInt3)
  {
    this.mVersionCode = paramInt1;
    this.zzaJE = paramGameEntity;
    this.zzaKG = paramPlayerEntity;
    this.zzaKm = paramArrayOfByte;
    this.zzEY = paramString;
    this.zzaKH = paramArrayList;
    this.zzabB = paramInt2;
    this.zzaJF = paramLong1;
    this.zzaKI = paramLong2;
    this.zzaKJ = paramBundle;
    this.zzBc = paramInt3;
  }
  
  public GameRequestEntity(GameRequest paramGameRequest)
  {
    this.mVersionCode = 2;
    this.zzaJE = new GameEntity(paramGameRequest.getGame());
    this.zzaKG = new PlayerEntity(paramGameRequest.getSender());
    this.zzEY = paramGameRequest.getRequestId();
    this.zzabB = paramGameRequest.getType();
    this.zzaJF = paramGameRequest.getCreationTimestamp();
    this.zzaKI = paramGameRequest.getExpirationTimestamp();
    this.zzBc = paramGameRequest.getStatus();
    Object localObject = paramGameRequest.getData();
    if (localObject == null) {
      this.zzaKm = null;
    }
    for (;;)
    {
      localObject = paramGameRequest.getRecipients();
      int j = ((List)localObject).size();
      this.zzaKH = new ArrayList(j);
      this.zzaKJ = new Bundle();
      int i = 0;
      while (i < j)
      {
        Player localPlayer = (Player)((Player)((List)localObject).get(i)).freeze();
        String str = localPlayer.getPlayerId();
        this.zzaKH.add((PlayerEntity)localPlayer);
        this.zzaKJ.putInt(str, paramGameRequest.getRecipientStatus(str));
        i += 1;
      }
      this.zzaKm = new byte[localObject.length];
      System.arraycopy(localObject, 0, this.zzaKm, 0, localObject.length);
    }
  }
  
  static int zza(GameRequest paramGameRequest)
  {
    return zzw.hashCode(new Object[] { paramGameRequest.getGame(), paramGameRequest.getRecipients(), paramGameRequest.getRequestId(), paramGameRequest.getSender(), zzb(paramGameRequest), Integer.valueOf(paramGameRequest.getType()), Long.valueOf(paramGameRequest.getCreationTimestamp()), Long.valueOf(paramGameRequest.getExpirationTimestamp()) });
  }
  
  static boolean zza(GameRequest paramGameRequest, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof GameRequest)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramGameRequest == paramObject);
      paramObject = (GameRequest)paramObject;
      if ((!zzw.equal(((GameRequest)paramObject).getGame(), paramGameRequest.getGame())) || (!zzw.equal(((GameRequest)paramObject).getRecipients(), paramGameRequest.getRecipients())) || (!zzw.equal(((GameRequest)paramObject).getRequestId(), paramGameRequest.getRequestId())) || (!zzw.equal(((GameRequest)paramObject).getSender(), paramGameRequest.getSender())) || (!Arrays.equals(zzb((GameRequest)paramObject), zzb(paramGameRequest))) || (!zzw.equal(Integer.valueOf(((GameRequest)paramObject).getType()), Integer.valueOf(paramGameRequest.getType()))) || (!zzw.equal(Long.valueOf(((GameRequest)paramObject).getCreationTimestamp()), Long.valueOf(paramGameRequest.getCreationTimestamp())))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(Long.valueOf(((GameRequest)paramObject).getExpirationTimestamp()), Long.valueOf(paramGameRequest.getExpirationTimestamp())));
    return false;
  }
  
  private static int[] zzb(GameRequest paramGameRequest)
  {
    List localList = paramGameRequest.getRecipients();
    int j = localList.size();
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (i < j)
    {
      arrayOfInt[i] = paramGameRequest.getRecipientStatus(((Player)localList.get(i)).getPlayerId());
      i += 1;
    }
    return arrayOfInt;
  }
  
  static String zzc(GameRequest paramGameRequest)
  {
    return zzw.zzy(paramGameRequest).zzg("Game", paramGameRequest.getGame()).zzg("Sender", paramGameRequest.getSender()).zzg("Recipients", paramGameRequest.getRecipients()).zzg("Data", paramGameRequest.getData()).zzg("RequestId", paramGameRequest.getRequestId()).zzg("Type", Integer.valueOf(paramGameRequest.getType())).zzg("CreationTimestamp", Long.valueOf(paramGameRequest.getCreationTimestamp())).zzg("ExpirationTimestamp", Long.valueOf(paramGameRequest.getExpirationTimestamp())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public GameRequest freeze()
  {
    return this;
  }
  
  public long getCreationTimestamp()
  {
    return this.zzaJF;
  }
  
  public byte[] getData()
  {
    return this.zzaKm;
  }
  
  public long getExpirationTimestamp()
  {
    return this.zzaKI;
  }
  
  public Game getGame()
  {
    return this.zzaJE;
  }
  
  public int getRecipientStatus(String paramString)
  {
    return this.zzaKJ.getInt(paramString, 0);
  }
  
  public List<Player> getRecipients()
  {
    return new ArrayList(this.zzaKH);
  }
  
  public String getRequestId()
  {
    return this.zzEY;
  }
  
  public Player getSender()
  {
    return this.zzaKG;
  }
  
  public int getStatus()
  {
    return this.zzBc;
  }
  
  public int getType()
  {
    return this.zzabB;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zza(this);
  }
  
  public boolean isConsumed(String paramString)
  {
    return getRecipientStatus(paramString) == 1;
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return zzc(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    GameRequestEntityCreator.zza(this, paramParcel, paramInt);
  }
  
  public Bundle zzxT()
  {
    return this.zzaKJ;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\request\GameRequestEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */