package com.microsoft.xbox.toolkit.ui;

import java.net.URI;

public class XLEURIArg
{
  private final int errorResourceId;
  private final int loadingResourceId;
  private final URI uri;
  
  public XLEURIArg(URI paramURI)
  {
    this(paramURI, -1, -1);
  }
  
  public XLEURIArg(URI paramURI, int paramInt1, int paramInt2)
  {
    this.uri = paramURI;
    this.loadingResourceId = paramInt1;
    this.errorResourceId = paramInt2;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    if (paramObject == null) {}
    do
    {
      boolean bool1 = false;
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (paramObject == this);
        if (!(paramObject instanceof XLEURIArg)) {
          break;
        }
        paramObject = (XLEURIArg)paramObject;
        if ((this.loadingResourceId != ((XLEURIArg)paramObject).loadingResourceId) || (this.errorResourceId != ((XLEURIArg)paramObject).errorResourceId)) {
          break;
        }
        bool1 = bool2;
      } while (this.uri == ((XLEURIArg)paramObject).uri);
    } while ((this.uri == null) || (!this.uri.equals(((XLEURIArg)paramObject).uri)));
    return true;
  }
  
  public int getErrorResourceId()
  {
    return this.errorResourceId;
  }
  
  public int getLoadingResourceId()
  {
    return this.loadingResourceId;
  }
  
  public TextureBindingOption getTextureBindingOption()
  {
    return new TextureBindingOption(-1, -1, this.loadingResourceId, this.errorResourceId, false);
  }
  
  public URI getUri()
  {
    return this.uri;
  }
  
  public int hashCode()
  {
    int j = (this.loadingResourceId + 13) * 17 + this.errorResourceId;
    int i = j;
    if (this.uri != null) {
      i = j * 23 + this.uri.hashCode();
    }
    return i;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\XLEURIArg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */