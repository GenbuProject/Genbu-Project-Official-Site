package com.amazon.device.iap.internal.b.d;

import com.amazon.device.iap.internal.b.d;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.model.PurchaseUpdatesResponseBuilder;
import com.amazon.device.iap.internal.model.UserDataBuilder;
import com.amazon.device.iap.model.PurchaseUpdatesResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.UserData;
import com.amazon.venezia.command.SuccessResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

public final class c
  extends b
{
  private static final String b = c.class.getSimpleName();
  
  public c(com.amazon.device.iap.internal.b.e parame, boolean paramBoolean)
  {
    super(parame, "2.0", paramBoolean);
  }
  
  private List<Receipt> a(String paramString1, String paramString2, String paramString3)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    paramString2 = new JSONArray(paramString2);
    int i = 0;
    for (;;)
    {
      if (i < paramString2.length()) {
        try
        {
          localArrayList.add(com.amazon.device.iap.internal.util.a.a(paramString2.getJSONObject(i), paramString1, paramString3));
          i += 1;
        }
        catch (com.amazon.device.iap.internal.b.a locala)
        {
          for (;;)
          {
            com.amazon.device.iap.internal.util.e.b(b, "fail to parse receipt, requestId:" + locala.a());
          }
        }
        catch (d locald)
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
    return localArrayList;
  }
  
  protected boolean a(SuccessResult paramSuccessResult)
    throws Exception
  {
    Object localObject2 = paramSuccessResult.getData();
    com.amazon.device.iap.internal.util.e.a(b, "data: " + localObject2);
    Object localObject1 = (String)((Map)localObject2).get("userId");
    String str = (String)((Map)localObject2).get("marketplace");
    paramSuccessResult = (String)((Map)localObject2).get("requestId");
    List localList = a((String)localObject1, (String)((Map)localObject2).get("receipts"), paramSuccessResult);
    paramSuccessResult = (String)((Map)localObject2).get("cursor");
    boolean bool = Boolean.valueOf((String)((Map)localObject2).get("hasMore")).booleanValue();
    localObject2 = b();
    localObject1 = new UserDataBuilder().setUserId((String)localObject1).setMarketplace(str).build();
    localObject1 = new PurchaseUpdatesResponseBuilder().setRequestId(((com.amazon.device.iap.internal.b.e)localObject2).c()).setRequestStatus(PurchaseUpdatesResponse.RequestStatus.SUCCESSFUL).setUserData((UserData)localObject1).setReceipts(localList).setHasMore(bool).build();
    ((com.amazon.device.iap.internal.b.e)localObject2).d().a("newCursor", paramSuccessResult);
    ((com.amazon.device.iap.internal.b.e)localObject2).d().a(localObject1);
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\d\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */