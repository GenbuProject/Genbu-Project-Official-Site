package com.amazon.device.iap.internal.model;

import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserData;
import com.amazon.device.iap.model.UserDataResponse;
import com.amazon.device.iap.model.UserDataResponse.RequestStatus;

public class UserDataResponseBuilder
{
  private RequestId requestId;
  private UserDataResponse.RequestStatus requestStatus;
  private UserData userData;
  
  public UserDataResponse build()
  {
    return new UserDataResponse(this);
  }
  
  public RequestId getRequestId()
  {
    return this.requestId;
  }
  
  public UserDataResponse.RequestStatus getRequestStatus()
  {
    return this.requestStatus;
  }
  
  public UserData getUserData()
  {
    return this.userData;
  }
  
  public UserDataResponseBuilder setRequestId(RequestId paramRequestId)
  {
    this.requestId = paramRequestId;
    return this;
  }
  
  public UserDataResponseBuilder setRequestStatus(UserDataResponse.RequestStatus paramRequestStatus)
  {
    this.requestStatus = paramRequestStatus;
    return this;
  }
  
  public UserDataResponseBuilder setUserData(UserData paramUserData)
  {
    this.userData = paramUserData;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\model\UserDataResponseBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */