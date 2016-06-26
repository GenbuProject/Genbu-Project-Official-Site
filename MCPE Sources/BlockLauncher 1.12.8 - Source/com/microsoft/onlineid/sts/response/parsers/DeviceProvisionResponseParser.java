package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DeviceProvisionResponseParser
  extends BasePullParser
{
  private StsError _error;
  private String _puid;
  
  public DeviceProvisionResponseParser(XmlPullParser paramXmlPullParser)
  {
    super(paramXmlPullParser, "", "DeviceAddResponse");
  }
  
  public StsError getError()
  {
    verifyParseCalled();
    return this._error;
  }
  
  public String getPuid()
  {
    verifyParseCalled();
    return this._puid;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    if (Strings.equalsIgnoreCase(this._parser.getAttributeValue("", "Success"), "true"))
    {
      nextStartTag("puid");
      this._puid = nextRequiredText();
      return;
    }
    StringCodeErrorParser localStringCodeErrorParser = new StringCodeErrorParser(this._parser);
    localStringCodeErrorParser.parse();
    this._error = new StsError(localStringCodeErrorParser.getError());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\sts\response\parsers\DeviceProvisionResponseParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */