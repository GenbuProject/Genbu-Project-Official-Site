package com.microsoft.onlineid.analytics;

import java.util.Map;

public abstract interface IClientAnalytics
{
  public abstract ITimedAnalyticsEvent createTimedEvent(String paramString1, String paramString2);
  
  public abstract ITimedAnalyticsEvent createTimedEvent(String paramString1, String paramString2, String paramString3);
  
  public abstract IClientAnalytics logCertificates(Map<String, byte[]> paramMap);
  
  public abstract IClientAnalytics logClockSkew(long paramLong);
  
  public abstract IClientAnalytics logEvent(String paramString1, String paramString2);
  
  public abstract IClientAnalytics logEvent(String paramString1, String paramString2, String paramString3);
  
  public abstract IClientAnalytics logEvent(String paramString1, String paramString2, String paramString3, Long paramLong);
  
  public abstract IClientAnalytics logException(Throwable paramThrowable);
  
  public abstract IClientAnalytics logScreenView(String paramString);
  
  public abstract IClientAnalytics logTotalAccountsEvent(String paramString, int paramInt1, int paramInt2);
  
  public abstract IClientAnalytics send(Map<String, String> paramMap);
  
  public abstract void setTestMode();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\analytics\IClientAnalytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */