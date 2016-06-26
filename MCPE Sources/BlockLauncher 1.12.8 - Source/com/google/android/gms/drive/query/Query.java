package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.drive.query.internal.LogicalFilter;
import com.google.android.gms.drive.query.internal.MatchAllFilter;
import com.google.android.gms.drive.query.internal.Operator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Query
  implements SafeParcelable
{
  public static final Parcelable.Creator<Query> CREATOR = new zza();
  final int mVersionCode;
  final List<DriveSpace> zzapB;
  private final Set<DriveSpace> zzapC;
  final boolean zzarL;
  final LogicalFilter zzatV;
  final String zzatW;
  final SortOrder zzatX;
  final List<String> zzatY;
  final boolean zzatZ;
  
  private Query(int paramInt, LogicalFilter paramLogicalFilter, String paramString, SortOrder paramSortOrder, List<String> paramList, boolean paramBoolean1, List<DriveSpace> paramList1, Set<DriveSpace> paramSet, boolean paramBoolean2)
  {
    this.mVersionCode = paramInt;
    this.zzatV = paramLogicalFilter;
    this.zzatW = paramString;
    this.zzatX = paramSortOrder;
    this.zzatY = paramList;
    this.zzatZ = paramBoolean1;
    this.zzapB = paramList1;
    this.zzapC = paramSet;
    this.zzarL = paramBoolean2;
  }
  
  Query(int paramInt, LogicalFilter paramLogicalFilter, String paramString, SortOrder paramSortOrder, List<String> paramList, boolean paramBoolean1, List<DriveSpace> paramList1, boolean paramBoolean2) {}
  
  private Query(LogicalFilter paramLogicalFilter, String paramString, SortOrder paramSortOrder, List<String> paramList, boolean paramBoolean1, Set<DriveSpace> paramSet, boolean paramBoolean2) {}
  
  public int describeContents()
  {
    return 0;
  }
  
  public Filter getFilter()
  {
    return this.zzatV;
  }
  
  @Deprecated
  public String getPageToken()
  {
    return this.zzatW;
  }
  
  public SortOrder getSortOrder()
  {
    return this.zzatX;
  }
  
  public String toString()
  {
    return String.format(Locale.US, "Query[%s,%s,PageToken=%s,Spaces=%s]", new Object[] { this.zzatV, this.zzatX, this.zzatW, this.zzapB });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public List<String> zztJ()
  {
    return this.zzatY;
  }
  
  public boolean zztK()
  {
    return this.zzatZ;
  }
  
  public Set<DriveSpace> zztL()
  {
    return this.zzapC;
  }
  
  public boolean zztM()
  {
    return this.zzarL;
  }
  
  public static class Builder
  {
    private Set<DriveSpace> zzapC;
    private boolean zzarL;
    private String zzatW;
    private SortOrder zzatX;
    private List<String> zzatY;
    private boolean zzatZ;
    private final List<Filter> zzaua = new ArrayList();
    
    public Builder() {}
    
    public Builder(Query paramQuery)
    {
      this.zzaua.add(paramQuery.getFilter());
      this.zzatW = paramQuery.getPageToken();
      this.zzatX = paramQuery.getSortOrder();
      this.zzatY = paramQuery.zztJ();
      this.zzatZ = paramQuery.zztK();
      this.zzapC = paramQuery.zztL();
      this.zzarL = paramQuery.zztM();
    }
    
    public Builder addFilter(Filter paramFilter)
    {
      if (!(paramFilter instanceof MatchAllFilter)) {
        this.zzaua.add(paramFilter);
      }
      return this;
    }
    
    public Query build()
    {
      return new Query(new LogicalFilter(Operator.zzauC, this.zzaua), this.zzatW, this.zzatX, this.zzatY, this.zzatZ, this.zzapC, this.zzarL, null);
    }
    
    @Deprecated
    public Builder setPageToken(String paramString)
    {
      this.zzatW = paramString;
      return this;
    }
    
    public Builder setSortOrder(SortOrder paramSortOrder)
    {
      this.zzatX = paramSortOrder;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\query\Query.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */