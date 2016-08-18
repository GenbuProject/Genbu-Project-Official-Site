package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;

class OverrideValue
  implements Value
{
  private final Class type;
  private final Value value;
  
  public OverrideValue(Value paramValue, Class paramClass)
  {
    this.value = paramValue;
    this.type = paramClass;
  }
  
  public int getLength()
  {
    return this.value.getLength();
  }
  
  public Class getType()
  {
    return this.type;
  }
  
  public Object getValue()
  {
    return this.value.getValue();
  }
  
  public boolean isReference()
  {
    return this.value.isReference();
  }
  
  public void setValue(Object paramObject)
  {
    this.value.setValue(paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\OverrideValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */