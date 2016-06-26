package com.microsoft.bond;

import com.microsoft.bond.internal.CompactBinaryV2Writer;
import com.microsoft.bond.internal.DecimalHelper;
import com.microsoft.bond.internal.IntegerHelper;
import com.microsoft.bond.internal.StringHelper;
import com.microsoft.bond.io.BondOutputStream;
import java.io.IOException;

public class CompactBinaryWriter
  extends ProtocolWriter
{
  public static final short MAGIC = (short)ProtocolType.COMPACT_PROTOCOL.getValue();
  private final BondOutputStream stream;
  private final ProtocolVersion version;
  private final byte[] writeBuffer;
  
  protected CompactBinaryWriter(ProtocolVersion paramProtocolVersion, BondOutputStream paramBondOutputStream)
  {
    this.version = paramProtocolVersion;
    this.stream = paramBondOutputStream;
    this.writeBuffer = new byte[10];
  }
  
  public static CompactBinaryWriter createV1(BondOutputStream paramBondOutputStream)
  {
    return new CompactBinaryWriter(ProtocolVersion.ONE, paramBondOutputStream);
  }
  
  public static CompactBinaryWriter createV2(BondOutputStream paramBondOutputStream)
  {
    return new CompactBinaryV2Writer(paramBondOutputStream);
  }
  
  public ProtocolVersion getVersion()
  {
    return this.version;
  }
  
  public boolean hasCapability(ProtocolCapability paramProtocolCapability)
  {
    switch (paramProtocolCapability)
    {
    default: 
      return super.hasCapability(paramProtocolCapability);
    }
    return true;
  }
  
  public String toString()
  {
    return String.format("[%s version=%d]", new Object[] { getClass().getName(), Short.valueOf(this.version.getValue()) });
  }
  
  public void writeBlob(BondBlob paramBondBlob)
    throws IOException
  {
    this.stream.write(paramBondBlob.getBuffer(), paramBondBlob.getOffset(), paramBondBlob.size());
  }
  
  public void writeBool(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      writeUInt8((byte)i);
      return;
    }
  }
  
  public void writeContainerBegin(int paramInt, BondDataType paramBondDataType)
    throws IOException
  {
    writeUInt8((byte)paramBondDataType.getValue());
    writeUInt32(paramInt);
  }
  
  public void writeContainerBegin(int paramInt, BondDataType paramBondDataType1, BondDataType paramBondDataType2)
    throws IOException
  {
    writeUInt8((byte)paramBondDataType1.getValue());
    writeUInt8((byte)paramBondDataType2.getValue());
    writeUInt32(paramInt);
  }
  
  public void writeContainerEnd() {}
  
  public void writeDouble(double paramDouble)
    throws IOException
  {
    DecimalHelper.encodeDouble(paramDouble, this.writeBuffer);
    this.stream.write(this.writeBuffer, 0, 8);
  }
  
  public void writeFieldBegin(BondDataType paramBondDataType, int paramInt, BondSerializable paramBondSerializable)
    throws IOException
  {
    int i = (byte)paramBondDataType.getValue();
    if (paramInt <= 5)
    {
      this.stream.write(i | paramInt << 5);
      return;
    }
    if (paramInt <= 255)
    {
      this.stream.write(i | 0xC0);
      this.stream.write(paramInt);
      return;
    }
    this.stream.write(i | 0xE0);
    this.stream.write(paramInt);
    this.stream.write(paramInt >>> 8);
  }
  
  public void writeFloat(float paramFloat)
    throws IOException
  {
    DecimalHelper.encodeFloat(paramFloat, this.writeBuffer);
    this.stream.write(this.writeBuffer, 0, 4);
  }
  
  public void writeInt16(short paramShort)
    throws IOException
  {
    int i = IntegerHelper.encodeVarUInt16(IntegerHelper.encodeZigzag16(paramShort), this.writeBuffer, 0);
    this.stream.write(this.writeBuffer, 0, i);
  }
  
  public void writeInt32(int paramInt)
    throws IOException
  {
    paramInt = IntegerHelper.encodeVarUInt32(IntegerHelper.encodeZigzag32(paramInt), this.writeBuffer, 0);
    this.stream.write(this.writeBuffer, 0, paramInt);
  }
  
  public void writeInt64(long paramLong)
    throws IOException
  {
    int i = IntegerHelper.encodeVarUInt64(IntegerHelper.encodeZigzag64(paramLong), this.writeBuffer, 0);
    this.stream.write(this.writeBuffer, 0, i);
  }
  
  public void writeInt8(byte paramByte)
    throws IOException
  {
    this.stream.write(paramByte);
  }
  
  public void writeString(String paramString)
    throws IOException
  {
    if (paramString.isEmpty())
    {
      writeUInt32(0);
      return;
    }
    paramString = StringHelper.encodeToUtf8(paramString);
    writeUInt32(paramString.length);
    this.stream.write(paramString);
  }
  
  public void writeStructEnd(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {}
    for (int i = BondDataType.BT_STOP_BASE.getValue();; i = BondDataType.BT_STOP.getValue())
    {
      writeUInt8((byte)i);
      return;
    }
  }
  
  public void writeUInt16(short paramShort)
    throws IOException
  {
    int i = IntegerHelper.encodeVarUInt16(paramShort, this.writeBuffer, 0);
    this.stream.write(this.writeBuffer, 0, i);
  }
  
  public void writeUInt32(int paramInt)
    throws IOException
  {
    paramInt = IntegerHelper.encodeVarUInt32(paramInt, this.writeBuffer, 0);
    this.stream.write(this.writeBuffer, 0, paramInt);
  }
  
  public void writeUInt64(long paramLong)
    throws IOException
  {
    int i = IntegerHelper.encodeVarUInt64(paramLong, this.writeBuffer, 0);
    this.stream.write(this.writeBuffer, 0, i);
  }
  
  public void writeUInt8(byte paramByte)
    throws IOException
  {
    this.stream.write(paramByte);
  }
  
  public void writeVersion()
    throws IOException
  {
    writeUInt16(MAGIC);
    writeUInt16(this.version.getValue());
  }
  
  public void writeWString(String paramString)
    throws IOException
  {
    if (paramString.isEmpty())
    {
      writeUInt32(0);
      return;
    }
    writeUInt32(paramString.length());
    paramString = StringHelper.encodeToUtf16(paramString);
    this.stream.write(paramString, 0, paramString.length);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\CompactBinaryWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */