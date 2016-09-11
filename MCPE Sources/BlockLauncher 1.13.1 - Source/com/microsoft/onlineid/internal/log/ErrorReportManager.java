package com.microsoft.onlineid.internal.log;

import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import com.microsoft.onlineid.internal.configuration.Settings;
import com.microsoft.onlineid.sts.AuthenticatorAccountManager;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.nio.CharBuffer;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class ErrorReportManager
{
  private static final String ConfirmationTitle = "Report a problem?";
  private static final String CrashReportEmailTitleFormat = "MSA Android Application Crash Report - %s";
  private static final String CrashReportExtension = ".stacktrace";
  private static WeakReference<Context> CurrentActivityContext;
  private static Context CurrentAppContext;
  private static final String DontAskAgainMessage = "No, don't ask again";
  private static final DateFormat EmailTitleDateFormat = DateFormat.getDateTimeInstance(2, 2, Locale.getDefault());
  private static final String IgnoreCrashReportingStorageKeyName = "isIgnoreCrashReporting";
  private static final int LogCatNumberLines = 5000;
  private static final String ScreenshotFileName = "com.microsoft.msa.authenticator.screenshot.jpg";
  private static final String SendCrashReportConfirmation = "A problem occurred last time you ran this application. Would you like to report it?";
  private static final String SendEmailTo = "WS-MSACLIENT-AFB@microsoft.com";
  private File _contextFilePath;
  private boolean _sendLogs = true;
  private boolean _sendScreenshot = false;
  
  public ErrorReportManager()
  {
    CurrentAppContext = null;
  }
  
  public ErrorReportManager(Context paramContext)
  {
    if (paramContext != null) {
      init(paramContext);
    }
  }
  
  private void appendValue(PrintWriter paramPrintWriter, String paramString1, String paramString2)
  {
    appendValue(paramPrintWriter, paramString1, paramString2, true);
  }
  
  private void appendValue(PrintWriter paramPrintWriter, String paramString1, String paramString2, boolean paramBoolean)
  {
    String str = "%s : %s\n";
    if (paramBoolean) {
      str = "      " + "%s : %s\n";
    }
    paramPrintWriter.append(String.format(Locale.US, str, new Object[] { paramString1, paramString2 }));
  }
  
  protected static void collectLogCatLogs(PrintWriter paramPrintWriter, boolean paramBoolean)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramBoolean) {}
    try
    {
      int i = android.os.Process.myPid();
      localObject1 = localObject2;
      if (i > 0) {
        localObject1 = Integer.toString(i) + "):";
      }
      localObject2 = Integer.toString(5000);
      String str = Logger.getLogTag() + ":*";
      localObject2 = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[] { "logcat", "-t", localObject2, "-v", "time", str, "*:S" }).getInputStream()));
      for (;;)
      {
        str = ((BufferedReader)localObject2).readLine();
        if (str == null) {
          return;
        }
        if ((localObject1 == null) || (str.contains((CharSequence)localObject1)))
        {
          paramPrintWriter.append(str);
          paramPrintWriter.append("\n");
        }
      }
      return;
    }
    catch (Exception paramPrintWriter)
    {
      Logger.error("Exception in collectLogCat", paramPrintWriter);
    }
  }
  
  private static String getOrientation()
  {
    if (CurrentAppContext.getResources().getConfiguration().orientation == 1) {
      return "Portrait";
    }
    return "Landscape";
  }
  
  private static String getScreenSize()
  {
    switch (CurrentAppContext.getResources().getConfiguration().screenLayout & 0xF)
    {
    default: 
      return "Undefined";
    case 1: 
      return "Small";
    case 2: 
      return "Normal";
    case 3: 
      return "Large";
    }
    return "Xlarge";
  }
  
  private static int getStringResourceIdAtRuntime(String paramString)
  {
    return CurrentAppContext.getResources().getIdentifier(paramString, "string", CurrentAppContext.getPackageName());
  }
  
  protected void askUserPermissionToEmailCrashReport()
  {
    DialogInterface.OnClickListener local3 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        paramAnonymousDialogInterface = ErrorReportManager.this;
        if (paramAnonymousInt == -1) {}
        for (boolean bool = true;; bool = false)
        {
          paramAnonymousDialogInterface.sendMailAndDeleteFiles(bool);
          if (paramAnonymousInt == -3) {
            Settings.getInstance(ErrorReportManager.CurrentAppContext).setSetting("isIgnoreCrashReporting", "true");
          }
          return;
        }
      }
    };
    AlertDialog.Builder localBuilder = new AlertDialog.Builder((Context)CurrentActivityContext.get());
    localBuilder.setTitle("Report a problem?");
    localBuilder.setMessage("A problem occurred last time you ran this application. Would you like to report it?");
    localBuilder.setPositiveButton(17039379, local3);
    localBuilder.setNegativeButton(17039369, local3);
    localBuilder.setNeutralButton("No, don't ask again", local3);
    localBuilder.show();
  }
  
  public void checkAndSendCrashReportWithUserPermission(Context paramContext)
  {
    try
    {
      if (isIgnoreCrashReportingFlagSet()) {
        return;
      }
      CurrentActivityContext = new WeakReference(paramContext);
      paramContext = getCrashErrorFileList();
      if ((paramContext != null) && (paramContext.length > 0))
      {
        askUserPermissionToEmailCrashReport();
        return;
      }
    }
    catch (Exception paramContext)
    {
      Logger.error("!Error checkAndSendCrashReportWithUserPermission:", paramContext);
    }
  }
  
  protected void constructReport(Throwable paramThrowable, boolean paramBoolean, String paramString, PrintWriter paramPrintWriter)
  {
    int i = 0;
    if (paramString != null) {}
    do
    {
      try
      {
        if (!paramString.isEmpty())
        {
          paramPrintWriter.append(paramString);
          paramPrintWriter.append("\n\n");
        }
        paramString = new AuthenticatorAccountManager(CurrentAppContext);
        if (paramString.hasAccounts())
        {
          paramString = paramString.getAccounts().iterator();
          while (paramString.hasNext())
          {
            AuthenticatorUserAccount localAuthenticatorUserAccount = (AuthenticatorUserAccount)paramString.next();
            appendValue(paramPrintWriter, "PUID", localAuthenticatorUserAccount.getPuid(), false);
            appendValue(paramPrintWriter, "Username", localAuthenticatorUserAccount.getUsername(), false);
            appendValue(paramPrintWriter, "GcmRegistrationID", localAuthenticatorUserAccount.getGcmRegistrationID(), false);
            paramPrintWriter.append("\n");
          }
        }
        paramPrintWriter.append(new Date().toString());
      }
      catch (Exception paramThrowable)
      {
        Logger.error("Exception in constructReport:", paramThrowable);
        return;
      }
      paramPrintWriter.append("\n\n");
      getDeviceInfo(paramPrintWriter);
      if (paramThrowable != null)
      {
        paramPrintWriter.append("Stack : \n");
        paramPrintWriter.append("-------------------- \n");
        paramThrowable.printStackTrace(paramPrintWriter);
        paramThrowable = paramThrowable.getCause();
        while ((paramThrowable != null) && (i < 5))
        {
          paramPrintWriter.append("Cause :");
          paramPrintWriter.append(String.valueOf(i));
          paramPrintWriter.append("-------------------- \n");
          paramThrowable.printStackTrace(paramPrintWriter);
          paramThrowable = paramThrowable.getCause();
          i += 1;
        }
      }
    } while (!this._sendLogs);
    paramPrintWriter.append("-------------------- \n");
    paramPrintWriter.append("\nLogcat:\n\n");
    collectLogCatLogs(paramPrintWriter, true);
    paramPrintWriter.append("\n");
    paramPrintWriter.append("-------------------- \n");
  }
  
  protected void deleteFileNoThrow(File paramFile)
  {
    if (paramFile != null) {}
    try
    {
      paramFile.delete();
      return;
    }
    catch (Exception paramFile)
    {
      Logger.error("deleteFileNoThrow failed", paramFile);
    }
  }
  
  protected void deleteScreenshot()
  {
    try
    {
      File localFile = getScreenshotFile();
      if (localFile != null) {
        deleteFileNoThrow(localFile);
      }
      return;
    }
    catch (Exception localException)
    {
      Logger.warning("Exception in deleteScreenshot", localException);
    }
  }
  
  /* Error */
  protected void emailLogs(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore 5
    //   8: new 400	java/io/CharArrayWriter
    //   11: dup
    //   12: invokespecial 401	java/io/CharArrayWriter:<init>	()V
    //   15: astore_2
    //   16: new 114	java/io/PrintWriter
    //   19: dup
    //   20: aload_2
    //   21: invokespecial 404	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   24: astore_3
    //   25: aload_0
    //   26: aconst_null
    //   27: iconst_0
    //   28: aload_1
    //   29: aload_3
    //   30: invokevirtual 406	com/microsoft/onlineid/internal/log/ErrorReportManager:constructReport	(Ljava/lang/Throwable;ZLjava/lang/String;Ljava/io/PrintWriter;)V
    //   33: aload_3
    //   34: invokevirtual 409	java/io/PrintWriter:close	()V
    //   37: aload_2
    //   38: invokevirtual 410	java/lang/Object:toString	()Ljava/lang/String;
    //   41: astore 4
    //   43: aload_3
    //   44: invokevirtual 409	java/io/PrintWriter:close	()V
    //   47: aload_2
    //   48: invokevirtual 413	java/io/Writer:close	()V
    //   51: aload 4
    //   53: astore_2
    //   54: getstatic 76	com/microsoft/onlineid/internal/log/ErrorReportManager:CurrentAppContext	Landroid/content/Context;
    //   57: invokevirtual 199	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   60: ldc_w 415
    //   63: invokestatic 417	com/microsoft/onlineid/internal/log/ErrorReportManager:getStringResourceIdAtRuntime	(Ljava/lang/String;)I
    //   66: invokevirtual 420	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   69: astore 5
    //   71: getstatic 66	com/microsoft/onlineid/internal/log/ErrorReportManager:EmailTitleDateFormat	Ljava/text/DateFormat;
    //   74: new 347	java/util/Date
    //   77: dup
    //   78: invokespecial 348	java/util/Date:<init>	()V
    //   81: invokevirtual 423	java/text/DateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   84: astore 4
    //   86: aload 4
    //   88: astore_3
    //   89: aload_1
    //   90: ifnull +52 -> 142
    //   93: aload 4
    //   95: astore_3
    //   96: aload_1
    //   97: invokevirtual 300	java/lang/String:isEmpty	()Z
    //   100: ifne +42 -> 142
    //   103: new 91	java/lang/StringBuilder
    //   106: dup
    //   107: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   110: aload 4
    //   112: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: ldc_w 425
    //   118: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: aload_1
    //   122: iconst_0
    //   123: aload_1
    //   124: invokevirtual 428	java/lang/String:length	()I
    //   127: bipush 50
    //   129: invokestatic 434	java/lang/Math:min	(II)I
    //   132: invokevirtual 438	java/lang/String:substring	(II)Ljava/lang/String;
    //   135: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   141: astore_3
    //   142: aload_0
    //   143: getstatic 247	com/microsoft/onlineid/internal/log/ErrorReportManager:CurrentActivityContext	Ljava/lang/ref/WeakReference;
    //   146: aload_2
    //   147: ldc 45
    //   149: getstatic 106	java/util/Locale:US	Ljava/util/Locale;
    //   152: ldc_w 440
    //   155: iconst_2
    //   156: anewarray 4	java/lang/Object
    //   159: dup
    //   160: iconst_0
    //   161: aload 5
    //   163: aastore
    //   164: dup
    //   165: iconst_1
    //   166: aload_3
    //   167: aastore
    //   168: invokestatic 112	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   171: invokevirtual 444	com/microsoft/onlineid/internal/log/ErrorReportManager:sendEmail	(Ljava/lang/ref/WeakReference;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   174: return
    //   175: astore 4
    //   177: aconst_null
    //   178: astore_2
    //   179: aload 5
    //   181: astore_3
    //   182: ldc_w 446
    //   185: aload 4
    //   187: invokestatic 396	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   190: aload_3
    //   191: invokevirtual 409	java/io/PrintWriter:close	()V
    //   194: aload_2
    //   195: invokevirtual 413	java/io/Writer:close	()V
    //   198: ldc_w 448
    //   201: astore_2
    //   202: goto -148 -> 54
    //   205: astore_1
    //   206: ldc_w 446
    //   209: aload_1
    //   210: invokestatic 396	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   213: return
    //   214: astore_1
    //   215: aconst_null
    //   216: astore_2
    //   217: aload_3
    //   218: invokevirtual 409	java/io/PrintWriter:close	()V
    //   221: aload_2
    //   222: invokevirtual 413	java/io/Writer:close	()V
    //   225: aload_1
    //   226: athrow
    //   227: astore_1
    //   228: goto -22 -> 206
    //   231: astore_1
    //   232: goto -15 -> 217
    //   235: astore 4
    //   237: aload 5
    //   239: astore_3
    //   240: goto -58 -> 182
    //   243: astore 4
    //   245: goto -63 -> 182
    //   248: astore_1
    //   249: aload 4
    //   251: astore_3
    //   252: goto -35 -> 217
    //   255: astore_1
    //   256: goto -39 -> 217
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	259	0	this	ErrorReportManager
    //   0	259	1	paramString	String
    //   15	207	2	localObject1	Object
    //   1	251	3	localObject2	Object
    //   3	108	4	str1	String
    //   175	11	4	localException1	Exception
    //   235	1	4	localException2	Exception
    //   243	7	4	localException3	Exception
    //   6	232	5	str2	String
    // Exception table:
    //   from	to	target	type
    //   8	16	175	java/lang/Exception
    //   43	51	205	java/lang/Exception
    //   8	16	214	finally
    //   54	86	227	java/lang/Exception
    //   96	142	227	java/lang/Exception
    //   142	174	227	java/lang/Exception
    //   190	198	227	java/lang/Exception
    //   217	227	227	java/lang/Exception
    //   25	43	231	finally
    //   16	25	235	java/lang/Exception
    //   25	43	243	java/lang/Exception
    //   16	25	248	finally
    //   182	190	255	finally
  }
  
  public void generateAndSaveCrashReport(Throwable paramThrowable)
  {
    try
    {
      if (isIgnoreCrashReportingFlagSet()) {
        return;
      }
      if (this._sendScreenshot) {
        saveScreenshot(CurrentActivityContext);
      }
      Object localObject = "stack-" + System.currentTimeMillis() + ".stacktrace";
      localObject = new PrintWriter(CurrentAppContext.openFileOutput((String)localObject, 0));
      constructReport(paramThrowable, true, null, (PrintWriter)localObject);
      ((PrintWriter)localObject).close();
      return;
    }
    catch (Exception paramThrowable)
    {
      Logger.warning("Error in generateAndSaveCrashReport: ", paramThrowable);
    }
  }
  
  public void generateAndSendReportWithUserPermission(Context paramContext)
  {
    generateAndSendReportWithUserPermission(paramContext, null);
  }
  
  public void generateAndSendReportWithUserPermission(Context paramContext, String paramString)
  {
    try
    {
      CurrentActivityContext = new WeakReference(paramContext);
      if (this._sendScreenshot) {
        saveScreenshot(CurrentActivityContext);
      }
      emailLogs(paramString);
      return;
    }
    catch (Exception paramContext)
    {
      Logger.error("!Error generateAndSendReportWithUserPermission:", paramContext);
    }
  }
  
  protected File[] getCrashErrorFileList()
  {
    File[] arrayOfFile = new File[0];
    Object localObject = arrayOfFile;
    try
    {
      if (this._contextFilePath != null)
      {
        localObject = new FilenameFilter()
        {
          public boolean accept(File paramAnonymousFile, String paramAnonymousString)
          {
            return paramAnonymousString.endsWith(".stacktrace");
          }
        };
        localObject = this._contextFilePath.listFiles((FilenameFilter)localObject);
      }
      return (File[])localObject;
    }
    catch (Exception localException)
    {
      Logger.warning("Exception in getCrashErrorFileList", localException);
    }
    return arrayOfFile;
  }
  
  /* Error */
  protected void getDeviceInfo(PrintWriter paramPrintWriter)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: ldc_w 493
    //   5: getstatic 76	com/microsoft/onlineid/internal/log/ErrorReportManager:CurrentAppContext	Landroid/content/Context;
    //   8: invokevirtual 235	android/content/Context:getPackageName	()Ljava/lang/String;
    //   11: iconst_0
    //   12: invokespecial 87	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;Z)V
    //   15: aload_0
    //   16: aload_1
    //   17: ldc_w 495
    //   20: aload_0
    //   21: getfield 484	com/microsoft/onlineid/internal/log/ErrorReportManager:_contextFilePath	Ljava/io/File;
    //   24: invokevirtual 498	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   27: iconst_0
    //   28: invokespecial 87	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;Z)V
    //   31: aload_0
    //   32: aload_1
    //   33: ldc_w 500
    //   36: getstatic 76	com/microsoft/onlineid/internal/log/ErrorReportManager:CurrentAppContext	Landroid/content/Context;
    //   39: invokevirtual 504	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   42: getstatic 76	com/microsoft/onlineid/internal/log/ErrorReportManager:CurrentAppContext	Landroid/content/Context;
    //   45: invokevirtual 235	android/content/Context:getPackageName	()Ljava/lang/String;
    //   48: iconst_0
    //   49: invokevirtual 510	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   52: getfield 515	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   55: iconst_0
    //   56: invokespecial 87	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;Z)V
    //   59: aload_1
    //   60: ldc_w 517
    //   63: invokevirtual 117	java/io/PrintWriter:append	(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
    //   66: pop
    //   67: aload_0
    //   68: aload_1
    //   69: ldc_w 519
    //   72: getstatic 524	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   75: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   78: aload_0
    //   79: aload_1
    //   80: ldc_w 528
    //   83: getstatic 531	android/os/Build$VERSION:SDK_INT	I
    //   86: invokestatic 371	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   89: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   92: aload_0
    //   93: aload_1
    //   94: ldc_w 533
    //   97: getstatic 538	android/os/Build:BOARD	Ljava/lang/String;
    //   100: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   103: aload_0
    //   104: aload_1
    //   105: ldc_w 540
    //   108: getstatic 543	android/os/Build:BRAND	Ljava/lang/String;
    //   111: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   114: aload_0
    //   115: aload_1
    //   116: ldc_w 545
    //   119: getstatic 548	android/os/Build:MODEL	Ljava/lang/String;
    //   122: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   125: aload_0
    //   126: aload_1
    //   127: ldc_w 550
    //   130: getstatic 553	android/os/Build:DEVICE	Ljava/lang/String;
    //   133: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   136: aload_0
    //   137: aload_1
    //   138: ldc_w 555
    //   141: getstatic 558	android/os/Build:DISPLAY	Ljava/lang/String;
    //   144: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   147: aload_0
    //   148: aload_1
    //   149: ldc_w 560
    //   152: getstatic 563	android/os/Build:FINGERPRINT	Ljava/lang/String;
    //   155: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   158: aload_0
    //   159: aload_1
    //   160: ldc_w 565
    //   163: getstatic 568	android/os/Build:HOST	Ljava/lang/String;
    //   166: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   169: aload_0
    //   170: aload_1
    //   171: ldc_w 570
    //   174: getstatic 572	android/os/Build:ID	Ljava/lang/String;
    //   177: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   180: aload_0
    //   181: aload_1
    //   182: ldc_w 574
    //   185: getstatic 548	android/os/Build:MODEL	Ljava/lang/String;
    //   188: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   191: aload_0
    //   192: aload_1
    //   193: ldc_w 576
    //   196: getstatic 579	android/os/Build:PRODUCT	Ljava/lang/String;
    //   199: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   202: aload_0
    //   203: aload_1
    //   204: ldc_w 581
    //   207: getstatic 584	android/os/Build:TAGS	Ljava/lang/String;
    //   210: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   213: aload_0
    //   214: aload_1
    //   215: ldc_w 586
    //   218: getstatic 589	android/os/Build:TYPE	Ljava/lang/String;
    //   221: invokestatic 592	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   224: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   227: aload_0
    //   228: aload_1
    //   229: ldc_w 594
    //   232: getstatic 597	android/os/Build:USER	Ljava/lang/String;
    //   235: invokestatic 592	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   238: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   241: aload_0
    //   242: aload_1
    //   243: ldc_w 599
    //   246: invokestatic 58	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   249: invokevirtual 600	java/util/Locale:toString	()Ljava/lang/String;
    //   252: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   255: aload_0
    //   256: aload_1
    //   257: ldc_w 602
    //   260: getstatic 76	com/microsoft/onlineid/internal/log/ErrorReportManager:CurrentAppContext	Landroid/content/Context;
    //   263: invokevirtual 199	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   266: invokevirtual 606	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   269: getfield 612	android/util/DisplayMetrics:density	F
    //   272: invokestatic 615	java/lang/String:valueOf	(F)Ljava/lang/String;
    //   275: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   278: aload_0
    //   279: aload_1
    //   280: ldc_w 617
    //   283: invokestatic 619	com/microsoft/onlineid/internal/log/ErrorReportManager:getScreenSize	()Ljava/lang/String;
    //   286: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   289: aload_0
    //   290: aload_1
    //   291: ldc_w 621
    //   294: invokestatic 623	com/microsoft/onlineid/internal/log/ErrorReportManager:getOrientation	()Ljava/lang/String;
    //   297: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   300: aload_1
    //   301: ldc_w 625
    //   304: invokevirtual 117	java/io/PrintWriter:append	(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
    //   307: pop
    //   308: aload_0
    //   309: aload_1
    //   310: ldc_w 627
    //   313: new 91	java/lang/StringBuilder
    //   316: dup
    //   317: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   320: invokestatic 632	android/os/Environment:getDataDirectory	()Ljava/io/File;
    //   323: invokevirtual 635	java/io/File:getTotalSpace	()J
    //   326: ldc2_w 636
    //   329: ldiv
    //   330: invokestatic 640	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   333: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   336: ldc_w 642
    //   339: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   345: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   348: aload_0
    //   349: aload_1
    //   350: ldc_w 644
    //   353: new 91	java/lang/StringBuilder
    //   356: dup
    //   357: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   360: invokestatic 632	android/os/Environment:getDataDirectory	()Ljava/io/File;
    //   363: invokevirtual 647	java/io/File:getUsableSpace	()J
    //   366: ldc2_w 636
    //   369: ldiv
    //   370: invokestatic 640	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   373: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: ldc_w 642
    //   379: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   382: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   385: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   388: aload_1
    //   389: ldc_w 649
    //   392: invokevirtual 117	java/io/PrintWriter:append	(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
    //   395: pop
    //   396: aload_0
    //   397: aload_1
    //   398: ldc_w 651
    //   401: new 91	java/lang/StringBuilder
    //   404: dup
    //   405: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   408: invokestatic 656	android/os/Debug:getNativeHeapAllocatedSize	()J
    //   411: ldc2_w 636
    //   414: ldiv
    //   415: invokestatic 640	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   418: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   421: ldc_w 642
    //   424: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   427: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   430: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   433: aload_0
    //   434: aload_1
    //   435: ldc_w 658
    //   438: new 91	java/lang/StringBuilder
    //   441: dup
    //   442: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   445: invokestatic 661	android/os/Debug:getNativeHeapFreeSize	()J
    //   448: ldc2_w 636
    //   451: ldiv
    //   452: invokestatic 640	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   455: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   458: ldc_w 642
    //   461: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   464: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   467: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   470: aload_0
    //   471: aload_1
    //   472: ldc_w 663
    //   475: new 91	java/lang/StringBuilder
    //   478: dup
    //   479: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   482: invokestatic 666	android/os/Debug:getNativeHeapSize	()J
    //   485: ldc2_w 636
    //   488: ldiv
    //   489: invokestatic 640	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   492: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: ldc_w 642
    //   498: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   501: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   504: invokespecial 526	com/microsoft/onlineid/internal/log/ErrorReportManager:appendValue	(Ljava/io/PrintWriter;Ljava/lang/String;Ljava/lang/String;)V
    //   507: aload_1
    //   508: ldc -70
    //   510: invokevirtual 117	java/io/PrintWriter:append	(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
    //   513: pop
    //   514: return
    //   515: astore_2
    //   516: ldc_w 668
    //   519: aload_2
    //   520: invokestatic 396	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   523: goto -16 -> 507
    //   526: astore_2
    //   527: goto -468 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	530	0	this	ErrorReportManager
    //   0	530	1	paramPrintWriter	PrintWriter
    //   515	5	2	localException1	Exception
    //   526	1	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	31	515	java/lang/Exception
    //   59	507	515	java/lang/Exception
    //   31	59	526	java/lang/Exception
  }
  
  /* Error */
  protected File getScreenshotFile()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload 4
    //   5: astore_3
    //   6: aload_0
    //   7: getfield 72	com/microsoft/onlineid/internal/log/ErrorReportManager:_sendScreenshot	Z
    //   10: ifeq +52 -> 62
    //   13: new 379	java/io/File
    //   16: dup
    //   17: new 91	java/lang/StringBuilder
    //   20: dup
    //   21: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   24: invokestatic 671	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   27: invokevirtual 674	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   30: getstatic 677	java/io/File:separator	Ljava/lang/String;
    //   33: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: ldc 39
    //   38: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: invokespecial 679	java/io/File:<init>	(Ljava/lang/String;)V
    //   47: astore_2
    //   48: aload_2
    //   49: invokevirtual 682	java/io/File:exists	()Z
    //   52: istore_1
    //   53: aload 4
    //   55: astore_3
    //   56: iload_1
    //   57: ifeq +5 -> 62
    //   60: aload_2
    //   61: astore_3
    //   62: aload_3
    //   63: areturn
    //   64: astore_3
    //   65: aconst_null
    //   66: astore_2
    //   67: ldc_w 684
    //   70: aload_3
    //   71: invokestatic 396	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   74: aload_2
    //   75: areturn
    //   76: astore_3
    //   77: goto -10 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	this	ErrorReportManager
    //   52	5	1	bool	boolean
    //   47	28	2	localFile	File
    //   5	58	3	localObject1	Object
    //   64	7	3	localException1	Exception
    //   76	1	3	localException2	Exception
    //   1	53	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   6	48	64	java/lang/Exception
    //   48	53	76	java/lang/Exception
  }
  
  public void init(Context paramContext)
  {
    try
    {
      CurrentAppContext = paramContext;
      if ((this._contextFilePath == null) && (CurrentAppContext != null)) {
        this._contextFilePath = CurrentAppContext.getFilesDir();
      }
      return;
    }
    catch (Exception paramContext)
    {
      Logger.warning("Error in init: ", paramContext);
    }
  }
  
  protected boolean isIgnoreCrashReportingFlagSet()
  {
    return Settings.getInstance(CurrentAppContext).isSettingEnabled("isIgnoreCrashReporting");
  }
  
  protected void notifyUserOfNoMailApp()
  {
    DialogInterface.OnClickListener local2 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    };
    AlertDialog.Builder localBuilder = new AlertDialog.Builder((Context)CurrentActivityContext.get());
    localBuilder.setTitle(getStringResourceIdAtRuntime("send_feedback_no_email_app_header"));
    localBuilder.setMessage(getStringResourceIdAtRuntime("send_feedback_no_email_app_body"));
    localBuilder.setPositiveButton(getStringResourceIdAtRuntime("popup_button_close"), local2);
    localBuilder.show();
  }
  
  /* Error */
  protected void saveScreenshot(WeakReference<Context> paramWeakReference)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 714	com/microsoft/onlineid/internal/log/ErrorReportManager:deleteScreenshot	()V
    //   4: aconst_null
    //   5: astore_3
    //   6: aconst_null
    //   7: astore 4
    //   9: aload_3
    //   10: astore_2
    //   11: aload_1
    //   12: invokevirtual 253	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   15: checkcast 716	android/app/Activity
    //   18: invokevirtual 720	android/app/Activity:getWindow	()Landroid/view/Window;
    //   21: invokevirtual 726	android/view/Window:getDecorView	()Landroid/view/View;
    //   24: astore_1
    //   25: aload_3
    //   26: astore_2
    //   27: aload_1
    //   28: iconst_1
    //   29: invokevirtual 732	android/view/View:setDrawingCacheEnabled	(Z)V
    //   32: aload_3
    //   33: astore_2
    //   34: aload_1
    //   35: invokevirtual 735	android/view/View:buildDrawingCache	()V
    //   38: aload_3
    //   39: astore_2
    //   40: aload_1
    //   41: invokevirtual 739	android/view/View:getDrawingCache	()Landroid/graphics/Bitmap;
    //   44: astore 5
    //   46: aload_3
    //   47: astore_2
    //   48: new 741	java/io/FileOutputStream
    //   51: dup
    //   52: new 91	java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   59: invokestatic 671	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   62: invokevirtual 674	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   65: getstatic 677	java/io/File:separator	Ljava/lang/String;
    //   68: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: ldc 39
    //   73: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokespecial 742	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   82: astore_1
    //   83: aload 5
    //   85: getstatic 748	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   88: bipush 10
    //   90: aload_1
    //   91: invokevirtual 754	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   94: pop
    //   95: aload_1
    //   96: ifnull +7 -> 103
    //   99: aload_1
    //   100: invokevirtual 755	java/io/FileOutputStream:close	()V
    //   103: return
    //   104: astore_3
    //   105: aload_1
    //   106: astore_2
    //   107: ldc_w 757
    //   110: aload_3
    //   111: invokestatic 396	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   114: aload_1
    //   115: ifnull -12 -> 103
    //   118: aload_1
    //   119: invokevirtual 755	java/io/FileOutputStream:close	()V
    //   122: return
    //   123: astore_1
    //   124: ldc_w 757
    //   127: aload_1
    //   128: invokestatic 396	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   131: return
    //   132: astore_3
    //   133: aload_2
    //   134: astore_1
    //   135: aload_3
    //   136: astore_2
    //   137: aload_1
    //   138: ifnull +7 -> 145
    //   141: aload_1
    //   142: invokevirtual 755	java/io/FileOutputStream:close	()V
    //   145: aload_2
    //   146: athrow
    //   147: astore_1
    //   148: goto -24 -> 124
    //   151: astore_2
    //   152: goto -15 -> 137
    //   155: astore_3
    //   156: aload 4
    //   158: astore_1
    //   159: goto -54 -> 105
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	162	0	this	ErrorReportManager
    //   0	162	1	paramWeakReference	WeakReference<Context>
    //   10	136	2	localObject1	Object
    //   151	1	2	localObject2	Object
    //   5	42	3	localObject3	Object
    //   104	7	3	localException1	Exception
    //   132	4	3	localObject4	Object
    //   155	1	3	localException2	Exception
    //   7	150	4	localObject5	Object
    //   44	40	5	localBitmap	android.graphics.Bitmap
    // Exception table:
    //   from	to	target	type
    //   83	95	104	java/lang/Exception
    //   118	122	123	java/lang/Exception
    //   141	145	123	java/lang/Exception
    //   145	147	123	java/lang/Exception
    //   11	25	132	finally
    //   27	32	132	finally
    //   34	38	132	finally
    //   40	46	132	finally
    //   48	83	132	finally
    //   107	114	132	finally
    //   99	103	147	java/lang/Exception
    //   83	95	151	finally
    //   11	25	155	java/lang/Exception
    //   27	32	155	java/lang/Exception
    //   34	38	155	java/lang/Exception
    //   40	46	155	java/lang/Exception
    //   48	83	155	java/lang/Exception
  }
  
  protected void sendEmail(WeakReference<Context> paramWeakReference, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      File localFile = getScreenshotFile();
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.putExtra("android.intent.extra.EMAIL", new String[] { paramString2 });
      localIntent.putExtra("android.intent.extra.SUBJECT", paramString3);
      localIntent.putExtra("android.intent.extra.TEXT", paramString1 + "\n");
      localIntent.setType("message/rfc822");
      if (localFile != null) {
        localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(localFile));
      }
      ((Context)paramWeakReference.get()).startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramWeakReference)
    {
      notifyUserOfNoMailApp();
      Logger.warning("ActivityNotFoundException in sendEmail.", paramWeakReference);
      return;
    }
    catch (Exception paramWeakReference)
    {
      Logger.warning("Exception in sendEmail.", paramWeakReference);
    }
  }
  
  protected void sendMailAndDeleteFiles(boolean paramBoolean)
  {
    Object localObject1 = null;
    File[] arrayOfFile = getCrashErrorFileList();
    int i;
    int j;
    File localFile1;
    try
    {
      Arrays.sort(arrayOfFile);
      int k = arrayOfFile.length;
      i = 0;
      j = 0;
      if (i < k)
      {
        localFile1 = arrayOfFile[i];
        if (paramBoolean)
        {
          long l = j;
          j = (int)(localFile1.length() + l);
        }
        else
        {
          deleteFileNoThrow(localFile1);
        }
      }
    }
    catch (Exception localException1)
    {
      Logger.warning("Error in sendMailAndDeleteFiles: ", localException1);
    }
    label92:
    label147:
    label267:
    label272:
    for (;;)
    {
      return;
      CharBuffer localCharBuffer;
      File localFile2;
      if (j > 0)
      {
        localCharBuffer = CharBuffer.allocate(j);
        j = arrayOfFile.length;
        i = 0;
        if (i >= j) {
          break label267;
        }
        localFile2 = arrayOfFile[i];
        for (;;)
        {
          try
          {
            localFileReader = new FileReader(localFile2.getAbsoluteFile());
          }
          catch (Exception localException3)
          {
            FileReader localFileReader;
            localObject3 = null;
            continue;
          }
          for (;;)
          {
            try
            {
              if (localFileReader.read(localCharBuffer) > 0) {
                break;
              }
              localCharBuffer.flip();
            }
            catch (Exception localException4)
            {
              break label147;
            }
            try
            {
              localFileReader.close();
            }
            catch (Exception localException2)
            {
              Logger.error("Error closing the report file", localException2);
            }
            finally
            {
              deleteFileNoThrow(localFile2);
            }
          }
          Logger.warning("Error reading the report file", localFile1);
        }
      }
      for (;;)
      {
        if ((!paramBoolean) || (localObject2 == null)) {
          break label272;
        }
        sendEmail(CurrentActivityContext, ((CharBuffer)localObject2).toString(), "WS-MSACLIENT-AFB@microsoft.com", String.format(Locale.US, "MSA Android Application Crash Report - %s", new Object[] { EmailTitleDateFormat.format(new Date()) }));
        return;
        i += 1;
        break;
        i += 1;
        break label92;
        Object localObject3 = localCharBuffer;
      }
    }
  }
  
  public void setSendLogs(boolean paramBoolean)
  {
    this._sendLogs = paramBoolean;
  }
  
  public void setSendScreenshot(boolean paramBoolean)
  {
    this._sendScreenshot = paramBoolean;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\log\ErrorReportManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */