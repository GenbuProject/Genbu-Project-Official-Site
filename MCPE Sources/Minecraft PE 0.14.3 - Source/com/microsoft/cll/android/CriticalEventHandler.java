package com.microsoft.cll.android;

import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

public class CriticalEventHandler
  extends AbstractHandler
{
  private final String TAG = "CriticalEventHandler";
  
  public CriticalEventHandler(ILogger paramILogger, String paramString, ClientTelemetry paramClientTelemetry)
  {
    super(paramILogger, paramString, paramClientTelemetry);
    this.fileStorage = new FileStorage(".crit.cllevent", paramILogger, paramString, this);
  }
  
  private void dropNormalFile()
  {
    File[] arrayOfFile = findExistingFiles(".norm.cllevent");
    if (arrayOfFile.length < 2)
    {
      this.logger.info("CriticalEventHandler", "There are no normal files to delete");
      return;
    }
    long l1 = arrayOfFile[0].lastModified();
    Object localObject = arrayOfFile[0];
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      File localFile = arrayOfFile[i];
      long l2 = l1;
      if (localFile.lastModified() < l1)
      {
        l2 = localFile.lastModified();
        localObject = localFile;
      }
      i += 1;
      l1 = l2;
    }
    totalStorageUsed.getAndAdd(-1L * ((File)localObject).length());
    ((File)localObject).delete();
  }
  
  /* Error */
  public void add(String paramString, java.util.List<String> paramList)
    throws java.io.IOException, FileStorage.FileFullException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 79	com/microsoft/cll/android/Tuple
    //   5: dup
    //   6: aload_1
    //   7: aload_2
    //   8: invokespecial 82	com/microsoft/cll/android/Tuple:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   11: astore_2
    //   12: iconst_0
    //   13: istore_3
    //   14: aload_0
    //   15: aload_2
    //   16: invokevirtual 86	com/microsoft/cll/android/CriticalEventHandler:canAdd	(Lcom/microsoft/cll/android/Tuple;)Z
    //   19: ifne +47 -> 66
    //   22: iload_3
    //   23: getstatic 92	com/microsoft/cll/android/SettingsStore$Settings:MAXCRITICALCANADDATTEMPTS	Lcom/microsoft/cll/android/SettingsStore$Settings;
    //   26: invokestatic 98	com/microsoft/cll/android/SettingsStore:getCllSettingsAsInt	(Lcom/microsoft/cll/android/SettingsStore$Settings;)I
    //   29: if_icmplt +13 -> 42
    //   32: aload_0
    //   33: getfield 102	com/microsoft/cll/android/CriticalEventHandler:clientTelemetry	Lcom/microsoft/cll/android/ClientTelemetry;
    //   36: invokevirtual 107	com/microsoft/cll/android/ClientTelemetry:IncrementEventsDroppedDueToQuota	()V
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_0
    //   43: getfield 38	com/microsoft/cll/android/CriticalEventHandler:logger	Lcom/microsoft/cll/android/ILogger;
    //   46: ldc 12
    //   48: ldc 109
    //   50: invokeinterface 112 3 0
    //   55: aload_0
    //   56: invokespecial 114	com/microsoft/cll/android/CriticalEventHandler:dropNormalFile	()V
    //   59: iload_3
    //   60: iconst_1
    //   61: iadd
    //   62: istore_3
    //   63: goto -49 -> 14
    //   66: aload_0
    //   67: getfield 25	com/microsoft/cll/android/CriticalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   70: aload_2
    //   71: invokevirtual 115	com/microsoft/cll/android/FileStorage:canAdd	(Lcom/microsoft/cll/android/Tuple;)Z
    //   74: ifne +45 -> 119
    //   77: aload_0
    //   78: getfield 38	com/microsoft/cll/android/CriticalEventHandler:logger	Lcom/microsoft/cll/android/ILogger;
    //   81: ldc 12
    //   83: ldc 117
    //   85: invokeinterface 46 3 0
    //   90: aload_0
    //   91: getfield 25	com/microsoft/cll/android/CriticalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   94: invokevirtual 120	com/microsoft/cll/android/FileStorage:close	()V
    //   97: aload_0
    //   98: new 16	com/microsoft/cll/android/FileStorage
    //   101: dup
    //   102: ldc 18
    //   104: aload_0
    //   105: getfield 38	com/microsoft/cll/android/CriticalEventHandler:logger	Lcom/microsoft/cll/android/ILogger;
    //   108: aload_0
    //   109: getfield 123	com/microsoft/cll/android/CriticalEventHandler:filePath	Ljava/lang/String;
    //   112: aload_0
    //   113: invokespecial 21	com/microsoft/cll/android/FileStorage:<init>	(Ljava/lang/String;Lcom/microsoft/cll/android/ILogger;Ljava/lang/String;Lcom/microsoft/cll/android/AbstractHandler;)V
    //   116: putfield 25	com/microsoft/cll/android/CriticalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   119: aload_0
    //   120: getfield 25	com/microsoft/cll/android/CriticalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   123: aload_2
    //   124: invokevirtual 126	com/microsoft/cll/android/FileStorage:add	(Lcom/microsoft/cll/android/Tuple;)V
    //   127: getstatic 56	com/microsoft/cll/android/CriticalEventHandler:totalStorageUsed	Ljava/util/concurrent/atomic/AtomicLong;
    //   130: aload_1
    //   131: invokevirtual 131	java/lang/String:length	()I
    //   134: i2l
    //   135: invokevirtual 67	java/util/concurrent/atomic/AtomicLong:getAndAdd	(J)J
    //   138: pop2
    //   139: aload_0
    //   140: getfield 25	com/microsoft/cll/android/CriticalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   143: invokevirtual 134	com/microsoft/cll/android/FileStorage:flush	()V
    //   146: goto -107 -> 39
    //   149: astore_1
    //   150: aload_0
    //   151: monitorexit
    //   152: aload_1
    //   153: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	154	0	this	CriticalEventHandler
    //   0	154	1	paramString	String
    //   0	154	2	paramList	java.util.List<String>
    //   13	50	3	i	int
    // Exception table:
    //   from	to	target	type
    //   2	12	149	finally
    //   14	39	149	finally
    //   42	59	149	finally
    //   66	119	149	finally
    //   119	146	149	finally
  }
  
  public void close()
  {
    this.logger.info("CriticalEventHandler", "Closing critical file");
    this.fileStorage.close();
  }
  
  public void dispose(IStorage paramIStorage)
  {
    totalStorageUsed.getAndAdd(-1L * paramIStorage.size());
  }
  
  /* Error */
  public java.util.List<IStorage> getFilesForDraining()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 25	com/microsoft/cll/android/CriticalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   6: invokevirtual 149	com/microsoft/cll/android/FileStorage:size	()J
    //   9: lconst_0
    //   10: lcmp
    //   11: ifle +43 -> 54
    //   14: aload_0
    //   15: getfield 25	com/microsoft/cll/android/CriticalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   18: invokevirtual 120	com/microsoft/cll/android/FileStorage:close	()V
    //   21: aload_0
    //   22: ldc 18
    //   24: invokevirtual 153	com/microsoft/cll/android/CriticalEventHandler:getFilesByExtensionForDraining	(Ljava/lang/String;)Ljava/util/List;
    //   27: astore_1
    //   28: aload_0
    //   29: new 16	com/microsoft/cll/android/FileStorage
    //   32: dup
    //   33: ldc 18
    //   35: aload_0
    //   36: getfield 38	com/microsoft/cll/android/CriticalEventHandler:logger	Lcom/microsoft/cll/android/ILogger;
    //   39: aload_0
    //   40: getfield 123	com/microsoft/cll/android/CriticalEventHandler:filePath	Ljava/lang/String;
    //   43: aload_0
    //   44: invokespecial 21	com/microsoft/cll/android/FileStorage:<init>	(Ljava/lang/String;Lcom/microsoft/cll/android/ILogger;Ljava/lang/String;Lcom/microsoft/cll/android/AbstractHandler;)V
    //   47: putfield 25	com/microsoft/cll/android/CriticalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: areturn
    //   54: aload_0
    //   55: ldc 18
    //   57: invokevirtual 153	com/microsoft/cll/android/CriticalEventHandler:getFilesByExtensionForDraining	(Ljava/lang/String;)Ljava/util/List;
    //   60: astore_1
    //   61: goto -11 -> 50
    //   64: astore_1
    //   65: aload_0
    //   66: monitorexit
    //   67: aload_1
    //   68: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	this	CriticalEventHandler
    //   27	34	1	localList	java.util.List
    //   64	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	50	64	finally
    //   54	61	64	finally
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\CriticalEventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */