package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class CompositeListUnion
  implements Repeater
{
  private final Context context;
  private final LabelMap elements;
  private final Group group;
  private final Expression path;
  private final Style style;
  private final Type type;
  
  public CompositeListUnion(Context paramContext, Group paramGroup, Expression paramExpression, Type paramType)
    throws Exception
  {
    this.elements = paramGroup.getElements();
    this.style = paramContext.getStyle();
    this.context = paramContext;
    this.group = paramGroup;
    this.type = paramType;
    this.path = paramExpression;
  }
  
  private Object readElement(InputNode paramInputNode)
    throws Exception
  {
    String str = paramInputNode.getName();
    str = this.path.getElement(str);
    return ((Label)this.elements.get(str)).getConverter(this.context).read(paramInputNode);
  }
  
  private Object readElement(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    String str = paramInputNode.getName();
    str = this.path.getElement(str);
    return ((Label)this.elements.get(str)).getConverter(this.context).read(paramInputNode, paramObject);
  }
  
  private Object readText(InputNode paramInputNode)
    throws Exception
  {
    return this.group.getText().getConverter(this.context).read(paramInputNode);
  }
  
  private Object readText(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    return this.group.getText().getConverter(this.context).read(paramInputNode.getParent(), paramObject);
  }
  
  private void write(OutputNode paramOutputNode, Object paramObject, Label paramLabel)
    throws Exception
  {
    Converter localConverter = paramLabel.getConverter(this.context);
    paramObject = Collections.singleton(paramObject);
    if (!paramLabel.isInline())
    {
      paramLabel = paramLabel.getName();
      paramLabel = this.style.getElement(paramLabel);
      if (!paramOutputNode.isCommitted()) {
        paramOutputNode.setName(paramLabel);
      }
    }
    localConverter.write(paramOutputNode, paramObject);
  }
  
  private void write(OutputNode paramOutputNode, Collection paramCollection)
    throws Exception
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Object localObject = paramCollection.next();
      if (localObject != null)
      {
        Class localClass = localObject.getClass();
        Label localLabel = this.group.getLabel(localClass);
        if (localLabel == null) {
          throw new UnionException("Entry of %s not declared in %s with annotation %s", new Object[] { localClass, this.type, this.group });
        }
        write(paramOutputNode, localObject, localLabel);
      }
    }
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    if (this.group.getText() == null) {
      return readElement(paramInputNode);
    }
    return readText(paramInputNode);
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    Object localObject = readElement(paramInputNode, paramObject);
    if (this.group.getText() != null) {
      localObject = readText(paramInputNode, paramObject);
    }
    return localObject;
  }
  
  public boolean validate(InputNode paramInputNode)
    throws Exception
  {
    String str = paramInputNode.getName();
    str = this.path.getElement(str);
    return ((Label)this.elements.get(str)).getConverter(this.context).validate(paramInputNode);
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    paramObject = (Collection)paramObject;
    if (this.group.isInline())
    {
      if (!((Collection)paramObject).isEmpty()) {
        write(paramOutputNode, (Collection)paramObject);
      }
      while (paramOutputNode.isCommitted()) {
        return;
      }
      paramOutputNode.remove();
      return;
    }
    write(paramOutputNode, (Collection)paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\CompositeListUnion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */