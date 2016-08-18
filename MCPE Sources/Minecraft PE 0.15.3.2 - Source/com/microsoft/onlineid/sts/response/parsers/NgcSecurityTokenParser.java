package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class NgcSecurityTokenParser
  extends BasePullParser
{
  private String _tokenBlob;
  
  public NgcSecurityTokenParser(XmlPullParser paramXmlPullParser)
  {
    super(paramXmlPullParser, "http://schemas.xmlsoap.org/ws/2005/02/trust", "RequestedSecurityToken");
  }
  
  public String getTokenBlob()
  {
    verifyParseCalled();
    return this._tokenBlob;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    nextStartTag("EncryptedData");
    EncryptedSoapNodeParser localEncryptedSoapNodeParser = new EncryptedSoapNodeParser(this._parser);
    localEncryptedSoapNodeParser.parse();
    this._tokenBlob = localEncryptedSoapNodeParser.getCipherValue();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\response\parsers\NgcSecurityTokenParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */