package net.zhuoweizhang.mcpelauncher.api;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.ScriptManager;
import net.zhuoweizhang.mcpelauncher.patch.PatchUtils;

public class ImportScriptActivity
  extends ImportActivity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.installConfirmText.setText(R.string.script_import_confirm);
  }
  
  protected void startImport()
  {
    try
    {
      File localFile = new File(getDir("modscripts", 0), this.mFile.getName());
      PatchUtils.copy(this.mFile, localFile);
      ScriptManager.androidContext = getApplicationContext();
      ScriptManager.loadEnabledScriptsNames(getApplicationContext());
      ScriptManager.setOriginalLocation(this.mFile, localFile);
      ScriptManager.setEnabled(localFile, true);
      Toast.makeText(this, R.string.script_imported, 1).show();
      setResult(-1);
      finish();
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
        Toast.makeText(this, R.string.manage_patches_import_error, 1).show();
        setResult(0);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\api\ImportScriptActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */