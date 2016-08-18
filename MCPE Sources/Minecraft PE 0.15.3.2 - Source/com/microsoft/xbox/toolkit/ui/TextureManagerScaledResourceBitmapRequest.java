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
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof TextureManagerScaledResourceBitmapRequest)) {
        return false;
      }
      paramObject = (TextureManagerScaledResourceBitmapRequest)paramObject;
    } while ((this.resourceId == ((TextureManagerScaledResourceBitmapRequest)paramObject).resourceId) && (this.bindingOption.equals(((TextureManagerScaledResourceBitmapRequest)paramObject).bindingOption)));
    return false;
  }
  
  public int hashCode()
  {
    return this.resourceId;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\TextureManagerScaledResourceBitmapRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */