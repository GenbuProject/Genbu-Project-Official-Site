package com.microsoft.xbox.service.network.managers;

import java.util.ArrayList;

public class FollowingSummaryResult
{
  public ArrayList<People> people;
  public int totalCount;
  
  public static class People
  {
    public String displayName;
    public String displayPicRaw;
    public String gamertag;
    public boolean isFavorite;
    public boolean isIdentityShared;
    public String realName;
    public String xuid;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\network\managers\FollowingSummaryResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */