package org.simpleframework.xml.core;

import java.util.List;

abstract interface Instantiator
{
  public abstract List<Creator> getCreators();
  
  public abstract Object getInstance()
    throws Exception;
  
  public abstract Object getInstance(Criteria paramCriteria)
    throws Exception;
  
  public abstract Parameter getParameter(String paramString);
  
  public abstract List<Parameter> getParameters();
  
  public abstract boolean isDefault();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Instantiator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */