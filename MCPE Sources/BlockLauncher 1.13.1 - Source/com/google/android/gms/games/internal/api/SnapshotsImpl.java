package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange.Builder;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult;
import com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult;
import com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult;
import com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult;

public final class SnapshotsImpl
  implements Snapshots
{
  public PendingResult<Snapshots.CommitSnapshotResult> commitAndClose(GoogleApiClient paramGoogleApiClient, final Snapshot paramSnapshot, final SnapshotMetadataChange paramSnapshotMetadataChange)
  {
    paramGoogleApiClient.zzb(new CommitImpl(paramGoogleApiClient, paramSnapshot)
    {
      protected void zza(GamesClientImpl paramAnonymousGamesClientImpl)
        throws RemoteException
      {
        paramAnonymousGamesClientImpl.zza(this, paramSnapshot, paramSnapshotMetadataChange);
      }
    });
  }
  
  public PendingResult<Snapshots.DeleteSnapshotResult> delete(GoogleApiClient paramGoogleApiClient, final SnapshotMetadata paramSnapshotMetadata)
  {
    paramGoogleApiClient.zzb(new DeleteImpl(paramGoogleApiClient, paramSnapshotMetadata)
    {
      protected void zza(GamesClientImpl paramAnonymousGamesClientImpl)
        throws RemoteException
      {
        paramAnonymousGamesClientImpl.zzi(this, paramSnapshotMetadata.getSnapshotId());
      }
    });
  }
  
  public void discardAndClose(GoogleApiClient paramGoogleApiClient, Snapshot paramSnapshot)
  {
    Games.zzh(paramGoogleApiClient).zza(paramSnapshot);
  }
  
  public int getMaxCoverImageSize(GoogleApiClient paramGoogleApiClient)
  {
    return Games.zzh(paramGoogleApiClient).zzwQ();
  }
  
  public int getMaxDataSize(GoogleApiClient paramGoogleApiClient)
  {
    return Games.zzh(paramGoogleApiClient).zzwP();
  }
  
  public Intent getSelectSnapshotIntent(GoogleApiClient paramGoogleApiClient, String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    return Games.zzh(paramGoogleApiClient).zza(paramString, paramBoolean1, paramBoolean2, paramInt);
  }
  
  public SnapshotMetadata getSnapshotFromBundle(Bundle paramBundle)
  {
    if ((paramBundle == null) || (!paramBundle.containsKey("com.google.android.gms.games.SNAPSHOT_METADATA"))) {
      return null;
    }
    return (SnapshotMetadata)paramBundle.getParcelable("com.google.android.gms.games.SNAPSHOT_METADATA");
  }
  
  public PendingResult<Snapshots.LoadSnapshotsResult> load(GoogleApiClient paramGoogleApiClient, final boolean paramBoolean)
  {
    paramGoogleApiClient.zza(new LoadImpl(paramGoogleApiClient, paramBoolean)
    {
      protected void zza(GamesClientImpl paramAnonymousGamesClientImpl)
        throws RemoteException
      {
        paramAnonymousGamesClientImpl.zzf(this, paramBoolean);
      }
    });
  }
  
  public PendingResult<Snapshots.OpenSnapshotResult> open(GoogleApiClient paramGoogleApiClient, SnapshotMetadata paramSnapshotMetadata)
  {
    return open(paramGoogleApiClient, paramSnapshotMetadata.getUniqueName(), false);
  }
  
  public PendingResult<Snapshots.OpenSnapshotResult> open(GoogleApiClient paramGoogleApiClient, SnapshotMetadata paramSnapshotMetadata, int paramInt)
  {
    return open(paramGoogleApiClient, paramSnapshotMetadata.getUniqueName(), false, paramInt);
  }
  
  public PendingResult<Snapshots.OpenSnapshotResult> open(GoogleApiClient paramGoogleApiClient, String paramString, boolean paramBoolean)
  {
    return open(paramGoogleApiClient, paramString, paramBoolean, -1);
  }
  
  public PendingResult<Snapshots.OpenSnapshotResult> open(GoogleApiClient paramGoogleApiClient, final String paramString, final boolean paramBoolean, final int paramInt)
  {
    paramGoogleApiClient.zzb(new OpenImpl(paramGoogleApiClient, paramString)
    {
      protected void zza(GamesClientImpl paramAnonymousGamesClientImpl)
        throws RemoteException
      {
        paramAnonymousGamesClientImpl.zza(this, paramString, paramBoolean, paramInt);
      }
    });
  }
  
  public PendingResult<Snapshots.OpenSnapshotResult> resolveConflict(GoogleApiClient paramGoogleApiClient, String paramString, Snapshot paramSnapshot)
  {
    SnapshotMetadata localSnapshotMetadata = paramSnapshot.getMetadata();
    SnapshotMetadataChange localSnapshotMetadataChange = new SnapshotMetadataChange.Builder().fromMetadata(localSnapshotMetadata).build();
    return resolveConflict(paramGoogleApiClient, paramString, localSnapshotMetadata.getSnapshotId(), localSnapshotMetadataChange, paramSnapshot.getSnapshotContents());
  }
  
  public PendingResult<Snapshots.OpenSnapshotResult> resolveConflict(GoogleApiClient paramGoogleApiClient, final String paramString1, final String paramString2, final SnapshotMetadataChange paramSnapshotMetadataChange, final SnapshotContents paramSnapshotContents)
  {
    paramGoogleApiClient.zzb(new OpenImpl(paramGoogleApiClient, paramString1)
    {
      protected void zza(GamesClientImpl paramAnonymousGamesClientImpl)
        throws RemoteException
      {
        paramAnonymousGamesClientImpl.zza(this, paramString1, paramString2, paramSnapshotMetadataChange, paramSnapshotContents);
      }
    });
  }
  
  private static abstract class CommitImpl
    extends Games.BaseGamesApiMethodImpl<Snapshots.CommitSnapshotResult>
  {
    private CommitImpl(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Snapshots.CommitSnapshotResult zzaD(final Status paramStatus)
    {
      new Snapshots.CommitSnapshotResult()
      {
        public SnapshotMetadata getSnapshotMetadata()
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
  
  private static abstract class DeleteImpl
    extends Games.BaseGamesApiMethodImpl<Snapshots.DeleteSnapshotResult>
  {
    private DeleteImpl(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Snapshots.DeleteSnapshotResult zzaE(final Status paramStatus)
    {
      new Snapshots.DeleteSnapshotResult()
      {
        public String getSnapshotId()
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
  
  private static abstract class LoadImpl
    extends Games.BaseGamesApiMethodImpl<Snapshots.LoadSnapshotsResult>
  {
    private LoadImpl(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Snapshots.LoadSnapshotsResult zzaF(final Status paramStatus)
    {
      new Snapshots.LoadSnapshotsResult()
      {
        public SnapshotMetadataBuffer getSnapshots()
        {
          return new SnapshotMetadataBuffer(DataHolder.zzbI(14));
        }
        
        public Status getStatus()
        {
          return paramStatus;
        }
        
        public void release() {}
      };
    }
  }
  
  private static abstract class OpenImpl
    extends Games.BaseGamesApiMethodImpl<Snapshots.OpenSnapshotResult>
  {
    private OpenImpl(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    public Snapshots.OpenSnapshotResult zzaG(final Status paramStatus)
    {
      new Snapshots.OpenSnapshotResult()
      {
        public String getConflictId()
        {
          return null;
        }
        
        public Snapshot getConflictingSnapshot()
        {
          return null;
        }
        
        public SnapshotContents getResolutionSnapshotContents()
        {
          return null;
        }
        
        public Snapshot getSnapshot()
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
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\internal\api\SnapshotsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */