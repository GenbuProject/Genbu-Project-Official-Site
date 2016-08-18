package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

class MethodContact
  implements Contact
{
  private MethodPart get;
  private Class item;
  private Class[] items;
  private Annotation label;
  private String name;
  private Class owner;
  private MethodPart set;
  private Class type;
  
  public MethodContact(MethodPart paramMethodPart)
  {
    this(paramMethodPart, null);
  }
  
  public MethodContact(MethodPart paramMethodPart1, MethodPart paramMethodPart2)
  {
    this.owner = paramMethodPart1.getDeclaringClass();
    this.label = paramMethodPart1.getAnnotation();
    this.items = paramMethodPart1.getDependents();
    this.item = paramMethodPart1.getDependent();
    this.type = paramMethodPart1.getType();
    this.name = paramMethodPart1.getName();
    this.set = paramMethodPart2;
    this.get = paramMethodPart1;
  }
  
  public Object get(Object paramObject)
    throws Exception
  {
    return this.get.getMethod().invoke(paramObject, new Object[0]);
  }
  
  public Annotation getAnnotation()
  {
    return this.label;
  }
  
  public <T extends Annotation> T getAnnotation(Class<T> paramClass)
  {
    Annotation localAnnotation2 = this.get.getAnnotation(paramClass);
    Annotation localAnnotation1;
    if (paramClass == this.label.annotationType()) {
      localAnnotation1 = this.label;
    }
    do
    {
      do
      {
        return localAnnotation1;
        localAnnotation1 = localAnnotation2;
      } while (localAnnotation2 != null);
      localAnnotation1 = localAnnotation2;
    } while (this.set == null);
    return this.set.getAnnotation(paramClass);
  }
  
  public Class getDeclaringClass()
  {
    return this.owner;
  }
  
  public Class getDependent()
  {
    return this.item;
  }
  
  public Class[] getDependents()
  {
    return this.items;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public MethodPart getRead()
  {
    return this.get;
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public MethodPart getWrite()
  {
    return this.set;
  }
  
  public boolean isReadOnly()
  {
    return this.set == null;
  }
  
  public void set(Object paramObject1, Object paramObject2)
    throws Exception
  {
    Class localClass = this.get.getMethod().getDeclaringClass();
    if (this.set == null) {
      throw new MethodException("Property '%s' is read only in %s", new Object[] { this.name, localClass });
    }
    this.set.getMethod().invoke(paramObject1, new Object[] { paramObject2 });
  }
  
  public String toString()
  {
    return String.format("method '%s'", new Object[] { this.name });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\MethodContact.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */