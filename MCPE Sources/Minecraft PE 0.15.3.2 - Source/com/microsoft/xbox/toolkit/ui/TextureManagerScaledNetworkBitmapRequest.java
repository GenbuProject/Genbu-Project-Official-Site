package com.microsoft.xbox.toolkit.ui;

import com.microsoft.xbox.toolkit.XLEFileCacheItemKey;

public class TextureManagerScaledNetworkBitmapRequest
  implements XLEFileCacheItemKey
{
  public final TextureBindingOption bindingOption;
  public final String url;
  
  public TextureManagerScaledNetworkBitmapRequest(String paramString)
  {
    this(paramString, new TextureBindingOption());
  }
  
  public TextureManagerScaledNetworkBitmapRequest(String paramString, TextureBindingOption paramTextureBindingOption)
  {
    this.url = paramString;
    this.bindingOption = paramTextureBindingOption;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof TextureManagerScaledNetworkBitmapRequest)) {
        return false;
      }
      paramObject = (TextureManagerScaledNetworkBitmapRequest)paramObject;
    } while ((this.url.equals(((TextureManagerScaledNetworkBitmapRequest)paramObject).url)) && (this.bindingOption.equals(((TextureManagerScaledNetworkBitmapRequest)paramObject).bindingOption)));
    return false;
  }
  
  public String getKeyString()
  {
    return this.url;
  }
  
  public int hashCode()
  {
    if (this.url == null) {
      return 0;
    }
    return this.url.hashCode();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ui\TextureManagerScaledNetworkBitmapRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */