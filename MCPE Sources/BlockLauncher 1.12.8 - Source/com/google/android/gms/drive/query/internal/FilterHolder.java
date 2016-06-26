package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.query.Filter;

public class FilterHolder
  implements SafeParcelable
{
  public static final Parcelable.Creator<FilterHolder> CREATOR = new zzd();
  final int mVersionCode;
  private final Filter zzapi;
  final ComparisonFilter<?> zzauk;
  final FieldOnlyFilter zzaul;
  final LogicalFilter zzaum;
  final NotFilter zzaun;
  final InFilter<?> zzauo;
  final MatchAllFilter zzaup;
  final HasFilter zzauq;
  final FullTextSearchFilter zzaur;
  final OwnedByMeFilter zzaus;
  
  FilterHolder(int paramInt, ComparisonFilter<?> paramComparisonFilter, FieldOnlyFilter paramFieldOnlyFilter, LogicalFilter paramLogicalFilter, NotFilter paramNotFilter, InFilter<?> paramInFilter, MatchAllFilter paramMatchAllFilter, HasFilter<?> paramHasFilter, FullTextSearchFilter paramFullTextSearchFilter, OwnedByMeFilter paramOwnedByMeFilter)
  {
    this.mVersionCode = paramInt;
    this.zzauk = paramComparisonFilter;
    this.zzaul = paramFieldOnlyFilter;
    this.zzaum = paramLogicalFilter;
    this.zzaun = paramNotFilter;
    this.zzauo = paramInFilter;
    this.zzaup = paramMatchAllFilter;
    this.zzauq = paramHasFilter;
    this.zzaur = paramFullTextSearchFilter;
    this.zzaus = paramOwnedByMeFilter;
    if (this.zzauk != null)
    {
      this.zzapi = this.zzauk;
      return;
    }
    if (this.zzaul != null)
    {
      this.zzapi = this.zzaul;
      return;
    }
    if (this.zzaum != null)
    {
      this.zzapi = this.zzaum;
      return;
    }
    if (this.zzaun != null)
    {
      this.zzapi = this.zzaun;
      return;
    }
    if (this.zzauo != null)
    {
      this.zzapi = this.zzauo;
      return;
    }
    if (this.zzaup != null)
    {
      this.zzapi = this.zzaup;
      return;
    }
    if (this.zzauq != null)
    {
      this.zzapi = this.zzauq;
      return;
    }
    if (this.zzaur != null)
    {
      this.zzapi = this.zzaur;
      return;
    }
    if (this.zzaus != null)
    {
      this.zzapi = this.zzaus;
      return;
    }
    throw new IllegalArgumentException("At least one filter must be set.");
  }
  
  public FilterHolder(Filter paramFilter)
  {
    zzx.zzb(paramFilter, "Null filter.");
    this.mVersionCode = 2;
    if ((paramFilter instanceof ComparisonFilter))
    {
      localObject = (ComparisonFilter)paramFilter;
      this.zzauk = ((ComparisonFilter)localObject);
      if (!(paramFilter instanceof FieldOnlyFilter)) {
        break label247;
      }
      localObject = (FieldOnlyFilter)paramFilter;
      label45:
      this.zzaul = ((FieldOnlyFilter)localObject);
      if (!(paramFilter instanceof LogicalFilter)) {
        break label252;
      }
      localObject = (LogicalFilter)paramFilter;
      label62:
      this.zzaum = ((LogicalFilter)localObject);
      if (!(paramFilter instanceof NotFilter)) {
        break label257;
      }
      localObject = (NotFilter)paramFilter;
      label79:
      this.zzaun = ((NotFilter)localObject);
      if (!(paramFilter instanceof InFilter)) {
        break label262;
      }
      localObject = (InFilter)paramFilter;
      label96:
      this.zzauo = ((InFilter)localObject);
      if (!(paramFilter instanceof MatchAllFilter)) {
        break label267;
      }
      localObject = (MatchAllFilter)paramFilter;
      label113:
      this.zzaup = ((MatchAllFilter)localObject);
      if (!(paramFilter instanceof HasFilter)) {
        break label272;
      }
      localObject = (HasFilter)paramFilter;
      label130:
      this.zzauq = ((HasFilter)localObject);
      if (!(paramFilter instanceof FullTextSearchFilter)) {
        break label277;
      }
      localObject = (FullTextSearchFilter)paramFilter;
      label147:
      this.zzaur = ((FullTextSearchFilter)localObject);
      if (!(paramFilter instanceof OwnedByMeFilter)) {
        break label282;
      }
    }
    label247:
    label252:
    label257:
    label262:
    label267:
    label272:
    label277:
    label282:
    for (Object localObject = (OwnedByMeFilter)paramFilter;; localObject = null)
    {
      this.zzaus = ((OwnedByMeFilter)localObject);
      if ((this.zzauk != null) || (this.zzaul != null) || (this.zzaum != null) || (this.zzaun != null) || (this.zzauo != null) || (this.zzaup != null) || (this.zzauq != null) || (this.zzaur != null) || (this.zzaus != null)) {
        break label287;
      }
      throw new IllegalArgumentException("Invalid filter type.");
      localObject = null;
      break;
      localObject = null;
      break label45;
      localObject = null;
      break label62;
      localObject = null;
      break label79;
      localObject = null;
      break label96;
      localObject = null;
      break label113;
      localObject = null;
      break label130;
      localObject = null;
      break label147;
    }
    label287:
    this.zzapi = paramFilter;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Filter getFilter()
  {
    return this.zzapi;
  }
  
  public String toString()
  {
    return String.format("FilterHolder[%s]", new Object[] { this.zzapi });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\query\internal\FilterHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */