package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.internal.zzaj;
import com.google.android.gms.internal.zzan;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzeo;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzif.zza;
import com.google.android.gms.internal.zzih;
import com.google.android.gms.internal.zzim;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zziq;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zzit;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzjj;
import com.google.android.gms.internal.zzmq;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzb
  extends zzim
  implements zzc.zza
{
  private final Context mContext;
  zzeo zzCf;
  private AdRequestInfoParcel zzCu;
  AdResponseParcel zzGe;
  private Runnable zzGf;
  private final Object zzGg = new Object();
  private final zza.zza zzHg;
  private final AdRequestInfoParcel.zza zzHh;
  zzit zzHi;
  private final zzan zzyt;
  
  public zzb(Context paramContext, AdRequestInfoParcel.zza paramzza, zzan paramzzan, zza.zza paramzza1)
  {
    this.zzHg = paramzza1;
    this.mContext = paramContext;
    this.zzHh = paramzza;
    this.zzyt = paramzzan;
  }
  
  private void zzc(int paramInt, String paramString)
  {
    if ((paramInt == 3) || (paramInt == -1))
    {
      zzin.zzaJ(paramString);
      if (this.zzGe != null) {
        break label93;
      }
      this.zzGe = new AdResponseParcel(paramInt);
      label33:
      if (this.zzCu == null) {
        break label115;
      }
    }
    label93:
    label115:
    for (paramString = this.zzCu;; paramString = new AdRequestInfoParcel(this.zzHh, null, -1L))
    {
      paramString = new zzif.zza(paramString, this.zzGe, this.zzCf, null, paramInt, -1L, this.zzGe.zzHX, null);
      this.zzHg.zza(paramString);
      return;
      zzin.zzaK(paramString);
      break;
      this.zzGe = new AdResponseParcel(paramInt, this.zzGe.zzBU);
      break label33;
    }
  }
  
  public void onStop()
  {
    synchronized (this.zzGg)
    {
      if (this.zzHi != null) {
        this.zzHi.cancel();
      }
      return;
    }
  }
  
  zzit zza(VersionInfoParcel paramVersionInfoParcel, zzji<AdRequestInfoParcel> paramzzji)
  {
    return zzc.zza(this.mContext, paramVersionInfoParcel, paramzzji, this);
  }
  
  protected AdSizeParcel zzb(AdRequestInfoParcel paramAdRequestInfoParcel)
    throws zzb.zza
  {
    if (this.zzGe.zzHW == null) {
      throw new zza("The ad response must specify one of the supported ad sizes.", 0);
    }
    Object localObject = this.zzGe.zzHW.split("x");
    if (localObject.length != 2) {
      throw new zza("Invalid ad size format from the ad response: " + this.zzGe.zzHW, 0);
    }
    for (;;)
    {
      int i;
      AdSizeParcel localAdSizeParcel;
      try
      {
        int m = Integer.parseInt(localObject[0]);
        int n = Integer.parseInt(localObject[1]);
        localObject = paramAdRequestInfoParcel.zzrp.zzuj;
        int i1 = localObject.length;
        i = 0;
        if (i >= i1) {
          break;
        }
        localAdSizeParcel = localObject[i];
        float f = this.mContext.getResources().getDisplayMetrics().density;
        if (localAdSizeParcel.width == -1)
        {
          j = (int)(localAdSizeParcel.widthPixels / f);
          if (localAdSizeParcel.height != -2) {
            break label253;
          }
          k = (int)(localAdSizeParcel.heightPixels / f);
          if ((m != j) || (n != k)) {
            break label263;
          }
          return new AdSizeParcel(localAdSizeParcel, paramAdRequestInfoParcel.zzrp.zzuj);
        }
      }
      catch (NumberFormatException paramAdRequestInfoParcel)
      {
        throw new zza("Invalid ad size number from the ad response: " + this.zzGe.zzHW, 0);
      }
      int j = localAdSizeParcel.width;
      continue;
      label253:
      int k = localAdSizeParcel.height;
      continue;
      label263:
      i += 1;
    }
    throw new zza("The ad size from the ad response was not one of the requested sizes: " + this.zzGe.zzHW, 0);
  }
  
  public void zzb(@NonNull AdResponseParcel arg1)
  {
    zzin.zzaI("Received ad response.");
    this.zzGe = ???;
    long l = zzr.zzbG().elapsedRealtime();
    synchronized (this.zzGg)
    {
      this.zzHi = null;
      try
      {
        if ((this.zzGe.errorCode != -2) && (this.zzGe.errorCode != -3)) {
          throw new zza("There was a problem getting an ad response. ErrorCode: " + this.zzGe.errorCode, this.zzGe.errorCode);
        }
      }
      catch (zza ???)
      {
        zzc(???.getErrorCode(), ???.getMessage());
        zzir.zzMc.removeCallbacks(this.zzGf);
        return;
      }
    }
    zzgq();
    if (this.zzCu.zzrp.zzuj != null) {}
    for (??? = zzb(this.zzCu);; ??? = null)
    {
      zzr.zzbF().zzB(this.zzGe.zzId);
      if (!TextUtils.isEmpty(this.zzGe.zzIb)) {}
      for (;;)
      {
        try
        {
          JSONObject localJSONObject = new JSONObject(this.zzGe.zzIb);
          ??? = new zzif.zza(this.zzCu, this.zzGe, this.zzCf, ???, -2, l, this.zzGe.zzHX, localJSONObject);
          this.zzHg.zza(???);
          zzir.zzMc.removeCallbacks(this.zzGf);
          return;
        }
        catch (Exception localException)
        {
          zzin.zzb("Error parsing the JSON for Active View.", localException);
        }
        Object localObject2 = null;
      }
    }
  }
  
  public void zzbr()
  {
    zzin.zzaI("AdLoaderBackgroundTask started.");
    this.zzGf = new Runnable()
    {
      public void run()
      {
        synchronized (zzb.zza(zzb.this))
        {
          if (zzb.this.zzHi == null) {
            return;
          }
          zzb.this.onStop();
          zzb.zza(zzb.this, 2, "Timed out waiting for ad response.");
          return;
        }
      }
    };
    zzir.zzMc.postDelayed(this.zzGf, ((Long)zzbt.zzwX.get()).longValue());
    final zzjj localzzjj = new zzjj();
    long l = zzr.zzbG().elapsedRealtime();
    zziq.zza(new Runnable()
    {
      public void run()
      {
        synchronized (zzb.zza(zzb.this))
        {
          zzb.this.zzHi = zzb.this.zza(zzb.zzb(zzb.this).zzrl, localzzjj);
          if (zzb.this.zzHi == null)
          {
            zzb.zza(zzb.this, 0, "Could not start the ad request service.");
            zzir.zzMc.removeCallbacks(zzb.zzc(zzb.this));
          }
          return;
        }
      }
    });
    String str = this.zzyt.zzab().zzb(this.mContext);
    this.zzCu = new AdRequestInfoParcel(this.zzHh, str, l);
    localzzjj.zzh(this.zzCu);
  }
  
  protected void zzgq()
    throws zzb.zza
  {
    if (this.zzGe.errorCode == -3) {}
    do
    {
      return;
      if (TextUtils.isEmpty(this.zzGe.body)) {
        throw new zza("No fill from ad server.", 3);
      }
      zzr.zzbF().zza(this.mContext, this.zzGe.zzHB);
    } while (!this.zzGe.zzHT);
    try
    {
      this.zzCf = new zzeo(this.zzGe.body);
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new zza("Could not parse mediation config: " + this.zzGe.body, 0);
    }
  }
  
  @zzhb
  static final class zza
    extends Exception
  {
    private final int zzGu;
    
    public zza(String paramString, int paramInt)
    {
      super();
      this.zzGu = paramInt;
    }
    
    public int getErrorCode()
    {
      return this.zzGu;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\request\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */