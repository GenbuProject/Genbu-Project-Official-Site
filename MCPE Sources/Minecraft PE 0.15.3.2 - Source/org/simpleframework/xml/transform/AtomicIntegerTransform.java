package org.simpleframework.xml.transform;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicIntegerTransform
  implements Transform<AtomicInteger>
{
  public AtomicInteger read(String paramString)
  {
    return new AtomicInteger(Integer.valueOf(paramString).intValue());
  }
  
  public String write(AtomicInteger paramAtomicInteger)
  {
    return paramAtomicInteger.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\AtomicIntegerTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */