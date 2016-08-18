package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class FieldContact
  implements Contact
{
  private final Cache<Annotation> cache = new ConcurrentCache();
  private final Field field;
  private final Annotation label;
  private final Annotation[] list;
  private final int modifier;
  private final String name;
  
  public FieldContact(Field paramField, Annotation paramAnnotation, Annotation[] paramArrayOfAnnotation)
  {
    this.modifier = paramField.getModifiers();
    this.name = paramField.getName();
    this.label = paramAnnotation;
    this.field = paramField;
    this.list = paramArrayOfAnnotation;
  }
  
  private <T extends Annotation> T getCache(Class<T> paramClass)
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
  
  public Object get(Object paramObject)
    throws Exception
  {
    return this.field.get(paramObject);
  }
  
  public Annotation getAnnotation()
  {
    return this.label;
  }
  
  public <T extends Annotation> T getAnnotation(Class<T> paramClass)
  {
    if (paramClass == this.label.annotationType()) {
      return this.label;
    }
    return getCache(paramClass);
  }
  
  public Class getDeclaringClass()
  {
    return this.field.getDeclaringClass();
  }
  
  public Class getDependent()
  {
    return Reflector.getDependent(this.field);
  }
  
  public Class[] getDependents()
  {
    return Reflector.getDependents(this.field);
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public Class getType()
  {
    return this.field.getType();
  }
  
  public boolean isFinal()
  {
    return Modifier.isFinal(this.modifier);
  }
  
  public boolean isReadOnly()
  {
    return (!isStatic()) && (isFinal());
  }
  
  public boolean isStatic()
  {
    return Modifier.isStatic(this.modifier);
  }
  
  public void set(Object paramObject1, Object paramObject2)
    throws Exception
  {
    if (!isFinal()) {
      this.field.set(paramObject1, paramObject2);
    }
  }
  
  public String toString()
  {
    return String.format("field '%s' %s", new Object[] { getName(), this.field.toString() });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\FieldContact.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */