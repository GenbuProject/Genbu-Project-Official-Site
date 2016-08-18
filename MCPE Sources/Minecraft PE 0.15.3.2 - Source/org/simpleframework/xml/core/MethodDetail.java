package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class MethodDetail
{
  private final Annotation[] list;
  private final Method method;
  private final String name;
  
  public MethodDetail(Method paramMethod)
  {
    this.list = paramMethod.getDeclaredAnnotations();
    this.name = paramMethod.getName();
    this.method = paramMethod;
  }
  
  public Annotation[] getAnnotations()
  {
    return this.list;
  }
  
  public Method getMethod()
  {
    return this.method;
  }
  
  public String getName()
  {
    return this.name;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\MethodDetail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */