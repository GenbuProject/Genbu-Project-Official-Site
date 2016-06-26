package com.amazon.device.iap.internal.model;

import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserData;

public class PurchaseResponseBuilder
{
  private Receipt receipt;
  private RequestId requestId;
  private PurchaseResponse.RequestStatus requestStatus;
  private UserData userData;
  
  public PurchaseResponse build()
  {
    return new PurchaseResponse(this);
  }
  
  public Receipt getReceipt()
  {
    return this.receipt;
  }
  
  public RequestId getRequestId()
  {
    return this.requestId;
  }
  
  public PurchaseResponse.RequestStatus getRequestStatus()
  {
    return this.requestStatus;
  }
  
  public UserData getUserData()
  {
    return this.userData;
  }
  
  public PurchaseResponseBuilder setReceipt(Receipt paramReceipt)
  {
    this.receipt = paramReceipt;
    return this;
  }
  
  public PurchaseResponseBuilder setRequestId(RequestId paramRequestId)
  {
    this.requestId = paramRequestId;
    return this;
  }
  
  public PurchaseResponseBuilder setRequestStatus(PurchaseResponse.RequestStatus paramRequestStatus)
  {
    this.requestStatus = paramRequestStatus;
    return this;
  }
  
  public PurchaseResponseBuilder setUserData(UserData paramUserData)
  {
    this.userData = paramUserData;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\model\PurchaseResponseBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */