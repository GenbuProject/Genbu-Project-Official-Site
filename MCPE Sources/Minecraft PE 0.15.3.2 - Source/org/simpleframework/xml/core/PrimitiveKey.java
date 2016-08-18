package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class PrimitiveKey
  implements Converter
{
  private final Context context;
  private final Entry entry;
  private final PrimitiveFactory factory;
  private final Primitive root;
  private final Style style;
  private final Type type;
  
  public PrimitiveKey(Context paramContext, Entry paramEntry, Type paramType)
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
    paramInputNode = paramInputNode.getAttribute(this.style.getAttribute(paramString));
    if (paramInputNode == null) {
      return null;
    }
    return this.root.read(paramInputNode);
  }
  
  private Object readElement(InputNode paramInputNode, String paramString)
    throws Exception
  {
    paramInputNode = paramInputNode.getNext(this.style.getElement(paramString));
    if (paramInputNode == null) {
      return null;
    }
    return this.root.read(paramInputNode);
  }
  
  private boolean validateAttribute(InputNode paramInputNode, String paramString)
    throws Exception
  {
    paramInputNode = paramInputNode.getAttribute(this.style.getElement(paramString));
    if (paramInputNode == null) {
      return true;
    }
    return this.root.validate(paramInputNode);
  }
  
  private boolean validateElement(InputNode paramInputNode, String paramString)
    throws Exception
  {
    paramInputNode = paramInputNode.getNext(this.style.getElement(paramString));
    if (paramInputNode == null) {
      return true;
    }
    return this.root.validate(paramInputNode);
  }
  
  private void writeAttribute(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    Class localClass = this.type.getType();
    String str2 = this.factory.getText(paramObject);
    String str1 = this.entry.getKey();
    paramObject = str1;
    if (str1 == null) {
      paramObject = this.context.getName(localClass);
    }
    paramObject = this.style.getAttribute((String)paramObject);
    if (str2 != null) {
      paramOutputNode.setAttribute((String)paramObject, str2);
    }
  }
  
  private void writeElement(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    Class localClass = this.type.getType();
    String str2 = this.entry.getKey();
    String str1 = str2;
    if (str2 == null) {
      str1 = this.context.getName(localClass);
    }
    paramOutputNode = paramOutputNode.getChild(this.style.getElement(str1));
    if ((paramObject != null) && (!isOverridden(paramOutputNode, paramObject))) {
      this.root.write(paramOutputNode, paramObject);
    }
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    Class localClass = this.type.getType();
    String str2 = this.entry.getKey();
    String str1 = str2;
    if (str2 == null) {
      str1 = this.context.getName(localClass);
    }
    if (!this.entry.isAttribute()) {
      return readElement(paramInputNode, str1);
    }
    return readAttribute(paramInputNode, str1);
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    Class localClass = this.type.getType();
    if (paramObject != null) {
      throw new PersistenceException("Can not read key of %s for %s", new Object[] { localClass, this.entry });
    }
    return read(paramInputNode);
  }
  
  public boolean validate(InputNode paramInputNode)
    throws Exception
  {
    Class localClass = this.type.getType();
    String str2 = this.entry.getKey();
    String str1 = str2;
    if (str2 == null) {
      str1 = this.context.getName(localClass);
    }
    if (!this.entry.isAttribute()) {
      return validateElement(paramInputNode, str1);
    }
    return validateAttribute(paramInputNode, str1);
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    if (!this.entry.isAttribute()) {
      writeElement(paramOutputNode, paramObject);
    }
    while (paramObject == null) {
      return;
    }
    writeAttribute(paramOutputNode, paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\PrimitiveKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */