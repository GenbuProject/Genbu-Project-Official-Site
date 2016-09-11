package com.microsoft.cll.android;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class CriticalEventHandler
  extends AbstractHandler
{
  private final String TAG = "AndroidCll-CriticalEventHandler";
  
  public CriticalEventHandler(ILogger paramILogger, String paramString, ClientTelemetry paramClientTelemetry)
  {
    super(paramILogger, paramString, paramClientTelemetry);
    this.fileStorage = new FileStorage(".crit.cllevent", paramILogger, paramString, this);
  }
  
  public void add(String paramString, List<String> paramList)
    throws IOException, FileStorage.FileFullException
  {
    try
    {
      paramList = new Tuple(paramString, paramList);
      if (!ensureCanAdd(paramList, EventEnums.Persistence.PersistenceCritical))
      {
        this.clientTelemetry.IncrementEventsDroppedDueToQuota();
        this.logger.warn("AndroidCll-CriticalEventHandler", "Out of storage space for critical events. Logged event was dropped.");
      }
      if (!this.fileStorage.canAdd(paramList))
      {
        this.logger.info("AndroidCll-CriticalEventHandler", "Closing full file and opening a new one");
        this.fileStorage.close();
        this.fileStorage = new FileStorage(".crit.cllevent", this.logger, this.filePath, this);
      }
      this.fileStorage.add(paramList);
      totalStorageUsed.getAndAdd(paramString.length());
      this.fileStorage.flush();
      return;
    }
    finally {}
  }
  
  public void close()
  {
    this.logger.info("AndroidCll-CriticalEventHandler", "Closing critical file");
    this.fileStorage.close();
  }
  
  public void dispose(IStorage paramIStorage)
  {
    totalStorageUsed.getAndAdd(-1L * paramIStorage.size());
  }
  
  /* Error */
  public List<IStorage> getFilesForDraining()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 25	com/microsoft/cll/android/CriticalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   6: invokevirtual 124	com/microsoft/cll/android/FileStorage:size	()J
    //   9: lconst_0
    //   10: lcmp
    //   11: ifle +43 -> 54
    //   14: aload_0
    //   15: getfield 25	com/microsoft/cll/android/CriticalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   18: invokevirtual 81	com/microsoft/cll/android/FileStorage:close	()V
    //   21: aload_0
    //   22: ldc 18
    //   24: invokevirtual 128	com/microsoft/cll/android/CriticalEventHandler:getFilesByExtensionForDraining	(Ljava/lang/String;)Ljava/util/List;
    //   27: astore_1
    //   28: aload_0
    //   29: new 16	com/microsoft/cll/android/FileStorage
    //   32: dup
    //   33: ldc 18
    //   35: aload_0
    //   36: getfield 61	com/microsoft/cll/android/CriticalEventHandler:logger	Lcom/microsoft/cll/android/ILogger;
    //   39: aload_0
    //   40: getfield 84	com/microsoft/cll/android/CriticalEventHandler:filePath	Ljava/lang/String;
    //   43: aload_0
    //   44: invokespecial 21	com/microsoft/cll/android/FileStorage:<init>	(Ljava/lang/String;Lcom/microsoft/cll/android/ILogger;Ljava/lang/String;Lcom/microsoft/cll/android/AbstractHandler;)V
    //   47: putfield 25	com/microsoft/cll/android/CriticalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: areturn
    //   54: aload_0
    //   55: ldc 18
    //   57: invokevirtual 128	com/microsoft/cll/android/CriticalEventHandler:getFilesByExtensionForDraining	(Ljava/lang/String;)Ljava/util/List;
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
    //   27	34	1	localList	List
    //   64	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	50	64	finally
    //   54	61	64	finally
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\CriticalEventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */