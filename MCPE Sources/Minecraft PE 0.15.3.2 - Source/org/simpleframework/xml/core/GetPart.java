package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class GetPart
  implements MethodPart
{
  private final Cache<Annotation> cache = new ConcurrentCache();
  private final Annotation label;
  private final Annotation[] list;
  private final Method method;
  private final String name;
  private final MethodType type;
  
  public GetPart(MethodName paramMethodName, Annotation paramAnnotation, Annotation[] paramArrayOfAnnotation)
  {
    this.method = paramMethodName.getMethod();
    this.name = paramMethodName.getName();
    this.type = paramMethodName.getType();
    this.label = paramAnnotation;
    this.list = paramArrayOfAnnotation;
  }
  
  public Annotation getAnnotation()
  {
    return this.label;
  }
  
  public <T extends Annotation> T getAnnotation(Class<T> paramClass)
  {
    if (this.cache.isEmpty())
    {
      Annotation[] arrayOfAnnotation = this.list;
      int j = arrayOfAnnotation.length;
      int i = 0;
      while (i < j)
      {
        Annotation localAnnotation = arrayOfAnnotation[i];
        Class localClass = localAnnotation.annotationType();
        this.cache.cache(localClass, localAnnotation);
        i += 1;
      }
    }
    return (Annotation)this.cache.fetch(paramClass);
  }
  
  public Class getDeclaringClass()
  {
    return this.method.getDeclaringClass();
  }
  
  public Class getDependent()
  {
    return Reflector.getReturnDependent(this.method);
  }
  
  public Class[] getDependents()
  {
    return Reflector.getReturnDependents(this.method);
  }
  
  public Method getMethod()
  {
    if (!this.method.isAccessible()) {
      this.method.setAccessible(true);
    }
    return this.method;
  }
  
  public MethodType getMethodType()
  {
    return this.type;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Class getType()
  {
    return this.method.getReturnType();
  }
  
  public String toString()
  {
    return this.method.toGenericString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\GetPart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */