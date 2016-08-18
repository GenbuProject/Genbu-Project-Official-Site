package org.simpleframework.xml.core;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;

class MapFactory
  extends Factory
{
  public MapFactory(Context paramContext, Type paramType)
  {
    super(paramContext, paramType);
  }
  
  private boolean isMap(Class paramClass)
  {
    return Map.class.isAssignableFrom(paramClass);
  }
  
  public Class getConversion(Class paramClass)
    throws Exception
  {
    if (paramClass.isAssignableFrom(HashMap.class)) {
      return HashMap.class;
    }
    if (paramClass.isAssignableFrom(TreeMap.class)) {
      return TreeMap.class;
    }
    throw new InstantiationException("Cannot instantiate %s for %s", new Object[] { paramClass, this.type });
  }
  
  public Object getInstance()
    throws Exception
  {
    Class localClass3 = getType();
    Class localClass1 = localClass3;
    Class localClass2 = localClass1;
    if (!isInstantiable(localClass1)) {
      localClass2 = getConversion(localClass3);
    }
    if (!isMap(localClass2)) {
      throw new InstantiationException("Invalid map %s for %s", new Object[] { localClass3, this.type });
    }
    return localClass2.newInstance();
  }
  
  public Instance getInstance(Value paramValue)
    throws Exception
  {
    Class localClass2 = paramValue.getType();
    Class localClass1 = localClass2;
    if (!isInstantiable(localClass2)) {
      localClass1 = getConversion(localClass2);
    }
    if (!isMap(localClass1)) {
      throw new InstantiationException("Invalid map %s for %s", new Object[] { localClass1, this.type });
    }
    return new ConversionInstance(this.context, paramValue, localClass1);
  }
  
  public Instance getInstance(InputNode paramInputNode)
    throws Exception
  {
    paramInputNode = getOverride(paramInputNode);
    Class localClass = getType();
    if (paramInputNode != null) {
      return getInstance(paramInputNode);
    }
    paramInputNode = localClass;
    if (!isInstantiable(localClass)) {
      paramInputNode = getConversion(localClass);
    }
    if (!isMap(paramInputNode)) {
      throw new InstantiationException("Invalid map %s for %s", new Object[] { paramInputNode, this.type });
    }
    return this.context.getInstance(paramInputNode);
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\MapFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */