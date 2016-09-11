package com.microsoft.xbox.service.model;

import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.ui.Search.TrieSearch;
import java.util.ArrayList;
import java.util.List;

public class SearchResultPerson
{
  public String GamertagAfter;
  public String GamertagBefore;
  public String GamertagMatch;
  public String RealNameAfter;
  public String RealNameBefore;
  public String RealNameMatch;
  public String SearchText;
  public String StatusAfter;
  public String StatusBefore;
  public String StatusMatch;
  
  public SearchResultPerson(FollowersData paramFollowersData, String paramString)
  {
    if (isNullOrWhitespace(paramString)) {
      throw new IllegalArgumentException(paramString);
    }
    this.SearchText = paramString;
    setInlineRuns(paramFollowersData);
  }
  
  private static List<String> getRuns(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList(3);
    int i = TrieSearch.findWordIndex(paramString1, paramString2);
    int j = paramString2.length();
    if (i != -1)
    {
      localArrayList.add(paramString1.substring(0, i));
      localArrayList.add(paramString1.substring(i, paramString2.length() + i));
      localArrayList.add(paramString1.substring(j + i, paramString1.length()));
      return localArrayList;
    }
    localArrayList.add(paramString1);
    localArrayList.add("");
    localArrayList.add("");
    return localArrayList;
  }
  
  private static boolean isNullOrWhitespace(String paramString)
  {
    return (JavaUtil.isNullOrEmpty(paramString)) || (paramString.trim().isEmpty());
  }
  
  private void setInlineRuns(FollowersData paramFollowersData)
  {
    List localList = getRuns(paramFollowersData.getGamertag(), this.SearchText);
    if (localList.size() == 3)
    {
      this.GamertagBefore = ((String)localList.get(0));
      this.GamertagMatch = ((String)localList.get(1));
      this.GamertagAfter = ((String)localList.get(2));
    }
    localList = getRuns(paramFollowersData.getGamerRealName(), this.SearchText);
    if (localList.size() == 3)
    {
      this.RealNameBefore = ((String)localList.get(0));
      this.RealNameMatch = ((String)localList.get(1));
      this.RealNameAfter = ((String)localList.get(2));
    }
    paramFollowersData = getRuns(paramFollowersData.presenceString, this.SearchText);
    if (paramFollowersData.size() == 3)
    {
      this.StatusBefore = ((String)paramFollowersData.get(0));
      this.StatusMatch = ((String)paramFollowersData.get(1));
      this.StatusAfter = ((String)paramFollowersData.get(2));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\model\SearchResultPerson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */