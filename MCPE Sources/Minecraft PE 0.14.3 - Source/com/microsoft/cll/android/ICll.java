package com.microsoft.cll.android;

import com.microsoft.telemetry.Base;
import java.util.List;
import java.util.Map;

public abstract interface ICll
{
  public abstract void SubscribeCllEvents(ICllEvents paramICllEvents);
  
  public abstract String getAppUserId();
  
  public abstract void log(PreSerializedEvent paramPreSerializedEvent, List<String> paramList, EventSensitivity... paramVarArgs);
  
  public abstract void log(Base paramBase, List<String> paramList, EventSensitivity... paramVarArgs);
  
  public abstract void log(Base paramBase, Map<String, String> paramMap);
  
  public abstract void log(Base paramBase, EventSensitivity... paramVarArgs);
  
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\ICll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */