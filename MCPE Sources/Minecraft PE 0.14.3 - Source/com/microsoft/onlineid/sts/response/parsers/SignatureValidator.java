package com.microsoft.onlineid.sts.response.parsers;

import android.text.TextUtils;
import android.util.Xml;
import com.microsoft.onlineid.sts.XmlSigner;
import com.microsoft.onlineid.sts.exception.StsParseException;
import com.microsoft.onlineid.sts.exception.StsSignatureException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SignatureValidator
{
  private final Map<String, String> _computedDigests;
  private final Map<String, String> _parsedDigests;
  private final byte[] _sessionKey;
  private byte[] _signKeyNonce;
  private String _signatureValue;
  private String _signedInfoXml;
  private final XmlSigner _signer;
  
  public SignatureValidator(byte[] paramArrayOfByte)
  {
    this._sessionKey = paramArrayOfByte;
    this._signer = new XmlSigner();
    this._computedDigests = new HashMap();
    this._parsedDigests = new HashMap();
  }
  
  private void parseSignedInfoNode(BasePullParser paramBasePullParser)
    throws StsParseException, IOException, XmlPullParserException
  {
    this._signedInfoXml = paramBasePullParser.readRawOuterXml();
    paramBasePullParser = Xml.newPullParser();
    paramBasePullParser.setInput(new StringReader(this._signedInfoXml));
    new BasePullParser(paramBasePullParser, null, "SignedInfo")
    {
      protected void onParse()
        throws XmlPullParserException, IOException, StsParseException
      {
        while (nextStartTagNoThrow("Reference"))
        {
          String str = this._parser.getAttributeValue(null, "URI");
          Object localObject = getLocation();
          if (!((NodeScope)localObject).nextStartTagNoThrow("DigestValue")) {
            throw new StsSignatureException("Missing DigestValue for URI " + str, new Object[0]);
          }
          localObject = ((NodeScope)localObject).nextRequiredText();
          if ((TextUtils.isEmpty(str)) || (!str.startsWith("#"))) {
            throw new StsSignatureException("Invalid digest URI: " + str, new Object[0]);
          }
          if (TextUtils.isEmpty((CharSequence)localObject)) {
            throw new StsSignatureException("Invalid digest: " + (String)localObject, new Object[0]);
          }
          str = str.substring(1);
          SignatureValidator.this._parsedDigests.put(str, localObject);
        }
      }
    }.parse();
  }
  
  public boolean canValidate()
  {
    return (this._sessionKey != null) && (!TextUtils.isEmpty(this._signedInfoXml)) && (this._signKeyNonce != null) && (!TextUtils.isEmpty(this._signatureValue));
  }
  
  public XmlPullParser computeNodeDigest(BasePullParser paramBasePullParser)
    throws XmlPullParserException, IOException, StsParseException
  {
    Object localObject = paramBasePullParser._parser;
    ((XmlPullParser)localObject).require(2, null, null);
    String str = ((XmlPullParser)localObject).getAttributeValue(null, "Id");
    if (!TextUtils.isEmpty(str))
    {
      paramBasePullParser = paramBasePullParser.readRawOuterXml();
      localObject = this._signer.computeDigest(paramBasePullParser);
      if (this._computedDigests.containsKey(str)) {
        throw new StsSignatureException("Duplicate element for Id=\"" + str + "\"", new Object[0]);
      }
      this._computedDigests.put(str, localObject);
      localObject = Xml.newPullParser();
      ((XmlPullParser)localObject).setInput(new StringReader(paramBasePullParser));
    }
    return (XmlPullParser)localObject;
  }
  
  public void parseSignatureNode(BasePullParser paramBasePullParser)
    throws StsParseException, IOException, XmlPullParserException
  {
    new BasePullParser(paramBasePullParser._parser, "http://www.w3.org/2000/09/xmldsig#", "Signature")
    {
      protected void onParse()
        throws XmlPullParserException, IOException, StsParseException
      {
        while (nextStartTagNoThrow())
        {
          String str = getPrefixedTagName();
          if ("SignedInfo".equals(str)) {
            SignatureValidator.this.parseSignedInfoNode(this);
          } else if ("SignatureValue".equals(str)) {
            SignatureValidator.access$102(SignatureValidator.this, nextRequiredText());
          } else {
            skipElement();
          }
        }
        if (TextUtils.isEmpty(SignatureValidator.this._signatureValue)) {
          throw new StsSignatureException("<SignatureValue> node was missing.", new Object[0]);
        }
        if (TextUtils.isEmpty(SignatureValidator.this._signedInfoXml)) {
          throw new StsSignatureException("<SignedInfo> node was missing.", new Object[0]);
        }
      }
    }.parse();
  }
  
  public void setSignKeyNonce(byte[] paramArrayOfByte)
  {
    this._signKeyNonce = paramArrayOfByte;
  }
  
  public void validate()
    throws StsSignatureException
  {
    Object localObject = this._computedDigests.entrySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      if (this._parsedDigests.containsKey(localEntry.getKey()))
      {
        String str = (String)this._parsedDigests.remove(localEntry.getKey());
        if (!str.equals(localEntry.getValue())) {
          throw new StsSignatureException(String.format(Locale.US, "Digest mismatch: id=\"%s\", expected=\"%s\", actual=\"%s\"", new Object[] { localEntry.getKey(), str, localEntry.getValue() }), new Object[0]);
        }
      }
    }
    if (!this._parsedDigests.isEmpty()) {
      throw new StsSignatureException("Failed to compute digests for element ids " + Arrays.toString(this._parsedDigests.keySet().toArray()), new Object[0]);
    }
    if (TextUtils.isEmpty(this._signedInfoXml)) {
      throw new StsSignatureException("<SignedInfo> node was missing.", new Object[0]);
    }
    if ((this._signKeyNonce == null) || (this._signKeyNonce.length == 0)) {
      throw new StsSignatureException("SignKey nonce was missing or invalid.", new Object[0]);
    }
    localObject = this._signer.computeSignatureForResponse(this._sessionKey, this._signKeyNonce, this._signedInfoXml);
    if (!this._signatureValue.equals(localObject)) {
      throw new StsSignatureException(String.format(Locale.US, "Signature mismatch: expected=\"%s\", actual=\"%s\"", new Object[] { this._signatureValue, localObject }), new Object[0]);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\response\parsers\SignatureValidator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */