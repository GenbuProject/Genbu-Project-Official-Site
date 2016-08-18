package com.microsoft.bond.internal;

import com.microsoft.bond.BondDataType;
import com.microsoft.bond.BondException;
import com.microsoft.bond.ProtocolReader;
import com.microsoft.bond.ProtocolReader.ListTag;
import com.microsoft.bond.ProtocolReader.MapTag;
import com.microsoft.bond.Void;
import java.io.IOException;

public class SkipHelper
{
  public static void skip(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    int i;
    switch (paramBondDataType)
    {
    default: 
      throw new BondException("Unknown type to skip: " + paramBondDataType.toString());
    case ???: 
      paramProtocolReader.readBool();
      return;
    case ???: 
      paramProtocolReader.readUInt8();
      return;
    case ???: 
      paramProtocolReader.readUInt16();
      return;
    case ???: 
      paramProtocolReader.readUInt32();
      return;
    case ???: 
      paramProtocolReader.readUInt64();
      return;
    case ???: 
      paramProtocolReader.readFloat();
      return;
    case ???: 
      paramProtocolReader.readDouble();
      return;
    case ???: 
      paramProtocolReader.readString();
      return;
    case ???: 
      new Void().readNested(paramProtocolReader);
      return;
    case ???: 
    case ???: 
      paramBondDataType = paramProtocolReader.readContainerBegin();
      i = 0;
      while (i < paramBondDataType.size)
      {
        paramProtocolReader.skip(paramBondDataType.type);
        i += 1;
      }
      paramProtocolReader.readContainerEnd();
      return;
    case ???: 
      paramBondDataType = paramProtocolReader.readMapContainerBegin();
      i = 0;
      while (i < paramBondDataType.size)
      {
        paramProtocolReader.skip(paramBondDataType.keyType);
        paramProtocolReader.skip(paramBondDataType.valueType);
        i += 1;
      }
      paramProtocolReader.readContainerEnd();
      return;
    case ???: 
      paramProtocolReader.readInt8();
      return;
    case ???: 
      paramProtocolReader.readInt16();
      return;
    case ???: 
      paramProtocolReader.readInt32();
      return;
    case ???: 
      paramProtocolReader.readInt64();
      return;
    }
    paramProtocolReader.readWString();
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\bond\internal\SkipHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */