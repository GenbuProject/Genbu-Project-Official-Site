package net.zhuoweizhang.mcpelauncher.texture;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.zhuoweizhang.mcpelauncher.TexturePack;
import net.zhuoweizhang.mcpelauncher.ZipTexturePack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TexturePackLoader
{
  public static final String TYPE_ADDON = "addon";
  public static final String TYPE_MPKG = "mpkg";
  public static final String TYPE_ZIP = "zip";
  
  public static String describeTexturePack(Context paramContext, TexturePackDescription paramTexturePackDescription)
  {
    String str = paramTexturePackDescription.path;
    if (!paramTexturePackDescription.type.equals("zip"))
    {
      paramContext = str;
      if (!paramTexturePackDescription.type.equals("mpkg")) {}
    }
    else
    {
      paramContext = str.substring(str.lastIndexOf("/") + 1);
    }
    return paramContext;
  }
  
  private static void doLoadIcon(TexturePack paramTexturePack, TexturePackDescription paramTexturePackDescription)
    throws Exception
  {
    paramTexturePack = paramTexturePack.getInputStream("pack.png");
    if (paramTexturePack == null) {
      return;
    }
    paramTexturePackDescription.img = BitmapFactory.decodeStream(paramTexturePack);
    paramTexturePack.close();
  }
  
  private static void doLoadMeta(TexturePack paramTexturePack, TexturePackDescription paramTexturePackDescription)
    throws Exception
  {
    paramTexturePack = paramTexturePack.getInputStream("pack.mcmeta");
    if (paramTexturePack == null) {
      return;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['?'];
    for (;;)
    {
      int i = paramTexturePack.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    paramTexturePack.close();
    paramTexturePackDescription.description = new JSONObject(new String(arrayOfByte, "UTF-8")).getJSONObject("pack").getString("description");
  }
  
  public static boolean isCompatible(TexturePack paramTexturePack, List<String> paramList1, List<String> paramList2)
    throws Exception
  {
    return (isCompatibleArray(paramTexturePack, "assets/images/terrain.meta", paramList1)) && (isCompatibleArray(paramTexturePack, "assets/images/items.meta", paramList2));
  }
  
  private static boolean isCompatibleArray(TexturePack paramTexturePack, String paramString, List<String> paramList)
    throws Exception
  {
    InputStream localInputStream = paramTexturePack.getInputStream(paramString);
    if (localInputStream == null) {}
    do
    {
      while (!paramString.hasNext())
      {
        return true;
        paramTexturePack = new byte[(int)paramTexturePack.getSize(paramString)];
        localInputStream.read(paramTexturePack);
        localInputStream.close();
        paramTexturePack = metaToSet(paramTexturePack);
        paramString = paramList.iterator();
      }
    } while (paramTexturePack.contains((String)paramString.next()));
    return false;
  }
  
  public static List<TexturePackDescription> loadDescriptions(Context paramContext)
    throws JSONException
  {
    paramContext = new JSONArray(paramContext.getSharedPreferences("mcpelauncherprefs", 0).getString("texture_packs", "[]"));
    int j = paramContext.length();
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(TexturePackDescription.fromJson(paramContext.getJSONObject(i)));
      i += 1;
    }
    return localArrayList;
  }
  
  public static List<TexturePackDescription> loadDescriptionsWithIcons(Context paramContext)
    throws JSONException
  {
    paramContext = loadDescriptions(paramContext);
    Iterator localIterator = paramContext.iterator();
    while (localIterator.hasNext())
    {
      TexturePackDescription localTexturePackDescription = (TexturePackDescription)localIterator.next();
      try
      {
        loadIconForDescription(localTexturePackDescription);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return paramContext;
  }
  
  public static void loadIconForDescription(TexturePackDescription paramTexturePackDescription)
    throws Exception
  {
    TexturePack localTexturePack = loadTexturePack(paramTexturePackDescription);
    doLoadIcon(localTexturePack, paramTexturePackDescription);
    doLoadMeta(localTexturePack, paramTexturePackDescription);
  }
  
  private static TexturePack loadTexturePack(TexturePackDescription paramTexturePackDescription)
    throws Exception
  {
    if ((paramTexturePackDescription.type.equals("zip")) || (paramTexturePackDescription.type.equals("mpkg"))) {
      return new ZipTexturePack(new File(paramTexturePackDescription.path));
    }
    throw new RuntimeException("Unsupported texture pack type: " + paramTexturePackDescription);
  }
  
  public static List<TexturePack> loadTexturePacks(Context paramContext, List<String> paramList, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Exception
  {
    paramList = loadDescriptions(paramContext);
    paramContext = new ArrayList(paramList.size());
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      paramContext.add(loadTexturePack((TexturePackDescription)paramList.next()));
    }
    return paramContext;
  }
  
  public static List<String> metaToList(byte[] paramArrayOfByte)
    throws Exception
  {
    paramArrayOfByte = new JSONArray(new String(paramArrayOfByte, "UTF-8"));
    int j = paramArrayOfByte.length();
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(paramArrayOfByte.getJSONObject(i).getString("name"));
      i += 1;
    }
    return localArrayList;
  }
  
  public static Set<String> metaToSet(byte[] paramArrayOfByte)
    throws Exception
  {
    paramArrayOfByte = new JSONArray(new String(paramArrayOfByte, "UTF-8"));
    int j = paramArrayOfByte.length();
    HashSet localHashSet = new HashSet();
    int i = 0;
    while (i < j)
    {
      localHashSet.add(paramArrayOfByte.getJSONObject(i).getString("name"));
      i += 1;
    }
    return localHashSet;
  }
  
  public static void saveDescriptions(Context paramContext, List<TexturePackDescription> paramList)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    while (i < paramList.size())
    {
      localJSONArray.put(i, ((TexturePackDescription)paramList.get(i)).toJson());
      i += 1;
    }
    paramContext.getSharedPreferences("mcpelauncherprefs", 0).edit().putString("texture_packs", localJSONArray.toString()).commit();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\texture\TexturePackLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */