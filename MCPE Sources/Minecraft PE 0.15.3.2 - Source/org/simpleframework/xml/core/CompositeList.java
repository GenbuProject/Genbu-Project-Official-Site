package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.Iterator;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class CompositeList
  implements Converter
{
  private final Type entry;
  private final CollectionFactory factory;
  private final String name;
  private final Traverser root;
  private final Type type;
  
  public CompositeList(Context paramContext, Type paramType1, Type paramType2, String paramString)
  {
    this.factory = new CollectionFactory(paramContext, paramType1);
    this.root = new Traverser(paramContext);
    this.entry = paramType2;
    this.type = paramType1;
    this.name = paramString;
  }
  
  private Object populate(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    paramObject = (Collection)paramObject;
    for (;;)
    {
      InputNode localInputNode = paramInputNode.getNext();
      Class localClass = this.entry.getType();
      if (localInputNode == null) {
        return paramObject;
      }
      ((Collection)paramObject).add(this.root.read(localInputNode, localClass));
    }
  }
  
  private boolean validate(InputNode paramInputNode, Class paramClass)
    throws Exception
  {
    for (;;)
    {
      paramClass = paramInputNode.getNext();
      Class localClass = this.entry.getType();
      if (paramClass == null) {
        return true;
      }
      this.root.validate(paramClass, localClass);
    }
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
    paramObject = ((Collection)paramObject).iterator();
    while (((Iterator)paramObject).hasNext())
    {
      Object localObject = ((Iterator)paramObject).next();
      if (localObject != null)
      {
        Class localClass1 = this.entry.getType();
        Class localClass2 = localObject.getClass();
        if (!localClass1.isAssignableFrom(localClass2)) {
          throw new PersistenceException("Entry %s does not match %s for %s", new Object[] { localClass2, this.entry, this.type });
        }
        this.root.write(paramOutputNode, localObject, localClass1, this.name);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\CompositeList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */