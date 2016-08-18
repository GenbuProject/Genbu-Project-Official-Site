package com.microsoft.xbox.service.network.managers;

import java.util.ArrayList;

public abstract interface IFollowingResult
{
  public static class FollowingResult
  {
    public ArrayList<IFollowingResult.People> people;
    public int totalCount;
  }
  
  public static class People
  {
    public boolean isFavorite;
    public String xuid;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\network\managers\IFollowingResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */