package com.amazon.device.iap.model;

import com.amazon.device.iap.internal.model.ReceiptBuilder;
import com.amazon.device.iap.internal.util.d;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public final class Receipt
{
  private static final String CANCEL_DATE = "endDate";
  private static final Date DATE_CANCELED = new Date(1L);
  private static final String PRODUCT_TYPE = "itemType";
  private static final String PURCHASE_DATE = "purchaseDate";
  private static final String RECEIPT_ID = "receiptId";
  private static final String SKU = "sku";
  private final Date cancelDate;
  private final ProductType productType;
  private final Date purchaseDate;
  private final String receiptId;
  private final String sku;
  
  public Receipt(ReceiptBuilder paramReceiptBuilder)
  {
    d.a(paramReceiptBuilder.getSku(), "sku");
    d.a(paramReceiptBuilder.getProductType(), "productType");
    if (ProductType.SUBSCRIPTION == paramReceiptBuilder.getProductType()) {
      d.a(paramReceiptBuilder.getPurchaseDate(), "purchaseDate");
    }
    this.receiptId = paramReceiptBuilder.getReceiptId();
    this.sku = paramReceiptBuilder.getSku();
    this.productType = paramReceiptBuilder.getProductType();
    this.purchaseDate = paramReceiptBuilder.getPurchaseDate();
    this.cancelDate = paramReceiptBuilder.getCancelDate();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (Receipt)paramObject;
        if (this.cancelDate == null)
        {
          if (((Receipt)paramObject).cancelDate != null) {
            return false;
          }
        }
        else if (!this.cancelDate.equals(((Receipt)paramObject).cancelDate)) {
          return false;
        }
        if (this.productType != ((Receipt)paramObject).productType) {
          return false;
        }
        if (this.purchaseDate == null)
        {
          if (((Receipt)paramObject).purchaseDate != null) {
            return false;
          }
        }
        else if (!this.purchaseDate.equals(((Receipt)paramObject).purchaseDate)) {
          return false;
        }
        if (this.receiptId == null)
        {
          if (((Receipt)paramObject).receiptId != null) {
            return false;
          }
        }
        else if (!this.receiptId.equals(((Receipt)paramObject).receiptId)) {
          return false;
        }
        if (this.sku != null) {
          break;
        }
      } while (((Receipt)paramObject).sku == null);
      return false;
    } while (this.sku.equals(((Receipt)paramObject).sku));
    return false;
  }
  
  public Date getCancelDate()
  {
    return this.cancelDate;
  }
  
  public ProductType getProductType()
  {
    return this.productType;
  }
  
  public Date getPurchaseDate()
  {
    return this.purchaseDate;
  }
  
  public String getReceiptId()
  {
    return this.receiptId;
  }
  
  public String getSku()
  {
    return this.sku;
  }
  
  public int hashCode()
  {
    int n = 0;
    int i;
    int j;
    label21:
    int k;
    label30:
    int m;
    if (this.cancelDate == null)
    {
      i = 0;
      if (this.productType != null) {
        break label85;
      }
      j = 0;
      if (this.purchaseDate != null) {
        break label96;
      }
      k = 0;
      if (this.receiptId != null) {
        break label107;
      }
      m = 0;
      label40:
      if (this.sku != null) {
        break label119;
      }
    }
    for (;;)
    {
      return (m + (k + (j + (i + 31) * 31) * 31) * 31) * 31 + n;
      i = this.cancelDate.hashCode();
      break;
      label85:
      j = this.productType.hashCode();
      break label21;
      label96:
      k = this.purchaseDate.hashCode();
      break label30;
      label107:
      m = this.receiptId.hashCode();
      break label40;
      label119:
      n = this.sku.hashCode();
    }
  }
  
  public boolean isCanceled()
  {
    return this.cancelDate != null;
  }
  
  public JSONObject toJSON()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("receiptId", this.receiptId);
      localJSONObject.put("sku", this.sku);
      localJSONObject.put("itemType", this.productType);
      localJSONObject.put("purchaseDate", this.purchaseDate);
      localJSONObject.put("endDate", this.cancelDate);
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return localJSONObject;
  }
  
  public String toString()
  {
    try
    {
      String str = toJSON().toString(4);
      return str;
    }
    catch (JSONException localJSONException) {}
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\model\Receipt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */