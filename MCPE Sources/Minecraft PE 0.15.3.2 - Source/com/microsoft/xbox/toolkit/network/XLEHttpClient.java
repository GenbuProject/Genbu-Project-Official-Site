package com.microsoft.xbox.toolkit.network;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;

public class XLEHttpClient
  extends AbstractXLEHttpClient
{
  DefaultHttpClient client;
  
  public XLEHttpClient(ClientConnectionManager paramClientConnectionManager, HttpParams paramHttpParams)
  {
    this.client = new DefaultHttpClient(paramClientConnectionManager, paramHttpParams);
  }
  
  protected HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws ClientProtocolException, IOException
  {
    return this.client.execute(paramHttpUriRequest, new BasicHttpContext());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\network\XLEHttpClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */