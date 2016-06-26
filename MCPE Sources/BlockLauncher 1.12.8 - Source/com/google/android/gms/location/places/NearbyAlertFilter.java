package com.google.android.gms.location.places;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class NearbyAlertFilter
  extends zza
  implements SafeParcelable
{
  public static final zzd CREATOR = new zzd();
  final int mVersionCode;
  final List<String> zzaPj;
  final List<Integer> zzaPk;
  final List<UserDataType> zzaPl;
  final String zzaPm;
  final boolean zzaPn;
  private final Set<String> zzaPo;
  private final Set<Integer> zzaPp;
  private final Set<UserDataType> zzaPq;
  
  NearbyAlertFilter(int paramInt, @Nullable List<String> paramList, @Nullable List<Integer> paramList1, @Nullable List<UserDataType> paramList2, @Nullable String paramString, boolean paramBoolean)
  {
    this.mVersionCode = paramInt;
    if (paramList1 == null)
    {
      paramList1 = Collections.emptyList();
      this.zzaPk = paramList1;
      if (paramList2 != null) {
        break label103;
      }
      paramList1 = Collections.emptyList();
      label31:
      this.zzaPl = paramList1;
      if (paramList != null) {
        break label112;
      }
    }
    label103:
    label112:
    for (paramList = Collections.emptyList();; paramList = Collections.unmodifiableList(paramList))
    {
      this.zzaPj = paramList;
      this.zzaPp = zzw(this.zzaPk);
      this.zzaPq = zzw(this.zzaPl);
      this.zzaPo = zzw(this.zzaPj);
      this.zzaPm = paramString;
      this.zzaPn = paramBoolean;
      return;
      paramList1 = Collections.unmodifiableList(paramList1);
      break;
      paramList1 = Collections.unmodifiableList(paramList2);
      break label31;
    }
  }
  
  public static NearbyAlertFilter zzh(Collection<String> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      throw new IllegalArgumentException("NearbyAlertFilters must contain at least oneplace ID to match results with.");
    }
    return new NearbyAlertFilter(0, zzf(paramCollection), null, null, null, false);
  }
  
  public static NearbyAlertFilter zzi(Collection<Integer> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      throw new IllegalArgumentException("NearbyAlertFilters must contain at least oneplace type to match results with.");
    }
    return new NearbyAlertFilter(0, null, zzf(paramCollection), null, null, false);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof NearbyAlertFilter)) {
        return false;
      }
      paramObject = (NearbyAlertFilter)paramObject;
      if ((this.zzaPm == null) && (((NearbyAlertFilter)paramObject).zzaPm != null)) {
        return false;
      }
    } while ((this.zzaPp.equals(((NearbyAlertFilter)paramObject).zzaPp)) && (this.zzaPq.equals(((NearbyAlertFilter)paramObject).zzaPq)) && (this.zzaPo.equals(((NearbyAlertFilter)paramObject).zzaPo)) && ((this.zzaPm == null) || (this.zzaPm.equals(((NearbyAlertFilter)paramObject).zzaPm))) && (this.zzaPn == ((NearbyAlertFilter)paramObject).zzyX()));
    return false;
  }
  
  public Set<String> getPlaceIds()
  {
    return this.zzaPo;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzaPp, this.zzaPq, this.zzaPo, this.zzaPm, Boolean.valueOf(this.zzaPn) });
  }
  
  public String toString()
  {
    zzw.zza localzza = zzw.zzy(this);
    if (!this.zzaPp.isEmpty()) {
      localzza.zzg("types", this.zzaPp);
    }
    if (!this.zzaPo.isEmpty()) {
      localzza.zzg("placeIds", this.zzaPo);
    }
    if (!this.zzaPq.isEmpty()) {
      localzza.zzg("requestedUserDataTypes", this.zzaPq);
    }
    if (this.zzaPm != null) {
      localzza.zzg("chainName", this.zzaPm);
    }
    localzza.zzg("Beacon required: ", Boolean.valueOf(this.zzaPn));
    return localzza.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
  
  public boolean zzyX()
  {
    return this.zzaPn;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\NearbyAlertFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */