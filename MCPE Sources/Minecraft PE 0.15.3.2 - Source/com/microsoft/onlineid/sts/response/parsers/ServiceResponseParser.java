package com.microsoft.onlineid.sts.response.parsers;

import android.text.TextUtils;
import android.util.Xml;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.log.RedactableResponse;
import com.microsoft.onlineid.sts.Cryptography;
import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.SharedKeyGenerator;
import com.microsoft.onlineid.sts.SharedKeyGenerator.KeyPurpose;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.UserProperties;
import com.microsoft.onlineid.sts.UserProperties.UserProperty;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ServiceResponseParser
  extends BasePullParser
{
  private int _authState;
  private String _configVersion;
  private DAToken _daToken;
  private byte[] _encKeyNonce;
  private StsError _error;
  private Date _expires;
  private Set<Integer> _flights;
  private String _inlineAuthUrl;
  private String _puid;
  private int _reqStatus;
  private final byte[] _sessionKey;
  private Ticket _ticket;
  private StsError _ticketError;
  private String _ticketInlineAuthUrl;
  private final ISecurityScope _ticketScope;
  private UserProperties _userProperties;
  
  public ServiceResponseParser(XmlPullParser paramXmlPullParser, byte[] paramArrayOfByte)
  {
    this(paramXmlPullParser, paramArrayOfByte, null);
  }
  
  public ServiceResponseParser(XmlPullParser paramXmlPullParser, byte[] paramArrayOfByte, ISecurityScope paramISecurityScope)
  {
    super(paramXmlPullParser, "http://www.w3.org/2003/05/soap-envelope", "Envelope");
    this._sessionKey = paramArrayOfByte;
    this._ticketScope = paramISecurityScope;
  }
  
  private String decryptEncryptedBlob(String paramString)
    throws StsParseException
  {
    paramString = TextParsers.parseBase64(paramString);
    byte[] arrayOfByte = new SharedKeyGenerator(this._sessionKey).generateKey(SharedKeyGenerator.KeyPurpose.STSDigest, this._encKeyNonce);
    try
    {
      paramString = new String(Cryptography.decryptWithAesCbcPcs5PaddingCipher(paramString, arrayOfByte), Strings.Utf8Charset);
      return paramString;
    }
    catch (IllegalBlockSizeException paramString)
    {
      throw new StsParseException(paramString);
    }
    catch (BadPaddingException paramString)
    {
      throw new StsParseException(paramString);
    }
  }
  
  private void parseAndSaveFromPassport(String paramString)
    throws StsParseException, IOException, XmlPullParserException
  {
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    localXmlPullParser.setInput(new StringReader(paramString));
    paramString = new PassportParser(localXmlPullParser);
    paramString.parse();
    saveFromPassport(paramString);
  }
  
  private void parseAndSaveFromTokenCollection(String paramString)
    throws XmlPullParserException, IOException, StsParseException
  {
    XmlPullParser localXmlPullParser = Xml.newPullParser();
    localXmlPullParser.setInput(new StringReader(paramString));
    paramString = new TokenCollectionParser(localXmlPullParser, this._ticketScope);
    paramString.parse();
    this._daToken = paramString.getDAToken();
    this._ticket = paramString.getTicket();
    this._ticketError = paramString.getTicketError();
    this._ticketInlineAuthUrl = paramString.getTicketInlineAuthUrl();
  }
  
  private void saveFromPassport(PassportParser paramPassportParser)
    throws StsParseException, IOException, XmlPullParserException
  {
    this._authState = paramPassportParser.getAuthState();
    this._reqStatus = paramPassportParser.getReqStatus();
    this._inlineAuthUrl = paramPassportParser.getInlineAuthUrl();
    this._configVersion = paramPassportParser.getConfigVersion();
    this._puid = paramPassportParser.getPuid();
    this._userProperties = paramPassportParser.getUserProperties();
    this._flights = paramPassportParser.getFlights();
    if ((this._userProperties != null) && (this._userProperties.get(UserProperties.UserProperty.CID) == null)) {
      throw new StsParseException("CID not found.", new Object[0]);
    }
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
  
  public byte[] getEncKeyNonce()
  {
    verifyParseCalled();
    return this._encKeyNonce;
  }
  
  public StsError getError()
  {
    verifyParseCalled();
    return this._error;
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
  
  public Date getResponseExpiry()
  {
    verifyParseCalled();
    return this._expires;
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
  
  public UserProperties getUserProperties()
  {
    verifyParseCalled();
    return this._userProperties;
  }
  
  protected void onParse()
    throws IOException, StsParseException, XmlPullParserException
  {
    PassportParser localPassportParser = null;
    Object localObject2 = null;
    Object localObject1 = null;
    SignatureValidator localSignatureValidator = new SignatureValidator(this._sessionKey);
    while (nextStartTagNoThrow())
    {
      String str = getPrefixedTagName();
      if (str.equals("S:Header"))
      {
        localObject2 = new ServiceHeaderParser(this._parser, localSignatureValidator);
        ((ServiceHeaderParser)localObject2).parse();
        this._expires = ((ServiceHeaderParser)localObject2).getResponseExpiry();
        this._encKeyNonce = ((ServiceHeaderParser)localObject2).getEncKeyNonce();
        localPassportParser = ((ServiceHeaderParser)localObject2).getPassportParser();
        localObject2 = ((ServiceHeaderParser)localObject2).getEncryptedHeader();
      }
      else if (str.equals("S:Body"))
      {
        localObject1 = new ServiceBodyParser(localSignatureValidator.computeNodeDigest(this));
        ((ServiceBodyParser)localObject1).parse();
        this._error = ((ServiceBodyParser)localObject1).getError();
        localObject1 = ((ServiceBodyParser)localObject1).getEncryptedBody();
      }
      else
      {
        skipElement();
      }
    }
    if ((this._encKeyNonce != null) || (localSignatureValidator.canValidate())) {
      localSignatureValidator.validate();
    }
    if (!TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject2 = decryptEncryptedBlob((String)localObject2);
      Logger.info(new RedactableResponse("Decrypted service response header: " + (String)localObject2));
      parseAndSaveFromPassport((String)localObject2);
    }
    for (;;)
    {
      if (this._error == null)
      {
        localObject1 = decryptEncryptedBlob((String)localObject1);
        Logger.info(new RedactableResponse("Decrypted service response body: " + (String)localObject1));
        parseAndSaveFromTokenCollection((String)localObject1);
      }
      return;
      if (localPassportParser != null) {
        saveFromPassport(localPassportParser);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\response\parsers\ServiceResponseParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */