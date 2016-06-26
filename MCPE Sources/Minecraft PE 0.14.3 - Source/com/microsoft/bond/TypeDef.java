package com.microsoft.bond;

import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TypeDef
  implements BondMirror, BondSerializable
{
  private boolean bonded_type;
  private TypeDef element;
  private BondDataType id;
  private TypeDef key;
  private short struct_def;
  
  public TypeDef()
  {
    reset();
  }
  
  public static SchemaDef getRuntimeSchema()
  {
    return Schema.schemaDef;
  }
  
  private void readFieldImpl_element(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    ReadHelper.validateType(paramBondDataType, BondDataType.BT_LIST);
    paramBondDataType = paramProtocolReader.readContainerBegin();
    ReadHelper.validateType(paramBondDataType.type, BondDataType.BT_STRUCT);
    if (paramBondDataType.size == 1)
    {
      if (this.element == null) {
        this.element = new TypeDef();
      }
      this.element.readNested(paramProtocolReader);
    }
    for (;;)
    {
      paramProtocolReader.readContainerEnd();
      return;
      if (paramBondDataType.size == 0) {}
    }
  }
  
  private void readFieldImpl_key(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    ReadHelper.validateType(paramBondDataType, BondDataType.BT_LIST);
    paramBondDataType = paramProtocolReader.readContainerBegin();
    ReadHelper.validateType(paramBondDataType.type, BondDataType.BT_STRUCT);
    if (paramBondDataType.size == 1)
    {
      if (this.key == null) {
        this.key = new TypeDef();
      }
      this.key.readNested(paramProtocolReader);
    }
    for (;;)
    {
      paramProtocolReader.readContainerEnd();
      return;
      if (paramBondDataType.size == 0) {}
    }
  }
  
  public BondSerializable clone()
  {
    return null;
  }
  
  public BondMirror createInstance(StructDef paramStructDef)
  {
    if (Schema.metadata == paramStructDef.getMetadata()) {
      return new TypeDef();
    }
    return null;
  }
  
  public final boolean getBonded_type()
  {
    return this.bonded_type;
  }
  
  public final TypeDef getElement()
  {
    return this.element;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 0: 
      return this.id;
    case 1: 
      return Short.valueOf(this.struct_def);
    case 2: 
      return this.element;
    case 3: 
      return this.key;
    }
    return Boolean.valueOf(this.bonded_type);
  }
  
  public final BondDataType getId()
  {
    return this.id;
  }
  
  public final TypeDef getKey()
  {
    return this.key;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public final short getStruct_def()
  {
    return this.struct_def;
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
      paramObject = (TypeDef)paramObject;
    } while ((!memberwiseCompareQuick((TypeDef)paramObject)) || (!memberwiseCompareDeep((TypeDef)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(TypeDef paramTypeDef)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    int i;
    int j;
    if (1 != 0)
    {
      bool1 = bool2;
      if (this.element != null)
      {
        if (1 == 0) {
          break label142;
        }
        if (this.element != null) {
          break label132;
        }
        i = 1;
        if (paramTypeDef.element != null) {
          break label137;
        }
        j = 1;
        label44:
        if (i != j) {
          break label142;
        }
        i = 1;
        label51:
        if (i == 0) {
          break label161;
        }
        if (this.element != null) {
          break label147;
        }
        label62:
        bool1 = true;
      }
    }
    label65:
    bool2 = bool1;
    if (bool1)
    {
      bool2 = bool1;
      if (this.key != null)
      {
        if (!bool1) {
          break label177;
        }
        if (this.key != null) {
          break label167;
        }
        i = 1;
        label99:
        if (paramTypeDef.key != null) {
          break label172;
        }
        j = 1;
        label108:
        if (i != j) {
          break label177;
        }
        i = 1;
        label115:
        if (i == 0) {
          break label196;
        }
        if (this.key != null) {
          break label182;
        }
      }
    }
    label132:
    label137:
    label142:
    label147:
    label161:
    label167:
    label172:
    label177:
    label182:
    while (this.key.memberwiseCompare(paramTypeDef.key))
    {
      bool2 = true;
      return bool2;
      i = 0;
      break;
      j = 0;
      break label44;
      i = 0;
      break label51;
      if (this.element.memberwiseCompare(paramTypeDef.element)) {
        break label62;
      }
      bool1 = false;
      break label65;
      i = 0;
      break label99;
      j = 0;
      break label108;
      i = 0;
      break label115;
    }
    label196:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(TypeDef paramTypeDef)
  {
    label34:
    label47:
    int j;
    if ((1 != 0) && (this.id == paramTypeDef.id))
    {
      i = 1;
      if ((i == 0) || (this.struct_def != paramTypeDef.struct_def)) {
        break label114;
      }
      i = 1;
      if (i == 0) {
        break label129;
      }
      if (this.element != null) {
        break label119;
      }
      i = 1;
      if (paramTypeDef.element != null) {
        break label124;
      }
      j = 1;
      label56:
      if (i != j) {
        break label129;
      }
      i = 1;
      label63:
      if (i == 0) {
        break label144;
      }
      if (this.key != null) {
        break label134;
      }
      i = 1;
      label76:
      if (paramTypeDef.key != null) {
        break label139;
      }
      j = 1;
      label85:
      if (i != j) {
        break label144;
      }
    }
    label114:
    label119:
    label124:
    label129:
    label134:
    label139:
    label144:
    for (int i = 1;; i = 0)
    {
      if ((i == 0) || (this.bonded_type != paramTypeDef.bonded_type)) {
        break label149;
      }
      return true;
      i = 0;
      break;
      i = 0;
      break label34;
      i = 0;
      break label47;
      j = 0;
      break label56;
      i = 0;
      break label63;
      i = 0;
      break label76;
      j = 0;
      break label85;
    }
    label149:
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
      this.id = BondDataType.fromValue(ReadHelper.readInt32(paramProtocolReader, localFieldTag.type));
      continue;
      this.struct_def = ReadHelper.readUInt16(paramProtocolReader, localFieldTag.type);
      continue;
      readFieldImpl_element(paramProtocolReader, localFieldTag.type);
      continue;
      readFieldImpl_key(paramProtocolReader, localFieldTag.type);
      continue;
      this.bonded_type = ReadHelper.readBool(paramProtocolReader, localFieldTag.type);
    }
  }
  
  protected void readUntagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolReader.readStructBegin(paramBoolean);
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.id = BondDataType.fromValue(paramProtocolReader.readInt32());
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.struct_def = paramProtocolReader.readUInt16();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      readFieldImpl_element(paramProtocolReader, BondDataType.BT_LIST);
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      readFieldImpl_key(paramProtocolReader, BondDataType.BT_LIST);
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.bonded_type = paramProtocolReader.readBool();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("TypeDef", "com.microsoft.bond.TypeDef");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    this.id = BondDataType.BT_STRUCT;
    this.struct_def = 0;
    this.element = null;
    this.key = null;
    this.bonded_type = false;
  }
  
  public final void setBonded_type(boolean paramBoolean)
  {
    this.bonded_type = paramBoolean;
  }
  
  public final void setElement(TypeDef paramTypeDef)
  {
    this.element = paramTypeDef;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 0: 
      this.id = ((BondDataType)paramObject);
      return;
    case 1: 
      this.struct_def = ((Short)paramObject).shortValue();
      return;
    case 2: 
      this.element = ((TypeDef)paramObject);
      return;
    case 3: 
      this.key = ((TypeDef)paramObject);
      return;
    }
    this.bonded_type = ((Boolean)paramObject).booleanValue();
  }
  
  public final void setId(BondDataType paramBondDataType)
  {
    this.id = paramBondDataType;
  }
  
  public final void setKey(TypeDef paramTypeDef)
  {
    this.key = paramTypeDef;
  }
  
  public final void setStruct_def(short paramShort)
  {
    this.struct_def = paramShort;
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
    boolean bool1 = true;
    boolean bool2 = paramProtocolWriter.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolWriter.writeStructBegin(Schema.metadata, paramBoolean);
    label118:
    int i;
    if ((!bool2) || (this.id.getValue() != Schema.id_metadata.getDefault_value().getInt_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_INT32, 0, Schema.id_metadata);
      paramProtocolWriter.writeInt32(this.id.getValue());
      paramProtocolWriter.writeFieldEnd();
      if ((bool2) && (this.struct_def == Schema.struct_def_metadata.getDefault_value().getUint_value())) {
        break label309;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_UINT16, 1, Schema.struct_def_metadata);
      paramProtocolWriter.writeUInt16(this.struct_def);
      paramProtocolWriter.writeFieldEnd();
      if (this.element == null) {
        break label323;
      }
      i = 1;
      label127:
      if ((bool2) && (i == 0)) {
        break label328;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_LIST, 2, Schema.element_metadata);
      paramProtocolWriter.writeContainerBegin(i, BondDataType.BT_STRUCT);
      if (i != 0) {
        this.element.writeNested(paramProtocolWriter, false);
      }
      paramProtocolWriter.writeContainerEnd();
      paramProtocolWriter.writeFieldEnd();
      label176:
      if (this.key == null) {
        break label342;
      }
      i = 1;
      label185:
      if ((bool2) && (i == 0)) {
        break label347;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_LIST, 3, Schema.key_metadata);
      paramProtocolWriter.writeContainerBegin(i, BondDataType.BT_STRUCT);
      if (i != 0) {
        this.key.writeNested(paramProtocolWriter, false);
      }
      paramProtocolWriter.writeContainerEnd();
      paramProtocolWriter.writeFieldEnd();
      label234:
      if (bool2)
      {
        bool2 = this.bonded_type;
        if (Schema.bonded_type_metadata.getDefault_value().getUint_value() == 0L) {
          break label361;
        }
        label259:
        if (bool2 == bool1) {
          break label367;
        }
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_BOOL, 4, Schema.bonded_type_metadata);
      paramProtocolWriter.writeBool(this.bonded_type);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_INT32, 0, Schema.id_metadata);
      break;
      label309:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_UINT16, 1, Schema.struct_def_metadata);
      break label118;
      label323:
      i = 0;
      break label127;
      label328:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_LIST, 2, Schema.element_metadata);
      break label176;
      label342:
      i = 0;
      break label185;
      label347:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_LIST, 3, Schema.key_metadata);
      break label234;
      label361:
      bool1 = false;
      break label259;
      label367:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_BOOL, 4, Schema.bonded_type_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata bonded_type_metadata;
    private static final Metadata element_metadata;
    private static final Metadata id_metadata;
    private static final Metadata key_metadata;
    public static final Metadata metadata = new Metadata();
    public static final SchemaDef schemaDef;
    private static final Metadata struct_def_metadata;
    
    static
    {
      metadata.setName("TypeDef");
      metadata.setQualified_name("com.microsoft.bond.TypeDef");
      id_metadata = new Metadata();
      id_metadata.setName("id");
      id_metadata.getDefault_value().setInt_value(BondDataType.BT_STRUCT.getValue());
      struct_def_metadata = new Metadata();
      struct_def_metadata.setName("struct_def");
      struct_def_metadata.getDefault_value().setUint_value(0L);
      element_metadata = new Metadata();
      element_metadata.setName("element");
      key_metadata = new Metadata();
      key_metadata.setName("key");
      bonded_type_metadata = new Metadata();
      bonded_type_metadata.setName("bonded_type");
      bonded_type_metadata.getDefault_value().setUint_value(0L);
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
      localFieldDef.setMetadata(id_metadata);
      localFieldDef.getType().setId(BondDataType.BT_INT32);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)1);
      localFieldDef.setMetadata(struct_def_metadata);
      localFieldDef.getType().setId(BondDataType.BT_UINT16);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)2);
      localFieldDef.setMetadata(element_metadata);
      localFieldDef.getType().setId(BondDataType.BT_LIST);
      localFieldDef.getType().setElement(new TypeDef());
      localFieldDef.getType().setElement(getTypeDef(paramSchemaDef));
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)3);
      localFieldDef.setMetadata(key_metadata);
      localFieldDef.getType().setId(BondDataType.BT_LIST);
      localFieldDef.getType().setElement(new TypeDef());
      localFieldDef.getType().setElement(getTypeDef(paramSchemaDef));
      localStructDef.getFields().add(localFieldDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)4);
      paramSchemaDef.setMetadata(bonded_type_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_BOOL);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\bond\TypeDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */