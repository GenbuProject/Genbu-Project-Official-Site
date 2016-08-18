package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class PrimitiveValue
  implements Converter
{
  private final Context context;
  private final Entry entry;
  private final PrimitiveFactory factory;
  private final Primitive root;
  private final Style style;
  private final Type type;
  
  public PrimitiveValue(Context paramContext, Entry paramEntry, Type paramType)
  {
    this.factory = new PrimitiveFactory(paramContext, paramType);
    this.root = new Primitive(paramContext, paramType);
    this.style = paramContext.getStyle();
    this.context = paramContext;
    this.entry = paramEntry;
    this.type = paramType;
  }
  
  private boolean isOverridden(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    return this.factory.setOverride(this.type, paramObject, paramOutputNode);
  }
  
  private Object readAttribute(InputNode paramInputNode, String paramString)
    throws Exception
  {
    InputNode localInputNode = paramInputNode;
    if (paramString != null) {
      localInputNode = paramInputNode.getAttribute(this.style.getAttribute(paramString));
    }
    if (localInputNode == null) {
      return null;
    }
    return this.root.read(localInputNode);
  }
  
  private Object readElement(InputNode paramInputNode, String paramString)
    throws Exception
  {
    paramInputNode = paramInputNode.getNext(this.style.getAttribute(paramString));
    if (paramInputNode == null) {
      return null;
    }
    return this.root.read(paramInputNode);
  }
  
  private boolean validateAttribute(InputNode paramInputNode, String paramString)
    throws Exception
  {
    InputNode localInputNode = paramInputNode;
    if (paramString != null) {
      localInputNode = paramInputNode.getNext(this.style.getAttribute(paramString));
    }
    if (localInputNode == null) {
      return true;
    }
    return this.root.validate(localInputNode);
  }
  
  private boolean validateElement(InputNode paramInputNode, String paramString)
    throws Exception
  {
    if (paramInputNode.getNext(this.style.getAttribute(paramString)) == null) {
      return true;
    }
    return this.root.validate(paramInputNode);
  }
  
  private void writeAttribute(OutputNode paramOutputNode, Object paramObject, String paramString)
    throws Exception
  {
    if (paramObject != null)
    {
      OutputNode localOutputNode = paramOutputNode;
      if (paramString != null) {
        localOutputNode = paramOutputNode.setAttribute(this.style.getAttribute(paramString), null);
      }
      this.root.write(localOutputNode, paramObject);
    }
  }
  
  private void writeElement(OutputNode paramOutputNode, Object paramObject, String paramString)
    throws Exception
  {
    paramOutputNode = paramOutputNode.getChild(this.style.getAttribute(paramString));
    if ((paramObject != null) && (!isOverridden(paramOutputNode, paramObject))) {
      this.root.write(paramOutputNode, paramObject);
    }
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    Class localClass = this.type.getType();
    String str2 = this.entry.getValue();
    if (!this.entry.isInline())
    {
      String str1 = str2;
      if (str2 == null) {
        str1 = this.context.getName(localClass);
      }
      return readElement(paramInputNode, str1);
    }
    return readAttribute(paramInputNode, str2);
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    Class localClass = this.type.getType();
    if (paramObject != null) {
      throw new PersistenceException("Can not read value of %s for %s", new Object[] { localClass, this.entry });
    }
    return read(paramInputNode);
  }
  
  public boolean validate(InputNode paramInputNode)
    throws Exception
  {
    Class localClass = this.type.getType();
    String str2 = this.entry.getValue();
    if (!this.entry.isInline())
    {
      String str1 = str2;
      if (str2 == null) {
        str1 = this.context.getName(localClass);
      }
      return validateElement(paramInputNode, str1);
    }
    return validateAttribute(paramInputNode, str2);
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    Class localClass = this.type.getType();
    String str2 = this.entry.getValue();
    if (!this.entry.isInline())
    {
      String str1 = str2;
      if (str2 == null) {
        str1 = this.context.getName(localClass);
      }
      writeElement(paramOutputNode, paramObject, str1);
      return;
    }
    writeAttribute(paramOutputNode, paramObject, str2);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\PrimitiveValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */