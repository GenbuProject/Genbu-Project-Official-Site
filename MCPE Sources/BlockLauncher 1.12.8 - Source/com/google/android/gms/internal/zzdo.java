package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.overlay.zzk;
import com.google.android.gms.ads.internal.util.client.zza;
import java.util.Map;
import java.util.WeakHashMap;
import org.json.JSONObject;

@zzhb
public final class zzdo
  implements zzdf
{
  private final Map<zzjp, Integer> zzzI = new WeakHashMap();
  
  private static int zza(Context paramContext, Map<String, String> paramMap, String paramString, int paramInt)
  {
    paramMap = (String)paramMap.get(paramString);
    int i = paramInt;
    if (paramMap != null) {}
    try
    {
      i = zzn.zzcS().zzb(paramContext, Integer.parseInt(paramMap));
      return i;
    }
    catch (NumberFormatException paramContext)
    {
      zzin.zzaK("Could not parse " + paramString + " in a video GMSG: " + paramMap);
    }
    return paramInt;
  }
  
  public void zza(zzjp paramzzjp, Map<String, String> paramMap)
  {
    Object localObject2 = (String)paramMap.get("action");
    if (localObject2 == null) {
      zzin.zzaK("Action missing from video GMSG.");
    }
    int i;
    int j;
    for (;;)
    {
      return;
      if (zzin.zzQ(3))
      {
        localObject1 = new JSONObject(paramMap);
        ((JSONObject)localObject1).remove("google.afma.Notify_dt");
        zzin.zzaI("Video GMSG: " + (String)localObject2 + " " + ((JSONObject)localObject1).toString());
      }
      if ("background".equals(localObject2))
      {
        paramMap = (String)paramMap.get("color");
        if (TextUtils.isEmpty(paramMap))
        {
          zzin.zzaK("Color parameter missing from color video GMSG.");
          return;
        }
        try
        {
          i = Color.parseColor(paramMap);
          paramMap = paramzzjp.zzia();
          if (paramMap != null)
          {
            paramMap = paramMap.zzhM();
            if (paramMap != null)
            {
              paramMap.setBackgroundColor(i);
              return;
            }
          }
        }
        catch (IllegalArgumentException paramzzjp)
        {
          zzin.zzaK("Invalid color parameter in video GMSG.");
          return;
        }
        this.zzzI.put(paramzzjp, Integer.valueOf(i));
        return;
      }
      localObject1 = paramzzjp.zzia();
      if (localObject1 == null)
      {
        zzin.zzaK("Could not get underlay container for a video GMSG.");
        return;
      }
      boolean bool1 = "new".equals(localObject2);
      boolean bool2 = "position".equals(localObject2);
      if ((bool1) || (bool2))
      {
        localObject2 = paramzzjp.getContext();
        j = zza((Context)localObject2, paramMap, "x", 0);
        int k = zza((Context)localObject2, paramMap, "y", 0);
        int m = zza((Context)localObject2, paramMap, "w", -1);
        int n = zza((Context)localObject2, paramMap, "h", -1);
        try
        {
          i = Integer.parseInt((String)paramMap.get("player"));
          if ((bool1) && (((zzjo)localObject1).zzhM() == null))
          {
            ((zzjo)localObject1).zza(j, k, m, n, i);
            if (!this.zzzI.containsKey(paramzzjp)) {
              continue;
            }
            i = ((Integer)this.zzzI.get(paramzzjp)).intValue();
            paramzzjp = ((zzjo)localObject1).zzhM();
            paramzzjp.setBackgroundColor(i);
            paramzzjp.zzfE();
          }
        }
        catch (NumberFormatException paramMap)
        {
          for (;;)
          {
            i = 0;
          }
          ((zzjo)localObject1).zze(j, k, m, n);
          return;
        }
      }
    }
    Object localObject1 = ((zzjo)localObject1).zzhM();
    if (localObject1 == null)
    {
      zzk.zzg(paramzzjp);
      return;
    }
    if ("click".equals(localObject2))
    {
      paramzzjp = paramzzjp.getContext();
      i = zza(paramzzjp, paramMap, "x", 0);
      j = zza(paramzzjp, paramMap, "y", 0);
      long l = SystemClock.uptimeMillis();
      paramzzjp = MotionEvent.obtain(l, l, 0, i, j, 0);
      ((zzk)localObject1).zzd(paramzzjp);
      paramzzjp.recycle();
      return;
    }
    if ("currentTime".equals(localObject2))
    {
      paramzzjp = (String)paramMap.get("time");
      if (paramzzjp == null)
      {
        zzin.zzaK("Time parameter missing from currentTime video GMSG.");
        return;
      }
      try
      {
        ((zzk)localObject1).seekTo((int)(Float.parseFloat(paramzzjp) * 1000.0F));
        return;
      }
      catch (NumberFormatException paramMap)
      {
        zzin.zzaK("Could not parse time parameter from currentTime video GMSG: " + paramzzjp);
        return;
      }
    }
    if ("hide".equals(localObject2))
    {
      ((zzk)localObject1).setVisibility(4);
      return;
    }
    if ("load".equals(localObject2))
    {
      ((zzk)localObject1).zzfD();
      return;
    }
    if ("mimetype".equals(localObject2))
    {
      ((zzk)localObject1).setMimeType((String)paramMap.get("mimetype"));
      return;
    }
    if ("muted".equals(localObject2))
    {
      if (Boolean.parseBoolean((String)paramMap.get("muted")))
      {
        ((zzk)localObject1).zzff();
        return;
      }
      ((zzk)localObject1).zzfg();
      return;
    }
    if ("pause".equals(localObject2))
    {
      ((zzk)localObject1).pause();
      return;
    }
    if ("play".equals(localObject2))
    {
      ((zzk)localObject1).play();
      return;
    }
    if ("show".equals(localObject2))
    {
      ((zzk)localObject1).setVisibility(0);
      return;
    }
    if ("src".equals(localObject2))
    {
      ((zzk)localObject1).zzap((String)paramMap.get("src"));
      return;
    }
    if ("volume".equals(localObject2))
    {
      paramzzjp = (String)paramMap.get("volume");
      if (paramzzjp == null)
      {
        zzin.zzaK("Level parameter missing from volume video GMSG.");
        return;
      }
      try
      {
        ((zzk)localObject1).zza(Float.parseFloat(paramzzjp));
        return;
      }
      catch (NumberFormatException paramMap)
      {
        zzin.zzaK("Could not parse volume parameter from volume video GMSG: " + paramzzjp);
        return;
      }
    }
    if ("watermark".equals(localObject2))
    {
      ((zzk)localObject1).zzfE();
      return;
    }
    zzin.zzaK("Unknown video action: " + (String)localObject2);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzdo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */