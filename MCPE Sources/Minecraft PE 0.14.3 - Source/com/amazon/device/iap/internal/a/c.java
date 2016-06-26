package com.amazon.device.iap.internal.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.internal.model.ProductBuilder;
import com.amazon.device.iap.internal.model.ProductDataResponseBuilder;
import com.amazon.device.iap.internal.model.PurchaseResponseBuilder;
import com.amazon.device.iap.internal.model.PurchaseUpdatesResponseBuilder;
import com.amazon.device.iap.internal.model.ReceiptBuilder;
import com.amazon.device.iap.internal.model.UserDataBuilder;
import com.amazon.device.iap.internal.model.UserDataResponseBuilder;
import com.amazon.device.iap.internal.util.e;
import com.amazon.device.iap.model.FulfillmentResult;
import com.amazon.device.iap.model.Product;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.ProductDataResponse.RequestStatus;
import com.amazon.device.iap.model.ProductType;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserData;
import com.amazon.device.iap.model.UserDataResponse;
import com.amazon.device.iap.model.UserDataResponse.RequestStatus;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class c
  implements com.amazon.device.iap.internal.c
{
  private static final String a = c.class.getSimpleName();
  
  private Intent a(String paramString)
  {
    paramString = new Intent(paramString);
    paramString.setComponent(new ComponentName("com.amazon.sdktestclient", "com.amazon.sdktestclient.command.CommandBroker"));
    return paramString;
  }
  
  private Product a(String paramString, JSONObject paramJSONObject)
    throws JSONException
  {
    ProductType localProductType = ProductType.valueOf(paramJSONObject.optString("itemType"));
    Object localObject2 = paramJSONObject.getJSONObject("priceJson");
    Object localObject1 = Currency.getInstance(((JSONObject)localObject2).optString("currency"));
    localObject2 = new BigDecimal(((JSONObject)localObject2).optString("value"));
    localObject1 = ((Currency)localObject1).getSymbol() + localObject2;
    localObject2 = paramJSONObject.optString("title");
    String str = paramJSONObject.optString("description");
    paramJSONObject = paramJSONObject.optString("smallIconUrl");
    return new ProductBuilder().setSku(paramString).setProductType(localProductType).setDescription(str).setPrice((String)localObject1).setSmallIconUrl(paramJSONObject).setTitle((String)localObject2).build();
  }
  
  private Receipt a(JSONObject paramJSONObject)
    throws ParseException
  {
    String str1 = paramJSONObject.optString("receiptId");
    String str2 = paramJSONObject.optString("sku");
    ProductType localProductType = ProductType.valueOf(paramJSONObject.optString("itemType"));
    Object localObject = paramJSONObject.optString("purchaseDate");
    localObject = b.a.parse((String)localObject);
    paramJSONObject = paramJSONObject.optString("cancelDate");
    if ((paramJSONObject == null) || (paramJSONObject.length() == 0)) {}
    for (paramJSONObject = null;; paramJSONObject = b.a.parse(paramJSONObject)) {
      return new ReceiptBuilder().setReceiptId(str1).setSku(str2).setProductType(localProductType).setPurchaseDate((Date)localObject).setCancelDate(paramJSONObject).build();
    }
  }
  
  private void a(Intent paramIntent)
    throws JSONException
  {
    PurchaseUpdatesResponse localPurchaseUpdatesResponse = b(paramIntent);
    if (localPurchaseUpdatesResponse.getRequestStatus() == PurchaseUpdatesResponse.RequestStatus.SUCCESSFUL)
    {
      paramIntent = new JSONObject(paramIntent.getStringExtra("purchaseUpdatesOutput")).optString("offset");
      Log.i(a, "Offset for PurchaseUpdatesResponse:" + paramIntent);
      com.amazon.device.iap.internal.util.b.a(localPurchaseUpdatesResponse.getUserData().getUserId(), paramIntent);
    }
    a(localPurchaseUpdatesResponse);
  }
  
  private void a(String paramString1, String paramString2, boolean paramBoolean)
  {
    try
    {
      Context localContext = com.amazon.device.iap.internal.d.d().b();
      String str = com.amazon.device.iap.internal.util.b.a(paramString2);
      Log.i(a, "send PurchaseUpdates with user id:" + paramString2 + ";reset flag:" + paramBoolean + ", local cursor:" + str + ", parsed from old requestId:" + paramString1);
      paramString2 = new Bundle();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("requestId", paramString1.toString());
      paramString1 = str;
      if (paramBoolean) {
        paramString1 = null;
      }
      localJSONObject.put("offset", paramString1);
      localJSONObject.put("sdkVersion", "2.0.61.0");
      localJSONObject.put("packageName", localContext.getPackageName());
      paramString2.putString("purchaseUpdatesInput", localJSONObject.toString());
      paramString1 = a("com.amazon.testclient.iap.purchaseUpdates");
      paramString1.addFlags(268435456);
      paramString1.putExtras(paramString2);
      localContext.startService(paramString1);
      return;
    }
    catch (JSONException paramString1)
    {
      e.b(a, "Error in sendPurchaseUpdatesRequest.");
    }
  }
  
  private void a(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      Context localContext = com.amazon.device.iap.internal.d.d().b();
      Bundle localBundle = new Bundle();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("requestId", paramString);
      localJSONObject.put("packageName", localContext.getPackageName());
      localJSONObject.put("sdkVersion", "2.0.61.0");
      localJSONObject.put("isPurchaseUpdates", paramBoolean1);
      localJSONObject.put("reset", paramBoolean2);
      localBundle.putString("userInput", localJSONObject.toString());
      paramString = a("com.amazon.testclient.iap.appUserId");
      paramString.addFlags(268435456);
      paramString.putExtras(localBundle);
      localContext.startService(paramString);
      return;
    }
    catch (JSONException paramString)
    {
      e.b(a, "Error in sendGetUserDataRequest.");
    }
  }
  
  private PurchaseUpdatesResponse b(Intent paramIntent)
  {
    int i = 0;
    localObject2 = PurchaseUpdatesResponse.RequestStatus.FAILED;
    for (;;)
    {
      try
      {
        localObject3 = new JSONObject(paramIntent.getStringExtra("purchaseUpdatesOutput"));
        paramIntent = RequestId.fromString(((JSONObject)localObject3).optString("requestId"));
      }
      catch (Exception localException2)
      {
        Object localObject4;
        Intent localIntent;
        localArrayList = null;
        localObject3 = null;
        paramIntent = null;
        localObject1 = localObject2;
        bool = false;
        localObject2 = localArrayList;
        continue;
      }
      try
      {
        localObject1 = PurchaseUpdatesResponse.RequestStatus.valueOf(((JSONObject)localObject3).optString("status"));
        localObject2 = localObject1;
        bool = ((JSONObject)localObject3).optBoolean("isMore");
      }
      catch (Exception localException3)
      {
        localArrayList = null;
        localObject3 = paramIntent;
        paramIntent = null;
        localObject1 = localObject2;
        bool = false;
        localObject2 = localArrayList;
        continue;
      }
      try
      {
        localObject2 = ((JSONObject)localObject3).optString("userId");
        localObject4 = ((JSONObject)localObject3).optString("marketplace");
        localObject2 = new UserDataBuilder().setUserId((String)localObject2).setMarketplace((String)localObject4).build();
      }
      catch (Exception localException4)
      {
        localObject3 = paramIntent;
        paramIntent = null;
        localObject2 = null;
        continue;
      }
      try
      {
        if (localObject1 != PurchaseUpdatesResponse.RequestStatus.SUCCESSFUL) {
          continue;
        }
        localArrayList = new ArrayList();
        try
        {
          localObject4 = ((JSONObject)localObject3).optJSONArray("receipts");
          localObject3 = localArrayList;
          if (localObject4 == null) {
            continue;
          }
          localObject3 = localArrayList;
          if (i >= ((JSONArray)localObject4).length()) {
            continue;
          }
          localObject3 = ((JSONArray)localObject4).optJSONObject(i);
          try
          {
            localArrayList.add(a((JSONObject)localObject3));
            i += 1;
          }
          catch (Exception localException6)
          {
            Log.e(a, "Failed to parse receipt from json:" + localObject3);
            continue;
          }
          Log.e(a, "Error parsing purchase updates output", localException1);
        }
        catch (Exception localException1)
        {
          localObject3 = paramIntent;
          paramIntent = localArrayList;
        }
      }
      catch (Exception localException5)
      {
        localArrayList = null;
        localObject3 = paramIntent;
        paramIntent = localArrayList;
        continue;
      }
      return new PurchaseUpdatesResponseBuilder().setRequestId((RequestId)localObject3).setRequestStatus((PurchaseUpdatesResponse.RequestStatus)localObject1).setUserData((UserData)localObject2).setReceipts(paramIntent).setHasMore(bool).build();
      localObject3 = null;
      localIntent = paramIntent;
      paramIntent = (Intent)localObject3;
      localObject3 = localIntent;
    }
  }
  
  private void c(Intent paramIntent)
  {
    a(d(paramIntent));
  }
  
  private ProductDataResponse d(Intent paramIntent)
  {
    Iterator localIterator = null;
    localObject6 = null;
    localObject2 = ProductDataResponse.RequestStatus.FAILED;
    for (;;)
    {
      Object localObject1;
      LinkedHashSet localLinkedHashSet;
      try
      {
        localJSONObject = new JSONObject(paramIntent.getStringExtra("itemDataOutput"));
        paramIntent = RequestId.fromString(localJSONObject.optString("requestId"));
      }
      catch (Exception localException2)
      {
        JSONObject localJSONObject;
        label213:
        Object localObject3;
        label262:
        label270:
        localObject1 = null;
        paramIntent = (Intent)localObject2;
        localLinkedHashSet = null;
        localObject5 = localObject6;
        localObject2 = localObject1;
        localObject1 = localLinkedHashSet;
        continue;
      }
      for (;;)
      {
        try
        {
          localObject1 = ProductDataResponse.RequestStatus.valueOf(localJSONObject.optString("status"));
          localObject2 = localObject1;
          if (localObject1 == ProductDataResponse.RequestStatus.FAILED) {
            continue;
          }
          localObject2 = localObject1;
          localLinkedHashSet = new LinkedHashSet();
        }
        catch (Exception localException3)
        {
          localLinkedHashSet = null;
          localObject1 = paramIntent;
          paramIntent = (Intent)localObject2;
          localObject5 = localObject6;
          localObject2 = localLinkedHashSet;
          break label213;
          localObject5 = null;
          localObject2 = localException4;
          Object localObject4 = localObject5;
          break label270;
        }
        try
        {
          localObject5 = new HashMap();
          try
          {
            localObject2 = localJSONObject.optJSONArray("unavailableSkus");
            if (localObject2 != null)
            {
              int i = 0;
              while (i < ((JSONArray)localObject2).length())
              {
                localLinkedHashSet.add(((JSONArray)localObject2).getString(i));
                i += 1;
              }
            }
            localObject2 = localJSONObject.optJSONObject("items");
            if (localObject2 == null) {
              break label262;
            }
            localIterator = ((JSONObject)localObject2).keys();
            while (localIterator.hasNext())
            {
              localObject6 = (String)localIterator.next();
              ((Map)localObject5).put(localObject6, a((String)localObject6, ((JSONObject)localObject2).optJSONObject((String)localObject6)));
            }
            Log.e(a, "Error parsing item data output", localException1);
          }
          catch (Exception localException1)
          {
            localObject6 = localObject1;
            localObject1 = paramIntent;
            localObject2 = localLinkedHashSet;
            paramIntent = (Intent)localObject6;
          }
        }
        catch (Exception localException4)
        {
          localObject2 = localObject1;
          localObject1 = paramIntent;
          localObject5 = localObject6;
          paramIntent = (Intent)localObject2;
          localObject2 = localLinkedHashSet;
          break label213;
        }
      }
    }
    localObject3 = localObject2;
    localObject2 = localObject5;
    for (;;)
    {
      return new ProductDataResponseBuilder().setRequestId((RequestId)localObject1).setRequestStatus(paramIntent).setProductData((Map)localObject2).setUnavailableSkus((Set)localObject3).build();
      localObject2 = localObject5;
      localObject3 = localLinkedHashSet;
      localObject5 = localObject1;
      localObject1 = paramIntent;
      paramIntent = (Intent)localObject5;
    }
  }
  
  private void e(Intent paramIntent)
  {
    UserDataResponse localUserDataResponse = f(paramIntent);
    RequestId localRequestId = localUserDataResponse.getRequestId();
    String str = paramIntent.getStringExtra("userInput");
    try
    {
      paramIntent = new JSONObject(str);
      if ((localRequestId == null) || (paramIntent == null))
      {
        a(localUserDataResponse);
        return;
      }
    }
    catch (JSONException paramIntent)
    {
      for (;;)
      {
        Log.e(a, "Unable to parse request data: " + str, paramIntent);
        paramIntent = null;
      }
      if (!paramIntent.optBoolean("isPurchaseUpdates", false))
      {
        a(localUserDataResponse);
        return;
      }
      if ((localUserDataResponse.getUserData() == null) || (com.amazon.device.iap.internal.util.d.a(localUserDataResponse.getUserData().getUserId())))
      {
        Log.e(a, "No Userid found in userDataResponse" + localUserDataResponse);
        a(new PurchaseUpdatesResponseBuilder().setRequestId(localRequestId).setRequestStatus(PurchaseUpdatesResponse.RequestStatus.FAILED).setUserData(localUserDataResponse.getUserData()).setReceipts(new ArrayList()).setHasMore(false).build());
        return;
      }
      Log.i(a, "sendGetPurchaseUpdates with user id" + localUserDataResponse.getUserData().getUserId());
      boolean bool = paramIntent.optBoolean("reset", true);
      a(localRequestId.toString(), localUserDataResponse.getUserData().getUserId(), bool);
    }
  }
  
  private UserDataResponse f(Intent paramIntent)
  {
    String str2 = null;
    for (localObject = UserDataResponse.RequestStatus.FAILED;; localObject = null) {
      try
      {
        JSONObject localJSONObject = new JSONObject(paramIntent.getStringExtra("userOutput"));
        RequestId localRequestId = RequestId.fromString(localJSONObject.optString("requestId"));
        String str1;
        Log.e(a, "Error parsing userid output", (Throwable)localObject);
      }
      catch (Exception localException1)
      {
        try
        {
          paramIntent = UserDataResponse.RequestStatus.valueOf(localJSONObject.optString("status"));
          localObject = str2;
        }
        catch (Exception localException3)
        {
          paramIntent = (Intent)localObject;
          localObject = localException3;
          continue;
        }
        try
        {
          if (paramIntent == UserDataResponse.RequestStatus.SUCCESSFUL)
          {
            localObject = localJSONObject.optString("userId");
            str2 = localJSONObject.optString("marketplace");
            localObject = new UserDataBuilder().setUserId((String)localObject).setMarketplace(str2).build();
          }
          return new UserDataResponseBuilder().setRequestId(localRequestId).setRequestStatus(paramIntent).setUserData((UserData)localObject).build();
        }
        catch (Exception localException2)
        {
          continue;
        }
        localException1 = localException1;
        str2 = null;
        paramIntent = (Intent)localObject;
        localObject = localException1;
        str1 = str2;
      }
    }
  }
  
  private void g(Intent paramIntent)
  {
    a(h(paramIntent));
  }
  
  private PurchaseResponse h(Intent paramIntent)
  {
    Object localObject5 = null;
    localObject2 = PurchaseResponse.RequestStatus.FAILED;
    for (;;)
    {
      try
      {
        localObject3 = new JSONObject(paramIntent.getStringExtra("purchaseOutput"));
        RequestId localRequestId = RequestId.fromString(((JSONObject)localObject3).optString("requestId"));
        JSONObject localJSONObject;
        Log.e(a, "Error parsing purchase output", localException1);
      }
      catch (Exception localException1)
      {
        try
        {
          paramIntent = ((JSONObject)localObject3).optString("userId");
          localObject1 = ((JSONObject)localObject3).optString("marketplace");
          localObject1 = new UserDataBuilder().setUserId(paramIntent).setMarketplace((String)localObject1).build();
        }
        catch (Exception localException3)
        {
          for (;;)
          {
            Object localObject3;
            Intent localIntent;
            Object localObject4;
            Object localObject1 = null;
            paramIntent = (Intent)localObject2;
            localObject2 = localException1;
            localException2 = localException3;
          }
        }
        try
        {
          paramIntent = PurchaseResponse.RequestStatus.safeValueOf(((JSONObject)localObject3).optString("purchaseStatus"));
        }
        catch (Exception localException4)
        {
          paramIntent = (Intent)localObject2;
          localObject2 = localException2;
          localException2 = localException4;
          break label174;
        }
        try
        {
          localJSONObject = ((JSONObject)localObject3).optJSONObject("receipt");
          localObject3 = localObject5;
          localIntent = paramIntent;
          localObject4 = localObject1;
          localObject2 = localRequestId;
          if (localJSONObject != null)
          {
            localObject3 = a(localJSONObject);
            localObject2 = localRequestId;
            localObject4 = localObject1;
            localIntent = paramIntent;
          }
          return new PurchaseResponseBuilder().setRequestId((RequestId)localObject2).setRequestStatus(localIntent).setUserData((UserData)localObject4).setReceipt((Receipt)localObject3).build();
        }
        catch (Exception localException5)
        {
          localObject2 = localException2;
          localException2 = localException5;
          break label174;
        }
        localException1 = localException1;
        localObject1 = null;
        localObject3 = null;
        paramIntent = (Intent)localObject2;
        localObject2 = localObject3;
      }
      label174:
      localObject3 = localObject5;
      localIntent = paramIntent;
      localObject4 = localObject1;
    }
  }
  
  public void a(Context paramContext, Intent paramIntent)
  {
    e.a(a, "handleResponse");
    paramIntent.setComponent(new ComponentName("com.amazon.sdktestclient", "com.amazon.sdktestclient.command.CommandBroker"));
    try
    {
      paramContext = paramIntent.getExtras().getString("responseType");
      if (paramContext.equalsIgnoreCase("com.amazon.testclient.iap.purchase"))
      {
        g(paramIntent);
        return;
      }
      if (paramContext.equalsIgnoreCase("com.amazon.testclient.iap.appUserId"))
      {
        e(paramIntent);
        return;
      }
    }
    catch (Exception paramContext)
    {
      Log.e(a, "Error handling response.", paramContext);
      return;
    }
    if (paramContext.equalsIgnoreCase("com.amazon.testclient.iap.itemData"))
    {
      c(paramIntent);
      return;
    }
    if (paramContext.equalsIgnoreCase("com.amazon.testclient.iap.purchaseUpdates")) {
      a(paramIntent);
    }
  }
  
  public void a(RequestId paramRequestId)
  {
    e.a(a, "sendGetUserDataRequest");
    a(paramRequestId.toString(), false, false);
  }
  
  public void a(RequestId paramRequestId, String paramString)
  {
    e.a(a, "sendPurchaseRequest");
    try
    {
      Context localContext = com.amazon.device.iap.internal.d.d().b();
      Bundle localBundle = new Bundle();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("sku", paramString);
      localJSONObject.put("requestId", paramRequestId.toString());
      localJSONObject.put("packageName", localContext.getPackageName());
      localJSONObject.put("sdkVersion", "2.0.61.0");
      localBundle.putString("purchaseInput", localJSONObject.toString());
      paramRequestId = a("com.amazon.testclient.iap.purchase");
      paramRequestId.addFlags(268435456);
      paramRequestId.putExtras(localBundle);
      localContext.startService(paramRequestId);
      return;
    }
    catch (JSONException paramRequestId)
    {
      e.b(a, "Error in sendPurchaseRequest.");
    }
  }
  
  public void a(RequestId paramRequestId, String paramString, FulfillmentResult paramFulfillmentResult)
  {
    e.a(a, "sendNotifyPurchaseFulfilled");
    try
    {
      Context localContext = com.amazon.device.iap.internal.d.d().b();
      Bundle localBundle = new Bundle();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("requestId", paramRequestId.toString());
      localJSONObject.put("packageName", localContext.getPackageName());
      localJSONObject.put("receiptId", paramString);
      localJSONObject.put("fulfillmentResult", paramFulfillmentResult);
      localJSONObject.put("sdkVersion", "2.0.61.0");
      localBundle.putString("purchaseFulfilledInput", localJSONObject.toString());
      paramRequestId = a("com.amazon.testclient.iap.purchaseFulfilled");
      paramRequestId.addFlags(268435456);
      paramRequestId.putExtras(localBundle);
      localContext.startService(paramRequestId);
      return;
    }
    catch (JSONException paramRequestId)
    {
      e.b(a, "Error in sendNotifyPurchaseFulfilled.");
    }
  }
  
  public void a(RequestId paramRequestId, Set<String> paramSet)
  {
    e.a(a, "sendItemDataRequest");
    try
    {
      Context localContext = com.amazon.device.iap.internal.d.d().b();
      Bundle localBundle = new Bundle();
      JSONObject localJSONObject = new JSONObject();
      paramSet = new JSONArray(paramSet);
      localJSONObject.put("requestId", paramRequestId.toString());
      localJSONObject.put("packageName", localContext.getPackageName());
      localJSONObject.put("skus", paramSet);
      localJSONObject.put("sdkVersion", "2.0.61.0");
      localBundle.putString("itemDataInput", localJSONObject.toString());
      paramRequestId = a("com.amazon.testclient.iap.itemData");
      paramRequestId.addFlags(268435456);
      paramRequestId.putExtras(localBundle);
      localContext.startService(paramRequestId);
      return;
    }
    catch (JSONException paramRequestId)
    {
      e.b(a, "Error in sendItemDataRequest.");
    }
  }
  
  public void a(RequestId paramRequestId, boolean paramBoolean)
  {
    RequestId localRequestId = paramRequestId;
    if (paramRequestId == null) {
      localRequestId = new RequestId();
    }
    e.a(a, "sendPurchaseUpdatesRequest/sendGetUserData first:" + localRequestId);
    a(localRequestId.toString(), true, paramBoolean);
  }
  
  protected void a(final Object paramObject)
  {
    com.amazon.device.iap.internal.util.d.a(paramObject, "response");
    Context localContext = com.amazon.device.iap.internal.d.d().b();
    final PurchasingListener localPurchasingListener = com.amazon.device.iap.internal.d.d().a();
    if ((localContext == null) || (localPurchasingListener == null))
    {
      e.a(a, "PurchasingListener is not set. Dropping response: " + paramObject);
      return;
    }
    paramObject = new Runnable()
    {
      public void run()
      {
        try
        {
          if ((paramObject instanceof ProductDataResponse))
          {
            localPurchasingListener.onProductDataResponse((ProductDataResponse)paramObject);
            return;
          }
          if ((paramObject instanceof UserDataResponse))
          {
            localPurchasingListener.onUserDataResponse((UserDataResponse)paramObject);
            return;
          }
        }
        catch (Exception localException)
        {
          e.b(c.a(), "Error in sendResponse: " + localException);
          return;
        }
        if ((paramObject instanceof PurchaseUpdatesResponse))
        {
          localPurchasingListener.onPurchaseUpdatesResponse((PurchaseUpdatesResponse)paramObject);
          return;
        }
        if ((paramObject instanceof PurchaseResponse))
        {
          localPurchasingListener.onPurchaseResponse((PurchaseResponse)paramObject);
          return;
        }
        e.b(c.a(), "Unknown response type:" + paramObject.getClass().getName());
      }
    };
    new Handler(localContext.getMainLooper()).post((Runnable)paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */