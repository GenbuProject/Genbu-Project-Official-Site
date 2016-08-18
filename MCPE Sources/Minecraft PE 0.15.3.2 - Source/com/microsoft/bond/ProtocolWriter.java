package com.microsoft.bond;

import java.io.Closeable;
import java.io.IOException;

public abstract class ProtocolWriter
  implements Closeable
{
  public void close()
    throws IOException
  {}
  
  public ProtocolWriter getFirstPassWriter()
  {
    return null;
  }
  
  public boolean hasCapability(ProtocolCapability paramProtocolCapability)
  {
    return false;
  }
  
  public void writeBegin() {}
  
  public abstract void writeBlob(BondBlob paramBondBlob)
    throws IOException;
  
  public abstract void writeBool(boolean paramBoolean)
    throws IOException;
  
  public abstract void writeContainerBegin(int paramInt, BondDataType paramBondDataType)
    throws IOException;
  
  public abstract void writeContainerBegin(int paramInt, BondDataType paramBondDataType1, BondDataType paramBondDataType2)
    throws IOException;
  
  public abstract void writeContainerEnd()
    throws IOException;
  
  public abstract void writeDouble(double paramDouble)
    throws IOException;
  
  public void writeEnd() {}
  
  public void writeFieldBegin(BondDataType paramBondDataType, int paramInt, BondSerializable paramBondSerializable)
    throws IOException
  {}
  
  public void writeFieldEnd()
    throws IOException
  {}
  
  public void writeFieldOmitted(BondDataType paramBondDataType, int paramInt, BondSerializable paramBondSerializable)
    throws IOException
  {}
  
  public abstract void writeFloat(float paramFloat)
    throws IOException;
  
  public abstract void writeInt16(short paramShort)
    throws IOException;
  
  public abstract void writeInt32(int paramInt)
    throws IOException;
  
  public abstract void writeInt64(long paramLong)
    throws IOException;
  
  public abstract void writeInt8(byte paramByte)
    throws IOException;
  
  public abstract void writeString(String paramString)
    throws IOException;
  
  public void writeStructBegin(BondSerializable paramBondSerializable, boolean paramBoolean)
    throws IOException
  {}
  
  public void writeStructEnd(boolean paramBoolean)
    throws IOException
  {}
  
  public abstract void writeUInt16(short paramShort)
    throws IOException;
  
  public abstract void writeUInt32(int paramInt)
    throws IOException;
  
  public abstract void writeUInt64(long paramLong)
    throws IOException;
  
  public abstract void writeUInt8(byte paramByte)
    throws IOException;
  
  public abstract void writeVersion()
    throws IOException;
  
  public abstract void writeWString(String paramString)
    throws IOException;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\bond\ProtocolWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */