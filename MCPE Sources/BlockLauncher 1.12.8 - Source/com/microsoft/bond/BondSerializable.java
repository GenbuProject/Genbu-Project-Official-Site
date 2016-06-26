package com.microsoft.bond;

import java.io.IOException;
import java.io.InputStream;

public abstract interface BondSerializable
{
  public abstract BondSerializable clone();
  
  public abstract void marshal(ProtocolWriter paramProtocolWriter)
    throws IOException;
  
  public abstract boolean memberwiseCompare(Object paramObject);
  
  public abstract void read(ProtocolReader paramProtocolReader)
    throws IOException;
  
  public abstract void read(ProtocolReader paramProtocolReader, BondSerializable paramBondSerializable)
    throws IOException;
  
  public abstract void readNested(ProtocolReader paramProtocolReader)
    throws IOException;
  
  public abstract void reset();
  
  public abstract void unmarshal(InputStream paramInputStream)
    throws IOException;
  
  public abstract void unmarshal(InputStream paramInputStream, BondSerializable paramBondSerializable)
    throws IOException;
  
  public abstract void write(ProtocolWriter paramProtocolWriter)
    throws IOException;
  
  public abstract void writeNested(ProtocolWriter paramProtocolWriter, boolean paramBoolean)
    throws IOException;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\BondSerializable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */