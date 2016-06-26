package com.microsoft.onlineid.interop.xbox.toolkit;

public class HttpError
{
  private final int errorCode;
  private final String errorMessage;
  private final int httpStatus;
  
  public HttpError(int paramInt1, int paramInt2, String paramString)
  {
    this.errorCode = paramInt1;
    this.httpStatus = paramInt2;
    this.errorMessage = paramString;
  }
  
  public int getErrorCode()
  {
    return this.errorCode;
  }
  
  public String getErrorMessage()
  {
    return this.errorMessage;
  }
  
  public int getHttpStatus()
  {
    return this.httpStatus;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("errorCode: ").append(this.errorCode).append(", httpStatus: ").append(this.httpStatus).append(", errorMessage: ").append(this.errorMessage);
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\interop\xbox\toolkit\HttpError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */