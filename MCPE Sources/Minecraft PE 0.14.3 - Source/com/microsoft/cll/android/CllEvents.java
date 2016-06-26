package com.microsoft.cll.android;

public class CllEvents
  implements ICllEvents
{
  private final ClientTelemetry clientTelemetry;
  private final Cll cll;
  private final PartA partA;
  
  public CllEvents(PartA paramPartA, ClientTelemetry paramClientTelemetry, Cll paramCll)
  {
    this.partA = paramPartA;
    this.clientTelemetry = paramClientTelemetry;
    this.cll = paramCll;
  }
  
  public void eventDropped(String paramString) {}
  
  public void sendComplete() {}
  
  public void stopped() {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\CllEvents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */