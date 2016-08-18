package com.microsoft.bond;

import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Variant
  implements BondSerializable, BondMirror
{
  private double double_value;
  private long int_value;
  private boolean nothing;
  private String string_value;
  private long uint_value;
  private String wstring_value;
  
  public Variant()
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
  
  public final double getDouble_value()
  {
    return this.double_value;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    case 0: 
      return Long.valueOf(this.uint_value);
    case 1: 
      return Long.valueOf(this.int_value);
    case 2: 
      return Double.valueOf(this.double_value);
    case 3: 
      return this.string_value;
    case 4: 
      return this.wstring_value;
    }
    return Boolean.valueOf(this.nothing);
  }
  
  public final long getInt_value()
  {
    return this.int_value;
  }
  
  public final boolean getNothing()
  {
    return this.nothing;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public final String getString_value()
  {
    return this.string_value;
  }
  
  public final long getUint_value()
  {
    return this.uint_value;
  }
  
  public final String getWstring_value()
  {
    return this.wstring_value;
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
      paramObject = (Variant)paramObject;
    } while ((!memberwiseCompareQuick((Variant)paramObject)) || (!memberwiseCompareDeep((Variant)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(Variant paramVariant)
  {
    int i;
    if (1 != 0) {
      if (this.string_value == null)
      {
        i = 1;
        label13:
        if (i == 0) {
          break label59;
        }
        if (this.wstring_value != null) {
          break label45;
        }
      }
    }
    label45:
    while (this.wstring_value.equals(paramVariant.wstring_value))
    {
      return true;
      if (this.string_value.equals(paramVariant.string_value)) {
        break;
      }
      i = 0;
      break label13;
    }
    label59:
    return false;
  }
  
  protected boolean memberwiseCompareQuick(Variant paramVariant)
  {
    label36:
    label60:
    label62:
    label75:
    int j;
    if ((1 != 0) && (this.uint_value == paramVariant.uint_value))
    {
      i = 1;
      if ((i == 0) || (this.int_value != paramVariant.int_value)) {
        break label168;
      }
      i = 1;
      if (i == 0) {
        break label185;
      }
      if (!Double.isNaN(this.double_value)) {
        break label173;
      }
      if (!Double.isNaN(paramVariant.double_value)) {
        break label185;
      }
      i = 1;
      if (i == 0) {
        break label200;
      }
      if (this.string_value != null) {
        break label190;
      }
      i = 1;
      if (paramVariant.string_value != null) {
        break label195;
      }
      j = 1;
      label84:
      if (i != j) {
        break label200;
      }
      i = 1;
      label91:
      if (i == 0) {
        break label222;
      }
      if (this.string_value != null) {
        break label205;
      }
      label102:
      i = 1;
      label104:
      if (i == 0) {
        break label237;
      }
      if (this.wstring_value != null) {
        break label227;
      }
      i = 1;
      label117:
      if (paramVariant.wstring_value != null) {
        break label232;
      }
      j = 1;
      label126:
      if (i != j) {
        break label237;
      }
      i = 1;
      label133:
      if (i == 0) {
        break label259;
      }
      if (this.wstring_value != null) {
        break label242;
      }
    }
    label144:
    label168:
    label173:
    label185:
    label190:
    label195:
    label200:
    label205:
    label222:
    label227:
    label232:
    label237:
    label242:
    label259:
    for (int i = 1;; i = 0)
    {
      if ((i == 0) || (this.nothing != paramVariant.nothing)) {
        break label264;
      }
      return true;
      i = 0;
      break;
      i = 0;
      break label36;
      if (this.double_value == paramVariant.double_value) {
        break label60;
      }
      i = 0;
      break label62;
      i = 0;
      break label75;
      j = 0;
      break label84;
      i = 0;
      break label91;
      if (this.string_value.length() == paramVariant.string_value.length()) {
        break label102;
      }
      i = 0;
      break label104;
      i = 0;
      break label117;
      j = 0;
      break label126;
      i = 0;
      break label133;
      if (this.wstring_value.length() == paramVariant.wstring_value.length()) {
        break label144;
      }
    }
    label264:
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
      this.uint_value = ReadHelper.readUInt64(paramProtocolReader, localFieldTag.type);
      continue;
      this.int_value = ReadHelper.readInt64(paramProtocolReader, localFieldTag.type);
      continue;
      this.double_value = ReadHelper.readDouble(paramProtocolReader, localFieldTag.type);
      continue;
      this.string_value = ReadHelper.readString(paramProtocolReader, localFieldTag.type);
      continue;
      this.wstring_value = ReadHelper.readWString(paramProtocolReader, localFieldTag.type);
      continue;
      this.nothing = ReadHelper.readBool(paramProtocolReader, localFieldTag.type);
    }
  }
  
  protected void readUntagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolReader.readStructBegin(paramBoolean);
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.uint_value = paramProtocolReader.readUInt64();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.int_value = paramProtocolReader.readInt64();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.double_value = paramProtocolReader.readDouble();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.string_value = paramProtocolReader.readString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.wstring_value = paramProtocolReader.readWString();
    }
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      this.nothing = paramProtocolReader.readBool();
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("Variant", "com.microsoft.bond.Variant");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    this.uint_value = 0L;
    this.int_value = 0L;
    this.double_value = 0.0D;
    this.string_value = "";
    this.wstring_value = "";
    this.nothing = false;
  }
  
  public final void setDouble_value(double paramDouble)
  {
    this.double_value = paramDouble;
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    case 0: 
      this.uint_value = ((Long)paramObject).longValue();
      return;
    case 1: 
      this.int_value = ((Long)paramObject).longValue();
      return;
    case 2: 
      this.double_value = ((Double)paramObject).doubleValue();
      return;
    case 3: 
      this.string_value = ((String)paramObject);
      return;
    case 4: 
      this.wstring_value = ((String)paramObject);
      return;
    }
    this.nothing = ((Boolean)paramObject).booleanValue();
  }
  
  public final void setInt_value(long paramLong)
  {
    this.int_value = paramLong;
  }
  
  public final void setNothing(boolean paramBoolean)
  {
    this.nothing = paramBoolean;
  }
  
  public final void setString_value(String paramString)
  {
    this.string_value = paramString;
  }
  
  public final void setUint_value(long paramLong)
  {
    this.uint_value = paramLong;
  }
  
  public final void setWstring_value(String paramString)
  {
    this.wstring_value = paramString;
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
    if ((!bool2) || (this.uint_value != Schema.uint_value_metadata.getDefault_value().getUint_value()))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_UINT64, 0, Schema.uint_value_metadata);
      paramProtocolWriter.writeUInt64(this.uint_value);
      paramProtocolWriter.writeFieldEnd();
      if ((bool2) && (this.int_value == Schema.int_value_metadata.getDefault_value().getInt_value())) {
        break label316;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_INT64, 1, Schema.int_value_metadata);
      paramProtocolWriter.writeInt64(this.int_value);
      paramProtocolWriter.writeFieldEnd();
      label109:
      if ((bool2) && (this.double_value == Schema.double_value_metadata.getDefault_value().getDouble_value())) {
        break label330;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_DOUBLE, 2, Schema.double_value_metadata);
      paramProtocolWriter.writeDouble(this.double_value);
      paramProtocolWriter.writeFieldEnd();
      label154:
      if ((bool2) && (this.string_value == Schema.string_value_metadata.getDefault_value().getString_value())) {
        break label344;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_STRING, 3, Schema.string_value_metadata);
      paramProtocolWriter.writeString(this.string_value);
      paramProtocolWriter.writeFieldEnd();
      label198:
      if ((bool2) && (this.wstring_value == Schema.wstring_value_metadata.getDefault_value().getWstring_value())) {
        break label358;
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_WSTRING, 4, Schema.wstring_value_metadata);
      paramProtocolWriter.writeWString(this.wstring_value);
      paramProtocolWriter.writeFieldEnd();
      label242:
      if (bool2)
      {
        bool2 = this.nothing;
        if (Schema.nothing_metadata.getDefault_value().getUint_value() == 0L) {
          break label372;
        }
        label267:
        if (bool2 == bool1) {
          break label377;
        }
      }
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_BOOL, 5, Schema.nothing_metadata);
      paramProtocolWriter.writeBool(this.nothing);
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_UINT64, 0, Schema.uint_value_metadata);
      break;
      label316:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_INT64, 1, Schema.int_value_metadata);
      break label109;
      label330:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_DOUBLE, 2, Schema.double_value_metadata);
      break label154;
      label344:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_STRING, 3, Schema.string_value_metadata);
      break label198;
      label358:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_WSTRING, 4, Schema.wstring_value_metadata);
      break label242;
      label372:
      bool1 = false;
      break label267;
      label377:
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_BOOL, 5, Schema.nothing_metadata);
    }
  }
  
  public static class Schema
  {
    private static final Metadata double_value_metadata;
    private static final Metadata int_value_metadata;
    public static final Metadata metadata = new Metadata();
    private static final Metadata nothing_metadata;
    public static final SchemaDef schemaDef;
    private static final Metadata string_value_metadata;
    private static final Metadata uint_value_metadata;
    private static final Metadata wstring_value_metadata;
    
    static
    {
      metadata.setName("Variant");
      metadata.setQualified_name("com.microsoft.bond.Variant");
      uint_value_metadata = new Metadata();
      uint_value_metadata.setName("uint_value");
      uint_value_metadata.getDefault_value().setUint_value(0L);
      int_value_metadata = new Metadata();
      int_value_metadata.setName("int_value");
      int_value_metadata.getDefault_value().setInt_value(0L);
      double_value_metadata = new Metadata();
      double_value_metadata.setName("double_value");
      double_value_metadata.getDefault_value().setDouble_value(0.0D);
      string_value_metadata = new Metadata();
      string_value_metadata.setName("string_value");
      wstring_value_metadata = new Metadata();
      wstring_value_metadata.setName("wstring_value");
      nothing_metadata = new Metadata();
      nothing_metadata.setName("nothing");
      nothing_metadata.getDefault_value().setUint_value(0L);
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
      paramSchemaDef.setMetadata(uint_value_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_UINT64);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)1);
      paramSchemaDef.setMetadata(int_value_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_INT64);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)2);
      paramSchemaDef.setMetadata(double_value_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_DOUBLE);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)3);
      paramSchemaDef.setMetadata(string_value_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_STRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)4);
      paramSchemaDef.setMetadata(wstring_value_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_WSTRING);
      localStructDef.getFields().add(paramSchemaDef);
      paramSchemaDef = new FieldDef();
      paramSchemaDef.setId((short)5);
      paramSchemaDef.setMetadata(nothing_metadata);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\bond\Variant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */