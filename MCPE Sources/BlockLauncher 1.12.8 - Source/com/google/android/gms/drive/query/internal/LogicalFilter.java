package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.query.Filter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LogicalFilter
  extends AbstractFilter
{
  public static final Parcelable.Creator<LogicalFilter> CREATOR = new zzk();
  final int mVersionCode;
  private List<Filter> zzaua;
  final Operator zzaug;
  final List<FilterHolder> zzauv;
  
  LogicalFilter(int paramInt, Operator paramOperator, List<FilterHolder> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzaug = paramOperator;
    this.zzauv = paramList;
  }
  
  public LogicalFilter(Operator paramOperator, Filter paramFilter, Filter... paramVarArgs)
  {
    this.mVersionCode = 1;
    this.zzaug = paramOperator;
    this.zzauv = new ArrayList(paramVarArgs.length + 1);
    this.zzauv.add(new FilterHolder(paramFilter));
    this.zzaua = new ArrayList(paramVarArgs.length + 1);
    this.zzaua.add(paramFilter);
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramOperator = paramVarArgs[i];
      this.zzauv.add(new FilterHolder(paramOperator));
      this.zzaua.add(paramOperator);
      i += 1;
    }
  }
  
  public LogicalFilter(Operator paramOperator, Iterable<Filter> paramIterable)
  {
    this.mVersionCode = 1;
    this.zzaug = paramOperator;
    this.zzaua = new ArrayList();
    this.zzauv = new ArrayList();
    paramOperator = paramIterable.iterator();
    while (paramOperator.hasNext())
    {
      paramIterable = (Filter)paramOperator.next();
      this.zzaua.add(paramIterable);
      this.zzauv.add(new FilterHolder(paramIterable));
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzk.zza(this, paramParcel, paramInt);
  }
  
  public <T> T zza(zzf<T> paramzzf)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.zzauv.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((FilterHolder)localIterator.next()).getFilter().zza(paramzzf));
    }
    return (T)paramzzf.zzb(this.zzaug, localArrayList);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\query\internal\LogicalFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */