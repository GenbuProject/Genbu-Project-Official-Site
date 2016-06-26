package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DerivedKeyTokenParser
  extends BasePullParser
{
  private byte[] _nonce;
  
  public DerivedKeyTokenParser(XmlPullParser paramXmlPullParser)
  {
    super(paramXmlPullParser, "http://schemas.xmlsoap.org/ws/2005/02/sc", "DerivedKeyToken");
  }
  
  public byte[] getNonce()
  {
    verifyParseCalled();
    return this._nonce;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    nextStartTag("wssc:Nonce");
    this._nonce = TextParsers.parseBase64(nextRequiredText());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\sts\response\parsers\DerivedKeyTokenParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */