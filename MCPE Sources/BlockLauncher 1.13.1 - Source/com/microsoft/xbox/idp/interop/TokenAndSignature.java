package com.microsoft.xbox.idp.interop;

public class TokenAndSignature
{
  private final long id;
  
  TokenAndSignature(long paramLong)
  {
    this.id = paramLong;
  }
  
  private static native void delete(long paramLong);
  
  private static native String getAgeGroup(long paramLong);
  
  private static native String getGamertag(long paramLong);
  
  private static native String getPriviliges(long paramLong);
  
  private static native String getReserved(long paramLong);
  
  private static native String getSignature(long paramLong);
  
  private static native String getToken(long paramLong);
  
  private static native String getUserHash(long paramLong);
  
  private static native String getWebAccountId(long paramLong);
  
  private static native String getXuid(long paramLong);
  
  protected void finalize()
    throws Throwable
  {
    delete(this.id);
    super.finalize();
  }
  
  public String getAgeGroup()
  {
    return getAgeGroup(this.id);
  }
  
  public String getGamertag()
  {
    return getGamertag(this.id);
  }
  
  public String getPriviliges()
  {
    return getPriviliges(this.id);
  }
  
  public String getReserved()
  {
    return getReserved(this.id);
  }
  
  public String getSignature()
  {
    return getSignature(this.id);
  }
  
  public String getToken()
  {
    return getToken(this.id);
  }
  
  public String getUserHash()
  {
    return getUserHash(this.id);
  }
  
  public String getWebAccountId()
  {
    return getWebAccountId(this.id);
  }
  
  public String getXuid()
  {
    return getXuid(this.id);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\interop\TokenAndSignature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */