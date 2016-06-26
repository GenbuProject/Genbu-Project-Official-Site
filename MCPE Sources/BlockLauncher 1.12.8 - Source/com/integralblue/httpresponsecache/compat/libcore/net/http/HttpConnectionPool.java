package com.integralblue.httpresponsecache.compat.libcore.net.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

final class HttpConnectionPool
{
  public static final HttpConnectionPool INSTANCE = new HttpConnectionPool();
  private final HashMap<HttpConnection.Address, List<HttpConnection>> connectionPool = new HashMap();
  private final int maxConnections;
  
  private HttpConnectionPool()
  {
    String str = System.getProperty("http.keepAlive");
    if ((str != null) && (!Boolean.parseBoolean(str)))
    {
      this.maxConnections = 0;
      return;
    }
    str = System.getProperty("http.maxConnections");
    if (str != null) {}
    for (int i = Integer.parseInt(str);; i = 5)
    {
      this.maxConnections = i;
      return;
    }
  }
  
  public HttpConnection get(HttpConnection.Address paramAddress, int paramInt)
    throws IOException
  {
    synchronized (this.connectionPool)
    {
      Object localObject1 = (List)this.connectionPool.get(paramAddress);
      while (localObject1 != null)
      {
        HttpConnection localHttpConnection = (HttpConnection)((List)localObject1).remove(((List)localObject1).size() - 1);
        Object localObject2 = localObject1;
        if (((List)localObject1).isEmpty())
        {
          this.connectionPool.remove(paramAddress);
          localObject2 = null;
        }
        localObject1 = localObject2;
        if (localHttpConnection.isEligibleForRecycling()) {
          return localHttpConnection;
        }
      }
      return paramAddress.connect(paramInt);
    }
  }
  
  public void recycle(HttpConnection paramHttpConnection)
  {
    HttpConnection.Address localAddress;
    if ((this.maxConnections > 0) && (paramHttpConnection.isEligibleForRecycling())) {
      localAddress = paramHttpConnection.getAddress();
    }
    synchronized (this.connectionPool)
    {
      List localList = (List)this.connectionPool.get(localAddress);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList();
        this.connectionPool.put(localAddress, localObject);
      }
      if (((List)localObject).size() < this.maxConnections)
      {
        paramHttpConnection.setRecycled();
        ((List)localObject).add(paramHttpConnection);
        return;
      }
      paramHttpConnection.closeSocketAndStreams();
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\integralblue\httpresponsecache\compat\libcore\net\http\HttpConnectionPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */