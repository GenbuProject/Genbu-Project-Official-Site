package com.microsoft.onlineid.sts.response;

import android.text.TextUtils;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.sts.ClockSkewManager;
import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.UserProperties;
import com.microsoft.onlineid.sts.exception.StsParseException;
import com.microsoft.onlineid.sts.response.parsers.ServiceResponseParser;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;

public class ServiceResponse
  extends AbstractSoapResponse
{
  private final ClockSkewManager _clockSkewManager;
  private final byte[] _decryptionSessionKey;
  private ServiceResponseParser _parser;
  private final ISecurityScope _ticketScope;
  
  public ServiceResponse(byte[] paramArrayOfByte, ISecurityScope paramISecurityScope, ClockSkewManager paramClockSkewManager)
  {
    this._decryptionSessionKey = paramArrayOfByte;
    this._ticketScope = paramISecurityScope;
    this._clockSkewManager = paramClockSkewManager;
  }
  
  public ServiceResponse(byte[] paramArrayOfByte, ClockSkewManager paramClockSkewManager)
  {
    this(paramArrayOfByte, null, paramClockSkewManager);
  }
  
  public String getConfigVersion()
  {
    return this._parser.getConfigVersion();
  }
  
  public DAToken getDAToken()
  {
    return this._parser.getDAToken();
  }
  
  public StsError getError()
  {
    StsError localStsError2 = this._parser.getError();
    StsError localStsError1 = localStsError2;
    if (localStsError2 == null) {
      localStsError1 = this._parser.getTicketError();
    }
    return localStsError1;
  }
  
  public Set<Integer> getFlights()
  {
    return this._parser.getFlights();
  }
  
  public String getInlineAuthUrl()
  {
    String str2 = this._parser.getInlineAuthUrl();
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = this._parser.getTicketInlineAuthUrl();
    }
    return str1;
  }
  
  public String getPuid()
  {
    return this._parser.getPuid();
  }
  
  public Ticket getTicket()
  {
    return this._parser.getTicket();
  }
  
  public StsError getTicketError()
  {
    return this._parser.getTicketError();
  }
  
  public UserProperties getUserProperties()
  {
    return this._parser.getUserProperties();
  }
  
  protected void parse(XmlPullParser paramXmlPullParser)
    throws StsParseException, IOException
  {
    if (this._parser != null) {
      throw new IllegalStateException("Each response object may only parse its respone once.");
    }
    this._parser = new ServiceResponseParser(paramXmlPullParser, this._decryptionSessionKey, this._ticketScope);
    this._parser.parse();
    validateExpirationTime();
  }
  
  protected void validateExpirationTime()
    throws StsParseException
  {
    Date localDate1 = this._clockSkewManager.getCurrentServerTime();
    Date localDate2 = this._parser.getResponseExpiry();
    if ((localDate2 != null) && (localDate1.after(localDate2))) {
      throw new StsParseException("Response is expired. Current time: %s Expiry Time: %s", new Object[] { localDate1.toString(), localDate2.toString() });
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\response\ServiceResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */