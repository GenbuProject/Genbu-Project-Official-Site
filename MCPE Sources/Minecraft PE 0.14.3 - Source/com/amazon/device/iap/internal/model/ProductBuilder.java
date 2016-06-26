package com.amazon.device.iap.internal.model;

import com.amazon.device.iap.model.Product;
import com.amazon.device.iap.model.ProductType;

public class ProductBuilder
{
  private String description;
  private String price;
  private ProductType productType;
  private String sku;
  private String smallIconUrl;
  private String title;
  
  public Product build()
  {
    return new Product(this);
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getPrice()
  {
    return this.price;
  }
  
  public ProductType getProductType()
  {
    return this.productType;
  }
  
  public String getSku()
  {
    return this.sku;
  }
  
  public String getSmallIconUrl()
  {
    return this.smallIconUrl;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public ProductBuilder setDescription(String paramString)
  {
    this.description = paramString;
    return this;
  }
  
  public ProductBuilder setPrice(String paramString)
  {
    this.price = paramString;
    return this;
  }
  
  public ProductBuilder setProductType(ProductType paramProductType)
  {
    this.productType = paramProductType;
    return this;
  }
  
  public ProductBuilder setSku(String paramString)
  {
    this.sku = paramString;
    return this;
  }
  
  public ProductBuilder setSmallIconUrl(String paramString)
  {
    this.smallIconUrl = paramString;
    return this;
  }
  
  public ProductBuilder setTitle(String paramString)
  {
    this.title = paramString;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\model\ProductBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */