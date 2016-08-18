package org.simpleframework.xml.core;

import java.lang.reflect.Modifier;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

abstract class Factory
{
  protected Context context;
  protected Class override;
  protected Support support;
  protected Type type;
  
  protected Factory(Context paramContext, Type paramType)
  {
    this(paramContext, paramType, null);
  }
  
  protected Factory(Context paramContext, Type paramType, Class paramClass)
  {
    this.support = paramContext.getSupport();
    this.override = paramClass;
    this.context = paramContext;
    this.type = paramType;
  }
  
  private Type getPrimitive(Type paramType, Class paramClass)
    throws Exception
  {
    Object localObject = this.support;
    Class localClass = Support.getPrimitive(paramClass);
    localObject = paramType;
    if (localClass != paramClass) {
      localObject = new OverrideType(paramType, localClass);
    }
    return (Type)localObject;
  }
  
  public static boolean isCompatible(Class paramClass1, Class paramClass2)
  {
    Class localClass = paramClass1;
    if (paramClass1.isArray()) {
      localClass = paramClass1.getComponentType();
    }
    return localClass.isAssignableFrom(paramClass2);
  }
  
  public static boolean isInstantiable(Class paramClass)
  {
    int i = paramClass.getModifiers();
    if (Modifier.isAbstract(i)) {}
    while (Modifier.isInterface(i)) {
      return false;
    }
    return true;
  }
  
  public Value getConversion(InputNode paramInputNode)
    throws Exception
  {
    Value localValue = this.context.getOverride(this.type, paramInputNode);
    paramInputNode = localValue;
    if (localValue != null)
    {
      paramInputNode = localValue;
      if (this.override != null)
      {
        Class localClass = localValue.getType();
        paramInputNode = localValue;
        if (!isCompatible(this.override, localClass)) {
          paramInputNode = new OverrideValue(localValue, this.override);
        }
      }
    }
    return paramInputNode;
  }
  
  public Object getInstance()
    throws Exception
  {
    Class localClass = getType();
    if (!isInstantiable(localClass)) {
      throw new InstantiationException("Type %s can not be instantiated", new Object[] { localClass });
    }
    return localClass.newInstance();
  }
  
  protected Value getOverride(InputNode paramInputNode)
    throws Exception
  {
    Value localValue = getConversion(paramInputNode);
    if (localValue != null)
    {
      paramInputNode = paramInputNode.getPosition();
      Class localClass = localValue.getType();
      if (!isCompatible(getType(), localClass)) {
        throw new InstantiationException("Incompatible %s for %s at %s", new Object[] { localClass, this.type, paramInputNode });
      }
    }
    return localValue;
  }
  
  public Class getType()
  {
    if (this.override != null) {
      return this.override;
    }
    return this.type.getType();
  }
  
  public boolean setOverride(Type paramType, Object paramObject, OutputNode paramOutputNode)
    throws Exception
  {
    Class localClass = paramType.getType();
    Type localType = paramType;
    if (localClass.isPrimitive()) {
      localType = getPrimitive(paramType, localClass);
    }
    return this.context.setOverride(localType, paramObject, paramOutputNode);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */