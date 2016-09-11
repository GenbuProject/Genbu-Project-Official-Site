package com.microsoft.xbox.toolkit;

public class XLEMemoryCacheEntry<V>
{
  private int byteCount;
  private V data;
  
  public XLEMemoryCacheEntry(V paramV, int paramInt)
  {
    if (paramV == null) {
      throw new IllegalArgumentException("data");
    }
    if (paramInt <= 0) {
      throw new IllegalArgumentException("byteCount");
    }
    this.data = paramV;
    this.byteCount = paramInt;
  }
  
  public int getByteCount()
  {
    return this.byteCount;
  }
  
  public V getValue()
  {
    return (V)this.data;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\XLEMemoryCacheEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */