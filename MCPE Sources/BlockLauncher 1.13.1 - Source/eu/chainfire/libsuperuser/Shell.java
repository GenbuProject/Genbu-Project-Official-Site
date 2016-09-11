package eu.chainfire.libsuperuser;

import android.os.Handler;
import android.os.Looper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class Shell
{
  @Deprecated
  public static List<String> run(String paramString, String[] paramArrayOfString, boolean paramBoolean)
  {
    return run(paramString, paramArrayOfString, paramBoolean);
  }
  
  /* Error */
  public static List<String> run(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 56	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   4: astore 10
    //   6: new 58	java/util/ArrayList
    //   9: dup
    //   10: invokespecial 59	java/util/ArrayList:<init>	()V
    //   13: invokestatic 65	java/util/Collections:synchronizedList	(Ljava/util/List;)Ljava/util/List;
    //   16: astore 7
    //   18: aload_2
    //   19: astore 8
    //   21: aload_2
    //   22: ifnull +191 -> 213
    //   25: new 67	java/util/HashMap
    //   28: dup
    //   29: invokespecial 68	java/util/HashMap:<init>	()V
    //   32: astore 8
    //   34: aload 8
    //   36: invokestatic 74	java/lang/System:getenv	()Ljava/util/Map;
    //   39: invokeinterface 80 2 0
    //   44: aload_2
    //   45: arraylength
    //   46: istore 5
    //   48: iconst_0
    //   49: istore 4
    //   51: iload 4
    //   53: iload 5
    //   55: if_icmpge +51 -> 106
    //   58: aload_2
    //   59: iload 4
    //   61: aaload
    //   62: astore 9
    //   64: aload 9
    //   66: ldc 82
    //   68: invokevirtual 86	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   71: istore 6
    //   73: iload 6
    //   75: iflt +397 -> 472
    //   78: aload 8
    //   80: aload 9
    //   82: iconst_0
    //   83: iload 6
    //   85: invokevirtual 90	java/lang/String:substring	(II)Ljava/lang/String;
    //   88: aload 9
    //   90: iload 6
    //   92: iconst_1
    //   93: iadd
    //   94: invokevirtual 93	java/lang/String:substring	(I)Ljava/lang/String;
    //   97: invokeinterface 97 3 0
    //   102: pop
    //   103: goto +369 -> 472
    //   106: iconst_0
    //   107: istore 4
    //   109: aload 8
    //   111: invokeinterface 101 1 0
    //   116: anewarray 52	java/lang/String
    //   119: astore_2
    //   120: aload 8
    //   122: invokeinterface 105 1 0
    //   127: invokeinterface 111 1 0
    //   132: astore 9
    //   134: aload_2
    //   135: astore 8
    //   137: aload 9
    //   139: invokeinterface 117 1 0
    //   144: ifeq +69 -> 213
    //   147: aload 9
    //   149: invokeinterface 121 1 0
    //   154: checkcast 123	java/util/Map$Entry
    //   157: astore 8
    //   159: aload_2
    //   160: iload 4
    //   162: new 125	java/lang/StringBuilder
    //   165: dup
    //   166: invokespecial 126	java/lang/StringBuilder:<init>	()V
    //   169: aload 8
    //   171: invokeinterface 129 1 0
    //   176: checkcast 52	java/lang/String
    //   179: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: ldc 82
    //   184: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: aload 8
    //   189: invokeinterface 136 1 0
    //   194: checkcast 52	java/lang/String
    //   197: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   203: aastore
    //   204: iload 4
    //   206: iconst_1
    //   207: iadd
    //   208: istore 4
    //   210: goto -76 -> 134
    //   213: invokestatic 145	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   216: aload_0
    //   217: aload 8
    //   219: invokevirtual 149	java/lang/Runtime:exec	(Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/Process;
    //   222: astore 8
    //   224: new 151	java/io/DataOutputStream
    //   227: dup
    //   228: aload 8
    //   230: invokevirtual 157	java/lang/Process:getOutputStream	()Ljava/io/OutputStream;
    //   233: invokespecial 160	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   236: astore 11
    //   238: new 162	eu/chainfire/libsuperuser/StreamGobbler
    //   241: dup
    //   242: new 125	java/lang/StringBuilder
    //   245: dup
    //   246: invokespecial 126	java/lang/StringBuilder:<init>	()V
    //   249: aload 10
    //   251: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: ldc -92
    //   256: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   262: aload 8
    //   264: invokevirtual 168	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   267: aload 7
    //   269: invokespecial 171	eu/chainfire/libsuperuser/StreamGobbler:<init>	(Ljava/lang/String;Ljava/io/InputStream;Ljava/util/List;)V
    //   272: astore 9
    //   274: new 125	java/lang/StringBuilder
    //   277: dup
    //   278: invokespecial 126	java/lang/StringBuilder:<init>	()V
    //   281: aload 10
    //   283: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: ldc -83
    //   288: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   291: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   294: astore 10
    //   296: aload 8
    //   298: invokevirtual 176	java/lang/Process:getErrorStream	()Ljava/io/InputStream;
    //   301: astore 12
    //   303: iload_3
    //   304: ifeq +177 -> 481
    //   307: aload 7
    //   309: astore_2
    //   310: new 162	eu/chainfire/libsuperuser/StreamGobbler
    //   313: dup
    //   314: aload 10
    //   316: aload 12
    //   318: aload_2
    //   319: invokespecial 171	eu/chainfire/libsuperuser/StreamGobbler:<init>	(Ljava/lang/String;Ljava/io/InputStream;Ljava/util/List;)V
    //   322: astore_2
    //   323: aload 9
    //   325: invokevirtual 179	eu/chainfire/libsuperuser/StreamGobbler:start	()V
    //   328: aload_2
    //   329: invokevirtual 179	eu/chainfire/libsuperuser/StreamGobbler:start	()V
    //   332: aload_1
    //   333: arraylength
    //   334: istore 5
    //   336: iconst_0
    //   337: istore 4
    //   339: iload 4
    //   341: iload 5
    //   343: if_icmpge +48 -> 391
    //   346: aload_1
    //   347: iload 4
    //   349: aaload
    //   350: astore 10
    //   352: aload 11
    //   354: new 125	java/lang/StringBuilder
    //   357: dup
    //   358: invokespecial 126	java/lang/StringBuilder:<init>	()V
    //   361: aload 10
    //   363: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   366: ldc -75
    //   368: invokevirtual 133	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   374: invokevirtual 185	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   377: aload 11
    //   379: invokevirtual 188	java/io/DataOutputStream:flush	()V
    //   382: iload 4
    //   384: iconst_1
    //   385: iadd
    //   386: istore 4
    //   388: goto -49 -> 339
    //   391: aload 11
    //   393: ldc -66
    //   395: invokevirtual 185	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
    //   398: aload 11
    //   400: invokevirtual 188	java/io/DataOutputStream:flush	()V
    //   403: aload 8
    //   405: invokevirtual 193	java/lang/Process:waitFor	()I
    //   408: pop
    //   409: aload 11
    //   411: invokevirtual 196	java/io/DataOutputStream:close	()V
    //   414: aload 9
    //   416: invokevirtual 199	eu/chainfire/libsuperuser/StreamGobbler:join	()V
    //   419: aload_2
    //   420: invokevirtual 199	eu/chainfire/libsuperuser/StreamGobbler:join	()V
    //   423: aload 8
    //   425: invokevirtual 202	java/lang/Process:destroy	()V
    //   428: aload 7
    //   430: astore_1
    //   431: aload_0
    //   432: ldc -52
    //   434: invokevirtual 208	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   437: ifeq +23 -> 460
    //   440: aload 8
    //   442: invokevirtual 211	java/lang/Process:exitValue	()I
    //   445: istore 4
    //   447: aload 7
    //   449: astore_1
    //   450: iload 4
    //   452: sipush 255
    //   455: if_icmpne +5 -> 460
    //   458: aconst_null
    //   459: astore_1
    //   460: aload_1
    //   461: areturn
    //   462: astore_0
    //   463: aconst_null
    //   464: areturn
    //   465: astore_0
    //   466: aconst_null
    //   467: areturn
    //   468: astore_1
    //   469: goto -55 -> 414
    //   472: iload 4
    //   474: iconst_1
    //   475: iadd
    //   476: istore 4
    //   478: goto -427 -> 51
    //   481: aconst_null
    //   482: astore_2
    //   483: goto -173 -> 310
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	486	0	paramString	String
    //   0	486	1	paramArrayOfString1	String[]
    //   0	486	2	paramArrayOfString2	String[]
    //   0	486	3	paramBoolean	boolean
    //   49	428	4	i	int
    //   46	298	5	j	int
    //   71	23	6	k	int
    //   16	432	7	localList	List
    //   19	422	8	localObject1	Object
    //   62	353	9	localObject2	Object
    //   4	358	10	str	String
    //   236	174	11	localDataOutputStream	DataOutputStream
    //   301	16	12	localInputStream	java.io.InputStream
    // Exception table:
    //   from	to	target	type
    //   25	48	462	java/io/IOException
    //   64	73	462	java/io/IOException
    //   78	103	462	java/io/IOException
    //   109	134	462	java/io/IOException
    //   137	204	462	java/io/IOException
    //   213	303	462	java/io/IOException
    //   310	336	462	java/io/IOException
    //   352	382	462	java/io/IOException
    //   391	409	462	java/io/IOException
    //   414	428	462	java/io/IOException
    //   431	447	462	java/io/IOException
    //   25	48	465	java/lang/InterruptedException
    //   64	73	465	java/lang/InterruptedException
    //   78	103	465	java/lang/InterruptedException
    //   109	134	465	java/lang/InterruptedException
    //   137	204	465	java/lang/InterruptedException
    //   213	303	465	java/lang/InterruptedException
    //   310	336	465	java/lang/InterruptedException
    //   352	382	465	java/lang/InterruptedException
    //   391	409	465	java/lang/InterruptedException
    //   409	414	465	java/lang/InterruptedException
    //   414	428	465	java/lang/InterruptedException
    //   431	447	465	java/lang/InterruptedException
    //   409	414	468	java/io/IOException
  }
  
  public static class Builder
  {
    private boolean autoHandler = true;
    private List<Shell.Command> commands = new LinkedList();
    private Map<String, String> environment = new HashMap();
    private Handler handler = null;
    private StreamGobbler.OnLineListener onSTDERRLineListener = null;
    private StreamGobbler.OnLineListener onSTDOUTLineListener = null;
    private String shell = "sh";
    private boolean wantSTDERR = false;
    
    public Builder addCommand(String paramString)
    {
      return addCommand(paramString, 0, null);
    }
    
    public Builder addCommand(String paramString, int paramInt, Shell.OnCommandResultListener paramOnCommandResultListener)
    {
      return addCommand(new String[] { paramString }, paramInt, paramOnCommandResultListener);
    }
    
    public Builder addCommand(List<String> paramList)
    {
      return addCommand(paramList, 0, null);
    }
    
    public Builder addCommand(List<String> paramList, int paramInt, Shell.OnCommandResultListener paramOnCommandResultListener)
    {
      return addCommand((String[])paramList.toArray(new String[paramList.size()]), paramInt, paramOnCommandResultListener);
    }
    
    public Builder addCommand(String[] paramArrayOfString)
    {
      return addCommand(paramArrayOfString, 0, null);
    }
    
    public Builder addCommand(String[] paramArrayOfString, int paramInt, Shell.OnCommandResultListener paramOnCommandResultListener)
    {
      this.commands.add(new Shell.Command(paramArrayOfString, paramInt, paramOnCommandResultListener));
      return this;
    }
    
    public Builder addEnvironment(String paramString1, String paramString2)
    {
      this.environment.put(paramString1, paramString2);
      return this;
    }
    
    public Builder addEnvironment(Map<String, String> paramMap)
    {
      this.environment.putAll(paramMap);
      return this;
    }
    
    public Shell.Interactive open()
    {
      return new Shell.Interactive(this, null);
    }
    
    public Builder setAutoHandler(boolean paramBoolean)
    {
      this.autoHandler = paramBoolean;
      return this;
    }
    
    public Builder setHandler(Handler paramHandler)
    {
      this.handler = paramHandler;
      return this;
    }
    
    public Builder setOnSTDERRLineListener(StreamGobbler.OnLineListener paramOnLineListener)
    {
      this.onSTDERRLineListener = paramOnLineListener;
      return this;
    }
    
    public Builder setOnSTDOUTLineListener(StreamGobbler.OnLineListener paramOnLineListener)
    {
      this.onSTDOUTLineListener = paramOnLineListener;
      return this;
    }
    
    public Builder setShell(String paramString)
    {
      this.shell = paramString;
      return this;
    }
    
    public Builder setWantSTDERR(boolean paramBoolean)
    {
      this.wantSTDERR = paramBoolean;
      return this;
    }
    
    public Builder useSH()
    {
      return setShell("sh");
    }
    
    public Builder useSU()
    {
      return setShell("su");
    }
  }
  
  private static class Command
  {
    private static int commandCounter = 0;
    private final int code;
    private final String[] commands;
    private final String marker;
    private final Shell.OnCommandResultListener onCommandResultListener;
    
    public Command(String[] paramArrayOfString, int paramInt, Shell.OnCommandResultListener paramOnCommandResultListener)
    {
      this.commands = paramArrayOfString;
      this.code = paramInt;
      this.onCommandResultListener = paramOnCommandResultListener;
      paramArrayOfString = new StringBuilder().append(UUID.randomUUID().toString());
      paramInt = commandCounter + 1;
      commandCounter = paramInt;
      this.marker = String.format("-%08x", new Object[] { Integer.valueOf(paramInt) });
    }
  }
  
  public static class Interactive
  {
    private StreamGobbler STDERR = null;
    private DataOutputStream STDIN = null;
    private StreamGobbler STDOUT = null;
    private final boolean autoHandler;
    private volatile List<String> buffer = null;
    private Object callbackSync = new Object();
    private volatile int callbacks = 0;
    private volatile boolean closed = true;
    private volatile Shell.Command command = null;
    private final List<Shell.Command> commands;
    private final Map<String, String> environment;
    private final Handler handler;
    private volatile boolean idle = true;
    private Object idleSync = new Object();
    private volatile int lastExitCode = 0;
    private volatile String lastMarkerSTDERR = null;
    private volatile String lastMarkerSTDOUT = null;
    private final StreamGobbler.OnLineListener onSTDERRLineListener;
    private final StreamGobbler.OnLineListener onSTDOUTLineListener;
    private Process process = null;
    private volatile boolean running = false;
    private final String shell;
    private final boolean wantSTDERR;
    
    private Interactive(Shell.Builder paramBuilder)
    {
      this.autoHandler = paramBuilder.autoHandler;
      this.shell = paramBuilder.shell;
      this.wantSTDERR = paramBuilder.wantSTDERR;
      this.commands = paramBuilder.commands;
      this.environment = paramBuilder.environment;
      this.onSTDOUTLineListener = paramBuilder.onSTDOUTLineListener;
      this.onSTDERRLineListener = paramBuilder.onSTDERRLineListener;
      if ((Looper.myLooper() != null) && (paramBuilder.handler == null) && (this.autoHandler)) {}
      for (this.handler = new Handler();; this.handler = paramBuilder.handler)
      {
        open();
        return;
      }
    }
    
    private void addBuffer(String paramString)
    {
      try
      {
        if (this.buffer != null) {
          this.buffer.add(paramString);
        }
        return;
      }
      finally
      {
        paramString = finally;
        throw paramString;
      }
    }
    
    private void endCallback()
    {
      synchronized (this.callbackSync)
      {
        this.callbacks -= 1;
        if (this.callbacks == 0) {
          this.callbackSync.notifyAll();
        }
        return;
      }
    }
    
    private boolean open()
    {
      for (;;)
      {
        try
        {
          if (this.environment.size() != 0) {
            continue;
          }
          this.process = Runtime.getRuntime().exec(this.shell);
          this.STDIN = new DataOutputStream(this.process.getOutputStream());
          this.STDOUT = new StreamGobbler(this.shell.toUpperCase() + "-", this.process.getInputStream(), new StreamGobbler.OnLineListener()
          {
            public void onLine(String paramAnonymousString)
            {
              if (paramAnonymousString.startsWith(Shell.Interactive.access$1400(Shell.Interactive.this).marker)) {}
              try
              {
                Shell.Interactive.access$1502(Shell.Interactive.this, Integer.valueOf(paramAnonymousString.substring(Shell.Interactive.access$1400(Shell.Interactive.this).marker.length() + 1), 10).intValue());
                Shell.Interactive.access$1602(Shell.Interactive.this, Shell.Interactive.access$1400(Shell.Interactive.this).marker);
                Shell.Interactive.this.processMarker();
                return;
                Shell.Interactive.this.addBuffer(paramAnonymousString);
                Shell.Interactive.this.processLine(paramAnonymousString, Shell.Interactive.this.onSTDOUTLineListener);
                return;
              }
              catch (Exception paramAnonymousString)
              {
                for (;;) {}
              }
            }
          });
          this.STDERR = new StreamGobbler(this.shell.toUpperCase() + "*", this.process.getErrorStream(), new StreamGobbler.OnLineListener()
          {
            public void onLine(String paramAnonymousString)
            {
              if (paramAnonymousString.startsWith(Shell.Interactive.access$1400(Shell.Interactive.this).marker))
              {
                Shell.Interactive.access$2102(Shell.Interactive.this, Shell.Interactive.access$1400(Shell.Interactive.this).marker);
                Shell.Interactive.this.processMarker();
                return;
              }
              if (Shell.Interactive.this.wantSTDERR) {
                Shell.Interactive.this.addBuffer(paramAnonymousString);
              }
              Shell.Interactive.this.processLine(paramAnonymousString, Shell.Interactive.this.onSTDERRLineListener);
            }
          });
          this.STDOUT.start();
          this.STDERR.start();
          this.running = true;
          this.closed = false;
          runNextCommand();
          bool = true;
        }
        catch (IOException localIOException)
        {
          Object localObject2;
          int i;
          String[] arrayOfString;
          Map.Entry localEntry;
          boolean bool = false;
          continue;
        }
        finally {}
        return bool;
        localObject2 = new HashMap();
        ((Map)localObject2).putAll(System.getenv());
        ((Map)localObject2).putAll(this.environment);
        i = 0;
        arrayOfString = new String[((Map)localObject2).size()];
        localObject2 = ((Map)localObject2).entrySet().iterator();
        if (((Iterator)localObject2).hasNext())
        {
          localEntry = (Map.Entry)((Iterator)localObject2).next();
          arrayOfString[i] = ((String)localEntry.getKey() + "=" + (String)localEntry.getValue());
          i += 1;
        }
        else
        {
          this.process = Runtime.getRuntime().exec(this.shell, arrayOfString);
        }
      }
    }
    
    /* Error */
    private void processLine(final String paramString, final StreamGobbler.OnLineListener paramOnLineListener)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_2
      //   3: ifnull +32 -> 35
      //   6: aload_0
      //   7: getfield 145	eu/chainfire/libsuperuser/Shell$Interactive:handler	Landroid/os/Handler;
      //   10: ifnull +28 -> 38
      //   13: aload_0
      //   14: invokespecial 313	eu/chainfire/libsuperuser/Shell$Interactive:startCallback	()V
      //   17: aload_0
      //   18: getfield 145	eu/chainfire/libsuperuser/Shell$Interactive:handler	Landroid/os/Handler;
      //   21: new 11	eu/chainfire/libsuperuser/Shell$Interactive$2
      //   24: dup
      //   25: aload_0
      //   26: aload_2
      //   27: aload_1
      //   28: invokespecial 316	eu/chainfire/libsuperuser/Shell$Interactive$2:<init>	(Leu/chainfire/libsuperuser/Shell$Interactive;Leu/chainfire/libsuperuser/StreamGobbler$OnLineListener;Ljava/lang/String;)V
      //   31: invokevirtual 320	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   34: pop
      //   35: aload_0
      //   36: monitorexit
      //   37: return
      //   38: aload_2
      //   39: aload_1
      //   40: invokeinterface 325 2 0
      //   45: goto -10 -> 35
      //   48: astore_1
      //   49: aload_0
      //   50: monitorexit
      //   51: aload_1
      //   52: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	53	0	this	Interactive
      //   0	53	1	paramString	String
      //   0	53	2	paramOnLineListener	StreamGobbler.OnLineListener
      // Exception table:
      //   from	to	target	type
      //   6	35	48	finally
      //   38	45	48	finally
    }
    
    /* Error */
    private void processMarker()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 86	eu/chainfire/libsuperuser/Shell$Interactive:command	Leu/chainfire/libsuperuser/Shell$Command;
      //   6: invokestatic 331	eu/chainfire/libsuperuser/Shell$Command:access$1100	(Leu/chainfire/libsuperuser/Shell$Command;)Ljava/lang/String;
      //   9: aload_0
      //   10: getfield 82	eu/chainfire/libsuperuser/Shell$Interactive:lastMarkerSTDOUT	Ljava/lang/String;
      //   13: invokevirtual 334	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   16: ifeq +101 -> 117
      //   19: aload_0
      //   20: getfield 86	eu/chainfire/libsuperuser/Shell$Interactive:command	Leu/chainfire/libsuperuser/Shell$Command;
      //   23: invokestatic 331	eu/chainfire/libsuperuser/Shell$Command:access$1100	(Leu/chainfire/libsuperuser/Shell$Command;)Ljava/lang/String;
      //   26: aload_0
      //   27: getfield 84	eu/chainfire/libsuperuser/Shell$Interactive:lastMarkerSTDERR	Ljava/lang/String;
      //   30: invokevirtual 334	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   33: ifeq +84 -> 117
      //   36: aload_0
      //   37: getfield 86	eu/chainfire/libsuperuser/Shell$Interactive:command	Leu/chainfire/libsuperuser/Shell$Command;
      //   40: invokestatic 338	eu/chainfire/libsuperuser/Shell$Command:access$1000	(Leu/chainfire/libsuperuser/Shell$Command;)Leu/chainfire/libsuperuser/Shell$OnCommandResultListener;
      //   43: ifnull +55 -> 98
      //   46: aload_0
      //   47: getfield 88	eu/chainfire/libsuperuser/Shell$Interactive:buffer	Ljava/util/List;
      //   50: ifnull +48 -> 98
      //   53: aload_0
      //   54: getfield 145	eu/chainfire/libsuperuser/Shell$Interactive:handler	Landroid/os/Handler;
      //   57: ifnull +63 -> 120
      //   60: aload_0
      //   61: getfield 88	eu/chainfire/libsuperuser/Shell$Interactive:buffer	Ljava/util/List;
      //   64: astore_2
      //   65: aload_0
      //   66: getfield 80	eu/chainfire/libsuperuser/Shell$Interactive:lastExitCode	I
      //   69: istore_1
      //   70: aload_0
      //   71: getfield 86	eu/chainfire/libsuperuser/Shell$Interactive:command	Leu/chainfire/libsuperuser/Shell$Command;
      //   74: astore_3
      //   75: aload_0
      //   76: invokespecial 313	eu/chainfire/libsuperuser/Shell$Interactive:startCallback	()V
      //   79: aload_0
      //   80: getfield 145	eu/chainfire/libsuperuser/Shell$Interactive:handler	Landroid/os/Handler;
      //   83: new 9	eu/chainfire/libsuperuser/Shell$Interactive$1
      //   86: dup
      //   87: aload_0
      //   88: aload_3
      //   89: iload_1
      //   90: aload_2
      //   91: invokespecial 341	eu/chainfire/libsuperuser/Shell$Interactive$1:<init>	(Leu/chainfire/libsuperuser/Shell$Interactive;Leu/chainfire/libsuperuser/Shell$Command;ILjava/util/List;)V
      //   94: invokevirtual 320	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   97: pop
      //   98: aload_0
      //   99: aconst_null
      //   100: putfield 86	eu/chainfire/libsuperuser/Shell$Interactive:command	Leu/chainfire/libsuperuser/Shell$Command;
      //   103: aload_0
      //   104: aconst_null
      //   105: putfield 88	eu/chainfire/libsuperuser/Shell$Interactive:buffer	Ljava/util/List;
      //   108: aload_0
      //   109: iconst_1
      //   110: putfield 70	eu/chainfire/libsuperuser/Shell$Interactive:idle	Z
      //   113: aload_0
      //   114: invokespecial 265	eu/chainfire/libsuperuser/Shell$Interactive:runNextCommand	()V
      //   117: aload_0
      //   118: monitorexit
      //   119: return
      //   120: aload_0
      //   121: getfield 86	eu/chainfire/libsuperuser/Shell$Interactive:command	Leu/chainfire/libsuperuser/Shell$Command;
      //   124: invokestatic 338	eu/chainfire/libsuperuser/Shell$Command:access$1000	(Leu/chainfire/libsuperuser/Shell$Command;)Leu/chainfire/libsuperuser/Shell$OnCommandResultListener;
      //   127: aload_0
      //   128: getfield 86	eu/chainfire/libsuperuser/Shell$Interactive:command	Leu/chainfire/libsuperuser/Shell$Command;
      //   131: invokestatic 345	eu/chainfire/libsuperuser/Shell$Command:access$1200	(Leu/chainfire/libsuperuser/Shell$Command;)I
      //   134: aload_0
      //   135: getfield 80	eu/chainfire/libsuperuser/Shell$Interactive:lastExitCode	I
      //   138: aload_0
      //   139: getfield 88	eu/chainfire/libsuperuser/Shell$Interactive:buffer	Ljava/util/List;
      //   142: invokeinterface 351 4 0
      //   147: goto -49 -> 98
      //   150: astore_2
      //   151: aload_0
      //   152: monitorexit
      //   153: aload_2
      //   154: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	155	0	this	Interactive
      //   69	21	1	i	int
      //   64	27	2	localList	List
      //   150	4	2	localObject	Object
      //   74	15	3	localCommand	Shell.Command
      // Exception table:
      //   from	to	target	type
      //   2	98	150	finally
      //   98	117	150	finally
      //   120	147	150	finally
    }
    
    private void runNextCommand()
    {
      runNextCommand(true);
    }
    
    private void runNextCommand(boolean paramBoolean)
    {
      boolean bool = isRunning();
      if (!bool) {
        this.idle = true;
      }
      if ((bool) && (this.idle) && (this.commands.size() > 0))
      {
        ??? = (Shell.Command)this.commands.get(0);
        this.commands.remove(0);
        this.buffer = null;
        this.lastExitCode = 0;
        this.lastMarkerSTDOUT = null;
        this.lastMarkerSTDERR = null;
        if (((Shell.Command)???).commands.length <= 0) {
          break label299;
        }
      }
      for (;;)
      {
        try
        {
          if (((Shell.Command)???).onCommandResultListener != null) {
            this.buffer = Collections.synchronizedList(new ArrayList());
          }
          this.idle = false;
          this.command = ((Shell.Command)???);
          String[] arrayOfString = ((Shell.Command)???).commands;
          int j = arrayOfString.length;
          int i = 0;
          if (i < j)
          {
            String str = arrayOfString[i];
            this.STDIN.writeBytes(str + "\n");
            i += 1;
            continue;
          }
          this.STDIN.writeBytes("echo " + ((Shell.Command)???).marker + " $?\n");
          this.STDIN.writeBytes("echo " + ((Shell.Command)???).marker + " >&2\n");
          this.STDIN.flush();
        }
        catch (IOException localIOException)
        {
          label299:
          continue;
        }
        if ((this.idle) && (paramBoolean)) {}
        synchronized (this.idleSync)
        {
          this.idleSync.notifyAll();
          return;
          runNextCommand(false);
        }
      }
    }
    
    private void startCallback()
    {
      synchronized (this.callbackSync)
      {
        this.callbacks += 1;
        return;
      }
    }
    
    public void addCommand(String paramString)
    {
      addCommand(paramString, 0, null);
    }
    
    public void addCommand(String paramString, int paramInt, Shell.OnCommandResultListener paramOnCommandResultListener)
    {
      addCommand(new String[] { paramString }, paramInt, paramOnCommandResultListener);
    }
    
    public void addCommand(List<String> paramList)
    {
      addCommand(paramList, 0, null);
    }
    
    public void addCommand(List<String> paramList, int paramInt, Shell.OnCommandResultListener paramOnCommandResultListener)
    {
      addCommand((String[])paramList.toArray(new String[paramList.size()]), paramInt, paramOnCommandResultListener);
    }
    
    public void addCommand(String[] paramArrayOfString)
    {
      addCommand(paramArrayOfString, 0, null);
    }
    
    public void addCommand(String[] paramArrayOfString, int paramInt, Shell.OnCommandResultListener paramOnCommandResultListener)
    {
      try
      {
        if (this.running)
        {
          this.commands.add(new Shell.Command(paramArrayOfString, paramInt, paramOnCommandResultListener));
          runNextCommand();
        }
        return;
      }
      finally
      {
        paramArrayOfString = finally;
        throw paramArrayOfString;
      }
    }
    
    /* Error */
    public void close()
    {
      // Byte code:
      //   0: aload_0
      //   1: invokevirtual 421	eu/chainfire/libsuperuser/Shell$Interactive:isIdle	()Z
      //   4: istore_1
      //   5: aload_0
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 68	eu/chainfire/libsuperuser/Shell$Interactive:running	Z
      //   11: ifne +6 -> 17
      //   14: aload_0
      //   15: monitorexit
      //   16: return
      //   17: aload_0
      //   18: iconst_0
      //   19: putfield 68	eu/chainfire/libsuperuser/Shell$Interactive:running	Z
      //   22: aload_0
      //   23: iconst_1
      //   24: putfield 72	eu/chainfire/libsuperuser/Shell$Interactive:closed	Z
      //   27: aload_0
      //   28: monitorexit
      //   29: iload_1
      //   30: ifne +3 -> 33
      //   33: iload_1
      //   34: ifne +8 -> 42
      //   37: aload_0
      //   38: invokevirtual 424	eu/chainfire/libsuperuser/Shell$Interactive:waitForIdle	()Z
      //   41: pop
      //   42: aload_0
      //   43: getfield 62	eu/chainfire/libsuperuser/Shell$Interactive:STDIN	Ljava/io/DataOutputStream;
      //   46: ldc_w 426
      //   49: invokevirtual 383	java/io/DataOutputStream:writeBytes	(Ljava/lang/String;)V
      //   52: aload_0
      //   53: getfield 62	eu/chainfire/libsuperuser/Shell$Interactive:STDIN	Ljava/io/DataOutputStream;
      //   56: invokevirtual 392	java/io/DataOutputStream:flush	()V
      //   59: aload_0
      //   60: getfield 60	eu/chainfire/libsuperuser/Shell$Interactive:process	Ljava/lang/Process;
      //   63: invokevirtual 429	java/lang/Process:waitFor	()I
      //   66: pop
      //   67: aload_0
      //   68: getfield 62	eu/chainfire/libsuperuser/Shell$Interactive:STDIN	Ljava/io/DataOutputStream;
      //   71: invokevirtual 431	java/io/DataOutputStream:close	()V
      //   74: aload_0
      //   75: getfield 64	eu/chainfire/libsuperuser/Shell$Interactive:STDOUT	Leu/chainfire/libsuperuser/StreamGobbler;
      //   78: invokevirtual 434	eu/chainfire/libsuperuser/StreamGobbler:join	()V
      //   81: aload_0
      //   82: getfield 66	eu/chainfire/libsuperuser/Shell$Interactive:STDERR	Leu/chainfire/libsuperuser/StreamGobbler;
      //   85: invokevirtual 434	eu/chainfire/libsuperuser/StreamGobbler:join	()V
      //   88: aload_0
      //   89: getfield 60	eu/chainfire/libsuperuser/Shell$Interactive:process	Ljava/lang/Process;
      //   92: invokevirtual 437	java/lang/Process:destroy	()V
      //   95: return
      //   96: astore_2
      //   97: return
      //   98: astore_2
      //   99: aload_0
      //   100: monitorexit
      //   101: aload_2
      //   102: athrow
      //   103: astore_2
      //   104: return
      //   105: astore_2
      //   106: goto -32 -> 74
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	109	0	this	Interactive
      //   4	30	1	bool	boolean
      //   96	1	2	localIOException1	IOException
      //   98	4	2	localObject	Object
      //   103	1	2	localInterruptedException	InterruptedException
      //   105	1	2	localIOException2	IOException
      // Exception table:
      //   from	to	target	type
      //   42	67	96	java/io/IOException
      //   74	95	96	java/io/IOException
      //   7	16	98	finally
      //   17	29	98	finally
      //   99	101	98	finally
      //   42	67	103	java/lang/InterruptedException
      //   67	74	103	java/lang/InterruptedException
      //   74	95	103	java/lang/InterruptedException
      //   67	74	105	java/io/IOException
    }
    
    protected void finalize()
      throws Throwable
    {
      if (!this.closed) {}
      super.finalize();
    }
    
    public boolean hasHandler()
    {
      return this.handler != null;
    }
    
    /* Error */
    public boolean isIdle()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: invokevirtual 357	eu/chainfire/libsuperuser/Shell$Interactive:isRunning	()Z
      //   6: ifne +24 -> 30
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 70	eu/chainfire/libsuperuser/Shell$Interactive:idle	Z
      //   14: aload_0
      //   15: getfield 76	eu/chainfire/libsuperuser/Shell$Interactive:idleSync	Ljava/lang/Object;
      //   18: astore_2
      //   19: aload_2
      //   20: monitorenter
      //   21: aload_0
      //   22: getfield 76	eu/chainfire/libsuperuser/Shell$Interactive:idleSync	Ljava/lang/Object;
      //   25: invokevirtual 195	java/lang/Object:notifyAll	()V
      //   28: aload_2
      //   29: monitorexit
      //   30: aload_0
      //   31: getfield 70	eu/chainfire/libsuperuser/Shell$Interactive:idle	Z
      //   34: istore_1
      //   35: aload_0
      //   36: monitorexit
      //   37: iload_1
      //   38: ireturn
      //   39: astore_3
      //   40: aload_2
      //   41: monitorexit
      //   42: aload_3
      //   43: athrow
      //   44: astore_2
      //   45: aload_0
      //   46: monitorexit
      //   47: aload_2
      //   48: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	49	0	this	Interactive
      //   34	4	1	bool	boolean
      //   44	4	2	localObject2	Object
      //   39	4	3	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   21	30	39	finally
      //   40	42	39	finally
      //   2	21	44	finally
      //   30	35	44	finally
      //   42	44	44	finally
    }
    
    public boolean isRunning()
    {
      try
      {
        this.process.exitValue();
        return false;
      }
      catch (IllegalThreadStateException localIllegalThreadStateException) {}
      return true;
    }
    
    public boolean waitForIdle()
    {
      if (isRunning()) {
        synchronized (this.idleSync)
        {
          for (;;)
          {
            boolean bool = this.idle;
            if (!bool) {
              try
              {
                this.idleSync.wait();
              }
              catch (InterruptedException localInterruptedException1)
              {
                return false;
              }
            }
          }
          if ((this.handler == null) || (this.handler.getLooper() == null) || (this.handler.getLooper() == Looper.myLooper())) {
            break label119;
          }
          synchronized (this.callbackSync)
          {
            for (;;)
            {
              int i = this.callbacks;
              if (i > 0) {
                try
                {
                  this.callbackSync.wait();
                }
                catch (InterruptedException localInterruptedException2)
                {
                  return false;
                }
              }
            }
          }
        }
      }
      label119:
      return true;
    }
  }
  
  public static abstract interface OnCommandResultListener
  {
    public abstract void onCommandResult(int paramInt1, int paramInt2, List<String> paramList);
  }
  
  public static class SH
  {
    public static List<String> run(String paramString)
    {
      return Shell.run("sh", new String[] { paramString }, null, false);
    }
    
    public static List<String> run(List<String> paramList)
    {
      return Shell.run("sh", (String[])paramList.toArray(new String[paramList.size()]), null, false);
    }
    
    public static List<String> run(String[] paramArrayOfString)
    {
      return Shell.run("sh", paramArrayOfString, null, false);
    }
  }
  
  public static class SU
  {
    public static boolean available()
    {
      Object localObject = run(new String[] { "id", "echo -EOC-" });
      if (localObject == null) {}
      String str;
      do
      {
        while (!((Iterator)localObject).hasNext())
        {
          return false;
          localObject = ((List)localObject).iterator();
        }
        str = (String)((Iterator)localObject).next();
        if (str.contains("uid=")) {
          return str.contains("uid=0");
        }
      } while (!str.contains("-EOC-"));
      return true;
    }
    
    public static List<String> run(String paramString)
    {
      return Shell.run("su", new String[] { paramString }, null, false);
    }
    
    public static List<String> run(List<String> paramList)
    {
      return Shell.run("su", (String[])paramList.toArray(new String[paramList.size()]), null, false);
    }
    
    public static List<String> run(String[] paramArrayOfString)
    {
      return Shell.run("su", paramArrayOfString, null, false);
    }
    
    public static String version(boolean paramBoolean)
    {
      if (paramBoolean) {}
      for (Object localObject = "su -V";; localObject = "su -v")
      {
        localObject = Shell.run("sh", new String[] { localObject, "exit" }, null, false);
        if (localObject != null) {
          break;
        }
        return null;
      }
      localObject = ((List)localObject).iterator();
      for (;;)
      {
        String str;
        if (((Iterator)localObject).hasNext())
        {
          str = (String)((Iterator)localObject).next();
          if (!paramBoolean)
          {
            if (!str.contains(".")) {
              continue;
            }
            return str;
          }
        }
        try
        {
          int i = Integer.parseInt(str);
          if (i <= 0) {
            continue;
          }
          return str;
        }
        catch (NumberFormatException localNumberFormatException) {}
        return null;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\eu\chainfire\libsuperuser\Shell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */