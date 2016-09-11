package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.zzms;

public final class ParticipantEntity
  extends GamesDowngradeableSafeParcel
  implements Participant
{
  public static final Parcelable.Creator<ParticipantEntity> CREATOR = new ParticipantEntityCreatorCompat();
  private final int mVersionCode;
  private final int zzBc;
  private final String zzWQ;
  private final Uri zzaCd;
  private final Uri zzaCe;
  private final String zzaCo;
  private final String zzaCp;
  private final String zzaDX;
  private final PlayerEntity zzaDq;
  private final String zzaFa;
  private final boolean zzaJM;
  private final ParticipantResult zzaJN;
  private final int zzaab;
  
  ParticipantEntity(int paramInt1, String paramString1, String paramString2, Uri paramUri1, Uri paramUri2, int paramInt2, String paramString3, boolean paramBoolean, PlayerEntity paramPlayerEntity, int paramInt3, ParticipantResult paramParticipantResult, String paramString4, String paramString5)
  {
    this.mVersionCode = paramInt1;
    this.zzaFa = paramString1;
    this.zzWQ = paramString2;
    this.zzaCd = paramUri1;
    this.zzaCe = paramUri2;
    this.zzBc = paramInt2;
    this.zzaDX = paramString3;
    this.zzaJM = paramBoolean;
    this.zzaDq = paramPlayerEntity;
    this.zzaab = paramInt3;
    this.zzaJN = paramParticipantResult;
    this.zzaCo = paramString4;
    this.zzaCp = paramString5;
  }
  
  public ParticipantEntity(Participant paramParticipant)
  {
    this.mVersionCode = 3;
    this.zzaFa = paramParticipant.getParticipantId();
    this.zzWQ = paramParticipant.getDisplayName();
    this.zzaCd = paramParticipant.getIconImageUri();
    this.zzaCe = paramParticipant.getHiResImageUri();
    this.zzBc = paramParticipant.getStatus();
    this.zzaDX = paramParticipant.zzwt();
    this.zzaJM = paramParticipant.isConnectedToRoom();
    Object localObject = paramParticipant.getPlayer();
    if (localObject == null) {}
    for (localObject = null;; localObject = new PlayerEntity((Player)localObject))
    {
      this.zzaDq = ((PlayerEntity)localObject);
      this.zzaab = paramParticipant.getCapabilities();
      this.zzaJN = paramParticipant.getResult();
      this.zzaCo = paramParticipant.getIconImageUrl();
      this.zzaCp = paramParticipant.getHiResImageUrl();
      return;
    }
  }
  
  static int zza(Participant paramParticipant)
  {
    return zzw.hashCode(new Object[] { paramParticipant.getPlayer(), Integer.valueOf(paramParticipant.getStatus()), paramParticipant.zzwt(), Boolean.valueOf(paramParticipant.isConnectedToRoom()), paramParticipant.getDisplayName(), paramParticipant.getIconImageUri(), paramParticipant.getHiResImageUri(), Integer.valueOf(paramParticipant.getCapabilities()), paramParticipant.getResult(), paramParticipant.getParticipantId() });
  }
  
  static boolean zza(Participant paramParticipant, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof Participant)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramParticipant == paramObject);
      paramObject = (Participant)paramObject;
      if ((!zzw.equal(((Participant)paramObject).getPlayer(), paramParticipant.getPlayer())) || (!zzw.equal(Integer.valueOf(((Participant)paramObject).getStatus()), Integer.valueOf(paramParticipant.getStatus()))) || (!zzw.equal(((Participant)paramObject).zzwt(), paramParticipant.zzwt())) || (!zzw.equal(Boolean.valueOf(((Participant)paramObject).isConnectedToRoom()), Boolean.valueOf(paramParticipant.isConnectedToRoom()))) || (!zzw.equal(((Participant)paramObject).getDisplayName(), paramParticipant.getDisplayName())) || (!zzw.equal(((Participant)paramObject).getIconImageUri(), paramParticipant.getIconImageUri())) || (!zzw.equal(((Participant)paramObject).getHiResImageUri(), paramParticipant.getHiResImageUri())) || (!zzw.equal(Integer.valueOf(((Participant)paramObject).getCapabilities()), Integer.valueOf(paramParticipant.getCapabilities()))) || (!zzw.equal(((Participant)paramObject).getResult(), paramParticipant.getResult()))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((Participant)paramObject).getParticipantId(), paramParticipant.getParticipantId()));
    return false;
  }
  
  static String zzb(Participant paramParticipant)
  {
    return zzw.zzy(paramParticipant).zzg("ParticipantId", paramParticipant.getParticipantId()).zzg("Player", paramParticipant.getPlayer()).zzg("Status", Integer.valueOf(paramParticipant.getStatus())).zzg("ClientAddress", paramParticipant.zzwt()).zzg("ConnectedToRoom", Boolean.valueOf(paramParticipant.isConnectedToRoom())).zzg("DisplayName", paramParticipant.getDisplayName()).zzg("IconImage", paramParticipant.getIconImageUri()).zzg("IconImageUrl", paramParticipant.getIconImageUrl()).zzg("HiResImage", paramParticipant.getHiResImageUri()).zzg("HiResImageUrl", paramParticipant.getHiResImageUrl()).zzg("Capabilities", Integer.valueOf(paramParticipant.getCapabilities())).zzg("Result", paramParticipant.getResult()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public Participant freeze()
  {
    return this;
  }
  
  public int getCapabilities()
  {
    return this.zzaab;
  }
  
  public String getDisplayName()
  {
    if (this.zzaDq == null) {
      return this.zzWQ;
    }
    return this.zzaDq.getDisplayName();
  }
  
  public void getDisplayName(CharArrayBuffer paramCharArrayBuffer)
  {
    if (this.zzaDq == null)
    {
      zzms.zzb(this.zzWQ, paramCharArrayBuffer);
      return;
    }
    this.zzaDq.getDisplayName(paramCharArrayBuffer);
  }
  
  public Uri getHiResImageUri()
  {
    if (this.zzaDq == null) {
      return this.zzaCe;
    }
    return this.zzaDq.getHiResImageUri();
  }
  
  public String getHiResImageUrl()
  {
    if (this.zzaDq == null) {
      return this.zzaCp;
    }
    return this.zzaDq.getHiResImageUrl();
  }
  
  public Uri getIconImageUri()
  {
    if (this.zzaDq == null) {
      return this.zzaCd;
    }
    return this.zzaDq.getIconImageUri();
  }
  
  public String getIconImageUrl()
  {
    if (this.zzaDq == null) {
      return this.zzaCo;
    }
    return this.zzaDq.getIconImageUrl();
  }
  
  public String getParticipantId()
  {
    return this.zzaFa;
  }
  
  public Player getPlayer()
  {
    return this.zzaDq;
  }
  
  public ParticipantResult getResult()
  {
    return this.zzaJN;
  }
  
  public int getStatus()
  {
    return this.zzBc;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zza(this);
  }
  
  public boolean isConnectedToRoom()
  {
    return this.zzaJM;
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
    Object localObject2 = null;
    int j = 0;
    if (!zzqC())
    {
      ParticipantEntityCreator.zza(this, paramParcel, paramInt);
      return;
    }
    paramParcel.writeString(this.zzaFa);
    paramParcel.writeString(this.zzWQ);
    Object localObject1;
    if (this.zzaCd == null)
    {
      localObject1 = null;
      label46:
      paramParcel.writeString((String)localObject1);
      if (this.zzaCe != null) {
        break label143;
      }
      localObject1 = localObject2;
      label63:
      paramParcel.writeString((String)localObject1);
      paramParcel.writeInt(this.zzBc);
      paramParcel.writeString(this.zzaDX);
      if (!this.zzaJM) {
        break label155;
      }
      i = 1;
      label94:
      paramParcel.writeInt(i);
      if (this.zzaDq != null) {
        break label160;
      }
    }
    label143:
    label155:
    label160:
    for (int i = j;; i = 1)
    {
      paramParcel.writeInt(i);
      if (this.zzaDq == null) {
        break;
      }
      this.zzaDq.writeToParcel(paramParcel, paramInt);
      return;
      localObject1 = this.zzaCd.toString();
      break label46;
      localObject1 = this.zzaCe.toString();
      break label63;
      i = 0;
      break label94;
    }
  }
  
  public String zzwt()
  {
    return this.zzaDX;
  }
  
  static final class ParticipantEntityCreatorCompat
    extends ParticipantEntityCreator
  {
    public ParticipantEntity zzes(Parcel paramParcel)
    {
      int i = 1;
      if ((ParticipantEntity.zzc(ParticipantEntity.zzvC())) || (ParticipantEntity.zzdG(ParticipantEntity.class.getCanonicalName()))) {
        return super.zzes(paramParcel);
      }
      String str1 = paramParcel.readString();
      String str2 = paramParcel.readString();
      Object localObject1 = paramParcel.readString();
      Object localObject2;
      label68:
      int j;
      String str3;
      boolean bool;
      if (localObject1 == null)
      {
        localObject1 = null;
        localObject2 = paramParcel.readString();
        if (localObject2 != null) {
          break label151;
        }
        localObject2 = null;
        j = paramParcel.readInt();
        str3 = paramParcel.readString();
        if (paramParcel.readInt() <= 0) {
          break label161;
        }
        bool = true;
        label89:
        if (paramParcel.readInt() <= 0) {
          break label167;
        }
        label96:
        if (i == 0) {
          break label172;
        }
      }
      label151:
      label161:
      label167:
      label172:
      for (paramParcel = (PlayerEntity)PlayerEntity.CREATOR.createFromParcel(paramParcel);; paramParcel = null)
      {
        return new ParticipantEntity(3, str1, str2, (Uri)localObject1, (Uri)localObject2, j, str3, bool, paramParcel, 7, null, null, null);
        localObject1 = Uri.parse((String)localObject1);
        break;
        localObject2 = Uri.parse((String)localObject2);
        break label68;
        bool = false;
        break label89;
        i = 0;
        break label96;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\multiplayer\ParticipantEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */