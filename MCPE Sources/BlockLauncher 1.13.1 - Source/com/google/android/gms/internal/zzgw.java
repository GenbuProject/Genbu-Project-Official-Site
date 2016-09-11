package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.formats.zza;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.formats.zzh.zza;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zze;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzgw
  implements Callable<zzif>
{
  private static final long zzGF = TimeUnit.SECONDS.toMillis(60L);
  private final Context mContext;
  private final zziw zzGG;
  private final zzp zzGH;
  private final zzee zzGI;
  private boolean zzGJ;
  private List<String> zzGK;
  private JSONObject zzGL;
  private final zzif.zza zzGd;
  private int zzGu;
  private final Object zzpV = new Object();
  private final zzan zzyt;
  
  public zzgw(Context paramContext, zzp paramzzp, zzee paramzzee, zziw paramzziw, zzan paramzzan, zzif.zza paramzza)
  {
    this.mContext = paramContext;
    this.zzGH = paramzzp;
    this.zzGG = paramzziw;
    this.zzGI = paramzzee;
    this.zzGd = paramzza;
    this.zzyt = paramzzan;
    this.zzGJ = false;
    this.zzGu = -2;
    this.zzGK = null;
  }
  
  private zzh.zza zza(zzed paramzzed, zza paramzza, JSONObject paramJSONObject)
    throws ExecutionException, InterruptedException, JSONException
  {
    if (zzgn()) {
      return null;
    }
    JSONObject localJSONObject = paramJSONObject.getJSONObject("tracking_urls_and_actions");
    Object localObject = zzc(localJSONObject, "impression_tracking_urls");
    if (localObject == null) {}
    for (localObject = null;; localObject = Arrays.asList((Object[])localObject))
    {
      this.zzGK = ((List)localObject);
      this.zzGL = localJSONObject.optJSONObject("active_view");
      paramzza = paramzza.zza(this, paramJSONObject);
      if (paramzza != null) {
        break;
      }
      zzin.e("Failed to retrieve ad assets.");
      return null;
    }
    paramzza.zzb(new zzi(this.mContext, this.zzGH, paramzzed, this.zzyt, paramJSONObject, paramzza, this.zzGd.zzLd.zzrl));
    return paramzza;
  }
  
  private zzif zza(zzh.zza paramzza)
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        int j = this.zzGu;
        int i = j;
        if (paramzza == null)
        {
          i = j;
          if (this.zzGu == -2) {
            i = 0;
          }
        }
        if (i != -2)
        {
          paramzza = null;
          return new zzif(this.zzGd.zzLd.zzHt, null, this.zzGd.zzLe.zzBQ, i, this.zzGd.zzLe.zzBR, this.zzGK, this.zzGd.zzLe.orientation, this.zzGd.zzLe.zzBU, this.zzGd.zzLd.zzHw, false, null, null, null, null, null, 0L, this.zzGd.zzrp, this.zzGd.zzLe.zzHS, this.zzGd.zzKY, this.zzGd.zzKZ, this.zzGd.zzLe.zzHY, this.zzGL, paramzza, null, null, null, this.zzGd.zzLe.zzIm);
        }
      }
    }
  }
  
  private zzjg<zzc> zza(JSONObject paramJSONObject, final boolean paramBoolean1, boolean paramBoolean2)
    throws JSONException
  {
    if (paramBoolean1) {}
    final double d;
    for (String str = paramJSONObject.getString("url");; str = paramJSONObject.optString("url"))
    {
      d = paramJSONObject.optDouble("scale", 1.0D);
      if (!TextUtils.isEmpty(str)) {
        break;
      }
      zza(0, paramBoolean1);
      return new zzje(null);
    }
    if (paramBoolean2) {
      return new zzje(new zzc(null, Uri.parse(str), d));
    }
    this.zzGG.zza(str, new zziw.zza()
    {
      public zzc zzg(InputStream paramAnonymousInputStream)
      {
        try
        {
          paramAnonymousInputStream = zzna.zzk(paramAnonymousInputStream);
          if (paramAnonymousInputStream == null)
          {
            zzgw.this.zza(2, paramBoolean1);
            return null;
          }
        }
        catch (IOException paramAnonymousInputStream)
        {
          for (;;)
          {
            paramAnonymousInputStream = null;
          }
          paramAnonymousInputStream = BitmapFactory.decodeByteArray(paramAnonymousInputStream, 0, paramAnonymousInputStream.length);
          if (paramAnonymousInputStream == null)
          {
            zzgw.this.zza(2, paramBoolean1);
            return null;
          }
          paramAnonymousInputStream.setDensity((int)(160.0D * d));
        }
        return new zzc(new BitmapDrawable(Resources.getSystem(), paramAnonymousInputStream), Uri.parse(this.zzDr), d);
      }
      
      public zzc zzgo()
      {
        zzgw.this.zza(2, paramBoolean1);
        return null;
      }
    });
  }
  
  private void zza(zzh.zza paramzza, zzed paramzzed)
  {
    if (!(paramzza instanceof zzf)) {
      return;
    }
    final Object localObject = (zzf)paramzza;
    paramzza = new zzb();
    localObject = new zzdf()
    {
      public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
      {
        paramAnonymouszzjp = (String)paramAnonymousMap.get("asset");
        zzgw.zza(zzgw.this, localObject, paramAnonymouszzjp);
      }
    };
    paramzza.zzHb = ((zzdf)localObject);
    paramzzed.zza("/nativeAdCustomClick", (zzdf)localObject);
  }
  
  private Integer zzb(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getJSONObject(paramString);
      int i = Color.rgb(paramJSONObject.getInt("r"), paramJSONObject.getInt("g"), paramJSONObject.getInt("b"));
      return Integer.valueOf(i);
    }
    catch (JSONException paramJSONObject) {}
    return null;
  }
  
  private JSONObject zzb(final zzed paramzzed)
    throws TimeoutException, JSONException
  {
    if (zzgn()) {
      return null;
    }
    final zzjd localzzjd = new zzjd();
    final zzb localzzb = new zzb();
    zzdf local1 = new zzdf()
    {
      public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
      {
        paramzzed.zzb("/nativeAdPreProcess", localzzb.zzHb);
        try
        {
          paramAnonymouszzjp = (String)paramAnonymousMap.get("success");
          if (!TextUtils.isEmpty(paramAnonymouszzjp))
          {
            localzzjd.zzg(new JSONObject(paramAnonymouszzjp).getJSONArray("ads").getJSONObject(0));
            return;
          }
        }
        catch (JSONException paramAnonymouszzjp)
        {
          zzin.zzb("Malformed native JSON response.", paramAnonymouszzjp);
          zzgw.this.zzF(0);
          zzx.zza(zzgw.this.zzgn(), "Unable to set the ad state error!");
          localzzjd.zzg(null);
        }
      }
    };
    localzzb.zzHb = local1;
    paramzzed.zza("/nativeAdPreProcess", local1);
    paramzzed.zza("google.afma.nativeAds.preProcessJsonGmsg", new JSONObject(this.zzGd.zzLe.body));
    return (JSONObject)localzzjd.get(zzGF, TimeUnit.MILLISECONDS);
  }
  
  private void zzb(zzcp paramzzcp, String paramString)
  {
    try
    {
      zzct localzzct = this.zzGH.zzs(paramzzcp.getCustomTemplateId());
      if (localzzct != null) {
        localzzct.zza(paramzzcp, paramString);
      }
      return;
    }
    catch (RemoteException paramzzcp)
    {
      zzin.zzd("Failed to call onCustomClick for asset " + paramString + ".", paramzzcp);
    }
  }
  
  private String[] zzc(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    paramJSONObject = paramJSONObject.optJSONArray(paramString);
    if (paramJSONObject == null) {
      return null;
    }
    paramString = new String[paramJSONObject.length()];
    int i = 0;
    while (i < paramJSONObject.length())
    {
      paramString[i] = paramJSONObject.getString(i);
      i += 1;
    }
    return paramString;
  }
  
  private static List<Drawable> zzf(List<zzc> paramList)
    throws RemoteException
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add((Drawable)zze.zzp(((zzc)paramList.next()).zzdJ()));
    }
    return localArrayList;
  }
  
  private zzed zzgm()
    throws CancellationException, ExecutionException, InterruptedException, TimeoutException
  {
    if (zzgn()) {
      return null;
    }
    String str = (String)zzbt.zzwC.get();
    if (this.zzGd.zzLe.zzEF.indexOf("https") == 0) {}
    for (Object localObject = "https:";; localObject = "http:")
    {
      localObject = (String)localObject + str;
      localObject = (zzed)this.zzGI.zza(this.mContext, this.zzGd.zzLd.zzrl, (String)localObject, this.zzyt).get(zzGF, TimeUnit.MILLISECONDS);
      ((zzed)localObject).zza(this.zzGH, this.zzGH, this.zzGH, this.zzGH, false, null, null, null, null);
      return (zzed)localObject;
    }
  }
  
  public void zzF(int paramInt)
  {
    synchronized (this.zzpV)
    {
      this.zzGJ = true;
      this.zzGu = paramInt;
      return;
    }
  }
  
  public zzjg<zzc> zza(JSONObject paramJSONObject, String paramString, boolean paramBoolean1, boolean paramBoolean2)
    throws JSONException
  {
    if (paramBoolean1) {}
    for (paramJSONObject = paramJSONObject.getJSONObject(paramString);; paramJSONObject = paramJSONObject.optJSONObject(paramString))
    {
      paramString = paramJSONObject;
      if (paramJSONObject == null) {
        paramString = new JSONObject();
      }
      return zza(paramString, paramBoolean1, paramBoolean2);
    }
  }
  
  public List<zzjg<zzc>> zza(JSONObject paramJSONObject, String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    throws JSONException
  {
    if (paramBoolean1) {}
    ArrayList localArrayList;
    for (paramJSONObject = paramJSONObject.getJSONArray(paramString);; paramJSONObject = paramJSONObject.optJSONArray(paramString))
    {
      localArrayList = new ArrayList();
      if ((paramJSONObject != null) && (paramJSONObject.length() != 0)) {
        break;
      }
      zza(0, paramBoolean1);
      return localArrayList;
    }
    if (paramBoolean3) {}
    for (int i = paramJSONObject.length();; i = 1)
    {
      int j = 0;
      while (j < i)
      {
        JSONObject localJSONObject = paramJSONObject.getJSONObject(j);
        paramString = localJSONObject;
        if (localJSONObject == null) {
          paramString = new JSONObject();
        }
        localArrayList.add(zza(paramString, paramBoolean1, paramBoolean2));
        j += 1;
      }
    }
    return localArrayList;
  }
  
  public Future<zzc> zza(JSONObject paramJSONObject, String paramString, boolean paramBoolean)
    throws JSONException
  {
    paramString = paramJSONObject.getJSONObject(paramString);
    boolean bool = paramString.optBoolean("require", true);
    paramJSONObject = paramString;
    if (paramString == null) {
      paramJSONObject = new JSONObject();
    }
    return zza(paramJSONObject, bool, paramBoolean);
  }
  
  public void zza(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean) {
      zzF(paramInt);
    }
  }
  
  protected zza zze(JSONObject paramJSONObject)
    throws JSONException, TimeoutException
  {
    if (zzgn()) {
      return null;
    }
    final String str = paramJSONObject.getString("template_id");
    boolean bool1;
    if (this.zzGd.zzLd.zzrD != null)
    {
      bool1 = this.zzGd.zzLd.zzrD.zzyA;
      if (this.zzGd.zzLd.zzrD == null) {
        break label98;
      }
    }
    label98:
    for (boolean bool2 = this.zzGd.zzLd.zzrD.zzyC;; bool2 = false)
    {
      if (!"2".equals(str)) {
        break label103;
      }
      return new zzgx(bool1, bool2);
      bool1 = false;
      break;
    }
    label103:
    if ("1".equals(str)) {
      return new zzgy(bool1, bool2);
    }
    if ("3".equals(str))
    {
      str = paramJSONObject.getString("custom_template_id");
      final zzjd localzzjd = new zzjd();
      zzir.zzMc.post(new Runnable()
      {
        public void run()
        {
          localzzjd.zzg(zzgw.zza(zzgw.this).zzbv().get(str));
        }
      });
      if (localzzjd.get(zzGF, TimeUnit.MILLISECONDS) != null) {
        return new zzgz(bool1);
      }
      zzin.e("No handler for custom template: " + paramJSONObject.getString("custom_template_id"));
    }
    for (;;)
    {
      return null;
      zzF(0);
    }
  }
  
  public zzjg<zza> zzf(JSONObject paramJSONObject)
    throws JSONException
  {
    JSONObject localJSONObject = paramJSONObject.optJSONObject("attribution");
    if (localJSONObject == null) {
      return new zzje(null);
    }
    final String str = localJSONObject.optString("text");
    final int i = localJSONObject.optInt("text_size", -1);
    final Integer localInteger1 = zzb(localJSONObject, "text_color");
    final Integer localInteger2 = zzb(localJSONObject, "bg_color");
    final int j = localJSONObject.optInt("animation_ms", 1000);
    final int k = localJSONObject.optInt("presentation_ms", 4000);
    paramJSONObject = new ArrayList();
    if (localJSONObject.optJSONArray("images") != null) {
      paramJSONObject = zza(localJSONObject, "images", false, false, true);
    }
    for (;;)
    {
      zzjf.zza(zzjf.zzl(paramJSONObject), new zzjf.zza()
      {
        public zza zzh(List<zzc> paramAnonymousList)
        {
          if (paramAnonymousList != null) {
            for (;;)
            {
              try
              {
                if (paramAnonymousList.isEmpty()) {
                  break;
                }
                String str = str;
                List localList = zzgw.zzg(paramAnonymousList);
                Integer localInteger1 = localInteger2;
                Integer localInteger2 = localInteger1;
                if (i > 0)
                {
                  paramAnonymousList = Integer.valueOf(i);
                  paramAnonymousList = new zza(str, localList, localInteger1, localInteger2, paramAnonymousList, k + j);
                }
              }
              catch (RemoteException paramAnonymousList)
              {
                zzin.zzb("Could not get attribution icon", paramAnonymousList);
                return null;
              }
              paramAnonymousList = null;
            }
          }
          paramAnonymousList = null;
          return paramAnonymousList;
        }
      });
      paramJSONObject.add(zza(localJSONObject, "image", false, false));
    }
  }
  
  public zzif zzgl()
  {
    try
    {
      Object localObject1 = zzgm();
      Object localObject2 = zzb((zzed)localObject1);
      localObject2 = zza((zzed)localObject1, zze((JSONObject)localObject2), (JSONObject)localObject2);
      zza((zzh.zza)localObject2, (zzed)localObject1);
      localObject1 = zza((zzh.zza)localObject2);
      return (zzif)localObject1;
    }
    catch (JSONException localJSONException)
    {
      zzin.zzd("Malformed native JSON response.", localJSONException);
      if (!this.zzGJ) {
        zzF(0);
      }
      return zza(null);
    }
    catch (TimeoutException localTimeoutException)
    {
      for (;;)
      {
        zzin.zzd("Timeout when loading native ad.", localTimeoutException);
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
    catch (ExecutionException localExecutionException)
    {
      for (;;) {}
    }
    catch (CancellationException localCancellationException)
    {
      for (;;) {}
    }
  }
  
  public boolean zzgn()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzGJ;
      return bool;
    }
  }
  
  public static abstract interface zza<T extends zzh.zza>
  {
    public abstract T zza(zzgw paramzzgw, JSONObject paramJSONObject)
      throws JSONException, InterruptedException, ExecutionException;
  }
  
  class zzb
  {
    public zzdf zzHb;
    
    zzb() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzgw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */