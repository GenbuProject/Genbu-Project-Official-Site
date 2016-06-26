package com.amazon.device.iap.internal.b.e;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.model.UserDataBuilder;
import com.amazon.device.iap.internal.model.UserDataResponseBuilder;
import com.amazon.device.iap.internal.util.d;
import com.amazon.device.iap.model.UserData;
import com.amazon.device.iap.model.UserDataResponse.RequestStatus;
import com.amazon.venezia.command.SuccessResult;
import java.util.Map;

public final class c
  extends b
{
  private static final String b = c.class.getSimpleName();
  
  public c(com.amazon.device.iap.internal.b.e parame)
  {
    super(parame, "2.0");
  }
  
  protected boolean a(SuccessResult paramSuccessResult)
    throws RemoteException, KiwiException
  {
    com.amazon.device.iap.internal.util.e.a(b, "onResult: result = " + paramSuccessResult);
    paramSuccessResult = paramSuccessResult.getData();
    com.amazon.device.iap.internal.util.e.a(b, "data: " + paramSuccessResult);
    Object localObject1 = (String)paramSuccessResult.get("userId");
    Object localObject2 = (String)paramSuccessResult.get("marketplace");
    paramSuccessResult = b();
    if ((d.a((String)localObject1)) || (d.a((String)localObject2)))
    {
      paramSuccessResult.d().a(new UserDataResponseBuilder().setRequestId(paramSuccessResult.c()).setRequestStatus(UserDataResponse.RequestStatus.FAILED).build());
      return false;
    }
    localObject1 = new UserDataBuilder().setUserId((String)localObject1).setMarketplace((String)localObject2).build();
    localObject2 = new UserDataResponseBuilder().setRequestId(paramSuccessResult.c()).setRequestStatus(UserDataResponse.RequestStatus.SUCCESSFUL).setUserData((UserData)localObject1).build();
    paramSuccessResult.d().a("userId", ((UserData)localObject1).getUserId());
    paramSuccessResult.d().a(localObject2);
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\e\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */