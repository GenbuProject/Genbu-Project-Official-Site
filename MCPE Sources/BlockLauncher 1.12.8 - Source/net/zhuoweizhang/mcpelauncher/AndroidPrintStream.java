package net.zhuoweizhang.mcpelauncher;

import android.util.Log;

class AndroidPrintStream
  extends LoggingPrintStream
{
  private final int priority;
  private final String tag;
  
  public AndroidPrintStream(int paramInt, String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException("tag");
    }
    this.priority = paramInt;
    this.tag = paramString;
  }
  
  protected void log(String paramString)
  {
    Log.println(this.priority, this.tag, paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\AndroidPrintStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */