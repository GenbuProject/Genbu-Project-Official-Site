package org.simpleframework.xml.util;

public abstract interface Cache<T>
{
  public abstract void cache(Object paramObject, T paramT);
  
  public abstract boolean contains(Object paramObject);
  
  public abstract T fetch(Object paramObject);
  
  public abstract boolean isEmpty();
  
  public abstract T take(Object paramObject);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\util\Cache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */