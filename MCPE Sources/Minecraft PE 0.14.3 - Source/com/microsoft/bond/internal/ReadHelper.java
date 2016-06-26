package com.microsoft.bond.internal;

import com.microsoft.bond.BondBlob;
import com.microsoft.bond.BondDataType;
import com.microsoft.bond.ProtocolReader;
import com.microsoft.bond.ProtocolReader.FieldTag;
import com.microsoft.bond.ProtocolReader.ListTag;
import java.io.IOException;

public final class ReadHelper
{
  public static void invalideType(BondDataType paramBondDataType1, BondDataType paramBondDataType2) {}
  
  public static BondBlob readBlob(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    validateType(paramBondDataType, BondDataType.BT_LIST);
    paramBondDataType = paramProtocolReader.readContainerBegin();
    validateType(paramBondDataType.type, BondDataType.BT_LIST);
    if (paramBondDataType.size == 0) {}
    for (paramBondDataType = new BondBlob();; paramBondDataType = new BondBlob(paramProtocolReader.readBlob(paramBondDataType.size)))
    {
      paramProtocolReader.readContainerEnd();
      return paramBondDataType;
    }
  }
  
  public static boolean readBool(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    validateType(paramBondDataType, BondDataType.BT_BOOL);
    return paramProtocolReader.readBool();
  }
  
  public static double readDouble(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    if ((paramBondDataType == BondDataType.BT_DOUBLE) || (paramBondDataType == BondDataType.BT_UNAVAILABLE)) {
      return paramProtocolReader.readDouble();
    }
    if (paramBondDataType == BondDataType.BT_FLOAT) {
      return paramProtocolReader.readFloat();
    }
    invalideType(paramBondDataType, BondDataType.BT_DOUBLE);
    return 0.0D;
  }
  
  public static float readFloat(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    validateType(paramBondDataType, BondDataType.BT_FLOAT);
    return paramProtocolReader.readFloat();
  }
  
  public static short readInt16(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    if ((paramBondDataType == BondDataType.BT_INT16) || (paramBondDataType == BondDataType.BT_UNAVAILABLE)) {
      return paramProtocolReader.readInt16();
    }
    if (paramBondDataType == BondDataType.BT_INT8) {
      return (short)paramProtocolReader.readInt8();
    }
    invalideType(paramBondDataType, BondDataType.BT_INT16);
    return 0;
  }
  
  public static int readInt32(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    if ((paramBondDataType == BondDataType.BT_INT32) || (paramBondDataType == BondDataType.BT_UNAVAILABLE)) {
      return paramProtocolReader.readInt32();
    }
    if (paramBondDataType == BondDataType.BT_INT16) {
      return paramProtocolReader.readInt16();
    }
    if (paramBondDataType == BondDataType.BT_INT8) {
      return paramProtocolReader.readInt8();
    }
    invalideType(paramBondDataType, BondDataType.BT_INT32);
    return 0;
  }
  
  public static long readInt64(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    if ((paramBondDataType == BondDataType.BT_INT64) || (paramBondDataType == BondDataType.BT_UNAVAILABLE)) {
      return paramProtocolReader.readInt64();
    }
    if (paramBondDataType == BondDataType.BT_INT32) {
      return paramProtocolReader.readInt32();
    }
    if (paramBondDataType == BondDataType.BT_INT16) {
      return paramProtocolReader.readInt16();
    }
    if (paramBondDataType == BondDataType.BT_INT8) {
      return paramProtocolReader.readInt8();
    }
    invalideType(paramBondDataType, BondDataType.BT_INT64);
    return 0L;
  }
  
  public static byte readInt8(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    validateType(paramBondDataType, BondDataType.BT_INT8);
    return paramProtocolReader.readInt8();
  }
  
  public static String readString(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    validateType(paramBondDataType, BondDataType.BT_STRING);
    return paramProtocolReader.readString();
  }
  
  public static short readUInt16(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    if ((paramBondDataType == BondDataType.BT_UINT16) || (paramBondDataType == BondDataType.BT_UNAVAILABLE)) {
      return paramProtocolReader.readUInt16();
    }
    if (paramBondDataType == BondDataType.BT_UINT8) {
      return (short)paramProtocolReader.readUInt8();
    }
    invalideType(paramBondDataType, BondDataType.BT_UINT16);
    return 0;
  }
  
  public static int readUInt32(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    if ((paramBondDataType == BondDataType.BT_UINT32) || (paramBondDataType == BondDataType.BT_UNAVAILABLE)) {
      return paramProtocolReader.readUInt32();
    }
    if (paramBondDataType == BondDataType.BT_UINT16) {
      return paramProtocolReader.readUInt16();
    }
    if (paramBondDataType == BondDataType.BT_UINT8) {
      return paramProtocolReader.readUInt8();
    }
    invalideType(paramBondDataType, BondDataType.BT_UINT32);
    return 0;
  }
  
  public static long readUInt64(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    if ((paramBondDataType == BondDataType.BT_UINT64) || (paramBondDataType == BondDataType.BT_UNAVAILABLE)) {
      return paramProtocolReader.readUInt64();
    }
    if (paramBondDataType == BondDataType.BT_UINT32) {
      return paramProtocolReader.readUInt32();
    }
    if (paramBondDataType == BondDataType.BT_UINT16) {
      return paramProtocolReader.readUInt16();
    }
    if (paramBondDataType == BondDataType.BT_UINT8) {
      return paramProtocolReader.readUInt8();
    }
    invalideType(paramBondDataType, BondDataType.BT_UINT64);
    return 0L;
  }
  
  public static byte readUInt8(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    validateType(paramBondDataType, BondDataType.BT_UINT8);
    return paramProtocolReader.readUInt8();
  }
  
  public static String readWString(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    validateType(paramBondDataType, BondDataType.BT_WSTRING);
    return paramProtocolReader.readWString();
  }
  
  public static void skipPartialStruct(ProtocolReader paramProtocolReader)
    throws IOException
  {
    ProtocolReader.FieldTag localFieldTag;
    do
    {
      paramProtocolReader.readStructBegin(true);
      for (localFieldTag = paramProtocolReader.readFieldBegin(); (localFieldTag.type != BondDataType.BT_STOP) && (localFieldTag.type != BondDataType.BT_STOP_BASE); localFieldTag = paramProtocolReader.readFieldBegin())
      {
        paramProtocolReader.skip(localFieldTag.type);
        paramProtocolReader.readFieldEnd();
      }
      paramProtocolReader.readStructEnd();
    } while (BondDataType.BT_STOP != localFieldTag.type);
  }
  
  public static void validateType(BondDataType paramBondDataType1, BondDataType paramBondDataType2)
  {
    if ((paramBondDataType1 != paramBondDataType2) && (paramBondDataType1 != BondDataType.BT_UNAVAILABLE)) {
      invalideType(paramBondDataType1, paramBondDataType2);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\bond\internal\ReadHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */