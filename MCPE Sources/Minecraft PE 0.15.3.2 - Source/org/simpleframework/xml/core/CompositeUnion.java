package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class CompositeUnion
  implements Converter
{
  private final Context context;
  private final LabelMap elements;
  private final Group group;
  private final Expression path;
  private final Type type;
  
  public CompositeUnion(Context paramContext, Group paramGroup, Expression paramExpression, Type paramType)
    throws Exception
  {
    this.elements = paramGroup.getElements();
    this.context = paramContext;
    this.group = paramGroup;
    this.type = paramType;
    this.path = paramExpression;
  }
  
  private void write(OutputNode paramOutputNode, Object paramObject, Label paramLabel)
    throws Exception
  {
    paramLabel.getConverter(this.context).write(paramOutputNode, paramObject);
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
    Class localClass = paramObject.getClass();
    Label localLabel = this.group.getLabel(localClass);
    if (localLabel == null) {
      throw new UnionException("Value of %s not declared in %s with annotation %s", new Object[] { localClass, this.type, this.group });
    }
    write(paramOutputNode, paramObject, localLabel);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\CompositeUnion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */