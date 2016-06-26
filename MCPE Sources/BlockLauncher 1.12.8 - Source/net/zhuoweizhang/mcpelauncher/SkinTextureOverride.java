package net.zhuoweizhang.mcpelauncher;

import android.content.SharedPreferences;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SkinTextureOverride
  implements TexturePack
{
  public void close()
    throws IOException
  {}
  
  public InputStream getInputStream(String paramString)
    throws IOException
  {
    if ((!Utils.getPrefs(0).getBoolean("zz_skin_enable", false)) || (Utils.isSafeMode())) {}
    do
    {
      do
      {
        do
        {
          return null;
        } while (!paramString.equals("images/mob/char.png"));
        paramString = Utils.getPrefs(1).getString("player_skin", null);
      } while (paramString == null);
      paramString = new File(paramString);
    } while (!paramString.exists());
    return new FileInputStream(paramString);
  }
  
  public long getSize(String paramString)
    throws IOException
  {
    return 0L;
  }
  
  public List<String> listFiles()
    throws IOException
  {
    return new ArrayList();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\SkinTextureOverride.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */