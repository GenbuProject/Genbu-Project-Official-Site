package net.zhuoweizhang.mcpelauncher.texture;

import android.graphics.Bitmap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract interface ImageLoader
{
  public abstract Bitmap load(InputStream paramInputStream)
    throws IOException;
  
  public abstract void save(Bitmap paramBitmap, OutputStream paramOutputStream)
    throws IOException;
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\texture\ImageLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */