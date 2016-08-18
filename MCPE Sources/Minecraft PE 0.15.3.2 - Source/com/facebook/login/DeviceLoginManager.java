package com.facebook.login;

import android.net.Uri;
import java.util.Collection;

public class DeviceLoginManager
  extends LoginManager
{
  private static volatile DeviceLoginManager instance;
  private Uri deviceRedirectUri;
  
  public static DeviceLoginManager getInstance()
  {
    if (instance == null) {}
    try
    {
      if (instance == null) {
        instance = new DeviceLoginManager();
      }
      return instance;
    }
    finally {}
  }
  
  protected LoginClient.Request createLoginRequest(Collection<String> paramCollection)
  {
    paramCollection = super.createLoginRequest(paramCollection);
    Uri localUri = getDeviceRedirectUri();
    if (localUri != null) {
      paramCollection.setDeviceRedirectUriString(localUri.toString());
    }
    return paramCollection;
  }
  
  public Uri getDeviceRedirectUri()
  {
    return this.deviceRedirectUri;
  }
  
  public void setDeviceRedirectUri(Uri paramUri)
  {
    this.deviceRedirectUri = paramUri;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\login\DeviceLoginManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */