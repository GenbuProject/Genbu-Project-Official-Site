package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cast.internal.zzf;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CastDevice
  implements SafeParcelable
{
  public static final int CAPABILITY_AUDIO_IN = 8;
  public static final int CAPABILITY_AUDIO_OUT = 4;
  public static final int CAPABILITY_MULTIZONE_GROUP = 32;
  public static final int CAPABILITY_VIDEO_IN = 2;
  public static final int CAPABILITY_VIDEO_OUT = 1;
  public static final Parcelable.Creator<CastDevice> CREATOR = new zzb();
  private final int mVersionCode;
  private int zzBc;
  private String zzZT;
  String zzZU;
  private Inet4Address zzZV;
  private String zzZW;
  private String zzZX;
  private String zzZY;
  private int zzZZ;
  private List<WebImage> zzaaa;
  private int zzaab;
  private String zzaac;
  
  private CastDevice()
  {
    this(4, null, null, null, null, null, -1, new ArrayList(), 0, -1, null);
  }
  
  CastDevice(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt2, List<WebImage> paramList, int paramInt3, int paramInt4, String paramString6)
  {
    this.mVersionCode = paramInt1;
    this.zzZT = zzbZ(paramString1);
    this.zzZU = zzbZ(paramString2);
    if (!TextUtils.isEmpty(this.zzZU)) {}
    try
    {
      paramString1 = InetAddress.getByName(this.zzZU);
      if ((paramString1 instanceof Inet4Address)) {
        this.zzZV = ((Inet4Address)paramString1);
      }
      this.zzZW = zzbZ(paramString3);
      this.zzZX = zzbZ(paramString4);
      this.zzZY = zzbZ(paramString5);
      this.zzZZ = paramInt2;
      if (paramList != null)
      {
        this.zzaaa = paramList;
        this.zzaab = paramInt3;
        this.zzBc = paramInt4;
        this.zzaac = zzbZ(paramString6);
        return;
      }
    }
    catch (UnknownHostException paramString1)
    {
      for (;;)
      {
        Log.i("CastDevice", "Unable to convert host address (" + this.zzZU + ") to ipaddress: " + paramString1.getMessage());
        continue;
        paramList = new ArrayList();
      }
    }
  }
  
  public static CastDevice getFromBundle(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return null;
    }
    paramBundle.setClassLoader(CastDevice.class.getClassLoader());
    return (CastDevice)paramBundle.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
  }
  
  private static String zzbZ(String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    return str;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      do
      {
        return true;
        if (!(paramObject instanceof CastDevice)) {
          return false;
        }
        paramObject = (CastDevice)paramObject;
        if (this.zzZT != null) {
          break;
        }
      } while (((CastDevice)paramObject).zzZT == null);
      return false;
    } while ((zzf.zza(this.zzZT, ((CastDevice)paramObject).zzZT)) && (zzf.zza(this.zzZV, ((CastDevice)paramObject).zzZV)) && (zzf.zza(this.zzZX, ((CastDevice)paramObject).zzZX)) && (zzf.zza(this.zzZW, ((CastDevice)paramObject).zzZW)) && (zzf.zza(this.zzZY, ((CastDevice)paramObject).zzZY)) && (this.zzZZ == ((CastDevice)paramObject).zzZZ) && (zzf.zza(this.zzaaa, ((CastDevice)paramObject).zzaaa)) && (this.zzaab == ((CastDevice)paramObject).zzaab) && (this.zzBc == ((CastDevice)paramObject).zzBc) && (zzf.zza(this.zzaac, ((CastDevice)paramObject).zzaac)));
    return false;
  }
  
  public int getCapabilities()
  {
    return this.zzaab;
  }
  
  public String getDeviceId()
  {
    if (this.zzZT.startsWith("__cast_nearby__")) {
      return this.zzZT.substring("__cast_nearby__".length() + 1);
    }
    return this.zzZT;
  }
  
  public String getDeviceVersion()
  {
    return this.zzZY;
  }
  
  public String getFriendlyName()
  {
    return this.zzZW;
  }
  
  public WebImage getIcon(int paramInt1, int paramInt2)
  {
    Object localObject1 = null;
    if (this.zzaaa.isEmpty()) {
      return null;
    }
    if ((paramInt1 <= 0) || (paramInt2 <= 0)) {
      return (WebImage)this.zzaaa.get(0);
    }
    Iterator localIterator = this.zzaaa.iterator();
    Object localObject2 = null;
    WebImage localWebImage;
    int i;
    int j;
    if (localIterator.hasNext())
    {
      localWebImage = (WebImage)localIterator.next();
      i = localWebImage.getWidth();
      j = localWebImage.getHeight();
      if ((i >= paramInt1) && (j >= paramInt2))
      {
        if ((localObject2 != null) && ((((WebImage)localObject2).getWidth() <= i) || (((WebImage)localObject2).getHeight() <= j))) {
          break label210;
        }
        localObject2 = localWebImage;
      }
    }
    label210:
    for (;;)
    {
      break;
      if ((i < paramInt1) && (j < paramInt2) && ((localObject1 == null) || ((((WebImage)localObject1).getWidth() < i) && (((WebImage)localObject1).getHeight() < j))))
      {
        localObject1 = localWebImage;
        continue;
        if (localObject2 != null) {}
        for (;;)
        {
          return (WebImage)localObject2;
          if (localObject1 != null) {
            localObject2 = localObject1;
          } else {
            localObject2 = (WebImage)this.zzaaa.get(0);
          }
        }
      }
    }
  }
  
  public List<WebImage> getIcons()
  {
    return Collections.unmodifiableList(this.zzaaa);
  }
  
  public Inet4Address getIpAddress()
  {
    return this.zzZV;
  }
  
  public String getModelName()
  {
    return this.zzZX;
  }
  
  public int getServicePort()
  {
    return this.zzZZ;
  }
  
  public int getStatus()
  {
    return this.zzBc;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public boolean hasCapabilities(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt == null) {
      return false;
    }
    int j = paramArrayOfInt.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label33;
      }
      if (!hasCapability(paramArrayOfInt[i])) {
        break;
      }
      i += 1;
    }
    label33:
    return true;
  }
  
  public boolean hasCapability(int paramInt)
  {
    return (this.zzaab & paramInt) == paramInt;
  }
  
  public boolean hasIcons()
  {
    return !this.zzaaa.isEmpty();
  }
  
  public int hashCode()
  {
    if (this.zzZT == null) {
      return 0;
    }
    return this.zzZT.hashCode();
  }
  
  public boolean isOnLocalNetwork()
  {
    return !this.zzZT.startsWith("__cast_nearby__");
  }
  
  public boolean isSameDevice(CastDevice paramCastDevice)
  {
    if (paramCastDevice == null) {}
    do
    {
      return false;
      if (this.zzZT != null) {
        break;
      }
    } while (paramCastDevice.zzZT != null);
    return true;
    return zzf.zza(this.zzZT, paramCastDevice.zzZT);
  }
  
  public void putInBundle(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return;
    }
    paramBundle.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
  }
  
  public String toString()
  {
    return String.format("\"%s\" (%s)", new Object[] { this.zzZW, this.zzZT });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public String zzny()
  {
    return this.zzZT;
  }
  
  public String zznz()
  {
    return this.zzaac;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\CastDevice.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */