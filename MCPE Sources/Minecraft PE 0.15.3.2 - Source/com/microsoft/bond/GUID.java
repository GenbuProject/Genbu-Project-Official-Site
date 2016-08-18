package com.microsoft.bond;

import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GUID
  implements BondSerializable, BondMirror
{
  private int Data1;
  private short Data2;
  private short Data3;
  private long Data4;
  
  public GUID()
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
  
  public final int getData1()
  {
    return this.Data1;
  }
  
  public final short getData2()
  {
    return this.Data2;
  }
  
  public final short getData3()
  {
    return this.Data3;
  }
  
  public final long getData4()
  {
    return this.Data4;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 0: 
      return Integer.valueOf(this.Data1);
    case 1: 
      return Short.valueOf(this.Data2);
    case 2: 
      return Short.valueOf(this.Data3);
    }
    return Long.valueOf(this.Data4);
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
      paramObject = (GUID)paramObject;
    } while ((!memberwiseCompareQuick((GUID)paramObject)) || (!memberwiseCompareDeep((GUID)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(GUID paramGUID)
  {
    return true;
  }
  
  protected boolean memberwiseCompareQuick(GUID paramGUID)
  {
    if ((1 != 0) && (this.Data1 == paramGUID.Data1))
    {
      i = 1;
      if ((i == 0) || (this.Data2 != paramGUID.Data2)) {
        break label74;
      }
      i = 1;
      label34:
      if ((i == 0) || (this.Data3 != paramGUID.Data3)) {
        break label79;
      }
    }
    label74:
    label79:
    for (int i = 1;; i = 0)
    {
      if ((i == 0) || (this.Data4 != paramGUID.Data4)) {
        break label84;
      }
      return true;
      i = 0;
      break;
      i = 0;
      break label34;
    }
    label84:
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
      this.Data1 = ReadHelper.readUInt32(paramProtocolReader, localFieldTag.type);
      continue;
      this.Data2 = ReadHelper.readUInt16(paramProtocolReader, localFieldTag.type);
      continue;
      this.Data3 = ReadHelper.readUInt16(paramProtocolReader, localFieldTag.type);
      continue;
      this.Data4 = ReadHelper.readUInt64(paramProtocolReader, localFieldTag.type);
    }
  }
  
  protected void readUntagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolReader.readStructBegin(paramBoolean);
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.Data1 = paramProtocolReader.readUInt32();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.Data2 = paramProtocolReader.readUInt16();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.Data3 = paramProtocolReader.readUInt16();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.Data4 = paramProtocolReader.readUInt64();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("GUID", "com.microsoft.bond.GUID");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    this.Data1 = 0;
    this.Data2 = 0;
    this.Data3 = 0;
    this.Data4 = 0L;
  }
  
  public final void setData1(int paramInt)
  {
    this.Data1 = paramInt;
  }
  
  public final void setData2(short paramShort)
  {
    this.Data2 = paramShort;
  }
  
  public final void setData3(short paramShort)
  {
    this.Data3 = paramShort;
  }
  
  public final void setData4(long paramLong)
  {
    this.Data4 = paramLong;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 0: 
      this.Data1 = ((Integer)paramObject).intValue();
      return;
    case 1: 
      this.Data2 = ((Short)paramObject).shortValue();
      return;
    case 2: 
      this.Data3 = ((Short)paramObject).shortValue();
      return;
    }
    this.Data4 = ((Long)paramObject).longValue();
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
    if ((!bool) || (this.Data1 != Schema.Data1_metadata.getDefault_value().getUint_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_UINT32, 0, Schema.Data1_metadata);
      paramProtocolWriter.writeUInt32(this.Data1);
      paramProtocolWriter.writeFieldEnd();
      if ((bool) && (this.Data2 == Schema.Data2_metadata.getDefault_value().getUint_value())) {
        break label215;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_UINT16, 1, Schema.Data2_metadata);
      paramProtocolWriter.writeUInt16(this.Data2);
      paramProtocolWriter.writeFieldEnd();
      label106:
      if ((bool) && (this.Data3 == Schema.Data3_metadata.getDefault_value().getUint_value())) {
        break label229;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_UINT16, 2, Schema.Data3_metadata);
      paramProtocolWriter.writeUInt16(this.Data3);
      paramProtocolWriter.writeFieldEnd();
      label151:
      if ((bool) && (this.Data4 == Schema.Data4_metadata.getDefault_value().getUint_value())) {
        break label243;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_UINT64, 3, Schema.Data4_metadata);
      paramProtocolWriter.writeUInt64(this.Data4);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_UINT32, 0, Schema.Data1_metadata);
      break;
      label215:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_UINT16, 1, Schema.Data2_metadata);
      break label106;
      label229:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_UINT16, 2, Schema.Data3_metadata);
      break label151;
      label243:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_UINT64, 3, Schema.Data4_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata Data1_metadata;
    private static final Metadata Data2_metadata;
    private static final Metadata Data3_metadata;
    private static final Metadata Data4_metadata;
    public static final Metadata metadata = new Metadata();
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("GUID");
      metadata.setQualified_name("com.microsoft.bond.GUID");
      Data1_metadata = new Metadata();
      Data1_metadata.setName("Data1");
      Data1_metadata.getDefault_value().setUint_value(0L);
      Data2_metadata = new Metadata();
      Data2_metadata.setName("Data2");
      Data2_metadata.getDefault_value().setUint_value(0L);
      Data3_metadata = new Metadata();
      Data3_metadata.setName("Data3");
      Data3_metadata.getDefault_value().setUint_value(0L);
      Data4_metadata = new Metadata();
      Data4_metadata.setName("Data4");
      Data4_metadata.getDefault_value().setUint_value(0L);
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
      paramSchemaDef.setId((short)0);
      paramSchemaDef.setMetadata(Data1_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_UINT32);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)1);
      paramSchemaDef.setMetadata(Data2_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_UINT16);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)2);
      paramSchemaDef.setMetadata(Data3_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_UINT16);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)3);
      paramSchemaDef.setMetadata(Data4_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_UINT64);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\bond\GUID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */