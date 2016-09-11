package com.microsoft.onlineid.sts.response;

import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.exception.StsParseException;
import com.microsoft.onlineid.sts.response.parsers.DeviceProvisionResponseParser;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;

public class DeviceProvisionResponse
  extends AbstractStsResponse
{
  private DeviceProvisionResponseParser _parser;
  
  public StsError getError()
  {
    return this._parser.getError();
  }
  
  public String getPuid()
  {
    return this._parser.getPuid();
  }
  
  protected void parse(XmlPullParser paramXmlPullParser)
    throws IOException, StsParseException
  {
    if (this._parser != null) {
      throw new IllegalStateException("Each response object may only parse its respone once.");
    }
    this._parser = new DeviceProvisionResponseParser(paramXmlPullParser);
    this._parser.parse();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\sts\response\DeviceProvisionResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */