package com.facebook;

public class FacebookGraphResponseException
  extends FacebookException
{
  private final GraphResponse graphResponse;
  
  public FacebookGraphResponseException(GraphResponse paramGraphResponse, String paramString)
  {
    super(paramString);
    this.graphResponse = paramGraphResponse;
  }
  
  public final GraphResponse getGraphResponse()
  {
    return this.graphResponse;
  }
  
  public final String toString()
  {
    if (this.graphResponse != null) {}
    for (FacebookRequestError localFacebookRequestError = this.graphResponse.getError();; localFacebookRequestError = null)
    {
      StringBuilder localStringBuilder = new StringBuilder().append("{FacebookGraphResponseException: ");
      String str = getMessage();
      if (str != null)
      {
        localStringBuilder.append(str);
        localStringBuilder.append(" ");
      }
      if (localFacebookRequestError != null) {
        localStringBuilder.append("httpResponseCode: ").append(localFacebookRequestError.getRequestStatusCode()).append(", facebookErrorCode: ").append(localFacebookRequestError.getErrorCode()).append(", facebookErrorType: ").append(localFacebookRequestError.getErrorType()).append(", message: ").append(localFacebookRequestError.getErrorMessage()).append("}");
      }
      return localStringBuilder.toString();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\FacebookGraphResponseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */