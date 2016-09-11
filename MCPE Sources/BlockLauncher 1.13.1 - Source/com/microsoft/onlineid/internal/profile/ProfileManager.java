package com.microsoft.onlineid.internal.profile;

import android.content.Context;
import android.content.Intent;
import android.util.JsonReader;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.SecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.ApiRequest;
import com.microsoft.onlineid.internal.MsaService;
import com.microsoft.onlineid.internal.configuration.Environment;
import com.microsoft.onlineid.internal.exception.AccountNotFoundException;
import com.microsoft.onlineid.internal.exception.PromptNeededException;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.internal.transport.Transport;
import com.microsoft.onlineid.internal.transport.TransportFactory;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.sts.ClockSkewManager;
import com.microsoft.onlineid.sts.ServerConfig;
import com.microsoft.onlineid.sts.ServerConfig.KnownEnvironment;
import com.microsoft.onlineid.sts.exception.InvalidResponseException;
import com.microsoft.onlineid.sts.exception.StsException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

public class ProfileManager
{
  protected static final String AppIdRequestProperty = "PS-ApplicationId";
  protected static final String AuthTicketRequestProperty = "PS-MSAAuthTicket";
  protected static final String ClientVersion = "MSA Android";
  protected static final String ClientVersionRequestProperty = "X-ClientVersion";
  protected static final String OrderedBasicNameAttributeName = "PublicProfile.OrderedBasicName";
  protected static final String ProfileAppId = "F5EF4246-47B3-403A-885B-023BBAE0F547";
  protected static final ISecurityScope ProfileServiceScopeInt = new SecurityScope("ssl.live-int.com", "mbi_ssl");
  protected static final ISecurityScope ProfileServiceScopeProduction = new SecurityScope("ssl.live.com", "mbi_ssl");
  protected static final String ProfileServiceUrlInt = "https://directory.services.live-int.com/profile/mine/WLX.Profiles.IC.json";
  protected static final String ProfileServiceUrlProduction = "https://pf.directory.live.com/profile/mine/WLX.Profiles.IC.json";
  private final Context _applicationContext;
  private final ClockSkewManager _clockSkewManager;
  private final JsonParser _jsonParser;
  private final ServerConfig _serverConfig;
  private final TicketManager _ticketManager;
  private final TransportFactory _transportFactory;
  private final TypedStorage _typedStorage;
  
  @Deprecated
  public ProfileManager()
  {
    this._applicationContext = null;
    this._clockSkewManager = null;
    this._jsonParser = null;
    this._serverConfig = null;
    this._ticketManager = null;
    this._transportFactory = null;
    this._typedStorage = null;
  }
  
  public ProfileManager(Context paramContext)
  {
    this._applicationContext = paramContext;
    this._clockSkewManager = new ClockSkewManager(paramContext);
    this._jsonParser = new JsonParser();
    this._serverConfig = new ServerConfig(paramContext);
    this._ticketManager = new TicketManager(paramContext);
    this._transportFactory = new TransportFactory(paramContext);
    this._typedStorage = new TypedStorage(paramContext);
  }
  
  public ApiRequest createUpdateProfileRequest(String paramString)
  {
    return new ApiRequest(this._applicationContext, new Intent(this._applicationContext, MsaService.class).setAction("com.microsoft.onlineid.internal.UPDATE_PROFILE")).setAccountPuid(paramString);
  }
  
  public void updateProfile(String paramString1, String paramString2)
    throws IOException, NetworkException, AccountNotFoundException, PromptNeededException, InvalidResponseException, StsException
  {
    boolean bool = this._serverConfig.getEnvironment().equals(ServerConfig.KnownEnvironment.Production.getEnvironment());
    Ticket localTicket;
    Transport localTransport;
    Object localObject2;
    if (bool)
    {
      localObject1 = ProfileServiceScopeProduction;
      localTicket = this._ticketManager.getTicket(paramString1, (ISecurityScope)localObject1, paramString2, true);
      localTransport = this._transportFactory.createTransport();
      localObject2 = null;
      if (!bool) {
        break label221;
      }
    }
    label221:
    for (Object localObject1 = "https://pf.directory.live.com/profile/mine/WLX.Profiles.IC.json";; localObject1 = "https://directory.services.live-int.com/profile/mine/WLX.Profiles.IC.json")
    {
      paramString2 = (String)localObject2;
      try
      {
        localTransport.openGetRequest(new URL((String)localObject1));
        paramString2 = (String)localObject2;
        localTransport.addRequestProperty("PS-ApplicationId", "F5EF4246-47B3-403A-885B-023BBAE0F547");
        paramString2 = (String)localObject2;
        localTransport.addRequestProperty("PS-MSAAuthTicket", localTicket.getValue());
        paramString2 = (String)localObject2;
        localTransport.addRequestProperty("X-ClientVersion", "MSA Android");
        paramString2 = (String)localObject2;
        localObject1 = localTransport.getResponseStream();
        paramString2 = (String)localObject1;
        localObject2 = new JsonReader(new BufferedReader(new InputStreamReader((InputStream)localObject1)));
        paramString2 = (String)localObject1;
        localObject2 = this._jsonParser.parseDisplayName((JsonReader)localObject2);
        paramString2 = (String)localObject1;
        paramString1 = this._typedStorage.readAccount(paramString1);
        if (paramString1 != null) {
          break label228;
        }
        paramString2 = (String)localObject1;
        throw new AccountNotFoundException("Account was deleted before operation could be completed.");
      }
      finally
      {
        localTransport.closeConnection();
        if (paramString2 != null) {
          paramString2.close();
        }
      }
      localObject1 = ProfileServiceScopeInt;
      break;
    }
    label228:
    paramString2 = (String)localObject1;
    paramString1.setDisplayName((String)localObject2);
    paramString2 = (String)localObject1;
    paramString1.setTimeOfLastProfileUpdate(this._clockSkewManager.getCurrentServerTime().getTime());
    paramString2 = (String)localObject1;
    this._typedStorage.writeAccount(paramString1);
    localTransport.closeConnection();
    if (localObject1 != null) {
      ((InputStream)localObject1).close();
    }
  }
  
  protected static class JsonParser
  {
    protected void findName(JsonReader paramJsonReader, String paramString)
      throws IOException
    {
      while (paramJsonReader.hasNext())
      {
        if (paramString.equals(paramJsonReader.nextName())) {
          return;
        }
        paramJsonReader.skipValue();
      }
      throw new IOException("Unable to find name " + paramString);
    }
    
    protected String parseDisplayName(JsonReader paramJsonReader)
      throws IOException
    {
      try
      {
        paramJsonReader.beginObject();
        findName(paramJsonReader, "Views");
        paramJsonReader.beginArray();
        paramJsonReader.beginObject();
        findName(paramJsonReader, "Attributes");
        paramJsonReader.beginArray();
        while (paramJsonReader.hasNext())
        {
          String str = readDisplayNameFromEntry(paramJsonReader);
          if (str != null) {
            return str;
          }
        }
        return null;
      }
      finally
      {
        paramJsonReader.close();
      }
    }
    
    protected String readDisplayNameFromEntry(JsonReader paramJsonReader)
      throws IOException
    {
      Object localObject1 = null;
      paramJsonReader.beginObject();
      String str1 = null;
      String str2 = null;
      while (paramJsonReader.hasNext())
      {
        Object localObject2 = paramJsonReader.nextName();
        if ("Name".equals(localObject2))
        {
          str2 = paramJsonReader.nextString();
        }
        else if ("Value".equals(localObject2))
        {
          localObject2 = paramJsonReader.peek();
          switch (ProfileManager.1.$SwitchMap$android$util$JsonToken[localObject2.ordinal()])
          {
          default: 
            paramJsonReader.skipValue();
            break;
          case 1: 
            paramJsonReader.nextNull();
            str1 = null;
            break;
          case 2: 
            str1 = paramJsonReader.nextString();
            break;
          }
        }
        else
        {
          paramJsonReader.skipValue();
        }
      }
      paramJsonReader.endObject();
      paramJsonReader = (JsonReader)localObject1;
      if ("PublicProfile.OrderedBasicName".equals(str2)) {
        paramJsonReader = str1;
      }
      return paramJsonReader;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\profile\ProfileManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */