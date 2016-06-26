package com.google.android.gms.games;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

public abstract interface Players
{
  public static final String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";
  
  public abstract Intent getCompareProfileIntent(GoogleApiClient paramGoogleApiClient, Player paramPlayer);
  
  public abstract Player getCurrentPlayer(GoogleApiClient paramGoogleApiClient);
  
  public abstract String getCurrentPlayerId(GoogleApiClient paramGoogleApiClient);
  
  public abstract Intent getPlayerSearchIntent(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<LoadPlayersResult> loadConnectedPlayers(GoogleApiClient paramGoogleApiClient, boolean paramBoolean);
  
  public abstract PendingResult<LoadPlayersResult> loadInvitablePlayers(GoogleApiClient paramGoogleApiClient, int paramInt, boolean paramBoolean);
  
  public abstract PendingResult<LoadPlayersResult> loadMoreInvitablePlayers(GoogleApiClient paramGoogleApiClient, int paramInt);
  
  public abstract PendingResult<LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(GoogleApiClient paramGoogleApiClient, int paramInt);
  
  public abstract PendingResult<LoadPlayersResult> loadPlayer(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<LoadPlayersResult> loadPlayer(GoogleApiClient paramGoogleApiClient, String paramString, boolean paramBoolean);
  
  public abstract PendingResult<LoadPlayersResult> loadRecentlyPlayedWithPlayers(GoogleApiClient paramGoogleApiClient, int paramInt, boolean paramBoolean);
  
  public static abstract interface LoadPlayersResult
    extends Releasable, Result
  {
    public abstract PlayerBuffer getPlayers();
  }
  
  public static abstract interface LoadProfileSettingsResult
    extends Result
  {}
  
  public static abstract interface LoadXpForGameCategoriesResult
    extends Result
  {}
  
  public static abstract interface LoadXpForGamesResult
    extends Result
  {}
  
  public static abstract interface LoadXpStreamResult
    extends Result
  {}
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\Players.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */