package org.simpleframework.xml.core;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class ScannerFactory
{
  private final Cache<Scanner> cache = new ConcurrentCache();
  private final Support support;
  
  public ScannerFactory(Support paramSupport)
  {
    this.support = paramSupport;
  }
  
  public Scanner getInstance(Class paramClass)
    throws Exception
  {
    Object localObject2 = (Scanner)this.cache.fetch(paramClass);
    Object localObject1 = localObject2;
    Detail localDetail;
    if (localObject2 == null)
    {
      localDetail = this.support.getDetail(paramClass);
      if (!this.support.isPrimitive(paramClass)) {
        break label64;
      }
      localObject1 = new PrimitiveScanner(localDetail);
    }
    for (;;)
    {
      this.cache.cache(paramClass, localObject1);
      return (Scanner)localObject1;
      label64:
      localObject2 = new ObjectScanner(localDetail, this.support);
      localObject1 = localObject2;
      if (((Scanner)localObject2).isPrimitive())
      {
        localObject1 = localObject2;
        if (!this.support.isContainer(paramClass)) {
          localObject1 = new DefaultScanner(localDetail, this.support);
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\ScannerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */