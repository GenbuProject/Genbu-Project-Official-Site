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
    //   46: astore_3
    //   47: aload_3
    //   48: invokevirtual 111	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   51: astore 4
    //   53: aload 4
    //   55: astore_3
    //   56: aload 4
    //   58: instanceof 113
    //   61: ifeq +619 -> 680
    //   64: aload 4
    //   66: astore_3
    //   67: aload_0
    //   68: getfield 30	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   71: invokevirtual 118	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpAttempts	()V
    //   74: aload 4
    //   76: astore_3
    //   77: aload 4
    //   79: checkcast 113	javax/net/ssl/HttpsURLConnection
    //   82: astore 5
    //   84: aload 4
    //   86: astore_3
    //   87: aload 5
    //   89: getstatic 123	com/microsoft/cll/android/SettingsStore$Settings:HTTPTIMEOUTINTERVAL	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   92: invokestatic 129	com/microsoft/cll/android/SettingsStore:getCllSettingsAsInt	(Lcom/microsoft/cll/android/SettingsStore$Settings;)I
    //   95: invokevirtual 133	javax/net/ssl/HttpsURLConnection:setConnectTimeout	(I)V
    //   98: aload 4
    //   100: astore_3
    //   101: aload 5
    //   103: ldc -121
    //   105: invokevirtual 138	javax/net/ssl/HttpsURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   108: aload 4
    //   110: astore_3
    //   111: aload 5
    //   113: ldc -116
    //   115: ldc -114
    //   117: invokevirtual 145	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   120: aload 4
    //   122: astore_3
    //   123: aload 5
    //   125: ldc -109
    //   127: aload_0
    //   128: getfield 149	com/microsoft/cll/android/AbstractSettings:ETagSettingName	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   131: invokestatic 153	com/microsoft/cll/android/SettingsStore:getCllSettingsAsString	(Lcom/microsoft/cll/android/SettingsStore$Settings;)Ljava/lang/String;
    //   134: invokevirtual 145	javax/net/ssl/HttpsURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   137: aload 4
    //   139: astore_3
    //   140: ldc -101
    //   142: invokestatic 161	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   145: getstatic 167	java/util/Locale:US	Ljava/util/Locale;
    //   148: invokestatic 173	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
    //   151: invokevirtual 177	java/util/Calendar:getTimeInMillis	()J
    //   154: lstore_1
    //   155: aload 4
    //   157: astore_3
    //   158: aload 5
    //   160: invokevirtual 180	javax/net/ssl/HttpsURLConnection:connect	()V
    //   163: aload 4
    //   165: astore_3
    //   166: ldc -101
    //   168: invokestatic 161	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
    //   171: getstatic 167	java/util/Locale:US	Ljava/util/Locale;
    //   174: invokestatic 173	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
    //   177: invokevirtual 177	java/util/Calendar:getTimeInMillis	()J
    //   180: lload_1
    //   181: lsub
    //   182: lstore_1
    //   183: aload 4
    //   185: astore_3
    //   186: aload_0
    //   187: getfield 30	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   190: lload_1
    //   191: l2i
    //   192: invokevirtual 183	com/microsoft/cll/android/ClientTelemetry:SetAvgSettingsLatencyMs	(I)V
    //   195: aload 4
    //   197: astore_3
    //   198: aload_0
    //   199: getfield 30	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   202: lload_1
    //   203: l2i
    //   204: invokevirtual 186	com/microsoft/cll/android/ClientTelemetry:SetMaxSettingsLatencyMs	(I)V
    //   207: aload 4
    //   209: astore_3
    //   210: aload 5
    //   212: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   215: sipush 404
    //   218: if_icmpne +152 -> 370
    //   221: aload 4
    //   223: astore_3
    //   224: aload_0
    //   225: getfield 28	com/microsoft/cll/android/AbstractSettings:disableUploadOn404	Z
    //   228: ifeq +142 -> 370
    //   231: aload 4
    //   233: astore_3
    //   234: aload_0
    //   235: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   238: aload_0
    //   239: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   242: ldc -64
    //   244: invokeinterface 98 3 0
    //   249: aload 4
    //   251: astore_3
    //   252: getstatic 195	com/microsoft/cll/android/SettingsStore$Settings:UPLOADENABLED	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   255: ldc -59
    //   257: invokestatic 201	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   260: aload 4
    //   262: astore_3
    //   263: aload 5
    //   265: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   268: sipush 200
    //   271: if_icmpeq +17 -> 288
    //   274: aload 4
    //   276: astore_3
    //   277: aload 5
    //   279: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   282: sipush 304
    //   285: if_icmpne +190 -> 475
    //   288: aload 4
    //   290: astore_3
    //   291: aload 5
    //   293: ldc -53
    //   295: invokevirtual 207	javax/net/ssl/HttpsURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   298: astore 6
    //   300: aload 6
    //   302: ifnull +26 -> 328
    //   305: aload 4
    //   307: astore_3
    //   308: aload 6
    //   310: invokevirtual 213	java/lang/String:isEmpty	()Z
    //   313: ifne +15 -> 328
    //   316: aload 4
    //   318: astore_3
    //   319: aload_0
    //   320: getfield 149	com/microsoft/cll/android/AbstractSettings:ETagSettingName	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   323: aload 6
    //   325: invokestatic 201	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   328: aload 4
    //   330: astore_3
    //   331: aload 5
    //   333: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   336: sipush 200
    //   339: if_icmpeq +214 -> 553
    //   342: aload 4
    //   344: astore_3
    //   345: aload 5
    //   347: invokevirtual 216	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   350: aconst_null
    //   351: areturn
    //   352: astore_3
    //   353: aload_0
    //   354: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   357: aload_0
    //   358: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   361: ldc -38
    //   363: invokeinterface 221 3 0
    //   368: aconst_null
    //   369: areturn
    //   370: aload 4
    //   372: astore_3
    //   373: aload 5
    //   375: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   378: sipush 404
    //   381: if_icmpeq -121 -> 260
    //   384: aload 4
    //   386: astore_3
    //   387: aload_0
    //   388: getfield 28	com/microsoft/cll/android/AbstractSettings:disableUploadOn404	Z
    //   391: ifeq -131 -> 260
    //   394: aload 4
    //   396: astore_3
    //   397: aload_0
    //   398: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   401: aload_0
    //   402: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   405: ldc -33
    //   407: invokeinterface 98 3 0
    //   412: aload 4
    //   414: astore_3
    //   415: getstatic 195	com/microsoft/cll/android/SettingsStore$Settings:UPLOADENABLED	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   418: ldc -31
    //   420: invokestatic 201	com/microsoft/cll/android/SettingsStore:updateCllSetting	(Lcom/microsoft/cll/android/SettingsStore$Settings;Ljava/lang/String;)V
    //   423: goto -163 -> 260
    //   426: astore 5
    //   428: aload 4
    //   430: astore_3
    //   431: aload_0
    //   432: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   435: aload_0
    //   436: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   439: aload 5
    //   441: invokevirtual 228	java/io/IOException:getMessage	()Ljava/lang/String;
    //   444: invokeinterface 221 3 0
    //   449: aload 4
    //   451: astore_3
    //   452: aload_0
    //   453: getfield 30	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   456: iconst_m1
    //   457: invokevirtual 231	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpFailures	(I)V
    //   460: aload 4
    //   462: ifnull +11 -> 473
    //   465: aload 4
    //   467: invokevirtual 237	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   470: invokevirtual 242	java/io/InputStream:close	()V
    //   473: aconst_null
    //   474: areturn
    //   475: aload 4
    //   477: astore_3
    //   478: aload_0
    //   479: getfield 30	com/microsoft/cll/android/AbstractSettings:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   482: aload 5
    //   484: invokevirtual 190	javax/net/ssl/HttpsURLConnection:getResponseCode	()I
    //   487: invokevirtual 231	com/microsoft/cll/android/ClientTelemetry:IncrementSettingsHttpFailures	(I)V
    //   490: goto -162 -> 328
    //   493: astore 5
    //   495: aload 4
    //   497: astore_3
    //   498: aload_0
    //   499: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   502: aload_0
    //   503: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   506: aload 5
    //   508: invokevirtual 243	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   511: invokeinterface 221 3 0
    //   516: aload 4
    //   518: ifnull -45 -> 473
    //   521: aload 4
    //   523: invokevirtual 237	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   526: invokevirtual 242	java/io/InputStream:close	()V
    //   529: goto -56 -> 473
    //   532: astore_3
    //   533: aload_0
    //   534: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   537: aload_0
    //   538: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   541: aload_3
    //   542: invokevirtual 244	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   545: invokeinterface 221 3 0
    //   550: goto -77 -> 473
    //   553: aload 4
    //   555: astore_3
    //   556: new 246	java/io/BufferedReader
    //   559: dup
    //   560: new 248	java/io/InputStreamReader
    //   563: dup
    //   564: aload 5
    //   566: invokevirtual 249	javax/net/ssl/HttpsURLConnection:getInputStream	()Ljava/io/InputStream;
    //   569: invokespecial 252	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   572: invokespecial 255	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   575: astore 6
    //   577: aload 4
    //   579: astore_3
    //   580: new 41	java/lang/StringBuilder
    //   583: dup
    //   584: invokespecial 42	java/lang/StringBuilder:<init>	()V
    //   587: astore 7
    //   589: aload 4
    //   591: astore_3
    //   592: aload 6
    //   594: invokevirtual 258	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   597: astore 8
    //   599: aload 8
    //   601: ifnull +40 -> 641
    //   604: aload 4
    //   606: astore_3
    //   607: aload 7
    //   609: aload 8
    //   611: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   614: pop
    //   615: goto -26 -> 589
    //   618: astore 5
    //   620: aload_3
    //   621: astore 4
    //   623: aload 5
    //   625: astore_3
    //   626: aload 4
    //   628: ifnull +11 -> 639
    //   631: aload 4
    //   633: invokevirtual 237	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   636: invokevirtual 242	java/io/InputStream:close	()V
    //   639: aload_3
    //   640: athrow
    //   641: aload 4
    //   643: astore_3
    //   644: aload 6
    //   646: invokevirtual 259	java/io/BufferedReader:close	()V
    //   649: aload 4
    //   651: astore_3
    //   652: aload 5
    //   654: invokevirtual 216	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   657: new 261	org/json/JSONObject
    //   660: dup
    //   661: aload 7
    //   663: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   666: invokespecial 262	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   669: astore_3
    //   670: aload_3
    //   671: areturn
    //   672: astore 5
    //   674: aconst_null
    //   675: astore 4
    //   677: goto -249 -> 428
    //   680: aload 4
    //   682: ifnull -209 -> 473
    //   685: aload 4
    //   687: invokevirtual 237	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   690: invokevirtual 242	java/io/InputStream:close	()V
    //   693: goto -220 -> 473
    //   696: astore_3
    //   697: aload_0
    //   698: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   701: aload_0
    //   702: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   705: aload_3
    //   706: invokevirtual 244	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   709: invokeinterface 221 3 0
    //   714: goto -241 -> 473
    //   717: astore_3
    //   718: aload_0
    //   719: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   722: aload_0
    //   723: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   726: aload_3
    //   727: invokevirtual 244	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   730: invokeinterface 221 3 0
    //   735: goto -262 -> 473
    //   738: astore 4
    //   740: aload_0
    //   741: getfield 32	com/microsoft/cll/android/AbstractSettings:logger	Lcom/microsoft/cll/android/ILogger;
    //   744: aload_0
    //   745: getfield 26	com/microsoft/cll/android/AbstractSettings:TAG	Ljava/lang/String;
    //   748: aload 4
    //   750: invokevirtual 244	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   753: invokeinterface 221 3 0
    //   758: goto -119 -> 639
    //   761: astore_3
    //   762: aconst_null
    //   763: astore 4
    //   765: goto -139 -> 626
    //   768: astore 5
    //   770: aconst_null
    //   771: astore 4
    //   773: goto -278 -> 495
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	776	0	this	AbstractSettings
    //   154	49	1	l	long
    //   46	299	3	localObject1	Object
    //   352	1	3	localMalformedURLException	java.net.MalformedURLException
    //   372	126	3	localObject2	Object
    //   532	10	3	localException1	Exception
    //   555	116	3	localObject3	Object
    //   696	10	3	localException2	Exception
    //   717	10	3	localException3	Exception
    //   761	1	3	localObject4	Object
    //   51	635	4	localObject5	Object
    //   738	11	4	localException4	Exception
    //   763	9	4	localObject6	Object
    //   82	292	5	localHttpsURLConnection	javax.net.ssl.HttpsURLConnection
    //   426	57	5	localIOException1	java.io.IOException
    //   493	72	5	localJSONException1	org.json.JSONException
    //   618	35	5	localObject7	Object
    //   672	1	5	localIOException2	java.io.IOException
    //   768	1	5	localJSONException2	org.json.JSONException
    //   298	347	6	localObject8	Object
    //   587	75	7	localStringBuilder	StringBuilder
    //   597	13	8	str	String
    // Exception table:
    //   from	to	target	type
    //   15	47	352	java/net/MalformedURLException
    //   56	64	426	java/io/IOException
    //   67	74	426	java/io/IOException
    //   77	84	426	java/io/IOException
    //   87	98	426	java/io/IOException
    //   101	108	426	java/io/IOException
    //   111	120	426	java/io/IOException
    //   123	137	426	java/io/IOException
    //   140	155	426	java/io/IOException
    //   158	163	426	java/io/IOException
    //   166	183	426	java/io/IOException
    //   186	195	426	java/io/IOException
    //   198	207	426	java/io/IOException
    //   210	221	426	java/io/IOException
    //   224	231	426	java/io/IOException
    //   234	249	426	java/io/IOException
    //   252	260	426	java/io/IOException
    //   263	274	426	java/io/IOException
    //   277	288	426	java/io/IOException
    //   291	300	426	java/io/IOException
    //   308	316	426	java/io/IOException
    //   319	328	426	java/io/IOException
    //   331	342	426	java/io/IOException
    //   345	350	426	java/io/IOException
    //   373	384	426	java/io/IOException
    //   387	394	426	java/io/IOException
    //   397	412	426	java/io/IOException
    //   415	423	426	java/io/IOException
    //   478	490	426	java/io/IOException
    //   556	577	426	java/io/IOException
    //   580	589	426	java/io/IOException
    //   592	599	426	java/io/IOException
    //   607	615	426	java/io/IOException
    //   644	649	426	java/io/IOException
    //   652	657	426	java/io/IOException
    //   56	64	493	org/json/JSONException
    //   67	74	493	org/json/JSONException
    //   77	84	493	org/json/JSONException
    //   87	98	493	org/json/JSONException
    //   101	108	493	org/json/JSONException
    //   111	120	493	org/json/JSONException
    //   123	137	493	org/json/JSONException
    //   140	155	493	org/json/JSONException
    //   158	163	493	org/json/JSONException
    //   166	183	493	org/json/JSONException
    //   186	195	493	org/json/JSONException
    //   198	207	493	org/json/JSONException
    //   210	221	493	org/json/JSONException
    //   224	231	493	org/json/JSONException
    //   234	249	493	org/json/JSONException
    //   252	260	493	org/json/JSONException
    //   263	274	493	org/json/JSONException
    //   277	288	493	org/json/JSONException
    //   291	300	493	org/json/JSONException
    //   308	316	493	org/json/JSONException
    //   319	328	493	org/json/JSONException
    //   331	342	493	org/json/JSONException
    //   345	350	493	org/json/JSONException
    //   373	384	493	org/json/JSONException
    //   387	394	493	org/json/JSONException
    //   397	412	493	org/json/JSONException
    //   415	423	493	org/json/JSONException
    //   478	490	493	org/json/JSONException
    //   556	577	493	org/json/JSONException
    //   580	589	493	org/json/JSONException
    //   592	599	493	org/json/JSONException
    //   607	615	493	org/json/JSONException
    //   644	649	493	org/json/JSONException
    //   652	657	493	org/json/JSONException
    //   521	529	532	java/lang/Exception
    //   56	64	618	finally
    //   67	74	618	finally
    //   77	84	618	finally
    //   87	98	618	finally
    //   101	108	618	finally
    //   111	120	618	finally
    //   123	137	618	finally
    //   140	155	618	finally
    //   158	163	618	finally
    //   166	183	618	finally
    //   186	195	618	finally
    //   198	207	618	finally
    //   210	221	618	finally
    //   224	231	618	finally
    //   234	249	618	finally
    //   252	260	618	finally
    //   263	274	618	finally
    //   277	288	618	finally
    //   291	300	618	finally
    //   308	316	618	finally
    //   319	328	618	finally
    //   331	342	618	finally
    //   345	350	618	finally
    //   373	384	618	finally
    //   387	394	618	finally
    //   397	412	618	finally
    //   415	423	618	finally
    //   431	449	618	finally
    //   452	460	618	finally
    //   478	490	618	finally
    //   498	516	618	finally
    //   556	577	618	finally
    //   580	589	618	finally
    //   592	599	618	finally
    //   607	615	618	finally
    //   644	649	618	finally
    //   652	657	618	finally
    //   47	53	672	java/io/IOException
    //   657	670	672	java/io/IOException
    //   685	693	696	java/lang/Exception
    //   465	473	717	java/lang/Exception
    //   631	639	738	java/lang/Exception
    //   47	53	761	finally
    //   657	670	761	finally
    //   47	53	768	org/json/JSONException
    //   657	670	768	org/json/JSONException
  }
  
  public void setSettingsEndpoint(String paramString)
  {
    this.endpoint = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\cll\android\AbstractSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */