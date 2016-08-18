package com.microsoft.xbox.service.model.friendfinder;

import com.microsoft.xbox.service.network.managers.IPeopleHubResult.PeopleHubPersonSummary;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.PeopleHubRecommendation;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.RecommendationType;
import java.net.URI;

public class FriendFinderSuggestionModel
{
  public String gamerTag;
  public URI imageUri;
  public String presence;
  public String realName;
  public IPeopleHubResult.RecommendationType recommendationType;
  
  public static FriendFinderSuggestionModel fromPeopleHubSummary(IPeopleHubResult.PeopleHubPersonSummary paramPeopleHubPersonSummary)
  {
    FriendFinderSuggestionModel localFriendFinderSuggestionModel = new FriendFinderSuggestionModel();
    localFriendFinderSuggestionModel.imageUri = URI.create(paramPeopleHubPersonSummary.displayPicRaw);
    localFriendFinderSuggestionModel.gamerTag = paramPeopleHubPersonSummary.gamertag;
    localFriendFinderSuggestionModel.realName = paramPeopleHubPersonSummary.realName;
    if (paramPeopleHubPersonSummary.recommendation != null) {}
    for (IPeopleHubResult.RecommendationType localRecommendationType = paramPeopleHubPersonSummary.recommendation.getRecommendationType();; localRecommendationType = IPeopleHubResult.RecommendationType.Unknown)
    {
      localFriendFinderSuggestionModel.recommendationType = localRecommendationType;
      localFriendFinderSuggestionModel.presence = paramPeopleHubPersonSummary.presenceText;
      return localFriendFinderSuggestionModel;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\friendfinder\FriendFinderSuggestionModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */