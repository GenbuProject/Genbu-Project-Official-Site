package com.microsoft.xbox.service.network.managers;

import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.StreamUtil;
import com.microsoft.xbox.toolkit.TimeMonitor;
import com.microsoft.xbox.toolkit.UrlUtil;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.network.AbstractXLEHttpClient;
import com.microsoft.xbox.toolkit.network.HttpClientFactory;
import com.microsoft.xbox.toolkit.network.XLEHttpStatusAndStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;

public class ServiceCommon
{
  public static final int MaxBIErrorParamLength = 2048;
  
  public static void AddWebHeaders(HttpUriRequest paramHttpUriRequest, List<Header> paramList)
  {
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        paramHttpUriRequest.addHeader((Header)paramList.next());
      }
    }
  }
  
  private static void ParseHttpResponseForStatus(String paramString1, int paramInt, String paramString2)
    throws XLEException
  {
    ParseHttpResponseForStatus(paramString1, paramInt, paramString2, null);
  }
  
  private static void ParseHttpResponseForStatus(String paramString1, int paramInt, String paramString2, InputStream paramInputStream)
    throws XLEException
  {
    int i;
    if ((paramInt >= 200) && (paramInt < 400)) {
      i = 1;
    }
    while (i == 0) {
      if (paramInt == -1)
      {
        throw new XLEException(3L);
        i = 0;
      }
      else
      {
        if ((paramInt == 401) || (paramInt == 403)) {
          throw new XLEException(1020L);
        }
        if (paramInt == 400)
        {
          if (paramInputStream == null) {
            throw new XLEException(15L);
          }
          throw new XLEException(15L, null, null, StreamUtil.ReadAsString(paramInputStream));
        }
        if (paramInt == 500) {
          throw new XLEException(13L);
        }
        if (paramInt == 503) {
          throw new XLEException(18L);
        }
        if (paramInt == 404) {
          throw new XLEException(21L);
        }
        throw new XLEException(4L);
      }
    }
  }
  
  public static boolean delete(String paramString, List<Header> paramList)
    throws XLEException
  {
    int i = deleteWithStatus(paramString, paramList);
    return (i == 200) || (i == 204);
  }
  
  public static boolean delete(String paramString1, List<Header> paramList, String paramString2)
    throws XLEException
  {
    try
    {
      if (JavaUtil.isNullOrEmpty(paramString2)) {
        return delete(paramString1, paramList);
      }
      boolean bool = delete(paramString1, paramList, paramString2.getBytes("UTF-8"));
      return bool;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      throw new XLEException(5L, paramString1);
    }
  }
  
  public static boolean delete(String paramString, List<Header> paramList, byte[] paramArrayOfByte)
    throws XLEException
  {
    boolean bool = false;
    Object localObject = UrlUtil.getEncodedUri(paramString);
    paramString = ((URI)localObject).toString();
    new TimeMonitor();
    localObject = new HttpDeleteWithRequestBody((URI)localObject);
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0)) {}
    try
    {
      ((HttpDeleteWithRequestBody)localObject).setEntity(new ByteArrayEntity(paramArrayOfByte));
      paramString = excuteHttpRequest((HttpUriRequest)localObject, paramString, paramList, false, 0);
      if ((paramString.statusCode == 200) || (paramString.statusCode == 204)) {
        bool = true;
      }
      paramString.close();
      return bool;
    }
    catch (Exception paramString)
    {
      throw new XLEException(5L, paramString);
    }
  }
  
  public static int deleteWithStatus(String paramString, List<Header> paramList)
    throws XLEException
  {
    paramString = UrlUtil.getEncodedUri(paramString);
    String str = paramString.toString();
    new TimeMonitor();
    paramString = excuteHttpRequest(new HttpDelete(paramString), str, paramList, false, 0);
    paramString.close();
    return paramString.statusCode;
  }
  
  private static XLEHttpStatusAndStream excuteHttpRequest(HttpUriRequest paramHttpUriRequest, String paramString, List<Header> paramList, boolean paramBoolean, int paramInt)
    throws XLEException
  {
    return excuteHttpRequest(paramHttpUriRequest, paramString, paramList, paramBoolean, paramInt, false);
  }
  
  private static XLEHttpStatusAndStream excuteHttpRequest(HttpUriRequest paramHttpUriRequest, String paramString, List<Header> paramList, boolean paramBoolean1, int paramInt, boolean paramBoolean2)
    throws XLEException
  {
    AddWebHeaders(paramHttpUriRequest, paramList);
    new XLEHttpStatusAndStream();
    XLEHttpStatusAndStream localXLEHttpStatusAndStream = HttpClientFactory.networkOperationsFactory.getHttpClient(paramInt).getHttpStatusAndStreamInternal(paramHttpUriRequest, true);
    if (!paramBoolean2) {}
    for (;;)
    {
      try
      {
        ParseHttpResponseForStatus(paramString, localXLEHttpStatusAndStream.statusCode, localXLEHttpStatusAndStream.statusLine);
        if ((localXLEHttpStatusAndStream.stream != null) || (!paramBoolean1)) {
          break label294;
        }
        throw new XLEException(7L);
      }
      catch (XLEException localXLEException)
      {
        JsonObject localJsonObject1 = new JsonObject();
        JsonObject localJsonObject2 = new JsonObject();
        paramList = "";
        if (localXLEHttpStatusAndStream != null) {
          continue;
        }
        paramInt = 0;
        String str = "";
        if (paramHttpUriRequest == null) {
          continue;
        }
        paramHttpUriRequest.getMethod();
        paramString = paramList;
        if (localXLEHttpStatusAndStream == null) {
          continue;
        }
        paramString = paramList;
        if (TextUtils.isEmpty(localXLEHttpStatusAndStream.statusLine)) {
          continue;
        }
        if (localXLEHttpStatusAndStream.statusLine.length() <= 2048) {
          continue;
        }
        paramString = localXLEHttpStatusAndStream.statusLine.substring(0, 2048);
        paramList = str;
        if (paramHttpUriRequest == null) {
          continue;
        }
        paramList = str;
        if (paramHttpUriRequest.getURI() == null) {
          continue;
        }
        paramList = paramHttpUriRequest.getURI().toString();
        paramHttpUriRequest = paramList;
        if (paramList.length() <= 2048) {
          continue;
        }
        paramHttpUriRequest = paramList.substring(0, 2048);
        localJsonObject1.addProperty("Request", paramHttpUriRequest);
        localJsonObject2.addProperty("code", Integer.valueOf(paramInt));
        localJsonObject2.addProperty("description", paramString);
        localJsonObject1.add("Response", localJsonObject2);
        throw localXLEException;
        paramInt = localXLEHttpStatusAndStream.statusCode;
        continue;
        paramString = localXLEHttpStatusAndStream.statusLine;
        continue;
      }
      ParseHttpResponseForStatus(paramString, localXLEHttpStatusAndStream.statusCode, localXLEHttpStatusAndStream.statusLine, localXLEHttpStatusAndStream.stream);
    }
    label294:
    return localXLEHttpStatusAndStream;
  }
  
  public static XLEHttpStatusAndStream getStreamAndStatus(String paramString, List<Header> paramList)
    throws XLEException
  {
    XLEHttpStatusAndStream localXLEHttpStatusAndStream = getStreamAndStatus(paramString, paramList, true, 0);
    paramString = localXLEHttpStatusAndStream;
    if (localXLEHttpStatusAndStream != null)
    {
      paramString = localXLEHttpStatusAndStream;
      if (!JavaUtil.isNullOrEmpty(localXLEHttpStatusAndStream.redirectUrl)) {
        paramString = getStreamAndStatus(localXLEHttpStatusAndStream.redirectUrl, paramList);
      }
    }
    return paramString;
  }
  
  private static XLEHttpStatusAndStream getStreamAndStatus(String paramString, List<Header> paramList, boolean paramBoolean, int paramInt)
    throws XLEException
  {
    return getStreamAndStatus(paramString, paramList, paramBoolean, paramInt, false);
  }
  
  private static XLEHttpStatusAndStream getStreamAndStatus(String paramString, List<Header> paramList, boolean paramBoolean1, int paramInt, boolean paramBoolean2)
    throws XLEException
  {
    if (paramBoolean1) {
      paramString = UrlUtil.getEncodedUri(paramString);
    }
    for (;;)
    {
      String str = paramString.toString();
      return excuteHttpRequest(new HttpGet(paramString), str, paramList, true, paramInt, paramBoolean2);
      try
      {
        paramString = new URI(paramString);
      }
      catch (URISyntaxException paramString)
      {
        paramString = null;
      }
    }
  }
  
  public static XLEHttpStatusAndStream postStreamWithStatus(String paramString, List<Header> paramList, byte[] paramArrayOfByte)
    throws XLEException
  {
    Object localObject = UrlUtil.getEncodedUri(paramString);
    paramString = ((URI)localObject).toString();
    localObject = new HttpPost((URI)localObject);
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0)) {}
    try
    {
      ((HttpPost)localObject).setEntity(new ByteArrayEntity(paramArrayOfByte));
      return excuteHttpRequest((HttpUriRequest)localObject, paramString, paramList, false, 0);
    }
    catch (Exception paramString)
    {
      throw new XLEException(5L, paramString);
    }
  }
  
  public static XLEHttpStatusAndStream postStringWithStatus(String paramString1, List<Header> paramList, String paramString2)
    throws XLEException
  {
    try
    {
      paramString1 = postStreamWithStatus(paramString1, paramList, paramString2.getBytes("UTF-8"));
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      throw new XLEException(5L, paramString1);
    }
  }
  
  public static XLEHttpStatusAndStream putStreamWithStatus(String paramString, List<Header> paramList, byte[] paramArrayOfByte)
    throws XLEException
  {
    Object localObject = UrlUtil.getEncodedUri(paramString);
    paramString = ((URI)localObject).toString();
    localObject = new HttpPut((URI)localObject);
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0)) {}
    try
    {
      ((HttpPut)localObject).setEntity(new ByteArrayEntity(paramArrayOfByte));
      return excuteHttpRequest((HttpUriRequest)localObject, paramString, paramList, false, 0);
    }
    catch (Exception paramString)
    {
      throw new XLEException(5L, paramString);
    }
  }
  
  public static XLEHttpStatusAndStream putStringWithStatus(String paramString1, List<Header> paramList, String paramString2)
    throws XLEException
  {
    try
    {
      paramString1 = putStreamWithStatus(paramString1, paramList, paramString2.getBytes("UTF-8"));
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      throw new XLEException(5L, paramString1);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\network\managers\ServiceCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */