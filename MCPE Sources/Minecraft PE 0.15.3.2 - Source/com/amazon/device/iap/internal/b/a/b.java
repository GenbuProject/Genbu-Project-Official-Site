package com.amazon.device.iap.internal.b.a;

import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.model.PurchaseResponseBuilder;
import com.amazon.device.iap.internal.model.UserDataBuilder;
import com.amazon.device.iap.internal.util.d;
import com.amazon.device.iap.model.ProductType;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.UserData;
import com.amazon.venezia.command.SuccessResult;
import java.util.Map;
import org.json.JSONObject;

public final class b
  extends c
{
  private static final String a = b.class.getSimpleName();
  
  public b(com.amazon.device.iap.internal.b.e parame)
  {
    super(parame, "1.0");
  }
  
  private void a(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null)) {}
    for (;;)
    {
      return;
      try
      {
        Object localObject = new JSONObject(paramString3);
        if (PurchaseResponse.RequestStatus.safeValueOf(((JSONObject)localObject).getString("orderStatus")) == PurchaseResponse.RequestStatus.SUCCESSFUL)
        {
          localObject = com.amazon.device.iap.internal.util.a.a((JSONObject)localObject, paramString2, paramString1);
          com.amazon.device.iap.internal.c.a.a().a(paramString1, paramString2, ((Receipt)localObject).getReceiptId(), paramString3);
          return;
        }
      }
      catch (Throwable paramString1)
      {
        com.amazon.device.iap.internal.util.e.b(a, "Error in savePendingReceipt: " + paramString1);
      }
    }
  }
  
  protected boolean a(SuccessResult paramSuccessResult)
    throws Exception
  {
    paramSuccessResult = paramSuccessResult.getData();
    com.amazon.device.iap.internal.util.e.a(a, "data: " + paramSuccessResult);
    Object localObject2 = (String)getCommandData().get("requestId");
    Object localObject1 = (String)paramSuccessResult.get("userId");
    String str1 = (String)paramSuccessResult.get("marketplace");
    String str2 = (String)paramSuccessResult.get("receipt");
    if ((localObject2 == null) || (!com.amazon.device.iap.internal.c.b.a().a((String)localObject2)))
    {
      b().d().b();
      return true;
    }
    if (d.a(str2))
    {
      a((String)localObject1, str1, (String)localObject2, PurchaseResponse.RequestStatus.FAILED);
      return false;
    }
    paramSuccessResult = new JSONObject(str2);
    PurchaseResponse.RequestStatus localRequestStatus = PurchaseResponse.RequestStatus.safeValueOf(paramSuccessResult.getString("orderStatus"));
    if (localRequestStatus == PurchaseResponse.RequestStatus.SUCCESSFUL) {}
    for (;;)
    {
      try
      {
        paramSuccessResult = com.amazon.device.iap.internal.util.a.a(paramSuccessResult, (String)localObject1, (String)localObject2);
        if (ProductType.CONSUMABLE == paramSuccessResult.getProductType()) {
          a((String)localObject2, (String)localObject1, str2);
        }
        localObject2 = b();
        localObject1 = new UserDataBuilder().setUserId((String)localObject1).setMarketplace(str1).build();
        paramSuccessResult = new PurchaseResponseBuilder().setRequestId(((com.amazon.device.iap.internal.b.e)localObject2).c()).setRequestStatus(localRequestStatus).setUserData((UserData)localObject1).setReceipt(paramSuccessResult).build();
        ((com.amazon.device.iap.internal.b.e)localObject2).d().a(paramSuccessResult);
        return true;
      }
      catch (Throwable paramSuccessResult)
      {
        a((String)localObject1, str1, (String)localObject2, PurchaseResponse.RequestStatus.FAILED);
        return false;
      }
      paramSuccessResult = null;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\internal\b\a\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */