package com.microsoft.onlineid.sts.request;

import com.microsoft.onlineid.sts.ClockSkewManager;
import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.XmlSigner;
import com.microsoft.onlineid.sts.response.AbstractSoapResponse;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class AbstractSoapRequest<ResponseType extends AbstractSoapResponse>
  extends AbstractStsRequest<ResponseType>
{
  public static final String MsaAppGuid = "{F501FD64-9070-46AB-993C-6F7B71D8D883}";
  public static final String PSNamespace = "http://schemas.microsoft.com/Passport/SoapServices/PPCRL";
  public static final String PsfNamespace = "http://schemas.microsoft.com/Passport/SoapServices/SOAPFault";
  private static final int RequestExpiryMilliseconds = 300000;
  public static final String SamlNamespace = "urn:oasis:names:tc:SAML:1.0:assertion";
  public static final String SoapNamespace = "http://www.w3.org/2003/05/soap-envelope";
  public static final String WsaNamespace = "http://www.w3.org/2005/08/addressing";
  public static final String WspNamespace = "http://schemas.xmlsoap.org/ws/2004/09/policy";
  public static final String WsscNamespace = "http://schemas.xmlsoap.org/ws/2005/02/sc";
  public static final String WsseNamespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
  public static final String WstNamespace = "http://schemas.xmlsoap.org/ws/2005/02/trust";
  public static final String WsuNamespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
  
  protected void addEnvelopeNamespaces(Element paramElement)
  {
    paramElement.setAttribute("xmlns:ps", "http://schemas.microsoft.com/Passport/SoapServices/PPCRL");
    paramElement.setAttribute("xmlns:wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
    paramElement.setAttribute("xmlns:saml", "urn:oasis:names:tc:SAML:1.0:assertion");
    paramElement.setAttribute("xmlns:wsp", "http://schemas.xmlsoap.org/ws/2004/09/policy");
    paramElement.setAttribute("xmlns:wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
    paramElement.setAttribute("xmlns:wsa", "http://www.w3.org/2005/08/addressing");
    paramElement.setAttribute("xmlns:wssc", "http://schemas.xmlsoap.org/ws/2005/02/sc");
    paramElement.setAttribute("xmlns:wst", "http://schemas.xmlsoap.org/ws/2005/02/trust");
  }
  
  protected final void appendDeviceDAToken(Element paramElement, DAToken paramDAToken)
  {
    paramElement = Requests.appendElement(paramElement, "wsse:BinarySecurityToken", paramDAToken.getOneTimeSignedCredential(getClockSkewManager().getCurrentServerTime(), "{F501FD64-9070-46AB-993C-6F7B71D8D883}"));
    paramElement.setAttribute("ValueType", "urn:liveid:sha1device");
    paramElement.setAttribute("Id", "DeviceDAToken");
  }
  
  protected final void appendTimestamp(Element paramElement)
  {
    paramElement = Requests.appendElement(paramElement, "wsu:Timestamp");
    paramElement.setAttribute("wsu:Id", "Timestamp");
    paramElement.setAttribute("xmlns:wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    Date localDate1 = getClockSkewManager().getCurrentServerTime();
    Date localDate2 = new Date(localDate1.getTime() + 300000L);
    Requests.appendElement(paramElement, "wsu:Created", localSimpleDateFormat.format(localDate1));
    Requests.appendElement(paramElement, "wsu:Expires", localSimpleDateFormat.format(localDate2));
    if ((this instanceof ISignableRequest)) {
      ((ISignableRequest)this).getXmlSigner().addElementToSign(paramElement);
    }
  }
  
  protected void buildAuthInfo(Element paramElement)
  {
    Requests.appendElement(paramElement, "ps:BinaryVersion", "11");
    Requests.appendElement(paramElement, "ps:DeviceType", "Android");
  }
  
  public Document buildRequest()
  {
    Document localDocument = createBlankDocument("http://www.w3.org/2003/05/soap-envelope", "s:Envelope");
    Object localObject = localDocument.getDocumentElement();
    addEnvelopeNamespaces((Element)localObject);
    buildSoapHeader(Requests.appendElement((Node)localObject, "s:Header"));
    buildSoapBody(Requests.appendElement((Node)localObject, "s:Body"));
    if ((this instanceof ISignableRequest))
    {
      localObject = (ISignableRequest)this;
      ((ISignableRequest)localObject).getXmlSigner().sign((ISignableRequest)localObject);
    }
    return localDocument;
  }
  
  protected abstract void buildSecurityNode(Element paramElement);
  
  protected abstract void buildSoapBody(Element paramElement);
  
  protected void buildSoapHeader(Element paramElement)
  {
    Requests.appendElement(paramElement, "wsa:Action", "http://schemas.xmlsoap.org/ws/2005/02/trust/RST/Issue").setAttribute("s:mustUnderstand", "1");
    Requests.appendElement(paramElement, "wsa:To", getDestination().toString()).setAttribute("s:mustUnderstand", "1");
    Requests.appendElement(paramElement, "wsa:MessageID", String.valueOf(System.currentTimeMillis()));
    Element localElement = Requests.appendElement(paramElement, "ps:AuthInfo");
    localElement.setAttribute("xmlns:ps", "http://schemas.microsoft.com/Passport/SoapServices/PPCRL");
    localElement.setAttribute("Id", "PPAuthInfo");
    buildAuthInfo(localElement);
    if ((this instanceof ISignableRequest)) {
      ((ISignableRequest)this).getXmlSigner().addElementToSign(localElement);
    }
    buildSecurityNode(Requests.appendElement(paramElement, "wsse:Security"));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\request\AbstractSoapRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */