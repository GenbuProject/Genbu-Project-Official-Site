package net.zhuoweizhang.mcpelauncher.texture;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipException;
import net.zhuoweizhang.mcpelauncher.TexturePack;
import net.zhuoweizhang.mcpelauncher.ZipTexturePack;

public class ModPkgTexturePack
  implements TexturePack
{
  static Set<String> filterTheseOut = new HashSet();
  public List<ZipTexturePack> subPacks = new ArrayList();
  
  static
  {
    filterTheseOut.add("images/terrain.meta");
    filterTheseOut.add("images/items.meta");
    filterTheseOut.add("items.json");
    filterTheseOut.add("images/terrain-atlas.tga");
    filterTheseOut.add("images/terrain-atlas_mip0.tga");
    filterTheseOut.add("images/terrain-atlas_mip1.tga");
    filterTheseOut.add("images/terrain-atlas_mip2.tga");
    filterTheseOut.add("images/terrain-atlas_mip3.tga");
    filterTheseOut.add("images/items-opaque.png");
  }
  
  public void addPackage(File paramFile)
    throws ZipException, IOException
  {
    this.subPacks.add(new ZipTexturePack(paramFile));
  }
  
  public void close()
    throws IOException
  {
    Iterator localIterator = this.subPacks.iterator();
    while (localIterator.hasNext()) {
      ((ZipTexturePack)localIterator.next()).close();
    }
    this.subPacks.clear();
  }
  
  public InputStream getInputStream(String paramString)
    throws IOException
  {
    if (filterTheseOut.contains(paramString)) {
      return null;
    }
    Iterator localIterator = this.subPacks.iterator();
    while (localIterator.hasNext())
    {
      InputStream localInputStream = ((ZipTexturePack)localIterator.next()).getInputStream(paramString);
      if (localInputStream != null) {
        return localInputStream;
      }
    }
    return null;
  }
  
  public long getSize(String paramString)
    throws IOException
  {
    if (filterTheseOut.contains(paramString)) {
      return -1L;
    }
    Iterator localIterator = this.subPacks.iterator();
    while (localIterator.hasNext())
    {
      long l = ((ZipTexturePack)localIterator.next()).getSize(paramString);
      if (l != -1L) {
        return l;
      }
    }
    return -1L;
  }
  
  public List<String> listFiles()
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.subPacks.iterator();
    while (localIterator.hasNext()) {
      localArrayList.addAll(((ZipTexturePack)localIterator.next()).listFiles());
    }
    return localArrayList;
  }
  
  public void removePackage(String paramString)
    throws IOException
  {
    int i = this.subPacks.size() - 1;
    while (i >= 0)
    {
      ZipTexturePack localZipTexturePack = (ZipTexturePack)this.subPacks.get(i);
      if (!localZipTexturePack.getZipName().equals(paramString))
      {
        i -= 1;
      }
      else
      {
        localZipTexturePack.close();
        this.subPacks.remove(i);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\texture\ModPkgTexturePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */