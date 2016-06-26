package com.microsoft.onlineid.analytics;

import com.google.android.gms.analytics.HitBuilders.TimingBuilder;
import com.google.android.gms.analytics.Tracker;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.log.Logger;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TimedAnalyticsEvent
  implements ITimedAnalyticsEvent
{
  private static final long StartTimeNotSet = -1L;
  private final HitBuilders.TimingBuilder _builder;
  private long _startTime = -1L;
  private final Tracker _tracker;
  
  TimedAnalyticsEvent(Tracker paramTracker, String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 != null) && (paramString2 != null)) {}
    for (boolean bool = true;; bool = false)
    {
      Assertion.check(bool);
      this._tracker = paramTracker;
      this._builder = new HitBuilders.TimingBuilder();
      this._builder.setCategory(paramString1);
      this._builder.setVariable(paramString2);
      if (paramString3 != null) {
        this._builder.setLabel(paramString3);
      }
      return;
    }
  }
  
  public void end()
  {
    if (this._startTime != -1L)
    {
      long l1 = System.nanoTime();
      long l2 = this._startTime;
      l1 = TimeUnit.MILLISECONDS.convert(l1 - l2, TimeUnit.NANOSECONDS);
      this._builder.setValue(l1);
      send(this._builder.build());
      return;
    }
    Logger.error("TimedAnalyticsEvent.end() called before start().");
  }
  
  void send(Map<String, String> paramMap)
  {
    this._tracker.send(paramMap);
  }
  
  public TimedAnalyticsEvent setLabel(String paramString)
  {
    this._builder.setLabel(paramString);
    return this;
  }
  
  public TimedAnalyticsEvent start()
  {
    this._startTime = System.nanoTime();
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\analytics\TimedAnalyticsEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */