package com.microsoft.onlineid.internal.log;

import android.util.Log;

public class LogInstance
{
  public static final String LogTag = "MSA";
  public static final int MaxLogLength = 4000;
  private boolean _isLoggingEnabled = false;
  private boolean _isRedactionEnabled = true;
  private boolean _isStackTraceLoggingEnabled = true;
  
  protected LogInstance() {}
  
  protected LogInstance(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this._isRedactionEnabled = paramBoolean1;
    this._isLoggingEnabled = paramBoolean2;
    this._isStackTraceLoggingEnabled = paramBoolean3;
  }
  
  private void logMessageLevel(String paramString, int paramInt, Throwable paramThrowable)
  {
    switch (paramInt)
    {
    default: 
      if (paramThrowable == null)
      {
        logInfo(paramString);
        return;
      }
      break;
    case 6: 
      if (paramThrowable == null)
      {
        logError(paramString);
        return;
      }
      logError(paramString, paramThrowable);
      return;
    case 5: 
      if (paramThrowable == null)
      {
        logWarning(paramString);
        return;
      }
      logWarning(paramString, paramThrowable);
      return;
    }
    logInfo(paramString, paramThrowable);
  }
  
  protected String getStackTraceInfo(String paramString, int paramInt)
  {
    int i = 0;
    if (!this._isStackTraceLoggingEnabled) {
      return paramString;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    for (;;)
    {
      try
      {
        localStringBuilder.append(paramString);
        paramString = Thread.currentThread().getStackTrace();
        int k = paramString.length;
        int j = 0;
        if (i < k)
        {
          Object localObject = paramString[i];
          j += 1;
          if (!((StackTraceElement)localObject).getMethodName().contains("getStackTrace")) {
            continue;
          }
          paramInt = j + paramInt;
          localStringBuilder.append(" ");
          localStringBuilder.append(paramString[paramInt].getMethodName());
          localStringBuilder.append("()@");
          localStringBuilder.append(paramString[paramInt].getFileName());
          localStringBuilder.append("_");
          localStringBuilder.append(paramString[paramInt].getLineNumber());
        }
      }
      catch (Exception paramString)
      {
        logWarning("Error in getStackTraceInfo", paramString);
        continue;
      }
      return localStringBuilder.toString();
      i += 1;
    }
  }
  
  protected void logError(String paramString)
  {
    Log.e("MSA", paramString);
  }
  
  protected void logError(String paramString, Throwable paramThrowable)
  {
    Log.e("MSA", paramString, paramThrowable);
  }
  
  protected void logInfo(String paramString)
  {
    Log.i("MSA", paramString);
  }
  
  protected void logInfo(String paramString, Throwable paramThrowable)
  {
    Log.i("MSA", paramString, paramThrowable);
  }
  
  protected void logMessage(String paramString, int paramInt, Throwable paramThrowable)
  {
    logMessage(paramString, paramInt, paramThrowable, 4);
  }
  
  protected void logMessage(String paramString, int paramInt1, Throwable paramThrowable, int paramInt2)
  {
    if ((paramString != null) && (this._isLoggingEnabled) && (Log.isLoggable("MSA", paramInt1)))
    {
      int k = paramString.length();
      int j;
      for (int i = 0; i < k; i = j)
      {
        j = Math.min(k, i + 4000);
        logMessageLevel(getStackTraceInfo(paramString.substring(i, j), paramInt2), paramInt1, paramThrowable);
      }
    }
  }
  
  protected void logRedactedMessage(IRedactable paramIRedactable, int paramInt)
  {
    if (paramIRedactable != null)
    {
      if (this._isRedactionEnabled == true) {
        logMessage(paramIRedactable.getRedactedString(), paramInt, null, 4);
      }
    }
    else {
      return;
    }
    logMessage(paramIRedactable.getUnredactedString(), paramInt, null, 4);
  }
  
  protected void logWarning(String paramString)
  {
    Log.w("MSA", paramString);
  }
  
  protected void logWarning(String paramString, Throwable paramThrowable)
  {
    Log.w("MSA", paramString, paramThrowable);
  }
  
  protected void setIsLoggingEnabled(boolean paramBoolean)
  {
    this._isLoggingEnabled = paramBoolean;
  }
  
  protected void setIsRedactionEnable(boolean paramBoolean)
  {
    this._isRedactionEnabled = paramBoolean;
  }
  
  protected void setIsStackTraceLoggingEnabled(boolean paramBoolean)
  {
    this._isStackTraceLoggingEnabled = paramBoolean;
  }
  
  protected boolean shouldRedact()
  {
    return (this._isRedactionEnabled) && (this._isLoggingEnabled);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\log\LogInstance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */