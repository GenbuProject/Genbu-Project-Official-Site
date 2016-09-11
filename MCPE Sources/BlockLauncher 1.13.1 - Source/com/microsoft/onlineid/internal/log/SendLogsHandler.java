package com.microsoft.onlineid.internal.log;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.microsoft.onlineid.internal.Assertion;

public class SendLogsHandler
{
  protected static final long SendKeyEventIntervalMillis = 5000L;
  protected static final String ToastMsg = "Press the 'volume down' button %d more time(s) to send logs.";
  private Context _activityContext = null;
  private Context _applicationContext = null;
  private ErrorReportManager _errorReport;
  private byte _sendLogsKeyCounter = -1;
  private long _startTime = 0L;
  private Toast _toast;
  
  public SendLogsHandler(Activity paramActivity)
  {
    this(paramActivity, null);
  }
  
  protected SendLogsHandler(Activity paramActivity, ErrorReportManager paramErrorReportManager)
  {
    this._activityContext = paramActivity;
    if (paramActivity != null) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      Assertion.check(bool1);
      this._applicationContext = paramActivity.getApplicationContext();
      bool1 = bool2;
      if (this._applicationContext != null) {
        bool1 = true;
      }
      Assertion.check(bool1);
      paramActivity = paramErrorReportManager;
      if (paramErrorReportManager == null) {
        paramActivity = new ErrorReportManager(this._applicationContext);
      }
      this._errorReport = paramActivity;
      return;
    }
  }
  
  protected SendLogsHandler(Context paramContext, ErrorReportManager paramErrorReportManager)
  {
    this._activityContext = paramContext;
    this._errorReport = paramErrorReportManager;
  }
  
  protected long getTimeMillis()
  {
    return System.currentTimeMillis();
  }
  
  public void sendLogs()
  {
    this._errorReport.generateAndSendReportWithUserPermission(this._activityContext);
  }
  
  public void sendLogs(String paramString)
  {
    this._errorReport.generateAndSendReportWithUserPermission(this._activityContext, paramString);
  }
  
  public void setSendLogs(boolean paramBoolean)
  {
    this._errorReport.setSendLogs(paramBoolean);
  }
  
  public void setSendScreenshot(boolean paramBoolean)
  {
    this._errorReport.setSendScreenshot(paramBoolean);
  }
  
  protected void showToast(String paramString)
  {
    if (this._toast == null) {
      this._toast = Toast.makeText(this._applicationContext, paramString, 1);
    }
    for (;;)
    {
      this._toast.show();
      return;
      this._toast.setText(paramString);
    }
  }
  
  public void trySendLogsOnKeyEvent(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 24: 
      this._sendLogsKeyCounter = 2;
      showToast(String.format("Press the 'volume down' button %d more time(s) to send logs.", new Object[] { Byte.valueOf(this._sendLogsKeyCounter) }));
      this._startTime = getTimeMillis();
      return;
    }
    long l1 = getTimeMillis();
    long l2 = this._startTime;
    if ((this._sendLogsKeyCounter >= 0) && (l1 - l2 < 5000L))
    {
      this._sendLogsKeyCounter -= 1;
      if (this._sendLogsKeyCounter > 0)
      {
        showToast(String.format("Press the 'volume down' button %d more time(s) to send logs.", new Object[] { Byte.valueOf(this._sendLogsKeyCounter) }));
        return;
      }
      sendLogs();
      this._sendLogsKeyCounter = -1;
      return;
    }
    this._sendLogsKeyCounter = -1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\log\SendLogsHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */