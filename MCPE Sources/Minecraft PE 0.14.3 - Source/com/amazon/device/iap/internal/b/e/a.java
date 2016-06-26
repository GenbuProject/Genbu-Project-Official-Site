package com.amazon.device.iap.internal.b.e;

import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.b.i;
import com.amazon.device.iap.internal.model.UserDataResponseBuilder;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserDataResponse;
import com.amazon.device.iap.model.UserDataResponse.RequestStatus;

public final class a
  extends e
{
  public a(RequestId paramRequestId)
  {
    super(paramRequestId);
    paramRequestId = new c(this);
    paramRequestId.b(new d(this));
    a(paramRequestId);
  }
  
  public void a()
  {
    a((UserDataResponse)d().a());
  }
  
  public void b()
  {
    UserDataResponse localUserDataResponse2 = (UserDataResponse)d().a();
    UserDataResponse localUserDataResponse1 = localUserDataResponse2;
    if (localUserDataResponse2 == null) {
      localUserDataResponse1 = new UserDataResponseBuilder().setRequestId(c()).setRequestStatus(UserDataResponse.RequestStatus.FAILED).build();
    }
    a(localUserDataResponse1);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\e\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */