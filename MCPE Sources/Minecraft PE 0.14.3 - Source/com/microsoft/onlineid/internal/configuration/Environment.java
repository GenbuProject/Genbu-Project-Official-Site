package com.microsoft.onlineid.internal.configuration;

import com.microsoft.onlineid.internal.Objects;
import java.net.URL;

public class Environment
{
  private final URL _configUrl;
  private final String _environmentName;
  
  public Environment(String paramString, URL paramURL)
  {
    this._environmentName = paramString;
    this._configUrl = paramURL;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof Environment))) {
        break;
      }
      paramObject = (Environment)paramObject;
    } while ((Objects.equals(getEnvironmentName(), ((Environment)paramObject).getEnvironmentName())) && (Objects.equals(getConfigUrl(), ((Environment)paramObject).getConfigUrl())));
    return false;
    return false;
  }
  
  public URL getConfigUrl()
  {
    return this._configUrl;
  }
  
  public String getEnvironmentName()
  {
    return this._environmentName;
  }
  
  public int hashCode()
  {
    return Objects.hashCode(this._environmentName) + Objects.hashCode(this._configUrl);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\configuration\Environment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */