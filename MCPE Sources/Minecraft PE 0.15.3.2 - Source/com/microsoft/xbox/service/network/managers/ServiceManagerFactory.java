package com.microsoft.xbox.service.network.managers;

import com.microsoft.xbox.service.network.managers.xblshared.ISLSServiceManager;
import com.microsoft.xbox.service.network.managers.xblshared.SLSXsapiServiceManager;

public class ServiceManagerFactory
{
  private static ServiceManagerFactory instance = new ServiceManagerFactory();
  private ISLSServiceManager slsServiceManager;
  
  public static ServiceManagerFactory getInstance()
  {
    return instance;
  }
  
  public ISLSServiceManager getSLSServiceManager()
  {
    if (this.slsServiceManager == null) {
      this.slsServiceManager = new SLSXsapiServiceManager();
    }
    return this.slsServiceManager;
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\network\managers\ServiceManagerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */