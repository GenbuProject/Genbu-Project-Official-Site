package com.microsoft.bond.internal;

import com.microsoft.bond.BondDataType;
import com.microsoft.bond.CompactBinaryReader;
import com.microsoft.bond.ProtocolReader.ListTag;
import com.microsoft.bond.ProtocolVersion;
import com.microsoft.bond.io.BondInputStream;
import java.io.IOException;

public class CompactBinaryV2Reader
  extends CompactBinaryReader
{
  public CompactBinaryV2Reader(BondInputStream paramBondInputStream)
  {
    super(ProtocolVersion.TWO, paramBondInputStream);
  }
  
  public ProtocolReader.ListTag readContainerBegin()
    throws IOException
  {
    int i = readUInt8();
    BondDataType localBondDataType = BondDataType.fromValue(i & 0x1F);
    if ((i & 0xE0) != 0) {
      return new ProtocolReader.ListTag(((byte)(i >> 5) & 0x7) - 1, localBondDataType);
    }
    return new ProtocolReader.ListTag(readUInt32(), localBondDataType);
  }
  
  public void readStructBegin(boolean paramBoolean)
    throws IOException
  {
    if (!paramBoolean) {
      readUInt32();
    }
  }
  
  public void skip(BondDataType paramBondDataType)
    throws IOException
  {
    switch (paramBondDataType)
    {
    default: 
      super.skip(paramBondDataType);
      return;
    }
    int i = readUInt32();
    this.stream.setPositionRelative(i);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\bond\internal\CompactBinaryV2Reader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */