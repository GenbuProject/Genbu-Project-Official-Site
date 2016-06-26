package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.DriveId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ParentDriveIdSet
  implements SafeParcelable
{
  public static final Parcelable.Creator<ParentDriveIdSet> CREATOR = new zzl();
  final int mVersionCode;
  final List<PartialDriveId> zzasS;
  
  public ParentDriveIdSet()
  {
    this(1, new ArrayList());
  }
  
  ParentDriveIdSet(int paramInt, List<PartialDriveId> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzasS = paramList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzl.zza(this, paramParcel, paramInt);
  }
  
  public Set<DriveId> zzD(long paramLong)
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.zzasS.iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((PartialDriveId)localIterator.next()).zzE(paramLong));
    }
    return localHashSet;
  }
  
  public void zza(PartialDriveId paramPartialDriveId)
  {
    this.zzasS.add(paramPartialDriveId);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\metadata\internal\ParentDriveIdSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */