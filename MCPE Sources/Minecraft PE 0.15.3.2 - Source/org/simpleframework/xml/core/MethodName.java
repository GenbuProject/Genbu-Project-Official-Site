package org.simpleframework.xml.core;

import java.lang.reflect.Method;

class MethodName
{
  private Method method;
  private String name;
  private MethodType type;
  
  public MethodName(Method paramMethod, MethodType paramMethodType, String paramString)
  {
    this.method = paramMethod;
    this.type = paramMethodType;
    this.name = paramString;
  }
  
  public Method getMethod()
  {
    return this.method;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public MethodType getType()
  {
    return this.type;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\MethodName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */