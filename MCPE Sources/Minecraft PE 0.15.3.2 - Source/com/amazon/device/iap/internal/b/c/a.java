package com.amazon.device.iap.internal.b.c;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.model.ProductBuilder;
import com.amazon.device.iap.internal.model.ProductDataResponseBuilder;
import com.amazon.device.iap.internal.util.MetricsHelper;
import com.amazon.device.iap.internal.util.d;
import com.amazon.device.iap.model.Product;
import com.amazon.device.iap.model.ProductDataResponse.RequestStatus;
import com.amazon.device.iap.model.ProductType;
import com.amazon.venezia.command.SuccessResult;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public final class a
  extends c
{
  private static final String b = a.class.getSimpleName();
  
  public a(com.amazon.device.iap.internal.b.e parame, Set<String> paramSet)
  {
    super(parame, "2.0", paramSet);
  }
  
  private Product a(String paramString, Map paramMap)
    throws IllegalArgumentException
  {
    String str1 = (String)paramMap.get(paramString);
    try
    {
      Object localObject2 = new JSONObject(str1);
      ProductType localProductType = ProductType.valueOf(((JSONObject)localObject2).getString("itemType").toUpperCase());
      String str2 = ((JSONObject)localObject2).getString("description");
      Object localObject1 = ((JSONObject)localObject2).optString("price", null);
      paramMap = (Map)localObject1;
      if (d.a((String)localObject1))
      {
        JSONObject localJSONObject = ((JSONObject)localObject2).optJSONObject("priceJson");
        paramMap = (Map)localObject1;
        if (localJSONObject != null)
        {
          paramMap = Currency.getInstance(localJSONObject.getString("currency"));
          localObject1 = new BigDecimal(localJSONObject.getString("value"));
          paramMap = paramMap.getSymbol() + localObject1;
        }
      }
      localObject1 = ((JSONObject)localObject2).getString("title");
      localObject2 = ((JSONObject)localObject2).getString("iconUrl");
      paramString = new ProductBuilder().setSku(paramString).setProductType(localProductType).setDescription(str2).setPrice(paramMap).setSmallIconUrl((String)localObject2).setTitle((String)localObject1).build();
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new IllegalArgumentException("error in parsing json string" + str1);
    }
  }
  
  protected boolean a(SuccessResult paramSuccessResult)
    throws RemoteException, KiwiException
  {
    Object localObject = paramSuccessResult.getData();
    com.amazon.device.iap.internal.util.e.a(b, "data: " + localObject);
    paramSuccessResult = new LinkedHashSet();
    HashMap localHashMap = new HashMap();
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      if (!((Map)localObject).containsKey(str1)) {
        paramSuccessResult.add(str1);
      } else {
        try
        {
          localHashMap.put(str1, a(str1, (Map)localObject));
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          paramSuccessResult.add(str1);
          String str2 = (String)((Map)localObject).get(str1);
          MetricsHelper.submitJsonParsingExceptionMetrics(c(), str2, b + ".onResult()");
          com.amazon.device.iap.internal.util.e.b(b, "Error parsing JSON for SKU " + str1 + ": " + localIllegalArgumentException.getMessage());
        }
      }
    }
    localObject = b();
    paramSuccessResult = new ProductDataResponseBuilder().setRequestId(((com.amazon.device.iap.internal.b.e)localObject).c()).setRequestStatus(ProductDataResponse.RequestStatus.SUCCESSFUL).setUnavailableSkus(paramSuccessResult).setProductData(localHashMap).build();
    ((com.amazon.device.iap.internal.b.e)localObject).d().a(paramSuccessResult);
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\internal\b\c\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */