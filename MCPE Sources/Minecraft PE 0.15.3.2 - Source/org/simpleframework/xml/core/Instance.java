package org.simpleframework.xml.core;

abstract interface Instance
{
  public abstract Object getInstance()
    throws Exception;
  
  public abstract Class getType();
  
  public abstract boolean isReference();
  
  public abstract Object setInstance(Object paramObject)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Instance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */