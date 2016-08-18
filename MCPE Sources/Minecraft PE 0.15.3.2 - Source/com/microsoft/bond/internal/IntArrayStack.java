package com.microsoft.bond.internal;

public class IntArrayStack
{
  private static final int DEFAULT_CAPACITY = 32;
  private int size;
  private int[] values;
  
  public IntArrayStack()
  {
    this(32);
  }
  
  public IntArrayStack(int paramInt)
  {
    this.values = new int[paramInt];
  }
  
  private void ensureExtraCapacity(int paramInt)
  {
    paramInt = this.size + paramInt;
    if (paramInt > this.values.length)
    {
      int[] arrayOfInt = new int[paramInt * 2];
      System.arraycopy(this.values, 0, arrayOfInt, 0, this.values.length);
      this.values = arrayOfInt;
    }
  }
  
  public void clear()
  {
    this.size = 0;
  }
  
  public int get(int paramInt)
  {
    return this.values[paramInt];
  }
  
  public int getSize()
  {
    return this.size;
  }
  
  public int pop()
  {
    this.size -= 1;
    return this.values[this.size];
  }
  
  public void push(int paramInt)
  {
    ensureExtraCapacity(1);
    this.values[this.size] = paramInt;
    this.size += 1;
  }
  
  public void set(int paramInt1, int paramInt2)
  {
    this.values[paramInt1] = paramInt2;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\bond\internal\IntArrayStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */