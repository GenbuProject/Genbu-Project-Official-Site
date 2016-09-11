package net.zhuoweizhang.mcpelauncher.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.Utils;

public final class AboutAppActivity
  extends Activity
  implements View.OnLongClickListener, View.OnClickListener
{
  public static final String FORUMS_PAGE_URL = "http://www.minecraftforum.net/topic/1675581-blocklauncher-an-android-app-that-patches-minecraft-pe-without-reinstall/";
  public static final String LICENSES_URL = "https://gist.github.com/zhuowei/da4c2fec46d4d23050bf";
  public static final int SLEEP_INTERVAL = 120;
  public TextView appNameText;
  public TextView appVersionText;
  public int frame;
  public Button gotoForumsButton;
  public Button ossLicensesButton;
  
  public void onClick(View paramView)
  {
    if (paramView == this.gotoForumsButton) {
      openForumsPage();
    }
    while (paramView != this.ossLicensesButton) {
      return;
    }
    paramView = new Intent("android.intent.action.VIEW");
    paramView.setData(Uri.parse("https://gist.github.com/zhuowei/da4c2fec46d4d23050bf"));
    startActivity(paramView);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    Utils.setLanguageOverride();
    super.onCreate(paramBundle);
    setContentView(R.layout.about);
    this.appNameText = ((TextView)findViewById(R.id.about_appnametext));
    this.appNameText.setOnLongClickListener(this);
    this.gotoForumsButton = ((Button)findViewById(R.id.about_go_to_forums_button));
    this.gotoForumsButton.setOnClickListener(this);
    this.ossLicensesButton = ((Button)findViewById(R.id.about_oss_license_info_button));
    this.ossLicensesButton.setOnClickListener(this);
    this.appVersionText = ((TextView)findViewById(R.id.about_appversiontext));
    paramBundle = "Top secret alpha pre-prerelease";
    try
    {
      String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      paramBundle = str;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    this.appVersionText.setText(paramBundle);
  }
  
  public boolean onLongClick(View paramView)
  {
    if (paramView == this.appNameText)
    {
      paramView = new Intent("android.intent.action.VIEW");
      paramView.setData(Uri.parse("http://www.youtube.com/watch?v=6K7VaIdttkw"));
      startActivity(paramView);
      return true;
    }
    return false;
  }
  
  protected void openForumsPage()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("http://www.minecraftforum.net/topic/1675581-blocklauncher-an-android-app-that-patches-minecraft-pe-without-reinstall/"));
    startActivity(localIntent);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\ui\AboutAppActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */