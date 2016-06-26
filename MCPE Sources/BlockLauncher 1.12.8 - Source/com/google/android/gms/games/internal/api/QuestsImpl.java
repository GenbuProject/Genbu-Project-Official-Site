package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.quest.Quests.AcceptQuestResult;
import com.google.android.gms.games.quest.Quests.ClaimMilestoneResult;
import com.google.android.gms.games.quest.Quests.LoadQuestsResult;

public final class QuestsImpl
  implements Quests
{
  public PendingResult<Quests.AcceptQuestResult> accept(GoogleApiClient paramGoogleApiClient, final String paramString)
  {
    paramGoogleApiClient.zzb(new AcceptImpl(paramGoogleApiClient, paramString)
    {
      protected void zza(GamesClientImpl paramAnonymousGamesClientImpl)
        throws RemoteException
      {
        paramAnonymousGamesClientImpl.zzh(this, paramString);
      }
    });
  }
  
  public PendingResult<Quests.ClaimMilestoneResult> claim(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2)
  {
    paramGoogleApiClient.zzb(new ClaimImpl(paramGoogleApiClient, paramString1)
    {
      protected void zza(GamesClientImpl paramAnonymousGamesClientImpl)
        throws RemoteException
      {
        paramAnonymousGamesClientImpl.zzb(this, paramString1, paramString2);
      }
    });
  }
  
  public Intent getQuestIntent(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    return Games.zzh(paramGoogleApiClient).zzdI(paramString);
  }
  
  public Intent getQuestsIntent(GoogleApiClient paramGoogleApiClient, int[] paramArrayOfInt)
  {
    return Games.zzh(paramGoogleApiClient).zzb(paramArrayOfInt);
  }
  
  public PendingResult<Quests.LoadQuestsResult> load(GoogleApiClient paramGoogleApiClient, final int[] paramArrayOfInt, final int paramInt, final boolean paramBoolean)
  {
    paramGoogleApiClient.zza(new LoadsImpl(paramGoogleApiClient, paramArrayOfInt)
    {
      protected void zza(GamesClientImpl paramAnonymousGamesClientImpl)
        throws RemoteException
      {
        paramAnonymousGamesClientImpl.zza(this, paramArrayOfInt, paramInt, paramBoolean);
      }
    });
  }
  
  public PendingResult<Quests.LoadQuestsResult> loadByIds(GoogleApiClient paramGoogleApiClient, final boolean paramBoolean, final String... paramVarArgs)
  {
    paramGoogleApiClient.zza(new LoadsImpl(paramGoogleApiClient, paramBoolean)
    {
      protected void zza(GamesClientImpl paramAnonymousGamesClientImpl)
        throws RemoteException
      {
        paramAnonymousGamesClientImpl.zzb(this, paramBoolean, paramVarArgs);
      }
    });
  }
  
  public void registerQuestUpdateListener(GoogleApiClient paramGoogleApiClient, QuestUpdateListener paramQuestUpdateListener)
  {
    GamesClientImpl localGamesClientImpl = Games.zzb(paramGoogleApiClient, false);
    if (localGamesClientImpl != null) {
      localGamesClientImpl.zzc(paramGoogleApiClient.zzr(paramQuestUpdateListener));
    }
  }
  
  public void showStateChangedPopup(GoogleApiClient paramGoogleApiClient, String paramString)
  {
    paramGoogleApiClient = Games.zzb(paramGoogleApiClient, false);
    if (paramGoogleApiClient != null) {
      paramGoogleApiClient.zzdJ(paramString);
    }
  }
  
  public void unregisterQuestUpdateListener(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient = Games.zzb(paramGoogleApiClient, false);
    if (paramGoogleApiClient != null) {
      paramGoogleApiClient.zzwF();
    }
  }
  
  private static abstract class AcceptImpl
    extends Games.BaseGamesApiMethodImpl<Quests.AcceptQuestResult>
  {
    private AcceptImpl(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Quests.AcceptQuestResult zzaw(final Status paramStatus)
    {
      new Quests.AcceptQuestResult()
      {
        public Quest getQuest()
        {
          return null;
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
  
  private static abstract class ClaimImpl
    extends Games.BaseGamesApiMethodImpl<Quests.ClaimMilestoneResult>
  {
    private ClaimImpl(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Quests.ClaimMilestoneResult zzax(final Status paramStatus)
    {
      new Quests.ClaimMilestoneResult()
      {
        public Milestone getMilestone()
        {
          return null;
        }
        
        public Quest getQuest()
        {
          return null;
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
  
  private static abstract class LoadsImpl
    extends Games.BaseGamesApiMethodImpl<Quests.LoadQuestsResult>
  {
    private LoadsImpl(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Quests.LoadQuestsResult zzay(final Status paramStatus)
    {
      new Quests.LoadQuestsResult()
      {
        public QuestBuffer getQuests()
        {
          return new QuestBuffer(DataHolder.zzbI(paramStatus.getStatusCode()));
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\internal\api\QuestsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */