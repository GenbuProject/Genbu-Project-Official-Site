package com.microsoft.bond.internal;

import com.microsoft.bond.BondDataType;
import com.microsoft.bond.BondSerializable;
import com.microsoft.bond.CompactBinaryWriter;
import com.microsoft.bond.ProtocolVersion;
import com.microsoft.bond.ProtocolWriter;
import com.microsoft.bond.io.BondOutputStream;
import java.io.IOException;

public class CompactBinaryV2Writer
  extends CompactBinaryWriter
{
  private final CompactBinaryByteCounterWriter byteCounterWriter = new CompactBinaryByteCounterWriter();
  private int currentIndex = 0;
  
  public CompactBinaryV2Writer(BondOutputStream paramBondOutputStream)
  {
    super(ProtocolVersion.TWO, paramBondOutputStream);
  }
  
  public ProtocolWriter getFirstPassWriter()
  {
    if (this.currentIndex == 0) {
      return this.byteCounterWriter;
    }
    return null;
  }
  
  public void writeContainerBegin(int paramInt, BondDataType paramBondDataType)
    throws IOException
  {
    if (paramInt < 7)
    {
      writeUInt8((byte)(paramBondDataType.getValue() | paramInt + 1 << 5));
      return;
    }
    super.writeContainerBegin(paramInt, paramBondDataType);
  }
  
  public void writeEnd()
  {
    this.currentIndex = 0;
    this.byteCounterWriter.reset();
  }
  
  public void writeStructBegin(BondSerializable paramBondSerializable, boolean paramBoolean)
    throws IOException
  {
    if (!paramBoolean)
    {
      writeUInt32(this.byteCounterWriter.getByteLength(this.currentIndex));
      this.currentIndex += 1;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\bond\internal\CompactBinaryV2Writer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */