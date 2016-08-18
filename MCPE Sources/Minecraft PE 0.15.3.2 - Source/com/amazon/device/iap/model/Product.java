package com.amazon.device.iap.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amazon.device.iap.internal.model.ProductBuilder;
import com.amazon.device.iap.internal.util.d;
import org.json.JSONException;
import org.json.JSONObject;

public final class Product
  implements Parcelable
{
  public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator()
  {
    public Product createFromParcel(Parcel paramAnonymousParcel)
    {
      return new Product(paramAnonymousParcel, null);
    }
    
    public Product[] newArray(int paramAnonymousInt)
    {
      return new Product[paramAnonymousInt];
    }
  };
  private static final String DESCRIPTION = "description";
  private static final String PRICE = "price";
  private static final String PRODUCT_TYPE = "productType";
  private static final String SKU = "sku";
  private static final String SMALL_ICON_URL = "smallIconUrl";
  private static final String TITLE = "title";
  private final String description;
  private final String price;
  private final ProductType productType;
  private final String sku;
  private final String smallIconUrl;
  private final String title;
  
  private Product(Parcel paramParcel)
  {
    this.sku = paramParcel.readString();
    this.productType = ProductType.valueOf(paramParcel.readString());
    this.description = paramParcel.readString();
    this.price = paramParcel.readString();
    this.smallIconUrl = paramParcel.readString();
    this.title = paramParcel.readString();
  }
  
  public Product(ProductBuilder paramProductBuilder)
  {
    d.a(paramProductBuilder.getSku(), "sku");
    d.a(paramProductBuilder.getProductType(), "productType");
    d.a(paramProductBuilder.getDescription(), "description");
    d.a(paramProductBuilder.getTitle(), "title");
    d.a(paramProductBuilder.getSmallIconUrl(), "smallIconUrl");
    if (ProductType.SUBSCRIPTION != paramProductBuilder.getProductType()) {
      d.a(paramProductBuilder.getPrice(), "price");
    }
    this.sku = paramProductBuilder.getSku();
    this.productType = paramProductBuilder.getProductType();
    this.description = paramProductBuilder.getDescription();
    this.price = paramProductBuilder.getPrice();
    this.smallIconUrl = paramProductBuilder.getSmallIconUrl();
    this.title = paramProductBuilder.getTitle();
  }
  
  public int describeContents()
  {
    return 0;
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
  
  public JSONObject toJSON()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("sku", this.sku);
    localJSONObject.put("productType", this.productType);
    localJSONObject.put("description", this.description);
    localJSONObject.put("price", this.price);
    localJSONObject.put("smallIconUrl", this.smallIconUrl);
    localJSONObject.put("title", this.title);
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
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.sku);
    paramParcel.writeString(this.productType.toString());
    paramParcel.writeString(this.description);
    paramParcel.writeString(this.price);
    paramParcel.writeString(this.smallIconUrl);
    paramParcel.writeString(this.title);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\model\Product.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */