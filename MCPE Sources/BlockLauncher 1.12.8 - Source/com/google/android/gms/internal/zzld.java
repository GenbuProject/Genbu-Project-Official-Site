package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyApi.ProxyResult;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzx;

public class zzld
  implements ProxyApi
{
  public PendingResult<ProxyApi.ProxyResult> performProxyRequest(GoogleApiClient paramGoogleApiClient, final ProxyRequest paramProxyRequest)
  {
    zzx.zzz(paramGoogleApiClient);
    zzx.zzz(paramProxyRequest);
    paramGoogleApiClient.zzb(new zzlc(paramGoogleApiClient)
    {
      protected void zza(Context paramAnonymousContext, zzlb paramAnonymouszzlb)
        throws RemoteException
      {
        paramAnonymouszzlb.zza(new zzky()
        {
          public void zza(ProxyResponse paramAnonymous2ProxyResponse)
          {
            zzld.1.this.zza(new zzle(paramAnonymous2ProxyResponse));
          }
        }, paramProxyRequest);
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */