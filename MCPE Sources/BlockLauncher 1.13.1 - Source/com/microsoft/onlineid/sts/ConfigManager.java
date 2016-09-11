package com.microsoft.onlineid.sts;

import android.content.Context;
import android.text.TextUtils;
import android.util.Xml;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.configuration.Environment;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.internal.transport.Transport;
import com.microsoft.onlineid.internal.transport.TransportFactory;
import com.microsoft.onlineid.sts.exception.StsParseException;
import com.microsoft.onlineid.sts.response.parsers.ConfigParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConfigManager
{
  private final Context _applicationContext;
  private ServerConfig _config;
  private TypedStorage _storage;
  
  public ConfigManager(Context paramContext)
  {
    this._applicationContext = paramContext;
  }
  
  static long compareVersions(String paramString1, String paramString2)
  {
    label24:
    int j;
    if (TextUtils.isEmpty(paramString1))
    {
      paramString1 = new String[0];
      if (!TextUtils.isEmpty(paramString2)) {
        break label89;
      }
      paramString2 = new String[0];
      j = 0;
      i = 0;
      label28:
      if ((j < paramString1.length) || (j < paramString2.length)) {
        if (j >= paramString1.length) {
          break label112;
        }
      }
    }
    label89:
    label112:
    for (int i = Integer.parseInt(paramString1[j]);; i = 0)
    {
      if (j < paramString2.length) {}
      for (int k = Integer.parseInt(paramString2[j]);; k = 0)
      {
        i -= k;
        if (i != 0)
        {
          return i;
          paramString1 = paramString1.split("\\.");
          break;
          paramString2 = paramString2.split("\\.");
          break label24;
        }
        j += 1;
        break label28;
      }
    }
  }
  
  private boolean downloadConfiguration(Environment paramEnvironment)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    Logger.info("Downloading new PPCRL config file (" + paramEnvironment + ").");
    Transport localTransport = getTransportFactory().createTransport();
    for (;;)
    {
      try
      {
        localTransport.openGetRequest(paramEnvironment.getConfigUrl());
        i = localTransport.getResponseCode();
        if (i != 200) {
          continue;
        }
        bool2 = parseConfig(localTransport.getResponseStream(), paramEnvironment);
        bool1 = bool2;
      }
      catch (NetworkException paramEnvironment)
      {
        int i;
        Logger.error("Failed to update ppcrlconfig.", paramEnvironment);
        ClientAnalytics.get().logException(paramEnvironment);
        localTransport.closeConnection();
        continue;
      }
      catch (IOException paramEnvironment)
      {
        Logger.error("Failed to update ppcrlconfig.", paramEnvironment);
        ClientAnalytics.get().logException(paramEnvironment);
        localTransport.closeConnection();
        continue;
      }
      catch (XmlPullParserException paramEnvironment)
      {
        Logger.error("Failed to update ppcrlconfig.", paramEnvironment);
        ClientAnalytics.get().logException(paramEnvironment);
        localTransport.closeConnection();
        continue;
      }
      catch (StsParseException paramEnvironment)
      {
        Logger.error("Failed to update ppcrlconfig.", paramEnvironment);
        ClientAnalytics.get().logException(paramEnvironment);
        localTransport.closeConnection();
        continue;
      }
      finally
      {
        localTransport.closeConnection();
      }
      if (!bool1) {
        break;
      }
      Logger.info("Successfully updated ppcrlconfig to version: " + getCurrentConfigVersion());
      getStorage().writeConfigLastDownloadedTime();
      return bool1;
      Logger.error("Failed to update ppcrlconfig due to HTTP response code " + i);
      bool1 = bool2;
    }
    Logger.error("Failed to update ppcrlconfig (parseConfig() returned false).");
    return bool1;
  }
  
  protected ServerConfig getConfig()
  {
    if (this._config == null) {
      this._config = new ServerConfig(this._applicationContext);
    }
    return this._config;
  }
  
  public String getCurrentConfigVersion()
  {
    return getConfig().getString(ServerConfig.Version);
  }
  
  protected TypedStorage getStorage()
  {
    if (this._storage == null) {
      this._storage = new TypedStorage(this._applicationContext);
    }
    return this._storage;
  }
  
  protected TransportFactory getTransportFactory()
  {
    return new TransportFactory(this._applicationContext);
  }
  
  public boolean hasConfigBeenUpdatedRecently(long paramLong)
  {
    return (System.currentTimeMillis() - paramLong) / 1000L < getConfig().getInt(ServerConfig.Int.MinSecondsBetweenConfigDownloads);
  }
  
  public boolean isClientConfigVersionOlder(String paramString)
  {
    boolean bool = false;
    try
    {
      long l = compareVersions(paramString, getCurrentConfigVersion());
      if (l < 0L) {
        bool = true;
      }
      return bool;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      Logger.warning("Invalid client version: " + paramString, localNumberFormatException);
    }
    return false;
  }
  
  protected boolean parseConfig(InputStream paramInputStream, Environment paramEnvironment)
    throws IOException, XmlPullParserException, StsParseException
  {
    try
    {
      XmlPullParser localXmlPullParser = Xml.newPullParser();
      localXmlPullParser.setInput(paramInputStream, null);
      Integer localInteger = getConfig().getNgcCloudPinLength();
      ServerConfig.Editor localEditor = getConfig().edit();
      localEditor.clear();
      localEditor.setString(ServerConfig.EnvironmentName, paramEnvironment.getEnvironmentName());
      localEditor.setUrl(ServerConfig.Endpoint.Configuration, paramEnvironment.getConfigUrl());
      localEditor.setInt(ServerConfig.NgcCloudPinLength, localInteger.intValue());
      new ConfigParser(localXmlPullParser, localEditor).parse();
      boolean bool = localEditor.commit();
      return bool;
    }
    finally
    {
      paramInputStream.close();
    }
  }
  
  public boolean switchEnvironment(Environment paramEnvironment)
  {
    if (paramEnvironment.equals(getConfig().getEnvironment())) {
      return true;
    }
    return downloadConfiguration(paramEnvironment);
  }
  
  public boolean update()
  {
    return downloadConfiguration(getConfig().getEnvironment());
  }
  
  public boolean updateIfFirstDownloadNeeded()
  {
    if (compareVersions(getCurrentConfigVersion(), "1") == 0L) {
      return update();
    }
    return true;
  }
  
  public boolean updateIfNeeded(String paramString)
  {
    if (hasConfigBeenUpdatedRecently(getStorage().readConfigLastDownloadedTime())) {}
    for (;;)
    {
      return true;
      String str = getCurrentConfigVersion();
      Logger.info(String.format(Locale.US, "Checking for PPCRL config update from version \"%s\" to version \"%s\"", new Object[] { str, paramString }));
      try
      {
        long l = compareVersions(paramString, str);
        if (l > 0L) {
          return downloadConfiguration(getConfig().getEnvironment());
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Logger.warning("Invalid server configuration requested: " + paramString, localNumberFormatException);
      }
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\sts\ConfigManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */