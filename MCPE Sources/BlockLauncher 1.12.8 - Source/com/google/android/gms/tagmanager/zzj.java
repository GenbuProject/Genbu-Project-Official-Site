package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag.zza;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzj
  extends zzdd
{
  private static final String ID = zzad.zzcJ.toString();
  private static final String URL = zzae.zzhZ.toString();
  private static final String zzbhF = zzae.zzdI.toString();
  private static final String zzbhG = zzae.zzhY.toString();
  static final String zzbhH = "gtm_" + ID + "_unrepeatable";
  private static final Set<String> zzbhI = new HashSet();
  private final Context mContext;
  private final zza zzbhJ;
  
  public zzj(Context paramContext)
  {
    this(paramContext, new zza()
    {
      public zzar zzFX()
      {
        return zzz.zzaX(zzj.this);
      }
    });
  }
  
  zzj(Context paramContext, zza paramzza)
  {
    super(ID, new String[] { URL });
    this.zzbhJ = paramzza;
    this.mContext = paramContext;
  }
  
  private boolean zzfL(String paramString)
  {
    boolean bool1 = true;
    for (;;)
    {
      try
      {
        boolean bool2 = zzfN(paramString);
        if (bool2) {
          return bool1;
        }
        if (zzfM(paramString)) {
          zzbhI.add(paramString);
        } else {
          bool1 = false;
        }
      }
      finally {}
    }
  }
  
  public void zzR(Map<String, zzag.zza> paramMap)
  {
    String str;
    if (paramMap.get(zzbhG) != null)
    {
      str = zzdf.zzg((zzag.zza)paramMap.get(zzbhG));
      if ((str == null) || (!zzfL(str))) {
        break label46;
      }
    }
    label46:
    do
    {
      return;
      str = null;
      break;
      Uri.Builder localBuilder = Uri.parse(zzdf.zzg((zzag.zza)paramMap.get(URL))).buildUpon();
      paramMap = (zzag.zza)paramMap.get(zzbhF);
      if (paramMap != null)
      {
        paramMap = zzdf.zzl(paramMap);
        if (!(paramMap instanceof List))
        {
          zzbg.e("ArbitraryPixel: additional params not a list: not sending partial hit: " + localBuilder.build().toString());
          return;
        }
        paramMap = ((List)paramMap).iterator();
        while (paramMap.hasNext())
        {
          Object localObject = paramMap.next();
          if (!(localObject instanceof Map))
          {
            zzbg.e("ArbitraryPixel: additional params contains non-map: not sending partial hit: " + localBuilder.build().toString());
            return;
          }
          localObject = ((Map)localObject).entrySet().iterator();
          while (((Iterator)localObject).hasNext())
          {
            Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
            localBuilder.appendQueryParameter(localEntry.getKey().toString(), localEntry.getValue().toString());
          }
        }
      }
      paramMap = localBuilder.build().toString();
      this.zzbhJ.zzFX().zzgc(paramMap);
      zzbg.v("ArbitraryPixel: url = " + paramMap);
    } while (str == null);
    try
    {
      zzbhI.add(str);
      zzcv.zzb(this.mContext, zzbhH, str, "true");
      return;
    }
    finally {}
  }
  
  boolean zzfM(String paramString)
  {
    return this.mContext.getSharedPreferences(zzbhH, 0).contains(paramString);
  }
  
  boolean zzfN(String paramString)
  {
    return zzbhI.contains(paramString);
  }
  
  public static abstract interface zza
  {
    public abstract zzar zzFX();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */