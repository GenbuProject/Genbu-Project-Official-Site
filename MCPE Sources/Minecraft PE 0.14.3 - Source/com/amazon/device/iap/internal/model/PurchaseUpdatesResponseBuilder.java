package com.amazon.device.iap.internal.model;

import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserData;
import java.util.List;

public class PurchaseUpdatesResponseBuilder
{
  private boolean hasMore;
  private List<Receipt> receipts;
  private RequestId requestId;
  private PurchaseUpdatesResponse.RequestStatus requestStatus;
  private UserData userData;
  
  public PurchaseUpdatesResponse build()
  {
    return new PurchaseUpdatesResponse(this);
  }
  
  public List<Receipt> getReceipts()
  {
    return this.receipts;
  }
  
  public RequestId getRequestId()
  {
    return this.requestId;
  }
  
  public PurchaseUpdatesResponse.RequestStatus getRequestStatus()
  {
    return this.requestStatus;
  }
  
  public UserData getUserData()
  {
    return this.userData;
  }
  
  public boolean hasMore()
  {
    return this.hasMore;
  }
  
  public PurchaseUpdatesResponseBuilder setHasMore(boolean paramBoolean)
  {
    this.hasMore = paramBoolean;
    return this;
  }
  
  public PurchaseUpdatesResponseBuilder setReceipts(List<Receipt> paramList)
  {
    this.receipts = paramList;
    return this;
  }
  
  public PurchaseUpdatesResponseBuilder setRequestId(RequestId paramRequestId)
  {
    this.requestId = paramRequestId;
    return this;
  }
  
  public PurchaseUpdatesResponseBuilder setRequestStatus(PurchaseUpdatesResponse.RequestStatus paramRequestStatus)
  {
    this.requestStatus = paramRequestStatus;
    return this;
  }
  
  public PurchaseUpdatesResponseBuilder setUserData(UserData paramUserData)
  {
    this.userData = paramUserData;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\model\PurchaseUpdatesResponseBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */