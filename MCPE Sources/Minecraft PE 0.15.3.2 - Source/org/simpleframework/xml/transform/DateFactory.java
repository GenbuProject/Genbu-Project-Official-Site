package org.simpleframework.xml.transform;

import java.lang.reflect.Constructor;
import java.util.Date;

class DateFactory<T extends Date>
{
  private final Constructor<T> factory;
  
  public DateFactory(Class<T> paramClass)
    throws Exception
  {
    this(paramClass, new Class[] { Long.TYPE });
  }
  
  public DateFactory(Class<T> paramClass, Class... paramVarArgs)
    throws Exception
  {
    this.factory = paramClass.getDeclaredConstructor(paramVarArgs);
  }
  
  public T getInstance(Object... paramVarArgs)
    throws Exception
  {
    return (Date)this.factory.newInstance(paramVarArgs);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\DateFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */