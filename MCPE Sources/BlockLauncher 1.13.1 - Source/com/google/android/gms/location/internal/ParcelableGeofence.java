package com.google.android.gms.location.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

public class ParcelableGeofence
  implements SafeParcelable, Geofence
{
  public static final zzo CREATOR = new zzo();
  private final int mVersionCode;
  private final String zzEY;
  private final int zzaNC;
  private final short zzaNE;
  private final double zzaNF;
  private final double zzaNG;
  private final float zzaNH;
  private final int zzaNI;
  private final int zzaNJ;
  private final long zzaOZ;
  
  public ParcelableGeofence(int paramInt1, String paramString, int paramInt2, short paramShort, double paramDouble1, double paramDouble2, float paramFloat, long paramLong, int paramInt3, int paramInt4)
  {
    zzek(paramString);
    zze(paramFloat);
    zza(paramDouble1, paramDouble2);
    paramInt2 = zzhF(paramInt2);
    this.mVersionCode = paramInt1;
    this.zzaNE = paramShort;
    this.zzEY = paramString;
    this.zzaNF = paramDouble1;
    this.zzaNG = paramDouble2;
    this.zzaNH = paramFloat;
    this.zzaOZ = paramLong;
    this.zzaNC = paramInt2;
    this.zzaNI = paramInt3;
    this.zzaNJ = paramInt4;
  }
  
  public ParcelableGeofence(String paramString, int paramInt1, short paramShort, double paramDouble1, double paramDouble2, float paramFloat, long paramLong, int paramInt2, int paramInt3)
  {
    this(1, paramString, paramInt1, paramShort, paramDouble1, paramDouble2, paramFloat, paramLong, paramInt2, paramInt3);
  }
  
  private static void zza(double paramDouble1, double paramDouble2)
  {
    if ((paramDouble1 > 90.0D) || (paramDouble1 < -90.0D)) {
      throw new IllegalArgumentException("invalid latitude: " + paramDouble1);
    }
    if ((paramDouble2 > 180.0D) || (paramDouble2 < -180.0D)) {
      throw new IllegalArgumentException("invalid longitude: " + paramDouble2);
    }
  }
  
  private static void zze(float paramFloat)
  {
    if (paramFloat <= 0.0F) {
      throw new IllegalArgumentException("invalid radius: " + paramFloat);
    }
  }
  
  private static void zzek(String paramString)
  {
    if ((paramString == null) || (paramString.length() > 100)) {
      throw new IllegalArgumentException("requestId is null or too long: " + paramString);
    }
  }
  
  private static int zzhF(int paramInt)
  {
    int i = paramInt & 0x7;
    if (i == 0) {
      throw new IllegalArgumentException("No supported transition specified: " + paramInt);
    }
    return i;
  }
  
  @SuppressLint({"DefaultLocale"})
  private static String zzhG(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    }
    return "CIRCLE";
  }
  
  public static ParcelableGeofence zzo(byte[] paramArrayOfByte)
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
    localParcel.setDataPosition(0);
    paramArrayOfByte = CREATOR.zzeZ(localParcel);
    localParcel.recycle();
    return paramArrayOfByte;
  }
  
  public int describeContents()
  {
    zzo localzzo = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (paramObject == null) {
        return false;
      }
      if (!(paramObject instanceof ParcelableGeofence)) {
        return false;
      }
      paramObject = (ParcelableGeofence)paramObject;
      if (this.zzaNH != ((ParcelableGeofence)paramObject).zzaNH) {
        return false;
      }
      if (this.zzaNF != ((ParcelableGeofence)paramObject).zzaNF) {
        return false;
      }
      if (this.zzaNG != ((ParcelableGeofence)paramObject).zzaNG) {
        return false;
      }
    } while (this.zzaNE == ((ParcelableGeofence)paramObject).zzaNE);
    return false;
  }
  
  public long getExpirationTime()
  {
    return this.zzaOZ;
  }
  
  public double getLatitude()
  {
    return this.zzaNF;
  }
  
  public double getLongitude()
  {
    return this.zzaNG;
  }
  
  public int getNotificationResponsiveness()
  {
    return this.zzaNI;
  }
  
  public String getRequestId()
  {
    return this.zzEY;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    long l = Double.doubleToLongBits(this.zzaNF);
    int i = (int)(l ^ l >>> 32);
    l = Double.doubleToLongBits(this.zzaNG);
    return ((((i + 31) * 31 + (int)(l ^ l >>> 32)) * 31 + Float.floatToIntBits(this.zzaNH)) * 31 + this.zzaNE) * 31 + this.zzaNC;
  }
  
  public String toString()
  {
    return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[] { zzhG(this.zzaNE), this.zzEY, Integer.valueOf(this.zzaNC), Double.valueOf(this.zzaNF), Double.valueOf(this.zzaNG), Float.valueOf(this.zzaNH), Integer.valueOf(this.zzaNI / 1000), Integer.valueOf(this.zzaNJ), Long.valueOf(this.zzaOZ) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzo localzzo = CREATOR;
    zzo.zza(this, paramParcel, paramInt);
  }
  
  public short zzyT()
  {
    return this.zzaNE;
  }
  
  public float zzyU()
  {
    return this.zzaNH;
  }
  
  public int zzyV()
  {
    return this.zzaNC;
  }
  
  public int zzyW()
  {
    return this.zzaNJ;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\internal\ParcelableGeofence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */