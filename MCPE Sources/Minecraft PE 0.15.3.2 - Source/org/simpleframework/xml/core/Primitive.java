package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

class Primitive
  implements Converter
{
  private final Context context;
  private final String empty;
  private final Class expect;
  private final PrimitiveFactory factory;
  private final Type type;
  
  public Primitive(Context paramContext, Type paramType)
  {
    this(paramContext, paramType, null);
  }
  
  public Primitive(Context paramContext, Type paramType, String paramString)
  {
    this.factory = new PrimitiveFactory(paramContext, paramType);
    this.expect = paramType.getType();
    this.context = paramContext;
    this.empty = paramString;
    this.type = paramType;
  }
  
  private Object readElement(InputNode paramInputNode)
    throws Exception
  {
    Instance localInstance = this.factory.getInstance(paramInputNode);
    if (!localInstance.isReference()) {
      return readElement(paramInputNode, localInstance);
    }
    return localInstance.getInstance();
  }
  
  private Object readElement(InputNode paramInputNode, Instance paramInstance)
    throws Exception
  {
    paramInputNode = read(paramInputNode, this.expect);
    if (paramInstance != null) {
      paramInstance.setInstance(paramInputNode);
    }
    return paramInputNode;
  }
  
  private Object readTemplate(String paramString, Class paramClass)
    throws Exception
  {
    paramString = this.context.getProperty(paramString);
    if (paramString != null) {
      return this.factory.getInstance(paramString, paramClass);
    }
    return null;
  }
  
  private boolean validateElement(InputNode paramInputNode)
    throws Exception
  {
    paramInputNode = this.factory.getInstance(paramInputNode);
    if (!paramInputNode.isReference()) {
      paramInputNode.setInstance(null);
    }
    return true;
  }
  
  public Object read(InputNode paramInputNode)
    throws Exception
  {
    if (paramInputNode.isElement()) {
      return readElement(paramInputNode);
    }
    return read(paramInputNode, this.expect);
  }
  
  public Object read(InputNode paramInputNode, Class paramClass)
    throws Exception
  {
    paramInputNode = paramInputNode.getValue();
    if (paramInputNode == null) {
      return null;
    }
    if ((this.empty != null) && (paramInputNode.equals(this.empty))) {
      return this.empty;
    }
    return readTemplate(paramInputNode, paramClass);
  }
  
  public Object read(InputNode paramInputNode, Object paramObject)
    throws Exception
  {
    if (paramObject != null) {
      throw new PersistenceException("Can not read existing %s for %s", new Object[] { this.expect, this.type });
    }
    return read(paramInputNode);
  }
  
  public boolean validate(InputNode paramInputNode)
    throws Exception
  {
    if (paramInputNode.isElement()) {
      validateElement(paramInputNode);
    }
    for (;;)
    {
      return true;
      paramInputNode.getValue();
    }
  }
  
  public void write(OutputNode paramOutputNode, Object paramObject)
    throws Exception
  {
    paramObject = this.factory.getText(paramObject);
    if (paramObject != null) {
      paramOutputNode.setValue((String)paramObject);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Primitive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */