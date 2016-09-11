package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ProofTokenParser
  extends BasePullParser
{
  private byte[] _sessionKey;
  
  public ProofTokenParser(XmlPullParser paramXmlPullParser)
  {
    super(paramXmlPullParser, "http://schemas.xmlsoap.org/ws/2005/02/trust", "RequestedProofToken");
  }
  
  public byte[] getSessionKey()
  {
    verifyParseCalled();
    return this._sessionKey;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    while (nextStartTagNoThrow())
    {
      String str = getPrefixedTagName();
      boolean bool;
      if (str.equals("EncryptedKey"))
      {
        new EncryptedSoapNodeParser(this._parser, "EncryptedKey").parse();
        if (this._sessionKey == null) {}
        for (bool = true;; bool = false)
        {
          Assertion.check(bool, "Only one of EncryptedKey or wst:BinarySecret is expected");
          this._sessionKey = null;
          break;
        }
      }
      if (str.equals("wst:BinarySecret"))
      {
        if (this._sessionKey == null) {}
        for (bool = true;; bool = false)
        {
          Assertion.check(bool, "Only one of EncryptedKey or wst:BinarySecret is expected");
          this._sessionKey = TextParsers.parseBase64(nextRequiredText());
          break;
        }
      }
      skipElement();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\sts\response\parsers\ProofTokenParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */