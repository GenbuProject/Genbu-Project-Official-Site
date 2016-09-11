package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.internal.zzab;
import com.google.android.gms.analytics.internal.zzaf;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzh;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzkb;
import com.google.android.gms.internal.zzkc;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzpq;
import com.google.android.gms.internal.zzpr;
import com.google.android.gms.internal.zzps;
import com.google.android.gms.internal.zzpt;
import com.google.android.gms.internal.zzpu;
import com.google.android.gms.internal.zzpv;
import com.google.android.gms.internal.zzpw;
import com.google.android.gms.internal.zzpx;
import com.google.android.gms.internal.zzpy;
import com.google.android.gms.measurement.zzi;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzb
  extends com.google.android.gms.analytics.internal.zzc
  implements zzi
{
  private static DecimalFormat zzOU;
  private final zzf zzOK;
  private final String zzOV;
  private final Uri zzOW;
  private final boolean zzOX;
  private final boolean zzOY;
  
  public zzb(zzf paramzzf, String paramString)
  {
    this(paramzzf, paramString, true, false);
  }
  
  public zzb(zzf paramzzf, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramzzf);
    zzx.zzcM(paramString);
    this.zzOK = paramzzf;
    this.zzOV = paramString;
    this.zzOX = paramBoolean1;
    this.zzOY = paramBoolean2;
    this.zzOW = zzaU(this.zzOV);
  }
  
  private static String zzH(Map<String, String> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (localStringBuilder.length() != 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append("=");
      localStringBuilder.append((String)localEntry.getValue());
    }
    return localStringBuilder.toString();
  }
  
  private static void zza(Map<String, String> paramMap, String paramString, double paramDouble)
  {
    if (paramDouble != 0.0D) {
      paramMap.put(paramString, zzb(paramDouble));
    }
  }
  
  private static void zza(Map<String, String> paramMap, String paramString, int paramInt1, int paramInt2)
  {
    if ((paramInt1 > 0) && (paramInt2 > 0)) {
      paramMap.put(paramString, paramInt1 + "x" + paramInt2);
    }
  }
  
  private static void zza(Map<String, String> paramMap, String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramMap.put(paramString, "1");
    }
  }
  
  static Uri zzaU(String paramString)
  {
    zzx.zzcM(paramString);
    Uri.Builder localBuilder = new Uri.Builder();
    localBuilder.scheme("uri");
    localBuilder.authority("google-analytics.com");
    localBuilder.path(paramString);
    return localBuilder.build();
  }
  
  static String zzb(double paramDouble)
  {
    if (zzOU == null) {
      zzOU = new DecimalFormat("0.######");
    }
    return zzOU.format(paramDouble);
  }
  
  private static void zzb(Map<String, String> paramMap, String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString2)) {
      paramMap.put(paramString1, paramString2);
    }
  }
  
  public static Map<String, String> zzc(com.google.android.gms.measurement.zzc paramzzc)
  {
    HashMap localHashMap = new HashMap();
    Object localObject1 = (zzkd)paramzzc.zze(zzkd.class);
    Object localObject2;
    Object localObject3;
    if (localObject1 != null)
    {
      localObject1 = ((zzkd)localObject1).zziR().entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        localObject3 = zzi(((Map.Entry)localObject2).getValue());
        if (localObject3 != null) {
          localHashMap.put(((Map.Entry)localObject2).getKey(), localObject3);
        }
      }
    }
    localObject1 = (zzke)paramzzc.zze(zzke.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "t", ((zzke)localObject1).zziS());
      zzb(localHashMap, "cid", ((zzke)localObject1).getClientId());
      zzb(localHashMap, "uid", ((zzke)localObject1).getUserId());
      zzb(localHashMap, "sc", ((zzke)localObject1).zziV());
      zza(localHashMap, "sf", ((zzke)localObject1).zziX());
      zza(localHashMap, "ni", ((zzke)localObject1).zziW());
      zzb(localHashMap, "adid", ((zzke)localObject1).zziT());
      zza(localHashMap, "ate", ((zzke)localObject1).zziU());
    }
    localObject1 = (zzpw)paramzzc.zze(zzpw.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "cd", ((zzpw)localObject1).zzBc());
      zza(localHashMap, "a", ((zzpw)localObject1).zzBd());
      zzb(localHashMap, "dr", ((zzpw)localObject1).zzBe());
    }
    localObject1 = (zzpu)paramzzc.zze(zzpu.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "ec", ((zzpu)localObject1).zzAZ());
      zzb(localHashMap, "ea", ((zzpu)localObject1).getAction());
      zzb(localHashMap, "el", ((zzpu)localObject1).getLabel());
      zza(localHashMap, "ev", ((zzpu)localObject1).getValue());
    }
    localObject1 = (zzpr)paramzzc.zze(zzpr.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "cn", ((zzpr)localObject1).getName());
      zzb(localHashMap, "cs", ((zzpr)localObject1).getSource());
      zzb(localHashMap, "cm", ((zzpr)localObject1).zzAK());
      zzb(localHashMap, "ck", ((zzpr)localObject1).zzAL());
      zzb(localHashMap, "cc", ((zzpr)localObject1).getContent());
      zzb(localHashMap, "ci", ((zzpr)localObject1).getId());
      zzb(localHashMap, "anid", ((zzpr)localObject1).zzAM());
      zzb(localHashMap, "gclid", ((zzpr)localObject1).zzAN());
      zzb(localHashMap, "dclid", ((zzpr)localObject1).zzAO());
      zzb(localHashMap, "aclid", ((zzpr)localObject1).zzAP());
    }
    localObject1 = (zzpv)paramzzc.zze(zzpv.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "exd", ((zzpv)localObject1).getDescription());
      zza(localHashMap, "exf", ((zzpv)localObject1).zzBa());
    }
    localObject1 = (zzpx)paramzzc.zze(zzpx.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "sn", ((zzpx)localObject1).zzBg());
      zzb(localHashMap, "sa", ((zzpx)localObject1).getAction());
      zzb(localHashMap, "st", ((zzpx)localObject1).getTarget());
    }
    localObject1 = (zzpy)paramzzc.zze(zzpy.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "utv", ((zzpy)localObject1).zzBh());
      zza(localHashMap, "utt", ((zzpy)localObject1).getTimeInMillis());
      zzb(localHashMap, "utc", ((zzpy)localObject1).zzAZ());
      zzb(localHashMap, "utl", ((zzpy)localObject1).getLabel());
    }
    localObject1 = (zzkb)paramzzc.zze(zzkb.class);
    if (localObject1 != null)
    {
      localObject1 = ((zzkb)localObject1).zziP().entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        localObject3 = zzc.zzU(((Integer)((Map.Entry)localObject2).getKey()).intValue());
        if (!TextUtils.isEmpty((CharSequence)localObject3)) {
          localHashMap.put(localObject3, ((Map.Entry)localObject2).getValue());
        }
      }
    }
    localObject1 = (zzkc)paramzzc.zze(zzkc.class);
    if (localObject1 != null)
    {
      localObject1 = ((zzkc)localObject1).zziQ().entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        localObject3 = zzc.zzW(((Integer)((Map.Entry)localObject2).getKey()).intValue());
        if (!TextUtils.isEmpty((CharSequence)localObject3)) {
          localHashMap.put(localObject3, zzb(((Double)((Map.Entry)localObject2).getValue()).doubleValue()));
        }
      }
    }
    localObject1 = (zzpt)paramzzc.zze(zzpt.class);
    if (localObject1 != null)
    {
      localObject2 = ((zzpt)localObject1).zzAV();
      if (localObject2 != null)
      {
        localObject2 = ((ProductAction)localObject2).build().entrySet().iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (Map.Entry)((Iterator)localObject2).next();
          if (((String)((Map.Entry)localObject3).getKey()).startsWith("&")) {
            localHashMap.put(((String)((Map.Entry)localObject3).getKey()).substring(1), ((Map.Entry)localObject3).getValue());
          } else {
            localHashMap.put(((Map.Entry)localObject3).getKey(), ((Map.Entry)localObject3).getValue());
          }
        }
      }
      localObject2 = ((zzpt)localObject1).zzAY().iterator();
      int i = 1;
      while (((Iterator)localObject2).hasNext())
      {
        localHashMap.putAll(((Promotion)((Iterator)localObject2).next()).zzba(zzc.zzaa(i)));
        i += 1;
      }
      localObject2 = ((zzpt)localObject1).zzAW().iterator();
      i = 1;
      while (((Iterator)localObject2).hasNext())
      {
        localHashMap.putAll(((Product)((Iterator)localObject2).next()).zzba(zzc.zzY(i)));
        i += 1;
      }
      localObject1 = ((zzpt)localObject1).zzAX().entrySet().iterator();
      i = 1;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        Object localObject4 = (List)((Map.Entry)localObject2).getValue();
        localObject3 = zzc.zzad(i);
        localObject4 = ((List)localObject4).iterator();
        int j = 1;
        while (((Iterator)localObject4).hasNext())
        {
          localHashMap.putAll(((Product)((Iterator)localObject4).next()).zzba((String)localObject3 + zzc.zzab(j)));
          j += 1;
        }
        if (!TextUtils.isEmpty((CharSequence)((Map.Entry)localObject2).getKey())) {
          localHashMap.put((String)localObject3 + "nm", ((Map.Entry)localObject2).getKey());
        }
        i += 1;
      }
    }
    localObject1 = (zzps)paramzzc.zze(zzps.class);
    if (localObject1 != null)
    {
      zzb(localHashMap, "ul", ((zzps)localObject1).getLanguage());
      zza(localHashMap, "sd", ((zzps)localObject1).zzAQ());
      zza(localHashMap, "sr", ((zzps)localObject1).zzAR(), ((zzps)localObject1).zzAS());
      zza(localHashMap, "vp", ((zzps)localObject1).zzAT(), ((zzps)localObject1).zzAU());
    }
    paramzzc = (zzpq)paramzzc.zze(zzpq.class);
    if (paramzzc != null)
    {
      zzb(localHashMap, "an", paramzzc.zzlg());
      zzb(localHashMap, "aid", paramzzc.zzwK());
      zzb(localHashMap, "aiid", paramzzc.zzAJ());
      zzb(localHashMap, "av", paramzzc.zzli());
    }
    return localHashMap;
  }
  
  private static String zzi(Object paramObject)
  {
    if (paramObject == null) {
      paramObject = null;
    }
    String str;
    do
    {
      return (String)paramObject;
      if (!(paramObject instanceof String)) {
        break;
      }
      str = (String)paramObject;
      paramObject = str;
    } while (!TextUtils.isEmpty(str));
    return null;
    if ((paramObject instanceof Double))
    {
      paramObject = (Double)paramObject;
      if (((Double)paramObject).doubleValue() != 0.0D) {
        return zzb(((Double)paramObject).doubleValue());
      }
      return null;
    }
    if ((paramObject instanceof Boolean))
    {
      if (paramObject != Boolean.FALSE) {
        return "1";
      }
      return null;
    }
    return String.valueOf(paramObject);
  }
  
  public void zzb(com.google.android.gms.measurement.zzc paramzzc)
  {
    zzx.zzz(paramzzc);
    zzx.zzb(paramzzc.zzAz(), "Can't deliver not submitted measurement");
    zzx.zzcE("deliver should be called on worker thread");
    Object localObject2 = paramzzc.zzAu();
    Object localObject1 = (zzke)((com.google.android.gms.measurement.zzc)localObject2).zzf(zzke.class);
    if (TextUtils.isEmpty(((zzke)localObject1).zziS())) {
      zzjm().zzh(zzc((com.google.android.gms.measurement.zzc)localObject2), "Ignoring measurement without type");
    }
    do
    {
      return;
      if (TextUtils.isEmpty(((zzke)localObject1).getClientId()))
      {
        zzjm().zzh(zzc((com.google.android.gms.measurement.zzc)localObject2), "Ignoring measurement without client id");
        return;
      }
    } while (this.zzOK.zzjz().getAppOptOut());
    double d = ((zzke)localObject1).zziX();
    if (zzam.zza(d, ((zzke)localObject1).getClientId()))
    {
      zzb("Sampling enabled. Hit sampled out. sampling rate", Double.valueOf(d));
      return;
    }
    localObject2 = zzc((com.google.android.gms.measurement.zzc)localObject2);
    ((Map)localObject2).put("v", "1");
    ((Map)localObject2).put("_v", zze.zzQm);
    ((Map)localObject2).put("tid", this.zzOV);
    if (this.zzOK.zzjz().isDryRunEnabled())
    {
      zzc("Dry run is enabled. GoogleAnalytics would have sent", zzH((Map)localObject2));
      return;
    }
    HashMap localHashMap = new HashMap();
    zzam.zzc(localHashMap, "uid", ((zzke)localObject1).getUserId());
    Object localObject3 = (zzpq)paramzzc.zze(zzpq.class);
    if (localObject3 != null)
    {
      zzam.zzc(localHashMap, "an", ((zzpq)localObject3).zzlg());
      zzam.zzc(localHashMap, "aid", ((zzpq)localObject3).zzwK());
      zzam.zzc(localHashMap, "av", ((zzpq)localObject3).zzli());
      zzam.zzc(localHashMap, "aiid", ((zzpq)localObject3).zzAJ());
    }
    localObject3 = ((zzke)localObject1).getClientId();
    String str = this.zzOV;
    if (!TextUtils.isEmpty(((zzke)localObject1).zziT())) {}
    for (boolean bool = true;; bool = false)
    {
      localObject1 = new zzh(0L, (String)localObject3, str, bool, 0L, localHashMap);
      ((Map)localObject2).put("_s", String.valueOf(zziH().zza((zzh)localObject1)));
      paramzzc = new zzab(zzjm(), (Map)localObject2, paramzzc.zzAx(), true);
      zziH().zza(paramzzc);
      return;
    }
  }
  
  public Uri zziA()
  {
    return this.zzOW;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\analytics\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */