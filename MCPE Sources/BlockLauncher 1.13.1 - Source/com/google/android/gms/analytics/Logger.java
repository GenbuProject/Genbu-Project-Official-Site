package com.google.android.gms.analytics;

@Deprecated
public abstract interface Logger
{
  @Deprecated
  public abstract void error(Exception paramException);
  
  @Deprecated
  public abstract void error(String paramString);
  
  @Deprecated
  public abstract int getLogLevel();
  
  @Deprecated
  public abstract void info(String paramString);
  
  @Deprecated
  public abstract void setLogLevel(int paramInt);
  
  @Deprecated
  public abstract void verbose(String paramString);
  
  @Deprecated
  public abstract void warn(String paramString);
  
  @Deprecated
  public static class LogLevel
  {
    public static final int ERROR = 3;
    public static final int INFO = 1;
    public static final int VERBOSE = 0;
    public static final int WARNING = 2;
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\Logger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */