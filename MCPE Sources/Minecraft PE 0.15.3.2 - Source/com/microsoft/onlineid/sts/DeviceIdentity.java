package com.microsoft.onlineid.sts;

import com.microsoft.onlineid.internal.Objects;
import java.io.Serializable;

public class DeviceIdentity
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final DeviceCredentials _credentials;
  private final String _puid;
  private DAToken _token;
  
  public DeviceIdentity(DeviceCredentials paramDeviceCredentials, String paramString, DAToken paramDAToken)
  {
    if ((paramDeviceCredentials == null) || (paramString == null)) {
      throw new IllegalArgumentException("credentials and puid must not be null.");
    }
    this._credentials = paramDeviceCredentials;
    this._puid = paramString;
    this._token = paramDAToken;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof DeviceIdentity))) {
        break;
      }
      paramObject = (DeviceIdentity)paramObject;
    } while ((Objects.equals(this._credentials, ((DeviceIdentity)paramObject)._credentials)) && (Objects.equals(this._puid, ((DeviceIdentity)paramObject)._puid)) && (Objects.equals(this._token, ((DeviceIdentity)paramObject)._token)));
    return false;
    return false;
  }
  
  public DeviceCredentials getCredentials()
  {
    return this._credentials;
  }
  
  public DAToken getDAToken()
  {
    return this._token;
  }
  
  public String getPuid()
  {
    return this._puid;
  }
  
  public int hashCode()
  {
    return this._credentials.hashCode() + this._puid.hashCode() + Objects.hashCode(this._token);
  }
  
  void setDAToken(DAToken paramDAToken)
  {
    this._token = paramDAToken;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\DeviceIdentity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */