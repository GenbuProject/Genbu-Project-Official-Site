package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public final class MostRecentGameInfoEntity
  implements SafeParcelable, MostRecentGameInfo
{
  public static final MostRecentGameInfoEntityCreator CREATOR = new MostRecentGameInfoEntityCreator();
  private final int mVersionCode;
  private final String zzaIm;
  private final String zzaIn;
  private final long zzaIo;
  private final Uri zzaIp;
  private final Uri zzaIq;
  private final Uri zzaIr;
  
  MostRecentGameInfoEntity(int paramInt, String paramString1, String paramString2, long paramLong, Uri paramUri1, Uri paramUri2, Uri paramUri3)
  {
    this.mVersionCode = paramInt;
    this.zzaIm = paramString1;
    this.zzaIn = paramString2;
    this.zzaIo = paramLong;
    this.zzaIp = paramUri1;
    this.zzaIq = paramUri2;
    this.zzaIr = paramUri3;
  }
  
  public MostRecentGameInfoEntity(MostRecentGameInfo paramMostRecentGameInfo)
  {
    this.mVersionCode = 2;
    this.zzaIm = paramMostRecentGameInfo.zzxy();
    this.zzaIn = paramMostRecentGameInfo.zzxz();
    this.zzaIo = paramMostRecentGameInfo.zzxA();
    this.zzaIp = paramMostRecentGameInfo.zzxB();
    this.zzaIq = paramMostRecentGameInfo.zzxC();
    this.zzaIr = paramMostRecentGameInfo.zzxD();
  }
  
  static int zza(MostRecentGameInfo paramMostRecentGameInfo)
  {
    return zzw.hashCode(new Object[] { paramMostRecentGameInfo.zzxy(), paramMostRecentGameInfo.zzxz(), Long.valueOf(paramMostRecentGameInfo.zzxA()), paramMostRecentGameInfo.zzxB(), paramMostRecentGameInfo.zzxC(), paramMostRecentGameInfo.zzxD() });
  }
  
  static boolean zza(MostRecentGameInfo paramMostRecentGameInfo, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof MostRecentGameInfo)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramMostRecentGameInfo == paramObject);
      paramObject = (MostRecentGameInfo)paramObject;
      if ((!zzw.equal(((MostRecentGameInfo)paramObject).zzxy(), paramMostRecentGameInfo.zzxy())) || (!zzw.equal(((MostRecentGameInfo)paramObject).zzxz(), paramMostRecentGameInfo.zzxz())) || (!zzw.equal(Long.valueOf(((MostRecentGameInfo)paramObject).zzxA()), Long.valueOf(paramMostRecentGameInfo.zzxA()))) || (!zzw.equal(((MostRecentGameInfo)paramObject).zzxB(), paramMostRecentGameInfo.zzxB())) || (!zzw.equal(((MostRecentGameInfo)paramObject).zzxC(), paramMostRecentGameInfo.zzxC()))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((MostRecentGameInfo)paramObject).zzxD(), paramMostRecentGameInfo.zzxD()));
    return false;
  }
  
  static String zzb(MostRecentGameInfo paramMostRecentGameInfo)
  {
    return zzw.zzy(paramMostRecentGameInfo).zzg("GameId", paramMostRecentGameInfo.zzxy()).zzg("GameName", paramMostRecentGameInfo.zzxz()).zzg("ActivityTimestampMillis", Long.valueOf(paramMostRecentGameInfo.zzxA())).zzg("GameIconUri", paramMostRecentGameInfo.zzxB()).zzg("GameHiResUri", paramMostRecentGameInfo.zzxC()).zzg("GameFeaturedUri", paramMostRecentGameInfo.zzxD()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
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
    MostRecentGameInfoEntityCreator.zza(this, paramParcel, paramInt);
  }
  
  public long zzxA()
  {
    return this.zzaIo;
  }
  
  public Uri zzxB()
  {
    return this.zzaIp;
  }
  
  public Uri zzxC()
  {
    return this.zzaIq;
  }
  
  public Uri zzxD()
  {
    return this.zzaIr;
  }
  
  public MostRecentGameInfo zzxE()
  {
    return this;
  }
  
  public String zzxy()
  {
    return this.zzaIm;
  }
  
  public String zzxz()
  {
    return this.zzaIn;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\internal\player\MostRecentGameInfoEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */