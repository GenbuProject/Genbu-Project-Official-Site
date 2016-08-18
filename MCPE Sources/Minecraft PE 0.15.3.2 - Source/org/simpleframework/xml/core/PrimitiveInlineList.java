package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.Iterator;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.Mode;
import org.simpleframework.xml.stream.OutputNode;

class PrimitiveInlineList
  implements Repeater
{
  private final Type entry;
  private final CollectionFactory factory;
  private final String parent;
  private final Primitive root;
  
  public PrimitiveInlineList(Context paramContext, Type paramType1, Type paramType2, String paramString)
  {
    this.factory = new CollectionFactory(paramContext, paramType1);
    this.root = new Primitive(paramContext, paramType2);
    this.parent = paramString;
    this.entry = paramType2;
  }
  
  private boolean isOverridden(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    return this.factory.setOverride(this.entry, paramObject, paramOutputNode);
  }
  
  private Object read(InputNode paramInputNode, Collection paramCollection)
    throws Exception
  {
    InputNode localInputNode = paramInputNode.getParent();
    String str = paramInputNode.getName();
    while (paramInputNode != null)
    {
      paramInputNode = this.root.read(paramInputNode);
      if (paramInputNode != null) {
        paramCollection.add(paramInputNode);
      }
      paramInputNode = localInputNode.getNext(str);
    }
    return paramCollection;
  }
  
  private void write(OutputNode paramOutputNode, Object paramObject, Mode paramMode)
    throws Exception
  {
    paramObject = ((Collection)paramObject).iterator();
    while (((Iterator)paramObject).hasNext())
    {
      Object localObject = ((Iterator)paramObject).next();
      if (localObject != null)
      {
        OutputNode localOutputNode = paramOutputNode.getChild(this.parent);
        if (!isOverridden(localOutputNode, localObject))
        {
          localOutputNode.setMode(paramMode);
          this.root.write(localOutputNode, localObject);
        }
      }
    }
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
    String str = paramInputNode.getName();
    while (paramInputNode != null)
    {
      if (!this.root.validate(paramInputNode)) {
        return false;
      }
      paramInputNode = localInputNode.getNext(str);
    }
    return true;
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    OutputNode localOutputNode = paramOutputNode.getParent();
    Mode localMode = paramOutputNode.getMode();
    if (!paramOutputNode.isCommitted()) {
      paramOutputNode.remove();
    }
    write(localOutputNode, paramObject, localMode);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\PrimitiveInlineList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */