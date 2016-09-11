package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.games.video.Videos.ListVideosResult;
import com.google.android.gms.games.video.Videos.VideoAvailableResult;
import com.google.android.gms.games.video.Videos.VideoCapabilitiesResult;

public final class VideosImpl
  implements Videos
{
  private static abstract class AvailableImpl
    extends Games.BaseGamesApiMethodImpl<Videos.VideoAvailableResult>
  {
    public Videos.VideoAvailableResult zzaO(final Status paramStatus)
    {
      new Videos.VideoAvailableResult()
      {
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
  
  private static abstract class CapabilitiesImpl
    extends Games.BaseGamesApiMethodImpl<Videos.VideoCapabilitiesResult>
  {
    public Videos.VideoCapabilitiesResult zzaP(final Status paramStatus)
    {
      new Videos.VideoCapabilitiesResult()
      {
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
  
  private static abstract class ListImpl
    extends Games.BaseGamesApiMethodImpl<Videos.ListVideosResult>
  {
    public Videos.ListVideosResult zzaQ(final Status paramStatus)
    {
      new Videos.ListVideosResult()
      {
        public Status getStatus()
        {
          return paramStatus;
        }
      };
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\internal\api\VideosImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */