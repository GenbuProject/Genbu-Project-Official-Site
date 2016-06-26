package com.microsoft.bond;

import com.microsoft.bond.internal.Marshaler;
import com.microsoft.bond.internal.ReadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class List<T extends BondSerializable, U extends BondSerializable>
  implements BondSerializable, BondMirror
{
  private Class<T> generic_type_T;
  private Class<U> generic_type_U;
  private LinkedList<T> value;
  
  public List()
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
    //   13: iconst_0
    //   14: istore_3
    //   15: iload_3
    //   16: aload 4
    //   18: getfield 89	com/microsoft/bond/ProtocolReader$ListTag:size	I
    //   21: if_icmpge +36 -> 57
    //   24: aload_0
    //   25: getfield 32	com/microsoft/bond/List:generic_type_T	Ljava/lang/Class;
    //   28: invokevirtual 93	java/lang/Class:newInstance	()Ljava/lang/Object;
    //   31: checkcast 7	com/microsoft/bond/BondSerializable
    //   34: astore_2
    //   35: aload_2
    //   36: aload_1
    //   37: invokeinterface 97 2 0
    //   42: aload_0
    //   43: getfield 99	com/microsoft/bond/List:value	Ljava/util/LinkedList;
    //   46: aload_2
    //   47: invokevirtual 105	java/util/LinkedList:addLast	(Ljava/lang/Object;)V
    //   50: iload_3
    //   51: iconst_1
    //   52: iadd
    //   53: istore_3
    //   54: goto -39 -> 15
    //   57: aload_1
    //   58: invokevirtual 108	com/microsoft/bond/ProtocolReader:readContainerEnd	()V
    //   61: return
    //   62: astore_2
    //   63: aconst_null
    //   64: astore_2
    //   65: goto -23 -> 42
    //   68: astore 5
    //   70: goto -28 -> 42
    //   73: astore_2
    //   74: aconst_null
    //   75: astore_2
    //   76: goto -34 -> 42
    //   79: astore 5
    //   81: goto -39 -> 42
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	List
    //   0	84	1	paramProtocolReader	ProtocolReader
    //   0	84	2	paramBondDataType	BondDataType
    //   14	40	3	i	int
    //   11	6	4	localListTag	ProtocolReader.ListTag
    //   68	1	5	localInstantiationException	InstantiationException
    //   79	1	5	localIllegalAccessException	IllegalAccessException
    // Exception table:
    //   from	to	target	type
    //   24	35	62	java/lang/InstantiationException
    //   35	42	68	java/lang/InstantiationException
    //   24	35	73	java/lang/IllegalAccessException
    //   35	42	79	java/lang/IllegalAccessException
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
  
  public final LinkedList<T> getValue()
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
      paramObject = (List)paramObject;
    } while ((!memberwiseCompareQuick((List)paramObject)) || (!memberwiseCompareDeep((List)paramObject)));
    return true;
  }
  
  protected boolean memberwiseCompareDeep(List<T, U> paramList)
  {
    if ((this.value != null) && (this.value.size() != 0))
    {
      int i = 0;
      while (i < this.value.size())
      {
        BondSerializable localBondSerializable = (BondSerializable)this.value.get(i);
        localBondSerializable = (BondSerializable)paramList.value.get(i);
        i += 1;
      }
    }
    return true;
  }
  
  protected boolean memberwiseCompareQuick(List<T, U> paramList)
  {
    int i;
    int j;
    if (this.value == null)
    {
      i = 1;
      if (paramList.value != null) {
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
    while (this.value.size() == paramList.value.size())
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
    reset("List", "com.microsoft.bond.List");
  }
  
  protected void reset(String paramString1, String paramString2)
  {
    if (this.value == null)
    {
      this.value = new LinkedList();
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
    this.value = ((LinkedList)paramObject);
  }
  
  public final void setValue(LinkedList<T> paramLinkedList)
  {
    this.value = paramLinkedList;
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
      metadata.setName("List");
      metadata.setQualified_name("com.microsoft.bond.List");
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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\bond\List.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */