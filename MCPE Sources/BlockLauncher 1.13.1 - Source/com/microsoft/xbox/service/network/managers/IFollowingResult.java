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


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\network\managers\IFollowingResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */