package net.zhuoweizhang.mcpelauncher;

import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class ResourcePack
{
  public File file;
  
  public ResourcePack(File paramFile)
  {
    this.file = paramFile;
  }
  
  public static List<ResourcePack> getAllResourcePacks()
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject3 = new File(Environment.getExternalStorageDirectory(), "games/com.mojang/resource_packs");
    if (!((File)localObject3).exists()) {}
    for (;;)
    {
      return localArrayList;
      Object localObject1 = readAllIds();
      if (((List)localObject1).size() != 0)
      {
        HashMap localHashMap = new HashMap();
        Object localObject2 = Charset.forName("UTF-8");
        localObject3 = ((File)localObject3).listFiles();
        int j = localObject3.length;
        int i = 0;
        if (i < j)
        {
          File localFile = localObject3[i];
          Object localObject4 = new File(localFile, "resources.json");
          if (!((File)localObject4).exists()) {}
          for (;;)
          {
            i += 1;
            break;
            try
            {
              byte[] arrayOfByte = new byte[(int)((File)localObject4).length()];
              localObject4 = new FileInputStream((File)localObject4);
              ((FileInputStream)localObject4).read(arrayOfByte);
              ((FileInputStream)localObject4).close();
              localHashMap.put(new JSONObject(new String(arrayOfByte, (Charset)localObject2)).getString("pack_id"), localFile);
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
          }
        }
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (File)localHashMap.get((String)((Iterator)localObject1).next());
          if (localObject2 != null) {
            localArrayList.add(new ResourcePack((File)localObject2));
          }
        }
      }
    }
  }
  
  public static List<String> readAllIds()
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = new File(Environment.getExternalStorageDirectory(), "games/com.mojang/minecraftpe/resource_packs.txt");
    if (!((File)localObject2).exists()) {}
    for (;;)
    {
      return localArrayList;
      Object localObject1 = new byte[(int)((File)localObject2).length()];
      localObject2 = new FileInputStream((File)localObject2);
      ((FileInputStream)localObject2).read((byte[])localObject1);
      ((FileInputStream)localObject2).close();
      localObject1 = new String((byte[])localObject1, Charset.forName("UTF-8")).split("\n");
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        localObject2 = localObject1[i];
        if (((String)localObject2).length() != 0) {
          localArrayList.add(localObject2);
        }
        i += 1;
      }
    }
  }
  
  public InputStream getInputStream(String paramString)
    throws IOException
  {
    paramString = new File(this.file, paramString);
    if (!paramString.exists()) {
      return null;
    }
    return new FileInputStream(paramString);
  }
  
  public String getName()
  {
    return this.file.getName();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\ResourcePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */