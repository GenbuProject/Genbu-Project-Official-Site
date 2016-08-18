package com.microsoft.xbox.toolkit;

import java.util.HashMap;

public class XLEAllocationTracker
{
  private static XLEAllocationTracker instance = new XLEAllocationTracker();
  private HashMap<String, HashMap<String, Integer>> adapterCounter = new HashMap();
  
  public static XLEAllocationTracker getInstance()
  {
    return instance;
  }
  
  private HashMap<String, Integer> getTagHash(String paramString)
  {
    if (!this.adapterCounter.containsKey(paramString)) {
      this.adapterCounter.put(paramString, new HashMap());
    }
    return (HashMap)this.adapterCounter.get(paramString);
  }
  
  public void debugDecrement(String paramString1, String paramString2) {}
  
  public int debugGetOverallocatedCount(String paramString)
  {
    return 0;
  }
  
  public int debugGetTotalCount(String paramString)
  {
    return 0;
  }
  
  public void debugIncrement(String paramString1, String paramString2) {}
  
  public void debugPrintOverallocated(String paramString) {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\XLEAllocationTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */