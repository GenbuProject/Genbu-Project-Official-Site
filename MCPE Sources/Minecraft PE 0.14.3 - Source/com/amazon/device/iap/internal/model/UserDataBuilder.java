package com.amazon.device.iap.internal.model;

import com.amazon.device.iap.model.UserData;

public class UserDataBuilder
{
  private String marketplace;
  private String userId;
  
  public UserData build()
  {
    return new UserData(this);
  }
  
  public String getMarketplace()
  {
    return this.marketplace;
  }
  
  public String getUserId()
  {
    return this.userId;
  }
  
  public UserDataBuilder setMarketplace(String paramString)
  {
    this.marketplace = paramString;
    return this;
  }
  
  public UserDataBuilder setUserId(String paramString)
  {
    this.userId = paramString;
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\model\UserDataBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */