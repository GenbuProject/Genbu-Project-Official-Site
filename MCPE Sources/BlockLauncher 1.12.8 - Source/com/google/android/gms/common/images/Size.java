package com.google.android.gms.common.images;

public final class Size
{
  private final int zzoG;
  private final int zzoH;
  
  public Size(int paramInt1, int paramInt2)
  {
    this.zzoG = paramInt1;
    this.zzoH = paramInt2;
  }
  
  public static Size parseSize(String paramString)
    throws NumberFormatException
  {
    if (paramString == null) {
      throw new IllegalArgumentException("string must not be null");
    }
    int j = paramString.indexOf('*');
    int i = j;
    if (j < 0) {
      i = paramString.indexOf('x');
    }
    if (i < 0) {
      throw zzcC(paramString);
    }
    try
    {
      Size localSize = new Size(Integer.parseInt(paramString.substring(0, i)), Integer.parseInt(paramString.substring(i + 1)));
      return localSize;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw zzcC(paramString);
    }
  }
  
  private static NumberFormatException zzcC(String paramString)
  {
    throw new NumberFormatException("Invalid Size: \"" + paramString + "\"");
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == null) {}
    do
    {
      return false;
      if (this == paramObject) {
        return true;
      }
    } while (!(paramObject instanceof Size));
    paramObject = (Size)paramObject;
    if ((this.zzoG == ((Size)paramObject).zzoG) && (this.zzoH == ((Size)paramObject).zzoH)) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public int getHeight()
  {
    return this.zzoH;
  }
  
  public int getWidth()
  {
    return this.zzoG;
  }
  
  public int hashCode()
  {
    return this.zzoH ^ (this.zzoG << 16 | this.zzoG >>> 16);
  }
  
  public String toString()
  {
    return this.zzoG + "x" + this.zzoH;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\images\Size.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */