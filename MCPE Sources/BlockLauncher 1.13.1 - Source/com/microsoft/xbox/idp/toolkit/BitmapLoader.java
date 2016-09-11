package com.microsoft.xbox.idp.toolkit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.microsoft.xbox.idp.util.HttpCall;
import com.microsoft.xbox.idp.util.HttpCall.Callback;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BitmapLoader
  extends WorkerLoader<Result>
{
  private static final String TAG = BitmapLoader.class.getSimpleName();
  
  public BitmapLoader(Context paramContext, Cache paramCache, Object paramObject, HttpCall paramHttpCall)
  {
    super(paramContext, new MyWorker(paramCache, paramObject, paramHttpCall, null));
  }
  
  public BitmapLoader(Context paramContext, HttpCall paramHttpCall)
  {
    this(paramContext, null, null, paramHttpCall);
  }
  
  private static void readStream(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    for (;;)
    {
      int i = paramInputStream.read();
      if (i == -1) {
        break;
      }
      paramOutputStream.write(i);
    }
  }
  
  protected boolean isDataReleased(Result paramResult)
  {
    return paramResult.isReleased();
  }
  
  protected void releaseData(Result paramResult)
  {
    paramResult.release();
  }
  
  public static abstract interface Cache
  {
    public abstract void clear();
    
    public abstract byte[] get(Object paramObject);
    
    public abstract byte[] put(Object paramObject, byte[] paramArrayOfByte);
    
    public abstract byte[] remove(Object paramObject);
  }
  
  private static class MyWorker
    implements WorkerLoader.Worker<BitmapLoader.Result>
  {
    private final BitmapLoader.Cache cache;
    private final HttpCall httpCall;
    private final Object resultKey;
    
    static
    {
      if (!BitmapLoader.class.desiredAssertionStatus()) {}
      for (boolean bool = true;; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }
    
    private MyWorker(BitmapLoader.Cache paramCache, Object paramObject, HttpCall paramHttpCall)
    {
      assert (paramHttpCall != null);
      this.cache = paramCache;
      this.resultKey = paramObject;
      this.httpCall = paramHttpCall;
    }
    
    private boolean hasCache()
    {
      return (this.cache != null) && (this.resultKey != null);
    }
    
    public void cancel() {}
    
    public void start(final WorkerLoader.ResultListener<BitmapLoader.Result> paramResultListener)
    {
      if (hasCache()) {
        synchronized (this.cache)
        {
          final byte[] arrayOfByte = this.cache.get(this.resultKey);
          if (arrayOfByte != null)
          {
            new Thread(new Runnable()
            {
              public void run()
              {
                paramResultListener.onResult(new BitmapLoader.Result(BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length)));
              }
            }).start();
            return;
          }
        }
      }
      this.httpCall.getResponseAsync(new HttpCall.Callback()
      {
        public void processHttpError(int paramAnonymousInt1, int paramAnonymousInt2, String paramAnonymousString)
        {
          Log.e(BitmapLoader.TAG, "errorCode: " + paramAnonymousInt1 + ", httpStatus: " + paramAnonymousInt2 + ", errorMessage: " + paramAnonymousString);
          paramResultListener.onResult(new BitmapLoader.Result(new HttpError(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousString)));
        }
        
        /* Error */
        public void processResponse(InputStream paramAnonymousInputStream)
          throws java.lang.Exception
        {
          // Byte code:
          //   0: new 80	java/io/ByteArrayOutputStream
          //   3: dup
          //   4: invokespecial 81	java/io/ByteArrayOutputStream:<init>	()V
          //   7: astore_2
          //   8: new 83	java/io/BufferedInputStream
          //   11: dup
          //   12: aload_1
          //   13: invokespecial 85	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
          //   16: astore_1
          //   17: aload_1
          //   18: aload_2
          //   19: invokestatic 89	com/microsoft/xbox/idp/toolkit/BitmapLoader:access$100	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
          //   22: aload_2
          //   23: invokevirtual 93	java/io/ByteArrayOutputStream:toByteArray	()[B
          //   26: astore 4
          //   28: aload_0
          //   29: getfield 22	com/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker$2:this$0	Lcom/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker;
          //   32: invokestatic 97	com/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker:access$200	(Lcom/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker;)Z
          //   35: ifeq +37 -> 72
          //   38: aload_0
          //   39: getfield 22	com/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker$2:this$0	Lcom/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker;
          //   42: invokestatic 101	com/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker:access$300	(Lcom/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker;)Lcom/microsoft/xbox/idp/toolkit/BitmapLoader$Cache;
          //   45: astore_3
          //   46: aload_3
          //   47: monitorenter
          //   48: aload_0
          //   49: getfield 22	com/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker$2:this$0	Lcom/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker;
          //   52: invokestatic 101	com/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker:access$300	(Lcom/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker;)Lcom/microsoft/xbox/idp/toolkit/BitmapLoader$Cache;
          //   55: aload_0
          //   56: getfield 22	com/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker$2:this$0	Lcom/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker;
          //   59: invokestatic 105	com/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker:access$400	(Lcom/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker;)Ljava/lang/Object;
          //   62: aload 4
          //   64: invokeinterface 111 3 0
          //   69: pop
          //   70: aload_3
          //   71: monitorexit
          //   72: aload_0
          //   73: getfield 24	com/microsoft/xbox/idp/toolkit/BitmapLoader$MyWorker$2:val$listener	Lcom/microsoft/xbox/idp/toolkit/WorkerLoader$ResultListener;
          //   76: new 61	com/microsoft/xbox/idp/toolkit/BitmapLoader$Result
          //   79: dup
          //   80: aload 4
          //   82: iconst_0
          //   83: aload 4
          //   85: arraylength
          //   86: invokestatic 117	android/graphics/BitmapFactory:decodeByteArray	([BII)Landroid/graphics/Bitmap;
          //   89: invokespecial 120	com/microsoft/xbox/idp/toolkit/BitmapLoader$Result:<init>	(Landroid/graphics/Bitmap;)V
          //   92: invokeinterface 74 2 0
          //   97: aload_1
          //   98: invokevirtual 123	java/io/BufferedInputStream:close	()V
          //   101: aload_2
          //   102: invokevirtual 124	java/io/ByteArrayOutputStream:close	()V
          //   105: return
          //   106: astore 4
          //   108: aload_3
          //   109: monitorexit
          //   110: aload 4
          //   112: athrow
          //   113: astore_3
          //   114: aload_1
          //   115: invokevirtual 123	java/io/BufferedInputStream:close	()V
          //   118: aload_3
          //   119: athrow
          //   120: astore_1
          //   121: aload_2
          //   122: invokevirtual 124	java/io/ByteArrayOutputStream:close	()V
          //   125: aload_1
          //   126: athrow
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	127	0	this	2
          //   0	127	1	paramAnonymousInputStream	InputStream
          //   7	115	2	localByteArrayOutputStream	java.io.ByteArrayOutputStream
          //   113	6	3	localObject1	Object
          //   26	58	4	arrayOfByte	byte[]
          //   106	5	4	localObject2	Object
          // Exception table:
          //   from	to	target	type
          //   48	72	106	finally
          //   108	110	106	finally
          //   17	48	113	finally
          //   72	97	113	finally
          //   110	113	113	finally
          //   8	17	120	finally
          //   97	101	120	finally
          //   114	120	120	finally
        }
      });
    }
  }
  
  public static class Result
    extends LoaderResult<Bitmap>
  {
    protected Result(Bitmap paramBitmap)
    {
      super(null);
    }
    
    protected Result(HttpError paramHttpError)
    {
      super(paramHttpError);
    }
    
    public boolean isReleased()
    {
      return (hasData()) && (((Bitmap)getData()).isRecycled());
    }
    
    public void release()
    {
      if (hasData()) {
        ((Bitmap)getData()).recycle();
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\toolkit\BitmapLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */