package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

abstract interface Context
{
  public abstract Object getAttribute(Object paramObject);
  
  public abstract Caller getCaller(Class paramClass)
    throws Exception;
  
  public abstract Decorator getDecorator(Class paramClass)
    throws Exception;
  
  public abstract Instance getInstance(Class paramClass);
  
  public abstract Instance getInstance(Value paramValue);
  
  public abstract String getName(Class paramClass)
    throws Exception;
  
  public abstract Value getOverride(Type paramType, InputNode paramInputNode)
    throws Exception;
  
  public abstract String getProperty(String paramString);
  
  public abstract Schema getSchema(Class paramClass)
    throws Exception;
  
  public abstract Session getSession();
  
  public abstract Style getStyle();
  
  public abstract Support getSupport();
  
  public abstract Class getType(Type paramType, Object paramObject);
  
  public abstract Version getVersion(Class paramClass)
    throws Exception;
  
  public abstract boolean isFloat(Class paramClass)
    throws Exception;
  
  public abstract boolean isFloat(Type paramType)
    throws Exception;
  
  public abstract boolean isPrimitive(Class paramClass)
    throws Exception;
  
  public abstract boolean isPrimitive(Type paramType)
    throws Exception;
  
  public abstract boolean isStrict();
  
  public abstract boolean setOverride(Type paramType, Object paramObject, OutputNode paramOutputNode)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\Context.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */