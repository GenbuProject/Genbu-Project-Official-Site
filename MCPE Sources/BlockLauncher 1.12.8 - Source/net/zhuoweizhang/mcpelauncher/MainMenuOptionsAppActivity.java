package net.zhuoweizhang.mcpelauncher;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import java.io.PrintStream;
import net.zhuoweizhang.mcpelauncher.ui.MainMenuOptionsActivity;

public class MainMenuOptionsAppActivity
  extends MainMenuOptionsActivity
{
  private AdView adView;
  
  private void addAds()
  {
    Object localObject = findViewById(16908298).getParent();
    if (localObject == null)
    {
      System.out.println("Main menu options: no parent (is this device Batman?)");
      return;
    }
    localObject = ((ViewParent)localObject).getParent();
    if (localObject == null)
    {
      System.out.println("Main menu options: no parent of parent");
      return;
    }
    localObject = ((ViewParent)localObject).getParent();
    if ((localObject == null) || (!(localObject instanceof LinearLayout)))
    {
      System.out.println("Main menu options: no parent of parent of parent");
      return;
    }
    localObject = (LinearLayout)localObject;
    this.adView = new AdView(this);
    this.adView.setAdUnitId("ca-app-pub-2652482030334356/6560239824");
    this.adView.setAdSize(AdSize.SMART_BANNER);
    AdRequest localAdRequest = AdConfiguration.buildRequest();
    ((LinearLayout)localObject).addView(this.adView, 0);
    this.adView.loadAd(localAdRequest);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    try
    {
      addAds();
      return;
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
  }
  
  public void onDestroy()
  {
    if (this.adView != null) {
      this.adView.destroy();
    }
    super.onDestroy();
  }
  
  public void onPause()
  {
    if (this.adView != null) {
      this.adView.pause();
    }
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.adView != null) {
      this.adView.resume();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\MainMenuOptionsAppActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */