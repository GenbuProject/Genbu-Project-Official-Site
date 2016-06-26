package net.zhuoweizhang.mcpelauncher.api;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.Utils;

public class ImportSkinActivity
  extends ImportActivity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.installConfirmText.setText(R.string.skin_import_confirm);
  }
  
  protected void startImport()
  {
    Utils.getPrefs(1).edit().putString("player_skin", this.mFile.getAbsolutePath()).apply();
    Utils.getPrefs(0).edit().putBoolean("zz_skin_enable", true).apply();
    Toast.makeText(this, R.string.skin_imported, 1).show();
    finish();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\api\ImportSkinActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */