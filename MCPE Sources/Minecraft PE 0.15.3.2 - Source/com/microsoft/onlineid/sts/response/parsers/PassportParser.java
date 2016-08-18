package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.sts.UserProperties;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PassportParser
  extends BasePullParser
{
  private int _authState;
  private String _configVersion;
  private final Set<Integer> _flights = new HashSet();
  private String _inlineAuthUrl;
  private String _nonce;
  private String _puid;
  private int _reqStatus;
  private UserProperties _userProperties;
  
  public PassportParser(XmlPullParser paramXmlPullParser)
  {
    super(paramXmlPullParser, null, "pp");
  }
  
  public int getAuthState()
  {
    verifyParseCalled();
    return this._authState;
  }
  
  public String getConfigVersion()
  {
    verifyParseCalled();
    return this._configVersion;
  }
  
  public Set<Integer> getFlights()
  {
    verifyParseCalled();
    return this._flights;
  }
  
  public String getInlineAuthUrl()
  {
    verifyParseCalled();
    return this._inlineAuthUrl;
  }
  
  public String getNonce()
  {
    verifyParseCalled();
    return this._nonce;
  }
  
  public String getPuid()
  {
    verifyParseCalled();
    return this._puid;
  }
  
  public int getReqStatus()
  {
    verifyParseCalled();
    return this._reqStatus;
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
      Object localObject = getPrefixedTagName();
      if (((String)localObject).equals("psf:authstate"))
      {
        this._authState = TextParsers.parseIntHex(nextRequiredText());
      }
      else if (((String)localObject).equals("psf:reqstatus"))
      {
        this._reqStatus = TextParsers.parseIntHex(nextRequiredText());
      }
      else if (((String)localObject).equals("psf:inlineauthurl"))
      {
        this._inlineAuthUrl = nextRequiredText();
      }
      else if (((String)localObject).equals("psf:signChallenge"))
      {
        this._nonce = nextRequiredText();
      }
      else if (((String)localObject).equals("psf:configVersion"))
      {
        this._configVersion = nextRequiredText();
      }
      else if (((String)localObject).equals("psf:PUID"))
      {
        this._puid = nextRequiredText();
      }
      else
      {
        if (((String)localObject).equals("psf:flights"))
        {
          localObject = nextRequiredText().split(",");
          int j = localObject.length;
          int i = 0;
          while (i < j)
          {
            String str = localObject[i];
            this._flights.add(Integer.valueOf(TextParsers.parseIntHex(str)));
            i += 1;
          }
        }
        if (((String)localObject).equals("psf:credProperties"))
        {
          localObject = new UserPropertiesParser(this._parser);
          ((UserPropertiesParser)localObject).parse();
          this._userProperties = ((UserPropertiesParser)localObject).getUserProperties();
        }
        else
        {
          skipElement();
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\response\parsers\PassportParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */