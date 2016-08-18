package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;

class PrimitiveFactory
  extends Factory
{
  public PrimitiveFactory(Context paramContext, Type paramType)
  {
    super(paramContext, paramType);
  }
  
  public PrimitiveFactory(Context paramContext, Type paramType, Class paramClass)
  {
    super(paramContext, paramType, paramClass);
  }
  
  public Object getInstance(String paramString, Class paramClass)
    throws Exception
  {
    return this.support.read(paramString, paramClass);
  }
  
  public Instance getInstance(InputNode paramInputNode)
    throws Exception
  {
    paramInputNode = getOverride(paramInputNode);
    Class localClass = getType();
    if (paramInputNode == null) {
      return this.context.getInstance(localClass);
    }
    return new ObjectInstance(this.context, paramInputNode);
  }
  
  public String getText(Object paramObject)
    throws Exception
  {
    Class localClass = paramObject.getClass();
    if (localClass.isEnum()) {
      return this.support.write(paramObject, localClass);
    }
    return this.support.write(paramObject, localClass);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\PrimitiveFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */