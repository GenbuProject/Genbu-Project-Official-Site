package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.zzd;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class zzi
{
  public static void zza(SignInConfiguration paramSignInConfiguration, List<zzd> paramList, Map<zzd, List<String>> paramMap)
  {
    zzx.zzz(paramSignInConfiguration);
    zzx.zzz(paramList);
    zzx.zzz(paramMap);
    paramSignInConfiguration = paramSignInConfiguration.zznm();
    if (paramSignInConfiguration != null)
    {
      paramList.add(zzd.zzXh);
      paramList = new LinkedList();
      paramSignInConfiguration = paramSignInConfiguration.zzmN().iterator();
      while (paramSignInConfiguration.hasNext()) {
        paramList.add(((Scope)paramSignInConfiguration.next()).zzpb());
      }
      paramMap.put(zzd.zzXh, paramList);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\auth\api\signin\internal\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */