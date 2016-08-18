package com.microsoft.bond;

import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class FieldDef
  implements BondSerializable, BondMirror
{
  private short id;
  private Metadata metadata;
  private TypeDef type;
  
  public FieldDef()
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
    if (Metadata.Schema.metadata == paramStructDef.getMetadata()) {
      return new Metadata();
    }
    if (TypeDef.Schema.metadata == paramStructDef.getMetadata()) {
      return new TypeDef();
    }
    return null;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 0: 
      return this.metadata;
    case 1: 
      return Short.valueOf(this.id);
    }
    return this.type;
  }
  
  public final short getId()
  {
    return this.id;
  }
  
  public final Metadata getMetadata()
  {
    return this.metadata;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public final TypeDef getType()
  {
    return this.type;
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
      paramObject = (FieldDef)paramObject;
    } while ((!memberwiseCompareQuick((FieldDef)paramObject)) || (!memberwiseCompareDeep((FieldDef)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(FieldDef paramFieldDef)
  {
    int i;
    if (1 != 0) {
      if (this.metadata == null)
      {
        i = 1;
        label13:
        if (i == 0) {
          break label59;
        }
        if (this.type != null) {
          break label45;
        }
      }
    }
    label45:
    while (this.type.memberwiseCompare(paramFieldDef.type))
    {
      return true;
      if (this.metadata.memberwiseCompare(paramFieldDef.metadata)) {
        break;
      }
      i = 0;
      break label13;
    }
    label59:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(FieldDef paramFieldDef)
  {
    return (1 != 0) && (this.id == paramFieldDef.id);
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
      ReadHelper.validateType(localFieldTag.type, BondDataType.BT_STRUCT);
      this.metadata.readNested(paramProtocolReader);
      continue;
      this.id = ReadHelper.readUInt16(paramProtocolReader, localFieldTag.type);
      continue;
      ReadHelper.validateType(localFieldTag.type, BondDataType.BT_STRUCT);
      this.type.readNested(paramProtocolReader);
    }
  }
  
  protected void readUntagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolReader.readStructBegin(paramBoolean);
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.metadata.read(paramProtocolReader);
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.id = paramProtocolReader.readUInt16();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.type.read(paramProtocolReader);
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("FieldDef", "com.microsoft.bond.FieldDef");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    this.metadata = new Metadata();
    this.id = 0;
    this.type = new TypeDef();
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 0: 
      this.metadata = ((Metadata)paramObject);
      return;
    case 1: 
      this.id = ((Short)paramObject).shortValue();
      return;
    }
    this.type = ((TypeDef)paramObject);
  }
  
  public final void setId(short paramShort)
  {
    this.id = paramShort;
  }
  
  public final void setMetadata(Metadata paramMetadata)
  {
    this.metadata = paramMetadata;
  }
  
  public final void setType(TypeDef paramTypeDef)
  {
    this.type = paramTypeDef;
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
    paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRUCT, 0, Schema.metadata_metadata);
    this.metadata.writeNested(paramProtocolWriter, false);
    paramProtocolWriter.writeFieldEnd();
    if ((!bool) || (this.id != Schema.id_metadata.getDefault_value().getUint_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_UINT16, 1, Schema.id_metadata);
      paramProtocolWriter.writeUInt16(this.id);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRUCT, 2, Schema.type_metadata);
      this.type.writeNested(paramProtocolWriter, false);
      paramProtocolWriter.writeFieldEnd();
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_UINT16, 1, Schema.id_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata id_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata metadata_metadata;
    public static final SchemaDef schemaDef;
    private static final Metadata type_metadata;
    
    static
    {
      metadata.setName("FieldDef");
      metadata.setQualified_name("com.microsoft.bond.FieldDef");
      metadata_metadata = new Metadata();
      metadata_metadata.setName("metadata");
      id_metadata = new Metadata();
      id_metadata.setName("id");
      id_metadata.getDefault_value().setUint_value(0L);
      type_metadata = new Metadata();
      type_metadata.setName("type");
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
      FieldDef localFieldDef = new FieldDef();
      localFieldDef.setId((short)0);
      localFieldDef.setMetadata(metadata_metadata);
      localFieldDef.setType(Metadata.Schema.getTypeDef(paramSchemaDef));
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)1);
      localFieldDef.setMetadata(id_metadata);
      localFieldDef.getType().setId(BondDataType.BT_UINT16);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)2);
      localFieldDef.setMetadata(type_metadata);
      localFieldDef.setType(TypeDef.Schema.getTypeDef(paramSchemaDef));
      localStructDef.getFields().add(localFieldDef);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\bond\FieldDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */