package com.microsoft.xbox.service.network.managers;

import java.util.ArrayList;
import java.util.Iterator;

public final class MutedListResultContainer
{
  public static class MutedListResult
  {
    public ArrayList<MutedListResultContainer.MutedUser> users = new ArrayList();
    
    public void add(String paramString)
    {
      this.users.add(new MutedListResultContainer.MutedUser(paramString));
    }
    
    public boolean contains(String paramString)
    {
      Iterator localIterator = this.users.iterator();
      while (localIterator.hasNext()) {
        if (((MutedListResultContainer.MutedUser)localIterator.next()).xuid.equalsIgnoreCase(paramString)) {
          return true;
        }
      }
      return false;
    }
    
    public MutedListResultContainer.MutedUser remove(String paramString)
    {
      Iterator localIterator = this.users.iterator();
      while (localIterator.hasNext())
      {
        MutedListResultContainer.MutedUser localMutedUser = (MutedListResultContainer.MutedUser)localIterator.next();
        if (localMutedUser.xuid.equalsIgnoreCase(paramString))
        {
          this.users.remove(localMutedUser);
          return localMutedUser;
        }
      }
      return null;
    }
  }
  
  public static class MutedUser
  {
    public String xuid;
    
    public MutedUser(String paramString)
    {
      this.xuid = paramString;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\network\managers\MutedListResultContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */