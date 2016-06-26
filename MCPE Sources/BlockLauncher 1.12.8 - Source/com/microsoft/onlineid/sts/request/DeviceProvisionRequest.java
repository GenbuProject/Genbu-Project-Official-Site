package com.microsoft.onlineid.sts.request;

import com.microsoft.onlineid.sts.DeviceCredentials;
import com.microsoft.onlineid.sts.ServerConfig.Endpoint;
import com.microsoft.onlineid.sts.response.DeviceProvisionResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DeviceProvisionRequest
  extends AbstractStsRequest<DeviceProvisionResponse>
{
  private DeviceCredentials _credentials;
  
  public Document buildRequest()
  {
    Document localDocument = createBlankDocument(null, "DeviceAddRequest");
    Element localElement1 = localDocument.getDocumentElement();
    Element localElement2 = Requests.appendElement(localElement1, "ClientInfo");
    localElement2.setAttribute("name", "MSAAndroidApp");
    localElement2.setAttribute("version", "1.0");
    localElement1 = Requests.appendElement(localElement1, "Authentication");
    Requests.appendElement(localElement1, "Membername", this._credentials.getUsername());
    Requests.appendElement(localElement1, "Password", this._credentials.getPassword());
    return localDocument;
  }
  
  public ServerConfig.Endpoint getEndpoint()
  {
    return ServerConfig.Endpoint.DeviceProvision;
  }
  
  public DeviceProvisionResponse instantiateResponse()
  {
    return new DeviceProvisionResponse();
  }
  
  public void setDeviceCredentials(DeviceCredentials paramDeviceCredentials)
  {
    this._credentials = paramDeviceCredentials;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\sts\request\DeviceProvisionRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */