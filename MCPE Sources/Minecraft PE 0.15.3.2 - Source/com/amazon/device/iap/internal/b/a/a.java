package com.amazon.device.iap.internal.b.a;

import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.model.PurchaseResponseBuilder;
import com.amazon.device.iap.internal.model.UserDataBuilder;
import com.amazon.device.iap.internal.util.d;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;
import com.amazon.device.iap.model.UserData;
import com.amazon.venezia.command.SuccessResult;
import java.util.Map;
import org.json.JSONObject;

public final class a
  extends c
{
  private static final String a = a.class.getSimpleName();
  
  public a(com.amazon.device.iap.internal.b.e parame)
  {
    super(parame, "2.0");
  }
  
  protected boolean a(SuccessResult paramSuccessResult)
    throws Exception
  {
    paramSuccessResult = paramSuccessResult.getData();
    com.amazon.device.iap.internal.util.e.a(a, "data: " + paramSuccessResult);
    Object localObject2 = (String)getCommandData().get("requestId");
    Object localObject1 = (String)paramSuccessResult.get("userId");
    String str = (String)paramSuccessResult.get("marketplace");
    paramSuccessResult = (String)paramSuccessResult.get("receipt");
    if (d.a(paramSuccessResult))
    {
      a((String)localObject1, str, (String)localObject2, PurchaseResponse.RequestStatus.FAILED);
      return false;
    }
    paramSuccessResult = new JSONObject(paramSuccessResult);
    PurchaseResponse.RequestStatus localRequestStatus = PurchaseResponse.RequestStatus.safeValueOf(paramSuccessResult.getString("orderStatus"));
    if (localRequestStatus == PurchaseResponse.RequestStatus.SUCCESSFUL) {}
    for (;;)
    {
      try
      {
        paramSuccessResult = com.amazon.device.iap.internal.util.a.a(paramSuccessResult, (String)localObject1, (String)localObject2);
        localObject2 = b();
        localObject1 = new UserDataBuilder().setUserId((String)localObject1).setMarketplace(str).build();
        paramSuccessResult = new PurchaseResponseBuilder().setRequestId(((com.amazon.device.iap.internal.b.e)localObject2).c()).setRequestStatus(localRequestStatus).setUserData((UserData)localObject1).setReceipt(paramSuccessResult).build();
        ((com.amazon.device.iap.internal.b.e)localObject2).d().a(paramSuccessResult);
        return true;
      }
      catch (Throwable paramSuccessResult)
      {
        a((String)localObject1, str, (String)localObject2, PurchaseResponse.RequestStatus.FAILED);
        return false;
      }
      paramSuccessResult = null;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\internal\b\a\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */