package com.microsoft.cll.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

public class EventSender
{
  private final String NO_HTTPS_CONN = "URL didn't return HttpsUrlConnection instance.";
  private final String TAG = "AndroidCll-EventSender";
  private final ClientTelemetry clientTelemetry;
  private final URL endpoint;
  private final ILogger logger;
  
  public EventSender(URL paramURL, ClientTelemetry paramClientTelemetry, ILogger paramILogger)
  {
    this.endpoint = paramURL;
    this.clientTelemetry = paramClientTelemetry;
    this.logger = paramILogger;
  }
  
  private long getTime()
  {
    return Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US).getTimeInMillis();
  }
  
  protected HttpURLConnection openConnection(int paramInt, boolean paramBoolean, TicketHeaders paramTicketHeaders)
    throws IOException
  {
    Object localObject1 = "";
    Object localObject2 = localObject1;
    if (paramTicketHeaders != null)
    {
      localObject2 = localObject1;
      if (!paramTicketHeaders.xtokens.isEmpty())
      {
        int i = 1;
        localObject3 = paramTicketHeaders.xtokens.entrySet().iterator();
        for (;;)
        {
          localObject2 = localObject1;
          if (!((Iterator)localObject3).hasNext()) {
            break;
          }
          Map.Entry localEntry = (Map.Entry)((Iterator)localObject3).next();
          localObject2 = localObject1;
          if (i == 0) {
            localObject2 = (String)localObject1 + ";";
          }
          localObject1 = (String)localObject2 + "\"" + (String)localEntry.getKey() + "\"=\"" + (String)localEntry.getValue() + "\"";
          i = 0;
        }
      }
    }
    localObject1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'", Locale.US);
    ((SimpleDateFormat)localObject1).setTimeZone(TimeZone.getTimeZone("UTC"));
    Object localObject3 = this.endpoint.openConnection();
    if ((localObject3 instanceof HttpURLConnection))
    {
      localObject3 = (HttpURLConnection)localObject3;
      ((HttpURLConnection)localObject3).setConnectTimeout(SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.HTTPTIMEOUTINTERVAL));
      ((HttpURLConnection)localObject3).setReadTimeout(SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.HTTPTIMEOUTINTERVAL));
      ((HttpURLConnection)localObject3).setInstanceFollowRedirects(false);
      ((HttpURLConnection)localObject3).setUseCaches(false);
      ((HttpURLConnection)localObject3).setDoOutput(true);
      ((HttpURLConnection)localObject3).setRequestMethod("POST");
      ((HttpURLConnection)localObject3).setRequestProperty("Content-Type", "application/x-json-stream; charset=utf-8");
      ((HttpURLConnection)localObject3).setRequestProperty("X-UploadTime", ((SimpleDateFormat)localObject1).format(new Date()).toString());
      ((HttpURLConnection)localObject3).setRequestProperty("Content-Length", Integer.toString(paramInt));
      if (paramBoolean)
      {
        ((HttpURLConnection)localObject3).setRequestProperty("Accept", "application/json");
        ((HttpURLConnection)localObject3).setRequestProperty("Accept-Encoding", "gzip, deflate");
        ((HttpURLConnection)localObject3).setRequestProperty("Content-Encoding", "deflate");
      }
      if (localObject2 != "")
      {
        ((HttpURLConnection)localObject3).setRequestProperty("X-Tickets", (String)localObject2);
        ((HttpURLConnection)localObject3).setRequestProperty("X-AuthXToken", paramTicketHeaders.authXToken);
        if (paramTicketHeaders.msaDeviceTicket != null) {
          ((HttpURLConnection)localObject3).setRequestProperty("X-AuthMsaDeviceTicket", paramTicketHeaders.msaDeviceTicket);
        }
      }
      return (HttpURLConnection)localObject3;
    }
    this.clientTelemetry.IncrementVortexHttpFailures(-1);
    throw new IOException("URL didn't return HttpsUrlConnection instance.");
  }
  
  protected String processResponseBody(BufferedReader paramBufferedReader)
  {
    return processResponseBodyConditionally(paramBufferedReader, true);
  }
  
  protected String processResponseBodyConditionally(BufferedReader paramBufferedReader, boolean paramBoolean)
  {
    localStringBuilder = new StringBuilder();
    try
    {
      for (;;)
      {
        String str = paramBufferedReader.readLine();
        if (str == null) {
          break;
        }
        localStringBuilder.append(str);
      }
      try
      {
        int i = new JSONObject(localStringBuilder.toString()).getInt("rej");
        this.clientTelemetry.IncrementRejectDropCount(i);
        this.logger.info("AndroidCll-EventSender", localStringBuilder.toString());
        return localStringBuilder.toString();
      }
      catch (JSONException paramBufferedReader)
      {
        for (;;)
        {
          this.logger.info("AndroidCll-EventSender", paramBufferedReader.getMessage());
        }
      }
      catch (RuntimeException paramBufferedReader)
      {
        for (;;)
        {
          this.logger.info("AndroidCll-EventSender", paramBufferedReader.getMessage());
        }
      }
    }
    catch (IOException paramBufferedReader)
    {
      this.logger.error("AndroidCll-EventSender", "Couldn't read response body");
      if (!paramBoolean) {}
    }
  }
  
  /* Error */
  public int sendEvent(byte[] paramArrayOfByte, boolean paramBoolean, TicketHeaders paramTicketHeaders)
    throws IOException
  {
    // Byte code:
    //   0: sipush 500
    //   3: istore 4
    //   5: aconst_null
    //   6: astore 9
    //   8: aconst_null
    //   9: astore 13
    //   11: aconst_null
    //   12: astore 11
    //   14: aconst_null
    //   15: astore 12
    //   17: aload_0
    //   18: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   21: invokevirtual 278	com/microsoft/cll/android/ClientTelemetry:IncrementVortexHttpAttempts	()V
    //   24: aload_0
    //   25: aload_1
    //   26: arraylength
    //   27: iload_2
    //   28: aload_3
    //   29: invokevirtual 280	com/microsoft/cll/android/EventSender:openConnection	(IZLcom/microsoft/cll/android/TicketHeaders;)Ljava/net/HttpURLConnection;
    //   32: astore 14
    //   34: aload 14
    //   36: invokevirtual 283	java/net/HttpURLConnection:connect	()V
    //   39: aload_0
    //   40: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   43: ldc 24
    //   45: ldc_w 285
    //   48: invokeinterface 254 3 0
    //   53: aload 14
    //   55: invokevirtual 289	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   58: astore_3
    //   59: aload_3
    //   60: aload_1
    //   61: invokevirtual 295	java/io/OutputStream:write	([B)V
    //   64: aload_3
    //   65: invokevirtual 298	java/io/OutputStream:flush	()V
    //   68: aload_3
    //   69: invokevirtual 301	java/io/OutputStream:close	()V
    //   72: aload_0
    //   73: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   76: ldc 24
    //   78: ldc_w 303
    //   81: invokeinterface 254 3 0
    //   86: aload_0
    //   87: invokespecial 305	com/microsoft/cll/android/EventSender:getTime	()J
    //   90: lstore 7
    //   92: aload 11
    //   94: astore 10
    //   96: aload 9
    //   98: astore_3
    //   99: iload 4
    //   101: istore 5
    //   103: aload 14
    //   105: invokevirtual 309	java/net/HttpURLConnection:getResponseCode	()I
    //   108: istore 6
    //   110: iload 6
    //   112: istore 4
    //   114: aload 13
    //   116: astore_1
    //   117: aload 11
    //   119: astore 10
    //   121: aload 9
    //   123: astore_3
    //   124: iload 4
    //   126: istore 5
    //   128: aload 14
    //   130: invokevirtual 313	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   133: astore 9
    //   135: aload 12
    //   137: astore_3
    //   138: aload 9
    //   140: astore 10
    //   142: aload 9
    //   144: ifnull +74 -> 218
    //   147: aload 9
    //   149: astore_1
    //   150: aload 11
    //   152: astore 10
    //   154: aload 9
    //   156: astore_3
    //   157: iload 4
    //   159: istore 5
    //   161: new 244	java/io/BufferedReader
    //   164: dup
    //   165: new 315	java/io/InputStreamReader
    //   168: dup
    //   169: aload 9
    //   171: invokespecial 318	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   174: invokespecial 321	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   177: astore 13
    //   179: iload 4
    //   181: sipush 200
    //   184: if_icmpne +160 -> 344
    //   187: iconst_1
    //   188: istore_2
    //   189: aload 9
    //   191: astore_1
    //   192: aload 11
    //   194: astore 10
    //   196: aload 9
    //   198: astore_3
    //   199: iload 4
    //   201: istore 5
    //   203: aload_0
    //   204: aload 13
    //   206: iload_2
    //   207: invokevirtual 238	com/microsoft/cll/android/EventSender:processResponseBodyConditionally	(Ljava/io/BufferedReader;Z)Ljava/lang/String;
    //   210: pop
    //   211: aload 9
    //   213: astore 10
    //   215: aload 12
    //   217: astore_3
    //   218: aload 10
    //   220: ifnull +8 -> 228
    //   223: aload 10
    //   225: invokevirtual 324	java/io/InputStream:close	()V
    //   228: aload_3
    //   229: ifnull +7 -> 236
    //   232: aload_3
    //   233: invokevirtual 324	java/io/InputStream:close	()V
    //   236: iload 4
    //   238: sipush 500
    //   241: if_icmplt +37 -> 278
    //   244: iload 4
    //   246: sipush 600
    //   249: if_icmpge +29 -> 278
    //   252: aload_0
    //   253: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   256: ldc 24
    //   258: ldc_w 326
    //   261: invokeinterface 254 3 0
    //   266: aload_0
    //   267: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   270: aload 14
    //   272: invokevirtual 309	java/net/HttpURLConnection:getResponseCode	()I
    //   275: invokevirtual 229	com/microsoft/cll/android/ClientTelemetry:IncrementVortexHttpFailures	(I)V
    //   278: aload_0
    //   279: invokespecial 305	com/microsoft/cll/android/EventSender:getTime	()J
    //   282: lload 7
    //   284: lsub
    //   285: lstore 7
    //   287: aload_0
    //   288: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   291: lload 7
    //   293: l2i
    //   294: invokevirtual 329	com/microsoft/cll/android/ClientTelemetry:SetAvgVortexLatencyMs	(I)V
    //   297: aload_0
    //   298: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   301: lload 7
    //   303: l2i
    //   304: invokevirtual 332	com/microsoft/cll/android/ClientTelemetry:SetMaxVortexLatencyMs	(I)V
    //   307: iload 4
    //   309: ireturn
    //   310: astore_1
    //   311: aload_0
    //   312: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   315: ldc 24
    //   317: ldc_w 285
    //   320: invokeinterface 254 3 0
    //   325: aload_1
    //   326: athrow
    //   327: astore_1
    //   328: aload_0
    //   329: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   332: ldc 24
    //   334: ldc_w 303
    //   337: invokeinterface 254 3 0
    //   342: aload_1
    //   343: athrow
    //   344: iconst_0
    //   345: istore_2
    //   346: goto -157 -> 189
    //   349: astore_3
    //   350: aload 11
    //   352: astore 10
    //   354: aload_1
    //   355: astore_3
    //   356: iload 4
    //   358: istore 5
    //   360: aload 14
    //   362: invokevirtual 335	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   365: astore 9
    //   367: aload 9
    //   369: astore_3
    //   370: aload_1
    //   371: astore 10
    //   373: aload 9
    //   375: ifnull -157 -> 218
    //   378: aload 9
    //   380: astore 10
    //   382: aload_1
    //   383: astore_3
    //   384: iload 4
    //   386: istore 5
    //   388: new 244	java/io/BufferedReader
    //   391: dup
    //   392: new 315	java/io/InputStreamReader
    //   395: dup
    //   396: aload 9
    //   398: invokespecial 318	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   401: invokespecial 321	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   404: astore 11
    //   406: iload 4
    //   408: sipush 400
    //   411: if_icmpne +124 -> 535
    //   414: iconst_1
    //   415: istore_2
    //   416: aload 9
    //   418: astore 10
    //   420: aload_1
    //   421: astore_3
    //   422: iload 4
    //   424: istore 5
    //   426: aload_0
    //   427: aload 11
    //   429: iload_2
    //   430: invokevirtual 238	com/microsoft/cll/android/EventSender:processResponseBodyConditionally	(Ljava/io/BufferedReader;Z)Ljava/lang/String;
    //   433: pop
    //   434: aload 9
    //   436: astore_3
    //   437: aload_1
    //   438: astore 10
    //   440: goto -222 -> 218
    //   443: astore_1
    //   444: aload_3
    //   445: ifnull +7 -> 452
    //   448: aload_3
    //   449: invokevirtual 324	java/io/InputStream:close	()V
    //   452: aload 10
    //   454: ifnull +8 -> 462
    //   457: aload 10
    //   459: invokevirtual 324	java/io/InputStream:close	()V
    //   462: iload 5
    //   464: sipush 500
    //   467: if_icmplt +37 -> 504
    //   470: iload 5
    //   472: sipush 600
    //   475: if_icmpge +29 -> 504
    //   478: aload_0
    //   479: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   482: ldc 24
    //   484: ldc_w 326
    //   487: invokeinterface 254 3 0
    //   492: aload_0
    //   493: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   496: aload 14
    //   498: invokevirtual 309	java/net/HttpURLConnection:getResponseCode	()I
    //   501: invokevirtual 229	com/microsoft/cll/android/ClientTelemetry:IncrementVortexHttpFailures	(I)V
    //   504: aload_0
    //   505: invokespecial 305	com/microsoft/cll/android/EventSender:getTime	()J
    //   508: lload 7
    //   510: lsub
    //   511: lstore 7
    //   513: aload_0
    //   514: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   517: lload 7
    //   519: l2i
    //   520: invokevirtual 329	com/microsoft/cll/android/ClientTelemetry:SetAvgVortexLatencyMs	(I)V
    //   523: aload_0
    //   524: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   527: lload 7
    //   529: l2i
    //   530: invokevirtual 332	com/microsoft/cll/android/ClientTelemetry:SetMaxVortexLatencyMs	(I)V
    //   533: aload_1
    //   534: athrow
    //   535: iconst_0
    //   536: istore_2
    //   537: goto -121 -> 416
    //   540: astore_1
    //   541: goto -427 -> 114
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	544	0	this	EventSender
    //   0	544	1	paramArrayOfByte	byte[]
    //   0	544	2	paramBoolean	boolean
    //   0	544	3	paramTicketHeaders	TicketHeaders
    //   3	420	4	i	int
    //   101	375	5	j	int
    //   108	3	6	k	int
    //   90	438	7	l	long
    //   6	429	9	localInputStream	java.io.InputStream
    //   94	364	10	localObject1	Object
    //   12	416	11	localBufferedReader1	BufferedReader
    //   15	201	12	localObject2	Object
    //   9	196	13	localBufferedReader2	BufferedReader
    //   32	465	14	localHttpURLConnection	HttpURLConnection
    // Exception table:
    //   from	to	target	type
    //   34	39	310	finally
    //   53	72	327	finally
    //   128	135	349	java/io/IOException
    //   161	179	349	java/io/IOException
    //   203	211	349	java/io/IOException
    //   103	110	443	finally
    //   128	135	443	finally
    //   161	179	443	finally
    //   203	211	443	finally
    //   360	367	443	finally
    //   388	406	443	finally
    //   426	434	443	finally
    //   103	110	540	java/io/IOException
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\EventSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */