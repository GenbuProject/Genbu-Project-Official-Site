package com.microsoft.onlineid.internal.sso;

public class SsoService
{
  public static final String SsoServiceIntent = "com.microsoft.msa.action.SSO_SERVICE";
  private final long _firstInstallTime;
  private final String _packageName;
  private final String _sdkVersion;
  private final int _ssoVersion;
  
  public SsoService(String paramString1, int paramInt, String paramString2)
  {
    this._packageName = paramString1;
    this._ssoVersion = paramInt;
    this._sdkVersion = paramString2;
    this._firstInstallTime = -1L;
  }
  
  public SsoService(String paramString1, int paramInt, String paramString2, long paramLong)
  {
    this._packageName = paramString1;
    this._ssoVersion = paramInt;
    this._sdkVersion = paramString2;
    this._firstInstallTime = paramLong;
  }
  
  public long getFirstInstallTime()
  {
    return this._firstInstallTime;
  }
  
  public String getPackageName()
  {
    return this._packageName;
  }
  
  public String getSdkVersion()
  {
    return this._sdkVersion;
  }
  
  public int getSsoVersion()
  {
    return this._ssoVersion;
  }
  
  public String toString()
  {
    return "[" + this._packageName + ": sso " + this._ssoVersion + ", sdk " + this._sdkVersion + "]";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\sso\SsoService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */