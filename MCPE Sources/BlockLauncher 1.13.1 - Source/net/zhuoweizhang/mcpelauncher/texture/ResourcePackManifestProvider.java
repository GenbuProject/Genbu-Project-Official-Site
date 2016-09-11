package net.zhuoweizhang.mcpelauncher.texture;

import com.mojang.minecraftpe.MainActivity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.zhuoweizhang.mcpelauncher.TexturePack;
import org.json.JSONException;
import org.json.JSONObject;

public class ResourcePackManifestProvider
  implements TexturePack
{
  public boolean hasChanges = false;
  public String manifestPath;
  public JSONObject metaObj;
  
  public ResourcePackManifestProvider(String paramString)
  {
    this.manifestPath = paramString;
  }
  
  private void loadAtlas(MainActivity paramMainActivity)
    throws Exception
  {
    paramMainActivity = paramMainActivity.getInputStreamForAsset(this.manifestPath);
    byte[] arrayOfByte = new byte['?'];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    for (;;)
    {
      int i = paramMainActivity.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    paramMainActivity.close();
    this.metaObj = new JSONObject(new String(localByteArrayOutputStream.toByteArray(), "UTF-8"));
  }
  
  public void addTextures(List<String[]> paramList)
    throws JSONException
  {
    if (paramList.size() == 0) {
      return;
    }
    JSONObject localJSONObject = this.metaObj.getJSONObject("resources").getJSONObject("textures");
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String[] arrayOfString = (String[])paramList.next();
      localJSONObject.put(arrayOfString[0], arrayOfString[1]);
    }
    this.hasChanges = true;
  }
  
  public void close()
    throws IOException
  {
    this.metaObj = null;
    this.hasChanges = false;
  }
  
  public InputStream getInputStream(String paramString)
    throws IOException
  {
    if (!this.hasChanges) {}
    while (!paramString.equals(this.manifestPath)) {
      return null;
    }
    return new ByteArrayInputStream(this.metaObj.toString().getBytes("UTF-8"));
  }
  
  public long getSize(String paramString)
  {
    return 0L;
  }
  
  public void init(MainActivity paramMainActivity)
    throws Exception
  {
    this.hasChanges = false;
    loadAtlas(paramMainActivity);
  }
  
  public List<String> listFiles()
    throws IOException
  {
    return new ArrayList();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\texture\ResourcePackManifestProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */