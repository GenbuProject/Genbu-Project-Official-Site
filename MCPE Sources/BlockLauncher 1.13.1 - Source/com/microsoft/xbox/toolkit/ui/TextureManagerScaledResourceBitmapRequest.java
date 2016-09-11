package com.microsoft.xbox.toolkit.ui;

public class TextureManagerScaledResourceBitmapRequest
{
  public final TextureBindingOption bindingOption;
  public final int resourceId;
  
  public TextureManagerScaledResourceBitmapRequest(int paramInt)
  {
    this(paramInt, new TextureBindingOption());
  }
  
  public TextureManagerScaledResourceBitmapRequest(int paramInt, TextureBindingOption paramTextureBindingOption)
  {
    this.resourceId = paramInt;
    this.bindingOption = paramTextureBindingOption;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    if (this == paramObject) {}
    do
    {
      boolean bool1 = true;
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (!(paramObject instanceof TextureManagerScaledResourceBitmapRequest));
        paramObject = (TextureManagerScaledResourceBitmapRequest)paramObject;
        bool1 = bool2;
      } while (this.resourceId != ((TextureManagerScaledResourceBitmapRequest)paramObject).resourceId);
    } while (this.bindingOption.equals(((TextureManagerScaledResourceBitmapRequest)paramObject).bindingOption));
    return false;
  }
  
  public int hashCode()
  {
    return this.resourceId;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\TextureManagerScaledResourceBitmapRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */