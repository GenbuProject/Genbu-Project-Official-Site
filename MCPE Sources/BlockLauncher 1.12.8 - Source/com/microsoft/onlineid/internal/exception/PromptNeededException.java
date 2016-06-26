package com.microsoft.onlineid.internal.exception;

import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.ApiRequest;

public class PromptNeededException
  extends AuthenticationException
{
  private static final long serialVersionUID = 1L;
  private ApiRequest _request;
  
  public PromptNeededException(ApiRequest paramApiRequest)
  {
    this._request = paramApiRequest;
  }
  
  public ApiRequest getRequest()
  {
    return this._request;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\exception\PromptNeededException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */