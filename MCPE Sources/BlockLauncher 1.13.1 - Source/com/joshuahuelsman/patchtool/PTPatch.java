package com.joshuahuelsman.patchtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class PTPatch
{
  public static final byte[] magic = { -1, 80, 84, 80 };
  public static final byte[] op_codes = { -86, -35, -18 };
  public int count;
  Header mHeader = new Header();
  public String name;
  private byte[] patch_array;
  
  public static final int byteArrayToInt(byte[] paramArrayOfByte)
  {
    return (paramArrayOfByte[0] << 24) + ((paramArrayOfByte[1] & 0xFF) << 16) + ((paramArrayOfByte[2] & 0xFF) << 8) + (paramArrayOfByte[3] & 0xFF);
  }
  
  public static final byte[] intToByteArray(int paramInt)
  {
    return new byte[] { (byte)(paramInt >>> 24), (byte)(paramInt >>> 16), (byte)(paramInt >>> 8), (byte)paramInt };
  }
  
  public static byte[] readPatch(String paramString)
    throws IOException
  {
    byte[] arrayOfByte = new byte[(int)new File(paramString).length()];
    paramString = new FileInputStream(paramString);
    paramString.read(arrayOfByte, 0, arrayOfByte.length);
    paramString.close();
    return arrayOfByte;
  }
  
  public void applyPatch(File paramFile)
    throws IOException
  {
    Object localObject = new byte[(int)paramFile.length()];
    FileInputStream localFileInputStream = new FileInputStream(paramFile);
    localFileInputStream.read((byte[])localObject);
    localFileInputStream.close();
    localObject = ByteBuffer.wrap((byte[])localObject);
    for (this.count = 0; this.count < this.mHeader.num_patches; this.count += 1)
    {
      ((ByteBuffer)localObject).position(getNextAddr());
      ((ByteBuffer)localObject).put(getNextData());
    }
    paramFile.delete();
    paramFile = new FileOutputStream(paramFile);
    paramFile.write(((ByteBuffer)localObject).array());
    paramFile.close();
  }
  
  public void applyPatch(ByteBuffer paramByteBuffer)
    throws IOException
  {
    for (this.count = 0; this.count < this.mHeader.num_patches; this.count += 1)
    {
      paramByteBuffer.position(getNextAddr());
      paramByteBuffer.put(getNextData());
    }
  }
  
  public void applyPatch(byte[] paramArrayOfByte)
    throws IOException
  {
    applyPatch(ByteBuffer.wrap(paramArrayOfByte));
  }
  
  public boolean checkMagic()
  {
    return (this.patch_array[0] == magic[0]) && (this.patch_array[1] == magic[1]) && (this.patch_array[2] == magic[2]) && (this.patch_array[3] == magic[3]);
  }
  
  public void checkMinecraftVersion() {}
  
  public int getCurrentIndex()
  {
    return byteArrayToInt(new byte[] { this.mHeader.indices[(this.count * 4)], this.mHeader.indices[(this.count * 4 + 1)], this.mHeader.indices[(this.count * 4 + 2)], this.mHeader.indices[(this.count * 4 + 3)] });
  }
  
  public int getDataLength()
  {
    int i = this.mHeader.indices[(this.count * 4)];
    int j = this.mHeader.indices[(this.count * 4 + 1)];
    int k = this.mHeader.indices[(this.count * 4 + 2)];
    int m = this.mHeader.indices[(this.count * 4 + 3)];
    if (this.count != this.mHeader.num_patches - 1) {}
    for (int n = byteArrayToInt(new byte[] { this.mHeader.indices[((this.count + 1) * 4)], this.mHeader.indices[((this.count + 1) * 4 + 1)], this.mHeader.indices[((this.count + 1) * 4 + 2)], this.mHeader.indices[((this.count + 1) * 4 + 3)] });; n = this.patch_array.length) {
      return n - (byteArrayToInt(new byte[] { i, j, k, m }) + 4);
    }
  }
  
  public String getDescription()
  {
    try
    {
      String str = new String(getMetaData(), "UTF-8");
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public byte[] getIndices()
  {
    byte[] arrayOfByte = new byte[this.mHeader.num_patches * 4];
    int i = 0;
    while (i < this.mHeader.num_patches * 4)
    {
      arrayOfByte[i] = this.patch_array[(i + 6)];
      i += 1;
    }
    return arrayOfByte;
  }
  
  public byte[] getMetaData()
  {
    this.count = 0;
    int i = getCurrentIndex();
    int j = this.mHeader.num_patches * 4 + 6;
    byte[] arrayOfByte = new byte[i - j];
    System.arraycopy(this.patch_array, j, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
  
  public int getMinecraftVersion()
  {
    return this.patch_array[4];
  }
  
  public int getNextAddr()
  {
    int i = byteArrayToInt(new byte[] { this.mHeader.indices[(this.count * 4)], this.mHeader.indices[(this.count * 4 + 1)], this.mHeader.indices[(this.count * 4 + 2)], this.mHeader.indices[(this.count * 4 + 3)] });
    return byteArrayToInt(new byte[] { this.patch_array[i], this.patch_array[(i + 1)], this.patch_array[(i + 2)], this.patch_array[(i + 3)] });
  }
  
  public byte[] getNextData()
  {
    byte[] arrayOfByte = new byte[getDataLength()];
    int k = getCurrentIndex();
    int j = 0;
    int i = 0;
    while (i < getDataLength())
    {
      arrayOfByte[j] = this.patch_array[(k + 4 + i)];
      j += 1;
      i += 1;
    }
    return arrayOfByte;
  }
  
  public int getNumPatches()
  {
    return this.patch_array[5];
  }
  
  public void loadPatch(File paramFile)
    throws IOException
  {
    this.patch_array = new byte[(int)paramFile.length()];
    paramFile = new FileInputStream(paramFile);
    paramFile.read(this.patch_array);
    paramFile.close();
    this.mHeader.minecraft_ver = getMinecraftVersion();
    this.mHeader.num_patches = getNumPatches();
    this.mHeader.indices = getIndices();
    this.count = 0;
  }
  
  public void loadPatch(byte[] paramArrayOfByte)
  {
    this.patch_array = paramArrayOfByte;
    this.mHeader.minecraft_ver = getMinecraftVersion();
    this.mHeader.num_patches = getNumPatches();
    this.mHeader.indices = getIndices();
    this.count = 0;
  }
  
  public void removePatch(ByteBuffer paramByteBuffer, byte[] paramArrayOfByte)
  {
    paramArrayOfByte = ByteBuffer.wrap(paramArrayOfByte);
    for (this.count = 0; this.count < this.mHeader.num_patches; this.count += 1)
    {
      int i = getNextAddr();
      paramByteBuffer.position(i);
      paramArrayOfByte.position(i);
      byte[] arrayOfByte = new byte[getDataLength()];
      paramArrayOfByte.get(arrayOfByte);
      paramByteBuffer.put(arrayOfByte);
    }
  }
  
  class Header
  {
    byte[] indices;
    byte[] magic = new byte[4];
    int minecraft_ver;
    int num_patches;
    
    Header() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\joshuahuelsman\patchtool\PTPatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */