package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;

public class Frame
{
  public static final int ROTATION_0 = 0;
  public static final int ROTATION_180 = 2;
  public static final int ROTATION_270 = 3;
  public static final int ROTATION_90 = 1;
  private Bitmap mBitmap = null;
  private Metadata zzbni = new Metadata();
  private ByteBuffer zzbnj = null;
  
  private ByteBuffer zzIe()
  {
    int i = 0;
    int j = this.mBitmap.getWidth();
    int k = this.mBitmap.getHeight();
    int[] arrayOfInt = new int[j * k];
    this.mBitmap.getPixels(arrayOfInt, 0, j, 0, 0, j, k);
    byte[] arrayOfByte = new byte[j * k];
    while (i < arrayOfInt.length)
    {
      arrayOfByte[i] = ((byte)(int)(Color.red(arrayOfInt[i]) * 0.299F + Color.green(arrayOfInt[i]) * 0.587F + Color.blue(arrayOfInt[i]) * 0.114F));
      i += 1;
    }
    return ByteBuffer.wrap(arrayOfByte);
  }
  
  public Bitmap getBitmap()
  {
    return this.mBitmap;
  }
  
  public ByteBuffer getGrayscaleImageData()
  {
    if (this.mBitmap != null) {
      return zzIe();
    }
    return this.zzbnj;
  }
  
  public Metadata getMetadata()
  {
    return this.zzbni;
  }
  
  public static class Builder
  {
    private Frame zzbnk = new Frame(null);
    
    public Frame build()
    {
      if ((Frame.zza(this.zzbnk) == null) && (Frame.zzb(this.zzbnk) == null)) {
        throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
      }
      return this.zzbnk;
    }
    
    public Builder setBitmap(Bitmap paramBitmap)
    {
      int i = paramBitmap.getWidth();
      int j = paramBitmap.getHeight();
      Frame.zza(this.zzbnk, paramBitmap);
      paramBitmap = this.zzbnk.getMetadata();
      Frame.Metadata.zza(paramBitmap, i);
      Frame.Metadata.zzb(paramBitmap, j);
      return this;
    }
    
    public Builder setId(int paramInt)
    {
      Frame.Metadata.zzc(this.zzbnk.getMetadata(), paramInt);
      return this;
    }
    
    public Builder setImageData(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3)
    {
      if (paramByteBuffer == null) {
        throw new IllegalArgumentException("Null image data supplied.");
      }
      if (paramByteBuffer.capacity() < paramInt1 * paramInt2) {
        throw new IllegalArgumentException("Invalid image data size.");
      }
      switch (paramInt3)
      {
      default: 
        throw new IllegalArgumentException("Unsupported image format: " + paramInt3);
      }
      Frame.zza(this.zzbnk, paramByteBuffer);
      paramByteBuffer = this.zzbnk.getMetadata();
      Frame.Metadata.zza(paramByteBuffer, paramInt1);
      Frame.Metadata.zzb(paramByteBuffer, paramInt2);
      return this;
    }
    
    public Builder setRotation(int paramInt)
    {
      Frame.Metadata.zzd(this.zzbnk.getMetadata(), paramInt);
      return this;
    }
    
    public Builder setTimestampMillis(long paramLong)
    {
      Frame.Metadata.zza(this.zzbnk.getMetadata(), paramLong);
      return this;
    }
  }
  
  public static class Metadata
  {
    private int mId;
    private int zzDE;
    private long zzaJi;
    private int zzoG;
    private int zzoH;
    
    public Metadata() {}
    
    public Metadata(Metadata paramMetadata)
    {
      this.zzoG = paramMetadata.getWidth();
      this.zzoH = paramMetadata.getHeight();
      this.mId = paramMetadata.getId();
      this.zzaJi = paramMetadata.getTimestampMillis();
      this.zzDE = paramMetadata.getRotation();
    }
    
    public int getHeight()
    {
      return this.zzoH;
    }
    
    public int getId()
    {
      return this.mId;
    }
    
    public int getRotation()
    {
      return this.zzDE;
    }
    
    public long getTimestampMillis()
    {
      return this.zzaJi;
    }
    
    public int getWidth()
    {
      return this.zzoG;
    }
    
    public void zzIf()
    {
      if (this.zzDE % 2 != 0)
      {
        int i = this.zzoG;
        this.zzoG = this.zzoH;
        this.zzoH = i;
      }
      this.zzDE = 0;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\vision\Frame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */