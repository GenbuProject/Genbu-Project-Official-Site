package com.microsoft.cll.android;

import com.microsoft.telemetry.extensions.device;
import org.json.JSONObject;

public abstract class AbstractSettings
{
  protected SettingsStore.Settings ETagSettingName;
  protected String TAG = "AbstractSettings";
  protected final ClientTelemetry clientTelemetry;
  protected boolean disableUploadOn404 = false;
  protected String endpoint;
  protected final ILogger logger;
  private final PartA partA;
  
  protected AbstractSettings(ClientTelemetry paramClientTelemetry, ILogger paramILogger, PartA paramPartA)
  {
    this.clientTelemetry = paramClientTelemetry;
    this.logger = paramILogger;
    this.partA = paramPartA;
  }
  
  public abstract void ParseSettings(JSONObject paramJSONObject);
  
  protected String getQueryParameters()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('?');
    localStringBuilder.append("os=");
    localStringBuilder.append(this.partA.osName);
    localStringBuilder.append("&osVer=");
    localStringBuilder.append(this.partA.osVer);
    localStringBuilder.append("&deviceClass=");
    localStringBuilder.append(this.partA.deviceExt.getDeviceClass());
    localStringBuilder.append("&deviceId=");
    localStringBuilder.append(this.partA.deviceExt.getLocalId());
    return localStringBuilder.toString();
  }
  
  /* Error */
  public JSONObject getSettings()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   4: aload_0
    //   5: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   8: ldc 92
    //   10: invokeinterface 98 3 0
    //   15: new 100	java/net/URL
    //   18: dup
    //   19: new 41	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 42	java/lang/StringBuilder:<init>	()V
    //   26: aload_0
    //   27: getfield 102	com/microsoft/cll/android/AbstractSettings:endpoint	Ljava/lang/String;
    //   30: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_0
    //   34: invokevirtual 104	com/microsoft/cll/android/AbstractSettings:getQueryParameters	()Ljava/lang/String;
    //   37: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   43: invokespecial 107	java/net/URL:<init>	(Ljava/lang/String;)V
    //   46: astore 6
    //   48: aconst_null
    //   49: astore 5
    //   51: aconst_null
    //   52: astore_3
    //   53: aconst_null
    //   54: astore 4
    //   56: aload 6
    //   58: invokevirtual 111	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   61: astore 6
    //   63: aload 6
    //   65: astore 4
    //   67: aload 6
    //   69: astore 5
    //   71: aload 6
    //   73: astore_3
    //   74: aload 6
    //   76: instanceof 113
    //   79: ifeq +945 -> 1024
    //   82: aload 6
    //   84: astore 4
    //   86: aload 6
    //   88: astore 5
    //   90: aload 6
    //   92: astore_3
    //   93: aload_0
    //   94: getfield 30	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   97: invokevirtual 118	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpAttempts	()V
    //   100: aload 6
    //   102: astore 4
    //   104: aload 6
    //   106: astore 5
    //   108: aload 6
    //   110: astore_3
    //   111: aload 6
    //   113: checkcast 113	javax/net/ssl/HttpsURLConnection
    //   116: astore 8
    //   118: aload 6
    //   120: astore 4
    //   122: aload 6
    //   124: astore 5
    //   126: aload 6
    //   128: astore_3
    //   129: aload 8
    //   131: getstatic 123	com/microsoft/cll/android/SettingsStore$Settings:HTTPTIMEOUTINTERVAL	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   134: invokestatic 129	com/microsoft/cll/android/SettingsStore:getCllSettingsAsInt	(Lcom/microsoft/cll/android/SettingsStore$Settings;)I
    //   137: invokevirtual 133	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
    //   140: aload 6
    //   142: astore 4
    //   144: aload 6
    //   146: astore 5
    //   148: aload 6
    //   150: astore_3
    //   151: aload 8
    //   153: ldc -121
    //   155: invokevirtual 138	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   158: aload 6
    //   160: astore 4
    //   162: aload 6
    //   164: astore 5
    //   166: aload 6
    //   168: astore_3
    //   169: aload 8
    //   171: ldc -116
    //   173: ldc -114
    //   175: invokevirtual 145	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   178: aload 6
    //   180: astore 4
    //   182: aload 6
    //   184: astore 5
    //   186: aload 6
    //   188: astore_3
    //   189: aload 8
    //   191: ldc -109
    //   193: aload_0
    //   194: getfield 149	com/microsoft/cll/android/AbstractSettings:ETagSettingName	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   197: invokestatic 153	com/microsoft/cll/android/SettingsStore:getCllSettingsAsString	(Lcom/microsoft/cll/android/SettingsStore$Settings;)Ljava/lang/String;
    //   200: invokevirtual 145	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   203: aload 6
    //   205: astore 4
    //   207: aload 6
    //   209: astore 5
    //   211: aload 6
    //   213: astore_3
    //   214: ldc -101
    //   216: invokestatic 161	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   219: getstatic 167	java/util/Locale:US	Ljava/util/Locale;
    //   222: invokestatic 173	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
    //   225: invokevirtual 177	java/util/Calendar:getTimeInMillis	()J
    //   228: lstore_1
    //   229: aload 6
    //   231: astore 4
    //   233: aload 6
    //   235: astore 5
    //   237: aload 6
    //   239: astore_3
    //   240: aload 8
    //   242: invokevirtual 180	javax/net/ssl/HttpsURLConnection:connect	()V
    //   245: aload 6
    //   247: astore 4
    //   249: aload 6
    //   251: astore 5
    //   253: aload 6
    //   255: astore_3
    //   256: ldc -101
    //   258: invokestatic 161	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   261: getstatic 167	java/util/Locale:US	Ljava/util/Locale;
    //   264: invokestatic 173	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
    //   267: invokevirtual 177	java/util/Calendar:getTimeInMillis	()J
    //   270: lload_1
    //   271: lsub
    //   272: lstore_1
    //   273: aload 6
    //   275: astore 4
    //   277: aload 6
    //   279: astore 5
    //   281: aload 6
    //   283: astore_3
    //   284: aload_0
    //   285: getfield 30	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   288: lload_1
    //   289: l2i
    //   290: invokevirtual 183	com/microsoft/cll/android/ClientTelemetry:SetAvgSettingsLatencyMs	(I)V
    //   293: aload 6
    //   295: astore 4
    //   297: aload 6
    //   299: astore 5
    //   301: aload 6
    //   303: astore_3
    //   304: aload_0
    //   305: getfield 30	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   308: lload_1
    //   309: l2i
    //   310: invokevirtual 186	com/microsoft/cll/android/ClientTelemetry:SetMaxSettingsLatencyMs	(I)V
    //   313: aload 6
    //   315: astore 4
    //   317: aload 6
    //   319: astore 5
    //   321: aload 6
    //   323: astore_3
    //   324: aload 8
    //   326: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   329: sipush 404
    //   332: if_icmpne +246 -> 578
    //   335: aload 6
    //   337: astore 4
    //   339: aload 6
    //   341: astore 5
    //   343: aload 6
    //   345: astore_3
    //   346: aload_0
    //   347: getfield 28	com/microsoft/cll/android/AbstractSettings:disableUploadOn404	Z
    //   350: ifeq +228 -> 578
    //   353: aload 6
    //   355: astore 4
    //   357: aload 6
    //   359: astore 5
    //   361: aload 6
    //   363: astore_3
    //   364: aload_0
    //   365: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   368: aload_0
    //   369: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   372: ldc -64
    //   374: invokeinterface 98 3 0
    //   379: aload 6
    //   381: astore 4
    //   383: aload 6
    //   385: astore 5
    //   387: aload 6
    //   389: astore_3
    //   390: getstatic 195	com/microsoft/cll/android/SettingsStore$Settings:UPLOADENABLED	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   393: ldc -59
    //   395: invokestatic 201	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   398: aload 6
    //   400: astore 4
    //   402: aload 6
    //   404: astore 5
    //   406: aload 6
    //   408: astore_3
    //   409: aload 8
    //   411: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   414: sipush 200
    //   417: if_icmpeq +25 -> 442
    //   420: aload 6
    //   422: astore 4
    //   424: aload 6
    //   426: astore 5
    //   428: aload 6
    //   430: astore_3
    //   431: aload 8
    //   433: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   436: sipush 304
    //   439: if_icmpne +276 -> 715
    //   442: aload 6
    //   444: astore 4
    //   446: aload 6
    //   448: astore 5
    //   450: aload 6
    //   452: astore_3
    //   453: aload 8
    //   455: ldc -53
    //   457: invokevirtual 207	javax/net/ssl/HttpsURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   460: astore 7
    //   462: aload 7
    //   464: ifnull +42 -> 506
    //   467: aload 6
    //   469: astore 4
    //   471: aload 6
    //   473: astore 5
    //   475: aload 6
    //   477: astore_3
    //   478: aload 7
    //   480: invokevirtual 213	java/lang/String:isEmpty	()Z
    //   483: ifne +23 -> 506
    //   486: aload 6
    //   488: astore 4
    //   490: aload 6
    //   492: astore 5
    //   494: aload 6
    //   496: astore_3
    //   497: aload_0
    //   498: getfield 149	com/microsoft/cll/android/AbstractSettings:ETagSettingName	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   501: aload 7
    //   503: invokestatic 201	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   506: aload 6
    //   508: astore 4
    //   510: aload 6
    //   512: astore 5
    //   514: aload 6
    //   516: astore_3
    //   517: aload 8
    //   519: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   522: sipush 200
    //   525: if_icmpeq +296 -> 821
    //   528: aload 6
    //   530: astore 4
    //   532: aload 6
    //   534: astore 5
    //   536: aload 6
    //   538: astore_3
    //   539: aload 8
    //   541: invokevirtual 216	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   544: aconst_null
    //   545: astore_3
    //   546: iconst_0
    //   547: ifeq +11 -> 558
    //   550: new 218	java/lang/NullPointerException
    //   553: dup
    //   554: invokespecial 219	java/lang/NullPointerException:<init>	()V
    //   557: athrow
    //   558: aload_3
    //   559: areturn
    //   560: astore_3
    //   561: aload_0
    //   562: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   565: aload_0
    //   566: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   569: ldc -35
    //   571: invokeinterface 224 3 0
    //   576: aconst_null
    //   577: areturn
    //   578: aload 6
    //   580: astore 4
    //   582: aload 6
    //   584: astore 5
    //   586: aload 6
    //   588: astore_3
    //   589: aload 8
    //   591: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   594: sipush 404
    //   597: if_icmpeq -199 -> 398
    //   600: aload 6
    //   602: astore 4
    //   604: aload 6
    //   606: astore 5
    //   608: aload 6
    //   610: astore_3
    //   611: aload_0
    //   612: getfield 28	com/microsoft/cll/android/AbstractSettings:disableUploadOn404	Z
    //   615: ifeq -217 -> 398
    //   618: aload 6
    //   620: astore 4
    //   622: aload 6
    //   624: astore 5
    //   626: aload 6
    //   628: astore_3
    //   629: aload_0
    //   630: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   633: aload_0
    //   634: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   637: ldc -30
    //   639: invokeinterface 98 3 0
    //   644: aload 6
    //   646: astore 4
    //   648: aload 6
    //   650: astore 5
    //   652: aload 6
    //   654: astore_3
    //   655: getstatic 195	com/microsoft/cll/android/SettingsStore$Settings:UPLOADENABLED	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   658: ldc -28
    //   660: invokestatic 201	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   663: goto -265 -> 398
    //   666: astore 5
    //   668: aload 4
    //   670: astore_3
    //   671: aload_0
    //   672: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   675: aload_0
    //   676: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   679: aload 5
    //   681: invokevirtual 231	java/io/IOException:getMessage	()Ljava/lang/String;
    //   684: invokeinterface 224 3 0
    //   689: aload 4
    //   691: astore_3
    //   692: aload_0
    //   693: getfield 30	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   696: iconst_m1
    //   697: invokevirtual 234	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpFailures	(I)V
    //   700: aload 4
    //   702: ifnull +11 -> 713
    //   705: aload 4
    //   707: invokevirtual 240	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   710: invokevirtual 245	java/io/InputStream:close	()V
    //   713: aconst_null
    //   714: areturn
    //   715: aload 6
    //   717: astore 4
    //   719: aload 6
    //   721: astore 5
    //   723: aload 6
    //   725: astore_3
    //   726: aload_0
    //   727: getfield 30	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   730: aload 8
    //   732: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   735: invokevirtual 234	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpFailures	(I)V
    //   738: goto -232 -> 506
    //   741: astore 4
    //   743: aload 5
    //   745: astore_3
    //   746: aload_0
    //   747: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   750: aload_0
    //   751: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   754: aload 4
    //   756: invokevirtual 246	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   759: invokeinterface 224 3 0
    //   764: aload 5
    //   766: ifnull -53 -> 713
    //   769: aload 5
    //   771: invokevirtual 240	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   774: invokevirtual 245	java/io/InputStream:close	()V
    //   777: goto -64 -> 713
    //   780: astore_3
    //   781: aload_0
    //   782: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   785: aload_0
    //   786: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   789: aload_3
    //   790: invokevirtual 247	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   793: invokeinterface 224 3 0
    //   798: goto -85 -> 713
    //   801: astore_3
    //   802: aload_0
    //   803: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   806: aload_0
    //   807: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   810: aload_3
    //   811: invokevirtual 247	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   814: invokeinterface 224 3 0
    //   819: aconst_null
    //   820: areturn
    //   821: aload 6
    //   823: astore 4
    //   825: aload 6
    //   827: astore 5
    //   829: aload 6
    //   831: astore_3
    //   832: new 249	java/io/BufferedReader
    //   835: dup
    //   836: new 251	java/io/InputStreamReader
    //   839: dup
    //   840: aload 8
    //   842: invokevirtual 252	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
    //   845: invokespecial 255	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   848: invokespecial 258	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   851: astore 10
    //   853: aload 6
    //   855: astore 4
    //   857: aload 6
    //   859: astore 5
    //   861: aload 6
    //   863: astore_3
    //   864: new 41	java/lang/StringBuilder
    //   867: dup
    //   868: invokespecial 42	java/lang/StringBuilder:<init>	()V
    //   871: astore 7
    //   873: aload 6
    //   875: astore 4
    //   877: aload 6
    //   879: astore 5
    //   881: aload 6
    //   883: astore_3
    //   884: aload 10
    //   886: invokevirtual 261	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   889: astore 9
    //   891: aload 9
    //   893: ifnull +41 -> 934
    //   896: aload 6
    //   898: astore 4
    //   900: aload 6
    //   902: astore 5
    //   904: aload 6
    //   906: astore_3
    //   907: aload 7
    //   909: aload 9
    //   911: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   914: pop
    //   915: goto -42 -> 873
    //   918: astore 4
    //   920: aload_3
    //   921: ifnull +10 -> 931
    //   924: aload_3
    //   925: invokevirtual 240	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   928: invokevirtual 245	java/io/InputStream:close	()V
    //   931: aload 4
    //   933: athrow
    //   934: aload 6
    //   936: astore 4
    //   938: aload 6
    //   940: astore 5
    //   942: aload 6
    //   944: astore_3
    //   945: aload 10
    //   947: invokevirtual 262	java/io/BufferedReader:close	()V
    //   950: aload 6
    //   952: astore 4
    //   954: aload 6
    //   956: astore 5
    //   958: aload 6
    //   960: astore_3
    //   961: aload 8
    //   963: invokevirtual 216	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   966: aconst_null
    //   967: astore 5
    //   969: aconst_null
    //   970: astore_3
    //   971: aconst_null
    //   972: astore 4
    //   974: new 264	org/json/JSONObject
    //   977: dup
    //   978: aload 7
    //   980: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   983: invokespecial 265	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   986: astore 6
    //   988: aload 6
    //   990: astore_3
    //   991: iconst_0
    //   992: ifeq -434 -> 558
    //   995: new 218	java/lang/NullPointerException
    //   998: dup
    //   999: invokespecial 219	java/lang/NullPointerException:<init>	()V
    //   1002: athrow
    //   1003: astore_3
    //   1004: aload_0
    //   1005: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   1008: aload_0
    //   1009: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   1012: aload_3
    //   1013: invokevirtual 247	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1016: invokeinterface 224 3 0
    //   1021: aload 6
    //   1023: areturn
    //   1024: aload 6
    //   1026: ifnull -313 -> 713
    //   1029: aload 6
    //   1031: invokevirtual 240	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   1034: invokevirtual 245	java/io/InputStream:close	()V
    //   1037: goto -324 -> 713
    //   1040: astore_3
    //   1041: aload_0
    //   1042: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   1045: aload_0
    //   1046: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   1049: aload_3
    //   1050: invokevirtual 247	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1053: invokeinterface 224 3 0
    //   1058: goto -345 -> 713
    //   1061: astore_3
    //   1062: aload_0
    //   1063: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   1066: aload_0
    //   1067: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   1070: aload_3
    //   1071: invokevirtual 247	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1074: invokeinterface 224 3 0
    //   1079: goto -366 -> 713
    //   1082: astore_3
    //   1083: aload_0
    //   1084: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   1087: aload_0
    //   1088: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   1091: aload_3
    //   1092: invokevirtual 247	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1095: invokeinterface 224 3 0
    //   1100: goto -169 -> 931
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1103	0	this	AbstractSettings
    //   228	81	1	l	long
    //   52	507	3	localObject1	Object
    //   560	1	3	localMalformedURLException	java.net.MalformedURLException
    //   588	158	3	localObject2	Object
    //   780	10	3	localException1	Exception
    //   801	10	3	localException2	Exception
    //   831	160	3	localObject3	Object
    //   1003	10	3	localException3	Exception
    //   1040	10	3	localException4	Exception
    //   1061	10	3	localException5	Exception
    //   1082	10	3	localException6	Exception
    //   54	664	4	localObject4	Object
    //   741	14	4	localJSONException	org.json.JSONException
    //   823	76	4	localObject5	Object
    //   918	14	4	localObject6	Object
    //   936	37	4	localObject7	Object
    //   49	602	5	localObject8	Object
    //   666	14	5	localIOException	java.io.IOException
    //   721	247	5	localObject9	Object
    //   46	984	6	localObject10	Object
    //   460	519	7	localObject11	Object
    //   116	846	8	localHttpsURLConnection	javax.net.ssl.HttpsURLConnection
    //   889	21	9	str	String
    //   851	95	10	localBufferedReader	java.io.BufferedReader
    // Exception table:
    //   from	to	target	type
    //   15	48	560	java/net/MalformedURLException
    //   56	63	666	java/io/IOException
    //   74	82	666	java/io/IOException
    //   93	100	666	java/io/IOException
    //   111	118	666	java/io/IOException
    //   129	140	666	java/io/IOException
    //   151	158	666	java/io/IOException
    //   169	178	666	java/io/IOException
    //   189	203	666	java/io/IOException
    //   214	229	666	java/io/IOException
    //   240	245	666	java/io/IOException
    //   256	273	666	java/io/IOException
    //   284	293	666	java/io/IOException
    //   304	313	666	java/io/IOException
    //   324	335	666	java/io/IOException
    //   346	353	666	java/io/IOException
    //   364	379	666	java/io/IOException
    //   390	398	666	java/io/IOException
    //   409	420	666	java/io/IOException
    //   431	442	666	java/io/IOException
    //   453	462	666	java/io/IOException
    //   478	486	666	java/io/IOException
    //   497	506	666	java/io/IOException
    //   517	528	666	java/io/IOException
    //   539	544	666	java/io/IOException
    //   589	600	666	java/io/IOException
    //   611	618	666	java/io/IOException
    //   629	644	666	java/io/IOException
    //   655	663	666	java/io/IOException
    //   726	738	666	java/io/IOException
    //   832	853	666	java/io/IOException
    //   864	873	666	java/io/IOException
    //   884	891	666	java/io/IOException
    //   907	915	666	java/io/IOException
    //   945	950	666	java/io/IOException
    //   961	966	666	java/io/IOException
    //   974	988	666	java/io/IOException
    //   56	63	741	org/json/JSONException
    //   74	82	741	org/json/JSONException
    //   93	100	741	org/json/JSONException
    //   111	118	741	org/json/JSONException
    //   129	140	741	org/json/JSONException
    //   151	158	741	org/json/JSONException
    //   169	178	741	org/json/JSONException
    //   189	203	741	org/json/JSONException
    //   214	229	741	org/json/JSONException
    //   240	245	741	org/json/JSONException
    //   256	273	741	org/json/JSONException
    //   284	293	741	org/json/JSONException
    //   304	313	741	org/json/JSONException
    //   324	335	741	org/json/JSONException
    //   346	353	741	org/json/JSONException
    //   364	379	741	org/json/JSONException
    //   390	398	741	org/json/JSONException
    //   409	420	741	org/json/JSONException
    //   431	442	741	org/json/JSONException
    //   453	462	741	org/json/JSONException
    //   478	486	741	org/json/JSONException
    //   497	506	741	org/json/JSONException
    //   517	528	741	org/json/JSONException
    //   539	544	741	org/json/JSONException
    //   589	600	741	org/json/JSONException
    //   611	618	741	org/json/JSONException
    //   629	644	741	org/json/JSONException
    //   655	663	741	org/json/JSONException
    //   726	738	741	org/json/JSONException
    //   832	853	741	org/json/JSONException
    //   864	873	741	org/json/JSONException
    //   884	891	741	org/json/JSONException
    //   907	915	741	org/json/JSONException
    //   945	950	741	org/json/JSONException
    //   961	966	741	org/json/JSONException
    //   974	988	741	org/json/JSONException
    //   769	777	780	java/lang/Exception
    //   550	558	801	java/lang/Exception
    //   56	63	918	finally
    //   74	82	918	finally
    //   93	100	918	finally
    //   111	118	918	finally
    //   129	140	918	finally
    //   151	158	918	finally
    //   169	178	918	finally
    //   189	203	918	finally
    //   214	229	918	finally
    //   240	245	918	finally
    //   256	273	918	finally
    //   284	293	918	finally
    //   304	313	918	finally
    //   324	335	918	finally
    //   346	353	918	finally
    //   364	379	918	finally
    //   390	398	918	finally
    //   409	420	918	finally
    //   431	442	918	finally
    //   453	462	918	finally
    //   478	486	918	finally
    //   497	506	918	finally
    //   517	528	918	finally
    //   539	544	918	finally
    //   589	600	918	finally
    //   611	618	918	finally
    //   629	644	918	finally
    //   655	663	918	finally
    //   671	689	918	finally
    //   692	700	918	finally
    //   726	738	918	finally
    //   746	764	918	finally
    //   832	853	918	finally
    //   864	873	918	finally
    //   884	891	918	finally
    //   907	915	918	finally
    //   945	950	918	finally
    //   961	966	918	finally
    //   974	988	918	finally
    //   995	1003	1003	java/lang/Exception
    //   1029	1037	1040	java/lang/Exception
    //   705	713	1061	java/lang/Exception
    //   924	931	1082	java/lang/Exception
  }
  
  public void setSettingsEndpoint(String paramString)
  {
    this.endpoint = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\AbstractSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */