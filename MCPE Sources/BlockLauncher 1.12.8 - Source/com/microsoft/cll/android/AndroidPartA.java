package com.microsoft.cll.android;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.microsoft.telemetry.extensions.device;
import com.microsoft.telemetry.extensions.os;
import com.microsoft.telemetry.extensions.user;
import java.util.Locale;

public class AndroidPartA
  extends PartA
{
  private final String DeviceTypePC = "Android.PC";
  private final String DeviceTypePhone = "Android.Phone";
  protected final String TAG = "AndroidPartA";
  protected Context appContext;
  
  public AndroidPartA(ILogger paramILogger, String paramString, Context paramContext, CorrelationVector paramCorrelationVector)
  {
    super(paramILogger, paramString, paramCorrelationVector);
    this.appContext = paramContext;
    PopulateConstantValues();
  }
  
  @TargetApi(14)
  private boolean testRadioVersion()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Build.VERSION.SDK_INT >= 14)
    {
      bool1 = bool2;
      if (Build.getRadioVersion() != null) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  protected void PopulateConstantValues()
  {
    setDeviceInfo();
    setUserId();
    setAppInfo();
    setOs();
  }
  
  double getDeviceScreenSize(int paramInt1, int paramInt2, int paramInt3)
  {
    double d1 = paramInt2 / paramInt3;
    double d2 = paramInt1 / paramInt3;
    return Math.sqrt(Math.pow(d1, 2.0D) + Math.pow(d2, 2.0D));
  }
  
  protected void setAppInfo()
  {
    Object localObject = this.appContext.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(this.appContext.getPackageName(), 0);
      this.appVer = ((PackageInfo)localObject).versionName;
      this.appId = ("A:" + ((PackageInfo)localObject).packageName);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      this.logger.error("AndroidPartA", "Could not get package name");
    }
  }
  
  @SuppressLint({"MissingPermission"})
  protected void setDeviceInfo()
  {
    this.deviceExt.setLocalId("");
    try
    {
      if ((this.appContext != null) && (this.uniqueId == null))
      {
        this.uniqueId = Settings.Secure.getString(this.appContext.getContentResolver(), "android_id");
        if (this.uniqueId != null) {
          break label160;
        }
        this.uniqueId = ((WifiManager)this.appContext.getSystemService("wifi")).getConnectionInfo().getMacAddress().replace(":", "");
        this.deviceExt.setLocalId("m:" + this.uniqueId);
      }
      while (testRadioVersion())
      {
        this.deviceExt.setDeviceClass("Android.Phone");
        this.osVer = String.format("%s", new Object[] { Build.VERSION.RELEASE });
        this.osExt.setLocale(Locale.getDefault().toString().replaceAll("_", "-"));
        return;
        label160:
        this.deviceExt.setLocalId("a:" + this.uniqueId);
      }
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        this.logger.info("AndroidPartA", "Access Wifi State permission was not Provided. DeviceID will be blank");
        continue;
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((WindowManager)this.appContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
        int i = localDisplayMetrics.widthPixels;
        if (getDeviceScreenSize(localDisplayMetrics.heightPixels, i, localDisplayMetrics.densityDpi) >= 8.0D) {
          this.deviceExt.setDeviceClass("Android.PC");
        } else {
          this.deviceExt.setDeviceClass("Android.Phone");
        }
      }
    }
  }
  
  protected void setOs()
  {
    this.osName = "Android";
  }
  
  @SuppressLint({"MissingPermission"})
  protected void setUserId()
  {
    if (this.appContext != null) {
      try
      {
        Object localObject = AccountManager.get(this.appContext).getAccountsByType("com.google");
        if (localObject.length > 0)
        {
          localObject = HashStringSha256(localObject[0].name);
          this.userExt.setLocalId("g:" + (String)localObject);
          return;
        }
      }
      catch (SecurityException localSecurityException)
      {
        this.logger.info("AndroidPartA", "Get_Accounts permission was not provided. UserID will be blank");
      }
    }
    this.userExt.setLocalId("");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\cll\android\AndroidPartA.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */