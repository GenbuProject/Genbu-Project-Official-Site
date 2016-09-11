package com.microsoft.cll.android;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public abstract class AbstractHandler
{
  protected static final String criticalEventFileExtension = ".crit.cllevent";
  protected static final String normalEventFileExtension = ".norm.cllevent";
  protected static AtomicLong totalStorageUsed = new AtomicLong(0L);
  private final String TAG = "AndroidCll-AbstractHandler";
  protected final ClientTelemetry clientTelemetry;
  protected String filePath;
  protected FileStorage fileStorage;
  protected final ILogger logger;
  
  public AbstractHandler(ILogger paramILogger, String paramString, ClientTelemetry paramClientTelemetry)
  {
    this.filePath = paramString;
    this.logger = paramILogger;
    this.clientTelemetry = paramClientTelemetry;
    setFileStorageUsed();
  }
  
  private boolean deleteFile(File paramFile)
  {
    try
    {
      boolean bool = paramFile.delete();
      return bool;
    }
    catch (Exception paramFile)
    {
      this.logger.info("AndroidCll-AbstractHandler", "Exception while deleting the file: " + paramFile.toString());
    }
    return false;
  }
  
  private void setFileStorageUsed()
  {
    int j = 0;
    totalStorageUsed.set(0L);
    File[] arrayOfFile = findExistingFiles(".crit.cllevent");
    int k = arrayOfFile.length;
    int i = 0;
    File localFile;
    while (i < k)
    {
      localFile = arrayOfFile[i];
      totalStorageUsed.getAndAdd(localFile.length());
      i += 1;
    }
    arrayOfFile = findExistingFiles(".norm.cllevent");
    k = arrayOfFile.length;
    i = j;
    while (i < k)
    {
      localFile = arrayOfFile[i];
      totalStorageUsed.getAndAdd(localFile.length());
      i += 1;
    }
  }
  
  public abstract void add(String paramString, List<String> paramList)
    throws IOException, FileStorage.FileFullException;
  
  public boolean canAdd(Tuple paramTuple)
  {
    long l = totalStorageUsed.get();
    return ((String)paramTuple.a).length() + l <= SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXFILESSPACE);
  }
  
  public abstract void close();
  
  public abstract void dispose(IStorage paramIStorage);
  
  protected boolean dropOldestFile(boolean paramBoolean)
  {
    Object localObject1 = findExistingFiles(".norm.cllevent");
    Object localObject2 = localObject1;
    if (localObject1.length <= 1)
    {
      localObject2 = localObject1;
      if (paramBoolean) {
        localObject2 = findExistingFiles(".crit.cllevent");
      }
    }
    if (localObject2.length <= 1)
    {
      this.logger.info("AndroidCll-AbstractHandler", "There are no files to delete");
      paramBoolean = false;
      return paramBoolean;
    }
    long l = localObject2[0].lastModified();
    localObject1 = localObject2[0];
    int j = localObject2.length;
    int i = 0;
    label80:
    if (i < j)
    {
      Object localObject3 = localObject2[i];
      if (((File)localObject3).lastModified() >= l) {
        break label156;
      }
      l = ((File)localObject3).lastModified();
      localObject1 = localObject3;
    }
    label156:
    for (;;)
    {
      i += 1;
      break label80;
      l = ((File)localObject1).length();
      boolean bool = deleteFile((File)localObject1);
      paramBoolean = bool;
      if (!bool) {
        break;
      }
      totalStorageUsed.getAndAdd(-l);
      return bool;
    }
  }
  
  protected boolean ensureCanAdd(Tuple<String, List<String>> paramTuple, EventEnums.Persistence paramPersistence)
  {
    int j = SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXCRITICALCANADDATTEMPTS);
    if (paramPersistence == EventEnums.Persistence.PersistenceCritical) {}
    boolean bool2;
    for (boolean bool1 = true;; bool1 = false)
    {
      bool2 = canAdd(paramTuple);
      int i = 0;
      boolean bool3 = true;
      while ((!bool2) && (i < j) && (bool3))
      {
        this.logger.warn("AndroidCll-AbstractHandler", "Out of storage space. Attempting to drop one oldest file.");
        bool3 = dropOldestFile(bool1);
        bool2 = canAdd(paramTuple);
        i += 1;
      }
    }
    return bool2;
  }
  
  protected File[] findExistingFiles(final String paramString)
  {
    paramString = new FilenameFilter()
    {
      public boolean accept(File paramAnonymousFile, String paramAnonymousString)
      {
        return paramAnonymousString.toLowerCase().endsWith(paramString);
      }
    };
    File[] arrayOfFile = new File(this.filePath).listFiles(paramString);
    paramString = arrayOfFile;
    if (arrayOfFile == null) {
      paramString = new File[0];
    }
    return paramString;
  }
  
  protected List<IStorage> getFilesByExtensionForDraining(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = findExistingFiles(paramString);
    int j = paramString.length;
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        Object localObject = paramString[i];
        try
        {
          FileStorage localFileStorage = new FileStorage(this.logger, ((File)localObject).getAbsolutePath(), this);
          localArrayList.add(localFileStorage);
          localFileStorage.close();
          i += 1;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            this.logger.info("AndroidCll-AbstractHandler", "File " + ((File)localObject).getName() + " is in use still");
          }
        }
      }
    }
    return localArrayList;
  }
  
  public abstract List<IStorage> getFilesForDraining();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\AbstractHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */