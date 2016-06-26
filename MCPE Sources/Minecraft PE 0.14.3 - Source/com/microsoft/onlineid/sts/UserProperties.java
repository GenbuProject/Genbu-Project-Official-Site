package com.microsoft.onlineid.sts;

import com.microsoft.onlineid.internal.Objects;
import java.util.HashMap;
import java.util.Map;

public class UserProperties
{
  private final Map<UserProperty, String> _userProperties = new HashMap();
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject != null) && ((paramObject instanceof UserProperties)))
    {
      paramObject = (UserProperties)paramObject;
      return Objects.equals(this._userProperties, ((UserProperties)paramObject)._userProperties);
    }
    return false;
  }
  
  public String get(UserProperty paramUserProperty)
  {
    return (String)this._userProperties.get(paramUserProperty);
  }
  
  public boolean has(UserProperty paramUserProperty)
  {
    return this._userProperties.containsKey(paramUserProperty);
  }
  
  public int hashCode()
  {
    return Objects.hashCode(this._userProperties);
  }
  
  public UserProperties put(UserProperty paramUserProperty, String paramString)
  {
    this._userProperties.put(paramUserProperty, paramString);
    return this;
  }
  
  public static enum UserProperty
  {
    CID;
    
    private UserProperty() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\UserProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */