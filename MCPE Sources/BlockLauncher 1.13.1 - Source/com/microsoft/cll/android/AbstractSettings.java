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
    //   46: astore_3
    //   47: aload_3
    //   48: invokevirtual 80	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   51: astore 4
    //   53: aload 4
    //   55: astore_3
    //   56: aload 4
    //   58: instanceof 82
    //   61: ifeq +651 -> 712
    //   64: aload 4
    //   66: astore_3
    //   67: aload_0
    //   68: getfield 31	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   71: invokevirtual 87	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpAttempts	()V
    //   74: aload 4
    //   76: astore_3
    //   77: aload 4
    //   79: checkcast 82	javax/net/ssl/HttpsURLConnection
    //   82: astore 5
    //   84: aload 4
    //   86: astore_3
    //   87: aload 5
    //   89: invokevirtual 91	javax/net/ssl/HttpsURLConnection:getConnectTimeout	()I
    //   92: pop
    //   93: aload 4
    //   95: astore_3
    //   96: aload 5
    //   98: invokevirtual 94	javax/net/ssl/HttpsURLConnection:getReadTimeout	()I
    //   101: pop
    //   102: aload 4
    //   104: astore_3
    //   105: aload 5
    //   107: getstatic 99	com/microsoft/cll/android/SettingsStore$Settings:HTTPTIMEOUTINTERVAL	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   110: invokestatic 105	com/microsoft/cll/android/SettingsStore:getCllSettingsAsInt	(Lcom/microsoft/cll/android/SettingsStore$Settings;)I
    //   113: invokevirtual 109	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
    //   116: aload 4
    //   118: astore_3
    //   119: aload 5
    //   121: getstatic 99	com/microsoft/cll/android/SettingsStore$Settings:HTTPTIMEOUTINTERVAL	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   124: invokestatic 105	com/microsoft/cll/android/SettingsStore:getCllSettingsAsInt	(Lcom/microsoft/cll/android/SettingsStore$Settings;)I
    //   127: invokevirtual 112	javax/net/ssl/HttpsURLConnection:setReadTimeout	(I)V
    //   130: aload 4
    //   132: astore_3
    //   133: aload 5
    //   135: ldc 114
    //   137: invokevirtual 117	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   140: aload 4
    //   142: astore_3
    //   143: aload 5
    //   145: ldc 119
    //   147: ldc 121
    //   149: invokevirtual 124	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   152: aload 4
    //   154: astore_3
    //   155: aload 5
    //   157: ldc 126
    //   159: aload_0
    //   160: getfield 128	com/microsoft/cll/android/AbstractSettings:ETagSettingName	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   163: invokestatic 132	com/microsoft/cll/android/SettingsStore:getCllSettingsAsString	(Lcom/microsoft/cll/android/SettingsStore$Settings;)Ljava/lang/String;
    //   166: invokevirtual 124	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   169: aload 4
    //   171: astore_3
    //   172: ldc -122
    //   174: invokestatic 140	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   177: getstatic 146	java/util/Locale:US	Ljava/util/Locale;
    //   180: invokestatic 152	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
    //   183: invokevirtual 156	java/util/Calendar:getTimeInMillis	()J
    //   186: lstore_1
    //   187: aload 4
    //   189: astore_3
    //   190: aload 5
    //   192: invokevirtual 159	javax/net/ssl/HttpsURLConnection:connect	()V
    //   195: aload 4
    //   197: astore_3
    //   198: ldc -122
    //   200: invokestatic 140	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   203: getstatic 146	java/util/Locale:US	Ljava/util/Locale;
    //   206: invokestatic 152	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
    //   209: invokevirtual 156	java/util/Calendar:getTimeInMillis	()J
    //   212: lload_1
    //   213: lsub
    //   214: lstore_1
    //   215: aload 4
    //   217: astore_3
    //   218: aload_0
    //   219: getfield 31	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   222: lload_1
    //   223: l2i
    //   224: invokevirtual 162	com/microsoft/cll/android/ClientTelemetry:SetAvgSettingsLatencyMs	(I)V
    //   227: aload 4
    //   229: astore_3
    //   230: aload_0
    //   231: getfield 31	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   234: lload_1
    //   235: l2i
    //   236: invokevirtual 165	com/microsoft/cll/android/ClientTelemetry:SetMaxSettingsLatencyMs	(I)V
    //   239: aload 4
    //   241: astore_3
    //   242: aload 5
    //   244: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   247: sipush 404
    //   250: if_icmpne +152 -> 402
    //   253: aload 4
    //   255: astore_3
    //   256: aload_0
    //   257: getfield 29	com/microsoft/cll/android/AbstractSettings:disableUploadOn404	Z
    //   260: ifeq +142 -> 402
    //   263: aload 4
    //   265: astore_3
    //   266: aload_0
    //   267: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   270: aload_0
    //   271: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   274: ldc -86
    //   276: invokeinterface 56 3 0
    //   281: aload 4
    //   283: astore_3
    //   284: getstatic 173	com/microsoft/cll/android/SettingsStore$Settings:UPLOADENABLED	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   287: ldc -81
    //   289: invokestatic 179	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   292: aload 4
    //   294: astore_3
    //   295: aload 5
    //   297: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   300: sipush 200
    //   303: if_icmpeq +17 -> 320
    //   306: aload 4
    //   308: astore_3
    //   309: aload 5
    //   311: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   314: sipush 304
    //   317: if_icmpne +190 -> 507
    //   320: aload 4
    //   322: astore_3
    //   323: aload 5
    //   325: ldc -75
    //   327: invokevirtual 185	javax/net/ssl/HttpsURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   330: astore 6
    //   332: aload 6
    //   334: ifnull +26 -> 360
    //   337: aload 4
    //   339: astore_3
    //   340: aload 6
    //   342: invokevirtual 191	java/lang/String:isEmpty	()Z
    //   345: ifne +15 -> 360
    //   348: aload 4
    //   350: astore_3
    //   351: aload_0
    //   352: getfield 128	com/microsoft/cll/android/AbstractSettings:ETagSettingName	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   355: aload 6
    //   357: invokestatic 179	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   360: aload 4
    //   362: astore_3
    //   363: aload 5
    //   365: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   368: sipush 200
    //   371: if_icmpeq +214 -> 585
    //   374: aload 4
    //   376: astore_3
    //   377: aload 5
    //   379: invokevirtual 194	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   382: aconst_null
    //   383: areturn
    //   384: astore_3
    //   385: aload_0
    //   386: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   389: aload_0
    //   390: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   393: ldc -60
    //   395: invokeinterface 199 3 0
    //   400: aconst_null
    //   401: areturn
    //   402: aload 4
    //   404: astore_3
    //   405: aload 5
    //   407: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   410: sipush 404
    //   413: if_icmpeq -121 -> 292
    //   416: aload 4
    //   418: astore_3
    //   419: aload_0
    //   420: getfield 29	com/microsoft/cll/android/AbstractSettings:disableUploadOn404	Z
    //   423: ifeq -131 -> 292
    //   426: aload 4
    //   428: astore_3
    //   429: aload_0
    //   430: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   433: aload_0
    //   434: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   437: ldc -55
    //   439: invokeinterface 56 3 0
    //   444: aload 4
    //   446: astore_3
    //   447: getstatic 173	com/microsoft/cll/android/SettingsStore$Settings:UPLOADENABLED	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   450: ldc -53
    //   452: invokestatic 179	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   455: goto -163 -> 292
    //   458: astore 5
    //   460: aload 4
    //   462: astore_3
    //   463: aload_0
    //   464: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   467: aload_0
    //   468: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   471: aload 5
    //   473: invokevirtual 206	java/io/IOException:getMessage	()Ljava/lang/String;
    //   476: invokeinterface 199 3 0
    //   481: aload 4
    //   483: astore_3
    //   484: aload_0
    //   485: getfield 31	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   488: iconst_m1
    //   489: invokevirtual 209	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpFailures	(I)V
    //   492: aload 4
    //   494: ifnull +11 -> 505
    //   497: aload 4
    //   499: invokevirtual 215	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   502: invokevirtual 220	java/io/InputStream:close	()V
    //   505: aconst_null
    //   506: areturn
    //   507: aload 4
    //   509: astore_3
    //   510: aload_0
    //   511: getfield 31	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   514: aload 5
    //   516: invokevirtual 168	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   519: invokevirtual 209	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpFailures	(I)V
    //   522: goto -162 -> 360
    //   525: astore 5
    //   527: aload 4
    //   529: astore_3
    //   530: aload_0
    //   531: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   534: aload_0
    //   535: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   538: aload 5
    //   540: invokevirtual 221	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   543: invokeinterface 199 3 0
    //   548: aload 4
    //   550: ifnull -45 -> 505
    //   553: aload 4
    //   555: invokevirtual 215	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   558: invokevirtual 220	java/io/InputStream:close	()V
    //   561: goto -56 -> 505
    //   564: astore_3
    //   565: aload_0
    //   566: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   569: aload_0
    //   570: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   573: aload_3
    //   574: invokevirtual 222	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   577: invokeinterface 199 3 0
    //   582: goto -77 -> 505
    //   585: aload 4
    //   587: astore_3
    //   588: new 224	java/io/BufferedReader
    //   591: dup
    //   592: new 226	java/io/InputStreamReader
    //   595: dup
    //   596: aload 5
    //   598: invokevirtual 227	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
    //   601: invokespecial 230	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   604: invokespecial 233	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   607: astore 6
    //   609: aload 4
    //   611: astore_3
    //   612: new 60	java/lang/StringBuilder
    //   615: dup
    //   616: invokespecial 61	java/lang/StringBuilder:<init>	()V
    //   619: astore 7
    //   621: aload 4
    //   623: astore_3
    //   624: aload 6
    //   626: invokevirtual 236	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   629: astore 8
    //   631: aload 8
    //   633: ifnull +40 -> 673
    //   636: aload 4
    //   638: astore_3
    //   639: aload 7
    //   641: aload 8
    //   643: invokevirtual 67	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   646: pop
    //   647: goto -26 -> 621
    //   650: astore 5
    //   652: aload_3
    //   653: astore 4
    //   655: aload 5
    //   657: astore_3
    //   658: aload 4
    //   660: ifnull +11 -> 671
    //   663: aload 4
    //   665: invokevirtual 215	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   668: invokevirtual 220	java/io/InputStream:close	()V
    //   671: aload_3
    //   672: athrow
    //   673: aload 4
    //   675: astore_3
    //   676: aload 6
    //   678: invokevirtual 237	java/io/BufferedReader:close	()V
    //   681: aload 4
    //   683: astore_3
    //   684: aload 5
    //   686: invokevirtual 194	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   689: new 239	org/json/JSONObject
    //   692: dup
    //   693: aload 7
    //   695: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   698: invokespecial 240	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   701: astore_3
    //   702: aload_3
    //   703: areturn
    //   704: astore 5
    //   706: aconst_null
    //   707: astore 4
    //   709: goto -249 -> 460
    //   712: aload 4
    //   714: ifnull -209 -> 505
    //   717: aload 4
    //   719: invokevirtual 215	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   722: invokevirtual 220	java/io/InputStream:close	()V
    //   725: goto -220 -> 505
    //   728: astore_3
    //   729: aload_0
    //   730: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   733: aload_0
    //   734: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   737: aload_3
    //   738: invokevirtual 222	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   741: invokeinterface 199 3 0
    //   746: goto -241 -> 505
    //   749: astore_3
    //   750: aload_0
    //   751: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   754: aload_0
    //   755: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   758: aload_3
    //   759: invokevirtual 222	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   762: invokeinterface 199 3 0
    //   767: goto -262 -> 505
    //   770: astore 4
    //   772: aload_0
    //   773: getfield 33	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   776: aload_0
    //   777: getfield 27	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   780: aload 4
    //   782: invokevirtual 222	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   785: invokeinterface 199 3 0
    //   790: goto -119 -> 671
    //   793: astore_3
    //   794: aconst_null
    //   795: astore 4
    //   797: goto -139 -> 658
    //   800: astore 5
    //   802: aconst_null
    //   803: astore 4
    //   805: goto -278 -> 527
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	808	0	this	AbstractSettings
    //   186	49	1	l	long
    //   46	331	3	localObject1	Object
    //   384	1	3	localMalformedURLException	java.net.MalformedURLException
    //   404	126	3	localObject2	Object
    //   564	10	3	localException1	Exception
    //   587	116	3	localObject3	Object
    //   728	10	3	localException2	Exception
    //   749	10	3	localException3	Exception
    //   793	1	3	localObject4	Object
    //   51	667	4	localObject5	Object
    //   770	11	4	localException4	Exception
    //   795	9	4	localObject6	Object
    //   82	324	5	localHttpsURLConnection	javax.net.ssl.HttpsURLConnection
    //   458	57	5	localIOException1	java.io.IOException
    //   525	72	5	localJSONException1	org.json.JSONException
    //   650	35	5	localObject7	Object
    //   704	1	5	localIOException2	java.io.IOException
    //   800	1	5	localJSONException2	org.json.JSONException
    //   330	347	6	localObject8	Object
    //   619	75	7	localStringBuilder	StringBuilder
    //   629	13	8	str	String
    // Exception table:
    //   from	to	target	type
    //   15	47	384	java/net/MalformedURLException
    //   56	64	458	java/io/IOException
    //   67	74	458	java/io/IOException
    //   77	84	458	java/io/IOException
    //   87	93	458	java/io/IOException
    //   96	102	458	java/io/IOException
    //   105	116	458	java/io/IOException
    //   119	130	458	java/io/IOException
    //   133	140	458	java/io/IOException
    //   143	152	458	java/io/IOException
    //   155	169	458	java/io/IOException
    //   172	187	458	java/io/IOException
    //   190	195	458	java/io/IOException
    //   198	215	458	java/io/IOException
    //   218	227	458	java/io/IOException
    //   230	239	458	java/io/IOException
    //   242	253	458	java/io/IOException
    //   256	263	458	java/io/IOException
    //   266	281	458	java/io/IOException
    //   284	292	458	java/io/IOException
    //   295	306	458	java/io/IOException
    //   309	320	458	java/io/IOException
    //   323	332	458	java/io/IOException
    //   340	348	458	java/io/IOException
    //   351	360	458	java/io/IOException
    //   363	374	458	java/io/IOException
    //   377	382	458	java/io/IOException
    //   405	416	458	java/io/IOException
    //   419	426	458	java/io/IOException
    //   429	444	458	java/io/IOException
    //   447	455	458	java/io/IOException
    //   510	522	458	java/io/IOException
    //   588	609	458	java/io/IOException
    //   612	621	458	java/io/IOException
    //   624	631	458	java/io/IOException
    //   639	647	458	java/io/IOException
    //   676	681	458	java/io/IOException
    //   684	689	458	java/io/IOException
    //   56	64	525	org/json/JSONException
    //   67	74	525	org/json/JSONException
    //   77	84	525	org/json/JSONException
    //   87	93	525	org/json/JSONException
    //   96	102	525	org/json/JSONException
    //   105	116	525	org/json/JSONException
    //   119	130	525	org/json/JSONException
    //   133	140	525	org/json/JSONException
    //   143	152	525	org/json/JSONException
    //   155	169	525	org/json/JSONException
    //   172	187	525	org/json/JSONException
    //   190	195	525	org/json/JSONException
    //   198	215	525	org/json/JSONException
    //   218	227	525	org/json/JSONException
    //   230	239	525	org/json/JSONException
    //   242	253	525	org/json/JSONException
    //   256	263	525	org/json/JSONException
    //   266	281	525	org/json/JSONException
    //   284	292	525	org/json/JSONException
    //   295	306	525	org/json/JSONException
    //   309	320	525	org/json/JSONException
    //   323	332	525	org/json/JSONException
    //   340	348	525	org/json/JSONException
    //   351	360	525	org/json/JSONException
    //   363	374	525	org/json/JSONException
    //   377	382	525	org/json/JSONException
    //   405	416	525	org/json/JSONException
    //   419	426	525	org/json/JSONException
    //   429	444	525	org/json/JSONException
    //   447	455	525	org/json/JSONException
    //   510	522	525	org/json/JSONException
    //   588	609	525	org/json/JSONException
    //   612	621	525	org/json/JSONException
    //   624	631	525	org/json/JSONException
    //   639	647	525	org/json/JSONException
    //   676	681	525	org/json/JSONException
    //   684	689	525	org/json/JSONException
    //   553	561	564	java/lang/Exception
    //   56	64	650	finally
    //   67	74	650	finally
    //   77	84	650	finally
    //   87	93	650	finally
    //   96	102	650	finally
    //   105	116	650	finally
    //   119	130	650	finally
    //   133	140	650	finally
    //   143	152	650	finally
    //   155	169	650	finally
    //   172	187	650	finally
    //   190	195	650	finally
    //   198	215	650	finally
    //   218	227	650	finally
    //   230	239	650	finally
    //   242	253	650	finally
    //   256	263	650	finally
    //   266	281	650	finally
    //   284	292	650	finally
    //   295	306	650	finally
    //   309	320	650	finally
    //   323	332	650	finally
    //   340	348	650	finally
    //   351	360	650	finally
    //   363	374	650	finally
    //   377	382	650	finally
    //   405	416	650	finally
    //   419	426	650	finally
    //   429	444	650	finally
    //   447	455	650	finally
    //   463	481	650	finally
    //   484	492	650	finally
    //   510	522	650	finally
    //   530	548	650	finally
    //   588	609	650	finally
    //   612	621	650	finally
    //   624	631	650	finally
    //   639	647	650	finally
    //   676	681	650	finally
    //   684	689	650	finally
    //   47	53	704	java/io/IOException
    //   689	702	704	java/io/IOException
    //   717	725	728	java/lang/Exception
    //   497	505	749	java/lang/Exception
    //   663	671	770	java/lang/Exception
    //   47	53	793	finally
    //   689	702	793	finally
    //   47	53	800	org/json/JSONException
    //   689	702	800	org/json/JSONException
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\AbstractSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */