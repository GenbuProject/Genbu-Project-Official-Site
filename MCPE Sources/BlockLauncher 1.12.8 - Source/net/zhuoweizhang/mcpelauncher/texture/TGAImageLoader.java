package net.zhuoweizhang.mcpelauncher.texture;

import android.graphics.Bitmap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import net.zhuoweizhang.mcpelauncher.texture.tga.TGAImage;
import net.zhuoweizhang.mcpelauncher.texture.tga.TGALoader;

public class TGAImageLoader
  implements ImageLoader
{
  private static void invertBuffer(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt1 * 4 * 2];
    int i = paramInt1 * 4;
    paramInt1 = 0;
    while (paramInt1 < paramInt2 / 2)
    {
      paramByteBuffer.position(paramInt1 * i);
      paramByteBuffer.get(arrayOfByte, 0, i);
      paramByteBuffer.position((paramInt2 - paramInt1 - 1) * i);
      paramByteBuffer.get(arrayOfByte, i, i);
      paramByteBuffer.position((paramInt2 - paramInt1 - 1) * i);
      paramByteBuffer.put(arrayOfByte, 0, i);
      paramByteBuffer.position(paramInt1 * i);
      paramByteBuffer.put(arrayOfByte, i, i);
      paramInt1 += 1;
    }
    paramByteBuffer.rewind();
  }
  
  public Bitmap load(InputStream paramInputStream)
    throws IOException
  {
    return TGALoader.load(paramInputStream, false);
  }
  
  public void save(Bitmap paramBitmap, OutputStream paramOutputStream)
    throws IOException
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(paramBitmap.getWidth() * paramBitmap.getHeight() * 4);
    int[] arrayOfInt = new int[paramBitmap.getWidth() * paramBitmap.getHeight()];
    paramBitmap.getPixels(arrayOfInt, 0, paramBitmap.getWidth(), 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    localByteBuffer.order(ByteOrder.LITTLE_ENDIAN).asIntBuffer().put(arrayOfInt);
    invertBuffer(localByteBuffer, paramBitmap.getWidth(), paramBitmap.getHeight());
    TGAImage.createFromData(paramBitmap.getWidth(), paramBitmap.getHeight(), true, false, localByteBuffer).write(Channels.newChannel(paramOutputStream));
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\texture\TGAImageLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */