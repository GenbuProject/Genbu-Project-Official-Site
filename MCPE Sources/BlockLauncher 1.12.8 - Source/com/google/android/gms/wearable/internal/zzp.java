package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.wearable.ChannelIOException;
import java.io.IOException;
import java.io.InputStream;

public final class zzp
  extends InputStream
{
  private final InputStream zzbsj;
  private volatile zzm zzbsk;
  
  public zzp(InputStream paramInputStream)
  {
    this.zzbsj = ((InputStream)zzx.zzz(paramInputStream));
  }
  
  private int zzlK(int paramInt)
    throws ChannelIOException
  {
    if (paramInt == -1)
    {
      zzm localzzm = this.zzbsk;
      if (localzzm != null) {
        throw new ChannelIOException("Channel closed unexpectedly before stream was finished", localzzm.zzbsa, localzzm.zzbsb);
      }
    }
    return paramInt;
  }
  
  public int available()
    throws IOException
  {
    return this.zzbsj.available();
  }
  
  public void close()
    throws IOException
  {
    this.zzbsj.close();
  }
  
  public void mark(int paramInt)
  {
    this.zzbsj.mark(paramInt);
  }
  
  public boolean markSupported()
  {
    return this.zzbsj.markSupported();
  }
  
  public int read()
    throws IOException
  {
    return zzlK(this.zzbsj.read());
  }
  
  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    return zzlK(this.zzbsj.read(paramArrayOfByte));
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    return zzlK(this.zzbsj.read(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  public void reset()
    throws IOException
  {
    this.zzbsj.reset();
  }
  
  public long skip(long paramLong)
    throws IOException
  {
    return this.zzbsj.skip(paramLong);
  }
  
  zzu zzIJ()
  {
    new zzu()
    {
      public void zzb(zzm paramAnonymouszzm)
      {
        zzp.this.zza(paramAnonymouszzm);
      }
    };
  }
  
  void zza(zzm paramzzm)
  {
    this.zzbsk = ((zzm)zzx.zzz(paramzzm));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */