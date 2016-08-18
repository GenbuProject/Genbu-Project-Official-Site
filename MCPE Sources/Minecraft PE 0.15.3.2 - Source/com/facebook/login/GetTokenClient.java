package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.PlatformServiceClient;

final class GetTokenClient
  extends PlatformServiceClient
{
  GetTokenClient(Context paramContext, String paramString)
  {
    super(paramContext, 65536, 65537, 20121101, paramString);
  }
  
  protected void populateRequestBundle(Bundle paramBundle) {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\login\GetTokenClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */