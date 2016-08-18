package org.simpleframework.xml.core;

abstract interface Criteria
  extends Iterable<Object>
{
  public abstract void commit(Object paramObject)
    throws Exception;
  
  public abstract Variable get(Object paramObject)
    throws Exception;
  
  public abstract Variable get(Label paramLabel)
    throws Exception;
  
  public abstract Variable remove(Object paramObject)
    throws Exception;
  
  public abstract Variable resolve(String paramString)
    throws Exception;
  
  public abstract void set(Label paramLabel, Object paramObject)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Criteria.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */