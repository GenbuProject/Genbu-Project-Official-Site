package net.zhuoweizhang.mcpelauncher.patch;

import com.joshuahuelsman.patchtool.PTPatch;
import com.mojang.minecraftpe.MainActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import net.zhuoweizhang.mcpelauncher.MaraudersMap;
import net.zhuoweizhang.mcpelauncher.MinecraftVersion;
import net.zhuoweizhang.mcpelauncher.MinecraftVersion.PatchTranslator;

public final class PatchUtils
{
  public static MinecraftVersion minecraftVersion = null;
  
  public static boolean canLivePatch(File paramFile)
    throws IOException
  {
    return true;
  }
  
  public static void copy(File paramFile1, File paramFile2)
    throws IOException
  {
    FileInputStream localFileInputStream = new FileInputStream(paramFile1);
    paramFile1 = new byte[(int)paramFile1.length()];
    localFileInputStream.read(paramFile1);
    localFileInputStream.close();
    paramFile2 = new FileOutputStream(paramFile2);
    paramFile2.write(paramFile1);
    paramFile2.close();
  }
  
  public static byte[] createMovwInstr(int paramInt1, int paramInt2)
  {
    long l = 0xF2400000 | paramInt1 << 8 | paramInt2 & 0xFF | (paramInt2 >> 8 & 0x7) << 12 | (paramInt2 >> 11 & 0x1) << 26 | (paramInt2 >> 12 & 0xF) << 16;
    byte[] arrayOfByte = intToLEByteArray(l);
    System.out.println("Port patch: " + Long.toString(l, 16));
    return arrayOfByte;
  }
  
  public static final byte[] intToLEByteArray(long paramLong)
  {
    return new byte[] { (byte)(int)(paramLong >>> 16), (byte)(int)(paramLong >>> 24), (byte)(int)paramLong, (byte)(int)(paramLong >>> 8) };
  }
  
  public static void patch(ByteBuffer paramByteBuffer, PTPatch paramPTPatch)
  {
    MinecraftVersion.PatchTranslator localPatchTranslator = minecraftVersion.translator;
    for (paramPTPatch.count = 0; paramPTPatch.count < paramPTPatch.getNumPatches(); paramPTPatch.count += 1)
    {
      int j = paramPTPatch.getNextAddr();
      int i = j;
      if (localPatchTranslator != null) {
        i = localPatchTranslator.get(j);
      }
      positionBuf(paramByteBuffer, i).put(paramPTPatch.getNextData());
    }
  }
  
  private static ByteBuffer positionBuf(ByteBuffer paramByteBuffer, int paramInt)
  {
    ByteBuffer localByteBuffer = paramByteBuffer;
    if (paramByteBuffer == MainActivity.minecraftLibBuffer)
    {
      localByteBuffer = paramByteBuffer;
      if (paramInt >= 0)
      {
        localByteBuffer = paramByteBuffer;
        if (paramInt < MaraudersMap.minecraftTextBuffer.capacity()) {
          localByteBuffer = MaraudersMap.minecraftTextBuffer;
        }
      }
    }
    localByteBuffer.position(paramInt);
    return localByteBuffer;
  }
  
  public static void unpatch(ByteBuffer paramByteBuffer, byte[] paramArrayOfByte, PTPatch paramPTPatch)
  {
    MinecraftVersion.PatchTranslator localPatchTranslator = minecraftVersion.translator;
    paramArrayOfByte = ByteBuffer.wrap(paramArrayOfByte);
    for (paramPTPatch.count = 0; paramPTPatch.count < paramPTPatch.getNumPatches(); paramPTPatch.count += 1)
    {
      int j = paramPTPatch.getNextAddr();
      int i = j;
      if (localPatchTranslator != null) {
        i = localPatchTranslator.get(j);
      }
      ByteBuffer localByteBuffer = positionBuf(paramByteBuffer, i);
      paramArrayOfByte.position(i);
      byte[] arrayOfByte = new byte[paramPTPatch.getDataLength()];
      paramArrayOfByte.get(arrayOfByte);
      localByteBuffer.put(arrayOfByte);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\patch\PatchUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */