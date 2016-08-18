package com.facebook;

public class FacebookDialogException
  extends FacebookException
{
  static final long serialVersionUID = 1L;
  private int errorCode;
  private String failingUrl;
  
  public FacebookDialogException(String paramString1, int paramInt, String paramString2)
  {
    super(paramString1);
    this.errorCode = paramInt;
    this.failingUrl = paramString2;
  }
  
  public int getErrorCode()
  {
    return this.errorCode;
  }
  
  public String getFailingUrl()
  {
    return this.failingUrl;
  }
  
  public final String toString()
  {
    return "{FacebookDialogException: " + "errorCode: " + getErrorCode() + ", message: " + getMessage() + ", url: " + getFailingUrl() + "}";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\FacebookDialogException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */