package net.zhuoweizhang.mcpelauncher;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract interface TexturePack
  extends Closeable
{
  public abstract InputStream getInputStream(String paramString)
    throws IOException;
  
  public abstract long getSize(String paramString)
    throws IOException;
  
  public abstract List<String> listFiles()
    throws IOException;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\TexturePack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */