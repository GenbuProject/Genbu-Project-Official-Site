package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzax;
import com.google.android.gms.internal.zzcb;
import com.google.android.gms.internal.zzcc;
import com.google.android.gms.internal.zzce;
import com.google.android.gms.internal.zzcf;
import com.google.android.gms.internal.zzdf;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzif.zza;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziu;
import com.google.android.gms.internal.zzjp;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.internal.zzjr;
import java.util.Map;

@zzhb
public abstract class zzc
  extends zzb
  implements zzg, zzft
{
  public zzc(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzex paramzzex, VersionInfoParcel paramVersionInfoParcel, zzd paramzzd)
  {
    super(paramContext, paramAdSizeParcel, paramString, paramzzex, paramVersionInfoParcel, paramzzd);
  }
  
  protected zzjp zza(zzif.zza paramzza, zze paramzze)
  {
    Object localObject = this.zzpj.zzrm.getNextView();
    if ((localObject instanceof zzjp))
    {
      zzin.zzaI("Reusing webview...");
      localObject = (zzjp)localObject;
      ((zzjp)localObject).zza(this.zzpj.context, this.zzpj.zzrp, this.zzpe);
    }
    for (;;)
    {
      ((zzjp)localObject).zzhU().zzb(this, this, this, this, false, this, null, paramzze, this);
      zza((zzeh)localObject);
      ((zzjp)localObject).zzaM(paramzza.zzLd.zzHI);
      return (zzjp)localObject;
      if (localObject != null) {
        this.zzpj.zzrm.removeView((View)localObject);
      }
      localObject = zzr.zzbD().zza(this.zzpj.context, this.zzpj.zzrp, false, false, this.zzpj.zzrk, this.zzpj.zzrl, this.zzpe, this.zzpm);
      if (this.zzpj.zzrp.zzuj == null) {
        zzb(((zzjp)localObject).getView());
      }
    }
  }
  
  public void zza(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    zzaS();
  }
  
  public void zza(zzcf paramzzcf)
  {
    zzx.zzcD("setOnCustomRenderedAdLoadedListener must be called on the main UI thread.");
    this.zzpj.zzrE = paramzzcf;
  }
  
  protected void zza(zzeh paramzzeh)
  {
    paramzzeh.zza("/trackActiveViewUnit", new zzdf()
    {
      public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
      {
        if (zzc.this.zzpj.zzrq != null)
        {
          zzc.this.zzpl.zza(zzc.this.zzpj.zzrp, zzc.this.zzpj.zzrq, paramAnonymouszzjp.getView(), paramAnonymouszzjp);
          return;
        }
        zzin.zzaK("Request to enable ActiveView before adState is available.");
      }
    });
  }
  
  protected void zza(final zzif.zza paramzza, final zzcb paramzzcb)
  {
    if (paramzza.errorCode != -2)
    {
      zzir.zzMc.post(new Runnable()
      {
        public void run()
        {
          zzc.this.zzb(new zzif(paramzza, null, null, null, null, null, null));
        }
      });
      return;
    }
    if (paramzza.zzrp != null) {
      this.zzpj.zzrp = paramzza.zzrp;
    }
    if ((paramzza.zzLe.zzHT) && (!paramzza.zzLe.zzum))
    {
      this.zzpj.zzrL = 0;
      this.zzpj.zzro = zzr.zzbB().zza(this.zzpj.context, this, paramzza, this.zzpj.zzrk, null, this.zzpn, this, paramzzcb);
      return;
    }
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        if ((paramzza.zzLe.zzIc) && (zzc.this.zzpj.zzrE != null))
        {
          Object localObject = null;
          if (paramzza.zzLe.zzEF != null) {
            localObject = zzr.zzbC().zzaC(paramzza.zzLe.zzEF);
          }
          localObject = new zzcc(zzc.this, (String)localObject, paramzza.zzLe.body);
          zzc.this.zzpj.zzrL = 1;
          try
          {
            zzc.this.zzph = false;
            zzc.this.zzpj.zzrE.zza((zzce)localObject);
            return;
          }
          catch (RemoteException localRemoteException)
          {
            zzin.zzd("Could not call the onCustomRenderedAdLoadedListener.", localRemoteException);
            zzc.this.zzph = true;
          }
        }
        final zze localzze = new zze();
        zzjp localzzjp = zzc.this.zza(paramzza, localzze);
        localzze.zza(new zze.zzb(paramzza, localzzjp));
        localzzjp.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymous2View, MotionEvent paramAnonymous2MotionEvent)
          {
            localzze.recordClick();
            return false;
          }
        });
        localzzjp.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            localzze.recordClick();
          }
        });
        zzc.this.zzpj.zzrL = 0;
        zzc.this.zzpj.zzro = zzr.zzbB().zza(zzc.this.zzpj.context, zzc.this, paramzza, zzc.this.zzpj.zzrk, localzzjp, zzc.this.zzpn, zzc.this, paramzzcb);
      }
    });
  }
  
  protected boolean zza(zzif paramzzif1, zzif paramzzif2)
  {
    if ((this.zzpj.zzbW()) && (this.zzpj.zzrm != null)) {
      this.zzpj.zzrm.zzcc().zzaF(paramzzif2.zzHY);
    }
    return super.zza(paramzzif1, paramzzif2);
  }
  
  public void zzbd()
  {
    onAdClicked();
  }
  
  public void zzbe()
  {
    recordImpression();
    zzaP();
  }
  
  public void zzbf()
  {
    zzaQ();
  }
  
  public void zzc(View paramView)
  {
    this.zzpj.zzrK = paramView;
    zzb(new zzif(this.zzpj.zzrr, null, null, null, null, null, null));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */