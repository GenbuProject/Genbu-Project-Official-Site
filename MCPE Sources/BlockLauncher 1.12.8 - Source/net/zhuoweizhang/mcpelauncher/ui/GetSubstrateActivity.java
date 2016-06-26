package net.zhuoweizhang.mcpelauncher.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import net.zhuoweizhang.mcpelauncher.R.layout;

public class GetSubstrateActivity
  extends Activity
{
  private boolean isPlay()
  {
    try
    {
      boolean bool = getPackageManager().getInstallerPackageName(getPackageName()).equals("com.android.vending");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public void downloadClicked(View paramView)
  {
    if (isPlay()) {}
    for (paramView = "market://details?id=com.saurik.substrate";; paramView = "http://www.cydiasubstrate.com/")
    {
      paramView = new Intent("android.intent.action.VIEW", Uri.parse(paramView));
      paramView.addFlags(268435456);
      startActivity(paramView);
      finish();
      return;
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.get_substrate);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ui\GetSubstrateActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */