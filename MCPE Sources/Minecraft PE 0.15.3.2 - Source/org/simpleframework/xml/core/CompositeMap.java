package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class CompositeMap
  implements Converter
{
  private final Entry entry;
  private final MapFactory factory;
  private final Converter key;
  private final Style style;
  private final Converter value;
  
  public CompositeMap(Context paramContext, Entry paramEntry, Type paramType)
    throws Exception
  {
    this.factory = new MapFactory(paramContext, paramType);
    this.value = paramEntry.getValue(paramContext);
    this.key = paramEntry.getKey(paramContext);
    this.style = paramContext.getStyle();
    this.entry = paramEntry;
  }
  
  private Object populate(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    paramObject = (Map)paramObject;
    for (;;)
    {
      InputNode localInputNode = paramInputNode.getNext();
      if (localInputNode == null) {
        return paramObject;
      }
      ((Map)paramObject).put(this.key.read(localInputNode), this.value.read(localInputNode));
    }
  }
  
  private boolean validate(InputNode paramInputNode, Class paramClass)
    throws Exception
  {
    boolean bool2 = false;
    do
    {
      paramClass = paramInputNode.getNext();
      boolean bool1;
      if (paramClass == null) {
        bool1 = true;
      }
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!this.key.validate(paramClass));
    } while (this.value.validate(paramClass));
    return false;
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    Instance localInstance = this.factory.getInstance(paramInputNode);
    Object localObject2 = localInstance.getInstance();
    Object localObject1 = localObject2;
    if (!localInstance.isReference()) {
      localObject1 = populate(paramInputNode, localObject2);
    }
    return localObject1;
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    Object localObject = this.factory.getInstance(paramInputNode);
    if (((Instance)localObject).isReference()) {
      localObject = ((Instance)localObject).getInstance();
    }
    do
    {
      return localObject;
      ((Instance)localObject).setInstance(paramObject);
      localObject = paramObject;
    } while (paramObject == null);
    return populate(paramInputNode, paramObject);
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
    paramObject = (Map)paramObject;
    Iterator localIterator = ((Map)paramObject).keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      Object localObject2 = this.entry.getEntry();
      localObject2 = paramOutputNode.getChild(this.style.getElement((String)localObject2));
      Object localObject3 = ((Map)paramObject).get(localObject1);
      this.key.write((OutputNode)localObject2, localObject1);
      this.value.write((OutputNode)localObject2, localObject3);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\CompositeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */