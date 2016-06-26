package com.microsoft.onlineid.interop.xbox.services;

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


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\xbox\services\EndpointsFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */