package com.google.android.gms.fitness.data;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.provider.Settings.Secure;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zznv;
import com.google.android.gms.internal.zzoz;

public final class Device
  implements SafeParcelable
{
  public static final Parcelable.Creator<Device> CREATOR = new zzi();
  public static final int TYPE_CHEST_STRAP = 4;
  public static final int TYPE_PHONE = 1;
  public static final int TYPE_SCALE = 5;
  public static final int TYPE_TABLET = 2;
  public static final int TYPE_UNKNOWN = 0;
  public static final int TYPE_WATCH = 3;
  private final int mVersionCode;
  private final int zzabB;
  private final String zzadc;
  private final String zzawI;
  private final String zzawJ;
  private final String zzawK;
  private final int zzawL;
  
  Device(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2, int paramInt3)
  {
    this.mVersionCode = paramInt1;
    this.zzawI = ((String)zzx.zzz(paramString1));
    this.zzawJ = ((String)zzx.zzz(paramString2));
    this.zzadc = "";
    this.zzawK = ((String)zzx.zzz(paramString4));
    this.zzabB = paramInt2;
    this.zzawL = paramInt3;
  }
  
  public Device(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    this(paramString1, paramString2, "", paramString3, paramInt, 0);
  }
  
  public Device(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2)
  {
    this(1, paramString1, paramString2, "", paramString4, paramInt1, paramInt2);
  }
  
  public static Device getLocalDevice(Context paramContext)
  {
    int i = zznv.zzaG(paramContext);
    paramContext = zzaC(paramContext);
    return new Device(Build.MANUFACTURER, Build.MODEL, Build.VERSION.RELEASE, paramContext, i, 2);
  }
  
  private boolean zza(Device paramDevice)
  {
    return (zzw.equal(this.zzawI, paramDevice.zzawI)) && (zzw.equal(this.zzawJ, paramDevice.zzawJ)) && (zzw.equal(this.zzadc, paramDevice.zzadc)) && (zzw.equal(this.zzawK, paramDevice.zzawK)) && (this.zzabB == paramDevice.zzabB) && (this.zzawL == paramDevice.zzawL);
  }
  
  private static String zzaC(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  private boolean zzus()
  {
    return zzur() == 1;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof Device)) && (zza((Device)paramObject)));
  }
  
  public String getManufacturer()
  {
    return this.zzawI;
  }
  
  public String getModel()
  {
    return this.zzawJ;
  }
  
  String getStreamIdentifier()
  {
    return String.format("%s:%s:%s", new Object[] { this.zzawI, this.zzawJ, this.zzawK });
  }
  
  public int getType()
  {
    return this.zzabB;
  }
  
  public String getUid()
  {
    return this.zzawK;
  }
  
  public String getVersion()
  {
    return this.zzadc;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzawI, this.zzawJ, this.zzadc, this.zzawK, Integer.valueOf(this.zzabB) });
  }
  
  public String toString()
  {
    return String.format("Device{%s:%s:%s:%s}", new Object[] { getStreamIdentifier(), this.zzadc, Integer.valueOf(this.zzabB), Integer.valueOf(this.zzawL) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzi.zza(this, paramParcel, paramInt);
  }
  
  public int zzur()
  {
    return this.zzawL;
  }
  
  public String zzut()
  {
    if (zzus()) {
      return this.zzawK;
    }
    return zzoz.zzdF(this.zzawK);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\Device.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */