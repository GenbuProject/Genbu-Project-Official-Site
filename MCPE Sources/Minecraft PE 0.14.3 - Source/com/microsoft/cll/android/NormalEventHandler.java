package com.microsoft.cll.android;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class NormalEventHandler
  extends AbstractHandler
{
  private final String TAG = "NormalEventHandler";
  private final int queueSize = SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.NORMALEVENTMEMORYQUEUESIZE);
  private ArrayBlockingQueue<Tuple<String, List<String>>> queueStorage = new ArrayBlockingQueue(this.queueSize);
  
  public NormalEventHandler(ILogger paramILogger, String paramString, ClientTelemetry paramClientTelemetry)
  {
    super(paramILogger, paramString, paramClientTelemetry);
    this.fileStorage = new FileStorage(".norm.cllevent", paramILogger, paramString, this);
  }
  
  public void add(String paramString, List<String> paramList)
  {
    try
    {
      paramString = new Tuple(paramString, paramList);
      if (!this.queueStorage.offer(paramString))
      {
        writeQueueToDisk();
        this.queueStorage.offer(paramString);
      }
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void close()
  {
    this.logger.info("NormalEventHandler", "Closing normal file");
    writeQueueToDisk();
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
    //   3: getfield 51	com/microsoft/cll/android/NormalEventHandler:queueStorage	Ljava/util/concurrent/ArrayBlockingQueue;
    //   6: invokevirtual 109	java/util/concurrent/ArrayBlockingQueue:size	()I
    //   9: ifle +7 -> 16
    //   12: aload_0
    //   13: invokevirtual 67	com/microsoft/cll/android/NormalEventHandler:writeQueueToDisk	()V
    //   16: aload_0
    //   17: getfield 44	com/microsoft/cll/android/NormalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   20: invokevirtual 110	com/microsoft/cll/android/FileStorage:size	()J
    //   23: lconst_0
    //   24: lcmp
    //   25: ifle +43 -> 68
    //   28: aload_0
    //   29: getfield 44	com/microsoft/cll/android/NormalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   32: invokevirtual 84	com/microsoft/cll/android/FileStorage:close	()V
    //   35: aload_0
    //   36: ldc 37
    //   38: invokevirtual 114	com/microsoft/cll/android/NormalEventHandler:getFilesByExtensionForDraining	(Ljava/lang/String;)Ljava/util/List;
    //   41: astore_1
    //   42: aload_0
    //   43: new 35	com/microsoft/cll/android/FileStorage
    //   46: dup
    //   47: ldc 37
    //   49: aload_0
    //   50: getfield 74	com/microsoft/cll/android/NormalEventHandler:logger	Lcom/microsoft/cll/android/ILogger;
    //   53: aload_0
    //   54: getfield 117	com/microsoft/cll/android/NormalEventHandler:filePath	Ljava/lang/String;
    //   57: aload_0
    //   58: invokespecial 40	com/microsoft/cll/android/FileStorage:<init>	(Ljava/lang/String;Lcom/microsoft/cll/android/ILogger;Ljava/lang/String;Lcom/microsoft/cll/android/AbstractHandler;)V
    //   61: putfield 44	com/microsoft/cll/android/NormalEventHandler:fileStorage	Lcom/microsoft/cll/android/FileStorage;
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: areturn
    //   68: aload_0
    //   69: ldc 37
    //   71: invokevirtual 114	com/microsoft/cll/android/NormalEventHandler:getFilesByExtensionForDraining	(Ljava/lang/String;)Ljava/util/List;
    //   74: astore_1
    //   75: goto -11 -> 64
    //   78: astore_1
    //   79: aload_0
    //   80: monitorexit
    //   81: aload_1
    //   82: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	this	NormalEventHandler
    //   41	34	1	localList	List
    //   78	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	16	78	finally
    //   16	64	78	finally
    //   68	75	78	finally
  }
  
  void writeQueueToDisk()
  {
    try
    {
      Object localObject1 = new ArrayList(this.queueSize);
      this.queueStorage.drainTo((Collection)localObject1);
      this.logger.info("NormalEventHandler", "Writing " + ((List)localObject1).size() + " events to disk");
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localTuple = (Tuple)((Iterator)localObject1).next();
        if (canAdd(localTuple)) {
          break label144;
        }
        this.logger.info("NormalEventHandler", "Dropping event due to reaching max file storage");
        this.clientTelemetry.IncrementEventsDroppedDueToQuota();
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Tuple localTuple;
        this.logger.error("NormalEventHandler", "Could not write events to disk");
        this.fileStorage.flush();
        return;
        label144:
        if (!this.fileStorage.canAdd(localTuple))
        {
          this.logger.info("NormalEventHandler", "Closing full file and opening a new one");
          this.fileStorage.close();
          this.fileStorage = new FileStorage(".norm.cllevent", this.logger, this.filePath, this);
        }
        this.fileStorage.add(localTuple);
        totalStorageUsed.getAndAdd(((String)localTuple.a).length());
      }
    }
    finally {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\NormalEventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */