package org.simpleframework.xml.core;

import java.lang.reflect.Constructor;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class InstanceFactory
{
  private final Cache<Constructor> cache = new ConcurrentCache();
  
  public Instance getInstance(Class paramClass)
  {
    return new ClassInstance(paramClass);
  }
  
  public Instance getInstance(Value paramValue)
  {
    return new ValueInstance(paramValue);
  }
  
  protected Object getObject(Class paramClass)
    throws Exception
  {
    Constructor localConstructor2 = (Constructor)this.cache.fetch(paramClass);
    Constructor localConstructor1 = localConstructor2;
    if (localConstructor2 == null)
    {
      localConstructor1 = paramClass.getDeclaredConstructor(new Class[0]);
      if (!localConstructor1.isAccessible()) {
        localConstructor1.setAccessible(true);
      }
      this.cache.cache(paramClass, localConstructor1);
    }
    return localConstructor1.newInstance(new Object[0]);
  }
  
  private class ClassInstance
    implements Instance
  {
    private Class type;
    private Object value;
    
    public ClassInstance(Class paramClass)
    {
      this.type = paramClass;
    }
    
    public Object getInstance()
      throws Exception
    {
      if (this.value == null) {
        this.value = InstanceFactory.this.getObject(this.type);
      }
      return this.value;
    }
    
    public Class getType()
    {
      return this.type;
    }
    
    public boolean isReference()
    {
      return false;
    }
    
    public Object setInstance(Object paramObject)
      throws Exception
    {
      this.value = paramObject;
      return paramObject;
    }
  }
  
  private class ValueInstance
    implements Instance
  {
    private final Class type;
    private final Value value;
    
    public ValueInstance(Value paramValue)
    {
      this.type = paramValue.getType();
      this.value = paramValue;
    }
    
    public Object getInstance()
      throws Exception
    {
      Object localObject1;
      if (this.value.isReference()) {
        localObject1 = this.value.getValue();
      }
      Object localObject2;
      do
      {
        return localObject1;
        localObject2 = InstanceFactory.this.getObject(this.type);
        localObject1 = localObject2;
      } while (this.value == null);
      this.value.setValue(localObject2);
      return localObject2;
    }
    
    public Class getType()
    {
      return this.type;
    }
    
    public boolean isReference()
    {
      return this.value.isReference();
    }
    
    public Object setInstance(Object paramObject)
    {
      if (this.value != null) {
        this.value.setValue(paramObject);
      }
      return paramObject;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\InstanceFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */