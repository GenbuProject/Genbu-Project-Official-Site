package net.zhuoweizhang.mcpelauncher;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.mojang.minecraftpe.MainActivity;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class AddonOverrideTexturePack
  implements TexturePack
{
  private final MainActivity activity;
  private final Map<String, ZipFile> assets = new HashMap();
  private final String prefix;
  private final Map<String, ZipFile> zipsByPackage = new HashMap();
  
  public AddonOverrideTexturePack(MainActivity paramMainActivity, String paramString)
  {
    this.activity = paramMainActivity;
    this.prefix = paramString;
    initAddons();
  }
  
  private void initAddons()
  {
    Object localObject = this.activity;
    localObject = MainActivity.loadedAddons.iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      System.out.println("Addon textures: " + str);
      try
      {
        addPackage(new File(this.activity.getPackageManager().getPackageInfo(str, 0).applicationInfo.publicSourceDir), str);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void addPackage(File paramFile, String paramString)
    throws IOException
  {
    paramFile = new ZipFile(paramFile);
    Enumeration localEnumeration = paramFile.entries();
    while (localEnumeration.hasMoreElements())
    {
      String str = ((ZipEntry)localEnumeration.nextElement()).getName();
      if ((!str.contains("__MACOSX")) && (str.indexOf("assets/") == 0) && (str.charAt(str.length() - 1) != '/')) {
        this.assets.put(str, paramFile);
      }
    }
    this.zipsByPackage.put(paramString, paramFile);
  }
  
  public void close()
    throws IOException
  {}
  
  public InputStream getInputStream(String paramString)
    throws IOException
  {
    Object localObject = paramString;
    if (paramString.startsWith(this.prefix)) {
      localObject = paramString.substring(this.prefix.length());
    }
    paramString = "assets/" + (String)localObject;
    localObject = (ZipFile)this.assets.get(paramString);
    if (localObject == null) {
      return null;
    }
    return ((ZipFile)localObject).getInputStream(((ZipFile)localObject).getEntry(paramString));
  }
  
  public long getSize(String paramString)
    throws IOException
  {
    Object localObject = paramString;
    if (paramString.startsWith(this.prefix)) {
      localObject = paramString.substring(this.prefix.length());
    }
    paramString = "assets/" + (String)localObject;
    localObject = (ZipFile)this.assets.get(paramString);
    if (localObject == null) {
      return -1L;
    }
    return ((ZipFile)localObject).getEntry(paramString).getSize();
  }
  
  public Map<String, ZipFile> getZipsByPackage()
  {
    return this.zipsByPackage;
  }
  
  public List<String> listFiles()
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.assets.entrySet().iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((String)((Map.Entry)localIterator.next()).getKey()).substring("assets/".length()));
    }
    return localArrayList;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\AddonOverrideTexturePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */