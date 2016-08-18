package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import java.util.Date;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TimeListParser
  extends BasePullParser
{
  private Date _expires;
  
  public TimeListParser(XmlPullParser paramXmlPullParser)
  {
    super(paramXmlPullParser, null, null);
  }
  
  public Date getExpires()
  {
    verifyParseCalled();
    return this._expires;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    nextStartTag("wsu:Expires");
    DateParser localDateParser = new DateParser(this._parser, DateParser.DateType.Iso8601DateTimeIgnoreTimeZone);
    localDateParser.parse();
    this._expires = localDateParser.getDate();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\response\parsers\TimeListParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */