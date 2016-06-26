package com.microsoft.onlineid.sts;

import com.microsoft.onlineid.internal.Objects;
import java.io.Serializable;

public class DeviceCredentials
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final String _password;
  private final String _username;
  
  public DeviceCredentials(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      throw new IllegalArgumentException("username and password must not be null.");
    }
    this._username = paramString1;
    this._password = paramString2;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof DeviceCredentials))) {
        break;
      }
      paramObject = (DeviceCredentials)paramObject;
    } while ((Objects.equals(this._username, ((DeviceCredentials)paramObject)._username)) && (Objects.equals(this._password, ((DeviceCredentials)paramObject)._password)));
    return false;
    return false;
  }
  
  public String getPassword()
  {
    return this._password;
  }
  
  public String getUsername()
  {
    return this._username;
  }
  
  public int hashCode()
  {
    return this._username.hashCode() + this._password.hashCode();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\DeviceCredentials.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */