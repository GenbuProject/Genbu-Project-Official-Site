package net.zhuoweizhang.mcpelauncher.texture;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import java.io.InputStream;
import java.io.OutputStream;

public class PNGImageLoader
  implements ImageLoader
{
  public Bitmap load(InputStream paramInputStream)
  {
    return BitmapFactory.decodeStream(paramInputStream);
  }
  
  public void save(Bitmap paramBitmap, OutputStream paramOutputStream)
  {
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, paramOutputStream);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\texture\PNGImageLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */