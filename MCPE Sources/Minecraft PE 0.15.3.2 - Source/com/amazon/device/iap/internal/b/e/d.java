package com.amazon.device.iap.internal.b.e;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.model.UserDataBuilder;
import com.amazon.device.iap.internal.model.UserDataResponseBuilder;
import com.amazon.device.iap.model.UserData;
import com.amazon.device.iap.model.UserDataResponse;
import com.amazon.device.iap.model.UserDataResponse.RequestStatus;
import com.amazon.venezia.command.SuccessResult;
import java.util.Map;

public final class d
  extends b
{
  private static final String b = d.class.getSimpleName();
  
  public d(com.amazon.device.iap.internal.b.e parame)
  {
    super(parame, "1.0");
  }
  
  protected boolean a(SuccessResult paramSuccessResult)
    throws RemoteException, KiwiException
  {
    com.amazon.device.iap.internal.util.e.a(b, "onSuccessInternal: result = " + paramSuccessResult);
    paramSuccessResult = paramSuccessResult.getData();
    com.amazon.device.iap.internal.util.e.a(b, "data: " + paramSuccessResult);
    Object localObject = (String)paramSuccessResult.get("userId");
    paramSuccessResult = b();
    if (com.amazon.device.iap.internal.util.d.a((String)localObject))
    {
      paramSuccessResult.d().a(new UserDataResponseBuilder().setRequestId(paramSuccessResult.c()).setRequestStatus(UserDataResponse.RequestStatus.FAILED).build());
      return false;
    }
    localObject = new UserDataBuilder().setUserId((String)localObject).setMarketplace(a).build();
    UserDataResponse localUserDataResponse = new UserDataResponseBuilder().setRequestId(paramSuccessResult.c()).setRequestStatus(UserDataResponse.RequestStatus.SUCCESSFUL).setUserData((UserData)localObject).build();
    paramSuccessResult.d().a("userId", ((UserData)localObject).getUserId());
    paramSuccessResult.d().a(localUserDataResponse);
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\internal\b\e\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */