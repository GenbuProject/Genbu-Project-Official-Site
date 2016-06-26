package net.zhuoweizhang.mcpelauncher;

import android.content.Context;
import com.integralblue.httpresponsecache.HttpResponseCache;
import java.io.File;
import java.lang.reflect.Method;
import java.net.ResponseCache;
import java.net.URL;

public class ScriptTextureDownloader
  implements Runnable
{
  public Runnable afterDownloadAction;
  public boolean canUseStale;
  public File file;
  public URL url;
  
  public ScriptTextureDownloader(URL paramURL, File paramFile)
  {
    this(paramURL, paramFile, null, true);
  }
  
  public ScriptTextureDownloader(URL paramURL, File paramFile, Runnable paramRunnable, boolean paramBoolean)
  {
    this.url = paramURL;
    this.file = paramFile;
    this.afterDownloadAction = paramRunnable;
    this.canUseStale = paramBoolean;
  }
  
  public static void attachCache(Context paramContext)
  {
    paramContext = new File(paramContext.getExternalCacheDir(), "http");
    try
    {
      Class.forName("android.net.http.HttpResponseCache").getMethod("install", new Class[] { File.class, Long.TYPE }).invoke(null, new Object[] { paramContext, Long.valueOf(10485760L) });
      return;
    }
    catch (Exception localException)
    {
      try
      {
        HttpResponseCache.install(paramContext, 10485760L);
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static void flushCache()
  {
    try
    {
      ResponseCache localResponseCache = ResponseCache.getDefault();
      if (localResponseCache == null) {
        return;
      }
      localResponseCache.getClass().getMethod("flush", new Class[0]).invoke(localResponseCache, new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  /* Error */
  protected void fetch()
    throws Exception
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 5
    //   5: aconst_null
    //   6: astore 6
    //   8: iconst_0
    //   9: istore_1
    //   10: aload_3
    //   11: astore 4
    //   13: getstatic 108	java/lang/System:out	Ljava/io/PrintStream;
    //   16: aload_0
    //   17: getfield 25	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:url	Ljava/net/URL;
    //   20: invokevirtual 114	java/io/PrintStream:println	(Ljava/lang/Object;)V
    //   23: aload_3
    //   24: astore 4
    //   26: aload_0
    //   27: getfield 25	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:url	Ljava/net/URL;
    //   30: invokevirtual 120	java/net/URL:getPath	()Ljava/lang/String;
    //   33: pop
    //   34: aload_3
    //   35: astore 4
    //   37: aload_0
    //   38: getfield 25	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:url	Ljava/net/URL;
    //   41: invokevirtual 124	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   44: checkcast 126	java/net/HttpURLConnection
    //   47: astore 7
    //   49: aload_3
    //   50: astore 4
    //   52: aload 7
    //   54: ldc -128
    //   56: ldc -126
    //   58: invokevirtual 134	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: aload_3
    //   62: astore 4
    //   64: aload_0
    //   65: getfield 31	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:canUseStale	Z
    //   68: ifeq +33 -> 101
    //   71: aload_3
    //   72: astore 4
    //   74: aload 7
    //   76: ldc -120
    //   78: new 138	java/lang/StringBuilder
    //   81: dup
    //   82: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   85: ldc -115
    //   87: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: ldc -110
    //   92: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   95: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: invokevirtual 134	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   101: aload_3
    //   102: astore 4
    //   104: aload 7
    //   106: iconst_1
    //   107: invokevirtual 156	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   110: aload_3
    //   111: astore 4
    //   113: aload 7
    //   115: iconst_1
    //   116: invokevirtual 159	java/net/HttpURLConnection:setDoInput	(Z)V
    //   119: aload_3
    //   120: astore 4
    //   122: aload 7
    //   124: invokevirtual 162	java/net/HttpURLConnection:connect	()V
    //   127: aload_3
    //   128: astore 4
    //   130: aload 7
    //   132: invokevirtual 166	java/net/HttpURLConnection:getResponseCode	()I
    //   135: istore_2
    //   136: iload_2
    //   137: istore_1
    //   138: aload_3
    //   139: astore 4
    //   141: aload 7
    //   143: invokevirtual 170	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   146: astore 7
    //   148: aload 7
    //   150: astore_3
    //   151: iload_2
    //   152: istore_1
    //   153: iload_1
    //   154: sipush 400
    //   157: if_icmpge +7 -> 164
    //   160: aload_3
    //   161: ifnonnull +116 -> 277
    //   164: aload_3
    //   165: astore 4
    //   167: getstatic 173	java/lang/System:err	Ljava/io/PrintStream;
    //   170: new 138	java/lang/StringBuilder
    //   173: dup
    //   174: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   177: ldc -81
    //   179: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: aload_0
    //   183: getfield 25	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:url	Ljava/net/URL;
    //   186: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   189: ldc -76
    //   191: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: iload_1
    //   195: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   198: ldc -74
    //   200: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: invokevirtual 185	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   209: aload_3
    //   210: ifnull +7 -> 217
    //   213: aload_3
    //   214: invokevirtual 190	java/io/InputStream:close	()V
    //   217: iconst_0
    //   218: ifeq +11 -> 229
    //   221: new 192	java/lang/NullPointerException
    //   224: dup
    //   225: invokespecial 193	java/lang/NullPointerException:<init>	()V
    //   228: athrow
    //   229: return
    //   230: astore 7
    //   232: aload_3
    //   233: astore 4
    //   235: aload 7
    //   237: invokevirtual 88	java/lang/Exception:printStackTrace	()V
    //   240: aload 5
    //   242: astore_3
    //   243: goto -90 -> 153
    //   246: astore_3
    //   247: aload 4
    //   249: astore 5
    //   251: aload 6
    //   253: astore 4
    //   255: aload 5
    //   257: ifnull +8 -> 265
    //   260: aload 5
    //   262: invokevirtual 190	java/io/InputStream:close	()V
    //   265: aload 4
    //   267: ifnull +8 -> 275
    //   270: aload 4
    //   272: invokevirtual 196	java/io/FileOutputStream:close	()V
    //   275: aload_3
    //   276: athrow
    //   277: aload_3
    //   278: astore 4
    //   280: aload_0
    //   281: getfield 27	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:file	Ljava/io/File;
    //   284: invokevirtual 199	java/io/File:getParentFile	()Ljava/io/File;
    //   287: invokevirtual 203	java/io/File:mkdirs	()Z
    //   290: pop
    //   291: aload_3
    //   292: astore 4
    //   294: new 195	java/io/FileOutputStream
    //   297: dup
    //   298: aload_0
    //   299: getfield 27	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:file	Ljava/io/File;
    //   302: invokespecial 206	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   305: astore 5
    //   307: sipush 4096
    //   310: newarray <illegal type>
    //   312: astore 4
    //   314: aload_3
    //   315: aload 4
    //   317: invokevirtual 210	java/io/InputStream:read	([B)I
    //   320: istore_1
    //   321: iload_1
    //   322: iconst_m1
    //   323: if_icmpeq +15 -> 338
    //   326: aload 5
    //   328: aload 4
    //   330: iconst_0
    //   331: iload_1
    //   332: invokevirtual 214	java/io/FileOutputStream:write	([BII)V
    //   335: goto -21 -> 314
    //   338: aload 5
    //   340: invokevirtual 216	java/io/FileOutputStream:flush	()V
    //   343: aload_3
    //   344: ifnull +7 -> 351
    //   347: aload_3
    //   348: invokevirtual 190	java/io/InputStream:close	()V
    //   351: aload 5
    //   353: ifnull +8 -> 361
    //   356: aload 5
    //   358: invokevirtual 196	java/io/FileOutputStream:close	()V
    //   361: return
    //   362: astore_3
    //   363: goto -146 -> 217
    //   366: astore_3
    //   367: return
    //   368: astore_3
    //   369: goto -18 -> 351
    //   372: astore_3
    //   373: goto -12 -> 361
    //   376: astore 5
    //   378: goto -113 -> 265
    //   381: astore 4
    //   383: goto -108 -> 275
    //   386: astore 6
    //   388: aload 5
    //   390: astore 4
    //   392: aload_3
    //   393: astore 5
    //   395: aload 6
    //   397: astore_3
    //   398: goto -143 -> 255
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	401	0	this	ScriptTextureDownloader
    //   9	323	1	i	int
    //   135	17	2	j	int
    //   1	242	3	localObject1	Object
    //   246	102	3	localObject2	Object
    //   362	1	3	localException1	Exception
    //   366	1	3	localException2	Exception
    //   368	1	3	localException3	Exception
    //   372	21	3	localException4	Exception
    //   397	1	3	localObject3	Object
    //   11	318	4	localObject4	Object
    //   381	1	4	localException5	Exception
    //   390	1	4	localException6	Exception
    //   3	354	5	localObject5	Object
    //   376	13	5	localException7	Exception
    //   393	1	5	localException8	Exception
    //   6	246	6	localObject6	Object
    //   386	10	6	localObject7	Object
    //   47	102	7	localObject8	Object
    //   230	6	7	localException9	Exception
    // Exception table:
    //   from	to	target	type
    //   130	136	230	java/lang/Exception
    //   141	148	230	java/lang/Exception
    //   13	23	246	finally
    //   26	34	246	finally
    //   37	49	246	finally
    //   52	61	246	finally
    //   64	71	246	finally
    //   74	101	246	finally
    //   104	110	246	finally
    //   113	119	246	finally
    //   122	127	246	finally
    //   130	136	246	finally
    //   141	148	246	finally
    //   167	209	246	finally
    //   235	240	246	finally
    //   280	291	246	finally
    //   294	307	246	finally
    //   213	217	362	java/lang/Exception
    //   221	229	366	java/lang/Exception
    //   347	351	368	java/lang/Exception
    //   356	361	372	java/lang/Exception
    //   260	265	376	java/lang/Exception
    //   270	275	381	java/lang/Exception
    //   307	314	386	finally
    //   314	321	386	finally
    //   326	335	386	finally
    //   338	343	386	finally
  }
  
  public void run()
  {
    try
    {
      fetch();
      if (this.afterDownloadAction == null)
      {
        ScriptManager.requestGraphicsReset();
        return;
      }
      ScriptManager.runOnMainThread(this.afterDownloadAction);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ScriptTextureDownloader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */