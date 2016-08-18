package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.Iterator;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class CompositeInlineList
  implements Repeater
{
  private final Type entry;
  private final CollectionFactory factory;
  private final String name;
  private final Traverser root;
  private final Type type;
  
  public CompositeInlineList(Context paramContext, Type paramType1, Type paramType2, String paramString)
  {
    this.factory = new CollectionFactory(paramContext, paramType1);
    this.root = new Traverser(paramContext);
    this.entry = paramType2;
    this.type = paramType1;
    this.name = paramString;
  }
  
  private Object read(InputNode paramInputNode, Class paramClass)
    throws Exception
  {
    paramInputNode = this.root.read(paramInputNode, paramClass);
    paramClass = paramInputNode.getClass();
    if (!this.entry.getType().isAssignableFrom(paramClass)) {
      throw new PersistenceException("Entry %s does not match %s for %s", new Object[] { paramClass, this.entry, this.type });
    }
    return paramInputNode;
  }
  
  private Object read(InputNode paramInputNode, Collection paramCollection)
    throws Exception
  {
    InputNode localInputNode = paramInputNode.getParent();
    String str = paramInputNode.getName();
    while (paramInputNode != null)
    {
      paramInputNode = read(paramInputNode, this.entry.getType());
      if (paramInputNode != null) {
        paramCollection.add(paramInputNode);
      }
      paramInputNode = localInputNode.getNext(str);
    }
    return paramCollection;
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    Collection localCollection = (Collection)this.factory.getInstance();
    if (localCollection != null) {
      return read(paramInputNode, localCollection);
    }
    return null;
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    paramObject = (Collection)paramObject;
    if (paramObject != null) {
      return read(paramInputNode, (Collection)paramObject);
    }
    return read(paramInputNode);
  }
  
  public boolean validate(InputNode paramInputNode)
    throws Exception
  {
    InputNode localInputNode = paramInputNode.getParent();
    Class localClass = this.entry.getType();
    String str = paramInputNode.getName();
    while (paramInputNode != null)
    {
      if (!this.root.validate(paramInputNode, localClass)) {
        return false;
      }
      paramInputNode = localInputNode.getNext(str);
    }
    return true;
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    paramObject = (Collection)paramObject;
    OutputNode localOutputNode = paramOutputNode.getParent();
    if (!paramOutputNode.isCommitted()) {
      paramOutputNode.remove();
    }
    write(localOutputNode, (Collection)paramObject);
  }
  
  public void write(OutputNode paramOutputNode, Collection paramCollection)
    throws Exception
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Object localObject = paramCollection.next();
      if (localObject != null)
      {
        Class localClass1 = this.entry.getType();
        Class localClass2 = localObject.getClass();
        if (!localClass1.isAssignableFrom(localClass2)) {
          throw new PersistenceException("Entry %s does not match %s for %s", new Object[] { localClass2, localClass1, this.type });
        }
        this.root.write(paramOutputNode, localObject, localClass1, this.name);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\CompositeInlineList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */