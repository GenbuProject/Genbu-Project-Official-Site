package net.zhuoweizhang.mcpelauncher;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ScriptOverrideTexturePack
  implements TexturePack
{
  private Context context;
  
  public ScriptOverrideTexturePack(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void close()
    throws IOException
  {}
  
  public InputStream getInputStream(String paramString)
    throws IOException
  {
    if (ScriptManager.androidContext == null) {}
    do
    {
      return null;
      paramString = ScriptManager.getTextureOverrideFile(paramString);
    } while (!paramString.exists());
    return new FileInputStream(paramString);
  }
  
  public long getSize(String paramString)
    throws IOException
  {
    if (ScriptManager.androidContext == null) {}
    do
    {
      return -1L;
      paramString = ScriptManager.getTextureOverrideFile(paramString);
    } while (!paramString.exists());
    return paramString.length();
  }
  
  public List<String> listFiles()
  {
    return new ArrayList();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ScriptOverrideTexturePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */