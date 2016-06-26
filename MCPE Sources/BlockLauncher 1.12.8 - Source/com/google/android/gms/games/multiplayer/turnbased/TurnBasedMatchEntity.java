package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.zzms;
import java.util.ArrayList;

public final class TurnBasedMatchEntity
  implements SafeParcelable, TurnBasedMatch
{
  public static final Parcelable.Creator<TurnBasedMatchEntity> CREATOR = new TurnBasedMatchEntityCreator();
  private final int mVersion;
  private final int mVersionCode;
  private final long zzaDt;
  private final String zzaER;
  private final GameEntity zzaJE;
  private final long zzaJF;
  private final ArrayList<ParticipantEntity> zzaJI;
  private final int zzaJJ;
  private final Bundle zzaJZ;
  private final String zzaKb;
  private final String zzaKj;
  private final String zzaKk;
  private final int zzaKl;
  private final byte[] zzaKm;
  private final String zzaKn;
  private final byte[] zzaKo;
  private final int zzaKp;
  private final int zzaKq;
  private final boolean zzaKr;
  private final String zzaKs;
  private final String zzaxl;
  
  TurnBasedMatchEntity(int paramInt1, GameEntity paramGameEntity, String paramString1, String paramString2, long paramLong1, String paramString3, long paramLong2, String paramString4, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte1, ArrayList<ParticipantEntity> paramArrayList, String paramString5, byte[] paramArrayOfByte2, int paramInt5, Bundle paramBundle, int paramInt6, boolean paramBoolean, String paramString6, String paramString7)
  {
    this.mVersionCode = paramInt1;
    this.zzaJE = paramGameEntity;
    this.zzaER = paramString1;
    this.zzaKb = paramString2;
    this.zzaJF = paramLong1;
    this.zzaKj = paramString3;
    this.zzaDt = paramLong2;
    this.zzaKk = paramString4;
    this.zzaKl = paramInt2;
    this.zzaKq = paramInt6;
    this.zzaJJ = paramInt3;
    this.mVersion = paramInt4;
    this.zzaKm = paramArrayOfByte1;
    this.zzaJI = paramArrayList;
    this.zzaKn = paramString5;
    this.zzaKo = paramArrayOfByte2;
    this.zzaKp = paramInt5;
    this.zzaJZ = paramBundle;
    this.zzaKr = paramBoolean;
    this.zzaxl = paramString6;
    this.zzaKs = paramString7;
  }
  
  public TurnBasedMatchEntity(TurnBasedMatch paramTurnBasedMatch)
  {
    this.mVersionCode = 2;
    this.zzaJE = new GameEntity(paramTurnBasedMatch.getGame());
    this.zzaER = paramTurnBasedMatch.getMatchId();
    this.zzaKb = paramTurnBasedMatch.getCreatorId();
    this.zzaJF = paramTurnBasedMatch.getCreationTimestamp();
    this.zzaKj = paramTurnBasedMatch.getLastUpdaterId();
    this.zzaDt = paramTurnBasedMatch.getLastUpdatedTimestamp();
    this.zzaKk = paramTurnBasedMatch.getPendingParticipantId();
    this.zzaKl = paramTurnBasedMatch.getStatus();
    this.zzaKq = paramTurnBasedMatch.getTurnStatus();
    this.zzaJJ = paramTurnBasedMatch.getVariant();
    this.mVersion = paramTurnBasedMatch.getVersion();
    this.zzaKn = paramTurnBasedMatch.getRematchId();
    this.zzaKp = paramTurnBasedMatch.getMatchNumber();
    this.zzaJZ = paramTurnBasedMatch.getAutoMatchCriteria();
    this.zzaKr = paramTurnBasedMatch.isLocallyModified();
    this.zzaxl = paramTurnBasedMatch.getDescription();
    this.zzaKs = paramTurnBasedMatch.getDescriptionParticipantId();
    byte[] arrayOfByte = paramTurnBasedMatch.getData();
    if (arrayOfByte == null)
    {
      this.zzaKm = null;
      arrayOfByte = paramTurnBasedMatch.getPreviousMatchData();
      if (arrayOfByte != null) {
        break label313;
      }
      this.zzaKo = null;
    }
    for (;;)
    {
      paramTurnBasedMatch = paramTurnBasedMatch.getParticipants();
      int j = paramTurnBasedMatch.size();
      this.zzaJI = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        this.zzaJI.add((ParticipantEntity)((Participant)paramTurnBasedMatch.get(i)).freeze());
        i += 1;
      }
      this.zzaKm = new byte[arrayOfByte.length];
      System.arraycopy(arrayOfByte, 0, this.zzaKm, 0, arrayOfByte.length);
      break;
      label313:
      this.zzaKo = new byte[arrayOfByte.length];
      System.arraycopy(arrayOfByte, 0, this.zzaKo, 0, arrayOfByte.length);
    }
  }
  
  static int zza(TurnBasedMatch paramTurnBasedMatch)
  {
    return zzw.hashCode(new Object[] { paramTurnBasedMatch.getGame(), paramTurnBasedMatch.getMatchId(), paramTurnBasedMatch.getCreatorId(), Long.valueOf(paramTurnBasedMatch.getCreationTimestamp()), paramTurnBasedMatch.getLastUpdaterId(), Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp()), paramTurnBasedMatch.getPendingParticipantId(), Integer.valueOf(paramTurnBasedMatch.getStatus()), Integer.valueOf(paramTurnBasedMatch.getTurnStatus()), paramTurnBasedMatch.getDescription(), Integer.valueOf(paramTurnBasedMatch.getVariant()), Integer.valueOf(paramTurnBasedMatch.getVersion()), paramTurnBasedMatch.getParticipants(), paramTurnBasedMatch.getRematchId(), Integer.valueOf(paramTurnBasedMatch.getMatchNumber()), paramTurnBasedMatch.getAutoMatchCriteria(), Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots()), Boolean.valueOf(paramTurnBasedMatch.isLocallyModified()) });
  }
  
  static int zza(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
    int j = localArrayList.size();
    int i = 0;
    while (i < j)
    {
      Participant localParticipant = (Participant)localArrayList.get(i);
      if (localParticipant.getParticipantId().equals(paramString)) {
        return localParticipant.getStatus();
      }
      i += 1;
    }
    throw new IllegalStateException("Participant " + paramString + " is not in match " + paramTurnBasedMatch.getMatchId());
  }
  
  static boolean zza(TurnBasedMatch paramTurnBasedMatch, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof TurnBasedMatch)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramTurnBasedMatch == paramObject);
      paramObject = (TurnBasedMatch)paramObject;
      if ((!zzw.equal(((TurnBasedMatch)paramObject).getGame(), paramTurnBasedMatch.getGame())) || (!zzw.equal(((TurnBasedMatch)paramObject).getMatchId(), paramTurnBasedMatch.getMatchId())) || (!zzw.equal(((TurnBasedMatch)paramObject).getCreatorId(), paramTurnBasedMatch.getCreatorId())) || (!zzw.equal(Long.valueOf(((TurnBasedMatch)paramObject).getCreationTimestamp()), Long.valueOf(paramTurnBasedMatch.getCreationTimestamp()))) || (!zzw.equal(((TurnBasedMatch)paramObject).getLastUpdaterId(), paramTurnBasedMatch.getLastUpdaterId())) || (!zzw.equal(Long.valueOf(((TurnBasedMatch)paramObject).getLastUpdatedTimestamp()), Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp()))) || (!zzw.equal(((TurnBasedMatch)paramObject).getPendingParticipantId(), paramTurnBasedMatch.getPendingParticipantId())) || (!zzw.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getStatus()), Integer.valueOf(paramTurnBasedMatch.getStatus()))) || (!zzw.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getTurnStatus()), Integer.valueOf(paramTurnBasedMatch.getTurnStatus()))) || (!zzw.equal(((TurnBasedMatch)paramObject).getDescription(), paramTurnBasedMatch.getDescription())) || (!zzw.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getVariant()), Integer.valueOf(paramTurnBasedMatch.getVariant()))) || (!zzw.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getVersion()), Integer.valueOf(paramTurnBasedMatch.getVersion()))) || (!zzw.equal(((TurnBasedMatch)paramObject).getParticipants(), paramTurnBasedMatch.getParticipants())) || (!zzw.equal(((TurnBasedMatch)paramObject).getRematchId(), paramTurnBasedMatch.getRematchId())) || (!zzw.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getMatchNumber()), Integer.valueOf(paramTurnBasedMatch.getMatchNumber()))) || (!zzw.equal(((TurnBasedMatch)paramObject).getAutoMatchCriteria(), paramTurnBasedMatch.getAutoMatchCriteria())) || (!zzw.equal(Integer.valueOf(((TurnBasedMatch)paramObject).getAvailableAutoMatchSlots()), Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots())))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(Boolean.valueOf(((TurnBasedMatch)paramObject).isLocallyModified()), Boolean.valueOf(paramTurnBasedMatch.isLocallyModified())));
    return false;
  }
  
  static String zzb(TurnBasedMatch paramTurnBasedMatch)
  {
    return zzw.zzy(paramTurnBasedMatch).zzg("Game", paramTurnBasedMatch.getGame()).zzg("MatchId", paramTurnBasedMatch.getMatchId()).zzg("CreatorId", paramTurnBasedMatch.getCreatorId()).zzg("CreationTimestamp", Long.valueOf(paramTurnBasedMatch.getCreationTimestamp())).zzg("LastUpdaterId", paramTurnBasedMatch.getLastUpdaterId()).zzg("LastUpdatedTimestamp", Long.valueOf(paramTurnBasedMatch.getLastUpdatedTimestamp())).zzg("PendingParticipantId", paramTurnBasedMatch.getPendingParticipantId()).zzg("MatchStatus", Integer.valueOf(paramTurnBasedMatch.getStatus())).zzg("TurnStatus", Integer.valueOf(paramTurnBasedMatch.getTurnStatus())).zzg("Description", paramTurnBasedMatch.getDescription()).zzg("Variant", Integer.valueOf(paramTurnBasedMatch.getVariant())).zzg("Data", paramTurnBasedMatch.getData()).zzg("Version", Integer.valueOf(paramTurnBasedMatch.getVersion())).zzg("Participants", paramTurnBasedMatch.getParticipants()).zzg("RematchId", paramTurnBasedMatch.getRematchId()).zzg("PreviousData", paramTurnBasedMatch.getPreviousMatchData()).zzg("MatchNumber", Integer.valueOf(paramTurnBasedMatch.getMatchNumber())).zzg("AutoMatchCriteria", paramTurnBasedMatch.getAutoMatchCriteria()).zzg("AvailableAutoMatchSlots", Integer.valueOf(paramTurnBasedMatch.getAvailableAutoMatchSlots())).zzg("LocallyModified", Boolean.valueOf(paramTurnBasedMatch.isLocallyModified())).zzg("DescriptionParticipantId", paramTurnBasedMatch.getDescriptionParticipantId()).toString();
  }
  
  static String zzb(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    paramTurnBasedMatch = paramTurnBasedMatch.getParticipants();
    int j = paramTurnBasedMatch.size();
    int i = 0;
    while (i < j)
    {
      Participant localParticipant = (Participant)paramTurnBasedMatch.get(i);
      Player localPlayer = localParticipant.getPlayer();
      if ((localPlayer != null) && (localPlayer.getPlayerId().equals(paramString))) {
        return localParticipant.getParticipantId();
      }
      i += 1;
    }
    return null;
  }
  
  static Participant zzc(TurnBasedMatch paramTurnBasedMatch, String paramString)
  {
    ArrayList localArrayList = paramTurnBasedMatch.getParticipants();
    int j = localArrayList.size();
    int i = 0;
    while (i < j)
    {
      Participant localParticipant = (Participant)localArrayList.get(i);
      if (localParticipant.getParticipantId().equals(paramString)) {
        return localParticipant;
      }
      i += 1;
    }
    throw new IllegalStateException("Participant " + paramString + " is not in match " + paramTurnBasedMatch.getMatchId());
  }
  
  static ArrayList<String> zzc(TurnBasedMatch paramTurnBasedMatch)
  {
    paramTurnBasedMatch = paramTurnBasedMatch.getParticipants();
    int j = paramTurnBasedMatch.size();
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(((Participant)paramTurnBasedMatch.get(i)).getParticipantId());
      i += 1;
    }
    return localArrayList;
  }
  
  public boolean canRematch()
  {
    return (this.zzaKl == 2) && (this.zzaKn == null);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public TurnBasedMatch freeze()
  {
    return this;
  }
  
  public Bundle getAutoMatchCriteria()
  {
    return this.zzaJZ;
  }
  
  public int getAvailableAutoMatchSlots()
  {
    if (this.zzaJZ == null) {
      return 0;
    }
    return this.zzaJZ.getInt("max_automatch_players");
  }
  
  public long getCreationTimestamp()
  {
    return this.zzaJF;
  }
  
  public String getCreatorId()
  {
    return this.zzaKb;
  }
  
  public byte[] getData()
  {
    return this.zzaKm;
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzaxl, paramCharArrayBuffer);
  }
  
  public Participant getDescriptionParticipant()
  {
    String str = getDescriptionParticipantId();
    if (str == null) {
      return null;
    }
    return getParticipant(str);
  }
  
  public String getDescriptionParticipantId()
  {
    return this.zzaKs;
  }
  
  public Game getGame()
  {
    return this.zzaJE;
  }
  
  public long getLastUpdatedTimestamp()
  {
    return this.zzaDt;
  }
  
  public String getLastUpdaterId()
  {
    return this.zzaKj;
  }
  
  public String getMatchId()
  {
    return this.zzaER;
  }
  
  public int getMatchNumber()
  {
    return this.zzaKp;
  }
  
  public Participant getParticipant(String paramString)
  {
    return zzc(this, paramString);
  }
  
  public String getParticipantId(String paramString)
  {
    return zzb(this, paramString);
  }
  
  public ArrayList<String> getParticipantIds()
  {
    return zzc(this);
  }
  
  public int getParticipantStatus(String paramString)
  {
    return zza(this, paramString);
  }
  
  public ArrayList<Participant> getParticipants()
  {
    return new ArrayList(this.zzaJI);
  }
  
  public String getPendingParticipantId()
  {
    return this.zzaKk;
  }
  
  public byte[] getPreviousMatchData()
  {
    return this.zzaKo;
  }
  
  public String getRematchId()
  {
    return this.zzaKn;
  }
  
  public int getStatus()
  {
    return this.zzaKl;
  }
  
  public int getTurnStatus()
  {
    return this.zzaKq;
  }
  
  public int getVariant()
  {
    return this.zzaJJ;
  }
  
  public int getVersion()
  {
    return this.mVersion;
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
  
  public boolean isLocallyModified()
  {
    return this.zzaKr;
  }
  
  public String toString()
  {
    return zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    TurnBasedMatchEntityCreator.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\multiplayer\turnbased\TurnBasedMatchEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */