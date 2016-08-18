package com.microsoft.cll.android;

import org.json.JSONObject;

public abstract class AbstractSettings
{
  protected SettingsStore.Settings ETagSettingName;
  protected String TAG = "AndroidCll-AbstractSettings";
  protected final ClientTelemetry clientTelemetry;
  protected boolean disableUploadOn404 = false;
  protected String endpoint;
  protected final ILogger logger;
  private final PartA partA;
  protected String queryParam;
  
  protected AbstractSettings(ClientTelemetry paramClientTelemetry, ILogger paramILogger, PartA paramPartA)
  {
    this.clientTelemetry = paramClientTelemetry;
    this.logger = paramILogger;
    this.partA = paramPartA;
  }
  
  public abstract void ParseSettings(JSONObject paramJSONObject);
  
  /* Error */
  public JSONObject getSettings()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   4: aload_0
    //   5: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   8: ldc 50
    //   10: invokeinterface 56 3 0
    //   15: new 58	java/net/URL
    //   18: dup
    //   19: new 60	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   26: aload_0
    //   27: getfield 63	com/microsoft/cll/android/AbstractSettings:endpoint	Ljava/lang/String;
    //   30: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_0
    //   34: getfield 69	com/microsoft/cll/android/AbstractSettings:queryParam	Ljava/lang/String;
    //   37: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   43: invokespecial 76	java/net/URL:<init>	(Ljava/lang/String;)V
    //   46: astore 6
    //   48: aconst_null
    //   49: astore 5
    //   51: aconst_null
    //   52: astore_3
    //   53: aconst_null
    //   54: astore 4
    //   56: aload 6
    //   58: invokevirtual 80	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   61: astore 6
    //   63: aload 6
    //   65: astore 4
    //   67: aload 6
    //   69: astore 5
    //   71: aload 6
    //   73: astore_3
    //   74: aload 6
    //   76: instanceof 82
    //   79: ifeq +1001 -> 1080
    //   82: aload 6
    //   84: astore 4
    //   86: aload 6
    //   88: astore 5
    //   90: aload 6
    //   92: astore_3
    //   93: aload_0
    //   94: getfield 31	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   97: invokevirtual 87	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpAttempts	()V
    //   100: aload 6
    //   102: astore 4
    //   104: aload 6
    //   106: astore 5
    //   108: aload 6
    //   110: astore_3
    //   111: aload 6
    //   113: checkcast 82	javax/net/ssl/HttpsURLConnection
    //   116: astore 8
    //   118: aload 6
    //   120: astore 4
    //   122: aload 6
    //   124: astore 5
    //   126: aload 6
    //   128: astore_3
    //   129: aload 8
    //   131: invokevirtual 91	javax/net/ssl/HttpsURLConnection:getConnectTimeout	()I
    //   134: pop
    //   135: aload 6
    //   137: astore 4
    //   139: aload 6
    //   141: astore 5
    //   143: aload 6
    //   145: astore_3
    //   146: aload 8
    //   148: invokevirtual 94	javax/net/ssl/HttpsURLConnection:getReadTimeout	()I
    //   151: pop
    //   152: aload 6
    //   154: astore 4
    //   156: aload 6
    //   158: astore 5
    //   160: aload 6
    //   162: astore_3
    //   163: aload 8
    //   165: getstatic 99	com/microsoft/cll/android/SettingsStore$Settings:HTTPTIMEOUTINTERVAL	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   168: invokestatic 105	com/microsoft/cll/android/SettingsStore:getCllSettingsAsInt	(Lcom/microsoft/cll/android/SettingsStore$Settings;)I
    //   171: invokevirtual 109	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
    //   174: aload 6
    //   176: astore 4
    //   178: aload 6
    //   180: astore 5
    //   182: aload 6
    //   184: astore_3
    //   185: aload 8
    //   187: getstatic 99	com/microsoft/cll/android/SettingsStore$Settings:HTTPTIMEOUTINTERVAL	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   190: invokestatic 105	com/microsoft/cll/android/SettingsStore:getCllSettingsAsInt	(Lcom/microsoft/cll/android/SettingsStore$Settings;)I
    //   193: invokevirtual 112	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
    //   196: aload 6
    //   198: astore 4
    //   200: aload 6
    //   202: astore 5
    //   204: aload 6
    //   206: astore_3
    //   207: aload 8
    //   209: ldc 114
    //   211: invokevirtual 117	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   214: aload 6
    //   216: astore 4
    //   218: aload 6
    //   220: astore 5
    //   222: aload 6
    //   224: astore_3
    //   225: aload 8
    //   227: ldc 119
    //   229: ldc 121
    //   231: invokevirtual 124	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   234: aload 6
    //   236: astore 4
    //   238: aload 6
    //   240: astore 5
    //   242: aload 6
    //   244: astore_3
    //   245: aload 8
    //   247: ldc 126
    //   249: aload_0
    //   250: getfield 128	com/microsoft/cll/android/AbstractSettings:ETagSettingName	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   253: invokestatic 132	com/microsoft/cll/android/SettingsStore:getCllSettingsAsString	(Lcom/microsoft/cll/android/SettingsStore$Settings;)Ljava/lang/String;
    //   256: invokevirtual 124	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   259: aload 6
    //   261: astore 4
    //   263: aload 6
    //   265: astore 5
    //   267: aload 6
    //   269: astore_3
    //   270: ldc -122
    //   272: invokestatic 140	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   275: getstatic 146	java/util/Locale:US	Ljava/util/Locale;
    //   278: invokestatic 152	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
    //   281: invokevirtual 156	java/util/Calendar:getTimeInMillis	()J
    //   284: lstore_1
    //   285: aload 6
    //   287: astore 4
    //   289: aload 6
    //   291: astore 5
    //   293: aload 6
    //   295: astore_3
    //   296: aload 8
    //   298: invokevirtual 159	javax/net/ssl/HttpsURLConnection:connect	()V
    //   301: aload 6
    //   303: astore 4
    //   305: aload 6
    //   307: astore 5
    //   309: aload 6
    //   311: astore_3
    //   312: ldc -122
    //   314: invokestatic 140	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   317: getstatic 146	java/util/Locale:US	Ljava/util/Locale;
    //   320: invokestatic 152	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
    //   323: invokevirtual 156	java/util/Calendar:getTimeInMillis	()J
    //   326: lload_1
    //   327: lsub
    //   328: lstore_1
    //   329: aload 6
    //   331: astore 4
    //   333: aload 6
    //   335: astore 5
    //   337: aload 6
    //   339: astore_3
    //   340: aload_0
    //   341: getfield 31	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   344: lload_1
    //   345: l2i
    //   346: invokevirtual 162	com/microsoft/cll/android/ClientTelemetry:SetAvgSettingsLatencyMs	(I)V
    //   349: aload 6
    //   351: astore 4
    //   353: aload 6
    //   355: astore 5
    //   357: aload 6
    //   359: astore_3
    //   360: aload_0
    //   361: getfield 31	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   364: lload_1
    //   365: l2i
    //   366: invokevirtual 165	com/microsoft/cll/android/ClientTelemetry:SetMaxSettingsLatencyMs	(I)V
    //   369: aload 6
    //   371: astore 4
    //   373: aload 6
    //   375: astore 5
    //   377: aload 6
    //   379: astore_3
    //   380: aload 8
    //   382: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   385: sipush 404
    //   388: if_icmpne +246 -> 634
    //   391: aload 6
    //   393: astore 4
    //   395: aload 6
    //   397: astore 5
    //   399: aload 6
    //   401: astore_3
    //   402: aload_0
    //   403: getfield 29	com/microsoft/cll/android/AbstractSettings:disableUploadOn404	Z
    //   406: ifeq +228 -> 634
    //   409: aload 6
    //   411: astore 4
    //   413: aload 6
    //   415: astore 5
    //   417: aload 6
    //   419: astore_3
    //   420: aload_0
    //   421: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   424: aload_0
    //   425: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   428: ldc -86
    //   430: invokeinterface 56 3 0
    //   435: aload 6
    //   437: astore 4
    //   439: aload 6
    //   441: astore 5
    //   443: aload 6
    //   445: astore_3
    //   446: getstatic 173	com/microsoft/cll/android/SettingsStore$Settings:UPLOADENABLED	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   449: ldc -81
    //   451: invokestatic 179	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   454: aload 6
    //   456: astore 4
    //   458: aload 6
    //   460: astore 5
    //   462: aload 6
    //   464: astore_3
    //   465: aload 8
    //   467: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   470: sipush 200
    //   473: if_icmpeq +25 -> 498
    //   476: aload 6
    //   478: astore 4
    //   480: aload 6
    //   482: astore 5
    //   484: aload 6
    //   486: astore_3
    //   487: aload 8
    //   489: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   492: sipush 304
    //   495: if_icmpne +276 -> 771
    //   498: aload 6
    //   500: astore 4
    //   502: aload 6
    //   504: astore 5
    //   506: aload 6
    //   508: astore_3
    //   509: aload 8
    //   511: ldc -75
    //   513: invokevirtual 185	javax/net/ssl/HttpsURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   516: astore 7
    //   518: aload 7
    //   520: ifnull +42 -> 562
    //   523: aload 6
    //   525: astore 4
    //   527: aload 6
    //   529: astore 5
    //   531: aload 6
    //   533: astore_3
    //   534: aload 7
    //   536: invokevirtual 191	java/lang/String:isEmpty	()Z
    //   539: ifne +23 -> 562
    //   542: aload 6
    //   544: astore 4
    //   546: aload 6
    //   548: astore 5
    //   550: aload 6
    //   552: astore_3
    //   553: aload_0
    //   554: getfield 128	com/microsoft/cll/android/AbstractSettings:ETagSettingName	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   557: aload 7
    //   559: invokestatic 179	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   562: aload 6
    //   564: astore 4
    //   566: aload 6
    //   568: astore 5
    //   570: aload 6
    //   572: astore_3
    //   573: aload 8
    //   575: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   578: sipush 200
    //   581: if_icmpeq +296 -> 877
    //   584: aload 6
    //   586: astore 4
    //   588: aload 6
    //   590: astore 5
    //   592: aload 6
    //   594: astore_3
    //   595: aload 8
    //   597: invokevirtual 194	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   600: aconst_null
    //   601: astore_3
    //   602: iconst_0
    //   603: ifeq +11 -> 614
    //   606: new 196	java/lang/NullPointerException
    //   609: dup
    //   610: invokespecial 197	java/lang/NullPointerException:<init>	()V
    //   613: athrow
    //   614: aload_3
    //   615: areturn
    //   616: astore_3
    //   617: aload_0
    //   618: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   621: aload_0
    //   622: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   625: ldc -57
    //   627: invokeinterface 202 3 0
    //   632: aconst_null
    //   633: areturn
    //   634: aload 6
    //   636: astore 4
    //   638: aload 6
    //   640: astore 5
    //   642: aload 6
    //   644: astore_3
    //   645: aload 8
    //   647: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   650: sipush 404
    //   653: if_icmpeq -199 -> 454
    //   656: aload 6
    //   658: astore 4
    //   660: aload 6
    //   662: astore 5
    //   664: aload 6
    //   666: astore_3
    //   667: aload_0
    //   668: getfield 29	com/microsoft/cll/android/AbstractSettings:disableUploadOn404	Z
    //   671: ifeq -217 -> 454
    //   674: aload 6
    //   676: astore 4
    //   678: aload 6
    //   680: astore 5
    //   682: aload 6
    //   684: astore_3
    //   685: aload_0
    //   686: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   689: aload_0
    //   690: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   693: ldc -52
    //   695: invokeinterface 56 3 0
    //   700: aload 6
    //   702: astore 4
    //   704: aload 6
    //   706: astore 5
    //   708: aload 6
    //   710: astore_3
    //   711: getstatic 173	com/microsoft/cll/android/SettingsStore$Settings:UPLOADENABLED	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   714: ldc -50
    //   716: invokestatic 179	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   719: goto -265 -> 454
    //   722: astore 5
    //   724: aload 4
    //   726: astore_3
    //   727: aload_0
    //   728: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   731: aload_0
    //   732: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   735: aload 5
    //   737: invokevirtual 209	java/io/IOException:getMessage	()Ljava/lang/String;
    //   740: invokeinterface 202 3 0
    //   745: aload 4
    //   747: astore_3
    //   748: aload_0
    //   749: getfield 31	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   752: iconst_m1
    //   753: invokevirtual 212	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpFailures	(I)V
    //   756: aload 4
    //   758: ifnull +11 -> 769
    //   761: aload 4
    //   763: invokevirtual 218	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   766: invokevirtual 223	java/io/InputStream:close	()V
    //   769: aconst_null
    //   770: areturn
    //   771: aload 6
    //   773: astore 4
    //   775: aload 6
    //   777: astore 5
    //   779: aload 6
    //   781: astore_3
    //   782: aload_0
    //   783: getfield 31	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   786: aload 8
    //   788: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   791: invokevirtual 212	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpFailures	(I)V
    //   794: goto -232 -> 562
    //   797: astore 4
    //   799: aload 5
    //   801: astore_3
    //   802: aload_0
    //   803: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   806: aload_0
    //   807: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   810: aload 4
    //   812: invokevirtual 224	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   815: invokeinterface 202 3 0
    //   820: aload 5
    //   822: ifnull -53 -> 769
    //   825: aload 5
    //   827: invokevirtual 218	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   830: invokevirtual 223	java/io/InputStream:close	()V
    //   833: goto -64 -> 769
    //   836: astore_3
    //   837: aload_0
    //   838: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   841: aload_0
    //   842: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   845: aload_3
    //   846: invokevirtual 225	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   849: invokeinterface 202 3 0
    //   854: goto -85 -> 769
    //   857: astore_3
    //   858: aload_0
    //   859: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   862: aload_0
    //   863: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   866: aload_3
    //   867: invokevirtual 225	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   870: invokeinterface 202 3 0
    //   875: aconst_null
    //   876: areturn
    //   877: aload 6
    //   879: astore 4
    //   881: aload 6
    //   883: astore 5
    //   885: aload 6
    //   887: astore_3
    //   888: new 227	java/io/BufferedReader
    //   891: dup
    //   892: new 229	java/io/InputStreamReader
    //   895: dup
    //   896: aload 8
    //   898: invokevirtual 230	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
    //   901: invokespecial 233	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   904: invokespecial 236	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   907: astore 9
    //   909: aload 6
    //   911: astore 4
    //   913: aload 6
    //   915: astore 5
    //   917: aload 6
    //   919: astore_3
    //   920: new 60	java/lang/StringBuilder
    //   923: dup
    //   924: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   927: astore 7
    //   929: aload 6
    //   931: astore 4
    //   933: aload 6
    //   935: astore 5
    //   937: aload 6
    //   939: astore_3
    //   940: aload 9
    //   942: invokevirtual 239	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   945: astore 10
    //   947: aload 10
    //   949: ifnull +41 -> 990
    //   952: aload 6
    //   954: astore 4
    //   956: aload 6
    //   958: astore 5
    //   960: aload 6
    //   962: astore_3
    //   963: aload 7
    //   965: aload 10
    //   967: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   970: pop
    //   971: goto -42 -> 929
    //   974: astore 4
    //   976: aload_3
    //   977: ifnull +10 -> 987
    //   980: aload_3
    //   981: invokevirtual 218	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   984: invokevirtual 223	java/io/InputStream:close	()V
    //   987: aload 4
    //   989: athrow
    //   990: aload 6
    //   992: astore 4
    //   994: aload 6
    //   996: astore 5
    //   998: aload 6
    //   1000: astore_3
    //   1001: aload 9
    //   1003: invokevirtual 240	java/io/BufferedReader:close	()V
    //   1006: aload 6
    //   1008: astore 4
    //   1010: aload 6
    //   1012: astore 5
    //   1014: aload 6
    //   1016: astore_3
    //   1017: aload 8
    //   1019: invokevirtual 194	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   1022: aconst_null
    //   1023: astore 5
    //   1025: aconst_null
    //   1026: astore_3
    //   1027: aconst_null
    //   1028: astore 4
    //   1030: new 242	org/json/JSONObject
    //   1033: dup
    //   1034: aload 7
    //   1036: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1039: invokespecial 243	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   1042: astore 6
    //   1044: aload 6
    //   1046: astore_3
    //   1047: iconst_0
    //   1048: ifeq -434 -> 614
    //   1051: new 196	java/lang/NullPointerException
    //   1054: dup
    //   1055: invokespecial 197	java/lang/NullPointerException:<init>	()V
    //   1058: athrow
    //   1059: astore_3
    //   1060: aload_0
    //   1061: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   1064: aload_0
    //   1065: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   1068: aload_3
    //   1069: invokevirtual 225	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1072: invokeinterface 202 3 0
    //   1077: aload 6
    //   1079: areturn
    //   1080: aload 6
    //   1082: ifnull -313 -> 769
    //   1085: aload 6
    //   1087: invokevirtual 218	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   1090: invokevirtual 223	java/io/InputStream:close	()V
    //   1093: goto -324 -> 769
    //   1096: astore_3
    //   1097: aload_0
    //   1098: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   1101: aload_0
    //   1102: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   1105: aload_3
    //   1106: invokevirtual 225	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1109: invokeinterface 202 3 0
    //   1114: goto -345 -> 769
    //   1117: astore_3
    //   1118: aload_0
    //   1119: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   1122: aload_0
    //   1123: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   1126: aload_3
    //   1127: invokevirtual 225	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1130: invokeinterface 202 3 0
    //   1135: goto -366 -> 769
    //   1138: astore_3
    //   1139: aload_0
    //   1140: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   1143: aload_0
    //   1144: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   1147: aload_3
    //   1148: invokevirtual 225	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1151: invokeinterface 202 3 0
    //   1156: goto -169 -> 987
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1159	0	this	AbstractSettings
    //   284	81	1	l	long
    //   52	563	3	localObject1	Object
    //   616	1	3	localMalformedURLException	java.net.MalformedURLException
    //   644	158	3	localObject2	Object
    //   836	10	3	localException1	Exception
    //   857	10	3	localException2	Exception
    //   887	160	3	localObject3	Object
    //   1059	10	3	localException3	Exception
    //   1096	10	3	localException4	Exception
    //   1117	10	3	localException5	Exception
    //   1138	10	3	localException6	Exception
    //   54	720	4	localObject4	Object
    //   797	14	4	localJSONException	org.json.JSONException
    //   879	76	4	localObject5	Object
    //   974	14	4	localObject6	Object
    //   992	37	4	localObject7	Object
    //   49	658	5	localObject8	Object
    //   722	14	5	localIOException	java.io.IOException
    //   777	247	5	localObject9	Object
    //   46	1040	6	localObject10	Object
    //   516	519	7	localObject11	Object
    //   116	902	8	localHttpsURLConnection	javax.net.ssl.HttpsURLConnection
    //   907	95	9	localBufferedReader	java.io.BufferedReader
    //   945	21	10	str	String
    // Exception table:
    //   from	to	target	type
    //   15	48	616	java/net/MalformedURLException
    //   56	63	722	java/io/IOException
    //   74	82	722	java/io/IOException
    //   93	100	722	java/io/IOException
    //   111	118	722	java/io/IOException
    //   129	135	722	java/io/IOException
    //   146	152	722	java/io/IOException
    //   163	174	722	java/io/IOException
    //   185	196	722	java/io/IOException
    //   207	214	722	java/io/IOException
    //   225	234	722	java/io/IOException
    //   245	259	722	java/io/IOException
    //   270	285	722	java/io/IOException
    //   296	301	722	java/io/IOException
    //   312	329	722	java/io/IOException
    //   340	349	722	java/io/IOException
    //   360	369	722	java/io/IOException
    //   380	391	722	java/io/IOException
    //   402	409	722	java/io/IOException
    //   420	435	722	java/io/IOException
    //   446	454	722	java/io/IOException
    //   465	476	722	java/io/IOException
    //   487	498	722	java/io/IOException
    //   509	518	722	java/io/IOException
    //   534	542	722	java/io/IOException
    //   553	562	722	java/io/IOException
    //   573	584	722	java/io/IOException
    //   595	600	722	java/io/IOException
    //   645	656	722	java/io/IOException
    //   667	674	722	java/io/IOException
    //   685	700	722	java/io/IOException
    //   711	719	722	java/io/IOException
    //   782	794	722	java/io/IOException
    //   888	909	722	java/io/IOException
    //   920	929	722	java/io/IOException
    //   940	947	722	java/io/IOException
    //   963	971	722	java/io/IOException
    //   1001	1006	722	java/io/IOException
    //   1017	1022	722	java/io/IOException
    //   1030	1044	722	java/io/IOException
    //   56	63	797	org/json/JSONException
    //   74	82	797	org/json/JSONException
    //   93	100	797	org/json/JSONException
    //   111	118	797	org/json/JSONException
    //   129	135	797	org/json/JSONException
    //   146	152	797	org/json/JSONException
    //   163	174	797	org/json/JSONException
    //   185	196	797	org/json/JSONException
    //   207	214	797	org/json/JSONException
    //   225	234	797	org/json/JSONException
    //   245	259	797	org/json/JSONException
    //   270	285	797	org/json/JSONException
    //   296	301	797	org/json/JSONException
    //   312	329	797	org/json/JSONException
    //   340	349	797	org/json/JSONException
    //   360	369	797	org/json/JSONException
    //   380	391	797	org/json/JSONException
    //   402	409	797	org/json/JSONException
    //   420	435	797	org/json/JSONException
    //   446	454	797	org/json/JSONException
    //   465	476	797	org/json/JSONException
    //   487	498	797	org/json/JSONException
    //   509	518	797	org/json/JSONException
    //   534	542	797	org/json/JSONException
    //   553	562	797	org/json/JSONException
    //   573	584	797	org/json/JSONException
    //   595	600	797	org/json/JSONException
    //   645	656	797	org/json/JSONException
    //   667	674	797	org/json/JSONException
    //   685	700	797	org/json/JSONException
    //   711	719	797	org/json/JSONException
    //   782	794	797	org/json/JSONException
    //   888	909	797	org/json/JSONException
    //   920	929	797	org/json/JSONException
    //   940	947	797	org/json/JSONException
    //   963	971	797	org/json/JSONException
    //   1001	1006	797	org/json/JSONException
    //   1017	1022	797	org/json/JSONException
    //   1030	1044	797	org/json/JSONException
    //   825	833	836	java/lang/Exception
    //   606	614	857	java/lang/Exception
    //   56	63	974	finally
    //   74	82	974	finally
    //   93	100	974	finally
    //   111	118	974	finally
    //   129	135	974	finally
    //   146	152	974	finally
    //   163	174	974	finally
    //   185	196	974	finally
    //   207	214	974	finally
    //   225	234	974	finally
    //   245	259	974	finally
    //   270	285	974	finally
    //   296	301	974	finally
    //   312	329	974	finally
    //   340	349	974	finally
    //   360	369	974	finally
    //   380	391	974	finally
    //   402	409	974	finally
    //   420	435	974	finally
    //   446	454	974	finally
    //   465	476	974	finally
    //   487	498	974	finally
    //   509	518	974	finally
    //   534	542	974	finally
    //   553	562	974	finally
    //   573	584	974	finally
    //   595	600	974	finally
    //   645	656	974	finally
    //   667	674	974	finally
    //   685	700	974	finally
    //   711	719	974	finally
    //   727	745	974	finally
    //   748	756	974	finally
    //   782	794	974	finally
    //   802	820	974	finally
    //   888	909	974	finally
    //   920	929	974	finally
    //   940	947	974	finally
    //   963	971	974	finally
    //   1001	1006	974	finally
    //   1017	1022	974	finally
    //   1030	1044	974	finally
    //   1051	1059	1059	java/lang/Exception
    //   1085	1093	1096	java/lang/Exception
    //   761	769	1117	java/lang/Exception
    //   980	987	1138	java/lang/Exception
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\AbstractSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */