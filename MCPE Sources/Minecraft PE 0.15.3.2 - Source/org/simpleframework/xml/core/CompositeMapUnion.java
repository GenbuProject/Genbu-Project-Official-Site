package org.simpleframework.xml.core;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class CompositeMapUnion
  implements Repeater
{
  private final Context context;
  private final LabelMap elements;
  private final Group group;
  private final Expression path;
  private final Style style;
  private final Type type;
  
  public CompositeMapUnion(Context paramContext, Group paramGroup, Expression paramExpression, Type paramType)
    throws Exception
  {
    this.elements = paramGroup.getElements();
    this.style = paramContext.getStyle();
    this.context = paramContext;
    this.group = paramGroup;
    this.type = paramType;
    this.path = paramExpression;
  }
  
  private void write(OutputNode paramOutputNode, Object paramObject1, Object paramObject2, Label paramLabel)
    throws Exception
  {
    Converter localConverter = paramLabel.getConverter(this.context);
    paramObject1 = Collections.singletonMap(paramObject1, paramObject2);
    if (!paramLabel.isInline())
    {
      paramObject2 = paramLabel.getName();
      paramObject2 = this.style.getElement((String)paramObject2);
      if (!paramOutputNode.isCommitted()) {
        paramOutputNode.setName((String)paramObject2);
      }
    }
    localConverter.write(paramOutputNode, paramObject1);
  }
  
  private void write(OutputNode paramOutputNode, Map paramMap)
    throws Exception
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      Object localObject2 = paramMap.get(localObject1);
      if (localObject2 != null)
      {
        Class localClass = localObject2.getClass();
        Label localLabel = this.group.getLabel(localClass);
        if (localLabel == null) {
          throw new UnionException("Value of %s not declared in %s with annotation %s", new Object[] { localClass, this.type, this.group });
        }
        write(paramOutputNode, localObject1, localObject2, localLabel);
      }
    }
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    String str = paramInputNode.getName();
    str = this.path.getElement(str);
    return ((Label)this.elements.get(str)).getConverter(this.context).read(paramInputNode);
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    String str = paramInputNode.getName();
    str = this.path.getElement(str);
    return ((Label)this.elements.get(str)).getConverter(this.context).read(paramInputNode, paramObject);
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
    paramObject = (Map)paramObject;
    if (this.group.isInline())
    {
      if (!((Map)paramObject).isEmpty()) {
        write(paramOutputNode, (Map)paramObject);
      }
      while (paramOutputNode.isCommitted()) {
        return;
      }
      paramOutputNode.remove();
      return;
    }
    write(paramOutputNode, (Map)paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\CompositeMapUnion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */