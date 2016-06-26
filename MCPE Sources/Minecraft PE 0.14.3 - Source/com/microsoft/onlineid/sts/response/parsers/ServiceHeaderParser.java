package com.microsoft.onlineid.sts.response.parsers;

import android.text.TextUtils;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import java.util.Date;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ServiceHeaderParser
  extends BasePullParser
{
  private byte[] _encKeyNonce;
  private String _encryptedHeader;
  private Date _expires;
  private PassportParser _passportParser;
  private final SignatureValidator _validator;
  
  public ServiceHeaderParser(XmlPullParser paramXmlPullParser)
  {
    this(paramXmlPullParser, null);
  }
  
  public ServiceHeaderParser(XmlPullParser paramXmlPullParser, SignatureValidator paramSignatureValidator)
  {
    super(paramXmlPullParser, "http://www.w3.org/2003/05/soap-envelope", "Header");
    this._validator = paramSignatureValidator;
  }
  
  public byte[] getEncKeyNonce()
  {
    verifyParseCalled();
    return this._encKeyNonce;
  }
  
  public String getEncryptedHeader()
  {
    verifyParseCalled();
    return this._encryptedHeader;
  }
  
  public PassportParser getPassportParser()
  {
    verifyParseCalled();
    return this._passportParser;
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
      Object localObject1 = getPrefixedTagName();
      Object localObject2 = this._parser.getAttributeValue("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "Id");
      if (((String)localObject1).equals("wsse:Security"))
      {
        localObject1 = new SecurityParser(this._parser, this._validator);
        ((SecurityParser)localObject1).parse();
        this._expires = ((SecurityParser)localObject1).getResponseExpiry();
        this._encKeyNonce = ((SecurityParser)localObject1).getEncKeyNonce();
      }
      else if (((String)localObject1).equals("psf:pp"))
      {
        this._passportParser = new PassportParser(this._parser);
        this._passportParser.parse();
      }
      else
      {
        if (((String)localObject1).equals("psf:EncryptedPP"))
        {
          localObject2 = getLocation();
          ((NodeScope)localObject2).nextStartTag("EncryptedData");
          if (this._validator != null) {}
          for (localObject1 = this._validator.computeNodeDigest(this);; localObject1 = this._parser)
          {
            localObject1 = new EncryptedSoapNodeParser((XmlPullParser)localObject1);
            ((EncryptedSoapNodeParser)localObject1).parse();
            this._encryptedHeader = ((EncryptedSoapNodeParser)localObject1).getCipherValue();
            ((NodeScope)localObject2).finish();
            break;
          }
        }
        if ((this._validator != null) && (!TextUtils.isEmpty((CharSequence)localObject2))) {
          this._validator.computeNodeDigest(this);
        } else {
          skipElement();
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\response\parsers\ServiceHeaderParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */