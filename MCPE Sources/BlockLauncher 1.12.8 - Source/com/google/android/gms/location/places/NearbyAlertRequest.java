package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.Set;

public final class NearbyAlertRequest
  implements SafeParcelable
{
  public static final zze CREATOR = new zze();
  private int mPriority = 110;
  private final int mVersionCode;
  private final int zzaNC;
  private final int zzaPr;
  @Deprecated
  private final PlaceFilter zzaPs;
  private final NearbyAlertFilter zzaPt;
  private final boolean zzaPu;
  private final int zzaPv;
  
  NearbyAlertRequest(int paramInt1, int paramInt2, int paramInt3, PlaceFilter paramPlaceFilter, NearbyAlertFilter paramNearbyAlertFilter, boolean paramBoolean, int paramInt4, int paramInt5)
  {
    this.mVersionCode = paramInt1;
    this.zzaNC = paramInt2;
    this.zzaPr = paramInt3;
    if (paramNearbyAlertFilter != null) {
      this.zzaPt = paramNearbyAlertFilter;
    }
    for (;;)
    {
      this.zzaPs = null;
      this.zzaPu = paramBoolean;
      this.zzaPv = paramInt4;
      this.mPriority = paramInt5;
      return;
      if (paramPlaceFilter != null)
      {
        if ((paramPlaceFilter.getPlaceIds() != null) && (!paramPlaceFilter.getPlaceIds().isEmpty())) {
          this.zzaPt = NearbyAlertFilter.zzh(paramPlaceFilter.getPlaceIds());
        } else if ((paramPlaceFilter.getPlaceTypes() != null) && (!paramPlaceFilter.getPlaceTypes().isEmpty())) {
          this.zzaPt = NearbyAlertFilter.zzi(paramPlaceFilter.getPlaceTypes());
        } else {
          this.zzaPt = null;
        }
      }
      else {
        this.zzaPt = null;
      }
    }
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
      if (!(paramObject instanceof NearbyAlertRequest)) {
        return false;
      }
      paramObject = (NearbyAlertRequest)paramObject;
    } while ((this.zzaNC == ((NearbyAlertRequest)paramObject).zzaNC) && (this.zzaPr == ((NearbyAlertRequest)paramObject).zzaPr) && (zzw.equal(this.zzaPt, ((NearbyAlertRequest)paramObject).zzaPt)) && (this.mPriority == ((NearbyAlertRequest)paramObject).mPriority));
    return false;
  }
  
  public int getPriority()
  {
    return this.mPriority;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.zzaNC), Integer.valueOf(this.zzaPr), this.zzaPt, Integer.valueOf(this.mPriority) });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("transitionTypes", Integer.valueOf(this.zzaNC)).zzg("loiteringTimeMillis", Integer.valueOf(this.zzaPr)).zzg("nearbyAlertFilter", this.zzaPt).zzg("priority", Integer.valueOf(this.mPriority)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  public int zzyV()
  {
    return this.zzaNC;
  }
  
  public int zzyY()
  {
    return this.zzaPr;
  }
  
  @Deprecated
  public PlaceFilter zzyZ()
  {
    return null;
  }
  
  public NearbyAlertFilter zzza()
  {
    return this.zzaPt;
  }
  
  public boolean zzzb()
  {
    return this.zzaPu;
  }
  
  public int zzzc()
  {
    return this.zzaPv;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\NearbyAlertRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */