package com.microsoft.onlineid.sts.request;

import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.DeviceCredentials;
import com.microsoft.onlineid.sts.ServerConfig.Endpoint;
import com.microsoft.onlineid.sts.response.DeviceAuthResponse;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Element;

public class DeviceAuthRequest
  extends AbstractTokenRequest<DeviceAuthResponse>
{
  private DeviceCredentials _credentials;
  
  protected void buildSecurityNode(Element paramElement)
  {
    Element localElement = Requests.appendElement(paramElement, "wsse:UsernameToken");
    localElement.setAttribute("wsu:Id", "devicesoftware");
    Requests.appendElement(localElement, "wsse:Username", this._credentials.getUsername());
    Requests.appendElement(localElement, "wsse:Password", this._credentials.getPassword());
    appendTimestamp(paramElement);
  }
  
  public ServerConfig.Endpoint getEndpoint()
  {
    return ServerConfig.Endpoint.Sts;
  }
  
  protected final List<ISecurityScope> getRequestedScopes()
  {
    return Collections.singletonList(DAToken.Scope);
  }
  
  public DeviceAuthResponse instantiateResponse()
  {
    return new DeviceAuthResponse(getClockSkewManager());
  }
  
  public void setDeviceCredentials(DeviceCredentials paramDeviceCredentials)
  {
    this._credentials = paramDeviceCredentials;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\sts\request\DeviceAuthRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */