package com.amazon.device.iap.internal.util;

import com.amazon.android.Kiwi;
import com.amazon.device.iap.internal.model.ReceiptBuilder;
import com.amazon.device.iap.model.ProductType;
import com.amazon.device.iap.model.Receipt;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  private static final String a = a.class.getSimpleName();
  
  private static Receipt a(JSONObject paramJSONObject)
    throws JSONException
  {
    Object localObject2 = null;
    String str1 = paramJSONObject.optString("token");
    String str2 = paramJSONObject.getString("sku");
    ProductType localProductType = ProductType.valueOf(paramJSONObject.getString("itemType").toUpperCase());
    Object localObject1 = paramJSONObject.optString("startDate");
    if (a((String)localObject1))
    {
      localObject1 = null;
      paramJSONObject = paramJSONObject.optString("endDate");
      if (!a(paramJSONObject)) {
        break label104;
      }
    }
    label104:
    for (paramJSONObject = (JSONObject)localObject2;; paramJSONObject = b(paramJSONObject))
    {
      return new ReceiptBuilder().setReceiptId(str1).setSku(str2).setProductType(localProductType).setPurchaseDate((Date)localObject1).setCancelDate(paramJSONObject).build();
      localObject1 = b((String)localObject1);
      break;
    }
  }
  
  public static Receipt a(JSONObject paramJSONObject, String paramString1, String paramString2)
    throws com.amazon.device.iap.internal.b.a, com.amazon.device.iap.internal.b.d, IllegalArgumentException
  {
    c localc = b(paramJSONObject);
    switch (1.a[localc.ordinal()])
    {
    default: 
      return d(paramJSONObject, paramString1, paramString2);
    case 1: 
      return c(paramJSONObject, paramString1, paramString2);
    }
    return b(paramJSONObject, paramString1, paramString2);
  }
  
  protected static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }
  
  private static c b(JSONObject paramJSONObject)
  {
    String str = paramJSONObject.optString("DeviceId");
    if (!d.a(paramJSONObject.optString("receiptId"))) {
      return c.c;
    }
    if (d.a(str)) {
      return c.a;
    }
    return c.b;
  }
  
  private static Receipt b(JSONObject paramJSONObject, String paramString1, String paramString2)
    throws com.amazon.device.iap.internal.b.a, com.amazon.device.iap.internal.b.d
  {
    String str = paramJSONObject.optString("signature");
    if (d.a(str))
    {
      e.b(a, "a signature was not found in the receipt for request ID " + paramString2);
      MetricsHelper.submitReceiptVerificationFailureMetrics(paramString2, "NO Signature found", str);
      throw new com.amazon.device.iap.internal.b.d(paramString2, null, str);
    }
    Receipt localReceipt;
    try
    {
      localReceipt = a(paramJSONObject);
      paramJSONObject = paramString1 + "-" + localReceipt.getReceiptId();
      boolean bool = Kiwi.isSignedByKiwi(paramJSONObject, str);
      e.a(a, "stringToVerify/legacy:\n" + paramJSONObject + "\nsignature:\n" + str);
      if (!bool)
      {
        MetricsHelper.submitReceiptVerificationFailureMetrics(paramString2, paramJSONObject, str);
        throw new com.amazon.device.iap.internal.b.d(paramString2, paramJSONObject, str);
      }
    }
    catch (JSONException paramString1)
    {
      throw new com.amazon.device.iap.internal.b.a(paramString2, paramJSONObject.toString(), paramString1);
    }
    return localReceipt;
  }
  
  protected static Date b(String paramString)
    throws JSONException
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    try
    {
      paramString = localSimpleDateFormat.parse(paramString);
      long l = paramString.getTime();
      if (0L == l) {
        paramString = null;
      }
      return paramString;
    }
    catch (ParseException paramString)
    {
      throw new JSONException(paramString.getMessage());
    }
  }
  
  private static Receipt c(JSONObject paramJSONObject, String paramString1, String paramString2)
    throws com.amazon.device.iap.internal.b.a, com.amazon.device.iap.internal.b.d
  {
    Date localDate = null;
    String str2 = paramJSONObject.optString("DeviceId");
    String str1 = paramJSONObject.optString("signature");
    if (d.a(str1))
    {
      e.b(a, "a signature was not found in the receipt for request ID " + paramString2);
      MetricsHelper.submitReceiptVerificationFailureMetrics(paramString2, "NO Signature found", str1);
      throw new com.amazon.device.iap.internal.b.d(paramString2, null, str1);
    }
    Receipt localReceipt;
    for (;;)
    {
      try
      {
        localReceipt = a(paramJSONObject);
        ProductType localProductType = localReceipt.getProductType();
        String str3 = localReceipt.getSku();
        String str4 = localReceipt.getReceiptId();
        if (ProductType.SUBSCRIPTION == localReceipt.getProductType())
        {
          paramJSONObject = localReceipt.getPurchaseDate();
          if (ProductType.SUBSCRIPTION == localReceipt.getProductType()) {
            localDate = localReceipt.getCancelDate();
          }
          paramJSONObject = String.format("%s|%s|%s|%s|%s|%s|%s|%tQ|%tQ", new Object[] { "2.0.61.0", paramString1, str2, localProductType, str3, str4, paramString2, paramJSONObject, localDate });
          e.a(a, "stringToVerify/v1:\n" + paramJSONObject + "\nsignature:\n" + str1);
          if (Kiwi.isSignedByKiwi(paramJSONObject, str1)) {
            break;
          }
          MetricsHelper.submitReceiptVerificationFailureMetrics(paramString2, paramJSONObject, str1);
          throw new com.amazon.device.iap.internal.b.d(paramString2, paramJSONObject, str1);
        }
      }
      catch (JSONException paramString1)
      {
        throw new com.amazon.device.iap.internal.b.a(paramString2, paramJSONObject.toString(), paramString1);
      }
      paramJSONObject = null;
    }
    return localReceipt;
  }
  
  private static Receipt d(JSONObject paramJSONObject, String paramString1, String paramString2)
    throws com.amazon.device.iap.internal.b.a, com.amazon.device.iap.internal.b.d
  {
    Date localDate = null;
    String str2 = paramJSONObject.optString("DeviceId");
    String str1 = paramJSONObject.optString("signature");
    if (d.a(str1))
    {
      e.b(a, "a signature was not found in the receipt for request ID " + paramString2);
      MetricsHelper.submitReceiptVerificationFailureMetrics(paramString2, "NO Signature found", str1);
      throw new com.amazon.device.iap.internal.b.d(paramString2, null, str1);
    }
    try
    {
      String str3 = paramJSONObject.getString("receiptId");
      String str4 = paramJSONObject.getString("sku");
      ProductType localProductType = ProductType.valueOf(paramJSONObject.getString("itemType").toUpperCase());
      Object localObject = paramJSONObject.optString("purchaseDate");
      String str5;
      if (a((String)localObject))
      {
        localObject = null;
        str5 = paramJSONObject.optString("cancelDate");
        if (!a(str5)) {
          break label296;
        }
      }
      for (;;)
      {
        localObject = new ReceiptBuilder().setReceiptId(str3).setSku(str4).setProductType(localProductType).setPurchaseDate((Date)localObject).setCancelDate(localDate).build();
        paramJSONObject = String.format("%s|%s|%s|%s|%s|%tQ|%tQ", new Object[] { paramString1, str2, ((Receipt)localObject).getProductType(), ((Receipt)localObject).getSku(), ((Receipt)localObject).getReceiptId(), ((Receipt)localObject).getPurchaseDate(), ((Receipt)localObject).getCancelDate() });
        e.a(a, "stringToVerify/v2:\n" + paramJSONObject + "\nsignature:\n" + str1);
        if (Kiwi.isSignedByKiwi(paramJSONObject, str1)) {
          return localObject;
        }
        MetricsHelper.submitReceiptVerificationFailureMetrics(paramString2, paramJSONObject, str1);
        throw new com.amazon.device.iap.internal.b.d(paramString2, paramJSONObject, str1);
        localObject = b((String)localObject);
        break;
        label296:
        localDate = b(str5);
      }
      return (Receipt)localObject;
    }
    catch (JSONException paramString1)
    {
      throw new com.amazon.device.iap.internal.b.a(paramString2, paramJSONObject.toString(), paramString1);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\util\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */