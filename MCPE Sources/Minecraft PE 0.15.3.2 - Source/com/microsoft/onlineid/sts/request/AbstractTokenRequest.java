package com.microsoft.onlineid.sts.request;

import android.text.TextUtils;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.sts.XmlSigner;
import com.microsoft.onlineid.sts.response.AbstractSoapResponse;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Element;

public abstract class AbstractTokenRequest<ResponseType extends AbstractSoapResponse>
  extends AbstractSoapRequest<ResponseType>
{
  private Element appendTokenRequestElement(Element paramElement, ISecurityScope paramISecurityScope)
  {
    paramElement = Requests.appendElement(paramElement, "wst:RequestSecurityToken");
    paramElement.setAttribute("xmlns:wst", "http://schemas.xmlsoap.org/ws/2005/02/trust");
    Requests.appendElement(paramElement, "wst:RequestType", "http://schemas.xmlsoap.org/ws/2005/02/trust/Issue");
    Element localElement = Requests.appendElement(paramElement, "wsp:AppliesTo");
    localElement.setAttribute("xmlns:wsp", "http://schemas.xmlsoap.org/ws/2004/09/policy");
    localElement = Requests.appendElement(localElement, "wsa:EndpointReference");
    localElement.setAttribute("xmlns:wsa", "http://www.w3.org/2005/08/addressing");
    Requests.appendElement(localElement, "wsa:Address", paramISecurityScope.getTarget());
    paramISecurityScope = paramISecurityScope.getPolicy();
    if (!TextUtils.isEmpty(paramISecurityScope))
    {
      localElement = Requests.appendElement(paramElement, "wsp:PolicyReference");
      localElement.setAttribute("xmlns:wsp", "http://schemas.xmlsoap.org/ws/2004/09/policy");
      localElement.setAttribute("URI", paramISecurityScope);
    }
    return paramElement;
  }
  
  protected void buildAuthInfo(Element paramElement)
  {
    super.buildAuthInfo(paramElement);
    Requests.appendElement(paramElement, "ps:HostingApp", "{F501FD64-9070-46AB-993C-6F7B71D8D883}");
  }
  
  protected void buildSoapBody(Element paramElement)
  {
    List localList = getRequestedScopes();
    Element localElement = paramElement;
    if (localList.size() > 1)
    {
      paramElement = Requests.appendElement(paramElement, "ps:RequestMultipleSecurityTokens");
      paramElement.setAttribute("xmlns:ps", "http://schemas.microsoft.com/Passport/SoapServices/PPCRL");
      paramElement.setAttribute("Id", "RSTS");
      localElement = paramElement;
      if ((this instanceof ISignableRequest))
      {
        ((ISignableRequest)this).getXmlSigner().addElementToSign(paramElement);
        localElement = paramElement;
      }
    }
    int i = 0;
    paramElement = localList.iterator();
    while (paramElement.hasNext())
    {
      appendTokenRequestElement(localElement, (ISecurityScope)paramElement.next()).setAttribute("Id", "RST" + i);
      i += 1;
    }
  }
  
  protected abstract List<ISecurityScope> getRequestedScopes();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\request\AbstractTokenRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */