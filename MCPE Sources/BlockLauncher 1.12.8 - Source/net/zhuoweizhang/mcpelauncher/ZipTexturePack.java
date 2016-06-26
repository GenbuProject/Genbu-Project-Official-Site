package net.zhuoweizhang.mcpelauncher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class ZipTexturePack
  implements TexturePack
{
  private HashMap<String, ZipEntry> entries = new HashMap();
  private File file;
  private HashMap<String, ZipEntry> fullNameEntries = new HashMap();
  private ZipFile zipFile;
  
  public ZipTexturePack(File paramFile)
    throws ZipException, IOException
  {
    this.file = paramFile;
    this.zipFile = new ZipFile(paramFile);
    putZipEntriesIntoMap();
  }
  
  private ZipEntry getEntry(String paramString)
  {
    ZipEntry localZipEntry = (ZipEntry)this.fullNameEntries.get(paramString);
    if (localZipEntry != null) {
      return localZipEntry;
    }
    localZipEntry = (ZipEntry)this.fullNameEntries.get("assets/" + paramString);
    if (localZipEntry != null) {
      return localZipEntry;
    }
    paramString = getFilenameOnly(paramString);
    return (ZipEntry)this.entries.get(paramString);
  }
  
  private static String getFilenameOnly(String paramString)
  {
    paramString = paramString.split("/");
    return paramString[(paramString.length - 1)];
  }
  
  private void putZipEntriesIntoMap()
  {
    Enumeration localEnumeration = this.zipFile.entries();
    while (localEnumeration.hasMoreElements())
    {
      ZipEntry localZipEntry = (ZipEntry)localEnumeration.nextElement();
      if (!localZipEntry.getName().contains("__MACOSX"))
      {
        this.fullNameEntries.put(localZipEntry.getName(), localZipEntry);
        this.entries.put(getFilenameOnly(localZipEntry.getName()), localZipEntry);
      }
    }
  }
  
  public void close()
    throws IOException
  {
    this.zipFile.close();
  }
  
  public InputStream getInputStream(String paramString)
    throws IOException
  {
    paramString = getEntry(paramString);
    if (paramString == null) {
      return null;
    }
    return this.zipFile.getInputStream(paramString);
  }
  
  public long getSize(String paramString)
    throws IOException
  {
    paramString = getEntry(paramString);
    if (paramString == null) {
      return -1L;
    }
    return paramString.getSize();
  }
  
  public String getZipName()
  {
    return this.file.getName();
  }
  
  public List<String> listFiles()
    throws IOException
  {
    Enumeration localEnumeration = this.zipFile.entries();
    ArrayList localArrayList = new ArrayList();
    while (localEnumeration.hasMoreElements())
    {
      ZipEntry localZipEntry = (ZipEntry)localEnumeration.nextElement();
      if (!localZipEntry.getName().contains("__MACOSX")) {
        localArrayList.add(localZipEntry.getName());
      }
    }
    return localArrayList;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\ZipTexturePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */