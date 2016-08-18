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
        int i = 0;
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
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 8
    //   6: aconst_null
    //   7: astore_3
    //   8: aconst_null
    //   9: astore 5
    //   11: aconst_null
    //   12: astore 6
    //   14: ldc_w 400
    //   17: astore 7
    //   19: new 402	java/io/CharArrayWriter
    //   22: dup
    //   23: invokespecial 403	java/io/CharArrayWriter:<init>	()V
    //   26: astore_2
    //   27: new 114	java/io/PrintWriter
    //   30: dup
    //   31: aload_2
    //   32: invokespecial 406	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   35: astore_3
    //   36: aload_0
    //   37: aconst_null
    //   38: iconst_0
    //   39: aload_1
    //   40: aload_3
    //   41: invokevirtual 408	com/microsoft/onlineid/internal/log/ErrorReportManager:constructReport	(Ljava/lang/Throwable;ZLjava/lang/String;Ljava/io/PrintWriter;)V
    //   44: aload_3
    //   45: invokevirtual 411	java/io/PrintWriter:close	()V
    //   48: aload_2
    //   49: invokevirtual 412	java/lang/Object:toString	()Ljava/lang/String;
    //   52: astore 4
    //   54: aload_3
    //   55: invokevirtual 411	java/io/PrintWriter:close	()V
    //   58: aload_2
    //   59: invokevirtual 415	java/io/Writer:close	()V
    //   62: aload 4
    //   64: astore_2
    //   65: getstatic 76	com/microsoft/onlineid/internal/log/ErrorReportManager:CurrentAppContext	Landroid/content/Context;
    //   68: invokevirtual 199	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   71: ldc_w 417
    //   74: invokestatic 419	com/microsoft/onlineid/internal/log/ErrorReportManager:getStringResourceIdAtRuntime	(Ljava/lang/String;)I
    //   77: invokevirtual 422	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   80: astore 5
    //   82: getstatic 66	com/microsoft/onlineid/internal/log/ErrorReportManager:EmailTitleDateFormat	Ljava/text/DateFormat;
    //   85: new 347	java/util/Date
    //   88: dup
    //   89: invokespecial 348	java/util/Date:<init>	()V
    //   92: invokevirtual 425	java/text/DateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   95: astore 4
    //   97: aload 4
    //   99: astore_3
    //   100: aload_1
    //   101: ifnull +52 -> 153
    //   104: aload 4
    //   106: astore_3
    //   107: aload_1
    //   108: invokevirtual 300	java/lang/String:isEmpty	()Z
    //   111: ifne +42 -> 153
    //   114: new 91	java/lang/StringBuilder
    //   117: dup
    //   118: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   121: aload 4
    //   123: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: ldc_w 427
    //   129: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload_1
    //   133: iconst_0
    //   134: aload_1
    //   135: invokevirtual 430	java/lang/String:length	()I
    //   138: bipush 50
    //   140: invokestatic 436	java/lang/Math:min	(II)I
    //   143: invokevirtual 440	java/lang/String:substring	(II)Ljava/lang/String;
    //   146: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   152: astore_3
    //   153: aload_0
    //   154: getstatic 247	com/microsoft/onlineid/internal/log/ErrorReportManager:CurrentActivityContext	Ljava/lang/ref/WeakReference;
    //   157: aload_2
    //   158: ldc 45
    //   160: getstatic 106	java/util/Locale:US	Ljava/util/Locale;
    //   163: ldc_w 442
    //   166: iconst_2
    //   167: anewarray 4	java/lang/Object
    //   170: dup
    //   171: iconst_0
    //   172: aload 5
    //   174: aastore
    //   175: dup
    //   176: iconst_1
    //   177: aload_3
    //   178: aastore
    //   179: invokestatic 112	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   182: invokevirtual 446	com/microsoft/onlineid/internal/log/ErrorReportManager:sendEmail	(Ljava/lang/ref/WeakReference;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   185: return
    //   186: astore 5
    //   188: aload 8
    //   190: astore_2
    //   191: aload 6
    //   193: astore_3
    //   194: aload_2
    //   195: astore 4
    //   197: ldc_w 448
    //   200: aload 5
    //   202: invokestatic 396	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   205: aload 6
    //   207: invokevirtual 411	java/io/PrintWriter:close	()V
    //   210: aload_2
    //   211: invokevirtual 415	java/io/Writer:close	()V
    //   214: aload 7
    //   216: astore_2
    //   217: goto -152 -> 65
    //   220: astore_1
    //   221: ldc_w 448
    //   224: aload_1
    //   225: invokestatic 396	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   228: return
    //   229: astore_1
    //   230: aload_3
    //   231: invokevirtual 411	java/io/PrintWriter:close	()V
    //   234: aload 4
    //   236: invokevirtual 415	java/io/Writer:close	()V
    //   239: aload_1
    //   240: athrow
    //   241: astore_1
    //   242: goto -21 -> 221
    //   245: astore_1
    //   246: aload 5
    //   248: astore_3
    //   249: aload_2
    //   250: astore 4
    //   252: goto -22 -> 230
    //   255: astore_1
    //   256: aload_2
    //   257: astore 4
    //   259: goto -29 -> 230
    //   262: astore 5
    //   264: goto -73 -> 191
    //   267: astore 5
    //   269: aload_3
    //   270: astore 6
    //   272: goto -81 -> 191
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	275	0	this	ErrorReportManager
    //   0	275	1	paramString	String
    //   26	231	2	localObject1	Object
    //   7	263	3	localObject2	Object
    //   1	257	4	localObject3	Object
    //   9	164	5	str1	String
    //   186	61	5	localException1	Exception
    //   262	1	5	localException2	Exception
    //   267	1	5	localException3	Exception
    //   12	259	6	localObject4	Object
    //   17	198	7	str2	String
    //   4	185	8	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   19	27	186	java/lang/Exception
    //   65	97	220	java/lang/Exception
    //   107	153	220	java/lang/Exception
    //   153	185	220	java/lang/Exception
    //   205	214	220	java/lang/Exception
    //   230	241	220	java/lang/Exception
    //   19	27	229	finally
    //   197	205	229	finally
    //   54	62	241	java/lang/Exception
    //   27	36	245	finally
    //   36	54	255	finally
    //   27	36	262	java/lang/Exception
    //   36	54	267	java/lang/Exception
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
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: aload_0
    //   5: getfield 72	com/microsoft/onlineid/internal/log/ErrorReportManager:_sendScreenshot	Z
    //   8: ifeq +65 -> 73
    //   11: new 379	java/io/File
    //   14: dup
    //   15: new 91	java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   22: invokestatic 671	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   25: invokevirtual 674	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   28: getstatic 677	java/io/File:separator	Ljava/lang/String;
    //   31: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: ldc 39
    //   36: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: invokespecial 679	java/io/File:<init>	(Ljava/lang/String;)V
    //   45: astore_2
    //   46: aload_2
    //   47: invokevirtual 682	java/io/File:exists	()Z
    //   50: istore_1
    //   51: iload_1
    //   52: ifeq +5 -> 57
    //   55: aload_2
    //   56: areturn
    //   57: aconst_null
    //   58: areturn
    //   59: astore 4
    //   61: aload_3
    //   62: astore_2
    //   63: aload 4
    //   65: astore_3
    //   66: ldc_w 684
    //   69: aload_3
    //   70: invokestatic 396	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   73: aload_2
    //   74: areturn
    //   75: astore_3
    //   76: goto -10 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	this	ErrorReportManager
    //   50	2	1	bool	boolean
    //   1	73	2	localObject1	Object
    //   3	67	3	localObject2	Object
    //   75	1	3	localException1	Exception
    //   59	5	4	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   4	46	59	java/lang/Exception
    //   46	51	75	java/lang/Exception
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
    //   5: astore 4
    //   7: aconst_null
    //   8: astore_3
    //   9: aload 4
    //   11: astore_2
    //   12: aload_1
    //   13: invokevirtual 253	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   16: checkcast 716	android/app/Activity
    //   19: invokevirtual 720	android/app/Activity:getWindow	()Landroid/view/Window;
    //   22: invokevirtual 726	android/view/Window:getDecorView	()Landroid/view/View;
    //   25: astore_1
    //   26: aload 4
    //   28: astore_2
    //   29: aload_1
    //   30: iconst_1
    //   31: invokevirtual 732	android/view/View:setDrawingCacheEnabled	(Z)V
    //   34: aload 4
    //   36: astore_2
    //   37: aload_1
    //   38: invokevirtual 735	android/view/View:buildDrawingCache	()V
    //   41: aload 4
    //   43: astore_2
    //   44: aload_1
    //   45: invokevirtual 739	android/view/View:getDrawingCache	()Landroid/graphics/Bitmap;
    //   48: astore 5
    //   50: aload 4
    //   52: astore_2
    //   53: new 741	java/io/FileOutputStream
    //   56: dup
    //   57: new 91	java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   64: invokestatic 671	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   67: invokevirtual 674	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   70: getstatic 677	java/io/File:separator	Ljava/lang/String;
    //   73: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: ldc 39
    //   78: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 102	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: invokespecial 742	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   87: astore_1
    //   88: aload 5
    //   90: getstatic 748	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   93: bipush 10
    //   95: aload_1
    //   96: invokevirtual 754	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   99: pop
    //   100: aload_1
    //   101: ifnull +67 -> 168
    //   104: aload_1
    //   105: invokevirtual 755	java/io/FileOutputStream:close	()V
    //   108: return
    //   109: astore_2
    //   110: aload_3
    //   111: astore_1
    //   112: aload_2
    //   113: astore_3
    //   114: aload_1
    //   115: astore_2
    //   116: ldc_w 757
    //   119: aload_3
    //   120: invokestatic 396	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   123: aload_1
    //   124: ifnull -16 -> 108
    //   127: aload_1
    //   128: invokevirtual 755	java/io/FileOutputStream:close	()V
    //   131: return
    //   132: astore_1
    //   133: ldc_w 757
    //   136: aload_1
    //   137: invokestatic 396	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   140: return
    //   141: astore_1
    //   142: aload_2
    //   143: ifnull +7 -> 150
    //   146: aload_2
    //   147: invokevirtual 755	java/io/FileOutputStream:close	()V
    //   150: aload_1
    //   151: athrow
    //   152: astore_1
    //   153: goto -20 -> 133
    //   156: astore_3
    //   157: aload_1
    //   158: astore_2
    //   159: aload_3
    //   160: astore_1
    //   161: goto -19 -> 142
    //   164: astore_3
    //   165: goto -51 -> 114
    //   168: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	169	0	this	ErrorReportManager
    //   0	169	1	paramWeakReference	WeakReference<Context>
    //   11	42	2	localObject1	Object
    //   109	4	2	localException1	Exception
    //   115	44	2	localWeakReference	WeakReference<Context>
    //   8	112	3	localObject2	Object
    //   156	4	3	localObject3	Object
    //   164	1	3	localException2	Exception
    //   5	46	4	localObject4	Object
    //   48	41	5	localBitmap	android.graphics.Bitmap
    // Exception table:
    //   from	to	target	type
    //   12	26	109	java/lang/Exception
    //   29	34	109	java/lang/Exception
    //   37	41	109	java/lang/Exception
    //   44	50	109	java/lang/Exception
    //   53	88	109	java/lang/Exception
    //   127	131	132	java/lang/Exception
    //   146	150	132	java/lang/Exception
    //   150	152	132	java/lang/Exception
    //   12	26	141	finally
    //   29	34	141	finally
    //   37	41	141	finally
    //   44	50	141	finally
    //   53	88	141	finally
    //   116	123	141	finally
    //   104	108	152	java/lang/Exception
    //   88	100	156	finally
    //   88	100	164	java/lang/Exception
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
    File[] arrayOfFile = getCrashErrorFileList();
    Object localObject1 = null;
    int j;
    int i;
    File localFile1;
    try
    {
      Arrays.sort(arrayOfFile);
      j = 0;
      int k = arrayOfFile.length;
      i = 0;
      if (i >= k) {
        break label72;
      }
      localFile1 = arrayOfFile[i];
      if (paramBoolean) {
        j = (int)(j + localFile1.length());
      } else {
        deleteFileNoThrow(localFile1);
      }
    }
    catch (Exception localException1)
    {
      Logger.warning("Error in sendMailAndDeleteFiles: ", localException1);
    }
    label71:
    return;
    label72:
    CharBuffer localCharBuffer;
    if (j > 0)
    {
      localCharBuffer = CharBuffer.allocate(j);
      j = arrayOfFile.length;
      i = 0;
    }
    for (;;)
    {
      Object localObject2 = localCharBuffer;
      File localFile2;
      if (i < j)
      {
        localFile2 = arrayOfFile[i];
        localObject5 = null;
        for (;;)
        {
          try
          {
            localObject2 = new FileReader(localFile2.getAbsoluteFile());
          }
          catch (Exception localException4)
          {
            Object localObject4 = localObject5;
            continue;
          }
          try
          {
            if (((FileReader)localObject2).read(localCharBuffer) > 0) {
              continue;
            }
            localCharBuffer.flip();
          }
          catch (Exception localException3)
          {
            continue;
          }
          try
          {
            ((FileReader)localObject2).close();
          }
          catch (Exception localException2)
          {
            Logger.error("Error closing the report file", localException2);
          }
          finally
          {
            deleteFileNoThrow(localFile2);
          }
          Logger.warning("Error reading the report file", localFile1);
        }
      }
      if ((!paramBoolean) || (localObject3 == null)) {
        break label71;
      }
      sendEmail(CurrentActivityContext, ((CharBuffer)localObject3).toString(), "WS-MSACLIENT-AFB@microsoft.com", String.format(Locale.US, "MSA Android Application Crash Report - %s", new Object[] { EmailTitleDateFormat.format(new Date()) }));
      return;
      i += 1;
      break;
      i += 1;
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\log\ErrorReportManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */