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
    Object localObject3;
    int i;
    if (paramTicketHeaders != null)
    {
      localObject2 = localObject1;
      if (!paramTicketHeaders.xtokens.isEmpty())
      {
        localObject3 = paramTicketHeaders.xtokens.entrySet().iterator();
        i = 1;
        localObject2 = localObject1;
        if (((Iterator)localObject3).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)localObject3).next();
          if (i != 0) {
            break label398;
          }
          localObject1 = (String)localObject1 + ";";
        }
      }
    }
    label398:
    for (;;)
    {
      localObject1 = (String)localObject1 + "\"" + (String)((Map.Entry)localObject2).getKey() + "\"=\"" + (String)((Map.Entry)localObject2).getValue() + "\"";
      i = 0;
      break;
      localObject1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'", Locale.US);
      ((SimpleDateFormat)localObject1).setTimeZone(TimeZone.getTimeZone("UTC"));
      localObject3 = this.endpoint.openConnection();
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
    //   9: astore 11
    //   11: aload_0
    //   12: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   15: invokevirtual 278	com/microsoft/cll/android/ClientTelemetry:IncrementVortexHttpAttempts	()V
    //   18: aload_0
    //   19: aload_1
    //   20: arraylength
    //   21: iload_2
    //   22: aload_3
    //   23: invokevirtual 280	com/microsoft/cll/android/EventSender:openConnection	(IZLcom/microsoft/cll/android/TicketHeaders;)Ljava/net/HttpURLConnection;
    //   26: astore 12
    //   28: aload 12
    //   30: invokevirtual 283	java/net/HttpURLConnection:connect	()V
    //   33: aload_0
    //   34: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   37: ldc 24
    //   39: ldc_w 285
    //   42: invokeinterface 254 3 0
    //   47: aload 12
    //   49: invokevirtual 289	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   52: astore_3
    //   53: aload_3
    //   54: aload_1
    //   55: invokevirtual 295	java/io/OutputStream:write	([B)V
    //   58: aload_3
    //   59: invokevirtual 298	java/io/OutputStream:flush	()V
    //   62: aload_3
    //   63: invokevirtual 301	java/io/OutputStream:close	()V
    //   66: aload_0
    //   67: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   70: ldc 24
    //   72: ldc_w 303
    //   75: invokeinterface 254 3 0
    //   80: aload_0
    //   81: invokespecial 305	com/microsoft/cll/android/EventSender:getTime	()J
    //   84: lstore 7
    //   86: iload 4
    //   88: istore 5
    //   90: aload 12
    //   92: invokevirtual 309	java/net/HttpURLConnection:getResponseCode	()I
    //   95: istore 6
    //   97: iload 6
    //   99: istore 4
    //   101: aload 9
    //   103: astore_1
    //   104: iload 4
    //   106: istore 5
    //   108: aload 12
    //   110: invokevirtual 313	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   113: astore_3
    //   114: aload_3
    //   115: astore 9
    //   117: aload 11
    //   119: astore 10
    //   121: aload_3
    //   122: ifnull +55 -> 177
    //   125: aload_3
    //   126: astore_1
    //   127: aload_3
    //   128: astore 9
    //   130: new 244	java/io/BufferedReader
    //   133: dup
    //   134: new 315	java/io/InputStreamReader
    //   137: dup
    //   138: aload_3
    //   139: invokespecial 318	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   142: invokespecial 321	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   145: astore 10
    //   147: iload 4
    //   149: sipush 200
    //   152: if_icmpne +153 -> 305
    //   155: iconst_1
    //   156: istore_2
    //   157: aload_3
    //   158: astore_1
    //   159: aload_3
    //   160: astore 9
    //   162: aload_0
    //   163: aload 10
    //   165: iload_2
    //   166: invokevirtual 238	com/microsoft/cll/android/EventSender:processResponseBodyConditionally	(Ljava/io/BufferedReader;Z)Ljava/lang/String;
    //   169: pop
    //   170: aload 11
    //   172: astore 10
    //   174: aload_3
    //   175: astore 9
    //   177: aload 9
    //   179: ifnull +8 -> 187
    //   182: aload 9
    //   184: invokevirtual 324	java/io/InputStream:close	()V
    //   187: aload 10
    //   189: ifnull +8 -> 197
    //   192: aload 10
    //   194: invokevirtual 324	java/io/InputStream:close	()V
    //   197: iload 4
    //   199: sipush 500
    //   202: if_icmplt +37 -> 239
    //   205: iload 4
    //   207: sipush 600
    //   210: if_icmpge +29 -> 239
    //   213: aload_0
    //   214: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   217: ldc 24
    //   219: ldc_w 326
    //   222: invokeinterface 254 3 0
    //   227: aload_0
    //   228: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   231: aload 12
    //   233: invokevirtual 309	java/net/HttpURLConnection:getResponseCode	()I
    //   236: invokevirtual 229	com/microsoft/cll/android/ClientTelemetry:IncrementVortexHttpFailures	(I)V
    //   239: aload_0
    //   240: invokespecial 305	com/microsoft/cll/android/EventSender:getTime	()J
    //   243: lload 7
    //   245: lsub
    //   246: lstore 7
    //   248: aload_0
    //   249: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   252: lload 7
    //   254: l2i
    //   255: invokevirtual 329	com/microsoft/cll/android/ClientTelemetry:SetAvgVortexLatencyMs	(I)V
    //   258: aload_0
    //   259: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   262: lload 7
    //   264: l2i
    //   265: invokevirtual 332	com/microsoft/cll/android/ClientTelemetry:SetMaxVortexLatencyMs	(I)V
    //   268: iload 4
    //   270: ireturn
    //   271: astore_1
    //   272: aload_0
    //   273: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   276: ldc 24
    //   278: ldc_w 285
    //   281: invokeinterface 254 3 0
    //   286: aload_1
    //   287: athrow
    //   288: astore_1
    //   289: aload_0
    //   290: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   293: ldc 24
    //   295: ldc_w 303
    //   298: invokeinterface 254 3 0
    //   303: aload_1
    //   304: athrow
    //   305: iconst_0
    //   306: istore_2
    //   307: goto -150 -> 157
    //   310: astore_3
    //   311: aload_1
    //   312: astore 9
    //   314: aload 12
    //   316: invokevirtual 335	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   319: astore_3
    //   320: aload_1
    //   321: astore 9
    //   323: aload_3
    //   324: astore 10
    //   326: aload_3
    //   327: ifnull -150 -> 177
    //   330: new 244	java/io/BufferedReader
    //   333: dup
    //   334: new 315	java/io/InputStreamReader
    //   337: dup
    //   338: aload_3
    //   339: invokespecial 318	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   342: invokespecial 321	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   345: astore 9
    //   347: iload 4
    //   349: sipush 400
    //   352: if_icmpne +121 -> 473
    //   355: iconst_1
    //   356: istore_2
    //   357: aload_0
    //   358: aload 9
    //   360: iload_2
    //   361: invokevirtual 238	com/microsoft/cll/android/EventSender:processResponseBodyConditionally	(Ljava/io/BufferedReader;Z)Ljava/lang/String;
    //   364: pop
    //   365: aload_1
    //   366: astore 9
    //   368: aload_3
    //   369: astore 10
    //   371: goto -194 -> 177
    //   374: astore 10
    //   376: aload_1
    //   377: astore 9
    //   379: aload 10
    //   381: astore_1
    //   382: aload 9
    //   384: ifnull +8 -> 392
    //   387: aload 9
    //   389: invokevirtual 324	java/io/InputStream:close	()V
    //   392: aload_3
    //   393: ifnull +7 -> 400
    //   396: aload_3
    //   397: invokevirtual 324	java/io/InputStream:close	()V
    //   400: iload 4
    //   402: sipush 500
    //   405: if_icmplt +37 -> 442
    //   408: iload 4
    //   410: sipush 600
    //   413: if_icmpge +29 -> 442
    //   416: aload_0
    //   417: getfield 32	com/microsoft/cll/android/EventSender:logger	Lcom/microsoft/cll/android/ILogger;
    //   420: ldc 24
    //   422: ldc_w 326
    //   425: invokeinterface 254 3 0
    //   430: aload_0
    //   431: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   434: aload 12
    //   436: invokevirtual 309	java/net/HttpURLConnection:getResponseCode	()I
    //   439: invokevirtual 229	com/microsoft/cll/android/ClientTelemetry:IncrementVortexHttpFailures	(I)V
    //   442: aload_0
    //   443: invokespecial 305	com/microsoft/cll/android/EventSender:getTime	()J
    //   446: lload 7
    //   448: lsub
    //   449: lstore 7
    //   451: aload_0
    //   452: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   455: lload 7
    //   457: l2i
    //   458: invokevirtual 329	com/microsoft/cll/android/ClientTelemetry:SetAvgVortexLatencyMs	(I)V
    //   461: aload_0
    //   462: getfield 30	com/microsoft/cll/android/EventSender:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   465: lload 7
    //   467: l2i
    //   468: invokevirtual 332	com/microsoft/cll/android/ClientTelemetry:SetMaxVortexLatencyMs	(I)V
    //   471: aload_1
    //   472: athrow
    //   473: iconst_0
    //   474: istore_2
    //   475: goto -118 -> 357
    //   478: astore_1
    //   479: goto -378 -> 101
    //   482: astore_1
    //   483: aconst_null
    //   484: astore_3
    //   485: aconst_null
    //   486: astore 9
    //   488: iload 5
    //   490: istore 4
    //   492: goto -110 -> 382
    //   495: astore_1
    //   496: aconst_null
    //   497: astore_3
    //   498: goto -116 -> 382
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	501	0	this	EventSender
    //   0	501	1	paramArrayOfByte	byte[]
    //   0	501	2	paramBoolean	boolean
    //   0	501	3	paramTicketHeaders	TicketHeaders
    //   3	488	4	i	int
    //   88	401	5	j	int
    //   95	3	6	k	int
    //   84	382	7	l	long
    //   6	481	9	localObject1	Object
    //   119	251	10	localObject2	Object
    //   374	6	10	localObject3	Object
    //   9	162	11	localObject4	Object
    //   26	409	12	localHttpURLConnection	HttpURLConnection
    // Exception table:
    //   from	to	target	type
    //   28	33	271	finally
    //   47	66	288	finally
    //   108	114	310	java/io/IOException
    //   130	147	310	java/io/IOException
    //   162	170	310	java/io/IOException
    //   330	347	374	finally
    //   357	365	374	finally
    //   90	97	478	java/io/IOException
    //   90	97	482	finally
    //   108	114	482	finally
    //   130	147	495	finally
    //   162	170	495	finally
    //   314	320	495	finally
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\EventSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */