package net.zhuoweizhang.mcpelauncher;

import android.os.Bundle;
import com.google.android.gms.ads.AdView;
import net.zhuoweizhang.mcpelauncher.ui.ManagePatchesActivity;

public class ManagePatchesAppActivity
  extends ManagePatchesActivity
{
  private AdView adView;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.adView = ((AdView)findViewById(2131296374));
    paramBundle = AdConfiguration.buildRequest();
    this.adView.loadAd(paramBundle);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ManagePatchesAppActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */