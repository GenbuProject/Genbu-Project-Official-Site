package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.internal.zzch;
import com.google.android.gms.internal.zzch.zza;
import com.google.android.gms.internal.zzdf;
import com.google.android.gms.internal.zzen;
import com.google.android.gms.internal.zzes;
import com.google.android.gms.internal.zzey;
import com.google.android.gms.internal.zzfb;
import com.google.android.gms.internal.zzfc;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.internal.zzjq.zza;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzm
{
  private static zzd zza(zzfb paramzzfb)
    throws RemoteException
  {
    return new zzd(paramzzfb.getHeadline(), paramzzfb.getImages(), paramzzfb.getBody(), paramzzfb.zzdK(), paramzzfb.getCallToAction(), paramzzfb.getStarRating(), paramzzfb.getStore(), paramzzfb.getPrice(), null, paramzzfb.getExtras());
  }
  
  private static com.google.android.gms.ads.internal.formats.zze zza(zzfc paramzzfc)
    throws RemoteException
  {
    return new com.google.android.gms.ads.internal.formats.zze(paramzzfc.getHeadline(), paramzzfc.getImages(), paramzzfc.getBody(), paramzzfc.zzdO(), paramzzfc.getCallToAction(), paramzzfc.getAdvertiser(), null, paramzzfc.getExtras());
  }
  
  static zzdf zza(zzfb paramzzfb, final zzfc paramzzfc, final zzf.zza paramzza)
  {
    new zzdf()
    {
      public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
      {
        paramAnonymousMap = paramAnonymouszzjp.getView();
        if (paramAnonymousMap == null) {}
        do
        {
          return;
          try
          {
            if (this.zzqv == null) {
              continue;
            }
            if (!this.zzqv.getOverrideClickHandling())
            {
              this.zzqv.zzc(com.google.android.gms.dynamic.zze.zzC(paramAnonymousMap));
              paramzza.onClick();
              return;
            }
          }
          catch (RemoteException paramAnonymouszzjp)
          {
            zzin.zzd("Unable to call handleClick on mapper", paramAnonymouszzjp);
            return;
          }
          zzm.zzb(paramAnonymouszzjp);
          return;
        } while (paramzzfc == null);
        if (!paramzzfc.getOverrideClickHandling())
        {
          paramzzfc.zzc(com.google.android.gms.dynamic.zze.zzC(paramAnonymousMap));
          paramzza.onClick();
          return;
        }
        zzm.zzb(paramAnonymouszzjp);
      }
    };
  }
  
  static zzdf zza(CountDownLatch paramCountDownLatch)
  {
    new zzdf()
    {
      public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
      {
        this.zzqu.countDown();
        paramAnonymouszzjp = paramAnonymouszzjp.getView();
        if (paramAnonymouszzjp == null) {
          return;
        }
        paramAnonymouszzjp.setVisibility(0);
      }
    };
  }
  
  private static String zza(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    if (paramBitmap == null)
    {
      zzin.zzaK("Bitmap is null. Returning empty string");
      return "";
    }
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    paramBitmap = Base64.encodeToString(localByteArrayOutputStream.toByteArray(), 0);
    return "data:image/png;base64," + paramBitmap;
  }
  
  static String zza(zzch paramzzch)
  {
    if (paramzzch == null)
    {
      zzin.zzaK("Image is null. Returning empty string");
      return "";
    }
    try
    {
      Object localObject = paramzzch.getUri();
      if (localObject != null)
      {
        localObject = ((Uri)localObject).toString();
        return (String)localObject;
      }
    }
    catch (RemoteException localRemoteException)
    {
      zzin.zzaK("Unable to get image uri. Trying data uri next");
    }
    return zzb(paramzzch);
  }
  
  private static JSONObject zza(Bundle paramBundle, String paramString)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    if ((paramBundle == null) || (TextUtils.isEmpty(paramString))) {
      return localJSONObject;
    }
    paramString = new JSONObject(paramString);
    Iterator localIterator = paramString.keys();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (paramBundle.containsKey(str)) {
        if ("image".equals(paramString.getString(str)))
        {
          Object localObject = paramBundle.get(str);
          if ((localObject instanceof Bitmap)) {
            localJSONObject.put(str, zza((Bitmap)localObject));
          } else {
            zzin.zzaK("Invalid type. An image type extra should return a bitmap");
          }
        }
        else if ((paramBundle.get(str) instanceof Bitmap))
        {
          zzin.zzaK("Invalid asset type. Bitmap should be returned only for image type");
        }
        else
        {
          localJSONObject.put(str, String.valueOf(paramBundle.get(str)));
        }
      }
    }
    return localJSONObject;
  }
  
  public static void zza(zzif paramzzif, zzf.zza paramzza)
  {
    zzjp localzzjp;
    View localView;
    if (zzg(paramzzif))
    {
      localzzjp = paramzzif.zzED;
      localView = localzzjp.getView();
      if (localView == null) {
        zzin.zzaK("AdWebView is null");
      }
    }
    else
    {
      return;
    }
    List localList;
    try
    {
      localList = paramzzif.zzCp.zzBM;
      if ((localList == null) || (localList.isEmpty()))
      {
        zzin.zzaK("No template ids present in mediation response");
        return;
      }
    }
    catch (RemoteException paramzzif)
    {
      zzin.zzd("Error occurred while recording impression and registering for clicks", paramzzif);
      return;
    }
    zzfb localzzfb = paramzzif.zzCq.zzeF();
    paramzzif = paramzzif.zzCq.zzeG();
    if ((localList.contains("2")) && (localzzfb != null))
    {
      localzzfb.zzd(com.google.android.gms.dynamic.zze.zzC(localView));
      if (!localzzfb.getOverrideImpressionRecording()) {
        localzzfb.recordImpression();
      }
      localzzjp.zzhU().zza("/nativeExpressViewClicked", zza(localzzfb, null, paramzza));
      return;
    }
    if ((localList.contains("1")) && (paramzzif != null))
    {
      paramzzif.zzd(com.google.android.gms.dynamic.zze.zzC(localView));
      if (!paramzzif.getOverrideImpressionRecording()) {
        paramzzif.recordImpression();
      }
      localzzjp.zzhU().zza("/nativeExpressViewClicked", zza(null, paramzzif, paramzza));
      return;
    }
    zzin.zzaK("No matching template id and mapper");
  }
  
  private static void zza(zzjp paramzzjp)
  {
    View.OnClickListener localOnClickListener = paramzzjp.zzif();
    if (localOnClickListener != null) {
      localOnClickListener.onClick(paramzzjp.getView());
    }
  }
  
  private static void zza(final zzjp paramzzjp, zzd paramzzd, final String paramString)
  {
    paramzzjp.zzhU().zza(new zzjq.zza()
    {
      public void zza(zzjp paramAnonymouszzjp, boolean paramAnonymousBoolean)
      {
        try
        {
          paramAnonymouszzjp = new JSONObject();
          paramAnonymouszzjp.put("headline", this.zzqq.getHeadline());
          paramAnonymouszzjp.put("body", this.zzqq.getBody());
          paramAnonymouszzjp.put("call_to_action", this.zzqq.getCallToAction());
          paramAnonymouszzjp.put("price", this.zzqq.getPrice());
          paramAnonymouszzjp.put("star_rating", String.valueOf(this.zzqq.getStarRating()));
          paramAnonymouszzjp.put("store", this.zzqq.getStore());
          paramAnonymouszzjp.put("icon", zzm.zza(this.zzqq.zzdK()));
          localObject1 = new JSONArray();
          Object localObject2 = this.zzqq.getImages();
          if (localObject2 != null)
          {
            localObject2 = ((List)localObject2).iterator();
            while (((Iterator)localObject2).hasNext()) {
              ((JSONArray)localObject1).put(zzm.zza(zzm.zzd(((Iterator)localObject2).next())));
            }
          }
          paramAnonymouszzjp.put("images", localObject1);
        }
        catch (JSONException paramAnonymouszzjp)
        {
          zzin.zzd("Exception occurred when loading assets", paramAnonymouszzjp);
          return;
        }
        paramAnonymouszzjp.put("extras", zzm.zzb(this.zzqq.getExtras(), paramString));
        Object localObject1 = new JSONObject();
        ((JSONObject)localObject1).put("assets", paramAnonymouszzjp);
        ((JSONObject)localObject1).put("template_id", "2");
        paramzzjp.zza("google.afma.nativeExpressAds.loadAssets", (JSONObject)localObject1);
      }
    });
  }
  
  private static void zza(final zzjp paramzzjp, com.google.android.gms.ads.internal.formats.zze paramzze, final String paramString)
  {
    paramzzjp.zzhU().zza(new zzjq.zza()
    {
      public void zza(zzjp paramAnonymouszzjp, boolean paramAnonymousBoolean)
      {
        try
        {
          paramAnonymouszzjp = new JSONObject();
          paramAnonymouszzjp.put("headline", this.zzqt.getHeadline());
          paramAnonymouszzjp.put("body", this.zzqt.getBody());
          paramAnonymouszzjp.put("call_to_action", this.zzqt.getCallToAction());
          paramAnonymouszzjp.put("advertiser", this.zzqt.getAdvertiser());
          paramAnonymouszzjp.put("logo", zzm.zza(this.zzqt.zzdO()));
          localObject1 = new JSONArray();
          Object localObject2 = this.zzqt.getImages();
          if (localObject2 != null)
          {
            localObject2 = ((List)localObject2).iterator();
            while (((Iterator)localObject2).hasNext()) {
              ((JSONArray)localObject1).put(zzm.zza(zzm.zzd(((Iterator)localObject2).next())));
            }
          }
          paramAnonymouszzjp.put("images", localObject1);
        }
        catch (JSONException paramAnonymouszzjp)
        {
          zzin.zzd("Exception occurred when loading assets", paramAnonymouszzjp);
          return;
        }
        paramAnonymouszzjp.put("extras", zzm.zzb(this.zzqt.getExtras(), paramString));
        Object localObject1 = new JSONObject();
        ((JSONObject)localObject1).put("assets", paramAnonymouszzjp);
        ((JSONObject)localObject1).put("template_id", "1");
        paramzzjp.zza("google.afma.nativeExpressAds.loadAssets", (JSONObject)localObject1);
      }
    });
  }
  
  private static void zza(zzjp paramzzjp, CountDownLatch paramCountDownLatch)
  {
    paramzzjp.zzhU().zza("/nativeExpressAssetsLoaded", zza(paramCountDownLatch));
    paramzzjp.zzhU().zza("/nativeExpressAssetsLoadingFailed", zzb(paramCountDownLatch));
  }
  
  public static boolean zza(zzjp paramzzjp, zzes paramzzes, CountDownLatch paramCountDownLatch)
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = zzb(paramzzjp, paramzzes, paramCountDownLatch);
      bool1 = bool2;
    }
    catch (RemoteException paramzzjp)
    {
      for (;;)
      {
        zzin.zzd("Unable to invoke load assets", paramzzjp);
      }
    }
    catch (RuntimeException paramzzjp)
    {
      paramCountDownLatch.countDown();
      throw paramzzjp;
    }
    if (!bool1) {
      paramCountDownLatch.countDown();
    }
    return bool1;
  }
  
  static zzdf zzb(CountDownLatch paramCountDownLatch)
  {
    new zzdf()
    {
      public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
      {
        zzin.zzaK("Adapter returned an ad, but assets substitution failed");
        this.zzqu.countDown();
        paramAnonymouszzjp.destroy();
      }
    };
  }
  
  private static String zzb(zzch paramzzch)
  {
    try
    {
      paramzzch = paramzzch.zzdJ();
      if (paramzzch == null)
      {
        zzin.zzaK("Drawable is null. Returning empty string");
        return "";
      }
      paramzzch = (Drawable)com.google.android.gms.dynamic.zze.zzp(paramzzch);
      if (!(paramzzch instanceof BitmapDrawable))
      {
        zzin.zzaK("Drawable is not an instance of BitmapDrawable. Returning empty string");
        return "";
      }
    }
    catch (RemoteException paramzzch)
    {
      zzin.zzaK("Unable to get drawable. Returning empty string");
      return "";
    }
    return zza(((BitmapDrawable)paramzzch).getBitmap());
  }
  
  private static boolean zzb(zzjp paramzzjp, zzes paramzzes, CountDownLatch paramCountDownLatch)
    throws RemoteException
  {
    Object localObject = paramzzjp.getView();
    if (localObject == null)
    {
      zzin.zzaK("AdWebView is null");
      return false;
    }
    ((View)localObject).setVisibility(4);
    localObject = paramzzes.zzCp.zzBM;
    if ((localObject == null) || (((List)localObject).isEmpty()))
    {
      zzin.zzaK("No template ids present in mediation response");
      return false;
    }
    zza(paramzzjp, paramCountDownLatch);
    paramCountDownLatch = paramzzes.zzCq.zzeF();
    zzfc localzzfc = paramzzes.zzCq.zzeG();
    if ((((List)localObject).contains("2")) && (paramCountDownLatch != null))
    {
      zza(paramzzjp, zza(paramCountDownLatch), paramzzes.zzCp.zzBL);
      paramCountDownLatch = paramzzes.zzCp.zzBJ;
      paramzzes = paramzzes.zzCp.zzBK;
      if (paramzzes == null) {
        break label189;
      }
      paramzzjp.loadDataWithBaseURL(paramzzes, paramCountDownLatch, "text/html", "UTF-8", null);
    }
    for (;;)
    {
      return true;
      if ((((List)localObject).contains("1")) && (localzzfc != null))
      {
        zza(paramzzjp, zza(localzzfc), paramzzes.zzCp.zzBL);
        break;
      }
      zzin.zzaK("No matching template id and mapper");
      return false;
      label189:
      paramzzjp.loadData(paramCountDownLatch, "text/html", "UTF-8");
    }
  }
  
  private static zzch zzc(Object paramObject)
  {
    if ((paramObject instanceof IBinder)) {
      return zzch.zza.zzt((IBinder)paramObject);
    }
    return null;
  }
  
  public static View zzf(zzif paramzzif)
  {
    if (paramzzif == null)
    {
      zzin.e("AdState is null");
      return null;
    }
    if (zzg(paramzzif)) {
      return paramzzif.zzED.getView();
    }
    try
    {
      paramzzif = paramzzif.zzCq.getView();
      if (paramzzif == null)
      {
        zzin.zzaK("View in mediation adapter is null.");
        return null;
      }
      paramzzif = (View)com.google.android.gms.dynamic.zze.zzp(paramzzif);
      return paramzzif;
    }
    catch (RemoteException paramzzif)
    {
      zzin.zzd("Could not get View from mediation adapter.", paramzzif);
    }
    return null;
  }
  
  public static boolean zzg(zzif paramzzif)
  {
    return (paramzzif != null) && (paramzzif.zzHT) && (paramzzif.zzCp != null) && (paramzzif.zzCp.zzBJ != null);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */