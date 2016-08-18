package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.sts.ClockSkewManager;
import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import java.util.Date;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DeviceAuthResponseParser
  extends BasePullParser
{
  private int _authState;
  private final ClockSkewManager _clockSkewManager;
  private String _configVersion;
  private DAToken _daToken;
  private StsError _error;
  private Date _expires;
  private int _reqStatus;
  
  public DeviceAuthResponseParser(XmlPullParser paramXmlPullParser, ClockSkewManager paramClockSkewManager)
  {
    super(paramXmlPullParser, "http://www.w3.org/2003/05/soap-envelope", "Envelope");
    this._clockSkewManager = paramClockSkewManager;
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
  
  public DAToken getDAToken()
  {
    verifyParseCalled();
    return this._daToken;
  }
  
  public StsError getError()
  {
    verifyParseCalled();
    return this._error;
  }
  
  public int getReqStatus()
  {
    verifyParseCalled();
    return this._reqStatus;
  }
  
  Date getResponseExpiry()
  {
    verifyParseCalled();
    return this._expires;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    while (nextStartTagNoThrow())
    {
      localObject = getPrefixedTagName();
      if (((String)localObject).equals("S:Header"))
      {
        localObject = new ServiceHeaderParser(this._parser);
        ((ServiceHeaderParser)localObject).parse();
        this._expires = ((ServiceHeaderParser)localObject).getResponseExpiry();
        localObject = ((ServiceHeaderParser)localObject).getPassportParser();
        if (localObject == null) {
          throw new StsParseException("Missing passport node in device auth response.", new Object[0]);
        }
        this._authState = ((PassportParser)localObject).getAuthState();
        this._reqStatus = ((PassportParser)localObject).getReqStatus();
        this._configVersion = ((PassportParser)localObject).getConfigVersion();
      }
      else if (((String)localObject).equals("S:Body"))
      {
        localObject = new ServiceBodyParser(this._parser);
        ((ServiceBodyParser)localObject).parse();
        this._error = ((ServiceBodyParser)localObject).getError();
        this._daToken = ((ServiceBodyParser)localObject).getDAToken();
      }
      else
      {
        skipElement();
      }
    }
    if ((this._error == null) && (this._expires == null)) {
      throw new StsParseException("S:Header tag not found", new Object[0]);
    }
    if ((this._error == null) && (this._daToken == null)) {
      throw new StsParseException("S:Body tag not found", new Object[0]);
    }
    Object localObject = this._clockSkewManager.getCurrentServerTime();
    if ((this._expires != null) && (((Date)localObject).after(this._expires))) {
      throw new StsParseException("Response is expired. Current time: %s Expiry Time: %s", new Object[] { ((Date)localObject).toString(), this._expires.toString() });
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\response\parsers\DeviceAuthResponseParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */