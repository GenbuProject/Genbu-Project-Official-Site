package net.zhuoweizhang.mcpelauncher.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;

public class NoMinecraftActivity
  extends Activity
{
  private String learnmoreUri = null;
  
  public void learnMoreClicked(View paramView)
  {
    paramView = new Intent("android.intent.action.VIEW", Uri.parse(this.learnmoreUri));
    try
    {
      startActivity(paramView);
      return;
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.no_minecraft);
    paramBundle = getIntent();
    String str = paramBundle.getStringExtra("message");
    if (str != null) {
      ((TextView)findViewById(R.id.no_minecraft_text)).setText(str);
    }
    this.learnmoreUri = paramBundle.getStringExtra("learnmore_uri");
    if (this.learnmoreUri != null) {
      ((Button)findViewById(R.id.no_minecraft_learn_more)).setVisibility(0);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ui\NoMinecraftActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */