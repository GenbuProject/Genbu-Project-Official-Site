package com.microsoft.onlineid.sts.response.parsers;

import android.text.TextUtils;
import android.text.TextUtils.SimpleStringSplitter;
import com.microsoft.onlineid.internal.configuration.ISetting;
import com.microsoft.onlineid.sts.ServerConfig;
import com.microsoft.onlineid.sts.ServerConfig.Editor;
import com.microsoft.onlineid.sts.ServerConfig.Endpoint;
import com.microsoft.onlineid.sts.ServerConfig.Int;
import com.microsoft.onlineid.sts.exception.StsParseException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConfigParser
  extends BasePullParser
{
  static final String CfgNamespace = "http://schemas.microsoft.com/Passport/PPCRL";
  static final String DefaultNamespace = "http://www.w3.org/2000/09/xmldsig#";
  private final ServerConfig.Editor _editor;
  private final Map<String, ServerConfig.Endpoint> _endpointSettings;
  private final Map<String, ISetting<Integer>> _intSettings;
  private final Map<String, ISetting<Set<String>>> _stringSetSettings;
  private final Map<String, ISetting<String>> _stringSettings;
  
  public ConfigParser(XmlPullParser paramXmlPullParser, ServerConfig.Editor paramEditor)
  {
    super(paramXmlPullParser, "http://www.w3.org/2000/09/xmldsig#", "Signature");
    this._editor = paramEditor;
    this._stringSettings = new HashMap();
    addSetting(this._stringSettings, ServerConfig.Version);
    this._intSettings = new HashMap();
    paramXmlPullParser = ServerConfig.Int.values();
    int j = paramXmlPullParser.length;
    int i = 0;
    while (i < j)
    {
      paramEditor = paramXmlPullParser[i];
      addSetting(this._intSettings, paramEditor);
      i += 1;
    }
    this._endpointSettings = new HashMap();
    paramXmlPullParser = ServerConfig.Endpoint.values();
    j = paramXmlPullParser.length;
    i = 0;
    while (i < j)
    {
      paramEditor = paramXmlPullParser[i];
      addSetting(this._endpointSettings, paramEditor);
      i += 1;
    }
    this._stringSetSettings = new HashMap();
    addSetting(this._stringSetSettings, ServerConfig.AndroidSsoCertificates);
  }
  
  protected <V, T extends ISetting<V>> void addSetting(Map<String, T> paramMap, T paramT)
  {
    paramMap.put(paramT.getSettingName(), paramT);
  }
  
  protected void onParse()
    throws XmlPullParserException, IOException, StsParseException
  {
    nextStartTag("cfg:Configuration");
    NodeScope localNodeScope = getLocation();
    while (localNodeScope.nextStartTagNoThrow())
    {
      String str = getPrefixedTagName();
      if ((str.equalsIgnoreCase("cfg:Settings")) || (str.equalsIgnoreCase("cfg:ServiceURIs")) || (str.equalsIgnoreCase("cfg:ServiceURIs1"))) {
        parseSettings();
      } else {
        localNodeScope.skipElement();
      }
    }
  }
  
  protected void parseSettings()
    throws IOException, XmlPullParserException, StsParseException
  {
    NodeScope localNodeScope = getLocation();
    while (localNodeScope.nextStartTagNoThrow())
    {
      String str = this._parser.getName();
      if ((!tryParseStringSetting(str)) && (!tryParseIntSetting(str)) && (!tryParseEndpointSetting(str)) && (!tryParseStringSetSetting(str))) {
        localNodeScope.skipElement();
      }
    }
  }
  
  protected boolean tryParseEndpointSetting(String paramString)
    throws StsParseException, XmlPullParserException, IOException
  {
    ServerConfig.Endpoint localEndpoint = (ServerConfig.Endpoint)this._endpointSettings.get(paramString);
    if (localEndpoint != null)
    {
      this._editor.setUrl(localEndpoint, TextParsers.parseUrl(nextRequiredText(), paramString));
      return true;
    }
    return false;
  }
  
  protected boolean tryParseIntSetting(String paramString)
    throws StsParseException, XmlPullParserException, IOException
  {
    ISetting localISetting = (ISetting)this._intSettings.get(paramString);
    if (localISetting != null)
    {
      this._editor.setInt(localISetting, TextParsers.parseInt(nextRequiredText(), paramString));
      return true;
    }
    return false;
  }
  
  protected boolean tryParseStringSetSetting(String paramString)
    throws StsParseException, XmlPullParserException, IOException
  {
    paramString = (ISetting)this._stringSetSettings.get(paramString);
    if (paramString != null)
    {
      Object localObject = nextRequiredText();
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        HashSet localHashSet = new HashSet();
        TextUtils.SimpleStringSplitter localSimpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
        localSimpleStringSplitter.setString((String)localObject);
        localObject = localSimpleStringSplitter.iterator();
        while (((Iterator)localObject).hasNext()) {
          localHashSet.add((String)((Iterator)localObject).next());
        }
        this._editor.setStringSet(paramString, localHashSet);
      }
      return true;
    }
    return false;
  }
  
  protected boolean tryParseStringSetting(String paramString)
    throws StsParseException, XmlPullParserException, IOException
  {
    paramString = (ISetting)this._stringSettings.get(paramString);
    if (paramString != null)
    {
      this._editor.setString(paramString, nextRequiredText());
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\response\parsers\ConfigParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */