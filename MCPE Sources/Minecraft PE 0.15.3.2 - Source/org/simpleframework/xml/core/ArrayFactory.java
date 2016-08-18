package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.Position;

class ArrayFactory
  extends Factory
{
  public ArrayFactory(Context paramContext, Type paramType)
  {
    super(paramContext, paramType);
  }
  
  private Class getComponentType()
    throws Exception
  {
    Class localClass = getType();
    if (!localClass.isArray()) {
      throw new InstantiationException("The %s not an array for %s", new Object[] { localClass, this.type });
    }
    return localClass.getComponentType();
  }
  
  private Instance getInstance(Value paramValue, Class paramClass)
    throws Exception
  {
    Class localClass = getComponentType();
    if (!localClass.isAssignableFrom(paramClass)) {
      throw new InstantiationException("Array of type %s cannot hold %s for %s", new Object[] { localClass, paramClass, this.type });
    }
    return new ArrayInstance(paramValue);
  }
  
  public Object getInstance()
    throws Exception
  {
    Class localClass = getComponentType();
    if (localClass != null) {
      return Array.newInstance(localClass, 0);
    }
    return null;
  }
  
  public Instance getInstance(InputNode paramInputNode)
    throws Exception
  {
    Position localPosition = paramInputNode.getPosition();
    paramInputNode = getOverride(paramInputNode);
    if (paramInputNode == null) {
      throw new ElementException("Array length required for %s at %s", new Object[] { this.type, localPosition });
    }
    return getInstance(paramInputNode, paramInputNode.getType());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ArrayFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */