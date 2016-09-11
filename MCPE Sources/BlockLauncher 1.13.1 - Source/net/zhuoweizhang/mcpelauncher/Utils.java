package net.zhuoweizhang.mcpelauncher;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import com.mojang.minecraftpe.MainActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collection<*>;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class Utils
{
  protected static Context mContext = null;
  
  public static void clearDirectory(File paramFile)
  {
    paramFile = paramFile.listFiles();
    if (paramFile == null) {}
    for (;;)
    {
      return;
      int j = paramFile.length;
      int i = 0;
      while (i < j)
      {
        File localFile = paramFile[i];
        if (localFile.isDirectory()) {
          clearDirectory(localFile);
        }
        localFile.delete();
        i += 1;
      }
    }
  }
  
  public static String getArchName(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "Unknown";
    case 0: 
      return "ARM";
    }
    return "Intel";
  }
  
  public static Field getDeclaredFieldRecursive(Class<?> paramClass, String paramString)
  {
    if (paramClass == null) {
      return null;
    }
    try
    {
      Field localField = paramClass.getDeclaredField(paramString);
      return localField;
    }
    catch (NoSuchFieldException localNoSuchFieldException) {}
    return getDeclaredFieldRecursive(paramClass.getSuperclass(), paramString);
  }
  
  public static int getElfArch(File paramFile)
    throws IOException
  {
    FileInputStream localFileInputStream = new FileInputStream(paramFile);
    byte[] arrayOfByte = new byte[2];
    localFileInputStream.skip(18L);
    localFileInputStream.read(arrayOfByte, 0, 2);
    int i = arrayOfByte[0] | arrayOfByte[1] << 8;
    localFileInputStream.close();
    if (i == 40) {
      return 0;
    }
    if (i == 3) {
      return 1;
    }
    System.err.println(paramFile + " has unknown architecture 0x" + Integer.toString(i, 16));
    return 0;
  }
  
  public static Set<String> getEnabledPatches()
  {
    String str = getPrefs(1).getString("enabledPatches", "");
    if (str.equals("")) {
      return new HashSet();
    }
    return new HashSet(Arrays.asList(str.split(";")));
  }
  
  public static Set<String> getEnabledScripts()
  {
    String str = getPrefs(1).getString("enabledScripts", "");
    if (str.equals("")) {
      return new HashSet();
    }
    return new HashSet(Arrays.asList(str.split(";")));
  }
  
  public static int getMaxPatches()
  {
    return mContext.getResources().getInteger(R.integer.max_num_patches);
  }
  
  public static int getMaxScripts()
  {
    return mContext.getResources().getInteger(R.integer.max_num_scripts);
  }
  
  public static SharedPreferences getPrefs(int paramInt)
  {
    
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return PreferenceManager.getDefaultSharedPreferences(mContext);
    case 1: 
      return mContext.getSharedPreferences("mcpelauncherprefs", 0);
    }
    return mContext.getSharedPreferences("safe_mode_counter", 0);
  }
  
  public static boolean hasExtrasPackage(Context paramContext)
  {
    return paramContext.getPackageName().equals("net.zhuoweizhang.mcpelauncher.pro");
  }
  
  public static boolean hasTooManyPatches()
  {
    int i = getMaxPatches();
    return (i >= 0) && (getEnabledPatches().size() >= i);
  }
  
  public static boolean hasTooManyScripts()
  {
    int i = getMaxScripts();
    return (i >= 0) && (getEnabledScripts().size() >= i);
  }
  
  public static boolean isPro()
  {
    return mContext.getPackageName().equals("net.zhuoweizhang.mcpelauncher.pro");
  }
  
  public static boolean isSafeMode()
  {
    boolean bool = false;
    if (((MainActivity.libLoaded) && (MainActivity.tempSafeMode)) || (getPrefs(0).getBoolean("zz_safe_mode", false))) {
      bool = true;
    }
    return bool;
  }
  
  public static String join(Collection<?> paramCollection, String paramString)
  {
    Object localObject1 = new StringBuilder();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Object localObject2 = paramCollection.next();
      ((StringBuilder)localObject1).append(paramString).append(localObject2.toString());
    }
    localObject1 = ((StringBuilder)localObject1).toString();
    paramCollection = (Collection<?>)localObject1;
    if (((String)localObject1).length() >= paramString.length()) {
      paramCollection = ((String)localObject1).substring(paramString.length());
    }
    return paramCollection;
  }
  
  public static String joinArray(Object[] paramArrayOfObject, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < paramArrayOfObject.length)
    {
      if (i != 0) {
        localStringBuilder.append(paramString);
      }
      if (paramArrayOfObject[i] == null) {}
      for (String str = "null";; str = paramArrayOfObject[i].toString())
      {
        localStringBuilder.append(str);
        i += 1;
        break;
      }
    }
    return localStringBuilder.toString();
  }
  
  /* Error */
  public static long parseMemInfo()
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 269	java/io/BufferedReader
    //   6: dup
    //   7: new 271	java/io/FileReader
    //   10: dup
    //   11: ldc_w 273
    //   14: invokespecial 275	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   17: invokespecial 278	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   20: astore_3
    //   21: aload_3
    //   22: invokevirtual 281	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   25: astore_2
    //   26: aload_2
    //   27: ifnull +79 -> 106
    //   30: aload_2
    //   31: ldc_w 283
    //   34: invokevirtual 287	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   37: ifeq -16 -> 21
    //   40: aload_2
    //   41: ldc_w 283
    //   44: invokevirtual 143	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   47: astore 4
    //   49: aload 4
    //   51: iconst_0
    //   52: aaload
    //   53: invokevirtual 290	java/lang/String:trim	()Ljava/lang/String;
    //   56: astore_2
    //   57: aload 4
    //   59: iconst_1
    //   60: aaload
    //   61: invokevirtual 290	java/lang/String:trim	()Ljava/lang/String;
    //   64: ldc_w 292
    //   67: invokevirtual 143	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   70: astore 4
    //   72: aload_2
    //   73: ldc_w 294
    //   76: invokevirtual 134	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   79: ifeq -58 -> 21
    //   82: aload 4
    //   84: iconst_0
    //   85: aaload
    //   86: invokestatic 300	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   89: lstore_0
    //   90: lload_0
    //   91: ldc2_w 301
    //   94: lmul
    //   95: lstore_0
    //   96: aload_3
    //   97: ifnull +7 -> 104
    //   100: aload_3
    //   101: invokevirtual 303	java/io/BufferedReader:close	()V
    //   104: lload_0
    //   105: lreturn
    //   106: aload_3
    //   107: ifnull +7 -> 114
    //   110: aload_3
    //   111: invokevirtual 303	java/io/BufferedReader:close	()V
    //   114: ldc2_w 304
    //   117: lreturn
    //   118: astore_2
    //   119: aload 4
    //   121: astore_3
    //   122: aload_3
    //   123: ifnull +7 -> 130
    //   126: aload_3
    //   127: invokevirtual 303	java/io/BufferedReader:close	()V
    //   130: aload_2
    //   131: athrow
    //   132: astore_2
    //   133: lload_0
    //   134: lreturn
    //   135: astore_2
    //   136: goto -22 -> 114
    //   139: astore_3
    //   140: goto -10 -> 130
    //   143: astore_2
    //   144: goto -22 -> 122
    // Local variable table:
    //   start	length	slot	name	signature
    //   89	45	0	l	long
    //   25	48	2	str	String
    //   118	13	2	localObject1	Object
    //   132	1	2	localIOException1	IOException
    //   135	1	2	localIOException2	IOException
    //   143	1	2	localObject2	Object
    //   20	107	3	localObject3	Object
    //   139	1	3	localIOException3	IOException
    //   1	119	4	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   3	21	118	finally
    //   100	104	132	java/io/IOException
    //   110	114	135	java/io/IOException
    //   126	130	139	java/io/IOException
    //   21	26	143	finally
    //   30	90	143	finally
  }
  
  protected static void requireInit()
  {
    if (mContext == null) {
      throw new RuntimeException("Tried to work with Utils class without context");
    }
  }
  
  public static void setContext(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public static void setLanguageOverride()
  {
    requireInit();
    Object localObject = getPrefs(0).getString("zz_language_override", "");
    if (((String)localObject).length() == 0) {
      return;
    }
    localObject = ((String)localObject).split("_");
    String str = localObject[0];
    if (localObject.length > 1) {}
    for (localObject = localObject[1];; localObject = "")
    {
      Resources localResources = mContext.getResources();
      Configuration localConfiguration = new Configuration(localResources.getConfiguration());
      DisplayMetrics localDisplayMetrics = localResources.getDisplayMetrics();
      localConfiguration.locale = new Locale(str, (String)localObject);
      localResources.updateConfiguration(localConfiguration, localDisplayMetrics);
      return;
    }
  }
  
  public static void setupTheme(Context paramContext, boolean paramBoolean)
  {
    if (getPrefs(0).getBoolean("zz_theme_dark", false)) {
      if (!paramBoolean) {
        break label30;
      }
    }
    label30:
    for (int i = R.style.FullscreenDarkTheme;; i = R.style.BlockLauncherDarkTheme)
    {
      paramContext.setTheme(i);
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\Utils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */