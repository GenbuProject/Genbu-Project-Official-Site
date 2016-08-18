package org.simpleframework.xml.transform;

import java.util.Currency;

class CurrencyTransform
  implements Transform<Currency>
{
  public Currency read(String paramString)
  {
    return Currency.getInstance(paramString);
  }
  
  public String write(Currency paramCurrency)
  {
    return paramCurrency.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\CurrencyTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */