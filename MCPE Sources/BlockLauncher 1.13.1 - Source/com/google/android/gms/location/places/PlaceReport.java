package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;

public class PlaceReport
  implements SafeParcelable
{
  public static final Parcelable.Creator<PlaceReport> CREATOR = new zzj();
  private final String mTag;
  final int mVersionCode;
  private final String zzaPH;
  private final String zzaPI;
  
  PlaceReport(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.mVersionCode = paramInt;
    this.zzaPH = paramString1;
    this.mTag = paramString2;
    this.zzaPI = paramString3;
  }
  
  public static PlaceReport create(String paramString1, String paramString2)
  {
    return zzk(paramString1, paramString2, "unknown");
  }
  
  private static boolean zzel(String paramString)
  {
    boolean bool = true;
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        bool = false;
      }
      return bool;
      if (paramString.equals("unknown"))
      {
        i = 0;
        continue;
        if (paramString.equals("userReported"))
        {
          i = 1;
          continue;
          if (paramString.equals("inferredGeofencing"))
          {
            i = 2;
            continue;
            if (paramString.equals("inferredRadioSignals"))
            {
              i = 3;
              continue;
              if (paramString.equals("inferredReverseGeocoding"))
              {
                i = 4;
                continue;
                if (paramString.equals("inferredSnappedToRoad")) {
                  i = 5;
                }
              }
            }
          }
        }
      }
    }
  }
  
  public static PlaceReport zzk(String paramString1, String paramString2, String paramString3)
  {
    zzx.zzz(paramString1);
    zzx.zzcM(paramString2);
    zzx.zzcM(paramString3);
    zzx.zzb(zzel(paramString3), "Invalid source");
    return new PlaceReport(1, paramString1, paramString2, paramString3);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof PlaceReport)) {}
    do
    {
      return false;
      paramObject = (PlaceReport)paramObject;
    } while ((!zzw.equal(this.zzaPH, ((PlaceReport)paramObject).zzaPH)) || (!zzw.equal(this.mTag, ((PlaceReport)paramObject).mTag)) || (!zzw.equal(this.zzaPI, ((PlaceReport)paramObject).zzaPI)));
    return true;
  }
  
  public String getPlaceId()
  {
    return this.zzaPH;
  }
  
  public String getSource()
  {
    return this.zzaPI;
  }
  
  public String getTag()
  {
    return this.mTag;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzaPH, this.mTag, this.zzaPI });
  }
  
  public String toString()
  {
    zzw.zza localzza = zzw.zzy(this);
    localzza.zzg("placeId", this.zzaPH);
    localzza.zzg("tag", this.mTag);
    if (!"unknown".equals(this.zzaPI)) {
      localzza.zzg("source", this.zzaPI);
    }
    return localzza.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\PlaceReport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */