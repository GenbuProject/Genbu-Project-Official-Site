package org.simpleframework.xml.core;

public class PersistenceException
  extends Exception
{
  public PersistenceException(String paramString, Object... paramVarArgs)
  {
    super(String.format(paramString, paramVarArgs));
  }
  
  public PersistenceException(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    super(String.format(paramString, paramVarArgs), paramThrowable);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\PersistenceException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */