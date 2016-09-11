package net.zhuoweizhang.mcpelauncher.texture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.zhuoweizhang.mcpelauncher.TexturePack;

public class TextureUtils
{
  public static List<String> getAllFilesFilter(List<TexturePack> paramList, String paramString)
    throws IOException
  {
    ArrayList localArrayList1 = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = ((TexturePack)paramList.next()).listFiles();
      ArrayList localArrayList2 = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Iterator)localObject).next();
        if (str.contains(paramString)) {
          localArrayList2.add(str);
        }
      }
      Collections.sort(localArrayList2);
      localArrayList1.addAll(localArrayList2);
    }
    return localArrayList1;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\texture\TextureUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */