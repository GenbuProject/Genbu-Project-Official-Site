package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.internal.zzhb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zzhb
public class zzh
{
  public static final zzh zzug = new zzh();
  
  public static zzh zzcO()
  {
    return zzug;
  }
  
  public AdRequestParcel zza(Context paramContext, zzaa paramzzaa)
  {
    Object localObject1 = paramzzaa.getBirthday();
    long l;
    String str1;
    int i;
    label59:
    boolean bool1;
    int j;
    Location localLocation;
    Bundle localBundle;
    boolean bool2;
    String str2;
    if (localObject1 != null)
    {
      l = ((Date)localObject1).getTime();
      str1 = paramzzaa.getContentUrl();
      i = paramzzaa.getGender();
      localObject1 = paramzzaa.getKeywords();
      if (((Set)localObject1).isEmpty()) {
        break label231;
      }
      localObject1 = Collections.unmodifiableList(new ArrayList((Collection)localObject1));
      bool1 = paramzzaa.isTestDevice(paramContext);
      j = paramzzaa.zzdd();
      localLocation = paramzzaa.getLocation();
      localBundle = paramzzaa.getNetworkExtrasBundle(AdMobAdapter.class);
      bool2 = paramzzaa.getManualImpressionsEnabled();
      str2 = paramzzaa.getPublisherProvidedId();
      localObject2 = paramzzaa.zzda();
      if (localObject2 == null) {
        break label237;
      }
    }
    label231:
    label237:
    for (Object localObject2 = new SearchAdRequestParcel((SearchAdRequest)localObject2);; localObject2 = null)
    {
      Object localObject3 = null;
      Context localContext = paramContext.getApplicationContext();
      paramContext = (Context)localObject3;
      if (localContext != null)
      {
        paramContext = localContext.getPackageName();
        paramContext = zzn.zzcS().zza(Thread.currentThread().getStackTrace(), paramContext);
      }
      boolean bool3 = paramzzaa.isDesignedForFamilies();
      return new AdRequestParcel(7, l, localBundle, i, (List)localObject1, bool1, j, bool2, str2, (SearchAdRequestParcel)localObject2, localLocation, str1, paramzzaa.zzdc(), paramzzaa.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(paramzzaa.zzde())), paramzzaa.zzcZ(), paramContext, bool3);
      l = -1L;
      break;
      localObject1 = null;
      break label59;
    }
  }
  
  public RewardedVideoAdRequestParcel zza(Context paramContext, zzaa paramzzaa, String paramString)
  {
    return new RewardedVideoAdRequestParcel(zza(paramContext, paramzzaa), paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\client\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */