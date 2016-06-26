package com.mojang.android.net;

import java.io.PrintStream;
import org.apache.http.Header;

public class HTTPResponse
{
  public static final int STATUS_FAIL = 0;
  public static final int STATUS_SUCCESS = 1;
  private String body;
  private Header[] headers;
  private int responseCode;
  private int status;
  
  public HTTPResponse(int paramInt1, int paramInt2, String paramString, Header[] paramArrayOfHeader)
  {
    this.status = paramInt1;
    this.responseCode = paramInt2;
    this.body = paramString;
    this.headers = paramArrayOfHeader;
  }
  
  public String getBody()
  {
    if (HTTPRequest.debugNet) {
      System.out.println("get response " + this.body);
    }
    return this.body;
  }
  
  public Header[] getHeaders()
  {
    if (HTTPRequest.debugNet) {
      System.out.println("get headers");
    }
    return this.headers;
  }
  
  public int getResponseCode()
  {
    if (HTTPRequest.debugNet) {
      System.out.println("get response code");
    }
    return this.responseCode;
  }
  
  public int getStatus()
  {
    if (HTTPRequest.debugNet) {
      System.out.println("get status");
    }
    return this.status;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\mojang\android\net\HTTPResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */