package com.google.android.gms.ads.internal.util.client;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzne;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.StringTokenizer;

@zzhb
public class zza
{
  public static final Handler zzMS = new Handler(Looper.getMainLooper());
  private static final String zzMT = AdView.class.getName();
  private static final String zzMU = InterstitialAd.class.getName();
  private static final String zzMV = PublisherAdView.class.getName();
  private static final String zzMW = PublisherInterstitialAd.class.getName();
  private static final String zzMX = SearchAdView.class.getName();
  private static final String zzMY = AdLoader.class.getName();
  
  private void zza(ViewGroup paramViewGroup, AdSizeParcel paramAdSizeParcel, String paramString, int paramInt1, int paramInt2)
  {
    if (paramViewGroup.getChildCount() != 0) {
      return;
    }
    Context localContext = paramViewGroup.getContext();
    TextView localTextView = new TextView(localContext);
    localTextView.setGravity(17);
    localTextView.setText(paramString);
    localTextView.setTextColor(paramInt1);
    localTextView.setBackgroundColor(paramInt2);
    paramString = new FrameLayout(localContext);
    paramString.setBackgroundColor(paramInt1);
    paramInt1 = zzb(localContext, 3);
    paramString.addView(localTextView, new FrameLayout.LayoutParams(paramAdSizeParcel.widthPixels - paramInt1, paramAdSizeParcel.heightPixels - paramInt1, 17));
    paramViewGroup.addView(paramString, paramAdSizeParcel.widthPixels, paramAdSizeParcel.heightPixels);
  }
  
  public String zzT(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    if (paramContext == null) {}
    for (paramContext = null;; paramContext = Settings.Secure.getString(paramContext, "android_id"))
    {
      if ((paramContext == null) || (zzhI())) {
        paramContext = "emulator";
      }
      return zzaH(paramContext);
    }
  }
  
  public boolean zzU(Context paramContext)
  {
    return zze.isGooglePlayServicesAvailable(paramContext) == 0;
  }
  
  public boolean zzV(Context paramContext)
  {
    if (paramContext.getResources().getConfiguration().orientation != 2) {}
    do
    {
      return false;
      paramContext = paramContext.getResources().getDisplayMetrics();
    } while ((int)(paramContext.heightPixels / paramContext.density) >= 600);
    return true;
  }
  
  public boolean zzW(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    int j;
    int i;
    if (zzne.zzsi())
    {
      paramContext.getRealMetrics(localDisplayMetrics);
      j = localDisplayMetrics.heightPixels;
      i = localDisplayMetrics.widthPixels;
      paramContext.getMetrics(localDisplayMetrics);
      int k = localDisplayMetrics.heightPixels;
      int m = localDisplayMetrics.widthPixels;
      if ((k != j) || (m != i)) {
        break label141;
      }
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      label141:
      try
      {
        j = ((Integer)Display.class.getMethod("getRawHeight", new Class[0]).invoke(paramContext, new Object[0])).intValue();
        i = ((Integer)Display.class.getMethod("getRawWidth", new Class[0]).invoke(paramContext, new Object[0])).intValue();
      }
      catch (Exception paramContext) {}
    }
    return false;
  }
  
  public int zzX(Context paramContext)
  {
    int i = paramContext.getResources().getIdentifier("navigation_bar_width", "dimen", "android");
    if (i > 0) {
      return paramContext.getResources().getDimensionPixelSize(i);
    }
    return 0;
  }
  
  public int zza(DisplayMetrics paramDisplayMetrics, int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, paramDisplayMetrics);
  }
  
  @Nullable
  public String zza(StackTraceElement[] paramArrayOfStackTraceElement, String paramString)
  {
    int i = 0;
    if (i + 1 < paramArrayOfStackTraceElement.length)
    {
      StackTraceElement localStackTraceElement = paramArrayOfStackTraceElement[i];
      String str = localStackTraceElement.getClassName();
      if ((!"loadAd".equalsIgnoreCase(localStackTraceElement.getMethodName())) || ((!zzMT.equalsIgnoreCase(str)) && (!zzMU.equalsIgnoreCase(str)) && (!zzMV.equalsIgnoreCase(str)) && (!zzMW.equalsIgnoreCase(str)) && (!zzMX.equalsIgnoreCase(str)) && (!zzMY.equalsIgnoreCase(str)))) {}
    }
    for (paramArrayOfStackTraceElement = paramArrayOfStackTraceElement[(i + 1)].getClassName();; paramArrayOfStackTraceElement = null)
    {
      if (paramString != null)
      {
        paramString = zzb(paramString, ".", 3);
        if ((paramArrayOfStackTraceElement != null) && (!paramArrayOfStackTraceElement.contains(paramString)))
        {
          return paramArrayOfStackTraceElement;
          i += 1;
          break;
        }
      }
      return null;
    }
  }
  
  public void zza(ViewGroup paramViewGroup, AdSizeParcel paramAdSizeParcel, String paramString)
  {
    zza(paramViewGroup, paramAdSizeParcel, paramString, -16777216, -1);
  }
  
  public void zza(ViewGroup paramViewGroup, AdSizeParcel paramAdSizeParcel, String paramString1, String paramString2)
  {
    zzb.zzaK(paramString2);
    zza(paramViewGroup, paramAdSizeParcel, paramString1, -65536, -16777216);
  }
  
  public String zzaH(String paramString)
  {
    int i = 0;
    while (i < 2) {
      try
      {
        Object localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).update(paramString.getBytes());
        localObject = String.format(Locale.US, "%032X", new Object[] { new BigInteger(1, ((MessageDigest)localObject).digest()) });
        return (String)localObject;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        i += 1;
      }
    }
    return null;
  }
  
  public int zzb(Context paramContext, int paramInt)
  {
    return zza(paramContext.getResources().getDisplayMetrics(), paramInt);
  }
  
  public int zzb(DisplayMetrics paramDisplayMetrics, int paramInt)
  {
    return Math.round(paramInt / paramDisplayMetrics.density);
  }
  
  String zzb(String paramString1, String paramString2, int paramInt)
  {
    StringTokenizer localStringTokenizer = new StringTokenizer(paramString1, paramString2);
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramInt - 1;
    paramString2 = paramString1;
    if (paramInt > 0)
    {
      paramString2 = paramString1;
      if (localStringTokenizer.hasMoreElements())
      {
        localStringBuilder.append(localStringTokenizer.nextToken());
        paramInt = i;
        while ((paramInt > 0) && (localStringTokenizer.hasMoreElements()))
        {
          localStringBuilder.append(".").append(localStringTokenizer.nextToken());
          paramInt -= 1;
        }
        paramString2 = localStringBuilder.toString();
      }
    }
    return paramString2;
  }
  
  public int zzc(Context paramContext, int paramInt)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getMetrics(localDisplayMetrics);
    return zzb(localDisplayMetrics, paramInt);
  }
  
  public boolean zzhI()
  {
    return Build.DEVICE.startsWith("generic");
  }
  
  public boolean zzhJ()
  {
    return Looper.myLooper() == Looper.getMainLooper();
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\util\client\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */