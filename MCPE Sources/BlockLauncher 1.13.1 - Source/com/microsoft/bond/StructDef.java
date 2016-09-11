package com.microsoft.bond;

import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class StructDef
  implements BondSerializable, BondMirror
{
  private TypeDef base_def;
  private ArrayList<FieldDef> fields;
  private Metadata metadata;
  
  public StructDef()
  {
    reset();
  }
  
  public static SchemaDef getRuntimeSchema()
  {
    return Schema.schemaDef;
  }
  
  private void readFieldImpl_base_def(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    ReadHelper.validateType(paramBondDataType, BondDataType.BT_LIST);
    paramBondDataType = paramProtocolReader.readContainerBegin();
    ReadHelper.validateType(paramBondDataType.type, BondDataType.BT_STRUCT);
    if (paramBondDataType.size == 1)
    {
      if (this.base_def == null) {
        this.base_def = new TypeDef();
      }
      this.base_def.readNested(paramProtocolReader);
    }
    for (;;)
    {
      paramProtocolReader.readContainerEnd();
      return;
      if (paramBondDataType.size == 0) {}
    }
  }
  
  private void readFieldImpl_fields(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    ReadHelper.validateType(paramBondDataType, BondDataType.BT_LIST);
    paramBondDataType = paramProtocolReader.readContainerBegin();
    ReadHelper.validateType(paramBondDataType.type, BondDataType.BT_STRUCT);
    this.fields.ensureCapacity(paramBondDataType.size);
    int i = 0;
    while (i < paramBondDataType.size)
    {
      FieldDef localFieldDef = new FieldDef();
      localFieldDef.readNested(paramProtocolReader);
      this.fields.add(localFieldDef);
      i += 1;
    }
    paramProtocolReader.readContainerEnd();
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
    if (FieldDef.Schema.metadata == paramStructDef.getMetadata()) {
      return new FieldDef();
    }
    return null;
  }
  
  public final TypeDef getBase_def()
  {
    return this.base_def;
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
      return this.base_def;
    }
    return this.fields;
  }
  
  public final ArrayList<FieldDef> getFields()
  {
    return this.fields;
  }
  
  public final Metadata getMetadata()
  {
    return this.metadata;
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
      paramObject = (StructDef)paramObject;
    } while ((!memberwiseCompareQuick((StructDef)paramObject)) || (!memberwiseCompareDeep((StructDef)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(StructDef paramStructDef)
  {
    boolean bool2;
    label10:
    boolean bool1;
    int i;
    label44:
    int j;
    if (this.metadata == null)
    {
      bool2 = true;
      bool1 = bool2;
      if (bool2)
      {
        bool1 = bool2;
        if (this.base_def != null)
        {
          if (!bool2) {
            break label229;
          }
          if (this.base_def != null) {
            break label219;
          }
          i = 1;
          if (paramStructDef.base_def != null) {
            break label224;
          }
          j = 1;
          label53:
          if (i != j) {
            break label229;
          }
          i = 1;
          label60:
          if (i == 0) {
            break label248;
          }
          if (this.base_def != null) {
            break label234;
          }
          label71:
          bool1 = true;
        }
      }
      label74:
      bool2 = bool1;
      if (bool1)
      {
        bool2 = bool1;
        if (this.fields != null)
        {
          bool2 = bool1;
          if (this.fields.size() != 0) {
            i = 0;
          }
        }
      }
    }
    for (;;)
    {
      if (i >= this.fields.size()) {
        break label293;
      }
      FieldDef localFieldDef1 = (FieldDef)this.fields.get(i);
      FieldDef localFieldDef2 = (FieldDef)paramStructDef.fields.get(i);
      label159:
      int k;
      if (bool1) {
        if (localFieldDef1 == null)
        {
          j = 1;
          if (localFieldDef2 != null) {
            break label259;
          }
          k = 1;
          label167:
          if (j != k) {
            break label265;
          }
          j = 1;
          label175:
          if (j == 0) {
            break label280;
          }
          if (localFieldDef1 != null) {
            break label270;
          }
        }
      }
      label184:
      label219:
      label224:
      label229:
      label234:
      label248:
      label259:
      label265:
      label270:
      label280:
      for (bool1 = true;; bool1 = false)
      {
        if (bool1) {
          break label286;
        }
        bool2 = bool1;
        return bool2;
        if (this.metadata.memberwiseCompare(paramStructDef.metadata)) {
          break;
        }
        bool2 = false;
        break label10;
        i = 0;
        break label44;
        j = 0;
        break label53;
        i = 0;
        break label60;
        if (this.base_def.memberwiseCompare(paramStructDef.base_def)) {
          break label71;
        }
        bool1 = false;
        break label74;
        j = 0;
        break label159;
        k = 0;
        break label167;
        j = 0;
        break label175;
        if (localFieldDef1.memberwiseCompare(localFieldDef2)) {
          break label184;
        }
      }
      label286:
      i += 1;
    }
    label293:
    return bool1;
  }
  
  protected boolean memberwiseCompareQuick(StructDef paramStructDef)
  {
    int i;
    int j;
    if (this.base_def == null)
    {
      i = 1;
      if (paramStructDef.base_def != null) {
        break label72;
      }
      j = 1;
      label18:
      if (i != j) {
        break label77;
      }
      i = 1;
      label25:
      if (i == 0) {
        break label92;
      }
      if (this.fields != null) {
        break label82;
      }
      i = 1;
      label38:
      if (paramStructDef.fields != null) {
        break label87;
      }
      j = 1;
      label47:
      if (i != j) {
        break label92;
      }
      i = 1;
      label54:
      if (i == 0) {
        break label114;
      }
      if (this.fields != null) {
        break label97;
      }
    }
    label72:
    label77:
    label82:
    label87:
    label92:
    label97:
    while (this.fields.size() == paramStructDef.fields.size())
    {
      return true;
      i = 0;
      break;
      j = 0;
      break label18;
      i = 0;
      break label25;
      i = 0;
      break label38;
      j = 0;
      break label47;
      i = 0;
      break label54;
    }
    label114:
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
      ReadHelper.validateType(localFieldTag.type, BondDataType.BT_STRUCT);
      this.metadata.readNested(paramProtocolReader);
      continue;
      readFieldImpl_base_def(paramProtocolReader, localFieldTag.type);
      continue;
      readFieldImpl_fields(paramProtocolReader, localFieldTag.type);
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
      readFieldImpl_base_def(paramProtocolReader, BondDataType.BT_LIST);
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      readFieldImpl_fields(paramProtocolReader, BondDataType.BT_LIST);
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("StructDef", "com.microsoft.bond.StructDef");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    this.metadata = new Metadata();
    this.base_def = null;
    if (this.fields == null)
    {
      this.fields = new ArrayList();
      return;
    }
    this.fields.clear();
  }
  
  public final void setBase_def(TypeDef paramTypeDef)
  {
    this.base_def = paramTypeDef;
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
      this.base_def = ((TypeDef)paramObject);
      return;
    }
    this.fields = ((ArrayList)paramObject);
  }
  
  public final void setFields(ArrayList<FieldDef> paramArrayList)
  {
    this.fields = paramArrayList;
  }
  
  public final void setMetadata(Metadata paramMetadata)
  {
    this.metadata = paramMetadata;
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
    int i;
    if (this.base_def != null)
    {
      i = 1;
      if ((bool) && (i == 0)) {
        break label177;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_LIST, 1, Schema.base_def_metadata);
      paramProtocolWriter.writeContainerBegin(i, BondDataType.BT_STRUCT);
      if (i != 0) {
        this.base_def.writeNested(paramProtocolWriter, false);
      }
      paramProtocolWriter.writeContainerEnd();
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      i = this.fields.size();
      if ((bool) && (i == 0)) {
        break label205;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_LIST, 2, Schema.fields_metadata);
      paramProtocolWriter.writeContainerBegin(i, BondDataType.BT_STRUCT);
      Iterator localIterator = this.fields.iterator();
      while (localIterator.hasNext()) {
        ((FieldDef)localIterator.next()).writeNested(paramProtocolWriter, false);
      }
      i = 0;
      break;
      label177:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_LIST, 1, Schema.base_def_metadata);
    }
    paramProtocolWriter.writeContainerEnd();
    paramProtocolWriter.writeFieldEnd();
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      label205:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_LIST, 2, Schema.fields_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata base_def_metadata;
    private static final Metadata fields_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata metadata_metadata;
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("StructDef");
      metadata.setQualified_name("com.microsoft.bond.StructDef");
      metadata_metadata = new Metadata();
      metadata_metadata.setName("metadata");
      base_def_metadata = new Metadata();
      base_def_metadata.setName("base_def");
      fields_metadata = new Metadata();
      fields_metadata.setName("fields");
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
      localFieldDef.setMetadata(base_def_metadata);
      localFieldDef.getType().setId(BondDataType.BT_LIST);
      localFieldDef.getType().setElement(new TypeDef());
      localFieldDef.getType().setElement(TypeDef.Schema.getTypeDef(paramSchemaDef));
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)2);
      localFieldDef.setMetadata(fields_metadata);
      localFieldDef.getType().setId(BondDataType.BT_LIST);
      localFieldDef.getType().setElement(new TypeDef());
      localFieldDef.getType().setElement(FieldDef.Schema.getTypeDef(paramSchemaDef));
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\StructDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */