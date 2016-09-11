package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzx;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class zzq
  extends zzz
{
  public zzq(zzw paramzzw)
  {
    super(paramzzw);
  }
  
  @WorkerThread
  private byte[] zzc(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    byte[] arrayOfByte = null;
    Object localObject = arrayOfByte;
    ByteArrayOutputStream localByteArrayOutputStream;
    try
    {
      localByteArrayOutputStream = new ByteArrayOutputStream();
      localObject = arrayOfByte;
      paramHttpURLConnection = paramHttpURLConnection.getInputStream();
      localObject = paramHttpURLConnection;
      arrayOfByte = new byte['?'];
      for (;;)
      {
        localObject = paramHttpURLConnection;
        int i = paramHttpURLConnection.read(arrayOfByte);
        if (i <= 0) {
          break;
        }
        localObject = paramHttpURLConnection;
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      localObject = paramHttpURLConnection;
    }
    finally
    {
      if (localObject != null) {
        ((InputStream)localObject).close();
      }
    }
    arrayOfByte = localByteArrayOutputStream.toByteArray();
    if (paramHttpURLConnection != null) {
      paramHttpURLConnection.close();
    }
    return arrayOfByte;
  }
  
  @WorkerThread
  public void zza(String paramString, URL paramURL, Map<String, String> paramMap, zza paramzza)
  {
    zzjk();
    zzjv();
    zzx.zzz(paramURL);
    zzx.zzz(paramzza);
    zzCn().zzh(new zzc(paramString, paramURL, null, paramMap, paramzza));
  }
  
  @WorkerThread
  public void zza(String paramString, URL paramURL, byte[] paramArrayOfByte, Map<String, String> paramMap, zza paramzza)
  {
    zzjk();
    zzjv();
    zzx.zzz(paramURL);
    zzx.zzz(paramArrayOfByte);
    zzx.zzz(paramzza);
    zzCn().zzh(new zzc(paramString, paramURL, paramArrayOfByte, paramMap, paramzza));
  }
  
  @WorkerThread
  protected HttpURLConnection zzc(URL paramURL)
    throws IOException
  {
    paramURL = paramURL.openConnection();
    if (!(paramURL instanceof HttpURLConnection)) {
      throw new IOException("Failed to obtain HTTP connection");
    }
    paramURL = (HttpURLConnection)paramURL;
    paramURL.setDefaultUseCaches(false);
    paramURL.setConnectTimeout((int)zzCp().zzBO());
    paramURL.setReadTimeout((int)zzCp().zzBP());
    paramURL.setInstanceFollowRedirects(false);
    paramURL.setDoInput(true);
    return paramURL;
  }
  
  protected void zziJ() {}
  
  public boolean zzlB()
  {
    zzjv();
    Object localObject1 = (ConnectivityManager)getContext().getSystemService("connectivity");
    try
    {
      localObject1 = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
      if ((localObject1 != null) && (((NetworkInfo)localObject1).isConnected())) {
        return true;
      }
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        Object localObject2 = null;
      }
    }
    return false;
  }
  
  @WorkerThread
  static abstract interface zza
  {
    public abstract void zza(String paramString, int paramInt, Throwable paramThrowable, byte[] paramArrayOfByte);
  }
  
  @WorkerThread
  private static class zzb
    implements Runnable
  {
    private final int zzBc;
    private final String zzTJ;
    private final zzq.zza zzaWP;
    private final Throwable zzaWQ;
    private final byte[] zzaWR;
    
    private zzb(String paramString, zzq.zza paramzza, int paramInt, Throwable paramThrowable, byte[] paramArrayOfByte)
    {
      zzx.zzz(paramzza);
      this.zzaWP = paramzza;
      this.zzBc = paramInt;
      this.zzaWQ = paramThrowable;
      this.zzaWR = paramArrayOfByte;
      this.zzTJ = paramString;
    }
    
    public void run()
    {
      this.zzaWP.zza(this.zzTJ, this.zzBc, this.zzaWQ, this.zzaWR);
    }
  }
  
  @WorkerThread
  private class zzc
    implements Runnable
  {
    private final String zzTJ;
    private final byte[] zzaWS;
    private final zzq.zza zzaWT;
    private final Map<String, String> zzaWU;
    private final URL zzzq;
    
    public zzc(URL paramURL, byte[] paramArrayOfByte, Map<String, String> paramMap, zzq.zza paramzza)
    {
      zzx.zzcM(paramURL);
      zzx.zzz(paramArrayOfByte);
      Object localObject;
      zzx.zzz(localObject);
      this.zzzq = paramArrayOfByte;
      this.zzaWS = paramMap;
      this.zzaWT = ((zzq.zza)localObject);
      this.zzTJ = paramURL;
      this.zzaWU = paramzza;
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   4: invokevirtual 59	com/google/android/gms/measurement/internal/zzq:zzCd	()V
      //   7: iconst_0
      //   8: istore 4
      //   10: iconst_0
      //   11: istore_3
      //   12: iconst_0
      //   13: istore 5
      //   15: aload_0
      //   16: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   19: aload_0
      //   20: getfield 42	com/google/android/gms/measurement/internal/zzq$zzc:zzzq	Ljava/net/URL;
      //   23: invokevirtual 62	com/google/android/gms/measurement/internal/zzq:zzc	(Ljava/net/URL;)Ljava/net/HttpURLConnection;
      //   26: astore 6
      //   28: iload 5
      //   30: istore_2
      //   31: iload_3
      //   32: istore_1
      //   33: aload_0
      //   34: getfield 50	com/google/android/gms/measurement/internal/zzq$zzc:zzaWU	Ljava/util/Map;
      //   37: ifnull +147 -> 184
      //   40: iload 5
      //   42: istore_2
      //   43: iload_3
      //   44: istore_1
      //   45: aload_0
      //   46: getfield 50	com/google/android/gms/measurement/internal/zzq$zzc:zzaWU	Ljava/util/Map;
      //   49: invokeinterface 68 1 0
      //   54: invokeinterface 74 1 0
      //   59: astore 8
      //   61: iload 5
      //   63: istore_2
      //   64: iload_3
      //   65: istore_1
      //   66: aload 8
      //   68: invokeinterface 80 1 0
      //   73: ifeq +111 -> 184
      //   76: iload 5
      //   78: istore_2
      //   79: iload_3
      //   80: istore_1
      //   81: aload 8
      //   83: invokeinterface 84 1 0
      //   88: checkcast 86	java/util/Map$Entry
      //   91: astore 7
      //   93: iload 5
      //   95: istore_2
      //   96: iload_3
      //   97: istore_1
      //   98: aload 6
      //   100: aload 7
      //   102: invokeinterface 89 1 0
      //   107: checkcast 91	java/lang/String
      //   110: aload 7
      //   112: invokeinterface 94 1 0
      //   117: checkcast 91	java/lang/String
      //   120: invokevirtual 100	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   123: goto -62 -> 61
      //   126: astore 8
      //   128: iload_2
      //   129: istore_1
      //   130: aconst_null
      //   131: astore 7
      //   133: aload 7
      //   135: ifnull +8 -> 143
      //   138: aload 7
      //   140: invokevirtual 105	java/io/OutputStream:close	()V
      //   143: aload 6
      //   145: ifnull +8 -> 153
      //   148: aload 6
      //   150: invokevirtual 108	java/net/HttpURLConnection:disconnect	()V
      //   153: aload_0
      //   154: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   157: invokevirtual 112	com/google/android/gms/measurement/internal/zzq:zzCn	()Lcom/google/android/gms/measurement/internal/zzv;
      //   160: new 114	com/google/android/gms/measurement/internal/zzq$zzb
      //   163: dup
      //   164: aload_0
      //   165: getfield 48	com/google/android/gms/measurement/internal/zzq$zzc:zzTJ	Ljava/lang/String;
      //   168: aload_0
      //   169: getfield 46	com/google/android/gms/measurement/internal/zzq$zzc:zzaWT	Lcom/google/android/gms/measurement/internal/zzq$zza;
      //   172: iload_1
      //   173: aload 8
      //   175: aconst_null
      //   176: aconst_null
      //   177: invokespecial 117	com/google/android/gms/measurement/internal/zzq$zzb:<init>	(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/zzq$zza;ILjava/lang/Throwable;[BLcom/google/android/gms/measurement/internal/zzq$1;)V
      //   180: invokevirtual 123	com/google/android/gms/measurement/internal/zzv:zzg	(Ljava/lang/Runnable;)V
      //   183: return
      //   184: iload 5
      //   186: istore_2
      //   187: iload_3
      //   188: istore_1
      //   189: aload_0
      //   190: getfield 44	com/google/android/gms/measurement/internal/zzq$zzc:zzaWS	[B
      //   193: ifnull +382 -> 575
      //   196: iload 5
      //   198: istore_2
      //   199: iload_3
      //   200: istore_1
      //   201: aload_0
      //   202: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   205: invokevirtual 127	com/google/android/gms/measurement/internal/zzq:zzCk	()Lcom/google/android/gms/measurement/internal/zzaj;
      //   208: aload_0
      //   209: getfield 44	com/google/android/gms/measurement/internal/zzq$zzc:zzaWS	[B
      //   212: invokevirtual 132	com/google/android/gms/measurement/internal/zzaj:zzg	([B)[B
      //   215: astore 8
      //   217: iload 5
      //   219: istore_2
      //   220: iload_3
      //   221: istore_1
      //   222: aload_0
      //   223: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   226: invokevirtual 136	com/google/android/gms/measurement/internal/zzq:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   229: invokevirtual 142	com/google/android/gms/measurement/internal/zzp:zzCK	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   232: ldc -112
      //   234: aload 8
      //   236: arraylength
      //   237: invokestatic 150	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   240: invokevirtual 156	com/google/android/gms/measurement/internal/zzp$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
      //   243: iload 5
      //   245: istore_2
      //   246: iload_3
      //   247: istore_1
      //   248: aload 6
      //   250: iconst_1
      //   251: invokevirtual 160	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   254: iload 5
      //   256: istore_2
      //   257: iload_3
      //   258: istore_1
      //   259: aload 6
      //   261: ldc -94
      //   263: ldc -92
      //   265: invokevirtual 100	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   268: iload 5
      //   270: istore_2
      //   271: iload_3
      //   272: istore_1
      //   273: aload 6
      //   275: aload 8
      //   277: arraylength
      //   278: invokevirtual 168	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
      //   281: iload 5
      //   283: istore_2
      //   284: iload_3
      //   285: istore_1
      //   286: aload 6
      //   288: invokevirtual 171	java/net/HttpURLConnection:connect	()V
      //   291: iload 5
      //   293: istore_2
      //   294: iload_3
      //   295: istore_1
      //   296: aload 6
      //   298: invokevirtual 175	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   301: astore 7
      //   303: aload 7
      //   305: aload 8
      //   307: invokevirtual 179	java/io/OutputStream:write	([B)V
      //   310: aload 7
      //   312: invokevirtual 105	java/io/OutputStream:close	()V
      //   315: iload 5
      //   317: istore_2
      //   318: iload_3
      //   319: istore_1
      //   320: aload 6
      //   322: invokevirtual 183	java/net/HttpURLConnection:getResponseCode	()I
      //   325: istore_3
      //   326: iload_3
      //   327: istore_2
      //   328: iload_3
      //   329: istore_1
      //   330: aload_0
      //   331: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   334: aload 6
      //   336: invokestatic 187	com/google/android/gms/measurement/internal/zzq:zza	(Lcom/google/android/gms/measurement/internal/zzq;Ljava/net/HttpURLConnection;)[B
      //   339: astore 8
      //   341: iconst_0
      //   342: ifeq +11 -> 353
      //   345: new 189	java/lang/NullPointerException
      //   348: dup
      //   349: invokespecial 190	java/lang/NullPointerException:<init>	()V
      //   352: athrow
      //   353: aload 6
      //   355: ifnull +8 -> 363
      //   358: aload 6
      //   360: invokevirtual 108	java/net/HttpURLConnection:disconnect	()V
      //   363: aload_0
      //   364: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   367: invokevirtual 112	com/google/android/gms/measurement/internal/zzq:zzCn	()Lcom/google/android/gms/measurement/internal/zzv;
      //   370: new 114	com/google/android/gms/measurement/internal/zzq$zzb
      //   373: dup
      //   374: aload_0
      //   375: getfield 48	com/google/android/gms/measurement/internal/zzq$zzc:zzTJ	Ljava/lang/String;
      //   378: aload_0
      //   379: getfield 46	com/google/android/gms/measurement/internal/zzq$zzc:zzaWT	Lcom/google/android/gms/measurement/internal/zzq$zza;
      //   382: iload_3
      //   383: aconst_null
      //   384: aload 8
      //   386: aconst_null
      //   387: invokespecial 117	com/google/android/gms/measurement/internal/zzq$zzb:<init>	(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/zzq$zza;ILjava/lang/Throwable;[BLcom/google/android/gms/measurement/internal/zzq$1;)V
      //   390: invokevirtual 123	com/google/android/gms/measurement/internal/zzv:zzg	(Ljava/lang/Runnable;)V
      //   393: return
      //   394: astore 7
      //   396: aload_0
      //   397: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   400: invokevirtual 136	com/google/android/gms/measurement/internal/zzq:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   403: invokevirtual 193	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   406: ldc -61
      //   408: aload 7
      //   410: invokevirtual 156	com/google/android/gms/measurement/internal/zzp$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
      //   413: goto -60 -> 353
      //   416: astore 7
      //   418: aload_0
      //   419: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   422: invokevirtual 136	com/google/android/gms/measurement/internal/zzq:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   425: invokevirtual 193	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   428: ldc -61
      //   430: aload 7
      //   432: invokevirtual 156	com/google/android/gms/measurement/internal/zzp$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
      //   435: goto -292 -> 143
      //   438: astore 6
      //   440: aconst_null
      //   441: astore 8
      //   443: aconst_null
      //   444: astore 7
      //   446: iload 4
      //   448: istore_1
      //   449: aload 7
      //   451: ifnull +8 -> 459
      //   454: aload 7
      //   456: invokevirtual 105	java/io/OutputStream:close	()V
      //   459: aload 8
      //   461: ifnull +8 -> 469
      //   464: aload 8
      //   466: invokevirtual 108	java/net/HttpURLConnection:disconnect	()V
      //   469: aload_0
      //   470: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   473: invokevirtual 112	com/google/android/gms/measurement/internal/zzq:zzCn	()Lcom/google/android/gms/measurement/internal/zzv;
      //   476: new 114	com/google/android/gms/measurement/internal/zzq$zzb
      //   479: dup
      //   480: aload_0
      //   481: getfield 48	com/google/android/gms/measurement/internal/zzq$zzc:zzTJ	Ljava/lang/String;
      //   484: aload_0
      //   485: getfield 46	com/google/android/gms/measurement/internal/zzq$zzc:zzaWT	Lcom/google/android/gms/measurement/internal/zzq$zza;
      //   488: iload_1
      //   489: aconst_null
      //   490: aconst_null
      //   491: aconst_null
      //   492: invokespecial 117	com/google/android/gms/measurement/internal/zzq$zzb:<init>	(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/zzq$zza;ILjava/lang/Throwable;[BLcom/google/android/gms/measurement/internal/zzq$1;)V
      //   495: invokevirtual 123	com/google/android/gms/measurement/internal/zzv:zzg	(Ljava/lang/Runnable;)V
      //   498: aload 6
      //   500: athrow
      //   501: astore 7
      //   503: aload_0
      //   504: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   507: invokevirtual 136	com/google/android/gms/measurement/internal/zzq:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   510: invokevirtual 193	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   513: ldc -61
      //   515: aload 7
      //   517: invokevirtual 156	com/google/android/gms/measurement/internal/zzp$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
      //   520: goto -61 -> 459
      //   523: astore 9
      //   525: aconst_null
      //   526: astore 7
      //   528: aload 6
      //   530: astore 8
      //   532: aload 9
      //   534: astore 6
      //   536: goto -87 -> 449
      //   539: astore 9
      //   541: aload 6
      //   543: astore 8
      //   545: iload 4
      //   547: istore_1
      //   548: aload 9
      //   550: astore 6
      //   552: goto -103 -> 449
      //   555: astore 8
      //   557: iconst_0
      //   558: istore_1
      //   559: aconst_null
      //   560: astore 7
      //   562: aconst_null
      //   563: astore 6
      //   565: goto -432 -> 133
      //   568: astore 8
      //   570: iconst_0
      //   571: istore_1
      //   572: goto -439 -> 133
      //   575: goto -260 -> 315
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	578	0	this	zzc
      //   32	540	1	i	int
      //   30	298	2	j	int
      //   11	372	3	k	int
      //   8	538	4	m	int
      //   13	303	5	n	int
      //   26	333	6	localHttpURLConnection	HttpURLConnection
      //   438	91	6	localObject1	Object
      //   534	30	6	localObject2	Object
      //   91	220	7	localObject3	Object
      //   394	15	7	localIOException1	IOException
      //   416	15	7	localIOException2	IOException
      //   444	11	7	localObject4	Object
      //   501	15	7	localIOException3	IOException
      //   526	35	7	localObject5	Object
      //   59	23	8	localIterator	java.util.Iterator
      //   126	48	8	localIOException4	IOException
      //   215	329	8	localObject6	Object
      //   555	1	8	localIOException5	IOException
      //   568	1	8	localIOException6	IOException
      //   523	10	9	localObject7	Object
      //   539	10	9	localObject8	Object
      // Exception table:
      //   from	to	target	type
      //   33	40	126	java/io/IOException
      //   45	61	126	java/io/IOException
      //   66	76	126	java/io/IOException
      //   81	93	126	java/io/IOException
      //   98	123	126	java/io/IOException
      //   189	196	126	java/io/IOException
      //   201	217	126	java/io/IOException
      //   222	243	126	java/io/IOException
      //   248	254	126	java/io/IOException
      //   259	268	126	java/io/IOException
      //   273	281	126	java/io/IOException
      //   286	291	126	java/io/IOException
      //   296	303	126	java/io/IOException
      //   320	326	126	java/io/IOException
      //   330	341	126	java/io/IOException
      //   345	353	394	java/io/IOException
      //   138	143	416	java/io/IOException
      //   15	28	438	finally
      //   454	459	501	java/io/IOException
      //   33	40	523	finally
      //   45	61	523	finally
      //   66	76	523	finally
      //   81	93	523	finally
      //   98	123	523	finally
      //   189	196	523	finally
      //   201	217	523	finally
      //   222	243	523	finally
      //   248	254	523	finally
      //   259	268	523	finally
      //   273	281	523	finally
      //   286	291	523	finally
      //   296	303	523	finally
      //   320	326	523	finally
      //   330	341	523	finally
      //   303	315	539	finally
      //   15	28	555	java/io/IOException
      //   303	315	568	java/io/IOException
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */