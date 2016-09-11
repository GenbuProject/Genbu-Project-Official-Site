package com.microsoft.xbox.service.network.managers;

import java.net.URI;
import org.apache.http.client.methods.HttpPost;

public class HttpDeleteWithRequestBody
  extends HttpPost
{
  public HttpDeleteWithRequestBody(URI paramURI)
  {
    super(paramURI);
  }
  
  public String getMethod()
  {
    return "DELETE";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\network\managers\HttpDeleteWithRequestBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */