package com.google.android.gms.location.places;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AutocompleteFilter
  implements SafeParcelable
{
  public static final zzc CREATOR = new zzc();
  public static final int TYPE_FILTER_ADDRESS = 2;
  public static final int TYPE_FILTER_CITIES = 5;
  public static final int TYPE_FILTER_ESTABLISHMENT = 34;
  public static final int TYPE_FILTER_GEOCODE = 1007;
  public static final int TYPE_FILTER_NONE = 0;
  public static final int TYPE_FILTER_REGIONS = 4;
  final int mVersionCode;
  final boolean zzaPg;
  final List<Integer> zzaPh;
  final int zzaPi;
  
  AutocompleteFilter(int paramInt, boolean paramBoolean, List<Integer> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzaPh = paramList;
    this.zzaPi = zzg(paramList);
    if (this.mVersionCode < 1)
    {
      if (!paramBoolean) {}
      for (paramBoolean = bool;; paramBoolean = false)
      {
        this.zzaPg = paramBoolean;
        return;
      }
    }
    this.zzaPg = paramBoolean;
  }
  
  @Deprecated
  public static AutocompleteFilter create(@Nullable Collection<Integer> paramCollection)
  {
    return new Builder().setTypeFilter(zzg(paramCollection)).build();
  }
  
  private static int zzg(@Nullable Collection<Integer> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {
      return 0;
    }
    return ((Integer)paramCollection.iterator().next()).intValue();
  }
  
  private static List<Integer> zzhJ(int paramInt)
  {
    return Arrays.asList(new Integer[] { Integer.valueOf(paramInt) });
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
      if (!(paramObject instanceof AutocompleteFilter)) {
        return false;
      }
      paramObject = (AutocompleteFilter)paramObject;
    } while ((this.zzaPi == this.zzaPi) && (this.zzaPg == ((AutocompleteFilter)paramObject).zzaPg));
    return false;
  }
  
  @Deprecated
  public Set<Integer> getPlaceTypes()
  {
    return new HashSet(zzhJ(this.zzaPi));
  }
  
  public int getTypeFilter()
  {
    return this.zzaPi;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Boolean.valueOf(this.zzaPg), Integer.valueOf(this.zzaPi) });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("includeQueryPredictions", Boolean.valueOf(this.zzaPg)).zzg("typeFilter", Integer.valueOf(this.zzaPi)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public static final class Builder
  {
    private boolean zzaPg = false;
    private int zzaPi = 0;
    
    public AutocompleteFilter build()
    {
      return new AutocompleteFilter(1, this.zzaPg, AutocompleteFilter.zzhK(this.zzaPi));
    }
    
    public Builder setTypeFilter(int paramInt)
    {
      this.zzaPi = paramInt;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\AutocompleteFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */