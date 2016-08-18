package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.Iterator;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class TextList
  implements Repeater
{
  private final CollectionFactory factory;
  private final Primitive primitive;
  private final Type type = new ClassType(String.class);
  
  public TextList(Context paramContext, Type paramType, Label paramLabel)
  {
    this.factory = new CollectionFactory(paramContext, paramType);
    this.primitive = new Primitive(paramContext, this.type);
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    Instance localInstance = this.factory.getInstance(paramInputNode);
    Object localObject = localInstance.getInstance();
    if (localInstance.isReference()) {
      return localInstance.getInstance();
    }
    return read(paramInputNode, localObject);
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    Collection localCollection = (Collection)paramObject;
    paramInputNode = this.primitive.read(paramInputNode);
    if (paramInputNode != null) {
      localCollection.add(paramInputNode);
    }
    return paramObject;
  }
  
  public boolean validate(InputNode paramInputNode)
    throws Exception
  {
    return true;
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    paramObject = (Collection)paramObject;
    paramOutputNode = paramOutputNode.getParent();
    paramObject = ((Collection)paramObject).iterator();
    while (((Iterator)paramObject).hasNext())
    {
      Object localObject = ((Iterator)paramObject).next();
      this.primitive.write(paramOutputNode, localObject);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\TextList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */