package net.zhuoweizhang.mcpelauncher.texture.tga;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public final class TGALoader
{
  public static final int RGBA8 = 4;
  public static final int TYPE_BLACKANDWHITE = 3;
  public static final int TYPE_BLACKANDWHITE_RLE = 11;
  public static final int TYPE_COLORMAPPED = 1;
  public static final int TYPE_COLORMAPPED_RLE = 9;
  public static final int TYPE_NO_IMAGE = 0;
  public static final int TYPE_TRUECOLOR = 2;
  public static final int TYPE_TRUECOLOR_RLE = 10;
  
  private static short flipEndian(short paramShort)
  {
    paramShort &= 0xFFFF;
    return (short)(paramShort << 8 | (0xFF00 & paramShort) >>> 8);
  }
  
  private static byte getBitsAsByte(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = paramInt1 / 8;
    paramInt1 %= 8;
    int j = 0;
    int k = paramInt2;
    int m = k - 1;
    if (m >= 0)
    {
      int n = paramArrayOfByte[i];
      if (paramInt1 == 7)
      {
        k = 1;
        label41:
        paramInt2 = j;
        if ((n & k) != 0) {
          if (m != 0) {
            break label111;
          }
        }
      }
      label111:
      for (paramInt2 = j + 1;; paramInt2 = j + (2 << m - 1))
      {
        n = paramInt1 + 1;
        k = m;
        paramInt1 = n;
        j = paramInt2;
        if (n != 8) {
          break;
        }
        paramInt1 = 0;
        i += 1;
        k = m;
        j = paramInt2;
        break;
        k = 2 << 6 - paramInt1;
        break label41;
      }
    }
    return (byte)j;
  }
  
  public static Bitmap load(InputStream paramInputStream, boolean paramBoolean)
    throws IOException
  {
    DataInputStream localDataInputStream = new DataInputStream(new BufferedInputStream(paramInputStream));
    int i = localDataInputStream.readUnsignedByte();
    int m = localDataInputStream.readUnsignedByte();
    int n = localDataInputStream.readUnsignedByte();
    localDataInputStream.readShort();
    int j = flipEndian(localDataInputStream.readShort());
    int k = localDataInputStream.readUnsignedByte();
    localDataInputStream.readShort();
    localDataInputStream.readShort();
    int i2 = flipEndian(localDataInputStream.readShort());
    int i3 = flipEndian(localDataInputStream.readShort());
    int i1 = localDataInputStream.readUnsignedByte();
    int i4 = localDataInputStream.readUnsignedByte();
    boolean bool = paramBoolean;
    label137:
    Object localObject1;
    Object localObject2;
    float f2;
    label249:
    int i5;
    if ((i4 & 0x20) != 0)
    {
      if (!paramBoolean) {
        bool = true;
      }
    }
    else
    {
      if (((i4 & 0x10) != 0) && (0 != 0)) {
        break label367;
      }
      if (i > 0) {
        localDataInputStream.skip(i);
      }
      localObject1 = null;
      paramInputStream = (InputStream)localObject1;
      if (m == 0) {
        break label396;
      }
      m = Math.min(k / 3, 8);
      localObject2 = new byte[k * j >> 3];
      localDataInputStream.read((byte[])localObject2);
      if (n != 1)
      {
        paramInputStream = (InputStream)localObject1;
        if (n != 9) {
          break label396;
        }
      }
      localObject1 = new ColorMapEntry[j];
      i4 = k - m * 3;
      float f1 = 255.0F / ((1 << m) - 1);
      f2 = 255.0F / ((1 << i4) - 1);
      i = 0;
      paramInputStream = (InputStream)localObject1;
      if (i >= j) {
        break label396;
      }
      paramInputStream = new ColorMapEntry();
      i5 = k * i;
      paramInputStream.red = ((byte)(int)(getBitsAsByte((byte[])localObject2, i5, m) * f1));
      paramInputStream.green = ((byte)(int)(getBitsAsByte((byte[])localObject2, i5 + m, m) * f1));
      paramInputStream.blue = ((byte)(int)(getBitsAsByte((byte[])localObject2, m * 2 + i5, m) * f1));
      if (i4 > 0) {
        break label370;
      }
    }
    label367:
    label370:
    for (paramInputStream.alpha = -1;; paramInputStream.alpha = ((byte)(int)(getBitsAsByte((byte[])localObject2, m * 3 + i5, i4) * f2)))
    {
      localObject1[i] = paramInputStream;
      i += 1;
      break label249;
      bool = false;
      break;
      break label137;
    }
    label396:
    if (i1 == 32)
    {
      localObject1 = new int[i2 * i3];
      m = 0;
      i = 0;
      j = 0;
      k = 0;
      if (n != 2) {
        break label596;
      }
      if (i1 == 32)
      {
        localObject2 = ByteBuffer.allocate(i2 * 4).order(ByteOrder.LITTLE_ENDIAN);
        paramInputStream = ((ByteBuffer)localObject2).asIntBuffer();
        localObject2 = ((ByteBuffer)localObject2).array();
        j = 0;
        i = k;
        while (j <= i3 - 1)
        {
          if (!bool) {
            i = (i3 - 1 - j) * i2 * 1;
          }
          localDataInputStream.read((byte[])localObject2, 0, localObject2.length);
          paramInputStream.position(0);
          paramInputStream.get((int[])localObject1, i, i2);
          i += i2;
          j += 1;
        }
      }
    }
    else
    {
      throw new RuntimeException("Only 32-bit color TGAs are supported");
    }
    for (;;)
    {
      return Bitmap.createBitmap((int[])localObject1, i2, i3, Bitmap.Config.ARGB_8888);
      throw new IOException("Unsupported TGA true color depth: " + i1);
      label596:
      if (n == 10)
      {
        if (i1 == 32)
        {
          k = 0;
          i = m;
          while (k <= i3 - 1)
          {
            if (!bool) {
              i = (i3 - 1 - k) * i2 * 1;
            }
            for (j = 0; j < i2; j = m + 1)
            {
              n = localDataInputStream.readByte();
              if ((n & 0x80) != 0)
              {
                n &= 0x7F;
                i1 = j + n;
                i4 = localDataInputStream.readByte();
                i5 = localDataInputStream.readByte();
                int i6 = localDataInputStream.readByte();
                int i7 = localDataInputStream.readByte();
                j = i;
                for (;;)
                {
                  m = i1;
                  i = j;
                  if (n < 0) {
                    break;
                  }
                  localObject1[j] = ((i7 & 0xFF) << 24 | (i6 & 0xFF) << 16 | (i5 & 0xFF) << 8 | i4 & 0xFF);
                  n -= 1;
                  j += 1;
                }
              }
              i1 = j + n;
              j = i;
              for (;;)
              {
                m = i1;
                i = j;
                if (n < 0) {
                  break;
                }
                i = localDataInputStream.readByte();
                m = localDataInputStream.readByte();
                i4 = localDataInputStream.readByte();
                localObject1[j] = ((localDataInputStream.readByte() & 0xFF) << 24 | (i4 & 0xFF) << 16 | (m & 0xFF) << 8 | i & 0xFF);
                n -= 1;
                j += 1;
              }
            }
            k += 1;
          }
        }
        else
        {
          throw new IOException("Unsupported TGA true color depth: " + i1);
        }
      }
      else
      {
        if (n != 1) {
          break;
        }
        k = i1 / 8;
        if (k == 1)
        {
          j = 0;
          while (j <= i3 - 1)
          {
            if (!bool) {
              i = (i3 - 1 - j) * i2 * 1;
            }
            k = 0;
            while (k < i2)
            {
              m = localDataInputStream.readUnsignedByte();
              if ((m >= paramInputStream.length) || (m < 0)) {
                throw new IOException("TGA: Invalid color map entry referenced: " + m);
              }
              localObject2 = paramInputStream[m];
              localObject1[i] = ((((ColorMapEntry)localObject2).alpha & 0xFF) << 24 | (((ColorMapEntry)localObject2).red & 0xFF) << 16 | (((ColorMapEntry)localObject2).green & 0xFF) << 8 | ((ColorMapEntry)localObject2).blue & 0xFF);
              k += 1;
              i += 1;
            }
            j += 1;
          }
        }
        if (k == 2)
        {
          k = 0;
          i = j;
          j = k;
          while (j <= i3 - 1)
          {
            if (!bool) {
              i = (i3 - 1 - j) * i2 * 1;
            }
            k = 0;
            while (k < i2)
            {
              m = flipEndian(localDataInputStream.readShort());
              if ((m >= paramInputStream.length) || (m < 0)) {
                throw new IOException("TGA: Invalid color map entry referenced: " + m);
              }
              localObject2 = paramInputStream[m];
              localObject1[i] = ((((ColorMapEntry)localObject2).alpha & 0xFF) << 24 | (((ColorMapEntry)localObject2).red & 0xFF) << 16 | (((ColorMapEntry)localObject2).green & 0xFF) << 8 | ((ColorMapEntry)localObject2).blue & 0xFF);
              k += 1;
              i += 1;
            }
            j += 1;
          }
        }
        throw new IOException("TGA: unknown colormap indexing size used: " + k);
      }
    }
    throw new IOException("Monochrome and RLE colormapped images are not supported");
  }
  
  static class ColorMapEntry
  {
    byte alpha;
    byte blue;
    byte green;
    byte red;
    
    public String toString()
    {
      return "entry: " + this.red + "," + this.green + "," + this.blue + "," + this.alpha;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\texture\tga\TGALoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */