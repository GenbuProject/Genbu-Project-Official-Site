package com.facebook;

public class FacebookServiceException
  extends FacebookException
{
  private static final long serialVersionUID = 1L;
  private final FacebookRequestError error;
  
  public FacebookServiceException(FacebookRequestError paramFacebookRequestError, String paramString)
  {
    super(paramString);
    this.error = paramFacebookRequestError;
  }
  
  public final FacebookRequestError getRequestError()
  {
    return this.error;
  }
  
  public final String toString()
  {
    return "{FacebookServiceException: " + "httpResponseCode: " + this.error.getRequestStatusCode() + ", facebookErrorCode: " + this.error.getErrorCode() + ", facebookErrorType: " + this.error.getErrorType() + ", message: " + this.error.getErrorMessage() + "}";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\FacebookServiceException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */