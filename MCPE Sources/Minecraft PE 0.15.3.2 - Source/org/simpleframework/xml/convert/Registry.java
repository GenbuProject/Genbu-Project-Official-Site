package org.simpleframework.xml.convert;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

public class Registry
{
  private final RegistryBinder binder = new RegistryBinder();
  private final Cache<Converter> cache = new ConcurrentCache();
  
  private Converter create(Class paramClass)
    throws Exception
  {
    Converter localConverter = this.binder.lookup(paramClass);
    if (localConverter != null) {
      this.cache.cache(paramClass, localConverter);
    }
    return localConverter;
  }
  
  public Registry bind(Class paramClass1, Class paramClass2)
    throws Exception
  {
    if (paramClass1 != null) {
      this.binder.bind(paramClass1, paramClass2);
    }
    return this;
  }
  
  public Registry bind(Class paramClass, Converter paramConverter)
    throws Exception
  {
    if (paramClass != null) {
      this.cache.cache(paramClass, paramConverter);
    }
    return this;
  }
  
  public Converter lookup(Class paramClass)
    throws Exception
  {
    Converter localConverter2 = (Converter)this.cache.fetch(paramClass);
    Converter localConverter1 = localConverter2;
    if (localConverter2 == null) {
      localConverter1 = create(paramClass);
    }
    return localConverter1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\convert\Registry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */