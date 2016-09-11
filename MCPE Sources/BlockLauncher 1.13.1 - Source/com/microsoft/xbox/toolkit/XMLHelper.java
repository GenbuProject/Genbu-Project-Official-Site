package com.microsoft.xbox.toolkit;

import java.io.OutputStream;
import java.io.StringWriter;
import org.simpleframework.xml.Serializer;

public class XMLHelper
{
  private static final int XML_WAIT_TIMEOUT_MS = 1000;
  private static XMLHelper instance = new XMLHelper();
  private Serializer serializer = null;
  
  public static XMLHelper instance()
  {
    return instance;
  }
  
  /* Error */
  public <T> T load(java.io.InputStream paramInputStream, Class<T> paramClass)
    throws XLEException
  {
    // Byte code:
    //   0: getstatic 43	com/microsoft/xbox/toolkit/ThreadManager:UIThread	Ljava/lang/Thread;
    //   3: invokestatic 49	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   6: if_acmpeq +12 -> 18
    //   9: invokestatic 55	com/microsoft/xbox/toolkit/BackgroundThreadWaitor:getInstance	()Lcom/microsoft/xbox/toolkit/BackgroundThreadWaitor;
    //   12: sipush 1000
    //   15: invokevirtual 59	com/microsoft/xbox/toolkit/BackgroundThreadWaitor:waitForReady	(I)V
    //   18: new 61	com/microsoft/xbox/toolkit/TimeMonitor
    //   21: dup
    //   22: invokespecial 62	com/microsoft/xbox/toolkit/TimeMonitor:<init>	()V
    //   25: pop
    //   26: invokestatic 49	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   29: invokevirtual 66	java/lang/Thread:getContextClassLoader	()Ljava/lang/ClassLoader;
    //   32: astore_3
    //   33: invokestatic 49	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   36: aload_2
    //   37: invokevirtual 71	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   40: invokevirtual 75	java/lang/Thread:setContextClassLoader	(Ljava/lang/ClassLoader;)V
    //   43: aload_0
    //   44: getfield 22	com/microsoft/xbox/toolkit/XMLHelper:serializer	Lorg/simpleframework/xml/Serializer;
    //   47: aload_2
    //   48: aload_1
    //   49: iconst_0
    //   50: invokeinterface 81 4 0
    //   55: astore_1
    //   56: invokestatic 49	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   59: aload_3
    //   60: invokevirtual 75	java/lang/Thread:setContextClassLoader	(Ljava/lang/ClassLoader;)V
    //   63: aload_1
    //   64: areturn
    //   65: astore_1
    //   66: invokestatic 49	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   69: aload_3
    //   70: invokevirtual 75	java/lang/Thread:setContextClassLoader	(Ljava/lang/ClassLoader;)V
    //   73: aload_1
    //   74: athrow
    //   75: astore_1
    //   76: new 35	com/microsoft/xbox/toolkit/XLEException
    //   79: dup
    //   80: ldc2_w 82
    //   83: aload_1
    //   84: invokevirtual 87	java/lang/Exception:toString	()Ljava/lang/String;
    //   87: invokespecial 90	com/microsoft/xbox/toolkit/XLEException:<init>	(JLjava/lang/String;)V
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	XMLHelper
    //   0	91	1	paramInputStream	java.io.InputStream
    //   0	91	2	paramClass	Class<T>
    //   32	38	3	localClassLoader	ClassLoader
    // Exception table:
    //   from	to	target	type
    //   33	56	65	finally
    //   26	33	75	java/lang/Exception
    //   56	63	75	java/lang/Exception
    //   66	75	75	java/lang/Exception
  }
  
  public <T> String save(T paramT)
    throws XLEException
  {
    new TimeMonitor();
    StringWriter localStringWriter = new StringWriter();
    try
    {
      this.serializer.write(paramT, localStringWriter);
      return localStringWriter.toString();
    }
    catch (Exception paramT)
    {
      throw new XLEException(9L, paramT.toString());
    }
  }
  
  public <T> void save(T paramT, OutputStream paramOutputStream)
    throws XLEException
  {
    new TimeMonitor();
    try
    {
      this.serializer.write(paramT, paramOutputStream);
      return;
    }
    catch (Exception paramT)
    {
      throw new XLEException(9L, paramT.toString());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\XMLHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */