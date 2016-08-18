package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

abstract class ParameterContact<T extends Annotation>
  implements Contact
{
  protected final Constructor factory;
  protected final int index;
  protected final T label;
  protected final Annotation[] labels = paramConstructor.getParameterAnnotations()[paramInt];
  protected final Class owner;
  
  public ParameterContact(T paramT, Constructor paramConstructor, int paramInt)
  {
    this.owner = paramConstructor.getDeclaringClass();
    this.factory = paramConstructor;
    this.index = paramInt;
    this.label = paramT;
  }
  
  public Object get(Object paramObject)
  {
    return null;
  }
  
  public Annotation getAnnotation()
  {
    return this.label;
  }
  
  public <A extends Annotation> A getAnnotation(Class<A> paramClass)
  {
    Annotation[] arrayOfAnnotation = this.labels;
    int j = arrayOfAnnotation.length;
    int i = 0;
    while (i < j)
    {
      Annotation localAnnotation = arrayOfAnnotation[i];
      if (localAnnotation.annotationType().equals(paramClass)) {
        return localAnnotation;
      }
      i += 1;
    }
    return null;
  }
  
  public Class getDeclaringClass()
  {
    return this.owner;
  }
  
  public Class getDependent()
  {
    return Reflector.getParameterDependent(this.factory, this.index);
  }
  
  public Class[] getDependents()
  {
    return Reflector.getParameterDependents(this.factory, this.index);
  }
  
  public abstract String getName();
  
  public Class getType()
  {
    return this.factory.getParameterTypes()[this.index];
  }
  
  public boolean isReadOnly()
  {
    return false;
  }
  
  public void set(Object paramObject1, Object paramObject2) {}
  
  public String toString()
  {
    return String.format("parameter %s of constructor %s", new Object[] { Integer.valueOf(this.index), this.factory });
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ParameterContact.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */