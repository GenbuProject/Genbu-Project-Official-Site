package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.strategy.Type;

class ClassType
  implements Type
{
  private final Class type;
  
  public ClassType(Class paramClass)
  {
    this.type = paramClass;
  }
  
  public <T extends Annotation> T getAnnotation(Class<T> paramClass)
  {
    return null;
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public String toString()
  {
    return this.type.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ClassType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */