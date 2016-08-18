package org.simpleframework.xml.transform;

import java.math.BigDecimal;

class BigDecimalTransform
  implements Transform<BigDecimal>
{
  public BigDecimal read(String paramString)
  {
    return new BigDecimal(paramString);
  }
  
  public String write(BigDecimal paramBigDecimal)
  {
    return paramBigDecimal.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\BigDecimalTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */