package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.measurement.zza;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

public class zzn
  extends zzz
{
  private static final X500Principal zzaWz = new X500Principal("CN=Android Debug,O=Android,C=US");
  private String zzSE;
  private String zzSF;
  private String zzaUa;
  private String zzaVd;
  private String zzaVi;
  private long zzaWA;
  
  zzn(zzw paramzzw)
  {
    super(paramzzw);
  }
  
  String zzBk()
  {
    zzjv();
    return this.zzaVd;
  }
  
  String zzBo()
  {
    zzjv();
    return this.zzaVi;
  }
  
  long zzBp()
  {
    return zzCp().zzBp();
  }
  
  long zzBq()
  {
    zzjv();
    return this.zzaWA;
  }
  
  boolean zzCD()
  {
    try
    {
      Object localObject = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 64);
      if ((localObject != null) && (((PackageInfo)localObject).signatures != null) && (((PackageInfo)localObject).signatures.length > 0))
      {
        localObject = localObject.signatures[0];
        boolean bool = ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(((Signature)localObject).toByteArray()))).getSubjectX500Principal().equals(zzaWz);
        return bool;
      }
    }
    catch (CertificateException localCertificateException)
    {
      zzAo().zzCE().zzj("Error obtaining certificate", localCertificateException);
      return true;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        zzAo().zzCE().zzj("Package name not found", localNameNotFoundException);
      }
    }
  }
  
  protected void zzba(Status paramStatus)
  {
    if (paramStatus == null)
    {
      zzAo().zzCE().zzfg("GoogleService failed to initialize (no status)");
      return;
    }
    zzAo().zzCE().zze("GoogleService failed to initialize, status", Integer.valueOf(paramStatus.getStatusCode()), paramStatus.getStatusMessage());
  }
  
  AppMetadata zzfe(String paramString)
  {
    String str1 = zzwK();
    String str2 = zzBk();
    String str3 = zzli();
    String str4 = zzBo();
    long l1 = zzBp();
    long l2 = zzBq();
    boolean bool2 = zzCo().zzAr();
    if (!zzCo().zzaXx) {}
    for (boolean bool1 = true;; bool1 = false) {
      return new AppMetadata(str1, str2, str3, str4, l1, l2, paramString, bool2, bool1);
    }
  }
  
  protected void zziJ()
  {
    String str2 = "Unknown";
    String str1 = "Unknown";
    PackageManager localPackageManager = getContext().getPackageManager();
    String str3 = getContext().getPackageName();
    Object localObject1 = localPackageManager.getInstallerPackageName(str3);
    Object localObject3;
    if (localObject1 == null) {
      localObject3 = "manual_install";
    }
    Object localObject4;
    for (;;)
    {
      localObject4 = str1;
      try
      {
        PackageInfo localPackageInfo = localPackageManager.getPackageInfo(getContext().getPackageName(), 0);
        localObject1 = str1;
        localObject4 = str2;
        if (localPackageInfo != null)
        {
          localObject4 = str1;
          CharSequence localCharSequence = localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo);
          localObject1 = str1;
          localObject4 = str1;
          if (!TextUtils.isEmpty(localCharSequence))
          {
            localObject4 = str1;
            localObject1 = localCharSequence.toString();
          }
          localObject4 = localObject1;
          str1 = localPackageInfo.versionName;
          localObject4 = str1;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1)
      {
        for (;;)
        {
          zzAo().zzCE().zzj("Error retrieving package info: appName", localObject4);
          localObject2 = localObject4;
          localObject4 = str2;
          continue;
          this.zzaWA = 0L;
          try
          {
            if (zzCD()) {
              continue;
            }
            localObject3 = localPackageManager.getPackageInfo(getContext().getPackageName(), 64);
            if ((((PackageInfo)localObject3).signatures == null) || (((PackageInfo)localObject3).signatures.length <= 0)) {
              continue;
            }
            this.zzaWA = zzaj.zzq(((MessageDigest)localObject2).digest(localObject3.signatures[0].toByteArray()));
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException2)
          {
            zzAo().zzCE().zzj("Package name not found", localNameNotFoundException2);
          }
          continue;
          localStatus = zza.zzaR(getContext());
          continue;
          int i = 0;
          continue;
          zzAo().zzCI().zzfg("AppMeasurement disabled with google_app_measurement_enable=0");
          continue;
          boolean bool = false;
        }
      }
      this.zzaUa = str3;
      this.zzaVi = ((String)localObject3);
      this.zzSF = ((String)localObject4);
      this.zzSE = ((String)localObject1);
      localObject1 = zzaj.zzbv("MD5");
      if (localObject1 != null) {
        break;
      }
      zzAo().zzCE().zzfg("Could not get MD5 instance");
      this.zzaWA = -1L;
      if (!zzCp().zzkr()) {
        break label455;
      }
      localObject1 = zza.zzb(getContext(), "-", true);
      if ((localObject1 == null) || (!((Status)localObject1).isSuccess())) {
        break label466;
      }
      i = 1;
      if (i == 0) {
        zzba((Status)localObject1);
      }
      if (i == 0) {
        break label487;
      }
      bool = zza.zzAr();
      if (!bool) {
        break label471;
      }
      zzAo().zzCK().zzfg("AppMeasurement enabled");
      this.zzaVd = "";
      if (!zzCp().zzkr()) {}
      try
      {
        localObject3 = zza.zzAp();
        localObject1 = localObject3;
        if (TextUtils.isEmpty((CharSequence)localObject3)) {
          localObject1 = "";
        }
        this.zzaVd = ((String)localObject1);
        if (bool) {
          zzAo().zzCK().zze("App package, google app id", this.zzaUa, this.zzaVd);
        }
        return;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        Object localObject2;
        Status localStatus;
        zzAo().zzCE().zzj("getGoogleAppId or isMeasurementEnabled failed with exception", localIllegalStateException);
      }
      localObject3 = localObject1;
      if ("com.android.vending".equals(localObject1)) {
        localObject3 = "";
      }
    }
    label455:
    label466:
    label471:
    label487:
    return;
  }
  
  String zzli()
  {
    zzjv();
    return this.zzSF;
  }
  
  String zzwK()
  {
    zzjv();
    return this.zzaUa;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\internal\zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */