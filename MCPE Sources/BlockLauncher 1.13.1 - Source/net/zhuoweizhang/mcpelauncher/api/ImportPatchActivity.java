package net.zhuoweizhang.mcpelauncher.api;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.mojang.minecraftpe.MainActivity;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import net.zhuoweizhang.mcpelauncher.PatchManager;
import net.zhuoweizhang.mcpelauncher.R.integer;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.Utils;
import net.zhuoweizhang.mcpelauncher.patch.PatchUtils;

public class ImportPatchActivity
  extends ImportActivity
{
  public boolean hasTooManyPatches()
  {
    int i = getResources().getInteger(R.integer.max_num_patches);
    Set localSet = PatchManager.getPatchManager(this).getEnabledPatches();
    return (i >= 0) && (localSet.size() >= i);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.installConfirmText.setText(R.string.manage_patches_import_confirm);
  }
  
  protected void startImport()
  {
    File localFile = new File(getDir("ptpatches", 0), this.mFile.getName());
    try
    {
      PatchUtils.copy(this.mFile, localFile);
      setResult(-1);
      boolean bool = hasTooManyPatches();
      PatchManager.getPatchManager(this).setEnabled(localFile, false);
      if (bool)
      {
        Toast.makeText(this, R.string.manage_patches_too_many, 1).show();
        finish();
        return;
      }
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      Toast.makeText(this, R.string.manage_patches_import_error, 1).show();
      return;
    }
    PatchManager.getPatchManager(this).setEnabled(localIOException, true);
    Utils.getPrefs(1).edit().putBoolean("force_prepatch", true).apply();
    if (MainActivity.libLoaded) {
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            Thread.sleep(100L);
            System.exit(0);
            return;
          }
          catch (Exception localException)
          {
            for (;;) {}
          }
        }
      }).start();
    }
    Toast.makeText(this, R.string.manage_patches_import_done, 0).show();
    finish();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\api\ImportPatchActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */