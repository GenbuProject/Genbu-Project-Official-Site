package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.sts.UserProperties;
import com.microsoft.onlineid.sts.UserProperties.UserProperty;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class UserPropertiesParser
  extends BasePullParser
{
  private final UserProperties _userProperties = new UserProperties();
  
  public UserPropertiesParser(XmlPullParser paramXmlPullParser)
  {
    super(paramXmlPullParser, "http://schemas.microsoft.com/Passport/SoapServices/SOAPFault", "credProperties");
  }
  
  public UserProperties getUserProperties()
  {
    verifyParseCalled();
    return this._userProperties;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    while (nextStartTagNoThrow())
    {
      Object localObject = this._parser.getAttributeValue("", "Name");
      if (localObject == null) {
        skipElement();
      } else {
        try
        {
          localObject = UserProperties.UserProperty.valueOf((String)localObject);
          this._userProperties.put((UserProperties.UserProperty)localObject, this._parser.nextText());
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
          skipElement();
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\sts\response\parsers\UserPropertiesParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */