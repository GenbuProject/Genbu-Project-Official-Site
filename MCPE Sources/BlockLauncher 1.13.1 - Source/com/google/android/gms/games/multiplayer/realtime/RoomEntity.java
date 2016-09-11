package com.google.android.gms.games.multiplayer.realtime;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.zzms;
import java.util.ArrayList;

public final class RoomEntity
  extends GamesDowngradeableSafeParcel
  implements Room
{
  public static final Parcelable.Creator<RoomEntity> CREATOR = new RoomEntityCreatorCompat();
  private final int mVersionCode;
  private final String zzaEw;
  private final long zzaJF;
  private final ArrayList<ParticipantEntity> zzaJI;
  private final int zzaJJ;
  private final Bundle zzaJZ;
  private final String zzaKb;
  private final int zzaKc;
  private final int zzaKd;
  private final String zzaxl;
  
  RoomEntity(int paramInt1, String paramString1, String paramString2, long paramLong, int paramInt2, String paramString3, int paramInt3, Bundle paramBundle, ArrayList<ParticipantEntity> paramArrayList, int paramInt4)
  {
    this.mVersionCode = paramInt1;
    this.zzaEw = paramString1;
    this.zzaKb = paramString2;
    this.zzaJF = paramLong;
    this.zzaKc = paramInt2;
    this.zzaxl = paramString3;
    this.zzaJJ = paramInt3;
    this.zzaJZ = paramBundle;
    this.zzaJI = paramArrayList;
    this.zzaKd = paramInt4;
  }
  
  public RoomEntity(Room paramRoom)
  {
    this.mVersionCode = 2;
    this.zzaEw = paramRoom.getRoomId();
    this.zzaKb = paramRoom.getCreatorId();
    this.zzaJF = paramRoom.getCreationTimestamp();
    this.zzaKc = paramRoom.getStatus();
    this.zzaxl = paramRoom.getDescription();
    this.zzaJJ = paramRoom.getVariant();
    this.zzaJZ = paramRoom.getAutoMatchCriteria();
    ArrayList localArrayList = paramRoom.getParticipants();
    int j = localArrayList.size();
    this.zzaJI = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      this.zzaJI.add((ParticipantEntity)((Participant)localArrayList.get(i)).freeze());
      i += 1;
    }
    this.zzaKd = paramRoom.getAutoMatchWaitEstimateSeconds();
  }
  
  static int zza(Room paramRoom)
  {
    return zzw.hashCode(new Object[] { paramRoom.getRoomId(), paramRoom.getCreatorId(), Long.valueOf(paramRoom.getCreationTimestamp()), Integer.valueOf(paramRoom.getStatus()), paramRoom.getDescription(), Integer.valueOf(paramRoom.getVariant()), paramRoom.getAutoMatchCriteria(), paramRoom.getParticipants(), Integer.valueOf(paramRoom.getAutoMatchWaitEstimateSeconds()) });
  }
  
  static int zza(Room paramRoom, String paramString)
  {
    ArrayList localArrayList = paramRoom.getParticipants();
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
    throw new IllegalStateException("Participant " + paramString + " is not in room " + paramRoom.getRoomId());
  }
  
  static boolean zza(Room paramRoom, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Room)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramRoom == paramObject);
      paramObject = (Room)paramObject;
      if ((!zzw.equal(((Room)paramObject).getRoomId(), paramRoom.getRoomId())) || (!zzw.equal(((Room)paramObject).getCreatorId(), paramRoom.getCreatorId())) || (!zzw.equal(Long.valueOf(((Room)paramObject).getCreationTimestamp()), Long.valueOf(paramRoom.getCreationTimestamp()))) || (!zzw.equal(Integer.valueOf(((Room)paramObject).getStatus()), Integer.valueOf(paramRoom.getStatus()))) || (!zzw.equal(((Room)paramObject).getDescription(), paramRoom.getDescription())) || (!zzw.equal(Integer.valueOf(((Room)paramObject).getVariant()), Integer.valueOf(paramRoom.getVariant()))) || (!zzw.equal(((Room)paramObject).getAutoMatchCriteria(), paramRoom.getAutoMatchCriteria())) || (!zzw.equal(((Room)paramObject).getParticipants(), paramRoom.getParticipants()))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(Integer.valueOf(((Room)paramObject).getAutoMatchWaitEstimateSeconds()), Integer.valueOf(paramRoom.getAutoMatchWaitEstimateSeconds())));
    return false;
  }
  
  static String zzb(Room paramRoom)
  {
    return zzw.zzy(paramRoom).zzg("RoomId", paramRoom.getRoomId()).zzg("CreatorId", paramRoom.getCreatorId()).zzg("CreationTimestamp", Long.valueOf(paramRoom.getCreationTimestamp())).zzg("RoomStatus", Integer.valueOf(paramRoom.getStatus())).zzg("Description", paramRoom.getDescription()).zzg("Variant", Integer.valueOf(paramRoom.getVariant())).zzg("AutoMatchCriteria", paramRoom.getAutoMatchCriteria()).zzg("Participants", paramRoom.getParticipants()).zzg("AutoMatchWaitEstimateSeconds", Integer.valueOf(paramRoom.getAutoMatchWaitEstimateSeconds())).toString();
  }
  
  static String zzb(Room paramRoom, String paramString)
  {
    paramRoom = paramRoom.getParticipants();
    int j = paramRoom.size();
    int i = 0;
    while (i < j)
    {
      Participant localParticipant = (Participant)paramRoom.get(i);
      Player localPlayer = localParticipant.getPlayer();
      if ((localPlayer != null) && (localPlayer.getPlayerId().equals(paramString))) {
        return localParticipant.getParticipantId();
      }
      i += 1;
    }
    return null;
  }
  
  static Participant zzc(Room paramRoom, String paramString)
  {
    ArrayList localArrayList = paramRoom.getParticipants();
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
    throw new IllegalStateException("Participant " + paramString + " is not in match " + paramRoom.getRoomId());
  }
  
  static ArrayList<String> zzc(Room paramRoom)
  {
    paramRoom = paramRoom.getParticipants();
    int j = paramRoom.size();
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(((Participant)paramRoom.get(i)).getParticipantId());
      i += 1;
    }
    return localArrayList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public Room freeze()
  {
    return this;
  }
  
  public Bundle getAutoMatchCriteria()
  {
    return this.zzaJZ;
  }
  
  public int getAutoMatchWaitEstimateSeconds()
  {
    return this.zzaKd;
  }
  
  public long getCreationTimestamp()
  {
    return this.zzaJF;
  }
  
  public String getCreatorId()
  {
    return this.zzaKb;
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public void getDescription(CharArrayBuffer paramCharArrayBuffer)
  {
    zzms.zzb(this.zzaxl, paramCharArrayBuffer);
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
  
  public String getRoomId()
  {
    return this.zzaEw;
  }
  
  public int getStatus()
  {
    return this.zzaKc;
  }
  
  public int getVariant()
  {
    return this.zzaJJ;
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
  
  public String toString()
  {
    return zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (!zzqC()) {
      RoomEntityCreator.zza(this, paramParcel, paramInt);
    }
    for (;;)
    {
      return;
      paramParcel.writeString(this.zzaEw);
      paramParcel.writeString(this.zzaKb);
      paramParcel.writeLong(this.zzaJF);
      paramParcel.writeInt(this.zzaKc);
      paramParcel.writeString(this.zzaxl);
      paramParcel.writeInt(this.zzaJJ);
      paramParcel.writeBundle(this.zzaJZ);
      int j = this.zzaJI.size();
      paramParcel.writeInt(j);
      int i = 0;
      while (i < j)
      {
        ((ParticipantEntity)this.zzaJI.get(i)).writeToParcel(paramParcel, paramInt);
        i += 1;
      }
    }
  }
  
  static final class RoomEntityCreatorCompat
    extends RoomEntityCreator
  {
    public RoomEntity zzev(Parcel paramParcel)
    {
      if ((RoomEntity.zzc(RoomEntity.zzvC())) || (RoomEntity.zzdG(RoomEntity.class.getCanonicalName()))) {
        return super.zzev(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      long l = paramParcel.readLong();
      int j = paramParcel.readInt();
      String str3 = paramParcel.readString();
      int k = paramParcel.readInt();
      Bundle localBundle = paramParcel.readBundle();
      int m = paramParcel.readInt();
      ArrayList localArrayList = new ArrayList(m);
      int i = 0;
      while (i < m)
      {
        localArrayList.add(ParticipantEntity.CREATOR.createFromParcel(paramParcel));
        i += 1;
      }
      return new RoomEntity(2, str1, str2, l, j, str3, k, localBundle, localArrayList, -1);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\multiplayer\realtime\RoomEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */