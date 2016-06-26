package com.microsoft.onlineid.interop.xbox.model;

public class GamerTag
{
  public static class Request
  {
    public String gamertag;
    public boolean preview;
    public String reservationId;
  }
  
  public static class ReservationRequest
  {
    public String Gamertag;
    public String ReservationId;
    
    public ReservationRequest() {}
    
    public ReservationRequest(String paramString1, String paramString2)
    {
      this.Gamertag = paramString1;
      this.ReservationId = paramString2;
    }
  }
  
  public static class Response
  {
    public boolean hasFree;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\interop\xbox\model\GamerTag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */