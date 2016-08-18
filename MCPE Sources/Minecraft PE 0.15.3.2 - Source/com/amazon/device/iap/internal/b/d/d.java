package com.amazon.device.iap.internal.b.d;

import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.c.c;
import com.amazon.device.iap.internal.model.PurchaseUpdatesResponseBuilder;
import com.amazon.device.iap.internal.model.ReceiptBuilder;
import com.amazon.device.iap.internal.model.UserDataBuilder;
import com.amazon.device.iap.model.ProductType;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.UserData;
import com.amazon.venezia.command.SuccessResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

public final class d
  extends b
{
  private static final String b = d.class.getSimpleName();
  private static final Date c = new Date(0L);
  
  public d(com.amazon.device.iap.internal.b.e parame)
  {
    super(parame, "1.0", true);
  }
  
  protected boolean a(SuccessResult paramSuccessResult)
    throws Exception
  {
    int j = 0;
    Object localObject2 = paramSuccessResult.getData();
    com.amazon.device.iap.internal.util.e.a(b, "data: " + localObject2);
    paramSuccessResult = (String)((Map)localObject2).get("userId");
    Object localObject1 = (String)((Map)localObject2).get("requestId");
    String str1 = (String)((Map)localObject2).get("marketplace");
    localObject1 = new ArrayList();
    Object localObject3 = new JSONArray((String)((Map)localObject2).get("receipts"));
    int i = 0;
    for (;;)
    {
      if (i < ((JSONArray)localObject3).length()) {
        try
        {
          Receipt localReceipt = com.amazon.device.iap.internal.util.a.a(((JSONArray)localObject3).getJSONObject(i), paramSuccessResult, null);
          ((List)localObject1).add(localReceipt);
          if (ProductType.ENTITLED == localReceipt.getProductType()) {
            c.a().a(paramSuccessResult, localReceipt.getReceiptId(), localReceipt.getSku());
          }
          i += 1;
        }
        catch (com.amazon.device.iap.internal.b.a locala)
        {
          for (;;)
          {
            com.amazon.device.iap.internal.util.e.b(b, "fail to parse receipt, requestId:" + locala.a());
          }
        }
        catch (com.amazon.device.iap.internal.b.d locald)
        {
          for (;;)
          {
            com.amazon.device.iap.internal.util.e.b(b, "fail to verify receipt, requestId:" + locald.a());
          }
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            com.amazon.device.iap.internal.util.e.b(b, "fail to verify receipt, requestId:" + localThrowable.getMessage());
          }
        }
      }
    }
    localObject3 = new JSONArray((String)((Map)localObject2).get("revocations"));
    i = j;
    for (;;)
    {
      if (i < ((JSONArray)localObject3).length()) {
        try
        {
          String str2 = ((JSONArray)localObject3).getString(i);
          String str3 = c.a().a(paramSuccessResult, str2);
          ((List)localObject1).add(new ReceiptBuilder().setSku(str2).setProductType(ProductType.ENTITLED).setPurchaseDate(null).setCancelDate(c).setReceiptId(str3).build());
          i += 1;
        }
        catch (JSONException localJSONException)
        {
          for (;;)
          {
            com.amazon.device.iap.internal.util.e.b(b, "fail to parse JSON[" + i + "] in \"" + localObject3 + "\"");
          }
        }
      }
    }
    localObject3 = (String)((Map)localObject2).get("cursor");
    boolean bool = "true".equalsIgnoreCase((String)((Map)localObject2).get("hasMore"));
    localObject2 = b();
    paramSuccessResult = new UserDataBuilder().setUserId(paramSuccessResult).setMarketplace(str1).build();
    paramSuccessResult = new PurchaseUpdatesResponseBuilder().setRequestId(((com.amazon.device.iap.internal.b.e)localObject2).c()).setRequestStatus(PurchaseUpdatesResponse.RequestStatus.SUCCESSFUL).setUserData(paramSuccessResult).setReceipts((List)localObject1).setHasMore(bool).build();
    localObject1 = com.amazon.device.iap.internal.c.a.a().b(paramSuccessResult.getUserData().getUserId());
    paramSuccessResult.getReceipts().addAll((Collection)localObject1);
    ((com.amazon.device.iap.internal.b.e)localObject2).d().a(paramSuccessResult);
    ((com.amazon.device.iap.internal.b.e)localObject2).d().a("newCursor", localObject3);
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\internal\b\d\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */