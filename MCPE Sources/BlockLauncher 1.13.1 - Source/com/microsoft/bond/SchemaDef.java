package com.microsoft.bond;

import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class SchemaDef
  implements BondSerializable, BondMirror
{
  private TypeDef root;
  private ArrayList<StructDef> structs;
  
  public SchemaDef()
  {
    reset();
  }
  
  public static SchemaDef getRuntimeSchema()
  {
    return Schema.schemaDef;
  }
  
  private void readFieldImpl_structs(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    ReadHelper.validateType(paramBondDataType, BondDataType.BT_LIST);
    paramBondDataType = paramProtocolReader.readContainerBegin();
    ReadHelper.validateType(paramBondDataType.type, BondDataType.BT_STRUCT);
    this.structs.ensureCapacity(paramBondDataType.size);
    int i = 0;
    while (i < paramBondDataType.size)
    {
      StructDef localStructDef = new StructDef();
      localStructDef.readNested(paramProtocolReader);
      this.structs.add(localStructDef);
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
    if (StructDef.Schema.metadata == paramStructDef.getMetadata()) {
      return new StructDef();
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
      return this.structs;
    }
    return this.root;
  }
  
  public final TypeDef getRoot()
  {
    return this.root;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public final ArrayList<StructDef> getStructs()
  {
    return this.structs;
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
      paramObject = (SchemaDef)paramObject;
    } while ((!memberwiseCompareQuick((SchemaDef)paramObject)) || (!memberwiseCompareDeep((SchemaDef)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(SchemaDef paramSchemaDef)
  {
    int j;
    int i;
    StructDef localStructDef1;
    StructDef localStructDef2;
    label69:
    int k;
    if ((this.structs != null) && (this.structs.size() != 0))
    {
      j = 0;
      i = 1;
      if (j < this.structs.size())
      {
        localStructDef1 = (StructDef)this.structs.get(j);
        localStructDef2 = (StructDef)paramSchemaDef.structs.get(j);
        if (i != 0) {
          if (localStructDef1 == null)
          {
            i = 1;
            if (localStructDef2 != null) {
              break label118;
            }
            k = 1;
            label77:
            if (i != k) {
              break label124;
            }
            i = 1;
            label85:
            if (i == 0) {
              break label139;
            }
            if (localStructDef1 != null) {
              break label129;
            }
            label94:
            i = 1;
            label96:
            if (i != 0) {
              break label144;
            }
          }
        }
      }
    }
    for (;;)
    {
      if (i != 0)
      {
        if (this.root == null) {}
        label118:
        label124:
        label129:
        label139:
        label144:
        while (this.root.memberwiseCompare(paramSchemaDef.root))
        {
          return true;
          i = 0;
          break label69;
          k = 0;
          break label77;
          i = 0;
          break label85;
          if (localStructDef1.memberwiseCompare(localStructDef2)) {
            break label94;
          }
          i = 0;
          break label96;
          j += 1;
          break;
        }
      }
      return false;
      continue;
      i = 1;
    }
  }
  
  protected boolean memberwiseCompareQuick(SchemaDef paramSchemaDef)
  {
    int i;
    int j;
    if (this.structs == null)
    {
      i = 1;
      if (paramSchemaDef.structs != null) {
        break label43;
      }
      j = 1;
      label18:
      if (i != j) {
        break label48;
      }
      i = 1;
      label25:
      if (i == 0) {
        break label70;
      }
      if (this.structs != null) {
        break label53;
      }
    }
    label43:
    label48:
    label53:
    while (this.structs.size() == paramSchemaDef.structs.size())
    {
      return true;
      i = 0;
      break;
      j = 0;
      break label18;
      i = 0;
      break label25;
    }
    label70:
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
      readFieldImpl_structs(paramProtocolReader, localFieldTag.type);
      continue;
      ReadHelper.validateType(localFieldTag.type, BondDataType.BT_STRUCT);
      this.root.readNested(paramProtocolReader);
    }
  }
  
  protected void readUntagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolReader.readStructBegin(paramBoolean);
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      readFieldImpl_structs(paramProtocolReader, BondDataType.BT_LIST);
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.root.read(paramProtocolReader);
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("SchemaDef", "com.microsoft.bond.SchemaDef");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    if (this.structs == null) {
      this.structs = new ArrayList();
    }
    for (;;)
    {
      this.root = new TypeDef();
      return;
      this.structs.clear();
    }
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 0: 
      this.structs = ((ArrayList)paramObject);
      return;
    }
    this.root = ((TypeDef)paramObject);
  }
  
  public final void setRoot(TypeDef paramTypeDef)
  {
    this.root = paramTypeDef;
  }
  
  public final void setStructs(ArrayList<StructDef> paramArrayList)
  {
    this.structs = paramArrayList;
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
    int i = this.structs.size();
    if ((!bool) || (i != 0))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_LIST, 0, Schema.structs_metadata);
      paramProtocolWriter.writeContainerBegin(i, BondDataType.BT_STRUCT);
      Iterator localIterator = this.structs.iterator();
      while (localIterator.hasNext()) {
        ((StructDef)localIterator.next()).writeNested(paramProtocolWriter, false);
      }
      paramProtocolWriter.writeContainerEnd();
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRUCT, 1, Schema.root_metadata);
      this.root.writeNested(paramProtocolWriter, false);
      paramProtocolWriter.writeFieldEnd();
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_LIST, 0, Schema.structs_metadata);
    }
  }
  
  public static class Schema
  {
    public static final Metadata metadata = new Metadata();
    private static final Metadata root_metadata;
    public static final SchemaDef schemaDef;
    private static final Metadata structs_metadata;
    
    static
    {
      metadata.setName("SchemaDef");
      metadata.setQualified_name("com.microsoft.bond.SchemaDef");
      structs_metadata = new Metadata();
      structs_metadata.setName("structs");
      root_metadata = new Metadata();
      root_metadata.setName("root");
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
      localFieldDef.setMetadata(structs_metadata);
      localFieldDef.getType().setId(BondDataType.BT_LIST);
      localFieldDef.getType().setElement(new TypeDef());
      localFieldDef.getType().setElement(StructDef.Schema.getTypeDef(paramSchemaDef));
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)1);
      localFieldDef.setMetadata(root_metadata);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\SchemaDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */