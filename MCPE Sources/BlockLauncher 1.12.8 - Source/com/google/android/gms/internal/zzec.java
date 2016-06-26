package com.google.android.gms.internal;

import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.zzg;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@zzhb
class zzec
{
  final int zzAC;
  final String zzpS;
  final AdRequestParcel zzqH;
  
  zzec(AdRequestParcel paramAdRequestParcel, String paramString, int paramInt)
  {
    this.zzqH = paramAdRequestParcel;
    this.zzpS = paramString;
    this.zzAC = paramInt;
  }
  
  zzec(zzea paramzzea)
  {
    this(paramzzea.zzei(), paramzzea.getAdUnitId(), paramzzea.getNetworkType());
  }
  
  zzec(String paramString)
    throws IOException
  {
    Object localObject1 = paramString.split("\000");
    if (localObject1.length != 3) {
      throw new IOException("Incorrect field count for QueueSeed.");
    }
    paramString = Parcel.obtain();
    try
    {
      this.zzpS = new String(Base64.decode(localObject1[0], 0), "UTF-8");
      this.zzAC = Integer.parseInt(localObject1[1]);
      localObject1 = Base64.decode(localObject1[2], 0);
      paramString.unmarshall((byte[])localObject1, 0, localObject1.length);
      paramString.setDataPosition(0);
      this.zzqH = AdRequestParcel.CREATOR.zzb(paramString);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new IOException("Malformed QueueSeed encoding.");
    }
    finally
    {
      paramString.recycle();
    }
  }
  
  String zzem()
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      String str1 = Base64.encodeToString(this.zzpS.getBytes("UTF-8"), 0);
      String str2 = Integer.toString(this.zzAC);
      this.zzqH.writeToParcel(localParcel, 0);
      String str3 = Base64.encodeToString(localParcel.marshall(), 0);
      str1 = str1 + "\000" + str2 + "\000" + str3;
      return str1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      zzin.e("QueueSeed encode failed because UTF-8 is not available.");
      return "";
    }
    finally
    {
      localParcel.recycle();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzec.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */