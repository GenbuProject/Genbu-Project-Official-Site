package com.microsoft.xbox.idp.util;

import java.io.InputStream;

public class HttpCall
{
  private final long id;
  
  public HttpCall(String paramString1, String paramString2, String paramString3)
  {
    this.id = create(paramString1, paramString2, paramString3, true);
  }
  
  public HttpCall(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    this.id = create(paramString1, paramString2, paramString3, paramBoolean);
  }
  
  private static native long create(String paramString1, String paramString2, String paramString3, boolean paramBoolean);
  
  private static native void delete(long paramLong);
  
  protected void finalize()
    throws Throwable
  {
    delete(this.id);
    super.finalize();
  }
  
  public native void getResponseAsync(Callback paramCallback);
  
  public native void getResponseAsync(CallbackWithHeaders paramCallbackWithHeaders);
  
  public native void setContentTypeHeaderValue(String paramString);
  
  public native void setCustomHeader(String paramString1, String paramString2);
  
  public native void setRequestBody(String paramString);
  
  public native void setRequestBody(byte[] paramArrayOfByte);
  
  public native void setRetryAllowed(boolean paramBoolean);
  
  public native void setXboxContractVersionHeaderValue(String paramString);
  
  public static abstract interface Callback
  {
    public abstract void processHttpError(int paramInt1, int paramInt2, String paramString);
    
    public abstract void processResponse(InputStream paramInputStream)
      throws Exception;
  }
  
  public static abstract interface CallbackWithHeaders
  {
    public abstract void processHttpError(int paramInt1, int paramInt2, String paramString, HttpHeaders paramHttpHeaders);
    
    public abstract void processResponse(int paramInt, InputStream paramInputStream, HttpHeaders paramHttpHeaders)
      throws Exception;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\util\HttpCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */