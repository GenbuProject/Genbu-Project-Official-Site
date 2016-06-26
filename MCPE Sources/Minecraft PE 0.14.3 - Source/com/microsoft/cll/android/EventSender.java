package com.microsoft.cll.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
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
  private final String TAG = "EventSender";
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
  
  protected String getResponseBody(BufferedReader paramBufferedReader)
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
        this.clientTelemetry.IncremenetRejectDropCount(i);
        this.logger.info("EventSender", localStringBuilder.toString());
        return localStringBuilder.toString();
      }
      catch (JSONException paramBufferedReader)
      {
        for (;;)
        {
          this.logger.info("EventSender", paramBufferedReader.getMessage());
        }
      }
      catch (RuntimeException paramBufferedReader)
      {
        for (;;)
        {
          this.logger.info("EventSender", paramBufferedReader.getMessage());
        }
      }
    }
    catch (IOException paramBufferedReader)
    {
      this.logger.error("EventSender", "Couldn't read response body");
    }
  }
  
  protected HttpURLConnection openConnection(int paramInt, boolean paramBoolean, TicketHeaders paramTicketHeaders)
    throws IOException
  {
    Object localObject1 = this.endpoint.openConnection();
    if ((localObject1 instanceof HttpURLConnection))
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)localObject1;
      localHttpURLConnection.setInstanceFollowRedirects(false);
      localHttpURLConnection.setRequestMethod("POST");
      localHttpURLConnection.setUseCaches(false);
      localHttpURLConnection.setRequestProperty("Content-Type", "application/x-json-stream; charset=utf-8");
      localObject1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'", Locale.US);
      ((SimpleDateFormat)localObject1).setTimeZone(TimeZone.getTimeZone("UTC"));
      localHttpURLConnection.setRequestProperty("X-UploadTime", ((SimpleDateFormat)localObject1).format(new Date()).toString());
      localHttpURLConnection.setRequestProperty("Content-Length", Integer.toString(paramInt));
      localHttpURLConnection.setConnectTimeout(SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.HTTPTIMEOUTINTERVAL));
      localHttpURLConnection.setDoOutput(true);
      if (paramBoolean)
      {
        localHttpURLConnection.setRequestProperty("Accept", "application/json");
        localHttpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
        localHttpURLConnection.setRequestProperty("Content-Encoding", "deflate");
      }
      if ((paramTicketHeaders != null) && (!paramTicketHeaders.xtokens.isEmpty()))
      {
        localObject1 = "";
        paramInt = 1;
        Iterator localIterator = paramTicketHeaders.xtokens.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          Object localObject2 = localObject1;
          if (paramInt == 0) {
            localObject2 = (String)localObject1 + ";";
          }
          localObject1 = (String)localObject2 + "\"" + (String)localEntry.getKey() + "\"=\"" + (String)localEntry.getValue() + "\"";
          paramInt = 0;
        }
        localHttpURLConnection.setRequestProperty("X-Tickets", (String)localObject1);
        localHttpURLConnection.setRequestProperty("X-AuthXToken", paramTicketHeaders.authXToken);
        if (paramTicketHeaders.msaDeviceTicket != null) {
          localHttpURLConnection.setRequestProperty("X-AuthMsaDeviceTicket", paramTicketHeaders.msaDeviceTicket);
        }
      }
      localHttpURLConnection.connect();
      return localHttpURLConnection;
    }
    this.clientTelemetry.IncrementVortexHttpFailures(-1);
    throw new IOException("URL didn't return HttpsUrlConnection instance.");
  }
  
  public int sendEvent(String paramString, TicketHeaders paramTicketHeaders)
    throws IOException
  {
    return sendEvent(paramString.getBytes(Charset.forName("UTF-8")), false, paramTicketHeaders);
  }
  
  public int sendEvent(byte[] paramArrayOfByte, boolean paramBoolean, TicketHeaders paramTicketHeaders)
    throws IOException
  {
    this.clientTelemetry.IncrementVortexHttpAttempts();
    paramTicketHeaders = openConnection(paramArrayOfByte.length, paramBoolean, paramTicketHeaders);
    try
    {
      OutputStream localOutputStream = paramTicketHeaders.getOutputStream();
      localOutputStream.write(paramArrayOfByte);
      localOutputStream.flush();
      localOutputStream.close();
      long l = getTime();
      i = paramTicketHeaders.getResponseCode();
      l = getTime() - l;
      if ((i == 200) && (paramTicketHeaders.getInputStream() != null))
      {
        getResponseBody(new BufferedReader(new InputStreamReader(paramTicketHeaders.getInputStream())));
        paramTicketHeaders.getInputStream().close();
        this.clientTelemetry.SetAvgVortexLatencyMs((int)l);
        this.clientTelemetry.SetMaxVortexLatencyMs((int)l);
        return i;
      }
    }
    catch (Exception paramArrayOfByte)
    {
      for (;;)
      {
        int i;
        this.logger.error("EventSender", "Error writing data");
        continue;
        if ((i >= 500) && (i < 600))
        {
          this.logger.error("EventSender", "Bad Response Code");
          this.clientTelemetry.IncrementVortexHttpFailures(paramTicketHeaders.getResponseCode());
        }
        else if (i == 400)
        {
          getResponseBody(new BufferedReader(new InputStreamReader(paramTicketHeaders.getErrorStream())));
          paramTicketHeaders.getInputStream().close();
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\EventSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */