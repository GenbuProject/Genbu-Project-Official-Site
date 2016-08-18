package com.microsoft.xbox.idp.services;

public class EndpointsFactory
{
  public static Endpoints get()
  {
    switch (Config.endpointType)
    {
    default: 
      return null;
    case ???: 
      return new EndpointsProd();
    }
    return new EndpointsDnet();
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\services\EndpointsFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */