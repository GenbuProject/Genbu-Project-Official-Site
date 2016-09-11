package com.microsoft.bond;

import java.io.IOException;
import java.util.Random;

public class RandomProtocolReader
  extends ProtocolReader
{
  private static final int DEFAULT_MAX_CONTAINER_SIZE = 10;
  private static final int DEFAULT_MAX_STRING_LENGTH = 20;
  private final int maxContainerSize;
  private final int maxStringLength;
  private final Random random;
  
  public RandomProtocolReader()
  {
    this.maxStringLength = 20;
    this.maxContainerSize = 10;
    this.random = new Random();
  }
  
  public RandomProtocolReader(long paramLong)
  {
    this(paramLong, 20, 10);
  }
  
  public RandomProtocolReader(long paramLong, int paramInt1, int paramInt2)
  {
    this.maxStringLength = paramInt1;
    this.maxContainerSize = paramInt2;
    this.random = new Random(paramLong);
  }
  
  public ProtocolReader clone()
  {
    return null;
  }
  
  public int getPosition()
    throws IOException
  {
    throw new IOException();
  }
  
  public boolean hasCapability(ProtocolCapability paramProtocolCapability)
  {
    return false;
  }
  
  public boolean isProtocolSame(ProtocolWriter paramProtocolWriter)
  {
    return false;
  }
  
  public BondBlob readBlob(int paramInt)
  {
    return null;
  }
  
  public boolean readBool()
  {
    return this.random.nextBoolean();
  }
  
  public ProtocolReader.ListTag readContainerBegin()
  {
    return new ProtocolReader.ListTag(this.random.nextInt(this.maxContainerSize) + 1, BondDataType.BT_UNAVAILABLE);
  }
  
  public void readContainerEnd() {}
  
  public double readDouble()
  {
    return this.random.nextLong() * this.random.nextDouble();
  }
  
  public float readFloat()
  {
    return (float)this.random.nextLong() * this.random.nextFloat();
  }
  
  public short readInt16()
  {
    return (short)(this.random.nextInt(65535) - 32767);
  }
  
  public int readInt32()
  {
    return this.random.nextInt();
  }
  
  public long readInt64()
  {
    return this.random.nextLong();
  }
  
  public byte readInt8()
  {
    return (byte)(this.random.nextInt(255) - 127);
  }
  
  public ProtocolReader.MapTag readMapContainerBegin()
  {
    return new ProtocolReader.MapTag(this.random.nextInt(this.maxContainerSize) + 1, BondDataType.BT_UNAVAILABLE, BondDataType.BT_UNAVAILABLE);
  }
  
  public String readString()
  {
    int j = this.random.nextInt(this.maxStringLength) + 1;
    StringBuilder localStringBuilder = new StringBuilder(j);
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append((char)(this.random.nextInt(94) + 32));
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public short readUInt16()
  {
    return (short)(0xFFFF & this.random.nextInt());
  }
  
  public int readUInt32()
  {
    return this.random.nextInt();
  }
  
  public long readUInt64()
  {
    return this.random.nextLong();
  }
  
  public byte readUInt8()
  {
    return (byte)this.random.nextInt(255);
  }
  
  public String readWString()
  {
    return readString();
  }
  
  public void setPosition(int paramInt)
    throws IOException
  {
    throw new IOException();
  }
  
  public void skip(BondDataType paramBondDataType) {}
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\RandomProtocolReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */