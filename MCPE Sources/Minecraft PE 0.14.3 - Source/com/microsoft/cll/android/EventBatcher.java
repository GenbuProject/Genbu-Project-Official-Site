package com.microsoft.cll.android;

public class EventBatcher
{
  private StringBuilder eventString;
  private final String newLine = "\r\n";
  private int numberOfEvents;
  private int size;
  
  public EventBatcher()
  {
    this.size = SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXEVENTSIZEINBYTES);
    this.eventString = new StringBuilder(this.size);
    this.numberOfEvents = 0;
  }
  
  public EventBatcher(int paramInt)
  {
    this.size = paramInt;
    this.eventString = new StringBuilder(paramInt);
    this.numberOfEvents = 0;
  }
  
  public void addEventToBatch(String paramString)
    throws EventBatcher.BatchFullException
  {
    if (!canAddToBatch(paramString)) {
      throw new BatchFullException("Batch size too large! Send this batch first then retry");
    }
    this.eventString.append(paramString).append("\r\n");
    this.numberOfEvents += 1;
  }
  
  protected boolean canAddToBatch(String paramString)
  {
    return (this.eventString.length() + "\r\n".length() + paramString.length() <= this.size) && (this.numberOfEvents < SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXEVENTSPERPOST));
  }
  
  public String getBatchedEvents()
  {
    String str = this.eventString.toString();
    this.eventString.setLength(0);
    this.numberOfEvents = 0;
    return str;
  }
  
  public class BatchFullException
    extends Exception
  {
    BatchFullException(String paramString)
    {
      super();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\cll\android\EventBatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */