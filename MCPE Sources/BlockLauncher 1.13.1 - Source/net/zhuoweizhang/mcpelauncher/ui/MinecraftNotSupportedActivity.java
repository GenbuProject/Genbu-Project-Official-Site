package net.zhuoweizhang.mcpelauncher.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.R.string;

public class MinecraftNotSupportedActivity
  extends Activity
{
  public TextView theText;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.no_minecraft);
    this.theText = ((TextView)findViewById(R.id.no_minecraft_text));
    Object localObject = getIntent();
    paramBundle = ((Intent)localObject).getStringExtra("minecraftVersion");
    localObject = ((Intent)localObject).getStringExtra("supportedVersion");
    paramBundle = getResources().getString(R.string.minecraft_version_not_supported).toString().replaceAll("MINECRAFT_VERSION", paramBundle).replaceAll("SUPPORTED_VERSION", (String)localObject);
    this.theText.setText(paramBundle);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\ui\MinecraftNotSupportedActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */