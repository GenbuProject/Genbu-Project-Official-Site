package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.List;

public class PlaceUserData
  implements SafeParcelable
{
  public static final zze CREATOR = new zze();
  final int mVersionCode;
  private final String zzVa;
  private final String zzaPH;
  private final List<PlaceAlias> zzaRg;
  
  PlaceUserData(int paramInt, String paramString1, String paramString2, List<PlaceAlias> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzVa = paramString1;
    this.zzaPH = paramString2;
    this.zzaRg = paramList;
  }
  
  public int describeContents()
  {
    zze localzze = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof PlaceUserData)) {
        return false;
      }
      paramObject = (PlaceUserData)paramObject;
    } while ((this.zzVa.equals(((PlaceUserData)paramObject).zzVa)) && (this.zzaPH.equals(((PlaceUserData)paramObject).zzaPH)) && (this.zzaRg.equals(((PlaceUserData)paramObject).zzaRg)));
    return false;
  }
  
  public String getPlaceId()
  {
    return this.zzaPH;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzVa, this.zzaPH, this.zzaRg });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("accountName", this.zzVa).zzg("placeId", this.zzaPH).zzg("placeAliases", this.zzaRg).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze localzze = CREATOR;
    zze.zza(this, paramParcel, paramInt);
  }
  
  public String zzzD()
  {
    return this.zzVa;
  }
  
  public List<PlaceAlias> zzzE()
  {
    return this.zzaRg;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\personalized\PlaceUserData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */