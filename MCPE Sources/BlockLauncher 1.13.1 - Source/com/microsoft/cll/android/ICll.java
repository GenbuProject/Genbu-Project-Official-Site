package com.microsoft.cll.android;

import Microsoft.Telemetry.Base;
import java.util.EnumSet;
import java.util.List;

public abstract interface ICll
{
  public abstract void SubscribeCllEvents(ICllEvents paramICllEvents);
  
  public abstract String getAppUserId();
  
  public abstract void log(Base paramBase);
  
  public abstract void log(Base paramBase, EventEnums.Latency paramLatency, EventEnums.Persistence paramPersistence, EnumSet<EventEnums.Sensitivity> paramEnumSet, double paramDouble, List<String> paramList);
  
  public abstract void log(Base paramBase, List<String> paramList);
  
  public abstract void log(String paramString1, String paramString2, EventEnums.Latency paramLatency, EventEnums.Persistence paramPersistence, EnumSet<EventEnums.Sensitivity> paramEnumSet, double paramDouble, List<String> paramList);
  
  public abstract void pause();
  
  public abstract void resume();
  
  public abstract void send();
  
  public abstract void setAppUserId(String paramString);
  
  public abstract void setDebugVerbosity(Verbosity paramVerbosity);
  
  public abstract void setEndpointUrl(String paramString);
  
  public abstract void setExperimentId(String paramString);
  
  public abstract void setXuidCallback(ITicketCallback paramITicketCallback);
  
  public abstract void start();
  
  public abstract void stop();
  
  public abstract void synchronize();
  
  public abstract void useLegacyCS(boolean paramBoolean);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\ICll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */