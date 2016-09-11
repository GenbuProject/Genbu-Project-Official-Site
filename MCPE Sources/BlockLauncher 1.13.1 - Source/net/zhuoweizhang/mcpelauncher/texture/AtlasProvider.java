package net.zhuoweizhang.mcpelauncher.texture;

import com.mojang.minecraftpe.MainActivity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.zhuoweizhang.mcpelauncher.TexturePack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AtlasProvider
  implements TexturePack
{
  public List<String[]> addedTextureNames = new ArrayList();
  public boolean hasChanges = false;
  public String importDir;
  public String metaName;
  public JSONObject metaObj;
  public String textureNamePrefix;
  
  public AtlasProvider(String paramString1, String paramString2, String paramString3)
  {
    this.metaName = paramString1;
    this.importDir = paramString2;
    this.textureNamePrefix = paramString3;
  }
  
  private boolean addAllToMeta(MainActivity paramMainActivity)
    throws Exception
  {
    paramMainActivity = TextureUtils.getAllFilesFilter(paramMainActivity.textureOverrides, this.importDir);
    if (paramMainActivity.size() == 0) {
      return false;
    }
    Object[] arrayOfObject = new Object[2];
    int i = paramMainActivity.size() - 1;
    if (i >= 0)
    {
      String str = (String)paramMainActivity.get(i);
      if (!str.toLowerCase().endsWith(".png")) {}
      for (;;)
      {
        i -= 1;
        break;
        parseNameParts(str, arrayOfObject);
        if (arrayOfObject[0] != null) {
          addFileIntoObj(str, arrayOfObject);
        }
      }
    }
    return true;
  }
  
  private void addFileIntoObj(String paramString, Object[] paramArrayOfObject)
    throws JSONException
  {
    Object localObject2 = (String)paramArrayOfObject[0];
    int j = ((Integer)paramArrayOfObject[1]).intValue();
    String str = this.textureNamePrefix + paramArrayOfObject[0] + "." + j;
    Object localObject1 = this.metaObj.getJSONObject("texture_data").optJSONObject((String)localObject2);
    paramArrayOfObject = (Object[])localObject1;
    if (localObject1 == null)
    {
      paramArrayOfObject = new JSONObject();
      this.metaObj.getJSONObject("texture_data").put((String)localObject2, paramArrayOfObject);
    }
    localObject2 = paramArrayOfObject.optJSONArray("textures");
    localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new JSONArray();
      paramArrayOfObject.put("textures", localObject1);
    }
    if (j < ((JSONArray)localObject1).length()) {
      ((JSONArray)localObject1).put(j, str);
    }
    for (;;)
    {
      this.addedTextureNames.add(new String[] { str, paramString });
      return;
      int i = ((JSONArray)localObject1).length();
      while (i <= j)
      {
        ((JSONArray)localObject1).put(i, str);
        i += 1;
      }
    }
  }
  
  private void loadAtlas(MainActivity paramMainActivity)
    throws Exception
  {
    paramMainActivity = paramMainActivity.getInputStreamForAsset(this.metaName);
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
  
  private void parseNameParts(String paramString, Object[] paramArrayOfObject)
  {
    paramArrayOfObject[0] = null;
    String str = paramString.substring(paramString.lastIndexOf("/") + 1, paramString.lastIndexOf("."));
    int i = str.lastIndexOf("_");
    if (i < 0) {
      return;
    }
    paramString = str.substring(0, i);
    str = str.substring(i + 1);
    try
    {
      paramArrayOfObject[1] = Integer.valueOf(Integer.parseInt(str));
      paramArrayOfObject[0] = paramString;
      return;
    }
    catch (NumberFormatException paramString) {}
  }
  
  public void close()
    throws IOException
  {
    this.metaObj = null;
    this.hasChanges = false;
  }
  
  public void dumpAtlas()
    throws IOException
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(new File("/sdcard/bl_dump_" + this.textureNamePrefix + "json"));
    localFileOutputStream.write(this.metaObj.toString().getBytes("UTF-8"));
    localFileOutputStream.close();
  }
  
  public InputStream getInputStream(String paramString)
    throws IOException
  {
    if (!this.hasChanges) {}
    while (!paramString.equals(this.metaName)) {
      return null;
    }
    return new ByteArrayInputStream(this.metaObj.toString().getBytes("UTF-8"));
  }
  
  public long getSize(String paramString)
  {
    return 0L;
  }
  
  public boolean hasIcon(String paramString, int paramInt)
  {
    boolean bool2 = true;
    boolean bool1 = true;
    try
    {
      paramString = this.metaObj.getJSONObject("texture_data").optJSONObject(paramString);
      if (paramString == null) {
        return false;
      }
      JSONArray localJSONArray = paramString.optJSONArray("textures");
      if (localJSONArray == null)
      {
        if ((paramInt != 0) || (paramString.optString("textures") == null)) {
          break label89;
        }
        bool1 = bool2;
      }
      else
      {
        int i = localJSONArray.length();
        if (paramInt < i) {}
        for (;;)
        {
          return bool1;
          bool1 = false;
        }
      }
      return bool1;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
      return false;
    }
    for (;;)
    {
      label89:
      bool1 = false;
    }
  }
  
  public void initAtlas(MainActivity paramMainActivity)
    throws Exception
  {
    this.hasChanges = false;
    loadAtlas(paramMainActivity);
    this.hasChanges = addAllToMeta(paramMainActivity);
  }
  
  public List<String> listFiles()
    throws IOException
  {
    return new ArrayList();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\texture\AtlasProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */