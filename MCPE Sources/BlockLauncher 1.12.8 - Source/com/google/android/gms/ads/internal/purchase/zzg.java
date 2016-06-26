package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.internal.zzgg.zza;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzin;

@zzhb
public final class zzg
  extends zzgg.zza
  implements ServiceConnection
{
  private Context mContext;
  zzb zzFC;
  private String zzFI;
  private zzf zzFM;
  private boolean zzFS = false;
  private int zzFT;
  private Intent zzFU;
  
  public zzg(Context paramContext, String paramString, boolean paramBoolean, int paramInt, Intent paramIntent, zzf paramzzf)
  {
    this.zzFI = paramString;
    this.zzFT = paramInt;
    this.zzFU = paramIntent;
    this.zzFS = paramBoolean;
    this.mContext = paramContext;
    this.zzFM = paramzzf;
  }
  
  public void finishPurchase()
  {
    int i = zzr.zzbM().zzd(this.zzFU);
    if ((this.zzFT != -1) || (i != 0)) {
      return;
    }
    this.zzFC = new zzb(this.mContext);
    Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
    localIntent.setPackage("com.android.vending");
    com.google.android.gms.common.stats.zzb.zzrP().zza(this.mContext, localIntent, this, 1);
  }
  
  public String getProductId()
  {
    return this.zzFI;
  }
  
  public Intent getPurchaseData()
  {
    return this.zzFU;
  }
  
  public int getResultCode()
  {
    return this.zzFT;
  }
  
  public boolean isVerified()
  {
    return this.zzFS;
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    zzin.zzaJ("In-app billing service connected.");
    this.zzFC.zzN(paramIBinder);
    paramComponentName = zzr.zzbM().zze(this.zzFU);
    paramComponentName = zzr.zzbM().zzar(paramComponentName);
    if (paramComponentName == null) {
      return;
    }
    if (this.zzFC.zzh(this.mContext.getPackageName(), paramComponentName) == 0) {
      zzh.zzy(this.mContext).zza(this.zzFM);
    }
    com.google.android.gms.common.stats.zzb.zzrP().zza(this.mContext, this);
    this.zzFC.destroy();
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    zzin.zzaJ("In-app billing service disconnected.");
    this.zzFC.destroy();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\purchase\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */