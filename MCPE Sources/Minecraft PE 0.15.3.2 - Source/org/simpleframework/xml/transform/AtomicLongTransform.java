package org.simpleframework.xml.transform;

import java.util.concurrent.atomic.AtomicLong;

class AtomicLongTransform
  implements Transform<AtomicLong>
{
  public AtomicLong read(String paramString)
  {
    return new AtomicLong(Long.valueOf(paramString).longValue());
  }
  
  public String write(AtomicLong paramAtomicLong)
  {
    return paramAtomicLong.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\AtomicLongTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */