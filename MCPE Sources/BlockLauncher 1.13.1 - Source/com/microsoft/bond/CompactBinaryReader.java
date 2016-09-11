package com.microsoft.bond;

import com.microsoft.bond.internal.CompactBinaryV2Reader;
import com.microsoft.bond.internal.DecimalHelper;
import com.microsoft.bond.internal.IntegerHelper;
import com.microsoft.bond.internal.SkipHelper;
import com.microsoft.bond.internal.StringHelper;
import com.microsoft.bond.io.BondInputStream;
import com.microsoft.bond.io.MemoryBondInputStream;
import java.io.IOException;

public class CompactBinaryReader
  extends ProtocolReader
{
  private byte[] readBuffer;
  protected final BondInputStream stream;
  private final ProtocolVersion version;
  
  protected CompactBinaryReader(ProtocolVersion paramProtocolVersion, BondInputStream paramBondInputStream)
  {
    this.version = paramProtocolVersion;
    this.stream = paramBondInputStream;
    this.readBuffer = new byte[64];
  }
  
  private void SkipContainer()
    throws IOException
  {
    ProtocolReader.ListTag localListTag = readContainerBegin();
    if ((localListTag.type == BondDataType.BT_UINT8) || (localListTag.type == BondDataType.BT_INT8)) {
      this.stream.setPositionRelative(localListTag.size);
    }
    for (;;)
    {
      readContainerEnd();
      return;
      int i = 0;
      while (i < localListTag.size)
      {
        skip(localListTag.type);
        i += 1;
      }
    }
  }
  
  public static CompactBinaryReader createV1(BondInputStream paramBondInputStream)
  {
    return new CompactBinaryReader(ProtocolVersion.ONE, paramBondInputStream);
  }
  
  public static CompactBinaryReader createV1(byte[] paramArrayOfByte)
  {
    return createV1(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static CompactBinaryReader createV1(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return createV1(new MemoryBondInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  public static CompactBinaryReader createV2(BondInputStream paramBondInputStream)
  {
    return new CompactBinaryV2Reader(paramBondInputStream);
  }
  
  public static CompactBinaryReader createV2(byte[] paramArrayOfByte)
  {
    return createV2(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static CompactBinaryReader createV2(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return createV2(new MemoryBondInputStream(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  private void ensureReadBufferCapacity(int paramInt)
  {
    if (this.readBuffer.length < paramInt) {
      this.readBuffer = new byte[paramInt];
    }
  }
  
  public ProtocolReader cloneReader()
    throws IOException
  {
    BondInputStream localBondInputStream = this.stream.clone(true);
    if (this.version == ProtocolVersion.ONE) {
      return createV1(localBondInputStream);
    }
    if (this.version == ProtocolVersion.TWO) {
      return createV2(localBondInputStream);
    }
    return null;
  }
  
  public void close()
    throws IOException
  {
    this.stream.close();
  }
  
  public int getPosition()
    throws IOException
  {
    return this.stream.getPosition();
  }
  
  public boolean hasCapability(ProtocolCapability paramProtocolCapability)
  {
    switch (paramProtocolCapability)
    {
    default: 
      return super.hasCapability(paramProtocolCapability);
    case ???: 
      return this.stream.isCloneable();
    case ???: 
    case ???: 
      return true;
    }
    return this.stream.isSeekable();
  }
  
  public boolean isProtocolSame(ProtocolWriter paramProtocolWriter)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramProtocolWriter instanceof CompactBinaryWriter))
    {
      paramProtocolWriter = (CompactBinaryWriter)paramProtocolWriter;
      bool1 = bool2;
      if (this.version == paramProtocolWriter.getVersion()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public BondBlob readBlob(int paramInt)
    throws IOException
  {
    return this.stream.readBlob(paramInt);
  }
  
  public boolean readBool()
    throws IOException
  {
    return readUInt8() != 0;
  }
  
  public ProtocolReader.ListTag readContainerBegin()
    throws IOException
  {
    BondDataType localBondDataType = BondDataType.fromValue(readUInt8());
    return new ProtocolReader.ListTag(readUInt32(), localBondDataType);
  }
  
  public void readContainerEnd() {}
  
  public double readDouble()
    throws IOException
  {
    ensureReadBufferCapacity(8);
    this.stream.read(this.readBuffer, 0, 8);
    return DecimalHelper.decodeDouble(this.readBuffer);
  }
  
  public ProtocolReader.FieldTag readFieldBegin()
    throws IOException
  {
    BondDataType localBondDataType = BondDataType.BT_STOP;
    int i = this.stream.read();
    localBondDataType = BondDataType.fromValue(i & 0x1F);
    i &= 0xE0;
    if (i == 224) {
      i = this.stream.read() & 0xFF | (this.stream.read() & 0xFF) << 8;
    }
    for (;;)
    {
      return new ProtocolReader.FieldTag(localBondDataType, i);
      if (i == 192) {
        i = this.stream.read();
      } else {
        i >>= 5;
      }
    }
  }
  
  public float readFloat()
    throws IOException
  {
    ensureReadBufferCapacity(4);
    this.stream.read(this.readBuffer, 0, 4);
    return DecimalHelper.decodeFloat(this.readBuffer);
  }
  
  public short readInt16()
    throws IOException
  {
    return IntegerHelper.decodeZigzag16(IntegerHelper.decodeVarInt16(this.stream));
  }
  
  public int readInt32()
    throws IOException
  {
    return IntegerHelper.decodeZigzag32(IntegerHelper.decodeVarInt32(this.stream));
  }
  
  public long readInt64()
    throws IOException
  {
    return IntegerHelper.decodeZigzag64(IntegerHelper.decodeVarInt64(this.stream));
  }
  
  public byte readInt8()
    throws IOException
  {
    return this.stream.read();
  }
  
  public ProtocolReader.MapTag readMapContainerBegin()
    throws IOException
  {
    BondDataType localBondDataType1 = BondDataType.fromValue(readUInt8());
    BondDataType localBondDataType2 = BondDataType.fromValue(readUInt8());
    return new ProtocolReader.MapTag(readUInt32(), localBondDataType1, localBondDataType2);
  }
  
  public String readString()
    throws IOException
  {
    int i = IntegerHelper.decodeVarInt32(this.stream);
    if (i == 0) {
      return "";
    }
    ensureReadBufferCapacity(i);
    this.stream.read(this.readBuffer, 0, i);
    return StringHelper.decodeFromUtf8(this.readBuffer, 0, i);
  }
  
  public short readUInt16()
    throws IOException
  {
    return IntegerHelper.decodeVarInt16(this.stream);
  }
  
  public int readUInt32()
    throws IOException
  {
    return IntegerHelper.decodeVarInt32(this.stream);
  }
  
  public long readUInt64()
    throws IOException
  {
    return IntegerHelper.decodeVarInt64(this.stream);
  }
  
  public byte readUInt8()
    throws IOException
  {
    return this.stream.read();
  }
  
  public String readWString()
    throws IOException
  {
    int i = IntegerHelper.decodeVarInt32(this.stream) << 1;
    if (i == 0) {
      return "";
    }
    ensureReadBufferCapacity(i);
    this.stream.read(this.readBuffer, 0, i);
    return StringHelper.decodeFromUtf16(this.readBuffer, 0, i);
  }
  
  public void setPosition(int paramInt)
    throws IOException
  {
    this.stream.setPosition(paramInt);
  }
  
  public void skip(BondDataType paramBondDataType)
    throws IOException
  {
    int i;
    switch (1.$SwitchMap$com$microsoft$bond$BondDataType[paramBondDataType.ordinal()])
    {
    default: 
      SkipHelper.skip(this, paramBondDataType);
      return;
    case 1: 
      i = readUInt32();
      this.stream.setPositionRelative(i);
      return;
    case 2: 
      i = readUInt32();
      this.stream.setPositionRelative(i << 1);
      return;
    case 3: 
    case 4: 
      SkipContainer();
      return;
    }
    do
    {
      for (paramBondDataType = readFieldBegin(); (paramBondDataType.type != BondDataType.BT_STOP) && (paramBondDataType.type != BondDataType.BT_STOP_BASE); paramBondDataType = readFieldBegin())
      {
        skip(paramBondDataType.type);
        readFieldEnd();
      }
    } while (paramBondDataType.type != BondDataType.BT_STOP);
  }
  
  public String toString()
  {
    return String.format("[%s version=%d]", new Object[] { getClass().getName(), Short.valueOf(this.version.getValue()) });
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\CompactBinaryReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */