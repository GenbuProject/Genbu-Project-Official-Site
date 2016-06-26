package com.google.android.gms.games.internal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.zza;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.api.internal.zze;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.api.internal.zzq.zzb;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.Games.GetServerAuthCodeResult;
import com.google.android.gms.games.Games.GetTokenResult;
import com.google.android.gms.games.Games.LoadExperimentsResult;
import com.google.android.gms.games.GamesMetadata.LoadGameInstancesResult;
import com.google.android.gms.games.GamesMetadata.LoadGameSearchSuggestionsResult;
import com.google.android.gms.games.GamesMetadata.LoadGamesResult;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.Notifications.ContactSettingLoadResult;
import com.google.android.gms.games.Notifications.GameMuteStatusChangeResult;
import com.google.android.gms.games.Notifications.GameMuteStatusLoadResult;
import com.google.android.gms.games.Notifications.InboxCountResult;
import com.google.android.gms.games.OnNearbyPlayerDetectedListener;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players.LoadPlayersResult;
import com.google.android.gms.games.Players.LoadProfileSettingsResult;
import com.google.android.gms.games.Players.LoadXpForGameCategoriesResult;
import com.google.android.gms.games.Players.LoadXpStreamResult;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;
import com.google.android.gms.games.appcontent.AppContents.LoadAppContentResult;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events.LoadEventsResult;
import com.google.android.gms.games.internal.constants.RequestType;
import com.google.android.gms.games.internal.events.EventIncrementCache;
import com.google.android.gms.games.internal.events.EventIncrementManager;
import com.google.android.gms.games.internal.experience.ExperienceEventBuffer;
import com.google.android.gms.games.internal.game.Acls.LoadAclResult;
import com.google.android.gms.games.internal.game.GameInstanceBuffer;
import com.google.android.gms.games.internal.game.GameSearchSuggestionBuffer;
import com.google.android.gms.games.internal.request.RequestUpdateOutcomes;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBufferHeader;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult;
import com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomBuffer;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests.AcceptQuestResult;
import com.google.android.gms.games.quest.Quests.ClaimMilestoneResult;
import com.google.android.gms.games.quest.Quests.LoadQuestsResult;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests.LoadRequestSummariesResult;
import com.google.android.gms.games.request.Requests.LoadRequestsResult;
import com.google.android.gms.games.request.Requests.SendRequestResult;
import com.google.android.gms.games.request.Requests.UpdateRequestsResult;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotContentsEntity;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataChangeEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult;
import com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult;
import com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult;
import com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats.LoadPlayerStatsResult;
import com.google.android.gms.games.video.VideoBuffer;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.VideoConfiguration;
import com.google.android.gms.games.video.Videos.ListVideosResult;
import com.google.android.gms.games.video.Videos.VideoAvailableResult;
import com.google.android.gms.games.video.Videos.VideoCapabilitiesResult;
import com.google.android.gms.signin.internal.zzh;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class GamesClientImpl
  extends zzj<IGamesService>
{
  EventIncrementManager zzaDZ = new EventIncrementManager()
  {
    public EventIncrementCache zzwS()
    {
      return new GamesClientImpl.GameClientEventIncrementCache(GamesClientImpl.this);
    }
  };
  private final String zzaEa;
  private PlayerEntity zzaEb;
  private GameEntity zzaEc;
  private final PopupManager zzaEd;
  private boolean zzaEe = false;
  private final Binder zzaEf;
  private final long zzaEg;
  private final Games.GamesOptions zzaEh;
  
  public GamesClientImpl(Context paramContext, Looper paramLooper, com.google.android.gms.common.internal.zzf paramzzf, Games.GamesOptions paramGamesOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 1, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzaEa = paramzzf.zzqv();
    this.zzaEf = new Binder();
    this.zzaEd = PopupManager.zza(this, paramzzf.zzqr());
    zzo(paramzzf.zzqx());
    this.zzaEg = hashCode();
    this.zzaEh = paramGamesOptions;
  }
  
  private static Room zzY(DataHolder paramDataHolder)
  {
    RoomBuffer localRoomBuffer = new RoomBuffer(paramDataHolder);
    paramDataHolder = null;
    try
    {
      if (localRoomBuffer.getCount() > 0) {
        paramDataHolder = (Room)((Room)localRoomBuffer.get(0)).freeze();
      }
      return paramDataHolder;
    }
    finally
    {
      localRoomBuffer.release();
    }
  }
  
  private void zzb(RemoteException paramRemoteException)
  {
    GamesLog.zzb("GamesClientImpl", "service died", paramRemoteException);
  }
  
  private void zzwv()
  {
    this.zzaEb = null;
    this.zzaEc = null;
  }
  
  public void disconnect()
  {
    this.zzaEe = false;
    if (isConnected()) {}
    try
    {
      IGamesService localIGamesService = (IGamesService)zzqJ();
      localIGamesService.zzwR();
      this.zzaDZ.flush();
      localIGamesService.zzF(this.zzaEg);
      super.disconnect();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        GamesLog.zzz("GamesClientImpl", "Failed to notify client disconnect.");
      }
    }
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    super.onConnectionFailed(paramConnectionResult);
    this.zzaEe = false;
  }
  
  public int zza(zzq<RealTimeMultiplayer.ReliableMessageSentCallback> paramzzq, byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    try
    {
      int i = ((IGamesService)zzqJ()).zza(new RealTimeReliableMessageBinderCallbacks(paramzzq), paramArrayOfByte, paramString1, paramString2);
      return i;
    }
    catch (RemoteException paramzzq)
    {
      zzb(paramzzq);
    }
    return -1;
  }
  
  public int zza(byte[] paramArrayOfByte, String paramString, String[] paramArrayOfString)
  {
    zzx.zzb(paramArrayOfString, "Participant IDs must not be null");
    try
    {
      int i = ((IGamesService)zzqJ()).zzb(paramArrayOfByte, paramString, paramArrayOfString);
      return i;
    }
    catch (RemoteException paramArrayOfByte)
    {
      zzb(paramArrayOfByte);
    }
    return -1;
  }
  
  public Intent zza(int paramInt1, byte[] paramArrayOfByte, int paramInt2, Bitmap paramBitmap, String paramString)
  {
    try
    {
      paramArrayOfByte = ((IGamesService)zzqJ()).zza(paramInt1, paramArrayOfByte, paramInt2, paramString);
      zzx.zzb(paramBitmap, "Must provide a non null icon");
      paramArrayOfByte.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", paramBitmap);
      return paramArrayOfByte;
    }
    catch (RemoteException paramArrayOfByte)
    {
      zzb(paramArrayOfByte);
    }
    return null;
  }
  
  public Intent zza(PlayerEntity paramPlayerEntity)
  {
    try
    {
      paramPlayerEntity = ((IGamesService)zzqJ()).zza(paramPlayerEntity);
      return paramPlayerEntity;
    }
    catch (RemoteException paramPlayerEntity)
    {
      zzb(paramPlayerEntity);
    }
    return null;
  }
  
  public Intent zza(Room paramRoom, int paramInt)
  {
    try
    {
      paramRoom = ((IGamesService)zzqJ()).zza((RoomEntity)paramRoom.freeze(), paramInt);
      return paramRoom;
    }
    catch (RemoteException paramRoom)
    {
      zzb(paramRoom);
    }
    return null;
  }
  
  public Intent zza(String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    try
    {
      paramString = ((IGamesService)zzqJ()).zza(paramString, paramBoolean1, paramBoolean2, paramInt);
      return paramString;
    }
    catch (RemoteException paramString)
    {
      zzb(paramString);
    }
    return null;
  }
  
  protected void zza(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2)
  {
    if ((paramInt1 == 0) && (paramBundle != null))
    {
      paramBundle.setClassLoader(GamesClientImpl.class.getClassLoader());
      this.zzaEe = paramBundle.getBoolean("show_welcome_popup");
      this.zzaEb = ((PlayerEntity)paramBundle.getParcelable("com.google.android.gms.games.current_player"));
      this.zzaEc = ((GameEntity)paramBundle.getParcelable("com.google.android.gms.games.current_game"));
    }
    super.zza(paramInt1, paramIBinder, paramBundle, paramInt2);
  }
  
  public void zza(IBinder paramIBinder, Bundle paramBundle)
  {
    if (isConnected()) {}
    try
    {
      ((IGamesService)zzqJ()).zza(paramIBinder, paramBundle);
      return;
    }
    catch (RemoteException paramIBinder)
    {
      zzb(paramIBinder);
    }
  }
  
  public void zza(GoogleApiClient.zza paramzza)
  {
    zzwv();
    super.zza(paramzza);
  }
  
  public void zza(zza.zzb<Invitations.LoadInvitationsResult> paramzzb, int paramInt)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new InvitationsLoadedBinderCallback(paramzzb), paramInt);
  }
  
  public void zza(zza.zzb<Requests.LoadRequestsResult> paramzzb, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new RequestsLoadedBinderCallbacks(paramzzb), paramInt1, paramInt2, paramInt3);
  }
  
  public void zza(zza.zzb<AppContents.LoadAppContentResult> paramzzb, int paramInt, String paramString, String[] paramArrayOfString, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new AppContentLoadedBinderCallbacks(paramzzb), paramInt, paramString, paramArrayOfString, paramBoolean);
  }
  
  public void zza(zza.zzb<Players.LoadPlayersResult> paramzzb, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new PlayersLoadedBinderCallback(paramzzb), paramInt, paramBoolean1, paramBoolean2);
  }
  
  public void zza(zza.zzb<TurnBasedMultiplayer.LoadMatchesResult> paramzzb, int paramInt, int[] paramArrayOfInt)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new TurnBasedMatchesLoadedBinderCallbacks(paramzzb), paramInt, paramArrayOfInt);
  }
  
  public void zza(zza.zzb<Leaderboards.LoadScoresResult> paramzzb, LeaderboardScoreBuffer paramLeaderboardScoreBuffer, int paramInt1, int paramInt2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new LeaderboardScoresLoadedBinderCallback(paramzzb), paramLeaderboardScoreBuffer.zzxJ().asBundle(), paramInt1, paramInt2);
  }
  
  public void zza(zza.zzb<TurnBasedMultiplayer.InitiateMatchResult> paramzzb, TurnBasedMatchConfig paramTurnBasedMatchConfig)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new TurnBasedMatchInitiatedBinderCallbacks(paramzzb), paramTurnBasedMatchConfig.getVariant(), paramTurnBasedMatchConfig.zzxP(), paramTurnBasedMatchConfig.getInvitedPlayerIds(), paramTurnBasedMatchConfig.getAutoMatchCriteria());
  }
  
  public void zza(zza.zzb<Snapshots.CommitSnapshotResult> paramzzb, Snapshot paramSnapshot, SnapshotMetadataChange paramSnapshotMetadataChange)
    throws RemoteException
  {
    SnapshotContents localSnapshotContents = paramSnapshot.getSnapshotContents();
    if (!localSnapshotContents.isClosed()) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Snapshot already closed");
      Object localObject = paramSnapshotMetadataChange.zzxU();
      if (localObject != null) {
        ((BitmapTeleporter)localObject).zzc(getContext().getCacheDir());
      }
      localObject = localSnapshotContents.zzsx();
      localSnapshotContents.close();
      ((IGamesService)zzqJ()).zza(new SnapshotCommittedBinderCallbacks(paramzzb), paramSnapshot.getMetadata().getSnapshotId(), (SnapshotMetadataChangeEntity)paramSnapshotMetadataChange, (Contents)localObject);
      return;
    }
  }
  
  public void zza(zza.zzb<Achievements.UpdateAchievementResult> paramzzb, String paramString)
    throws RemoteException
  {
    if (paramzzb == null) {}
    for (paramzzb = null;; paramzzb = new AchievementUpdatedBinderCallback(paramzzb))
    {
      ((IGamesService)zzqJ()).zza(paramzzb, paramString, this.zzaEd.zzxj(), this.zzaEd.zzxi());
      return;
    }
  }
  
  public void zza(zza.zzb<Achievements.UpdateAchievementResult> paramzzb, String paramString, int paramInt)
    throws RemoteException
  {
    if (paramzzb == null) {}
    for (paramzzb = null;; paramzzb = new AchievementUpdatedBinderCallback(paramzzb))
    {
      ((IGamesService)zzqJ()).zza(paramzzb, paramString, paramInt, this.zzaEd.zzxj(), this.zzaEd.zzxi());
      return;
    }
  }
  
  public void zza(zza.zzb<Leaderboards.LoadScoresResult> paramzzb, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new LeaderboardScoresLoadedBinderCallback(paramzzb), paramString, paramInt1, paramInt2, paramInt3, paramBoolean);
  }
  
  public void zza(zza.zzb<Players.LoadPlayersResult> paramzzb, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException
  {
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        throw new IllegalArgumentException("Invalid player collection: " + paramString);
        if (paramString.equals("played_with")) {
          i = 0;
        }
        break;
      }
    }
    ((IGamesService)zzqJ()).zzd(new PlayersLoadedBinderCallback(paramzzb), paramString, paramInt, paramBoolean1, paramBoolean2);
  }
  
  public void zza(zza.zzb<TurnBasedMultiplayer.LoadMatchesResult> paramzzb, String paramString, int paramInt, int[] paramArrayOfInt)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new TurnBasedMatchesLoadedBinderCallbacks(paramzzb), paramString, paramInt, paramArrayOfInt);
  }
  
  public void zza(zza.zzb<Leaderboards.SubmitScoreResult> paramzzb, String paramString1, long paramLong, String paramString2)
    throws RemoteException
  {
    if (paramzzb == null) {}
    for (paramzzb = null;; paramzzb = new SubmitScoreBinderCallbacks(paramzzb))
    {
      ((IGamesService)zzqJ()).zza(paramzzb, paramString1, paramLong, paramString2);
      return;
    }
  }
  
  public void zza(zza.zzb<TurnBasedMultiplayer.LeaveMatchResult> paramzzb, String paramString1, String paramString2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzc(new TurnBasedMatchLeftBinderCallbacks(paramzzb), paramString1, paramString2);
  }
  
  public void zza(zza.zzb<Leaderboards.LoadPlayerScoreResult> paramzzb, String paramString1, String paramString2, int paramInt1, int paramInt2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new PlayerLeaderboardScoreLoadedBinderCallback(paramzzb), paramString1, paramString2, paramInt1, paramInt2);
  }
  
  public void zza(zza.zzb<Requests.LoadRequestsResult> paramzzb, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new RequestsLoadedBinderCallbacks(paramzzb), paramString1, paramString2, paramInt1, paramInt2, paramInt3);
  }
  
  public void zza(zza.zzb<Leaderboards.LoadScoresResult> paramzzb, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new LeaderboardScoresLoadedBinderCallback(paramzzb), paramString1, paramString2, paramInt1, paramInt2, paramInt3, paramBoolean);
  }
  
  public void zza(zza.zzb<Players.LoadPlayersResult> paramzzb, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException
  {
    int i = -1;
    switch (paramString1.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        throw new IllegalArgumentException("Invalid player collection: " + paramString1);
        if (paramString1.equals("circled"))
        {
          i = 0;
          continue;
          if (paramString1.equals("played_with"))
          {
            i = 1;
            continue;
            if (paramString1.equals("nearby")) {
              i = 2;
            }
          }
        }
        break;
      }
    }
    ((IGamesService)zzqJ()).zza(new PlayersLoadedBinderCallback(paramzzb), paramString1, paramString2, paramInt, paramBoolean1, paramBoolean2);
  }
  
  public void zza(zza.zzb<Snapshots.OpenSnapshotResult> paramzzb, String paramString1, String paramString2, SnapshotMetadataChange paramSnapshotMetadataChange, SnapshotContents paramSnapshotContents)
    throws RemoteException
  {
    if (!paramSnapshotContents.isClosed()) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "SnapshotContents already closed");
      Object localObject = paramSnapshotMetadataChange.zzxU();
      if (localObject != null) {
        ((BitmapTeleporter)localObject).zzc(getContext().getCacheDir());
      }
      localObject = paramSnapshotContents.zzsx();
      paramSnapshotContents.close();
      ((IGamesService)zzqJ()).zza(new SnapshotOpenedBinderCallbacks(paramzzb), paramString1, paramString2, (SnapshotMetadataChangeEntity)paramSnapshotMetadataChange, (Contents)localObject);
      return;
    }
  }
  
  public void zza(zza.zzb<Leaderboards.LeaderboardMetadataResult> paramzzb, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzb(new LeaderboardsLoadedBinderCallback(paramzzb), paramString1, paramString2, paramBoolean);
  }
  
  public void zza(zza.zzb<Quests.LoadQuestsResult> paramzzb, String paramString1, String paramString2, boolean paramBoolean, String[] paramArrayOfString)
    throws RemoteException
  {
    this.zzaDZ.flush();
    ((IGamesService)zzqJ()).zza(new QuestsLoadedBinderCallbacks(paramzzb), paramString1, paramString2, paramArrayOfString, paramBoolean);
  }
  
  public void zza(zza.zzb<Quests.LoadQuestsResult> paramzzb, String paramString1, String paramString2, int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
    throws RemoteException
  {
    this.zzaDZ.flush();
    ((IGamesService)zzqJ()).zza(new QuestsLoadedBinderCallbacks(paramzzb), paramString1, paramString2, paramArrayOfInt, paramInt, paramBoolean);
  }
  
  public void zza(zza.zzb<Requests.UpdateRequestsResult> paramzzb, String paramString1, String paramString2, String[] paramArrayOfString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new RequestsUpdatedBinderCallbacks(paramzzb), paramString1, paramString2, paramArrayOfString);
  }
  
  public void zza(zza.zzb<Players.LoadPlayersResult> paramzzb, String paramString, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzf(new PlayersLoadedBinderCallback(paramzzb), paramString, paramBoolean);
  }
  
  public void zza(zza.zzb<Snapshots.OpenSnapshotResult> paramzzb, String paramString, boolean paramBoolean, int paramInt)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new SnapshotOpenedBinderCallbacks(paramzzb), paramString, paramBoolean, paramInt);
  }
  
  public void zza(zza.zzb<TurnBasedMultiplayer.UpdateMatchResult> paramzzb, String paramString1, byte[] paramArrayOfByte, String paramString2, ParticipantResult[] paramArrayOfParticipantResult)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new TurnBasedMatchUpdatedBinderCallbacks(paramzzb), paramString1, paramArrayOfByte, paramString2, paramArrayOfParticipantResult);
  }
  
  public void zza(zza.zzb<TurnBasedMultiplayer.UpdateMatchResult> paramzzb, String paramString, byte[] paramArrayOfByte, ParticipantResult[] paramArrayOfParticipantResult)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new TurnBasedMatchUpdatedBinderCallbacks(paramzzb), paramString, paramArrayOfByte, paramArrayOfParticipantResult);
  }
  
  public void zza(zza.zzb<Requests.SendRequestResult> paramzzb, String paramString, String[] paramArrayOfString, int paramInt1, byte[] paramArrayOfByte, int paramInt2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new RequestSentBinderCallbacks(paramzzb), paramString, paramArrayOfString, paramInt1, paramArrayOfByte, paramInt2);
  }
  
  public void zza(zza.zzb<Players.LoadPlayersResult> paramzzb, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzc(new PlayersLoadedBinderCallback(paramzzb), paramBoolean);
  }
  
  public void zza(zza.zzb<Status> paramzzb, boolean paramBoolean, Bundle paramBundle)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new ContactSettingsUpdatedBinderCallback(paramzzb), paramBoolean, paramBundle);
  }
  
  public void zza(zza.zzb<Events.LoadEventsResult> paramzzb, boolean paramBoolean, String... paramVarArgs)
    throws RemoteException
  {
    this.zzaDZ.flush();
    ((IGamesService)zzqJ()).zza(new EventsLoadedBinderCallback(paramzzb), paramBoolean, paramVarArgs);
  }
  
  public void zza(zza.zzb<Quests.LoadQuestsResult> paramzzb, int[] paramArrayOfInt, int paramInt, boolean paramBoolean)
    throws RemoteException
  {
    this.zzaDZ.flush();
    ((IGamesService)zzqJ()).zza(new QuestsLoadedBinderCallbacks(paramzzb), paramArrayOfInt, paramInt, paramBoolean);
  }
  
  public void zza(zza.zzb<Players.LoadPlayersResult> paramzzb, String[] paramArrayOfString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzc(new PlayersLoadedBinderCallback(paramzzb), paramArrayOfString);
  }
  
  public void zza(zzq<OnInvitationReceivedListener> paramzzq)
  {
    try
    {
      paramzzq = new InvitationReceivedBinderCallback(paramzzq);
      ((IGamesService)zzqJ()).zza(paramzzq, this.zzaEg);
      return;
    }
    catch (RemoteException paramzzq)
    {
      zzb(paramzzq);
    }
  }
  
  public void zza(zzq<RoomUpdateListener> paramzzq, zzq<RoomStatusUpdateListener> paramzzq1, zzq<RealTimeMessageReceivedListener> paramzzq2, RoomConfig paramRoomConfig)
  {
    try
    {
      paramzzq = new RoomBinderCallbacks(paramzzq, paramzzq1, paramzzq2);
      ((IGamesService)zzqJ()).zza(paramzzq, this.zzaEf, paramRoomConfig.getVariant(), paramRoomConfig.getInvitedPlayerIds(), paramRoomConfig.getAutoMatchCriteria(), false, this.zzaEg);
      return;
    }
    catch (RemoteException paramzzq)
    {
      zzb(paramzzq);
    }
  }
  
  public void zza(zzq<RoomUpdateListener> paramzzq, String paramString)
  {
    try
    {
      ((IGamesService)zzqJ()).zzc(new RoomBinderCallbacks(paramzzq), paramString);
      return;
    }
    catch (RemoteException paramzzq)
    {
      zzb(paramzzq);
    }
  }
  
  public void zza(Games.BaseGamesApiMethodImpl<Status> paramBaseGamesApiMethodImpl, String paramString1, String paramString2, VideoConfiguration paramVideoConfiguration)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new StartRecordingBinderCallback(paramBaseGamesApiMethodImpl), paramString1, paramString2, paramVideoConfiguration);
  }
  
  public void zza(@NonNull IGamesService paramIGamesService)
  {
    super.zza(paramIGamesService);
    if (this.zzaEe)
    {
      this.zzaEd.zzxh();
      this.zzaEe = false;
    }
    if (!this.zzaEh.zzaCE) {
      zzb(paramIGamesService);
    }
  }
  
  public void zza(Snapshot paramSnapshot)
  {
    paramSnapshot = paramSnapshot.getSnapshotContents();
    if (!paramSnapshot.isClosed()) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Snapshot already closed");
      Contents localContents = paramSnapshot.zzsx();
      paramSnapshot.close();
      try
      {
        ((IGamesService)zzqJ()).zza(localContents);
        return;
      }
      catch (RemoteException paramSnapshot)
      {
        zzb(paramSnapshot);
      }
    }
  }
  
  public String zzah(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.zzaEb != null)) {
      return this.zzaEb.getPlayerId();
    }
    try
    {
      String str = ((IGamesService)zzqJ()).zzwT();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  public Intent zzb(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    try
    {
      Intent localIntent = ((IGamesService)zzqJ()).zzb(paramInt1, paramInt2, paramBoolean);
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  public Intent zzb(int[] paramArrayOfInt)
  {
    try
    {
      paramArrayOfInt = ((IGamesService)zzqJ()).zzb(paramArrayOfInt);
      return paramArrayOfInt;
    }
    catch (RemoteException paramArrayOfInt)
    {
      zzb(paramArrayOfInt);
    }
    return null;
  }
  
  protected Set<Scope> zzb(Set<Scope> paramSet)
  {
    Scope localScope1 = new Scope("https://www.googleapis.com/auth/games");
    Scope localScope2 = new Scope("https://www.googleapis.com/auth/games.firstparty");
    Iterator localIterator = paramSet.iterator();
    int i = 0;
    boolean bool = false;
    Scope localScope3;
    if (localIterator.hasNext())
    {
      localScope3 = (Scope)localIterator.next();
      if (localScope3.equals(localScope1)) {
        bool = true;
      }
    }
    for (;;)
    {
      break;
      if (localScope3.equals(localScope2))
      {
        i = 1;
        continue;
        if (i != 0)
        {
          if (!bool) {}
          for (bool = true;; bool = false)
          {
            zzx.zza(bool, "Cannot have both %s and %s!", new Object[] { "https://www.googleapis.com/auth/games", "https://www.googleapis.com/auth/games.firstparty" });
            return paramSet;
          }
        }
        zzx.zza(bool, "Games APIs requires %s to function.", new Object[] { "https://www.googleapis.com/auth/games" });
        return paramSet;
      }
    }
  }
  
  public void zzb(zza.zzb<Players.LoadPlayersResult> paramzzb, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzb(new PlayersLoadedBinderCallback(paramzzb), paramInt, paramBoolean1, paramBoolean2);
  }
  
  public void zzb(zza.zzb<Achievements.UpdateAchievementResult> paramzzb, String paramString)
    throws RemoteException
  {
    if (paramzzb == null) {}
    for (paramzzb = null;; paramzzb = new AchievementUpdatedBinderCallback(paramzzb))
    {
      ((IGamesService)zzqJ()).zzb(paramzzb, paramString, this.zzaEd.zzxj(), this.zzaEd.zzxi());
      return;
    }
  }
  
  public void zzb(zza.zzb<Achievements.UpdateAchievementResult> paramzzb, String paramString, int paramInt)
    throws RemoteException
  {
    if (paramzzb == null) {}
    for (paramzzb = null;; paramzzb = new AchievementUpdatedBinderCallback(paramzzb))
    {
      ((IGamesService)zzqJ()).zzb(paramzzb, paramString, paramInt, this.zzaEd.zzxj(), this.zzaEd.zzxi());
      return;
    }
  }
  
  public void zzb(zza.zzb<Leaderboards.LoadScoresResult> paramzzb, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzb(new LeaderboardScoresLoadedBinderCallback(paramzzb), paramString, paramInt1, paramInt2, paramInt3, paramBoolean);
  }
  
  public void zzb(zza.zzb<Players.LoadPlayersResult> paramzzb, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzb(new PlayersLoadedBinderCallback(paramzzb), paramString, paramInt, paramBoolean1, paramBoolean2);
  }
  
  public void zzb(zza.zzb<Quests.ClaimMilestoneResult> paramzzb, String paramString1, String paramString2)
    throws RemoteException
  {
    this.zzaDZ.flush();
    ((IGamesService)zzqJ()).zzf(new QuestMilestoneClaimBinderCallbacks(paramzzb, paramString2), paramString1, paramString2);
  }
  
  public void zzb(zza.zzb<Leaderboards.LoadScoresResult> paramzzb, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzb(new LeaderboardScoresLoadedBinderCallback(paramzzb), paramString1, paramString2, paramInt1, paramInt2, paramInt3, paramBoolean);
  }
  
  public void zzb(zza.zzb<Players.LoadPlayersResult> paramzzb, String paramString1, String paramString2, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzb(new PlayersLoadedBinderCallback(paramzzb), paramString1, paramString2, paramInt, paramBoolean1, paramBoolean2);
  }
  
  public void zzb(zza.zzb<Achievements.LoadAchievementsResult> paramzzb, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new AchievementsLoadedBinderCallback(paramzzb), paramString1, paramString2, paramBoolean);
  }
  
  public void zzb(zza.zzb<Leaderboards.LeaderboardMetadataResult> paramzzb, String paramString, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzc(new LeaderboardsLoadedBinderCallback(paramzzb), paramString, paramBoolean);
  }
  
  public void zzb(zza.zzb<Leaderboards.LeaderboardMetadataResult> paramzzb, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzb(new LeaderboardsLoadedBinderCallback(paramzzb), paramBoolean);
  }
  
  public void zzb(zza.zzb<Quests.LoadQuestsResult> paramzzb, boolean paramBoolean, String[] paramArrayOfString)
    throws RemoteException
  {
    this.zzaDZ.flush();
    ((IGamesService)zzqJ()).zza(new QuestsLoadedBinderCallbacks(paramzzb), paramArrayOfString, paramBoolean);
  }
  
  public void zzb(zza.zzb<Requests.UpdateRequestsResult> paramzzb, String[] paramArrayOfString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new RequestsUpdatedBinderCallbacks(paramzzb), paramArrayOfString);
  }
  
  public void zzb(zzq<OnTurnBasedMatchUpdateReceivedListener> paramzzq)
  {
    try
    {
      paramzzq = new MatchUpdateReceivedBinderCallback(paramzzq);
      ((IGamesService)zzqJ()).zzb(paramzzq, this.zzaEg);
      return;
    }
    catch (RemoteException paramzzq)
    {
      zzb(paramzzq);
    }
  }
  
  public void zzb(zzq<RoomUpdateListener> paramzzq, zzq<RoomStatusUpdateListener> paramzzq1, zzq<RealTimeMessageReceivedListener> paramzzq2, RoomConfig paramRoomConfig)
  {
    try
    {
      paramzzq = new RoomBinderCallbacks(paramzzq, paramzzq1, paramzzq2);
      ((IGamesService)zzqJ()).zza(paramzzq, this.zzaEf, paramRoomConfig.getInvitationId(), false, this.zzaEg);
      return;
    }
    catch (RemoteException paramzzq)
    {
      zzb(paramzzq);
    }
  }
  
  public void zzb(IGamesService paramIGamesService)
  {
    try
    {
      paramIGamesService.zza(new PopupLocationInfoBinderCallbacks(this.zzaEd), this.zzaEg);
      return;
    }
    catch (RemoteException paramIGamesService)
    {
      zzb(paramIGamesService);
    }
  }
  
  public void zzb(String paramString, zza.zzb<Games.GetServerAuthCodeResult> paramzzb)
    throws RemoteException
  {
    zzx.zzh(paramString, "Please provide a valid serverClientId");
    ((IGamesService)zzqJ()).zza(paramString, new GetServerAuthCodeBinderCallbacks(paramzzb));
  }
  
  protected IGamesService zzbV(IBinder paramIBinder)
  {
    return IGamesService.Stub.zzbY(paramIBinder);
  }
  
  public Intent zzc(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    try
    {
      Intent localIntent = ((IGamesService)zzqJ()).zzc(paramInt1, paramInt2, paramBoolean);
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  public void zzc(zza.zzb<Players.LoadPlayersResult> paramzzb, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzc(new PlayersLoadedBinderCallback(paramzzb), paramInt, paramBoolean1, paramBoolean2);
  }
  
  public void zzc(zza.zzb<TurnBasedMultiplayer.InitiateMatchResult> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzl(new TurnBasedMatchInitiatedBinderCallbacks(paramzzb), paramString);
  }
  
  public void zzc(zza.zzb<Players.LoadXpStreamResult> paramzzb, String paramString, int paramInt)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzb(new PlayerXpStreamLoadedBinderCallback(paramzzb), paramString, paramInt);
  }
  
  public void zzc(zza.zzb<TurnBasedMultiplayer.InitiateMatchResult> paramzzb, String paramString1, String paramString2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzd(new TurnBasedMatchInitiatedBinderCallbacks(paramzzb), paramString1, paramString2);
  }
  
  public void zzc(zza.zzb<Snapshots.LoadSnapshotsResult> paramzzb, String paramString1, String paramString2, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzc(new SnapshotsLoadedBinderCallbacks(paramzzb), paramString1, paramString2, paramBoolean);
  }
  
  public void zzc(zza.zzb<Leaderboards.LeaderboardMetadataResult> paramzzb, String paramString, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzd(new LeaderboardsLoadedBinderCallback(paramzzb), paramString, paramBoolean);
  }
  
  public void zzc(zza.zzb<Achievements.LoadAchievementsResult> paramzzb, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new AchievementsLoadedBinderCallback(paramzzb), paramBoolean);
  }
  
  public void zzc(zza.zzb<Requests.UpdateRequestsResult> paramzzb, String[] paramArrayOfString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzb(new RequestsUpdatedBinderCallbacks(paramzzb), paramArrayOfString);
  }
  
  public void zzc(zzq<QuestUpdateListener> paramzzq)
  {
    try
    {
      paramzzq = new QuestUpdateBinderCallback(paramzzq);
      ((IGamesService)zzqJ()).zzd(paramzzq, this.zzaEg);
      return;
    }
    catch (RemoteException paramzzq)
    {
      zzb(paramzzq);
    }
  }
  
  public int zzd(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      int i = ((IGamesService)zzqJ()).zzb(paramArrayOfByte, paramString, null);
      return i;
    }
    catch (RemoteException paramArrayOfByte)
    {
      zzb(paramArrayOfByte);
    }
    return -1;
  }
  
  public void zzd(zza.zzb<Games.GetTokenResult> paramzzb)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzk(new GetAuthTokenBinderCallbacks(paramzzb));
  }
  
  public void zzd(zza.zzb<Players.LoadPlayersResult> paramzzb, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zze(new PlayersLoadedBinderCallback(paramzzb), paramInt, paramBoolean1, paramBoolean2);
  }
  
  public void zzd(zza.zzb<TurnBasedMultiplayer.InitiateMatchResult> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzm(new TurnBasedMatchInitiatedBinderCallbacks(paramzzb), paramString);
  }
  
  public void zzd(zza.zzb<Players.LoadXpStreamResult> paramzzb, String paramString, int paramInt)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzc(new PlayerXpStreamLoadedBinderCallback(paramzzb), paramString, paramInt);
  }
  
  public void zzd(zza.zzb<TurnBasedMultiplayer.InitiateMatchResult> paramzzb, String paramString1, String paramString2)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zze(new TurnBasedMatchInitiatedBinderCallbacks(paramzzb), paramString1, paramString2);
  }
  
  public void zzd(zza.zzb<Notifications.GameMuteStatusChangeResult> paramzzb, String paramString, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new GameMuteStatusChangedBinderCallback(paramzzb), paramString, paramBoolean);
  }
  
  public void zzd(zza.zzb<Events.LoadEventsResult> paramzzb, boolean paramBoolean)
    throws RemoteException
  {
    this.zzaDZ.flush();
    ((IGamesService)zzqJ()).zzf(new EventsLoadedBinderCallback(paramzzb), paramBoolean);
  }
  
  public void zzd(zzq<OnRequestReceivedListener> paramzzq)
  {
    try
    {
      paramzzq = new RequestReceivedBinderCallback(paramzzq);
      ((IGamesService)zzqJ()).zzc(paramzzq, this.zzaEg);
      return;
    }
    catch (RemoteException paramzzq)
    {
      zzb(paramzzq);
    }
  }
  
  public void zzdH(String paramString)
  {
    try
    {
      ((IGamesService)zzqJ()).zzdP(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      zzb(paramString);
    }
  }
  
  public Intent zzdI(String paramString)
  {
    try
    {
      paramString = ((IGamesService)zzqJ()).zzdI(paramString);
      return paramString;
    }
    catch (RemoteException paramString)
    {
      zzb(paramString);
    }
    return null;
  }
  
  public void zzdJ(String paramString)
  {
    try
    {
      ((IGamesService)zzqJ()).zza(paramString, this.zzaEd.zzxj(), this.zzaEd.zzxi());
      return;
    }
    catch (RemoteException paramString)
    {
      zzb(paramString);
    }
  }
  
  public void zze(zza.zzb<GamesMetadata.LoadGamesResult> paramzzb)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzd(new GamesLoadedBinderCallback(paramzzb));
  }
  
  public void zze(zza.zzb<TurnBasedMultiplayer.LeaveMatchResult> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzo(new TurnBasedMatchLeftBinderCallbacks(paramzzb), paramString);
  }
  
  public void zze(zza.zzb<Invitations.LoadInvitationsResult> paramzzb, String paramString, int paramInt)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzb(new InvitationsLoadedBinderCallback(paramzzb), paramString, paramInt, false);
  }
  
  public void zze(zza.zzb<Stats.LoadPlayerStatsResult> paramzzb, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzi(new PlayerStatsLoadedBinderCallbacks(paramzzb), paramBoolean);
  }
  
  public void zzf(zza.zzb<Status> paramzzb)
    throws RemoteException
  {
    this.zzaDZ.flush();
    ((IGamesService)zzqJ()).zza(new SignOutCompleteBinderCallbacks(paramzzb));
  }
  
  public void zzf(zza.zzb<TurnBasedMultiplayer.CancelMatchResult> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzn(new TurnBasedMatchCanceledBinderCallbacks(paramzzb), paramString);
  }
  
  public void zzf(zza.zzb<Requests.LoadRequestSummariesResult> paramzzb, String paramString, int paramInt)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zza(new RequestSummariesLoadedBinderCallbacks(paramzzb), paramString, paramInt);
  }
  
  public void zzf(zza.zzb<Snapshots.LoadSnapshotsResult> paramzzb, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzd(new SnapshotsLoadedBinderCallbacks(paramzzb), paramBoolean);
  }
  
  public void zzg(zza.zzb<Videos.VideoCapabilitiesResult> paramzzb)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzm(new VideoRecordingCapabilitiesBinderCallback(paramzzb));
  }
  
  public void zzg(zza.zzb<TurnBasedMultiplayer.LoadMatchResult> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzp(new TurnBasedMatchLoadedBinderCallbacks(paramzzb), paramString);
  }
  
  public void zzg(zza.zzb<Players.LoadProfileSettingsResult> paramzzb, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzg(new ProfileSettingsLoadedBinderCallback(paramzzb), paramBoolean);
  }
  
  public void zzgs(int paramInt)
  {
    this.zzaEd.setGravity(paramInt);
  }
  
  public void zzgt(int paramInt)
  {
    try
    {
      ((IGamesService)zzqJ()).zzgt(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.games.service.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.games.internal.IGamesService";
  }
  
  public void zzh(zza.zzb<Videos.VideoAvailableResult> paramzzb)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzn(new VideoRecordingAvailableBinderCallback(paramzzb));
  }
  
  public void zzh(zza.zzb<Quests.AcceptQuestResult> paramzzb, String paramString)
    throws RemoteException
  {
    this.zzaDZ.flush();
    ((IGamesService)zzqJ()).zzu(new QuestAcceptedBinderCallbacks(paramzzb), paramString);
  }
  
  public void zzh(zza.zzb<Status> paramzzb, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzh(new ProfileSettingsUpdatedBinderCallback(paramzzb), paramBoolean);
  }
  
  public void zzi(zza.zzb<Videos.ListVideosResult> paramzzb)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzl(new ListVideosBinderCallback(paramzzb));
  }
  
  public void zzi(zza.zzb<Snapshots.DeleteSnapshotResult> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzr(new SnapshotDeletedBinderCallbacks(paramzzb), paramString);
  }
  
  public void zzi(zza.zzb<Notifications.ContactSettingLoadResult> paramzzb, boolean paramBoolean)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zze(new ContactSettingsLoadedBinderCallback(paramzzb), paramBoolean);
  }
  
  public void zzj(zza.zzb<Acls.LoadAclResult> paramzzb)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzh(new NotifyAclLoadedBinderCallback(paramzzb));
  }
  
  public void zzj(zza.zzb<GamesMetadata.LoadGameInstancesResult> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzf(new GameInstancesLoadedBinderCallback(paramzzb), paramString);
  }
  
  public void zzk(zza.zzb<Notifications.InboxCountResult> paramzzb)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzt(new InboxCountsLoadedBinderCallback(paramzzb), null);
  }
  
  public void zzk(zza.zzb<GamesMetadata.LoadGameSearchSuggestionsResult> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzq(new GameSearchSuggestionsLoadedBinderCallback(paramzzb), paramString);
  }
  
  public Intent zzl(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramString = ((IGamesService)zzqJ()).zzm(paramString, paramInt1, paramInt2);
      return paramString;
    }
    catch (RemoteException paramString)
    {
      zzb(paramString);
    }
    return null;
  }
  
  public void zzl(zza.zzb<Games.LoadExperimentsResult> paramzzb)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzo(new ExperimentsLoadedBinderCallback(paramzzb));
  }
  
  public void zzl(zza.zzb<Players.LoadXpForGameCategoriesResult> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzs(new PlayerXpForGameCategoriesLoadedBinderCallback(paramzzb), paramString);
  }
  
  public void zzm(zza.zzb<Invitations.LoadInvitationsResult> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzk(new InvitationsLoadedBinderCallback(paramzzb), paramString);
  }
  
  public boolean zzmE()
  {
    return true;
  }
  
  protected Bundle zzml()
  {
    String str = getContext().getResources().getConfiguration().locale.toString();
    Bundle localBundle = this.zzaEh.zzvD();
    localBundle.putString("com.google.android.gms.games.key.gamePackageName", this.zzaEa);
    localBundle.putString("com.google.android.gms.games.key.desiredLocale", str);
    localBundle.putParcelable("com.google.android.gms.games.key.popupWindowToken", new BinderWrapper(this.zzaEd.zzxj()));
    localBundle.putInt("com.google.android.gms.games.key.API_VERSION", 3);
    localBundle.putBundle("com.google.android.gms.games.key.signInOptions", zzh.zza(zzqH()));
    return localBundle;
  }
  
  public void zzn(zza.zzb<Status> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzj(new NotifyAclUpdatedBinderCallback(paramzzb), paramString);
  }
  
  public void zzo(View paramView)
  {
    this.zzaEd.zzp(paramView);
  }
  
  public void zzo(zza.zzb<Notifications.GameMuteStatusLoadResult> paramzzb, String paramString)
    throws RemoteException
  {
    ((IGamesService)zzqJ()).zzi(new GameMuteStatusLoadedBinderCallback(paramzzb), paramString);
  }
  
  public Bundle zzoi()
  {
    try
    {
      Bundle localBundle = ((IGamesService)zzqJ()).zzoi();
      if (localBundle != null) {
        localBundle.setClassLoader(GamesClientImpl.class.getClassLoader());
      }
      return localBundle;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  public void zzp(String paramString, int paramInt)
  {
    this.zzaDZ.zzp(paramString, paramInt);
  }
  
  public void zzq(String paramString, int paramInt)
  {
    try
    {
      ((IGamesService)zzqJ()).zzq(paramString, paramInt);
      return;
    }
    catch (RemoteException paramString)
    {
      zzb(paramString);
    }
  }
  
  public void zzr(String paramString, int paramInt)
  {
    try
    {
      ((IGamesService)zzqJ()).zzr(paramString, paramInt);
      return;
    }
    catch (RemoteException paramString)
    {
      zzb(paramString);
    }
  }
  
  public Intent zzwA()
  {
    try
    {
      Intent localIntent = ((IGamesService)zzqJ()).zzwA();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  public Intent zzwB()
  {
    try
    {
      Intent localIntent = ((IGamesService)zzqJ()).zzwB();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  public Intent zzwC()
  {
    try
    {
      Intent localIntent = ((IGamesService)zzqJ()).zzwC();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  public void zzwD()
  {
    try
    {
      ((IGamesService)zzqJ()).zzG(this.zzaEg);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
  }
  
  public void zzwE()
  {
    try
    {
      ((IGamesService)zzqJ()).zzH(this.zzaEg);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
  }
  
  public void zzwF()
  {
    try
    {
      ((IGamesService)zzqJ()).zzJ(this.zzaEg);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
  }
  
  public void zzwG()
  {
    try
    {
      ((IGamesService)zzqJ()).zzI(this.zzaEg);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
  }
  
  public Intent zzwH()
  {
    try
    {
      Intent localIntent = ((IGamesService)zzqJ()).zzwH();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  public Intent zzwI()
  {
    try
    {
      Intent localIntent = ((IGamesService)zzqJ()).zzwI();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  public int zzwJ()
  {
    try
    {
      int i = ((IGamesService)zzqJ()).zzwJ();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return 4368;
  }
  
  public String zzwK()
  {
    try
    {
      String str = ((IGamesService)zzqJ()).zzwK();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  public int zzwL()
  {
    try
    {
      int i = ((IGamesService)zzqJ()).zzwL();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return -1;
  }
  
  public Intent zzwM()
  {
    try
    {
      Intent localIntent = ((IGamesService)zzqJ()).zzwM();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  public int zzwN()
  {
    try
    {
      int i = ((IGamesService)zzqJ()).zzwN();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return -1;
  }
  
  public int zzwO()
  {
    try
    {
      int i = ((IGamesService)zzqJ()).zzwO();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return -1;
  }
  
  public int zzwP()
  {
    try
    {
      int i = ((IGamesService)zzqJ()).zzwP();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return -1;
  }
  
  public int zzwQ()
  {
    try
    {
      int i = ((IGamesService)zzqJ()).zzwQ();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return -1;
  }
  
  public void zzwR()
  {
    if (isConnected()) {}
    try
    {
      ((IGamesService)zzqJ()).zzwR();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
  }
  
  public String zzww()
  {
    try
    {
      String str = ((IGamesService)zzqJ()).zzww();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  /* Error */
  public Player zzwx()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 1480	com/google/android/gms/games/internal/GamesClientImpl:zzqI	()V
    //   4: aload_0
    //   5: monitorenter
    //   6: aload_0
    //   7: getfield 527	com/google/android/gms/games/internal/GamesClientImpl:zzaEb	Lcom/google/android/gms/games/PlayerEntity;
    //   10: astore_1
    //   11: aload_1
    //   12: ifnonnull +51 -> 63
    //   15: new 1482	com/google/android/gms/games/PlayerBuffer
    //   18: dup
    //   19: aload_0
    //   20: invokevirtual 540	com/google/android/gms/games/internal/GamesClientImpl:zzqJ	()Landroid/os/IInterface;
    //   23: checkcast 542	com/google/android/gms/games/internal/IGamesService
    //   26: invokeinterface 1486 1 0
    //   31: invokespecial 1487	com/google/android/gms/games/PlayerBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   34: astore_1
    //   35: aload_1
    //   36: invokevirtual 1488	com/google/android/gms/games/PlayerBuffer:getCount	()I
    //   39: ifle +20 -> 59
    //   42: aload_0
    //   43: aload_1
    //   44: iconst_0
    //   45: invokevirtual 1491	com/google/android/gms/games/PlayerBuffer:get	(I)Lcom/google/android/gms/games/Player;
    //   48: invokeinterface 1494 1 0
    //   53: checkcast 644	com/google/android/gms/games/PlayerEntity
    //   56: putfield 527	com/google/android/gms/games/internal/GamesClientImpl:zzaEb	Lcom/google/android/gms/games/PlayerEntity;
    //   59: aload_1
    //   60: invokevirtual 1495	com/google/android/gms/games/PlayerBuffer:release	()V
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_0
    //   66: getfield 527	com/google/android/gms/games/internal/GamesClientImpl:zzaEb	Lcom/google/android/gms/games/PlayerEntity;
    //   69: areturn
    //   70: astore_2
    //   71: aload_1
    //   72: invokevirtual 1495	com/google/android/gms/games/PlayerBuffer:release	()V
    //   75: aload_2
    //   76: athrow
    //   77: astore_1
    //   78: aload_0
    //   79: aload_1
    //   80: invokespecial 515	com/google/android/gms/games/internal/GamesClientImpl:zzb	(Landroid/os/RemoteException;)V
    //   83: goto -20 -> 63
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	GamesClientImpl
    //   10	62	1	localObject1	Object
    //   77	3	1	localRemoteException	RemoteException
    //   86	4	1	localObject2	Object
    //   70	6	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   35	59	70	finally
    //   15	35	77	android/os/RemoteException
    //   59	63	77	android/os/RemoteException
    //   71	77	77	android/os/RemoteException
    //   6	11	86	finally
    //   15	35	86	finally
    //   59	63	86	finally
    //   63	65	86	finally
    //   71	77	86	finally
    //   78	83	86	finally
    //   87	89	86	finally
  }
  
  /* Error */
  public com.google.android.gms.games.Game zzwy()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 1480	com/google/android/gms/games/internal/GamesClientImpl:zzqI	()V
    //   4: aload_0
    //   5: monitorenter
    //   6: aload_0
    //   7: getfield 529	com/google/android/gms/games/internal/GamesClientImpl:zzaEc	Lcom/google/android/gms/games/GameEntity;
    //   10: astore_1
    //   11: aload_1
    //   12: ifnonnull +51 -> 63
    //   15: new 1499	com/google/android/gms/games/GameBuffer
    //   18: dup
    //   19: aload_0
    //   20: invokevirtual 540	com/google/android/gms/games/internal/GamesClientImpl:zzqJ	()Landroid/os/IInterface;
    //   23: checkcast 542	com/google/android/gms/games/internal/IGamesService
    //   26: invokeinterface 1502 1 0
    //   31: invokespecial 1503	com/google/android/gms/games/GameBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   34: astore_1
    //   35: aload_1
    //   36: invokevirtual 1504	com/google/android/gms/games/GameBuffer:getCount	()I
    //   39: ifle +20 -> 59
    //   42: aload_0
    //   43: aload_1
    //   44: iconst_0
    //   45: invokevirtual 1507	com/google/android/gms/games/GameBuffer:get	(I)Lcom/google/android/gms/games/Game;
    //   48: invokeinterface 1510 1 0
    //   53: checkcast 648	com/google/android/gms/games/GameEntity
    //   56: putfield 529	com/google/android/gms/games/internal/GamesClientImpl:zzaEc	Lcom/google/android/gms/games/GameEntity;
    //   59: aload_1
    //   60: invokevirtual 1511	com/google/android/gms/games/GameBuffer:release	()V
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_0
    //   66: getfield 529	com/google/android/gms/games/internal/GamesClientImpl:zzaEc	Lcom/google/android/gms/games/GameEntity;
    //   69: areturn
    //   70: astore_2
    //   71: aload_1
    //   72: invokevirtual 1511	com/google/android/gms/games/GameBuffer:release	()V
    //   75: aload_2
    //   76: athrow
    //   77: astore_1
    //   78: aload_0
    //   79: aload_1
    //   80: invokespecial 515	com/google/android/gms/games/internal/GamesClientImpl:zzb	(Landroid/os/RemoteException;)V
    //   83: goto -20 -> 63
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	GamesClientImpl
    //   10	62	1	localObject1	Object
    //   77	3	1	localRemoteException	RemoteException
    //   86	4	1	localObject2	Object
    //   70	6	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   35	59	70	finally
    //   15	35	77	android/os/RemoteException
    //   59	63	77	android/os/RemoteException
    //   71	77	77	android/os/RemoteException
    //   6	11	86	finally
    //   15	35	86	finally
    //   59	63	86	finally
    //   63	65	86	finally
    //   71	77	86	finally
    //   78	83	86	finally
    //   87	89	86	finally
  }
  
  public Intent zzwz()
  {
    try
    {
      Intent localIntent = ((IGamesService)zzqJ()).zzwz();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      zzb(localRemoteException);
    }
    return null;
  }
  
  private static abstract class AbstractPeerStatusNotifier
    extends GamesClientImpl.AbstractRoomStatusNotifier
  {
    private final ArrayList<String> zzaEj = new ArrayList();
    
    AbstractPeerStatusNotifier(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super();
      int i = 0;
      int j = paramArrayOfString.length;
      while (i < j)
      {
        this.zzaEj.add(paramArrayOfString[i]);
        i += 1;
      }
    }
    
    protected void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom)
    {
      zza(paramRoomStatusUpdateListener, paramRoom, this.zzaEj);
    }
    
    protected abstract void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList);
  }
  
  private static abstract class AbstractRoomNotifier
    extends zze<RoomUpdateListener>
  {
    AbstractRoomNotifier(DataHolder paramDataHolder)
    {
      super();
    }
    
    protected void zza(RoomUpdateListener paramRoomUpdateListener, DataHolder paramDataHolder)
    {
      zza(paramRoomUpdateListener, GamesClientImpl.zzZ(paramDataHolder), paramDataHolder.getStatusCode());
    }
    
    protected abstract void zza(RoomUpdateListener paramRoomUpdateListener, Room paramRoom, int paramInt);
  }
  
  private static abstract class AbstractRoomStatusNotifier
    extends zze<RoomStatusUpdateListener>
  {
    AbstractRoomStatusNotifier(DataHolder paramDataHolder)
    {
      super();
    }
    
    protected void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder)
    {
      zza(paramRoomStatusUpdateListener, GamesClientImpl.zzZ(paramDataHolder));
    }
    
    protected abstract void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom);
  }
  
  private static final class AcceptQuestResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Quests.AcceptQuestResult
  {
    private final Quest zzaEk;
    
    /* Error */
    AcceptQuestResultImpl(DataHolder paramDataHolder)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: invokespecial 15	com/google/android/gms/games/internal/GamesClientImpl$GamesDataHolderResult:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   5: new 17	com/google/android/gms/games/quest/QuestBuffer
      //   8: dup
      //   9: aload_1
      //   10: invokespecial 18	com/google/android/gms/games/quest/QuestBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   13: astore_1
      //   14: aload_1
      //   15: invokevirtual 22	com/google/android/gms/games/quest/QuestBuffer:getCount	()I
      //   18: ifle +27 -> 45
      //   21: aload_0
      //   22: new 24	com/google/android/gms/games/quest/QuestEntity
      //   25: dup
      //   26: aload_1
      //   27: iconst_0
      //   28: invokevirtual 28	com/google/android/gms/games/quest/QuestBuffer:get	(I)Ljava/lang/Object;
      //   31: checkcast 30	com/google/android/gms/games/quest/Quest
      //   34: invokespecial 33	com/google/android/gms/games/quest/QuestEntity:<init>	(Lcom/google/android/gms/games/quest/Quest;)V
      //   37: putfield 35	com/google/android/gms/games/internal/GamesClientImpl$AcceptQuestResultImpl:zzaEk	Lcom/google/android/gms/games/quest/Quest;
      //   40: aload_1
      //   41: invokevirtual 39	com/google/android/gms/games/quest/QuestBuffer:release	()V
      //   44: return
      //   45: aload_0
      //   46: aconst_null
      //   47: putfield 35	com/google/android/gms/games/internal/GamesClientImpl$AcceptQuestResultImpl:zzaEk	Lcom/google/android/gms/games/quest/Quest;
      //   50: goto -10 -> 40
      //   53: astore_2
      //   54: aload_1
      //   55: invokevirtual 39	com/google/android/gms/games/quest/QuestBuffer:release	()V
      //   58: aload_2
      //   59: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	60	0	this	AcceptQuestResultImpl
      //   0	60	1	paramDataHolder	DataHolder
      //   53	6	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   14	40	53	finally
      //   45	50	53	finally
    }
    
    public Quest getQuest()
    {
      return this.zzaEk;
    }
  }
  
  private static final class AchievementUpdatedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Achievements.UpdateAchievementResult> zzamC;
    
    AchievementUpdatedBinderCallback(zza.zzb<Achievements.UpdateAchievementResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzh(int paramInt, String paramString)
    {
      this.zzamC.zzs(new GamesClientImpl.UpdateAchievementResultImpl(paramInt, paramString));
    }
  }
  
  private static final class AchievementsLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Achievements.LoadAchievementsResult> zzamC;
    
    AchievementsLoadedBinderCallback(zza.zzb<Achievements.LoadAchievementsResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzh(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadAchievementsResultImpl(paramDataHolder));
    }
  }
  
  private static final class AppContentLoadedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<AppContents.LoadAppContentResult> zzaEl;
    
    public AppContentLoadedBinderCallbacks(zza.zzb<AppContents.LoadAppContentResult> paramzzb)
    {
      this.zzaEl = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zza(DataHolder[] paramArrayOfDataHolder)
    {
      this.zzaEl.zzs(new GamesClientImpl.LoadAppContentsResultImpl(paramArrayOfDataHolder));
    }
  }
  
  private static final class CancelMatchResultImpl
    implements TurnBasedMultiplayer.CancelMatchResult
  {
    private final Status zzUX;
    private final String zzaEm;
    
    CancelMatchResultImpl(Status paramStatus, String paramString)
    {
      this.zzUX = paramStatus;
      this.zzaEm = paramString;
    }
    
    public String getMatchId()
    {
      return this.zzaEm;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class ClaimMilestoneResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Quests.ClaimMilestoneResult
  {
    private final Quest zzaEk;
    private final Milestone zzaEn;
    
    /* Error */
    ClaimMilestoneResultImpl(DataHolder paramDataHolder, String paramString)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore_3
      //   2: aload_0
      //   3: aload_1
      //   4: invokespecial 18	com/google/android/gms/games/internal/GamesClientImpl$GamesDataHolderResult:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   7: new 20	com/google/android/gms/games/quest/QuestBuffer
      //   10: dup
      //   11: aload_1
      //   12: invokespecial 21	com/google/android/gms/games/quest/QuestBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   15: astore_1
      //   16: aload_1
      //   17: invokevirtual 25	com/google/android/gms/games/quest/QuestBuffer:getCount	()I
      //   20: ifle +108 -> 128
      //   23: aload_0
      //   24: new 27	com/google/android/gms/games/quest/QuestEntity
      //   27: dup
      //   28: aload_1
      //   29: iconst_0
      //   30: invokevirtual 31	com/google/android/gms/games/quest/QuestBuffer:get	(I)Ljava/lang/Object;
      //   33: checkcast 33	com/google/android/gms/games/quest/Quest
      //   36: invokespecial 36	com/google/android/gms/games/quest/QuestEntity:<init>	(Lcom/google/android/gms/games/quest/Quest;)V
      //   39: putfield 38	com/google/android/gms/games/internal/GamesClientImpl$ClaimMilestoneResultImpl:zzaEk	Lcom/google/android/gms/games/quest/Quest;
      //   42: aload_0
      //   43: getfield 38	com/google/android/gms/games/internal/GamesClientImpl$ClaimMilestoneResultImpl:zzaEk	Lcom/google/android/gms/games/quest/Quest;
      //   46: invokeinterface 42 1 0
      //   51: astore 5
      //   53: aload 5
      //   55: invokeinterface 47 1 0
      //   60: istore 4
      //   62: iload_3
      //   63: iload 4
      //   65: if_icmpge +53 -> 118
      //   68: aload 5
      //   70: iload_3
      //   71: invokeinterface 48 2 0
      //   76: checkcast 50	com/google/android/gms/games/quest/Milestone
      //   79: invokeinterface 54 1 0
      //   84: aload_2
      //   85: invokevirtual 60	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   88: ifeq +23 -> 111
      //   91: aload_0
      //   92: aload 5
      //   94: iload_3
      //   95: invokeinterface 48 2 0
      //   100: checkcast 50	com/google/android/gms/games/quest/Milestone
      //   103: putfield 62	com/google/android/gms/games/internal/GamesClientImpl$ClaimMilestoneResultImpl:zzaEn	Lcom/google/android/gms/games/quest/Milestone;
      //   106: aload_1
      //   107: invokevirtual 66	com/google/android/gms/games/quest/QuestBuffer:release	()V
      //   110: return
      //   111: iload_3
      //   112: iconst_1
      //   113: iadd
      //   114: istore_3
      //   115: goto -53 -> 62
      //   118: aload_0
      //   119: aconst_null
      //   120: putfield 62	com/google/android/gms/games/internal/GamesClientImpl$ClaimMilestoneResultImpl:zzaEn	Lcom/google/android/gms/games/quest/Milestone;
      //   123: aload_1
      //   124: invokevirtual 66	com/google/android/gms/games/quest/QuestBuffer:release	()V
      //   127: return
      //   128: aload_0
      //   129: aconst_null
      //   130: putfield 62	com/google/android/gms/games/internal/GamesClientImpl$ClaimMilestoneResultImpl:zzaEn	Lcom/google/android/gms/games/quest/Milestone;
      //   133: aload_0
      //   134: aconst_null
      //   135: putfield 38	com/google/android/gms/games/internal/GamesClientImpl$ClaimMilestoneResultImpl:zzaEk	Lcom/google/android/gms/games/quest/Quest;
      //   138: goto -15 -> 123
      //   141: astore_2
      //   142: aload_1
      //   143: invokevirtual 66	com/google/android/gms/games/quest/QuestBuffer:release	()V
      //   146: aload_2
      //   147: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	148	0	this	ClaimMilestoneResultImpl
      //   0	148	1	paramDataHolder	DataHolder
      //   0	148	2	paramString	String
      //   1	114	3	i	int
      //   60	6	4	j	int
      //   51	42	5	localList	List
      // Exception table:
      //   from	to	target	type
      //   16	62	141	finally
      //   68	106	141	finally
      //   118	123	141	finally
      //   128	138	141	finally
    }
    
    public Milestone getMilestone()
    {
      return this.zzaEn;
    }
    
    public Quest getQuest()
    {
      return this.zzaEk;
    }
  }
  
  private static final class CommitSnapshotResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Snapshots.CommitSnapshotResult
  {
    private final SnapshotMetadata zzaEo;
    
    /* Error */
    CommitSnapshotResultImpl(DataHolder paramDataHolder)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: invokespecial 15	com/google/android/gms/games/internal/GamesClientImpl$GamesDataHolderResult:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   5: new 17	com/google/android/gms/games/snapshot/SnapshotMetadataBuffer
      //   8: dup
      //   9: aload_1
      //   10: invokespecial 18	com/google/android/gms/games/snapshot/SnapshotMetadataBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   13: astore_1
      //   14: aload_1
      //   15: invokevirtual 22	com/google/android/gms/games/snapshot/SnapshotMetadataBuffer:getCount	()I
      //   18: ifle +24 -> 42
      //   21: aload_0
      //   22: new 24	com/google/android/gms/games/snapshot/SnapshotMetadataEntity
      //   25: dup
      //   26: aload_1
      //   27: iconst_0
      //   28: invokevirtual 28	com/google/android/gms/games/snapshot/SnapshotMetadataBuffer:get	(I)Lcom/google/android/gms/games/snapshot/SnapshotMetadata;
      //   31: invokespecial 31	com/google/android/gms/games/snapshot/SnapshotMetadataEntity:<init>	(Lcom/google/android/gms/games/snapshot/SnapshotMetadata;)V
      //   34: putfield 33	com/google/android/gms/games/internal/GamesClientImpl$CommitSnapshotResultImpl:zzaEo	Lcom/google/android/gms/games/snapshot/SnapshotMetadata;
      //   37: aload_1
      //   38: invokevirtual 37	com/google/android/gms/games/snapshot/SnapshotMetadataBuffer:release	()V
      //   41: return
      //   42: aload_0
      //   43: aconst_null
      //   44: putfield 33	com/google/android/gms/games/internal/GamesClientImpl$CommitSnapshotResultImpl:zzaEo	Lcom/google/android/gms/games/snapshot/SnapshotMetadata;
      //   47: goto -10 -> 37
      //   50: astore_2
      //   51: aload_1
      //   52: invokevirtual 37	com/google/android/gms/games/snapshot/SnapshotMetadataBuffer:release	()V
      //   55: aload_2
      //   56: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	57	0	this	CommitSnapshotResultImpl
      //   0	57	1	paramDataHolder	DataHolder
      //   50	6	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   14	37	50	finally
      //   42	47	50	finally
    }
    
    public SnapshotMetadata getSnapshotMetadata()
    {
      return this.zzaEo;
    }
  }
  
  private static final class ConnectedToRoomNotifier
    extends GamesClientImpl.AbstractRoomStatusNotifier
  {
    ConnectedToRoomNotifier(DataHolder paramDataHolder)
    {
      super();
    }
    
    public void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom)
    {
      paramRoomStatusUpdateListener.onConnectedToRoom(paramRoom);
    }
  }
  
  private static final class ContactSettingLoadResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Notifications.ContactSettingLoadResult
  {
    ContactSettingLoadResultImpl(DataHolder paramDataHolder)
    {
      super();
    }
  }
  
  private static final class ContactSettingsLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Notifications.ContactSettingLoadResult> zzamC;
    
    ContactSettingsLoadedBinderCallback(zza.zzb<Notifications.ContactSettingLoadResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzI(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.ContactSettingLoadResultImpl(paramDataHolder));
    }
  }
  
  private static final class ContactSettingsUpdatedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Status> zzamC;
    
    ContactSettingsUpdatedBinderCallback(zza.zzb<Status> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzgo(int paramInt)
    {
      this.zzamC.zzs(GamesStatusCodes.zzgc(paramInt));
    }
  }
  
  private static final class DeleteSnapshotResultImpl
    implements Snapshots.DeleteSnapshotResult
  {
    private final Status zzUX;
    private final String zzaEp;
    
    DeleteSnapshotResultImpl(int paramInt, String paramString)
    {
      this.zzUX = GamesStatusCodes.zzgc(paramInt);
      this.zzaEp = paramString;
    }
    
    public String getSnapshotId()
    {
      return this.zzaEp;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class DisconnectedFromRoomNotifier
    extends GamesClientImpl.AbstractRoomStatusNotifier
  {
    DisconnectedFromRoomNotifier(DataHolder paramDataHolder)
    {
      super();
    }
    
    public void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom)
    {
      paramRoomStatusUpdateListener.onDisconnectedFromRoom(paramRoom);
    }
  }
  
  private static final class EventsLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Events.LoadEventsResult> zzamC;
    
    EventsLoadedBinderCallback(zza.zzb<Events.LoadEventsResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzi(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadEventResultImpl(paramDataHolder));
    }
  }
  
  private static final class ExperimentsLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Games.LoadExperimentsResult> zzamC;
    
    ExperimentsLoadedBinderCallback(zza.zzb<Games.LoadExperimentsResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zza(int paramInt, long[] paramArrayOfLong)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadExperimentsResultImpl(paramInt, paramArrayOfLong));
    }
  }
  
  private class GameClientEventIncrementCache
    extends EventIncrementCache
  {
    public GameClientEventIncrementCache()
    {
      super(1000);
    }
    
    protected void zzs(String paramString, int paramInt)
    {
      try
      {
        if (GamesClientImpl.this.isConnected())
        {
          ((IGamesService)GamesClientImpl.this.zzqJ()).zzp(paramString, paramInt);
          return;
        }
        GamesLog.zzA("GamesClientImpl", "Unable to increment event " + paramString + " by " + paramInt + " because the games client is no longer connected");
        return;
      }
      catch (RemoteException paramString)
      {
        GamesClientImpl.zza(GamesClientImpl.this, paramString);
      }
    }
  }
  
  private static final class GameInstancesLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<GamesMetadata.LoadGameInstancesResult> zzamC;
    
    GameInstancesLoadedBinderCallback(zza.zzb<GamesMetadata.LoadGameInstancesResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzp(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadGameInstancesResultImpl(paramDataHolder));
    }
  }
  
  private static final class GameMuteStatusChangeResultImpl
    implements Notifications.GameMuteStatusChangeResult
  {
    private final Status zzUX;
    private final String zzaEq;
    private final boolean zzaEr;
    
    public GameMuteStatusChangeResultImpl(int paramInt, String paramString, boolean paramBoolean)
    {
      this.zzUX = GamesStatusCodes.zzgc(paramInt);
      this.zzaEq = paramString;
      this.zzaEr = paramBoolean;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class GameMuteStatusChangedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Notifications.GameMuteStatusChangeResult> zzamC;
    
    GameMuteStatusChangedBinderCallback(zza.zzb<Notifications.GameMuteStatusChangeResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zza(int paramInt, String paramString, boolean paramBoolean)
    {
      this.zzamC.zzs(new GamesClientImpl.GameMuteStatusChangeResultImpl(paramInt, paramString, paramBoolean));
    }
  }
  
  private static final class GameMuteStatusLoadResultImpl
    implements Notifications.GameMuteStatusLoadResult
  {
    private final Status zzUX;
    private final String zzaEq;
    private final boolean zzaEr;
    
    /* Error */
    public GameMuteStatusLoadResultImpl(DataHolder paramDataHolder)
    {
      // Byte code:
      //   0: aload_0
      //   1: invokespecial 20	java/lang/Object:<init>	()V
      //   4: aload_0
      //   5: aload_1
      //   6: invokevirtual 26	com/google/android/gms/common/data/DataHolder:getStatusCode	()I
      //   9: invokestatic 32	com/google/android/gms/games/GamesStatusCodes:zzgc	(I)Lcom/google/android/gms/common/api/Status;
      //   12: putfield 34	com/google/android/gms/games/internal/GamesClientImpl$GameMuteStatusLoadResultImpl:zzUX	Lcom/google/android/gms/common/api/Status;
      //   15: aload_1
      //   16: invokevirtual 37	com/google/android/gms/common/data/DataHolder:getCount	()I
      //   19: ifle +32 -> 51
      //   22: aload_0
      //   23: aload_1
      //   24: ldc 39
      //   26: iconst_0
      //   27: iconst_0
      //   28: invokevirtual 43	com/google/android/gms/common/data/DataHolder:zzd	(Ljava/lang/String;II)Ljava/lang/String;
      //   31: putfield 45	com/google/android/gms/games/internal/GamesClientImpl$GameMuteStatusLoadResultImpl:zzaEq	Ljava/lang/String;
      //   34: aload_0
      //   35: aload_1
      //   36: ldc 47
      //   38: iconst_0
      //   39: iconst_0
      //   40: invokevirtual 51	com/google/android/gms/common/data/DataHolder:zze	(Ljava/lang/String;II)Z
      //   43: putfield 53	com/google/android/gms/games/internal/GamesClientImpl$GameMuteStatusLoadResultImpl:zzaEr	Z
      //   46: aload_1
      //   47: invokevirtual 56	com/google/android/gms/common/data/DataHolder:close	()V
      //   50: return
      //   51: aload_0
      //   52: aconst_null
      //   53: putfield 45	com/google/android/gms/games/internal/GamesClientImpl$GameMuteStatusLoadResultImpl:zzaEq	Ljava/lang/String;
      //   56: aload_0
      //   57: iconst_0
      //   58: putfield 53	com/google/android/gms/games/internal/GamesClientImpl$GameMuteStatusLoadResultImpl:zzaEr	Z
      //   61: goto -15 -> 46
      //   64: astore_2
      //   65: aload_1
      //   66: invokevirtual 56	com/google/android/gms/common/data/DataHolder:close	()V
      //   69: aload_2
      //   70: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	71	0	this	GameMuteStatusLoadResultImpl
      //   0	71	1	paramDataHolder	DataHolder
      //   64	6	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   4	46	64	finally
      //   51	61	64	finally
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class GameMuteStatusLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Notifications.GameMuteStatusLoadResult> zzamC;
    
    GameMuteStatusLoadedBinderCallback(zza.zzb<Notifications.GameMuteStatusLoadResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzG(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.GameMuteStatusLoadResultImpl(paramDataHolder));
    }
  }
  
  private static final class GameSearchSuggestionsLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<GamesMetadata.LoadGameSearchSuggestionsResult> zzamC;
    
    GameSearchSuggestionsLoadedBinderCallback(zza.zzb<GamesMetadata.LoadGameSearchSuggestionsResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzq(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadGameSearchSuggestionsResultImpl(paramDataHolder));
    }
  }
  
  private static abstract class GamesDataHolderResult
    extends com.google.android.gms.common.api.internal.zzf
  {
    protected GamesDataHolderResult(DataHolder paramDataHolder)
    {
      super(GamesStatusCodes.zzgc(paramDataHolder.getStatusCode()));
    }
  }
  
  private static final class GamesLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<GamesMetadata.LoadGamesResult> zzamC;
    
    GamesLoadedBinderCallback(zza.zzb<GamesMetadata.LoadGamesResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzn(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadGamesResultImpl(paramDataHolder));
    }
  }
  
  private static final class GetAuthTokenBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Games.GetTokenResult> zzamC;
    
    public GetAuthTokenBinderCallbacks(zza.zzb<Games.GetTokenResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzg(int paramInt, String paramString)
    {
      Status localStatus = GamesStatusCodes.zzgc(paramInt);
      this.zzamC.zzs(new GamesClientImpl.GetTokenResultImpl(localStatus, paramString));
    }
  }
  
  private static final class GetServerAuthCodeBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Games.GetServerAuthCodeResult> zzamC;
    
    public GetServerAuthCodeBinderCallbacks(zza.zzb<Games.GetServerAuthCodeResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzg(int paramInt, String paramString)
    {
      Status localStatus = GamesStatusCodes.zzgc(paramInt);
      this.zzamC.zzs(new GamesClientImpl.GetServerAuthCodeResultImpl(localStatus, paramString));
    }
  }
  
  private static final class GetServerAuthCodeResultImpl
    implements Games.GetServerAuthCodeResult
  {
    private final Status zzUX;
    private final String zzaEs;
    
    GetServerAuthCodeResultImpl(Status paramStatus, String paramString)
    {
      this.zzUX = paramStatus;
      this.zzaEs = paramString;
    }
    
    public String getCode()
    {
      return this.zzaEs;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class GetTokenResultImpl
    implements Games.GetTokenResult
  {
    private final Status zzUX;
    private final String zzVo;
    
    GetTokenResultImpl(Status paramStatus, String paramString)
    {
      this.zzUX = paramStatus;
      this.zzVo = paramString;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class InboxCountResultImpl
    implements Notifications.InboxCountResult
  {
    private final Status zzUX;
    private final Bundle zzaEt;
    
    InboxCountResultImpl(Status paramStatus, Bundle paramBundle)
    {
      this.zzUX = paramStatus;
      this.zzaEt = paramBundle;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class InboxCountsLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Notifications.InboxCountResult> zzamC;
    
    InboxCountsLoadedBinderCallback(zza.zzb<Notifications.InboxCountResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzg(int paramInt, Bundle paramBundle)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      Status localStatus = GamesStatusCodes.zzgc(paramInt);
      this.zzamC.zzs(new GamesClientImpl.InboxCountResultImpl(localStatus, paramBundle));
    }
  }
  
  private static final class InitiateMatchResultImpl
    extends GamesClientImpl.TurnBasedMatchResult
    implements TurnBasedMultiplayer.InitiateMatchResult
  {
    InitiateMatchResultImpl(DataHolder paramDataHolder)
    {
      super();
    }
  }
  
  private static final class InvitationReceivedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zzq<OnInvitationReceivedListener> zzari;
    
    InvitationReceivedBinderCallback(zzq<OnInvitationReceivedListener> paramzzq)
    {
      this.zzari = paramzzq;
    }
    
    public void onInvitationRemoved(String paramString)
    {
      this.zzari.zza(new GamesClientImpl.InvitationRemovedNotifier(paramString));
    }
    
    public void zzs(DataHolder paramDataHolder)
    {
      InvitationBuffer localInvitationBuffer = new InvitationBuffer(paramDataHolder);
      paramDataHolder = null;
      try
      {
        if (localInvitationBuffer.getCount() > 0) {
          paramDataHolder = (Invitation)((Invitation)localInvitationBuffer.get(0)).freeze();
        }
        localInvitationBuffer.release();
        if (paramDataHolder != null) {
          this.zzari.zza(new GamesClientImpl.InvitationReceivedNotifier(paramDataHolder));
        }
        return;
      }
      finally
      {
        localInvitationBuffer.release();
      }
    }
  }
  
  private static final class InvitationReceivedNotifier
    implements zzq.zzb<OnInvitationReceivedListener>
  {
    private final Invitation zzaEu;
    
    InvitationReceivedNotifier(Invitation paramInvitation)
    {
      this.zzaEu = paramInvitation;
    }
    
    public void zza(OnInvitationReceivedListener paramOnInvitationReceivedListener)
    {
      paramOnInvitationReceivedListener.onInvitationReceived(this.zzaEu);
    }
    
    public void zzpr() {}
  }
  
  private static final class InvitationRemovedNotifier
    implements zzq.zzb<OnInvitationReceivedListener>
  {
    private final String zzUO;
    
    InvitationRemovedNotifier(String paramString)
    {
      this.zzUO = paramString;
    }
    
    public void zza(OnInvitationReceivedListener paramOnInvitationReceivedListener)
    {
      paramOnInvitationReceivedListener.onInvitationRemoved(this.zzUO);
    }
    
    public void zzpr() {}
  }
  
  private static final class InvitationsLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Invitations.LoadInvitationsResult> zzamC;
    
    InvitationsLoadedBinderCallback(zza.zzb<Invitations.LoadInvitationsResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzr(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadInvitationsResultImpl(paramDataHolder));
    }
  }
  
  private static final class JoinedRoomNotifier
    extends GamesClientImpl.AbstractRoomNotifier
  {
    public JoinedRoomNotifier(DataHolder paramDataHolder)
    {
      super();
    }
    
    public void zza(RoomUpdateListener paramRoomUpdateListener, Room paramRoom, int paramInt)
    {
      paramRoomUpdateListener.onJoinedRoom(paramInt, paramRoom);
    }
  }
  
  private static final class LeaderboardMetadataResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Leaderboards.LeaderboardMetadataResult
  {
    private final LeaderboardBuffer zzaEv;
    
    LeaderboardMetadataResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzaEv = new LeaderboardBuffer(paramDataHolder);
    }
    
    public LeaderboardBuffer getLeaderboards()
    {
      return this.zzaEv;
    }
  }
  
  private static final class LeaderboardScoresLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Leaderboards.LoadScoresResult> zzamC;
    
    LeaderboardScoresLoadedBinderCallback(zza.zzb<Leaderboards.LoadScoresResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zza(DataHolder paramDataHolder1, DataHolder paramDataHolder2)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadScoresResultImpl(paramDataHolder1, paramDataHolder2));
    }
  }
  
  private static final class LeaderboardsLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Leaderboards.LeaderboardMetadataResult> zzamC;
    
    LeaderboardsLoadedBinderCallback(zza.zzb<Leaderboards.LeaderboardMetadataResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzj(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LeaderboardMetadataResultImpl(paramDataHolder));
    }
  }
  
  private static final class LeaveMatchResultImpl
    extends GamesClientImpl.TurnBasedMatchResult
    implements TurnBasedMultiplayer.LeaveMatchResult
  {
    LeaveMatchResultImpl(DataHolder paramDataHolder)
    {
      super();
    }
  }
  
  private static final class LeftRoomNotifier
    implements zzq.zzb<RoomUpdateListener>
  {
    private final String zzaEw;
    private final int zzade;
    
    LeftRoomNotifier(int paramInt, String paramString)
    {
      this.zzade = paramInt;
      this.zzaEw = paramString;
    }
    
    public void zza(RoomUpdateListener paramRoomUpdateListener)
    {
      paramRoomUpdateListener.onLeftRoom(this.zzade, this.zzaEw);
    }
    
    public void zzpr() {}
  }
  
  private static final class ListVideosBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Videos.ListVideosResult> zzamC;
    
    ListVideosBinderCallback(zza.zzb<Videos.ListVideosResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzX(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.ListVideosResultImpl(paramDataHolder));
    }
  }
  
  public static final class ListVideosResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Videos.ListVideosResult
  {
    private final VideoBuffer zzaEx;
    
    public ListVideosResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzaEx = new VideoBuffer(paramDataHolder);
    }
  }
  
  private static final class LoadAchievementsResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Achievements.LoadAchievementsResult
  {
    private final AchievementBuffer zzaEy;
    
    LoadAchievementsResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzaEy = new AchievementBuffer(paramDataHolder);
    }
    
    public AchievementBuffer getAchievements()
    {
      return this.zzaEy;
    }
  }
  
  private static final class LoadAclResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Acls.LoadAclResult
  {
    LoadAclResultImpl(DataHolder paramDataHolder)
    {
      super();
    }
  }
  
  private static final class LoadAppContentsResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements AppContents.LoadAppContentResult
  {
    private final ArrayList<DataHolder> zzaEz;
    
    LoadAppContentsResultImpl(DataHolder[] paramArrayOfDataHolder)
    {
      super();
      this.zzaEz = new ArrayList(Arrays.asList(paramArrayOfDataHolder));
    }
  }
  
  private static final class LoadEventResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Events.LoadEventsResult
  {
    private final EventBuffer zzaEA;
    
    LoadEventResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzaEA = new EventBuffer(paramDataHolder);
    }
    
    public EventBuffer getEvents()
    {
      return this.zzaEA;
    }
  }
  
  private static final class LoadExperimentsResultImpl
    implements Games.LoadExperimentsResult
  {
    private final Status zzUX;
    private final Set<Long> zzaEB;
    
    LoadExperimentsResultImpl(int paramInt, long[] paramArrayOfLong)
    {
      this.zzUX = new Status(paramInt);
      this.zzaEB = new HashSet();
      paramInt = 0;
      while (paramInt < paramArrayOfLong.length)
      {
        this.zzaEB.add(Long.valueOf(paramArrayOfLong[paramInt]));
        paramInt += 1;
      }
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class LoadGameInstancesResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements GamesMetadata.LoadGameInstancesResult
  {
    private final GameInstanceBuffer zzaEC;
    
    LoadGameInstancesResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzaEC = new GameInstanceBuffer(paramDataHolder);
    }
  }
  
  private static final class LoadGameSearchSuggestionsResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements GamesMetadata.LoadGameSearchSuggestionsResult
  {
    private final GameSearchSuggestionBuffer zzaED;
    
    LoadGameSearchSuggestionsResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzaED = new GameSearchSuggestionBuffer(paramDataHolder);
    }
  }
  
  private static final class LoadGamesResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements GamesMetadata.LoadGamesResult
  {
    private final GameBuffer zzaEE;
    
    LoadGamesResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzaEE = new GameBuffer(paramDataHolder);
    }
    
    public GameBuffer getGames()
    {
      return this.zzaEE;
    }
  }
  
  private static final class LoadInvitationsResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Invitations.LoadInvitationsResult
  {
    private final InvitationBuffer zzaEF;
    
    LoadInvitationsResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzaEF = new InvitationBuffer(paramDataHolder);
    }
    
    public InvitationBuffer getInvitations()
    {
      return this.zzaEF;
    }
  }
  
  private static final class LoadMatchResultImpl
    extends GamesClientImpl.TurnBasedMatchResult
    implements TurnBasedMultiplayer.LoadMatchResult
  {
    LoadMatchResultImpl(DataHolder paramDataHolder)
    {
      super();
    }
  }
  
  private static final class LoadMatchesResultImpl
    implements TurnBasedMultiplayer.LoadMatchesResult
  {
    private final Status zzUX;
    private final LoadMatchesResponse zzaEG;
    
    LoadMatchesResultImpl(Status paramStatus, Bundle paramBundle)
    {
      this.zzUX = paramStatus;
      this.zzaEG = new LoadMatchesResponse(paramBundle);
    }
    
    public LoadMatchesResponse getMatches()
    {
      return this.zzaEG;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
    
    public void release()
    {
      this.zzaEG.release();
    }
  }
  
  private static final class LoadPlayerScoreResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Leaderboards.LoadPlayerScoreResult
  {
    private final LeaderboardScoreEntity zzaEH;
    
    /* Error */
    LoadPlayerScoreResultImpl(DataHolder paramDataHolder)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: invokespecial 15	com/google/android/gms/games/internal/GamesClientImpl$GamesDataHolderResult:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   5: new 17	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer
      //   8: dup
      //   9: aload_1
      //   10: invokespecial 18	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   13: astore_1
      //   14: aload_1
      //   15: invokevirtual 22	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:getCount	()I
      //   18: ifle +25 -> 43
      //   21: aload_0
      //   22: aload_1
      //   23: iconst_0
      //   24: invokevirtual 26	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:get	(I)Lcom/google/android/gms/games/leaderboard/LeaderboardScore;
      //   27: invokeinterface 32 1 0
      //   32: checkcast 34	com/google/android/gms/games/leaderboard/LeaderboardScoreEntity
      //   35: putfield 36	com/google/android/gms/games/internal/GamesClientImpl$LoadPlayerScoreResultImpl:zzaEH	Lcom/google/android/gms/games/leaderboard/LeaderboardScoreEntity;
      //   38: aload_1
      //   39: invokevirtual 40	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:release	()V
      //   42: return
      //   43: aload_0
      //   44: aconst_null
      //   45: putfield 36	com/google/android/gms/games/internal/GamesClientImpl$LoadPlayerScoreResultImpl:zzaEH	Lcom/google/android/gms/games/leaderboard/LeaderboardScoreEntity;
      //   48: goto -10 -> 38
      //   51: astore_2
      //   52: aload_1
      //   53: invokevirtual 40	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:release	()V
      //   56: aload_2
      //   57: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	58	0	this	LoadPlayerScoreResultImpl
      //   0	58	1	paramDataHolder	DataHolder
      //   51	6	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   14	38	51	finally
      //   43	48	51	finally
    }
    
    public LeaderboardScore getScore()
    {
      return this.zzaEH;
    }
  }
  
  private static final class LoadPlayerStatsResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Stats.LoadPlayerStatsResult
  {
    private final PlayerStats zzaEI;
    
    /* Error */
    LoadPlayerStatsResultImpl(DataHolder paramDataHolder)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: invokespecial 15	com/google/android/gms/games/internal/GamesClientImpl$GamesDataHolderResult:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   5: new 17	com/google/android/gms/games/stats/PlayerStatsBuffer
      //   8: dup
      //   9: aload_1
      //   10: invokespecial 18	com/google/android/gms/games/stats/PlayerStatsBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   13: astore_1
      //   14: aload_1
      //   15: invokevirtual 22	com/google/android/gms/games/stats/PlayerStatsBuffer:getCount	()I
      //   18: ifle +24 -> 42
      //   21: aload_0
      //   22: new 24	com/google/android/gms/games/stats/PlayerStatsEntity
      //   25: dup
      //   26: aload_1
      //   27: iconst_0
      //   28: invokevirtual 28	com/google/android/gms/games/stats/PlayerStatsBuffer:zzgV	(I)Lcom/google/android/gms/games/stats/PlayerStats;
      //   31: invokespecial 31	com/google/android/gms/games/stats/PlayerStatsEntity:<init>	(Lcom/google/android/gms/games/stats/PlayerStats;)V
      //   34: putfield 33	com/google/android/gms/games/internal/GamesClientImpl$LoadPlayerStatsResultImpl:zzaEI	Lcom/google/android/gms/games/stats/PlayerStats;
      //   37: aload_1
      //   38: invokevirtual 37	com/google/android/gms/games/stats/PlayerStatsBuffer:release	()V
      //   41: return
      //   42: aload_0
      //   43: aconst_null
      //   44: putfield 33	com/google/android/gms/games/internal/GamesClientImpl$LoadPlayerStatsResultImpl:zzaEI	Lcom/google/android/gms/games/stats/PlayerStats;
      //   47: goto -10 -> 37
      //   50: astore_2
      //   51: aload_1
      //   52: invokevirtual 37	com/google/android/gms/games/stats/PlayerStatsBuffer:release	()V
      //   55: aload_2
      //   56: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	57	0	this	LoadPlayerStatsResultImpl
      //   0	57	1	paramDataHolder	DataHolder
      //   50	6	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   14	37	50	finally
      //   42	47	50	finally
    }
    
    public PlayerStats getPlayerStats()
    {
      return this.zzaEI;
    }
  }
  
  private static final class LoadPlayersResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Players.LoadPlayersResult
  {
    private final PlayerBuffer zzaEJ;
    
    LoadPlayersResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzaEJ = new PlayerBuffer(paramDataHolder);
    }
    
    public PlayerBuffer getPlayers()
    {
      return this.zzaEJ;
    }
  }
  
  private static final class LoadProfileSettingsResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Players.LoadProfileSettingsResult
  {
    private final boolean zzaEK;
    private final boolean zzsj;
    
    /* Error */
    LoadProfileSettingsResultImpl(DataHolder paramDataHolder)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: invokespecial 16	com/google/android/gms/games/internal/GamesClientImpl$GamesDataHolderResult:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   5: aload_1
      //   6: invokevirtual 22	com/google/android/gms/common/data/DataHolder:getCount	()I
      //   9: ifle +38 -> 47
      //   12: aload_1
      //   13: iconst_0
      //   14: invokevirtual 26	com/google/android/gms/common/data/DataHolder:zzbH	(I)I
      //   17: istore_2
      //   18: aload_0
      //   19: aload_1
      //   20: ldc 28
      //   22: iconst_0
      //   23: iload_2
      //   24: invokevirtual 32	com/google/android/gms/common/data/DataHolder:zze	(Ljava/lang/String;II)Z
      //   27: putfield 34	com/google/android/gms/games/internal/GamesClientImpl$LoadProfileSettingsResultImpl:zzsj	Z
      //   30: aload_0
      //   31: aload_1
      //   32: ldc 36
      //   34: iconst_0
      //   35: iload_2
      //   36: invokevirtual 32	com/google/android/gms/common/data/DataHolder:zze	(Ljava/lang/String;II)Z
      //   39: putfield 38	com/google/android/gms/games/internal/GamesClientImpl$LoadProfileSettingsResultImpl:zzaEK	Z
      //   42: aload_1
      //   43: invokevirtual 42	com/google/android/gms/common/data/DataHolder:close	()V
      //   46: return
      //   47: aload_0
      //   48: iconst_1
      //   49: putfield 34	com/google/android/gms/games/internal/GamesClientImpl$LoadProfileSettingsResultImpl:zzsj	Z
      //   52: aload_0
      //   53: iconst_0
      //   54: putfield 38	com/google/android/gms/games/internal/GamesClientImpl$LoadProfileSettingsResultImpl:zzaEK	Z
      //   57: goto -15 -> 42
      //   60: astore_3
      //   61: aload_1
      //   62: invokevirtual 42	com/google/android/gms/common/data/DataHolder:close	()V
      //   65: aload_3
      //   66: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	67	0	this	LoadProfileSettingsResultImpl
      //   0	67	1	paramDataHolder	DataHolder
      //   17	19	2	i	int
      //   60	6	3	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   5	42	60	finally
      //   47	57	60	finally
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class LoadQuestsResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Quests.LoadQuestsResult
  {
    private final DataHolder zzahi;
    
    LoadQuestsResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzahi = paramDataHolder;
    }
    
    public QuestBuffer getQuests()
    {
      return new QuestBuffer(this.zzahi);
    }
  }
  
  private static final class LoadRequestSummariesResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Requests.LoadRequestSummariesResult
  {
    LoadRequestSummariesResultImpl(DataHolder paramDataHolder)
    {
      super();
    }
  }
  
  private static final class LoadRequestsResultImpl
    implements Requests.LoadRequestsResult
  {
    private final Status zzUX;
    private final Bundle zzaEL;
    
    LoadRequestsResultImpl(Status paramStatus, Bundle paramBundle)
    {
      this.zzUX = paramStatus;
      this.zzaEL = paramBundle;
    }
    
    public GameRequestBuffer getRequests(int paramInt)
    {
      String str = RequestType.zzgw(paramInt);
      if (!this.zzaEL.containsKey(str)) {
        return null;
      }
      return new GameRequestBuffer((DataHolder)this.zzaEL.get(str));
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
    
    public void release()
    {
      Iterator localIterator = this.zzaEL.keySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (String)localIterator.next();
        localObject = (DataHolder)this.zzaEL.getParcelable((String)localObject);
        if (localObject != null) {
          ((DataHolder)localObject).close();
        }
      }
    }
  }
  
  private static final class LoadScoresResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Leaderboards.LoadScoresResult
  {
    private final LeaderboardEntity zzaEM;
    private final LeaderboardScoreBuffer zzaEN;
    
    /* Error */
    LoadScoresResultImpl(DataHolder paramDataHolder1, DataHolder paramDataHolder2)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_2
      //   2: invokespecial 18	com/google/android/gms/games/internal/GamesClientImpl$GamesDataHolderResult:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   5: new 20	com/google/android/gms/games/leaderboard/LeaderboardBuffer
      //   8: dup
      //   9: aload_1
      //   10: invokespecial 21	com/google/android/gms/games/leaderboard/LeaderboardBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   13: astore_1
      //   14: aload_1
      //   15: invokevirtual 25	com/google/android/gms/games/leaderboard/LeaderboardBuffer:getCount	()I
      //   18: ifle +40 -> 58
      //   21: aload_0
      //   22: aload_1
      //   23: iconst_0
      //   24: invokevirtual 29	com/google/android/gms/games/leaderboard/LeaderboardBuffer:get	(I)Ljava/lang/Object;
      //   27: checkcast 31	com/google/android/gms/games/leaderboard/Leaderboard
      //   30: invokeinterface 35 1 0
      //   35: checkcast 37	com/google/android/gms/games/leaderboard/LeaderboardEntity
      //   38: putfield 39	com/google/android/gms/games/internal/GamesClientImpl$LoadScoresResultImpl:zzaEM	Lcom/google/android/gms/games/leaderboard/LeaderboardEntity;
      //   41: aload_1
      //   42: invokevirtual 43	com/google/android/gms/games/leaderboard/LeaderboardBuffer:release	()V
      //   45: aload_0
      //   46: new 45	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer
      //   49: dup
      //   50: aload_2
      //   51: invokespecial 46	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   54: putfield 48	com/google/android/gms/games/internal/GamesClientImpl$LoadScoresResultImpl:zzaEN	Lcom/google/android/gms/games/leaderboard/LeaderboardScoreBuffer;
      //   57: return
      //   58: aload_0
      //   59: aconst_null
      //   60: putfield 39	com/google/android/gms/games/internal/GamesClientImpl$LoadScoresResultImpl:zzaEM	Lcom/google/android/gms/games/leaderboard/LeaderboardEntity;
      //   63: goto -22 -> 41
      //   66: astore_2
      //   67: aload_1
      //   68: invokevirtual 43	com/google/android/gms/games/leaderboard/LeaderboardBuffer:release	()V
      //   71: aload_2
      //   72: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	73	0	this	LoadScoresResultImpl
      //   0	73	1	paramDataHolder1	DataHolder
      //   0	73	2	paramDataHolder2	DataHolder
      // Exception table:
      //   from	to	target	type
      //   14	41	66	finally
      //   58	63	66	finally
    }
    
    public Leaderboard getLeaderboard()
    {
      return this.zzaEM;
    }
    
    public LeaderboardScoreBuffer getScores()
    {
      return this.zzaEN;
    }
  }
  
  private static final class LoadSnapshotsResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Snapshots.LoadSnapshotsResult
  {
    LoadSnapshotsResultImpl(DataHolder paramDataHolder)
    {
      super();
    }
    
    public SnapshotMetadataBuffer getSnapshots()
    {
      return new SnapshotMetadataBuffer(this.zzahi);
    }
  }
  
  private static final class LoadXpForGameCategoriesResultImpl
    implements Players.LoadXpForGameCategoriesResult
  {
    private final Status zzUX;
    private final List<String> zzaEO;
    private final Bundle zzaEP;
    
    LoadXpForGameCategoriesResultImpl(Status paramStatus, Bundle paramBundle)
    {
      this.zzUX = paramStatus;
      this.zzaEO = paramBundle.getStringArrayList("game_category_list");
      this.zzaEP = paramBundle;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class LoadXpStreamResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Players.LoadXpStreamResult
  {
    private final ExperienceEventBuffer zzaEQ;
    
    LoadXpStreamResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzaEQ = new ExperienceEventBuffer(paramDataHolder);
    }
  }
  
  private static final class MatchRemovedNotifier
    implements zzq.zzb<OnTurnBasedMatchUpdateReceivedListener>
  {
    private final String zzaER;
    
    MatchRemovedNotifier(String paramString)
    {
      this.zzaER = paramString;
    }
    
    public void zza(OnTurnBasedMatchUpdateReceivedListener paramOnTurnBasedMatchUpdateReceivedListener)
    {
      paramOnTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchRemoved(this.zzaER);
    }
    
    public void zzpr() {}
  }
  
  private static final class MatchUpdateReceivedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zzq<OnTurnBasedMatchUpdateReceivedListener> zzari;
    
    MatchUpdateReceivedBinderCallback(zzq<OnTurnBasedMatchUpdateReceivedListener> paramzzq)
    {
      this.zzari = paramzzq;
    }
    
    public void onTurnBasedMatchRemoved(String paramString)
    {
      this.zzari.zza(new GamesClientImpl.MatchRemovedNotifier(paramString));
    }
    
    public void zzy(DataHolder paramDataHolder)
    {
      TurnBasedMatchBuffer localTurnBasedMatchBuffer = new TurnBasedMatchBuffer(paramDataHolder);
      paramDataHolder = null;
      try
      {
        if (localTurnBasedMatchBuffer.getCount() > 0) {
          paramDataHolder = (TurnBasedMatch)((TurnBasedMatch)localTurnBasedMatchBuffer.get(0)).freeze();
        }
        localTurnBasedMatchBuffer.release();
        if (paramDataHolder != null) {
          this.zzari.zza(new GamesClientImpl.MatchUpdateReceivedNotifier(paramDataHolder));
        }
        return;
      }
      finally
      {
        localTurnBasedMatchBuffer.release();
      }
    }
  }
  
  private static final class MatchUpdateReceivedNotifier
    implements zzq.zzb<OnTurnBasedMatchUpdateReceivedListener>
  {
    private final TurnBasedMatch zzaES;
    
    MatchUpdateReceivedNotifier(TurnBasedMatch paramTurnBasedMatch)
    {
      this.zzaES = paramTurnBasedMatch;
    }
    
    public void zza(OnTurnBasedMatchUpdateReceivedListener paramOnTurnBasedMatchUpdateReceivedListener)
    {
      paramOnTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchReceived(this.zzaES);
    }
    
    public void zzpr() {}
  }
  
  private static final class MessageReceivedNotifier
    implements zzq.zzb<RealTimeMessageReceivedListener>
  {
    private final RealTimeMessage zzaET;
    
    MessageReceivedNotifier(RealTimeMessage paramRealTimeMessage)
    {
      this.zzaET = paramRealTimeMessage;
    }
    
    public void zza(RealTimeMessageReceivedListener paramRealTimeMessageReceivedListener)
    {
      paramRealTimeMessageReceivedListener.onRealTimeMessageReceived(this.zzaET);
    }
    
    public void zzpr() {}
  }
  
  private static final class NearbyPlayerDetectedNotifier
    implements zzq.zzb<OnNearbyPlayerDetectedListener>
  {
    private final Player zzaEU;
    
    public void zza(OnNearbyPlayerDetectedListener paramOnNearbyPlayerDetectedListener)
    {
      paramOnNearbyPlayerDetectedListener.zza(this.zzaEU);
    }
    
    public void zzpr() {}
  }
  
  private static final class NotifyAclLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Acls.LoadAclResult> zzamC;
    
    NotifyAclLoadedBinderCallback(zza.zzb<Acls.LoadAclResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzH(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadAclResultImpl(paramDataHolder));
    }
  }
  
  private static final class NotifyAclUpdatedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Status> zzamC;
    
    NotifyAclUpdatedBinderCallback(zza.zzb<Status> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzgn(int paramInt)
    {
      this.zzamC.zzs(GamesStatusCodes.zzgc(paramInt));
    }
  }
  
  private static final class OpenSnapshotResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Snapshots.OpenSnapshotResult
  {
    private final Snapshot zzaEV;
    private final String zzaEW;
    private final Snapshot zzaEX;
    private final Contents zzaEY;
    private final SnapshotContents zzaEZ;
    
    OpenSnapshotResultImpl(DataHolder paramDataHolder, Contents paramContents)
    {
      this(paramDataHolder, null, paramContents, null, null);
    }
    
    OpenSnapshotResultImpl(DataHolder paramDataHolder, String paramString, Contents paramContents1, Contents paramContents2, Contents paramContents3)
    {
      super();
      SnapshotMetadataBuffer localSnapshotMetadataBuffer = new SnapshotMetadataBuffer(paramDataHolder);
      for (;;)
      {
        try
        {
          if (localSnapshotMetadataBuffer.getCount() == 0)
          {
            this.zzaEV = null;
            this.zzaEX = null;
            localSnapshotMetadataBuffer.release();
            this.zzaEW = paramString;
            this.zzaEY = paramContents3;
            this.zzaEZ = new SnapshotContentsEntity(paramContents3);
            return;
          }
          if (localSnapshotMetadataBuffer.getCount() != 1) {
            break label144;
          }
          if (paramDataHolder.getStatusCode() != 4004)
          {
            zzb.zzab(bool);
            this.zzaEV = new SnapshotEntity(new SnapshotMetadataEntity(localSnapshotMetadataBuffer.get(0)), new SnapshotContentsEntity(paramContents1));
            this.zzaEX = null;
            continue;
          }
          bool = false;
        }
        finally
        {
          localSnapshotMetadataBuffer.release();
        }
        continue;
        label144:
        this.zzaEV = new SnapshotEntity(new SnapshotMetadataEntity(localSnapshotMetadataBuffer.get(0)), new SnapshotContentsEntity(paramContents1));
        this.zzaEX = new SnapshotEntity(new SnapshotMetadataEntity(localSnapshotMetadataBuffer.get(1)), new SnapshotContentsEntity(paramContents2));
      }
    }
    
    public String getConflictId()
    {
      return this.zzaEW;
    }
    
    public Snapshot getConflictingSnapshot()
    {
      return this.zzaEX;
    }
    
    public SnapshotContents getResolutionSnapshotContents()
    {
      return this.zzaEZ;
    }
    
    public Snapshot getSnapshot()
    {
      return this.zzaEV;
    }
  }
  
  private static final class P2PConnectedNotifier
    implements zzq.zzb<RoomStatusUpdateListener>
  {
    private final String zzaFa;
    
    P2PConnectedNotifier(String paramString)
    {
      this.zzaFa = paramString;
    }
    
    public void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener)
    {
      paramRoomStatusUpdateListener.onP2PConnected(this.zzaFa);
    }
    
    public void zzpr() {}
  }
  
  private static final class P2PDisconnectedNotifier
    implements zzq.zzb<RoomStatusUpdateListener>
  {
    private final String zzaFa;
    
    P2PDisconnectedNotifier(String paramString)
    {
      this.zzaFa = paramString;
    }
    
    public void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener)
    {
      paramRoomStatusUpdateListener.onP2PDisconnected(this.zzaFa);
    }
    
    public void zzpr() {}
  }
  
  private static final class PeerConnectedNotifier
    extends GamesClientImpl.AbstractPeerStatusNotifier
  {
    PeerConnectedNotifier(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramArrayOfString);
    }
    
    protected void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeersConnected(paramRoom, paramArrayList);
    }
  }
  
  private static final class PeerDeclinedNotifier
    extends GamesClientImpl.AbstractPeerStatusNotifier
  {
    PeerDeclinedNotifier(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramArrayOfString);
    }
    
    protected void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeerDeclined(paramRoom, paramArrayList);
    }
  }
  
  private static final class PeerDisconnectedNotifier
    extends GamesClientImpl.AbstractPeerStatusNotifier
  {
    PeerDisconnectedNotifier(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramArrayOfString);
    }
    
    protected void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeersDisconnected(paramRoom, paramArrayList);
    }
  }
  
  private static final class PeerInvitedToRoomNotifier
    extends GamesClientImpl.AbstractPeerStatusNotifier
  {
    PeerInvitedToRoomNotifier(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramArrayOfString);
    }
    
    protected void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeerInvitedToRoom(paramRoom, paramArrayList);
    }
  }
  
  private static final class PeerJoinedRoomNotifier
    extends GamesClientImpl.AbstractPeerStatusNotifier
  {
    PeerJoinedRoomNotifier(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramArrayOfString);
    }
    
    protected void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeerJoined(paramRoom, paramArrayList);
    }
  }
  
  private static final class PeerLeftRoomNotifier
    extends GamesClientImpl.AbstractPeerStatusNotifier
  {
    PeerLeftRoomNotifier(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramArrayOfString);
    }
    
    protected void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeerLeft(paramRoom, paramArrayList);
    }
  }
  
  private static final class PlayerLeaderboardScoreLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Leaderboards.LoadPlayerScoreResult> zzamC;
    
    PlayerLeaderboardScoreLoadedBinderCallback(zza.zzb<Leaderboards.LoadPlayerScoreResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzJ(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadPlayerScoreResultImpl(paramDataHolder));
    }
  }
  
  private static final class PlayerStatsLoadedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Stats.LoadPlayerStatsResult> zzamC;
    
    public PlayerStatsLoadedBinderCallbacks(zza.zzb<Stats.LoadPlayerStatsResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzW(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadPlayerStatsResultImpl(paramDataHolder));
    }
  }
  
  private static final class PlayerXpForGameCategoriesLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Players.LoadXpForGameCategoriesResult> zzamC;
    
    PlayerXpForGameCategoriesLoadedBinderCallback(zza.zzb<Players.LoadXpForGameCategoriesResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzf(int paramInt, Bundle paramBundle)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      Status localStatus = GamesStatusCodes.zzgc(paramInt);
      this.zzamC.zzs(new GamesClientImpl.LoadXpForGameCategoriesResultImpl(localStatus, paramBundle));
    }
  }
  
  static final class PlayerXpStreamLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Players.LoadXpStreamResult> zzamC;
    
    PlayerXpStreamLoadedBinderCallback(zza.zzb<Players.LoadXpStreamResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzU(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadXpStreamResultImpl(paramDataHolder));
    }
  }
  
  private static final class PlayersLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Players.LoadPlayersResult> zzamC;
    
    PlayersLoadedBinderCallback(zza.zzb<Players.LoadPlayersResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzl(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadPlayersResultImpl(paramDataHolder));
    }
    
    public void zzm(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadPlayersResultImpl(paramDataHolder));
    }
  }
  
  private static final class PopupLocationInfoBinderCallbacks
    extends AbstractGamesClient
  {
    private final PopupManager zzaEd;
    
    public PopupLocationInfoBinderCallbacks(PopupManager paramPopupManager)
    {
      this.zzaEd = paramPopupManager;
    }
    
    public PopupLocationInfoParcelable zzws()
    {
      return new PopupLocationInfoParcelable(this.zzaEd.zzxk());
    }
  }
  
  static final class ProfileSettingsLoadedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Players.LoadProfileSettingsResult> zzamC;
    
    ProfileSettingsLoadedBinderCallback(zza.zzb<Players.LoadProfileSettingsResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzV(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.LoadProfileSettingsResultImpl(paramDataHolder));
    }
  }
  
  private static final class ProfileSettingsUpdatedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Status> zzamC;
    
    ProfileSettingsUpdatedBinderCallback(zza.zzb<Status> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzgp(int paramInt)
    {
      this.zzamC.zzs(GamesStatusCodes.zzgc(paramInt));
    }
  }
  
  private static final class QuestAcceptedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Quests.AcceptQuestResult> zzaFb;
    
    public QuestAcceptedBinderCallbacks(zza.zzb<Quests.AcceptQuestResult> paramzzb)
    {
      this.zzaFb = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzQ(DataHolder paramDataHolder)
    {
      this.zzaFb.zzs(new GamesClientImpl.AcceptQuestResultImpl(paramDataHolder));
    }
  }
  
  private static final class QuestCompletedNotifier
    implements zzq.zzb<QuestUpdateListener>
  {
    private final Quest zzaEk;
    
    QuestCompletedNotifier(Quest paramQuest)
    {
      this.zzaEk = paramQuest;
    }
    
    public void zza(QuestUpdateListener paramQuestUpdateListener)
    {
      paramQuestUpdateListener.onQuestCompleted(this.zzaEk);
    }
    
    public void zzpr() {}
  }
  
  private static final class QuestMilestoneClaimBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Quests.ClaimMilestoneResult> zzaFc;
    private final String zzaFd;
    
    public QuestMilestoneClaimBinderCallbacks(zza.zzb<Quests.ClaimMilestoneResult> paramzzb, String paramString)
    {
      this.zzaFc = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
      this.zzaFd = ((String)zzx.zzb(paramString, "MilestoneId must not be null"));
    }
    
    public void zzP(DataHolder paramDataHolder)
    {
      this.zzaFc.zzs(new GamesClientImpl.ClaimMilestoneResultImpl(paramDataHolder, this.zzaFd));
    }
  }
  
  private static final class QuestUpdateBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zzq<QuestUpdateListener> zzari;
    
    QuestUpdateBinderCallback(zzq<QuestUpdateListener> paramzzq)
    {
      this.zzari = paramzzq;
    }
    
    private Quest zzaa(DataHolder paramDataHolder)
    {
      QuestBuffer localQuestBuffer = new QuestBuffer(paramDataHolder);
      paramDataHolder = null;
      try
      {
        if (localQuestBuffer.getCount() > 0) {
          paramDataHolder = (Quest)((Quest)localQuestBuffer.get(0)).freeze();
        }
        return paramDataHolder;
      }
      finally
      {
        localQuestBuffer.release();
      }
    }
    
    public void zzR(DataHolder paramDataHolder)
    {
      paramDataHolder = zzaa(paramDataHolder);
      if (paramDataHolder != null) {
        this.zzari.zza(new GamesClientImpl.QuestCompletedNotifier(paramDataHolder));
      }
    }
  }
  
  private static final class QuestsLoadedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Quests.LoadQuestsResult> zzaFe;
    
    public QuestsLoadedBinderCallbacks(zza.zzb<Quests.LoadQuestsResult> paramzzb)
    {
      this.zzaFe = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzT(DataHolder paramDataHolder)
    {
      this.zzaFe.zzs(new GamesClientImpl.LoadQuestsResultImpl(paramDataHolder));
    }
  }
  
  private static final class RealTimeMessageSentNotifier
    implements zzq.zzb<RealTimeMultiplayer.ReliableMessageSentCallback>
  {
    private final String zzaFf;
    private final int zzaFg;
    private final int zzade;
    
    RealTimeMessageSentNotifier(int paramInt1, int paramInt2, String paramString)
    {
      this.zzade = paramInt1;
      this.zzaFg = paramInt2;
      this.zzaFf = paramString;
    }
    
    public void zza(RealTimeMultiplayer.ReliableMessageSentCallback paramReliableMessageSentCallback)
    {
      if (paramReliableMessageSentCallback != null) {
        paramReliableMessageSentCallback.onRealTimeMessageSent(this.zzade, this.zzaFg, this.zzaFf);
      }
    }
    
    public void zzpr() {}
  }
  
  private static final class RealTimeReliableMessageBinderCallbacks
    extends AbstractGamesCallbacks
  {
    final zzq<RealTimeMultiplayer.ReliableMessageSentCallback> zzaFh;
    
    public RealTimeReliableMessageBinderCallbacks(zzq<RealTimeMultiplayer.ReliableMessageSentCallback> paramzzq)
    {
      this.zzaFh = paramzzq;
    }
    
    public void zzb(int paramInt1, int paramInt2, String paramString)
    {
      if (this.zzaFh != null) {
        this.zzaFh.zza(new GamesClientImpl.RealTimeMessageSentNotifier(paramInt1, paramInt2, paramString));
      }
    }
  }
  
  private static final class RequestReceivedBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zzq<OnRequestReceivedListener> zzari;
    
    RequestReceivedBinderCallback(zzq<OnRequestReceivedListener> paramzzq)
    {
      this.zzari = paramzzq;
    }
    
    public void onRequestRemoved(String paramString)
    {
      this.zzari.zza(new GamesClientImpl.RequestRemovedNotifier(paramString));
    }
    
    public void zzt(DataHolder paramDataHolder)
    {
      GameRequestBuffer localGameRequestBuffer = new GameRequestBuffer(paramDataHolder);
      paramDataHolder = null;
      try
      {
        if (localGameRequestBuffer.getCount() > 0) {
          paramDataHolder = (GameRequest)((GameRequest)localGameRequestBuffer.get(0)).freeze();
        }
        localGameRequestBuffer.release();
        if (paramDataHolder != null) {
          this.zzari.zza(new GamesClientImpl.RequestReceivedNotifier(paramDataHolder));
        }
        return;
      }
      finally
      {
        localGameRequestBuffer.release();
      }
    }
  }
  
  private static final class RequestReceivedNotifier
    implements zzq.zzb<OnRequestReceivedListener>
  {
    private final GameRequest zzaFi;
    
    RequestReceivedNotifier(GameRequest paramGameRequest)
    {
      this.zzaFi = paramGameRequest;
    }
    
    public void zza(OnRequestReceivedListener paramOnRequestReceivedListener)
    {
      paramOnRequestReceivedListener.onRequestReceived(this.zzaFi);
    }
    
    public void zzpr() {}
  }
  
  private static final class RequestRemovedNotifier
    implements zzq.zzb<OnRequestReceivedListener>
  {
    private final String zzEY;
    
    RequestRemovedNotifier(String paramString)
    {
      this.zzEY = paramString;
    }
    
    public void zza(OnRequestReceivedListener paramOnRequestReceivedListener)
    {
      paramOnRequestReceivedListener.onRequestRemoved(this.zzEY);
    }
    
    public void zzpr() {}
  }
  
  private static final class RequestSentBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Requests.SendRequestResult> zzaFj;
    
    public RequestSentBinderCallbacks(zza.zzb<Requests.SendRequestResult> paramzzb)
    {
      this.zzaFj = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzL(DataHolder paramDataHolder)
    {
      this.zzaFj.zzs(new GamesClientImpl.SendRequestResultImpl(paramDataHolder));
    }
  }
  
  private static final class RequestSummariesLoadedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Requests.LoadRequestSummariesResult> zzaFk;
    
    public RequestSummariesLoadedBinderCallbacks(zza.zzb<Requests.LoadRequestSummariesResult> paramzzb)
    {
      this.zzaFk = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzM(DataHolder paramDataHolder)
    {
      this.zzaFk.zzs(new GamesClientImpl.LoadRequestSummariesResultImpl(paramDataHolder));
    }
  }
  
  private static final class RequestsLoadedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Requests.LoadRequestsResult> zzaFl;
    
    public RequestsLoadedBinderCallbacks(zza.zzb<Requests.LoadRequestsResult> paramzzb)
    {
      this.zzaFl = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzd(int paramInt, Bundle paramBundle)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      Status localStatus = GamesStatusCodes.zzgc(paramInt);
      this.zzaFl.zzs(new GamesClientImpl.LoadRequestsResultImpl(localStatus, paramBundle));
    }
  }
  
  private static final class RequestsUpdatedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Requests.UpdateRequestsResult> zzaFm;
    
    public RequestsUpdatedBinderCallbacks(zza.zzb<Requests.UpdateRequestsResult> paramzzb)
    {
      this.zzaFm = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzK(DataHolder paramDataHolder)
    {
      this.zzaFm.zzs(new GamesClientImpl.UpdateRequestsResultImpl(paramDataHolder));
    }
  }
  
  private static final class RoomAutoMatchingNotifier
    extends GamesClientImpl.AbstractRoomStatusNotifier
  {
    RoomAutoMatchingNotifier(DataHolder paramDataHolder)
    {
      super();
    }
    
    public void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom)
    {
      paramRoomStatusUpdateListener.onRoomAutoMatching(paramRoom);
    }
  }
  
  private static final class RoomBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zzq<? extends RoomUpdateListener> zzaFn;
    private final zzq<? extends RoomStatusUpdateListener> zzaFo;
    private final zzq<RealTimeMessageReceivedListener> zzaFp;
    
    public RoomBinderCallbacks(zzq<RoomUpdateListener> paramzzq)
    {
      this.zzaFn = ((zzq)zzx.zzb(paramzzq, "Callbacks must not be null"));
      this.zzaFo = null;
      this.zzaFp = null;
    }
    
    public RoomBinderCallbacks(zzq<? extends RoomUpdateListener> paramzzq, zzq<? extends RoomStatusUpdateListener> paramzzq1, zzq<RealTimeMessageReceivedListener> paramzzq2)
    {
      this.zzaFn = ((zzq)zzx.zzb(paramzzq, "Callbacks must not be null"));
      this.zzaFo = paramzzq1;
      this.zzaFp = paramzzq2;
    }
    
    public void onLeftRoom(int paramInt, String paramString)
    {
      this.zzaFn.zza(new GamesClientImpl.LeftRoomNotifier(paramInt, paramString));
    }
    
    public void onP2PConnected(String paramString)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.P2PConnectedNotifier(paramString));
      }
    }
    
    public void onP2PDisconnected(String paramString)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.P2PDisconnectedNotifier(paramString));
      }
    }
    
    public void onRealTimeMessageReceived(RealTimeMessage paramRealTimeMessage)
    {
      if (this.zzaFp != null) {
        this.zzaFp.zza(new GamesClientImpl.MessageReceivedNotifier(paramRealTimeMessage));
      }
    }
    
    public void zzA(DataHolder paramDataHolder)
    {
      this.zzaFn.zza(new GamesClientImpl.JoinedRoomNotifier(paramDataHolder));
    }
    
    public void zzB(DataHolder paramDataHolder)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.RoomConnectingNotifier(paramDataHolder));
      }
    }
    
    public void zzC(DataHolder paramDataHolder)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.RoomAutoMatchingNotifier(paramDataHolder));
      }
    }
    
    public void zzD(DataHolder paramDataHolder)
    {
      this.zzaFn.zza(new GamesClientImpl.RoomConnectedNotifier(paramDataHolder));
    }
    
    public void zzE(DataHolder paramDataHolder)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.ConnectedToRoomNotifier(paramDataHolder));
      }
    }
    
    public void zzF(DataHolder paramDataHolder)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.DisconnectedFromRoomNotifier(paramDataHolder));
      }
    }
    
    public void zza(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.PeerInvitedToRoomNotifier(paramDataHolder, paramArrayOfString));
      }
    }
    
    public void zzb(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.PeerJoinedRoomNotifier(paramDataHolder, paramArrayOfString));
      }
    }
    
    public void zzc(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.PeerLeftRoomNotifier(paramDataHolder, paramArrayOfString));
      }
    }
    
    public void zzd(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.PeerDeclinedNotifier(paramDataHolder, paramArrayOfString));
      }
    }
    
    public void zze(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.PeerConnectedNotifier(paramDataHolder, paramArrayOfString));
      }
    }
    
    public void zzf(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      if (this.zzaFo != null) {
        this.zzaFo.zza(new GamesClientImpl.PeerDisconnectedNotifier(paramDataHolder, paramArrayOfString));
      }
    }
    
    public void zzz(DataHolder paramDataHolder)
    {
      this.zzaFn.zza(new GamesClientImpl.RoomCreatedNotifier(paramDataHolder));
    }
  }
  
  private static final class RoomConnectedNotifier
    extends GamesClientImpl.AbstractRoomNotifier
  {
    RoomConnectedNotifier(DataHolder paramDataHolder)
    {
      super();
    }
    
    public void zza(RoomUpdateListener paramRoomUpdateListener, Room paramRoom, int paramInt)
    {
      paramRoomUpdateListener.onRoomConnected(paramInt, paramRoom);
    }
  }
  
  private static final class RoomConnectingNotifier
    extends GamesClientImpl.AbstractRoomStatusNotifier
  {
    RoomConnectingNotifier(DataHolder paramDataHolder)
    {
      super();
    }
    
    public void zza(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom)
    {
      paramRoomStatusUpdateListener.onRoomConnecting(paramRoom);
    }
  }
  
  private static final class RoomCreatedNotifier
    extends GamesClientImpl.AbstractRoomNotifier
  {
    public RoomCreatedNotifier(DataHolder paramDataHolder)
    {
      super();
    }
    
    public void zza(RoomUpdateListener paramRoomUpdateListener, Room paramRoom, int paramInt)
    {
      paramRoomUpdateListener.onRoomCreated(paramInt, paramRoom);
    }
  }
  
  private static final class SendRequestResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Requests.SendRequestResult
  {
    private final GameRequest zzaFi;
    
    /* Error */
    SendRequestResultImpl(DataHolder paramDataHolder)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: invokespecial 15	com/google/android/gms/games/internal/GamesClientImpl$GamesDataHolderResult:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   5: new 17	com/google/android/gms/games/request/GameRequestBuffer
      //   8: dup
      //   9: aload_1
      //   10: invokespecial 18	com/google/android/gms/games/request/GameRequestBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   13: astore_1
      //   14: aload_1
      //   15: invokevirtual 22	com/google/android/gms/games/request/GameRequestBuffer:getCount	()I
      //   18: ifle +28 -> 46
      //   21: aload_0
      //   22: aload_1
      //   23: iconst_0
      //   24: invokevirtual 26	com/google/android/gms/games/request/GameRequestBuffer:get	(I)Ljava/lang/Object;
      //   27: checkcast 28	com/google/android/gms/games/request/GameRequest
      //   30: invokeinterface 32 1 0
      //   35: checkcast 28	com/google/android/gms/games/request/GameRequest
      //   38: putfield 34	com/google/android/gms/games/internal/GamesClientImpl$SendRequestResultImpl:zzaFi	Lcom/google/android/gms/games/request/GameRequest;
      //   41: aload_1
      //   42: invokevirtual 38	com/google/android/gms/games/request/GameRequestBuffer:release	()V
      //   45: return
      //   46: aload_0
      //   47: aconst_null
      //   48: putfield 34	com/google/android/gms/games/internal/GamesClientImpl$SendRequestResultImpl:zzaFi	Lcom/google/android/gms/games/request/GameRequest;
      //   51: goto -10 -> 41
      //   54: astore_2
      //   55: aload_1
      //   56: invokevirtual 38	com/google/android/gms/games/request/GameRequestBuffer:release	()V
      //   59: aload_2
      //   60: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	61	0	this	SendRequestResultImpl
      //   0	61	1	paramDataHolder	DataHolder
      //   54	6	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   14	41	54	finally
      //   46	51	54	finally
    }
  }
  
  private static final class SignOutCompleteBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Status> zzamC;
    
    public SignOutCompleteBinderCallbacks(zza.zzb<Status> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzwr()
    {
      Status localStatus = GamesStatusCodes.zzgc(0);
      this.zzamC.zzs(localStatus);
    }
  }
  
  private static final class SnapshotCommittedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Snapshots.CommitSnapshotResult> zzaFq;
    
    public SnapshotCommittedBinderCallbacks(zza.zzb<Snapshots.CommitSnapshotResult> paramzzb)
    {
      this.zzaFq = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzO(DataHolder paramDataHolder)
    {
      this.zzaFq.zzs(new GamesClientImpl.CommitSnapshotResultImpl(paramDataHolder));
    }
  }
  
  static final class SnapshotDeletedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Snapshots.DeleteSnapshotResult> zzamC;
    
    public SnapshotDeletedBinderCallbacks(zza.zzb<Snapshots.DeleteSnapshotResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzj(int paramInt, String paramString)
    {
      this.zzamC.zzs(new GamesClientImpl.DeleteSnapshotResultImpl(paramInt, paramString));
    }
  }
  
  private static final class SnapshotOpenedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Snapshots.OpenSnapshotResult> zzaFr;
    
    public SnapshotOpenedBinderCallbacks(zza.zzb<Snapshots.OpenSnapshotResult> paramzzb)
    {
      this.zzaFr = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zza(DataHolder paramDataHolder, Contents paramContents)
    {
      this.zzaFr.zzs(new GamesClientImpl.OpenSnapshotResultImpl(paramDataHolder, paramContents));
    }
    
    public void zza(DataHolder paramDataHolder, String paramString, Contents paramContents1, Contents paramContents2, Contents paramContents3)
    {
      this.zzaFr.zzs(new GamesClientImpl.OpenSnapshotResultImpl(paramDataHolder, paramString, paramContents1, paramContents2, paramContents3));
    }
  }
  
  private static final class SnapshotsLoadedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Snapshots.LoadSnapshotsResult> zzaFs;
    
    public SnapshotsLoadedBinderCallbacks(zza.zzb<Snapshots.LoadSnapshotsResult> paramzzb)
    {
      this.zzaFs = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzN(DataHolder paramDataHolder)
    {
      this.zzaFs.zzs(new GamesClientImpl.LoadSnapshotsResultImpl(paramDataHolder));
    }
  }
  
  private static final class StartRecordingBinderCallback
    extends AbstractGamesCallbacks
  {
    private final Games.BaseGamesApiMethodImpl<Status> zzaFt;
    
    StartRecordingBinderCallback(Games.BaseGamesApiMethodImpl<Status> paramBaseGamesApiMethodImpl)
    {
      this.zzaFt = ((Games.BaseGamesApiMethodImpl)zzx.zzb(paramBaseGamesApiMethodImpl, "Holder must not be null"));
    }
    
    public void zzgq(int paramInt)
    {
      this.zzaFt.zza(new Status(paramInt));
    }
  }
  
  private static final class SubmitScoreBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Leaderboards.SubmitScoreResult> zzamC;
    
    public SubmitScoreBinderCallbacks(zza.zzb<Leaderboards.SubmitScoreResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzk(DataHolder paramDataHolder)
    {
      this.zzamC.zzs(new GamesClientImpl.SubmitScoreResultImpl(paramDataHolder));
    }
  }
  
  private static final class SubmitScoreResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Leaderboards.SubmitScoreResult
  {
    private final ScoreSubmissionData zzaFu;
    
    public SubmitScoreResultImpl(DataHolder paramDataHolder)
    {
      super();
      try
      {
        this.zzaFu = new ScoreSubmissionData(paramDataHolder);
        return;
      }
      finally
      {
        paramDataHolder.close();
      }
    }
    
    public ScoreSubmissionData getScoreData()
    {
      return this.zzaFu;
    }
  }
  
  private static final class TurnBasedMatchCanceledBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<TurnBasedMultiplayer.CancelMatchResult> zzaFv;
    
    public TurnBasedMatchCanceledBinderCallbacks(zza.zzb<TurnBasedMultiplayer.CancelMatchResult> paramzzb)
    {
      this.zzaFv = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzi(int paramInt, String paramString)
    {
      Status localStatus = GamesStatusCodes.zzgc(paramInt);
      this.zzaFv.zzs(new GamesClientImpl.CancelMatchResultImpl(localStatus, paramString));
    }
  }
  
  private static final class TurnBasedMatchInitiatedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<TurnBasedMultiplayer.InitiateMatchResult> zzaFw;
    
    public TurnBasedMatchInitiatedBinderCallbacks(zza.zzb<TurnBasedMultiplayer.InitiateMatchResult> paramzzb)
    {
      this.zzaFw = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzv(DataHolder paramDataHolder)
    {
      this.zzaFw.zzs(new GamesClientImpl.InitiateMatchResultImpl(paramDataHolder));
    }
  }
  
  private static final class TurnBasedMatchLeftBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<TurnBasedMultiplayer.LeaveMatchResult> zzaFx;
    
    public TurnBasedMatchLeftBinderCallbacks(zza.zzb<TurnBasedMultiplayer.LeaveMatchResult> paramzzb)
    {
      this.zzaFx = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzx(DataHolder paramDataHolder)
    {
      this.zzaFx.zzs(new GamesClientImpl.LeaveMatchResultImpl(paramDataHolder));
    }
  }
  
  private static final class TurnBasedMatchLoadedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<TurnBasedMultiplayer.LoadMatchResult> zzaFy;
    
    public TurnBasedMatchLoadedBinderCallbacks(zza.zzb<TurnBasedMultiplayer.LoadMatchResult> paramzzb)
    {
      this.zzaFy = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzu(DataHolder paramDataHolder)
    {
      this.zzaFy.zzs(new GamesClientImpl.LoadMatchResultImpl(paramDataHolder));
    }
  }
  
  private static abstract class TurnBasedMatchResult
    extends GamesClientImpl.GamesDataHolderResult
  {
    final TurnBasedMatch zzaES;
    
    /* Error */
    TurnBasedMatchResult(DataHolder paramDataHolder)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: invokespecial 13	com/google/android/gms/games/internal/GamesClientImpl$GamesDataHolderResult:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   5: new 15	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer
      //   8: dup
      //   9: aload_1
      //   10: invokespecial 16	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   13: astore_1
      //   14: aload_1
      //   15: invokevirtual 20	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer:getCount	()I
      //   18: ifle +28 -> 46
      //   21: aload_0
      //   22: aload_1
      //   23: iconst_0
      //   24: invokevirtual 24	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer:get	(I)Ljava/lang/Object;
      //   27: checkcast 26	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatch
      //   30: invokeinterface 30 1 0
      //   35: checkcast 26	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatch
      //   38: putfield 32	com/google/android/gms/games/internal/GamesClientImpl$TurnBasedMatchResult:zzaES	Lcom/google/android/gms/games/multiplayer/turnbased/TurnBasedMatch;
      //   41: aload_1
      //   42: invokevirtual 36	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer:release	()V
      //   45: return
      //   46: aload_0
      //   47: aconst_null
      //   48: putfield 32	com/google/android/gms/games/internal/GamesClientImpl$TurnBasedMatchResult:zzaES	Lcom/google/android/gms/games/multiplayer/turnbased/TurnBasedMatch;
      //   51: goto -10 -> 41
      //   54: astore_2
      //   55: aload_1
      //   56: invokevirtual 36	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer:release	()V
      //   59: aload_2
      //   60: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	61	0	this	TurnBasedMatchResult
      //   0	61	1	paramDataHolder	DataHolder
      //   54	6	2	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   14	41	54	finally
      //   46	51	54	finally
    }
    
    public TurnBasedMatch getMatch()
    {
      return this.zzaES;
    }
  }
  
  private static final class TurnBasedMatchUpdatedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<TurnBasedMultiplayer.UpdateMatchResult> zzaFz;
    
    public TurnBasedMatchUpdatedBinderCallbacks(zza.zzb<TurnBasedMultiplayer.UpdateMatchResult> paramzzb)
    {
      this.zzaFz = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzw(DataHolder paramDataHolder)
    {
      this.zzaFz.zzs(new GamesClientImpl.UpdateMatchResultImpl(paramDataHolder));
    }
  }
  
  private static final class TurnBasedMatchesLoadedBinderCallbacks
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<TurnBasedMultiplayer.LoadMatchesResult> zzaFA;
    
    public TurnBasedMatchesLoadedBinderCallbacks(zza.zzb<TurnBasedMultiplayer.LoadMatchesResult> paramzzb)
    {
      this.zzaFA = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzc(int paramInt, Bundle paramBundle)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      Status localStatus = GamesStatusCodes.zzgc(paramInt);
      this.zzaFA.zzs(new GamesClientImpl.LoadMatchesResultImpl(localStatus, paramBundle));
    }
  }
  
  private static final class UpdateAchievementResultImpl
    implements Achievements.UpdateAchievementResult
  {
    private final Status zzUX;
    private final String zzaDj;
    
    UpdateAchievementResultImpl(int paramInt, String paramString)
    {
      this.zzUX = GamesStatusCodes.zzgc(paramInt);
      this.zzaDj = paramString;
    }
    
    public String getAchievementId()
    {
      return this.zzaDj;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class UpdateMatchResultImpl
    extends GamesClientImpl.TurnBasedMatchResult
    implements TurnBasedMultiplayer.UpdateMatchResult
  {
    UpdateMatchResultImpl(DataHolder paramDataHolder)
    {
      super();
    }
  }
  
  private static final class UpdateRequestsResultImpl
    extends GamesClientImpl.GamesDataHolderResult
    implements Requests.UpdateRequestsResult
  {
    private final RequestUpdateOutcomes zzaFB;
    
    UpdateRequestsResultImpl(DataHolder paramDataHolder)
    {
      super();
      this.zzaFB = RequestUpdateOutcomes.zzab(paramDataHolder);
    }
    
    public Set<String> getRequestIds()
    {
      return this.zzaFB.getRequestIds();
    }
    
    public int getRequestOutcome(String paramString)
    {
      return this.zzaFB.getRequestOutcome(paramString);
    }
  }
  
  public static final class VideoAvailableResultImpl
    implements Videos.VideoAvailableResult
  {
    private final Status zzUX;
    private final boolean zzaFC;
    
    VideoAvailableResultImpl(Status paramStatus, boolean paramBoolean)
    {
      this.zzUX = paramStatus;
      this.zzaFC = paramBoolean;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  public static final class VideoCapabilitiesResultImpl
    implements Videos.VideoCapabilitiesResult
  {
    private final Status zzUX;
    private final VideoCapabilities zzaFD;
    
    VideoCapabilitiesResultImpl(Status paramStatus, VideoCapabilities paramVideoCapabilities)
    {
      this.zzUX = paramStatus;
      this.zzaFD = paramVideoCapabilities;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  private static final class VideoRecordingAvailableBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Videos.VideoAvailableResult> zzamC;
    
    VideoRecordingAvailableBinderCallback(zza.zzb<Videos.VideoAvailableResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zzd(int paramInt, boolean paramBoolean)
    {
      this.zzamC.zzs(new GamesClientImpl.VideoAvailableResultImpl(new Status(paramInt), paramBoolean));
    }
  }
  
  private static final class VideoRecordingCapabilitiesBinderCallback
    extends AbstractGamesCallbacks
  {
    private final zza.zzb<Videos.VideoCapabilitiesResult> zzamC;
    
    VideoRecordingCapabilitiesBinderCallback(zza.zzb<Videos.VideoCapabilitiesResult> paramzzb)
    {
      this.zzamC = ((zza.zzb)zzx.zzb(paramzzb, "Holder must not be null"));
    }
    
    public void zza(int paramInt, VideoCapabilities paramVideoCapabilities)
    {
      this.zzamC.zzs(new GamesClientImpl.VideoCapabilitiesResultImpl(new Status(paramInt), paramVideoCapabilities));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\internal\GamesClientImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */