package com.microsoft.xbox.toolkit;

import android.util.Pair;
import com.microsoft.xbox.idp.util.HttpCall.Callback;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TcuiHttpUtil
{
  /* Error */
  public static String getResponseBodySync(com.microsoft.xbox.idp.util.HttpCall paramHttpCall)
    throws XLEException
  {
    // Byte code:
    //   0: new 23	java/util/concurrent/atomic/AtomicReference
    //   3: dup
    //   4: invokespecial 24	java/util/concurrent/atomic/AtomicReference:<init>	()V
    //   7: astore_1
    //   8: aload_1
    //   9: new 26	android/util/Pair
    //   12: dup
    //   13: iconst_0
    //   14: invokestatic 32	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   17: aconst_null
    //   18: invokespecial 35	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   21: invokevirtual 39	java/util/concurrent/atomic/AtomicReference:set	(Ljava/lang/Object;)V
    //   24: aload_0
    //   25: new 10	com/microsoft/xbox/toolkit/TcuiHttpUtil$3
    //   28: dup
    //   29: aload_1
    //   30: invokespecial 42	com/microsoft/xbox/toolkit/TcuiHttpUtil$3:<init>	(Ljava/util/concurrent/atomic/AtomicReference;)V
    //   33: invokevirtual 48	com/microsoft/xbox/idp/util/HttpCall:getResponseAsync	(Lcom/microsoft/xbox/idp/util/HttpCall$Callback;)V
    //   36: aload_1
    //   37: monitorenter
    //   38: aload_1
    //   39: invokevirtual 52	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
    //   42: checkcast 26	android/util/Pair
    //   45: getfield 56	android/util/Pair:first	Ljava/lang/Object;
    //   48: checkcast 28	java/lang/Boolean
    //   51: invokevirtual 60	java/lang/Boolean:booleanValue	()Z
    //   54: ifne +11 -> 65
    //   57: aload_1
    //   58: invokevirtual 63	java/lang/Object:wait	()V
    //   61: goto -23 -> 38
    //   64: astore_0
    //   65: aload_1
    //   66: monitorexit
    //   67: aload_1
    //   68: invokevirtual 52	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
    //   71: checkcast 26	android/util/Pair
    //   74: getfield 66	android/util/Pair:second	Ljava/lang/Object;
    //   77: checkcast 68	java/lang/String
    //   80: areturn
    //   81: astore_0
    //   82: aload_1
    //   83: monitorexit
    //   84: aload_0
    //   85: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	paramHttpCall	com.microsoft.xbox.idp.util.HttpCall
    //   7	76	1	localAtomicReference	AtomicReference
    // Exception table:
    //   from	to	target	type
    //   38	61	64	java/lang/InterruptedException
    //   38	61	81	finally
    //   65	67	81	finally
    //   82	84	81	finally
  }
  
  /* Error */
  public static <T> T getResponseSync(com.microsoft.xbox.idp.util.HttpCall paramHttpCall, Class<T> paramClass)
    throws XLEException
  {
    // Byte code:
    //   0: new 23	java/util/concurrent/atomic/AtomicReference
    //   3: dup
    //   4: invokespecial 24	java/util/concurrent/atomic/AtomicReference:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: new 26	android/util/Pair
    //   12: dup
    //   13: iconst_0
    //   14: invokestatic 32	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   17: aconst_null
    //   18: invokespecial 35	android/util/Pair:<init>	(Ljava/lang/Object;Ljava/lang/Object;)V
    //   21: invokevirtual 39	java/util/concurrent/atomic/AtomicReference:set	(Ljava/lang/Object;)V
    //   24: aload_0
    //   25: new 6	com/microsoft/xbox/toolkit/TcuiHttpUtil$1
    //   28: dup
    //   29: aload_1
    //   30: aload_2
    //   31: invokespecial 74	com/microsoft/xbox/toolkit/TcuiHttpUtil$1:<init>	(Ljava/lang/Class;Ljava/util/concurrent/atomic/AtomicReference;)V
    //   34: invokevirtual 48	com/microsoft/xbox/idp/util/HttpCall:getResponseAsync	(Lcom/microsoft/xbox/idp/util/HttpCall$Callback;)V
    //   37: aload_2
    //   38: monitorenter
    //   39: aload_2
    //   40: invokevirtual 52	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
    //   43: checkcast 26	android/util/Pair
    //   46: getfield 56	android/util/Pair:first	Ljava/lang/Object;
    //   49: checkcast 28	java/lang/Boolean
    //   52: invokevirtual 60	java/lang/Boolean:booleanValue	()Z
    //   55: ifne +11 -> 66
    //   58: aload_2
    //   59: invokevirtual 63	java/lang/Object:wait	()V
    //   62: goto -23 -> 39
    //   65: astore_0
    //   66: aload_2
    //   67: monitorexit
    //   68: aload_2
    //   69: invokevirtual 52	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
    //   72: checkcast 26	android/util/Pair
    //   75: getfield 66	android/util/Pair:second	Ljava/lang/Object;
    //   78: areturn
    //   79: astore_0
    //   80: aload_2
    //   81: monitorexit
    //   82: aload_0
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	paramHttpCall	com.microsoft.xbox.idp.util.HttpCall
    //   0	84	1	paramClass	Class<T>
    //   7	74	2	localAtomicReference	AtomicReference
    // Exception table:
    //   from	to	target	type
    //   39	62	65	java/lang/InterruptedException
    //   39	62	79	finally
    //   66	68	79	finally
    //   80	82	79	finally
  }
  
  /* Error */
  public static boolean getResponseSyncSucceeded(com.microsoft.xbox.idp.util.HttpCall paramHttpCall, final List<Integer> paramList)
  {
    // Byte code:
    //   0: new 23	java/util/concurrent/atomic/AtomicReference
    //   3: dup
    //   4: invokespecial 24	java/util/concurrent/atomic/AtomicReference:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: new 8	com/microsoft/xbox/toolkit/TcuiHttpUtil$2
    //   12: dup
    //   13: aload_2
    //   14: aload_1
    //   15: invokespecial 81	com/microsoft/xbox/toolkit/TcuiHttpUtil$2:<init>	(Ljava/util/concurrent/atomic/AtomicReference;Ljava/util/List;)V
    //   18: invokevirtual 48	com/microsoft/xbox/idp/util/HttpCall:getResponseAsync	(Lcom/microsoft/xbox/idp/util/HttpCall$Callback;)V
    //   21: aload_2
    //   22: monitorenter
    //   23: aload_2
    //   24: invokevirtual 52	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
    //   27: ifnonnull +11 -> 38
    //   30: aload_2
    //   31: invokevirtual 63	java/lang/Object:wait	()V
    //   34: goto -11 -> 23
    //   37: astore_0
    //   38: aload_2
    //   39: monitorexit
    //   40: aload_2
    //   41: invokevirtual 52	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
    //   44: checkcast 28	java/lang/Boolean
    //   47: invokevirtual 60	java/lang/Boolean:booleanValue	()Z
    //   50: ireturn
    //   51: astore_0
    //   52: aload_2
    //   53: monitorexit
    //   54: aload_0
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	paramHttpCall	com.microsoft.xbox.idp.util.HttpCall
    //   0	56	1	paramList	List<Integer>
    //   7	46	2	localAtomicReference	AtomicReference
    // Exception table:
    //   from	to	target	type
    //   23	34	37	java/lang/InterruptedException
    //   23	34	51	finally
    //   38	40	51	finally
    //   52	54	51	finally
  }
  
  public static <T> void throwIfNullOrFalse(T paramT)
    throws XLEException
  {
    if ((paramT == null) && (!Boolean.getBoolean(paramT.toString()))) {
      throw new XLEException(2L);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\TcuiHttpUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */