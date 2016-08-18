package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;

class ObjectFactory
  extends PrimitiveFactory
{
  public ObjectFactory(Context paramContext, Type paramType, Class paramClass)
  {
    super(paramContext, paramType, paramClass);
  }
  
  public Instance getInstance(InputNode paramInputNode)
    throws Exception
  {
    paramInputNode = getOverride(paramInputNode);
    Class localClass = getType();
    if (paramInputNode == null)
    {
      if (!isInstantiable(localClass)) {
        throw new InstantiationException("Cannot instantiate %s for %s", new Object[] { localClass, this.type });
      }
      return this.context.getInstance(localClass);
    }
    return new ObjectInstance(this.context, paramInputNode);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ObjectFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */