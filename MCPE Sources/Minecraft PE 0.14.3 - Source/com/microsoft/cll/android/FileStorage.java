package com.microsoft.cll.android;

import com.microsoft.telemetry.IJsonSerializable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class FileStorage
  implements IStorage
{
  protected static final SynchronizedArrayList<String> fileLockList = new SynchronizedArrayList();
  private final String TAG = "FileStorage";
  private int eventsWritten;
  private String filePathAndName;
  private long fileSize;
  private FileReader inputFile;
  private boolean isOpen;
  private boolean isWritable;
  private final ILogger logger;
  private FileWriter outputFile;
  private AbstractHandler parent;
  private BufferedReader reader;
  private final EventSerializer serializer;
  
  public FileStorage(ILogger paramILogger, String paramString, AbstractHandler paramAbstractHandler)
    throws Exception
  {
    this.logger = paramILogger;
    this.serializer = new EventSerializer(paramILogger);
    this.filePathAndName = paramString;
    this.parent = paramAbstractHandler;
    if (fileLockList.contains(paramString)) {
      throw new Exception("Could not get lock for file");
    }
  }
  
  public FileStorage(String paramString1, ILogger paramILogger, String paramString2, AbstractHandler paramAbstractHandler)
  {
    this.eventsWritten = 0;
    this.fileSize = 0L;
    this.filePathAndName = (paramString2 + File.separator + UUID.randomUUID() + paramString1);
    this.logger = paramILogger;
    this.serializer = new EventSerializer(paramILogger);
    this.parent = paramAbstractHandler;
    int i = 1;
    while (!openFile())
    {
      this.filePathAndName = (paramString2 + "/" + UUID.randomUUID() + paramString1);
      int j = i + 1;
      i = j;
      if (j >= 5) {
        paramILogger.error("FileStorage", "Could not create a file");
      }
    }
  }
  
  private boolean getLock()
  {
    return fileLockList.add(this.filePathAndName);
  }
  
  private boolean openFile()
  {
    if (!getLock())
    {
      this.logger.info("FileStorage", "Could not get lock for file");
      return false;
    }
    File localFile = new File(this.filePathAndName);
    if (localFile.exists()) {
      this.isWritable = false;
    }
    for (;;)
    {
      try
      {
        this.inputFile = new FileReader(this.filePathAndName);
        this.reader = new BufferedReader(this.inputFile);
        this.fileSize = localFile.length();
        this.isOpen = true;
        return true;
      }
      catch (IOException localIOException1)
      {
        this.logger.error("FileStorage", "Event file was not found");
        return false;
      }
      this.isWritable = true;
      this.logger.info("FileStorage", "Creating new file");
      try
      {
        this.outputFile = new FileWriter(this.filePathAndName);
      }
      catch (IOException localIOException2)
      {
        this.logger.error("FileStorage", "Error opening file");
      }
    }
    return false;
  }
  
  public void add(Tuple<String, List<String>> paramTuple)
    throws FileStorage.FileFullException, IOException
  {
    if ((!this.isOpen) || (!this.isWritable))
    {
      this.logger.warn("FileStorage", "This file is not open or not writable");
      return;
    }
    if (!canAdd(paramTuple)) {
      throw new FileFullException("The file is already full!");
    }
    if (paramTuple.b != null)
    {
      Iterator localIterator = ((List)paramTuple.b).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        this.outputFile.write("x:" + str + "\r\n");
      }
    }
    this.outputFile.write((String)paramTuple.a);
    this.eventsWritten += 1;
    long l = this.fileSize;
    this.fileSize = (((String)paramTuple.a).length() + l);
  }
  
  public void add(IJsonSerializable paramIJsonSerializable)
    throws FileStorage.FileFullException, IOException
  {
    add(new Tuple(this.serializer.serialize(paramIJsonSerializable), null));
  }
  
  public boolean canAdd(Tuple<String, List<String>> paramTuple)
  {
    if ((!this.isOpen) || (!this.isWritable))
    {
      this.logger.warn("FileStorage", "This file is not open or not writable");
      return false;
    }
    int i = ((String)paramTuple.a).length();
    return (this.eventsWritten < SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXEVENTSPERPOST)) && (i + this.fileSize < SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXEVENTSIZEINBYTES));
  }
  
  public boolean canAdd(IJsonSerializable paramIJsonSerializable)
  {
    return canAdd(new Tuple(this.serializer.serialize(paramIJsonSerializable), null));
  }
  
  public void close()
  {
    if (this.isOpen)
    {
      flush();
      fileLockList.remove(this.filePathAndName);
      try
      {
        if (this.isWritable) {
          this.outputFile.close();
        }
        for (;;)
        {
          this.isOpen = false;
          return;
          this.inputFile.close();
          this.reader.close();
        }
        return;
      }
      catch (Exception localException)
      {
        this.logger.error("FileStorage", "Error when closing file");
      }
    }
  }
  
  public void discard()
  {
    this.logger.info("FileStorage", "Discarding file");
    close();
    this.parent.dispose(this);
    new File(this.filePathAndName).delete();
  }
  
  public List<Tuple<String, List<String>>> drain()
  {
    localArrayList2 = new ArrayList();
    if (!this.isOpen) {
      try
      {
        boolean bool = openFile();
        if (!bool) {
          return localArrayList2;
        }
      }
      catch (Exception localException1)
      {
        this.logger.error("FileStorage", "Error opening file");
        return localArrayList2;
      }
    }
    try
    {
      String str = this.reader.readLine();
      ArrayList localArrayList1 = new ArrayList();
      if (str != null)
      {
        if (str.startsWith("x:")) {
          localArrayList1.add(str.substring(2));
        }
        for (;;)
        {
          str = this.reader.readLine();
          break;
          if (localArrayList1.size() > 0)
          {
            localArrayList2.add(new Tuple(str, localArrayList1));
            localArrayList1 = new ArrayList();
          }
          else
          {
            localArrayList2.add(new Tuple(str, null));
          }
        }
      }
      return localArrayList2;
    }
    catch (Exception localException2)
    {
      this.logger.error("FileStorage", "Error reading from input file");
      this.logger.info("FileStorage", "Read " + localArrayList2.size() + " events from file");
    }
  }
  
  public void flush()
  {
    if ((!this.isOpen) || (!this.isWritable)) {
      return;
    }
    try
    {
      this.outputFile.flush();
      return;
    }
    catch (Exception localException)
    {
      this.logger.error("FileStorage", "Could not flush file");
    }
  }
  
  public long size()
  {
    if (!this.isOpen) {
      return new File(this.filePathAndName).length();
    }
    return this.fileSize;
  }
  
  class FileFullException
    extends Exception
  {
    public FileFullException(String paramString)
    {
      super();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\FileStorage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */