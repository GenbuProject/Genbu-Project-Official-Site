package org.simpleframework.xml.convert;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;

class ConverterScanner
{
  private final ScannerBuilder builder = new ScannerBuilder();
  private final ConverterFactory factory = new ConverterFactory();
  
  private <T extends Annotation> T getAnnotation(Class<?> paramClass, Class<T> paramClass1)
  {
    return this.builder.build(paramClass).scan(paramClass1);
  }
  
  private Convert getConvert(Class paramClass)
    throws Exception
  {
    Convert localConvert = (Convert)getAnnotation(paramClass, Convert.class);
    if ((localConvert != null) && ((Root)getAnnotation(paramClass, Root.class) == null)) {
      throw new ConvertException("Root annotation required for %s", new Object[] { paramClass });
    }
    return localConvert;
  }
  
  private Convert getConvert(Type paramType)
    throws Exception
  {
    Convert localConvert = (Convert)paramType.getAnnotation(Convert.class);
    if ((localConvert != null) && ((Element)paramType.getAnnotation(Element.class) == null)) {
      throw new ConvertException("Element annotation required for %s", new Object[] { paramType });
    }
    return localConvert;
  }
  
  private Convert getConvert(Type paramType, Class paramClass)
    throws Exception
  {
    Convert localConvert = getConvert(paramType);
    paramType = localConvert;
    if (localConvert == null) {
      paramType = getConvert(paramClass);
    }
    return paramType;
  }
  
  private Class getType(Type paramType, Object paramObject)
  {
    paramType = paramType.getType();
    if (paramObject != null) {
      paramType = paramObject.getClass();
    }
    return paramType;
  }
  
  private Class getType(Type paramType, Value paramValue)
  {
    paramType = paramType.getType();
    if (paramValue != null) {
      paramType = paramValue.getType();
    }
    return paramType;
  }
  
  public Converter getConverter(Type paramType, Object paramObject)
    throws Exception
  {
    paramType = getConvert(paramType, getType(paramType, paramObject));
    if (paramType != null) {
      return this.factory.getInstance(paramType);
    }
    return null;
  }
  
  public Converter getConverter(Type paramType, Value paramValue)
    throws Exception
  {
    paramType = getConvert(paramType, getType(paramType, paramValue));
    if (paramType != null) {
      return this.factory.getInstance(paramType);
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\convert\ConverterScanner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */