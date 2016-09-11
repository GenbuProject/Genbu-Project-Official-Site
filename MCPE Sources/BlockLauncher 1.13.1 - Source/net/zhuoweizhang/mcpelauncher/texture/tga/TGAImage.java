package net.zhuoweizhang.mcpelauncher.texture.tga;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class TGAImage
{
  private int bpp;
  private ByteBuffer data;
  private int format;
  private Header header;
  private boolean topToBottom = false;
  
  private TGAImage(Header paramHeader)
  {
    this.header = paramHeader;
  }
  
  public static TGAImage createFromData(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, ByteBuffer paramByteBuffer)
  {
    int i = 32;
    Object localObject = new Header();
    Header.access$302((Header)localObject, 2);
    Header.access$402((Header)localObject, paramInt1);
    Header.access$102((Header)localObject, paramInt2);
    if (paramBoolean1)
    {
      paramInt1 = 32;
      Header.access$002((Header)localObject, (byte)paramInt1);
      if (!paramBoolean2) {
        break label91;
      }
    }
    label91:
    for (paramInt1 = i;; paramInt1 = 0)
    {
      Header.access$502((Header)localObject, (byte)paramInt1);
      localObject = new TGAImage((Header)localObject);
      ((TGAImage)localObject).data = paramByteBuffer;
      return (TGAImage)localObject;
      paramInt1 = 24;
      break;
    }
  }
  
  private void decodeImage(LEDataInputStream paramLEDataInputStream)
    throws IOException
  {
    switch (this.header.imageType())
    {
    case 4: 
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    default: 
      return;
    case 1: 
      throw new IOException("TGADecoder Uncompressed Colormapped images not supported");
    case 2: 
      switch (this.header.pixelDepth)
      {
      default: 
        return;
      case 16: 
        throw new IOException("TGADecoder Compressed 16-bit True Color images not supported");
      }
      decodeRGBImageU24_32(paramLEDataInputStream);
      return;
    case 3: 
      throw new IOException("TGADecoder Uncompressed Grayscale images not supported");
    case 9: 
      throw new IOException("TGADecoder Compressed Colormapped images not supported");
    case 10: 
      throw new IOException("TGADecoder Compressed True Color images not supported");
    }
    throw new IOException("TGADecoder Compressed Grayscale images not supported");
  }
  
  private void decodeRGBImageU24_32(LEDataInputStream paramLEDataInputStream)
    throws IOException
  {
    int k = this.header.width() * (this.header.pixelDepth() / 8);
    byte[] arrayOfByte1 = new byte[k];
    byte[] arrayOfByte2 = new byte[this.header.height() * k];
    int i = 0;
    if (i < this.header.height())
    {
      paramLEDataInputStream.readFully(arrayOfByte1, 0, k);
      if (this.header.topToBottom() == this.topToBottom) {}
      for (int j = this.header.height - i - 1;; j = i)
      {
        System.arraycopy(arrayOfByte1, 0, arrayOfByte2, j * k, arrayOfByte1.length);
        i += 1;
        break;
      }
    }
    this.data = ByteBuffer.wrap(arrayOfByte2);
  }
  
  public static TGAImage read(InputStream paramInputStream)
    throws IOException
  {
    paramInputStream = new LEDataInputStream(new BufferedInputStream(paramInputStream));
    TGAImage localTGAImage = new TGAImage(new Header(paramInputStream));
    localTGAImage.decodeImage(paramInputStream);
    return localTGAImage;
  }
  
  public static TGAImage read(String paramString)
    throws IOException
  {
    return read(new FileInputStream(paramString));
  }
  
  public static void swapBGR(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    int j = 0;
    while (j < paramInt2)
    {
      int k = 0;
      while (k < paramInt1)
      {
        int m = j * paramInt1 + k;
        int i = paramArrayOfByte[(m + 0)];
        paramArrayOfByte[(m + 0)] = paramArrayOfByte[(m + 2)];
        paramArrayOfByte[(m + 2)] = i;
        k += paramInt3;
      }
      j += 1;
    }
  }
  
  public int getBytesPerPixel()
  {
    return this.bpp;
  }
  
  public ByteBuffer getData()
  {
    return this.data;
  }
  
  public int getGLFormat()
  {
    return this.format;
  }
  
  public int getHeight()
  {
    return this.header.height();
  }
  
  public int getWidth()
  {
    return this.header.width();
  }
  
  public void write(File paramFile)
    throws IOException
  {
    paramFile = new FileOutputStream(paramFile);
    FileChannel localFileChannel = paramFile.getChannel();
    write(localFileChannel);
    localFileChannel.force(true);
    localFileChannel.close();
    paramFile.close();
  }
  
  public void write(String paramString)
    throws IOException
  {
    write(new File(paramString));
  }
  
  public void write(WritableByteChannel paramWritableByteChannel)
    throws IOException
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(this.header.size());
    localByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    this.header.write(localByteBuffer);
    localByteBuffer.rewind();
    paramWritableByteChannel.write(localByteBuffer);
    paramWritableByteChannel.write(this.data);
    this.data.rewind();
  }
  
  public static class Header
  {
    public static final int BLACKWHITE = 11;
    public static final int COLORMAPPED = 9;
    public static final int ID_ATTRIBPERPIXEL = 15;
    public static final int ID_INTERLEAVE = 192;
    public static final int ID_RIGHTTOLEFT = 16;
    public static final int ID_TOPTOBOTTOM = 32;
    public static final int I_FOURWAY = 2;
    public static final int I_NOTINTERLEAVED = 0;
    public static final int I_TWOWAY = 1;
    public static final int NO_IMAGE = 0;
    public static final int TRUECOLOR = 10;
    public static final int TYPE_NEW = 0;
    public static final int TYPE_OLD = 1;
    public static final int TYPE_UNK = 2;
    public static final int UBLACKWHITE = 3;
    public static final int UCOLORMAPPED = 1;
    public static final int UTRUECOLOR = 2;
    private byte colorMapEntrySize;
    private int colorMapLength;
    private int colorMapType;
    private int firstEntryIndex;
    private int height;
    private int idLength;
    private byte imageDescriptor;
    private String imageID;
    private byte[] imageIDbuf;
    private int imageType;
    private byte pixelDepth;
    private int tgaType = 1;
    private int width;
    private int xOrigin;
    private int yOrigin;
    
    Header() {}
    
    Header(LEDataInputStream paramLEDataInputStream)
      throws IOException
    {
      this.idLength = paramLEDataInputStream.readUnsignedByte();
      this.colorMapType = paramLEDataInputStream.readUnsignedByte();
      this.imageType = paramLEDataInputStream.readUnsignedByte();
      this.firstEntryIndex = paramLEDataInputStream.readUnsignedShort();
      this.colorMapLength = paramLEDataInputStream.readUnsignedShort();
      this.colorMapEntrySize = paramLEDataInputStream.readByte();
      this.xOrigin = paramLEDataInputStream.readUnsignedShort();
      this.yOrigin = paramLEDataInputStream.readUnsignedShort();
      this.width = paramLEDataInputStream.readUnsignedShort();
      this.height = paramLEDataInputStream.readUnsignedShort();
      this.pixelDepth = paramLEDataInputStream.readByte();
      this.imageDescriptor = paramLEDataInputStream.readByte();
      if (this.idLength > 0)
      {
        this.imageIDbuf = new byte[this.idLength];
        paramLEDataInputStream.read(this.imageIDbuf, 0, this.idLength);
        this.imageID = new String(this.imageIDbuf, "US-ASCII");
      }
    }
    
    private void write(ByteBuffer paramByteBuffer)
    {
      paramByteBuffer.put((byte)this.idLength);
      paramByteBuffer.put((byte)this.colorMapType);
      paramByteBuffer.put((byte)this.imageType);
      paramByteBuffer.putShort((short)this.firstEntryIndex);
      paramByteBuffer.putShort((short)this.colorMapLength);
      paramByteBuffer.put(this.colorMapEntrySize);
      paramByteBuffer.putShort((short)this.xOrigin);
      paramByteBuffer.putShort((short)this.yOrigin);
      paramByteBuffer.putShort((short)this.width);
      paramByteBuffer.putShort((short)this.height);
      paramByteBuffer.put(this.pixelDepth);
      paramByteBuffer.put(this.imageDescriptor);
      if (this.idLength > 0) {}
      try
      {
        paramByteBuffer.put(this.imageID.getBytes("US-ASCII"));
        return;
      }
      catch (UnsupportedEncodingException paramByteBuffer)
      {
        throw new RuntimeException(paramByteBuffer);
      }
    }
    
    public byte attribPerPixel()
    {
      return (byte)(this.imageDescriptor & 0xF);
    }
    
    public byte colorMapEntrySize()
    {
      return this.colorMapEntrySize;
    }
    
    public int colorMapLength()
    {
      return this.colorMapLength;
    }
    
    public int colorMapType()
    {
      return this.colorMapType;
    }
    
    public int firstEntryIndex()
    {
      return this.firstEntryIndex;
    }
    
    public int height()
    {
      return this.height;
    }
    
    public int idLength()
    {
      return this.idLength;
    }
    
    public byte imageDescriptor()
    {
      return this.imageDescriptor;
    }
    
    public String imageID()
    {
      return this.imageID;
    }
    
    public byte[] imageIDbuf()
    {
      return this.imageIDbuf;
    }
    
    public int imageType()
    {
      return this.imageType;
    }
    
    public byte interleave()
    {
      return (byte)((this.imageDescriptor & 0xC0) >> 6);
    }
    
    public byte pixelDepth()
    {
      return this.pixelDepth;
    }
    
    public boolean rightToLeft()
    {
      return (this.imageDescriptor & 0x10) != 0;
    }
    
    public int size()
    {
      return this.idLength + 18;
    }
    
    public int tgaType()
    {
      return this.tgaType;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder().append("TGA Header  id length: ").append(this.idLength).append(" color map type: ").append(this.colorMapType).append(" image type: ").append(this.imageType).append(" first entry index: ").append(this.firstEntryIndex).append(" color map length: ").append(this.colorMapLength).append(" color map entry size: ").append(this.colorMapEntrySize).append(" x Origin: ").append(this.xOrigin).append(" y Origin: ").append(this.yOrigin).append(" width: ").append(this.width).append(" height: ").append(this.height).append(" pixel depth: ").append(this.pixelDepth).append(" image descriptor: ").append(this.imageDescriptor);
      if (this.imageIDbuf == null) {}
      for (String str = "";; str = " ID String: " + this.imageID) {
        return str;
      }
    }
    
    public boolean topToBottom()
    {
      return (this.imageDescriptor & 0x20) != 0;
    }
    
    public int width()
    {
      return this.width;
    }
    
    public int xOrigin()
    {
      return this.xOrigin;
    }
    
    public int yOrigin()
    {
      return this.yOrigin;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\texture\tga\TGAImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */