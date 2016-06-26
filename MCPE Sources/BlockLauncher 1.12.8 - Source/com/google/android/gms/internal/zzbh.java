package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

@zzhb
public class zzbh
{
  private final int zztp;
  private final int zztq;
  private final int zztr;
  private final zzbg zzts = new zzbj();
  
  public zzbh(int paramInt)
  {
    this.zztq = paramInt;
    this.zztp = 6;
    this.zztr = 0;
  }
  
  private String zzv(String paramString)
  {
    String[] arrayOfString = paramString.split("\n");
    if (arrayOfString.length == 0) {
      return "";
    }
    paramString = zzcM();
    Arrays.sort(arrayOfString, new Comparator()
    {
      public int compare(String paramAnonymousString1, String paramAnonymousString2)
      {
        return paramAnonymousString2.length() - paramAnonymousString1.length();
      }
    });
    int i = 0;
    if ((i < arrayOfString.length) && (i < this.zztq))
    {
      if (arrayOfString[i].trim().length() == 0) {}
      for (;;)
      {
        i += 1;
        break;
        try
        {
          paramString.write(this.zzts.zzu(arrayOfString[i]));
        }
        catch (IOException localIOException)
        {
          zzin.zzb("Error while writing hash to byteStream", localIOException);
        }
      }
    }
    return paramString.toString();
  }
  
  public String zza(ArrayList<String> paramArrayList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      localStringBuffer.append(((String)paramArrayList.next()).toLowerCase(Locale.US));
      localStringBuffer.append('\n');
    }
    switch (this.zztr)
    {
    default: 
      return "";
    case 0: 
      return zzw(localStringBuffer.toString());
    }
    return zzv(localStringBuffer.toString());
  }
  
  zza zzcM()
  {
    return new zza();
  }
  
  String zzw(String paramString)
  {
    Object localObject1 = paramString.split("\n");
    if (localObject1.length == 0) {
      return "";
    }
    paramString = zzcM();
    Object localObject2 = new PriorityQueue(this.zztq, new Comparator()
    {
      public int zza(zzbk.zza paramAnonymouszza1, zzbk.zza paramAnonymouszza2)
      {
        int i = paramAnonymouszza1.zzty - paramAnonymouszza2.zzty;
        if (i != 0) {
          return i;
        }
        return (int)(paramAnonymouszza1.value - paramAnonymouszza2.value);
      }
    });
    int i = 0;
    if (i < localObject1.length)
    {
      String[] arrayOfString = zzbi.zzy(localObject1[i]);
      if (arrayOfString.length == 0) {}
      for (;;)
      {
        i += 1;
        break;
        zzbk.zza(arrayOfString, this.zztq, this.zztp, (PriorityQueue)localObject2);
      }
    }
    localObject1 = ((PriorityQueue)localObject2).iterator();
    for (;;)
    {
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (zzbk.zza)((Iterator)localObject1).next();
        try
        {
          paramString.write(this.zzts.zzu(((zzbk.zza)localObject2).zztx));
        }
        catch (IOException localIOException)
        {
          zzin.zzb("Error while writing hash to byteStream", localIOException);
        }
      }
    }
    return paramString.toString();
  }
  
  static class zza
  {
    ByteArrayOutputStream zztu = new ByteArrayOutputStream(4096);
    Base64OutputStream zztv = new Base64OutputStream(this.zztu, 10);
    
    public String toString()
    {
      try
      {
        this.zztv.close();
      }
      catch (IOException localIOException1)
      {
        for (;;)
        {
          try
          {
            this.zztu.close();
            String str = this.zztu.toString();
            return str;
          }
          catch (IOException localIOException2)
          {
            zzin.zzb("HashManager: Unable to convert to Base64.", localIOException2);
            return "";
          }
          finally
          {
            this.zztu = null;
            this.zztv = null;
          }
          localIOException1 = localIOException1;
          zzin.zzb("HashManager: Unable to convert to Base64.", localIOException1);
        }
      }
    }
    
    public void write(byte[] paramArrayOfByte)
      throws IOException
    {
      this.zztv.write(paramArrayOfByte);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzbh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */