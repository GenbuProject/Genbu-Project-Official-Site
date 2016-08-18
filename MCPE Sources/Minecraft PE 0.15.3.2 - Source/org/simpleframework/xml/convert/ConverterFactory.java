package org.simpleframework.xml.convert;

import java.lang.reflect.Constructor;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class ConverterFactory
{
  private final Cache<Converter> cache = new ConcurrentCache();
  
  private Constructor getConstructor(Class paramClass)
    throws Exception
  {
    paramClass = paramClass.getDeclaredConstructor(new Class[0]);
    if (!paramClass.isAccessible()) {
      paramClass.setAccessible(true);
    }
    return paramClass;
  }
  
  private Converter getConverter(Class paramClass)
    throws Exception
  {
    Constructor localConstructor = getConstructor(paramClass);
    if (localConstructor == null) {
      throw new ConvertException("No default constructor for %s", new Object[] { paramClass });
    }
    return getConverter(paramClass, localConstructor);
  }
  
  private Converter getConverter(Class paramClass, Constructor paramConstructor)
    throws Exception
  {
    paramConstructor = (Converter)paramConstructor.newInstance(new Object[0]);
    if (paramConstructor != null) {
      this.cache.cache(paramClass, paramConstructor);
    }
    return paramConstructor;
  }
  
  public Converter getInstance(Class paramClass)
    throws Exception
  {
    Converter localConverter2 = (Converter)this.cache.fetch(paramClass);
    Converter localConverter1 = localConverter2;
    if (localConverter2 == null) {
      localConverter1 = getConverter(paramClass);
    }
    return localConverter1;
  }
  
  public Converter getInstance(Convert paramConvert)
    throws Exception
  {
    paramConvert = paramConvert.value();
    if (paramConvert.isInterface()) {
      throw new ConvertException("Can not instantiate %s", new Object[] { paramConvert });
    }
    return getInstance(paramConvert);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\convert\ConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */