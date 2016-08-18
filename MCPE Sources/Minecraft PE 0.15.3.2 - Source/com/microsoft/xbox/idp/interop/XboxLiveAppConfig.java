package com.microsoft.xbox.idp.interop;

public class XboxLiveAppConfig
{
  private final long id = create();
  
  private static native long create();
  
  private static native void delete(long paramLong);
  
  private static native String getEnvironment(long paramLong);
  
  private static native String getProxy(long paramLong);
  
  private static native String getSandbox(long paramLong);
  
  private static native String getScid(long paramLong);
  
  private static native int getTitleId(long paramLong);
  
  private static native void setEnvironment(long paramLong, String paramString);
  
  private static native void setProxy(long paramLong, String paramString);
  
  private static native void setSandbox(long paramLong, String paramString);
  
  protected void finalize()
    throws Throwable
  {
    super.finalize();
    delete(this.id);
  }
  
  public String getEnvironment()
  {
    return getEnvironment(this.id);
  }
  
  public String getProxy()
  {
    return getProxy(this.id);
  }
  
  public String getSandbox()
  {
    return getSandbox(this.id);
  }
  
  public String getScid()
  {
    return getScid(this.id);
  }
  
  public int getTitleId()
  {
    return getTitleId(this.id);
  }
  
  public void setEnvironment(String paramString)
  {
    setEnvironment(this.id, paramString);
  }
  
  public void setProxy(String paramString)
  {
    setProxy(this.id, paramString);
  }
  
  public void setSandbox(String paramString)
  {
    setSandbox(this.id, paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\interop\XboxLiveAppConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */