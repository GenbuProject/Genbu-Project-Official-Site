package com.microsoft.xbox.service.model;

import com.microsoft.xbox.service.network.managers.IPeopleHubResult.PeopleHubPersonSummary;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.PeopleHubRecommendation;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.RecommendationType;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.xle.app.XLEUtil;
import java.util.ArrayList;

public class RecommendationsPeopleData
  extends FollowersData
{
  private IPeopleHubResult.PeopleHubRecommendation recommendationInfo;
  
  public RecommendationsPeopleData(IPeopleHubResult.PeopleHubPersonSummary paramPeopleHubPersonSummary)
  {
    super(paramPeopleHubPersonSummary);
    XLEAssert.assertNotNull(paramPeopleHubPersonSummary.recommendation);
    this.recommendationInfo = paramPeopleHubPersonSummary.recommendation;
  }
  
  public RecommendationsPeopleData(boolean paramBoolean, FollowersData.DummyType paramDummyType)
  {
    super(paramBoolean, paramDummyType);
  }
  
  public boolean getIsFacebookFriend()
  {
    return this.recommendationInfo.getRecommendationType() == IPeopleHubResult.RecommendationType.FacebookFriend;
  }
  
  public String getRecommendationFirstReason()
  {
    if (XLEUtil.isNullOrEmpty(this.recommendationInfo.Reasons)) {
      return "";
    }
    return (String)this.recommendationInfo.Reasons.get(0);
  }
  
  public IPeopleHubResult.RecommendationType getRecommendationType()
  {
    return this.recommendationInfo.getRecommendationType();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\RecommendationsPeopleData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */