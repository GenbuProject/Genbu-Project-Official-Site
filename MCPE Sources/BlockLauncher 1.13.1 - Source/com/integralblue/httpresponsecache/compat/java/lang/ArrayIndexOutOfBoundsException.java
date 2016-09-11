package com.integralblue.httpresponsecache.compat.java.lang;

public class ArrayIndexOutOfBoundsException
  extends java.lang.ArrayIndexOutOfBoundsException
{
  private static final long serialVersionUID = -5116101128118950844L;
  
  public ArrayIndexOutOfBoundsException() {}
  
  public ArrayIndexOutOfBoundsException(int paramInt)
  {
    super("index=" + paramInt);
  }
  
  public ArrayIndexOutOfBoundsException(int paramInt1, int paramInt2)
  {
    super("length=" + paramInt1 + "; index=" + paramInt2);
  }
  
  public ArrayIndexOutOfBoundsException(int paramInt1, int paramInt2, int paramInt3)
  {
    super("length=" + paramInt1 + "; regionStart=" + paramInt2 + "; regionLength=" + paramInt3);
  }
  
  public ArrayIndexOutOfBoundsException(String paramString)
  {
    super(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\java\lang\ArrayIndexOutOfBoundsException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */