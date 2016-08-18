package org.simpleframework.xml.transform;

public class InvalidFormatException
  extends TransformException
{
  public InvalidFormatException(String paramString, Object... paramVarArgs)
  {
    super(String.format(paramString, paramVarArgs), new Object[0]);
  }
  
  public InvalidFormatException(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    super(String.format(paramString, paramVarArgs), new Object[] { paramThrowable });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\InvalidFormatException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */