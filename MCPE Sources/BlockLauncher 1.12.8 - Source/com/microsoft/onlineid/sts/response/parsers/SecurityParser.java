package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import java.util.Date;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SecurityParser
  extends BasePullParser
{
  private byte[] _encKeyNonce;
  private Date _expires;
  private final SignatureValidator _validator;
  
  public SecurityParser(XmlPullParser paramXmlPullParser)
  {
    this(paramXmlPullParser, null);
  }
  
  public SecurityParser(XmlPullParser paramXmlPullParser, SignatureValidator paramSignatureValidator)
  {
    super(paramXmlPullParser, "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security");
    this._validator = paramSignatureValidator;
  }
  
  public byte[] getEncKeyNonce()
  {
    verifyParseCalled();
    return this._encKeyNonce;
  }
  
  public Date getResponseExpiry()
  {
    verifyParseCalled();
    return this._expires;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    while (nextStartTagNoThrow())
    {
      Object localObject = getPrefixedTagName();
      if (((String)localObject).equals("wsu:Timestamp"))
      {
        if (this._validator != null) {}
        for (localObject = this._validator.computeNodeDigest(this);; localObject = this._parser)
        {
          localObject = new TimeListParser((XmlPullParser)localObject);
          ((TimeListParser)localObject).parse();
          this._expires = ((TimeListParser)localObject).getExpires();
          break;
        }
      }
      if (((String)localObject).equals("wssc:DerivedKeyToken"))
      {
        localObject = this._parser.getAttributeValue("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "Id");
        DerivedKeyTokenParser localDerivedKeyTokenParser = new DerivedKeyTokenParser(this._parser);
        localDerivedKeyTokenParser.parse();
        if ("EncKey".equals(localObject)) {
          this._encKeyNonce = localDerivedKeyTokenParser.getNonce();
        } else if (("SignKey".equals(localObject)) && (this._validator != null)) {
          this._validator.setSignKeyNonce(localDerivedKeyTokenParser.getNonce());
        }
      }
      else if (("Signature".equals(localObject)) && (this._validator != null))
      {
        this._validator.parseSignatureNode(this);
      }
      else
      {
        skipElement();
      }
    }
    if (this._expires == null) {
      throw new StsParseException("wsu:Timestamp node not found.", new Object[0]);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\sts\response\parsers\SecurityParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */