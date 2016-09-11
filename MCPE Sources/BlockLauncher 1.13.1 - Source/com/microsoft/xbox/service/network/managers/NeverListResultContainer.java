package com.microsoft.xbox.service.network.managers;

import java.util.ArrayList;
import java.util.Iterator;

public final class NeverListResultContainer
{
  public static class NeverListResult
  {
    public ArrayList<NeverListResultContainer.NeverUser> users = new ArrayList();
    
    public void add(String paramString)
    {
      this.users.add(new NeverListResultContainer.NeverUser(paramString));
    }
    
    public boolean contains(String paramString)
    {
      Iterator localIterator = this.users.iterator();
      while (localIterator.hasNext()) {
        if (((NeverListResultContainer.NeverUser)localIterator.next()).xuid.equalsIgnoreCase(paramString)) {
          return true;
        }
      }
      return false;
    }
    
    public NeverListResultContainer.NeverUser remove(String paramString)
    {
      Iterator localIterator = this.users.iterator();
      while (localIterator.hasNext())
      {
        NeverListResultContainer.NeverUser localNeverUser = (NeverListResultContainer.NeverUser)localIterator.next();
        if (localNeverUser.xuid.equalsIgnoreCase(paramString))
        {
          this.users.remove(localNeverUser);
          return localNeverUser;
        }
      }
      return null;
    }
  }
  
  public static class NeverUser
  {
    public String xuid;
    
    public NeverUser(String paramString)
    {
      this.xuid = paramString;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\network\managers\NeverListResultContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */