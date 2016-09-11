package com.google.android.gms.location.places;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlaceFilter
  extends zza
  implements SafeParcelable
{
  public static final zzg CREATOR = new zzg();
  private static final PlaceFilter zzaPz = new PlaceFilter();
  final int mVersionCode;
  final boolean zzaPA;
  final List<String> zzaPj;
  final List<Integer> zzaPk;
  final List<UserDataType> zzaPl;
  private final Set<String> zzaPo;
  private final Set<Integer> zzaPp;
  private final Set<UserDataType> zzaPq;
  
  public PlaceFilter()
  {
    this(false, null);
  }
  
  PlaceFilter(int paramInt, @Nullable List<Integer> paramList, boolean paramBoolean, @Nullable List<String> paramList1, @Nullable List<UserDataType> paramList2)
  {
    this.mVersionCode = paramInt;
    if (paramList == null)
    {
      paramList = Collections.emptyList();
      this.zzaPk = paramList;
      this.zzaPA = paramBoolean;
      if (paramList2 != null) {
        break label97;
      }
      paramList = Collections.emptyList();
      label36:
      this.zzaPl = paramList;
      if (paramList1 != null) {
        break label106;
      }
    }
    label97:
    label106:
    for (paramList = Collections.emptyList();; paramList = Collections.unmodifiableList(paramList1))
    {
      this.zzaPj = paramList;
      this.zzaPp = zzw(this.zzaPk);
      this.zzaPq = zzw(this.zzaPl);
      this.zzaPo = zzw(this.zzaPj);
      return;
      paramList = Collections.unmodifiableList(paramList);
      break;
      paramList = Collections.unmodifiableList(paramList2);
      break label36;
    }
  }
  
  public PlaceFilter(@Nullable Collection<Integer> paramCollection, boolean paramBoolean, @Nullable Collection<String> paramCollection1, @Nullable Collection<UserDataType> paramCollection2)
  {
    this(0, zzf(paramCollection), paramBoolean, zzf(paramCollection1), zzf(paramCollection2));
  }
  
  public PlaceFilter(boolean paramBoolean, @Nullable Collection<String> paramCollection)
  {
    this(null, paramBoolean, paramCollection, null);
  }
  
  @Deprecated
  public static PlaceFilter zzzd()
  {
    return new zza(null).zzze();
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
      if (!(paramObject instanceof PlaceFilter)) {
        return false;
      }
      paramObject = (PlaceFilter)paramObject;
    } while ((this.zzaPp.equals(((PlaceFilter)paramObject).zzaPp)) && (this.zzaPA == ((PlaceFilter)paramObject).zzaPA) && (this.zzaPq.equals(((PlaceFilter)paramObject).zzaPq)) && (this.zzaPo.equals(((PlaceFilter)paramObject).zzaPo)));
    return false;
  }
  
  public Set<String> getPlaceIds()
  {
    return this.zzaPo;
  }
  
  public Set<Integer> getPlaceTypes()
  {
    return this.zzaPp;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzaPp, Boolean.valueOf(this.zzaPA), this.zzaPq, this.zzaPo });
  }
  
  public boolean isRestrictedToPlacesOpenNow()
  {
    return this.zzaPA;
  }
  
  public String toString()
  {
    zzw.zza localzza = zzw.zzy(this);
    if (!this.zzaPp.isEmpty()) {
      localzza.zzg("types", this.zzaPp);
    }
    localzza.zzg("requireOpenNow", Boolean.valueOf(this.zzaPA));
    if (!this.zzaPo.isEmpty()) {
      localzza.zzg("placeIds", this.zzaPo);
    }
    if (!this.zzaPq.isEmpty()) {
      localzza.zzg("requestedUserDataTypes", this.zzaPq);
    }
    return localzza.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
  
  @Deprecated
  public static final class zza
  {
    private boolean zzaPA = false;
    private Collection<Integer> zzaPB = null;
    private Collection<UserDataType> zzaPC = null;
    private String[] zzaPD = null;
    
    public PlaceFilter zzze()
    {
      List localList = null;
      ArrayList localArrayList1;
      if (this.zzaPB != null)
      {
        localArrayList1 = new ArrayList(this.zzaPB);
        if (this.zzaPC == null) {
          break label75;
        }
      }
      label75:
      for (ArrayList localArrayList2 = new ArrayList(this.zzaPC);; localArrayList2 = null)
      {
        if (this.zzaPD != null) {
          localList = Arrays.asList(this.zzaPD);
        }
        return new PlaceFilter(localArrayList1, this.zzaPA, localList, localArrayList2);
        localArrayList1 = null;
        break;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\PlaceFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */