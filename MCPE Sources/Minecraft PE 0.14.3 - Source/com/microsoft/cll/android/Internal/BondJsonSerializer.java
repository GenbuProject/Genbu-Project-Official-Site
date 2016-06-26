package com.microsoft.cll.android.Internal;

import com.microsoft.bond.BondSerializable;
import com.microsoft.bond.ProtocolWriter;
import com.microsoft.cll.android.ILogger;
import java.io.IOException;

public class BondJsonSerializer
{
  private final String TAG = "EventSerializer";
  private final ILogger logger;
  private final StringBuilder resultString = new StringBuilder();
  private final ProtocolWriter writer = new JsonProtocol(this.resultString);
  
  public BondJsonSerializer(ILogger paramILogger)
  {
    this.logger = paramILogger;
  }
  
  public String serialize(BondSerializable paramBondSerializable)
  {
    try
    {
      paramBondSerializable.write(this.writer);
      paramBondSerializable = this.writer.toString();
      this.resultString.setLength(0);
      return paramBondSerializable;
    }
    catch (IOException paramBondSerializable)
    {
      for (;;)
      {
        this.logger.error("EventSerializer", "IOException when serializing");
      }
    }
    finally {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\Internal\BondJsonSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */