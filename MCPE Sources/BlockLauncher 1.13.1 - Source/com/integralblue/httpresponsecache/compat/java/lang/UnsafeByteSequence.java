package com.integralblue.httpresponsecache.compat.java.lang;

import com.integralblue.httpresponsecache.compat.Strings;
import java.nio.charset.Charset;

public class UnsafeByteSequence
{
  private byte[] bytes;
  private int count;
  
  public UnsafeByteSequence(int paramInt)
  {
    this.bytes = new byte[paramInt];
  }
  
  public void rewind()
  {
    this.count = 0;
  }
  
  public int size()
  {
    return this.count;
  }
  
  public byte[] toByteArray()
  {
    if (this.count == this.bytes.length) {
      return this.bytes;
    }
    byte[] arrayOfByte = new byte[this.count];
    System.arraycopy(this.bytes, 0, arrayOfByte, 0, this.count);
    return arrayOfByte;
  }
  
  public String toString(Charset paramCharset)
  {
    return Strings.construct(this.bytes, 0, this.count, paramCharset);
  }
  
  public void write(int paramInt)
  {
    if (this.count == this.bytes.length)
    {
      arrayOfByte = new byte[this.count * 2];
      System.arraycopy(this.bytes, 0, arrayOfByte, 0, this.count);
      this.bytes = arrayOfByte;
    }
    byte[] arrayOfByte = this.bytes;
    int i = this.count;
    this.count = (i + 1);
    arrayOfByte[i] = ((byte)paramInt);
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (this.count + paramInt2 >= this.bytes.length)
    {
      byte[] arrayOfByte = new byte[(this.count + paramInt2) * 2];
      System.arraycopy(this.bytes, 0, arrayOfByte, 0, this.count);
      this.bytes = arrayOfByte;
    }
    System.arraycopy(paramArrayOfByte, paramInt1, this.bytes, this.count, paramInt2);
    this.count += paramInt2;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\integralblue\httpresponsecache\compat\java\lang\UnsafeByteSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */