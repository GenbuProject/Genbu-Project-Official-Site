package com.microsoft.onlineid.sts.response;

import com.microsoft.onlineid.sts.ClockSkewManager;
import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.exception.StsParseException;
import com.microsoft.onlineid.sts.response.parsers.DeviceAuthResponseParser;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;

public class DeviceAuthResponse
  extends AbstractSoapResponse
{
  private final ClockSkewManager _clockSkewManager;
  private DeviceAuthResponseParser _parser;
  
  public DeviceAuthResponse(ClockSkewManager paramClockSkewManager)
  {
    this._clockSkewManager = paramClockSkewManager;
  }
  
  public DAToken getDAToken()
  {
    return this._parser.getDAToken();
  }
  
  public StsError getError()
  {
    return this._parser.getError();
  }
  
  protected void parse(XmlPullParser paramXmlPullParser)
    throws IOException, StsParseException
  {
    if (this._parser != null) {
      throw new IllegalStateException("Each response object may only parse its respone once.");
    }
    this._parser = new DeviceAuthResponseParser(paramXmlPullParser, this._clockSkewManager);
    this._parser.parse();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\response\DeviceAuthResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */