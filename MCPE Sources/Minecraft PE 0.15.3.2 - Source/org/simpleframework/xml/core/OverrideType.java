package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.strategy.Type;

class OverrideType
  implements Type
{
  private final Class override;
  private final Type type;
  
  public OverrideType(Type paramType, Class paramClass)
  {
    this.override = paramClass;
    this.type = paramType;
  }
  
  public <T extends Annotation> T getAnnotation(Class<T> paramClass)
  {
    return this.type.getAnnotation(paramClass);
  }
  
  public Class getType()
  {
    return this.override;
  }
  
  public String toString()
  {
    return this.type.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\OverrideType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */