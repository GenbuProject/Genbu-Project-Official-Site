package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;
import org.simpleframework.xml.stream.Style;

class CompositeKey
  implements Converter
{
  private final Context context;
  private final Entry entry;
  private final Traverser root;
  private final Style style;
  private final Type type;
  
  public CompositeKey(Context paramContext, Entry paramEntry, Type paramType)
    throws Exception
  {
    this.root = new Traverser(paramContext);
    this.style = paramContext.getStyle();
    this.context = paramContext;
    this.entry = paramEntry;
    this.type = paramType;
  }
  
  private Object read(InputNode paramInputNode, String paramString)
    throws Exception
  {
    String str = this.style.getElement(paramString);
    Class localClass = this.type.getType();
    paramString = paramInputNode;
    if (str != null) {
      paramString = paramInputNode.getNext(str);
    }
    if (paramString == null) {}
    while (paramString.isEmpty()) {
      return null;
    }
    return this.root.read(paramString, localClass);
  }
  
  private boolean validate(InputNode paramInputNode, String paramString)
    throws Exception
  {
    paramInputNode = paramInputNode.getNext(this.style.getElement(paramString));
    paramString = this.type.getType();
    if (paramInputNode == null) {}
    while (paramInputNode.isEmpty()) {
      return true;
    }
    return this.root.validate(paramInputNode, paramString);
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    Position localPosition = paramInputNode.getPosition();
    Class localClass = this.type.getType();
    String str2 = this.entry.getKey();
    String str1 = str2;
    if (str2 == null) {
      str1 = this.context.getName(localClass);
    }
    if (this.entry.isAttribute()) {
      throw new AttributeException("Can not have %s as an attribute for %s at %s", new Object[] { localClass, this.entry, localPosition });
    }
    return read(paramInputNode, str1);
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    Position localPosition = paramInputNode.getPosition();
    Class localClass = this.type.getType();
    if (paramObject != null) {
      throw new PersistenceException("Can not read key of %s for %s at %s", new Object[] { localClass, this.entry, localPosition });
    }
    return read(paramInputNode);
  }
  
  public boolean validate(InputNode paramInputNode)
    throws Exception
  {
    Position localPosition = paramInputNode.getPosition();
    Class localClass = this.type.getType();
    String str2 = this.entry.getKey();
    String str1 = str2;
    if (str2 == null) {
      str1 = this.context.getName(localClass);
    }
    if (this.entry.isAttribute()) {
      throw new ElementException("Can not have %s as an attribute for %s at %s", new Object[] { localClass, this.entry, localPosition });
    }
    return validate(paramInputNode, str1);
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    Class localClass = this.type.getType();
    String str2 = this.entry.getKey();
    if (this.entry.isAttribute()) {
      throw new ElementException("Can not have %s as an attribute for %s", new Object[] { localClass, this.entry });
    }
    String str1 = str2;
    if (str2 == null) {
      str1 = this.context.getName(localClass);
    }
    str1 = this.style.getElement(str1);
    this.root.write(paramOutputNode, paramObject, localClass, str1);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\CompositeKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */