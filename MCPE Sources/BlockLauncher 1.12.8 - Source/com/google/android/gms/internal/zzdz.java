package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@zzhb
class zzdz
{
  private final Object[] mParams;
  private boolean zzAA;
  
  zzdz(AdRequestParcel paramAdRequestParcel, String paramString, int paramInt)
  {
    this.mParams = zza(paramAdRequestParcel, paramString, paramInt);
  }
  
  private static Object[] zza(AdRequestParcel paramAdRequestParcel, String paramString, int paramInt)
  {
    HashSet localHashSet = new HashSet(Arrays.asList(((String)zzbt.zzwF.get()).split(",")));
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString);
    if (localHashSet.contains("networkType")) {
      localArrayList.add(Integer.valueOf(paramInt));
    }
    if (localHashSet.contains("birthday")) {
      localArrayList.add(Long.valueOf(paramAdRequestParcel.zztC));
    }
    if (localHashSet.contains("extras")) {
      localArrayList.add(zzc(paramAdRequestParcel.extras));
    }
    if (localHashSet.contains("gender")) {
      localArrayList.add(Integer.valueOf(paramAdRequestParcel.zztD));
    }
    if (localHashSet.contains("keywords"))
    {
      if (paramAdRequestParcel.zztE != null) {
        localArrayList.add(paramAdRequestParcel.zztE.toString());
      }
    }
    else
    {
      if (localHashSet.contains("isTestDevice")) {
        localArrayList.add(Boolean.valueOf(paramAdRequestParcel.zztF));
      }
      if (localHashSet.contains("tagForChildDirectedTreatment")) {
        localArrayList.add(Integer.valueOf(paramAdRequestParcel.zztG));
      }
      if (localHashSet.contains("manualImpressionsEnabled")) {
        localArrayList.add(Boolean.valueOf(paramAdRequestParcel.zztH));
      }
      if (localHashSet.contains("publisherProvidedId")) {
        localArrayList.add(paramAdRequestParcel.zztI);
      }
      if (localHashSet.contains("location"))
      {
        if (paramAdRequestParcel.zztK == null) {
          break label447;
        }
        localArrayList.add(paramAdRequestParcel.zztK.toString());
      }
      label289:
      if (localHashSet.contains("contentUrl")) {
        localArrayList.add(paramAdRequestParcel.zztL);
      }
      if (localHashSet.contains("networkExtras")) {
        localArrayList.add(zzc(paramAdRequestParcel.zztM));
      }
      if (localHashSet.contains("customTargeting")) {
        localArrayList.add(zzc(paramAdRequestParcel.zztN));
      }
      if (localHashSet.contains("categoryExclusions"))
      {
        if (paramAdRequestParcel.zztO == null) {
          break label457;
        }
        localArrayList.add(paramAdRequestParcel.zztO.toString());
      }
    }
    for (;;)
    {
      if (localHashSet.contains("requestAgent")) {
        localArrayList.add(paramAdRequestParcel.zztP);
      }
      if (localHashSet.contains("requestPackage")) {
        localArrayList.add(paramAdRequestParcel.zztQ);
      }
      return localArrayList.toArray();
      localArrayList.add(null);
      break;
      label447:
      localArrayList.add(null);
      break label289;
      label457:
      localArrayList.add(null);
    }
  }
  
  private static String zzc(Bundle paramBundle)
  {
    if (paramBundle == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    Collections.sort(new ArrayList(paramBundle.keySet()));
    Iterator localIterator = paramBundle.keySet().iterator();
    if (localIterator.hasNext())
    {
      Object localObject = paramBundle.get((String)localIterator.next());
      if (localObject == null) {
        localObject = "null";
      }
      for (;;)
      {
        localStringBuilder.append((String)localObject);
        break;
        if ((localObject instanceof Bundle)) {
          localObject = zzc((Bundle)localObject);
        } else {
          localObject = localObject.toString();
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzdz)) {
      return false;
    }
    paramObject = (zzdz)paramObject;
    return Arrays.equals(this.mParams, ((zzdz)paramObject).mParams);
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(this.mParams);
  }
  
  public String toString()
  {
    return "[InterstitialAdPoolKey " + Arrays.toString(this.mParams) + "]";
  }
  
  void zzeg()
  {
    this.zzAA = true;
  }
  
  boolean zzeh()
  {
    return this.zzAA;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzdz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */