package com.microsoft.xbox.toolkit;

public class TimeMonitor
{
  private final long NSTOMSEC = 1000000L;
  private long endTicks = 0L;
  private long startTicks = 0L;
  
  public long currentTime()
  {
    return (System.nanoTime() - this.startTicks) / 1000000L;
  }
  
  public long getElapsedMs()
  {
    long l = 0L;
    if (getIsStarted()) {
      if (this.endTicks == 0L) {
        break label36;
      }
    }
    label36:
    for (l = this.endTicks;; l = System.nanoTime())
    {
      l = (l - this.startTicks) / 1000000L;
      return l;
    }
  }
  
  public boolean getIsEnded()
  {
    return this.endTicks != 0L;
  }
  
  public boolean getIsStarted()
  {
    return this.startTicks != 0L;
  }
  
  public void reset()
  {
    this.startTicks = 0L;
    this.endTicks = 0L;
  }
  
  public void saveCurrentTime()
  {
    if (getIsStarted()) {
      this.endTicks = System.nanoTime();
    }
  }
  
  public void start()
  {
    this.startTicks = System.nanoTime();
    this.endTicks = 0L;
  }
  
  public void stop()
  {
    if ((this.startTicks != 0L) && (this.endTicks == 0L)) {
      this.endTicks = System.nanoTime();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\TimeMonitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */