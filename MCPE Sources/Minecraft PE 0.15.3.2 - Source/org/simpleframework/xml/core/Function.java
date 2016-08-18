package org.simpleframework.xml.core;

import java.lang.reflect.Method;

class Function
{
  private final boolean contextual;
  private final Method method;
  
  public Function(Method paramMethod)
  {
    this(paramMethod, false);
  }
  
  public Function(Method paramMethod, boolean paramBoolean)
  {
    this.contextual = paramBoolean;
    this.method = paramMethod;
  }
  
  public Object call(Context paramContext, Object paramObject)
    throws Exception
  {
    if (paramObject != null)
    {
      paramContext = paramContext.getSession().getMap();
      if (this.contextual) {
        return this.method.invoke(paramObject, new Object[] { paramContext });
      }
      return this.method.invoke(paramObject, new Object[0]);
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Function.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */