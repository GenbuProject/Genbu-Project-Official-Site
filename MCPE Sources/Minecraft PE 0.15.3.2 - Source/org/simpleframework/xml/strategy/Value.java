package org.simpleframework.xml.strategy;

public abstract interface Value
{
  public abstract int getLength();
  
  public abstract Class getType();
  
  public abstract Object getValue();
  
  public abstract boolean isReference();
  
  public abstract void setValue(Object paramObject);
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\strategy\Value.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */