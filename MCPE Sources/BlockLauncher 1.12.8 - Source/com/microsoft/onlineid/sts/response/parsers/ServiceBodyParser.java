package com.microsoft.onlineid.sts.response.parsers;

import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ServiceBodyParser
  extends BasePullParser
{
  private DAToken _daToken;
  private String _encryptedBody;
  private StsError _error;
  
  public ServiceBodyParser(XmlPullParser paramXmlPullParser)
  {
    super(paramXmlPullParser, "http://www.w3.org/2003/05/soap-envelope", "Body");
  }
  
  public DAToken getDAToken()
  {
    verifyParseCalled();
    return this._daToken;
  }
  
  public String getEncryptedBody()
  {
    verifyParseCalled();
    return this._encryptedBody;
  }
  
  public StsError getError()
  {
    verifyParseCalled();
    return this._error;
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    while (nextStartTagNoThrow())
    {
      Object localObject = getPrefixedTagName();
      if (((String)localObject).equals("S:Fault"))
      {
        localObject = new FaultParser(this._parser);
        ((FaultParser)localObject).parse();
        this._error = ((FaultParser)localObject).getError();
      }
      else if (((String)localObject).equals("EncryptedData"))
      {
        localObject = new EncryptedSoapNodeParser(this._parser);
        ((EncryptedSoapNodeParser)localObject).parse();
        this._encryptedBody = ((EncryptedSoapNodeParser)localObject).getCipherValue();
      }
      else if (((String)localObject).equals("wst:RequestSecurityTokenResponse"))
      {
        localObject = new TokenParser(this._parser, TokenParser.SecurityTokenMode.ServiceRequest);
        ((TokenParser)localObject).parse();
        this._daToken = ((TokenParser)localObject).getDAToken();
      }
      else
      {
        skipElement();
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\sts\response\parsers\ServiceBodyParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */