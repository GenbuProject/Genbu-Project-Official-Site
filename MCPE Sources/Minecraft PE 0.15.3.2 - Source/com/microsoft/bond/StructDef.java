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
    label14:
    boolean bool1;
    int i;
    label48:
    int j;
    if (1 != 0) {
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
              break label237;
            }
            if (this.base_def != null) {
              break label227;
            }
            i = 1;
            if (paramStructDef.base_def != null) {
              break label232;
            }
            j = 1;
            label57:
            if (i != j) {
              break label237;
            }
            i = 1;
            label64:
            if (i == 0) {
              break label256;
            }
            if (this.base_def != null) {
              break label242;
            }
            label75:
            bool1 = true;
          }
        }
        label78:
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
    }
    for (;;)
    {
      bool2 = bool1;
      FieldDef localFieldDef1;
      FieldDef localFieldDef2;
      label167:
      int k;
      if (i < this.fields.size())
      {
        localFieldDef1 = (FieldDef)this.fields.get(i);
        localFieldDef2 = (FieldDef)paramStructDef.fields.get(i);
        if (!bool1) {
          break label273;
        }
        if (localFieldDef1 != null) {
          break label262;
        }
        j = 1;
        if (localFieldDef2 != null) {
          break label267;
        }
        k = 1;
        label175:
        if (j != k) {
          break label273;
        }
        j = 1;
        label183:
        if (j == 0) {
          break label288;
        }
        if (localFieldDef1 != null) {
          break label278;
        }
      }
      label192:
      label227:
      label232:
      label237:
      label242:
      label256:
      label262:
      label267:
      label273:
      label278:
      label288:
      for (bool1 = true;; bool1 = false)
      {
        if (bool1) {
          break label294;
        }
        bool2 = bool1;
        return bool2;
        if (this.metadata.memberwiseCompare(paramStructDef.metadata)) {
          break;
        }
        bool2 = false;
        break label14;
        i = 0;
        break label48;
        j = 0;
        break label57;
        i = 0;
        break label64;
        if (this.base_def.memberwiseCompare(paramStructDef.base_def)) {
          break label75;
        }
        bool1 = false;
        break label78;
        j = 0;
        break label167;
        k = 0;
        break label175;
        j = 0;
        break label183;
        if (localFieldDef1.memberwiseCompare(localFieldDef2)) {
          break label192;
        }
      }
      label294:
      i += 1;
    }
  }
  
  protected boolean memberwiseCompareQuick(StructDef paramStructDef)
  {
    int i;
    int j;
    if (1 != 0) {
      if (this.base_def == null)
      {
        i = 1;
        if (paramStructDef.base_def != null) {
          break label76;
        }
        j = 1;
        label22:
        if (i != j) {
          break label81;
        }
        i = 1;
        label29:
        if (i == 0) {
          break label96;
        }
        if (this.fields != null) {
          break label86;
        }
        i = 1;
        label42:
        if (paramStructDef.fields != null) {
          break label91;
        }
        j = 1;
        label51:
        if (i != j) {
          break label96;
        }
        i = 1;
        label58:
        if (i == 0) {
          break label118;
        }
        if (this.fields != null) {
          break label101;
        }
      }
    }
    label76:
    label81:
    label86:
    label91:
    label96:
    label101:
    while (this.fields.size() == paramStructDef.fields.size())
    {
      return true;
      i = 0;
      break;
      j = 0;
      break label22;
      i = 0;
      break label29;
      i = 0;
      break label42;
      j = 0;
      break label51;
      i = 0;
      break label58;
    }
    label118:
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\bond\StructDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */