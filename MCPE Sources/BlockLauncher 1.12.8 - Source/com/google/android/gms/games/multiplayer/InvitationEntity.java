package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import java.util.ArrayList;

public final class InvitationEntity
  extends GamesDowngradeableSafeParcel
  implements Invitation
{
  public static final Parcelable.Creator<InvitationEntity> CREATOR = new InvitationEntityCreatorCompat();
  private final int mVersionCode;
  private final String zzUO;
  private final GameEntity zzaJE;
  private final long zzaJF;
  private final int zzaJG;
  private final ParticipantEntity zzaJH;
  private final ArrayList<ParticipantEntity> zzaJI;
  private final int zzaJJ;
  private final int zzaJK;
  
  InvitationEntity(int paramInt1, GameEntity paramGameEntity, String paramString, long paramLong, int paramInt2, ParticipantEntity paramParticipantEntity, ArrayList<ParticipantEntity> paramArrayList, int paramInt3, int paramInt4)
  {
    this.mVersionCode = paramInt1;
    this.zzaJE = paramGameEntity;
    this.zzUO = paramString;
    this.zzaJF = paramLong;
    this.zzaJG = paramInt2;
    this.zzaJH = paramParticipantEntity;
    this.zzaJI = paramArrayList;
    this.zzaJJ = paramInt3;
    this.zzaJK = paramInt4;
  }
  
  InvitationEntity(Invitation paramInvitation)
  {
    this.mVersionCode = 2;
    this.zzaJE = new GameEntity(paramInvitation.getGame());
    this.zzUO = paramInvitation.getInvitationId();
    this.zzaJF = paramInvitation.getCreationTimestamp();
    this.zzaJG = paramInvitation.getInvitationType();
    this.zzaJJ = paramInvitation.getVariant();
    this.zzaJK = paramInvitation.getAvailableAutoMatchSlots();
    String str = paramInvitation.getInviter().getParticipantId();
    Participant localParticipant = null;
    ArrayList localArrayList = paramInvitation.getParticipants();
    int j = localArrayList.size();
    this.zzaJI = new ArrayList(j);
    int i = 0;
    paramInvitation = localParticipant;
    while (i < j)
    {
      localParticipant = (Participant)localArrayList.get(i);
      if (localParticipant.getParticipantId().equals(str)) {
        paramInvitation = localParticipant;
      }
      this.zzaJI.add((ParticipantEntity)localParticipant.freeze());
      i += 1;
    }
    zzx.zzb(paramInvitation, "Must have a valid inviter!");
    this.zzaJH = ((ParticipantEntity)paramInvitation.freeze());
  }
  
  static int zza(Invitation paramInvitation)
  {
    return zzw.hashCode(new Object[] { paramInvitation.getGame(), paramInvitation.getInvitationId(), Long.valueOf(paramInvitation.getCreationTimestamp()), Integer.valueOf(paramInvitation.getInvitationType()), paramInvitation.getInviter(), paramInvitation.getParticipants(), Integer.valueOf(paramInvitation.getVariant()), Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots()) });
  }
  
  static boolean zza(Invitation paramInvitation, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Invitation)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramInvitation == paramObject);
      paramObject = (Invitation)paramObject;
      if ((!zzw.equal(((Invitation)paramObject).getGame(), paramInvitation.getGame())) || (!zzw.equal(((Invitation)paramObject).getInvitationId(), paramInvitation.getInvitationId())) || (!zzw.equal(Long.valueOf(((Invitation)paramObject).getCreationTimestamp()), Long.valueOf(paramInvitation.getCreationTimestamp()))) || (!zzw.equal(Integer.valueOf(((Invitation)paramObject).getInvitationType()), Integer.valueOf(paramInvitation.getInvitationType()))) || (!zzw.equal(((Invitation)paramObject).getInviter(), paramInvitation.getInviter())) || (!zzw.equal(((Invitation)paramObject).getParticipants(), paramInvitation.getParticipants())) || (!zzw.equal(Integer.valueOf(((Invitation)paramObject).getVariant()), Integer.valueOf(paramInvitation.getVariant())))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(Integer.valueOf(((Invitation)paramObject).getAvailableAutoMatchSlots()), Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots())));
    return false;
  }
  
  static String zzb(Invitation paramInvitation)
  {
    return zzw.zzy(paramInvitation).zzg("Game", paramInvitation.getGame()).zzg("InvitationId", paramInvitation.getInvitationId()).zzg("CreationTimestamp", Long.valueOf(paramInvitation.getCreationTimestamp())).zzg("InvitationType", Integer.valueOf(paramInvitation.getInvitationType())).zzg("Inviter", paramInvitation.getInviter()).zzg("Participants", paramInvitation.getParticipants()).zzg("Variant", Integer.valueOf(paramInvitation.getVariant())).zzg("AvailableAutoMatchSlots", Integer.valueOf(paramInvitation.getAvailableAutoMatchSlots())).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public Invitation freeze()
  {
    return this;
  }
  
  public int getAvailableAutoMatchSlots()
  {
    return this.zzaJK;
  }
  
  public long getCreationTimestamp()
  {
    return this.zzaJF;
  }
  
  public Game getGame()
  {
    return this.zzaJE;
  }
  
  public String getInvitationId()
  {
    return this.zzUO;
  }
  
  public int getInvitationType()
  {
    return this.zzaJG;
  }
  
  public Participant getInviter()
  {
    return this.zzaJH;
  }
  
  public ArrayList<Participant> getParticipants()
  {
    return new ArrayList(this.zzaJI);
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
      InvitationEntityCreator.zza(this, paramParcel, paramInt);
    }
    for (;;)
    {
      return;
      this.zzaJE.writeToParcel(paramParcel, paramInt);
      paramParcel.writeString(this.zzUO);
      paramParcel.writeLong(this.zzaJF);
      paramParcel.writeInt(this.zzaJG);
      this.zzaJH.writeToParcel(paramParcel, paramInt);
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
  
  static final class InvitationEntityCreatorCompat
    extends InvitationEntityCreator
  {
    public InvitationEntity zzer(Parcel paramParcel)
    {
      if ((InvitationEntity.zzc(InvitationEntity.zzvC())) || (InvitationEntity.zzdG(InvitationEntity.class.getCanonicalName()))) {
        return super.zzer(paramParcel);
      }
      GameEntity localGameEntity = (GameEntity)GameEntity.CREATOR.createFromParcel(paramParcel);
      String str = paramParcel.readString();
      long l = paramParcel.readLong();
      int j = paramParcel.readInt();
      ParticipantEntity localParticipantEntity = (ParticipantEntity)ParticipantEntity.CREATOR.createFromParcel(paramParcel);
      int k = paramParcel.readInt();
      ArrayList localArrayList = new ArrayList(k);
      int i = 0;
      while (i < k)
      {
        localArrayList.add(ParticipantEntity.CREATOR.createFromParcel(paramParcel));
        i += 1;
      }
      return new InvitationEntity(2, localGameEntity, str, l, j, localParticipantEntity, localArrayList, -1, 0);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\multiplayer\InvitationEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */