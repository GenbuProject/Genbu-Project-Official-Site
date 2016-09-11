package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantRef;
import java.util.ArrayList;

public final class TurnBasedMatchRef
  extends zzc
  implements TurnBasedMatch
{
  private final int zzaDQ;
  private final Game zzaJc;
  
  TurnBasedMatchRef(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    super(paramDataHolder, paramInt1);
    this.zzaJc = new GameRef(paramDataHolder, paramInt1);
    this.zzaDQ = paramInt2;
  }
  
  public boolean canRematch()
  {
    return (getTurnStatus() == 3) && (getRematchId() == null) && (getParticipants().size() > 1);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return TurnBasedMatchEntity.zza(this, paramObject);
  }
  
  public TurnBasedMatch freeze()
  {
    return new TurnBasedMatchEntity(this);
  }
  
  public Bundle getAutoMatchCriteria()
  {
    if (!getBoolean("has_automatch_criteria")) {
      return null;
    }
    return TurnBasedMatchConfig.createAutoMatchCriteria(getInteger("automatch_min_players"), getInteger("automatch_max_players"), getLong("automatch_bit_mask"));
  }
  
  public int getAvailableAutoMatchSlots()
  {
    if (!getBoolean("has_automatch_criteria")) {
      return 0;
    }
    return getInteger("automatch_max_players");
  }
  
  public long getCreationTimestamp()
  {
    return getLong("creation_timestamp");
  }
  
  public String getCreatorId()
  {
    return getString("creator_external");
  }
  
  public byte[] getData()
  {
    return getByteArray("data");
  }
  
  public String getDescription()
  {
    return getString("description");
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    zza("description", paramCharArrayBuffer);
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
    return getString("description_participant_id");
  }
  
  public Game getGame()
  {
    return this.zzaJc;
  }
  
  public long getLastUpdatedTimestamp()
  {
    return getLong("last_updated_timestamp");
  }
  
  public String getLastUpdaterId()
  {
    return getString("last_updater_external");
  }
  
  public String getMatchId()
  {
    return getString("external_match_id");
  }
  
  public int getMatchNumber()
  {
    return getInteger("match_number");
  }
  
  public Participant getParticipant(String paramString)
  {
    return TurnBasedMatchEntity.zzc(this, paramString);
  }
  
  public String getParticipantId(String paramString)
  {
    return TurnBasedMatchEntity.zzb(this, paramString);
  }
  
  public ArrayList<String> getParticipantIds()
  {
    return TurnBasedMatchEntity.zzc(this);
  }
  
  public int getParticipantStatus(String paramString)
  {
    return TurnBasedMatchEntity.zza(this, paramString);
  }
  
  public ArrayList<Participant> getParticipants()
  {
    ArrayList localArrayList = new ArrayList(this.zzaDQ);
    int i = 0;
    while (i < this.zzaDQ)
    {
      localArrayList.add(new ParticipantRef(this.zzahi, this.zzaje + i));
      i += 1;
    }
    return localArrayList;
  }
  
  public String getPendingParticipantId()
  {
    return getString("pending_participant_external");
  }
  
  public byte[] getPreviousMatchData()
  {
    return getByteArray("previous_match_data");
  }
  
  public String getRematchId()
  {
    return getString("rematch_id");
  }
  
  public int getStatus()
  {
    return getInteger("status");
  }
  
  public int getTurnStatus()
  {
    return getInteger("user_match_status");
  }
  
  public int getVariant()
  {
    return getInteger("variant");
  }
  
  public int getVersion()
  {
    return getInteger("version");
  }
  
  public int hashCode()
  {
    return TurnBasedMatchEntity.zza(this);
  }
  
  public boolean isLocallyModified()
  {
    return getBoolean("upsync_required");
  }
  
  public String toString()
  {
    return TurnBasedMatchEntity.zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((TurnBasedMatchEntity)freeze()).writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\multiplayer\turnbased\TurnBasedMatchRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */