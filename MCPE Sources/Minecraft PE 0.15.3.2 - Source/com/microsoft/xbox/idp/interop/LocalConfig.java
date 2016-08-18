package com.microsoft.xbox.idp.interop;

public final class LocalConfig
{
  private final long id = create();
  
  private static native long create();
  
  private static native void delete(long paramLong);
  
  private static native String getCid(long paramLong);
  
  protected void finalize()
    throws Throwable
  {
    delete(this.id);
    super.finalize();
  }
  
  public String getCid()
  {
    return getCid(this.id);
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\interop\LocalConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */