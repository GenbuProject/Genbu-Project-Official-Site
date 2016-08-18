package org.simpleframework.xml.strategy;

import org.simpleframework.xml.core.PersistenceException;

public class CycleException
  extends PersistenceException
{
  public CycleException(String paramString, Object... paramVarArgs)
  {
    super(paramString, paramVarArgs);
  }
  
  public CycleException(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    super(paramThrowable, paramString, paramVarArgs);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\CycleException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */