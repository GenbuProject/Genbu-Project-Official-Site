package com.microsoft.bond;

import java.io.Closeable;
import java.io.IOException;

public abstract class ProtocolReader
  implements Closeable
{
  public ProtocolReader cloneReader()
    throws IOException
  {
    return null;
  }
  
  public void close()
    throws IOException
  {}
  
  public abstract int getPosition()
    throws IOException;
  
  public boolean hasCapability(ProtocolCapability paramProtocolCapability)
  {
    return false;
  }
  
  public abstract boolean isProtocolSame(ProtocolWriter paramProtocolWriter);
  
  public void readBegin() {}
  
  public abstract BondBlob readBlob(int paramInt)
    throws IOException;
  
  public abstract boolean readBool()
    throws IOException;
  
  public abstract ListTag readContainerBegin()
    throws IOException;
  
  public abstract void readContainerEnd()
    throws IOException;
  
  public abstract double readDouble()
    throws IOException;
  
  public void readEnd() {}
  
  public FieldTag readFieldBegin()
    throws IOException
  {
    return new FieldTag(BondDataType.BT_UNAVAILABLE, 32767);
  }
  
  public void readFieldEnd()
    throws IOException
  {}
  
  public boolean readFieldOmitted()
    throws IOException
  {
    return false;
  }
  
  public abstract float readFloat()
    throws IOException;
  
  public abstract short readInt16()
    throws IOException;
  
  public abstract int readInt32()
    throws IOException;
  
  public abstract long readInt64()
    throws IOException;
  
  public abstract byte readInt8()
    throws IOException;
  
  public abstract MapTag readMapContainerBegin()
    throws IOException;
  
  public abstract String readString()
    throws IOException;
  
  public void readStructBegin(boolean paramBoolean)
    throws IOException
  {}
  
  public void readStructEnd()
    throws IOException
  {}
  
  public abstract short readUInt16()
    throws IOException;
  
  public abstract int readUInt32()
    throws IOException;
  
  public abstract long readUInt64()
    throws IOException;
  
  public abstract byte readUInt8()
    throws IOException;
  
  public abstract String readWString()
    throws IOException;
  
  public abstract void setPosition(int paramInt)
    throws IOException;
  
  public abstract void skip(BondDataType paramBondDataType)
    throws IOException;
  
  public static class FieldTag
  {
    public final int id;
    public final BondDataType type;
    
    FieldTag(BondDataType paramBondDataType, int paramInt)
    {
      this.type = paramBondDataType;
      this.id = paramInt;
    }
  }
  
  public static class ListTag
  {
    public final int size;
    public final BondDataType type;
    
    public ListTag(int paramInt, BondDataType paramBondDataType)
    {
      this.size = paramInt;
      this.type = paramBondDataType;
    }
  }
  
  public static class MapTag
  {
    public final BondDataType keyType;
    public final int size;
    public final BondDataType valueType;
    
    public MapTag(int paramInt, BondDataType paramBondDataType1, BondDataType paramBondDataType2)
    {
      this.size = paramInt;
      this.keyType = paramBondDataType1;
      this.valueType = paramBondDataType2;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\ProtocolReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */