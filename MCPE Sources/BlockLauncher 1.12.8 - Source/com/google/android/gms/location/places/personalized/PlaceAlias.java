package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public class PlaceAlias
  implements SafeParcelable
{
  public static final zzb CREATOR = new zzb();
  public static final PlaceAlias zzaRc = new PlaceAlias(0, "Home");
  public static final PlaceAlias zzaRd = new PlaceAlias(0, "Work");
  final int mVersionCode;
  private final String zzaRe;
  
  PlaceAlias(int paramInt, String paramString)
  {
    this.mVersionCode = paramInt;
    this.zzaRe = paramString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof PlaceAlias)) {
      return false;
    }
    paramObject = (PlaceAlias)paramObject;
    return zzw.equal(this.zzaRe, ((PlaceAlias)paramObject).zzaRe);
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzaRe });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("alias", this.zzaRe).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public String zzzB()
  {
    return this.zzaRe;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\personalized\PlaceAlias.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */