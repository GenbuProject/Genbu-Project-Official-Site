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
      //   11: istore 5
      //   13: iconst_0
      //   14: istore_3
      //   15: aload_0
      //   16: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   19: aload_0
      //   20: getfield 42	com/google/android/gms/measurement/internal/zzq$zzc:zzzq	Ljava/net/URL;
      //   23: invokevirtual 62	com/google/android/gms/measurement/internal/zzq:zzc	(Ljava/net/URL;)Ljava/net/HttpURLConnection;
      //   26: astore 6
      //   28: iload_3
      //   29: istore_2
      //   30: iload 5
      //   32: istore_1
      //   33: aload_0
      //   34: getfield 50	com/google/android/gms/measurement/internal/zzq$zzc:zzaWU	Ljava/util/Map;
      //   37: ifnull +155 -> 192
      //   40: iload_3
      //   41: istore_2
      //   42: iload 5
      //   44: istore_1
      //   45: aload_0
      //   46: getfield 50	com/google/android/gms/measurement/internal/zzq$zzc:zzaWU	Ljava/util/Map;
      //   49: invokeinterface 68 1 0
      //   54: invokeinterface 74 1 0
      //   59: astore 7
      //   61: iload_3
      //   62: istore_2
      //   63: iload 5
      //   65: istore_1
      //   66: aload 7
      //   68: invokeinterface 80 1 0
      //   73: ifeq +119 -> 192
      //   76: iload_3
      //   77: istore_2
      //   78: iload 5
      //   80: istore_1
      //   81: aload 7
      //   83: invokeinterface 84 1 0
      //   88: checkcast 86	java/util/Map$Entry
      //   91: astore 8
      //   93: iload_3
      //   94: istore_2
      //   95: iload 5
      //   97: istore_1
      //   98: aload 6
      //   100: aload 8
      //   102: invokeinterface 89 1 0
      //   107: checkcast 91	java/lang/String
      //   110: aload 8
      //   112: invokeinterface 94 1 0
      //   117: checkcast 91	java/lang/String
      //   120: invokevirtual 100	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   123: goto -62 -> 61
      //   126: astore 8
      //   128: iload_2
      //   129: istore_1
      //   130: aconst_null
      //   131: astore 9
      //   133: aload 6
      //   135: astore 7
      //   137: aload 9
      //   139: astore 6
      //   141: aload 6
      //   143: ifnull +8 -> 151
      //   146: aload 6
      //   148: invokevirtual 105	java/io/OutputStream:close	()V
      //   151: aload 7
      //   153: ifnull +8 -> 161
      //   156: aload 7
      //   158: invokevirtual 108	java/net/HttpURLConnection:disconnect	()V
      //   161: aload_0
      //   162: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   165: invokevirtual 112	com/google/android/gms/measurement/internal/zzq:zzCn	()Lcom/google/android/gms/measurement/internal/zzv;
      //   168: new 114	com/google/android/gms/measurement/internal/zzq$zzb
      //   171: dup
      //   172: aload_0
      //   173: getfield 48	com/google/android/gms/measurement/internal/zzq$zzc:zzTJ	Ljava/lang/String;
      //   176: aload_0
      //   177: getfield 46	com/google/android/gms/measurement/internal/zzq$zzc:zzaWT	Lcom/google/android/gms/measurement/internal/zzq$zza;
      //   180: iload_1
      //   181: aload 8
      //   183: aconst_null
      //   184: aconst_null
      //   185: invokespecial 117	com/google/android/gms/measurement/internal/zzq$zzb:<init>	(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/zzq$zza;ILjava/lang/Throwable;[BLcom/google/android/gms/measurement/internal/zzq$1;)V
      //   188: invokevirtual 123	com/google/android/gms/measurement/internal/zzv:zzg	(Ljava/lang/Runnable;)V
      //   191: return
      //   192: iload_3
      //   193: istore_2
      //   194: iload 5
      //   196: istore_1
      //   197: aload_0
      //   198: getfield 44	com/google/android/gms/measurement/internal/zzq$zzc:zzaWS	[B
      //   201: ifnull +398 -> 599
      //   204: iload_3
      //   205: istore_2
      //   206: iload 5
      //   208: istore_1
      //   209: aload_0
      //   210: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   213: invokevirtual 127	com/google/android/gms/measurement/internal/zzq:zzCk	()Lcom/google/android/gms/measurement/internal/zzaj;
      //   216: aload_0
      //   217: getfield 44	com/google/android/gms/measurement/internal/zzq$zzc:zzaWS	[B
      //   220: invokevirtual 132	com/google/android/gms/measurement/internal/zzaj:zzg	([B)[B
      //   223: astore 8
      //   225: iload_3
      //   226: istore_2
      //   227: iload 5
      //   229: istore_1
      //   230: aload_0
      //   231: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   234: invokevirtual 136	com/google/android/gms/measurement/internal/zzq:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   237: invokevirtual 142	com/google/android/gms/measurement/internal/zzp:zzCK	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   240: ldc -112
      //   242: aload 8
      //   244: arraylength
      //   245: invokestatic 150	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   248: invokevirtual 156	com/google/android/gms/measurement/internal/zzp$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
      //   251: iload_3
      //   252: istore_2
      //   253: iload 5
      //   255: istore_1
      //   256: aload 6
      //   258: iconst_1
      //   259: invokevirtual 160	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   262: iload_3
      //   263: istore_2
      //   264: iload 5
      //   266: istore_1
      //   267: aload 6
      //   269: ldc -94
      //   271: ldc -92
      //   273: invokevirtual 100	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   276: iload_3
      //   277: istore_2
      //   278: iload 5
      //   280: istore_1
      //   281: aload 6
      //   283: aload 8
      //   285: arraylength
      //   286: invokevirtual 168	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
      //   289: iload_3
      //   290: istore_2
      //   291: iload 5
      //   293: istore_1
      //   294: aload 6
      //   296: invokevirtual 171	java/net/HttpURLConnection:connect	()V
      //   299: iload_3
      //   300: istore_2
      //   301: iload 5
      //   303: istore_1
      //   304: aload 6
      //   306: invokevirtual 175	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   309: astore 7
      //   311: aload 7
      //   313: aload 8
      //   315: invokevirtual 179	java/io/OutputStream:write	([B)V
      //   318: aload 7
      //   320: invokevirtual 105	java/io/OutputStream:close	()V
      //   323: iload_3
      //   324: istore_2
      //   325: iload 5
      //   327: istore_1
      //   328: aload 6
      //   330: invokevirtual 183	java/net/HttpURLConnection:getResponseCode	()I
      //   333: istore_3
      //   334: iload_3
      //   335: istore_2
      //   336: iload_3
      //   337: istore_1
      //   338: aload_0
      //   339: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   342: aload 6
      //   344: invokestatic 187	com/google/android/gms/measurement/internal/zzq:zza	(Lcom/google/android/gms/measurement/internal/zzq;Ljava/net/HttpURLConnection;)[B
      //   347: astore 8
      //   349: iconst_0
      //   350: ifeq +11 -> 361
      //   353: new 189	java/lang/NullPointerException
      //   356: dup
      //   357: invokespecial 190	java/lang/NullPointerException:<init>	()V
      //   360: athrow
      //   361: aload 6
      //   363: ifnull +8 -> 371
      //   366: aload 6
      //   368: invokevirtual 108	java/net/HttpURLConnection:disconnect	()V
      //   371: aload_0
      //   372: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   375: invokevirtual 112	com/google/android/gms/measurement/internal/zzq:zzCn	()Lcom/google/android/gms/measurement/internal/zzv;
      //   378: new 114	com/google/android/gms/measurement/internal/zzq$zzb
      //   381: dup
      //   382: aload_0
      //   383: getfield 48	com/google/android/gms/measurement/internal/zzq$zzc:zzTJ	Ljava/lang/String;
      //   386: aload_0
      //   387: getfield 46	com/google/android/gms/measurement/internal/zzq$zzc:zzaWT	Lcom/google/android/gms/measurement/internal/zzq$zza;
      //   390: iload_3
      //   391: aconst_null
      //   392: aload 8
      //   394: aconst_null
      //   395: invokespecial 117	com/google/android/gms/measurement/internal/zzq$zzb:<init>	(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/zzq$zza;ILjava/lang/Throwable;[BLcom/google/android/gms/measurement/internal/zzq$1;)V
      //   398: invokevirtual 123	com/google/android/gms/measurement/internal/zzv:zzg	(Ljava/lang/Runnable;)V
      //   401: return
      //   402: astore 7
      //   404: aload_0
      //   405: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   408: invokevirtual 136	com/google/android/gms/measurement/internal/zzq:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   411: invokevirtual 193	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   414: ldc -61
      //   416: aload 7
      //   418: invokevirtual 156	com/google/android/gms/measurement/internal/zzp$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
      //   421: goto -60 -> 361
      //   424: astore 6
      //   426: aload_0
      //   427: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   430: invokevirtual 136	com/google/android/gms/measurement/internal/zzq:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   433: invokevirtual 193	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   436: ldc -61
      //   438: aload 6
      //   440: invokevirtual 156	com/google/android/gms/measurement/internal/zzp$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
      //   443: goto -292 -> 151
      //   446: astore 6
      //   448: aconst_null
      //   449: astore 8
      //   451: aconst_null
      //   452: astore 7
      //   454: iload 4
      //   456: istore_1
      //   457: aload 7
      //   459: ifnull +8 -> 467
      //   462: aload 7
      //   464: invokevirtual 105	java/io/OutputStream:close	()V
      //   467: aload 8
      //   469: ifnull +8 -> 477
      //   472: aload 8
      //   474: invokevirtual 108	java/net/HttpURLConnection:disconnect	()V
      //   477: aload_0
      //   478: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   481: invokevirtual 112	com/google/android/gms/measurement/internal/zzq:zzCn	()Lcom/google/android/gms/measurement/internal/zzv;
      //   484: new 114	com/google/android/gms/measurement/internal/zzq$zzb
      //   487: dup
      //   488: aload_0
      //   489: getfield 48	com/google/android/gms/measurement/internal/zzq$zzc:zzTJ	Ljava/lang/String;
      //   492: aload_0
      //   493: getfield 46	com/google/android/gms/measurement/internal/zzq$zzc:zzaWT	Lcom/google/android/gms/measurement/internal/zzq$zza;
      //   496: iload_1
      //   497: aconst_null
      //   498: aconst_null
      //   499: aconst_null
      //   500: invokespecial 117	com/google/android/gms/measurement/internal/zzq$zzb:<init>	(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/zzq$zza;ILjava/lang/Throwable;[BLcom/google/android/gms/measurement/internal/zzq$1;)V
      //   503: invokevirtual 123	com/google/android/gms/measurement/internal/zzv:zzg	(Ljava/lang/Runnable;)V
      //   506: aload 6
      //   508: athrow
      //   509: astore 7
      //   511: aload_0
      //   512: getfield 27	com/google/android/gms/measurement/internal/zzq$zzc:zzaWV	Lcom/google/android/gms/measurement/internal/zzq;
      //   515: invokevirtual 136	com/google/android/gms/measurement/internal/zzq:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   518: invokevirtual 193	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   521: ldc -61
      //   523: aload 7
      //   525: invokevirtual 156	com/google/android/gms/measurement/internal/zzp$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
      //   528: goto -61 -> 467
      //   531: astore 9
      //   533: aconst_null
      //   534: astore 7
      //   536: aload 6
      //   538: astore 8
      //   540: aload 9
      //   542: astore 6
      //   544: goto -87 -> 457
      //   547: astore 9
      //   549: aload 6
      //   551: astore 8
      //   553: iload 4
      //   555: istore_1
      //   556: aload 9
      //   558: astore 6
      //   560: goto -103 -> 457
      //   563: astore 8
      //   565: iconst_0
      //   566: istore_1
      //   567: aconst_null
      //   568: astore 6
      //   570: aconst_null
      //   571: astore 7
      //   573: goto -432 -> 141
      //   576: astore 9
      //   578: iconst_0
      //   579: istore_1
      //   580: aload 6
      //   582: astore 8
      //   584: aload 7
      //   586: astore 6
      //   588: aload 8
      //   590: astore 7
      //   592: aload 9
      //   594: astore 8
      //   596: goto -455 -> 141
      //   599: goto -276 -> 323
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	602	0	this	zzc
      //   32	548	1	i	int
      //   29	307	2	j	int
      //   14	377	3	k	int
      //   8	546	4	m	int
      //   11	315	5	n	int
      //   26	341	6	localObject1	Object
      //   424	15	6	localIOException1	IOException
      //   446	91	6	localObject2	Object
      //   542	45	6	localObject3	Object
      //   59	260	7	localObject4	Object
      //   402	15	7	localIOException2	IOException
      //   452	11	7	localObject5	Object
      //   509	15	7	localIOException3	IOException
      //   534	57	7	localObject6	Object
      //   91	20	8	localEntry	java.util.Map.Entry
      //   126	56	8	localIOException4	IOException
      //   223	329	8	localObject7	Object
      //   563	1	8	localIOException5	IOException
      //   582	13	8	localObject8	Object
      //   131	7	9	localObject9	Object
      //   531	10	9	localObject10	Object
      //   547	10	9	localObject11	Object
      //   576	17	9	localIOException6	IOException
      // Exception table:
      //   from	to	target	type
      //   33	40	126	java/io/IOException
      //   45	61	126	java/io/IOException
      //   66	76	126	java/io/IOException
      //   81	93	126	java/io/IOException
      //   98	123	126	java/io/IOException
      //   197	204	126	java/io/IOException
      //   209	225	126	java/io/IOException
      //   230	251	126	java/io/IOException
      //   256	262	126	java/io/IOException
      //   267	276	126	java/io/IOException
      //   281	289	126	java/io/IOException
      //   294	299	126	java/io/IOException
      //   304	311	126	java/io/IOException
      //   328	334	126	java/io/IOException
      //   338	349	126	java/io/IOException
      //   353	361	402	java/io/IOException
      //   146	151	424	java/io/IOException
      //   15	28	446	finally
      //   462	467	509	java/io/IOException
      //   33	40	531	finally
      //   45	61	531	finally
      //   66	76	531	finally
      //   81	93	531	finally
      //   98	123	531	finally
      //   197	204	531	finally
      //   209	225	531	finally
      //   230	251	531	finally
      //   256	262	531	finally
      //   267	276	531	finally
      //   281	289	531	finally
      //   294	299	531	finally
      //   304	311	531	finally
      //   328	334	531	finally
      //   338	349	531	finally
      //   311	323	547	finally
      //   15	28	563	java/io/IOException
      //   311	323	576	java/io/IOException
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */