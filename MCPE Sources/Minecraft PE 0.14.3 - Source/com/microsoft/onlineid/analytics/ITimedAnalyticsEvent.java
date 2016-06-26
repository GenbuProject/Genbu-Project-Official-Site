package com.microsoft.onlineid.analytics;

public abstract interface ITimedAnalyticsEvent
{
  public abstract void end();
  
  public abstract ITimedAnalyticsEvent setLabel(String paramString);
  
  public abstract ITimedAnalyticsEvent start();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\analytics\ITimedAnalyticsEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */