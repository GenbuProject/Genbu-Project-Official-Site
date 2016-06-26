package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public class LocationRequestInternal
  implements SafeParcelable
{
  public static final zzm CREATOR = new zzm();
  static final List<ClientIdentity> zzaOO = ;
  @Nullable
  String mTag;
  private final int mVersionCode;
  LocationRequest zzaBp;
  boolean zzaOP;
  boolean zzaOQ;
  boolean zzaOR;
  List<ClientIdentity> zzaOS;
  boolean zzaOT;
  
  LocationRequestInternal(int paramInt, LocationRequest paramLocationRequest, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, List<ClientIdentity> paramList, @Nullable String paramString, boolean paramBoolean4)
  {
    this.mVersionCode = paramInt;
    this.zzaBp = paramLocationRequest;
    this.zzaOP = paramBoolean1;
    this.zzaOQ = paramBoolean2;
    this.zzaOR = paramBoolean3;
    this.zzaOS = paramList;
    this.mTag = paramString;
    this.zzaOT = paramBoolean4;
  }
  
  public static LocationRequestInternal zza(@Nullable String paramString, LocationRequest paramLocationRequest)
  {
    return new LocationRequestInternal(1, paramLocationRequest, false, true, true, zzaOO, paramString, false);
  }
  
  @Deprecated
  public static LocationRequestInternal zzb(LocationRequest paramLocationRequest)
  {
    return zza(null, paramLocationRequest);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof LocationRequestInternal)) {}
    do
    {
      return false;
      paramObject = (LocationRequestInternal)paramObject;
    } while ((!zzw.equal(this.zzaBp, ((LocationRequestInternal)paramObject).zzaBp)) || (this.zzaOP != ((LocationRequestInternal)paramObject).zzaOP) || (this.zzaOQ != ((LocationRequestInternal)paramObject).zzaOQ) || (this.zzaOR != ((LocationRequestInternal)paramObject).zzaOR) || (this.zzaOT != ((LocationRequestInternal)paramObject).zzaOT) || (!zzw.equal(this.zzaOS, ((LocationRequestInternal)paramObject).zzaOS)));
    return true;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return this.zzaBp.hashCode();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.zzaBp.toString());
    if (this.mTag != null) {
      localStringBuilder.append(" tag=").append(this.mTag);
    }
    localStringBuilder.append(" nlpDebug=").append(this.zzaOP);
    localStringBuilder.append(" trigger=").append(this.zzaOR);
    localStringBuilder.append(" restorePIListeners=").append(this.zzaOQ);
    localStringBuilder.append(" hideAppOps=").append(this.zzaOT);
    localStringBuilder.append(" clients=").append(this.zzaOS);
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzm.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\internal\LocationRequestInternal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */