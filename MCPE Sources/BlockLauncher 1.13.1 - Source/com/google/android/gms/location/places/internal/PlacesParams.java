package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.Locale;

public class PlacesParams
  implements SafeParcelable
{
  public static final zzs CREATOR = new zzs();
  public static final PlacesParams zzaQW = new PlacesParams("com.google.android.gms", Locale.getDefault(), null);
  public final int versionCode;
  public final String zzaPU;
  public final String zzaQX;
  public final String zzaQY;
  public final String zzaQZ;
  public final int zzaRa;
  public final int zzaRb;
  
  public PlacesParams(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2, int paramInt3)
  {
    this.versionCode = paramInt1;
    this.zzaQX = paramString1;
    this.zzaQY = paramString2;
    this.zzaQZ = paramString3;
    this.zzaPU = paramString4;
    this.zzaRa = paramInt2;
    this.zzaRb = paramInt3;
  }
  
  public PlacesParams(String paramString1, Locale paramLocale, String paramString2)
  {
    this(3, paramString1, paramLocale.toString(), paramString2, null, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, 0);
  }
  
  public PlacesParams(String paramString1, Locale paramLocale, String paramString2, String paramString3, int paramInt)
  {
    this(3, paramString1, paramLocale.toString(), paramString2, paramString3, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, paramInt);
  }
  
  public int describeContents()
  {
    zzs localzzs = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof PlacesParams))) {
        return false;
      }
      paramObject = (PlacesParams)paramObject;
    } while ((this.zzaRa == ((PlacesParams)paramObject).zzaRa) && (this.zzaRb == ((PlacesParams)paramObject).zzaRb) && (this.zzaQY.equals(((PlacesParams)paramObject).zzaQY)) && (this.zzaQX.equals(((PlacesParams)paramObject).zzaQX)) && (zzw.equal(this.zzaQZ, ((PlacesParams)paramObject).zzaQZ)) && (zzw.equal(this.zzaPU, ((PlacesParams)paramObject).zzaPU)));
    return false;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzaQX, this.zzaQY, this.zzaQZ, this.zzaPU, Integer.valueOf(this.zzaRa), Integer.valueOf(this.zzaRb) });
  }
  
  @SuppressLint({"DefaultLocale"})
  public String toString()
  {
    return zzw.zzy(this).zzg("clientPackageName", this.zzaQX).zzg("locale", this.zzaQY).zzg("accountName", this.zzaQZ).zzg("gCoreClientName", this.zzaPU).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzs localzzs = CREATOR;
    zzs.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\internal\PlacesParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */