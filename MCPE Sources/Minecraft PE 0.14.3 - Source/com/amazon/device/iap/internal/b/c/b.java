package com.amazon.device.iap.internal.b.c;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.model.ProductBuilder;
import com.amazon.device.iap.internal.model.ProductDataResponseBuilder;
import com.amazon.device.iap.model.Product;
import com.amazon.device.iap.model.ProductDataResponse.RequestStatus;
import com.amazon.device.iap.model.ProductType;
import com.amazon.venezia.command.SuccessResult;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public final class b
  extends c
{
  private static final String b = b.class.getSimpleName();
  
  public b(com.amazon.device.iap.internal.b.e parame, Set<String> paramSet)
  {
    super(parame, "1.0", paramSet);
  }
  
  private Product a(String paramString, Map paramMap)
    throws IllegalArgumentException
  {
    paramMap = (String)paramMap.get(paramString);
    try
    {
      Object localObject = new JSONObject(paramMap);
      ProductType localProductType = ProductType.valueOf(((JSONObject)localObject).getString("itemType").toUpperCase());
      String str1 = ((JSONObject)localObject).getString("description");
      String str2 = ((JSONObject)localObject).optString("price");
      String str3 = ((JSONObject)localObject).getString("title");
      localObject = ((JSONObject)localObject).getString("iconUrl");
      paramString = new ProductBuilder().setSku(paramString).setProductType(localProductType).setDescription(str1).setPrice(str2).setSmallIconUrl((String)localObject).setTitle(str3).build();
      return paramString;
    }
    catch (JSONException paramString)
    {
      throw new IllegalArgumentException("error in parsing json string" + paramMap);
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
      String str = (String)localIterator.next();
      if (!((Map)localObject).containsKey(str)) {
        paramSuccessResult.add(str);
      } else {
        try
        {
          localHashMap.put(str, a(str, (Map)localObject));
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          paramSuccessResult.add(str);
          com.amazon.device.iap.internal.util.e.b(b, "Error parsing JSON for SKU " + str + ": " + localIllegalArgumentException.getMessage());
        }
      }
    }
    localObject = b();
    paramSuccessResult = new ProductDataResponseBuilder().setRequestId(((com.amazon.device.iap.internal.b.e)localObject).c()).setRequestStatus(ProductDataResponse.RequestStatus.SUCCESSFUL).setUnavailableSkus(paramSuccessResult).setProductData(localHashMap).build();
    ((com.amazon.device.iap.internal.b.e)localObject).d().a(paramSuccessResult);
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\c\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */