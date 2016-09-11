package com.microsoft.xbox.toolkit.ui;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

public class TextureManagerDownloadRequest
  implements Comparable<TextureManagerDownloadRequest>
{
  private static AtomicInteger nextIndex = new AtomicInteger(0);
  public int index;
  public TextureManagerScaledNetworkBitmapRequest key;
  public InputStream stream;
  
  public TextureManagerDownloadRequest(TextureManagerScaledNetworkBitmapRequest paramTextureManagerScaledNetworkBitmapRequest)
  {
    this.key = paramTextureManagerScaledNetworkBitmapRequest;
    this.index = nextIndex.incrementAndGet();
  }
  
  public int compareTo(TextureManagerDownloadRequest paramTextureManagerDownloadRequest)
  {
    return this.index - paramTextureManagerDownloadRequest.index;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ui\TextureManagerDownloadRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */