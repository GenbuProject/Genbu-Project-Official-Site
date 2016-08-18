package org.simpleframework.xml.strategy;

import java.lang.annotation.Annotation;

public abstract interface Type
{
  public abstract <T extends Annotation> T getAnnotation(Class<T> paramClass);
  
  public abstract Class getType();
  
  public abstract String toString();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\Type.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */