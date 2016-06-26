package com.amazon.android.framework.exception;

public abstract class KiwiException
  extends Exception
{
  private static final long serialVersionUID = 1L;
  private final String context;
  private final String reason;
  private final String type;
  
  public KiwiException(String paramString)
  {
    this(paramString, null);
  }
  
  public KiwiException(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, null);
  }
  
  public KiwiException(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1 + ": " + paramString2 + ": " + paramString3);
    this.type = paramString1;
    this.reason = paramString2;
    this.context = paramString3;
  }
  
  public KiwiException(String paramString1, String paramString2, Throwable paramThrowable)
  {
    this(paramString1, paramString2, getContext(paramThrowable));
  }
  
  public KiwiException(String paramString, Throwable paramThrowable)
  {
    this(paramString, getName(paramThrowable), paramThrowable);
  }
  
  private static String getContext(Throwable paramThrowable)
  {
    if (paramThrowable == null) {
      return null;
    }
    Throwable localThrowable = getRootCause(paramThrowable);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getName(paramThrowable)).append(":").append(paramThrowable.getMessage());
    if (paramThrowable != localThrowable) {
      localStringBuilder.append("/").append(getName(localThrowable)).append(":").append(localThrowable.getMessage());
    }
    return localStringBuilder.toString();
  }
  
  private static String getName(Throwable paramThrowable)
  {
    return paramThrowable.getClass().getName();
  }
  
  private static Throwable getRootCause(Throwable paramThrowable)
  {
    while (paramThrowable.getCause() != null) {
      paramThrowable = paramThrowable.getCause();
    }
    return paramThrowable;
  }
  
  public final String getContext()
  {
    return this.context;
  }
  
  public final String getReason()
  {
    return this.reason;
  }
  
  public final String getType()
  {
    return this.type;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\exception\KiwiException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */