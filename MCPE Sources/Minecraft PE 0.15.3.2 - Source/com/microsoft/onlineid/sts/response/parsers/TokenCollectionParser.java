package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TokenCollectionParser
  extends BasePullParser
{
  private DAToken _daToken;
  private Ticket _ticket;
  private StsError _ticketError;
  private String _ticketInlineAuthUrl;
  private final ISecurityScope _ticketScope;
  
  public TokenCollectionParser(XmlPullParser paramXmlPullParser)
  {
    this(paramXmlPullParser, null);
  }
  
  public TokenCollectionParser(XmlPullParser paramXmlPullParser, ISecurityScope paramISecurityScope)
  {
    super(paramXmlPullParser, "http://schemas.xmlsoap.org/ws/2005/02/trust", "RequestSecurityTokenResponseCollection");
    this._ticketScope = paramISecurityScope;
  }
  
  public DAToken getDAToken()
  {
    verifyParseCalled();
    return this._daToken;
  }
  
  public Ticket getTicket()
  {
    verifyParseCalled();
    return this._ticket;
  }
  
  public StsError getTicketError()
  {
    verifyParseCalled();
    return this._ticketError;
  }
  
  public String getTicketInlineAuthUrl()
  {
    verifyParseCalled();
    return this._ticketInlineAuthUrl;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    while (nextStartTagNoThrow("wst:RequestSecurityTokenResponse"))
    {
      TokenParser localTokenParser = new TokenParser(this._parser, this._ticketScope, TokenParser.SecurityTokenMode.ServiceRequest);
      localTokenParser.parse();
      if (localTokenParser.getDAToken() != null)
      {
        if (this._daToken == null)
        {
          bool = true;
          label48:
          Assertion.check(bool);
          this._daToken = localTokenParser.getDAToken();
        }
      }
      else
      {
        if (localTokenParser.getTicketError() != null)
        {
          if (this._ticketError != null) {
            break label132;
          }
          bool = true;
          label76:
          Assertion.check(bool);
          this._ticketError = localTokenParser.getTicketError();
          this._ticketInlineAuthUrl = localTokenParser.getTicketInlineAuthUrl();
        }
        if (localTokenParser.getTicket() == null) {
          continue;
        }
        if (this._ticket != null) {
          break label137;
        }
      }
      label132:
      label137:
      for (boolean bool = true;; bool = false)
      {
        Assertion.check(bool);
        this._ticket = localTokenParser.getTicket();
        break;
        bool = false;
        break label48;
        bool = false;
        break label76;
      }
    }
    if ((this._ticketScope != null) && (this._ticketError == null) && (this._ticket == null)) {
      throw new StsParseException("No ticket or ticket error found.", new Object[0]);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\response\parsers\TokenCollectionParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */