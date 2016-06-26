package com.amazon.android.c;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import com.amazon.android.framework.util.KiwiLogger;
import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class b
  implements Serializable
{
  private static final KiwiLogger a = new KiwiLogger("CrashReport");
  private static final long serialVersionUID = 1L;
  private final HashMap b = new LinkedHashMap();
  
  public b(Application paramApplication, Throwable paramThrowable)
  {
    try
    {
      this.b.put("crashTime", new Date().toString());
      Object localObject = a(paramApplication);
      if (localObject != null)
      {
        this.b.put("packageVersionName", ((PackageInfo)localObject).versionName);
        this.b.put("packageName", ((PackageInfo)localObject).packageName);
        this.b.put("packageFilePath", paramApplication.getFilesDir().getAbsolutePath());
      }
      this.b.put("deviceModel", Build.MODEL);
      this.b.put("androidVersion", Build.VERSION.RELEASE);
      this.b.put("deviceBoard", Build.BOARD);
      this.b.put("deviceBrand", Build.BRAND);
      this.b.put("deviceDisplay", Build.DISPLAY);
      this.b.put("deviceFingerPrint", Build.FINGERPRINT);
      this.b.put("deviceHost", Build.HOST);
      this.b.put("deviceId", Build.ID);
      this.b.put("deviceManufacturer", Build.MANUFACTURER);
      this.b.put("deviceProduct", Build.PRODUCT);
      this.b.put("deviceTags", Build.TAGS);
      this.b.put("deviceTime", Long.toString(Build.TIME));
      this.b.put("deviceType", Build.TYPE);
      this.b.put("deviceUser", Build.USER);
      localObject = this.b;
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l = localStatFs.getBlockSize();
      ((HashMap)localObject).put("totalInternalMemorySize", Long.toString(localStatFs.getBlockCount() * l));
      localObject = this.b;
      localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      l = localStatFs.getBlockSize();
      ((HashMap)localObject).put("availableInternalMemorySize", Long.toString(localStatFs.getAvailableBlocks() * l));
      paramApplication = (ActivityManager)paramApplication.getSystemService("activity");
      if (paramApplication != null)
      {
        localObject = new ActivityManager.MemoryInfo();
        paramApplication.getMemoryInfo((ActivityManager.MemoryInfo)localObject);
        this.b.put("memLowFlag", Boolean.toString(((ActivityManager.MemoryInfo)localObject).lowMemory));
        this.b.put("memLowThreshold", Long.toString(((ActivityManager.MemoryInfo)localObject).threshold));
      }
      this.b.put("nativeHeapSize", Long.toString(Debug.getNativeHeapSize()));
      this.b.put("nativeHeapFreeSize", Long.toString(Debug.getNativeHeapAllocatedSize()));
      this.b.put("threadAllocCount", Long.toString(Debug.getThreadAllocCount()));
      this.b.put("threadAllocSize", Long.toString(Debug.getThreadAllocSize()));
      a(paramThrowable);
      b();
      c();
      return;
    }
    catch (Throwable paramApplication)
    {
      while (!KiwiLogger.ERROR_ON) {}
      a.error("Error collection crash report details", paramApplication);
    }
  }
  
  private static PackageInfo a(Application paramApplication)
  {
    PackageManager localPackageManager = paramApplication.getPackageManager();
    try
    {
      paramApplication = localPackageManager.getPackageInfo(paramApplication.getPackageName(), 0);
      return paramApplication;
    }
    catch (PackageManager.NameNotFoundException paramApplication)
    {
      if (KiwiLogger.ERROR_ON) {
        a.error("Unable to fetch package info", paramApplication);
      }
    }
    return null;
  }
  
  private void a(Throwable paramThrowable)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    paramThrowable.printStackTrace(localPrintWriter);
    localStringBuilder.append(localStringWriter.toString());
    localStringBuilder.append("\n");
    paramThrowable = paramThrowable.getCause();
    while (paramThrowable != null)
    {
      paramThrowable.printStackTrace(localPrintWriter);
      localStringBuilder.append(localStringWriter.toString());
      paramThrowable = paramThrowable.getCause();
      localStringBuilder.append("\n\n");
    }
    localPrintWriter.close();
    this.b.put("stackTrace", localStringBuilder.toString());
  }
  
  private void b()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = Thread.getAllStackTraces().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      Thread localThread = (Thread)((Map.Entry)localObject).getKey();
      localObject = (StackTraceElement[])((Map.Entry)localObject).getValue();
      localStringBuilder.append("Thread : " + localThread.getId());
      if (!com.amazon.android.framework.util.b.a(localThread.getName())) {
        localStringBuilder.append("/" + localThread.getName());
      }
      localStringBuilder.append("\n");
      localStringBuilder.append("isAlive : " + localThread.isAlive() + "\n");
      localStringBuilder.append("isInterrupted : " + localThread.isInterrupted() + "\n");
      localStringBuilder.append("isDaemon : " + localThread.isDaemon() + "\n");
      int i = 0;
      while (i < localObject.length)
      {
        localStringBuilder.append("\tat " + localObject[i] + "\n");
        i += 1;
      }
      localStringBuilder.append("\n\n");
    }
    this.b.put("threadDump", localStringBuilder.toString());
  }
  
  private void c()
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)this.b.get("packageName")).append((String)this.b.get("packageVersionName")).append((String)this.b.get("androidVersion"));
      Object localObject = (String)this.b.get("stackTrace");
      if (localObject != null)
      {
        localObject = Pattern.compile("([a-zA-Z0-9_.]+(Exception|Error))|(at\\s.*\\(.*\\))").matcher((CharSequence)localObject);
        while (((Matcher)localObject).find()) {
          localStringBuilder.append(((Matcher)localObject).group());
        }
      }
      str = new BigInteger(MessageDigest.getInstance("SHA1").digest(localException.toString().getBytes("UTF-8"))).abs().toString(16);
    }
    catch (Exception localException)
    {
      if (KiwiLogger.ERROR_ON) {
        a.error("Error capturing crash id", localException);
      }
      return;
    }
    String str;
    this.b.put("crashId", str);
  }
  
  public final Map a()
  {
    return this.b;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\c\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */