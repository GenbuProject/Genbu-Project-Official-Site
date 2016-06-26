package eu.chainfire.libsuperuser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class StreamGobbler
  extends Thread
{
  private OnLineListener listener = null;
  private BufferedReader reader = null;
  private String shell = null;
  private List<String> writer = null;
  
  public StreamGobbler(String paramString, InputStream paramInputStream, OnLineListener paramOnLineListener)
  {
    this.shell = paramString;
    this.reader = new BufferedReader(new InputStreamReader(paramInputStream));
    this.listener = paramOnLineListener;
  }
  
  public StreamGobbler(String paramString, InputStream paramInputStream, List<String> paramList)
  {
    this.shell = paramString;
    this.reader = new BufferedReader(new InputStreamReader(paramInputStream));
    this.writer = paramList;
  }
  
  public void run()
  {
    try
    {
      for (;;)
      {
        String str = this.reader.readLine();
        if (str == null) {
          break;
        }
        if (this.writer != null) {
          this.writer.add(str);
        }
        if (this.listener != null) {
          this.listener.onLine(str);
        }
      }
      return;
    }
    catch (IOException localIOException1)
    {
      try
      {
        this.reader.close();
        return;
      }
      catch (IOException localIOException2) {}
    }
  }
  
  public static abstract interface OnLineListener
  {
    public abstract void onLine(String paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\eu\chainfire\libsuperuser\StreamGobbler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */