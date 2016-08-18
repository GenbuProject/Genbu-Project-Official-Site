package com.microsoft.xbox.toolkit.network;

import com.microsoft.xbox.toolkit.XLEException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

public abstract class AbstractXLEHttpClient
{
  protected abstract HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws ClientProtocolException, IOException;
  
  public XLEHttpStatusAndStream getHttpStatusAndStreamInternal(HttpUriRequest paramHttpUriRequest, boolean paramBoolean)
    throws XLEException
  {
    XLEHttpStatusAndStream localXLEHttpStatusAndStream = new XLEHttpStatusAndStream();
    for (;;)
    {
      HttpResponse localHttpResponse;
      try
      {
        localHttpResponse = execute(paramHttpUriRequest);
        if ((localHttpResponse != null) && (localHttpResponse.getStatusLine() != null))
        {
          localXLEHttpStatusAndStream.statusLine = localHttpResponse.getStatusLine().toString();
          localXLEHttpStatusAndStream.statusCode = localHttpResponse.getStatusLine().getStatusCode();
        }
        if ((localHttpResponse != null) && (localHttpResponse.getLastHeader("Location") != null)) {
          localXLEHttpStatusAndStream.redirectUrl = localHttpResponse.getLastHeader("Location").getValue();
        }
        if (localHttpResponse == null) {
          break label241;
        }
        localXLEHttpStatusAndStream.headers = localHttpResponse.getAllHeaders();
      }
      catch (Exception localException)
      {
        Object localObject1;
        paramHttpUriRequest.abort();
        if ((localXLEHttpStatusAndStream == null) || (localXLEHttpStatusAndStream.stream == null)) {
          continue;
        }
        localXLEHttpStatusAndStream.close();
        throw new XLEException(4L, localException);
      }
      if (localObject1 == null) {
        break;
      }
      localXLEHttpStatusAndStream.stream = new ByteArrayInputStream(EntityUtils.toByteArray((HttpEntity)localObject1));
      ((HttpEntity)localObject1).consumeContent();
      localObject1 = localHttpResponse.getFirstHeader("Content-Encoding");
      if ((localObject1 == null) || (!((Header)localObject1).getValue().equalsIgnoreCase("gzip"))) {
        break;
      }
      localXLEHttpStatusAndStream.stream = new GZIPInputStream(localXLEHttpStatusAndStream.stream);
      return localXLEHttpStatusAndStream;
      localObject1 = localHttpResponse.getEntity();
      continue;
      label241:
      if (localHttpResponse == null) {
        Object localObject2 = null;
      }
    }
    return localXLEHttpStatusAndStream;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\network\AbstractXLEHttpClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */