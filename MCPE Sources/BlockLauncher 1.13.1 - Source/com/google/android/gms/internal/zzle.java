package com.google.android.gms.internal;

import com.google.android.gms.auth.api.proxy.ProxyApi.ProxyResult;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.Status;

class zzle
  implements ProxyApi.ProxyResult
{
  private Status zzUX;
  private ProxyResponse zzWK;
  
  public zzle(ProxyResponse paramProxyResponse)
  {
    this.zzWK = paramProxyResponse;
    this.zzUX = Status.zzagC;
  }
  
  public zzle(Status paramStatus)
  {
    this.zzUX = paramStatus;
  }
  
  public ProxyResponse getResponse()
  {
    return this.zzWK;
  }
  
  public Status getStatus()
  {
    return this.zzUX;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */