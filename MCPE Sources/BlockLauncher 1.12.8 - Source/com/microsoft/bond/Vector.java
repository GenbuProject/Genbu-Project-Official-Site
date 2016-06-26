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
    this.generic_type_T = ((Class)arrayOfType[0]);
    this.generic_type_U = ((Class)arrayOfType[1]);
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
  
  /* Error */
  private void readFieldImpl_value(ProtocolReader paramProtocolReader, BondDataType paramBondDataType)
    throws IOException
  {
    // Byte code:
    //   0: aload_2
    //   1: getstatic 71	com/microsoft/bond/BondDataType:BT_LIST	Lcom/microsoft/bond/BondDataType;
    //   4: invokestatic 77	com/microsoft/bond/internal/ReadHelper:validateType	(Lcom/microsoft/bond/BondDataType;Lcom/microsoft/bond/BondDataType;)V
    //   7: aload_1
    //   8: invokevirtual 83	com/microsoft/bond/ProtocolReader:readContainerBegin	()Lcom/microsoft/bond/ProtocolReader$ListTag;
    //   11: astore 4
    //   13: aload_0
    //   14: getfield 85	com/microsoft/bond/Vector:value	Ljava/util/ArrayList;
    //   17: aload 4
    //   19: getfield 91	com/microsoft/bond/ProtocolReader$ListTag:size	I
    //   22: invokevirtual 97	java/util/ArrayList:ensureCapacity	(I)V
    //   25: iconst_0
    //   26: istore_3
    //   27: iload_3
    //   28: aload 4
    //   30: getfield 91	com/microsoft/bond/ProtocolReader$ListTag:size	I
    //   33: if_icmpge +37 -> 70
    //   36: aload_0
    //   37: getfield 32	com/microsoft/bond/Vector:generic_type_T	Ljava/lang/Class;
    //   40: invokevirtual 101	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   43: checkcast 7	com/microsoft/bond/BondSerializable
    //   46: astore_2
    //   47: aload_2
    //   48: aload_1
    //   49: invokeinterface 105 2 0
    //   54: aload_0
    //   55: getfield 85	com/microsoft/bond/Vector:value	Ljava/util/ArrayList;
    //   58: aload_2
    //   59: invokevirtual 109	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   62: pop
    //   63: iload_3
    //   64: iconst_1
    //   65: iadd
    //   66: istore_3
    //   67: goto -40 -> 27
    //   70: aload_1
    //   71: invokevirtual 112	com/microsoft/bond/ProtocolReader:readContainerEnd	()V
    //   74: return
    //   75: astore_2
    //   76: aconst_null
    //   77: astore_2
    //   78: goto -24 -> 54
    //   81: astore 5
    //   83: goto -29 -> 54
    //   86: astore_2
    //   87: aconst_null
    //   88: astore_2
    //   89: goto -35 -> 54
    //   92: astore 5
    //   94: goto -40 -> 54
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	97	0	this	Vector
    //   0	97	1	paramProtocolReader	ProtocolReader
    //   0	97	2	paramBondDataType	BondDataType
    //   26	41	3	i	int
    //   11	18	4	localListTag	ProtocolReader.ListTag
    //   81	1	5	localInstantiationException	InstantiationException
    //   92	1	5	localIllegalAccessException	IllegalAccessException
    // Exception table:
    //   from	to	target	type
    //   36	47	75	java/lang/InstantiationException
    //   47	54	81	java/lang/InstantiationException
    //   36	47	86	java/lang/IllegalAccessException
    //   47	54	92	java/lang/IllegalAccessException
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
    if ((this.value != null) && (this.value.size() != 0))
    {
      int i = 0;
      while (i < this.value.size())
      {
        BondSerializable localBondSerializable = (BondSerializable)this.value.get(i);
        localBondSerializable = (BondSerializable)paramVector.value.get(i);
        i += 1;
      }
    }
    return true;
  }
  
  protected boolean memberwiseCompareQuick(Vector<T, U> paramVector)
  {
    int i;
    int j;
    if (this.value == null)
    {
      i = 1;
      if (paramVector.value != null) {
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
      if (this.value != null) {
        break label53;
      }
    }
    label43:
    label48:
    label53:
    while (this.value.size() == paramVector.value.size())
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */