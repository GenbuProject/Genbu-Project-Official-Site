package net.zhuoweizhang.mcpelauncher.api;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.Utils;
import net.zhuoweizhang.mcpelauncher.texture.TexturePackDescription;
import net.zhuoweizhang.mcpelauncher.texture.TexturePackLoader;

public class ImportTexturepackActivity
  extends ImportActivity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.installConfirmText.setText(R.string.texturepack_import_confirm);
  }
  
  protected void startImport()
  {
    try
    {
      localList = TexturePackLoader.loadDescriptionsWithIcons(this);
      localTexturePackDescription = new TexturePackDescription("zip", this.mFile.getAbsolutePath());
      j = getPackageManager().getPackageInfo("com.mojang.minecraftpe", 0).versionCode;
      localObject = Utils.getPrefs(1);
      if (((SharedPreferences)localObject).getInt("last_version", -1) == j) {
        break label213;
      }
      i = 1;
      if (i != 0) {
        ((SharedPreferences)localObject).edit().putInt("last_version", j).apply();
      }
      if (getIntent().getAction().equals("net.zhuoweizhang.mcpelauncher.action.REPLACE_TEXTUREPACK")) {
        break label249;
      }
      if (i == 0) {
        break label218;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        List localList;
        TexturePackDescription localTexturePackDescription;
        int j;
        Object localObject;
        label105:
        label213:
        label218:
        localException.printStackTrace();
        continue;
        label249:
        int i = 1;
      }
    }
    if (i == 0)
    {
      j = 0;
      localObject = localList.iterator();
      do
      {
        i = j;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (!((TexturePackDescription)((Iterator)localObject).next()).path.equals(localTexturePackDescription.path));
      i = 1;
      if (i == 0) {
        localList.add(0, localTexturePackDescription);
      }
    }
    for (;;)
    {
      TexturePackLoader.saveDescriptions(this, localList);
      Utils.getPrefs(0).edit().putBoolean("zz_texture_pack_enable", true).apply();
      Toast.makeText(this, R.string.texturepack_imported, 1).show();
      finish();
      return;
      i = 0;
      break;
      i = 0;
      break label105;
      localList.clear();
      localList.add(localTexturePackDescription);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\api\ImportTexturepackActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */