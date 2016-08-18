package com.microsoft.bond;

import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class Vector<T extends BondSerializable, U extends BondSerializable>
  implements BondSerializable, BondMirror
{
  private Class<T> generic_type_T;
  private Class<U> generic_type_U;
  private ArrayList<T> value;
  
  public Vector()
  {
    Type[] arrayOfType = getGenericTypeArguments();
    int i = 0 + 1;
    this.generic_type_T = ((Class)arrayOfType[0]);
    this.generic_type_U = ((Class)arrayOfType[i]);
    reset();
  }
  
  private Type[] getGenericTypeArguments()
  {
    return ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments();
  }
  
  public static SchemaDef getRuntimeSchema()
  {
    return Schema.schemaDef;
  }
  
  private void readFieldImpl_value(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    ReadHelper.validateType(paramBondDataType, BondDataType.BT_LIST);
    ProtocolReader.ListTag localListTag = paramProtocolReader.readContainerBegin();
    this.value.ensureCapacity(localListTag.size);
    int i = 0;
    while (i < localListTag.size)
    {
      Object localObject = null;
      paramBondDataType = null;
      try
      {
        BondSerializable localBondSerializable = (BondSerializable)this.generic_type_T.newInstance();
        paramBondDataType = localBondSerializable;
        localObject = localBondSerializable;
        localBondSerializable.readNested(paramProtocolReader);
        paramBondDataType = localBondSerializable;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        for (;;) {}
      }
      catch (InstantiationException paramBondDataType)
      {
        for (;;)
        {
          paramBondDataType = localIllegalAccessException;
        }
      }
      this.value.add(paramBondDataType);
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
    return null;
  }
  
  public Object getField(FieldDef paramFieldDef)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return null;
    }
    return this.value;
  }
  
  public SchemaDef getSchema()
  {
    return getRuntimeSchema();
  }
  
  public final ArrayList<T> getValue()
  {
    return this.value;
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
      paramObject = (Vector)paramObject;
    } while ((!memberwiseCompareQuick((Vector)paramObject)) || (!memberwiseCompareDeep((Vector)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(Vector<T, U> paramVector)
  {
    int i;
    if ((1 != 0) && (this.value != null) && (this.value.size() != 0)) {
      i = 0;
    }
    for (;;)
    {
      if (i < this.value.size())
      {
        BondSerializable localBondSerializable = (BondSerializable)this.value.get(i);
        localBondSerializable = (BondSerializable)paramVector.value.get(i);
        if (1 != 0) {}
      }
      else
      {
        return true;
      }
      i += 1;
    }
  }
  
  protected boolean memberwiseCompareQuick(Vector<T, U> paramVector)
  {
    int i;
    int j;
    if (1 != 0) {
      if (this.value == null)
      {
        i = 1;
        if (paramVector.value != null) {
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
        if (this.value != null) {
          break label57;
        }
      }
    }
    label47:
    label52:
    label57:
    while (this.value.size() == paramVector.value.size())
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
      readFieldImpl_value(paramProtocolReader, localFieldTag.type);
    }
  }
  
  protected void readUntagged(ProtocolReader paramProtocolReader, boolean paramBoolean)
    throws IOException
  {
    boolean bool = paramProtocolReader.hasCapability(ProtocolCapability.CAN_OMIT_FIELDS);
    paramProtocolReader.readStructBegin(paramBoolean);
    if ((!bool) || (!paramProtocolReader.readFieldOmitted())) {
      readFieldImpl_value(paramProtocolReader, BondDataType.BT_LIST);
    }
    paramProtocolReader.readStructEnd();
  }
  
  public void reset()
  {
    reset("Vector", "com.microsoft.bond.Vector");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    if (this.value == null)
    {
      this.value = new ArrayList();
      return;
    }
    this.value.clear();
  }
  
  public void setField(FieldDef paramFieldDef, Object paramObject)
  {
    switch (paramFieldDef.getId())
    {
    default: 
      return;
    }
    this.value = ((ArrayList)paramObject);
  }
  
  public final void setValue(ArrayList<T> paramArrayList)
  {
    this.value = paramArrayList;
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
    int i = this.value.size();
    if ((!bool) || (i != 0))
    {
      paramProtocolWriter.writeFieldBegin(BondDataType.BT_LIST, 0, Schema.value_metadata);
      paramProtocolWriter.writeContainerBegin(i, BondDataType.BT_STRUCT);
      Iterator localIterator = this.value.iterator();
      while (localIterator.hasNext()) {
        ((BondSerializable)localIterator.next()).writeNested(paramProtocolWriter, false);
      }
      paramProtocolWriter.writeContainerEnd();
      paramProtocolWriter.writeFieldEnd();
    }
    for (;;)
    {
      paramProtocolWriter.writeStructEnd(paramBoolean);
      return;
      paramProtocolWriter.writeFieldOmitted(BondDataType.BT_LIST, 0, Schema.value_metadata);
    }
  }
  
  public static class Schema
  {
    public static final Metadata metadata = new Metadata();
    public static final SchemaDef schemaDef;
    private static final Metadata value_metadata;
    
    static
    {
      metadata.setName("Vector");
      metadata.setQualified_name("com.microsoft.bond.Vector");
      value_metadata = new Metadata();
      value_metadata.setName("value");
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
      paramSchemaDef.setMetadata(value_metadata);
      paramSchemaDef.getType().setId(BondDataType.BT_LIST);
      paramSchemaDef.getType().setElement(new TypeDef());
      paramSchemaDef.getType().getElement().setId(BondDataType.BT_STRUCT);
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\bond\Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */