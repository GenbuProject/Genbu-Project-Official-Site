package net.zhuoweizhang.mcpelauncher;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import java.io.PrintStream;
import net.zhuoweizhang.mcpelauncher.ui.LauncherActivity;

public class LauncherAppActivity
  extends LauncherActivity
{
  private static final int MESSAGE_AD_TIMEOUT = 609;
  private static final int MESSAGE_SHOW_AD = 608;
  private boolean adError = false;
  private Handler adHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 608) {
        LauncherAppActivity.this.actuallyShowAdvertisement();
      }
      while (paramAnonymousMessage.what != 609) {
        return;
      }
      LauncherAppActivity.this.adTimedOut();
    }
  };
  private boolean hasCalledShowAdvertisement = false;
  private InterstitialAd interstitial;
  private boolean needsShowAd = false;
  private PopupWindow shadePopup;
  
  private void actuallyShowAdvertisement()
  {
    ScriptManager.nativeSetExitEnabled(true);
    if (!this.shadePopup.isShowing())
    {
      System.out.println("No longer ready to show ad.");
      this.needsShowAd = false;
      return;
    }
    if (this.interstitial.isLoaded())
    {
      this.needsShowAd = false;
      this.interstitial.show();
      this.adHandler.removeMessages(609);
      loadInterstitialAdvertisement();
      return;
    }
    if (this.adError)
    {
      this.needsShowAd = false;
      this.adHandler.removeMessages(609);
      adOver();
      return;
    }
    this.needsShowAd = true;
    this.adHandler.sendEmptyMessageDelayed(609, 5000L);
  }
  
  private void adOver()
  {
    this.needsShowAd = false;
    this.shadePopup.dismiss();
    ScriptManager.nativeSetExitEnabled(true);
  }
  
  private void adTimedOut()
  {
    adOver();
  }
  
  public void leaveGameCallback()
  {
    super.leaveGameCallback();
    this.hasCalledShowAdvertisement = false;
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        LauncherAppActivity.this.showAdvertisement();
        LauncherAppActivity.access$002(LauncherAppActivity.this, true);
      }
    });
    while (!this.hasCalledShowAdvertisement) {
      try
      {
        Thread.sleep(20L);
      }
      catch (Exception localException1) {}
    }
    try
    {
      Thread.sleep(100L);
      return;
    }
    catch (Exception localException2) {}
  }
  
  protected void loadInterstitialAdvertisement()
  {
    this.adError = false;
    this.interstitial = new InterstitialAd(this);
    this.interstitial.setAdUnitId("ca-app-pub-2652482030334356/8558350222");
    this.interstitial.setAdListener(new AdListener()
    {
      public void onAdClosed()
      {
        LauncherAppActivity.this.adOver();
      }
      
      public void onAdFailedToLoad(int paramAnonymousInt)
      {
        LauncherAppActivity.access$102(LauncherAppActivity.this, true);
        if (LauncherAppActivity.this.needsShowAd)
        {
          LauncherAppActivity.access$202(LauncherAppActivity.this, false);
          LauncherAppActivity.this.adOver();
        }
      }
      
      public void onAdLoaded()
      {
        System.out.println("Ad loaded!");
        LauncherAppActivity.access$102(LauncherAppActivity.this, false);
        if (LauncherAppActivity.this.needsShowAd) {
          LauncherAppActivity.this.actuallyShowAdvertisement();
        }
      }
    });
    AdRequest localAdRequest = AdConfiguration.buildRequest();
    this.interstitial.loadAd(localAdRequest);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    loadInterstitialAdvertisement();
    paramBundle = new TextView(this);
    paramBundle.setText("Please wait...");
    this.shadePopup = new PopupWindow(paramBundle, -1, -1);
    this.shadePopup.setBackgroundDrawable(new ColorDrawable(-16777216));
  }
  
  public void onPrepareDialog(int paramInt, Dialog paramDialog)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      super.onPrepareDialog(paramInt, paramDialog);
      return;
      prepareRuntimeOptionsDialog(paramDialog);
    }
  }
  
  protected void prepareRuntimeOptionsDialog(Dialog paramDialog)
  {
    FrameLayout localFrameLayout = (FrameLayout)paramDialog.findViewById(16908331);
    localFrameLayout.setVisibility(0);
    paramDialog = localFrameLayout.getParent();
    if (paramDialog != null) {
      ((View)paramDialog).setVisibility(0);
    }
    AdView localAdView = (AdView)localFrameLayout.findViewById(-1091584273);
    paramDialog = localAdView;
    if (localAdView == null)
    {
      paramDialog = new AdView(this);
      paramDialog.setAdSize(AdSize.BANNER);
      paramDialog.setAdUnitId("ca-app-pub-2652482030334356/6560239824");
      paramDialog.setId(-1091584273);
      localFrameLayout.addView(paramDialog);
    }
    paramDialog.loadAd(AdConfiguration.buildRequest());
  }
  
  public void showAdvertisement()
  {
    if (this.adError)
    {
      loadInterstitialAdvertisement();
      return;
    }
    this.shadePopup.showAtLocation(getWindow().getDecorView(), 51, 0, 0);
    ScriptManager.nativeSetExitEnabled(false);
    this.adHandler.removeMessages(609);
    this.adHandler.sendEmptyMessageDelayed(608, 500L);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\LauncherAppActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */