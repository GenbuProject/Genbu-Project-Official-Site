package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.zze;
import java.util.Locale;

@zzhb
public final class zzhj
{
  public final int zzHD;
  public final int zzHE;
  public final float zzHF;
  public final int zzJQ;
  public final boolean zzJR;
  public final boolean zzJS;
  public final String zzJT;
  public final String zzJU;
  public final boolean zzJV;
  public final boolean zzJW;
  public final boolean zzJX;
  public final boolean zzJY;
  public final String zzJZ;
  public final String zzKa;
  public final int zzKb;
  public final int zzKc;
  public final int zzKd;
  public final int zzKe;
  public final int zzKf;
  public final int zzKg;
  public final double zzKh;
  public final boolean zzKi;
  public final boolean zzKj;
  public final int zzKk;
  public final String zzKl;
  
  zzhj(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, String paramString3, String paramString4, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat, int paramInt8, int paramInt9, double paramDouble, boolean paramBoolean7, boolean paramBoolean8, int paramInt10, String paramString5)
  {
    this.zzJQ = paramInt1;
    this.zzJR = paramBoolean1;
    this.zzJS = paramBoolean2;
    this.zzJT = paramString1;
    this.zzJU = paramString2;
    this.zzJV = paramBoolean3;
    this.zzJW = paramBoolean4;
    this.zzJX = paramBoolean5;
    this.zzJY = paramBoolean6;
    this.zzJZ = paramString3;
    this.zzKa = paramString4;
    this.zzKb = paramInt2;
    this.zzKc = paramInt3;
    this.zzKd = paramInt4;
    this.zzKe = paramInt5;
    this.zzKf = paramInt6;
    this.zzKg = paramInt7;
    this.zzHF = paramFloat;
    this.zzHD = paramInt8;
    this.zzHE = paramInt9;
    this.zzKh = paramDouble;
    this.zzKi = paramBoolean7;
    this.zzKj = paramBoolean8;
    this.zzKk = paramInt10;
    this.zzKl = paramString5;
  }
  
  public static final class zza
  {
    private int zzHD;
    private int zzHE;
    private float zzHF;
    private int zzJQ;
    private boolean zzJR;
    private boolean zzJS;
    private String zzJT;
    private String zzJU;
    private boolean zzJV;
    private boolean zzJW;
    private boolean zzJX;
    private boolean zzJY;
    private String zzJZ;
    private String zzKa;
    private int zzKb;
    private int zzKc;
    private int zzKd;
    private int zzKe;
    private int zzKf;
    private int zzKg;
    private double zzKh;
    private boolean zzKi;
    private boolean zzKj;
    private int zzKk;
    private String zzKl;
    
    public zza(Context paramContext)
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      zzB(paramContext);
      zza(paramContext, localPackageManager);
      zzC(paramContext);
      Locale localLocale = Locale.getDefault();
      boolean bool1;
      if (zza(localPackageManager, "geo:0,0?q=donuts") != null)
      {
        bool1 = true;
        this.zzJR = bool1;
        if (zza(localPackageManager, "http://www.google.com") == null) {
          break label128;
        }
        bool1 = bool2;
        label63:
        this.zzJS = bool1;
        this.zzJU = localLocale.getCountry();
        this.zzJV = zzn.zzcS().zzhI();
        this.zzJW = zze.zzap(paramContext);
        this.zzJZ = localLocale.getLanguage();
        this.zzKa = zza(localPackageManager);
        paramContext = paramContext.getResources();
        if (paramContext != null) {
          break label133;
        }
      }
      label128:
      label133:
      do
      {
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label63;
        paramContext = paramContext.getDisplayMetrics();
      } while (paramContext == null);
      this.zzHF = paramContext.density;
      this.zzHD = paramContext.widthPixels;
      this.zzHE = paramContext.heightPixels;
    }
    
    public zza(Context paramContext, zzhj paramzzhj)
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      zzB(paramContext);
      zza(paramContext, localPackageManager);
      zzC(paramContext);
      zzD(paramContext);
      this.zzJR = paramzzhj.zzJR;
      this.zzJS = paramzzhj.zzJS;
      this.zzJU = paramzzhj.zzJU;
      this.zzJV = paramzzhj.zzJV;
      this.zzJW = paramzzhj.zzJW;
      this.zzJZ = paramzzhj.zzJZ;
      this.zzKa = paramzzhj.zzKa;
      this.zzHF = paramzzhj.zzHF;
      this.zzHD = paramzzhj.zzHD;
      this.zzHE = paramzzhj.zzHE;
    }
    
    private void zzB(Context paramContext)
    {
      paramContext = (AudioManager)paramContext.getSystemService("audio");
      this.zzJQ = paramContext.getMode();
      this.zzJX = paramContext.isMusicActive();
      this.zzJY = paramContext.isSpeakerphoneOn();
      this.zzKb = paramContext.getStreamVolume(3);
      this.zzKf = paramContext.getRingerMode();
      this.zzKg = paramContext.getStreamVolume(2);
    }
    
    private void zzC(Context paramContext)
    {
      boolean bool = false;
      paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (paramContext != null)
      {
        int i = paramContext.getIntExtra("status", -1);
        int j = paramContext.getIntExtra("level", -1);
        int k = paramContext.getIntExtra("scale", -1);
        this.zzKh = (j / k);
        if ((i == 2) || (i == 5)) {
          bool = true;
        }
        this.zzKi = bool;
        return;
      }
      this.zzKh = -1.0D;
      this.zzKi = false;
    }
    
    private void zzD(Context paramContext)
    {
      this.zzKl = Build.FINGERPRINT;
    }
    
    private static ResolveInfo zza(PackageManager paramPackageManager, String paramString)
    {
      return paramPackageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)), 65536);
    }
    
    private static String zza(PackageManager paramPackageManager)
    {
      Object localObject = zza(paramPackageManager, "market://details?id=com.google.android.gms.ads");
      if (localObject == null) {}
      for (;;)
      {
        return null;
        localObject = ((ResolveInfo)localObject).activityInfo;
        if (localObject != null) {
          try
          {
            paramPackageManager = paramPackageManager.getPackageInfo(((ActivityInfo)localObject).packageName, 0);
            if (paramPackageManager != null)
            {
              paramPackageManager = paramPackageManager.versionCode + "." + ((ActivityInfo)localObject).packageName;
              return paramPackageManager;
            }
          }
          catch (PackageManager.NameNotFoundException paramPackageManager) {}
        }
      }
      return null;
    }
    
    @TargetApi(16)
    private void zza(Context paramContext, PackageManager paramPackageManager)
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
      this.zzJT = localTelephonyManager.getNetworkOperator();
      this.zzKd = localTelephonyManager.getNetworkType();
      this.zzKe = localTelephonyManager.getPhoneType();
      this.zzKc = -2;
      this.zzKj = false;
      this.zzKk = -1;
      if (zzr.zzbC().zza(paramPackageManager, paramContext.getPackageName(), "android.permission.ACCESS_NETWORK_STATE"))
      {
        paramContext = localConnectivityManager.getActiveNetworkInfo();
        if (paramContext == null) {
          break label128;
        }
        this.zzKc = paramContext.getType();
        this.zzKk = paramContext.getDetailedState().ordinal();
      }
      for (;;)
      {
        if (Build.VERSION.SDK_INT >= 16) {
          this.zzKj = localConnectivityManager.isActiveNetworkMetered();
        }
        return;
        label128:
        this.zzKc = -1;
      }
    }
    
    public zzhj zzgI()
    {
      return new zzhj(this.zzJQ, this.zzJR, this.zzJS, this.zzJT, this.zzJU, this.zzJV, this.zzJW, this.zzJX, this.zzJY, this.zzJZ, this.zzKa, this.zzKb, this.zzKc, this.zzKd, this.zzKe, this.zzKf, this.zzKg, this.zzHF, this.zzHD, this.zzHE, this.zzKh, this.zzKi, this.zzKj, this.zzKk, this.zzKl);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzhj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */