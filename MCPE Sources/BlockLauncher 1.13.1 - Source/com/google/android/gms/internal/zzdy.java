package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzr;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@zzhb
public class zzdy
{
  private final Map<zzdz, zzea> zzAx = new HashMap();
  private final LinkedList<zzdz> zzAy = new LinkedList();
  private zzdv zzAz;
  
  private String[] zzY(String paramString)
  {
    try
    {
      String[] arrayOfString = paramString.split("\000");
      int i = 0;
      for (;;)
      {
        paramString = arrayOfString;
        if (i >= arrayOfString.length) {
          break;
        }
        arrayOfString[i] = new String(Base64.decode(arrayOfString[i], 0), "UTF-8");
        i += 1;
      }
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString = new String[0];
    }
  }
  
  private static void zza(String paramString, zzdz paramzzdz)
  {
    if (zzin.zzQ(2)) {
      zzin.v(String.format(paramString, new Object[] { paramzzdz }));
    }
  }
  
  private String zzef()
  {
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Iterator localIterator = this.zzAy.iterator();
      while (localIterator.hasNext())
      {
        localStringBuilder.append(Base64.encodeToString(((zzdz)localIterator.next()).toString().getBytes("UTF-8"), 0));
        if (localIterator.hasNext()) {
          localStringBuilder.append("\000");
        }
      }
      str = localUnsupportedEncodingException.toString();
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      return "";
    }
    String str;
    return str;
  }
  
  void flush()
  {
    while (this.zzAy.size() > 0)
    {
      zzdz localzzdz = (zzdz)this.zzAy.remove();
      zzea localzzea = (zzea)this.zzAx.get(localzzdz);
      zza("Flushing interstitial queue for %s.", localzzdz);
      while (localzzea.size() > 0) {
        localzzea.zzej().zzAD.zzbp();
      }
      this.zzAx.remove(localzzdz);
    }
  }
  
  void restore()
  {
    if (this.zzAz == null) {}
    for (;;)
    {
      return;
      Object localObject2 = this.zzAz.zzed().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
      flush();
      HashMap localHashMap = new HashMap();
      Iterator localIterator = ((SharedPreferences)localObject2).getAll().entrySet().iterator();
      label55:
      Object localObject1;
      if (localIterator.hasNext()) {
        localObject1 = (Map.Entry)localIterator.next();
      }
      try
      {
        if (((String)((Map.Entry)localObject1).getKey()).equals("PoolKeys")) {
          break label55;
        }
        Object localObject3 = new zzec((String)((Map.Entry)localObject1).getValue());
        localObject1 = new zzdz(((zzec)localObject3).zzqH, ((zzec)localObject3).zzpS, ((zzec)localObject3).zzAC);
        if (this.zzAx.containsKey(localObject1)) {
          break label55;
        }
        localObject3 = new zzea(((zzec)localObject3).zzqH, ((zzec)localObject3).zzpS, ((zzec)localObject3).zzAC);
        this.zzAx.put(localObject1, localObject3);
        localHashMap.put(((zzdz)localObject1).toString(), localObject1);
        zza("Restored interstitial queue for %s.", (zzdz)localObject1);
      }
      catch (IOException localIOException)
      {
        zzin.zzd("Malformed preferences value for InterstitialAdPool.", localIOException);
        break label55;
        String[] arrayOfString = zzY(((SharedPreferences)localObject2).getString("PoolKeys", ""));
        int j = arrayOfString.length;
        int i = 0;
        while (i < j)
        {
          localObject2 = (zzdz)localHashMap.get(arrayOfString[i]);
          if (this.zzAx.containsKey(localObject2)) {
            this.zzAy.add(localObject2);
          }
          i += 1;
        }
      }
      catch (ClassCastException localClassCastException)
      {
        for (;;) {}
      }
    }
  }
  
  void save()
  {
    if (this.zzAz == null) {
      return;
    }
    SharedPreferences.Editor localEditor = this.zzAz.zzed().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
    localEditor.clear();
    Iterator localIterator = this.zzAx.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      zzdz localzzdz = (zzdz)((Map.Entry)localObject).getKey();
      if (localzzdz.zzeh())
      {
        localObject = new zzec((zzea)((Map.Entry)localObject).getValue()).zzem();
        localEditor.putString(localzzdz.toString(), (String)localObject);
        zza("Saved interstitial queue for %s.", localzzdz);
      }
    }
    localEditor.putString("PoolKeys", zzef());
    localEditor.commit();
  }
  
  zzea.zza zza(AdRequestParcel paramAdRequestParcel, String paramString)
  {
    int i = new zzhj.zza(this.zzAz.zzed()).zzgI().zzKc;
    zzdz localzzdz = new zzdz(paramAdRequestParcel, paramString, i);
    zzea localzzea = (zzea)this.zzAx.get(localzzdz);
    if (localzzea == null)
    {
      zza("Interstitial pool created at %s.", localzzdz);
      paramAdRequestParcel = new zzea(paramAdRequestParcel, paramString, i);
      this.zzAx.put(localzzdz, paramAdRequestParcel);
    }
    for (;;)
    {
      this.zzAy.remove(localzzdz);
      this.zzAy.add(localzzdz);
      localzzdz.zzeg();
      while (this.zzAy.size() > ((Integer)zzbt.zzwG.get()).intValue())
      {
        paramString = (zzdz)this.zzAy.remove();
        localzzea = (zzea)this.zzAx.get(paramString);
        zza("Evicting interstitial queue for %s.", paramString);
        while (localzzea.size() > 0) {
          localzzea.zzej().zzAD.zzbp();
        }
        this.zzAx.remove(paramString);
      }
      while (paramAdRequestParcel.size() > 0)
      {
        paramString = paramAdRequestParcel.zzej();
        if ((paramString.zzAG) && (zzr.zzbG().currentTimeMillis() - paramString.zzAF > 1000L * ((Integer)zzbt.zzwI.get()).intValue()))
        {
          zza("Expired interstitial at %s.", localzzdz);
        }
        else
        {
          zza("Pooled interstitial returned at %s.", localzzdz);
          return paramString;
        }
      }
      return null;
      paramAdRequestParcel = localzzea;
    }
  }
  
  void zza(zzdv paramzzdv)
  {
    if (this.zzAz == null)
    {
      this.zzAz = paramzzdv;
      restore();
    }
  }
  
  void zzee()
  {
    if (this.zzAz == null) {
      return;
    }
    Iterator localIterator = this.zzAx.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      zzdz localzzdz = (zzdz)((Map.Entry)localObject).getKey();
      localObject = (zzea)((Map.Entry)localObject).getValue();
      while (((zzea)localObject).size() < ((Integer)zzbt.zzwH.get()).intValue())
      {
        zza("Pooling one interstitial for %s.", localzzdz);
        ((zzea)localObject).zzb(this.zzAz);
      }
    }
    save();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzdy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */