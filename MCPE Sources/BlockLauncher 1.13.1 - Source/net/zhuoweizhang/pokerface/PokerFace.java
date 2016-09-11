package net.zhuoweizhang.pokerface;

import android.os.Build.VERSION;
import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;

public class PokerFace
{
  public static final int PROT_EXEC = 4;
  public static final int PROT_NONE = 0;
  public static final int PROT_READ = 1;
  public static final int PROT_WRITE = 2;
  public static final int _SC_PAGESIZE = 39;
  
  static
  {
    System.loadLibrary("gnustl_shared");
    System.loadLibrary("mcpelauncher_tinysubstrate");
  }
  
  public static ByteBuffer createDirectByteBuffer(long paramLong1, long paramLong2)
    throws Exception
  {
    if (Build.VERSION.SDK_INT >= 18) {
      try
      {
        ByteBuffer localByteBuffer = createDirectByteBufferNew(paramLong1, paramLong2);
        return localByteBuffer;
      }
      catch (NoSuchMethodException localNoSuchMethodException) {}
    }
    Constructor localConstructor = Class.forName("java.nio.ReadWriteDirectByteBuffer").getDeclaredConstructor(new Class[] { Integer.TYPE, Integer.TYPE });
    localConstructor.setAccessible(true);
    return (ByteBuffer)localConstructor.newInstance(new Object[] { Integer.valueOf((int)paramLong1), Integer.valueOf((int)paramLong2) });
  }
  
  private static ByteBuffer createDirectByteBufferNew(long paramLong1, long paramLong2)
    throws Exception
  {
    Constructor localConstructor = Class.forName("java.nio.DirectByteBuffer").getDeclaredConstructor(new Class[] { Long.TYPE, Integer.TYPE });
    localConstructor.setAccessible(true);
    return (ByteBuffer)localConstructor.newInstance(new Object[] { Long.valueOf(paramLong1), Integer.valueOf((int)paramLong2) });
  }
  
  public static void init() {}
  
  public static native int mprotect(long paramLong1, long paramLong2, int paramInt);
  
  public static native long sysconf(int paramInt);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\pokerface\PokerFace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */