package net.zhuoweizhang.mcpelauncher;

import android.os.Bundle;
import com.google.android.gms.ads.AdView;
import net.zhuoweizhang.mcpelauncher.ui.ManageScriptsActivity;

public class ManageScriptsAppActivity
  extends ManageScriptsActivity
{
  private AdView adView;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.adView = ((AdView)findViewById(2131296511));
    paramBundle = AdConfiguration.buildRequest();
    this.adView.loadAd(paramBundle);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\ManageScriptsAppActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */