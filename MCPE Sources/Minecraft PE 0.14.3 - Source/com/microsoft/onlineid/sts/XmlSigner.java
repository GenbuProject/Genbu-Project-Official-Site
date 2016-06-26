package com.microsoft.onlineid.sts;

import android.util.Base64;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.sts.request.ISignableRequest;
import com.microsoft.onlineid.sts.request.Requests;
import java.io.CharArrayWriter;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlSigner
{
  public static final String SignatureNamespace = "http://www.w3.org/2000/09/xmldsig#";
  private final MessageDigest _elementDigester = Cryptography.getSha256Digester();
  private final List<Element> _elementsToDigest = new ArrayList();
  private byte[] _nonce = null;
  
  private String getId(Element paramElement)
  {
    if (paramElement.getNodeName().equals("wsu:Timestamp")) {}
    for (String str = "wsu:Id";; str = "Id") {
      return paramElement.getAttribute(str);
    }
  }
  
  private byte[] getOrCreateNonce()
  {
    if (this._nonce == null)
    {
      this._nonce = new byte[32];
      new SecureRandom().nextBytes(this._nonce);
    }
    return this._nonce;
  }
  
  private Transformer getTransformer()
  {
    try
    {
      Transformer localTransformer = TransformerFactory.newInstance().newTransformer();
      return localTransformer;
    }
    catch (TransformerConfigurationException localTransformerConfigurationException)
    {
      throw new RuntimeException(localTransformerConfigurationException);
    }
    catch (TransformerFactoryConfigurationError localTransformerFactoryConfigurationError)
    {
      throw new RuntimeException(localTransformerFactoryConfigurationError);
    }
  }
  
  public void addElementToSign(Element paramElement)
  {
    this._elementsToDigest.add(paramElement);
  }
  
  String buildSignedInfoTag()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<SignedInfo xmlns=\"").append("http://www.w3.org/2000/09/xmldsig#").append("\">").append("<CanonicalizationMethod Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\">").append("</CanonicalizationMethod>").append("<SignatureMethod Algorithm=\"http://www.w3.org/2001/04/xmldsig-more#hmac-sha256\">").append("</SignatureMethod>");
    Iterator localIterator = this._elementsToDigest.iterator();
    while (localIterator.hasNext())
    {
      Element localElement = (Element)localIterator.next();
      String str = computeDigest(elementToCanonicalizedString(localElement));
      localStringBuilder.append("<Reference URI=\"#").append(getId(localElement)).append("\">").append("<Transforms>").append("<Transform Algorithm=\"http://www.w3.org/2001/10/xml-exc-c14n#\"></Transform>").append("</Transforms>").append("<DigestMethod Algorithm=\"http://www.w3.org/2001/04/xmlenc#sha256\"></DigestMethod>").append("<DigestValue>").append(str).append("</DigestValue>").append("</Reference>");
    }
    localStringBuilder.append("</SignedInfo>");
    return localStringBuilder.toString();
  }
  
  public String computeDigest(String paramString)
  {
    return Base64.encodeToString(this._elementDigester.digest(paramString.getBytes(Strings.Utf8Charset)), 2);
  }
  
  String computeSignatureForRequest(byte[] paramArrayOfByte, String paramString)
  {
    return computeSignatureImplementation(paramArrayOfByte, getOrCreateNonce(), paramString);
  }
  
  public String computeSignatureForResponse(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString)
  {
    return computeSignatureImplementation(paramArrayOfByte1, paramArrayOfByte2, paramString.replace("<SignedInfo>", "<SignedInfo xmlns=\"http://www.w3.org/2000/09/xmldsig#\">"));
  }
  
  String computeSignatureImplementation(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString)
  {
    return Base64.encodeToString(Cryptography.getInitializedHmacSha256Digester(new SecretKeySpec(new SharedKeyGenerator(paramArrayOfByte1).generateKey(SharedKeyGenerator.KeyPurpose.STSDigest, paramArrayOfByte2), "HmacSHA256")).doFinal(paramString.getBytes(Strings.Utf8Charset)), 2);
  }
  
  String elementToCanonicalizedString(Element paramElement)
  {
    paramElement = new DOMSource(paramElement);
    StreamResult localStreamResult = new StreamResult(new CharArrayWriter());
    Transformer localTransformer = getTransformer();
    localTransformer.setOutputProperty("method", "html");
    localTransformer.setOutputProperty("indent", "no");
    try
    {
      localTransformer.transform(paramElement, localStreamResult);
      return localStreamResult.getWriter().toString();
    }
    catch (TransformerException paramElement)
    {
      throw new RuntimeException(paramElement);
    }
  }
  
  public String getEncodedNonce()
  {
    return Base64.encodeToString(getOrCreateNonce(), 2);
  }
  
  public void sign(ISignableRequest paramISignableRequest)
  {
    Element localElement = paramISignableRequest.getParentOfSignatureNode();
    Document localDocument = localElement.getOwnerDocument();
    paramISignableRequest = paramISignableRequest.getSigningSessionKey();
    String str = buildSignedInfoTag();
    paramISignableRequest = "<Signature xmlns=\"" + "http://www.w3.org/2000/09/xmldsig#" + "\">" + str + "<SignatureValue>" + computeSignatureForRequest(paramISignableRequest, str) + "</SignatureValue>" + "<KeyInfo>" + "<wsse:SecurityTokenReference><wsse:Reference URI=\"#SignKey\"/></wsse:SecurityTokenReference>" + "</KeyInfo>" + "</Signature>";
    try
    {
      paramISignableRequest = Requests.xmlStringToElement(paramISignableRequest);
      localElement.appendChild(localDocument.importNode(paramISignableRequest, true));
      return;
    }
    catch (SAXException paramISignableRequest)
    {
      throw new RuntimeException(paramISignableRequest);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\XmlSigner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */