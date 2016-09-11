package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;

public final class MostRecentGameInfoRef
  extends zzc
  implements MostRecentGameInfo
{
  private final PlayerColumnNames zzaDh;
  
  public MostRecentGameInfoRef(DataHolder paramDataHolder, int paramInt, PlayerColumnNames paramPlayerColumnNames)
  {
    super(paramDataHolder, paramInt);
    this.zzaDh = paramPlayerColumnNames;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return MostRecentGameInfoEntity.zza(this, paramObject);
  }
  
  public int hashCode()
  {
    return MostRecentGameInfoEntity.zza(this);
  }
  
  public String toString()
  {
    return MostRecentGameInfoEntity.zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((MostRecentGameInfoEntity)zzxE()).writeToParcel(paramParcel, paramInt);
  }
  
  public long zzxA()
  {
    return getLong(this.zzaDh.zzaIM);
  }
  
  public Uri zzxB()
  {
    return zzcA(this.zzaDh.zzaIN);
  }
  
  public Uri zzxC()
  {
    return zzcA(this.zzaDh.zzaIO);
  }
  
  public Uri zzxD()
  {
    return zzcA(this.zzaDh.zzaIP);
  }
  
  public MostRecentGameInfo zzxE()
  {
    return new MostRecentGameInfoEntity(this);
  }
  
  public String zzxy()
  {
    return getString(this.zzaDh.zzaIK);
  }
  
  public String zzxz()
  {
    return getString(this.zzaDh.zzaIL);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\internal\player\MostRecentGameInfoRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */