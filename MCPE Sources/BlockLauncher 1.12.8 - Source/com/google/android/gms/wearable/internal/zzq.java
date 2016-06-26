package com.google.android.gms.wearable.internal;

import android.util.Log;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.wearable.ChannelIOException;
import java.io.IOException;
import java.io.OutputStream;

public final class zzq
  extends OutputStream
{
  private volatile zzm zzbsk;
  private final OutputStream zzbsm;
  
  public zzq(OutputStream paramOutputStream)
  {
    this.zzbsm = ((OutputStream)zzx.zzz(paramOutputStream));
  }
  
  private IOException zza(IOException paramIOException)
  {
    zzm localzzm = this.zzbsk;
    Object localObject = paramIOException;
    if (localzzm != null)
    {
      if (Log.isLoggable("ChannelOutputStream", 2)) {
        Log.v("ChannelOutputStream", "Caught IOException, but channel has been closed. Translating to ChannelIOException.", paramIOException);
      }
      localObject = new ChannelIOException("Channel closed unexpectedly before stream was finished", localzzm.zzbsa, localzzm.zzbsb);
    }
    return (IOException)localObject;
  }
  
  public void close()
    throws IOException
  {
    try
    {
      this.zzbsm.close();
      return;
    }
    catch (IOException localIOException)
    {
      throw zza(localIOException);
    }
  }
  
  public void flush()
    throws IOException
  {
    try
    {
      this.zzbsm.flush();
      return;
    }
    catch (IOException localIOException)
    {
      throw zza(localIOException);
    }
  }
  
  public void write(int paramInt)
    throws IOException
  {
    try
    {
      this.zzbsm.write(paramInt);
      return;
    }
    catch (IOException localIOException)
    {
      throw zza(localIOException);
    }
  }
  
  public void write(byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      this.zzbsm.write(paramArrayOfByte);
      return;
    }
    catch (IOException paramArrayOfByte)
    {
      throw zza(paramArrayOfByte);
    }
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    try
    {
      this.zzbsm.write(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    catch (IOException paramArrayOfByte)
    {
      throw zza(paramArrayOfByte);
    }
  }
  
  zzu zzIJ()
  {
    new zzu()
    {
      public void zzb(zzm paramAnonymouszzm)
      {
        zzq.this.zzc(paramAnonymouszzm);
      }
    };
  }
  
  void zzc(zzm paramzzm)
  {
    this.zzbsk = paramzzm;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */