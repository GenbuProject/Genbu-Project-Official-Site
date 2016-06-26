package com.mojang.android.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class HTTPRequest
{
  String mCookieData = "";
  HttpRequestBase mHTTPRequest = null;
  String mRequestBody = "";
  String mRequestContentType = "text/plain";
  HTTPResponse mResponse = new HTTPResponse();
  String mURL = "";
  
  private void addBodyToRequest(HttpEntityEnclosingRequestBase paramHttpEntityEnclosingRequestBase)
  {
    if (this.mRequestBody != "") {}
    try
    {
      StringEntity localStringEntity = new StringEntity(this.mRequestBody);
      localStringEntity.setContentType(this.mRequestContentType);
      paramHttpEntityEnclosingRequestBase.setEntity(localStringEntity);
      paramHttpEntityEnclosingRequestBase.addHeader("Content-Type", this.mRequestContentType);
      return;
    }
    catch (UnsupportedEncodingException paramHttpEntityEnclosingRequestBase)
    {
      paramHttpEntityEnclosingRequestBase.printStackTrace();
    }
  }
  
  private void addHeaders()
  {
    this.mHTTPRequest.addHeader("User-Agent", "MCPE/Android");
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 3000);
    this.mHTTPRequest.setParams(localBasicHttpParams);
    if ((this.mCookieData != null) && (this.mCookieData.length() > 0)) {
      this.mHTTPRequest.addHeader("Cookie", this.mCookieData);
    }
    this.mHTTPRequest.addHeader("Charset", "utf-8");
  }
  
  private void createHTTPRequest(String paramString)
  {
    for (;;)
    {
      try
      {
        if (paramString.equals("DELETE"))
        {
          this.mHTTPRequest = new HttpDelete(this.mURL);
          return;
        }
        if (paramString.equals("PUT"))
        {
          paramString = new HttpPut(this.mURL);
          addBodyToRequest(paramString);
          this.mHTTPRequest = paramString;
          continue;
        }
        if (!paramString.equals("GET")) {
          break label95;
        }
      }
      finally {}
      this.mHTTPRequest = new HttpGet(this.mURL);
      continue;
      label95:
      if (!paramString.equals("POST")) {
        break;
      }
      paramString = new HttpPost(this.mURL);
      addBodyToRequest(paramString);
      this.mHTTPRequest = paramString;
    }
    throw new InvalidParameterException("Unknown request method " + paramString);
  }
  
  public void abort()
  {
    try
    {
      this.mResponse.setStatus(2);
      if (this.mHTTPRequest != null) {
        this.mHTTPRequest.abort();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public HTTPResponse send(String paramString)
  {
    createHTTPRequest(paramString);
    addHeaders();
    if (this.mResponse.getStatus() == 2) {
      return this.mResponse;
    }
    try
    {
      paramString = HTTPClientManager.getHTTPClient().execute(this.mHTTPRequest);
      this.mResponse.setResponseCode(paramString.getStatusLine().getStatusCode());
      HttpEntity localHttpEntity = paramString.getEntity();
      this.mResponse.setBody(EntityUtils.toString(localHttpEntity));
      this.mResponse.setStatus(1);
      this.mResponse.setHeaders(paramString.getAllHeaders());
      paramString = this.mResponse;
      return paramString;
    }
    catch (ConnectTimeoutException paramString)
    {
      this.mResponse.setStatus(3);
      this.mHTTPRequest = null;
      return this.mResponse;
    }
    catch (ClientProtocolException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public void setContentType(String paramString)
  {
    this.mRequestContentType = paramString;
  }
  
  public void setCookieData(String paramString)
  {
    this.mCookieData = paramString;
  }
  
  public void setRequestBody(String paramString)
  {
    this.mRequestBody = paramString;
  }
  
  public void setURL(String paramString)
  {
    this.mURL = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\MCPE.jar!\com\mojang\android\net\HTTPRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */