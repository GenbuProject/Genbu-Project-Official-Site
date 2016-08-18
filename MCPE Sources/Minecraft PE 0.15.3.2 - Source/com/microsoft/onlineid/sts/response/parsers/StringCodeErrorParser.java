package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.internal.Integers;
import com.microsoft.onlineid.sts.StringCodeServerError;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class StringCodeErrorParser
  extends BasePullParser
{
  private String _code;
  private StringCodeServerError _error;
  private Integer _subCode;
  
  public StringCodeErrorParser(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException
  {
    super(paramXmlPullParser, null, null);
  }
  
  public StringCodeServerError getError()
  {
    verifyParseCalled();
    return this._error;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    while (nextStartTagNoThrow())
    {
      String str = this._parser.getName();
      if (str.equals("Error"))
      {
        this._code = this._parser.getAttributeValue("", "Code");
      }
      else if (str.equals("ErrorSubcode"))
      {
        str = this._parser.nextText();
        try
        {
          this._subCode = Integer.valueOf(Integers.parseIntHex(str));
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          throw new StsParseException("Hex error code could not be parsed: %s.", localIllegalArgumentException, new Object[] { str });
        }
      }
      else
      {
        skipElement();
      }
    }
    if (this._code == null) {
      throw new StsParseException("Required node \"Error\" is missing or empty.", new Object[0]);
    }
    if (this._subCode == null) {
      throw new StsParseException("Required node \"ErrorSubcode\" is missing.", new Object[0]);
    }
    this._error = new StringCodeServerError(this._code, this._subCode.intValue());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\response\parsers\StringCodeErrorParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */