package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class EncryptedSoapNodeParser
  extends BasePullParser
{
  private String _cipherValue;
  
  public EncryptedSoapNodeParser(XmlPullParser paramXmlPullParser)
  {
    this(paramXmlPullParser, "EncryptedData");
  }
  
  public EncryptedSoapNodeParser(XmlPullParser paramXmlPullParser, String paramString)
  {
    super(paramXmlPullParser, "http://www.w3.org/2001/04/xmlenc#", paramString);
  }
  
  public String getCipherValue()
  {
    verifyParseCalled();
    return this._cipherValue;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    nextStartTag("CipherData");
    NodeScope localNodeScope = getLocation();
    localNodeScope.nextStartTag("CipherValue");
    this._cipherValue = nextRequiredText();
    localNodeScope.finish();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\response\parsers\EncryptedSoapNodeParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */