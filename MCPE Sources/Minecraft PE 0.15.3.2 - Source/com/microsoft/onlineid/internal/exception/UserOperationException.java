package com.microsoft.onlineid.internal.exception;

import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.Strings;

public class UserOperationException
  extends AuthenticationException
{
  private static final long serialVersionUID = 1L;
  private final String _bodyString;
  private final String _headerString;
  
  public UserOperationException(String paramString)
  {
    super(paramString);
    Strings.verifyArgumentNotNullOrEmpty(paramString, "bodyMessage");
    this._headerString = null;
    this._bodyString = paramString;
  }
  
  public UserOperationException(String paramString1, String paramString2)
  {
    super(paramString1 + " " + paramString2);
    Strings.verifyArgumentNotNullOrEmpty(paramString2, "bodyMessage");
    Strings.verifyArgumentNotNullOrEmpty(paramString1, "headerString");
    this._headerString = paramString1;
    this._bodyString = paramString2;
  }
  
  public UserOperationException(String paramString1, String paramString2, Throwable paramThrowable)
  {
    super(paramString1 + " " + paramString2, paramThrowable);
    Strings.verifyArgumentNotNullOrEmpty(paramString2, "bodyMessage");
    Strings.verifyArgumentNotNullOrEmpty(paramString1, "headerString");
    this._headerString = paramString1;
    this._bodyString = paramString2;
  }
  
  public UserOperationException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
    Strings.verifyArgumentNotNullOrEmpty(paramString, "bodyMessage");
    this._headerString = null;
    this._bodyString = paramString;
  }
  
  public String getBodyMessage()
  {
    return this._bodyString;
  }
  
  public String getHeaderString()
  {
    return this._headerString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\exception\UserOperationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */