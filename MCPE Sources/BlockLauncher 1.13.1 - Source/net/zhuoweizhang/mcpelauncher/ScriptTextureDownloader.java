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
    //   1: astore 5
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore 6
    //   8: iconst_0
    //   9: istore_1
    //   10: aload 5
    //   12: astore 4
    //   14: getstatic 108	java/lang/System:out	Ljava/io/PrintStream;
    //   17: aload_0
    //   18: getfield 25	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:url	Ljava/net/URL;
    //   21: invokevirtual 114	java/io/PrintStream:println	(Ljava/lang/Object;)V
    //   24: aload 5
    //   26: astore 4
    //   28: aload_0
    //   29: getfield 25	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:url	Ljava/net/URL;
    //   32: invokevirtual 120	java/net/URL:getPath	()Ljava/lang/String;
    //   35: pop
    //   36: aload 5
    //   38: astore 4
    //   40: aload_0
    //   41: getfield 25	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:url	Ljava/net/URL;
    //   44: invokevirtual 124	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   47: checkcast 126	java/net/HttpURLConnection
    //   50: astore 7
    //   52: aload 5
    //   54: astore 4
    //   56: aload 7
    //   58: ldc -128
    //   60: ldc -126
    //   62: invokevirtual 134	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   65: aload 5
    //   67: astore 4
    //   69: aload_0
    //   70: getfield 31	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:canUseStale	Z
    //   73: ifeq +34 -> 107
    //   76: aload 5
    //   78: astore 4
    //   80: aload 7
    //   82: ldc -120
    //   84: new 138	java/lang/StringBuilder
    //   87: dup
    //   88: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   91: ldc -115
    //   93: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: ldc -110
    //   98: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   101: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   104: invokevirtual 134	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   107: aload 5
    //   109: astore 4
    //   111: aload 7
    //   113: iconst_1
    //   114: invokevirtual 156	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   117: aload 5
    //   119: astore 4
    //   121: aload 7
    //   123: iconst_1
    //   124: invokevirtual 159	java/net/HttpURLConnection:setDoInput	(Z)V
    //   127: aload 5
    //   129: astore 4
    //   131: aload 7
    //   133: invokevirtual 162	java/net/HttpURLConnection:connect	()V
    //   136: aload 5
    //   138: astore 4
    //   140: aload 7
    //   142: invokevirtual 166	java/net/HttpURLConnection:getResponseCode	()I
    //   145: istore_2
    //   146: iload_2
    //   147: istore_1
    //   148: aload 5
    //   150: astore 4
    //   152: aload 7
    //   154: invokevirtual 170	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   157: astore 7
    //   159: aload 7
    //   161: astore_3
    //   162: iload_2
    //   163: istore_1
    //   164: iload_1
    //   165: sipush 400
    //   168: if_icmpge +7 -> 175
    //   171: aload_3
    //   172: ifnonnull +114 -> 286
    //   175: aload_3
    //   176: astore 4
    //   178: getstatic 173	java/lang/System:err	Ljava/io/PrintStream;
    //   181: new 138	java/lang/StringBuilder
    //   184: dup
    //   185: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   188: ldc -81
    //   190: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: aload_0
    //   194: getfield 25	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:url	Ljava/net/URL;
    //   197: invokevirtual 178	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   200: ldc -76
    //   202: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   205: iload_1
    //   206: invokevirtual 149	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   209: ldc -74
    //   211: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   217: invokevirtual 185	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   220: aload_3
    //   221: ifnull +7 -> 228
    //   224: aload_3
    //   225: invokevirtual 190	java/io/InputStream:close	()V
    //   228: iconst_0
    //   229: ifeq +11 -> 240
    //   232: new 192	java/lang/NullPointerException
    //   235: dup
    //   236: invokespecial 193	java/lang/NullPointerException:<init>	()V
    //   239: athrow
    //   240: return
    //   241: astore 7
    //   243: aload 5
    //   245: astore 4
    //   247: aload 7
    //   249: invokevirtual 88	java/lang/Exception:printStackTrace	()V
    //   252: goto -88 -> 164
    //   255: astore_3
    //   256: aload 4
    //   258: astore 5
    //   260: aload 6
    //   262: astore 4
    //   264: aload 5
    //   266: ifnull +8 -> 274
    //   269: aload 5
    //   271: invokevirtual 190	java/io/InputStream:close	()V
    //   274: aload 4
    //   276: ifnull +8 -> 284
    //   279: aload 4
    //   281: invokevirtual 196	java/io/FileOutputStream:close	()V
    //   284: aload_3
    //   285: athrow
    //   286: aload_3
    //   287: astore 4
    //   289: aload_0
    //   290: getfield 27	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:file	Ljava/io/File;
    //   293: invokevirtual 199	java/io/File:getParentFile	()Ljava/io/File;
    //   296: invokevirtual 203	java/io/File:mkdirs	()Z
    //   299: pop
    //   300: aload_3
    //   301: astore 4
    //   303: new 195	java/io/FileOutputStream
    //   306: dup
    //   307: aload_0
    //   308: getfield 27	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:file	Ljava/io/File;
    //   311: invokespecial 206	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   314: astore 5
    //   316: sipush 4096
    //   319: newarray <illegal type>
    //   321: astore 4
    //   323: aload_3
    //   324: aload 4
    //   326: invokevirtual 210	java/io/InputStream:read	([B)I
    //   329: istore_1
    //   330: iload_1
    //   331: iconst_m1
    //   332: if_icmpeq +15 -> 347
    //   335: aload 5
    //   337: aload 4
    //   339: iconst_0
    //   340: iload_1
    //   341: invokevirtual 214	java/io/FileOutputStream:write	([BII)V
    //   344: goto -21 -> 323
    //   347: aload 5
    //   349: invokevirtual 216	java/io/FileOutputStream:flush	()V
    //   352: aload_3
    //   353: ifnull +7 -> 360
    //   356: aload_3
    //   357: invokevirtual 190	java/io/InputStream:close	()V
    //   360: aload 5
    //   362: ifnull +8 -> 370
    //   365: aload 5
    //   367: invokevirtual 196	java/io/FileOutputStream:close	()V
    //   370: return
    //   371: astore_3
    //   372: goto -144 -> 228
    //   375: astore_3
    //   376: return
    //   377: astore_3
    //   378: goto -18 -> 360
    //   381: astore_3
    //   382: goto -12 -> 370
    //   385: astore 5
    //   387: goto -113 -> 274
    //   390: astore 4
    //   392: goto -108 -> 284
    //   395: astore 6
    //   397: aload 5
    //   399: astore 4
    //   401: aload_3
    //   402: astore 5
    //   404: aload 6
    //   406: astore_3
    //   407: goto -143 -> 264
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	410	0	this	ScriptTextureDownloader
    //   9	332	1	i	int
    //   145	18	2	j	int
    //   4	221	3	localObject1	Object
    //   255	102	3	localObject2	Object
    //   371	1	3	localException1	Exception
    //   375	1	3	localException2	Exception
    //   377	1	3	localException3	Exception
    //   381	21	3	localException4	Exception
    //   406	1	3	localObject3	Object
    //   12	326	4	localObject4	Object
    //   390	1	4	localException5	Exception
    //   399	1	4	localException6	Exception
    //   1	365	5	localObject5	Object
    //   385	13	5	localException7	Exception
    //   402	1	5	localObject6	Object
    //   6	255	6	localObject7	Object
    //   395	10	6	localObject8	Object
    //   50	110	7	localObject9	Object
    //   241	7	7	localException8	Exception
    // Exception table:
    //   from	to	target	type
    //   140	146	241	java/lang/Exception
    //   152	159	241	java/lang/Exception
    //   14	24	255	finally
    //   28	36	255	finally
    //   40	52	255	finally
    //   56	65	255	finally
    //   69	76	255	finally
    //   80	107	255	finally
    //   111	117	255	finally
    //   121	127	255	finally
    //   131	136	255	finally
    //   140	146	255	finally
    //   152	159	255	finally
    //   178	220	255	finally
    //   247	252	255	finally
    //   289	300	255	finally
    //   303	316	255	finally
    //   224	228	371	java/lang/Exception
    //   232	240	375	java/lang/Exception
    //   356	360	377	java/lang/Exception
    //   365	370	381	java/lang/Exception
    //   269	274	385	java/lang/Exception
    //   279	284	390	java/lang/Exception
    //   316	323	395	finally
    //   323	330	395	finally
    //   335	344	395	finally
    //   347	352	395	finally
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\ScriptTextureDownloader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */