package com.amazon.device.iap.internal.model;

import com.amazon.device.iap.model.ProductType;
import com.amazon.device.iap.model.Receipt;
import java.util.Date;

public class ReceiptBuilder
{
  private Date cancelDate;
  private ProductType productType;
  private Date purchaseDate;
  private String receiptId;
  private String sku;
  
  public Receipt build()
  {
    return new Receipt(this);
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
  
  public ReceiptBuilder setCancelDate(Date paramDate)
  {
    this.cancelDate = paramDate;
    return this;
  }
  
  public ReceiptBuilder setProductType(ProductType paramProductType)
  {
    this.productType = paramProductType;
    return this;
  }
  
  public ReceiptBuilder setPurchaseDate(Date paramDate)
  {
    this.purchaseDate = paramDate;
    return this;
  }
  
  public ReceiptBuilder setReceiptId(String paramString)
  {
    this.receiptId = paramString;
    return this;
  }
  
  public ReceiptBuilder setSku(String paramString)
  {
    this.sku = paramString;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\model\ReceiptBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */