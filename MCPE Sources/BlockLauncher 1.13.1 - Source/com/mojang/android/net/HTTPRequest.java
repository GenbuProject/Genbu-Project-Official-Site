package com.mojang.android.net;

import android.text.TextUtils;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class HTTPRequest
{
  public static boolean debugNet = false;
  public String contentType;
  public String cookieData;
  public String requestBody;
  public String url;
  
  private static Header[] toApacheHeaders(HttpURLConnection paramHttpURLConnection)
  {
    Object localObject = paramHttpURLConnection.getHeaderFields();
    if (((Map)localObject).containsKey(null)) {}
    for (int i = ((Map)localObject).size() - 1;; i = ((Map)localObject).size())
    {
      paramHttpURLConnection = new Header[i];
      i = 0;
      localObject = ((Map)localObject).entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
        if (localEntry.getKey() != null)
        {
          paramHttpURLConnection[i] = new BasicHeader((String)localEntry.getKey(), TextUtils.join(",", (Iterable)localEntry.getValue()));
          i += 1;
        }
      }
    }
    return paramHttpURLConnection;
  }
  
  public void abort()
  {
    if (debugNet) {
      System.out.println("Abort");
    }
  }
  
  /* Error */
  public HTTPResponse send(String paramString)
  {
    // Byte code:
    //   0: getstatic 15	com/mojang/android/net/HTTPRequest:debugNet	Z
    //   3: ifeq +28 -> 31
    //   6: getstatic 91	java/lang/System:out	Ljava/io/PrintStream;
    //   9: new 105	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   16: ldc 108
    //   18: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: aload_1
    //   22: invokevirtual 112	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   28: invokevirtual 99	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   31: aconst_null
    //   32: astore 7
    //   34: aconst_null
    //   35: astore 6
    //   37: iconst_0
    //   38: istore_2
    //   39: aload 6
    //   41: astore 5
    //   43: aload 7
    //   45: astore 4
    //   47: new 118	java/net/URL
    //   50: dup
    //   51: aload_0
    //   52: getfield 120	com/mojang/android/net/HTTPRequest:url	Ljava/lang/String;
    //   55: invokespecial 122	java/net/URL:<init>	(Ljava/lang/String;)V
    //   58: invokevirtual 126	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   61: checkcast 23	java/net/HttpURLConnection
    //   64: astore 8
    //   66: aload 6
    //   68: astore 5
    //   70: aload 7
    //   72: astore 4
    //   74: aload 8
    //   76: ldc -128
    //   78: ldc -126
    //   80: invokevirtual 133	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   83: aload 6
    //   85: astore 5
    //   87: aload 7
    //   89: astore 4
    //   91: aload 8
    //   93: ldc -121
    //   95: aload_0
    //   96: getfield 137	com/mojang/android/net/HTTPRequest:cookieData	Ljava/lang/String;
    //   99: invokevirtual 133	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   102: aload 6
    //   104: astore 5
    //   106: aload 7
    //   108: astore 4
    //   110: aload 8
    //   112: iconst_1
    //   113: invokevirtual 141	java/net/HttpURLConnection:setDoInput	(Z)V
    //   116: aload 6
    //   118: astore 5
    //   120: aload 7
    //   122: astore 4
    //   124: aload 8
    //   126: iconst_1
    //   127: invokevirtual 144	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   130: aload 6
    //   132: astore 5
    //   134: aload 7
    //   136: astore 4
    //   138: aload 8
    //   140: ldc -110
    //   142: aload_0
    //   143: getfield 148	com/mojang/android/net/HTTPRequest:contentType	Ljava/lang/String;
    //   146: invokevirtual 133	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   149: aload 6
    //   151: astore 5
    //   153: aload 7
    //   155: astore 4
    //   157: aload 8
    //   159: aload_1
    //   160: invokevirtual 151	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   163: aload 6
    //   165: astore 5
    //   167: aload 7
    //   169: astore 4
    //   171: aload 8
    //   173: invokevirtual 154	java/net/HttpURLConnection:connect	()V
    //   176: aload 6
    //   178: astore 5
    //   180: aload 7
    //   182: astore 4
    //   184: new 156	java/io/OutputStreamWriter
    //   187: dup
    //   188: aload 8
    //   190: invokevirtual 160	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   193: invokespecial 163	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   196: astore_1
    //   197: aload 6
    //   199: astore 5
    //   201: aload 7
    //   203: astore 4
    //   205: aload_1
    //   206: aload_0
    //   207: getfield 165	com/mojang/android/net/HTTPRequest:requestBody	Ljava/lang/String;
    //   210: invokevirtual 168	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   213: aload 6
    //   215: astore 5
    //   217: aload 7
    //   219: astore 4
    //   221: aload_1
    //   222: invokevirtual 171	java/io/OutputStreamWriter:close	()V
    //   225: aload 7
    //   227: astore 4
    //   229: aload 8
    //   231: invokevirtual 174	java/net/HttpURLConnection:getResponseCode	()I
    //   234: istore_3
    //   235: iload_3
    //   236: istore_2
    //   237: aload 7
    //   239: astore 4
    //   241: aload 8
    //   243: invokevirtual 178	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   246: astore_1
    //   247: iload_3
    //   248: istore_2
    //   249: aload_1
    //   250: ifnonnull +84 -> 334
    //   253: aload_1
    //   254: astore 5
    //   256: aload_1
    //   257: astore 4
    //   259: new 103	java/lang/Exception
    //   262: dup
    //   263: ldc -76
    //   265: invokespecial 181	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   268: athrow
    //   269: astore_1
    //   270: aload 5
    //   272: astore 4
    //   274: aload_1
    //   275: invokevirtual 184	java/lang/Exception:printStackTrace	()V
    //   278: aload 5
    //   280: astore 4
    //   282: new 186	com/mojang/android/net/HTTPResponse
    //   285: dup
    //   286: iconst_0
    //   287: iconst_0
    //   288: aconst_null
    //   289: iconst_0
    //   290: anewarray 39	org/apache/http/Header
    //   293: invokespecial 189	com/mojang/android/net/HTTPResponse:<init>	(IILjava/lang/String;[Lorg/apache/http/Header;)V
    //   296: astore_1
    //   297: aload_1
    //   298: astore 4
    //   300: aload 5
    //   302: ifnull +11 -> 313
    //   305: aload 5
    //   307: invokevirtual 192	java/io/InputStream:close	()V
    //   310: aload_1
    //   311: astore 4
    //   313: aload 4
    //   315: areturn
    //   316: astore_1
    //   317: aload 6
    //   319: astore 5
    //   321: aload 7
    //   323: astore 4
    //   325: aload 8
    //   327: invokevirtual 195	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   330: astore_1
    //   331: goto -82 -> 249
    //   334: aload_1
    //   335: astore 5
    //   337: aload_1
    //   338: astore 4
    //   340: new 197	java/io/ByteArrayOutputStream
    //   343: dup
    //   344: invokespecial 198	java/io/ByteArrayOutputStream:<init>	()V
    //   347: astore 6
    //   349: aload_1
    //   350: astore 5
    //   352: aload_1
    //   353: astore 4
    //   355: sipush 4096
    //   358: newarray <illegal type>
    //   360: astore 7
    //   362: aload_1
    //   363: astore 5
    //   365: aload_1
    //   366: astore 4
    //   368: aload_1
    //   369: aload 7
    //   371: invokevirtual 202	java/io/InputStream:read	([B)I
    //   374: istore_3
    //   375: iload_3
    //   376: iconst_m1
    //   377: if_icmpeq +34 -> 411
    //   380: aload_1
    //   381: astore 5
    //   383: aload_1
    //   384: astore 4
    //   386: aload 6
    //   388: aload 7
    //   390: iconst_0
    //   391: iload_3
    //   392: invokevirtual 205	java/io/ByteArrayOutputStream:write	([BII)V
    //   395: goto -33 -> 362
    //   398: astore_1
    //   399: aload 4
    //   401: ifnull +8 -> 409
    //   404: aload 4
    //   406: invokevirtual 192	java/io/InputStream:close	()V
    //   409: aload_1
    //   410: athrow
    //   411: aload_1
    //   412: astore 5
    //   414: aload_1
    //   415: astore 4
    //   417: new 186	com/mojang/android/net/HTTPResponse
    //   420: dup
    //   421: iconst_1
    //   422: iload_2
    //   423: new 68	java/lang/String
    //   426: dup
    //   427: aload 6
    //   429: invokevirtual 209	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   432: ldc -45
    //   434: invokespecial 214	java/lang/String:<init>	([BLjava/lang/String;)V
    //   437: aload 8
    //   439: invokestatic 216	com/mojang/android/net/HTTPRequest:toApacheHeaders	(Ljava/net/HttpURLConnection;)[Lorg/apache/http/Header;
    //   442: invokespecial 189	com/mojang/android/net/HTTPResponse:<init>	(IILjava/lang/String;[Lorg/apache/http/Header;)V
    //   445: astore 6
    //   447: aload 6
    //   449: astore 4
    //   451: aload_1
    //   452: ifnull -139 -> 313
    //   455: aload_1
    //   456: invokevirtual 192	java/io/InputStream:close	()V
    //   459: aload 6
    //   461: areturn
    //   462: astore_1
    //   463: aload 6
    //   465: areturn
    //   466: astore 4
    //   468: aload_1
    //   469: areturn
    //   470: astore 4
    //   472: goto -63 -> 409
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	475	0	this	HTTPRequest
    //   0	475	1	paramString	String
    //   38	385	2	i	int
    //   234	158	3	j	int
    //   45	405	4	localObject1	Object
    //   466	1	4	localException1	Exception
    //   470	1	4	localException2	Exception
    //   41	372	5	localObject2	Object
    //   35	429	6	localObject3	Object
    //   32	357	7	arrayOfByte	byte[]
    //   64	374	8	localHttpURLConnection	HttpURLConnection
    // Exception table:
    //   from	to	target	type
    //   47	66	269	java/lang/Exception
    //   74	83	269	java/lang/Exception
    //   91	102	269	java/lang/Exception
    //   110	116	269	java/lang/Exception
    //   124	130	269	java/lang/Exception
    //   138	149	269	java/lang/Exception
    //   157	163	269	java/lang/Exception
    //   171	176	269	java/lang/Exception
    //   184	197	269	java/lang/Exception
    //   205	213	269	java/lang/Exception
    //   221	225	269	java/lang/Exception
    //   259	269	269	java/lang/Exception
    //   325	331	269	java/lang/Exception
    //   340	349	269	java/lang/Exception
    //   355	362	269	java/lang/Exception
    //   368	375	269	java/lang/Exception
    //   386	395	269	java/lang/Exception
    //   417	447	269	java/lang/Exception
    //   229	235	316	java/lang/Exception
    //   241	247	316	java/lang/Exception
    //   47	66	398	finally
    //   74	83	398	finally
    //   91	102	398	finally
    //   110	116	398	finally
    //   124	130	398	finally
    //   138	149	398	finally
    //   157	163	398	finally
    //   171	176	398	finally
    //   184	197	398	finally
    //   205	213	398	finally
    //   221	225	398	finally
    //   229	235	398	finally
    //   241	247	398	finally
    //   259	269	398	finally
    //   274	278	398	finally
    //   282	297	398	finally
    //   325	331	398	finally
    //   340	349	398	finally
    //   355	362	398	finally
    //   368	375	398	finally
    //   386	395	398	finally
    //   417	447	398	finally
    //   455	459	462	java/lang/Exception
    //   305	310	466	java/lang/Exception
    //   404	409	470	java/lang/Exception
  }
  
  public void setContentType(String paramString)
  {
    if (debugNet) {
      System.out.println("Content type: " + paramString);
    }
    this.contentType = paramString;
  }
  
  public void setCookieData(String paramString)
  {
    if (debugNet) {
      System.out.println("Cookie: " + paramString);
    }
    this.cookieData = paramString;
  }
  
  public void setRequestBody(String paramString)
  {
    if (debugNet) {
      System.out.println("Body: " + paramString);
    }
    this.requestBody = paramString;
  }
  
  public void setURL(String paramString)
  {
    if (debugNet) {
      System.out.println("URL: " + paramString);
    }
    this.url = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\mojang\android\net\HTTPRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */