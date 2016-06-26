package net.zhuoweizhang.mcpelauncher;

import android.content.BroadcastReceiver;

public class AddonInstallReceiver
  extends BroadcastReceiver
{
  /* Error */
  public void onReceive(android.content.Context paramContext, android.content.Intent paramIntent)
  {
    // Byte code:
    //   0: getstatic 21	java/lang/System:out	Ljava/io/PrintStream;
    //   3: new 23	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 24	java/lang/StringBuilder:<init>	()V
    //   10: ldc 26
    //   12: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: aload_2
    //   16: invokevirtual 36	android/content/Intent:toString	()Ljava/lang/String;
    //   19: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   25: invokevirtual 43	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   28: getstatic 21	java/lang/System:out	Ljava/io/PrintStream;
    //   31: new 23	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 24	java/lang/StringBuilder:<init>	()V
    //   38: ldc 45
    //   40: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: getstatic 51	com/mojang/minecraftpe/MainActivity:libLoaded	Z
    //   46: invokevirtual 54	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   49: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: invokevirtual 43	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   55: aload_2
    //   56: invokevirtual 58	android/content/Intent:getData	()Landroid/net/Uri;
    //   59: invokevirtual 61	android/net/Uri:toString	()Ljava/lang/String;
    //   62: bipush 8
    //   64: invokevirtual 67	java/lang/String:substring	(I)Ljava/lang/String;
    //   67: astore 4
    //   69: aload 4
    //   71: ldc 69
    //   73: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   76: istore_3
    //   77: getstatic 51	com/mojang/minecraftpe/MainActivity:libLoaded	Z
    //   80: ifne +8 -> 88
    //   83: iload_3
    //   84: ifne +4 -> 88
    //   87: return
    //   88: getstatic 78	net/zhuoweizhang/mcpelauncher/ui/MainMenuOptionsActivity:isManagingAddons	Z
    //   91: ifne -4 -> 87
    //   94: aload_2
    //   95: invokevirtual 81	android/content/Intent:getAction	()Ljava/lang/String;
    //   98: ldc 83
    //   100: invokevirtual 73	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   103: ifeq +13 -> 116
    //   106: aload_2
    //   107: ldc 85
    //   109: iconst_0
    //   110: invokevirtual 89	android/content/Intent:getBooleanExtra	(Ljava/lang/String;Z)Z
    //   113: ifne -26 -> 87
    //   116: aload_1
    //   117: invokevirtual 95	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   120: astore_1
    //   121: getstatic 21	java/lang/System:out	Ljava/io/PrintStream;
    //   124: new 23	java/lang/StringBuilder
    //   127: dup
    //   128: invokespecial 24	java/lang/StringBuilder:<init>	()V
    //   131: ldc 26
    //   133: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: aload 4
    //   138: invokevirtual 30	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: invokevirtual 37	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   144: invokevirtual 43	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   147: iload_3
    //   148: ifeq +88 -> 236
    //   151: getstatic 21	java/lang/System:out	Ljava/io/PrintStream;
    //   154: ldc 97
    //   156: invokevirtual 43	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   159: iconst_1
    //   160: invokestatic 103	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   163: invokeinterface 109 1 0
    //   168: ldc 111
    //   170: iconst_1
    //   171: invokeinterface 117 3 0
    //   176: invokeinterface 121 1 0
    //   181: pop
    //   182: getstatic 21	java/lang/System:out	Ljava/io/PrintStream;
    //   185: ldc 123
    //   187: invokevirtual 43	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   190: getstatic 127	com/mojang/minecraftpe/MainActivity:currentMainActivity	Ljava/lang/ref/WeakReference;
    //   193: ifnull +24 -> 217
    //   196: getstatic 127	com/mojang/minecraftpe/MainActivity:currentMainActivity	Ljava/lang/ref/WeakReference;
    //   199: invokevirtual 133	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   202: ifnull +15 -> 217
    //   205: getstatic 127	com/mojang/minecraftpe/MainActivity:currentMainActivity	Ljava/lang/ref/WeakReference;
    //   208: invokevirtual 133	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   211: checkcast 47	com/mojang/minecraftpe/MainActivity
    //   214: invokevirtual 136	com/mojang/minecraftpe/MainActivity:finish	()V
    //   217: new 138	java/lang/Thread
    //   220: dup
    //   221: new 6	net/zhuoweizhang/mcpelauncher/AddonInstallReceiver$1
    //   224: dup
    //   225: aload_0
    //   226: invokespecial 141	net/zhuoweizhang/mcpelauncher/AddonInstallReceiver$1:<init>	(Lnet/zhuoweizhang/mcpelauncher/AddonInstallReceiver;)V
    //   229: invokespecial 144	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   232: invokevirtual 147	java/lang/Thread:start	()V
    //   235: return
    //   236: getstatic 151	com/mojang/minecraftpe/MainActivity:loadedAddons	Ljava/util/Set;
    //   239: aload 4
    //   241: invokeinterface 156 2 0
    //   246: ifne -64 -> 182
    //   249: aload_1
    //   250: aload 4
    //   252: sipush 128
    //   255: invokevirtual 162	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   258: astore_1
    //   259: aload_1
    //   260: getfield 168	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   263: ifnull -176 -> 87
    //   266: aload_1
    //   267: getfield 168	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   270: ldc -86
    //   272: invokevirtual 176	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   275: astore_1
    //   276: aload_1
    //   277: ifnonnull -95 -> 182
    //   280: return
    //   281: astore_1
    //   282: aload_1
    //   283: invokevirtual 179	java/lang/Exception:printStackTrace	()V
    //   286: return
    //   287: astore_1
    //   288: aload_1
    //   289: invokevirtual 179	java/lang/Exception:printStackTrace	()V
    //   292: goto -75 -> 217
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	295	0	this	AddonInstallReceiver
    //   0	295	1	paramContext	android.content.Context
    //   0	295	2	paramIntent	android.content.Intent
    //   76	72	3	bool	boolean
    //   67	184	4	str	String
    // Exception table:
    //   from	to	target	type
    //   116	147	281	java/lang/Exception
    //   151	182	281	java/lang/Exception
    //   236	276	281	java/lang/Exception
    //   205	217	287	java/lang/Exception
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\AddonInstallReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */