package com.microsoft.onlineid.analytics;

import java.util.Map;

public class NopClientAnalytics
  implements IClientAnalytics
{
  public ITimedAnalyticsEvent createTimedEvent(String paramString1, String paramString2)
  {
    return new NopTimedAnalyticsEvent();
  }
  
  public ITimedAnalyticsEvent createTimedEvent(String paramString1, String paramString2, String paramString3)
  {
    return new NopTimedAnalyticsEvent();
  }
  
  public IClientAnalytics logCertificates(Map<String, byte[]> paramMap)
  {
    return this;
  }
  
  public IClientAnalytics logClockSkew(long paramLong)
  {
    return this;
  }
  
  public IClientAnalytics logEvent(String paramString1, String paramString2)
  {
    return this;
  }
  
  public IClientAnalytics logEvent(String paramString1, String paramString2, String paramString3)
  {
    return this;
  }
  
  public IClientAnalytics logEvent(String paramString1, String paramString2, String paramString3, Long paramLong)
  {
    return this;
  }
  
  public IClientAnalytics logException(Throwable paramThrowable)
  {
    return this;
  }
  
  public IClientAnalytics logScreenView(String paramString)
  {
    return this;
  }
  
  public IClientAnalytics logTotalAccountsEvent(String paramString, int paramInt1, int paramInt2)
  {
    return this;
  }
  
  public IClientAnalytics send(Map<String, String> paramMap)
  {
    return this;
  }
  
  public void setTestMode() {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\analytics\NopClientAnalytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */