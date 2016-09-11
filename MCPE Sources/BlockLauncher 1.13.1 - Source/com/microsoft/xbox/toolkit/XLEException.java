package com.microsoft.xbox.toolkit;

public class XLEException
  extends Exception
{
  private long errorCode;
  private boolean isHandled;
  private Object userObject;
  
  public XLEException(long paramLong)
  {
    this(paramLong, null, null, null);
  }
  
  public XLEException(long paramLong, String paramString)
  {
    this(paramLong, paramString, null, null);
  }
  
  public XLEException(long paramLong, String paramString, Throwable paramThrowable)
  {
    this(paramLong, null, paramThrowable, null);
  }
  
  public XLEException(long paramLong, String paramString, Throwable paramThrowable, Object paramObject)
  {
    super(paramString, paramThrowable);
    this.errorCode = paramLong;
    this.userObject = paramObject;
    this.isHandled = false;
  }
  
  public XLEException(long paramLong, Throwable paramThrowable)
  {
    this(paramLong, null, paramThrowable, null);
  }
  
  public long getErrorCode()
  {
    return this.errorCode;
  }
  
  public boolean getIsHandled()
  {
    return this.isHandled;
  }
  
  public Object getUserObject()
  {
    return this.userObject;
  }
  
  public void setIsHandled(boolean paramBoolean)
  {
    this.isHandled = paramBoolean;
  }
  
  public String toString()
  {
    int i = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(String.format("XLEException ErrorCode: %d; ErrorMessage: %s \n\n", new Object[] { Long.valueOf(this.errorCode), getMessage() }));
    if (getCause() != null)
    {
      localStringBuilder.append(String.format("\t Cause ErrorMessage: %s, StackTrace: ", new Object[] { getCause().toString() }));
      StackTraceElement[] arrayOfStackTraceElement = getCause().getStackTrace();
      int j = arrayOfStackTraceElement.length;
      while (i < j)
      {
        StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i];
        localStringBuilder.append("\n\n \t " + localStackTraceElement.toString());
        i += 1;
      }
    }
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\XLEException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */