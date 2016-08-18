package com.microsoft.onlineid.internal.transport;

import android.content.Context;
import com.microsoft.onlineid.sts.ServerConfig;
import com.microsoft.onlineid.sts.ServerConfig.Int;

public class TransportFactory
{
  private final Context _applicationContext;
  
  public TransportFactory(Context paramContext)
  {
    this._applicationContext = paramContext;
  }
  
  protected void configureTransport(Transport paramTransport)
  {
    ServerConfig localServerConfig = getServerConfig();
    paramTransport.setConnectionTimeoutMilliseconds(localServerConfig.getInt(ServerConfig.Int.ConnectTimeout));
    paramTransport.setReadTimeoutMilliseconds(localServerConfig.getInt(ServerConfig.Int.ReceiveTimeout));
    paramTransport.appendCustomUserAgentString(Transport.buildUserAgentString(this._applicationContext));
  }
  
  public Transport createTransport()
  {
    Transport localTransport = new Transport();
    configureTransport(localTransport);
    return localTransport;
  }
  
  protected ServerConfig getServerConfig()
  {
    return new ServerConfig(this._applicationContext);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\transport\TransportFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */