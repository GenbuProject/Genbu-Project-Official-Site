package org.simpleframework.xml.transform;

import java.math.BigInteger;

class BigIntegerTransform
  implements Transform<BigInteger>
{
  public BigInteger read(String paramString)
  {
    return new BigInteger(paramString);
  }
  
  public String write(BigInteger paramBigInteger)
  {
    return paramBigInteger.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\BigIntegerTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */