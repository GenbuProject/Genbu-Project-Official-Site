package com.microsoft.bond;

import com.microsoft.bond.internal.Marshaler;
import java.io.IOException;
import java.io.InputStream;

public class Bonded<T extends BondSerializable>
  implements BondSerializable
{
  private ProtocolReader Data;
  private T Value;
  
  public Bonded() {}
  
  public Bonded(T paramT)
  {
    this.Value = paramT;
  }
  
  public Bonded(ProtocolReader paramProtocolReader)
    throws IOException
  {
    read(paramProtocolReader);
  }
  
  public Bonded(ProtocolReader paramProtocolReader, SchemaDef paramSchemaDef)
    throws IOException
  {
    read(paramProtocolReader, paramSchemaDef);
  }
  
  public BondSerializable clone()
  {
    if (this.Data != null) {
      try
      {
        Bonded localBonded = new Bonded(this.Data.cloneReader());
        return localBonded;
      }
      catch (IOException localIOException)
      {
        return null;
      }
    }
    return new Bonded(this.Value);
  }
  
  public void deserialize(BondSerializable paramBondSerializable)
    throws IOException
  {
    paramBondSerializable.read(this.Data);
  }
  
  public T getValue()
  {
    return this.Value;
  }
  
  public void marshal(ProtocolWriter paramProtocolWriter)
    throws IOException
  {
    Marshaler.marshal(this, paramProtocolWriter);
  }
  
  public boolean memberwiseCompare(Object paramObject)
  {
    if (this.Value != null) {
      return this.Value.memberwiseCompare(paramObject);
    }
    return false;
  }
  
  public void read(ProtocolReader paramProtocolReader)
    throws IOException
  {
    readNested(paramProtocolReader);
  }
  
  public void read(ProtocolReader paramProtocolReader, BondSerializable paramBondSerializable)
    throws IOException
  {
    readNested(paramProtocolReader);
  }
  
  public void readNested(ProtocolReader paramProtocolReader)
    throws IOException
  {
    this.Value = null;
    this.Data = paramProtocolReader.cloneReader();
    paramProtocolReader.skip(BondDataType.BT_STRUCT);
  }
  
  public void reset()
  {
    this.Value = null;
    this.Data = null;
  }
  
  public void unmarshal(InputStream paramInputStream)
    throws IOException
  {
    Marshaler.unmarshal(paramInputStream, this);
  }
  
  public void unmarshal(InputStream paramInputStream, BondSerializable paramBondSerializable)
    throws IOException
  {
    Marshaler.unmarshal(paramInputStream, (SchemaDef)paramBondSerializable, this);
  }
  
  public void write(ProtocolWriter paramProtocolWriter)
    throws IOException
  {
    if (this.Data != null)
    {
      Transcoder.transcode(paramProtocolWriter, this.Data.cloneReader());
      return;
    }
    this.Value.write(paramProtocolWriter);
  }
  
  public void writeNested(ProtocolWriter paramProtocolWriter, boolean paramBoolean)
    throws IOException
  {
    write(paramProtocolWriter);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\Bonded.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */