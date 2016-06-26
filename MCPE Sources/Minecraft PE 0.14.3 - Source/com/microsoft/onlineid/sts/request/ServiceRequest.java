package com.microsoft.onlineid.sts.request;

import android.text.TextUtils;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.configuration.Experiment;
import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.ServerConfig.Endpoint;
import com.microsoft.onlineid.sts.XmlSigner;
import com.microsoft.onlineid.sts.exception.CorruptedUserDATokenException;
import com.microsoft.onlineid.sts.response.ServiceResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ServiceRequest
  extends AbstractTokenRequest<ServiceResponse>
  implements ISignableRequest
{
  private String _clientAppUri;
  private DAToken _deviceDA;
  private String _flowToken;
  private Element _parentOfSignatureNode;
  private boolean _requestFlights = false;
  protected List<ISecurityScope> _requestedScopes = new ArrayList();
  private XmlSigner _signer;
  private String _telemetry;
  private DAToken _userDA;
  
  public ServiceRequest()
  {
    this._requestedScopes.add(DAToken.Scope);
  }
  
  public void addRequest(ISecurityScope paramISecurityScope)
  {
    boolean bool2 = true;
    if (paramISecurityScope == null) {
      throw new IllegalArgumentException("Cannot request a null scope.");
    }
    if (this._requestedScopes.size() < 2)
    {
      bool1 = true;
      Assertion.check(bool1);
      if (paramISecurityScope.equals(DAToken.Scope)) {
        break label81;
      }
    }
    label81:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      Assertion.check(bool1);
      if (!this._requestedScopes.contains(paramISecurityScope)) {
        this._requestedScopes.add(paramISecurityScope);
      }
      return;
      bool1 = false;
      break;
    }
  }
  
  protected void buildAuthInfo(Element paramElement)
  {
    super.buildAuthInfo(paramElement);
    Requests.appendElement(paramElement, "ps:InlineUX", "Android");
    Requests.appendElement(paramElement, "ps:ConsentFlags", "1");
    Requests.appendElement(paramElement, "ps:IsConnected", "1");
    if (this._requestFlights) {
      Requests.appendElement(paramElement, "ps:Experiments", Experiment.getExperimentList());
    }
    if (this._flowToken != null) {
      Requests.appendElement(paramElement, "ps:InlineFT", this._flowToken);
    }
    Requests.appendElement(paramElement, "ps:ClientAppURI", this._clientAppUri);
    if (!TextUtils.isEmpty(this._telemetry)) {
      Requests.appendElement(paramElement, "ps:Telemetry", this._telemetry);
    }
  }
  
  protected void buildSecurityNode(Element paramElement)
  {
    try
    {
      Element localElement1 = Requests.xmlStringToElement(this._userDA.getToken());
      paramElement.appendChild(paramElement.getOwnerDocument().importNode(localElement1, true));
      appendDeviceDAToken(paramElement, this._deviceDA);
      localElement1 = Requests.appendElement(paramElement, "wssc:DerivedKeyToken");
      localElement1.setAttribute("wsu:Id", "SignKey");
      localElement1.setAttribute("Algorithm", "urn:liveid:SP800-108CTR-HMAC-SHA256");
      Element localElement2 = Requests.appendElement(localElement1, "wsse:RequestedTokenReference");
      Requests.appendElement(localElement2, "wsse:KeyIdentifier").setAttribute("ValueType", "http://docs.oasis-open.org/wss/2004/XX/oasis-2004XX-wss-saml-token-profile-1.0#SAMLAssertionID");
      Requests.appendElement(localElement2, "wsse:Reference").setAttribute("URI", "");
      Requests.appendElement(localElement1, "wssc:Nonce", this._signer.getEncodedNonce());
      appendTimestamp(paramElement);
      this._parentOfSignatureNode = paramElement;
      return;
    }
    catch (SAXException paramElement)
    {
      throw new CorruptedUserDATokenException("Unable to parse user DAToken blob into XML, possibly corrupt.", paramElement);
    }
  }
  
  public ServerConfig.Endpoint getEndpoint()
  {
    return ServerConfig.Endpoint.Sts;
  }
  
  public Element getParentOfSignatureNode()
  {
    return this._parentOfSignatureNode;
  }
  
  protected List<ISecurityScope> getRequestedScopes()
  {
    return this._requestedScopes;
  }
  
  public byte[] getSigningSessionKey()
  {
    return this._userDA.getSessionKey();
  }
  
  public XmlSigner getXmlSigner()
  {
    return this._signer;
  }
  
  public ServiceResponse instantiateResponse()
  {
    if (getRequestedScopes().size() == 2) {}
    for (boolean bool = true;; bool = false)
    {
      Assertion.check(bool);
      Object localObject2 = null;
      Iterator localIterator = getRequestedScopes().iterator();
      Object localObject1;
      do
      {
        localObject1 = localObject2;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = (ISecurityScope)localIterator.next();
      } while (localObject1.equals(DAToken.Scope));
      return new ServiceResponse(getSigningSessionKey(), (ISecurityScope)localObject1, getClockSkewManager());
    }
  }
  
  public void setClientAppUri(String paramString)
  {
    this._clientAppUri = paramString;
  }
  
  public void setDeviceDA(DAToken paramDAToken)
  {
    this._deviceDA = paramDAToken;
  }
  
  public void setFlowToken(String paramString)
  {
    this._flowToken = paramString;
  }
  
  public void setRequestFlights(boolean paramBoolean)
  {
    this._requestFlights = paramBoolean;
  }
  
  public void setTelemetry(String paramString)
  {
    this._telemetry = paramString;
  }
  
  public void setUserDA(DAToken paramDAToken)
  {
    this._userDA = paramDAToken;
  }
  
  public void setXmlSigner(XmlSigner paramXmlSigner)
  {
    this._signer = paramXmlSigner;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\request\ServiceRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */