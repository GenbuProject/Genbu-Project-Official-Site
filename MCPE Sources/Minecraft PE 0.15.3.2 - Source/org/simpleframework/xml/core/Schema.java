package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;

abstract interface Schema
{
  public abstract Caller getCaller();
  
  public abstract Decorator getDecorator();
  
  public abstract Instantiator getInstantiator();
  
  public abstract Version getRevision();
  
  public abstract Section getSection();
  
  public abstract Label getText();
  
  public abstract Label getVersion();
  
  public abstract boolean isPrimitive();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Schema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */