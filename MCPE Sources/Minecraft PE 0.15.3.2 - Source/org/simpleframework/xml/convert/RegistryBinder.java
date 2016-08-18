package org.simpleframework.xml.convert;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class RegistryBinder
{
  private final Cache<Class> cache = new ConcurrentCache();
  private final ConverterFactory factory = new ConverterFactory();
  
  private Converter create(Class paramClass)
    throws Exception
  {
    return this.factory.getInstance(paramClass);
  }
  
  public void bind(Class paramClass1, Class paramClass2)
    throws Exception
  {
    this.cache.cache(paramClass1, paramClass2);
  }
  
  public Converter lookup(Class paramClass)
    throws Exception
  {
    paramClass = (Class)this.cache.fetch(paramClass);
    if (paramClass != null) {
      return create(paramClass);
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\convert\RegistryBinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */