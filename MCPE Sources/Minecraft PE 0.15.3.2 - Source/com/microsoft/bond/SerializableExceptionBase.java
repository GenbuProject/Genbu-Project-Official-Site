package com.microsoft.bond;

import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SerializableExceptionBase
  implements BondSerializable, BondMirror
{
  private String m_message;
  
  public SerializableExceptionBase()
  {
    reset();
  }
  
  public static SchemaDef getRuntimeSchema()
  {
    return Schema.schemaDef;
  }
  
  public BondSerializable clone()
  {
    return null;
  }
  
  public BondMirror createInstance(StructDef paramStructDef)
  {
    return null;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    }
    return this.m_message;
  }
  
  public final String getM_message()
  {
    return this.m_message;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public void marshal(ProtocolWriter paramProtocolWriter)
    throws IOException
  {
    Marshaler.marshal(this, paramProtocolWriter);
  }
  
  public boolean memberwiseCompare(Object paramObject)
  {
    if (paramObject == null) {}
    do
    {
      return false;
      paramObject = (SerializableExceptionBase)paramObject;
    } while ((!memberwiseCompareQuick((SerializableExceptionBase)paramObject)) || (!memberwiseCompareDeep((SerializableExceptionBase)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(SerializableExceptionBase paramSerializableExceptionBase)
  {
    if (1 != 0)
    {
      if (this.m_message == null) {}
      while (this.m_message.equals(paramSerializableExceptionBase.m_message)) {
        return true;
      }
    }
    return false;
  }
  
  protected boolean memberwiseCompareQuick(SerializableExceptionBase paramSerializableExceptionBase)
  {
    int i;
    int j;
    if (1 != 0) {
      if (this.m_message == null)
      {
        i = 1;
        if (paramSerializableExceptionBase.m_message != null) {
          break label47;
        }
        j = 1;
        label22:
        if (i != j) {
          break label52;
        }
        i = 1;
        label29:
        if (i == 0) {
          break label74;
        }
        if (this.m_message != null) {
          break label57;
        }
      }
    }
    label47:
    label52:
    label57:
    while (this.m_message.length() == paramSerializableExceptionBase.m_message.length())
    {
      return true;
      i = 0;
      break;
      j = 0;
      break label22;
      i = 0;
      break label29;
    }
    label74:
    return false;
  }
  
  public void read(ProtocolReader paramProtocolReader)
    throws IOException
  {
    paramProtocolReader.readBegin();
    readNested(paramProtocolReader);
    paramProtocolReader.readEnd();
  }
  
  public void read(ProtocolReader paramProtocolReader, BondSerializable paramBondSerializable)
    throws IOException
  {}
  
  public void readNested(ProtocolReader paramProtocolReader)
    throws IOException
  {
    if (!paramProtocolReader.hasCapability(ProtocolCapability.TAGGED)) {
      readUntagged(paramProtocolReader, false);
    }
    while (!readTagged(paramProtocolReader, false)) {
      return;
    }
    ReadHelper.skipPartialStruct(paramProtocolReader);
  }
  
  protected boolean readTagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    paramProtocolReader.readStructBegin(paramBoolean);
    ProtocolReader.FieldTag localFieldTag = paramProtocolReader.readFieldBegin();
    if ((localFieldTag.type == BondDataType.BT_STOP) || (localFieldTag.type == BondDataType.BT_STOP_BASE))
    {
      if (localFieldTag.type == BondDataType.BT_STOP_BASE) {}
      for (paramBoolean = true;; paramBoolean = false)
      {
        paramProtocolReader.readStructEnd();
        return paramBoolean;
      }
    }
    switch (localFieldTag.id)
    {
    default: 
      paramProtocolReader.skip(localFieldTag.type);
    }
    for (;;)
    {
      paramProtocolReader.readFieldEnd();
      break;
      this.m_message = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
    }
  }
  
  protected void readUntagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolReader.readStructBegin(paramBoolean);
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.m_message = paramProtocolReader.readString();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("SerializableExceptionBase", "com.microsoft.bond.SerializableExceptionBase");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    this.m_message = "";
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    }
    this.m_message = ((String)paramObject);
  }
  
  public final void setM_message(String paramString)
  {
    this.m_message = paramString;
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
    paramProtocolWriter.writeBegin();
    ProtocolWriter localProtocolWriter = paramProtocolWriter.getFirstPassWriter();
    if (localProtocolWriter != null)
    {
      writeNested(localProtocolWriter, false);
      writeNested(paramProtocolWriter, false);
    }
    for (;;)
    {
      paramProtocolWriter.writeEnd();
      return;
      writeNested(paramProtocolWriter, false);
    }
  }
  
  public void writeNested(ProtocolWriter paramProtocolWriter, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolWriter.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolWriter.writeStructBegin(Schema.metadata, paramBoolean);
    if ((!bool) || (this.m_message != Schema.m_message_metadata.getDefault_value().getString_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 8189, Schema.m_message_metadata);
      paramProtocolWriter.writeString(this.m_message);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 8189, Schema.m_message_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata m_message_metadata;
    public static final Metadata metadata = new Metadata();
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("SerializableExceptionBase");
      metadata.setQualified_name("com.microsoft.bond.SerializableExceptionBase");
      m_message_metadata = new Metadata();
      m_message_metadata.setName("m_message");
      schemaDef = new SchemaDef();
      schemaDef.setRoot(getTypeDef(schemaDef));
    }
    
    private static short getStructDef(SchemaDef paramSchemaDef)
    {
      short s;
      for (int i = 0; i < paramSchemaDef.getStructs().size(); s = (short)(i + 1)) {
        if (((StructDef)paramSchemaDef.getStructs().get(i)).getMetadata() == metadata) {
          return i;
        }
      }
      StructDef localStructDef = new StructDef();
      paramSchemaDef.getStructs().add(localStructDef);
      localStructDef.setMetadata(metadata);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)8189);
      paramSchemaDef.setMetadata(m_message_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      return s;
    }
    
    public static TypeDef getTypeDef(SchemaDef paramSchemaDef)
    {
      TypeDef localTypeDef = new TypeDef();
      localTypeDef.setId(BondDataType.BT_STRUCT);
      localTypeDef.setStruct_def(getStructDef(paramSchemaDef));
      return localTypeDef;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\bond\SerializableExceptionBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */