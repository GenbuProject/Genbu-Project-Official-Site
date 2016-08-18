package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

abstract interface MethodPart
{
  public abstract Annotation getAnnotation();
  
  public abstract <T extends Annotation> T getAnnotation(Class<T> paramClass);
  
  public abstract Class getDeclaringClass();
  
  public abstract Class getDependent();
  
  public abstract Class[] getDependents();
  
  public abstract Method getMethod();
  
  public abstract MethodType getMethodType();
  
  public abstract String getName();
  
  public abstract Class getType();
  
  public abstract String toString();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\MethodPart.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */