package com.microsoft.xbox.toolkit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class XLEFileCache
{
  private static final String TAG = XLEFileCache.class.getSimpleName();
  private boolean enabled = true;
  private final long expiredTimer;
  final int maxFileNumber;
  private int readAccessCnt = 0;
  private int readSuccessfulCnt = 0;
  int size = 0;
  private int writeAccessCnt = 0;
  
  XLEFileCache()
  {
    this.expiredTimer = Long.MAX_VALUE;
    this.maxFileNumber = 0;
    this.enabled = false;
  }
  
  XLEFileCache(String paramString, int paramInt)
  {
    this(paramString, paramInt, Long.MAX_VALUE);
  }
  
  XLEFileCache(String paramString, int paramInt, long paramLong)
  {
    this.maxFileNumber = paramInt;
    this.expiredTimer = paramLong;
  }
  
  private void checkAndEnsureCapacity()
  {
    if ((this.size >= this.maxFileNumber) && (this.enabled))
    {
      File[] arrayOfFile = XLEFileCacheManager.getCacheRootDir(this).listFiles();
      arrayOfFile[new java.util.Random().nextInt(arrayOfFile.length)].delete();
      this.size = (arrayOfFile.length - 1);
    }
  }
  
  private String getCachedItemFileName(XLEFileCacheItemKey paramXLEFileCacheItemKey)
  {
    return String.valueOf(paramXLEFileCacheItemKey.getKeyString().hashCode());
  }
  
  private static int readInt(InputStream paramInputStream)
    throws IOException
  {
    int i = paramInputStream.read();
    int j = paramInputStream.read();
    int k = paramInputStream.read();
    int m = paramInputStream.read();
    if ((i | j | k | m) < 0) {
      throw new EOFException();
    }
    return (i << 24) + (j << 16) + (k << 8) + (m << 0);
  }
  
  /* Error */
  public boolean contains(XLEFileCacheItemKey paramXLEFileCacheItemKey)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 42	com/microsoft/xbox/toolkit/XLEFileCache:enabled	Z
    //   6: istore_2
    //   7: iload_2
    //   8: ifne +9 -> 17
    //   11: iconst_0
    //   12: istore_2
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_2
    //   16: ireturn
    //   17: new 75	java/io/File
    //   20: dup
    //   21: aload_0
    //   22: invokestatic 73	com/microsoft/xbox/toolkit/XLEFileCacheManager:getCacheRootDir	(Lcom/microsoft/xbox/toolkit/XLEFileCache;)Ljava/io/File;
    //   25: aload_0
    //   26: aload_1
    //   27: invokespecial 119	com/microsoft/xbox/toolkit/XLEFileCache:getCachedItemFileName	(Lcom/microsoft/xbox/toolkit/XLEFileCacheItemKey;)Ljava/lang/String;
    //   30: invokespecial 122	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   33: invokevirtual 125	java/io/File:exists	()Z
    //   36: istore_2
    //   37: goto -24 -> 13
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	XLEFileCache
    //   0	45	1	paramXLEFileCacheItemKey	XLEFileCacheItemKey
    //   6	31	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	7	40	finally
    //   17	37	40	finally
  }
  
  public InputStream getInputStreamForRead(XLEFileCacheItemKey paramXLEFileCacheItemKey)
  {
    Object localObject2 = null;
    for (;;)
    {
      Object localObject1;
      File localFile;
      try
      {
        boolean bool = this.enabled;
        if (!bool)
        {
          localObject1 = localObject2;
          return (InputStream)localObject1;
        }
        if (Thread.currentThread() != ThreadManager.UIThread)
        {
          bool = true;
          XLEAssert.assertTrue(bool);
          this.readAccessCnt += 1;
          localFile = new File(XLEFileCacheManager.getCacheRootDir(this), getCachedItemFileName(paramXLEFileCacheItemKey));
          localObject1 = localObject2;
          if (!localFile.exists()) {
            continue;
          }
          if (localFile.lastModified() >= System.currentTimeMillis() - this.expiredTimer) {
            break label124;
          }
          localFile.delete();
          this.size -= 1;
          localObject1 = localObject2;
          continue;
        }
        bool = false;
      }
      finally {}
      continue;
      try
      {
        label124:
        localObject1 = new CachedFileInputStreamItem(paramXLEFileCacheItemKey, localFile).getContentInputStream();
        this.readSuccessfulCnt += 1;
      }
      catch (IOException paramXLEFileCacheItemKey)
      {
        localObject1 = localObject2;
      }
    }
  }
  
  public int getItemsInCache()
  {
    return this.size;
  }
  
  public OutputStream getOuputStreamForSave(XLEFileCacheItemKey paramXLEFileCacheItemKey)
    throws IOException
  {
    for (;;)
    {
      try
      {
        if (!this.enabled)
        {
          paramXLEFileCacheItemKey = new OutputStream()
          {
            public void write(int paramAnonymousInt)
              throws IOException
            {}
          };
          return paramXLEFileCacheItemKey;
        }
        boolean bool;
        if (Thread.currentThread() != ThreadManager.UIThread)
        {
          bool = true;
          XLEAssert.assertTrue(bool);
          this.writeAccessCnt += 1;
          checkAndEnsureCapacity();
          File localFile = new File(XLEFileCacheManager.getCacheRootDir(this), getCachedItemFileName(paramXLEFileCacheItemKey));
          if (localFile.exists())
          {
            localFile.delete();
            this.size -= 1;
          }
          if (localFile.createNewFile()) {
            this.size += 1;
          }
          paramXLEFileCacheItemKey = new CachedFileOutputStreamItem(paramXLEFileCacheItemKey, localFile);
        }
        else
        {
          bool = false;
        }
      }
      finally {}
    }
  }
  
  /* Error */
  public void save(XLEFileCacheItemKey paramXLEFileCacheItemKey, InputStream paramInputStream)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 177	com/microsoft/xbox/toolkit/XLEFileCache:getOuputStreamForSave	(Lcom/microsoft/xbox/toolkit/XLEFileCacheItemKey;)Ljava/io/OutputStream;
    //   7: astore_1
    //   8: aload_1
    //   9: aload_2
    //   10: invokestatic 183	com/microsoft/xbox/toolkit/StreamUtil:CopyStream	(Ljava/io/OutputStream;Ljava/io/InputStream;)V
    //   13: aload_1
    //   14: invokevirtual 188	java/io/OutputStream:close	()V
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    //   25: astore_1
    //   26: goto -9 -> 17
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	29	0	this	XLEFileCache
    //   0	29	1	paramXLEFileCacheItemKey	XLEFileCacheItemKey
    //   0	29	2	paramInputStream	InputStream
    // Exception table:
    //   from	to	target	type
    //   2	17	20	finally
    //   2	17	25	java/io/IOException
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("Size=");
    localStringBuilder.append(this.size);
    localStringBuilder.append("\tRootDir=");
    localStringBuilder.append(XLEFileCacheManager.getCacheRootDir(this));
    localStringBuilder.append("\tMaxFileNumber=");
    localStringBuilder.append(this.maxFileNumber);
    localStringBuilder.append("\tExpiredTimerInSeconds=");
    localStringBuilder.append(this.expiredTimer);
    localStringBuilder.append("\tWriteAccessCnt=");
    localStringBuilder.append(this.writeAccessCnt);
    localStringBuilder.append("\tReadAccessCnt=");
    localStringBuilder.append(this.readAccessCnt);
    localStringBuilder.append("\tReadSuccessfulCnt=");
    localStringBuilder.append(this.readSuccessfulCnt);
    return localStringBuilder.toString();
  }
  
  private class CachedFileInputStreamItem
  {
    private byte[] computedMd5;
    private InputStream contentInputStream;
    private MessageDigest mDigest = null;
    private byte[] savedMd5;
    
    public CachedFileInputStreamItem(XLEFileCacheItemKey paramXLEFileCacheItemKey, File paramFile)
      throws IOException
    {
      this$1 = new FileInputStream(paramFile);
      try
      {
        this.mDigest = MessageDigest.getInstance("MD5");
        this.savedMd5 = new byte[this.mDigest.getDigestLength()];
        if (XLEFileCache.this.read(this.savedMd5) != this.mDigest.getDigestLength())
        {
          XLEFileCache.this.close();
          throw new IOException("Ddigest lengh check failed!");
        }
      }
      catch (NoSuchAlgorithmException paramXLEFileCacheItemKey)
      {
        XLEFileCache.this.close();
        throw new IOException("File digest failed! " + paramXLEFileCacheItemKey.getMessage());
        int i = XLEFileCache.readInt(XLEFileCache.this);
        byte[] arrayOfByte = new byte[i];
        if ((i != XLEFileCache.this.read(arrayOfByte)) || (!paramXLEFileCacheItemKey.getKeyString().equals(new String(arrayOfByte))))
        {
          paramFile.delete();
          throw new IOException("File key check failed because keyLength != readKeyLength or !key.getKeyString().equals(new String(urlOrSomething))");
        }
      }
      catch (OutOfMemoryError paramXLEFileCacheItemKey)
      {
        XLEFileCache.this.close();
        throw new IOException("File digest failed! Out of memory: " + paramXLEFileCacheItemKey.getMessage());
      }
      paramXLEFileCacheItemKey = new ByteArrayOutputStream();
      StreamUtil.CopyStream(paramXLEFileCacheItemKey, XLEFileCache.this);
      XLEFileCache.this.close();
      paramXLEFileCacheItemKey = paramXLEFileCacheItemKey.toByteArray();
      this.mDigest.update(paramXLEFileCacheItemKey);
      this.computedMd5 = this.mDigest.digest();
      if (isMd5Error())
      {
        paramFile.delete();
        throw new IOException(XLEFileCache.this.getFD() + "the saved md5 is not equal computed md5." + "ComputedMd5:" + this.computedMd5 + "     SavedMd5:" + this.savedMd5);
      }
      this.contentInputStream = new ByteArrayInputStream(paramXLEFileCacheItemKey);
    }
    
    private boolean isMd5Error()
    {
      boolean bool2 = false;
      int i = 0;
      for (;;)
      {
        boolean bool1 = bool2;
        if (i < this.mDigest.getDigestLength())
        {
          if (this.savedMd5[i] != this.computedMd5[i]) {
            bool1 = true;
          }
        }
        else {
          return bool1;
        }
        i += 1;
      }
    }
    
    public InputStream getContentInputStream()
    {
      return this.contentInputStream;
    }
  }
  
  private class CachedFileOutputStreamItem
    extends FileOutputStream
  {
    private File destFile;
    private MessageDigest mDigest = null;
    private boolean startDigest = false;
    private boolean writeMd5Finished = false;
    
    public CachedFileOutputStreamItem(XLEFileCacheItemKey paramXLEFileCacheItemKey, File paramFile)
      throws IOException
    {
      super();
      this.destFile = paramFile;
      try
      {
        this.mDigest = MessageDigest.getInstance("MD5");
        write(new byte[this.mDigest.getDigestLength()]);
        this$1 = paramXLEFileCacheItemKey.getKeyString().getBytes();
        writeInt(XLEFileCache.this.length);
        write(XLEFileCache.this);
        this.startDigest = true;
        return;
      }
      catch (NoSuchAlgorithmException this$1)
      {
        throw new IOException("File digest failed!" + XLEFileCache.this.getMessage());
      }
    }
    
    private final void writeInt(int paramInt)
      throws IOException
    {
      write(paramInt >>> 24 & 0xFF);
      write(paramInt >>> 16 & 0xFF);
      write(paramInt >>> 8 & 0xFF);
      write(paramInt >>> 0 & 0xFF);
    }
    
    public void close()
      throws IOException
    {
      super.close();
      if (!this.writeMd5Finished)
      {
        this.writeMd5Finished = true;
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(this.destFile, "rw");
        byte[] arrayOfByte = this.mDigest.digest();
        localRandomAccessFile.seek(0L);
        localRandomAccessFile.write(arrayOfByte);
        localRandomAccessFile.close();
      }
    }
    
    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException
    {
      super.write(paramArrayOfByte, paramInt1, paramInt2);
      if (this.startDigest) {
        this.mDigest.update(paramArrayOfByte, paramInt1, paramInt2);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\XLEFileCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */