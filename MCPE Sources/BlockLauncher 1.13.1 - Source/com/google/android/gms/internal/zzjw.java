package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.WebResourceResponse;
import com.google.android.gms.ads.internal.zzr;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzhb
@TargetApi(11)
public class zzjw
  extends zzjq
{
  public zzjw(zzjp paramzzjp, boolean paramBoolean)
  {
    super(paramzzjp, paramBoolean);
  }
  
  /* Error */
  public WebResourceResponse shouldInterceptRequest(android.webkit.WebView paramWebView, String paramString)
  {
    // Byte code:
    //   0: ldc 25
    //   2: new 27	java/io/File
    //   5: dup
    //   6: aload_2
    //   7: invokespecial 30	java/io/File:<init>	(Ljava/lang/String;)V
    //   10: invokevirtual 34	java/io/File:getName	()Ljava/lang/String;
    //   13: invokevirtual 40	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   16: ifne +10 -> 26
    //   19: aload_0
    //   20: aload_1
    //   21: aload_2
    //   22: invokespecial 42	com/google/android/gms/internal/zzjq:shouldInterceptRequest	(Landroid/webkit/WebView;Ljava/lang/String;)Landroid/webkit/WebResourceResponse;
    //   25: areturn
    //   26: aload_1
    //   27: instanceof 44
    //   30: ifne +15 -> 45
    //   33: ldc 46
    //   35: invokestatic 51	com/google/android/gms/internal/zzin:zzaK	(Ljava/lang/String;)V
    //   38: aload_0
    //   39: aload_1
    //   40: aload_2
    //   41: invokespecial 42	com/google/android/gms/internal/zzjq:shouldInterceptRequest	(Landroid/webkit/WebView;Ljava/lang/String;)Landroid/webkit/WebResourceResponse;
    //   44: areturn
    //   45: aload_1
    //   46: checkcast 44	com/google/android/gms/internal/zzjp
    //   49: astore 4
    //   51: aload 4
    //   53: invokeinterface 55 1 0
    //   58: invokevirtual 59	com/google/android/gms/internal/zzjq:zzfo	()V
    //   61: aload 4
    //   63: invokeinterface 63 1 0
    //   68: getfield 69	com/google/android/gms/ads/internal/client/AdSizeParcel:zzui	Z
    //   71: ifeq +65 -> 136
    //   74: getstatic 75	com/google/android/gms/internal/zzbt:zzwf	Lcom/google/android/gms/internal/zzbp;
    //   77: invokevirtual 81	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   80: checkcast 36	java/lang/String
    //   83: astore_3
    //   84: new 83	java/lang/StringBuilder
    //   87: dup
    //   88: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   91: ldc 87
    //   93: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: aload_3
    //   97: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: ldc 93
    //   102: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: invokestatic 99	com/google/android/gms/internal/zzin:v	(Ljava/lang/String;)V
    //   111: aload_0
    //   112: aload 4
    //   114: invokeinterface 103 1 0
    //   119: aload_0
    //   120: getfield 107	com/google/android/gms/internal/zzjw:zzpD	Lcom/google/android/gms/internal/zzjp;
    //   123: invokeinterface 111 1 0
    //   128: getfield 117	com/google/android/gms/ads/internal/util/client/VersionInfoParcel:afmaVersion	Ljava/lang/String;
    //   131: aload_3
    //   132: invokevirtual 121	com/google/android/gms/internal/zzjw:zzd	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Landroid/webkit/WebResourceResponse;
    //   135: areturn
    //   136: aload 4
    //   138: invokeinterface 125 1 0
    //   143: ifeq +16 -> 159
    //   146: getstatic 128	com/google/android/gms/internal/zzbt:zzwe	Lcom/google/android/gms/internal/zzbp;
    //   149: invokevirtual 81	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   152: checkcast 36	java/lang/String
    //   155: astore_3
    //   156: goto -72 -> 84
    //   159: getstatic 131	com/google/android/gms/internal/zzbt:zzwd	Lcom/google/android/gms/internal/zzbp;
    //   162: invokevirtual 81	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   165: checkcast 36	java/lang/String
    //   168: astore_3
    //   169: goto -85 -> 84
    //   172: astore_3
    //   173: new 83	java/lang/StringBuilder
    //   176: dup
    //   177: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   180: ldc -123
    //   182: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: aload_3
    //   186: invokevirtual 138	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   189: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   195: invokestatic 51	com/google/android/gms/internal/zzin:zzaK	(Ljava/lang/String;)V
    //   198: aload_0
    //   199: aload_1
    //   200: aload_2
    //   201: invokespecial 42	com/google/android/gms/internal/zzjq:shouldInterceptRequest	(Landroid/webkit/WebView;Ljava/lang/String;)Landroid/webkit/WebResourceResponse;
    //   204: areturn
    //   205: astore_3
    //   206: goto -33 -> 173
    //   209: astore_3
    //   210: goto -37 -> 173
    //   213: astore_3
    //   214: goto -41 -> 173
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	217	0	this	zzjw
    //   0	217	1	paramWebView	android.webkit.WebView
    //   0	217	2	paramString	String
    //   83	86	3	str	String
    //   172	14	3	localInterruptedException	InterruptedException
    //   205	1	3	localTimeoutException	TimeoutException
    //   209	1	3	localIOException	IOException
    //   213	1	3	localExecutionException	ExecutionException
    //   49	88	4	localzzjp	zzjp
    // Exception table:
    //   from	to	target	type
    //   0	26	172	java/lang/InterruptedException
    //   26	45	172	java/lang/InterruptedException
    //   45	84	172	java/lang/InterruptedException
    //   84	136	172	java/lang/InterruptedException
    //   136	156	172	java/lang/InterruptedException
    //   159	169	172	java/lang/InterruptedException
    //   0	26	205	java/util/concurrent/TimeoutException
    //   26	45	205	java/util/concurrent/TimeoutException
    //   45	84	205	java/util/concurrent/TimeoutException
    //   84	136	205	java/util/concurrent/TimeoutException
    //   136	156	205	java/util/concurrent/TimeoutException
    //   159	169	205	java/util/concurrent/TimeoutException
    //   0	26	209	java/io/IOException
    //   26	45	209	java/io/IOException
    //   45	84	209	java/io/IOException
    //   84	136	209	java/io/IOException
    //   136	156	209	java/io/IOException
    //   159	169	209	java/io/IOException
    //   0	26	213	java/util/concurrent/ExecutionException
    //   26	45	213	java/util/concurrent/ExecutionException
    //   45	84	213	java/util/concurrent/ExecutionException
    //   84	136	213	java/util/concurrent/ExecutionException
    //   136	156	213	java/util/concurrent/ExecutionException
    //   159	169	213	java/util/concurrent/ExecutionException
  }
  
  protected WebResourceResponse zzd(Context paramContext, String paramString1, String paramString2)
    throws IOException, ExecutionException, InterruptedException, TimeoutException
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("User-Agent", zzr.zzbC().zze(paramContext, paramString1));
    localHashMap.put("Cache-Control", "max-stale=3600");
    paramContext = (String)new zziw(paramContext).zzb(paramString2, localHashMap).get(60L, TimeUnit.SECONDS);
    if (paramContext == null) {
      return null;
    }
    return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(paramContext.getBytes("UTF-8")));
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzjw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */