package com.microsoft.bond;

import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Metadata
  implements BondSerializable, BondMirror
{
  private HashMap<String, String> attributes;
  private Variant default_value;
  private Modifier modifier;
  private String name;
  private String qualified_name;
  
  public Metadata()
  {
    reset();
  }
  
  public static SchemaDef getRuntimeSchema()
  {
    return Schema.schemaDef;
  }
  
  private void readFieldImpl_attributes(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    ReadHelper.validateType(paramBondDataType, BondDataType.BT_MAP);
    paramBondDataType = paramProtocolReader.readMapContainerBegin();
    int i = 0;
    while (i < paramBondDataType.size)
    {
      String str1 = ReadHelper.readString(paramProtocolReader, paramBondDataType.keyType);
      String str2 = ReadHelper.readString(paramProtocolReader, paramBondDataType.valueType);
      this.attributes.put(str1, str2);
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
    if (Variant.Schema.metadata == paramStructDef.getMetadata()) {
      return new Variant();
    }
    return null;
  }
  
  public final HashMap<String, String> getAttributes()
  {
    return this.attributes;
  }
  
  public final Variant getDefault_value()
  {
    return this.default_value;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 0: 
      return this.name;
    case 1: 
      return this.qualified_name;
    case 2: 
      return this.attributes;
    case 3: 
      return this.modifier;
    }
    return this.default_value;
  }
  
  public final Modifier getModifier()
  {
    return this.modifier;
  }
  
  public final String getName()
  {
    return this.name;
  }
  
  public final String getQualified_name()
  {
    return this.qualified_name;
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
      paramObject = (Metadata)paramObject;
    } while ((!memberwiseCompareQuick((Metadata)paramObject)) || (!memberwiseCompareDeep((Metadata)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(Metadata paramMetadata)
  {
    int i;
    label9:
    label20:
    label22:
    label63:
    String str1;
    String str2;
    if (this.name == null)
    {
      i = 1;
      if (i == 0) {
        break label244;
      }
      if (this.qualified_name != null) {
        break label230;
      }
      i = 1;
      j = i;
      if (i != 0)
      {
        j = i;
        if (this.attributes != null)
        {
          j = i;
          if (this.attributes.size() != 0)
          {
            Iterator localIterator = this.attributes.entrySet().iterator();
            if (!localIterator.hasNext()) {
              break label321;
            }
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            str1 = (String)localEntry.getValue();
            str2 = (String)paramMetadata.attributes.get(localEntry.getKey());
            if ((i == 0) || (!paramMetadata.attributes.containsKey(localEntry.getKey()))) {
              break label249;
            }
            j = 1;
            label139:
            i = j;
            if (j != 0)
            {
              if (j == 0) {
                break label264;
              }
              if (str1 != null) {
                break label254;
              }
              i = 1;
              label156:
              if (str2 != null) {
                break label259;
              }
              j = 1;
              label163:
              if (i != j) {
                break label264;
              }
              i = 1;
              label170:
              if (i == 0) {
                break label282;
              }
              if (str1 != null) {
                break label269;
              }
              label179:
              i = 1;
              label181:
              if (i == 0) {
                break label297;
              }
              if (str1 != null) {
                break label287;
              }
              label190:
              i = 1;
            }
            label192:
            if (i != 0) {
              break label318;
            }
          }
        }
      }
    }
    label230:
    label244:
    label249:
    label254:
    label259:
    label264:
    label269:
    label282:
    label287:
    label297:
    label318:
    label321:
    for (int j = i;; j = i)
    {
      if (j != 0)
      {
        if (this.default_value == null) {}
        while (this.default_value.memberwiseCompare(paramMetadata.default_value))
        {
          return true;
          if (this.name.equals(paramMetadata.name)) {
            break;
          }
          i = 0;
          break label9;
          if (this.qualified_name.equals(paramMetadata.qualified_name)) {
            break label20;
          }
          i = 0;
          break label22;
          j = 0;
          break label139;
          i = 0;
          break label156;
          j = 0;
          break label163;
          i = 0;
          break label170;
          if (str1.length() == str2.length()) {
            break label179;
          }
          i = 0;
          break label181;
          if (str1.equals(str2)) {
            break label190;
          }
          i = 0;
          break label192;
        }
      }
      return false;
      break label63;
    }
  }
  
  protected boolean memberwiseCompareQuick(Metadata paramMetadata)
  {
    int j;
    if (this.name == null)
    {
      i = 1;
      if (paramMetadata.name != null) {
        break label144;
      }
      j = 1;
      label18:
      if (i != j) {
        break label149;
      }
      i = 1;
      label25:
      if (i == 0) {
        break label171;
      }
      if (this.name != null) {
        break label154;
      }
      label36:
      i = 1;
      label38:
      if (i == 0) {
        break label186;
      }
      if (this.qualified_name != null) {
        break label176;
      }
      i = 1;
      label51:
      if (paramMetadata.qualified_name != null) {
        break label181;
      }
      j = 1;
      label60:
      if (i != j) {
        break label186;
      }
      i = 1;
      label67:
      if (i == 0) {
        break label208;
      }
      if (this.qualified_name != null) {
        break label191;
      }
      label78:
      i = 1;
      label80:
      if (i == 0) {
        break label223;
      }
      if (this.attributes != null) {
        break label213;
      }
      i = 1;
      label93:
      if (paramMetadata.attributes != null) {
        break label218;
      }
      j = 1;
      label102:
      if (i != j) {
        break label223;
      }
      i = 1;
      label109:
      if (i == 0) {
        break label245;
      }
      if (this.attributes != null) {
        break label228;
      }
    }
    label120:
    label144:
    label149:
    label154:
    label171:
    label176:
    label181:
    label186:
    label191:
    label208:
    label213:
    label218:
    label223:
    label228:
    label245:
    for (int i = 1;; i = 0)
    {
      if ((i == 0) || (this.modifier != paramMetadata.modifier)) {
        break label250;
      }
      return true;
      i = 0;
      break;
      j = 0;
      break label18;
      i = 0;
      break label25;
      if (this.name.length() == paramMetadata.name.length()) {
        break label36;
      }
      i = 0;
      break label38;
      i = 0;
      break label51;
      j = 0;
      break label60;
      i = 0;
      break label67;
      if (this.qualified_name.length() == paramMetadata.qualified_name.length()) {
        break label78;
      }
      i = 0;
      break label80;
      i = 0;
      break label93;
      j = 0;
      break label102;
      i = 0;
      break label109;
      if (this.attributes.size() == paramMetadata.attributes.size()) {
        break label120;
      }
    }
    label250:
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
      this.name = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      this.qualified_name = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      readFieldImpl_attributes(paramProtocolReader, localFieldTag.type);
      continue;
      this.modifier = Modifier.fromValue(ReadHelper.readInt32(paramProtocolReader, localFieldTag.type));
      continue;
      ReadHelper.validateType(localFieldTag.type, BondDataType.BT_STRUCT);
      this.default_value.readNested(paramProtocolReader);
    }
  }
  
  protected void readUntagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolReader.readStructBegin(paramBoolean);
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.name = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.qualified_name = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      readFieldImpl_attributes(paramProtocolReader, BondDataType.BT_MAP);
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.modifier = Modifier.fromValue(paramProtocolReader.readInt32());
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.default_value.read(paramProtocolReader);
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("Metadata", "com.microsoft.bond.Metadata");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    this.name = "";
    this.qualified_name = "";
    if (this.attributes == null) {
      this.attributes = new HashMap();
    }
    for (;;)
    {
      this.modifier = Modifier.Optional;
      this.default_value = new Variant();
      return;
      this.attributes.clear();
    }
  }
  
  public final void setAttributes(HashMap<String, String> paramHashMap)
  {
    this.attributes = paramHashMap;
  }
  
  public final void setDefault_value(Variant paramVariant)
  {
    this.default_value = paramVariant;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 0: 
      this.name = ((String)paramObject);
      return;
    case 1: 
      this.qualified_name = ((String)paramObject);
      return;
    case 2: 
      this.attributes = ((HashMap)paramObject);
      return;
    case 3: 
      this.modifier = ((Modifier)paramObject);
      return;
    }
    this.default_value = ((Variant)paramObject);
  }
  
  public final void setModifier(Modifier paramModifier)
  {
    this.modifier = paramModifier;
  }
  
  public final void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public final void setQualified_name(String paramString)
  {
    this.qualified_name = paramString;
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
    if ((!bool) || (this.name != Schema.name_metadata.getDefault_value().getString_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 0, Schema.name_metadata);
      paramProtocolWriter.writeString(this.name);
      paramProtocolWriter.writeFieldEnd();
      if ((bool) && (this.qualified_name == Schema.qualified_name_metadata.getDefault_value().getString_value())) {
        break label231;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 1, Schema.qualified_name_metadata);
      paramProtocolWriter.writeString(this.qualified_name);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      int i = this.attributes.size();
      if ((bool) && (i == 0)) {
        break label335;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_MAP, 2, Schema.attributes_metadata);
      paramProtocolWriter.writeContainerBegin(this.attributes.size(), BondDataType.BT_STRING, BondDataType.BT_STRING);
      Iterator localIterator = this.attributes.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        paramProtocolWriter.writeString((String)localEntry.getKey());
        paramProtocolWriter.writeString((String)localEntry.getValue());
      }
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 0, Schema.name_metadata);
      break;
      label231:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 1, Schema.qualified_name_metadata);
    }
    paramProtocolWriter.writeContainerEnd();
    paramProtocolWriter.writeFieldEnd();
    if ((!bool) || (this.modifier.getValue() != Schema.modifier_metadata.getDefault_value().getInt_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_INT32, 3, Schema.modifier_metadata);
      paramProtocolWriter.writeInt32(this.modifier.getValue());
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRUCT, 4, Schema.default_value_metadata);
      this.default_value.writeNested(paramProtocolWriter, false);
      paramProtocolWriter.writeFieldEnd();
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      label335:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_MAP, 2, Schema.attributes_metadata);
      break;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_INT32, 3, Schema.modifier_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata attributes_metadata;
    private static final Metadata default_value_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata modifier_metadata;
    private static final Metadata name_metadata;
    private static final Metadata qualified_name_metadata;
    public static final SchemaDef schemaDef;
    
    static
    {
      metadata.setName("Metadata");
      metadata.setQualified_name("com.microsoft.bond.Metadata");
      name_metadata = new Metadata();
      name_metadata.setName("name");
      qualified_name_metadata = new Metadata();
      qualified_name_metadata.setName("qualified_name");
      attributes_metadata = new Metadata();
      attributes_metadata.setName("attributes");
      modifier_metadata = new Metadata();
      modifier_metadata.setName("modifier");
      modifier_metadata.getDefault_value().setInt_value(Modifier.Optional.getValue());
      default_value_metadata = new Metadata();
      default_value_metadata.setName("default_value");
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
      localFieldDef.setMetadata(name_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)1);
      localFieldDef.setMetadata(qualified_name_metadata);
      localFieldDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)2);
      localFieldDef.setMetadata(attributes_metadata);
      localFieldDef.getType().setId(BondDataType.BT_MAP);
      localFieldDef.getType().setKey(new TypeDef());
      localFieldDef.getType().setElement(new TypeDef());
      localFieldDef.getType().getKey().setId(BondDataType.BT_STRING);
      localFieldDef.getType().getElement().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)3);
      localFieldDef.setMetadata(modifier_metadata);
      localFieldDef.getType().setId(BondDataType.BT_INT32);
      localStructDef.getFields().add(localFieldDef);
      localFieldDef = new FieldDef();
      localFieldDef.setId((short)4);
      localFieldDef.setMetadata(default_value_metadata);
      localFieldDef.setType(Variant.Schema.getTypeDef(paramSchemaDef));
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\bond\Metadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */