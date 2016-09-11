package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SecurityTokenParser
  extends BasePullParser
{
  private String _tokenBlob;
  
  public SecurityTokenParser(XmlPullParser paramXmlPullParser)
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
    while (nextStartTagNoThrow())
    {
      String str = getPrefixedTagName();
      boolean bool;
      if (str.equals("EncryptedData"))
      {
        if (this._tokenBlob == null) {}
        for (bool = true;; bool = false)
        {
          Assertion.check(bool);
          this._tokenBlob = readRawOuterXml();
          break;
        }
      }
      if (str.equals("wsse:BinarySecurityToken"))
      {
        if (this._tokenBlob == null) {}
        for (bool = true;; bool = false)
        {
          Assertion.check(bool);
          this._tokenBlob = nextRequiredText();
          break;
        }
      }
      skipElement();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\sts\response\parsers\SecurityTokenParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */