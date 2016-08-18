package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

class CompositeArray
  implements Converter
{
  private final Type entry;
  private final ArrayFactory factory;
  private final String parent;
  private final Traverser root;
  private final Type type;
  
  public CompositeArray(Context paramContext, Type paramType1, Type paramType2, String paramString)
  {
    this.factory = new ArrayFactory(paramContext, paramType1);
    this.root = new Traverser(paramContext);
    this.parent = paramString;
    this.entry = paramType2;
    this.type = paramType1;
  }
  
  private void read(InputNode paramInputNode, Object paramObject, int paramInt)
    throws Exception
  {
    Class localClass = this.entry.getType();
    Object localObject = null;
    if (!paramInputNode.isEmpty()) {
      localObject = this.root.read(paramInputNode, localClass);
    }
    Array.set(paramObject, paramInt, localObject);
  }
  
  private boolean validate(InputNode paramInputNode, Class paramClass)
    throws Exception
  {
    for (;;)
    {
      InputNode localInputNode = paramInputNode.getNext();
      if (localInputNode == null) {
        return true;
      }
      if (!localInputNode.isEmpty()) {
        this.root.validate(localInputNode, paramClass);
      }
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
      read(localInputNode, paramObject, i);
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
    while (i < j)
    {
      Object localObject = Array.get(paramObject, i);
      Class localClass = this.entry.getType();
      this.root.write(paramOutputNode, localObject, localClass, this.parent);
      i += 1;
    }
    paramOutputNode.commit();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\CompositeArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */