package org.simpleframework.xml.core;

abstract interface Creator
{
  public abstract Object getInstance()
    throws Exception;
  
  public abstract Object getInstance(Criteria paramCriteria)
    throws Exception;
  
  public abstract double getScore(Criteria paramCriteria)
    throws Exception;
  
  public abstract Signature getSignature()
    throws Exception;
  
  public abstract Class getType()
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Creator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */