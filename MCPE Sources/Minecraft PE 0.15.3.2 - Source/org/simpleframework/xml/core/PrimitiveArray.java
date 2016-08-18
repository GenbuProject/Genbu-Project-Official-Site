package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

class PrimitiveArray
  implements Converter
{
  private final Type entry;
  private final ArrayFactory factory;
  private final String parent;
  private final Primitive root;
  private final Type type;
  
  public PrimitiveArray(Context paramContext, Type paramType1, Type paramType2, String paramString)
  {
    this.factory = new ArrayFactory(paramContext, paramType1);
    this.root = new Primitive(paramContext, paramType2);
    this.parent = paramString;
    this.entry = paramType2;
    this.type = paramType1;
  }
  
  private boolean isOverridden(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    return this.factory.setOverride(this.entry, paramObject, paramOutputNode);
  }
  
  private boolean validate(InputNode paramInputNode, Class paramClass)
    throws Exception
  {
    for (;;)
    {
      paramClass = paramInputNode.getNext();
      if (paramClass == null) {
        return true;
      }
      this.root.validate(paramClass);
    }
  }
  
  private void write(OutputNode paramOutputNode, Object paramObject, int paramInt)
    throws Exception
  {
    paramObject = Array.get(paramObject, paramInt);
    if ((paramObject != null) && (!isOverridden(paramOutputNode, paramObject))) {
      this.root.write(paramOutputNode, paramObject);
    }
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    Instance localInstance = this.factory.getInstance(paramInputNode);
    Object localObject2 = localInstance.getInstance();
    Object localObject1 = localObject2;
    if (!localInstance.isReference()) {
      localObject1 = read(paramInputNode, localObject2);
    }
    return localObject1;
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    int j = Array.getLength(paramObject);
    int i = 0;
    for (;;)
    {
      Position localPosition = paramInputNode.getPosition();
      InputNode localInputNode = paramInputNode.getNext();
      if (localInputNode == null) {
        return paramObject;
      }
      if (i >= j) {
        throw new ElementException("Array length missing or incorrect for %s at %s", new Object[] { this.type, localPosition });
      }
      Array.set(paramObject, i, this.root.read(localInputNode));
      i += 1;
    }
  }
  
  public boolean validate(InputNode paramInputNode)
    throws Exception
  {
    Instance localInstance = this.factory.getInstance(paramInputNode);
    if (!localInstance.isReference())
    {
      localInstance.setInstance(null);
      return validate(paramInputNode, localInstance.getType());
    }
    return true;
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    int j = Array.getLength(paramObject);
    int i = 0;
    for (;;)
    {
      OutputNode localOutputNode;
      if (i < j)
      {
        localOutputNode = paramOutputNode.getChild(this.parent);
        if (localOutputNode != null) {}
      }
      else
      {
        return;
      }
      write(localOutputNode, paramObject, i);
      i += 1;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\PrimitiveArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */