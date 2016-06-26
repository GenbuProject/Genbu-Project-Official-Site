package com.microsoft.cll.android;

import java.util.Arrays;
import java.util.zip.Deflater;

public class EventCompressor
{
  private final String TAG = "EventCompressor";
  private final ILogger logger;
  
  public EventCompressor(ILogger paramILogger)
  {
    this.logger = paramILogger;
  }
  
  public byte[] compress(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("UTF-8");
      byte[] arrayOfByte = new byte[SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXEVENTSIZEINBYTES)];
      Deflater localDeflater = new Deflater(-1, true);
      localDeflater.setInput(paramString);
      localDeflater.finish();
      int i = localDeflater.deflate(arrayOfByte);
      if (i >= SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXEVENTSIZEINBYTES))
      {
        this.logger.error("EventCompressor", "Compression resulted in a string of at least the max event buffer size of Vortex. Most likely this means we lost part of the string.");
        return null;
      }
      paramString = Arrays.copyOfRange(arrayOfByte, 0, i);
      return paramString;
    }
    catch (Exception paramString)
    {
      this.logger.error("EventCompressor", "Could not compress events");
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\cll\android\EventCompressor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */