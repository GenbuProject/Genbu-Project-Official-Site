package com.microsoft.xbox.idp.toolkit;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.microsoft.xbox.idp.util.HttpCall;
import com.microsoft.xbox.idp.util.HttpCall.Callback;

public class ObjectLoader<T>
  extends WorkerLoader<Result<T>>
{
  private static final String TAG = ObjectLoader.class.getSimpleName();
  
  public ObjectLoader(Context paramContext, Cache paramCache, Object paramObject, Class<T> paramClass, Gson paramGson, HttpCall paramHttpCall)
  {
    super(paramContext, new MyWorker(paramCache, paramObject, paramClass, paramGson, paramHttpCall, null));
  }
  
  public ObjectLoader(Context paramContext, Class<T> paramClass, Gson paramGson, HttpCall paramHttpCall)
  {
    this(paramContext, null, null, paramClass, paramGson, paramHttpCall);
  }
  
  protected boolean isDataReleased(Result<T> paramResult)
  {
    return paramResult.isReleased();
  }
  
  protected void releaseData(Result<T> paramResult)
  {
    paramResult.release();
  }
  
  public static abstract interface Cache
  {
    public abstract void clear();
    
    public abstract <T> ObjectLoader.Result<T> get(Object paramObject);
    
    public abstract <T> ObjectLoader.Result<T> put(Object paramObject, ObjectLoader.Result<T> paramResult);
    
    public abstract <T> ObjectLoader.Result<T> remove(Object paramObject);
  }
  
  private static class MyWorker<T>
    implements WorkerLoader.Worker<ObjectLoader.Result<T>>
  {
    private final ObjectLoader.Cache cache;
    private final Class<T> cls;
    private final Gson gson;
    private final HttpCall httpCall;
    private final Object resultKey;
    
    private MyWorker(ObjectLoader.Cache paramCache, Object paramObject, Class<T> paramClass, Gson paramGson, HttpCall paramHttpCall)
    {
      this.cache = paramCache;
      this.resultKey = paramObject;
      this.cls = paramClass;
      this.gson = paramGson;
      this.httpCall = paramHttpCall;
    }
    
    private boolean hasCache()
    {
      return (this.cache != null) && (this.resultKey != null);
    }
    
    public void cancel() {}
    
    public void start(final WorkerLoader.ResultListener<ObjectLoader.Result<T>> paramResultListener)
    {
      if (hasCache()) {
        synchronized (this.cache)
        {
          ObjectLoader.Result localResult = this.cache.get(this.resultKey);
          if (localResult != null)
          {
            paramResultListener.onResult(localResult);
            return;
          }
        }
      }
      this.httpCall.getResponseAsync(new HttpCall.Callback()
      {
        public void processHttpError(int paramAnonymousInt1, int paramAnonymousInt2, String arg3)
        {
          Log.e(ObjectLoader.TAG, "errorCode: " + paramAnonymousInt1 + ", httpStatus: " + paramAnonymousInt2 + ", errorMessage: " + ???);
          ObjectLoader.Result localResult = new ObjectLoader.Result(new HttpError(paramAnonymousInt1, paramAnonymousInt2, ???));
          if (ObjectLoader.MyWorker.this.hasCache()) {}
          synchronized (ObjectLoader.MyWorker.this.cache)
          {
            ObjectLoader.MyWorker.this.cache.put(ObjectLoader.MyWorker.this.resultKey, localResult);
            paramResultListener.onResult(localResult);
            return;
          }
        }
        
        /* Error */
        public void processResponse(java.io.InputStream paramAnonymousInputStream)
          throws java.lang.Exception
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 22	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker$1:this$0	Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;
          //   4: invokestatic 100	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker:access$100	(Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;)Ljava/lang/Class;
          //   7: ldc 102
          //   9: if_acmpne +24 -> 33
          //   12: aload_0
          //   13: getfield 24	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker$1:val$listener	Lcom/microsoft/xbox/idp/toolkit/WorkerLoader$ResultListener;
          //   16: new 61	com/microsoft/xbox/idp/toolkit/ObjectLoader$Result
          //   19: dup
          //   20: aconst_null
          //   21: checkcast 4	java/lang/Object
          //   24: invokespecial 104	com/microsoft/xbox/idp/toolkit/ObjectLoader$Result:<init>	(Ljava/lang/Object;)V
          //   27: invokeinterface 92 2 0
          //   32: return
          //   33: new 106	java/io/StringWriter
          //   36: dup
          //   37: invokespecial 107	java/io/StringWriter:<init>	()V
          //   40: astore_2
          //   41: new 109	java/io/InputStreamReader
          //   44: dup
          //   45: new 111	java/io/BufferedInputStream
          //   48: dup
          //   49: aload_1
          //   50: invokespecial 113	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
          //   53: invokespecial 114	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
          //   56: astore_1
          //   57: new 61	com/microsoft/xbox/idp/toolkit/ObjectLoader$Result
          //   60: dup
          //   61: aload_0
          //   62: getfield 22	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker$1:this$0	Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;
          //   65: invokestatic 118	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker:access$200	(Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;)Lcom/google/gson/Gson;
          //   68: aload_1
          //   69: aload_0
          //   70: getfield 22	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker$1:this$0	Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;
          //   73: invokestatic 100	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker:access$100	(Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;)Ljava/lang/Class;
          //   76: invokevirtual 124	com/google/gson/Gson:fromJson	(Ljava/io/Reader;Ljava/lang/Class;)Ljava/lang/Object;
          //   79: invokespecial 104	com/microsoft/xbox/idp/toolkit/ObjectLoader$Result:<init>	(Ljava/lang/Object;)V
          //   82: astore 4
          //   84: aload_0
          //   85: getfield 22	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker$1:this$0	Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;
          //   88: invokestatic 72	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker:access$300	(Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;)Z
          //   91: ifeq +37 -> 128
          //   94: aload_0
          //   95: getfield 22	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker$1:this$0	Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;
          //   98: invokestatic 76	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker:access$400	(Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;)Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$Cache;
          //   101: astore_3
          //   102: aload_3
          //   103: monitorenter
          //   104: aload_0
          //   105: getfield 22	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker$1:this$0	Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;
          //   108: invokestatic 76	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker:access$400	(Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;)Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$Cache;
          //   111: aload_0
          //   112: getfield 22	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker$1:this$0	Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;
          //   115: invokestatic 80	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker:access$500	(Lcom/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker;)Ljava/lang/Object;
          //   118: aload 4
          //   120: invokeinterface 86 3 0
          //   125: pop
          //   126: aload_3
          //   127: monitorexit
          //   128: aload_0
          //   129: getfield 24	com/microsoft/xbox/idp/toolkit/ObjectLoader$MyWorker$1:val$listener	Lcom/microsoft/xbox/idp/toolkit/WorkerLoader$ResultListener;
          //   132: aload 4
          //   134: invokeinterface 92 2 0
          //   139: aload_1
          //   140: invokevirtual 127	java/io/InputStreamReader:close	()V
          //   143: aload_2
          //   144: invokevirtual 128	java/io/StringWriter:close	()V
          //   147: return
          //   148: astore 4
          //   150: aload_3
          //   151: monitorexit
          //   152: aload 4
          //   154: athrow
          //   155: astore_3
          //   156: aload_1
          //   157: invokevirtual 127	java/io/InputStreamReader:close	()V
          //   160: aload_3
          //   161: athrow
          //   162: astore_1
          //   163: aload_2
          //   164: invokevirtual 128	java/io/StringWriter:close	()V
          //   167: aload_1
          //   168: athrow
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	169	0	this	1
          //   0	169	1	paramAnonymousInputStream	java.io.InputStream
          //   40	124	2	localStringWriter	java.io.StringWriter
          //   155	6	3	localObject1	Object
          //   82	51	4	localResult	ObjectLoader.Result
          //   148	5	4	localObject2	Object
          // Exception table:
          //   from	to	target	type
          //   104	128	148	finally
          //   150	152	148	finally
          //   57	104	155	finally
          //   128	139	155	finally
          //   152	155	155	finally
          //   41	57	162	finally
          //   139	143	162	finally
          //   156	162	162	finally
        }
      });
    }
  }
  
  public static class Result<T>
    extends LoaderResult<T>
  {
    protected Result(HttpError paramHttpError)
    {
      super(paramHttpError);
    }
    
    protected Result(T paramT)
    {
      super(null);
    }
    
    public boolean isReleased()
    {
      return true;
    }
    
    public void release() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\toolkit\ObjectLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */