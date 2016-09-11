package com.microsoft.xbox.toolkit.network;

import java.io.InputStream;
import org.apache.http.Header;

public class XLEHttpStatusAndStream
{
  public Header[] headers = new Header[0];
  public String redirectUrl = null;
  public int statusCode = -1;
  public String statusLine = null;
  public InputStream stream = null;
  
  public void close()
  {
    if (this.stream != null) {}
    try
    {
      this.stream.close();
      this.stream = null;
      return;
    }
    catch (Exception localException) {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\network\XLEHttpStatusAndStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */