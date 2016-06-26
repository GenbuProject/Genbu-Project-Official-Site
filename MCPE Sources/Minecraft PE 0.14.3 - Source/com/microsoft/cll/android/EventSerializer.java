package com.microsoft.cll.android;

import com.microsoft.telemetry.IJsonSerializable;
import java.io.IOException;
import java.io.StringWriter;

public class EventSerializer
{
  private final String TAG = "EventSerializer";
  private final ILogger logger;
  
  public EventSerializer(ILogger paramILogger)
  {
    this.logger = paramILogger;
  }
  
  public String serialize(IJsonSerializable paramIJsonSerializable)
  {
    StringWriter localStringWriter = new StringWriter();
    try
    {
      paramIJsonSerializable.serialize(localStringWriter);
      paramIJsonSerializable = localStringWriter.toString() + "\r\n";
      this.logger.info("EventSerializer", paramIJsonSerializable);
      return paramIJsonSerializable;
    }
    catch (IOException paramIJsonSerializable)
    {
      for (;;)
      {
        this.logger.error("EventSerializer", "IOException when serializing");
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\EventSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */