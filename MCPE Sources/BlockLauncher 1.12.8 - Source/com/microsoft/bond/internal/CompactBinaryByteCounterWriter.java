package com.microsoft.bond.internal;

import com.microsoft.bond.BondBlob;
import com.microsoft.bond.BondDataType;
import com.microsoft.bond.BondSerializable;
import com.microsoft.bond.ProtocolCapability;
import com.microsoft.bond.ProtocolWriter;
import java.io.IOException;

public class CompactBinaryByteCounterWriter
  extends ProtocolWriter
{
  private IntArrayStack byteLengths = new IntArrayStack(32);
  private IntArrayStack byteLengthsIndexes = new IntArrayStack(8);
  private int positionBytes;
  
  public int getByteLength(int paramInt)
  {
    return this.byteLengths.get(paramInt);
  }
  
  public boolean hasCapability(ProtocolCapability paramProtocolCapability)
  {
    boolean bool = true;
    switch (paramProtocolCapability)
    {
    default: 
      bool = super.hasCapability(paramProtocolCapability);
    }
    return bool;
  }
  
  public void reset()
  {
    this.positionBytes = 0;
    this.byteLengths.clear();
    this.byteLengthsIndexes.clear();
  }
  
  public void writeBlob(BondBlob paramBondBlob)
    throws IOException
  {
    this.positionBytes += paramBondBlob.size();
  }
  
  public void writeBool(boolean paramBoolean)
    throws IOException
  {
    this.positionBytes += 1;
  }
  
  public void writeContainerBegin(int paramInt, BondDataType paramBondDataType)
    throws IOException
  {
    int i = this.positionBytes;
    if (paramInt < 7) {}
    for (paramInt = 0;; paramInt = IntegerHelper.getVarUInt32Size(paramInt))
    {
      this.positionBytes = (paramInt + 1 + i);
      return;
    }
  }
  
  public void writeContainerBegin(int paramInt, BondDataType paramBondDataType1, BondDataType paramBondDataType2)
    throws IOException
  {
    this.positionBytes += IntegerHelper.getVarUInt32Size(paramInt) + 2;
  }
  
  public void writeContainerEnd()
    throws IOException
  {}
  
  public void writeDouble(double paramDouble)
    throws IOException
  {
    this.positionBytes += 8;
  }
  
  public void writeFieldBegin(BondDataType paramBondDataType, int paramInt, BondSerializable paramBondSerializable)
    throws IOException
  {
    if (paramInt <= 5)
    {
      this.positionBytes += 1;
      return;
    }
    if (paramInt <= 255)
    {
      this.positionBytes += 2;
      return;
    }
    this.positionBytes += 3;
  }
  
  public void writeFieldEnd()
    throws IOException
  {}
  
  public void writeFieldOmitted(BondDataType paramBondDataType, int paramInt, BondSerializable paramBondSerializable)
    throws IOException
  {}
  
  public void writeFloat(float paramFloat)
    throws IOException
  {
    this.positionBytes += 4;
  }
  
  public void writeInt16(short paramShort)
    throws IOException
  {
    writeUInt16(IntegerHelper.encodeZigzag16(paramShort));
  }
  
  public void writeInt32(int paramInt)
    throws IOException
  {
    writeUInt32(IntegerHelper.encodeZigzag32(paramInt));
  }
  
  public void writeInt64(long paramLong)
    throws IOException
  {
    writeUInt64(IntegerHelper.encodeZigzag64(paramLong));
  }
  
  public void writeInt8(byte paramByte)
    throws IOException
  {
    this.positionBytes += 1;
  }
  
  public void writeString(String paramString)
    throws IOException
  {
    if ((paramString == null) || (paramString.isEmpty()))
    {
      this.positionBytes += 1;
      return;
    }
    int i = StringHelper.encodeToUtf8(paramString).length;
    int j = this.positionBytes;
    this.positionBytes = (i + IntegerHelper.getVarUInt32Size(i) + j);
  }
  
  public void writeStructBegin(BondSerializable paramBondSerializable, boolean paramBoolean)
    throws IOException
  {
    if (!paramBoolean)
    {
      this.byteLengthsIndexes.push(this.byteLengths.getSize());
      this.byteLengths.push(this.positionBytes);
    }
  }
  
  public void writeStructEnd(boolean paramBoolean)
    throws IOException
  {
    this.positionBytes += 1;
    if (!paramBoolean)
    {
      int i = this.byteLengthsIndexes.pop();
      int j = this.positionBytes - this.byteLengths.get(i);
      this.byteLengths.set(i, j);
      this.positionBytes += IntegerHelper.getVarUInt32Size(j);
    }
  }
  
  public void writeUInt16(short paramShort)
    throws IOException
  {
    this.positionBytes += IntegerHelper.getVarUInt16Size(paramShort);
  }
  
  public void writeUInt32(int paramInt)
    throws IOException
  {
    this.positionBytes += IntegerHelper.getVarUInt32Size(paramInt);
  }
  
  public void writeUInt64(long paramLong)
    throws IOException
  {
    this.positionBytes += IntegerHelper.getVarUInt64Size(paramLong);
  }
  
  public void writeUInt8(byte paramByte)
    throws IOException
  {
    this.positionBytes += 1;
  }
  
  public void writeVersion()
    throws IOException
  {
    this.positionBytes += 4;
  }
  
  public void writeWString(String paramString)
    throws IOException
  {
    if (paramString.isEmpty())
    {
      this.positionBytes += 1;
      return;
    }
    int i = StringHelper.encodeToUtf16(paramString).length;
    int j = this.positionBytes;
    this.positionBytes = (i + IntegerHelper.getVarUInt32Size(paramString.length()) + j);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\internal\CompactBinaryByteCounterWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */